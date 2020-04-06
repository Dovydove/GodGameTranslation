package p004jp.p005co.imobile.sdkads.android;

import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.concurrent.Executors;
import org.andengine.util.level.constants.LevelConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p004jp.p005co.imobile.sdkads.android.ImobileSdkAd.AdShowType;

/* renamed from: jp.co.imobile.sdkads.android.d */
final class C0339d extends WebViewClient {

    /* renamed from: a */
    final /* synthetic */ C0318a f98a;

    /* renamed from: b */
    private final /* synthetic */ C0361z f99b;

    C0339d(C0318a aVar, C0361z zVar) {
        this.f98a = aVar;
        this.f99b = zVar;
    }

    public final void onPageFinished(WebView webView, String url) {
        if (url.equals(this.f99b.mo7279f())) {
            new StringBuilder("url:").append(url);
            C0359x.m125a(null);
            this.f98a.f108d = Boolean.valueOf(true);
            if (this.f98a.f109e != null) {
                this.f98a.f109e.onAdReadyCompleted();
            }
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView web, String url) {
        String str;
        C0340e eVar = (C0340e) web;
        Boolean.valueOf(false);
        String str2 = "";
        new StringBuilder("call url:").append(url);
        C0359x.m125a(null);
        if (url.indexOf("api://") != 0) {
            return false;
        }
        String substring = url.substring(url.indexOf("//") + "//".length(), url.indexOf("?"));
        String substring2 = url.substring(url.indexOf("?cb=") + "?cb=".length(), url.indexOf("&values"));
        try {
            String decode = URLDecoder.decode(url.substring(url.indexOf("&values=") + "&values=".length()), "UTF-8");
            Boolean valueOf = Boolean.valueOf(Boolean.valueOf(url.substring(url.indexOf("&adclose=") + "&adclose=".length(), url.indexOf("&values="))).booleanValue());
            new StringBuilder("apiName:").append(substring).append(" callbackFunctionName:").append(substring2).append(" value:").append(decode).append(" adclose:").append(valueOf);
            C0359x.m125a(null);
            try {
                if (substring.equals("GetSpotInfo")) {
                    str = this.f99b.mo7268a(this.f98a.f114j, this.f98a.f115k);
                    new StringBuilder("value:").append(str);
                    C0359x.m125a(null);
                } else if (substring.equals("GetDeviceInfo")) {
                    str = C0353r.m99a().mo7259f();
                    new StringBuilder("value:").append(str);
                    C0359x.m125a(null);
                } else if (substring.equals("SetAdSizeAndPosition")) {
                    JSONObject c = C0353r.m109c(decode);
                    C0318a.m47a(this.f98a, C0353r.m95a(c, "left"), C0353r.m95a(c, "top"), C0353r.m95a(c, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH), C0353r.m95a(c, LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT));
                    C0359x.m126b("Ad view transform Complate.", "");
                    str = "{\"status\":\"succeed\"}";
                } else if (substring.equals("ShowAdReady")) {
                    JSONObject c2 = C0353r.m109c(decode);
                    Calendar instance = Calendar.getInstance();
                    instance.add(13, C0353r.m95a(c2, "effectTime"));
                    this.f98a.f105a = instance.getTime();
                    this.f98a.mo7241a(C0345j.DISPLAYABLE);
                    C0359x.m126b("Ad view create Complate.", "");
                    this.f98a.f117m.onAdReadyCompleted();
                    str = str2;
                } else if (substring.equals("ImpSend")) {
                    C0318a aVar = this.f98a;
                    Executors.newCachedThreadPool().submit(new C0344i(aVar, Executors.newCachedThreadPool().submit(new C0343h(aVar, decode)), decode));
                    str = str2;
                } else {
                    if (substring.equals("ClickSend")) {
                        if (this.f98a.f58o != null) {
                            JSONObject c3 = C0353r.m109c(decode);
                            String str3 = (String) this.f98a.f106b.get(C0353r.m107b(c3, "advertisementId"));
                            String b = C0353r.m107b(c3, "clickUrl");
                            Intent intent = str3 == null ? new Intent("android.intent.action.VIEW", Uri.parse(b)) : new Intent("android.intent.action.VIEW", Uri.parse(new StringBuilder(String.valueOf(b)).append("&vh=").append(str3).toString()));
                            intent.setFlags(ErrorDialogData.BINDER_CRASH);
                            this.f98a.f58o.startActivity(intent);
                            this.f98a.f117m.onAdCliclkCompleted();
                            new StringBuilder("url:").append(decode).append(" viewHash:").append(str3);
                            C0359x.m126b("Ad View click send complete.", "");
                            if (this.f98a.f115k.booleanValue() && this.f98a.f116l != null) {
                                this.f98a.f107c.mo7217a(this.f98a.f116l);
                            }
                        } else {
                            C0359x.m126b("Ad View click send failed.", "");
                            this.f98a.f117m.onFailed(FailNotificationReason.UNKNOWN);
                        }
                        if (this.f99b.mo7274b() == AdShowType.DIALOG) {
                            this.f98a.f58o = null;
                            str = str2;
                        }
                    } else if (substring.equals("CloseAd")) {
                        this.f98a.f117m.onAdCloseCompleted();
                        C0359x.m126b("Ad View closed.", "");
                        this.f98a.f58o = null;
                        str = str2;
                    } else if (substring.equals("ShowAdBeforeComplete")) {
                        JSONObject c4 = C0353r.m109c(decode);
                        C0318a.m47a(this.f98a, C0353r.m95a(c4, "left"), C0353r.m95a(c4, "top"), C0353r.m95a(c4, LevelConstants.TAG_LEVEL_ATTRIBUTE_WIDTH), C0353r.m95a(c4, LevelConstants.TAG_LEVEL_ATTRIBUTE_HEIGHT));
                        if (this.f98a.f116l != null) {
                            C0359x.m125a(null);
                            this.f98a.f116l.onAdReadyCompleted();
                        }
                        new StringBuilder("value:").append(decode);
                        C0359x.m125a(null);
                        str = str2;
                    } else if (substring.equals("OpenImobileSite")) {
                        Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(decode));
                        intent2.setFlags(ErrorDialogData.BINDER_CRASH);
                        this.f98a.f58o.startActivity(intent2);
                        str = str2;
                    } else if (!substring.equals("DeviceRotatedComplete")) {
                        if (substring.equals("IsAppInstall")) {
                            str = Boolean.toString(C0353r.m103a(decode));
                        } else if (substring.equals("IsAppInstallFromAppId")) {
                            str = Boolean.toString(C0353r.m99a().mo7256b(decode));
                        } else if (substring.equals("StartApp")) {
                            C0353r.m100a(this.f98a.f58o, decode);
                            this.f98a.f58o = null;
                            str = str2;
                        } else if (substring.equals("StartAppFromAppId")) {
                            C0353r.m108b(this.f98a.f58o, decode);
                            this.f98a.f58o = null;
                            str = str2;
                        } else if (substring.equals("StartAppFromUrl")) {
                            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(decode));
                            intent3.setFlags(ErrorDialogData.BINDER_CRASH);
                            this.f98a.f58o.startActivity(intent3);
                            str = str2;
                        } else if (substring.equals("SendRequestFromHtml")) {
                            C0353r.m101a(decode, substring2, this.f98a.f107c);
                            str = str2;
                        } else if (substring.equals("ApplicationTargeting")) {
                            str = Boolean.toString(C0353r.m104a(new JSONObject(URLDecoder.decode(decode, "UTF8"))));
                        } else if (substring.equals("SomeApplicationTargeting")) {
                            JSONObject jSONObject = new JSONObject();
                            JSONArray jSONArray = new JSONArray();
                            JSONArray jSONArray2 = new JSONObject(URLDecoder.decode(decode, "UTF8")).getJSONArray("targetApps");
                            for (int i = 0; i < jSONArray2.length(); i++) {
                                JSONObject jSONObject2 = jSONArray2.getJSONObject(i);
                                JSONObject jSONObject3 = new JSONObject();
                                jSONObject3.put("advertisementId", jSONObject2.getString("advertisementId"));
                                jSONObject3.put("result", Boolean.toString(C0353r.m104a(new JSONObject(jSONObject2.getString("condition")))));
                                jSONArray.put(jSONObject3);
                            }
                            jSONObject.put("result", jSONArray);
                            str = jSONObject.toString();
                        } else if (substring.equals("NotDeliveryAd")) {
                            this.f98a.f117m.onFailed(FailNotificationReason.NOT_DELIVERY_AD);
                            this.f98a.mo7241a(C0345j.ERROR);
                            str = str2;
                        } else if (substring.equals("IsAdViewActive")) {
                            str = "{\"result\":\"" + Boolean.toString(eVar.mo7213a().booleanValue()) + "\"}";
                            this.f98a.f57n.bringToFront();
                            new StringBuilder("IsAdViewActive:").append(str);
                            C0359x.m125a(null);
                        } else if (substring.equals("Error")) {
                            this.f98a.mo7241a(C0345j.SCRIPT_ERROR);
                            this.f98a.f117m.onFailed(FailNotificationReason.RESPONSE);
                            new StringBuilder("error:").append(decode);
                            C0359x.m126b("Ad View error.", "ErroCode(script error)");
                        }
                    }
                    str = str2;
                }
                if (valueOf.booleanValue()) {
                    C0318a.m50d(this.f98a);
                }
                if (!substring2.equals("")) {
                    if (str.equals("")) {
                        eVar.mo7215a("javascript:" + substring2 + "();");
                    } else {
                        eVar.mo7215a("javascript:" + substring2 + "('" + str + "');");
                        new StringBuilder("result:").append(str);
                        C0359x.m125a(null);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                String str4 = "ApplicationTargeting data error.";
                new StringBuilder("UnsupportedEncodingException error:").append(decode);
                C0359x.m126b(str4, "ErroCode(t data error1)");
                throw new C0360y(FailNotificationReason.RESPONSE);
            } catch (JSONException e2) {
                new StringBuilder("JSONException error:").append(decode);
                C0359x.m126b("ApplicationTargeting data error.", "ErroCode(t data error2)");
                throw new C0360y(FailNotificationReason.RESPONSE);
            } catch (UnsupportedEncodingException e3) {
                String str5 = "ApplicationTargeting data error.";
                new StringBuilder("UnsupportedEncodingException error:").append(decode);
                C0359x.m126b(str5, "ErroCode(t data error1)");
                throw new C0360y(FailNotificationReason.RESPONSE);
            } catch (JSONException e4) {
                new StringBuilder("JSONException error:").append(decode);
                C0359x.m126b("ApplicationTargeting data error.", "ErroCode(t data error2)");
                throw new C0360y(FailNotificationReason.RESPONSE);
            } catch (C0360y e5) {
                this.f98a.mo7241a(C0345j.SCRIPT_ERROR);
                eVar.mo7215a("javascript:error(" + substring + ");");
                this.f98a.f117m.onFailed(FailNotificationReason.RESPONSE);
                new StringBuilder("error:").append(decode);
                C0359x.m126b("Ad View error.", "ErroCode(api error)");
            }
            return true;
        } catch (UnsupportedEncodingException e6) {
            new StringBuilder("ErroCode(value)").append(url).append("Message:").append(e6.getMessage());
            C0359x.m126b("Ad View Data Create Error.", "ErroCode(url)");
            return false;
        }
    }
}
