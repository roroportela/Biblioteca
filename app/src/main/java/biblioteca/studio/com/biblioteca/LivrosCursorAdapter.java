package biblioteca.studio.com.biblioteca;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class LivrosCursorAdapter extends CursorAdapter {


    public LivrosCursorAdapter(Context context, Cursor c) {
        super(context,c,false);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(
                R.layout.list_item_livro, viewGroup, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textView = (TextView) view.findViewById( R.id.text_view_produto);
        String livro_Nome = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
        textView.setText(livro_Nome);
    }
}
