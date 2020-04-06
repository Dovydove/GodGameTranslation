package org.andengine.p028ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import android.widget.Toast;
import org.andengine.util.ActivityUtils;
import org.andengine.util.call.AsyncCallable;
import org.andengine.util.call.Callable;
import org.andengine.util.call.Callback;
import org.andengine.util.progress.ProgressCallable;

/* renamed from: org.andengine.ui.activity.BaseActivity */
public abstract class BaseActivity extends Activity {
    public void toastOnUIThread(CharSequence pText) {
        toastOnUIThread(pText, 1);
    }

    public void toastOnUIThread(final CharSequence pText, final int pDuration) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Toast.makeText(this, pText, pDuration).show();
        } else {
            runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(BaseActivity.this, pText, pDuration).show();
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public <T> void doAsync(int pTitleResourceID, int pMessageResourceID, Callable<T> pCallable, Callback<T> pCallback) {
        doAsync(pTitleResourceID, pMessageResourceID, pCallable, pCallback, null);
    }

    /* access modifiers changed from: protected */
    public <T> void doAsync(int pTitleResourceID, int pMessageResourceID, Callable<T> pCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        ActivityUtils.doAsync((Context) this, pTitleResourceID, pMessageResourceID, pCallable, pCallback, pExceptionCallback);
    }

    /* access modifiers changed from: protected */
    public <T> void doProgressAsync(int pTitleResourceID, int pIconResourceID, ProgressCallable<T> pCallable, Callback<T> pCallback) {
        doProgressAsync(pTitleResourceID, pIconResourceID, pCallable, pCallback, null);
    }

    /* access modifiers changed from: protected */
    public <T> void doProgressAsync(int pTitleResourceID, int pIconResourceID, ProgressCallable<T> pCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        ActivityUtils.doProgressAsync((Context) this, pTitleResourceID, pIconResourceID, pCallable, pCallback, pExceptionCallback);
    }

    /* access modifiers changed from: protected */
    public <T> void doAsync(int pTitleResourceID, int pMessageResourceID, AsyncCallable<T> pAsyncCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        ActivityUtils.doAsync((Context) this, pTitleResourceID, pMessageResourceID, pAsyncCallable, pCallback, pExceptionCallback);
    }
}
