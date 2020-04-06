package net.nend.android.internal.p023ui.p024a;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.WeakHashMap;
import net.nend.android.NendAdNative;
import net.nend.android.NendAdNative.Callback;
import net.nend.android.NendAdNativeViewHolder;
import net.nend.android.internal.p007a.C0524b;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0743b;
import net.nend.android.internal.utilities.C0740a.C0748f;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

/* renamed from: net.nend.android.internal.ui.a.f */
/* compiled from: NendAdNativeRenderer */
public class C0657f {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final Object[] f924a = new Object[0];
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static WeakHashMap<View, OnPreDrawListener> f925b = new WeakHashMap<>();
    /* access modifiers changed from: private */

    /* renamed from: c */
    public NendAdNative f926c;

    /* renamed from: d */
    private OnClickListener f927d = new OnClickListener() {
        public void onClick(View view) {
            C0755e.m1198a(view.getContext(), C0657f.this.f926c.getClickUrl());
            C0657f.this.f926c.onClick();
        }
    };

    /* renamed from: e */
    private OnClickListener f928e = new OnClickListener() {
        public void onClick(View view) {
            C0657f.this.f926c.onClickInformation(view.getContext());
        }
    };
    /* access modifiers changed from: private */

    /* renamed from: f */
    public View f929f;

    /* renamed from: g */
    private OnPreDrawListener f930g = new OnPreDrawListener() {
        public boolean onPreDraw() {
            if (C0657f.m950a(C0657f.this.f929f.getRootView(), C0657f.this.f929f, 50)) {
                C0657f.this.f926c.onImpression();
                if (C0657f.this.f929f.getViewTreeObserver().isAlive()) {
                    C0657f.this.f929f.getViewTreeObserver().removeOnPreDrawListener(this);
                    C0657f.f925b.remove(C0657f.this.f929f);
                }
            }
            return true;
        }
    };

    /* renamed from: a */
    private C0748f<Bitmap> m947a(final String str) {
        return new C0748f<>((C0743b<V>) new C0743b<Bitmap>() {
            public String getRequestUrl() {
                return str;
            }

            /* renamed from: a */
            public Bitmap makeResponse(byte[] bArr) {
                Bitmap decodeByteArray;
                if (bArr != null) {
                    try {
                        synchronized (C0657f.f924a) {
                            decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
                        }
                        return decodeByteArray;
                    } catch (OutOfMemoryError e) {
                        System.gc();
                        C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e);
                    } catch (IllegalStateException e2) {
                        C0757f.m1215a(C0758g.ERR_HTTP_REQUEST, (Throwable) e2);
                    }
                }
                return null;
            }
        });
    }

    /* renamed from: a */
    public void mo8023a(NendAdNativeViewHolder nendAdNativeViewHolder, NendAdNative nendAdNative) {
        if (nendAdNative != null) {
            m949a(nendAdNativeViewHolder.titleTextView, nendAdNative.getTitleText());
            m949a(nendAdNativeViewHolder.contentTextView, nendAdNative.getContentText());
            m949a(nendAdNativeViewHolder.promotionUrlTextView, nendAdNative.getPromotionUrl());
            m949a(nendAdNativeViewHolder.promotionNameTextView, nendAdNative.getPromotionName());
            m949a(nendAdNativeViewHolder.prTextView, nendAdNativeViewHolder.prText);
            m949a(nendAdNativeViewHolder.actionTextView, nendAdNative.getActionText());
            m948a(nendAdNativeViewHolder.adImageView, nendAdNative.getAdImageUrl(), nendAdNative);
            m948a(nendAdNativeViewHolder.logoImageView, nendAdNative.getLogoImageUrl(), nendAdNative);
            mo8021a(nendAdNativeViewHolder.itemView, nendAdNativeViewHolder.prTextView, nendAdNative);
        }
    }

    /* renamed from: a */
    private void m949a(TextView textView, String str) {
        if (textView != null && textView.getVisibility() == 0 && !TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
    }

    /* renamed from: a */
    private void m948a(final ImageView imageView, String str, NendAdNative nendAdNative) {
        if (imageView != null && imageView.getVisibility() == 0 && !TextUtils.isEmpty(str)) {
            mo8022a(str, nendAdNative, (Callback) new Callback() {
                public void onSuccess(Bitmap bitmap) {
                    imageView.setImageBitmap(bitmap);
                }

                public void onFailure(Exception exc) {
                    imageView.setImageDrawable(null);
                }
            });
        }
    }

    /* renamed from: a */
    public void mo8022a(final String str, final NendAdNative nendAdNative, final Callback callback) {
        Bitmap cache = nendAdNative.getCache(str);
        if (cache == null || cache.isRecycled()) {
            C0740a.m1170a().mo8183a(m947a(str), new C0742a<Bitmap>() {
                /* renamed from: a */
                public void mo7491a(Bitmap bitmap, Exception exc) {
                    if (bitmap != null) {
                        callback.onSuccess(bitmap);
                        nendAdNative.setCache(str, bitmap);
                        return;
                    }
                    callback.onFailure(new C0524b(C0758g.ERR_UNEXPECTED));
                }
            });
            return;
        }
        callback.onSuccess(cache);
    }

    /* renamed from: a */
    public void mo8021a(View view, TextView textView, NendAdNative nendAdNative) {
        if (view == null || textView == null || nendAdNative == null) {
            C0757f.m1220c("Parameter is invalid for activateAd.");
            return;
        }
        OnPreDrawListener onPreDrawListener = (OnPreDrawListener) f925b.get(view);
        if (onPreDrawListener != null) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(onPreDrawListener);
            }
            f925b.remove(view);
        }
        this.f926c = nendAdNative;
        this.f929f = view;
        view.setOnClickListener(this.f927d);
        textView.setOnClickListener(this.f928e);
        if (!this.f926c.isSentImpression() && view.getViewTreeObserver().isAlive()) {
            view.getViewTreeObserver().addOnPreDrawListener(this.f930g);
            f925b.put(view, this.f930g);
        }
    }

    /* renamed from: a */
    public static boolean m950a(View view, View view2, int i) {
        Rect rect = new Rect();
        if (view2 == null || view2.getVisibility() != 0) {
            return false;
        }
        if ((view != null && view.getParent() == null) || !view2.getGlobalVisibleRect(rect)) {
            return false;
        }
        long height = ((long) rect.height()) * ((long) rect.width());
        long height2 = ((long) view2.getHeight()) * ((long) view2.getWidth());
        if (height2 <= 0 || height * 100 < height2 * ((long) i)) {
            return false;
        }
        return true;
    }
}
