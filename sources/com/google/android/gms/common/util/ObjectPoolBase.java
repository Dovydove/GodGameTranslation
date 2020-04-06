package com.google.android.gms.common.util;

import java.util.ArrayList;

public abstract class ObjectPoolBase<T> {
    private final ArrayList<T> zzaag;
    private final int zzaah;

    public ObjectPoolBase(int i) {
        this.zzaag = new ArrayList<>(i);
        this.zzaah = i;
    }

    public final T aquire() {
        T newObject;
        synchronized (this.zzaag) {
            int size = this.zzaag.size();
            newObject = size > 0 ? this.zzaag.remove(size - 1) : newObject();
        }
        return newObject;
    }

    /* access modifiers changed from: protected */
    public boolean cleanUpObject(T t) {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract T newObject();

    public final boolean release(T t) {
        boolean z = false;
        synchronized (this.zzaag) {
            int size = this.zzaag.size();
            for (int i = 0; i < size; i++) {
                if (this.zzaag.get(i) == t) {
                    String valueOf = String.valueOf(t);
                    throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 25).append("Object released already: ").append(valueOf).toString());
                }
            }
            if (size < this.zzaah && cleanUpObject(t)) {
                this.zzaag.add(t);
                z = true;
            }
        }
        return z;
    }
}
