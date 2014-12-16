package fernandowirtz.pruebaosmeuslugares;

import java.util.Vector;



import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.Toast;

public class ListLugares extends ListActivity implements OnItemClickListener{

	private ListLugaresAdapter listLugaresAdapter;
	private ListActivity listaLugares;
	ListView lista;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_lugares);
		lista= (ListView) findViewById(android.R.id.list);
		lista.setOnItemClickListener(this);
		listLugaresAdapter = new ListLugaresAdapter(this);
		setListAdapter(listLugaresAdapter);
		registerForContextMenu(lista);
		//listaLugares.onOptionsItemSelected(item)
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.lugares, menu);
		return true;
	}

	
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_contextual, menu);
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
		if (id == R.id.editarLugar) {
			Intent i = new Intent(this, EditLugares.class);
			i.putExtra("add", true);
			startActivity(i);
		}
		if (id == R.id.anhadirCategoria) {
			Intent i = new Intent(this, EditarCategoria.class);
			startActivity(i);
		}
		
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		System.out.println("clickea");
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Lugar lugar = (Lugar) listLugaresAdapter.getItem(info.position);
		
		
		if (item.getItemId() ==R.id.itemBorrar) {
			LugaresDB db = new LugaresDB(this);
			db.getWritableDatabase();
			db.borrarLugar(lugar);
			
			Toast.makeText(this, "Borrado", Toast.LENGTH_SHORT).show();
		}
		
		
		if (item.getItemId() ==R.id.itemEditar) {
			Intent i = new Intent(this, EditLugares.class);
			i.putExtra("add", false);
			i.putExtra("lugar", lugar);
			startActivity(i);
		}

		if (item.getItemId() == R.id.itemListarCategorias) {
			Intent i = new Intent(this, ListaCategorias.class);
			startActivity(i);
		}
		
		return super.onContextItemSelected(item);
	}
	
}
