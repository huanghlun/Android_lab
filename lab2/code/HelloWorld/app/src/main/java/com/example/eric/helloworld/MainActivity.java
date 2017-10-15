package com.example.eric.helloworld;

import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    class clickImage implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            final AlertDialog.Builder alertDialogBuilder=new AlertDialog.Builder(MainActivity.this);
            alertDialogBuilder.create();
            alertDialogBuilder.setTitle("上传图像");
            final String[] Items={"拍摄","从相册选择"};
            alertDialogBuilder.setItems(Items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    switch (i) {
                        case 0:
                            Toast.makeText(MainActivity.this, "您选择了[拍摄]", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(MainActivity.this, "您选择了[从相册中选择]", Toast.LENGTH_SHORT).show();
                            break;
                        default:
                            Toast.makeText(MainActivity.this, "您选择了[拍摄]", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            alertDialogBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "您选择了[取消]", Toast.LENGTH_SHORT).show();
                }
            });

            alertDialogBuilder.show();
        }
    }

    class changeRadio implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            String temp = null;
            RadioButton rb1 = (RadioButton) findViewById(R.id.radioButton);
            RadioButton rb2 = (RadioButton) findViewById(R.id.radioButton2);
            if(rb1.getId() == checkedId){
                temp = rb1.getText().toString(); //取得单选文本
            }
            if(rb2.getId() == checkedId){
                temp = rb2.getText().toString();   //取得单选文本
            }

            temp = "您选择了"+temp;      //设置文本信息
            final Snackbar snack = Snackbar.make(group, temp, Snackbar.LENGTH_SHORT);
            snack.setAction("确定", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snack.dismiss();
                }
            })
            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
            .setDuration(5000)
            .show();

        }
    }

    class clickLogin implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            EditText e1 = (EditText) findViewById(R.id.edittext1);
            String str1 = e1.getText().toString();
            EditText e2 = (EditText) findViewById(R.id.edittext2);
            String str2 = e2.getText().toString();
            String temp = null;
            if(TextUtils.isEmpty(str1)){
                temp = "学号不能为空";
                TextInputLayout t = (TextInputLayout) findViewById(R.id.textinput1);
                t.setErrorEnabled(true);
                t.setError(temp);
            }
            else{
                if(TextUtils.isEmpty(str2)){
                    temp = "密码不能为空";
                    TextInputLayout t = (TextInputLayout) findViewById(R.id.textinput2);
                    t.setErrorEnabled(true);
                    t.setError(temp);
                }
                else {
                    TextInputLayout t = (TextInputLayout) findViewById(R.id.textinput2);
                    t.setErrorEnabled(false);
                    TextInputLayout t1 = (TextInputLayout) findViewById(R.id.textinput1);
                    t1.setErrorEnabled(false);
                    if(str1.equals("123456") && str2.equals("6666")) {
                        temp = "登录成功";
                    }
                    else {
                        temp = "学号或密码错误";
                    }
                    final Snackbar snack = Snackbar.make(v, temp, Snackbar.LENGTH_SHORT);
                    snack.setAction("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            snack.dismiss();
                        }
                    })
                            .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                            .setDuration(5000)
                            .show();
                }
            }
        }
    }

    class clickSign implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String temp = null;
            RadioGroup rg1 = (RadioGroup) findViewById(R.id.button0);
            RadioButton radioButton = (RadioButton)findViewById(rg1.getCheckedRadioButtonId());
            String selectText = radioButton.getText().toString();

            temp = selectText + "注册功能尚未启用";
            if(selectText.equals("学生")){
                TextInputLayout t = (TextInputLayout) findViewById(R.id.textinput1);
                final Snackbar snack = Snackbar.make(v, temp, Snackbar.LENGTH_SHORT);
                snack.setAction("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snack.dismiss();
                    }
                })
                        .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                        .setDuration(5000)
                        .show();
            }
            else{
                Toast.makeText(MainActivity.this, temp, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View mImage;
        mImage = (ImageView) findViewById(R.id.imageView);
        mImage.setOnClickListener(new clickImage());

        RadioGroup mRadioGroup = (RadioGroup) findViewById(R.id.button0);
        mRadioGroup.setOnCheckedChangeListener(new changeRadio());

        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setOnClickListener(new clickLogin());

        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setOnClickListener(new clickSign());
    }
}
