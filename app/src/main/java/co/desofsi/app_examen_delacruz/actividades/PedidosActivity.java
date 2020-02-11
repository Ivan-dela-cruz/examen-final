package co.desofsi.app_examen_delacruz.actividades;

import androidx.appcompat.app.AppCompatActivity;
import co.desofsi.app_examen_delacruz.MainActivity;
import co.desofsi.app_examen_delacruz.R;
import co.desofsi.app_examen_delacruz.database.SQLiteHelper;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PedidosActivity extends AppCompatActivity {


    private EditText codigo,detalle,total,tipo;
    private Button guardar,actualizar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        init();
        eventoBotones();

    }

    public void init(){
        codigo = (EditText)findViewById(R.id.txt_codigo);
        detalle = (EditText)findViewById(R.id.txt_detalle);
        total = (EditText)findViewById(R.id.txt_total);
        tipo = (EditText)findViewById(R.id.txt_tipo);
        guardar = (Button)findViewById(R.id.btn_guardar);
        actualizar = (Button)findViewById(R.id.btn_actualizars);
    }

    public void eventoBotones(){
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{

                    double total_pedido = Double.parseDouble(total.getText().toString());
                    int tipo_p = Integer.parseInt(tipo.getText().toString());
                    MainActivity.sqLiteHelper.insertDataPedidos(codigo.getText().toString(),detalle.getText().toString(),total_pedido,tipo_p);
                    Toast.makeText(PedidosActivity.this,"Agregado exitosamente",Toast.LENGTH_SHORT).show();
                    limpiar();
                }catch (Exception e){
                    Toast.makeText(PedidosActivity.this,"Error en los datos o ya existe codigo",Toast.LENGTH_SHORT).show();
                }





            }
        });

        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               limpiar();

            }
        });
    }

    public  void limpiar(){
        codigo.setText("");
        detalle.setText("");
        tipo.setText("");
        total.setText("");
    }

}
