package org.andengine.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;

public final class StreamUtils {
    private static final int END_OF_STREAM = -1;
    public static final int IO_BUFFER_SIZE = 8192;

    /* JADX INFO: finally extract failed */
    public static final String readFully(InputStream pInputStream) throws IOException {
        StringWriter writer = new StringWriter();
        char[] buf = new char[8192];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(pInputStream, "UTF-8"));
            while (true) {
                int read = reader.read(buf);
                if (read != -1) {
                    writer.write(buf, 0, read);
                } else {
                    close(pInputStream);
                    return writer.toString();
                }
            }
        } catch (Throwable th) {
            close(pInputStream);
            throw th;
        }
    }

    public static final byte[] streamToBytes(InputStream pInputStream) throws IOException {
        return streamToBytes(pInputStream, -1);
    }

    public static final byte[] streamToBytes(InputStream pInputStream, int pReadLimit) throws IOException {
        int i;
        if (pReadLimit == -1) {
            i = 8192;
        } else {
            i = pReadLimit;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream(i);
        copy(pInputStream, os, pReadLimit);
        return os.toByteArray();
    }

    public static final void streamToBytes(InputStream pInputStream, int pByteLimit, byte[] pData) throws IOException {
        streamToBytes(pInputStream, pByteLimit, pData, 0);
    }

    public static final void streamToBytes(InputStream pInputStream, int pByteLimit, byte[] pData, int pOffset) throws IOException {
        if (pByteLimit > pData.length - pOffset) {
            throw new IOException("pData is not big enough.");
        }
        int pBytesLeftToRead = pByteLimit;
        int readTotal = 0;
        while (true) {
            int read = pInputStream.read(pData, pOffset + readTotal, pBytesLeftToRead);
            if (read == -1) {
                break;
            }
            readTotal += read;
            if (pBytesLeftToRead <= read) {
                break;
            }
            pBytesLeftToRead -= read;
        }
        if (readTotal != pByteLimit) {
            throw new IOException("ReadLimit: '" + pByteLimit + "', Read: '" + readTotal + "'.");
        }
    }

    public static final void copy(InputStream pInputStream, OutputStream pOutputStream) throws IOException {
        copy(pInputStream, pOutputStream, -1);
    }

    public static final void copy(InputStream pInputStream, byte[] pData) throws IOException {
        int dataOffset = 0;
        byte[] buf = new byte[8192];
        while (true) {
            int read = pInputStream.read(buf);
            if (read != -1) {
                System.arraycopy(buf, 0, pData, dataOffset, read);
                dataOffset += read;
            } else {
                return;
            }
        }
    }

    public static final void copy(InputStream pInputStream, ByteBuffer pByteBuffer) throws IOException {
        byte[] buf = new byte[8192];
        while (true) {
            int read = pInputStream.read(buf);
            if (read != -1) {
                pByteBuffer.put(buf, 0, read);
            } else {
                return;
            }
        }
    }

    public static final void copy(InputStream pInputStream, OutputStream pOutputStream, int pByteLimit) throws IOException {
        if (pByteLimit != -1) {
            byte[] buf = new byte[8192];
            int bufferReadLimit = Math.min(pByteLimit, 8192);
            long pBytesLeftToRead = (long) pByteLimit;
            while (true) {
                int read = pInputStream.read(buf, 0, bufferReadLimit);
                if (read != -1) {
                    if (pBytesLeftToRead <= ((long) read)) {
                        pOutputStream.write(buf, 0, (int) pBytesLeftToRead);
                        break;
                    } else {
                        pOutputStream.write(buf, 0, read);
                        pBytesLeftToRead -= (long) read;
                    }
                } else {
                    break;
                }
            }
        } else {
            byte[] buf2 = new byte[8192];
            while (true) {
                int read2 = pInputStream.read(buf2);
                if (read2 == -1) {
                    break;
                }
                pOutputStream.write(buf2, 0, read2);
            }
        }
        pOutputStream.flush();
    }

    public static final boolean copyAndClose(InputStream pInputStream, OutputStream pOutputStream) {
        try {
            copy(pInputStream, pOutputStream, -1);
            return true;
        } catch (IOException e) {
            return false;
        } finally {
            close(pInputStream);
            close(pOutputStream);
        }
    }

    public static final void close(Closeable pCloseable) {
        if (pCloseable != null) {
            try {
                pCloseable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static final void flushCloseStream(OutputStream pOutputStream) {
        if (pOutputStream != null) {
            try {
                pOutputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(pOutputStream);
            }
        }
    }

    public static final void flushCloseWriter(Writer pWriter) {
        if (pWriter != null) {
            try {
                pWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(pWriter);
            }
        }
    }
}
