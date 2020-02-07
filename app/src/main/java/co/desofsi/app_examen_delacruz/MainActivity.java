package co.desofsi.app_examen_delacruz;

import androidx.appcompat.app.AppCompatActivity;
import co.desofsi.app_examen_delacruz.actividades.PedidosActivity;
import co.desofsi.app_examen_delacruz.database.SQLiteHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.SQLClientInfoException;

public class MainActivity extends AppCompatActivity {


    public static SQLiteHelper sqLiteHelper;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(this,"bddelacruz.sqlite",null,1);
        sqLiteHelper.getWritableDatabase();
        sqLiteHelper.close();

        Button button = (Button)findViewById(R.id.menu_ingreso);
        listView = (ListView)findViewById(R.id.lista);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PedidosActivity.class);
                startActivity(intent);
            }
        });



    }
}
