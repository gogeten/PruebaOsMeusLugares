package fernandowirtz.pruebaosmeuslugares;

import java.util.Vector;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoriaAdapter extends BaseAdapter{
	
	private Vector<Categoria> vectorCategoria;
	private final Activity activity;
	
	public CategoriaAdapter (Activity activity) {
		this.activity = activity;
		LugaresDB lugaresDB = new LugaresDB(activity);
		this.vectorCategoria = lugaresDB.cargarCategoriasDB();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return vectorCategoria.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return vectorCategoria.elementAt(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Categoria categoria = (Categoria)getItem(position);
		return categoria.getId();
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub

		Categoria categoria = (Categoria)vectorCategoria.elementAt(position);
		TextView text = new TextView(activity);
		text.setText(categoria.getNombre());
        return text;
		
	}
	
	
	public int getPositionById(Long id) {
		//Buscar en lista 
		Categoria buscar = new Categoria();
		buscar.setId(id);
		return vectorCategoria.indexOf(buscar);
	}
}
