package net.nend.android;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import net.nend.android.NendAdFullBoardView.FullBoardAdClickListener;
import net.nend.android.internal.p023ui.activities.fullboard.NendAdFullBoardActivity;
import net.nend.android.internal.p023ui.activities.fullboard.NendAdFullBoardActivity.C0678a;
import net.nend.android.internal.p023ui.activities.fullboard.NendAdFullBoardActivity.C0679b;
import net.nend.android.internal.p023ui.activities.fullboard.NendAdFullBoardActivity.C0681d;

public class NendAdFullBoard {

    /* renamed from: a */
    private final NendAdNative f225a;

    /* renamed from: b */
    private final Bitmap f226b;

    /* renamed from: c */
    private final Bitmap f227c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public FullBoardAdListener f228d;

    /* renamed from: e */
    private C0679b f229e = new C0679b() {
        /* renamed from: a */
        public void mo7425a() {
            if (NendAdFullBoard.this.f228d != null) {
                NendAdFullBoard.this.f228d.onShowAd(NendAdFullBoard.this);
            }
        }

        /* renamed from: b */
        public void mo7426b() {
            if (NendAdFullBoard.this.f228d != null) {
                NendAdFullBoard.this.f228d.onDismissAd(NendAdFullBoard.this);
            }
        }

        /* renamed from: c */
        public void mo7427c() {
            if (NendAdFullBoard.this.f228d != null) {
                NendAdFullBoard.this.f228d.onClickAd(NendAdFullBoard.this);
            }
        }
    };

    public interface FullBoardAdListener extends FullBoardAdClickListener {
        void onDismissAd(NendAdFullBoard nendAdFullBoard);

        void onShowAd(NendAdFullBoard nendAdFullBoard);
    }

    NendAdFullBoard(NendAdNative nendAdNative, Bitmap bitmap, Bitmap bitmap2) {
        this.f225a = nendAdNative;
        this.f226b = bitmap;
        this.f227c = bitmap2;
    }

    public void show(Activity activity) {
        int i;
        int a = C0681d.m999a(this.f226b);
        int a2 = C0681d.m999a(this.f227c);
        if (this.f228d != null) {
            i = hashCode();
            C0678a.m992a(i, this.f229e);
        } else {
            i = -1;
        }
        activity.startActivity(new Intent(activity, NendAdFullBoardActivity.class).putExtras(NendAdFullBoardActivity.newBundle(this.f225a, a, a2, i)));
    }

    public void setAdListener(FullBoardAdListener fullBoardAdListener) {
        this.f228d = fullBoardAdListener;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public NendAdNative mo7420a() {
        return this.f225a;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public Bitmap mo7421b() {
        return this.f226b;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public Bitmap mo7422c() {
        return this.f227c;
    }
}
