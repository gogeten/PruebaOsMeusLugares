package fernandowirtz.pruebaosmeuslugares;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;

public class LugaresDB extends SQLiteOpenHelper {
	private static int version = 2;
	private static String nombreDB = "lugares.db";
	private static CursorFactory factory = null;
	private SQLiteDatabase db;
	private final String LOGTAG = "LugaresDB";
	Cursor cursor;
	Long idCategoria;

	public LugaresDB(Context context) {
		super(context, nombreDB, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		this.db = db;
		crearTablas(db);
		insertarCategoriasPrueba();
		insertarLugaresPrueba();
	}
	
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/* TODO Auto-generated method stub
		Log.i("INFO", "Base de datos: onUpgrade"+oldVersion+"->"+newVersion);
		if (newVersion > oldVersion) {
			try {
			db.execSQL("DROP TABLE IF EXISTS lugar");
			db.execSQL("DROP INDEX IF EXISTS IND_Lugar_NOMBRE");
			db.execSQL("DROP TABLE IF EXISTS categoria");
			db.execSQL("DROP INDEX IF EXISTS IND_Categoria_Nombre");
			insertarCategoriasPrueba();
			insertarLugaresPrueba();
			}catch(Exception e){
				Log.e(this.getClass().toString(), e.getMessage());
			}
			onCreate(db);
			
			Log.i(this.getClass().toString(), "Base de datos actualizada. versi—n 2");
		}*/
		
	}

	private void crearTablas(SQLiteDatabase db) {
		try {
			/*
			 * Creamos Tabla Lugar
			 */
			String createTable = "CREATE TABLE Lugar ("
					+ " lugar_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " lugar_nombre TEXT NOT NULL,"
					+ " lugar_categoria_id INTEGER NOT NULL,"
					+ " lugar_direccion TEXT NOT NULL,"
					+ " lugar_telefono TEXT," 
					+ " lugar_url TEXT,"
					+ " lugar_comentario TEXT, "
					+ " lugar_categoria_id_imagen );";
			db.execSQL(createTable);
			/*
			 * Índice único de nombre Lugar
			 */
			createTable = "CREATE UNIQUE INDEX IND_Lugar_NOMBRE ON Lugar (lugar_nombre ASC)";
			db.execSQL(createTable);
			/*
			 * Ahora creamos Tabla Categorías
			 */
			createTable = ("CREATE TABLE Categoria ("
					+ "categoria_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "categoria_nombre TEXT NOT NULL, " +
					"categoria_id_imagen INTEGER);");
			db.execSQL(createTable);
			/*
			 * Índice único de nombre Categoría
			 */
			createTable = "CREATE INDEX IND_Categoria_Nombre ON Categoria (categoria_nombre ASC)";
			db.execSQL(createTable);
		} catch (SQLException e) {
			Log.e("INFO", e.getMessage());
		}
	}

	private void insertarCategoriasPrueba() {
		try {
			db.execSQL("INSERT INTO Categoria (categoria_nombre, categoria_id_imagen) VALUES ('Playas',1)");
			db.execSQL("INSERT INTO Categoria (categoria_nombre, categoria_id_imagen) VALUES ('Restaurantes',2)");
			db.execSQL("INSERT INTO Categoria (categoria_nombre, categoria_id_imagen) VALUES ('Hoteles',3)");
			db.execSQL("INSERT INTO Categoria (categoria_nombre, categoria_id_imagen) VALUES ('Otros',4)");
		} catch (SQLException e) {
			Log.e("INFO", e.getMessage());
		}
	}

	private void insertarLugaresPrueba() {
		try {
			db.execSQL("INSERT INTO Lugar(lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_comentario, lugar_categoria_id_imagen) "
					+ "VALUES('Praia de Riazor',1, 'Riazor','981000000','','',0)");
			db.execSQL("INSERT INTO Lugar(lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_comentario, lugar_categoria_id_imagen) "
					+ "VALUES('Praia do Orzan',1, 'Orzan','981000000','','',0)");
			db.execSQL("INSERT INTO Lugar(lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_comentario, lugar_categoria_id_imagen) "
					+ "VALUES('La Mamma',2, 'Maestro Mateo','981000000','','',1)");
			db.execSQL("INSERT INTO Lugar(lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_comentario, lugar_categoria_id_imagen) "
					+ "VALUES('La Bottega',2, 'Calle Estrella','981000000','','',1)");
			db.execSQL("INSERT INTO Lugar(lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_comentario, lugar_categoria_id_imagen) "
					+ "VALUES('Hotel Riazor',3, 'Maestro Mateo','981000000','','',2)");
			db.execSQL("INSERT INTO Lugar(lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_comentario, lugar_categoria_id_imagen) "
					+ "VALUES('Hostal Manzanares',3, 'Rubine','981000000','','',2)");
			db.execSQL("INSERT INTO Lugar(lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_comentario, lugar_categoria_id_imagen) "
					+ "VALUES('El Balneario',4, 'Vilaboa','981000000','','',3)");
			db.execSQL("INSERT INTO Lugar(lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_comentario, lugar_categoria_id_imagen) "
					+ "VALUES('Camping Play',4, 'Cambre','981000000','','',3)");
		} catch (SQLException e) {
			Log.e("INFO", e.getMessage());
		}
	}

