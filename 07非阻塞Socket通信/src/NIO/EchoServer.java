package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServer {
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private int port = 8888;

	public EchoServer() {
		try {
			// 创建一个Selector对象
			selector = Selector.open();
			// 创建一个ServerSocketChannel对象
			serverSocketChannel = ServerSocketChannel.open();
			// 使得在同一主机上关闭了服务器程序，紧接着再启动该服务器程序时，可以顺利绑定到相同的端口
			serverSocketChannel.socket().setReuseAddress(true);
			// 使ServerSocketChannel工作于非阻塞模式
			serverSocketChannel.configureBlocking(false);
			// 把服务器进程与一个本地端口绑定
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void receive(SelectionKey key) throws IOException {
		//获得与SelectionKey关联的附件
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		//获取与SelectionKey关联的SocketChannel
		SocketChannel socketChannel = (SocketChannel) key.channel();
		//创建一个ByteBuffer，用于存放读到的数据
		ByteBuffer readBuffer = ByteBuffer.allocate(32);
		socketChannel.read(readBuffer);
		readBuffer.flip();
		//将buffer的极限设为容量
		buffer.limit(buffer.capacity());
		//把readBuffer的内容拷贝到buffer中
		//假定buffer的容量足够大，不会出现缓冲区溢出异常
		buffer.put(readBuffer);
	}

	public void service() throws IOException {
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (selector.select() > 0) {
			// 获得Selector的selected-keys集合
			Set<SelectionKey> readKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = null;
				try {
					// 取出一个SelectionKey
					key = iterator.next();
					// 把SelectionKey从Selector的selected-key集合中删除
					iterator.remove();
					if (key.isAcceptable()) {
						// 处理接收连接就绪事件
						// 获取与SelectionKey关联的ServerSocketChannel
						ServerSocketChannel ssc = (ServerSocketChannel) key
								.channel();
						// 获得与客户端连接的SocketChannel
						SocketChannel socketChannel = ssc.accept();
						System.out.println("接收到客户连接，来自："
								+ socketChannel.socket().getInetAddress() + ":"
								+ socketChannel.socket().getPort());
						// 将SocketChannel设置为非阻塞模式
						socketChannel.configureBlocking(false);
						// 创建一个用于存放用户发送来的数据缓冲区
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						// SocketChannel向Selector注册读就绪和写就绪事件,关联一个buffer附件
						socketChannel.register(selector, SelectionKey.OP_READ
								| SelectionKey.OP_WRITE, buffer);
					}
					if (key.isReadable()) {
						// 处理读就绪事件
						receive(key);
					}
					if (key.isWritable()) {
						// 处理写就绪事件
					}
				} catch (Exception e) {
					if (key != null) {
						// 使得这个SelectionKey失效
						// 使得Selector不再监控这个SelectionKey感兴趣的事件
						key.cancel();
						// 关闭与这个SelectionKey关联的SocketChannel
						key.channel().close();
					}
				}

			}
		}

	}

	public static void main(String[] args) {

	}

}