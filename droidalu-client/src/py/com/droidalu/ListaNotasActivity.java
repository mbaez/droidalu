package py.com.droidalu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;

public class ListaNotasActivity extends ListActivity {
	private final static String HOSTNAME = "200.10.228.84";
	private final static int PORT = 80;
	private final static String URL = "/droidalu/services/consulta/notas";
	private String query = "?id=#1&pin=#2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_notas);

		Bundle params = getIntent().getExtras();

		String id = params.getString("id");
		String pin = params.getString("pin");

		query = query.replace("#1", id).replace("#2", pin);
	    
		Gson gson = new Gson();
		Response resp = gson.fromJson(getNotas(URL + query), Response.class);
		
		TextView nombre = (TextView) findViewById(R.id.label_nombre);
		
		nombre.setText(resp.getAlumno().getNombre()+"\n"+resp.getAlumno().getApellido());
		
		setListAdapter(new ListaNotasCursorAdapter(this, resp.getAlumno()));
	}

	
	public class ListaNotasCursorAdapter extends BaseAdapter {
		private final LayoutInflater mInflater;

		@SuppressWarnings("unused")
		private Context mContext;

		private List<String> mTitles = new ArrayList<String>();

		private List<String> mContents = new ArrayList<String>();

		public ListaNotasCursorAdapter(Context context, Alumno alumno) {
			mContext = context;
			mInflater = LayoutInflater.from(context);
			init(alumno);
		}

		private void init(Alumno alumno) {
			int pos;

			for(Nota nota : alumno.getNotas()) {
				mTitles.add(nota.getAsignatura());
				mContents.add(nota.getCalificacion() + "\t" + nota.getCalificacionLetra());
			}
		}

		public int getCount() {
			return mTitles.size();
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view;

			if (convertView == null) {
				view = mInflater.inflate(R.layout.item_nota, parent,
						false);
			} else {
				view = convertView;
			}

			TextView title = (TextView) view.findViewById(R.id.nombre_materia);
			title.setText(mTitles.get(position));
			ImageView image = (ImageView) view.findViewById(R.id.icono);
			String nota = mContents.get(position);
			
			if(nota.contains("1"))
				image.setImageDrawable(getResources().getDrawable(R.drawable.ic_nota_1));
			else if(nota.contains("2"))
				image.setImageDrawable(getResources().getDrawable(R.drawable.ic_nota_2));
			else if(nota.contains("3"))
				image.setImageDrawable(getResources().getDrawable(R.drawable.ic_nota_3));
			else if(nota.contains("4"))
				image.setImageDrawable(getResources().getDrawable(R.drawable.ic_nota_4));
			else if(nota.contains("5"))
				image.setImageDrawable(getResources().getDrawable(R.drawable.ic_nota_5));
			
			title.setSelected(true);

			TextView content = (TextView) view
					.findViewById(R.id.nota);
			content.setText(nota);

			return view;
		}

	}
	/**
	 * 
	 * @param url
	 * @return
	 */
	private String getNotas(String url) {
		HttpGet getURL = new HttpGet(url);
		HttpHost host = new HttpHost(HOSTNAME, PORT);
		HttpClient client = new DefaultHttpClient();
		
		String responseText = null;
		HttpEntity entity = null;
		
		try {
			HttpResponse response = client.execute(host, getURL);
			entity = response.getEntity();
			responseText = EntityUtils.toString(entity);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entity != null) {
				try {
					entity.consumeContent();
				} catch (IOException e) {
				}
			}
		}

		return responseText;
	}
}
