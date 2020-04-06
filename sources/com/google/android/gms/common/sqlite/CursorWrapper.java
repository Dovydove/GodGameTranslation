package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;

public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzxu;

    public CursorWrapper(Cursor cursor) {
        Cursor cursor2;
        super(cursor);
        int i = 0;
        while (true) {
            int i2 = i;
            cursor2 = cursor;
            if (i2 < 10 && (cursor2 instanceof android.database.CursorWrapper)) {
                cursor = ((android.database.CursorWrapper) cursor2).getWrappedCursor();
                i = i2 + 1;
            }
        }
        if (!(cursor2 instanceof AbstractWindowedCursor)) {
            String str = "Unknown type: ";
            String valueOf = String.valueOf(cursor2.getClass().getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        this.zzxu = (AbstractWindowedCursor) cursor2;
    }

    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.zzxu.fillWindow(i, cursorWindow);
    }

    public CursorWindow getWindow() {
        return this.zzxu.getWindow();
    }

    public AbstractWindowedCursor getWrappedCursor() {
        return this.zzxu;
    }

    public boolean onMove(int i, int i2) {
        return this.zzxu.onMove(i, i2);
    }

    public void setWindow(CursorWindow cursorWindow) {
        this.zzxu.setWindow(cursorWindow);
    }
}
