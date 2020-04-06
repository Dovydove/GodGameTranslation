package org.andengine.util.adt.list;

import java.lang.Comparable;

public class SortedList<T extends Comparable<T>> implements ISortedList<T> {
    private static final int INDEX_INVALID = -1;
    private final IList<T> mList;

    public SortedList(IList<T> pList) {
        this.mList = pList;
    }

    public boolean isEmpty() {
        return this.mList.isEmpty();
    }

    public T get(int pIndex) throws IndexOutOfBoundsException {
        return (Comparable) this.mList.get(pIndex);
    }

    @Deprecated
    public void set(int pIndex, T pItem) throws IndexOutOfBoundsException {
        this.mList.set(pIndex, pItem);
    }

    public int indexOf(T pItem) {
        return binarySearch(pItem, false);
    }

    @Deprecated
    public void add(int pIndex, T pItem) {
        this.mList.add(pItem);
    }

    public void add(T pItem) {
        int index = binarySearch(pItem, true);
        if (index < 0) {
            this.mList.add(ListUtils.encodeInsertionIndex(index), pItem);
        } else {
            this.mList.add(index, pItem);
        }
    }

    public T removeFirst() {
        return (Comparable) this.mList.removeFirst();
    }

    public T removeLast() {
        return (Comparable) this.mList.removeLast();
    }

    public boolean remove(T pItem) {
        if (pItem == null) {
            return this.mList.remove(pItem);
        }
        int index = binarySearch(pItem, false);
        if (index < 0) {
            return false;
        }
        this.mList.remove(index);
        return true;
    }

    public T remove(int pIndex) {
        return (Comparable) this.mList.remove(pIndex);
    }

    public int size() {
        return this.mList.size();
    }

    public void clear() {
        this.mList.clear();
    }

    private int binarySearch(T pItem, boolean pReturnSequenceEndIfNoEqualItemFound) {
        int size = this.mList.size();
        int guess = binarySearch(0, size, pItem);
        if (guess >= 0) {
            return scanForEqualItem(0, size, guess, pItem, pReturnSequenceEndIfNoEqualItemFound);
        }
        return guess;
    }

    private int binarySearch(int pStart, int pEnd, T pItem) {
        int low = pStart;
        int high = pEnd - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int diff = pItem.compareTo((Comparable) this.mList.get(mid));
            if (diff > 0) {
                low = mid + 1;
            } else if (diff >= 0) {
                return mid;
            } else {
                high = mid - 1;
            }
        }
        return ListUtils.encodeInsertionIndex(low);
    }

    private int scanForEqualItem(int pStart, int pEnd, int pGuess, T pItem, boolean pReturnSequenceEndIfNoEqualItemFound) {
        int i = pGuess - 1;
        while (i >= pStart && pItem.compareTo(this.mList.get(i)) == 0) {
            i--;
        }
        int i2 = i + 1;
        while (i2 < pEnd) {
            T item = (Comparable) this.mList.get(i2);
            if (i2 <= pGuess) {
                if (pItem.equals(item)) {
                    return i2;
                }
            } else if (pItem.compareTo(item) != 0) {
                return ListUtils.encodeInsertionIndex(i2);
            } else {
                if (pItem.equals(item)) {
                    return i2;
                }
            }
            i2++;
        }
        if (!pReturnSequenceEndIfNoEqualItemFound) {
            return -1;
        }
        return i2;
    }
}
