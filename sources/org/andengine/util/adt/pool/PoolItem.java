package org.andengine.util.adt.pool;

public abstract class PoolItem {
    Pool<? extends PoolItem> mParent;
    boolean mRecycled = true;

    public Pool<? extends PoolItem> getParent() {
        return this.mParent;
    }

    public boolean isRecycled() {
        return this.mRecycled;
    }

    public boolean isFromPool(Pool<? extends PoolItem> pPool) {
        return pPool == this.mParent;
    }

    /* access modifiers changed from: protected */
    public void onRecycle() {
    }

    /* access modifiers changed from: protected */
    public void onObtain() {
    }

    public void recycle() {
        if (this.mParent == null) {
            throw new IllegalStateException("Item already recycled!");
        }
        this.mParent.recycle(this);
    }
}
