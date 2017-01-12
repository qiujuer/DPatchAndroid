package net.qiujuer.sample.dpatch;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import net.qiujuer.dpatch.DPatchBSDiffCreator;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doDiff();
    }

    private void doDiff() {
        new Thread() {
            @Override
            public void run() {

                String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File pPath = new File(sdPath + File.separator + "Download"
                        + File.separator);
                if (!pPath.exists()) pPath.mkdirs();

                Log.e("TAG", DPatchBSDiffCreator.create().diff(new File(pPath.getAbsoluteFile(), "2.6.9.apk").getAbsolutePath(),
                        new File(pPath.getAbsoluteFile(), "2.7.1.apk").getAbsolutePath(),
                        new File(pPath.getAbsoluteFile(), "patch.patch").getAbsolutePath()) + " ");

                show("done1");

                try {
                    new File(pPath.getAbsoluteFile(), "new.apk").createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.e("TAG", DPatchBSDiffCreator.create().patch(new File(pPath.getAbsoluteFile(), "2.6.9.apk").getAbsolutePath(),
                        new File(pPath.getAbsoluteFile(), "new.apk").getAbsolutePath(),
                        new File(pPath.getAbsoluteFile(), "patch.patch").getAbsolutePath()) + " ");

                show("done2");
            }
        }.start();
    }


    private void show(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                TextView tv = (TextView) findViewById(R.id.sample_text);
                tv.setText(msg);
            }
        });
    }
}
