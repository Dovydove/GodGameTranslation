package org.andengine.audio;

import java.util.ArrayList;
import org.andengine.audio.IAudioEntity;

public abstract class BaseAudioManager<T extends IAudioEntity> implements IAudioManager<T> {
    protected final ArrayList<T> mAudioEntities = new ArrayList<>();
    protected float mMasterVolume = 1.0f;

    public float getMasterVolume() {
        return this.mMasterVolume;
    }

    public void setMasterVolume(float pMasterVolume) {
        this.mMasterVolume = pMasterVolume;
        ArrayList<T> audioEntities = this.mAudioEntities;
        for (int i = audioEntities.size() - 1; i >= 0; i--) {
            ((IAudioEntity) audioEntities.get(i)).onMasterVolumeChanged(pMasterVolume);
        }
    }

    public void add(T pAudioEntity) {
        this.mAudioEntities.add(pAudioEntity);
    }

    public boolean remove(T pAudioEntity) {
        return this.mAudioEntities.remove(pAudioEntity);
    }

    public void releaseAll() {
        ArrayList<T> audioEntities = this.mAudioEntities;
        for (int i = audioEntities.size() - 1; i >= 0; i--) {
            T audioEntity = (IAudioEntity) audioEntities.get(i);
            audioEntity.stop();
            audioEntity.release();
        }
    }
}
