package _97DatagramSocket_Wu;

import java.net.NetworkInterface;

/**
 * Choose a network inteface to be the default for
 * outgoing IPv6 traffic that does not specify a scope_id (and which needs one).
 *
 * Platforms that do not require a default interface may return null
 * which is what this implementation does.
 */

class DefaultInterface {

    static NetworkInterface getDefault() {
        return null;
    }
}
