package net.nend.android;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import net.nend.android.internal.utilities.C0757f;
import net.nend.android.internal.utilities.C0758g;

public class NendAdNativeViewHolder {
    public TextView actionTextView;
    public ImageView adImageView;
    public TextView contentTextView;
    public View itemView;
    public ImageView logoImageView;
    public String prText;
    public TextView prTextView;
    public TextView promotionNameTextView;
    public TextView promotionUrlTextView;
    public TextView titleTextView;

    public NendAdNativeViewHolder(View view, NendAdNativeViewBinder nendAdNativeViewBinder) {
        if (view != null && nendAdNativeViewBinder != null) {
            this.itemView = view;
            try {
                this.adImageView = (ImageView) view.findViewById(nendAdNativeViewBinder.getAdImageId());
                this.logoImageView = (ImageView) view.findViewById(nendAdNativeViewBinder.getLogoImageId());
                this.titleTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getTitleId());
                this.contentTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getContentId());
                this.promotionNameTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getPromotionNameId());
                this.promotionUrlTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getPromotionUrId());
                this.prTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getPrId());
                this.actionTextView = (TextView) view.findViewById(nendAdNativeViewBinder.getActionId());
                this.prText = nendAdNativeViewBinder.getPrText();
            } catch (ClassCastException e) {
                C0757f.m1214a(C0758g.ERR_VALIDATION_BINDER_SETTING, e.getMessage());
                this.adImageView = null;
                this.logoImageView = null;
                this.titleTextView = null;
                this.contentTextView = null;
                this.promotionNameTextView = null;
                this.promotionUrlTextView = null;
                this.prTextView = null;
                this.actionTextView = null;
            }
        }
    }
}
