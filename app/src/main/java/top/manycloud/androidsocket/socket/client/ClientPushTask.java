package top.manycloud.androidsocket.socket.client;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ThreadUtils;

import java.io.PrintStream;
import java.net.Socket;

/**
 * @author xxh
 * @date 2018/12/11
 */
public class ClientPushTask extends ThreadUtils.SimpleTask {
    Socket socket;
    String message;
    ClientPushTask(Socket socket,String message) {
        this.socket = socket;
        this.message = message;
    }
    @Nullable
    @Override
    public Object doInBackground() throws Throwable {
        PrintStream printStream = new PrintStream(socket.getOutputStream());
        //向该输出流中写入要广播的内容
        printStream.println(message);
        return null;
    }

    @Override
    public void onSuccess(@Nullable Object result) {

    }
}
