package org.andengine.util.adt.pool;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.util.adt.list.ShiftList;
import org.andengine.util.adt.pool.PoolItem;
import org.andengine.util.adt.queue.IQueue;
import org.andengine.util.adt.queue.concurrent.SynchronizedQueue;

public abstract class PoolUpdateHandler<T extends PoolItem> implements IUpdateHandler {
    private final Pool<T> mPool;
    private final IQueue<T> mScheduledPoolItemQueue;

    /* access modifiers changed from: protected */
    public abstract T onAllocatePoolItem();

    /* access modifiers changed from: protected */
    public abstract void onHandlePoolItem(T t);

    public PoolUpdateHandler() {
        this.mScheduledPoolItemQueue = new SynchronizedQueue(new ShiftList());
        this.mPool = new Pool<T>() {
            /* access modifiers changed from: protected */
            public T onAllocatePoolItem() {
                return PoolUpdateHandler.this.onAllocatePoolItem();
            }
        };
    }

    public PoolUpdateHandler(int pInitialPoolSize) {
        this.mScheduledPoolItemQueue = new SynchronizedQueue(new ShiftList());
        this.mPool = new Pool<T>(pInitialPoolSize) {
            /* access modifiers changed from: protected */
            public T onAllocatePoolItem() {
                return PoolUpdateHandler.this.onAllocatePoolItem();
            }
        };
    }

    public PoolUpdateHandler(int pInitialPoolSize, int pGrowth) {
        this.mScheduledPoolItemQueue = new SynchronizedQueue(new ShiftList());
        this.mPool = new Pool<T>(pInitialPoolSize, pGrowth) {
            /* access modifiers changed from: protected */
            public T onAllocatePoolItem() {
                return PoolUpdateHandler.this.onAllocatePoolItem();
            }
        };
    }

    public PoolUpdateHandler(int pInitialPoolSize, int pGrowth, int pAvailableItemCountMaximum) {
        this.mScheduledPoolItemQueue = new SynchronizedQueue(new ShiftList());
        this.mPool = new Pool<T>(pInitialPoolSize, pGrowth, pAvailableItemCountMaximum) {
            /* access modifiers changed from: protected */
            public T onAllocatePoolItem() {
                return PoolUpdateHandler.this.onAllocatePoolItem();
            }
        };
    }

    public void onUpdate(float pSecondsElapsed) {
        IQueue<T> scheduledPoolItemQueue = this.mScheduledPoolItemQueue;
        Pool<T> pool = this.mPool;
        while (true) {
            T item = (PoolItem) scheduledPoolItemQueue.poll();
            if (item != null) {
                onHandlePoolItem(item);
                pool.recyclePoolItem(item);
            } else {
                return;
            }
        }
    }

    public void reset() {
        IQueue<T> scheduledPoolItemQueue = this.mScheduledPoolItemQueue;
        Pool<T> pool = this.mPool;
        while (true) {
            T item = (PoolItem) scheduledPoolItemQueue.poll();
            if (item != null) {
                pool.recyclePoolItem(item);
            } else {
                return;
            }
        }
    }

    public T obtainPoolItem() {
        return (PoolItem) this.mPool.obtainPoolItem();
    }

    public void postPoolItem(T pPoolItem) {
        if (pPoolItem == null) {
            throw new IllegalArgumentException("PoolItem already recycled!");
        } else if (!this.mPool.ownsPoolItem(pPoolItem)) {
            throw new IllegalArgumentException("PoolItem from another pool!");
        } else {
            this.mScheduledPoolItemQueue.enter(pPoolItem);
        }
    }
}
