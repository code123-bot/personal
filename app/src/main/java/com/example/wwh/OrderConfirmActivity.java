package com.example.wwh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finalhomework_lienyu.R;

public class OrderConfirmActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirm);

        // 接收TicketActivity传递的数据
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        String category = intent.getStringExtra("category");
        int quantity = intent.getIntExtra("quantity", 1);

        // 显示数据（示例）
        TextView tvInfo = findViewById(R.id.tv_order_info);
        tvInfo.setText(String.format(
                "订单详情：\n日期：%s\n时段：%s\n票种：%s\n数量：%d",
                date, time, category, quantity
        ));
    }
}
