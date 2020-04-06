package net.nend.android;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import net.nend.android.internal.utilities.C0755e;
import net.nend.android.internal.utilities.C0761i;

public class NendAdFullBoardView extends ConstraintLayout {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public boolean f246a = false;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public NendAdNative f247b;

    /* renamed from: c */
    private View f248c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public OnAdClickListener f249d;

    /* renamed from: e */
    private final OnClickListener f250e = new OnClickListener() {
        public void onClick(View view) {
            C0755e.m1198a(view.getContext(), NendAdFullBoardView.this.f247b.getClickUrl());
            if (NendAdFullBoardView.this.f249d != null) {
                NendAdFullBoardView.this.f249d.onClickAd();
            }
        }
    };

    /* renamed from: f */
    private final OnClickListener f251f = new OnClickListener() {
        public void onClick(View view) {
            NendAdFullBoardView.this.m166a(view);
        }
    };

    public static class Builder {

        /* renamed from: a */
        private final Context f260a;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public final NendAdFullBoard f261b;

        /* renamed from: c */
        private OnClickListener f262c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public FullBoardAdClickListener f263d;

        private Builder(Context context, NendAdFullBoard nendAdFullBoard) {
            this.f260a = context;
            this.f261b = nendAdFullBoard;
        }

        public static Builder with(Context context, NendAdFullBoard nendAdFullBoard) {
            if (context != null && nendAdFullBoard != null) {
                return new Builder(context, nendAdFullBoard);
            }
            throw new IllegalArgumentException("A null-argument was passed.");
        }

        public Builder enableCloseButton(OnClickListener onClickListener) {
            this.f262c = onClickListener;
            return this;
        }

        public Builder adClickListener(FullBoardAdClickListener fullBoardAdClickListener) {
            this.f263d = fullBoardAdClickListener;
            return this;
        }

        public View build() {
            NendAdFullBoardView nendAdFullBoardView = (NendAdFullBoardView) LayoutInflater.from(this.f260a).inflate(C0761i.m1234c(this.f260a, "activity_nend_ad_full_board"), null);
            nendAdFullBoardView.enableCloseButton(this.f262c);
            nendAdFullBoardView.setNativeAd(this.f261b.mo7420a(), this.f261b.mo7421b(), this.f261b.mo7422c());
            if (this.f263d != null) {
                nendAdFullBoardView.setOnAdClickListener(new OnAdClickListener() {
                    public void onClickAd() {
                        Builder.this.f263d.onClickAd(Builder.this.f261b);
                    }
                });
            }
            return nendAdFullBoardView;
        }
    }

    public interface FullBoardAdClickListener {
        void onClickAd(NendAdFullBoard nendAdFullBoard);
    }

    public interface OnAdClickListener {
        void onClickAd();
    }

    /* renamed from: net.nend.android.NendAdFullBoardView$a */
    private static abstract class C0456a implements AnimationListener {
        private C0456a() {
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    public NendAdFullBoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static Animation m168b(float f) {
        TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, f, 0.0f, 0.0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);
        translateAnimation.setFillEnabled(true);
        return translateAnimation;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.f248c = findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_close_button"));
            this.f248c.setVisibility(8);
            findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_card")).setOnClickListener(this.f250e);
            findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_information_button")).setOnClickListener(this.f251f);
            View findViewById = findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_action_button"));
            if (findViewById != null) {
                findViewById.setOnClickListener(this.f250e);
            }
            ViewTreeObserver viewTreeObserver = getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.addOnPreDrawListener(new OnPreDrawListener() {
                    public boolean onPreDraw() {
                        NendAdFullBoardView.this.f247b.onImpression();
                        ViewTreeObserver viewTreeObserver = NendAdFullBoardView.this.getViewTreeObserver();
                        if (viewTreeObserver.isAlive()) {
                            viewTreeObserver.removeOnPreDrawListener(this);
                        }
                        return true;
                    }
                });
            }
        }
    }

    public void enableCloseButton(OnClickListener onClickListener) {
        if (this.f248c != null && onClickListener != null) {
            this.f248c.setVisibility(0);
            this.f248c.setOnClickListener(onClickListener);
        }
    }

    public void setOnAdClickListener(OnAdClickListener onAdClickListener) {
        this.f249d = onAdClickListener;
    }

    public void setNativeAd(NendAdNative nendAdNative, Bitmap bitmap, Bitmap bitmap2) {
        this.f247b = nendAdNative;
        ((ImageView) findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_image"))).setImageBitmap(bitmap);
        ((ImageView) findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_logo"))).setImageBitmap(bitmap2);
        ((TextView) findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_promotion"))).setText(nendAdNative.getPromotionName());
        TextView textView = (TextView) findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_content"));
        if (textView != null) {
            textView.setText(nendAdNative.getContentText());
        }
        TextView textView2 = (TextView) findViewById(C0761i.m1233b(getContext(), "nend_full_board_ad_action_button"));
        if (textView2 != null) {
            textView2.setText(getContext().getString(C0761i.m1235d(getContext(), "nend_full_board_ad_action_button_text")));
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m166a(final View view) {
        if (view.getAnimation() == null) {
            final int left = view.getLeft();
            if (left != 0) {
                this.f246a = false;
                Animation b = m168b((float) (0 - left));
                b.setAnimationListener(new C0456a() {
                    public void onAnimationEnd(Animation animation) {
                        view.layout(0, view.getTop(), view.getWidth(), view.getBottom());
                        view.setAnimation(null);
                        NendAdFullBoardView.this.postDelayed(new Runnable() {
                            public void run() {
                                if (NendAdFullBoardView.this.f246a) {
                                    view.layout(left, view.getTop(), view.getWidth() - Math.abs(left), view.getBottom());
                                    return;
                                }
                                Animation a = NendAdFullBoardView.m168b((float) left);
                                a.setAnimationListener(new C0456a() {
                                    public void onAnimationEnd(Animation animation) {
                                        view.layout(left, view.getTop(), view.getWidth() - Math.abs(left), view.getBottom());
                                        view.setAnimation(null);
                                    }
                                });
                                view.startAnimation(a);
                            }
                        }, 1000);
                    }
                });
                view.startAnimation(b);
                return;
            }
            this.f246a = true;
            this.f247b.onClickInformation(getContext());
        }
    }
}
