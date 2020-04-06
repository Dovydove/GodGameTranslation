package kanatamikado.p006ae.reiki;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.util.HorizontalAlign;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/* renamed from: kanatamikado.ae.reiki.DataBackup */
public class DataBackup extends KeyListenScene implements IOnSceneTouchListener, OnClickListener {
    private final String APPLI_NO = "2";
    private final boolean DEBUG_LOG_FLG = false;
    private final float FONT_SIZE = 24.0f;
    private final int LAST_OK = 21;
    private final int PASSWORD_UPDATE = 13;
    private final int SAVE_NG = 12;
    private final int SAVE_OK = 11;
    /* access modifiers changed from: private */
    public Sprite bgImg;
    /* access modifiers changed from: private */
    public Sound buttonCanceledSound;
    /* access modifiers changed from: private */
    public ButtonSprite buttonNg;
    /* access modifiers changed from: private */
    public ButtonSprite buttonOk;
    /* access modifiers changed from: private */
    public Sound buttonPressedSound;
    /* access modifiers changed from: private */
    public ButtonSprite buttonPw;
    /* access modifiers changed from: private */

    /* renamed from: db */
    public SQLiteDatabase f211db = this.dbh.getWritableDatabase();
    private Database dbh = new Database(getBaseActivity());
    ITexture droidFontTexture;
    /* access modifiers changed from: private */
    public Font fontWhite;
    /* access modifiers changed from: private */
    public Text infoText;
    /* access modifiers changed from: private */
    public Sprite informationBox;
    /* access modifiers changed from: private */
    public String newPass = "null";
    /* access modifiers changed from: private */
    public SharedPreferences pre;
    /* access modifiers changed from: private */
    public String saveData = "";
    private int soundFlg = 1;
    /* access modifiers changed from: private */
    public String userCode = "null";
    /* access modifiers changed from: private */
    public String userPass = "null";

    public DataBackup(MultiSceneActivity baseActivity) {
        super(baseActivity);
        init();
    }

