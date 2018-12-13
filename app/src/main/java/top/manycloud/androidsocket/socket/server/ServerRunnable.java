package top.manycloud.androidsocket.socket.server;

import android.util.Log;


import com.blankj.utilcode.util.ThreadUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

import top.manycloud.androidsocket.socket.SocketConfig;

/**
 * @author xxh
 */
public class ServerRunnable implements Runnable {
    private static final String TAG = "ServerRunnable";
    private boolean isStart = false;
    private ServerSocket serverSocket;
    public static ArrayList<Socket> socketList = new ArrayList<>();
    private Socket socket = null;
    private HandleServerMessage handleServerMessage;

    public boolean isStart() {
        return isStart;
    }

    @Override
    public void run() {
        try {
            //创建一个ServerSocket，用于监听客户端Socket的连接请求
            serverSocket = new ServerSocket(SocketConfig.PORT);
            //启动成功的操作
            StartSucces();
            isStart = true;
            while (isStart) {
                socket = serverSocket.accept();
                ConnetSucces();
                //启动一个异步线程去接收数据
                handleServerMessage = new HandleServerMessage(socket);
                new Thread(handleServerMessage).start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
            Log.i(TAG, "服务端停止：");
            ToastUtils.showLong("服务器端停止" );
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run: 终止");

    }

    //启动成功的操作在里面写
    private void StartSucces() {
        Log.i(TAG, "StartSucces: SocetServer启动成功");
        ToastUtils.showShort("SocetServer启动成功");

    }

    private void ConnetSucces() {
        Log.i(TAG, "ConnetSucces: SocetServer连接成功");
        ToastUtils.showShort("SocetServer连接client成功");
    }
    // 发送方法，这里采用了线程池去启动发送线程
    public void sendMessage( String message) {
        if (isStart){
            ThreadUtils.executeBySingle(new ServerPushTask(socket,message));
        }else {
            Log.e(TAG, "sendMessage: socket服务器未连接" );
        }
    }
    //通过捕获异常退出
    public void stop() {
        try {
            if (serverSocket != null && isStart == true) {
                serverSocket.close();
                isStart = false;
            } else {
                Log.i(TAG, "stop: 未开启serverSocket");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
