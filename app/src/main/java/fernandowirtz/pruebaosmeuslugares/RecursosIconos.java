package fernandowirtz.pruebaosmeuslugares;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

public class RecursosIconos {
	private Activity activity;
	private Resources resources;
	private TypedArray listaIconos;
	private ArrayList<String>listaNombreIconos;
	
	public RecursosIconos(Activity activity) {
		super();
		this.activity = activity;
		obtenerResources();
	}

	private void obtenerResources() {
		listaNombreIconos = new ArrayList<String>();
		resources = activity.getResources();
		listaIconos = resources.obtainTypedArray(R.array.array_iconos_lugares);

		for (String s : resources.getStringArray(R.array.array_string_iconos_lugares)) {
			listaNombreIconos.add(s);
		}
	}
	
	/**
	 * Obtiene el id por su valor en el array nombreIconos
	 * @param id (String)
	 * @return drawable
	 */
	public Drawable obtenerIdImagen (String id) {
		int posicion = listaNombreIconos.indexOf(id);
		return listaIconos.getDrawable(posicion);
	}
	/**
	 * Obtiene el id por su valor en el array drawable
	 * @param id (int)
	 * @return drawable
	 */
	public Drawable obtenerIdImagen (int id) {
		return listaIconos.getDrawable(id);
	}
	
	public ArrayList<String> getListaNombresCategoria() {
		return listaNombreIconos;
	}
	
	/**
	 * Devuelve la lista Drawable de iconos
	 * @return
	 */
	public TypedArray getDrawableListaIconos () {
		return listaIconos;
	}
}
