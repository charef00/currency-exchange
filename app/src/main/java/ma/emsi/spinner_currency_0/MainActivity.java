package ma.emsi.spinner_currency_0;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerfrom;
    private Spinner spinnerto;
    private Button calculate;
    private EditText amount;
    private TextView txt;
    private ArrayAdapter<String> modelFrom;
    private ArrayAdapter<String> modelTo;
    String[] currencies={"MAD","USD","EUR"};
    String[] symbol={"DH","$","€"};

    double[][] xchange=
            {
                {1,0.097,0.091},
                {10.34,1,0.94},
                {11.01,1.06,1}
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_xchange);
        spinnerfrom=findViewById(R.id.spinnerFrom);
        spinnerto=findViewById(R.id.spinnerTo);
        modelFrom=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,currencies);
        spinnerfrom.setAdapter(modelFrom);

        spinnerto.setAdapter(modelFrom);
        calculate=findViewById(R.id.btnCalculate);
        amount=findViewById(R.id.amount);
        txt=findViewById(R.id.result);

        spinnerfrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                modelTo=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,symbol);
                spinnerto.setAdapter(modelTo);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value=Integer.parseInt(amount.getText().toString());

                int from=Integer.parseInt(spinnerfrom.getSelectedItemId()+"");
                int to=Integer.parseInt(spinnerto.getSelectedItemId()+"");
                double calcule=value*xchange[from][to];
                Toast.makeText(MainActivity.this,calcule+"",Toast.LENGTH_SHORT).show();
                txt.setText(calcule+" "+symbol[to]);
            }
        });

    }
}