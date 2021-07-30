package com.example.android2;

import static retrofit2.converter.gson.GsonConverterFactory.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private TextView mJsonTxtView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mJsonTxtView = findViewById(R.id.jsonText);
        getPosts();


    }

    private void getPosts (){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonAPI jsonAPI = retrofit.create(JsonAPI.class);
        Call<List<Posts>> call = jsonAPI.getPosts();

        call.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if(!response.isSuccessful()){
                    mJsonTxtView.setText("Codigo"+response.code());
                    return;
                }

                List<Posts> postsList = response.body();

                for(Posts post: postsList){
                    String content = "";
                    content += "userId: "+post.getUserId()+"\n\n";
                    content += "id: "+post.getId()+"\n\n";
                    content += "title: "+post.getTitle()+"\n\n";
                    content += "body: "+post.getBody()+"\n\n";

                    mJsonTxtView.append(content);

                }

            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                    mJsonTxtView.setText(t.getMessage());

            }
        });


    }

}