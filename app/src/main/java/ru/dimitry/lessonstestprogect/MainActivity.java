package ru.dimitry.lessonstestprogect;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.snackbar.Snackbar;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    TextView textViewForEditText;
    TextView textViewForButton;
    TextView textViewForToast;
    TextView textViewForSnackbar;
    TextView textViewForCheckbox;
    TextView textViewForToggleButton;
    EditText editText;
    Button button;
    Button buttonForToast;
    Button buttonForSnackbar;
    CheckBox checkboxR;
    CheckBox checkboxG;
    CheckBox checkboxB;
    ToggleButton toggleButton;
    RadioGroup radioGroup;
    RadioButton rBut1;
    RadioButton rBut2;

    public void init() {
        textViewForEditText = findViewById(R.id.text_view_for_edit_text_id);
        textViewForButton = findViewById(R.id.text_view_for_button_id);
        textViewForToast = findViewById(R.id.text_view_for_toast_id);
        textViewForSnackbar = findViewById(R.id.text_view_for_snackbar_id);
        textViewForCheckbox = findViewById(R.id.text_view_for_checkbox_id);
        textViewForToggleButton = findViewById(R.id.text_view_for_toggle_button_id);
        editText = findViewById(R.id.edit_text_id);
        button = findViewById(R.id.button_id);
        buttonForToast = findViewById(R.id.button_for_toast_id);
        buttonForSnackbar = findViewById(R.id.button_for_snackbar_id);
        checkboxR = findViewById(R.id.checkbox_r_id);
        checkboxG = findViewById(R.id.checkbox_g_id);
        checkboxB = findViewById(R.id.checkbox_b_id);
        toggleButton = findViewById(R.id.toggle_button_id);
        radioGroup = findViewById(R.id.radio_group_id);
        rBut1 = findViewById(R.id.radio_button_1_id);
        rBut2 = findViewById(R.id.radio_button_2_id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        /**
         * onTextChanged заменяет текст в TextView,  на тот что написан в EditText
         */
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editText.length() != 0) {
                    textViewForEditText.setText(s);
                } else {
                    textViewForEditText.setText(R.string.text_for_edit_text_tv);
                }
            }
        });
        AtomicBoolean buttonIsPressed = new AtomicBoolean(false);
        /**
         * button заменяет текст в TextView, переключая его на 2 варианта
         */
        button.setOnClickListener(View -> {
            if (!buttonIsPressed.get()) {
                textViewForButton.setText(R.string.text_fot_button_tv_press);
                buttonIsPressed.set(true);
            } else {
                textViewForButton.setText(R.string.text_fot_button_tv);
                buttonIsPressed.set(false);
            }
        });
        /**
         * button вызывает Toast с сообщением
         */
        buttonForToast.setOnClickListener(View -> {
            Toast toast = makeText(this, R.string.text_for_toast_message, Toast.LENGTH_SHORT);
            //toast.setGravity(Gravity.TOP,0,0);
            toast.show();
        });
        /**
         * button вызывает Snackbar с текстом. В Snackbar есть button, который заменяет в TextView текст, на 2 варианта
         */
        AtomicBoolean snackbarIsClick = new AtomicBoolean(false);
        buttonForSnackbar.setOnClickListener(View -> {
            Snackbar snackbar = Snackbar.make(View, R.string.text_for_snackbar_message, Snackbar.LENGTH_SHORT);

            snackbar.setAction(R.string.text_for_snackbar_button, new View.OnClickListener() {
                @Override
                public void onClick(android.view.View v) {

                    if (!snackbarIsClick.get()) {
                        textViewForSnackbar.setText(R.string.text_for_snackbar_on);
                        snackbarIsClick.set(true);
                    } else {
                        textViewForSnackbar.setText(R.string.text_for_snackbar_off);
                        snackbarIsClick.set(false);
                    }
                }
            });
            snackbar.show();
        });
        /**
         *разная комбинация Checkbox меняет bg у TextView на цвет или комбинацию цветов
         */
        checkboxR.setOnClickListener(View -> {
            setRGBBgCheckbox();
        });
        checkboxG.setOnClickListener(View -> {
            setRGBBgCheckbox();
        });
        checkboxB.setOnClickListener(View -> {
            setRGBBgCheckbox();
        });
        /**
         *
         */
        toggleButton.setOnClickListener(View -> {
            boolean toggleButtonIsCheck = toggleButton.isChecked();
            if (toggleButtonIsCheck) {
                textViewForToggleButton.setText(R.string.text_for_toggle_button_is_press);
            } else {
                textViewForToggleButton.setText(R.string.text_for_toggle_button_is_not_press);
            }
        });

        rBut1.setOnClickListener((view)->onRadioButtonClicked(view));
        rBut2.setOnClickListener((view)->onRadioButtonClicked(view));

    }

    public void setRGBBgCheckbox() {
        int r = 0, g = 0, b = 0;
        if (checkboxR.isChecked()) {
            r = 255;
        }
        if (checkboxG.isChecked()) {
            g = 255;
        }
        if (checkboxB.isChecked()) {
            b = 255;
        }
        if (r == 0 && g == 0 & b == 0) {
            textViewForCheckbox.setBackgroundResource(R.color.grey_panel);
        } else {
            textViewForCheckbox.setBackgroundColor(Color.rgb(r, g, b));
        }
    }
    public void onRadioButtonClicked(View view){
        RadioButton radio = (RadioButton) view;
        boolean checked = radio.isChecked();
        String test = radio.getText().toString();
        switch (test) {
            case "Left":
                Toast toastL =makeText(this,"Left",Toast.LENGTH_SHORT);
                toastL.setGravity(Gravity.LEFT,0,0);
                toastL.show();
                break;
            case "Right":
                Toast toastR =makeText(this,"Right",Toast.LENGTH_SHORT);
                toastR.setGravity(Gravity.RIGHT,0,0);
                toastR.show();
                break;
        }
    }
}