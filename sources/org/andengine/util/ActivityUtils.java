package org.andengine.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.AsyncTask;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import org.andengine.util.call.AsyncCallable;
import org.andengine.util.call.Callable;
import org.andengine.util.call.Callback;
import org.andengine.util.debug.Debug;
import org.andengine.util.exception.CancelledException;
import org.andengine.util.progress.IProgressListener;
import org.andengine.util.progress.ProgressCallable;

public class ActivityUtils {
    public static void requestFullscreen(Activity pActivity) {
        Window window = pActivity.getWindow();
        window.addFlags(1024);
        window.clearFlags(2048);
        window.requestFeature(1);
    }

    public static void setScreenBrightness(Activity pActivity, float pScreenBrightness) {
        Window window = pActivity.getWindow();
        LayoutParams windowLayoutParams = window.getAttributes();
        windowLayoutParams.screenBrightness = pScreenBrightness;
        window.setAttributes(windowLayoutParams);
    }

    public static void keepScreenOn(Activity pActivity) {
        pActivity.getWindow().addFlags(128);
    }

    public static <T> void doAsync(Context pContext, int pTitleResourceID, int pMessageResourceID, Callable<T> pCallable, Callback<T> pCallback) {
        doAsync(pContext, pTitleResourceID, pMessageResourceID, pCallable, pCallback, null, false);
    }

    public static <T> void doAsync(Context pContext, CharSequence pTitle, CharSequence pMessage, Callable<T> pCallable, Callback<T> pCallback) {
        doAsync(pContext, pTitle, pMessage, pCallable, pCallback, null, false);
    }

    public static <T> void doAsync(Context pContext, int pTitleResourceID, int pMessageResourceID, Callable<T> pCallable, Callback<T> pCallback, boolean pCancelable) {
        doAsync(pContext, pTitleResourceID, pMessageResourceID, pCallable, pCallback, null, pCancelable);
    }

    public static <T> void doAsync(Context pContext, CharSequence pTitle, CharSequence pMessage, Callable<T> pCallable, Callback<T> pCallback, boolean pCancelable) {
        doAsync(pContext, pTitle, pMessage, pCallable, pCallback, null, pCancelable);
    }

    public static <T> void doAsync(Context pContext, int pTitleResourceID, int pMessageResourceID, Callable<T> pCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        doAsync(pContext, pTitleResourceID, pMessageResourceID, pCallable, pCallback, pExceptionCallback, false);
    }

    public static <T> void doAsync(Context pContext, CharSequence pTitle, CharSequence pMessage, Callable<T> pCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        doAsync(pContext, pTitle, pMessage, pCallable, pCallback, pExceptionCallback, false);
    }

    public static <T> void doAsync(Context pContext, int pTitleResourceID, int pMessageResourceID, Callable<T> pCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback, boolean pCancelable) {
        doAsync(pContext, (CharSequence) pContext.getString(pTitleResourceID), (CharSequence) pContext.getString(pMessageResourceID), pCallable, pCallback, pExceptionCallback, pCancelable);
    }

    public static <T> void doAsync(Context pContext, CharSequence pTitle, CharSequence pMessage, Callable<T> pCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback, boolean pCancelable) {
        final Context context = pContext;
        final CharSequence charSequence = pTitle;
        final CharSequence charSequence2 = pMessage;
        final boolean z = pCancelable;
        final Callback<Exception> callback = pExceptionCallback;
        final Callable<T> callable = pCallable;
        final Callback<T> callback2 = pCallback;
        new AsyncTask<Void, Void, T>() {
            private Exception mException = null;
            private ProgressDialog mPD;

            public void onPreExecute() {
                this.mPD = ProgressDialog.show(context, charSequence, charSequence2, true, z);
                if (z) {
                    this.mPD.setOnCancelListener(new OnCancelListener() {
                        public void onCancel(DialogInterface pDialogInterface) {
                            callback.onCallback(new CancelledException());
                            pDialogInterface.dismiss();
                        }
                    });
                }
                super.onPreExecute();
            }

            public T doInBackground(Void... params) {
                try {
                    return callable.call();
                } catch (Exception e) {
                    this.mException = e;
                    return null;
                }
            }

            public void onPostExecute(T result) {
                try {
                    this.mPD.dismiss();
                } catch (Exception e) {
                    Debug.m1274e("Error", (Throwable) e);
                }
                if (isCancelled()) {
                    this.mException = new CancelledException();
                }
                if (this.mException == null) {
                    callback2.onCallback(result);
                } else if (callback == null) {
                    Debug.m1274e("Error", (Throwable) this.mException);
                } else {
                    callback.onCallback(this.mException);
                }
                super.onPostExecute(result);
            }
        }.execute(null);
    }