	/**
	 * Realiza una consulta SQL con los datos de los lugares y el nombre de la
	 * categoria
	 * 
	 * @return Vector <Lugar>
	 */
	public Vector<Lugar> cargarLugaresDesdeBD() {
		Vector<Lugar> vectorLugares = new Vector<Lugar>();
		SQLiteDatabase db = this.getReadableDatabase();
		String consultaSQL = "SELECT Lugar.*, categoria_nombre, categoria_id_imagen "
				+ "FROM Lugar JOIN Categoria ON Lugar.lugar_categoria_id = Categoria.categoria_id";
		Cursor cursor = db.rawQuery(consultaSQL, null);

		while (cursor.moveToNext()) {
			
			try {
				Lugar lugar = new Lugar();
				lugar.setId(cursor.getLong(cursor.getColumnIndex(lugar.LUGAR_ID)));
				lugar.setNombre(cursor.getString(cursor
						.getColumnIndex(lugar.LUGAR_NOMBRE)));
				Long idCategoria = cursor.getLong(cursor
						.getColumnIndex(lugar.LUGAR_CATEGORIA_ID));
				String nombreCategoria = cursor.getString(cursor
						.getColumnIndex(Categoria.CATEGORIA_NOMBRE));
				Long id_imagen=cursor.getLong(cursor.getColumnIndex(Categoria.CATEGORIA_ID_IMAGEN));
				lugar.setCategoria(new Categoria(idCategoria, nombreCategoria, id_imagen));
				lugar.setDireccion(cursor.getString(cursor
						.getColumnIndex(lugar.LUGAR_DIRECCION)));
				lugar.setTelefono(cursor.getString(cursor
						.getColumnIndex(lugar.LUGAR_TELEFONO)));
				lugar.setUrl(cursor.getString(cursor
						.getColumnIndex(lugar.LUGAR_URL)));
				lugar.setComentario(cursor.getString(cursor
						.getColumnIndex(lugar.LUGAR_COMENTARIO)));

				vectorLugares.add(lugar);
				
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vectorLugares;
	}

	public Vector<Categoria> cargarCategoriasDB() {
		Vector<Categoria> listaCategoria = new Vector<Categoria>();
		SQLiteDatabase db = this.getReadableDatabase();
		String consultaSQL = "SELECT * FROM Categoria";
		Cursor cursor = db.rawQuery(consultaSQL, null);

		while (cursor.moveToNext()) {
			Categoria categoria = new Categoria();
			categoria.setId(cursor.getLong(cursor
					.getColumnIndex(Categoria.CATEGORIA_ID)));
			categoria.setNombre(cursor.getString(cursor
					.getColumnIndex(Categoria.CATEGORIA_NOMBRE)));
			categoria.setIdImagenCategoria(cursor.getLong(cursor.
					getColumnIndex(Categoria.CATEGORIA_ID_IMAGEN)));
			listaCategoria.add(categoria);
		}
		return listaCategoria;
	}

	public void insertarNuevoLugar(Bundle bundle) {
		SQLiteDatabase db = getWritableDatabase();

		try {
			/**
			 * Obtenemos id de categoría por su valor en el spinner
			 */
			try {
				String consultaID ="select categoria_id from Categoria where categoria_nombre ='"
						+ bundle.getString("categoria_nombre") + "'";
						System.out.println(bundle.getString("categoria_nombre"));
				cursor = db.rawQuery(consultaID , null);
			
			cursor.moveToPosition(0);
			idCategoria = cursor.getLong(0);
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.i("Mostrando ID Categoria: ", ""+idCategoria );
			}
			
			String SQL =("INSERT INTO Lugar (lugar_nombre, lugar_categoria_id, lugar_direccion, lugar_telefono, lugar_url, lugar_categoria_id_imagen, lugar_comentario) "
					+ "VALUES ('"
					+ bundle.getString("lugar_nombre")
					+ "',"
					+ idCategoria
					+ ",'"
					+ bundle.getString("lugar_direccion")
					+ "','"
					+ bundle.getString("lugar_telefono")
					+ "','"
					+ bundle.getString("lugar_url")
					+ "','"
					+ bundle.getLong("lugar_categoria_id_imagen")
					+ "','"
					+ bundle.getString("lugar_comentario") + "')");
			db.execSQL(SQL);
			
			Log.i(LOGTAG,"Se terminó de insertar");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Log.e("Error al insertar Nuevo Lugar", e.getMessage());
			
		}

	}
	
	public void insertarNuevaCategoria (Categoria categoria) {
		SQLiteDatabase db = getWritableDatabase();
		db.insert("Categoria", null, categoria.getContentValuesCategoria());
		System.out.println(categoria.getNombre());
		
	}

	public void borrarLugar(Lugar l) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = getWritableDatabase();
		db.delete("Lugar", "lugar_nombre = '" + l.getNombre()+"'", null);
	}

	public void updateLugar(Lugar l) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = getWritableDatabase();
		db.update("Lugar", l.getContentValuesCategoria(), 
				"lugar_nombre = '"+l.getNombre()+"'", null);
	}

}
