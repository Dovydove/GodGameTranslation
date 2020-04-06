package kanatamikado.p006ae.reiki;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.KeyEvent;
import android.widget.Toast;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
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
import org.andengine.p028ui.dialog.StringInputDialogBuilder;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.call.Callback;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/* renamed from: kanatamikado.ae.reiki.DataRestore */
public class DataRestore extends KeyListenScene implements IOnSceneTouchListener, OnClickListener {
    private final String APPLI_NO = "2";
    private final boolean DEBUG_LOG_FLG = false;
    private final float FONT_SIZE = 24.0f;
    private final int LOAD_NG = 12;
    private final int LOAD_OK = 11;
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

    /* renamed from: db */
    private SQLiteDatabase f212db = this.dbh.getWritableDatabase();
    private Database dbh = new Database(getBaseActivity());
    ITexture droidFontTexture;
    /* access modifiers changed from: private */
    public Font fontWhite;
    /* access modifiers changed from: private */
    public Text infoText;
    /* access modifiers changed from: private */
    public Sprite informationBox;
    /* access modifiers changed from: private */
    public String[] loadData;
    private String orgCode = "null";
    private SharedPreferences pre;
    private int soundFlg = 1;
    /* access modifiers changed from: private */
    public String userCode = "null";
    /* access modifiers changed from: private */
    public String userPass = "null";

    public DataRestore(MultiSceneActivity baseActivity) {
        super(baseActivity);
        init();
    }

