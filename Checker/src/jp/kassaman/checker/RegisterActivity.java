package jp.kassaman.checker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import jp.kassaman.checker.R;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener, OnDateSetListener {

    private int number = 1;
    private Calendar date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button setting = (Button) findViewById(R.id.settei);
        setting.setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.register, menu);
        return true;
    }

    // �A�N�V�����o�[�����������ɌĂ΂�郁�\�b�h
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_register) {
            EditText pro = (EditText) findViewById(R.id.productname);

            if (date == null || pro.getText().length() == 0) {
                Toast.makeText(this, "未記入項目あり", Toast.LENGTH_SHORT).show();

            } else {

                Data data = new Data();
                data.setDate(date);

                EditText edit = (EditText) findViewById(R.id.productname);
                data.setProductname(edit.getText().toString());

                EditText num = (EditText) findViewById(R.id.number);
                data.setNumber(Integer.parseInt(num.getText().toString()));

                ArrayList<Data> array = (ArrayList<Data>) FIleSave.readObjectFromFile(this, FIleSave.produce);
                if (array == null) {
                    array = new ArrayList<Data>();

                }
                array.add(data);

                FIleSave.writeObjectToFile(this, array, FIleSave.produce);

                setResult(0);

                finish();

            }

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.settei) {

            Calendar today = date;
            if (date == null) {
                today = Calendar.getInstance();
            }

            DatePickerDialog dialog = new DatePickerDialog(this, this, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH));
            dialog.show();
        }
        if (v.getId() == R.id.plus) {
            number++;

            EditText plus = (EditText) findViewById(R.id.number);
            plus.setText(Integer.toString(number));

        }
        if (v.getId() == R.id.minus) {
            number--;

            EditText minus = (EditText) findViewById(R.id.number);
            minus.setText(Integer.toString(number));
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

        TextView checker = (TextView) findViewById(R.id.checker);
        checker.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth, 23, 59, 59);
        date = calendar;

    }

}
