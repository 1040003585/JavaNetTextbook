/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QouteServerThread;

import java.io.*;
import java.net.*;
import java.util.*;

class SonServerThreadTest
    extends ServerThread {
    
  private long FIVE_SECONDS = 5000;
  public SonServerThreadTest() throws IOException {
    super("SonServerThreadTest");

  }

  @Override
  public void run() {
    while (moreQuotes) {
      try {
        byte[] buf = new byte[256];
        // construct quote
        String dString = null;
        if (in == null) {
          dString = new Date().toString();
        }
        else {
          dString = getNextQuote();
        }
        buf = dString.getBytes();
        // send it
        InetAddress group = InetAddress.getByName("239.213.213.13");
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group,
            6666);

        socket.send(packet);
        // sleep for a while
        try {
          sleep( (long) (Math.random() * FIVE_SECONDS));
        }
        catch (InterruptedException e) {}
      }
      catch (IOException e) {
        e.printStackTrace();
        moreQuotes = false;
      }
    }
    socket.close();
  }
}
