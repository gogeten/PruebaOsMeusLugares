package fernandowirtz.pruebaosmeuslugares;



import java.util.ArrayList;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorImagenesSpinner extends BaseAdapter{
	
	private Activity activity;
	private TypedArray listaImagenes;
	private ArrayList <String> listaNombresCategoria;
	private RecursosIconos drawable;
	
	public AdaptadorImagenesSpinner (Activity activity) {
		this.activity = activity;
		this.drawable = new RecursosIconos(activity);
		this.listaImagenes = drawable.getDrawableListaIconos();
		this.listaNombresCategoria = drawable.getListaNombresCategoria();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listaImagenes.length();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listaImagenes.getDrawable(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return listaImagenes.getIndex(position); //??
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		drawable = new RecursosIconos(activity);
		LayoutInflater inflater = activity.getLayoutInflater();
		View view = inflater.inflate(R.layout.layout_iconos_categoria, null, true);
		ImageView image = (ImageView)view.findViewById(R.id.imgLayoutCategoria);
		//ImageView i = (ImageView)listaImagenes.get(position);
		image.setImageDrawable((Drawable) getItem(position));
		TextView texto = (TextView) view.findViewById( R.id.txtImagenCategoria);
		texto.setText(listaNombresCategoria.get(position));
		return view;
	}

}
