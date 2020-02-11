package co.desofsi.app_examen_delacruz.actividades;

import androidx.appcompat.app.AppCompatActivity;
import co.desofsi.app_examen_delacruz.MainActivity;
import co.desofsi.app_examen_delacruz.R;
import co.desofsi.app_examen_delacruz.entidades.Pedidos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditarPedidoActivity extends AppCompatActivity {

    private EditText codigo, detalle, total, tipo;
    private Button eliminar, actualizar;
    private Pedidos pedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_pedido);
        init();
        pedidos = (Pedidos) getIntent().getExtras().getSerializable("pedido");

        //imprimir los valores del pedido encontrado
        codigo.setText(pedidos.getCodigo());
        detalle.setText(pedidos.getDetalle());
        total.setText(String.valueOf(pedidos.getTotal()));
        tipo.setText(String.valueOf(pedidos.getTipo()));

        //funcioon para los eventos
        eventos();
    }

    public void init() {
        codigo = (EditText) findViewById(R.id.txt_codigo);
        detalle = (EditText) findViewById(R.id.txt_detalle);
        total = (EditText) findViewById(R.id.txt_total);
        tipo = (EditText) findViewById(R.id.txt_tipo);
        eliminar = (Button) findViewById(R.id.btn_elimina);
        actualizar = (Button) findViewById(R.id.btn_actualiza);
    }

    public void eventos() {
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double total = pedidos.getTotal();
                if (total < 1000) {
                    String sql = "DELETE FROM pedidos WHERE ped_codigo = '" + pedidos.getCodigo() + "'";
                    MainActivity.sqLiteHelper.deletedDataTable(sql);
                    Toast.makeText(EditarPedidoActivity.this, "El pedido se ha eliminado", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(EditarPedidoActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(EditarPedidoActivity.this, "El pedido tiene un valor mayor a 1000", Toast.LENGTH_SHORT).show();
                }
            }
        });
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cod = codigo.getText().toString();
                String det = detalle.getText().toString();
                double tot = Double.parseDouble(total.getText().toString());
                int tip = Integer.parseInt(tipo.getText().toString());

                if (pedidos.getTipo() == 7) {
                    Toast.makeText(EditarPedidoActivity.this, "El pedido es de tipo 7 no se puede actualizar", Toast.LENGTH_SHORT).show();

                } else {
                    MainActivity.sqLiteHelper.updateDataPedido(cod, det, tot, tip);
                    Toast.makeText(EditarPedidoActivity.this, "El pedido se actualizo correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditarPedidoActivity.this, MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
