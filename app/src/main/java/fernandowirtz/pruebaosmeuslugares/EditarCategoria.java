package fernandowirtz.pruebaosmeuslugares;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

public class EditarCategoria extends Activity implements OnClickListener,
		OnItemSelectedListener {

	EditText nombreCategoria;
	Spinner spinnerImagen;
	ImageView imageGuardarCategoria;
	Categoria categoria = new Categoria();
	AdaptadorImagenesSpinner adaptador;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editar_categoria);

		adaptador = new AdaptadorImagenesSpinner(this);
		
		nombreCategoria = (EditText) findViewById(R.id.textEditorNombreCategoria);
		imageGuardarCategoria = (ImageView) findViewById(R.id.imgGuardarCategoria);
		imageGuardarCategoria.setOnClickListener(this);
		spinnerImagen = (Spinner) findViewById(R.id.spinnerIconosCategoria);
		spinnerImagen.setAdapter(adaptador);
		spinnerImagen.setOnItemSelectedListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editar_categoria, menu);
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
		if (validar()) {
				Categoria categoria = new Categoria();
				categoria.setNombre(nombreCategoria.getText().toString());
				LugaresDB lugaresDB = new LugaresDB(this);
				lugaresDB.getWritableDatabase();
				lugaresDB.insertarNuevaCategoria(categoria);
						
				
			}

		else {
			Toast.makeText(this, "El nombre de la categoría no puede" +
					" quedar en blanco", Toast.LENGTH_LONG).show();
		}
	}

	private boolean validar() {
		if (!nombreCategoria.toString().isEmpty()) {
			System.out.println("Prieba 1 True" +nombreCategoria.toString().isEmpty());
			return true;
		} else {
			System.out.println("Prieba 1 False" +nombreCategoria.toString().isEmpty());
			return false;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		categoria.setIdImagenCategoria(position);
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		categoria.setIdImagenCategoria(6);

	}
}
