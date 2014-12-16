package fernandowirtz.pruebaosmeuslugares;


import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class EditLugares extends Activity implements OnClickListener, OnItemSelectedListener {
	Vector<Categoria> vectorCategorias = new Vector<Categoria>();
	boolean add = false;
	Lugar lugar;
	Bundle bundle;
	EditText nombre;
	EditText direccion;
	Spinner spinner;
	EditText telefono;
	EditText url;
	EditText comentario;
	ArrayAdapter<String> adaptador;
	CategoriaAdapter categoriaAdapter;
	LugaresDB lugaresDB = new LugaresDB(this);
	Categoria categoria;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_lugares);
		add = getIntent().getExtras().getBoolean("add");
		
		ImageView imagenguardar = (ImageView)findViewById(R.id.imageGuardar);
		imagenguardar.setOnClickListener(this);
		
		nombre = (EditText)findViewById(R.id.editNombre);
		direccion = (EditText) findViewById(R.id.editDireccion);
		telefono = (EditText)findViewById(R.id.editTelefono);
		url = (EditText)findViewById(R.id.editUrl);
		comentario = (EditText)findViewById(R.id.editComentario);
		spinner = (Spinner)findViewById(R.id.spinnerCategoria);
		vectorCategorias = lugaresDB.cargarCategoriasDB();
		
		
		categoriaAdapter= new CategoriaAdapter(this);
		spinner.setAdapter(categoriaAdapter);
		spinner.setOnItemSelectedListener(this);
		
		categoria = new Categoria();
		
		if (!add) {
			editarLugarEspecifico();
		}
	}

	private boolean validar() {
		if (nombre.getText().toString().isEmpty() | direccion.getText().toString().isEmpty()
				| telefono.getText().toString().isEmpty()) {
			return false;
		}
		else {
			return true;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_lugares, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		try {
			
			if (add) {
				System.out.println(add);
				if (validar()) {
					crearObjetoLugar();
					try {
						LugaresDB lugaresDB = new LugaresDB(this);
						System.out.println(lugar.getBundle().get("categoria_nombre"));
						System.out.println(lugar.getBundle().getLong("categoria_id"));
						//lugaresDB.insertarNuevaCategoria(categoria.getContentValuesCategoria());
						lugaresDB.insertarNuevoLugar(lugar.getBundle());
						Toast.makeText(this, "Se ha insertado un nuevo lugar.", Toast.LENGTH_LONG).show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Toast.makeText(this, "Registro insertado con éxito", Toast.LENGTH_SHORT).show();
				}
				
			}
			else {
				Lugar lugar = (Lugar)getIntent().getExtras().getSerializable("lugar");
				LugaresDB db = new LugaresDB(this);
				db.getWritableDatabase();
				db.updateLugar(lugar);
				Toast.makeText(this, "Se ha actualizado", Toast.LENGTH_SHORT).show();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Log.e("Evento onClick",e.getMessage() );
		}
	}

	private void editarLugarEspecifico() {
		// TODO Auto-generated method stub
		
		Lugar lugar = (Lugar) getIntent().getExtras().getSerializable("lugar");
		
		nombre.setText(lugar.getNombre());
		direccion.setText(lugar.getDireccion());
		telefono.setText(lugar.getTelefono());
		comentario.setText(lugar.getComentario());
	}

	private void crearObjetoLugar() {
		lugar = new Lugar();
		lugar.setNombre(nombre.getText().toString());
		lugar.setDireccion(direccion.getText().toString());
		lugar.setTelefono(telefono.getText().toString());
		lugar.setUrl(url.getText().toString());
		lugar.setComentario(comentario.getText().toString());
		
		lugar.setCategoria(categoria);
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		
		categoria.setIdImagenCategoria(spinner.getSelectedItemId());
		categoria.setNombre(spinner.getSelectedItem().toString());
		/*System.out.println(categoria.getNombre());
		System.out.println(""+categoria.getIdImagenCategoria());
		System.out.println(select);*/
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
