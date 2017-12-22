package biblioteca.studio.com.biblioteca;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LivrosDbHelper extends SQLiteOpenHelper {



    public LivrosDbHelper(Context context){
        super(context, LivrosContract.DB_NOME,null, LivrosContract.DB_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LivrosContract.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(LivrosContract.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
    @Override
    public void onDowngrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(LivrosContract.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }
}
