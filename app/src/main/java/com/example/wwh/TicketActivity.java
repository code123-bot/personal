package com.example.wwh;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finalhomework_lienyu.R;

import java.util.Calendar;

public class TicketActivity extends AppCompatActivity {
    private EditText editTextDate, editTextNumber;
    private RadioGroup radioGroupTime, radioGroupCategory;
    private TextView textViewTicketsLeft;
    private Button buttonCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);

        initViews();

        setupDatePicker();

        setupButton();

        loadTicketsLeft();
    }

    private void initViews() {
        editTextDate = findViewById(R.id.editText_ticket_Date);
        editTextNumber = findViewById(R.id.editTextNumber_ticket);
        radioGroupTime = findViewById(R.id.radioGroup_ticket_time);
        radioGroupCategory = findViewById(R.id.radioGroup_ticket_category);
        textViewTicketsLeft = findViewById(R.id.textView_tickets_left);
        buttonCommit = findViewById(R.id.button_ticket_commit);

        // 默认选中上午和成人票
        radioGroupTime.check(R.id.radioButton_am);
        radioGroupCategory.check(R.id.radioButton_adult);
    }

    private void setupDatePicker() {
        editTextDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
                String date = year + "-" + (month + 1) + "-" + dayOfMonth;
                editTextDate.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
        });
    }

    private void loadTicketsLeft() {
        // 模拟网络请求获取余票
        new Handler().postDelayed(() -> {
            textViewTicketsLeft.setText("余票数量：128张");
        }, 1000);
    }

    private void setupButton() {
        buttonCommit.setOnClickListener(v -> {
            // 验证输入
            if (editTextDate.getText().toString().isEmpty()) {
                Toast.makeText(this, "请选择日期", Toast.LENGTH_SHORT).show();
                return;
            }

            if (editTextNumber.getText().toString().isEmpty() ||
                    Integer.parseInt(editTextNumber.getText().toString()) <= 0) {
                Toast.makeText(this, "请输入有效数量", Toast.LENGTH_SHORT).show();
                return;
            }

            // 获取用户选择
            String date = editTextDate.getText().toString();
            String time = radioGroupTime.getCheckedRadioButtonId() == R.id.radioButton_am ? "上午" : "下午";
            String category = radioGroupCategory.getCheckedRadioButtonId() == R.id.radioButton_adult ? "成人票" : "学生票";
            int quantity = Integer.parseInt(editTextNumber.getText().toString());

            // 跳转到订单确认页（示例）
            Intent intent = new Intent(this, OrderConfirmActivity.class);
            intent.putExtra("date", date);
            intent.putExtra("time", time);
            intent.putExtra("category", category);
            intent.putExtra("quantity", quantity);
            startActivity(intent);
        });
    }

    // 处理ActionBar返回按钮
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}