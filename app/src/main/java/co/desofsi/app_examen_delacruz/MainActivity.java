package co.desofsi.app_examen_delacruz;

import androidx.appcompat.app.AppCompatActivity;
import co.desofsi.app_examen_delacruz.database.SQLiteHelper;

import android.os.Bundle;

import java.sql.SQLClientInfoException;

public class MainActivity extends AppCompatActivity {


    public static SQLiteHelper sqLiteHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(this,"bddelacruz.sqlite",null,1);
        sqLiteHelper.getWritableDatabase();
        sqLiteHelper.close();


    }
}