    /* JADX INFO: finally extract failed */
    public void init() {
        this.pre = getBaseActivity().getSharedPreferences(MainActivity.PREFERRENCES_FILE_NAME, 0);
        this.soundFlg = this.pre.getInt("soundFlg", 1);
        soundChange(false);
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f212db.rawQuery("SELECT u.user_code, u.user_password FROM user_t u", null);
            if (cursor2.moveToFirst()) {
                this.orgCode = cursor2.getString(0);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            this.droidFontTexture = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
            this.fontWhite = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture, getBaseActivity().getResourceUtil().getTypeface(), 24.0f, true, Color.rgb(255, 255, 255));
            this.fontWhite.load();
            this.bgImg = getBaseActivity().getResourceUtil().getSprite("bg/initial.png");
            this.bgImg.setPosition(0.0f, 0.0f);
            attachChild(this.bgImg);
            this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
            placeToCenterX(this.informationBox, 20.0f);
            attachChild(this.informationBox);
            this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) "\nサーバに保管されている\nバックアップデータをロードします。\nデータロードには通信が必要です。\n\nデータをロードすると、\n現在のデータは上書きされます。\n\nよろしいですか？\n\n※サーバ負荷対策などのため、\n短期間での連続ロードを\n制限させて頂いております。\nご注意ください。", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
            placeToCenterX(this.buttonOk, 650.0f);
            this.buttonOk.setTag(11);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
            placeToCenterX(this.buttonNg, 750.0f);
            this.buttonNg.setTag(12);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
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
            this.infoText.detachSelf();
            this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) "\n引き継ぎ用データロード中です。\n今しばらくお待ちください。", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            idInputDialogBuilder();
        } else if (pButtonSprite.getTag() == 2) {
            ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
            getBaseActivity().finish();
        }
    }

    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        return true;
    }

    public void DataRestoreExe() {
        String str = "";
        boolean error = false;
        StringBuilder tmpStr = new StringBuilder();
        this.f212db.beginTransaction();
        try {
            this.dbh.userDataDrop(this.f212db);
            this.dbh.userDataCreate(this.f212db);
            String[] partData = this.loadData[1].split("_");
            tmpStr.append("INSERT INTO user_t VALUES (1");
            tmpStr.append(", \"");
            tmpStr.append(this.userCode);
            tmpStr.append("\"");
            tmpStr.append(", \"");
            tmpStr.append(this.userPass);
            tmpStr.append("\"");
            tmpStr.append(", \"");
            tmpStr.append(partData[0]);
            tmpStr.append("\"");
            for (int i = 1; i <= 6; i++) {
                tmpStr.append(", ");
                tmpStr.append(partData[i]);
            }
            tmpStr.append(", 0, 1, 0, ");
            tmpStr.append(partData[7]);
            tmpStr.append(", \"");
            tmpStr.append(partData[8]);
            tmpStr.append("\"");
            for (int i2 = 9; i2 <= 12; i2++) {
                tmpStr.append(", ");
                tmpStr.append(partData[i2]);
            }
            tmpStr.append(", ");
            tmpStr.append(System.currentTimeMillis());
            tmpStr.append(", ");
            tmpStr.append(partData[13]);
            tmpStr.append(");");
            this.f212db.execSQL(tmpStr.toString());
            String[] wrapData = this.loadData[2].split(":");
            for (String split : wrapData) {
                tmpStr.setLength(0);
                String[] partData2 = split.split("_");
                tmpStr.append("INSERT INTO user_kodama_t VALUES (");
                tmpStr.append(partData2[0]);
                for (int s = 1; s <= 2; s++) {
                    tmpStr.append(", ");
                    tmpStr.append(partData2[s]);
                }
                tmpStr.append(", \"");
                tmpStr.append(partData2[3]);
                tmpStr.append("\"");
                for (int s2 = 4; s2 <= 17; s2++) {
                    tmpStr.append(", ");
                    tmpStr.append(partData2[s2]);
                }
                tmpStr.append(", ");
                tmpStr.append(System.currentTimeMillis());
                tmpStr.append(", ");
                tmpStr.append(partData2[18]);
                tmpStr.append(", ");
                tmpStr.append(partData2[19]);
                tmpStr.append(", ");
                tmpStr.append(partData2[20]);
                tmpStr.append(");");
                this.f212db.execSQL(tmpStr.toString());
            }
            String[] wrapData2 = this.loadData[3].split(":");
            for (String split2 : wrapData2) {
                String[] partData3 = split2.split("_");
                this.f212db.execSQL("INSERT INTO user_party_t VALUES (" + partData3[0] + ", " + partData3[1] + ", " + partData3[2] + ");");
            }
            String[] wrapData3 = this.loadData[4].split(":");
            for (String split3 : wrapData3) {
                String[] partData4 = split3.split("_");
                this.f212db.execSQL("INSERT INTO user_item_t VALUES (" + partData4[0] + ", " + partData4[1] + ");");
            }
            String[] wrapData4 = this.loadData[5].split(":");
            for (String split4 : wrapData4) {
                String[] partData5 = split4.split("_");
                this.f212db.execSQL("INSERT INTO user_progress_t VALUES (" + partData5[0] + ", " + partData5[1] + ");");
            }
            this.f212db.setTransactionSuccessful();
        } catch (SQLException e) {
            error = true;
            e.printStackTrace();
        } finally {
            this.f212db.endTransaction();
        }
        if (error) {
            lastText("\nError_DataRestoreExe\n\nデータロードに失敗しました。\nお手数ですが、\n再操作をお願いします。");
            return;
        }
        lastText("\nデータロードが完了しました。\nアプリを再起動してください。");
        Editor editor = this.pre.edit();
        editor.putInt("areaSelect", 1);
        editor.commit();
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
        this.f212db.close();
        this.f212db = null;
        this.dbh.close();
        this.dbh = null;
    }

    public void destroy() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    DataRestore.this.buttonPressedSound.release();
                    DataRestore.this.buttonPressedSound = null;
                    DataRestore.this.buttonCanceledSound.release();
                    DataRestore.this.buttonCanceledSound = null;
                    DataRestore.this.fontWhite.unload();
                    DataRestore.this.fontWhite = null;
                    DataRestore.this.bgImg.dispose();
                    DataRestore.this.bgImg = null;
                    DataRestore.this.informationBox.dispose();
                    DataRestore.this.informationBox = null;
                    DataRestore.this.buttonNg.dispose();
                    DataRestore.this.buttonNg = null;
                    DataRestore.this.buttonOk.dispose();
                    DataRestore.this.buttonOk = null;
                    DataRestore.this.infoText.dispose();
                    DataRestore.this.infoText = null;
                    DataRestore.this.getBaseActivity().getSoundManager().releasePool();
                    KeyListenScene scene = new MenuScene(DataRestore.this.getBaseActivity());
                    DataRestore.this.getBaseActivity().getEngine().setScene(scene);
                    DataRestore.this.getBaseActivity().appendScene(scene);
                }
            });
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: private */
    public void DataRestoreHttp() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    URI url = null;
                    try {
                        url = new URI("http://www.tohofes.com/com/load_data.html");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    List<NameValuePair> postParams = new ArrayList<>();
                    postParams.add(new BasicNameValuePair("title", "2"));
                    postParams.add(new BasicNameValuePair("user_id", DataRestore.this.userCode));
                    postParams.add(new BasicNameValuePair("user_pw", DataRestore.this.userPass));
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
                                        DataRestore.this.loadData = EntityUtils.toString(response.getEntity(), "UTF-8").split("#");
                                        if (!DataRestore.this.loadData[0].equals("END")) {
                                            if (!DataRestore.this.loadData[0].equals("OK")) {
                                                if (3 > Integer.parseInt(DataRestore.this.loadData[1])) {
                                                    DataRestore.this.lastText("\nError_DataRestoreHttp\n\nデータロードに失敗しました。\nIDおよびパスワードをご確認ください。\n\nお手数ですが再起動して\n再度操作をお願いします。");
                                                    break;
                                                } else {
                                                    DataRestore.this.lastText("\nError_DataRestoreHttp\n\n連続ロード制限により\nデータロードに失敗しました。\n\n前回ロードから1日ほど\n間を空けて頂けますよう\nお願い致します。");
                                                    break;
                                                }
                                            } else {
                                                DataRestore.this.DataRestoreExe();
                                                break;
                                            }
                                        } else {
                                            DataRestore.this.lastText("\nデータロードに失敗しました。\n\nこのバックアップデータは\n別端末へ以降済みです。");
                                            break;
                                        }
                                    case 404:
                                        DataRestore.this.lastText("\nError_DataRestoreHttp\n\n通信に失敗しました。\n\nお手数ですが再起動して\n再度操作をお願いします。");
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
        this.buttonOk.setTag(2);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
    }

    private void idInputDialogBuilder() {
        getBaseActivity().runOnUiThread(new Runnable() {
            public void run() {
                new StringInputDialogBuilder(DataRestore.this.getBaseActivity(), C0436R.string.id_regist_title, C0436R.string.id_regist_detail, C0436R.string.id_regist_error, C0436R.C0438drawable.ic_launcher, new Callback<String>() {
                    public void onCallback(String pCallbackValue) {
                        DataRestore.this.userCode = pCallbackValue;
                        DataRestore.this.pwInputDialogBuilder();
                    }
                }, new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(DataRestore.this.getBaseActivity(), "\n入力をキャンセルしました。", 0).show();
                        DataRestore.this.lastText("\nデータロードに失敗しました。\nお手数ですが再起動して\n再度操作をお願いします。");
                    }
                }).create().show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void pwInputDialogBuilder() {
        getBaseActivity().runOnUiThread(new Runnable() {
            public void run() {
                new StringInputDialogBuilder(DataRestore.this.getBaseActivity(), C0436R.string.id_regist_title, C0436R.string.pw_regist_detail, C0436R.string.id_regist_error, C0436R.C0438drawable.ic_launcher, new Callback<String>() {
                    public void onCallback(String pCallbackValue) {
                        DataRestore.this.userPass = pCallbackValue;
                        DataRestore.this.DataRestoreHttp();
                    }
                }, new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(DataRestore.this.getBaseActivity(), "\n入力をキャンセルしました。", 0).show();
                        DataRestore.this.lastText("\nデータロードに失敗しました。\nお手数ですが再起動して\n再度操作をお願いします。");
                    }
                }).create().show();
            }
        });
    }
}
