package org.andengine.util.adt.cache;

import android.util.SparseArray;
import org.andengine.util.adt.pool.GenericPool;

public class IntLRUCache<V> {
    private final int mCapacity;
    private final IntLRUCacheQueue mIntLRUCacheQueue;
    private final GenericPool<IntLRUCacheValueHolder<V>> mIntLRUCacheValueHolderPool = new GenericPool<IntLRUCacheValueHolder<V>>() {
        /* access modifiers changed from: protected */
        public IntLRUCacheValueHolder<V> onAllocatePoolItem() {
            return new IntLRUCacheValueHolder<>();
        }

        /* access modifiers changed from: protected */
        public void onHandleRecycleItem(IntLRUCacheValueHolder<V> pIntLRUCacheValueHolder) {
            pIntLRUCacheValueHolder.mIntLRUCacheQueueNode = null;
            pIntLRUCacheValueHolder.mValue = null;
        }
    };
    private int mSize;
    private final SparseArray<IntLRUCacheValueHolder<V>> mSparseArray;

    static class IntLRUCacheQueue {
        private IntLRUCacheQueueNode mHead;
        private final GenericPool<IntLRUCacheQueueNode> mIntLRUCacheQueueNodePool = new GenericPool<IntLRUCacheQueueNode>() {
            /* access modifiers changed from: protected */
            public IntLRUCacheQueueNode onAllocatePoolItem() {
                return new IntLRUCacheQueueNode();
            }

            /* access modifiers changed from: protected */
            public void onHandleRecycleItem(IntLRUCacheQueueNode pIntLRUCacheQueueNode) {
                pIntLRUCacheQueueNode.mKey = 0;
                pIntLRUCacheQueueNode.mPrevious = null;
                pIntLRUCacheQueueNode.mNext = null;
            }
        };
        private IntLRUCacheQueueNode mTail;

        IntLRUCacheQueue() {
        }

        public boolean isEmpty() {
            return this.mHead == null;
        }

        public IntLRUCacheQueueNode add(int pKey) {
            IntLRUCacheQueueNode IntLRUCacheQueueNode = (IntLRUCacheQueueNode) this.mIntLRUCacheQueueNodePool.obtainPoolItem();
            IntLRUCacheQueueNode.mKey = pKey;
            return add(IntLRUCacheQueueNode);
        }

        private IntLRUCacheQueueNode add(IntLRUCacheQueueNode pIntLRUCacheQueueNode) {
            if (isEmpty()) {
                this.mHead = pIntLRUCacheQueueNode;
                this.mTail = this.mHead;
            } else {
                this.mTail.mNext = pIntLRUCacheQueueNode;
                pIntLRUCacheQueueNode.mPrevious = this.mTail;
                this.mTail = pIntLRUCacheQueueNode;
            }
            return this.mTail;
        }

        public int poll() {
            IntLRUCacheQueueNode head = this.mHead;
            int key = this.mHead.mKey;
            if (key == 0) {
                throw new IllegalStateException();
            }
            if (this.mHead.mNext == null) {
                this.mHead = null;
                this.mTail = null;
            } else {
                this.mHead = this.mHead.mNext;
                this.mHead.mPrevious = null;
            }
            this.mIntLRUCacheQueueNodePool.recyclePoolItem(head);
            return key;
        }

        public void moveToTail(IntLRUCacheQueueNode pIntLRUCacheQueueNode) {
            IntLRUCacheQueueNode next = pIntLRUCacheQueueNode.mNext;
            if (next != null) {
                IntLRUCacheQueueNode previous = pIntLRUCacheQueueNode.mPrevious;
                next.mPrevious = previous;
                if (previous == null) {
                    this.mHead = next;
                } else {
                    previous.mNext = next;
                }
                this.mTail.mNext = pIntLRUCacheQueueNode;
                pIntLRUCacheQueueNode.mPrevious = this.mTail;
                pIntLRUCacheQueueNode.mNext = null;
                this.mTail = pIntLRUCacheQueueNode;
            }
        }
    }

    static class IntLRUCacheQueueNode {
        int mKey;
        IntLRUCacheQueueNode mNext;
        IntLRUCacheQueueNode mPrevious;

        IntLRUCacheQueueNode() {
        }
    }

    static class IntLRUCacheValueHolder<V> {
        IntLRUCacheQueueNode mIntLRUCacheQueueNode;
        V mValue;

        IntLRUCacheValueHolder() {
        }
    }

    public IntLRUCache(int pCapacity) {
        this.mCapacity = pCapacity;
        this.mSparseArray = new SparseArray<>(pCapacity);
        this.mIntLRUCacheQueue = new IntLRUCacheQueue();
    }

    public int getCapacity() {
        return this.mCapacity;
    }

    public int getSize() {
        return this.mSize;
    }

    public boolean isEmpty() {
        return this.mSize == 0;
    }

    public V put(int pKey, V pValue) {
        IntLRUCacheValueHolder<V> existingIntLRUCacheValueHolder = (IntLRUCacheValueHolder) this.mSparseArray.get(pKey);
        if (existingIntLRUCacheValueHolder != null) {
            this.mIntLRUCacheQueue.moveToTail(existingIntLRUCacheValueHolder.mIntLRUCacheQueueNode);
            return existingIntLRUCacheValueHolder.mValue;
        }
        if (this.mSize >= this.mCapacity) {
            this.mSparseArray.remove(this.mIntLRUCacheQueue.poll());
            this.mSize--;
        }
        IntLRUCacheQueueNode IntLRUCacheQueueNode2 = this.mIntLRUCacheQueue.add(pKey);
        IntLRUCacheValueHolder<V> IntLRUCacheValueHolder2 = (IntLRUCacheValueHolder) this.mIntLRUCacheValueHolderPool.obtainPoolItem();
        IntLRUCacheValueHolder2.mValue = pValue;
        IntLRUCacheValueHolder2.mIntLRUCacheQueueNode = IntLRUCacheQueueNode2;
        this.mSparseArray.put(pKey, IntLRUCacheValueHolder2);
        this.mSize++;
        return null;
    }

    public V get(int pKey) {
        IntLRUCacheValueHolder<V> IntLRUCacheValueHolder2 = (IntLRUCacheValueHolder) this.mSparseArray.get(pKey);
        if (IntLRUCacheValueHolder2 == null) {
            return null;
        }
        this.mIntLRUCacheQueue.moveToTail(IntLRUCacheValueHolder2.mIntLRUCacheQueueNode);
        return IntLRUCacheValueHolder2.mValue;
    }

    public void clear() {
        while (!this.mIntLRUCacheQueue.isEmpty()) {
            int key = this.mIntLRUCacheQueue.poll();
            IntLRUCacheValueHolder<V> lruCacheValueHolder = (IntLRUCacheValueHolder) this.mSparseArray.get(key);
            if (lruCacheValueHolder == null) {
                throw new IllegalArgumentException();
            }
            this.mSparseArray.remove(key);
            this.mIntLRUCacheValueHolderPool.recyclePoolItem(lruCacheValueHolder);
        }
        this.mSize = 0;
    }
}
