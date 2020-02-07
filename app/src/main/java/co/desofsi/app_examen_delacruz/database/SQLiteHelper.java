package co.desofsi.app_examen_delacruz.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    final String TABLE_PEDIDO = "CREATE TABLE IF NOT EXISTS pedidos(ped_codigo TEXT PRIMARY KEY , ped_detalle TEXT, ped_tota DOUBLE,ped_tipo INTEGER)";

    public SQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    ////    EXRAER DATOS DE LAS TABLAS
    public Cursor getDataTable(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    ///INGRESO DE pedidos

    public void insertDataPedidos(String codigo, String detalle,  double total, int tipo) {

        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO pedidos VALUES(?,?,?,?)";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1, codigo);
        statement.bindString(2, detalle);
        statement.bindDouble(3, total);
        statement.bindDouble(4,tipo);
        statement.executeInsert();

    }


    public void deletedDataTable(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        if (database != null) {
            database.execSQL(sql);
            database.close();
        }


    }




    //actualizar pedidoss

    //(ped_codigo TEXT PRIMARY KEY , ped_detalle TEXT, ped_tota DOUBLE,ped_tipo INTEGER

    public void updateDataPedido(String codigo, String detalle,  double total, int tipo) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("ped_detalle", detalle);
        data.put("ped_tota", total);
        data.put("ped_tipo", tipo);
        database.update("pedidos", data, "ped_codigo=" + codigo, null);

    }









    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_PEDIDO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
