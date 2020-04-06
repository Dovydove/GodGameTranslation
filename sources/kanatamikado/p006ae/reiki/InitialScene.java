package kanatamikado.p006ae.reiki;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.time.TimeConstants;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/* renamed from: kanatamikado.ae.reiki.InitialScene */
public class InitialScene extends KeyListenScene implements OnClickListener {
    private static final float FONT_SIZE = 24.0f;
    private final String APPLI_NO = "2";
    private final int CONFIRM_NG = 13;
    private final int END_OK = 16;
    private final int INITIAL_OK = 2;
    private final int INITIAL_START = 1;
    private final int INITIAL_UPDATE = 11;
    private final int INITIAL_UPDATE_OK = 12;
    private final int MASTER_REV = 162;
    private final int QUEST_RESET = 14;
    private final int QUEST_RESET_OK = 15;
    private final boolean RESET_DISPLAY_CHECK = false;
    /* access modifiers changed from: private */
    public Sound StartPressedSound;
    /* access modifiers changed from: private */
    public Sprite bgImg;
    /* access modifiers changed from: private */
    public Music bgm;
    private int bgmFlg = 1;
    /* access modifiers changed from: private */
    public ButtonSprite buttonInfoNg;
    /* access modifiers changed from: private */
    public ButtonSprite buttonInfoOk;
    /* access modifiers changed from: private */
    public ButtonSprite buttonInfoReset;
    /* access modifiers changed from: private */
    public ButtonSprite buttonStart;
    /* access modifiers changed from: private */
    public Rectangle cartain;
    /* access modifiers changed from: private */
    public boolean confirmFlg = false;
    /* access modifiers changed from: private */
    public boolean dataUpdateFlg = false;
    /* access modifiers changed from: private */

    /* renamed from: db */
    public SQLiteDatabase f213db = this.dbh.getWritableDatabase();
    /* access modifiers changed from: private */
    public int dbRev = 0;
    /* access modifiers changed from: private */
    public Database dbh = new Database(getBaseActivity());
    /* access modifiers changed from: private */
    public Editor editor;
    /* access modifiers changed from: private */
    public Font fontWhite;
    /* access modifiers changed from: private */
    public String httpCheck = "empty";
    /* access modifiers changed from: private */
    public Text infoText;
    /* access modifiers changed from: private */
    public Sprite informationBox;
    /* access modifiers changed from: private */
    public boolean initializeFlg = false;
    private String lastLogin = null;
    /* access modifiers changed from: private */
    public String lastVersion = "0.0.0";
    private int nendAdFlg = 0;
    /* access modifiers changed from: private */
    public String nowDay = null;
    SharedPreferences pre;
    /* access modifiers changed from: private */
    public boolean resetConfirmFlg = false;
    /* access modifiers changed from: private */
    public boolean resetDisplayFlg = false;
    private int soundFlg = 1;
    /* access modifiers changed from: private */
    public Sprite titleSprite;
    /* access modifiers changed from: private */
    public StringBuilder tmpStr = new StringBuilder();
    /* access modifiers changed from: private */
    public String userCode = "0";
    /* access modifiers changed from: private */
    public int userQuestStatus = 0;
    /* access modifiers changed from: private */
    public Sprite waitText;

    public InitialScene(MultiSceneActivity context) {
        super(context);
        init();
    }

