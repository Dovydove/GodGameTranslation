package org.andengine.util.modifier;

import java.util.Comparator;
import org.andengine.util.exception.AndEngineRuntimeException;

public interface IModifier<T> {
    public static final Comparator<IModifier<?>> MODIFIER_COMPARATOR_DURATION_DESCENDING = new Comparator<IModifier<?>>() {
        public int compare(IModifier<?> pModifierA, IModifier<?> pModifierB) {
            float durationA = pModifierA.getDuration();
            float durationB = pModifierB.getDuration();
            if (durationA < durationB) {
                return 1;
            }
            if (durationA > durationB) {
                return -1;
            }
            return 0;
        }
    };

    public static class DeepCopyNotSupportedException extends AndEngineRuntimeException {
        private static final long serialVersionUID = -5838035434002587320L;
    }

    public interface IModifierListener<T> {
        void onModifierFinished(IModifier<T> iModifier, T t);

        void onModifierStarted(IModifier<T> iModifier, T t);
    }

    void addModifierListener(IModifierListener<T> iModifierListener);

    IModifier<T> deepCopy() throws DeepCopyNotSupportedException;

    float getDuration();

    float getSecondsElapsed();

    boolean isAutoUnregisterWhenFinished();

    boolean isFinished();

    float onUpdate(float f, T t);

    boolean removeModifierListener(IModifierListener<T> iModifierListener);

    void reset();

    void setAutoUnregisterWhenFinished(boolean z);
}
