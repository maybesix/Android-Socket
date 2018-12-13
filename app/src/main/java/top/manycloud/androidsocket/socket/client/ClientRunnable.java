package top.manycloud.androidsocket.socket.client;

import android.util.Log;

import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

import top.manycloud.androidsocket.socket.SocketConfig;

/**
 * @author xxh
 * @date 2018/12/11
 */
public class ClientRunnable implements Runnable {

    private static final String TAG = "ClientRunnable";
    private boolean isStart = false;
    private Socket socket;
    private String ip;
    //定义ServerSocket的端口号

    @Override
    public void run() {
        try {
            socket = new Socket(SocketConfig.IP, SocketConfig.PORT);
            isStart = true;
            StartSucces();
            try {
                //获取该socket对应的输入流
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String content = "";
                while ((content = bufferedReader.readLine()) != null) {
                    Log.i(TAG, "run: 收到来自服务器的消息：" + content);
                    ToastUtils.showLong("客户端收到消息:" + content);
                }
            } catch (SocketException e) {
                e.printStackTrace();
                Log.i(TAG, "客户端停止：");
                ToastUtils.showLong("客户端停止" );

            }  catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //用于发送消息
    public void sendMessage(String message) {
        if (isStart) {
            ThreadUtils.executeBySingle(new ClientPushTask(socket, message));
        } else {
            Log.e(TAG, "sendMessage: socket服务器未连接");
        }
    }

    //启动成功的操作在里面写
    private void StartSucces() {
        Log.i(TAG, "StartSucces: SocetServer启动成功");
    }

    public boolean isStart() {
        return isStart;
    }

    public void stop() {
        try {
            if (socket != null && isStart == true) {
                socket.close();
                isStart = false;
            } else {
                Log.i(TAG, "stop: 未开启ClientSocket");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
