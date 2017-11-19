package lk.ac.mrt.cse.dbs.simpleexpensemanager.control;
import android.content.Context;
import android.util.Log;

import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.ExpenseManager;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.control.exception.ExpenseManagerException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.SQLiteImpl.DBConnection;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.SQLiteImpl.DBCreator;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.SQLiteImpl.SQLiteAccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.SQLiteImpl.SQLiteTransactionDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.TransactionDAO;
/**
 * Created by tharindu on 11/18/17.
 */

public class PersistentExpenseManager extends ExpenseManager{
    Context context;

    public PersistentExpenseManager(Context context){
        this.context = context;
        DBCreator dbCreator= new DBCreator(context);
        try {
            setup();
        } catch (ExpenseManagerException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void setup() throws ExpenseManagerException {
        TransactionDAO SQLiteTransactionDAO = new SQLiteTransactionDAO(context);
        setTransactionsDAO(SQLiteTransactionDAO);
        AccountDAO SQLitAccountDAO = new SQLiteAccountDAO(context);
        setAccountsDAO(SQLitAccountDAO);
        DBConnection dbConnection = new DBConnection(context);
    }
}
