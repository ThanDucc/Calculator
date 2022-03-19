package com.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String str = "";
    private Button number0, number1, number2, number3, number4, number5, number6, number7,
            number8, number9, Add, Sub, Mul, Div, Clear, Ac, open, close, floating, Result;
    private TextView show_result, express;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clickListen();
    }

    protected void init() {
        show_result = findViewById(R.id.resultShow);
        express = findViewById(R.id.exp);

        number0 = findViewById(R.id.Number0);
        number1 = findViewById(R.id.Number1);
        number2 = findViewById(R.id.Number2);
        number3 = findViewById(R.id.Number3);
        number4 = findViewById(R.id.Number4);
        number5 = findViewById(R.id.Number5);
        number6 = findViewById(R.id.Number6);
        number7 = findViewById(R.id.Number7);
        number8 = findViewById(R.id.Number8);
        number9 = findViewById(R.id.Number9);
        Add = findViewById(R.id.Add);
        Sub = findViewById(R.id.Subtract);
        Mul = findViewById(R.id.Multiple);
        Div = findViewById(R.id.Division);
        Clear = findViewById(R.id.Clear);
        Ac = findViewById(R.id.AC);
        open = findViewById(R.id.open);
        close = findViewById(R.id.close);
        floating = findViewById(R.id.floating_point);
        Result = findViewById(R.id.result);
    }

    protected void clickListen() {
        number0.setOnClickListener(view -> {
            str = checkString(str);
            str += "0";
            express.setText(str);
        });

        number1.setOnClickListener(view -> {
            str = checkString(str);
            str += "1";
            express.setText(str);
        });

        number2.setOnClickListener(view -> {
            str = checkString(str);
            str += "2";
            express.setText(str);
        });

        number3.setOnClickListener(view -> {
            str = checkString(str);
            str += "3";
            express.setText(str);
        });

        number4.setOnClickListener(view -> {
            str = checkString(str);
            str += "4";
            express.setText(str);
        });

        number5.setOnClickListener(view -> {
            str = checkString(str);
            str += "5";
            express.setText(str);
        });

        number6.setOnClickListener(view -> {
            str = checkString(str);
            str += "6";
            express.setText(str);
        });

        number7.setOnClickListener(view -> {
            str = checkString(str);
            str += "7";
            express.setText(str);
        });

        number8.setOnClickListener(view -> {
            str = checkString(str);
            str += "8";
            express.setText(str);
        });

        number9.setOnClickListener(view -> {
            str = checkString(str);
            str += "9";
            express.setText(str);
        });

        Add.setOnClickListener(view -> {
            str += " + ";
            express.setText(str);
        });

        Sub.setOnClickListener(view -> {
            str += " - ";
            express.setText(str);
        });

        Mul.setOnClickListener(view -> {
            str += " x ";
            express.setText(str);
        });

        Div.setOnClickListener(view -> {
            str += " ÷ ";
            express.setText(str);
        });

        Clear.setOnClickListener(view -> {
            if (!str.isEmpty()) {
                str = str.substring(0, str.length() - 1);
                str = str.trim();
                express.setText(str);
            }
        });

        Ac.setOnClickListener(view -> {
            str = "";
            express.setText(str);
        });

        open.setOnClickListener(view -> {
            str += " ( ";
            express.setText(str);
        });

        close.setOnClickListener(view -> {
            str += " ) ";
            express.setText(str);
        });

        floating.setOnClickListener(view -> {
            int count = 0;
            boolean check = false;
            if (!str.isEmpty()) {
                for (int i = 0; i < str.length(); i++) {
                    if (str.charAt(i) == '+' || str.charAt(i) == '-' || str.charAt(i) == 'x'
                            || str.charAt(i) == '÷' || str.charAt(i) == '(' || str.charAt(i) == ')') {
                        count = 0;
                    }
                    if (str.charAt(i) == '.') {
                        count++;
                    }
                    check = count == 1;
                }
            }
            if (!check) {
                str += '.';
            }
            express.setText(str);
        });

        Result.setOnClickListener(view -> {
            if (!str.isEmpty() && str.charAt(0) == '.') {
                Toast.makeText(MainActivity.this, "Expression error.", Toast.LENGTH_SHORT).show();
            }
            if (!str.isEmpty() && str.charAt(0) != '.') {
                FullCalculator calc = new FullCalculator();
                String result = calc.processInput(str);
                if (result.equals("error")) {
                    Toast.makeText(MainActivity.this, "Expression error.", Toast.LENGTH_SHORT).show();
                } else {
                    String s = str + " = ";
                    express.setText(s);
                    str = result;
                    show_result.setText(result);
                }
            } else {
                show_result.setText("");
            }
        });
    }

    public String checkString(String str) {
        if (!str.isEmpty()) {
            if (str.charAt(str.length() - 1) == '+' || str.charAt(str.length() - 1) == '-'
                    || str.charAt(str.length() - 1) == 'x' || str.charAt(str.length() - 1) == '÷'
                    || str.charAt(str.length() - 1) == '(' || str.charAt(str.length() - 1) == ')') {
                str += " ";
            }
        }
        return str;
    }

}

