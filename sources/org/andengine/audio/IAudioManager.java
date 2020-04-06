package org.andengine.audio;

import org.andengine.audio.IAudioEntity;

public interface IAudioManager<T extends IAudioEntity> {
    void add(T t);

    float getMasterVolume();

    void releaseAll();

    boolean remove(T t);

    void setMasterVolume(float f);
}
