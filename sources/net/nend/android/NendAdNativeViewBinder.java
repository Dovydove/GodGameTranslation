package net.nend.android;

import android.view.View;
import net.nend.android.NendAdNative.AdvertisingExplicitly;

public class NendAdNativeViewBinder {

    /* renamed from: a */
    private int f481a;

    /* renamed from: b */
    private int f482b;

    /* renamed from: c */
    private int f483c;

    /* renamed from: d */
    private int f484d;

    /* renamed from: e */
    private int f485e;

    /* renamed from: f */
    private int f486f;

    /* renamed from: g */
    private int f487g;

    /* renamed from: h */
    private String f488h;

    /* renamed from: i */
    private int f489i;

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public int f490a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public int f491b;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f492c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public int f493d;
        /* access modifiers changed from: private */

        /* renamed from: e */
        public int f494e;
        /* access modifiers changed from: private */

        /* renamed from: f */
        public int f495f;
        /* access modifiers changed from: private */

        /* renamed from: g */
        public int f496g;
        /* access modifiers changed from: private */

        /* renamed from: h */
        public String f497h;
        /* access modifiers changed from: private */

        /* renamed from: i */
        public int f498i;

        public Builder adImageId(int i) {
            this.f490a = i;
            return this;
        }

        public Builder logoImageId(int i) {
            this.f491b = i;
            return this;
        }

        public Builder titleId(int i) {
            this.f492c = i;
            return this;
        }

        public Builder contentId(int i) {
            this.f493d = i;
            return this;
        }

        public Builder promotionNameId(int i) {
            this.f494e = i;
            return this;
        }

        public Builder promotionUrlId(int i) {
            this.f495f = i;
            return this;
        }

        public Builder prId(int i, AdvertisingExplicitly advertisingExplicitly) {
            this.f496g = i;
            this.f497h = advertisingExplicitly.getText();
            return this;
        }

        public Builder actionId(int i) {
            this.f498i = i;
            return this;
        }

        public NendAdNativeViewBinder build() {
            return new NendAdNativeViewBinder(this);
        }
    }

    private NendAdNativeViewBinder(Builder builder) {
        this.f481a = builder.f490a;
        this.f482b = builder.f491b;
        this.f483c = builder.f492c;
        this.f484d = builder.f493d;
        this.f485e = builder.f494e;
        this.f486f = builder.f495f;
        this.f487g = builder.f496g;
        this.f488h = builder.f497h;
        this.f489i = builder.f498i;
    }

    public NendAdNativeViewHolder createViewHolder(View view) {
        return new NendAdNativeViewHolder(view, this);
    }

    public int getAdImageId() {
        return this.f481a;
    }

    public int getLogoImageId() {
        return this.f482b;
    }

    public int getTitleId() {
        return this.f483c;
    }

    public int getContentId() {
        return this.f484d;
    }

    public int getPromotionNameId() {
        return this.f485e;
    }

    public int getPromotionUrId() {
        return this.f486f;
    }

    public int getPrId() {
        return this.f487g;
    }

    public String getPrText() {
        return this.f488h;
    }

    public int getActionId() {
        return this.f489i;
    }
}
