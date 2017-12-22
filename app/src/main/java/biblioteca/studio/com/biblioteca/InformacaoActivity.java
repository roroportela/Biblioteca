package biblioteca.studio.com.biblioteca;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import biblioteca.studio.com.biblioteca.LivrosContract.LivrosEntry;

public class InformacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacao);

        Intent intent = InformacaoActivity.this.getIntent();
        if (intent.hasExtra("posicao" )){
            int posicao = intent.getIntExtra("posicao",0);

            LivrosDbHelper produtoDbHelper = new LivrosDbHelper(this);
            SQLiteDatabase db = produtoDbHelper.getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM livros", null);
            cursor.moveToPosition(posicao);

            String produtoEditora  = cursor.getString(cursor.getColumnIndexOrThrow(
                    LivrosEntry.COLUNA_NOME_EDITORA));
            String produtoEdicao = cursor.getString(cursor.getColumnIndexOrThrow(
                    LivrosEntry.COLUNA_NOME_EDICAO));
            String produtoAutor = cursor.getString(cursor.getColumnIndexOrThrow(
                    LivrosEntry.COLUNA_NOME_AUTOR));
            String produtoImagem = cursor.getString(cursor.getColumnIndexOrThrow(
                    LivrosEntry.COLUNA_NOME_IMAGEM));
            String produtoDescricao  = cursor.getString(cursor.getColumnIndexOrThrow(
                    LivrosEntry.COLUNA_NOME_DESCRICAO));

            ((TextView) InformacaoActivity.this.findViewById(R.id.idEditora)).setText(produtoEditora);

            ((TextView) InformacaoActivity.this.findViewById(R.id.idEdicao))
                    .setText(produtoEdicao);

            ((TextView) InformacaoActivity.this.findViewById(R.id.idAutor))
                    .setText(produtoAutor);

            ((TextView) InformacaoActivity.this.findViewById(R.id.idDescricao))
                    .setText(produtoDescricao);

            Picasso.with(InformacaoActivity.this).load(produtoImagem).into(
                    (ImageView) InformacaoActivity.this.findViewById(R.id.idImagemLivro));

        }

    }
}