    /* JADX INFO: finally extract failed */
    public void init() {
        this.pre = getBaseActivity().getSharedPreferences(MainActivity.PREFERRENCES_FILE_NAME, 0);
        this.soundFlg = this.pre.getInt("soundFlg", 1);
        soundChange(false);
        this.droidFontTexture = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontWhite = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture, getBaseActivity().getResourceUtil().getTypeface(), 24.0f, true, Color.rgb(255, 255, 255));
        this.fontWhite.load();
        this.bgImg = getBaseActivity().getResourceUtil().getSprite("bg/initial.png");
        this.bgImg.setPosition(0.0f, 0.0f);
        attachChild(this.bgImg);
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(this.informationBox, 20.0f);
        attachChild(this.informationBox);
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f211db.rawQuery("SELECT u.user_code, u.user_password FROM user_t u", null);
            if (cursor2.moveToNext()) {
                this.userCode = cursor2.getString(0);
                this.userPass = cursor2.getString(1);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) "\nバックアップデータを生成して\nサーバに保管します。\n保管には通信が必要です。\n\n\nBGMのON/OFFなど一部の環境設定は\nバックアップ対象外です。\n\nまたサーバ負荷軽減のため、\nごく短時間での\n連続バックアップはできませんので\n併せてご注意ください。\n\n\nバックアップデータを生成しますか？", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
            this.buttonOk.setTag(11);
            placeToCenterX(this.buttonOk, 550.0f);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
            placeToCenterX(this.buttonNg, 650.0f);
            this.buttonNg.setTag(12);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
            if (this.userCode.equals("null")) {
                this.buttonPw = getBaseActivity().getResourceUtil().getButtonSprite("button/clear.png", "button/clear.png");
                this.buttonPw.setTag(0);
                this.buttonPw.setPosition(540.0f, 960.0f);
            } else {
                this.buttonPw = getBaseActivity().getResourceUtil().getButtonSprite("button/backup/password.png", "button/backup/password_p.png");
                this.buttonPw.setTag(13);
                placeToCenterX(this.buttonPw, 750.0f);
            }
            this.buttonPw.setOnClickListener(this);
            attachChild(this.buttonPw);
            registerTouchArea(this.buttonPw);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void prepareSoundAndMusic() {
        try {
            this.buttonPressedSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouSystem49.ogg");
            this.buttonCanceledSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouSystem43.ogg");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void soundChange(boolean forceOff) {
        if (this.soundFlg != 1 || forceOff) {
            this.buttonPressedSound.setVolume(0.0f);
            this.buttonCanceledSound.setVolume(0.0f);
            return;
        }
        this.buttonPressedSound.setVolume(1.0f);
        this.buttonCanceledSound.setVolume(1.0f);
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }

    public void onDestroy() {
    }

    public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        String str = "";
        if (pButtonSprite.getTag() == 12) {
            this.buttonPressedSound.play();
            free();
            destroy();
            ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
        } else if (pButtonSprite.getTag() == 11) {
            this.buttonPressedSound.play();
            this.buttonNg.detachSelf();
            unregisterTouchArea(this.buttonNg);
            this.buttonOk.detachSelf();
            unregisterTouchArea(this.buttonOk);
            this.buttonPw.detachSelf();
            unregisterTouchArea(this.buttonPw);
            this.infoText.detachSelf();
            this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) "\nバックアップ中です。\n今しばらくお待ちください。", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            if (this.userCode.equals("null")) {
                userCodeCreate();
            } else {
                dataBackupExe();
            }
        } else if (pButtonSprite.getTag() == 21) {
            free();
            destroy();
            ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
        } else if (pButtonSprite.getTag() == 13) {
            this.buttonPressedSound.play();
            this.buttonNg.detachSelf();
            unregisterTouchArea(this.buttonNg);
            this.buttonOk.detachSelf();
            unregisterTouchArea(this.buttonOk);
            this.buttonPw.detachSelf();
            unregisterTouchArea(this.buttonPw);
            this.infoText.detachSelf();
            this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) "\n通信中です。\n今しばらくお待ちください。", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            passwordUpdate();
        } else {
            this.buttonCanceledSound.play();
        }
    }

    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        return true;
    }

    public void dataBackupExe() {
        boolean startFlg = false;
        StringBuilder tmpStr = new StringBuilder();
        Cursor cursor = this.f211db.rawQuery("SELECT u.user_name, u.user_money, u.user_sp, u.user_max_sp, u.user_sp_restore_time, u.user_icon, u.user_difficult, u.user_last, u.last_login, u.user_sns_flg, u.user_review_flg, u.user_demo_no, u.user_login_num, u.challenge_num FROM user_t u", null);
        while (cursor.moveToNext()) {
            for (int i = 0; i < 14; i++) {
                if (i != 0) {
                    tmpStr.append("_");
                }
                if (i == 0 || i == 8) {
                    tmpStr.append(cursor.getString(i).replaceAll("_", "").replaceAll(":", "").replaceAll("'", "").replaceAll("\"", "").replaceAll("#", ""));
                } else if (i == 4) {
                    tmpStr.append(cursor.getLong(i));
                } else {
                    tmpStr.append(cursor.getInt(i));
                }
            }
        }
        tmpStr.append("#");
        Cursor cursor2 = this.f211db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.name, u.lv, u.exp, u.equip, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, u.skill, u.slv, u.spell1, u.spell2, u.spell3, u.spell4, u.protect_flg, u.faint_flg, u.max_lv FROM user_kodama_t u ORDER BY u.user_kodama_id ASC", null);
        while (cursor2.moveToNext()) {
            if (startFlg) {
                tmpStr.append(":");
            }
            for (int i2 = 0; i2 < 21; i2++) {
                if (i2 != 0) {
                    tmpStr.append("_");
                }
                if (i2 == 3) {
                    tmpStr.append(cursor2.getString(i2).replaceAll("_", "").replaceAll(":", "").replaceAll("'", "").replaceAll("\"", "").replaceAll("#", ""));
                } else {
                    tmpStr.append(cursor2.getInt(i2));
                }
            }
            startFlg = true;
        }
        tmpStr.append("#");
        boolean startFlg2 = false;
        Cursor cursor3 = this.f211db.rawQuery("SELECT u.party_no, u.sort_no, u.user_kodama_id FROM user_party_t u ORDER BY u.party_no ASC, u.sort_no ASC", null);
        while (cursor3.moveToNext()) {
            if (startFlg2) {
                tmpStr.append(":");
            }
            for (int i3 = 0; i3 < 3; i3++) {
                if (i3 != 0) {
                    tmpStr.append("_");
                }
                tmpStr.append(cursor3.getInt(i3));
            }
            startFlg2 = true;
        }
        tmpStr.append("#");
        boolean startFlg3 = false;
        Cursor cursor4 = this.f211db.rawQuery("SELECT u.item_id, u.num FROM user_item_t u ORDER BY u.item_id ASC", null);
        while (cursor4.moveToNext()) {
            if (startFlg3) {
                tmpStr.append(":");
            }
            for (int i4 = 0; i4 < 2; i4++) {
                if (i4 != 0) {
                    tmpStr.append("_");
                }
                tmpStr.append(cursor4.getInt(i4));
            }
            startFlg3 = true;
        }
        tmpStr.append("#");
        boolean startFlg4 = false;
        Cursor cursor5 = this.f211db.rawQuery("SELECT u.quest_id, u.diff FROM user_progress_t u ORDER BY u.quest_id ASC", null);
        while (cursor5.moveToNext()) {
            if (startFlg4) {
                tmpStr.append(":");
            }
            for (int i5 = 0; i5 < 2; i5++) {
                if (i5 != 0) {
                    tmpStr.append("_");
                }
                tmpStr.append(cursor5.getInt(i5));
            }
            startFlg4 = true;
        }
        tmpStr.append("#");
        boolean startFlg5 = false;
        Random rnd = new Random();
        for (int i6 = 30008; i6 < 30010; i6++) {
            for (int s = 0; s < 100; s++) {
                if (startFlg5) {
                    tmpStr.append(":");
                }
                tmpStr.append(i6);
                tmpStr.append("_");
                tmpStr.append(s);
                tmpStr.append("_");
                tmpStr.append(rnd.nextInt(1000));
                startFlg5 = true;
            }
        }
        this.saveData = tmpStr.toString();
        dataBackupHttp();
    }

    public void popAlert(String title, String text) {
        Sprite alertBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(alertBox, 20.0f);
        attachChild(alertBox);
        this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) "■" + title + "\n" + text, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
    }

    public void free() {
        setOnSceneTouchListener(null);
        clearEntityModifiers();
        this.pre = null;
        this.f211db.close();
        this.f211db = null;
        this.dbh.close();
        this.dbh = null;
    }

    public void destroy() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    DataBackup.this.buttonPressedSound.release();
                    DataBackup.this.buttonPressedSound = null;
                    DataBackup.this.buttonCanceledSound.release();
                    DataBackup.this.buttonCanceledSound = null;
                    DataBackup.this.fontWhite.unload();
                    DataBackup.this.fontWhite = null;
                    DataBackup.this.bgImg.dispose();
                    DataBackup.this.bgImg = null;
                    DataBackup.this.informationBox.dispose();
                    DataBackup.this.informationBox = null;
                    DataBackup.this.buttonNg.dispose();
                    DataBackup.this.buttonNg = null;
                    DataBackup.this.buttonOk.dispose();
                    DataBackup.this.buttonOk = null;
                    DataBackup.this.buttonPw.dispose();
                    DataBackup.this.buttonPw = null;
                    DataBackup.this.infoText.dispose();
                    DataBackup.this.infoText = null;
                    DataBackup.this.getBaseActivity().getSoundManager().releasePool();
                    KeyListenScene scene = new MenuScene(DataBackup.this.getBaseActivity());
                    DataBackup.this.getBaseActivity().getEngine().setScene(scene);
                    DataBackup.this.getBaseActivity().appendScene(scene);
                }
            });
        } catch (Exception e) {
        }
    }

    private void userCodeCreate() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    URI url = null;
                    try {
                        url = new URI("http://www.tohofes.com/com/id_issue.html");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    DataBackup.this.userPass = DataBackup.this.userPassCreate();
                    List<NameValuePair> postParams = new ArrayList<>();
                    postParams.add(new BasicNameValuePair("title", "2"));
                    postParams.add(new BasicNameValuePair("user_pw", DataBackup.this.userPass));
                    HttpPost request = new HttpPost(url);
                    try {
                        request.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    try {
                        httpClient.execute(request, new ResponseHandler<String>() {
                            public String handleResponse(HttpResponse response) throws IOException {
                                switch (response.getStatusLine().getStatusCode()) {
                                    case 200:
                                        String[] httpResult = EntityUtils.toString(response.getEntity(), "UTF-8").split("/");
                                        if (!httpResult[0].equals("OK")) {
                                            DataBackup.this.lastText("\nError_userCodeCreate\n\n通信に失敗しました。\nお手数ですが再起動して\n再度操作をお願いします。");
                                            break;
                                        } else {
                                            DataBackup.this.userCode = httpResult[1];
                                            DataBackup.this.f211db.execSQL("UPDATE user_t SET user_code=\"" + DataBackup.this.userCode + "\", user_password=\"" + DataBackup.this.userPass + "\"");
                                            DataBackup.this.dataBackupExe();
                                            break;
                                        }
                                }
                                return null;
                            }
                        });
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    } finally {
                        httpClient.getConnectionManager().shutdown();
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private void dataBackupHttp() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    URI url = null;
                    try {
                        url = new URI("http://www.tohofes.com/com/save_data.html");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    List<NameValuePair> postParams = new ArrayList<>();
                    postParams.add(new BasicNameValuePair("title", "2"));
                    postParams.add(new BasicNameValuePair("user_id", DataBackup.this.userCode));
                    postParams.add(new BasicNameValuePair("user_pw", DataBackup.this.userPass));
                    postParams.add(new BasicNameValuePair("data", DataBackup.this.saveData));
                    HttpPost request = new HttpPost(url);
                    try {
                        request.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    try {
                        httpClient.execute(request, new ResponseHandler<String>() {
                            public String handleResponse(HttpResponse response) throws IOException {
                                switch (response.getStatusLine().getStatusCode()) {
                                    case 200:
                                        if (!EntityUtils.toString(response.getEntity(), "UTF-8").equals("OK")) {
                                            DataBackup.this.lastText("\nError_dataBackupHttp_1\n\nデータの保管に失敗しました。\nお手数ですが再操作をお願いします。");
                                            break;
                                        } else {
                                            DataBackup.this.lastText("\nバックアップデータを更新しました。\n\nID：" + DataBackup.this.userCode + "\nパスワード：" + DataBackup.this.userPass + "\n\nIDとパスワードは\n必ずメモしておいて下さい。\n\nなおIDおよびパスワード紛失には\n対応致しかねますのでご注意ください。");
                                            Calendar cal = Calendar.getInstance();
                                            Editor editor = DataBackup.this.pre.edit();
                                            editor.putInt("backupDateY", cal.get(1));
                                            editor.putInt("backupDateM", cal.get(2) + 1);
                                            editor.putInt("backupDateD", cal.get(5));
                                            editor.commit();
                                            break;
                                        }
                                    case 404:
                                        DataBackup.this.lastText("\nError_dataBackupHttp_2\n\nデータの保管に失敗しました。\nお手数ですが再操作をお願いします。");
                                        break;
                                }
                                return null;
                            }
                        });
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    } finally {
                        httpClient.getConnectionManager().shutdown();
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private void passwordUpdate() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    URI url = null;
                    try {
                        url = new URI("http://www.tohofes.com/com/pass_update.html");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    DataBackup.this.newPass = DataBackup.this.userPassCreate();
                    List<NameValuePair> postParams = new ArrayList<>();
                    postParams.add(new BasicNameValuePair("title", "2"));
                    postParams.add(new BasicNameValuePair("user_id", DataBackup.this.userCode));
                    postParams.add(new BasicNameValuePair("user_pw", DataBackup.this.userPass));
                    postParams.add(new BasicNameValuePair("data", DataBackup.this.newPass));
                    HttpPost request = new HttpPost(url);
                    try {
                        request.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));
                    } catch (UnsupportedEncodingException e2) {
                        e2.printStackTrace();
                    }
                    DefaultHttpClient httpClient = new DefaultHttpClient();
                    try {
                        httpClient.execute(request, new ResponseHandler<String>() {
                            public String handleResponse(HttpResponse response) throws IOException {
                                switch (response.getStatusLine().getStatusCode()) {
                                    case 200:
                                        if (!EntityUtils.toString(response.getEntity(), "UTF-8").equals("OK")) {
                                            DataBackup.this.lastText("\nError_dataBackupHttp_1\n\nパスワードの更新に失敗しました。\nお手数ですが再操作をお願いします。");
                                            break;
                                        } else {
                                            DataBackup.this.lastText("\nパスワードを更新しました。\n\nID：" + DataBackup.this.userCode + "\nパスワード：" + DataBackup.this.newPass + "\n\nIDとパスワードは\n必ずメモしておいて下さい。\n\nなおIDおよびパスワード紛失には\n対応致しかねますのでご注意ください。");
                                            DataBackup.this.f211db.execSQL("UPDATE user_t SET user_password=\"" + DataBackup.this.newPass + "\"");
                                            break;
                                        }
                                    case 404:
                                        DataBackup.this.lastText("\nError_dataBackupHttp_2\n\nパスワードの更新に失敗しました。\nお手数ですが再操作をお願いします。");
                                        break;
                                }
                                return null;
                            }
                        });
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    } finally {
                        httpClient.getConnectionManager().shutdown();
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: private */
    public void lastText(String str) {
        this.infoText.detachSelf();
        this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ok.png", "button/information_ok_p.png");
        placeToCenterX(this.buttonOk, 650.0f);
        this.buttonOk.setTag(21);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
    }

    /* access modifiers changed from: private */
    public String userPassCreate() {
        String str = "abcdefghijkmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        StringBuilder buf = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            buf.append("abcdefghijkmnopqrstuvwxyz0123456789".charAt(rnd.nextInt("abcdefghijkmnopqrstuvwxyz0123456789".length())));
        }
        return buf.toString();
    }
}
