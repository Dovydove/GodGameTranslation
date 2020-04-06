package org.andengine.opengl.util;

import android.graphics.Bitmap;
import android.support.p000v4.view.ViewCompat;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.andengine.opengl.texture.PixelFormat;

public class GLHelper {
    public static Buffer getPixels(Bitmap pBitmap, PixelFormat pPixelFormat) {
        return getPixels(pBitmap, pPixelFormat, ByteOrder.nativeOrder());
    }

    public static Buffer getPixels(Bitmap pBitmap, PixelFormat pPixelFormat, ByteOrder pByteOrder) {
        int[] pixelsARGB_8888 = getPixelsARGB_8888(pBitmap);
        switch (pPixelFormat) {
            case RGB_565:
                return ShortBuffer.wrap(convertARGB_8888toRGB_565(pixelsARGB_8888, pByteOrder));
            case RGBA_8888:
                return IntBuffer.wrap(convertARGB_8888toRGBA_8888(pixelsARGB_8888, pByteOrder == ByteOrder.LITTLE_ENDIAN ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN));
            case RGBA_4444:
                return ShortBuffer.wrap(convertARGB_8888toRGBA_4444(pixelsARGB_8888, pByteOrder));
            case A_8:
                return ByteBuffer.wrap(convertARGB_8888toA_8(pixelsARGB_8888));
            default:
                throw new IllegalArgumentException("Unexpected " + PixelFormat.class.getSimpleName() + ": '" + pPixelFormat + "'.");
        }
    }

    public static int[] convertARGB_8888toRGBA_8888(int[] pPixelsARGB_8888) {
        return convertARGB_8888toRGBA_8888(pPixelsARGB_8888, ByteOrder.nativeOrder());
    }

    public static int[] convertARGB_8888toRGBA_8888(int[] pPixelsARGB_8888, ByteOrder pByteOrder) {
        if (pByteOrder == ByteOrder.LITTLE_ENDIAN) {
            for (int i = pPixelsARGB_8888.length - 1; i >= 0; i--) {
                int pixel = pPixelsARGB_8888[i];
                pPixelsARGB_8888[i] = (-16711936 & pixel) | ((pixel << 16) & 16711680) | ((pixel >> 16) & 255);
            }
        } else {
            for (int i2 = pPixelsARGB_8888.length - 1; i2 >= 0; i2--) {
                int pixel2 = pPixelsARGB_8888[i2];
                pPixelsARGB_8888[i2] = ((pixel2 << 8) & -256) | ((pixel2 >> 24) & 255);
            }
        }
        return pPixelsARGB_8888;
    }

    public static short[] convertARGB_8888toRGB_565(int[] pPixelsARGB_8888) {
        return convertARGB_8888toRGB_565(pPixelsARGB_8888, ByteOrder.nativeOrder());
    }

    public static short[] convertARGB_8888toRGB_565(int[] pPixelsARGB_8888, ByteOrder pByteOrder) {
        short[] pixelsRGB_565 = new short[pPixelsARGB_8888.length];
        if (pByteOrder == ByteOrder.LITTLE_ENDIAN) {
            for (int i = pPixelsARGB_8888.length - 1; i >= 0; i--) {
                int pixel = pPixelsARGB_8888[i];
                pixelsRGB_565[i] = (short) (((pixel >> 16) & 248) | ((pixel >> 13) & 7) | ((pixel << 3) & 57344) | ((pixel << 5) & 7936));
            }
        } else {
            for (int i2 = pPixelsARGB_8888.length - 1; i2 >= 0; i2--) {
                int pixel2 = pPixelsARGB_8888[i2];
                pixelsRGB_565[i2] = (short) (((pixel2 >> 8) & 63488) | ((pixel2 >> 5) & 2016) | ((pixel2 >> 3) & 31));
            }
        }
        return pixelsRGB_565;
    }

    public static short[] convertARGB_8888toRGBA_4444(int[] pPixelsARGB_8888) {
        return convertARGB_8888toRGBA_4444(pPixelsARGB_8888, ByteOrder.nativeOrder());
    }

