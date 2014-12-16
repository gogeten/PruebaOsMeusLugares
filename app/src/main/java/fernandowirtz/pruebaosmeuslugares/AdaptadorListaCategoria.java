package fernandowirtz.pruebaosmeuslugares;

import java.util.Vector;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorListaCategoria extends BaseAdapter{
	
	private Activity activity;
	private Vector<Categoria> vectorCategoria;;
	private Vector<String> nombresCategoria = new Vector<String>();
	private RecursosIconos drawable; 
	private TypedArray miListaImagenes;
	LugaresDB db;
	
	
	public AdaptadorListaCategoria (Activity activity) {
		this.activity = activity;
		db=new LugaresDB(activity);
		db.getReadableDatabase();
		vectorCategoria = db.cargarCategoriasDB();
		drawable = new  RecursosIconos(activity);
		miListaImagenes = drawable.getDrawableListaIconos();
		cargarNombreCategorias();
	}

	private Vector<String> cargarNombreCategorias() {
		for (Categoria c : vectorCategoria) {
			nombresCategoria.add(c.getNombre());
		}
		return nombresCategoria;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return nombresCategoria.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return nombresCategoria.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		System.out.println("entró");
		LayoutInflater inflater = activity.getLayoutInflater();
		View view = inflater.inflate(R.layout.layout_categorias_rellenar, null, true);
		ImageView image = (ImageView)view.findViewById(R.id.imageCargarListaCategorias);
		Categoria c = vectorCategoria.get(position);
		System.out.println(c.getIdImagenCategoria());
		System.out.println(c.getIdImagenCategoria());
		System.out.println(miListaImagenes.getString(position));
		
		System.out.println(image.toString());
		image.setImageDrawable( miListaImagenes.getDrawable((int) c.getIdImagenCategoria()-1));
		
		System.out.println("llegó");
		TextView texto = (TextView) view.findViewById( R.id.textTituloEditarCategoria);
		texto.setText(nombresCategoria.get(position));
		return view;
	}

}
