package net.nend.android;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class NendAdUserFeature {

    /* renamed from: a */
    private final Gender f501a;

    /* renamed from: b */
    private final String f502b;

    /* renamed from: c */
    private final int f503c;

    /* renamed from: d */
    private final JSONObject f504d;

    public static class Builder {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public Gender f506a = null;
        /* access modifiers changed from: private */

        /* renamed from: b */
        public String f507b = null;
        /* access modifiers changed from: private */

        /* renamed from: c */
        public int f508c = -1;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public JSONObject f509d = new JSONObject();

        /* renamed from: e */
        private SimpleDateFormat f510e = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        public Builder setGender(Gender gender) {
            this.f506a = gender;
            return this;
        }

        public Builder setBirthday(int i, int i2, int i3) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar(i, i2 - 1, i3);
            gregorianCalendar.setLenient(false);
            try {
                this.f507b = this.f510e.format(gregorianCalendar.getTime());
            } catch (IllegalArgumentException e) {
                this.f507b = null;
            }
            return this;
        }

        public Builder setAge(int i) {
            this.f508c = i;
            return this;
        }

        public Builder addCustomFeature(String str, int i) {
            try {
                this.f509d.put(str, i);
            } catch (JSONException e) {
            }
            return this;
        }

        public Builder addCustomFeature(String str, double d) {
            try {
                this.f509d.put(str, d);
            } catch (JSONException e) {
            }
            return this;
        }

        public Builder addCustomFeature(String str, String str2) {
            try {
                this.f509d.put(str, str2);
            } catch (JSONException e) {
            }
            return this;
        }

        public Builder addCustomFeature(String str, boolean z) {
            try {
                this.f509d.put(str, z);
            } catch (JSONException e) {
            }
            return this;
        }

        public NendAdUserFeature build() {
            return new NendAdUserFeature(this);
        }
    }

    public enum Gender {
        MALE,
        FEMALE;

        public String toString() {
            switch (this) {
                case MALE:
                    return "male";
                case FEMALE:
                    return "female";
                default:
                    return null;
            }
        }
    }

    private NendAdUserFeature(Builder builder) {
        this.f501a = builder.f506a;
        this.f502b = builder.f507b;
        this.f503c = builder.f508c;
        this.f504d = builder.f509d;
    }

    /* renamed from: a */
    private boolean m399a() {
        return this.f501a == null && this.f502b == null && this.f503c < 0 && this.f504d.length() == 0;
    }

    public JSONObject toJson() {
        if (m399a()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.putOpt("gender", this.f501a != null ? this.f501a.toString() : null);
            if (this.f502b != null) {
                jSONObject.put("birthday", this.f502b);
            }
            if (this.f503c >= 0 && this.f502b == null) {
                jSONObject.put("age", this.f503c);
            }
            if (this.f504d.length() > 0) {
                jSONObject.put("custom", this.f504d);
            }
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
