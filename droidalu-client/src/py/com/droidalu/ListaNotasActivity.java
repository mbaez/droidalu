package py.com.droidalu;

import java.util.ArrayList;
import java.util.List;

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
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_notas);
		
		Bundle params = getIntent().getExtras();
		String jsonResponse = params.getString("jsonResponse");

		Gson gson = new Gson();
		Response resp = gson.fromJson(jsonResponse, Response.class);
		TextView nombre = (TextView) findViewById(R.id.label_nombre);
		nombre.setText(resp.getAlumno().getNombre() + "\n"
				+ resp.getAlumno().getApellido());

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
			for (Nota nota : alumno.getNotas()) {
				mTitles.add(nota.getAsignatura());
				mContents.add(nota.getCalificacion() + "\t"
						+ nota.getCalificacionLetra());
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
				view = mInflater.inflate(R.layout.item_nota, parent, false);
			} else {
				view = convertView;
			}

			TextView title = (TextView) view.findViewById(R.id.nombre_materia);
			title.setText(mTitles.get(position));
			ImageView image = (ImageView) view.findViewById(R.id.icono);
			String nota = mContents.get(position);

			if (nota.contains("1"))
				image.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_nota_1));
			else if (nota.contains("2"))
				image.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_nota_2));
			else if (nota.contains("3"))
				image.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_nota_3));
			else if (nota.contains("4"))
				image.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_nota_4));
			else if (nota.contains("5"))
				image.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_nota_5));

			title.setSelected(true);

			TextView content = (TextView) view.findViewById(R.id.nota);
			content.setText(nota);

			return view;
		}

	}
}