    public static <T> void doProgressAsync(Context pContext, int pTitleResourceID, int pIconResourceID, ProgressCallable<T> pCallable, Callback<T> pCallback) {
        doProgressAsync(pContext, pTitleResourceID, pIconResourceID, pCallable, pCallback, null);
    }

    public static <T> void doProgressAsync(Context pContext, CharSequence pTitle, int pIconResourceID, ProgressCallable<T> pCallable, Callback<T> pCallback) {
        doProgressAsync(pContext, pTitle, pIconResourceID, pCallable, pCallback, null);
    }

    public static <T> void doProgressAsync(Context pContext, int pTitleResourceID, int pIconResourceID, ProgressCallable<T> pCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        doProgressAsync(pContext, (CharSequence) pContext.getString(pTitleResourceID), pIconResourceID, pCallable, pCallback, pExceptionCallback);
    }

    public static <T> void doProgressAsync(Context pContext, CharSequence pTitle, int pIconResourceID, ProgressCallable<T> pCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        final Context context = pContext;
        final CharSequence charSequence = pTitle;
        final int i = pIconResourceID;
        final ProgressCallable<T> progressCallable = pCallable;
        final Callback<T> callback = pCallback;
        final Callback<Exception> callback2 = pExceptionCallback;
        new AsyncTask<Void, Integer, T>() {
            private Exception mException = null;
            private ProgressDialog mPD;

            public void onPreExecute() {
                this.mPD = new ProgressDialog(context);
                this.mPD.setTitle(charSequence);
                this.mPD.setIcon(i);
                this.mPD.setIndeterminate(false);
                this.mPD.setProgressStyle(1);
                this.mPD.show();
                super.onPreExecute();
            }

            public T doInBackground(Void... params) {
                try {
                    return progressCallable.call(new IProgressListener() {
                        public void onProgressChanged(int pProgress) {
                            C08312.this.onProgressUpdate(Integer.valueOf(pProgress));
                        }
                    });
                } catch (Exception e) {
                    this.mException = e;
                    return null;
                }
            }

            public void onProgressUpdate(Integer... values) {
                this.mPD.setProgress(values[0].intValue());
            }

            public void onPostExecute(T result) {
                try {
                    this.mPD.dismiss();
                } catch (Exception e) {
                    Debug.m1274e("Error", (Throwable) e);
                }
                if (isCancelled()) {
                    this.mException = new CancelledException();
                }
                if (this.mException == null) {
                    callback.onCallback(result);
                } else if (callback2 == null) {
                    Debug.m1274e("Error", (Throwable) this.mException);
                } else {
                    callback2.onCallback(this.mException);
                }
                super.onPostExecute(result);
            }
        }.execute(null);
    }

    public static <T> void doAsync(Context pContext, int pTitleResourceID, int pMessageResourceID, AsyncCallable<T> pAsyncCallable, Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        doAsync(pContext, (CharSequence) pContext.getString(pTitleResourceID), (CharSequence) pContext.getString(pMessageResourceID), pAsyncCallable, pCallback, pExceptionCallback);
    }

    public static <T> void doAsync(Context pContext, CharSequence pTitle, CharSequence pMessage, AsyncCallable<T> pAsyncCallable, final Callback<T> pCallback, Callback<Exception> pExceptionCallback) {
        final ProgressDialog pd = ProgressDialog.show(pContext, pTitle, pMessage);
        pAsyncCallable.call(new Callback<T>() {
            public void onCallback(T result) {
                try {
                    pd.dismiss();
                } catch (Exception e) {
                    Debug.m1274e("Error", (Throwable) e);
                }
                pCallback.onCallback(result);
            }
        }, pExceptionCallback);
    }
}
