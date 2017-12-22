package biblioteca.studio.com.biblioteca;

import android.provider.BaseColumns;


public class LivrosContract {

    public static final String DB_NOME = "livros.db";
    public static final int DB_VERSAO = 2;
    public static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            LivrosEntry.TABELA_NOME + " (" +
            LivrosEntry._ID + " INTEGER PRIMARY KEY," +
            LivrosEntry.COLUNA_NOME_NOME + " TEXT," +
            LivrosEntry.COLUNA_NOME_EDICAO + " TEXT," +
            LivrosEntry.COLUNA_NOME_AUTOR + " TEXT," +
            LivrosEntry.COLUNA_NOME_EDITORA + " TEXT," +
            LivrosEntry.COLUNA_NOME_DESCRICAO + " TEXT," +
            LivrosEntry.COLUNA_NOME_IMAGEM + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + LivrosEntry.TABELA_NOME;

    public static class LivrosEntry implements BaseColumns {

        public static final String TABELA_NOME = "livros";
        public static final String COLUNA_NOME_NOME = "nome";
        public static final String COLUNA_NOME_EDICAO = "edicao";
        public static final String COLUNA_NOME_AUTOR = "autor";
        public static final String COLUNA_NOME_EDITORA = "editora";
        public static final String COLUNA_NOME_DESCRICAO = "descricao";
        public static final String COLUNA_NOME_IMAGEM = "imagem";

    }
}
