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
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
	private final static String HOSTNAME = "200.10.228.84";
	private final static int PORT = 80;
	private final static String URL = "/droidalu/services/consulta/notas";
	private String queryTemplate = "?id=#1&pin=#2";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Button okButton = (Button) findViewById(R.id.ok_button);

		okButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				EditText ciInput = (EditText) findViewById(R.id.input_ci);
				String ci = ciInput.getText().toString();

				EditText pinInput = (EditText) findViewById(R.id.input_pin);
				String pin = pinInput.getText().toString();
				
				String query = queryTemplate.replace("#1", ci).replace("#2", pin);
				
				String jsonResponse = getNotas(URL + query);

				if(jsonResponse.equals("{\"alumno\":\"\"}")) {
					AlertDialog.Builder alertbox = new AlertDialog.Builder(LoginActivity.this);
					 
		            alertbox.setMessage("No se obtuvo respuesta para ese usuario.");
		 
		            alertbox.setNeutralButton("Ok", null);
		 
		            alertbox.show();
		            
		            return;
				}
				
				Bundle params = new Bundle();
				params.putString("jsonResponse", jsonResponse);

				Intent intent = new Intent().setClass(LoginActivity.this,
						ListaNotasActivity.class);

				intent.putExtras(params);

				startActivity(intent);
			}
		});
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