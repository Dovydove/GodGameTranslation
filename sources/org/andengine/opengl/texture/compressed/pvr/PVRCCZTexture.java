package org.andengine.opengl.texture.compressed.pvr;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.andengine.opengl.texture.ITextureStateListener;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.compressed.pvr.PVRTexture.PVRTextureFormat;
import org.andengine.opengl.texture.compressed.pvr.pixelbufferstrategy.IPVRTexturePixelBufferStrategy;
import org.andengine.util.StreamUtils;
import org.andengine.util.adt.array.ArrayUtils;

public abstract class PVRCCZTexture extends PVRTexture {
    private CCZHeader mCCZHeader;

    public enum CCZCompressionFormat {
        ZLIB(0),
        BZIP2(1),
        GZIP(2),
        NONE(3);
        
        private final short mID;

        private CCZCompressionFormat(short pID) {
            this.mID = pID;
        }

        public InflaterInputStream wrap(InputStream pInputStream) throws IOException {
            switch (this) {
                case GZIP:
                    return new GZIPInputStream(pInputStream);
                case ZLIB:
                    return new InflaterInputStream(pInputStream, new Inflater());
                default:
                    throw new IllegalArgumentException("Unexpected " + CCZCompressionFormat.class.getSimpleName() + ": '" + this + "'.");
            }
        }

        public static CCZCompressionFormat fromID(short pID) {
            CCZCompressionFormat[] cczCompressionFormats;
            for (CCZCompressionFormat cczCompressionFormat : values()) {
                if (cczCompressionFormat.mID == pID) {
                    return cczCompressionFormat;
                }
            }
            throw new IllegalArgumentException("Unexpected " + CCZCompressionFormat.class.getSimpleName() + "-ID: '" + pID + "'.");
        }
    }

    public static class CCZHeader {
        static final byte[] MAGIC_IDENTIFIER = {67, 67, 90, 33};
        public static final int SIZE = 16;
        private final CCZCompressionFormat mCCZCompressionFormat;
        private final ByteBuffer mDataByteBuffer;

        public CCZHeader(byte[] pData) {
            this.mDataByteBuffer = ByteBuffer.wrap(pData);
            this.mDataByteBuffer.rewind();
            this.mDataByteBuffer.order(ByteOrder.BIG_ENDIAN);
            if (!ArrayUtils.equals(pData, 0, MAGIC_IDENTIFIER, 0, MAGIC_IDENTIFIER.length)) {
                throw new IllegalArgumentException("Invalid " + getClass().getSimpleName() + "!");
            }
            this.mCCZCompressionFormat = CCZCompressionFormat.fromID(getCCZCompressionFormatID());
        }

        private short getCCZCompressionFormatID() {
            return this.mDataByteBuffer.getShort(4);
        }

        public CCZCompressionFormat getCCZCompressionFormat() {
            return this.mCCZCompressionFormat;
        }

        public short getVersion() {
            return this.mDataByteBuffer.getShort(6);
        }

        public int getUserdata() {
            return this.mDataByteBuffer.getInt(8);
        }

        public int getUncompressedSize() {
            return this.mDataByteBuffer.getInt(12);
        }
    }

    public PVRCCZTexture(TextureManager pTextureManager, PVRTextureFormat pPVRTextureFormat) throws IllegalArgumentException, IOException {
        super(pTextureManager, pPVRTextureFormat);
    }

    public PVRCCZTexture(TextureManager pTextureManager, PVRTextureFormat pPVRTextureFormat, IPVRTexturePixelBufferStrategy pPVRTexturePixelBufferStrategy) throws IllegalArgumentException, IOException {
        super(pTextureManager, pPVRTextureFormat, pPVRTexturePixelBufferStrategy);
    }

    public PVRCCZTexture(TextureManager pTextureManager, PVRTextureFormat pPVRTextureFormat, ITextureStateListener pTextureStateListener) throws IllegalArgumentException, IOException {
        super(pTextureManager, pPVRTextureFormat, pTextureStateListener);
    }

    public PVRCCZTexture(TextureManager pTextureManager, PVRTextureFormat pPVRTextureFormat, IPVRTexturePixelBufferStrategy pPVRTexturePixelBufferStrategy, ITextureStateListener pTextureStateListener) throws IllegalArgumentException, IOException {
        super(pTextureManager, pPVRTextureFormat, pPVRTexturePixelBufferStrategy, pTextureStateListener);
    }

    public PVRCCZTexture(TextureManager pTextureManager, PVRTextureFormat pPVRTextureFormat, TextureOptions pTextureOptions) throws IllegalArgumentException, IOException {
        super(pTextureManager, pPVRTextureFormat, pTextureOptions);
    }

    public PVRCCZTexture(TextureManager pTextureManager, PVRTextureFormat pPVRTextureFormat, IPVRTexturePixelBufferStrategy pPVRTexturePixelBufferStrategy, TextureOptions pTextureOptions) throws IllegalArgumentException, IOException {
        super(pTextureManager, pPVRTextureFormat, pPVRTexturePixelBufferStrategy, pTextureOptions);
    }

    public PVRCCZTexture(TextureManager pTextureManager, PVRTextureFormat pPVRTextureFormat, TextureOptions pTextureOptions, ITextureStateListener pTextureStateListener) throws IllegalArgumentException, IOException {
        super(pTextureManager, pPVRTextureFormat, pTextureOptions, pTextureStateListener);
    }

    public PVRCCZTexture(TextureManager pTextureManager, PVRTextureFormat pPVRTextureFormat, IPVRTexturePixelBufferStrategy pPVRTexturePixelBufferStrategy, TextureOptions pTextureOptions, ITextureStateListener pTextureStateListener) throws IllegalArgumentException, IOException {
        super(pTextureManager, pPVRTextureFormat, pPVRTexturePixelBufferStrategy, pTextureOptions, pTextureStateListener);
    }

    public final InflaterInputStream getInputStream() throws IOException {
        InputStream inputStream = onGetInputStream();
        this.mCCZHeader = new CCZHeader(StreamUtils.streamToBytes(inputStream, 16));
        return this.mCCZHeader.getCCZCompressionFormat().wrap(inputStream);
    }

    public ByteBuffer getPVRTextureBuffer() throws IOException {
        InputStream inputStream = getInputStream();
        try {
            byte[] data = new byte[this.mCCZHeader.getUncompressedSize()];
            StreamUtils.copy(inputStream, data);
            return ByteBuffer.wrap(data);
        } finally {
            StreamUtils.close(inputStream);
        }
    }
}
