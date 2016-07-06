package ca.lalalala.retrofit4android16;

import org.json.JSONObject;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import ca.lalalala.retrofit4android16.restful.AuthImageDownloader;
import ca.lalalala.retrofit4android16.restful.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView textView;
    private ImageView imageView;
    private DisplayImageOptions options = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.test_button);
        imageView = (ImageView) findViewById(R.id.test_image);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connect();
            }
        });

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this)
                .imageDownloader(new AuthImageDownloader(getApplicationContext(),5000,5000))
                .build();
        ImageLoader.getInstance().init(config);

        connect();
    }

    private void connect(){
        ImageLoader.getInstance().displayImage("https://diandiangoal.com/Uploads/Merchant/5723740a15efc.png", imageView, options);
        Call<JSONObject> history = RestClient.restfulService.getHistory();
        history.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                Log.d(TAG, "onResponse: success");
                textView.setText("success");
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {
                Log.d(TAG, "onFailure: fail");
                textView.setText("fail");
            }
        });
    }
}
