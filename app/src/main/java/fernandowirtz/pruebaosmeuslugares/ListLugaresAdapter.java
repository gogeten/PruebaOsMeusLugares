package fernandowirtz.pruebaosmeuslugares;

import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.database.SQLException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListLugaresAdapter extends BaseAdapter {

	private final Activity activity;
	private Vector<Lugar> lista;
	private LugaresDB lugaresDb;
	private RecursosIconos drawable;

	/**
	 * @param activity
	 * @param lista
	 */
	public ListLugaresAdapter(Activity activity) {
		super();
		this.activity = activity;
		this.lista = new Vector<Lugar>();
		actualizarDesdeBd();
	}

	public void actualizarDesdeBd() throws SQLException {
		lugaresDb = new LugaresDB(activity);
		this.lista = lugaresDb.cargarLugaresDesdeBD();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return lista.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return lista.elementAt(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		Lugar lugar = (Lugar) getItem(position);
		return lugar.getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		drawable = new RecursosIconos(activity);
		LayoutInflater inflater = activity.getLayoutInflater();
		View view = inflater.inflate(R.layout.layout_lista, null, true);

		TextView textViewTitulo = (TextView) view
				.findViewById(R.id.textCaption);
		TextView textViewInfo = (TextView) view
				.findViewById(R.id.textInformacion);
		Lugar l = (Lugar) lista.elementAt(position);
		textViewTitulo.setText(l.getNombre());
		textViewInfo.setText(l.toString());

		ImageView image = (ImageView) view.findViewById(R.id.imageGuardar);
		image.setImageDrawable(drawable.obtenerIdImagen((int)l.getCategoria().getIdImagenCategoria()-1));
		
		return view;
	}
}