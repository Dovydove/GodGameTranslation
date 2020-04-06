package org.andengine.p028ui.dialog;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import org.andengine.util.call.Callback;

/* renamed from: org.andengine.ui.dialog.StringInputDialogBuilder */
public class StringInputDialogBuilder extends GenericInputDialogBuilder<String> {
    public StringInputDialogBuilder(Context pContext, int pTitleResID, int pMessageResID, int pErrorResID, int pIconResID, Callback<String> pSuccessCallback, OnCancelListener pOnCancelListener) {
        super(pContext, pTitleResID, pMessageResID, pErrorResID, pIconResID, pSuccessCallback, pOnCancelListener);
    }

    public StringInputDialogBuilder(Context pContext, int pTitleResID, int pMessageResID, int pErrorResID, int pIconResID, String pDefaultText, Callback<String> pSuccessCallback, OnCancelListener pOnCancelListener) {
        super(pContext, pTitleResID, pMessageResID, pErrorResID, pIconResID, pDefaultText, pSuccessCallback, pOnCancelListener);
    }

    /* access modifiers changed from: protected */
    public String generateResult(String pInput) {
        return pInput;
    }
}
