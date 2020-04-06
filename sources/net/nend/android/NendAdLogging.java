package net.nend.android;

import android.support.annotation.NonNull;
import net.nend.android.NendAdLogger.LogLevel;

public interface NendAdLogging {
    void logMessage(@NonNull String str, @NonNull LogLevel logLevel);
}
