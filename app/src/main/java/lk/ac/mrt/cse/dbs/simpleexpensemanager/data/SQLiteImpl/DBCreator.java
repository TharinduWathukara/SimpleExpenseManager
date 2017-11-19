package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.SQLiteImpl;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by tharindu on 11/18/17.
 */

public class DBCreator extends SQLiteOpenHelper{
    public DBCreator(Context context){
        super(context,Variable.DB_Name,null,1);
        SQLiteDatabase db= this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE Account ( Acc_no TEXT PRIMARY KEY, Branch_name TEXT NOT NULL, Account_halder_name  TEXT,Balance  REAL DEFAULT 0);");
        db.execSQL("CREATE TABLE AC_Transaction ( Transaction_no INTEGER PRIMARY KEY AUTOINCREMENT, Date TEXT NOT NULL, Acc_no TEXT NOT NULL, ExpenceType TEXT NOT NULL , Amount REAL NOT NULL, FOREIGN KEY(Acc_no) REFERENCES Account(Acc_no));");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS Account");
        db.execSQL("DROP TABLE IF EXISTS AC_Transaction");
        onCreate(db);
    }
}
