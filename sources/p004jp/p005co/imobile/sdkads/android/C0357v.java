package p004jp.p005co.imobile.sdkads.android;

import android.net.Uri.Builder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: jp.co.imobile.sdkads.android.v */
final class C0357v {
    C0357v() {
    }

    /* renamed from: a */
    private static Builder m118a(C0361z zVar, String str) {
        try {
            URL url = new URL(str);
            String protocol = url.getProtocol();
            String host = url.getHost();
            int port = url.getPort();
            String path = url.getPath();
            Builder builder = new Builder();
            builder.scheme(protocol);
            if (port > 0) {
                builder.encodedAuthority(new StringBuilder(String.valueOf(host)).append(":").append(Integer.toString(port)).toString());
            } else {
                builder.encodedAuthority(host);
            }
            builder.path(path);
            builder.appendQueryParameter("pid", zVar.mo7276c());
            builder.appendQueryParameter("mid", zVar.mo7277d());
            builder.appendQueryParameter("asid", zVar.mo7278e());
            C0353r.m99a().mo7255a(builder);
            builder.appendQueryParameter("test", Boolean.toString(ImobileSdkAd.m39b().booleanValue()));
            return builder;
        } catch (MalformedURLException e) {
            new StringBuilder("url MalformedURLException.").append(str);
            C0359x.m126b("Network request uri format error.", "");
            throw new C0360y(FailNotificationReason.PARAM);
        }
    }

    /* renamed from: a */
    static JSONObject m119a(String str) {
        boolean z = false;
        try {
            return m123d(m122c(str));
        } catch (C0360y e) {
            new StringBuilder("send uri:").append(str);
            C0359x.m126b("Network Imp Request send Error.", "");
            return z;
        }
    }

    /* renamed from: a */
    static JSONObject m120a(C0361z zVar) {
        return m123d(m122c(m118a(zVar, "http://spapi.i-mobile.co.jp/app/apiRich/ad_spot_environment.ashx").toString()));
    }

    /* renamed from: b */
    static String m121b(String str) {
        String str2 = "error";
        try {
            return m122c(str).toString();
        } catch (C0360y e) {
            new StringBuilder("send uri:").append(str);
            C0359x.m126b("Network RequestFromHtml Request send Error.", "");
            return str2;
        }
    }

    /* renamed from: c */
    static String m122c(String str) {
        C0353r.m99a();
        if (C0353r.m106b().equals("")) {
            C0359x.m126b("Network Condition.", "");
            throw new C0360y(FailNotificationReason.NETWORK_NOT_READY);
        }
        new StringBuilder("Request uri:").append(str);
        C0359x.m125a(null);
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpParams params = defaultHttpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 10000);
        HttpConnectionParams.setSoTimeout(params, 10000);
        HttpGet httpGet = new HttpGet(str);
        try {
            HttpResponse execute = defaultHttpClient.execute(httpGet);
            String str2 = "";
            if (execute != null && execute.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = execute.getEntity();
                try {
                    str2 = EntityUtils.toString(entity, "utf-8");
                    C0359x.m125a(null);
                    try {
                        entity.consumeContent();
                    } catch (IOException e) {
                        new StringBuilder("IOException.").append(entity.toString());
                        C0359x.m125a(e);
                    }
                } catch (ParseException e2) {
                    String str3 = "Network response data error";
                    new StringBuilder("httpEntity toString ParseException.").append(entity.toString());
                    C0359x.m126b(str3, "PARSE");
                    throw new C0360y(FailNotificationReason.RESPONSE);
                } catch (IOException e3) {
                    String str4 = "Network response data error";
                    new StringBuilder("IOException.").append(entity.toString());
                    C0359x.m126b(str4, "IO");
                    throw new C0360y(FailNotificationReason.RESPONSE);
                } catch (Throwable th) {
                    try {
                        entity.consumeContent();
                    } catch (IOException e4) {
                        new StringBuilder("IOException.").append(entity.toString());
                        C0359x.m125a(e4);
                    }
                    throw th;
                }
            }
            defaultHttpClient.getConnectionManager().shutdown();
            return str2;
        } catch (ClientProtocolException e5) {
            new StringBuilder("ClientProtocolException.").append(httpGet.toString());
            C0359x.m126b("Network Connection error.", "Timeout.");
            throw new C0360y(FailNotificationReason.NETWORK);
        } catch (IOException e6) {
            new StringBuilder("IOException.").append(httpGet.toString());
            C0359x.m126b("SdkConnection requestSend Error", "Connection.");
            throw new C0360y(FailNotificationReason.NETWORK);
        }
    }

    /* renamed from: d */
    private static JSONObject m123d(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            new StringBuilder("jsonObject ParseException.").append(str);
            C0359x.m126b("SdkConnection requestSend response data error", "DATA");
            throw new C0360y(FailNotificationReason.RESPONSE);
        }
    }
}
