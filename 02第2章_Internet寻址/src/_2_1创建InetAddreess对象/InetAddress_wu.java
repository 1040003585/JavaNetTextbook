package _2_1����InetAddreess����;

import java.io.Serializable;
import java.net.UnknownHostException;

/*
 *import java.net.InetAddress; 
 */
public final class InetAddress_wu extends Object implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static InetAddress_wu getLocalHost_wu() throws UnknownHostException {
		return null;
	}

	public static InetAddress_wu getByName_wu(String host)
			throws UnknownHostException {
		try {
			return InetAddress_wu.getAllByName_wu(host)[0];
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static InetAddress_wu[] getAllByName_wu(String args)
			throws Exception {
		return null;
	}

	// private String hostName;
	//
	// public String toString() {
	// return ((hostName != null) ? hostName : "") + "/" + getHostAddress();
	// }
	//
	// private String getHostAddress() {
	// // TODO Auto-generated method stub
	//
	// }
}