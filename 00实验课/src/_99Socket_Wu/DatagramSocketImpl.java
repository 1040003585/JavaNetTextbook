package _99Socket_Wu;

import java.io.IOException;


public abstract class DatagramSocketImpl {

    protected abstract void receive(DatagramPacket p) throws IOException;
    protected abstract void send(DatagramPacket p) throws IOException;

}
