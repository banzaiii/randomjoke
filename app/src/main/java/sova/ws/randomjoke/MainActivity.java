package sova.ws.randomjoke;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.io.UnsupportedEncodingException;


public class MainActivity extends ActionBarActivity {

    public TextView jokeView;
    public Button nextButton;
    final  String TAG = "MainActivity";
    public String json_url = "http://rzhunemogu.ru/RandJSON.aspx?CType=2";
    public String lastJoke = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jokeView = (TextView) findViewById(R.id.jokeView);
        nextButton = (Button) findViewById(R.id.nextButton);



        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextButton.setEnabled(false);
                loadJoke();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if( lastJoke.equals("") ) {
            loadJoke();
        } else {
            jokeView.setText(lastJoke);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadJoke()
    {
        jokeView.setText(R.string.jokeText);
        final NetApi rzhunemogu = new NetApi();

        rzhunemogu.getJson(json_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String joke = null;
                try {
                    joke = rzhunemogu.getJoke(new String(bytes, "CP-1251"));
                    byte[] bytesutf = joke.getBytes("UTF-8");
                    String joke_utf;
                    joke_utf = new String(bytesutf);

                    if( joke_utf.equals("") ) {
                        loadJoke();
                    } else {
                        jokeView.setText(joke_utf);
                    }


                    lastJoke =  joke_utf;
                    nextButton.setEnabled(true);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });


        //String json = rzhunemogu.getJson("http://rzhunemogu.ru/RandJSON.aspx?CType=1");
        //Log.d(TAG, "JSON:" + json);

    }


}
