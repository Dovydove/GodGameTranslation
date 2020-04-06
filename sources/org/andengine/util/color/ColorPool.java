package org.andengine.util.color;

import org.andengine.util.adt.pool.GenericPool;

public class ColorPool extends GenericPool<Color> {
    /* access modifiers changed from: protected */
    public Color onAllocatePoolItem() {
        return new Color(Color.WHITE);
    }

    /* access modifiers changed from: protected */
    public void onHandleRecycleItem(Color pColor) {
        pColor.setChecking(Color.WHITE);
        super.onHandleRecycleItem(pColor);
    }
}
