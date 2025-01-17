package _99Socket_Wu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileDescriptor;


public abstract class SocketImpl implements SocketOptions {

    Socket socket = null;
    ServerSocket serverSocket = null;

    protected FileDescriptor fd;

    protected InetAddress address;

    protected int port;

    protected int localport;

    protected abstract void create(boolean stream) throws IOException;

    protected abstract void connect(String host, int port) throws IOException;

    protected abstract void connect(InetAddress address, int port) throws IOException;

    protected abstract void connect(SocketAddress address, int timeout) throws IOException;

    protected abstract void bind(InetAddress host, int port) throws IOException;

    protected abstract void listen(int backlog) throws IOException;

    protected abstract void accept(SocketImpl s) throws IOException;

    protected abstract InputStream getInputStream() throws IOException;

    protected abstract OutputStream getOutputStream() throws IOException;

    protected abstract int available() throws IOException;

    protected abstract void close() throws IOException;

    protected void shutdownInput() throws IOException {
      throw new IOException("Method not implemented!");
    }

    protected void shutdownOutput() throws IOException {
      throw new IOException("Method not implemented!");
    }

    protected FileDescriptor getFileDescriptor() {
        return fd;
    }

    protected InetAddress getInetAddress() {
        return address;
    }

    protected int getPort() {
        return port;
    }
    protected boolean supportsUrgentData () {
        return false; // must be overridden in sub-class
    }
    protected abstract void sendUrgentData (int data) throws IOException;

    protected int getLocalPort() {
        return localport;
    }

    void setSocket(Socket soc) {
        this.socket = soc;
    }

    Socket getSocket() {
        return socket;
    }

    void setServerSocket(ServerSocket soc) {
        this.serverSocket = soc;
    }

    ServerSocket getServerSocket() {
        return serverSocket;
    }

    public String toString() {
        return "Socket[addr=" + getInetAddress() +
            ",port=" + getPort() + ",localport=" + getLocalPort()  + "]";
    }

    void reset() throws IOException {
        address = null;
        port = 0;
        localport = 0;
    }


    protected void setPerformancePreferences(int connectionTime,
                                          int latency,
                                          int bandwidth)
    {
        /* Not implemented yet */
    }
}
