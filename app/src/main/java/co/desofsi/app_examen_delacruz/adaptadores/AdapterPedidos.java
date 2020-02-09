package co.desofsi.app_examen_delacruz.adaptadores;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import co.desofsi.app_examen_delacruz.R;
import co.desofsi.app_examen_delacruz.entidades.Pedidos;


public class AdapterPedidos extends BaseAdapter {

    Context context;
    List<Pedidos> lista_gastosUsers;

    public AdapterPedidos(Context context, List<Pedidos> lista_gastosUsers) {
        this.context = context;
        this.lista_gastosUsers = lista_gastosUsers;
    }

    @Override
    public int getCount() {
        return lista_gastosUsers.size();
    }

    @Override
    public Object getItem(int position) {
        return lista_gastosUsers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lista_gastosUsers.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.item_resgistros, null);


        Pedidos gastosUser = lista_gastosUsers.get(position);
        TextView txt_describe = (TextView) view.findViewById(R.id.txt_describ_gasto_home);
        TextView txt_valor = (TextView) view.findViewById(R.id.txt_valor_gasto_home);
        TextView txt_fecha = (TextView) view.findViewById(R.id.txt_fecha_item);

        txt_describe.setText("tipo ("+ gastosUser.getTipo()+") "+gastosUser.getDetalle());
        txt_valor.setText("código ("+gastosUser.getCodigo()+")");
        txt_fecha.setText(String.valueOf(gastosUser.getTotal()));



        return view;
    }
}
