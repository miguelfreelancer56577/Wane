package app.wane.com.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.wane.com.model.CatLastStatus;
import static app.wane.com.soport.DataBase.*;

/**
 * Created by mangelt on 24/09/2016.
 */
public class CatLastStatusSQLiteHelper extends SQLiteOpenHelper {
    private final String sqlCreate = "CREATE TABLE catlaststatus ( nIdLastStatus SMALLINT(11) NOT NULL, sLastStatus CHAR(45) NOT NULL, sDescription CHAR(200) NULL,   PRIMARY KEY (nIdLastStatus))";
    private final String log = "CatLastStatusSQL";
    public CatLastStatusSQLiteHelper(Context contexto, CursorFactory factory) {
        super(contexto, NAMEBD, factory, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //execute query builder
        db.execSQL(sqlCreate);
        Log.i(log, "created data base");
        //rows
        List<CatLastStatus> status = new ArrayList<CatLastStatus>();
        status.add(new CatLastStatus(1, "Disponible", ""));
        status.add(new CatLastStatus(2, "1 pedido asignado", ""));
        status.add(new CatLastStatus(3, "2 pedidos asignados", ""));
        status.add(new CatLastStatus(4, "3+ pedidos asignados", ""));
        status.add(new CatLastStatus(5, "Formado para pagar", ""));
        status.add(new CatLastStatus(6, "En camino a entrega", ""));
        status.add(new CatLastStatus(7, "Inactivo 10 minutos", ""));
        status.add(new CatLastStatus(8, "Inactivo 15 minutos", ""));
        status.add(new CatLastStatus(9, "Inactivo 20 minutos", ""));
        status.add(new CatLastStatus(10, "Inactivo 30+ minutos", ""));
        status.add(new CatLastStatus(11, "Accidente de tránsito", ""));
        status.add(new CatLastStatus(12, "Incidente con el usuario", ""));
        status.add(new CatLastStatus(13, "Desconectado", ""));
        for (CatLastStatus item: status){
            //insert rows
            db.execSQL("INSERT INTO cat_laststatus(nIdLastStatus, sLastStatus, sDescription) VALUES ("+item.getnIdLastStatus()+", '"+item.getsLastStatus()+"', '"+item.getsDescription()+"');");
        }
        Log.i(log, "inserted all rows");
        //close conecction
        db.close();
        Log.i(log, "close conecction");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {
        //upgrade
    }
}
