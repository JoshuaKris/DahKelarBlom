package com.example.dahkelarblom.view.menuUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dahkelarblom.BaseVMF;
import com.example.dahkelarblom.DialogChooseFragment;
import com.example.dahkelarblom.model.responses.ViewAllMerchants;
import com.example.dahkelarblom.utils.BaseActivity;
import com.example.dahkelarblom.utils.Constants;
import com.example.dahkelarblom.utils.HeaderFragment;
import com.example.dahkelarblom.popup.PopupSuccessFragment;
import com.example.dahkelarblom.R;
import com.example.dahkelarblom.model.DialogItem;
import com.example.dahkelarblom.utils.Loading;
import com.google.gson.JsonObject;
import com.tom_roush.pdfbox.pdmodel.PDDocument;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class OrderActivity extends BaseActivity implements DialogChooseFragment.OnInputListener {

    private HeaderFragment headerFragment;
    private ImageButton ib_backButton;
    private RelativeLayout rl_order_choose, rl_fileChooser;
    private Button bt_order, bt_uploadFile;
    private TextView tv_price_estimation, tv_file_name, tv_file_pages, tv_hint_order_choose;
    private EditText et_bioName, et_bioPhoneNum, et_bioDate, et_bioAddInfo;

    private final int GET_PDF_KEY = 0;
    private static final int PERMSISSION_REQUEST = 100;
    private String dialogInput = "";
    private int tHour,tMinute;
    private ViewAllMerchants merchantThis;

    private final ArrayList<DialogItem> dialogItemList = new ArrayList<>();
    private DialogChooseFragment dialogChooseFragment;
    private OrderViewModel orderViewModel;

    private PopupSuccessFragment popupSuccessFragment;
    private final PopupSuccessFragment.PopupListener popupListener = isClicked -> {
       if (isClicked) {
           finish();
       }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        BaseVMF factory = new BaseVMF<>(new OrderViewModel(OrderActivity.this));
        orderViewModel = ViewModelProviders.of(this,factory).get(OrderViewModel.class);
        merchantThis = getIntent().getParcelableExtra("merchant_detail");
        initLiveData();

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.f_header);
        ib_backButton = Objects.requireNonNull(headerFragment.getView()).findViewById(R.id.ib_backButton);
        rl_order_choose = findViewById(R.id.rl_order_choose);
        rl_fileChooser = findViewById(R.id.rl_fileChooser);
        tv_hint_order_choose = findViewById(R.id.tv_hint_order_choose);
        tv_price_estimation = findViewById(R.id.tv_priceEstimation);
        bt_order = findViewById(R.id.bt_order);
        bt_uploadFile = findViewById(R.id.bt_uploadFile);
        tv_file_name = findViewById(R.id.tv_fileName);
        tv_file_pages = findViewById(R.id.tv_filePages);
        et_bioName = findViewById(R.id.et_bioName);
        et_bioPhoneNum = findViewById(R.id.et_bioPhoneNum);
        et_bioDate = findViewById(R.id.et_bioDate);
        et_bioAddInfo = findViewById(R.id.et_bioAddInfo);

        headerFragment.headerV2("Order",false,false);
        tv_hint_order_choose.setText(DEFAULT_ORDER_TEXT_KEY);

        ib_backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        rl_order_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogChooseFragment = DialogChooseFragment.newInstance(Constants.order,"Jenis Order");
                dialogChooseFragment.show(getSupportFragmentManager(), "dialogChooseOrder");
                dialogChooseFragment.setListener(OrderActivity.this);
            }
        });

        bt_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEquals(tv_hint_order_choose,DEFAULT_ORDER_TEXT_KEY) && isNotEmpty(et_bioName) &&
                    isNotEmpty(et_bioPhoneNum) && isNotEmpty(et_bioDate) && isNotEmpty(et_bioAddInfo)) {
                    JsonObject object = new JsonObject();
                    object.addProperty("jnsOrder",tv_hint_order_choose.getText().toString());
                    object.addProperty("keteranganOrder",et_bioAddInfo.getText().toString());
                    object.addProperty("username",et_bioName.getText().toString());
                    object.addProperty("noHp",et_bioPhoneNum.getText().toString());
                    object.addProperty("pengambilanOrder",et_bioDate.getText().toString());
                    object.addProperty("merchantId",merchantThis.getIdmerchant());
                    object.addProperty("status","file belum diterima");
                    orderViewModel.postMyOrder(object);
                }
                else {
                    Toast.makeText(OrderActivity.this, "Data masih ada yang kosong, silakan isi dahulu yang masih kosong.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        et_bioDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog dialog = new TimePickerDialog(
                        OrderActivity.this,
                        R.style.TimePicker,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                tHour = hourOfDay;
                                tMinute = minute;
                                String time = tHour+":"+tMinute;
                                SimpleDateFormat f24hours = new SimpleDateFormat("HH:mm", Locale.getDefault());
                                try {
                                    Date date = f24hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa",Locale.getDefault());
                                    et_bioDate.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                            }
                        },12,0,false);
                dialog.updateTime(tHour,tMinute);
                dialog.show();
            }
        });

        //hide upload button
        rl_fileChooser.setVisibility(View.GONE);
        bt_uploadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });
    }

    private void initLiveData() {
        orderViewModel.getUserOrder().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s != null) {
                    popupSuccessFragment = PopupSuccessFragment.newInstance(s,merchantThis.getMerchantEmail());
                    popupSuccessFragment.show(getSupportFragmentManager(), "popupSuccess");
                    popupSuccessFragment.setListener(popupListener);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_PDF_KEY && resultCode==RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            uploadFile(uri);
            try {
                PDDocument doc = PDDocument.load(getContentResolver().openInputStream(uri));
                int pages = doc.getNumberOfPages();
                setPages(pages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMSISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //if granted
                } else {
                    //if denied
                }
            }
        }
    }

    private void showLoading(Boolean isLoading) {
        WebView webView = findViewById(R.id.htmlloading);
        webView.setBackgroundColor(Color.TRANSPARENT);
        Loading loading = new Loading(webView);
//        if (isLoading) {
//            layoutLoading.setVisibility(View.VISIBLE);
//            ll_data_filter.setVisibility(View.GONE);
//            loading.start();
//        } else {
//            layoutLoading.setVisibility(View.GONE);
//            ll_data_filter.setVisibility(View.VISIBLE);
//            loading.close();
//        }
    }

    private void chooseFile() {
        if (ContextCompat.checkSelfPermission(OrderActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(OrderActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMSISSION_REQUEST);
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("application/pdf");
        startActivityForResult(Intent.createChooser(intent,"Select your file to upload"),GET_PDF_KEY);
    }

    private void uploadFile(Uri uri) {
        File file = new File(Objects.requireNonNull(uri.getPath()));
        String strFileName = file.getName();
        String mimeType = getContentResolver().getType(uri);
        Cursor returnCursor = getContentResolver().query(uri, null, null, null, null);
        /*
         * Get the column indexes of the data in the Cursor,
         * move to the first row in the Cursor, get the data,
         * and display it.
         */
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        int sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE);
        returnCursor.moveToFirst();

        tv_file_name.setText(returnCursor.getString(nameIndex));
        tv_file_name.setVisibility(View.VISIBLE);
    }

    private void setPages(int pages) {
        tv_price_estimation.setText(String.format("Harga Estimasi : Rp.%1$s",pages*1000));
        tv_price_estimation.setVisibility(View.VISIBLE);
        tv_file_pages.setText(String.format("Number of Page : %1$s",pages));
        tv_file_pages.setVisibility(View.VISIBLE);
    }

    @Override
    public void sendInput(String input) {
       if (!dialogInput.equalsIgnoreCase(input)) {
           dialogInput = input;
           tv_hint_order_choose.setText(dialogInput);
           tv_hint_order_choose.setTextColor(getColor(R.color.textColorLabel));
       }
    }
}
