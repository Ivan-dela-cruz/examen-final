package co.desofsi.app_examen_delacruz;

import androidx.appcompat.app.AppCompatActivity;
import co.desofsi.app_examen_delacruz.actividades.EditarPedidoActivity;
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
    private TextView total_fnal,txt_ultimo;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteHelper = new SQLiteHelper(this, "bdlema.sqlite", null, 1);
        sqLiteHelper.getWritableDatabase();
        sqLiteHelper.close();

        init();
        llenar();
        eventos();


    }

    private void init() {
        button = (Button) findViewById(R.id.menu_ingreso);
        listView = (ListView) findViewById(R.id.lista);
        total_fnal = (TextView) findViewById(R.id.txt_suma);
        txt_ultimo = (TextView) findViewById(R.id.txt_ultimo);
        lista_pe = new ArrayList<>();
    }

    public void eventos() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Pedidos pedidos  = lista_pe.get(position);
                        Intent intent = new Intent(MainActivity.this, EditarPedidoActivity.class);
               intent.putExtra("pedido",pedidos);
               startActivity(intent);
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
        int cont = 0;

        while (cursor.moveToNext()) {
            cont++;
            int id = Integer.parseInt(cursor.getString(0));
            String codigo = cursor.getString(0);
            String detalle = cursor.getString(1);

            double total = cursor.getDouble(2);


            int tipo = cursor.getInt(3);


            total_pe += total;


            lista_pe.add(new Pedidos(id, codigo, detalle, total, tipo));

        }


        int ultimo = lista_pe.size() - 1;

        if (ultimo != -1) {
           // Toast.makeText(MainActivity.this, "" + lista_pe.get(ultimo).getCodigo() + " " + lista_pe.get(ultimo).getDetalle() + "  " + lista_pe.get(ultimo).getTipo() + " " + lista_pe.get(ultimo).getTotal(), Toast.LENGTH_SHORT).show();
            AdapterPedidos adapterPedidos = new AdapterPedidos(MainActivity.this, lista_pe);
            listView.setAdapter(adapterPedidos);
            total_fnal.setText("TOTAL: " + total_pe + "      TOTAL PEDIDOS : " + cont);
            txt_ultimo.setText("ULTIMO PEDIDO => Cod:" + lista_pe.get(ultimo).getCodigo() + "   detalle:" + lista_pe.get(ultimo).getDetalle() + "   Tipo:" + lista_pe.get(ultimo).getTipo() + "   Total:" + lista_pe.get(ultimo).getTotal());
        } else {

        }


    }
}
