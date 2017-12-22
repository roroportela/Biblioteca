package biblioteca.studio.com.biblioteca;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import biblioteca.studio.com.biblioteca.LivrosContract.LivrosEntry;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private LivrosCursorAdapter adapter;
    Livros[] livros;
    private LivrosDbHelper livrosDbHelper = new LivrosDbHelper(this);
    private ImageView audio;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)this.findViewById(R.id.text_view_title);
        textView.setText(R.string.products_title);
        audio = (ImageView) findViewById(R.id.imageView);
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.nova_ucsal_a);
                tocarSom();
            }
        });

        SQLiteDatabase db = livrosDbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM livros", null);
        adapter = new LivrosCursorAdapter(this, cursor);

        ListView listView = (ListView)this.findViewById(R.id.list_view_produtos);

        listView.setAdapter(adapter);

        Context context = this;
        String text = "Olá Rodrigo!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, InformacaoActivity.class);
                intent.putExtra("posicao", i);
                startActivity(intent);
            }
        });
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("https://raw.githubusercontent.com/roroportela/livros/master/livro",
                new TextHttpResponseHandler() {
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Log.d("AsyncHttpClient", "onFailure response = " + responseString);
                        Log.d("AsyncHttpClient",throwable.toString());
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Log.d("AsyncHttpClient", "onSuccess response = " + responseString);
                        Gson gson = new GsonBuilder().create();
                        livros = gson.fromJson(responseString, Livros[].class);
                        deleteLivrosDB();
                        addProdutoToDB(livros);

                        SQLiteDatabase db = livrosDbHelper.getWritableDatabase();
                        Cursor cursor = db.rawQuery("SELECT * FROM livros", null);
                        adapter.changeCursor(cursor);
                    }
                });

        String[] localizacao = new String[3];
        localizacao[0] = "-12.9470644";
        localizacao[1] = "-38.4136661";

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.maps, new MapsActivity(localizacao)).commit();
    }

    private void addProdutoToDB(Livros[] livros) {
        SQLiteDatabase db = livrosDbHelper.getWritableDatabase();
        for(Livros p : livros){
            //Consulto se já existe produto com este nome poderia ser outro criterio
//            Cursor c = db.rawQuery("SELECT * FROM livros where nome=?", new String[]{p.getNome()});
            //se não existe grava
//            if(c.moveToFirst()) {
                ContentValues values = new ContentValues();
                values.put(LivrosEntry.COLUNA_NOME_NOME, p.getNome());
                values.put(LivrosEntry.COLUNA_NOME_EDICAO, p.getEdicao());
                values.put(LivrosEntry.COLUNA_NOME_AUTOR, p.getAutor());
                values.put(LivrosEntry.COLUNA_NOME_EDITORA, p.getEditora());
                values.put(LivrosEntry.COLUNA_NOME_DESCRICAO, p.getDescricao());
                values.put(LivrosEntry.COLUNA_NOME_IMAGEM, p.getImagem());
                db.insert(LivrosEntry.TABELA_NOME, null, values);
//            }

        }
    }

    private void tocarSom() {
        if(mediaPlayer!=null){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onDestroy() {
        if(mediaPlayer!=null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }

    private void deleteLivrosDB(){
        SQLiteDatabase db = livrosDbHelper.getWritableDatabase();
        db.delete(LivrosEntry.TABELA_NOME, null, null);
    }

}
