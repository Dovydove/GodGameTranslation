package org.andengine.util.adt.pool;

import org.andengine.util.adt.pool.PoolItem;

public abstract class Pool<T extends PoolItem> extends GenericPool<T> {
    public Pool() {
    }

    public Pool(int pInitialSize) {
        super(pInitialSize);
    }

    public Pool(int pInitialSize, int pGrowth) {
        super(pInitialSize, pGrowth);
    }

    public Pool(int pInitialSize, int pGrowth, int pAvailableItemCountMaximum) {
        super(pInitialSize, pGrowth, pAvailableItemCountMaximum);
    }

    /* access modifiers changed from: protected */
    public T onHandleAllocatePoolItem() {
        T poolItem = (PoolItem) super.onHandleAllocatePoolItem();
        poolItem.mParent = this;
        return poolItem;
    }

    /* access modifiers changed from: protected */
    public void onHandleObtainItem(T pPoolItem) {
        pPoolItem.mRecycled = false;
        pPoolItem.onObtain();
    }

    /* access modifiers changed from: protected */
    public void onHandleRecycleItem(T pPoolItem) {
        pPoolItem.onRecycle();
        pPoolItem.mRecycled = true;
    }

    public synchronized void recyclePoolItem(T pPoolItem) {
        if (pPoolItem.mParent == null) {
            throw new IllegalArgumentException("PoolItem not assigned to a pool!");
        } else if (!pPoolItem.isFromPool(this)) {
            throw new IllegalArgumentException("PoolItem from another pool!");
        } else if (pPoolItem.isRecycled()) {
            throw new IllegalArgumentException("PoolItem already recycled!");
        } else {
            super.recyclePoolItem(pPoolItem);
        }
    }

    public synchronized boolean ownsPoolItem(T pPoolItem) {
        return pPoolItem.mParent == this;
    }

    /* access modifiers changed from: 0000 */
    public void recycle(PoolItem pPoolItem) {
        recyclePoolItem((T) pPoolItem);
    }
}
