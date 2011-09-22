package py.com.droidalu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
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

				Bundle params = new Bundle();
				params.putString("id", ci);
				params.putString("pin", pin);

				Intent intent = new Intent().setClass(LoginActivity.this,
						ListaNotasActivity.class);

				intent.putExtras(params);

				startActivity(intent);
			}
		});
	}
}