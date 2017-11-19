package lk.ac.mrt.cse.dbs.simpleexpensemanager.data.SQLiteImpl;
import java.text.SimpleDateFormat;
import java.util.Locale;
/**
 * Created by tharindu on 11/18/17.
 */

public class Variable {
    public static final String DB_Name = "150667v.db";

    public static  final SimpleDateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy", Locale.getDefault());

    public static final String TRANSACTION_COL_2 = "Date";
    public static final String TRANSACTION_COL_3 = "Acc_no";
    public static final String TRANSACTION_COL_4 = "ExpenceType";
    public static final String TRANSACTION_COL_5 = "Amount";

    public static final String ACCOUNT_COL_1 = "Acc_no";
    public static final String ACCOUNT_COL_2 = "Branch_name";
    public static final String ACCOUNT_COL_3 = "Account_halder_name";
    public static final String ACCOUNT_COL_4 = "Balance";

    public static final String TRANSACTION_Type_Expense = "E";
    public static final String TRANSACTION_Type_Income = "I";
}
