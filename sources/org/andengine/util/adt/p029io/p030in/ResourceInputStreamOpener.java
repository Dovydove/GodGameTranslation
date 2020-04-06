package org.andengine.util.adt.p029io.p030in;

import android.content.res.Resources;
import java.io.IOException;
import java.io.InputStream;

/* renamed from: org.andengine.util.adt.io.in.ResourceInputStreamOpener */
public class ResourceInputStreamOpener implements IInputStreamOpener {
    private final int mResourceID;
    private final Resources mResources;

    public ResourceInputStreamOpener(Resources pResources, int pResourceID) {
        this.mResources = pResources;
        this.mResourceID = pResourceID;
    }

    public InputStream open() throws IOException {
        return this.mResources.openRawResource(this.mResourceID);
    }
}
