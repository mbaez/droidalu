package py.com.droidalu;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ListaNotasActivity extends Activity {
	private final static String HOSTNAME = "200.10.228.84";
	private final static int PORT = 8080;
	private final static String URL = "/droidalu/services/consulta/notas";
	private String query = "?id=#1&pin=#2";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista_notas);

		Bundle params = getIntent().getExtras();

		String id = params.getString("id");
		String pin = params.getString("pin");

		query = query.replace("#1", "2320385").replace("#2", "9999");

		TextView nombre = (TextView) findViewById(R.id.label_nombre);
		
		nombre.setText(getNotas(URL + query));
	}

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
