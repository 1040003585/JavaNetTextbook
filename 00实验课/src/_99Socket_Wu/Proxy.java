package _99Socket_Wu;



/**
 * This class represents a proxy setting, typically a type (http, socks) and
 * a socket address.
 * A <code>Proxy</code> is an immutable object.
 *
 * @see     java.net.ProxySelector
 * @author Yingxian Wang
 * @author Jean-Christophe Collet
 * @since   1.5
 */
public class Proxy {

    /**
     * Represents the proxy type.
     *
     * @since 1.5
     */
    public enum Type {
        /**
         * Represents a direct connection, or the absence of a proxy.
         */
        DIRECT,
        /**
         * Represents proxy for high level protocols such as HTTP or FTP.
         */
        HTTP,
        /**
         * Represents a SOCKS (V4 or V5) proxy.
         */
        SOCKS
    };

    private Type type;
    private SocketAddress sa;

    /**
     * A proxy setting that represents a <code>DIRECT</code> connection,
     * basically telling the protocol handler not to use any proxying.
     * Used, for instance, to create sockets bypassing any other global
     * proxy settings (like SOCKS):
     * <P>
     * <code>Socket s = new Socket(Proxy.NO_PROXY);</code><br>
     * <P>
     */
    public final static Proxy NO_PROXY = new Proxy();

    // Creates the proxy that represents a <code>DIRECT</code> connection.
    private Proxy() {
        type = type.DIRECT;
        sa = null;
    }

    /**
     * Creates an entry representing a PROXY connection.
     * Certain combinations are illegal. For instance, for types Http, and
     * Socks, a SocketAddress <b>must</b> be provided.
     * <P>
     * Use the <code>Proxy.NO_PROXY</code> constant
     * for representing a direct connection.
     *
     * @param type the <code>Type</code> of the proxy
     * @param sa the <code>SocketAddress</code> for that proxy
     * @throws IllegalArgumentException when the type and the address are
     * incompatible
     */
    public Proxy(Type type, SocketAddress sa) {
        if ((type == Type.DIRECT) || !(sa instanceof InetSocketAddress))
            throw new IllegalArgumentException("type " + type + " is not compatible with address " + sa);
        this.type = type;
        this.sa = sa;
    }

    /**
     * Returns the proxy type.
     *
     * @return a Type representing the proxy type
     */
    public Type type() {
        return type;
    }

    /**
     * Returns the socket address of the proxy, or
     * <code>null</code> if its a direct connection.
     *
     * @return a <code>SocketAddress</code> representing the socket end
     *         point of the proxy
     */
    public SocketAddress address() {
        return sa;
    }

    /**
     * Constructs a string representation of this Proxy.
     * This String is constructed by calling toString() on its type
     * and concatenating " @ " and the toString() result from its address
     * if its type is not <code>DIRECT</code>.
     *
     * @return  a string representation of this object.
     */
    public String toString() {
        if (type() == Type.DIRECT)
            return "DIRECT";
        return type() + " @ " + address();
    }

        /**
     * Compares this object against the specified object.
     * The result is <code>true</code> if and only if the argument is
     * not <code>null</code> and it represents the same proxy as
     * this object.
     * <p>
     * Two instances of <code>Proxy</code> represent the same
     * address if both the SocketAddresses and type are equal.
     *
     * @param   obj   the object to compare against.
     * @return  <code>true</code> if the objects are the same;
     *          <code>false</code> otherwise.
     * @see java.net.InetSocketAddress#equals(java.lang.Object)
     */
    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Proxy))
            return false;
        Proxy p = (Proxy) obj;
        if (p.type() == type()) {
            if (address() == null) {
                return (p.address() == null);
            } else
                return address().equals(p.address());
        }
        return false;
    }

    /**
     * Returns a hashcode for this Proxy.
     *
     * @return  a hash code value for this Proxy.
     */
    public final int hashCode() {
        if (address() == null)
            return type().hashCode();
        return type().hashCode() + address().hashCode();
    }
}

