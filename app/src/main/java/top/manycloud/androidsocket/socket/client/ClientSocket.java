package top.manycloud.androidsocket.socket.client;

import android.util.Log;

import java.net.Socket;

/**
 * @author xxh
 * @date 2018.12.11
 */
public class ClientSocket {

    private static final String TAG = "ClientSocket";
    private Boolean isStart = false;
    private Socket socket;
    private ClientRunnable clientRunnable;
    private Thread thread;
    private static class VideoClientSocketInstance {
        private static final ClientSocket INSTANCE = new ClientSocket();

    }

    public static ClientSocket getInstance() {
        return VideoClientSocketInstance.INSTANCE;
    }

    public void start() {
        if (clientRunnable == null ||!clientRunnable.isStart()){
            clientRunnable = new ClientRunnable();
            thread = new Thread(clientRunnable);
            thread.start();
        }else {
            Log.i(TAG, "start: 客户端已经运行！" );        }
    }

    public void stop() {
        clientRunnable.stop();
    }
    private boolean isStart(){
        return isStart;
    }
    public void sendMessage(String message) {
        clientRunnable.sendMessage(message);
    }

}
