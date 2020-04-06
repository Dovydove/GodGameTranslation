package net.nend.android;

public class NendAdRewardItem {

    /* renamed from: a */
    private final String f499a;

    /* renamed from: b */
    private final int f500b;

    NendAdRewardItem(String str, int i) {
        this.f499a = str;
        this.f500b = i;
    }

    public String getCurrencyName() {
        return this.f499a;
    }

    public int getCurrencyAmount() {
        return this.f500b;
    }
}
