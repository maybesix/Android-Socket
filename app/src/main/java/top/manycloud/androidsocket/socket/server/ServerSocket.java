package top.manycloud.androidsocket.socket.server;

import android.util.Log;

/**
 * @author xxh
 *
 */
public class ServerSocket {
    private static final String TAG = "ServerSocket";
    private ServerRunnable serverRunnable = null;
    private Thread thread = null;

    private ServerSocket() {
    }

    private static class VideoServerSocketInstance {
        private static final ServerSocket INSTANCE = new ServerSocket();
    }

    public static ServerSocket getInstance() {
        return VideoServerSocketInstance.INSTANCE;
    }

    //启动一个SocketServer的线程
    public void start() {
        //如果未启动
        if (serverRunnable == null || !serverRunnable.isStart()) {
            serverRunnable = new ServerRunnable();
            thread = new Thread(serverRunnable);
            thread.start();
        } else {
            Log.i(TAG, "start:已经启动 ");
        }
    }

    public void sendeMessage(String message) {
        serverRunnable.sendMessage(message);
    }

    public void stop() {
        serverRunnable.stop();
    }

    public boolean isStart() {
        if (serverRunnable == null) {
            return serverRunnable.isStart();
        }else {
            return false;
        }
    }

}
