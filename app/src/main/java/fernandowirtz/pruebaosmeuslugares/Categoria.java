package fernandowirtz.pruebaosmeuslugares;

import java.io.Serializable;

import android.content.ContentValues;
import android.os.Bundle;

public class Categoria implements Serializable{
	private long id;
	private String nombre;
	private long categoria_id_imagen;
	
	/*
	 * Mapeo para DB
	 */
	public static final String CATEGORIA_ID ="categoria_id";
	public static final String CATEGORIA_NOMBRE = "categoria_nombre";
	public static final String CATEGORIA_ID_IMAGEN = "categoria_id_imagen";
	
	public Categoria (long id, String nombre, long idImagenCategoria) {
		this.id = id;
		this.nombre = nombre;
		this.categoria_id_imagen = idImagenCategoria;
	}
	
	public Categoria() {}
	
	public Categoria (String nombre, long idImagenCategoria) {
		this.nombre = nombre;
		this.categoria_id_imagen = idImagenCategoria;
	}
	
	public long getIdImagenCategoria() {
		return categoria_id_imagen;
	}

	public void setIdImagenCategoria(long idImagenCategoria) {
		this.categoria_id_imagen = idImagenCategoria;
	}


	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof Categoria) {
			Categoria tmpCategoria = (Categoria)o;
			if (getId()==tmpCategoria.getId()) {
				return true;
			} else{
				return false;
			}
		}
		return false;
	}
	
	public ContentValues getContentValuesCategoria() {
		
		ContentValues values = new ContentValues();
		values.put("categoria_id_imagen", getIdImagenCategoria());
		values.put("categoria_nombre", getNombre());
		
		return values;
	}
}
