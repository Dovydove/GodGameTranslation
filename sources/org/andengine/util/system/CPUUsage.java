package org.andengine.util.system;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import org.andengine.util.StreamUtils;
import org.andengine.util.TextUtils;
import org.andengine.util.debug.Debug;

public class CPUUsage {
    private long mIdle = 0;
    private long mTotal = 0;
    private float mUsage = 0.0f;

    public float getUsage() {
        return this.mUsage;
    }

    public void update() {
        BufferedReader reader = null;
        try {
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/stat")), 8192);
            try {
                String[] parts = TextUtils.SPLITPATTERN_SPACE.split(reader2.readLine());
                long user = Long.parseLong(parts[2]);
                long nice = Long.parseLong(parts[3]);
                long system = Long.parseLong(parts[4]);
                long idle = Long.parseLong(parts[5]);
                long total = user + nice + system;
                this.mUsage = (100.0f * ((float) (total - this.mTotal))) / ((float) (((total - this.mTotal) + idle) - this.mIdle));
                this.mTotal = total;
                this.mIdle = idle;
                StreamUtils.close(reader2);
                BufferedReader bufferedReader = reader2;
            } catch (IOException e) {
                e = e;
                reader = reader2;
                try {
                    Debug.m1275e((Throwable) e);
                    StreamUtils.close(reader);
                } catch (Throwable th) {
                    th = th;
                    StreamUtils.close(reader);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                reader = reader2;
                StreamUtils.close(reader);
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            Debug.m1275e((Throwable) e);
            StreamUtils.close(reader);
        }
    }
}
