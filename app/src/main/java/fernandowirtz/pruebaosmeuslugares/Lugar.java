package fernandowirtz.pruebaosmeuslugares;

import java.io.Serializable;

import android.content.ContentValues;
import android.os.Bundle;

public class Lugar implements Serializable{
	
	private long id;
	private String nombre;
	private Categoria categoria;
	private String direccion;
	private String telefono;
	private String url;
	private String comentario;
	private Bundle bundle;
	
	/*
	 * Mapeo DB
	 */
	public final String LUGAR_ID = "lugar_id";
	public final String LUGAR_NOMBRE = "lugar_nombre";
	public final String LUGAR_CATEGORIA_ID = "lugar_categoria_id";
	public final String LUGAR_DIRECCION = "lugar_direccion";
	public final String LUGAR_TELEFONO = "lugar_telefono";
	public final String LUGAR_URL = "lugar_url";
	public final String LUGAR_COMENTARIO = "lugar_comentario";
	public final String LUGAR_CATEGORIA_ID_IMAGEN = "lugar_categoria_id_imagen";
	
	public Bundle getBundle() {
		// TODO Auto-generated method stub
		bundle = new Bundle();
		bundle.putString("lugar_nombre", this.nombre);
		bundle.putString("lugar_direccion", this.direccion);
		bundle.putString("lugar_telefono", this.telefono);
		bundle.putString("lugar_url", this.url);
		bundle.putString("lugar_comentario", this.comentario);
		bundle.putLong("lugar_categoria_id", this.categoria.getId());
		bundle.putString("categoria_nombre", this.categoria.getNombre());
		bundle.putLong("lugar_categoria_id_imagen", this.categoria.getIdImagenCategoria());
		
		return bundle;
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
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getComentario() {
		return comentario;
	}
	
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	
	

	@Override
	public String toString() {
		return "Categoria: "
				+ categoria.getNombre() + ", Direccion: " + direccion + 
				", Url: " + url + ", Teléfono:" + telefono + ", Comentario: "
				+ comentario;
	}
	
public ContentValues getContentValuesCategoria() {
		
		ContentValues values = new ContentValues();
		values.put("lugar_nombre", getNombre());
		values.put("lugar_direccion", getDireccion());
		values.put("lugar_telefono", getTelefono());
		values.put("lugar_url", getUrl());
		values.put("lugar_comentario", getComentario());
		values.put("lugar_categoria_id", getCategoria().getId());
		values.put("lugar_categoria_id_imagen", getCategoria().getIdImagenCategoria());
		return values;
	}
}
