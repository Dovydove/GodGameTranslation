package net.nend.android.internal.utilities;

import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/* renamed from: net.nend.android.internal.utilities.c */
/* compiled from: NendFileUtils */
public class C0753c {
    /* renamed from: c */
    private static boolean m1192c(File file) {
        if (!file.exists()) {
            return file.mkdirs();
        }
        return true;
    }

    /* renamed from: a */
    public static File m1185a(Context context, String str) {
        if (VERSION.SDK_INT >= 21) {
            return new File(context.getNoBackupFilesDir().getPath(), str);
        }
        return new File(context.getFilesDir().getPath(), str);
    }

    /* renamed from: a */
    protected static void m1188a(Context context, String str, String str2, String str3) {
        File a = m1185a(context, str2);
        if (m1192c(a)) {
            try {
                FileWriter fileWriter = new FileWriter(new File(a, str));
                fileWriter.write(str3);
                fileWriter.close();
            } catch (IOException e) {
                C0757f.m1218b("Failed to write file.", (Throwable) e);
            }
        }
    }

    @NonNull
    /* renamed from: a */
    public static String m1186a(File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    bufferedReader.close();
                    return sb.toString();
                }
            }
        } catch (IOException e) {
            C0757f.m1218b("Failed to read file.", (Throwable) e);
            return "";
        }
    }

    /* renamed from: b */
    protected static void m1190b(Context context, String str) {
        m1191b(m1185a(context, str));
    }

    /* renamed from: b */
    public static void m1191b(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File b : file.listFiles()) {
                    m1191b(b);
                }
            }
            file.delete();
        }
    }

    /* renamed from: a */
    public static String m1187a(String str) {
        return C0755e.m1195a(str).toUpperCase() + m1189b(str);
    }

    /* renamed from: b */
    private static String m1189b(String str) {
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return "";
        }
        String substring = str.substring(lastIndexOf);
        int indexOf = substring.indexOf("?");
        if (indexOf != -1) {
            return substring.substring(0, indexOf);
        }
        return substring;
    }
}
