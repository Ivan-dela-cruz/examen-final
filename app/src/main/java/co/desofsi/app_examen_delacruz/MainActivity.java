package co.desofsi.app_examen_delacruz;

import androidx.appcompat.app.AppCompatActivity;
import co.desofsi.app_examen_delacruz.actividades.PedidosActivity;
import co.desofsi.app_examen_delacruz.adaptadores.AdapterPedidos;
import co.desofsi.app_examen_delacruz.database.SQLiteHelper;
import co.desofsi.app_examen_delacruz.entidades.Pedidos;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    public static SQLiteHelper sqLiteHelper;
    private ListView listView;
    private ArrayList<Pedidos> lista_pe;
    private TextView total_fnal ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(this, "bdlema.sqlite", null, 1);
        sqLiteHelper.getWritableDatabase();
        sqLiteHelper.close();

        Button button = (Button) findViewById(R.id.menu_ingreso);
        listView = (ListView) findViewById(R.id.lista);
        total_fnal = (TextView)findViewById(R.id.txt_suma);
        lista_pe = new ArrayList<>();

        llenar();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                double total = lista_pe.get(position).getTotal();

                if (total < 1000) {


                    String sql = "DELETE FROM pedidos WHERE ped_codigo = '" + lista_pe.get(position).getCodigo() + "'";
                    sqLiteHelper.deletedDataTable(sql);

                    listView.clearAnimation();
                    llenar();


                } else {
                    Toast.makeText(MainActivity.this, "El pedido tiene un valor mayor a 1000", Toast.LENGTH_SHORT).show();
                }
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PedidosActivity.class);
                startActivity(intent);
            }
        });

    }

    public void llenar() {
        lista_pe.clear();
        Cursor cursor = sqLiteHelper.getDataTable("SELECT * FROM pedidos");
        double total_pe = 0;
        int cont=0;

        while (cursor.moveToNext()) {
            cont++;
            int id = Integer.parseInt(cursor.getString(0));
            String codigo = cursor.getString(0);
            String detalle = cursor.getString(1);

            double total = cursor.getDouble(2);


            int tipo = cursor.getInt(3);


            total_pe+=total;


            lista_pe.add(new Pedidos(id, codigo, detalle, total, tipo));

        }

        int ultimo = lista_pe.size()-1;

        Toast.makeText(MainActivity.this,""+lista_pe.get(ultimo).getCodigo()+" "+lista_pe.get(ultimo).getDetalle()+"  "+lista_pe.get(ultimo).getTipo()+" "+lista_pe.get(ultimo).getTotal(),Toast.LENGTH_SHORT).show();
        AdapterPedidos adapterPedidos = new AdapterPedidos(MainActivity.this, lista_pe);
        listView.setAdapter(adapterPedidos);
        total_fnal.setText("Toal: "+total_pe+"      Numero pedidos : "+cont);
    }
}
