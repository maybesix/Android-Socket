# Android-Socket

#### 项目介绍
可以直接使用的socket程序。

#### 软件架构
在Android端拿来即用的socket通讯程序。
引用的依赖是为了方便，随时可以去掉，不影响代码结构。

#### 使用说明
```
//服务器端开启
ServerSocket.getInstance().start();

//服务器端关闭
ServerSocket.getInstance().stop();

//客户端开启前，先配置IP
SocketConfig.IP="xxx.xxx.xxx.xxx";
ClientSocket.getInstance().start();

//客户端关闭
ClientSocket.getInstance().stop();

//服务器端发送
ServerSocket.getInstance().sendeMessage("message");

//客户端发送
ClientSocket.getInstance().sendMessage("message");

//注意：执行ServerSocket.getInstance().stop();不会停止已经构成连接的socket通讯接受线程。
//执行ClientSocket.getInstance().stop(); 才会停止已经构成连接的服务器线程。
```
