package com.example.abdussamed.getraenkelisteapp.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abdussamed.getraenkelisteapp.R;
import com.example.abdussamed.getraenkelisteapp.other.DatabaseHelper;
import com.example.abdussamed.getraenkelisteapp.other.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Abdussamed on 1/20/2017.
 */

public class SignUp extends Activity {

    DatabaseHelper dbhelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

    }

    public void getCard(String cardserial){

        new MyNetworkAsync().execute("http://nilschristian.bplaced.net/app/getcard.php?cardserial="+cardserial);

    }
    //id, cardserial ; inserts id and cardserial to serverdb
    public void linkCard(int id, String cardserial){
        new MyNetworkAsync().execute("http://nilschristian.bplaced.net/app/linkcard.php?userid="+id+"&cardserial="+cardserial);
    }

    //gets all beverages
    public void getAllBeverages(){

    }
    //should return 1 or 0 if product exists or not
    public void getBeverage(String productname){

    }
    //inserts new beverage into db
    public void newBeverage(String barcode, String productname, String price){

    }
    //keeps record of user consuming product, which user, which product, how many
    public void consumeDrink(int userid, int amount, String barcode){

    }
    //should return 1 if barcode exists, 0 if not
    public void getBarcode(String barcode){

    }
    //should update the price of an old product
    public void updatePrice(){

    }


    public void onSignUpClicked(View view){

        if(view.getId() == R.id.Bsignupbutton){
            EditText name = (EditText) findViewById(R.id.TFname);
            EditText email= (EditText) findViewById(R.id.TFemail);
            EditText username= (EditText) findViewById(R.id.TFusername);
            EditText pass1= (EditText) findViewById(R.id.TFpassword1);
            EditText pass2= (EditText) findViewById(R.id.TFpassword2);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String usernamestr = username.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!(pass1str.equals(pass2str))){
                //popupmessage
                Toast passToast = Toast.makeText(SignUp.this,"Passwords don't match!",Toast.LENGTH_SHORT );
                passToast.show();
            }
            else{
                //insert details to database

                User user = new User();
                user.setName(namestr);
                user.setUsername(usernamestr);
                user.setPass(pass1str);
                user.setEmail(emailstr);


                dbhelper.insertUser(user);
               // getCard(emailstr);
                //linkCard(3, emailstr);
                //Toast toast = Toast.makeText(SignUp.this,emailstr,Toast.LENGTH_SHORT );
                //toast.show();

            }
        }
    }
}

class MyNetworkAsync extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... string) {
        String a = "notdefined";
        try {
            a = getHTML(string[0].toString());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Log.d("getHTML", a);

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        String s = result.toString();
        Log.d("getHTMLinsideFunc",s);
        return s;
    }
}


