package atguigu.com.exermebileplayer.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import atguigu.com.exermebileplayer.R;

public class SystemVideoPlayerActivity extends AppCompatActivity {

    private VideoView vv;
    private Uri uti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_video_player);
        vv = (VideoView) findViewById(R.id.vv);
        uti = getIntent().getData();

        //准备好播放
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vv.start();
            }
        });
        //播放出错
        vv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(SystemVideoPlayerActivity.this, "播放出错", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //播放完成
        vv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(SystemVideoPlayerActivity.this, "播放完成", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        vv.setVideoURI(uti);
        vv.setMediaController(new MediaController(this));
    }

}
