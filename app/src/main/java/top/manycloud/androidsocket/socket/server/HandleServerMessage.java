package top.manycloud.androidsocket.socket.server;

import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author xxh
 * @date 2018/12/11
 */
public class HandleServerMessage implements Runnable {

    private static final String TAG = "HandleServerMessage";
    private BufferedReader bufferedReader;

    public HandleServerMessage(Socket socket) throws IOException {
        //获取该socket对应的输入流
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            String content = null;
            while ((content = bufferedReader.readLine()) != null) {
                Log.d("tag", "服务器端收到消息:" + content);
                ToastUtils.showLong("服务器端收到消息:" + content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "run: 结束socket服务器端消息接收线程");
    }
}
