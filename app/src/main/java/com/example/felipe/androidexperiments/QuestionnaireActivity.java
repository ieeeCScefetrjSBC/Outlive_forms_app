package com.example.felipe.androidexperiments;

import android.content.Context;
import android.os.CountDownTimer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class QuestionnaireActivity extends AppCompatActivity {

    Button button_save1;
    ProgressBar indeterminateBar;
    EditText edit_text1, edit_text2, edit_text3, edit_text4;
    Spinner spinner1, spinner2, spinner3;

    String text1,text2,text3,text4, spin1, spin2, spin3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        button_save1 = (Button)findViewById(R.id.button_save1);
        edit_text1 = (EditText)findViewById(R.id.editText1);
        edit_text2 = (EditText)findViewById(R.id.editText2);
        edit_text3 = (EditText)findViewById(R.id.editText3);
        edit_text4 = (EditText)findViewById(R.id.editText4);
        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        indeterminateBar = (ProgressBar)findViewById(R.id.indeterminateBar);

        //writeFile("teste.txt", "append");

        button_save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IndicateProgress(true);

                if(!edit_text1.getText().toString().isEmpty()&&
                   !edit_text2.getText().toString().isEmpty()&&
                   !edit_text3.getText().toString().isEmpty()&&
                   !edit_text4.getText().toString().isEmpty()){

                    text1 = edit_text1.getText().toString();
                    text2 = edit_text2.getText().toString();
                    text3 = edit_text3.getText().toString();
                    text4 = edit_text4.getText().toString();

                    spin1 = spinner1.getSelectedItem().toString();
                    spin2 = spinner2.getSelectedItem().toString();
                    spin3 = spinner3.getSelectedItem().toString();

                    writeToSDFile(text1,text2,text3,text4, spin1, spin2, spin3);

                }else{
                    indeterminateBar.setVisibility(View.GONE);
                    Toast.makeText(QuestionnaireActivity.this, "Um dos campos de texto está vazio!"+
                            " Por favor preencha antes de continuar.", Toast.LENGTH_LONG).show();
                }
                IndicateProgress(false);

                //writeFile("teste.txt", edit_text.getText().toString());
                //readFile("teste.txt");
            }
        });


    }

    void IndicateProgress(boolean ind) {
        if(ind){
            indeterminateBar.setVisibility(View.VISIBLE);
            return;
        }
        //################################
        CountDownTimer counter = new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                indeterminateBar.setVisibility(View.GONE);
            }
        }.start();
        //################################
    }


    private void writeToSDFile(String txt1,String txt2,String txt3,String txt4,
                               String spin1,String spin2,String spin3){

        // Find the root of the external storage.
        // See http://developer.android.com/guide/topics/data/data-  storage.html#filesExternal

        File root = Environment.getExternalStoragePublicDirectory("/formsGames");

        // See http://stackoverflow.com/questions/3551821/android-write-to-sd-card-folder

        File dir = new File (root, "download");
        dir.mkdirs();
        File file = new File(dir, "myData.csv");

        try {
            FileOutputStream f = new FileOutputStream(file, true);
            PrintWriter pw = new PrintWriter(f);
            pw.append(String.format("\"%1$s\",\"%2$s\",\"%3$s\",\"%4$s\",\"%5$s\",\"%6$s\",\"%7$s\"\n", text1, spin1, spin2, spin3, text2, text3, text4));
            pw.flush();
            pw.close();
            f.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.i("anything: ", "******* File not found. Did you" +
                    " add a WRITE_EXTERNAL_STORAGE permission to the   manifest?");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



//    private void writeFile(String FileName, String message){
//        try {
//            FileOutputStream fos = openFileOutput(FileName, Context.MODE_APPEND);
//            fos.write(message.getBytes());
//            fos.close();
//            Toast.makeText(this,"teste salvo", Toast.LENGTH_LONG).show();
//
//        } catch (java.io.IOException e){
//            e.printStackTrace();
//        }
//    }
//    private void readFile(String FileName){
//        try{
//            FileInputStream fin = openFileInput(FileName);
//            InputStreamReader inputStream = new InputStreamReader(fin);
//            BufferedReader bufferedReader = new BufferedReader(inputStream);
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while((line = bufferedReader.readLine())!= null){
//                stringBuilder.append(line);
//            }
//            fin.close();
//            inputStream.close();
//            String teste = stringBuilder.toString();
//            //Toast.makeText(this,"Teste retornado: "+teste, Toast.LENGTH_LONG).show();
//            titulo.setText(teste);
//            indeterminateBar.setVisibility(View.GONE);
//        }catch(java.io.IOException e){
//            e.printStackTrace();
//        }
//    }
}
