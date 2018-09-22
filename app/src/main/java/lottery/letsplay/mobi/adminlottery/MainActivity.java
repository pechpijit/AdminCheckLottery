package lottery.letsplay.mobi.adminlottery;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    EditText input_number;
    Button btn_ymd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input_number = findViewById(R.id.input_number);
        btn_ymd = findViewById(R.id.btn_ymd);

        btn_ymd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (input_number.getText().toString().trim().length() > 7) {
                    showProgressDialog("กำลังตรวจสอบข้อมูล");
                    addToFirebase();
                } else {
                    new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_Dark_Dialog)
                            .setTitle("ขออภัย")
                            .setMessage("กรุณากรอกเลขให้ครบ 8 หลัก")
                            .setNegativeButton("ปิด",null)
                            .show();
                }
            }
        });
    }

    private void addToFirebase() {
        String ymd = input_number.getText().toString().trim();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("LotteryApp").child("Lottery").child(ymd);
        ArrayList<String> list = new ArrayList<>();
        list.add("-");
        database.child("reward1").setValue(list);
        database.child("rewardLast2").setValue(list);
        database.child("reward2").setValue(list);
        database.child("reward3").setValue(list);
        database.child("reward4").setValue(list);
        database.child("reward5").setValue(list);
        list.add("-");
        database.child("rewardLast3").setValue(list);
        database.child("rewardFront3").setValue(list);
        database.child("reward1Close").setValue(list);
        hideProgressDialog();
        new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_Dark_Dialog)
                .setTitle("สำเร็จ")
                .setMessage("เพิ่มงวดหวยสำเร็จแล้ว")
                .setNegativeButton("ปิด",null)
                .show();
    }
}
