package net.nend.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.text.TextUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.nend.android.NendAdNativeClient.NendError;

public class NendAdFullBoardLoader {

    /* renamed from: a */
    private NendAdNativeClient f231a;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public Handler f232b;

    public interface Callback {
        void onFailure(FullBoardAdError fullBoardAdError);

        void onSuccess(NendAdFullBoard nendAdFullBoard);
    }

    public enum FullBoardAdError {
        FAILED_AD_REQUEST,
        FAILED_DOWNLOAD_IMAGE,
        INVALID_AD_SPACES
    }

    /* renamed from: net.nend.android.NendAdFullBoardLoader$a */
    private static class C0447a {

        /* renamed from: a */
        Bitmap f244a;

        /* renamed from: b */
        Bitmap f245b;

        private C0447a() {
            this.f244a = null;
            this.f245b = null;
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public boolean m161a() {
            return (this.f244a == null || this.f245b == null) ? false : true;
        }
    }

    /* renamed from: net.nend.android.NendAdFullBoardLoader$b */
    private static abstract class C0448b implements net.nend.android.NendAdNative.Callback {
        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public abstract void mo7434a(Bitmap bitmap, Exception exc);

        private C0448b() {
        }

        public void onSuccess(Bitmap bitmap) {
            mo7434a(bitmap, null);
        }

        public void onFailure(Exception exc) {
            mo7434a(null, exc);
        }
    }

    public NendAdFullBoardLoader(Context context, int i, String str) {
        this.f231a = new NendAdNativeClient(context, i, str);
        this.f232b = new Handler(context.getMainLooper());
    }

    public void loadAd(final Callback callback) {
        if (callback != null) {
            this.f231a.loadAd(new net.nend.android.NendAdNativeClient.Callback() {
                public void onSuccess(final NendAdNative nendAdNative) {
                    if (TextUtils.isEmpty(nendAdNative.getLogoImageUrl())) {
                        callback.onFailure(FullBoardAdError.INVALID_AD_SPACES);
                        return;
                    }
                    final C0447a aVar = new C0447a();
                    final CountDownLatch countDownLatch = new CountDownLatch(2);
                    ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                    newSingleThreadExecutor.execute(new Runnable() {
                        public void run() {
                            nendAdNative.downloadAdImage(new C0448b() {
                                /* access modifiers changed from: 0000 */
                                /* renamed from: a */
                                public void mo7434a(Bitmap bitmap, Exception exc) {
                                    aVar.f244a = bitmap;
                                    countDownLatch.countDown();
                                }
                            });
                            nendAdNative.downloadLogoImage(new C0448b() {
                                /* access modifiers changed from: 0000 */
                                /* renamed from: a */
                                public void mo7434a(Bitmap bitmap, Exception exc) {
                                    aVar.f245b = bitmap;
                                    countDownLatch.countDown();
                                }
                            });
                            try {
                                countDownLatch.await();
                            } catch (InterruptedException e) {
                            }
                            if (aVar.m161a()) {
                                NendAdFullBoardLoader.this.f232b.post(new Runnable() {
                                    public void run() {
                                        callback.onSuccess(new NendAdFullBoard(nendAdNative, aVar.f244a, aVar.f245b));
                                    }
                                });
                            } else {
                                NendAdFullBoardLoader.this.f232b.post(new Runnable() {
                                    public void run() {
                                        callback.onFailure(FullBoardAdError.FAILED_DOWNLOAD_IMAGE);
                                    }
                                });
                            }
                        }
                    });
                    newSingleThreadExecutor.shutdown();
                }

                public void onFailure(NendError nendError) {
                    callback.onFailure(FullBoardAdError.FAILED_AD_REQUEST);
                }
            });
        }
    }
}
