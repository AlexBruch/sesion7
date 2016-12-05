package com.s7.lasalle.sesion7;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterItem adapterItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Connection().execute();
    }

    private class Connection extends AsyncTask<String, String, String>{

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        HttpURLConnection connection;
        URL urlJSON = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog.setMessage("Descargando datos...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            try{

                /** DESCARREGAR INFO JSON **/
                urlJSON = new URL("http://www.v2msoft.com/curso-android/ws/lista_eventos_abiertos.php");
            }catch (MalformedURLException e) {
                e.printStackTrace();
                return e.toString();
            }try{
                connection = (HttpURLConnection) urlJSON.openConnection();
                connection.setRequestMethod("GET");

                /** PER CONFIRMAR QUE REBEM LA INFO **/
                connection.setDoInput(true);

            }catch (IOException e1) {

                e1.printStackTrace();
                return e1.toString();

            }try{
                int response_code = connection.getResponseCode();

                if(response_code == HttpURLConnection.HTTP_OK) {
                    InputStream input = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    return (stringBuilder.toString());
                } else {
                    return ("Fallo de connexión");
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                connection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String string) {
            progressDialog.dismiss();
            List<ItemList> info = new ArrayList<>();
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);

            progressDialog.dismiss();
            try{
                /** TREIEM LA INFO DEL JSON I LA POSEM EN UN ARRAYLIST COM SI FOS UN OBJECTE **/
                JSONArray jsonArray = new JSONArray(string);

                for(int x=0;x<jsonArray.length();x++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(x);
                    ItemList itemList = new ItemList();
                    itemList.name = jsonObject.getString("nombre");
                    itemList.details = jsonObject.getString("descripcion");
                    itemList.price = jsonObject.getString("precio");

                    info.add(itemList);
                }
                /** PASSEM LA INFO AL RECYCLERVIEW **/
                recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
                adapterItem = new AdapterItem(MainActivity.this, info);
                recyclerView.setAdapter(adapterItem);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);


            } catch (JSONException e) {
                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

}