    public void init() {
        httpRequest();
        this.pre = getBaseActivity().getSharedPreferences(MainActivity.PREFERRENCES_FILE_NAME, 0);
        this.editor = this.pre.edit();
        this.bgmFlg = this.pre.getInt("bgmFlg", 1);
        this.soundFlg = this.pre.getInt("soundFlg", 1);
        this.dbRev = this.pre.getInt("dbRev", 0);
        bgmChange(false);
        soundChange(false);
        if (this.bgm != null) {
            this.bgm.setLooping(true);
            this.bgm.play();
        }
        this.bgImg = getBaseActivity().getResourceUtil().getSprite("bg/title.png");
        this.bgImg.setPosition(0.0f, 0.0f);
        attachChild(this.bgImg);
        ITexture droidFontTexture = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontWhite = FontFactory.create(getBaseActivity().getFontManager(), droidFontTexture, getBaseActivity().getResourceUtil().getTypeface(), (float) FONT_SIZE, true, Color.rgb(255, 255, 255));
        droidFontTexture.unload();
        this.buttonStart = getBaseActivity().getResourceUtil().getButtonSprite("button/initial_start.png", "button/initial_start_p.png");
        placeToCenterX(this.buttonStart, 600.0f);
        this.buttonStart.setTag(1);
        this.buttonStart.setOnClickListener(this);
        attachChild(this.buttonStart);
        registerTouchArea(this.buttonStart);
        Cursor cursor = this.f213db.rawQuery("SELECT COUNT(*) FROM sqlite_master WHERE type='table' AND name='user_t'", null);
        if (!cursor.moveToFirst()) {
            this.initializeFlg = true;
        } else if (cursor.getInt(0) == 0) {
            this.initializeFlg = true;
        } else {
            cursor = this.f213db.rawQuery("SELECT u.user_quest_status, u.user_sns_flg FROM user_t u", null);
            if (cursor.moveToFirst()) {
                this.userQuestStatus = cursor.getInt(0);
                if (2 <= this.userQuestStatus) {
                    this.buttonInfoReset = getBaseActivity().getResourceUtil().getButtonSprite("button/button_quest_reset.png", "button/button_quest_reset_p.png");
                    this.buttonInfoReset.setPosition(230.0f, 776.0f);
                    this.buttonInfoReset.setTag(14);
                    this.buttonInfoReset.setOnClickListener(this);
                    attachChild(this.buttonInfoReset);
                    registerTouchArea(this.buttonInfoReset);
                    this.resetDisplayFlg = true;
                }
            }
        }
        this.titleSprite = getBaseActivity().getResourceUtil().getSprite("title.png");
        placeToCenterX(this.titleSprite, 130.0f);
        attachChild(this.titleSprite);
        this.cartain = new Rectangle(0.0f, 0.0f, 540.0f, 960.0f, getBaseActivity().getVertexBufferObjectManager());
        this.cartain.setColor(0.0f, 0.0f, 0.0f);
        attachChild(this.cartain);
        registerUpdateHandler(new TimerHandler(1.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                InitialScene.this.cartain.registerEntityModifier(new FadeOutModifier(1.0f));
            }
        }));
        if (cursor != null) {
            cursor.close();
        }
    }

    public void prepareSoundAndMusic() {
        try {
            Log.d("Debug", "prepareSoundAndMusic_music");
            this.bgm = MusicFactory.createMusicFromAsset(getBaseActivity().getMusicManager(), getBaseActivity(), "music/op.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Log.d("Debug", "prepareSoundAndMusic_sound");
            this.StartPressedSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamDecisionStart.ogg");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void bgmChange(boolean forceOff) {
        if (this.bgmFlg != 1 || forceOff) {
            if (this.bgm != null) {
                this.bgm.setVolume(0.0f);
            }
        } else if (this.bgm != null) {
            this.bgm.setVolume(1.0f);
        }
    }

    public void soundChange(boolean forceOff) {
        if (this.soundFlg != 1 || forceOff) {
            this.StartPressedSound.setVolume(0.0f);
        } else {
            this.StartPressedSound.setVolume(1.0f);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
        return false;
    }

    public void onDestroy() {
    }

    public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        this.StartPressedSound.play();
        String str = "";
        switch (pButtonSprite.getTag()) {
            case 1:
                gameStart();
                return;
            case 2:
                gameStart();
                return;
            case 11:
                confirmBoxOpen(12, true, "■プレイ開始前に\n　マスタデータを更新します。\n\n　セーブデータは保持されます。\n　よろしいですか？");
                return;
            case 12:
                this.dataUpdateFlg = true;
                gameStart();
                return;
            case 13:
                this.informationBox.detachSelf();
                this.infoText.detachSelf();
                this.buttonInfoOk.detachSelf();
                unregisterTouchArea(this.buttonInfoOk);
                this.buttonInfoNg.detachSelf();
                unregisterTouchArea(this.buttonInfoNg);
                attachChild(this.buttonStart);
                registerTouchArea(this.buttonStart);
                attachChild(this.buttonInfoReset);
                registerTouchArea(this.buttonInfoReset);
                this.confirmFlg = false;
                return;
            case 14:
                confirmBoxOpen(15, true, "■クエスト挑戦を中止して\n　マイページ画面に戻ります。\n\n　よろしいですか？");
                return;
            case 15:
                this.userQuestStatus = 1;
                this.f213db.execSQL("UPDATE user_t SET user_quest_status=1");
                gameStart();
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: private */
    public void confirmBoxOpen(int tag, boolean ng, String str) {
        String okFile;
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(this.informationBox, 30.0f);
        attachChild(this.informationBox);
        this.fontWhite.load();
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        String str2 = "";
        if (ng) {
            okFile = "information_yes";
        } else {
            okFile = "information_ok";
        }
        this.buttonInfoOk = getBaseActivity().getResourceUtil().getButtonSprite("button/" + okFile + ".png", "button/" + okFile + "_p.png");
        placeToCenterX(this.buttonInfoOk, 700.0f);
        this.buttonInfoOk.setTag(tag);
        this.buttonInfoOk.setOnClickListener(this);
        attachChild(this.buttonInfoOk);
        registerTouchArea(this.buttonInfoOk);
        if (ng) {
            this.buttonInfoNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
            placeToCenterX(this.buttonInfoNg, 800.0f);
            this.buttonInfoNg.setTag(13);
            this.buttonInfoNg.setOnClickListener(this);
            attachChild(this.buttonInfoNg);
            registerTouchArea(this.buttonInfoNg);
            this.resetConfirmFlg = true;
        }
        this.buttonStart.detachSelf();
        unregisterTouchArea(this.buttonStart);
        if (this.resetDisplayFlg) {
            this.buttonInfoReset.detachSelf();
            unregisterTouchArea(this.buttonInfoReset);
        }
        this.confirmFlg = true;
    }

    private void gameStart() {
        String str;
        String sqlR;
        this.bgImg.registerEntityModifier(new FadeOutModifier(0.5f));
        this.titleSprite.registerEntityModifier(new FadeOutModifier(0.5f));
        if (this.confirmFlg) {
            this.buttonInfoOk.detachSelf();
            unregisterTouchArea(this.buttonInfoOk);
            this.infoText.detachSelf();
        } else {
            this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
            placeToCenterX(this.informationBox, 30.0f);
            attachChild(this.informationBox);
            this.buttonStart.detachSelf();
            unregisterTouchArea(this.buttonStart);
        }
        if (this.resetDisplayFlg) {
            this.buttonInfoReset.detachSelf();
            unregisterTouchArea(this.buttonInfoReset);
        }
        if (this.resetConfirmFlg) {
            this.buttonInfoNg.detachSelf();
            unregisterTouchArea(this.buttonInfoReset);
        }
        this.waitText = getBaseActivity().getResourceUtil().getSprite("item/waitText.png");
        this.waitText.setPosition(150.0f, 760.0f);
        attachChild(this.waitText);
        String str2 = "";
        if (this.initializeFlg) {
            str = "ゲームデータ初期化中です。\n数分ほどかかりますので、\nしばらくお待ちください。\n\n\n■「東方玉霊姫」とは\n幻想郷の住人によく似た\n小さな式神「コダマ」と旅をしながら、 \nコダマ遊びで最強を目指すＲＰＧです。\n\n\n■「コダマ遊び」とは\nコダマ同士を１対１で戦わせる勝負です。\n勝ち抜き形式で、相手の全ての\nコダマを倒すと勝利になります。";
        } else {
            if (this.nowDay != null && this.httpCheck.equals("HttpRequestOk")) {
                Cursor cursor = this.f213db.rawQuery("SELECT u.user_money, u.last_login, u.user_login_num, u.user_code FROM user_t u", null);
                if (cursor.moveToFirst()) {
                    int userMoney = cursor.getInt(0);
                    this.lastLogin = cursor.getString(1);
                    int loginNum = cursor.getInt(2);
                    this.userCode = cursor.getString(3);
                    if (!this.nowDay.equals(this.lastLogin) && !this.nowDay.equals("end")) {
                        int loginNum2 = loginNum + 1;
                        String str3 = str2 + "\n\n\n";
                        if (!this.lastLogin.equals("null")) {
                            str3 = (str3 + "[前回ログイン：" + this.lastLogin + "]\n") + "[累計ログイン：" + loginNum2 + "日]\n\n";
                        }
                        int addMoeny = 10000;
                        if (100 <= loginNum2) {
                            addMoeny = TimeConstants.MICROSECONDS_PER_SECOND + ((loginNum2 - 100) * 5000);
                        } else if (2 <= loginNum2) {
                            addMoeny = loginNum2 * 10000;
                        }
                        String str4 = str3 + "■" + this.nowDay + "\nログインボーナス！\n" + addMoeny + "銭と霊酒" + 5 + "個を獲得しました。\n" + String.format("%,d", new Object[]{Integer.valueOf(userMoney)}) + "銭→" + String.format("%,d", new Object[]{Integer.valueOf(userMoney + addMoeny)}) + "銭";
                        int userMoney2 = userMoney + addMoeny;
                        String str5 = "";
                        Cursor cursor2 = this.f213db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=90001", null);
                        if (cursor2.moveToFirst()) {
                            sqlR = "UPDATE user_item_t SET num=" + (cursor2.getInt(0) + 5) + " WHERE item_id=90001;";
                            str2 = str4 + "\n霊酒：" + cursor2.getInt(0) + "→" + (cursor2.getInt(0) + 5);
                        } else {
                            sqlR = "INSERT INTO user_item_t VALUES (90001, " + 5 + ");";
                            str2 = str4 + "\n霊酒：0→" + 5;
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        String sqlT = "empty";
                        if (this.f213db.rawQuery("SELECT u.diff FROM user_progress_t u WHERE u.quest_id=" + 510000, null).moveToFirst()) {
                            sqlT = "DELETE FROM user_progress_t WHERE quest_id=" + 510000;
                        }
                        String sqlH = "empty";
                        if (this.f213db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=500002", null).moveToFirst()) {
                            sqlH = "DELETE FROM user_item_t WHERE item_id=500002";
                        }
                        this.f213db.beginTransaction();
                        try {
                            this.f213db.execSQL(sqlR);
                            this.f213db.execSQL("UPDATE user_t SET user_money=" + userMoney2 + ", last_login='" + this.nowDay + "', user_login_num=" + loginNum2);
                            if (!sqlT.equals("empty")) {
                                this.f213db.execSQL(sqlT);
                            }
                            if (!sqlH.equals("empty")) {
                                this.f213db.execSQL(sqlH);
                            }
                            this.f213db.setTransactionSuccessful();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            this.f213db.endTransaction();
                        }
                        this.editor.putInt("saveNum", 0);
                        this.editor.putInt("loadNum", 0);
                        this.editor.putInt("nendAdFlg", 0);
                        this.editor.commit();
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
            }
            this.tmpStr.setLength(0);
            this.tmpStr.append("■東方玉霊姫 Ver");
            String onText = "";
            PackageInfo packageInfo = null;
            try {
                packageInfo = getBaseActivity().getPackageManager().getPackageInfo(getBaseActivity().getPackageName(), 1);
            } catch (NameNotFoundException e2) {
                e2.printStackTrace();
            }
            if (!this.lastVersion.equals(packageInfo.versionName) && !this.lastVersion.equals("0.0.0")) {
                onText = "\n・最新Ver " + this.lastVersion + "\nアプリがアップデートされました。\nPlayStoreで更新してください。";
            }
            this.tmpStr.append(packageInfo.versionName);
            this.tmpStr.append(onText);
            String str6 = this.tmpStr.toString() + str2;
            this.tmpStr.setLength(0);
            this.tmpStr.append("\n\n\n■Tips\n");
            int pt = new Random().nextInt(11);
            if (pt == 0) {
                this.tmpStr.append("▽戦闘後に自動で回復\n\n");
                this.tmpStr.append("ＨＰとＶＰは、戦闘後に全回復します。\n\n戦闘不能も回復するので、\n後のことは考えなくても大丈夫。");
            } else if (pt == 1) {
                this.tmpStr.append("▽コダマが疲れたら交代\n\n");
                this.tmpStr.append("ＶＰが足りなくなると、\nコダマはスペルを\n使えなくなってしまいます。\n\nＶＰは控えにいると\n徐々に回復していきます。");
            } else if (pt == 2) {
                this.tmpStr.append("▽難易度ボーナス\n\n");
                this.tmpStr.append("高難易度でステージクリアすると、\n「難易度ボーナス」を獲得できます。\n\n難易度ボーナスが\n一定値に達するごとに、\n報酬アイテムをもらえます。");
            } else if (pt == 3) {
                this.tmpStr.append("▽レベル差と獲得経験値\n\n");
                this.tmpStr.append("マップレベルと比べて\nレベルの低いコダマは、\n経験値を多めに獲得できます。\n\n逆にレベルが高いコダマは、\n獲得経験値が少なくなります。");
            } else if (pt == 4) {
                this.tmpStr.append("▽属性一致スペル\n\n");
                this.tmpStr.append("自分が持つ属性と\n同じ属性のスペルを使うと、\nスペル威力が1.5倍になります。");
            } else if (pt == 5) {
                this.tmpStr.append("▽ビールは偉大\n\n");
                this.tmpStr.append("BPを消費して\nステータスボーナスに割り振ると、\nコダマの能力が強化されます。\n\nBPはイージービールなどの\nアイテムで上げることができます。");
            } else if (pt == 6) {
                this.tmpStr.append("▽スペルを覚えさせよう\n\n");
                this.tmpStr.append("コダマのスペル確認画面から\nスペルを習得させることができます。\n\nただし、スペルの習得には\n銭が必要です。");
            } else if (pt == 7) {
                this.tmpStr.append("▽能力変化スペルは強力\n\n");
                this.tmpStr.append("スペルの追加効果による\n能力の増加や減少は、\nバトル終了まで永続します。");
            } else if (pt == 8) {
                this.tmpStr.append("▽難しかったら難易度を下げよう\n\n");
                this.tmpStr.append("「その他」メニューで難易度を下げると、\n敵のレベルが下がります。\n\nどうしても勝てない時には\n難易度を下げてみてください。");
            } else if (pt == 9) {
                this.tmpStr.append("▽コンティニューを活用\n\n");
                this.tmpStr.append("バトルで負けたとしても、\n１回のクエスト挑戦につき\n10回までコンティニューできます。");
            } else if (pt == 10) {
                this.tmpStr.append("▽クエスト/バトルは再開可能\n\n");
                this.tmpStr.append("クエスト挑戦中やバトル中に\nアプリを終了させても、\n同じところから再開できます。");
            } else {
                this.tmpStr.append("▽アプリは削除しないで\n\n");
                this.tmpStr.append("アプリを削除すると、\nセーブデータも消えてしまうので\n注意してください。");
            }
            str = str6 + this.tmpStr.toString();
        }
        this.fontWhite.load();
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        if (this.dbRev == 1) {
            this.initializeFlg = true;
        }
        registerUpdateHandler(new TimerHandler(1.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                String str = "";
                if (InitialScene.this.initializeFlg) {
                    DataSet dbs = new DataSet(InitialScene.this.getBaseActivity());
                    dbs.masterInitialize(InitialScene.this.f213db);
                    dbs.masterInitializeUnitEtc(InitialScene.this.f213db);
                    dbs.masterInitializeLearn(InitialScene.this.f213db);
                    dbs.masterInitializeSpellSkill(InitialScene.this.f213db);
                    dbs.masterInitializeQuest(InitialScene.this.f213db);
                    InitialScene.this.dbh.unitDataSet(InitialScene.this.f213db);
                    InitialScene.this.waitText.detachSelf();
                    InitialScene.this.editor.putInt("dbRev", 162);
                    InitialScene.this.editor.commit();
                } else if (InitialScene.this.dbRev < 162 || InitialScene.this.dataUpdateFlg) {
                    if (2 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 13) {
                        InitialScene.this.protectFlgAdd();
                    }
                    if (2 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 31) {
                        InitialScene.this.etcFlgAdd();
                    }
                    if (2 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 86) {
                        InitialScene.this.maxLvAdd();
                    }
                    if (2 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 91) {
                        InitialScene.this.spellMissAdd(1431, 2700000);
                        InitialScene.this.spellMissAdd(1433, 2700000);
                    }
                    if (2 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 103) {
                        InitialScene.this.boxAdd();
                    }
                    if (2 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 149) {
                        InitialScene.this.spellChange(28, 10117, 11641);
                        InitialScene.this.spellChange(106, 10690, 11642);
                        InitialScene.this.spellChange(155, 10017, 11643);
                        InitialScene.this.spellChange(156, 10017, 11643);
                        InitialScene.this.spellChange(159, 10033, 11647);
                        InitialScene.this.spellChange(159, 10038, 11648);
                        InitialScene.this.spellChange(160, 10033, 11646);
                        InitialScene.this.spellChange(161, 10033, 11645);
                        InitialScene.this.spellChange(165, 10025, 11644);
                        InitialScene.this.spellChange(445, 10776, 11637);
                    }
                    if (2 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 150) {
                        InitialScene.this.spellChange(8, 78, 0);
                        InitialScene.this.spellChange(8, 10078, 0);
                        InitialScene.this.spellChange(9, 78, 0);
                        InitialScene.this.spellChange(9, 10078, 0);
                        InitialScene.this.spellChange(18, 10432, 0);
                        InitialScene.this.spellChange(50, 10155, 10421);
                        InitialScene.this.spellChange(64, 10283, 11655);
                        InitialScene.this.spellChange(135, 10165, 10173);
                        InitialScene.this.spellChange(136, 10165, 10173);
                        InitialScene.this.spellChange(152, 10744, 11652);
                        InitialScene.this.spellChange(176, 10209, 0);
                        InitialScene.this.spellChange(176, 10211, 0);
                        InitialScene.this.spellChange(204, 10818, 11351);
                        InitialScene.this.skillChange(255, 102, 181);
                        InitialScene.this.spellChange(259, 958, 0);
                        InitialScene.this.spellChange(259, 959, 0);
                        InitialScene.this.spellChange(259, 10959, 0);
                        InitialScene.this.spellChange(328, 10522, 11660);
                        InitialScene.this.spellChange(427, 10751, 0);
                        InitialScene.this.spellChange(450, 10595, 10597);
                        InitialScene.this.spellChange(491, 11274, 11273);
                        InitialScene.this.spellChange(497, 10560, 10561);
                    }
                    if (2 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 113) {
                        InitialScene.this.spellCheck20150824();
                    }
                    if (151 <= InitialScene.this.dbRev && InitialScene.this.dbRev <= 152) {
                        InitialScene.this.itemSend20160209();
                    }
                    DataSet dbs2 = new DataSet(InitialScene.this.getBaseActivity());
                    dbs2.masterInitialize(InitialScene.this.f213db);
                    dbs2.masterInitializeUnitEtc(InitialScene.this.f213db);
                    dbs2.masterInitializeLearn(InitialScene.this.f213db);
                    dbs2.masterInitializeSpellSkill(InitialScene.this.f213db);
                    dbs2.masterInitializeQuest(InitialScene.this.f213db);
                    InitialScene.this.waitText.detachSelf();
                    InitialScene.this.editor.putInt("dbRev", 162);
                    InitialScene.this.editor.commit();
                }
                Cursor cursor = InitialScene.this.f213db.rawQuery("SELECT u.data FROM user_battle_t u", null);
                if (!cursor.moveToFirst()) {
                    InitialScene.this.f213db.execSQL("INSERT INTO user_battle_t (user_battle_id, data) VALUES(1, 'NULL')");
                    InitialScene.this.f213db.execSQL("INSERT INTO user_battle_t (user_battle_id, data) VALUES(2, 'NULL')");
                } else {
                    cursor = InitialScene.this.f213db.rawQuery("SELECT u.data FROM user_battle_t u WHERE user_battle_id=2", null);
                    if (cursor.moveToFirst()) {
                        String sql = cursor.getString(0);
                        if (!sql.equals("NULL")) {
                            InitialScene.this.f213db.execSQL(sql);
                        }
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                InitialScene.this.registerUpdateHandler(new TimerHandler(1.0f, new ITimerCallback() {
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        if (InitialScene.this.bgm != null) {
                            InitialScene.this.bgm.stop();
                        }
                        InitialScene.this.free();
                        InitialScene.this.destroy();
                        ResourceUtil.getInstance(InitialScene.this.getBaseActivity()).resetAllTexture();
                    }
                }));
            }
        }));
    }

    /* access modifiers changed from: private */
    public void protectFlgAdd() {
        String str = "";
        this.f213db.beginTransaction();
        try {
            this.f213db.execSQL("ALTER TABLE user_kodama_t ADD protect_flg INTEGER");
            this.f213db.execSQL("UPDATE user_kodama_t SET protect_flg=0");
            this.f213db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.f213db.endTransaction();
        }
    }

    /* access modifiers changed from: private */
    public void etcFlgAdd() {
        String str = "";
        this.f213db.beginTransaction();
        try {
            this.f213db.execSQL("ALTER TABLE user_t ADD challenge_num INTEGER");
            this.f213db.execSQL("UPDATE user_t SET challenge_num=0");
            this.f213db.execSQL("ALTER TABLE user_kodama_t ADD faint_flg INTEGER");
            this.f213db.execSQL("UPDATE user_kodama_t SET faint_flg=0");
            this.f213db.execSQL("CREATE TABLE IF NOT EXISTS user_discharge_t (item_id INTEGER, no INTEGER, card_id INTEGER)");
            Random rnd = new Random();
            for (int i = 30008; i < 30010; i++) {
                for (int s = 0; s < 100; s++) {
                    this.f213db.execSQL("INSERT INTO user_discharge_t VALUES (" + i + ", " + s + ", " + rnd.nextInt(1000) + ");");
                }
            }
            this.f213db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.f213db.endTransaction();
        }
    }

    /* access modifiers changed from: private */
    public void maxLvAdd() {
        String str = "";
        this.f213db.beginTransaction();
        try {
            this.f213db.execSQL("ALTER TABLE user_kodama_t ADD max_lv INTEGER");
            this.f213db.execSQL("UPDATE user_kodama_t SET max_lv=100");
            this.f213db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.f213db.endTransaction();
        }
    }

    /* access modifiers changed from: private */
    public void spellMissAdd(int sno, int adds) {
        int addMoney = 0;
        Cursor cursor = this.f213db.rawQuery("SELECT u.kodama_id, u.spell1, u.spell2, u.spell3, u.spell4 FROM user_kodama_t u WHERE u.kodama_id<>0", null);
        while (cursor.moveToNext()) {
            for (int i = 1; i <= 4; i++) {
                if (cursor.getInt(i) == sno || cursor.getInt(i) == sno + 10000) {
                    addMoney += adds;
                }
            }
        }
        if (addMoney != 0) {
            cursor = this.f213db.rawQuery("SELECT u.user_money FROM user_t u", null);
            if (cursor.moveToFirst()) {
                this.f213db.execSQL("UPDATE user_t SET user_money=" + (cursor.getInt(0) + addMoney));
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    /* access modifiers changed from: private */
    public void boxAdd() {
        int no = 301;
        Cursor cursor = null;
        for (int i = 11; i <= 20; i++) {
            for (int s = 1; s <= 30; s++) {
                cursor = this.f213db.rawQuery("SELECT u.user_kodama_id FROM user_kodama_t u WHERE u.user_kodama_id=" + no, null);
                if (!cursor.moveToFirst()) {
                    this.f213db.execSQL("INSERT INTO user_kodama_t VALUES (" + no + ", " + ((i * 100) + s) + ", 0, \"null\", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100);");
                }
                no++;
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    /* access modifiers changed from: private */
    public void spellCheck20150824() {
        Cursor cursor = this.f213db.rawQuery("SELECT u.user_kodama_id, u.spell1, u.spell2, u.spell3, u.spell4 FROM user_kodama_t u WHERE u.kodama_id=374 AND (u.spell1=1305 OR u.spell2=1305 OR u.spell3=1305 OR u.spell4=1305)", null);
        while (cursor.moveToNext()) {
            String sql = "UPDATE user_kodama_t SET ";
            int i = 1;
            while (true) {
                if (i > 4) {
                    break;
                } else if (cursor.getInt(i) == 1305) {
                    sql = sql + "spell" + i + "=303";
                    break;
                } else {
                    i++;
                }
            }
            this.f213db.execSQL(sql + " WHERE user_kodama_id=" + cursor.getInt(0));
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    /* access modifiers changed from: private */
    public void spellChange(int kodamaId, int oldSpell, int newSpell) {
        int count = 0;
        Cursor cursor = this.f213db.rawQuery("SELECT u.user_kodama_id, u.spell1, u.spell2, u.spell3, u.spell4 FROM user_kodama_t u WHERE u.kodama_id=" + kodamaId + " AND (u.spell1=" + oldSpell + " OR u.spell2=" + oldSpell + " OR u.spell3=" + oldSpell + " OR u.spell4=" + oldSpell + ")", null);
        while (cursor.moveToNext()) {
            String sql = "UPDATE user_kodama_t SET ";
            int i = 1;
            while (true) {
                if (i > 4) {
                    break;
                } else if (cursor.getInt(i) == oldSpell) {
                    sql = sql + "spell" + i + "=" + newSpell;
                    break;
                } else {
                    i++;
                }
            }
            this.f213db.execSQL(sql + " WHERE user_kodama_id=" + cursor.getInt(0));
            if (10000 < oldSpell && newSpell == 0) {
                count++;
            }
        }
        if (1 <= count) {
            cursor = this.f213db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=40001", null);
            if (cursor.moveToFirst()) {
                this.f213db.execSQL("UPDATE user_item_t SET num=" + (cursor.getInt(0) + count) + " WHERE item_id=40001");
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    /* access modifiers changed from: private */
    public void skillChange(int kodamaId, int oldSkill, int newSkill) {
        Cursor cursor = this.f213db.rawQuery("SELECT u.user_kodama_id FROM user_kodama_t u WHERE u.kodama_id=" + kodamaId + " AND u.skill=" + oldSkill, null);
        while (cursor.moveToNext()) {
            this.f213db.execSQL("UPDATE user_kodama_t SET skill=" + newSkill + " WHERE user_kodama_id=" + cursor.getInt(0));
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    /* access modifiers changed from: private */
    public void itemSend20160209() {
        int count = 0;
        Cursor cursor = this.f213db.rawQuery("SELECT u.user_kodama_id FROM user_kodama_t u WHERE u.kodama_id=8 OR u.kodama_id=9 OR u.kodama_id=176 OR u.kodama_id=259 OR u.kodama_id=427", null);
        while (cursor.moveToNext()) {
            count++;
            if (5 <= count) {
                break;
            }
        }
        if (1 <= count) {
            cursor = this.f213db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=40001", null);
            if (cursor.moveToFirst()) {
                this.f213db.execSQL("UPDATE user_item_t SET num=" + (cursor.getInt(0) + count) + " WHERE item_id=40001");
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public void free() {
        setOnSceneTouchListener(null);
        clearEntityModifiers();
    }

    public void destroy() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    if (InitialScene.this.bgm != null) {
                        InitialScene.this.bgm.release();
                        InitialScene.this.bgm = null;
                    }
                    InitialScene.this.tmpStr = null;
                    InitialScene.this.StartPressedSound.release();
                    InitialScene.this.StartPressedSound = null;
                    InitialScene.this.fontWhite.unload();
                    InitialScene.this.fontWhite = null;
                    InitialScene.this.cartain.dispose();
                    InitialScene.this.cartain = null;
                    InitialScene.this.bgImg.dispose();
                    InitialScene.this.bgImg = null;
                    InitialScene.this.informationBox.dispose();
                    InitialScene.this.informationBox = null;
                    InitialScene.this.titleSprite.dispose();
                    InitialScene.this.titleSprite = null;
                    InitialScene.this.buttonStart.dispose();
                    InitialScene.this.buttonStart = null;
                    if (InitialScene.this.confirmFlg) {
                        InitialScene.this.buttonInfoOk.dispose();
                    }
                    InitialScene.this.buttonInfoOk = null;
                    if (InitialScene.this.resetDisplayFlg) {
                        InitialScene.this.buttonInfoReset.dispose();
                    }
                    InitialScene.this.buttonInfoReset = null;
                    if (InitialScene.this.resetConfirmFlg) {
                        InitialScene.this.buttonInfoNg.dispose();
                    }
                    InitialScene.this.buttonInfoNg = null;
                    InitialScene.this.infoText = null;
                    InitialScene.this.getBaseActivity().getSoundManager().releasePool();
                    InitialScene.this.dbh = null;
                    InitialScene.this.f213db = null;
                    if (3 <= InitialScene.this.userQuestStatus) {
                        KeyListenScene scene = new MainScene(InitialScene.this.getBaseActivity());
                        InitialScene.this.getBaseActivity().getEngine().setScene(scene);
                        InitialScene.this.getBaseActivity().appendScene(scene);
                    } else if (InitialScene.this.userQuestStatus == 2) {
                        KeyListenScene scene2 = new QuestScene(InitialScene.this.getBaseActivity());
                        InitialScene.this.getBaseActivity().getEngine().setScene(scene2);
                        InitialScene.this.getBaseActivity().appendScene(scene2);
                    } else {
                        KeyListenScene scene3 = new MenuScene(InitialScene.this.getBaseActivity());
                        InitialScene.this.getBaseActivity().getEngine().setScene(scene3);
                        InitialScene.this.getBaseActivity().appendScene(scene3);
                    }
                }
            });
        } catch (Exception e) {
        }
    }

    private void httpRequest() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    URI url = null;
                    try {
                        url = new URI("http://www.tohofes.com/com/day_mobile.html");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    List<NameValuePair> postParams = new ArrayList<>();
                    postParams.add(new BasicNameValuePair("title", "2"));
                    postParams.add(new BasicNameValuePair("user_id", InitialScene.this.userCode));
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
                                        String[] getCode = EntityUtils.toString(response.getEntity(), "UTF-8").split("_");
                                        if (getCode.length == 3) {
                                            InitialScene.this.httpCheck = getCode[0];
                                            InitialScene.this.nowDay = getCode[1];
                                            InitialScene.this.lastVersion = getCode[2];
                                            if (InitialScene.this.nowDay.equals("END")) {
                                                InitialScene.this.titleSprite.detachSelf();
                                                InitialScene.this.confirmBoxOpen(16, false, "本アプリのデータは\n別端末へ引き継ぎ済みです。");
                                                break;
                                            }
                                        }
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
}
