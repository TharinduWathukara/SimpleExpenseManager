package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.SQLiteImpl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.AccountDAO;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.exception.InvalidAccountException;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.Account;
import lk.ac.mrt.cse.dbs.simpleexpensemanager.data.model.ExpenseType;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tharindu on 11/18/17.
 */

public class SQLiteAccountDAO implements AccountDAO{
    SQLiteDatabase db;
    public SQLiteAccountDAO(Context context){
        db=DBConnection.getInstance(context).getWritableDatabase();
    }
    @Override
    public List<String> getAccountNumbersList(){
        List<String> accNameList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select "+Variable.ACCOUNT_COL_1 +" from Account",null);
        if (cursor.getCount()==0){
            Log.d("MYACTIVITY","No values");
        }else {
            if (cursor.moveToFirst()){
                do{
                    String accNo=cursor.getString(cursor.getColumnIndex(Variable.ACCOUNT_COL_1));
                    accNameList.add(accNo);
                }while (cursor.moveToNext());
            }
        }return accNameList;
    }

    @Override
    public List<Account> getAccountsList(){
        List<Account> accountList = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from Account",null);
        if(cursor.getCount()==0){
            Log.d("MYACTIVITY","No values");
            return null;
        }else{
            if(cursor.moveToFirst()) {
                do{
                    String accNo = cursor.getString(cursor.getColumnIndex(Variable.ACCOUNT_COL_1));
                    String bankName = cursor.getString(cursor.getColumnIndex(Variable.ACCOUNT_COL_2));
                    String accHolder = cursor.getString(cursor.getColumnIndex(Variable.ACCOUNT_COL_3));
                    double balance = cursor.getDouble(cursor.getColumnIndex(Variable.ACCOUNT_COL_4));
                    Account account = new Account(accNo,bankName,accHolder,balance);
                    accountList.add(account);
                }while (cursor.moveToNext());
            }
        }return accountList;
    }

    @Override
    public Account getAccount(String accountNo) throws InvalidAccountException {
        Account account = null;
        Cursor cursor = db.rawQuery("select * from Account where "+Variable.ACCOUNT_COL_1 +" = "+accountNo+";",null);
        if(cursor.getCount()==0){
            Log.d("MYACTIVITY","No values");
        }else{
            if(cursor.moveToFirst()) {
                do{
                    String accNo = cursor.getString(cursor.getColumnIndex(Variable.ACCOUNT_COL_1));
                    String bankName = cursor.getString(cursor.getColumnIndex(Variable.ACCOUNT_COL_2));
                    String accHolder = cursor.getString(cursor.getColumnIndex(Variable.ACCOUNT_COL_3));
                    double balance = cursor.getDouble(cursor.getColumnIndex(Variable.ACCOUNT_COL_4));
                    account = new Account(accNo,bankName,accHolder,balance);
                }while (cursor.moveToNext());
            }
        }return account;
    }

    @Override
    public void addAccount(Account account) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Variable.ACCOUNT_COL_1,account.getAccountNo());
        contentValues.put(Variable.ACCOUNT_COL_2,account.getBankName());
        contentValues.put(Variable.ACCOUNT_COL_3,account.getAccountHolderName());
        contentValues.put(Variable.ACCOUNT_COL_4,account.getBalance());
        long result = db.insert("Account",null,contentValues);
        if(result == -1){
            Log.d("MYACTIVITY", "NOT_INSERTED");
        }else
            Log.d("MYACTIVITY","INSERTED_ACCOUNT");
    }

    @Override
    public void removeAccount(String accountNo) throws InvalidAccountException {
        Account account = getAccount(accountNo);
        if (account == null) {
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        int state = db.delete("Account",Variable.ACCOUNT_COL_1 +" = "+accountNo,null );
        if(state == -1){
            Log.d("MYACTIVITY","NOT_DELETED");
        }else{
            Log.d("MYACTIVITY","DELETED_DATA");
        }
    }

    @Override
    public void updateBalance(String accountNo, ExpenseType expenseType, double amount) throws InvalidAccountException {
        Account account = getAccount(accountNo);
        if (account == null) {
            String msg = "Account " + accountNo + " is invalid.";
            throw new InvalidAccountException(msg);
        }
        switch (expenseType) {
            case EXPENSE:
                account.setBalance(account.getBalance() - amount);
                break;
            case INCOME:
                account.setBalance(account.getBalance() + amount);
                break;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Variable.ACCOUNT_COL_4, account.getBalance());
        int state = db.update("Account",contentValues,Variable.ACCOUNT_COL_1 + " = "+account.getAccountNo(),null);
        if(state!=-1){
            Log.d("MYACTIVITY","Updated");
        }else{
            Log.d("MYACTIVITY","error occoured in updated");
        }
    }
}
