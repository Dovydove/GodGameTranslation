package net.nend.android.internal.p023ui.views.p025a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Scroller;
import java.lang.ref.WeakReference;
import net.nend.android.internal.utilities.C0740a;
import net.nend.android.internal.utilities.C0740a.C0742a;
import net.nend.android.internal.utilities.C0740a.C0745d;
import net.nend.android.internal.utilities.C0751b.C0752a;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0755e.C0756a;

@SuppressLint({"ViewConstructor"})
/* renamed from: net.nend.android.internal.ui.views.a.b */
/* compiled from: OptOutImageView */
public final class C0706b extends ImageView implements OnClickListener {

    /* renamed from: a */
    private final float f1051a = getContext().getResources().getDisplayMetrics().density;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final String f1052b;

    /* renamed from: c */
    private final Scroller f1053c;

    /* renamed from: d */
    private final Handler f1054d;

    /* renamed from: e */
    private Bitmap f1055e;

    /* renamed from: f */
    private C0709b f1056f;

    /* renamed from: net.nend.android.internal.ui.views.a.b$a */
    /* compiled from: OptOutImageView */
    private static class C0708a extends Handler {

        /* renamed from: a */
        private WeakReference<C0706b> f1058a;

        C0708a(Looper looper, C0706b bVar) {
            super(looper);
            this.f1058a = new WeakReference<>(bVar);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            C0706b bVar = (C0706b) this.f1058a.get();
            if (bVar != null) {
                bVar.mo8108d();
            }
        }
    }

    /* renamed from: net.nend.android.internal.ui.views.a.b$b */
    /* compiled from: OptOutImageView */
    public interface C0709b {
        void onInformationButtonClick();
    }

    public C0706b(Context context, String str, int i, C0709b bVar) {
        super(context);
        this.f1053c = new Scroller(context);
        this.f1054d = new C0708a(Looper.getMainLooper(), this);
        this.f1056f = bVar;
        this.f1052b = C0756a.m1206a(context, C0752a.OPT_OUT_URL.mo8195a(), "https://www.nend.net/privacy/optsdkgate") + "?uid=" + str + "&spot=" + i;
        setPadding(m1081a(18), 0, m1081a(45) * -1, m1081a(18));
        setOnClickListener(this);
        this.f1055e = C0755e.m1200b(getContext(), "nend_information_icon.png");
        if (this.f1055e != null) {
            setImageBitmap(this.f1055e);
        }
    }

    /* renamed from: a */
    public boolean mo8104a() {
        return getDrawable() != null;
    }

    public void onClick(View view) {
        if (this.f1053c.getCurrX() == ((int) (45.0f * this.f1051a))) {
            C0740a.m1170a().mo8183a(new C0745d(getContext()), new C0742a<String>() {
                /* renamed from: a */
                public void mo7491a(String str, Exception exc) {
                    String str2 = C0706b.this.f1052b + "&gaid=" + str;
                    C0706b.this.m1084e();
                    C0755e.m1198a(C0706b.this.getContext(), str2);
                }
            });
            return;
        }
        mo8106c();
        this.f1054d.removeMessages(718);
        this.f1054d.sendEmptyMessageDelayed(718, 2000);
    }

    /* access modifiers changed from: private */
    /* renamed from: e */
    public void m1084e() {
        if (this.f1056f != null) {
            this.f1056f.onInformationButtonClick();
        }
    }

    /* renamed from: b */
    public void mo8105b() {
        if (this.f1055e != null) {
            if (!this.f1055e.isRecycled()) {
                this.f1055e.recycle();
            }
            this.f1055e = null;
        }
    }

    public void computeScroll() {
        if (this.f1053c.computeScrollOffset()) {
            setPadding(this.f1053c.getCurrX() + ((m1081a(18) * (m1081a(45) - this.f1053c.getCurrX())) / m1081a(45)), 0, m1081a(45) * -1, m1081a(18));
            scrollTo(this.f1053c.getCurrX(), this.f1053c.getCurrY());
            postInvalidate();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public void mo8106c() {
        this.f1053c.forceFinished(true);
        this.f1053c.startScroll(this.f1053c.getCurrX(), this.f1053c.getCurrY(), m1081a(45) - this.f1053c.getCurrX(), 0, 1000);
        invalidate();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public void mo8108d() {
        this.f1053c.startScroll(this.f1053c.getCurrX(), this.f1053c.getCurrY(), this.f1053c.getCurrX() * -1, 0, 1000);
        invalidate();
    }

    /* renamed from: a */
    private int m1081a(int i) {
        return (int) (((float) i) * this.f1051a);
    }
}
