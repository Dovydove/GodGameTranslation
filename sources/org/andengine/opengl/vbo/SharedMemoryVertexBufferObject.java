package org.andengine.opengl.vbo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.concurrent.locks.ReentrantLock;
import org.andengine.opengl.util.BufferUtils;
import org.andengine.opengl.vbo.attribute.VertexBufferObjectAttributes;

public abstract class SharedMemoryVertexBufferObject extends ZeroMemoryVertexBufferObject {
    private static ByteBuffer sSharedByteBuffer;
    private static ReentrantLock sSharedByteBufferLock = new ReentrantLock(true);

    public static int getSharedByteBufferByteCapacity() {
        int byteCapacity;
        try {
            sSharedByteBufferLock.lock();
            ByteBuffer sharedByteBuffer = sSharedByteBuffer;
            if (sharedByteBuffer == null) {
                byteCapacity = 0;
            } else {
                byteCapacity = sharedByteBuffer.capacity();
            }
            return byteCapacity;
        } finally {
            sSharedByteBufferLock.unlock();
        }
    }

    public SharedMemoryVertexBufferObject(VertexBufferObjectManager pVertexBufferObjectManager, int pCapacity, DrawType pDrawType, VertexBufferObjectAttributes pVertexBufferObjectAttributes) {
        super(pVertexBufferObjectManager, pCapacity, pDrawType, false, pVertexBufferObjectAttributes);
    }

    public void dispose() {
        super.dispose();
        try {
            sSharedByteBufferLock.lock();
            if (sSharedByteBuffer != null) {
                BufferUtils.freeDirectByteBuffer(sSharedByteBuffer);
                sSharedByteBuffer = null;
            }
        } finally {
            sSharedByteBufferLock.unlock();
        }
    }

    /* access modifiers changed from: protected */
    public ByteBuffer aquireByteBuffer() {
        sSharedByteBufferLock.lock();
        int byteCapacity = getByteCapacity();
        if (sSharedByteBuffer == null || sSharedByteBuffer.capacity() < byteCapacity) {
            if (sSharedByteBuffer != null) {
                BufferUtils.freeDirectByteBuffer(sSharedByteBuffer);
            }
            sSharedByteBuffer = BufferUtils.allocateDirectByteBuffer(byteCapacity);
            sSharedByteBuffer.order(ByteOrder.nativeOrder());
        }
        sSharedByteBuffer.limit(byteCapacity);
        return sSharedByteBuffer;
    }

    /* access modifiers changed from: protected */
    public void releaseByteBuffer(ByteBuffer byteBuffer) {
        sSharedByteBufferLock.unlock();
    }
}
