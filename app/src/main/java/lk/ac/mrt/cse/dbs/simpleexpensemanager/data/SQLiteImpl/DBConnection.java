package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.SQLiteImpl;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by tharindu on 11/18/17.
 */

public class DBConnection extends SQLiteOpenHelper{
    private static DBConnection instance;
    public SQLiteDatabase db = null;
    public DBConnection(Context context){
        super(context,Variable.DB_Name,null,1);
        db = this.getWritableDatabase();
    }

    public static DBConnection getInstance(Context context){
        if (instance==null){
            synchronized (DBConnection.class){
                if(instance==null){
                    instance = new DBConnection(context);
                }
            }
        }return instance;
    }

    public SQLiteDatabase getSQLiteDB(){
        return db;
    }
    @Override
    public void onCreate(SQLiteDatabase db){}
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
