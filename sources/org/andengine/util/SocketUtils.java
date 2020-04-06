package org.andengine.util;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import org.andengine.util.debug.Debug;

public final class SocketUtils {
    public static final void closeSocket(DatagramSocket pDatagramSocket) {
        if (pDatagramSocket != null && !pDatagramSocket.isClosed()) {
            pDatagramSocket.close();
        }
    }

    public static final void closeSocket(Socket pSocket) {
        if (pSocket != null && !pSocket.isClosed()) {
            try {
                pSocket.close();
            } catch (IOException e) {
                Debug.m1275e((Throwable) e);
            }
        }
    }

    public static final void closeSocket(ServerSocket pServerSocket) {
        if (pServerSocket != null && !pServerSocket.isClosed()) {
            try {
                pServerSocket.close();
            } catch (IOException e) {
                Debug.m1275e((Throwable) e);
            }
        }
    }
}
