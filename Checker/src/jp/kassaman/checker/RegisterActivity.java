package jp.kassaman.checker;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import jp.kassaman.checker.R;
import android.R.integer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener,
        OnDateSetListener {

    private int number = 1;
    private Calendar date;
    static final int REQUEST_CAPTURE_IMAGE = 100;

    Button picb;
    ImageView PoImage;

    Bitmap saveImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button setting = (Button) findViewById(R.id.settei);
        setting.setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViews();
        setListeners();

    }

    private void findViews() {
        picb = (Button) findViewById(R.id.pic);
        PoImage = (ImageView) findViewById(R.id.upImage);

    }

    private void setListeners() {
        picb.setOnClickListener(this);

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
                // 保存関係

                Data data = new Data();
                data.setDate(date);

                EditText edit = (EditText) findViewById(R.id.productname);
                data.setProductname(edit.getText().toString());

                EditText num = (EditText) findViewById(R.id.number);
                data.setNumber(Integer.parseInt(num.getText().toString()));

                // 画像をファイルに保存
                String file = data.getId() + ".jpg";

                /* Bitmapをファイルに記憶する　 */

                // fileというファイル名でファイル保存機能を呼び出す
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(file, MODE_PRIVATE);
                } catch (FileNotFoundException e1) {
                    // TODO 自動生成された catch ブロック
                    e1.printStackTrace();
                }
                //saveImageをjpgでファイルに書き出す

                saveImage.compress(CompressFormat.JPEG, 100, fos);

                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO 自動生成された catch ブロック
                    e.printStackTrace();
                }
                
                //画像ファイルを記憶
                data.setBitMap(file);

                ArrayList<Data> array = (ArrayList<Data>) FIleSave
                        .readObjectFromFile(this, FIleSave.produce);
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

            DatePickerDialog dialog = new DatePickerDialog(this, this,
                    today.get(Calendar.YEAR), today.get(Calendar.MONTH),
                    today.get(Calendar.DAY_OF_MONTH));
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
        if (v.getId() == R.id.pic) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, REQUEST_CAPTURE_IMAGE);

        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
            int dayOfMonth) {

        TextView checker = (TextView) findViewById(R.id.checker);
        checker.setText(year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
                

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, monthOfYear, dayOfMonth, 23, 59, 59);
        date = calendar;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // 撮ったあとの処理
        if (REQUEST_CAPTURE_IMAGE == requestCode
                && resultCode == Activity.RESULT_OK) {
            //
            Bitmap capturedImage = (Bitmap) data.getExtras().get("data");
            //ImageViewへのbitmapのセット
            PoImage.setImageBitmap(capturedImage);
            // リサイズ
            saveImage = Bitmap.createScaledBitmap(capturedImage, 100, 100,
                    false);
        }

    }

}