    public static short[] convertARGB_8888toRGBA_4444(int[] pPixelsARGB_8888, ByteOrder pByteOrder) {
        short[] pixelsRGBA_4444 = new short[pPixelsARGB_8888.length];
        if (pByteOrder == ByteOrder.LITTLE_ENDIAN) {
            for (int i = pPixelsARGB_8888.length - 1; i >= 0; i--) {
                int pixel = pPixelsARGB_8888[i];
                pixelsRGBA_4444[i] = (short) (((pixel >> 16) & 240) | ((pixel >> 12) & 15) | ((pixel << 8) & 61440) | ((pixel >> 20) & 3840));
            }
        } else {
            for (int i2 = pPixelsARGB_8888.length - 1; i2 >= 0; i2--) {
                int pixel2 = pPixelsARGB_8888[i2];
                pixelsRGBA_4444[i2] = (short) (((pixel2 >> 8) & 61440) | ((pixel2 >> 4) & 3840) | (pixel2 & 240) | ((pixel2 >> 28) & 15));
            }
        }
        return pixelsRGBA_4444;
    }

    public static byte[] convertARGB_8888toA_8(int[] pPixelsARGB_8888) {
        byte[] pixelsA_8 = new byte[pPixelsARGB_8888.length];
        for (int i = pPixelsARGB_8888.length - 1; i >= 0; i--) {
            pixelsA_8[i] = (byte) ((pPixelsARGB_8888[i] >> 24) & 255);
        }
        return pixelsA_8;
    }

    public static int[] getPixelsARGB_8888(Bitmap pBitmap) {
        int w = pBitmap.getWidth();
        int h = pBitmap.getHeight();
        int[] pixelsARGB_8888 = new int[(w * h)];
        pBitmap.getPixels(pixelsARGB_8888, 0, w, 0, 0, w, h);
        return pixelsARGB_8888;
    }

    public static int[] convertRGBA_8888toARGB_8888(int[] pPixelsRGBA_8888) {
        return convertRGBA_8888toARGB_8888(pPixelsRGBA_8888, ByteOrder.nativeOrder());
    }

    public static int[] convertRGBA_8888toARGB_8888(int[] pPixelsRGBA_8888, ByteOrder pByteOrder) {
        if (pByteOrder == ByteOrder.LITTLE_ENDIAN) {
            for (int i = pPixelsRGBA_8888.length - 1; i >= 0; i--) {
                int pixel = pPixelsRGBA_8888[i];
                pPixelsRGBA_8888[i] = (-16711936 & pixel) | ((pixel << 16) & 16711680) | ((pixel >> 16) & 255);
            }
        } else {
            for (int i2 = pPixelsRGBA_8888.length - 1; i2 >= 0; i2--) {
                int pixel2 = pPixelsRGBA_8888[i2];
                pPixelsRGBA_8888[i2] = ((pixel2 >> 8) & 16777215) | ((pixel2 << 24) & ViewCompat.MEASURED_STATE_MASK);
            }
        }
        return pPixelsRGBA_8888;
    }

    public static int[] convertRGBA_8888toARGB_8888_FlippedVertical(int[] pPixelsRGBA_8888, int pWidth, int pHeight) {
        return convertRGBA_8888toARGB_8888_FlippedVertical(pPixelsRGBA_8888, pWidth, pHeight, ByteOrder.nativeOrder());
    }

    public static int[] convertRGBA_8888toARGB_8888_FlippedVertical(int[] pPixelsRGBA_8888, int pWidth, int pHeight, ByteOrder pByteOrder) {
        int[] pixelsARGB_8888 = new int[(pWidth * pHeight)];
        if (pByteOrder == ByteOrder.LITTLE_ENDIAN) {
            for (int y = 0; y < pHeight; y++) {
                for (int x = 0; x < pWidth; x++) {
                    int pixel = pPixelsRGBA_8888[(y * pWidth) + x];
                    pixelsARGB_8888[(((pHeight - y) - 1) * pWidth) + x] = (-16711936 & pixel) | ((pixel << 16) & 16711680) | ((pixel >> 16) & 255);
                }
            }
        } else {
            for (int y2 = 0; y2 < pHeight; y2++) {
                for (int x2 = 0; x2 < pWidth; x2++) {
                    int pixel2 = pPixelsRGBA_8888[(y2 * pWidth) + x2];
                    pixelsARGB_8888[(((pHeight - y2) - 1) * pWidth) + x2] = ((pixel2 >> 8) & 16777215) | ((pixel2 << 24) & ViewCompat.MEASURED_STATE_MASK);
                }
            }
        }
        return pixelsARGB_8888;
    }
}
