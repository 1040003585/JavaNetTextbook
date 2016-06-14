package _97DatagramSocket_Wu;

/**
 * This interface defines a factory for datagram socket implementations. It
 * is used by the classes <code>DatagramSocket</code> to create actual socket
 * implementations.
 *
 * @author  Yingxian Wang
 * @see     java.net.DatagramSocket
 * @since   1.3
 */
public
interface DatagramSocketImplFactory {
    /**
     * Creates a new <code>DatagramSocketImpl</code> instance.
     *
     * @return  a new instance of <code>DatagramSocketImpl</code>.
     * @see     java.net.DatagramSocketImpl
     */
    DatagramSocketImpl createDatagramSocketImpl();
}
