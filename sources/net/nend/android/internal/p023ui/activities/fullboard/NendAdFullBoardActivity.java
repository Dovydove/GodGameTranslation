package net.nend.android.internal.p023ui.activities.fullboard;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.concurrent.atomic.AtomicInteger;
import net.nend.android.NendAdFullBoardView;
import net.nend.android.NendAdFullBoardView.OnAdClickListener;
import net.nend.android.NendAdNative;
import net.nend.android.internal.utilities.C0761i;

/* renamed from: net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity */
public class NendAdFullBoardActivity extends Activity {

    /* renamed from: a */
    private NendAdNative f971a;

    /* renamed from: b */
    private int f972b;

    /* renamed from: c */
    private int f973c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public int f974d;

    /* renamed from: e */
    private OnAdClickListener f975e = new OnAdClickListener() {
        public void onClickAd() {
            C0678a.m995d(NendAdFullBoardActivity.this.f974d);
        }
    };

    /* renamed from: net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity$a */
    public static class C0678a {

        /* renamed from: a */
        private static SparseArray<C0679b> f978a = new SparseArray<>();

        /* renamed from: a */
        public static void m992a(int i, C0679b bVar) {
            f978a.append(i, bVar);
        }

        /* renamed from: a */
        public static void m991a(int i) {
            f978a.remove(i);
        }

        /* renamed from: b */
        static void m993b(int i) {
            C0679b bVar = (C0679b) f978a.get(i);
            if (bVar != null) {
                bVar.mo7425a();
            }
        }

        /* renamed from: c */
        static void m994c(int i) {
            C0679b bVar = (C0679b) f978a.get(i);
            if (bVar != null) {
                bVar.mo7426b();
            }
        }

        /* renamed from: d */
        static void m995d(int i) {
            C0679b bVar = (C0679b) f978a.get(i);
            if (bVar != null) {
                bVar.mo7427c();
            }
        }
    }

    /* renamed from: net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity$b */
    public interface C0679b {
        /* renamed from: a */
        void mo7425a();

        /* renamed from: b */
        void mo7426b();

        /* renamed from: c */
        void mo7427c();
    }

    /* renamed from: net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity$c */
    private static class C0680c {

        /* renamed from: a */
        final NendAdNative f979a;

        /* renamed from: b */
        final int f980b;

        /* renamed from: c */
        final int f981c;

        /* renamed from: d */
        final int f982d;

        private C0680c(NendAdNative nendAdNative, int i, int i2, int i3) {
            this.f979a = nendAdNative;
            this.f980b = i;
            this.f981c = i2;
            this.f982d = i3;
        }
    }

    /* renamed from: net.nend.android.internal.ui.activities.fullboard.NendAdFullBoardActivity$d */
    public static class C0681d {

        /* renamed from: a */
        private static AtomicInteger f983a = new AtomicInteger();

        /* renamed from: b */
        private static SparseArray<Bitmap> f984b = new SparseArray<>();

        /* renamed from: a */
        public static int m999a(Bitmap bitmap) {
            int andIncrement = f983a.getAndIncrement();
            f984b.put(andIncrement, bitmap);
            return andIncrement;
        }

        /* renamed from: a */
        public static Bitmap m1000a(int i) {
            return (Bitmap) f984b.get(i);
        }

        /* renamed from: b */
        public static void m1001b(int i) {
            f984b.remove(i);
        }
    }

    public static Bundle newBundle(NendAdNative nendAdNative, int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("NendAdFullBoardNativeAd", nendAdNative);
        bundle.putInt("NendAdFullBoardAdImageKey", i);
        bundle.putInt("NendAdFullBoardLogoImageKey", i2);
        bundle.putInt("NendAdFullBoardListenerKey", i3);
        return bundle;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        C0680c cVar = (C0680c) getLastNonConfigurationInstance();
        if (cVar != null) {
            this.f971a = cVar.f979a;
            this.f972b = cVar.f980b;
            this.f973c = cVar.f981c;
            this.f974d = cVar.f982d;
        } else if (bundle == null) {
            Intent intent = getIntent();
            if (intent == null || intent.getParcelableExtra("NendAdFullBoardNativeAd") == null) {
                finish();
                return;
            }
            this.f971a = (NendAdNative) intent.getParcelableExtra("NendAdFullBoardNativeAd");
            this.f972b = intent.getIntExtra("NendAdFullBoardAdImageKey", -1);
            this.f973c = intent.getIntExtra("NendAdFullBoardLogoImageKey", -1);
            this.f974d = intent.getIntExtra("NendAdFullBoardListenerKey", -1);
            C0678a.m993b(this.f974d);
        } else {
            this.f971a = (NendAdNative) bundle.getParcelable("NendAdFullBoardNativeAd");
            this.f972b = bundle.getInt("NendAdFullBoardAdImageKey");
            this.f973c = bundle.getInt("NendAdFullBoardLogoImageKey");
            this.f974d = bundle.getInt("NendAdFullBoardListenerKey");
        }
        m989b();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable("NendAdFullBoardNativeAd", this.f971a);
        bundle.putInt("NendAdFullBoardAdImageKey", this.f972b);
        bundle.putInt("NendAdFullBoardLogoImageKey", this.f973c);
        bundle.putInt("NendAdFullBoardListenerKey", this.f974d);
        super.onSaveInstanceState(bundle);
    }

    public Object onRetainNonConfigurationInstance() {
        return new C0680c(this.f971a, this.f972b, this.f973c, this.f974d);
    }

    public void onBackPressed() {
        m988a();
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m988a() {
        C0681d.m1001b(this.f972b);
        C0681d.m1001b(this.f973c);
        C0678a.m994c(this.f974d);
        C0678a.m991a(this.f974d);
    }

    /* renamed from: b */
    private void m989b() {
        NendAdFullBoardView nendAdFullBoardView = (NendAdFullBoardView) LayoutInflater.from(this).inflate(C0761i.m1234c(this, "activity_nend_ad_full_board"), null);
        nendAdFullBoardView.setNativeAd(this.f971a, C0681d.m1000a(this.f972b), C0681d.m1000a(this.f973c));
        nendAdFullBoardView.setOnAdClickListener(this.f975e);
        nendAdFullBoardView.enableCloseButton(new OnClickListener() {
            public void onClick(View view) {
                NendAdFullBoardActivity.this.m988a();
                NendAdFullBoardActivity.this.finish();
            }
        });
        setContentView(nendAdFullBoardView);
    }
}
