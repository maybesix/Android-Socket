package top.manycloud.androidsocket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.NetworkUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.manycloud.androidsocket.socket.SocketConfig;
import top.manycloud.androidsocket.socket.client.ClientSocket;
import top.manycloud.androidsocket.socket.server.ServerSocket;

/**
 * @author xxh
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bt_server_start)
    Button btServerStart;
    @BindView(R.id.bt_server_stop)
    Button btServerStop;
    @BindView(R.id.bt_client_start)
    Button btClientStart;
    @BindView(R.id.bt_client_stop)
    Button btClientStop;
    @BindView(R.id.et_server)
    EditText etServer;
    @BindView(R.id.bt_server_send)
    Button btServerSend;
    @BindView(R.id.et_client)
    EditText etClient;
    @BindView(R.id.bt_client_send)
    Button btClientSend;
    @BindView(R.id.et_client_ip)
    EditText etClientIp;
    @BindView(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        textView.setText(NetworkUtils.getIPAddress(true));
    }

    @OnClick({R.id.bt_server_start, R.id.bt_server_stop, R.id.bt_client_start, R.id.bt_client_stop, R.id.bt_server_send, R.id.bt_client_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_server_start:
                //服务器端开启
                ServerSocket.getInstance().start();
                break;
            case R.id.bt_server_stop:
                //服务器端关闭
                ServerSocket.getInstance().stop();
                break;
            case R.id.bt_client_start:
                //客户端开启
                SocketConfig.IP = etClientIp.getText().toString();
                ClientSocket.getInstance().start();
                break;
            case R.id.bt_client_stop:
                //客户端关闭
                ClientSocket.getInstance().stop();
                break;
            case R.id.bt_server_send:
                //获取文本框内容然后发送
                ServerSocket.getInstance().sendeMessage(etServer.getText().toString());
                break;
            case R.id.bt_client_send:
                //获取文本框内容然后发送
                ClientSocket.getInstance().sendMessage(etClient.getText().toString());
                break;
            default:
                break;
        }
    }
}
