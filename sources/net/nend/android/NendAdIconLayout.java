package net.nend.android;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.p000v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import net.nend.android.NendAdIconLoader.OnClickListener;
import net.nend.android.NendAdIconLoader.OnFailedListener;
import net.nend.android.NendAdIconLoader.OnInformationClickListener;
import net.nend.android.NendAdIconLoader.OnReceiveListener;
import net.nend.android.internal.utilities.C0758g;
import net.nend.android.internal.utilities.C0761i;

public class NendAdIconLayout extends LinearLayout implements OnClickListener, OnFailedListener, OnInformationClickListener, OnReceiveListener {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    /* renamed from: a */
    private int f265a;

    /* renamed from: b */
    private String f266b;

    /* renamed from: c */
    private int f267c;

    /* renamed from: d */
    private boolean f268d;

    /* renamed from: e */
    private int f269e;

    /* renamed from: f */
    private boolean f270f;

    /* renamed from: g */
    private NendAdIconLoader f271g;

    /* renamed from: h */
    private OnClickListener f272h;

    /* renamed from: i */
    private OnInformationClickListener f273i;

    /* renamed from: j */
    private OnFailedListener f274j;

    /* renamed from: k */
    private OnReceiveListener f275k;

    public NendAdIconLayout(Context context, int i, String str, int i2) {
        super(context);
        this.f267c = 0;
        this.f268d = true;
        this.f269e = ViewCompat.MEASURED_STATE_MASK;
        this.f270f = true;
        this.f265a = i;
        this.f266b = str;
        this.f267c = i2;
    }

    public NendAdIconLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NendAdIconLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        this.f267c = 0;
        this.f268d = true;
        this.f269e = ViewCompat.MEASURED_STATE_MASK;
        this.f270f = true;
        if (attributeSet == null) {
            throw new NullPointerException(C0758g.ERR_INVALID_ATTRIBUTE_SET.mo8198b());
        }
        TypedArray typedArray = null;
        try {
            typedArray = C0761i.m1231a(context, attributeSet, i);
            this.f267c = typedArray.getInt(C0761i.m1229a(context, "NendIconCount"), 0);
            if (this.f267c <= 0) {
                throw new NullPointerException(C0758g.ERR_INVALID_ICON_COUNT.mo8198b());
            }
            setOrientation(typedArray.getInt(C0761i.m1229a(context, "NendOrientation"), 0));
            this.f269e = typedArray.getColor(C0761i.m1229a(context, "NendTitleColor"), ViewCompat.MEASURED_STATE_MASK);
            this.f268d = typedArray.getBoolean(C0761i.m1229a(context, "NendTitleVisible"), true);
            this.f270f = typedArray.getBoolean(C0761i.m1229a(context, "NendIconSpaceEnabled"), true);
            this.f265a = typedArray.getInt(C0761i.m1229a(context, "NendSpotId"), 0);
            this.f266b = typedArray.getString(C0761i.m1229a(context, "NendApiKey"));
            loadAd();
        } finally {
            if (typedArray != null) {
                typedArray.recycle();
            }
        }
    }

    public void loadAd() {
        this.f271g = new NendAdIconLoader(getContext(), this.f265a, this.f266b);
        int i = 0;
        while (i < this.f267c && i <= 7) {
            NendAdIconView nendAdIconView = new NendAdIconView(getContext());
            nendAdIconView.setTitleColor(this.f269e);
            nendAdIconView.setTitleVisible(this.f268d);
            nendAdIconView.setIconSpaceEnabled(this.f270f);
            this.f271g.addIconView(nendAdIconView);
            addView(nendAdIconView, new LayoutParams(-2, -2));
            i++;
        }
        this.f271g.loadAd();
        this.f271g.setOnClickListener(this);
        this.f271g.setOnInformationClickListener(this);
        this.f271g.setOnFailedListener(this);
        this.f271g.setOnReceiveListener(this);
    }

    public void resume() {
        this.f271g.resume();
    }

    public void pause() {
        this.f271g.pause();
    }

    public void setIconOrientation(int i) {
        setOrientation(i);
    }

    public void setTitleColor(int i) {
        this.f269e = i;
    }

    public void setTitleVisible(boolean z) {
        this.f268d = z;
    }

    public void setIconSpaceEnabled(boolean z) {
        this.f270f = z;
    }

    public void onReceiveAd(NendAdIconView nendAdIconView) {
        if (this.f275k != null) {
            this.f275k.onReceiveAd(nendAdIconView);
        }
    }

    public void setOnReceiveListener(OnReceiveListener onReceiveListener) {
        this.f275k = onReceiveListener;
    }

    public void onFailedToReceiveAd(NendIconError nendIconError) {
        if (this.f274j != null) {
            this.f274j.onFailedToReceiveAd(nendIconError);
        }
    }

    public void setOnFailedListener(OnFailedListener onFailedListener) {
        this.f274j = onFailedListener;
    }

    public void onClick(NendAdIconView nendAdIconView) {
        if (this.f272h != null) {
            this.f272h.onClick(nendAdIconView);
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.f272h = onClickListener;
    }

    public void onClickInformation(NendAdIconView nendAdIconView) {
        if (this.f273i != null) {
            this.f273i.onClickInformation(nendAdIconView);
        }
    }

    public void setOnInformationClickListener(OnInformationClickListener onInformationClickListener) {
        this.f273i = onInformationClickListener;
    }
}
