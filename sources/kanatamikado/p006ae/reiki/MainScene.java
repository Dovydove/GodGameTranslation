package kanatamikado.p006ae.reiki;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.p000v4.app.NotificationManagerCompat;
import android.view.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.FadeInModifier;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.MoveByModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.modifier.ParallelEntityModifier;
import org.andengine.entity.modifier.ScaleModifier;
import org.andengine.entity.modifier.SequenceEntityModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.ITouchArea;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.ButtonSprite;
import org.andengine.entity.sprite.ButtonSprite.OnClickListener;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.entity.text.TextOptions;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.font.BitmapFont;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.font.IFont;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.util.HorizontalAlign;
import org.andengine.util.modifier.ease.EaseBounceOut;
import org.andengine.util.modifier.ease.IEaseFunction;

/* renamed from: kanatamikado.ae.reiki.MainScene */
public class MainScene extends KeyListenScene implements IOnSceneTouchListener, OnClickListener {
    private static final float FONT_SIZE = 24.0f;
    private static final float LARGE_FONT_SIZE = 60.0f;
    private final int BATTLE_AREA_HEIGHT = 476;
    private final int BATTLE_MODE_BEING = 21;
    private final int BATTLE_MODE_INITIAL1 = 1;
    private final int BATTLE_MODE_INITIAL2 = 2;
    private final int BATTLE_MODE_MAIN = 11;
    private final int BOTTOM_MODE_ENEMY = 104;
    private final int BOTTOM_MODE_INITIAL = 100;
    private final int BOTTOM_MODE_SPELL = 101;
    private final int BOTTOM_MODE_TABLE = 103;
    private final int BOTTOM_MODE_UNIT = 102;
    private final int END_TAG = 99999996;
    private final int ENEMY_CHECK_TAG = 99999995;
    private final int GAME_END_NG = 99999997;
    private final int GAME_END_OK = 99999998;
    private final int HEADLINE_Y = 20;
    private final int ITEM_LIMIT = 999999;
    private final int LOG_TAG = 99999991;
    private final int NG_TAG = 99999999;
    private final int RETIRE_NG = 99999992;
    private final int RETIRE_OK = 99999993;
    private final int RETIRE_TAG = 99999994;
    private final int TUTORIAL_TAG = 99999990;
    private final int UNIT_NUM = 6;
    private final int VP_LIMIT = 100;
    /* access modifiers changed from: private */

    /* renamed from: a */
    public int f214a = 0;
    private int areaId = 1;
    /* access modifiers changed from: private */
    public boolean arrowFlg = false;
    /* access modifiers changed from: private */
    public AnimatedSprite arrowR;
    /* access modifiers changed from: private */
    public Sprite attackBullet;
    /* access modifiers changed from: private */
    public Sound attackSound;
    /* access modifiers changed from: private */
    public boolean attrDisplayFlg = false;
    /* access modifiers changed from: private */
    public Sprite attrTable;
    private boolean attrTableOpenFlg = false;
    /* access modifiers changed from: private */
    public String[] attrs = {"―", "無", "樹", "闘", "毒", "地", "風", "虫", "岩", "鋼", "霊", "水", "雷", "氷", "理", "炎", "神", "闇", "然", "―", "―"};
    /* access modifiers changed from: private */

    /* renamed from: b */
    public int f215b = 0;
    /* access modifiers changed from: private */
    public int battleMode = 1;
    /* access modifiers changed from: private */
    public Text battleText;
    /* access modifiers changed from: private */
    public Sprite bgBattleText;
    /* access modifiers changed from: private */
    public boolean bgBattleTextFlg = false;
    /* access modifiers changed from: private */
    public Sprite bgImg;
    /* access modifiers changed from: private */
    public Sprite bgInitial;
    /* access modifiers changed from: private */
    public Music bgm;
    private int bgmBossNo = 1;
    private int bgmFlg = 1;
    private int bgmMapNo = 1;
    /* access modifiers changed from: private */
    public BitmapFont bitmapFont;
    /* access modifiers changed from: private */
    public Sound bossAppeal;
    /* access modifiers changed from: private */
    public int bottomMode = 100;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> boxBgList;
    /* access modifiers changed from: private */
    public int[][] btfl = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 10}));
    /* access modifiers changed from: private */
    public ButtonSprite buttonCons;
    private ButtonSprite buttonLog;
    /* access modifiers changed from: private */
    public ButtonSprite buttonNg;
    /* access modifiers changed from: private */
    public ButtonSprite buttonOk;
    /* access modifiers changed from: private */
    public Sound cancellSound;
    /* access modifiers changed from: private */
    public Rectangle cartain;
    /* access modifiers changed from: private */
    public Rectangle cartainBottom;
    /* access modifiers changed from: private */
    public String[][] className = ((String[][]) Array.newInstance(String.class, new int[]{2, 6}));
    private boolean consecutiveFlg = false;
    /* access modifiers changed from: private */
    public float damage = 0.0f;
    private boolean damageReceiveFlg = false;
    /* access modifiers changed from: private */
    public Text[] damageText = new Text[2];
    /* access modifiers changed from: private */

    /* renamed from: db */
    public SQLiteDatabase f216db = this.dbh.getWritableDatabase();
    /* access modifiers changed from: private */
    public Database dbh = new Database(getBaseActivity());
    /* access modifiers changed from: private */
    public Sound destroySound;
    /* access modifiers changed from: private */
    public boolean dispBallFlg = false;
    /* access modifiers changed from: private */
    public Sprite dispBallImg;
    /* access modifiers changed from: private */
    public boolean dispBallImgFlg = false;
    /* access modifiers changed from: private */
    public int dispBallImgRotation = 0;
    /* access modifiers changed from: private */
    public int dispUnit = 0;
    ITexture droidFontTexture1;
    ITexture droidFontTexture2;
    ITexture droidFontTexture3;
    /* access modifiers changed from: private */
    public Sound dropEraseSound;
    private Editor editor;
    /* access modifiers changed from: private */

    /* renamed from: ef */
    public int f217ef = 0;
    /* access modifiers changed from: private */
    public Sprite enAttrImg1;
    /* access modifiers changed from: private */
    public Sprite enAttrImg2;
    /* access modifiers changed from: private */
    public Sprite enImg;
    /* access modifiers changed from: private */
    public Text enStText;
    /* access modifiers changed from: private */
    public Sprite enVarImg;
    /* access modifiers changed from: private */
    public Sprite endBox;
    private boolean endConfirmFlg = false;
    /* access modifiers changed from: private */
    public boolean endDisplayFlg = false;
    /* access modifiers changed from: private */
    public ButtonSprite endNg;
    /* access modifiers changed from: private */
    public ButtonSprite endOk;
    private boolean endStartFlg = false;
    /* access modifiers changed from: private */
    public Text endText;
    private boolean enemyDetailOpenFlg = false;
    private boolean enemySpellOpenFlg = false;
    /* access modifiers changed from: private */
    public int[][] espd = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{4, 5}));
    private int exProgressId = 500001;
    /* access modifiers changed from: private */
    public Sound extendSound;
    /* access modifiers changed from: private */
    public Sound faintSound;
    /* access modifiers changed from: private */
    public ArrayList<AnimatedSprite> fireworks;
    /* access modifiers changed from: private */
    public boolean fireworksFlg = false;
    /* access modifiers changed from: private */
    public Font fontBlack;
    /* access modifiers changed from: private */
    public Font fontWhite;
    /* access modifiers changed from: private */
    public Font fontYellow;
    TimerHandler handlerBattleUnitChange;
    TimerHandler handlerClearEffect;
    TimerHandler handlerDestroyEffect1;
    TimerHandler handlerEnd;
    TimerHandler handlerFireworks1;
    TimerHandler handlerFireworks2;
    TimerHandler handlerInit1;
    TimerHandler handlerInit2;
    TimerHandler handlerSendOut1;
    TimerHandler handlerSendOut2;
    TimerHandler handlerSendOut3;
    TimerHandler handlerSpell1;
    TimerHandler handlerSpell2;
    TimerHandler handlerSpell3;
    TimerHandler handlerSpell4;
    TimerHandler handlerSpell5;
    TimerHandler handlerSpell6;
    TimerHandler handlerTurnEnd1;
    TimerHandler handlerTurnEnd2;
    TimerHandler handlerUnitDisp1;
    TimerHandler handlerUnitDisp2;
    /* access modifiers changed from: private */
    public int[] ind = new int[2];
    private Sprite infoImg;
    /* access modifiers changed from: private */
    public Text infoText;
    /* access modifiers changed from: private */
    public Sprite informationBox;
    private boolean logOpenFlg = false;
    /* access modifiers changed from: private */
    public Sound mapClear;
    private int mapLv = 1;
    /* access modifiers changed from: private */
    public Sound mapMove;
    /* access modifiers changed from: private */
    public ArrayList<ButtonSprite> menuBottomButton;
    private Sprite messageBox;
    private boolean messageBoxFlg = false;
    private String messageLog = "";
    private String messageLog2 = "";
    private boolean messageLogFlg = false;
    /* access modifiers changed from: private */
    public String messageStr = "";
    /* access modifiers changed from: private */
    public Text messageText;
    private int nextUs = 0;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public int f218o = 0;
    /* access modifiers changed from: private */
    public int[] ord = new int[2];
    /* access modifiers changed from: private */
    public int[][][] orgSt = ((int[][][]) Array.newInstance(Integer.TYPE, new int[]{2, 6, 3}));
    private int partyNo = 1;
    /* access modifiers changed from: private */
    public SharedPreferences pre;
    private int preDamage = 0;
    /* access modifiers changed from: private */
    public Sound pressSound;
    private int prizeNo = 0;
    /* access modifiers changed from: private */
    public int questId = 0;
    private boolean retireConfirmFlg = false;
    /* access modifiers changed from: private */
    public Random rnd = new Random();
    /* access modifiers changed from: private */
    public Sound shotSound;
    /* access modifiers changed from: private */
    public int[] skc = new int[2];
    private int soundFlg = 1;
    /* access modifiers changed from: private */
    public int[][] spd = ((int[][]) Array.newInstance(Integer.TYPE, new int[]{2, 5}));
    /* access modifiers changed from: private */
    public AnimatedSprite spellEffect1;
    /* access modifiers changed from: private */
    public AnimatedSprite spellEffect2;
    /* access modifiers changed from: private */
    public boolean spellEffectFlg1 = false;
    /* access modifiers changed from: private */
    public boolean spellEffectFlg2 = false;
    /* access modifiers changed from: private */
    public Sound spellHealSound;
    /* access modifiers changed from: private */
    public Sound spellSound11;
    /* access modifiers changed from: private */
    public Sound spellSound12;
    /* access modifiers changed from: private */
    public Sound spellSound13;
    /* access modifiers changed from: private */
    public Sound spellSound14;
    /* access modifiers changed from: private */
    public Sound spellSound15;
    /* access modifiers changed from: private */
    public Sound spellSound16;
    /* access modifiers changed from: private */
    public Sound spellSound18;
    /* access modifiers changed from: private */
    public Sound spellSound19;
    /* access modifiers changed from: private */
    public Sound spellSound25;
    /* access modifiers changed from: private */
    public Sound spellSound27;
    /* access modifiers changed from: private */
    public Sound spellSound29;
    /* access modifiers changed from: private */
    public Sound spellSound30;
    /* access modifiers changed from: private */
    public Sound spellUseSound;
    /* access modifiers changed from: private */
    public int[] spl = new int[2];
    /* access modifiers changed from: private */
    public String[] spn = new String[2];
    /* access modifiers changed from: private */
    public Sound stageClear;
    private int stageId = 1;
    /* access modifiers changed from: private */
    public Sound statusDownSound;
    /* access modifiers changed from: private */
    public Sound statusUpSound;
    /* access modifiers changed from: private */
    public Sprite unAttrImg1;
    /* access modifiers changed from: private */
    public Sprite unAttrImg2;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> unBallImg;
    /* access modifiers changed from: private */
    public Sprite unImg;
    /* access modifiers changed from: private */
    public Text unStText;
    /* access modifiers changed from: private */
    public Rectangle[] unVar = new Rectangle[4];
    /* access modifiers changed from: private */
    public Sprite unVarImg;
    /* access modifiers changed from: private */
    public Sound unitAppearSound;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> unitBoxAttr1;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> unitBoxAttr2;
    /* access modifiers changed from: private */
    public ArrayList<ButtonSprite> unitBoxBt1;
    /* access modifiers changed from: private */
    public ArrayList<ButtonSprite> unitBoxBt2;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> unitBoxImg;
    /* access modifiers changed from: private */
    public Text[] unitBoxText = new Text[8];
    private boolean unitChangeFlg = false;
    /* access modifiers changed from: private */
    public AnimatedSprite unitChargeImg;
    /* access modifiers changed from: private */
    public boolean unitContinueFlg = false;
    private boolean unitDetailOpenFlg = false;
    /* access modifiers changed from: private */
    public String[][] unitName = ((String[][]) Array.newInstance(String.class, new int[]{2, 6}));
    private boolean unitSpellOpenFlg = false;
    /* access modifiers changed from: private */
    public int[][][] unitSt = ((int[][][]) Array.newInstance(Integer.TYPE, new int[]{2, 6, 18}));
    /* access modifiers changed from: private */
    public TimerHandler updateHandler = new TimerHandler(0.016666668f, true, new ITimerCallback() {
        public void onTimePassed(TimerHandler pTimerHandler) {
            if (MainScene.this.dispBallFlg) {
                MainScene.this.dispBallImgRotation = MainScene.this.dispBallImgRotation + 60;
                if (360 <= MainScene.this.dispBallImgRotation) {
                    MainScene.this.dispBallImgRotation = 0;
                }
                MainScene.this.dispBallImg.setRotation((float) MainScene.this.dispBallImgRotation);
            } else if (MainScene.this.userDemoEffectFlg) {
                if (MainScene.this.userDemoEffectUp) {
                    MainScene.this.userDemoEffect = MainScene.this.userDemoEffect + 2.0f;
                    if (MainScene.LARGE_FONT_SIZE < MainScene.this.userDemoEffect) {
                        MainScene.this.userDemoEffect = MainScene.LARGE_FONT_SIZE;
                        MainScene.this.userDemoEffectUp = false;
                    }
                } else {
                    MainScene.this.userDemoEffect = MainScene.this.userDemoEffect - 2.0f;
                    if (MainScene.this.userDemoEffect < 0.0f) {
                        MainScene.this.userDemoEffect = 0.0f;
                        MainScene.this.userDemoEffectUp = true;
                    }
                }
                MainScene.this.dispBallImg.setAlpha(MainScene.this.userDemoEffect / MainScene.LARGE_FONT_SIZE);
            }
        }
    });
    /* access modifiers changed from: private */

    /* renamed from: us */
    public int[] f219us = new int[2];
    /* access modifiers changed from: private */
    public float userDemoEffect = LARGE_FONT_SIZE;
    /* access modifiers changed from: private */
    public boolean userDemoEffectFlg = false;
    /* access modifiers changed from: private */
    public boolean userDemoEffectUp = false;
    /* access modifiers changed from: private */
    public int userDemoNo = 0;
    private int userDifficult;
    /* access modifiers changed from: private */
    public int[] userIcon = new int[2];
    /* access modifiers changed from: private */
    public int[] userKodamaId = new int[6];
    private int userLast = 0;
    /* access modifiers changed from: private */
    public String[] userName = new String[2];
    private int userQuestBattle = 0;
    /* access modifiers changed from: private */
    public int userQuestStatus = 1;
    /* access modifiers changed from: private */
    public boolean waitFlg = false;

    public MainScene(MultiSceneActivity baseActivity) {
        super(baseActivity);
        init();
    }

    public void init() {
        int[] sb = new int[4];
        this.pre = getBaseActivity().getSharedPreferences(MainActivity.PREFERRENCES_FILE_NAME, 0);
        this.editor = this.pre.edit();
        this.bgmFlg = this.pre.getInt("bgmFlg", 1);
        this.soundFlg = this.pre.getInt("soundFlg", 1);
        this.partyNo = this.pre.getInt("partyNo", 1);
        this.bitmapFont = new BitmapFont(getBaseActivity().getTextureManager(), getBaseActivity().getAssets(), "font/numG.fnt");
        this.bitmapFont.load();
        this.droidFontTexture1 = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontYellow = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture1, getBaseActivity().getResourceUtil().getTypeface(), (float) LARGE_FONT_SIZE, true, Color.rgb(244, 216, 119));
        this.fontYellow.load();
        this.droidFontTexture2 = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontWhite = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture2, getBaseActivity().getResourceUtil().getTypeface(), (float) FONT_SIZE, true, Color.rgb(255, 255, 255));
        this.fontWhite.load();
        this.droidFontTexture3 = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontBlack = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture3, getBaseActivity().getResourceUtil().getTypeface(), (float) FONT_SIZE, true, Color.rgb(0, 0, 0));
        this.fontBlack.load();
        Cursor cursor = this.f216db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=500001", null);
        if (cursor.moveToFirst()) {
            this.exProgressId = cursor.getInt(0);
        } else {
            this.exProgressId = 500001;
            this.f216db.execSQL("INSERT INTO user_item_t VALUES (500001, 500001);");
        }
        this.menuBottomButton = new ArrayList<>();
        this.unBallImg = new ArrayList<>();
        this.boxBgList = new ArrayList<>();
        this.unitBoxImg = new ArrayList<>();
        this.unitBoxAttr1 = new ArrayList<>();
        this.unitBoxAttr2 = new ArrayList<>();
        this.unitBoxBt1 = new ArrayList<>();
        this.unitBoxBt2 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            this.damageText[i] = new Text(0.0f, 0.0f, (IFont) this.bitmapFont, (CharSequence) "", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.damageText[i]);
        }
        this.bgInitial = getBaseActivity().getResourceUtil().getSprite("bg/initial.png");
        this.bgInitial.setPosition(0.0f, 0.0f);
        attachChild(this.bgInitial);
        this.endNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
        placeToCenterX(this.endNg, 960.0f);
        this.endNg.setTag(99999997);
        this.endNg.setOnClickListener(this);
        attachChild(this.endNg);
        registerTouchArea(this.endNg);
        this.endOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
        placeToCenterX(this.endOk, 960.0f);
        this.endOk.setTag(99999998);
        this.endOk.setOnClickListener(this);
        attachChild(this.endOk);
        registerTouchArea(this.endOk);
        this.f219us[0] = -1;
        this.f219us[1] = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            this.userKodamaId[i2] = 0;
        }
        for (int i3 = 0; i3 < 2; i3++) {
            for (int s = 0; s < 6; s++) {
                this.unitName[i3][s] = "";
                for (int n = 0; n < 18; n++) {
                    this.unitSt[i3][s][n] = 0;
                }
                for (int n2 = 0; n2 < 3; n2++) {
                    this.orgSt[i3][s][n2] = 0;
                }
            }
            for (int s2 = 0; s2 < 10; s2++) {
                this.btfl[i3][s2] = 0;
            }
        }
        Cursor cursor2 = this.f216db.rawQuery("SELECT u.user_id, u.user_code, u.user_password, u.user_name, u.user_icon, u.user_difficult, u.user_quest_id, u.user_quest_status, u.user_quest_battle, u.user_last, u.user_demo_no FROM user_t u", null);
        if (cursor2.moveToFirst()) {
            if (!cursor2.getString(3).equals("empty")) {
                this.userName[0] = cursor2.getString(3);
            }
            this.userIcon[0] = cursor2.getInt(4);
            this.userDifficult = cursor2.getInt(5);
            this.questId = cursor2.getInt(6);
            this.userQuestStatus = cursor2.getInt(7);
            this.userQuestBattle = cursor2.getInt(8);
            this.userLast = cursor2.getInt(9);
            this.userDemoNo = cursor2.getInt(10);
            Cursor cursor3 = this.f216db.rawQuery("SELECT q.area_id, q.stage_id, q.quest_name, q.map_lv, q.bgm_battle, q.bgm_boss, q.prize FROM quest_m q WHERE q.quest_id=" + this.questId, null);
            if (cursor3.moveToFirst()) {
                this.areaId = cursor3.getInt(0);
                this.stageId = cursor3.getInt(1);
                this.mapLv = cursor3.getInt(3);
                this.bgmMapNo = cursor3.getInt(4);
                this.bgmBossNo = cursor3.getInt(5);
                this.prizeNo = cursor3.getInt(6);
                if (this.bgmBossNo == 0) {
                    this.bgmBossNo = this.rnd.nextInt(18) + 3;
                    if (20 < this.bgmBossNo) {
                        this.bgmBossNo = 20;
                    }
                }
                try {
                    if (this.userQuestBattle == 7) {
                        this.bgm = MusicFactory.createMusicFromAsset(getBaseActivity().getMusicManager(), getBaseActivity(), "music/battle/" + this.bgmBossNo + ".mp3");
                    } else {
                        this.bgm = MusicFactory.createMusicFromAsset(getBaseActivity().getMusicManager(), getBaseActivity(), "music/battle/" + this.bgmMapNo + ".mp3");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bgmChange(false);
                soundChange(false);
                if (this.bgm != null) {
                    this.bgm.setLooping(true);
                    this.bgm.play();
                }
            } else {
                popAlert("データ取得エラー", "quest_m\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (this.mapLv <= 0) {
                Cursor cursor4 = this.f216db.rawQuery("SELECT u.quest_id FROM user_progress_t u WHERE u.quest_id < 100000 ORDER BY u.quest_id DESC", null);
                if (cursor4.moveToFirst()) {
                    Cursor cursor5 = this.f216db.rawQuery("SELECT q.map_lv  FROM quest_m q WHERE q.quest_id<=" + cursor4.getInt(0) + " ORDER BY q.quest_id DESC", null);
                    if (cursor5.moveToFirst()) {
                        this.mapLv = cursor5.getInt(0) + this.mapLv;
                    }
                }
            }
        } else {
            popAlert("データ取得エラー", "user_t\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
        }
        this.attackBullet = getBaseActivity().getResourceUtil().getSprite("battle/bullet1.png");
        this.attackBullet.setPosition(0.0f, 0.0f);
        this.attackBullet.setAlpha(0.0f);
        attachChild(this.attackBullet);
        Cursor cursor6 = this.f216db.rawQuery("SELECT p.sort_no, u.user_kodama_id, u.sort_no, u.kodama_id, u.name, u.lv, u.exp, u.equip, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, u.skill, u.slv, u.spell1, u.spell2, u.spell3, u.spell4, m.kodama_name, m.kodama_hp, m.kodama_atk, m.kodama_def, m.kodama_spd, m.kodama_attr1, m.kodama_attr2, i.hp, i.atk, i.def, i.spd, u.faint_flg FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id LEFT OUTER JOIN item_m i ON u.equip=i.item_id WHERE p.party_no = " + this.partyNo + " ORDER BY p.sort_no ASC", null);
        while (cursor6.moveToNext()) {
            int sortNo = cursor6.getInt(0) - 1;
            if (cursor6.getInt(3) != 0) {
                this.userKodamaId[sortNo] = cursor6.getInt(1);
                this.unitName[0][sortNo] = cursor6.getString(4);
                this.className[0][sortNo] = cursor6.getString(19);
                for (int i4 = 0; i4 < 4; i4++) {
                    sb[i4] = cursor6.getInt(i4 + 8);
                }
                this.unitSt[0][sortNo][0] = cursor6.getInt(3);
                for (int i5 = 0; i5 < 4; i5++) {
                    this.unitSt[0][sortNo][i5 + 1] = cursor6.getInt(i5 + 15);
                }
                this.unitSt[0][sortNo][5] = cursor6.getInt(13);
                this.unitSt[0][sortNo][6] = cursor6.getInt(14);
                this.unitSt[0][sortNo][7] = cursor6.getInt(5);
                this.unitSt[0][sortNo][15] = cursor6.getInt(24);
                this.unitSt[0][sortNo][16] = cursor6.getInt(25);
                this.unitSt[0][sortNo][17] = cursor6.getInt(7);
                int kodamaHp = cursor6.getInt(20);
                int kodamaAtk = cursor6.getInt(21);
                int kodamaDef = cursor6.getInt(22);
                int kodamaSpd = cursor6.getInt(23);
                int itemHp = cursor6.getInt(26);
                int itemVp = 0;
                if (2000 < itemHp) {
                    itemVp = itemHp - 2000;
                    itemHp -= 2000;
                } else if (1000 < itemHp) {
                    itemVp = itemHp + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
                    itemHp = 0;
                }
                int itemAtk = cursor6.getInt(27);
                int itemDef = cursor6.getInt(28);
                int itemSpd = cursor6.getInt(29);
                this.unitSt[0][sortNo][9] = ((int) Math.floor((double) (((this.unitSt[0][sortNo][7] * ((kodamaHp + 10) * 2)) / 100) + this.unitSt[0][sortNo][7] + 10))) + itemHp + sb[0];
                this.unitSt[0][sortNo][8] = this.unitSt[0][sortNo][9];
                if (100 <= this.unitSt[0][sortNo][7]) {
                    this.unitSt[0][sortNo][11] = 100;
                } else {
                    this.unitSt[0][sortNo][11] = ((int) Math.floor((double) (this.unitSt[0][sortNo][7] / 2))) + 50;
                }
                int[] iArr = this.unitSt[0][sortNo];
                iArr[11] = iArr[11] + itemVp;
                this.unitSt[0][sortNo][10] = this.unitSt[0][sortNo][11];
                this.unitSt[0][sortNo][12] = ((int) Math.floor((double) (((this.unitSt[0][sortNo][7] * ((kodamaAtk + 10) * 2)) / 100) + 5))) + itemAtk + sb[1];
                this.unitSt[0][sortNo][13] = ((int) Math.floor((double) (((this.unitSt[0][sortNo][7] * ((kodamaDef + 10) * 2)) / 100) + 5))) + itemDef + sb[2];
                this.unitSt[0][sortNo][14] = ((int) Math.floor((double) (((this.unitSt[0][sortNo][7] * ((kodamaSpd + 10) * 2)) / 100) + 5))) + itemSpd + sb[3];
                for (int i6 = 8; i6 <= 14; i6++) {
                    if (this.unitSt[0][sortNo][i6] <= 0) {
                        this.unitSt[0][sortNo][i6] = 1;
                    }
                }
                if (cursor6.getInt(30) == 1) {
                    this.unitSt[0][sortNo][8] = 0;
                }
                this.orgSt[0][sortNo][0] = this.unitSt[0][sortNo][12];
                this.orgSt[0][sortNo][1] = this.unitSt[0][sortNo][13];
                this.orgSt[0][sortNo][2] = this.unitSt[0][sortNo][14];
            }
        }
        for (int i7 = 0; i7 < 6; i7++) {
            cursor6 = this.f216db.rawQuery("SELECT e.title, e.name, e.img, m.kodama_id, m.kodama_name, m.kodama_hp, m.kodama_atk, m.kodama_def, m.kodama_spd, m.kodama_attr1, m.kodama_attr2 FROM enemy_m e LEFT OUTER JOIN kodama_m m ON e.k" + (i7 + 1) + "=m.kodama_id WHERE e.quest_id=" + this.questId + " AND e.battle=" + this.userQuestBattle, null);
            if (cursor6.moveToNext()) {
                this.userName[1] = cursor6.getString(1);
                this.messageStr = cursor6.getString(0) + "の" + this.userName[1] + "が\n勝負を仕掛けてきた！";
                this.userIcon[1] = cursor6.getInt(2);
                this.unitSt[1][i7][0] = cursor6.getInt(3);
                this.unitName[1][i7] = cursor6.getString(4);
                this.className[1][i7] = cursor6.getString(4);
                this.unitSt[1][i7][15] = cursor6.getInt(9);
                this.unitSt[1][i7][16] = cursor6.getInt(10);
                if (this.unitSt[1][i7][0] != 0) {
                    float tmpF = (float) ((int) Math.ceil((double) ((this.mapLv * ((this.userDifficult * 20) + 60)) / 100)));
                    if (this.userDifficult != 2 && tmpF == ((float) this.mapLv)) {
                        tmpF = this.userDifficult == 1 ? tmpF - 1.0f : tmpF + ((float) (this.userDifficult - 2));
                    }
                    if (tmpF < 1.0f) {
                        tmpF = 1.0f;
                    }
                    if (this.userQuestBattle == 7) {
                        tmpF += (float) ((int) Math.ceil((double) (tmpF / 50.0f)));
                    }
                    this.unitSt[1][i7][7] = Math.round(tmpF);
                    int kodamaHp2 = cursor6.getInt(5);
                    int kodamaAtk2 = cursor6.getInt(6);
                    int kodamaDef2 = cursor6.getInt(7);
                    int kodamaSpd2 = cursor6.getInt(8);
                    int enemySb = 0;
                    if (this.userDifficult == 2) {
                        enemySb = (int) Math.floor((double) (this.unitSt[1][i7][7] / 4));
                    } else if (this.userDifficult == 3) {
                        enemySb = (int) Math.floor((double) (this.unitSt[1][i7][7] / 3));
                    } else if (this.userDifficult == 4) {
                        enemySb = (int) Math.floor((double) (this.unitSt[1][i7][7] / 2));
                    }
                    this.unitSt[1][i7][9] = ((int) Math.floor((double) (((this.unitSt[1][i7][7] * ((kodamaHp2 + 10) * 2)) / 100) + this.unitSt[1][i7][7] + 10))) + enemySb;
                    this.unitSt[1][i7][8] = this.unitSt[1][i7][9];
                    this.unitSt[1][i7][11] = ((int) Math.floor((double) (this.unitSt[1][i7][7] / 2))) + 50;
                    if (100 < this.unitSt[1][i7][11]) {
                        this.unitSt[1][i7][11] = 100;
                    }
                    this.unitSt[1][i7][10] = this.unitSt[1][i7][11];
                    this.unitSt[1][i7][12] = ((int) Math.floor((double) (((this.unitSt[1][i7][7] * ((kodamaAtk2 + 10) * 2)) / 100) + 5))) + enemySb;
                    this.unitSt[1][i7][13] = ((int) Math.floor((double) (((this.unitSt[1][i7][7] * ((kodamaDef2 + 10) * 2)) / 100) + 5))) + enemySb;
                    this.unitSt[1][i7][14] = ((int) Math.floor((double) (((this.unitSt[1][i7][7] * ((kodamaSpd2 + 10) * 2)) / 100) + 5))) + enemySb;
                    this.orgSt[1][i7][0] = this.unitSt[1][i7][12];
                    this.orgSt[1][i7][1] = this.unitSt[1][i7][13];
                    this.orgSt[1][i7][2] = this.unitSt[1][i7][14];
                }
            }
        }
        String prizes = " AND s.prize<=";
        String sorts = "ASC";
        if (this.questId % 2 == 0) {
            sorts = "DESC";
        }
        for (int i8 = 0; i8 < 6; i8++) {
            if (this.unitSt[1][i8][0] != 0) {
                int tmps1 = 0;
                int tmps2 = 0;
                boolean tmps2firstFlg = false;
                if (800000 < this.areaId && this.areaId < 900000) {
                    prizes = prizes + "10000000 AND s.atk <= 120";
                } else if (700000 < this.areaId && this.areaId < 800000) {
                    prizes = prizes + "10000000 AND s.atk <= 150";
                } else if (this.unitSt[1][i8][7] < 20) {
                    prizes = prizes + "3000 AND m.learn_id<10000";
                } else if (this.unitSt[1][i8][7] < 60) {
                    prizes = prizes + "20000 AND m.learn_id<10000";
                } else if (this.unitSt[1][i8][7] < 100) {
                    prizes = prizes + "100000 AND m.learn_id<10000";
                } else if (this.unitSt[1][i8][7] < 130) {
                    prizes = prizes + "1000000 AND m.learn_id<10000";
                } else {
                    prizes = prizes + "1000000 AND m.learn_id<10000";
                }
                cursor6 = this.f216db.rawQuery("SELECT m.learn_id, s.attr, s.atk, s.code FROM kodama_learn_m m LEFT OUTER JOIN spell_m s ON m.learn_id=s.spell_id WHERE m.kodama_id=" + this.unitSt[1][i8][0] + " AND m.type=1" + prizes + " ORDER BY s.prize DESC, s.attr " + sorts + ", s.spell_id ASC", null);
                while (cursor6.moveToNext()) {
                    if (cursor6.getInt(2) > 0) {
                        if (800000 >= this.areaId || this.areaId >= 900000 || cursor6.getInt(3) != 31) {
                            if (this.unitSt[1][i8][1] == 0 && this.unitSt[1][i8][15] == cursor6.getInt(1)) {
                                this.unitSt[1][i8][1] = cursor6.getInt(0);
                            } else if (this.unitSt[1][i8][3] == 0 && this.unitSt[1][i8][15] == cursor6.getInt(1)) {
                                this.unitSt[1][i8][3] = cursor6.getInt(0);
                            } else if (this.unitSt[1][i8][2] == 0 && ((this.unitSt[1][i8][16] == 30 && 10 <= this.unitSt[1][i8][7] && this.unitSt[1][i8][15] != cursor6.getInt(1)) || this.unitSt[1][i8][16] == cursor6.getInt(1))) {
                                this.unitSt[1][i8][2] = cursor6.getInt(0);
                            } else if (this.unitSt[1][i8][4] == 0 && ((this.unitSt[1][i8][16] == 30 && 10 <= this.unitSt[1][i8][7] && this.unitSt[1][i8][15] != cursor6.getInt(1)) || this.unitSt[1][i8][16] == cursor6.getInt(1))) {
                                this.unitSt[1][i8][4] = cursor6.getInt(0);
                            } else if (tmps1 == 0 && this.unitSt[1][i8][15] != cursor6.getInt(1) && this.unitSt[1][i8][16] != cursor6.getInt(1) && 2 <= this.userDifficult && 10 <= this.unitSt[1][i8][7]) {
                                tmps1 = cursor6.getInt(0);
                            } else if (tmps2 == 0 && this.unitSt[1][i8][15] != cursor6.getInt(1) && this.unitSt[1][i8][16] != cursor6.getInt(1) && 2 <= this.userDifficult && 10 <= this.unitSt[1][i8][7]) {
                                tmps2 = cursor6.getInt(0);
                            }
                        } else if (tmps2firstFlg) {
                            tmps1 = cursor6.getInt(0);
                        } else {
                            tmps2 = cursor6.getInt(0);
                            tmps2firstFlg = true;
                        }
                    } else if (10 < cursor6.getInt(3) && cursor6.getInt(3) < 20) {
                        if (this.userIcon[1] == 2 || this.userIcon[1] == 4 || this.userIcon[1] == 1033 || this.userIcon[1] == 1101 || this.userIcon[1] == 1109 || this.userIcon[1] == 1110) {
                            tmps2 = cursor6.getInt(0);
                        }
                    }
                }
                if (tmps1 != 0 || tmps2 == 0) {
                    if (tmps1 != 0) {
                        this.unitSt[1][i8][4] = tmps1;
                    }
                    if (tmps2 != 0) {
                        this.unitSt[1][i8][3] = tmps2;
                    }
                } else {
                    this.unitSt[1][i8][4] = tmps2;
                }
                if (40 < this.unitSt[1][i8][7]) {
                    cursor6 = this.f216db.rawQuery("SELECT m.learn_id FROM kodama_learn_m m WHERE m.kodama_id=" + this.unitSt[1][i8][0] + " AND m.type=2", null);
                    if (cursor6.moveToFirst()) {
                        this.unitSt[1][i8][5] = cursor6.getInt(0);
                        this.unitSt[1][i8][6] = (int) Math.floor((double) ((this.unitSt[1][i8][7] - 30) / 10));
                        if (this.unitSt[1][i8][6] < 1) {
                            this.unitSt[1][i8][6] = 1;
                        }
                        if (5 < this.unitSt[1][i8][6]) {
                            this.unitSt[1][i8][6] = 5;
                        }
                    }
                }
            }
        }
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/clear.png", "button/clear.png");
        this.buttonOk.setPosition(0.0f, 0.0f);
        this.buttonOk.setTag(0);
        attachChild(this.buttonOk);
        this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("button/clear.png", "button/clear.png");
        this.buttonCons.setPosition(0.0f, 0.0f);
        this.buttonCons.setTag(0);
        attachChild(this.buttonCons);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/clear.png", "button/clear.png");
        this.buttonNg.setPosition(0.0f, 0.0f);
        this.buttonNg.setTag(0);
        attachChild(this.buttonNg);
        this.infoText = new Text(0.0f, 0.0f, (IFont) this.fontWhite, (CharSequence) "pre", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        this.informationBox.setPosition(0.0f, 0.0f);
        attachChild(this.informationBox);
        this.buttonOk.detachSelf();
        this.buttonCons.detachSelf();
        this.buttonNg.detachSelf();
        this.infoText.detachSelf();
        this.informationBox.detachSelf();
        this.attackBullet.detachSelf();
        if (this.userQuestStatus == 3) {
            condLoad();
            if (this.userDemoNo == 1) {
                this.bottomMode = 101;
            } else {
                this.bottomMode = 102;
            }
            battleOpen(true);
            if (this.userDemoNo == 1) {
                this.userDemoNo = 2;
                tutorialOpen("戦わせるコダマを選んだら、\n次は攻撃方法を選択します。\n\n下のスペル一覧から、\n攻撃に使うスペルを選択しましょう。");
            }
        } else {
            battleOpen(false);
            unitListOpen();
            this.bottomMode = 100;
        }
        this.cartain = new Rectangle(0.0f, 0.0f, 540.0f, 960.0f, getBaseActivity().getVertexBufferObjectManager());
        this.cartain.setColor(0.0f, 0.0f, 0.0f);
        attachChild(this.cartain);
        this.cartainBottom = new Rectangle(0.0f, 476.0f, 540.0f, 484.0f, getBaseActivity().getVertexBufferObjectManager());
        this.cartainBottom.setColor(0.0f, 0.0f, 0.0f);
        this.cartainBottom.setAlpha(0.5f);
        attachChild(this.cartainBottom);
        this.handlerInit1 = new TimerHandler(2.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.cartain.registerEntityModifier(new FadeOutModifier(1.0f));
                if (MainScene.this.userQuestStatus != 3) {
                    MainScene.this.condSave();
                    MainScene.this.userQuestStatus = 3;
                    MainScene.this.f216db.execSQL("UPDATE user_t SET user_quest_status=" + MainScene.this.userQuestStatus);
                }
                MainScene.this.handlerInit2 = new TimerHandler(1.0f, new ITimerCallback() {
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        if (MainScene.this.bottomMode == 100) {
                            MainScene.this.battleMode = 2;
                            MainScene.this.arrowOpen();
                        } else {
                            MainScene.this.battleMode = 11;
                            MainScene.this.cartainBottom.detachSelf();
                            if (MainScene.this.f219us[0] < 0 || MainScene.this.f219us[1] < 0) {
                                MainScene.this.messageStr = "戦闘に出すコダマを選択してください。";
                            } else {
                                MainScene.this.messageStr = MainScene.this.unitName[0][MainScene.this.f219us[0]] + "はどうする？";
                            }
                            MainScene.this.messageText.detachSelf();
                            MainScene.this.messageText = new Text(10.0f, 306.0f, (IFont) MainScene.this.fontBlack, (CharSequence) MainScene.this.messageStr, new TextOptions(HorizontalAlign.LEFT), MainScene.this.getBaseActivity().getVertexBufferObjectManager());
                            MainScene.this.attachChild(MainScene.this.messageText);
                        }
                        MainScene.this.cartain.detachSelf();
                        MainScene.this.setOnSceneTouchListener(MainScene.this);
                        MainScene.this.registerUpdateHandler(MainScene.this.updateHandler);
                    }
                });
                MainScene.this.registerUpdateHandler(MainScene.this.handlerInit2);
            }
        });
        registerUpdateHandler(this.handlerInit1);
        if (cursor6 != null) {
            cursor6.close();
        }
        hideAds1();
    }

    /* access modifiers changed from: private */
    public void tutorialOpen(String str) {
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/informationS.png");
        placeToCenterX(this.informationBox, 20.0f);
        attachChild(this.informationBox);
        this.infoText = new Text((float) LARGE_FONT_SIZE, (float) LARGE_FONT_SIZE, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ok.png", "button/information_ok_p.png");
        this.buttonOk.setPosition(170.0f, 420.0f);
        this.buttonOk.setTag(99999990);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.dispBallImg = getBaseActivity().getResourceUtil().getSprite("item/arrow.png");
        if (this.userDemoNo == 0) {
            this.dispBallImg.setPosition(150.0f, 590.0f);
        } else if (this.userDemoNo == 2) {
            this.dispBallImg.setPosition(250.0f, 660.0f);
        }
        attachChild(this.dispBallImg);
        this.userDemoEffectFlg = true;
        this.dispBallImgFlg = true;
    }

    private void tutorialClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.userDemoEffectFlg = false;
        this.dispBallImg.setAlpha(1.0f);
        this.dispBallImg.detachSelf();
    }

    public void prepareSoundAndMusic() {
        try {
            this.dropEraseSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamDecisionStart.ogg");
            this.shotSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MatchPowerShot.ogg");
            this.attackSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MatchAttack.ogg");
            this.faintSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/Pityu.ogg");
            this.bossAppeal = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/BossAppeal.ogg");
            this.mapMove = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MapMove.ogg");
            this.mapClear = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamClearJingle.ogg");
            this.stageClear = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamDecisionCheck.ogg");
            this.destroySound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamDestroy.ogg");
            this.pressSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouSystem49.ogg");
            this.cancellSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouSystem43.ogg");
            this.spellUseSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/useSpell.ogg");
            this.spellSound11 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MatchPlasma.ogg");
            this.spellSound12 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamRpgKira.ogg");
            this.spellSound13 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamWind.ogg");
            this.spellSound14 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MatchThunder.ogg");
            this.spellSound15 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamGyao.ogg");
            this.spellSound16 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MatchSmash.ogg");
            this.spellSound18 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamSlash003.ogg");
            this.spellSound19 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamPower001.ogg");
            this.spellSound25 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/LaboHoly1.ogg");
            this.spellSound27 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/OnjinSlash.ogg");
            this.spellSound29 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/LaboFlame1.ogg");
            this.spellSound30 = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/LaboWind2.ogg");
            this.spellHealSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/LaboHeal.ogg");
            this.statusUpSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/extend.ogg");
            this.statusDownSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamStatusDown.ogg");
            this.unitAppearSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouMagical10.ogg");
            this.extendSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/extend.ogg");
        } catch (IOException e) {
            e.printStackTrace();
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
            this.dropEraseSound.setVolume(0.0f);
            this.shotSound.setVolume(0.0f);
            this.attackSound.setVolume(0.0f);
            this.faintSound.setVolume(0.0f);
            this.bossAppeal.setVolume(0.0f);
            this.mapMove.setVolume(0.0f);
            this.mapClear.setVolume(0.0f);
            this.stageClear.setVolume(0.0f);
            this.destroySound.setVolume(0.0f);
            this.pressSound.setVolume(0.0f);
            this.cancellSound.setVolume(0.0f);
            this.spellUseSound.setVolume(0.0f);
            this.spellSound11.setVolume(0.0f);
            this.spellSound12.setVolume(0.0f);
            this.spellSound13.setVolume(0.0f);
            this.spellSound14.setVolume(0.0f);
            this.spellSound15.setVolume(0.0f);
            this.spellSound16.setVolume(0.0f);
            this.spellSound18.setVolume(0.0f);
            this.spellSound19.setVolume(0.0f);
            this.spellSound25.setVolume(0.0f);
            this.spellSound27.setVolume(0.0f);
            this.spellSound29.setVolume(0.0f);
            this.spellSound30.setVolume(0.0f);
            this.spellHealSound.setVolume(0.0f);
            this.statusUpSound.setVolume(0.0f);
            this.statusDownSound.setVolume(0.0f);
            this.unitAppearSound.setVolume(0.0f);
            this.extendSound.setVolume(0.0f);
            return;
        }
        this.dropEraseSound.setVolume(1.0f);
        this.shotSound.setVolume(1.0f);
        this.attackSound.setVolume(1.0f);
        this.faintSound.setVolume(1.0f);
        this.bossAppeal.setVolume(1.0f);
        this.mapMove.setVolume(1.0f);
        this.mapClear.setVolume(1.0f);
        this.stageClear.setVolume(1.0f);
        this.destroySound.setVolume(1.0f);
        this.pressSound.setVolume(1.0f);
        this.cancellSound.setVolume(1.0f);
        this.spellUseSound.setVolume(0.6f);
        this.spellSound11.setVolume(1.0f);
        this.spellSound12.setVolume(1.0f);
        this.spellSound13.setVolume(1.0f);
        this.spellSound14.setVolume(1.0f);
        this.spellSound15.setVolume(1.0f);
        this.spellSound16.setVolume(1.0f);
        this.spellSound18.setVolume(1.0f);
        this.spellSound19.setVolume(1.0f);
        this.spellSound25.setVolume(1.0f);
        this.spellSound27.setVolume(1.0f);
        this.spellSound29.setVolume(1.0f);
        this.spellSound30.setVolume(1.0f);
        this.spellHealSound.setVolume(1.0f);
        this.statusUpSound.setVolume(1.0f);
        this.statusDownSound.setVolume(1.0f);
        this.unitAppearSound.setVolume(1.0f);
        this.extendSound.setVolume(1.0f);
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getAction() == 0 && e.getKeyCode() == 4 && !this.endConfirmFlg) {
            endConfirmOpen();
        }
        return false;
    }

    public void onDestroy() {
    }

    public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        boolean self;
        boolean self2;
        if (pButtonSprite.getTag() == 99999996) {
            if (!this.endStartFlg) {
                this.endStartFlg = true;
                this.stageClear.play();
                this.cartain = new Rectangle(0.0f, 0.0f, 540.0f, 960.0f, getBaseActivity().getVertexBufferObjectManager());
                this.cartain.setColor(0.0f, 0.0f, 0.0f);
                this.cartain.setAlpha(0.0f);
                attachChild(this.cartain);
                this.cartain.registerEntityModifier(new FadeInModifier(1.0f));
                this.handlerEnd = new TimerHandler(1.0f, new ITimerCallback() {
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        MainScene.this.free();
                        MainScene.this.destroy();
                        ResourceUtil.getInstance(MainScene.this.getBaseActivity()).resetAllTexture();
                    }
                });
                registerUpdateHandler(this.handlerEnd);
            }
        } else if (this.userDemoNo == 0 || this.userDemoNo == 2) {
            if (pButtonSprite.getTag() == 99999990) {
                this.pressSound.play();
                this.userDemoNo++;
                tutorialClose();
                if (this.userDemoNo == 1 || this.userDemoNo == 3) {
                    this.f216db.execSQL("UPDATE user_t SET user_demo_no=" + this.userDemoNo);
                }
            }
        } else if (!this.waitFlg && this.battleMode == 11) {
            if (this.endConfirmFlg) {
                if (pButtonSprite.getTag() == 99999998) {
                    ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
                    getBaseActivity().finish();
                } else if (pButtonSprite.getTag() == 99999997) {
                    this.cancellSound.play();
                    endConfirmClose();
                }
            } else if (this.retireConfirmFlg) {
                if (pButtonSprite.getTag() == 99999993) {
                    this.pressSound.play();
                    retireExe();
                    return;
                }
                this.cancellSound.play();
                retireConfirmClose();
                battleOpen(true);
            } else if (this.unitDetailOpenFlg || this.enemyDetailOpenFlg) {
                if (pButtonSprite.getTag() == 99999999) {
                    this.cancellSound.play();
                    unitDetailClose();
                    battleOpen(true);
                } else if (10000000 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.pressSound.play();
                    if (this.unitDetailOpenFlg) {
                        self = true;
                    } else {
                        self = false;
                    }
                    unitDetailClose();
                    spellOpen(self, pButtonSprite.getTag() - 10000000);
                }
            } else if (this.unitSpellOpenFlg || this.enemySpellOpenFlg) {
                if (10000000 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.cancellSound.play();
                    if (this.unitSpellOpenFlg) {
                        self2 = true;
                    } else {
                        self2 = false;
                    }
                    spellClose();
                    unitDetailOpen(self2, pButtonSprite.getTag() - 10000000);
                }
            } else if (this.attrTableOpenFlg) {
                if (pButtonSprite.getTag() == 99999999) {
                    this.cancellSound.play();
                    if (this.bottomMode == 101) {
                        spellListOpen();
                    } else if (this.bottomMode == 102) {
                        unitListOpen();
                    }
                    attrTableClose();
                }
            } else if (this.logOpenFlg) {
                if (pButtonSprite.getTag() == 99999999) {
                    this.cancellSound.play();
                    if (this.bottomMode == 101) {
                        spellListOpen();
                    } else if (this.bottomMode == 102) {
                        unitListOpen();
                    }
                    logClose();
                }
            } else if (pButtonSprite.getTag() == 101) {
                if (this.bottomMode != 102 || this.f219us[0] < 0 || this.f219us[1] < 0) {
                    this.cancellSound.play();
                    return;
                }
                this.pressSound.play();
                unitListClose();
                spellListOpen();
            } else if (pButtonSprite.getTag() == 102) {
                if (this.bottomMode == 101) {
                    this.pressSound.play();
                    spellListClose();
                    unitListOpen();
                    return;
                }
                this.cancellSound.play();
            } else if (pButtonSprite.getTag() == 103) {
                if (this.bottomMode == 101 || this.bottomMode == 102) {
                    this.pressSound.play();
                    if (this.bottomMode == 101) {
                        spellListClose();
                    } else if (this.bottomMode == 102) {
                        unitListClose();
                    }
                    attrTableOpen();
                    return;
                }
                this.cancellSound.play();
            } else if (pButtonSprite.getTag() == 104) {
                if (this.bottomMode == 101 || this.bottomMode == 102) {
                    this.pressSound.play();
                    battleClose();
                    if (this.f219us[1] < 0) {
                        for (int i = 0; i < 6; i++) {
                            if (this.unitSt[1][i][8] > 0) {
                                unitDetailOpen(false, i);
                                return;
                            }
                        }
                        return;
                    }
                    unitDetailOpen(false, this.f219us[1]);
                    return;
                }
                this.cancellSound.play();
            } else if (pButtonSprite.getTag() == 99999991) {
                if (this.bottomMode == 101 || this.bottomMode == 102) {
                    this.pressSound.play();
                    if (this.bottomMode == 101) {
                        spellListClose();
                    } else if (this.bottomMode == 102) {
                        unitListClose();
                    }
                    logOpen();
                    return;
                }
                this.cancellSound.play();
            } else if (pButtonSprite.getTag() == 99999994) {
                this.pressSound.play();
                battleClose();
                spellListClose();
                retireConfirmOpen();
            } else if (this.bottomMode == 101) {
                if (pButtonSprite.getTag() == 99999999) {
                    this.cancellSound.play();
                } else if (pButtonSprite.getTag() != 99999994 && 10000000 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.pressSound.play();
                    battlePreProc(pButtonSprite.getTag() - 10000000);
                }
            } else if (this.bottomMode == 102) {
                if (pButtonSprite.getTag() == 99999999) {
                    this.cancellSound.play();
                } else if (10000000 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.pressSound.play();
                    if (this.f219us[0] < 0 || this.f219us[1] < 0) {
                        if (this.consecutiveFlg) {
                            this.buttonCons.detachSelf();
                            unregisterTouchArea(this.buttonCons);
                            this.consecutiveFlg = false;
                        }
                        if (this.messageLogFlg) {
                            this.messageLogFlg = false;
                            this.buttonLog.detachSelf();
                            unregisterTouchArea(this.buttonLog);
                        }
                        this.cartainBottom = new Rectangle(0.0f, 476.0f, 540.0f, 484.0f, getBaseActivity().getVertexBufferObjectManager());
                        this.cartainBottom.setColor(0.0f, 0.0f, 0.0f);
                        this.cartainBottom.setAlpha(0.5f);
                        attachChild(this.cartainBottom);
                        this.messageStr = "";
                        this.nextUs = pButtonSprite.getTag() - 10000000;
                        this.unitContinueFlg = true;
                        if (this.f219us[1] < 0) {
                            enemySendOut();
                        } else {
                            unitSendOut();
                        }
                    } else {
                        battlePreProc(pButtonSprite.getTag() - 10000000);
                    }
                } else if (20000000 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 30000000) {
                    this.pressSound.play();
                    battleClose();
                    unitDetailOpen(true, pButtonSprite.getTag() - 20000000);
                }
            } else if (pButtonSprite.getTag() == 99999995) {
                this.pressSound.play();
                battleClose();
                unitDetailOpen(false, this.f219us[1]);
            }
        }
    }

    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        float y = pSceneTouchEvent.getY();
        if (!this.waitFlg && this.battleMode == 2 && y < 960.0f && pSceneTouchEvent.getAction() == 1 && this.f219us[0] < 0) {
            this.battleMode = 11;
            this.messageStr = this.userName[1] + "は\n" + this.unitName[1][this.f219us[1]] + "を繰り出してきた！\n\n戦闘に出すコダマを選択してください。";
            this.messageText.detachSelf();
            this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.messageText);
            unitImgOpenEffect(1);
            this.bottomMode = 102;
            this.handlerInit1 = new TimerHandler(2.0f, new ITimerCallback() {
                public void onTimePassed(TimerHandler pTimerHandler) {
                    MainScene.this.cartainBottom.detachSelf();
                    MainScene.this.arrowR.detachSelf();
                }
            });
            registerUpdateHandler(this.handlerInit1);
        }
        return true;
    }

    private void enemySendOut() {
        int i = 0;
        while (true) {
            if (i >= 6) {
                break;
            } else if (this.unitSt[1][i][8] > 0) {
                this.f219us[1] = i;
                break;
            } else {
                i++;
            }
        }
        unitImgOpenEffect(1);
        this.messageStr += this.userName[1] + "は" + this.unitName[1][this.f219us[1]] + "を繰り出した！\n";
        unitCopy(0);
        unitCopy(1);
        this.messageText.detachSelf();
        this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
    }

    /* access modifiers changed from: private */
    public void unitSendOut() {
        if (this.f219us[0] < 0) {
            this.f219us[0] = this.nextUs;
            this.messageStr += this.userName[0] + "は" + this.unitName[0][this.f219us[0]] + "を繰り出した！\n";
            unitCopy(0);
            unitCopy(1);
            this.messageText.detachSelf();
            this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.messageText);
            unitImgOpenEffect(0);
        } else if (this.f219us[0] != this.nextUs) {
            unitImgCloseEffect(0);
            this.messageStr += this.userName[0] + "は" + this.unitName[0][this.f219us[0]] + "を戻して、\n";
            this.f219us[0] = this.nextUs;
            this.messageStr += this.unitName[0][this.f219us[0]] + "を繰り出した！\n";
            unitCopy(0);
            unitCopy(1);
            this.messageText.detachSelf();
            this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.messageText);
            this.handlerSendOut1 = new TimerHandler(0.6f, new ITimerCallback() {
                public void onTimePassed(TimerHandler pTimerHandler) {
                    MainScene.this.unImg.detachSelf();
                    MainScene.this.unitImgOpenEffect(0);
                }
            });
            registerUpdateHandler(this.handlerSendOut1);
        } else {
            unitSendOutAfter();
        }
    }

    /* access modifiers changed from: private */
    public void unitSendOutAfter() {
        this.messageStr += "\n" + this.unitName[0][this.f219us[0]] + "はどうする？";
        this.messageText.detachSelf();
        this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        unitStatusClose();
        unitStatusOpen();
        unitListClose();
        spellListOpen();
        this.waitFlg = false;
        if (!this.messageLog.equals("")) {
            this.buttonLog = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/log.png", "battle/button/log_p.png");
            this.buttonLog.setPosition(360.0f, 406.0f);
            this.buttonLog.setTag(99999991);
            this.buttonLog.setOnClickListener(this);
            attachChild(this.buttonLog);
            registerTouchArea(this.buttonLog);
            this.messageLogFlg = true;
        }
        this.cartainBottom.detachSelf();
        if (this.userDemoNo == 1) {
            this.userDemoNo = 2;
            tutorialOpen("戦わせるコダマを選んだら、\n次は攻撃方法を選択します。\n\n下のスペル一覧から、\n攻撃に使うスペルを選択しましょう。");
        }
    }

    /* access modifiers changed from: private */
    public void battleAdvance() {
        if (this.f218o >= 2 || this.unitSt[0][this.f219us[0]][8] <= 0 || this.unitSt[1][this.f219us[1]][8] <= 0) {
            boolean endFlg = true;
            int i = 0;
            while (true) {
                if (i >= 6) {
                    break;
                } else if (this.unitSt[0][i][8] > 0) {
                    endFlg = false;
                    break;
                } else {
                    i++;
                }
            }
            if (endFlg) {
                destroyEffect();
                return;
            }
            boolean endFlg2 = true;
            int i2 = 0;
            while (true) {
                if (i2 >= 6) {
                    break;
                } else if (this.unitSt[1][i2][8] > 0) {
                    endFlg2 = false;
                    break;
                } else {
                    i2++;
                }
            }
            if (endFlg2) {
                this.handlerClearEffect = new TimerHandler(1.0f, new ITimerCallback() {
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        MainScene.this.clearEffect();
                    }
                });
                registerUpdateHandler(this.handlerClearEffect);
                return;
            }
            turnEndStart();
            return;
        }
        battleExe();
    }

    private int attrCheck(int t, int cn, int act, boolean skill) {
        int result = 5;
        if (skill && 5000 < this.skc[cn] && this.skc[cn] < 6000 && t == ((this.skc[cn] - 5000) - (this.skc[cn] % 10)) / 10) {
            result = 4 <= this.unitSt[cn][act][6] ? 0 : 5 - 1;
        }
        if (result == 0) {
            return result;
        }
        for (int s = 15; s <= 16; s++) {
            int i = this.unitSt[cn][act][s];
            if (i != 10) {
                if ((t == 12 && i == 15) || ((t == 12 && i == 18) || ((t == 12 && i == 21) || ((t == 13 && i == 11) || ((t == 13 && i == 18) || ((t == 13 && i == 19) || ((t == 13 && i == 23) || ((t == 13 && i == 27) || ((t == 14 && i == 12) || ((t == 14 && i == 28) || ((t == 15 && i == 14) || ((t == 15 && i == 18) || ((t == 15 && i == 19) || ((t == 15 && i == 25) || ((t == 15 && i == 22) || ((t == 16 && i == 12) || ((t == 16 && i == 13) || ((t == 16 && i == 17) || ((t == 17 && i == 12) || ((t == 17 && i == 24) || ((t == 17 && i == 27) || ((t == 18 && i == 16) || ((t == 18 && i == 17) || ((t == 18 && i == 25) || ((t == 18 && i == 23) || ((t == 19 && i == 18) || ((t == 19 && i == 23) || ((t == 19 && i == 28) || ((t == 20 && i == 24) || ((t == 20 && i == 20) || ((t == 21 && i == 15) || ((t == 21 && i == 18) || ((t == 21 && i == 25) || ((t == 22 && i == 16) || ((t == 22 && i == 21) || ((t == 23 && i == 12) || ((t == 23 && i == 15) || ((t == 23 && i == 16) || ((t == 23 && i == 26) || ((t == 24 && i == 13) || ((t == 24 && i == 14) || ((t == 25 && i == 12) || ((t == 25 && i == 17) || ((t == 25 && i == 19) || ((t == 25 && i == 23) || ((t == 26 && i == 26) || ((t == 27 && i == 24) || ((t == 27 && i == 20) || ((t == 28 && i == 13) || ((t == 28 && i == 26) || (t == 28 && i == 27))))))))))))))))))))))))))))))))))))))))))))))))))) {
                    result++;
                }
                if ((t == 11 && i == 18) || ((t == 11 && i == 19) || ((t == 12 && i == 12) || ((t == 12 && i == 14) || ((t == 12 && i == 16) || ((t == 12 && i == 17) || ((t == 12 && i == 19) || ((t == 12 && i == 25) || ((t == 12 && i == 26) || ((t == 13 && i == 14) || ((t == 13 && i == 16) || ((t == 13 && i == 17) || ((t == 13 && i == 24) || ((t == 13 && i == 28) || ((t == 14 && i == 14) || ((t == 14 && i == 15) || ((t == 14 && i == 18) || ((t == 14 && i == 20) || ((t == 15 && i == 12) || ((t == 15 && i == 17) || ((t == 16 && i == 18) || ((t == 16 && i == 19) || ((t == 16 && i == 22) || ((t == 17 && i == 13) || ((t == 17 && i == 14) || ((t == 17 && i == 16) || ((t == 17 && i == 19) || ((t == 17 && i == 25) || ((t == 17 && i == 20) || ((t == 17 && i == 28) || ((t == 18 && i == 13) || ((t == 18 && i == 15) || ((t == 18 && i == 19) || ((t == 19 && i == 19) || ((t == 19 && i == 25) || ((t == 19 && i == 21) || ((t == 19 && i == 22) || ((t == 20 && i == 27) || ((t == 21 && i == 12) || ((t == 21 && i == 21) || ((t == 21 && i == 26) || ((t == 22 && i == 12) || ((t == 22 && i == 22) || ((t == 22 && i == 26) || ((t == 23 && i == 19) || ((t == 23 && i == 25) || ((t == 23 && i == 21) || ((t == 23 && i == 23) || ((t == 24 && i == 19) || ((t == 24 && i == 24) || ((t == 25 && i == 18) || ((t == 25 && i == 25) || ((t == 25 && i == 21) || ((t == 25 && i == 26) || ((t == 26 && i == 19) || ((t == 27 && i == 13) || ((t == 27 && i == 27) || ((t == 27 && i == 28) || ((t == 28 && i == 14) || ((t == 28 && i == 19) || (t == 28 && i == 25))))))))))))))))))))))))))))))))))))))))))))))))))))))))))))) {
                    result--;
                }
                if ((t == 11 && i == 20) || ((t == 13 && i == 20) || ((t == 14 && i == 19) || ((t == 15 && i == 16) || ((t == 22 && i == 15) || ((t == 24 && i == 27) || ((t == 20 && i == 11) || (t == 26 && i == 28)))))))) {
                    return 0;
                }
            }
        }
        return result;
    }

    private void battleOpen(boolean unitFlg) {
        this.bgImg = getBaseActivity().getResourceUtil().getSprite("battle/bg.png");
        this.bgImg.setPosition(0.0f, 0.0f);
        attachChild(this.bgImg);
        this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        unitStatusOpen();
        int posX = 0;
        for (int i = 0; i < 4; i++) {
            this.menuBottomButton.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/button" + (i + 1) + ".png", "battle/button/button" + (i + 1) + "_p.png"));
            ((ButtonSprite) this.menuBottomButton.get(i)).setPosition((float) posX, (float) 476);
            if (i == 0) {
                ((ButtonSprite) this.menuBottomButton.get(i)).setTag(101);
            }
            if (i == 1) {
                ((ButtonSprite) this.menuBottomButton.get(i)).setTag(102);
            }
            if (i == 2) {
                ((ButtonSprite) this.menuBottomButton.get(i)).setTag(103);
            }
            if (i == 3) {
                ((ButtonSprite) this.menuBottomButton.get(i)).setTag(104);
            }
            ((ButtonSprite) this.menuBottomButton.get(i)).setOnClickListener(this);
            attachChild((IEntity) this.menuBottomButton.get(i));
            registerTouchArea((ITouchArea) this.menuBottomButton.get(i));
            posX += 135;
        }
        if (unitFlg) {
            if (this.f219us[0] >= 0) {
                unitImgOpen(false);
            }
            if (this.f219us[1] >= 0) {
                unitImgOpen(true);
            }
        }
        if (this.f219us[0] >= 0 && this.f219us[1] < 0) {
            if (this.unitSt[0][this.f219us[0]][8] <= 0) {
                this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/consecutive_ng.png", "battle/button/consecutive_ng.png");
                this.buttonCons.setTag(99999999);
            } else {
                this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/consecutive.png", "battle/button/consecutive_p.png");
                this.buttonCons.setTag(10000000 + this.f219us[0]);
            }
            this.buttonCons.setPosition(360.0f, 336.0f);
            this.buttonCons.setOnClickListener(this);
            attachChild(this.buttonCons);
            registerTouchArea(this.buttonCons);
            this.consecutiveFlg = true;
        }
        if (this.bottomMode == 101) {
            spellListOpen();
        } else if (this.bottomMode == 102) {
            unitListOpen();
        }
        if (!this.messageLog.equals("")) {
            this.buttonLog = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/log.png", "battle/button/log_p.png");
            this.buttonLog.setPosition(360.0f, 406.0f);
            this.buttonLog.setTag(99999991);
            this.buttonLog.setOnClickListener(this);
            attachChild(this.buttonLog);
            registerTouchArea(this.buttonLog);
            this.messageLogFlg = true;
        }
    }

    private void battleClose() {
        this.bgImg.detachSelf();
        this.messageText.detachSelf();
        unitStatusClose();
        for (int i = 0; i < 4; i++) {
            ((ButtonSprite) this.menuBottomButton.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.menuBottomButton.get(i));
        }
        if (this.f219us[0] >= 0) {
            this.unImg.detachSelf();
        }
        if (this.f219us[1] >= 0) {
            this.enImg.detachSelf();
        }
        if (this.consecutiveFlg) {
            this.buttonCons.detachSelf();
            unregisterTouchArea(this.buttonCons);
            this.consecutiveFlg = false;
        }
        if (this.bottomMode == 101) {
            spellListClose();
        } else if (this.bottomMode == 102) {
            unitListClose();
        }
        if (this.messageLogFlg) {
            this.messageLogFlg = false;
            this.buttonLog.detachSelf();
            unregisterTouchArea(this.buttonLog);
        }
    }

    /* access modifiers changed from: private */
    public void unitStatusOpen() {
        String str;
        String str2;
        this.unVarImg = getBaseActivity().getResourceUtil().getSprite("battle/unitVarBg.png");
        this.enVarImg = getBaseActivity().getResourceUtil().getSprite("battle/unitVarBg.png");
        this.unVarImg.setPosition((float) 426, (float) 38);
        this.enVarImg.setPosition((float) 156, (float) 38);
        attachChild(this.unVarImg);
        attachChild(this.enVarImg);
        for (int i = 0; i < 2; i++) {
            String str3 = "";
            int attr1 = 30;
            int attr2 = 30;
            int posX = 387 - (i * 384);
            int varX = 426 - (i * 270);
            if (this.f219us[i] >= 0) {
                String str4 = "Lv" + this.unitSt[i][this.f219us[i]][7] + " " + this.unitName[i][this.f219us[i]] + "\nHP:";
                if (1000 <= this.unitSt[i][this.f219us[i]][8]) {
                    str = str4 + "???";
                } else {
                    str = str4 + this.unitSt[i][this.f219us[i]][8];
                }
                String str5 = str + "/";
                if (1000 <= this.unitSt[i][this.f219us[i]][9]) {
                    str2 = str5 + "???";
                } else {
                    str2 = str5 + this.unitSt[i][this.f219us[i]][9];
                }
                str3 = str2 + "\nVP:" + this.unitSt[i][this.f219us[i]][10] + "/" + this.unitSt[i][this.f219us[i]][11];
                attr1 = this.unitSt[i][this.f219us[i]][15];
                attr2 = this.unitSt[i][this.f219us[i]][16];
            }
            for (int s = 0; s < 2; s++) {
                float varWidth = 0.0f;
                if (this.f219us[i] >= 0) {
                    float tmpP = (float) this.unitSt[i][this.f219us[i]][(s * 2) + 8];
                    float tmpMax = (float) this.unitSt[i][this.f219us[i]][(s * 2) + 9];
                    if (tmpMax != 0.0f) {
                        varWidth = tmpP / tmpMax;
                    }
                }
                this.unVar[(i * 2) + s] = new Rectangle((float) (varX + 1), (float) ((s * 22) + 39), (float) Math.round(100.0f * varWidth), 21.0f, getBaseActivity().getVertexBufferObjectManager());
                if (((double) varWidth) <= 0.2d) {
                    this.unVar[(i * 2) + s].setColor(0.965f, 0.216f, 0.153f);
                } else if (((double) varWidth) <= 0.5d) {
                    this.unVar[(i * 2) + s].setColor(1.0f, 0.918f, 0.365f);
                } else if (s == 0) {
                    this.unVar[(i * 2) + s].setColor(0.051f, 0.941f, 0.145f);
                } else {
                    this.unVar[(i * 2) + s].setColor(0.156f, 0.384f, 0.804f);
                }
                attachChild(this.unVar[(i * 2) + s]);
            }
            if (i == 0) {
                this.unStText = new Text(280.0f, 12.0f, (IFont) this.fontBlack, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.unStText);
                this.unAttrImg1 = getBaseActivity().getResourceUtil().getSprite("attr/s/" + attr1 + ".png");
                this.unAttrImg2 = getBaseActivity().getResourceUtil().getSprite("attr/s/" + attr2 + ".png");
                this.unAttrImg1.setPosition(486.0f, 6.0f);
                this.unAttrImg2.setPosition(510.0f, 6.0f);
                attachChild(this.unAttrImg1);
                attachChild(this.unAttrImg2);
            } else {
                this.enStText = new Text(10.0f, 12.0f, (IFont) this.fontBlack, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.enStText);
                this.enAttrImg1 = getBaseActivity().getResourceUtil().getSprite("attr/s/" + attr1 + ".png");
                this.enAttrImg2 = getBaseActivity().getResourceUtil().getSprite("attr/s/" + attr2 + ".png");
                this.enAttrImg1.setPosition(216.0f, 6.0f);
                this.enAttrImg2.setPosition(240.0f, 6.0f);
                attachChild(this.enAttrImg1);
                attachChild(this.enAttrImg2);
            }
            for (int s2 = 0; s2 < 6; s2++) {
                if (this.unitSt[i][s2][8] > 0) {
                    this.unBallImg.add((i * 6) + s2, getBaseActivity().getResourceUtil().getSprite("battle/ball.png"));
                } else {
                    this.unBallImg.add((i * 6) + s2, getBaseActivity().getResourceUtil().getSprite("battle/ball_p.png"));
                }
                ((Sprite) this.unBallImg.get((i * 6) + s2)).setPosition((float) posX, 99.0f);
                attachChild((IEntity) this.unBallImg.get((i * 6) + s2));
                posX += 25;
            }
        }
    }

    /* access modifiers changed from: private */
    public void unitStatusClose() {
        this.unStText.detachSelf();
        this.unAttrImg1.detachSelf();
        this.unAttrImg2.detachSelf();
        this.enStText.detachSelf();
        this.enAttrImg1.detachSelf();
        this.enAttrImg2.detachSelf();
        for (int i = 0; i < 12; i++) {
            ((Sprite) this.unBallImg.get(i)).detachSelf();
        }
        this.unVarImg.detachSelf();
        this.enVarImg.detachSelf();
        for (int i2 = 0; i2 < 4; i2++) {
            this.unVar[i2].detachSelf();
        }
    }

    /* access modifiers changed from: private */
    public void unitImgOpenEffect(int enemy) {
        int posX;
        this.waitFlg = true;
        if (enemy == 0) {
            this.dispUnit = 0;
            posX = 305;
        } else {
            this.dispUnit = 1;
            posX = 0;
        }
        this.dispBallImg = getBaseActivity().getResourceUtil().getSprite("battle/ball.png");
        this.dispBallImg.setPosition((float) (posX + 106), -50.0f);
        attachChild(this.dispBallImg);
        this.dispBallImgFlg = true;
        this.dispBallFlg = true;
        this.dispBallImg.registerEntityModifier(new MoveModifier(2.0f, this.dispBallImg.getX(), this.dispBallImg.getX(), -50.0f, 246.0f, (IEaseFunction) EaseBounceOut.getInstance()));
        this.handlerUnitDisp1 = new TimerHandler(1.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.dispBallImg.registerEntityModifier(new FadeOutModifier(0.5f));
                MainScene.this.unitAppearSound.play();
                MainScene.this.cartain = new Rectangle(0.0f, 0.0f, 540.0f, 960.0f, MainScene.this.getBaseActivity().getVertexBufferObjectManager());
                MainScene.this.cartain.setColor(1.0f, 1.0f, 1.0f);
                MainScene.this.cartain.setAlpha(0.0f);
                MainScene.this.attachChild(MainScene.this.cartain);
                MainScene.this.cartain.registerEntityModifier(new SequenceEntityModifier(new FadeInModifier(0.2f), new FadeOutModifier(0.2f)));
                if (MainScene.this.dispUnit == 0) {
                    MainScene.this.unImg = MainScene.this.getBaseActivity().getResourceUtil().getSprite("kodama/" + MainScene.this.unitSt[MainScene.this.dispUnit][MainScene.this.f219us[MainScene.this.dispUnit]][0] + ".png");
                    MainScene.this.unImg.setPosition(358.0f, 142.0f);
                    MainScene.this.unImg.setAlpha(0.0f);
                    MainScene.this.unImg.setScale(0.0f);
                    MainScene.this.attachChild(MainScene.this.unImg);
                    MainScene.this.unImg.registerEntityModifier(new ParallelEntityModifier(new FadeInModifier(0.5f), new ScaleModifier(0.5f, 0.0f, 1.0f)));
                    return;
                }
                MainScene.this.enImg = MainScene.this.getBaseActivity().getResourceUtil().getSprite("kodama/" + MainScene.this.unitSt[MainScene.this.dispUnit][MainScene.this.f219us[MainScene.this.dispUnit]][0] + ".png");
                MainScene.this.enImg.setPosition(54.0f, 142.0f);
                MainScene.this.enImg.setAlpha(0.0f);
                MainScene.this.enImg.setScale(0.0f);
                MainScene.this.enImg.setFlippedHorizontal(true);
                MainScene.this.attachChild(MainScene.this.enImg);
                MainScene.this.enImg.registerEntityModifier(new ParallelEntityModifier(new FadeInModifier(0.5f), new ScaleModifier(0.5f, 0.0f, 1.0f)));
            }
        });
        registerUpdateHandler(this.handlerUnitDisp1);
        this.handlerUnitDisp2 = new TimerHandler(2.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.dispBallFlg = false;
                MainScene.this.dispBallImg.detachSelf();
                MainScene.this.cartain.detachSelf();
                if (MainScene.this.battleMode == 21) {
                    MainScene.this.f218o = MainScene.this.f218o + 1;
                    MainScene.this.battleAdvance();
                } else if (!MainScene.this.unitContinueFlg) {
                    if (MainScene.this.userDemoNo == 0) {
                        MainScene.this.pressSound.play();
                        MainScene.this.tutorialOpen("バトルでは、\nコダマを１体ずつ繰り出して\n１対１で戦わせます。\n\nそして先に相手のコダマを\n全て倒した方が勝利となります。\n\n\nまずは最初に繰り出すコダマを\n選択しましょう。\n\n「交代」をタップしてください。");
                    }
                    MainScene.this.waitFlg = false;
                } else if (MainScene.this.dispUnit == 1) {
                    MainScene.this.unitSendOut();
                } else {
                    MainScene.this.unitSendOutAfter();
                    MainScene.this.unitContinueFlg = false;
                }
            }
        });
        registerUpdateHandler(this.handlerUnitDisp2);
    }

    private void unitImgOpen(boolean enemy) {
        if (enemy) {
            this.enImg = getBaseActivity().getResourceUtil().getSprite("kodama/" + this.unitSt[1][this.f219us[1]][0] + ".png");
            this.enImg.setPosition(54.0f, 142.0f);
            this.enImg.setFlippedHorizontal(true);
            attachChild(this.enImg);
            return;
        }
        this.unImg = getBaseActivity().getResourceUtil().getSprite("kodama/" + this.unitSt[0][this.f219us[0]][0] + ".png");
        this.unImg.setPosition(358.0f, 142.0f);
        attachChild(this.unImg);
    }

    /* access modifiers changed from: private */
    public void unitImgCloseEffect(int enemy) {
        if (enemy == 1) {
            this.enImg.registerEntityModifier(new ParallelEntityModifier(new FadeOutModifier(0.5f), new ScaleModifier(0.5f, 1.0f, 0.0f)));
            return;
        }
        this.unImg.registerEntityModifier(new ParallelEntityModifier(new FadeOutModifier(0.5f), new ScaleModifier(0.5f, 1.0f, 0.0f)));
    }

    private void spellListOpen() {
        String str;
        this.bottomMode = 101;
        String str2 = "";
        Cursor cursor = null;
        int posX = 0;
        int posY = MainActivity.CAMERA_WIDTH;
        for (int i = 0; i < 8; i++) {
            boolean ngFlg = false;
            boolean tiredFlg = false;
            if (2 > i || i > 5) {
                this.boxBgList.add(i, getBaseActivity().getResourceUtil().getSprite("battle/base_spell_s.png"));
            } else {
                this.boxBgList.add(i, getBaseActivity().getResourceUtil().getSprite("battle/base_spell.png"));
            }
            ((Sprite) this.boxBgList.get(i)).setPosition((float) posX, (float) posY);
            attachChild((IEntity) this.boxBgList.get(i));
            if (2 > i || i > 5) {
                if (i < 2) {
                    this.unitBoxAttr1.add(i, getBaseActivity().getResourceUtil().getSprite("attr/s/" + this.unitSt[0][this.f219us[0]][i + 15] + ".png"));
                    if (this.unitSt[0][this.f219us[0]][i + 15] == 30) {
                        str2 = "";
                        ngFlg = true;
                    } else {
                        str2 = "　通常攻撃";
                    }
                } else if (i == 6) {
                    this.unitBoxAttr1.add(i, getBaseActivity().getResourceUtil().getSprite("attr/s/11.png"));
                    str2 = "　やすむ\nVPを少し回復";
                    if (this.unitSt[0][this.f219us[0]][11] < this.unitSt[0][this.f219us[0]][10]) {
                        tiredFlg = true;
                    }
                } else {
                    this.unitBoxAttr1.add(i, getBaseActivity().getResourceUtil().getSprite("attr/s/10.png"));
                    str2 = "";
                }
            } else if (this.unitSt[0][this.f219us[0]][i - 1] == 0) {
                this.unitBoxAttr1.add(i, getBaseActivity().getResourceUtil().getSprite("attr/s/10.png"));
                str2 = "";
                ngFlg = true;
            } else {
                cursor = this.f216db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, t.memo FROM spell_m m LEFT OUTER JOIN spell_text_m t ON m.code=t.code WHERE m.spell_id=" + this.unitSt[0][this.f219us[0]][i - 1], null);
                if (cursor.moveToFirst()) {
                    this.unitBoxAttr1.add(i, getBaseActivity().getResourceUtil().getSprite("attr/s/" + cursor.getInt(1) + ".png"));
                    String str3 = cursor.getString(0);
                    if (9 < str3.length()) {
                        str3 = str3.substring(0, 9) + "...";
                    }
                    String str4 = "　" + str3 + "\n";
                    if (cursor.getInt(2) == 0 || cursor.getInt(2) == 999) {
                        str = str4 + "威力―";
                    } else {
                        str = str4 + "威力" + cursor.getInt(2);
                    }
                    int tmp = cursor.getInt(3);
                    if (this.unitSt[0][this.f219us[0]][17] == 200033) {
                        tmp = Math.round((float) ((tmp * 120) / 100));
                    }
                    str2 = (str + "／消費" + tmp) + "\n" + cursor.getString(4);
                    if (this.unitSt[0][this.f219us[0]][10] < cursor.getInt(3)) {
                        tiredFlg = true;
                    }
                } else {
                    popAlert("データ取得エラー", "spellListOpen/spell_m：" + this.unitSt[0][this.f219us[0]][i - 1] + "\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                }
            }
            ((Sprite) this.unitBoxAttr1.get(i)).setPosition((float) (posX + 12), (float) (posY + 12));
            attachChild((IEntity) this.unitBoxAttr1.get(i));
            this.unitBoxText[i] = new Text((float) (posX + 12), (float) (posY + 12), (IFont) this.fontBlack, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.unitBoxText[i]);
            if (i == 7) {
                this.unitBoxBt1.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/retire.png", "battle/button/retire_p.png"));
                ((ButtonSprite) this.unitBoxBt1.get(i)).setTag(99999994);
            } else if (tiredFlg) {
                this.unitBoxBt1.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/use_ng.png", "battle/button/use_ng.png"));
                ((ButtonSprite) this.unitBoxBt1.get(i)).setTag(99999999);
            } else if (ngFlg) {
                this.unitBoxBt1.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/clear.png", "battle/button/clear.png"));
                ((ButtonSprite) this.unitBoxBt1.get(i)).setTag(99999999);
            } else {
                this.unitBoxBt1.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/use.png", "battle/button/use_p.png"));
                ((ButtonSprite) this.unitBoxBt1.get(i)).setTag(10000000 + i + 10);
            }
            if (2 > i || i > 5) {
                ((ButtonSprite) this.unitBoxBt1.get(i)).setPosition((float) (posX + 160), (float) (posY + 10));
            } else {
                ((ButtonSprite) this.unitBoxBt1.get(i)).setPosition((float) (posX + 160), (float) (posY + 80));
            }
            ((ButtonSprite) this.unitBoxBt1.get(i)).setOnClickListener(this);
            attachChild((IEntity) this.unitBoxBt1.get(i));
            registerTouchArea((ITouchArea) this.unitBoxBt1.get(i));
            posX += 270;
            if (540 <= posX) {
                posX = 0;
                if (i == 1) {
                    posY += 70;
                } else {
                    posY += 140;
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private void spellListClose() {
        for (int i = 0; i < 8; i++) {
            ((Sprite) this.boxBgList.get(i)).detachSelf();
            ((Sprite) this.unitBoxAttr1.get(i)).detachSelf();
            this.unitBoxText[i].detachSelf();
            ((ButtonSprite) this.unitBoxBt1.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.unitBoxBt1.get(i));
        }
    }

    private void unitListOpen() {
        String str;
        this.bottomMode = 102;
        int posX = 0;
        int posY = MainActivity.CAMERA_WIDTH;
        for (int i = 0; i < 6; i++) {
            if (this.unitSt[0][i][8] > 0 || this.unitSt[0][i][0] == 0) {
                this.boxBgList.add(i, getBaseActivity().getResourceUtil().getSprite("battle/base_unit.png"));
            } else {
                this.boxBgList.add(i, getBaseActivity().getResourceUtil().getSprite("battle/base_unit_f.png"));
            }
            ((Sprite) this.boxBgList.get(i)).setPosition((float) posX, (float) posY);
            attachChild((IEntity) this.boxBgList.get(i));
            this.unitBoxImg.add(i, getBaseActivity().getResourceUtil().getSprite("kodama_s/" + this.unitSt[0][i][0] + ".png"));
            ((Sprite) this.unitBoxImg.get(i)).setPosition((float) (posX + 20), (float) (posY + 12));
            attachChild((IEntity) this.unitBoxImg.get(i));
            this.unitBoxAttr1.add(i, getBaseActivity().getResourceUtil().getSprite("attr/s/" + this.unitSt[0][i][15] + ".png"));
            this.unitBoxAttr2.add(i, getBaseActivity().getResourceUtil().getSprite("attr/s/" + this.unitSt[0][i][16] + ".png"));
            ((Sprite) this.unitBoxAttr1.get(i)).setPosition((float) (posX + 12), (float) (posY + 12));
            ((Sprite) this.unitBoxAttr2.get(i)).setPosition((float) (posX + 12), (float) (posY + 36));
            attachChild((IEntity) this.unitBoxAttr1.get(i));
            attachChild((IEntity) this.unitBoxAttr2.get(i));
            if (this.unitSt[0][i][0] != 0) {
                str = "HP:" + this.unitSt[0][i][8] + "／" + this.unitSt[0][i][9] + "\nVP:" + this.unitSt[0][i][10] + "／" + this.unitSt[0][i][11];
            } else {
                str = "";
            }
            this.unitBoxText[i] = new Text((float) (posX + 90), (float) (posY + 20), (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.unitBoxText[i]);
            if (this.f219us[0] != i || this.f219us[1] >= 0) {
                if (this.f219us[0] == i || this.unitSt[0][i][8] <= 0) {
                    this.unitBoxBt1.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/clear.png", "battle/button/clear.png"));
                    ((ButtonSprite) this.unitBoxBt1.get(i)).setTag(99999999);
                } else {
                    this.unitBoxBt1.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/bottom1.png", "battle/button/bottom1_p.png"));
                    ((ButtonSprite) this.unitBoxBt1.get(i)).setTag(10000000 + i);
                }
            } else if (this.unitSt[0][i][8] <= 0) {
                this.unitBoxBt1.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/bottom3_ng.png", "battle/button/bottom3_ng.png"));
                ((ButtonSprite) this.unitBoxBt1.get(i)).setTag(99999999);
            } else {
                this.unitBoxBt1.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/bottom3.png", "battle/button/bottom3_p.png"));
                ((ButtonSprite) this.unitBoxBt1.get(i)).setTag(10000000 + i);
            }
            if (this.unitSt[0][i][8] > 0) {
                this.unitBoxBt2.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/bottom2.png", "battle/button/bottom2_p.png"));
                ((ButtonSprite) this.unitBoxBt2.get(i)).setTag(20000000 + i);
            } else {
                this.unitBoxBt2.add(i, getBaseActivity().getResourceUtil().getButtonSprite("battle/button/clear.png", "battle/button/clear.png"));
                ((ButtonSprite) this.unitBoxBt2.get(i)).setTag(99999999);
            }
            ((ButtonSprite) this.unitBoxBt1.get(i)).setPosition((float) (posX + 58), (float) (posY + 80));
            ((ButtonSprite) this.unitBoxBt2.get(i)).setPosition((float) (posX + 160), (float) (posY + 80));
            ((ButtonSprite) this.unitBoxBt1.get(i)).setOnClickListener(this);
            ((ButtonSprite) this.unitBoxBt2.get(i)).setOnClickListener(this);
            attachChild((IEntity) this.unitBoxBt1.get(i));
            attachChild((IEntity) this.unitBoxBt2.get(i));
            registerTouchArea((ITouchArea) this.unitBoxBt1.get(i));
            registerTouchArea((ITouchArea) this.unitBoxBt2.get(i));
            posX += 270;
            if (540 <= posX) {
                posX = 0;
                posY += 140;
            }
        }
    }

    private void unitListClose() {
        for (int i = 0; i < 6; i++) {
            ((Sprite) this.boxBgList.get(i)).detachSelf();
            ((Sprite) this.unitBoxImg.get(i)).detachSelf();
            ((Sprite) this.unitBoxAttr1.get(i)).detachSelf();
            ((Sprite) this.unitBoxAttr2.get(i)).detachSelf();
            this.unitBoxText[i].detachSelf();
            ((ButtonSprite) this.unitBoxBt1.get(i)).detachSelf();
            ((ButtonSprite) this.unitBoxBt2.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.unitBoxBt1.get(i));
            unregisterTouchArea((ITouchArea) this.unitBoxBt2.get(i));
        }
    }

    private void attrTableOpen() {
        this.attrTableOpenFlg = true;
        this.attrDisplayFlg = true;
        if (this.consecutiveFlg) {
            this.buttonCons.detachSelf();
            unregisterTouchArea(this.buttonCons);
            this.consecutiveFlg = false;
        }
        this.attrTable = getBaseActivity().getResourceUtil().getSprite("attr/table.png");
        this.attrTable.setPosition(0.0f, 420.0f);
        attachChild(this.attrTable);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        this.buttonOk.setPosition(340.0f, 356.0f);
        this.buttonOk.setTag(99999999);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
    }

    private void attrTableClose() {
        this.attrTable.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        if (this.f219us[0] >= 0 && this.f219us[1] < 0) {
            if (this.unitSt[0][this.f219us[0]][8] <= 0) {
                this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/consecutive_ng.png", "battle/button/consecutive_ng.png");
                this.buttonCons.setTag(99999999);
            } else {
                this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/consecutive.png", "battle/button/consecutive_p.png");
                this.buttonCons.setTag(10000000 + this.f219us[0]);
            }
            this.buttonCons.setPosition(360.0f, 336.0f);
            this.buttonCons.setOnClickListener(this);
            attachChild(this.buttonCons);
            registerTouchArea(this.buttonCons);
            this.consecutiveFlg = true;
        }
        this.attrTableOpenFlg = false;
    }

    private void logOpen() {
        this.logOpenFlg = true;
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/informationL.png");
        placeToCenterX(this.informationBox, 20.0f);
        attachChild(this.informationBox);
        this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) "【1ターン前】\n" + this.messageLog + "\n\n【2ターン前】\n" + this.messageLog2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        if (this.consecutiveFlg) {
            this.buttonCons.detachSelf();
            unregisterTouchArea(this.buttonCons);
            this.consecutiveFlg = false;
        }
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        this.buttonOk.setPosition(170.0f, 800.0f);
        this.buttonOk.setTag(99999999);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
    }

    private void logClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        if (this.f219us[1] < 0) {
            if (this.unitSt[0][this.f219us[0]][8] <= 0) {
                this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/consecutive_ng.png", "battle/button/consecutive_ng.png");
                this.buttonCons.setTag(99999999);
            } else {
                this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/consecutive.png", "battle/button/consecutive_p.png");
                this.buttonCons.setTag(10000000 + this.f219us[0]);
            }
            this.buttonCons.setPosition(360.0f, 336.0f);
            this.buttonCons.setOnClickListener(this);
            attachChild(this.buttonCons);
            registerTouchArea(this.buttonCons);
            this.consecutiveFlg = true;
        }
        this.logOpenFlg = false;
    }

    public void battlePreProc(int act) {
        this.battleMode = 21;
        this.waitFlg = true;
        if (this.messageLogFlg) {
            this.messageLogFlg = false;
            this.buttonLog.detachSelf();
            unregisterTouchArea(this.buttonLog);
        }
        this.messageLog2 = this.messageLog;
        this.messageLog = "";
        this.cartainBottom = new Rectangle(0.0f, 476.0f, 540.0f, 484.0f, getBaseActivity().getVertexBufferObjectManager());
        this.cartainBottom.setColor(0.0f, 0.0f, 0.0f);
        this.cartainBottom.setAlpha(0.5f);
        attachChild(this.cartainBottom);
        this.messageBox = getBaseActivity().getResourceUtil().getSprite("battle/message_box.png");
        this.messageBox.setPosition(0.0f, 0.0f);
        attachChild(this.messageBox);
        this.messageBoxFlg = true;
        for (int i = 0; i < 2; i++) {
            this.ind[i] = 0;
            this.ord[i] = i;
            this.spl[i] = 0;
            this.skc[i] = 0;
            this.spn[i] = "";
            for (int s = 0; s < 5; s++) {
                this.spd[i][s] = 0;
            }
        }
        for (int i2 = 0; i2 < 4; i2++) {
            for (int s2 = 0; s2 < 5; s2++) {
                this.espd[i2][s2] = 0;
            }
        }
        if (act < 10) {
            this.ind[0] = act;
        } else {
            this.ind[0] = 10;
            if (act == 16) {
                this.spl[0] = 1;
            } else if (act == 10 || act == 11) {
                this.spl[0] = 100000 + this.unitSt[0][this.f219us[0]][act + 5];
            } else {
                this.spl[0] = this.unitSt[0][this.f219us[0]][act - 11];
            }
        }
        Cursor cursor = this.f216db.rawQuery("SELECT m.skill_id, m.code FROM skill_m m WHERE m.skill_id=" + this.unitSt[0][this.f219us[0]][5] + " OR m.skill_id=" + this.unitSt[1][this.f219us[1]][5], null);
        while (cursor.moveToNext()) {
            for (int i3 = 0; i3 < 2; i3++) {
                if (cursor.getInt(0) == this.unitSt[i3][this.f219us[i3]][5]) {
                    this.skc[i3] = cursor.getInt(1);
                }
            }
        }
        this.ind[1] = -1;
        int tmp = this.rnd.nextInt(100);
        if ((this.unitSt[1][this.f219us[1]][10] < this.unitSt[1][this.f219us[1]][11] / 2 && tmp < 20) || ((this.unitSt[1][this.f219us[1]][10] < this.unitSt[1][this.f219us[1]][11] / 4 && tmp < 50) || (this.unitSt[1][this.f219us[1]][10] < this.unitSt[1][this.f219us[1]][11] / 10 && tmp < 80))) {
            int i4 = 0;
            while (true) {
                if (i4 >= 100) {
                    break;
                }
                int s3 = this.rnd.nextInt(6);
                if (this.f219us[1] != s3 && this.unitSt[1][s3][8] > 0) {
                    this.ind[1] = s3;
                    break;
                }
                i4++;
            }
        }
        if (this.ind[1] < 0) {
            this.ind[1] = 10;
            int i5 = 0;
            cursor = this.f216db.rawQuery("SELECT m.attr, m.atk, m.vp, m.code, m.spell_id FROM spell_m m WHERE m.spell_id<>0 AND m.vp<=" + this.unitSt[1][this.f219us[1]][10] + " AND (m.spell_id=" + this.unitSt[1][this.f219us[1]][1] + " OR m.spell_id=" + this.unitSt[1][this.f219us[1]][2] + " OR m.spell_id=" + this.unitSt[1][this.f219us[1]][3] + " OR m.spell_id=" + this.unitSt[1][this.f219us[1]][4] + ")", null);
            while (cursor.moveToNext()) {
                for (int s4 = 0; s4 < 5; s4++) {
                    this.espd[i5][s4] = cursor.getInt(s4);
                }
                i5++;
                if (4 <= i5) {
                    break;
                }
            }
            for (int i6 = 0; i6 < 4; i6++) {
                if (attrCheck(this.espd[i6][0], 0, this.f219us[0], true) == 0 && this.espd[i6][1] != 0) {
                    for (int s5 = 0; s5 < 5; s5++) {
                        this.espd[i6][s5] = 0;
                    }
                }
            }
            if (this.spl[1] == 0 && this.rnd.nextInt(100) < 75 && this.btfl[1][0] <= 1) {
                int i7 = 0;
                while (true) {
                    if (i7 < 4) {
                        if (10 < this.espd[i7][3] && this.espd[i7][3] < 20) {
                            this.spl[1] = this.espd[i7][4];
                            break;
                        }
                        i7++;
                    } else {
                        break;
                    }
                }
            }
            if (this.spl[1] == 0 && this.unitSt[0][this.f219us[0]][8] < this.unitSt[0][this.f219us[0]][9] / 5) {
                int i8 = 0;
                while (true) {
                    if (i8 < 4) {
                        if (30 < this.espd[i8][3] && this.espd[i8][3] < 40) {
                            this.spl[1] = this.espd[i8][4];
                            break;
                        }
                        i8++;
                    } else {
                        break;
                    }
                }
            }
            if (this.spl[1] == 0 && this.rnd.nextInt(100) < 80) {
                int i9 = 0;
                while (true) {
                    if (i9 >= 4) {
                        break;
                    }
                    int tmp2 = attrCheck(this.espd[i9][0], 0, this.f219us[0], true);
                    if (this.espd[i9][1] != 0 && 6 <= tmp2) {
                        this.spl[1] = this.espd[i9][4];
                        break;
                    }
                    i9++;
                }
            }
            if (this.spl[1] == 0 && this.rnd.nextInt(100) < 80) {
                int i10 = 0;
                while (true) {
                    if (i10 < 2) {
                        if (6 <= attrCheck(this.unitSt[1][this.f219us[1]][i10 + 15], 0, this.f219us[0], true) && this.unitSt[1][this.f219us[1]][i10 + 15] != 30) {
                            this.spl[1] = 100000 + this.unitSt[1][this.f219us[1]][i10 + 15];
                            break;
                        }
                        i10++;
                    } else {
                        break;
                    }
                }
            }
            if (this.spl[1] == 0 && this.rnd.nextInt(100) < 80) {
                int i11 = 0;
                while (true) {
                    if (i11 >= 4) {
                        break;
                    }
                    int tmp3 = attrCheck(this.espd[i11][0], 0, this.f219us[0], true);
                    if (this.espd[i11][1] != 0 && tmp3 == 5) {
                        this.spl[1] = this.espd[i11][4];
                        break;
                    }
                    i11++;
                }
            }
            if (this.spl[1] == 0) {
                int i12 = 0;
                while (true) {
                    if (i12 < 2) {
                        if (attrCheck(this.unitSt[1][this.f219us[1]][i12 + 15], 0, this.f219us[0], true) == 5 && this.unitSt[1][this.f219us[1]][i12 + 15] != 30) {
                            this.spl[1] = 100000 + this.unitSt[1][this.f219us[1]][i12 + 15];
                            break;
                        }
                        i12++;
                    } else {
                        break;
                    }
                }
            }
            if (this.spl[1] == 0 && this.rnd.nextInt(100) < 80) {
                int i13 = 0;
                while (true) {
                    if (i13 >= 4) {
                        break;
                    }
                    int tmp4 = attrCheck(this.espd[i13][0], 0, this.f219us[0], true);
                    if (this.espd[i13][1] != 0 && tmp4 != 0) {
                        this.spl[1] = this.espd[i13][4];
                        break;
                    }
                    i13++;
                }
            }
            if (this.spl[1] == 0) {
                int i14 = 0;
                while (true) {
                    if (i14 < 2) {
                        if (attrCheck(this.unitSt[1][this.f219us[1]][i14 + 15], 0, this.f219us[0], true) != 0 && this.unitSt[1][this.f219us[1]][i14 + 15] != 30) {
                            this.spl[1] = 100000 + this.unitSt[1][this.f219us[1]][i14 + 15];
                            break;
                        }
                        i14++;
                    } else {
                        break;
                    }
                }
            }
            if (this.spl[1] == 0) {
                this.spl[1] = 1;
            }
        }
        for (int i15 = 0; i15 < 2; i15++) {
            if (this.spl[i15] != 0) {
                cursor = this.f216db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, m.code, m.effect FROM spell_m m WHERE m.spell_id=" + this.spl[i15], null);
                if (cursor.moveToNext()) {
                    this.spn[i15] = cursor.getString(0);
                    for (int s6 = 0; s6 < 5; s6++) {
                        this.spd[i15][s6] = cursor.getInt(s6 + 1);
                    }
                } else {
                    popAlert("データ取得エラー", "spellListOpen/spell_m\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                }
            }
        }
        if (this.ind[0] < 10) {
            this.ord[0] = 0;
            this.ord[1] = 1;
        } else if (this.ind[1] < 10) {
            this.ord[0] = 1;
            this.ord[1] = 0;
        } else {
            int[] priority = new int[2];
            for (int i16 = 0; i16 < 2; i16++) {
                if (11 <= this.spd[i16][3] && this.spd[i16][3] < 20) {
                    priority[i16] = 4;
                } else if (this.spd[i16][3] == 31) {
                    priority[i16] = 1;
                } else if (1000 < this.spd[i16][3]) {
                    priority[i16] = 1;
                } else if (300 < this.spd[i16][3] && this.spd[i16][3] < 400) {
                    priority[i16] = 1;
                } else if (this.spd[i16][3] == 32) {
                    priority[i16] = -1;
                } else if (50 >= this.spd[i16][3] || this.spd[i16][3] >= 60) {
                    priority[i16] = 0;
                } else {
                    priority[i16] = -3;
                }
            }
            if (priority[1] < priority[0]) {
                this.ord[0] = 0;
                this.ord[1] = 1;
            } else if (priority[0] < priority[1]) {
                this.ord[0] = 1;
                this.ord[1] = 0;
            } else if (this.unitSt[1][this.f219us[1]][14] < this.unitSt[0][this.f219us[0]][14]) {
                this.ord[0] = 0;
                this.ord[1] = 1;
            } else if (this.unitSt[0][this.f219us[0]][14] < this.unitSt[1][this.f219us[1]][14]) {
                this.ord[0] = 1;
                this.ord[1] = 0;
            } else if (this.rnd.nextInt(2) == 0) {
                this.ord[0] = 0;
                this.ord[1] = 1;
            } else {
                this.ord[0] = 1;
                this.ord[1] = 0;
            }
        }
        this.f218o = 0;
        battleExe();
        if (cursor != null) {
            cursor.close();
        }
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: type inference failed for: r15v0, types: [kanatamikado.ae.reiki.MainScene] */
    private void battleExe() {
        int tmp;
        int tmp2;
        this.f214a = this.ord[this.f218o];
        this.f215b = Math.abs(this.f214a - 1);
        if (this.ind[this.f214a] < 10) {
            this.messageStr = this.userName[this.f214a] + "は" + this.unitName[this.f214a][this.f219us[this.f214a]] + "を戻して、\n" + this.unitName[this.f214a][this.ind[this.f214a]] + "を繰り出した！\n";
            this.f219us[this.f214a] = this.ind[this.f214a];
            unitCopy(this.f214a);
            this.messageLog += this.messageStr + "\n";
            this.messageText.detachSelf();
            this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.messageText);
            this.unitChangeFlg = true;
            unitImgCloseEffect(this.f214a);
            this.handlerBattleUnitChange = new TimerHandler(0.6f, new ITimerCallback() {
                public void onTimePassed(TimerHandler pTimerHandler) {
                    if (MainScene.this.f214a == 0) {
                        MainScene.this.unImg.detachSelf();
                    } else {
                        MainScene.this.enImg.detachSelf();
                    }
                    MainScene.this.unitImgOpenEffect(MainScene.this.f214a);
                    Cursor cursor = MainScene.this.f216db.rawQuery("SELECT m.skill_id, m.code FROM skill_m m WHERE m.skill_id=" + MainScene.this.unitSt[MainScene.this.f214a][MainScene.this.f219us[MainScene.this.f214a]][5], null);
                    if (cursor.moveToNext()) {
                        MainScene.this.skc[MainScene.this.f214a] = cursor.getInt(1);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    MainScene.this.unitStatusClose();
                    MainScene.this.unitStatusOpen();
                }
            });
            registerUpdateHandler(this.handlerBattleUnitChange);
            return;
        }
        boolean tiredFlg = false;
        this.messageStr = this.unitName[this.f214a][this.f219us[this.f214a]] + "の「" + this.spn[this.f214a] + "」！\n";
        int tmp3 = this.spd[this.f214a][2];
        if (this.unitSt[this.f214a][this.f219us[this.f214a]][17] == 200033) {
            tmp3 = Math.round((float) ((tmp3 * 120) / 100));
        }
        if (this.unitSt[this.f214a][this.f219us[this.f214a]][10] < tmp3) {
            this.messageStr += "しかしＶＰ不足で失敗した。\n";
            tiredFlg = true;
        } else {
            int[] iArr = this.unitSt[this.f214a][this.f219us[this.f214a]];
            iArr[10] = iArr[10] - tmp3;
            if (this.unitSt[this.f214a][this.f219us[this.f214a]][10] < 0) {
                this.unitSt[this.f214a][this.f219us[this.f214a]][10] = 0;
            }
            unitStatusClose();
            unitStatusOpen();
            if (this.spd[this.f214a][1] > 0) {
                if (this.skc[this.f214a] == 2001 && this.spd[this.f214a][0] != 11 && this.rnd.nextInt(100) < this.unitSt[this.f214a][this.f219us[this.f214a]][6] * 20) {
                    tmp3 = this.spd[this.f214a][0];
                    if (!(this.unitSt[this.f214a][this.f219us[this.f214a]][15] == tmp3 || this.unitSt[this.f214a][this.f219us[this.f214a]][16] == tmp3)) {
                        this.messageStr += this.unitName[this.f214a][this.f219us[this.f214a]] + "の属性２が" + this.attrs[tmp3 - 10] + "に変化した！\n";
                        this.unitSt[this.f214a][this.f219us[this.f214a]][16] = tmp3;
                    }
                }
                if ((300 < this.spd[this.f214a][3] && this.spd[this.f214a][3] < 400) || (600 < this.spd[this.f214a][3] && this.spd[this.f214a][3] < 700)) {
                    tmp3 = this.spd[this.f214a][3] % 100;
                    this.messageStr += this.unitName[this.f214a][this.f219us[this.f214a]] + "の属性２が" + this.attrs[tmp3 - 10] + "に変化した！\n";
                    this.unitSt[this.f214a][this.f219us[this.f214a]][16] = tmp3;
                    if ((this.unitSt[this.f214a][this.f219us[this.f214a]][0] == 306 || this.unitSt[this.f214a][this.f219us[this.f214a]][0] == 307) && tmp3 == 25) {
                        this.unitSt[this.f214a][this.f219us[this.f214a]][0] = 308;
                    } else if ((this.unitSt[this.f214a][this.f219us[this.f214a]][0] == 306 || this.unitSt[this.f214a][this.f219us[this.f214a]][0] == 308) && tmp3 == 27) {
                        this.unitSt[this.f214a][this.f219us[this.f214a]][0] = 307;
                    }
                    if (this.f214a == 0) {
                        this.unImg.detachSelf();
                        unitImgOpen(false);
                    } else {
                        this.enImg.detachSelf();
                        unitImgOpen(true);
                    }
                }
                if (this.spd[this.f214a][3] == 51) {
                    this.damage = (float) (((double) this.preDamage) * 1.5d);
                } else if (this.spd[this.f214a][3] == 52) {
                    this.damage = (float) (((double) this.preDamage) * 1.8d);
                } else if (this.spd[this.f214a][3] == 71) {
                    this.damage = (float) Math.round((float) (this.unitSt[this.f215b][this.f219us[this.f215b]][8] / 2));
                } else {
                    if (this.unitSt[this.f215b][this.f219us[this.f215b]][13] <= 0) {
                        this.damage = 99999.0f;
                    } else {
                        this.damage = ((float) ((((((((double) this.unitSt[this.f214a][this.f219us[this.f214a]][7]) * 0.4d) + 2.0d) * ((double) this.spd[this.f214a][1])) * ((double) this.unitSt[this.f214a][this.f219us[this.f214a]][12])) / ((double) this.unitSt[this.f215b][this.f219us[this.f215b]][13])) / 50.0d)) + 2.0f;
                    }
                    if (this.unitSt[this.f214a][this.f219us[this.f214a]][15] == this.spd[this.f214a][0] || this.unitSt[this.f214a][this.f219us[this.f214a]][16] == this.spd[this.f214a][0] || ((this.skc[this.f214a] == 2002 || this.skc[this.f214a] == 2003 || this.skc[this.f214a] == 6000) && this.rnd.nextInt(100) < this.unitSt[this.f214a][this.f219us[this.f214a]][6] * 20)) {
                        this.damage = (float) (((double) this.damage) * 1.5d);
                    }
                    if (this.spd[this.f214a][3] == 401) {
                        this.spd[this.f214a][0] = this.unitSt[this.f215b][this.f219us[this.f215b]][15];
                        if (this.spd[this.f214a][0] == 11) {
                            this.spd[this.f214a][4] = 25;
                        } else if (this.spd[this.f214a][0] == 12) {
                            this.spd[this.f214a][4] = 32;
                        } else if (this.spd[this.f214a][0] == 13) {
                            this.spd[this.f214a][4] = 18;
                        } else if (this.spd[this.f214a][0] == 14) {
                            this.spd[this.f214a][4] = 120;
                        } else if (this.spd[this.f214a][0] == 15) {
                            this.spd[this.f214a][4] = 13;
                        } else if (this.spd[this.f214a][0] == 16) {
                            this.spd[this.f214a][4] = 30;
                        } else if (this.spd[this.f214a][0] == 17) {
                            this.spd[this.f214a][4] = 34;
                        } else if (this.spd[this.f214a][0] == 18) {
                            this.spd[this.f214a][4] = 33;
                        } else if (this.spd[this.f214a][0] == 19) {
                            this.spd[this.f214a][4] = 15;
                        } else if (this.spd[this.f214a][0] == 20) {
                            this.spd[this.f214a][4] = 35;
                        } else if (this.spd[this.f214a][0] == 21) {
                            this.spd[this.f214a][4] = 24;
                        } else if (this.spd[this.f214a][0] == 22) {
                            this.spd[this.f214a][4] = 29;
                        } else if (this.spd[this.f214a][0] == 23) {
                            this.spd[this.f214a][4] = 12;
                        } else if (this.spd[this.f214a][0] == 24) {
                            this.spd[this.f214a][4] = 36;
                        } else if (this.spd[this.f214a][0] == 25) {
                            this.spd[this.f214a][4] = 11;
                        } else if (this.spd[this.f214a][0] == 26) {
                            this.spd[this.f214a][4] = 14;
                        } else if (this.spd[this.f214a][0] == 27) {
                            this.spd[this.f214a][4] = 17;
                        } else if (this.spd[this.f214a][0] == 28) {
                            this.spd[this.f214a][4] = 28;
                        }
                        this.messageStr += this.attrs[this.spd[this.f214a][0] - 10] + "属性で攻撃！\n";
                    }
                    if (2100 < this.skc[this.f214a] && this.skc[this.f214a] < 2200) {
                        this.damage = (this.damage * ((float) ((this.unitSt[this.f214a][this.f219us[this.f214a]][6] * (this.skc[this.f214a] % 100)) + 100))) / 100.0f;
                    } else if (this.damageReceiveFlg && 2300 < this.skc[this.f214a] && this.skc[this.f214a] < 2400) {
                        this.damage = (this.damage * ((float) ((this.unitSt[this.f214a][this.f219us[this.f214a]][6] * (this.skc[this.f214a] % 100)) + 100))) / 100.0f;
                    }
                    if ((4000 < this.skc[this.f215b] && this.skc[this.f215b] < 4100) || ((4100 < this.skc[this.f215b] && this.skc[this.f215b] < 4200 && this.unitSt[this.f215b][this.f219us[this.f215b]][8] == this.unitSt[this.f215b][this.f219us[this.f215b]][9]) || (4200 < this.skc[this.f215b] && this.skc[this.f215b] < 4300 && this.unitChangeFlg))) {
                        this.damage = (this.damage * ((float) (100 - (this.unitSt[this.f215b][this.f219us[this.f215b]][6] * (this.skc[this.f215b] % 100))))) / 100.0f;
                    }
                    if ((this.spd[this.f214a][0] == 13 && this.skc[this.f215b] == 5132) || ((this.spd[this.f214a][0] == 15 && this.skc[this.f215b] == 5152) || ((this.spd[this.f214a][0] == 16 && this.skc[this.f215b] == 5162) || ((this.spd[this.f214a][0] == 20 && this.skc[this.f215b] == 5202) || ((this.spd[this.f214a][0] == 21 && this.skc[this.f215b] == 5212) || ((this.spd[this.f214a][0] == 26 && this.skc[this.f215b] == 5262) || ((this.spd[this.f214a][0] == 27 && this.skc[this.f215b] == 5272) || (this.spd[this.f214a][0] == 28 && this.skc[this.f215b] == 5282)))))))) {
                        this.messageStr += "相手のスキルによりダメージが大幅に減少した！\n";
                        this.damage = (this.damage * ((float) (100 - (this.unitSt[this.f215b][this.f219us[this.f215b]][6] * 19)))) / 100.0f;
                    }
                    if (this.spd[this.f214a][3] == 81 && this.unitSt[this.f214a][this.f219us[this.f214a]][9] != 0) {
                        this.damage = (this.damage * ((float) (150 - ((this.unitSt[this.f214a][this.f219us[this.f214a]][8] * 50) / this.unitSt[this.f214a][this.f219us[this.f214a]][9])))) / 100.0f;
                    }
                    this.damage = (this.damage * ((float) (this.rnd.nextInt(11) + 90))) / 100.0f;
                    if (this.damage < 1.0f) {
                        this.damage = 1.0f;
                    } else if (99999.0f < this.damage) {
                        this.damage = 99999.0f;
                    }
                    if (Math.round(this.damage) > 0) {
                        int comp = attrCheck(this.spd[this.f214a][0], this.f215b, this.f219us[this.f215b], false);
                        if (5 < comp) {
                            this.messageStr += "効果は抜群だ！\n";
                            if (comp == 7) {
                                this.damage *= 3.0f;
                            }
                            if (comp == 6) {
                                this.damage *= 2.0f;
                            }
                            if (2200 < this.skc[this.f214a] && this.skc[this.f214a] < 2300) {
                                this.damage = (this.damage * ((float) ((this.unitSt[this.f214a][this.f219us[this.f214a]][6] * (this.skc[this.f214a] % 100)) + 100))) / 100.0f;
                            } else if (this.skc[this.f214a] == 2003) {
                                this.damage = (this.damage * ((float) ((this.unitSt[this.f214a][this.f219us[this.f214a]][6] * 10) + 100))) / 100.0f;
                            }
                            if (4300 < this.skc[this.f215b] && this.skc[this.f215b] < 4400) {
                                this.messageStr += "相手のスキルによりダメージが減少した！\n";
                                this.damage = (this.damage * ((float) (100 - (this.unitSt[this.f215b][this.f219us[this.f215b]][6] * (this.skc[this.f215b] % 100))))) / 100.0f;
                            }
                        } else if (comp == 0) {
                            this.messageStr += "あまり効果がないようだ。\n";
                            this.damage = 0.0f;
                        } else if (comp < 5) {
                            this.messageStr += "効果はいまひとつのようだ。\n";
                            if (comp == 4) {
                                this.damage /= 2.0f;
                            }
                            if (comp == 3) {
                                this.damage /= 3.0f;
                            }
                            if (this.damage < 0.0f) {
                                this.damage = 1.0f;
                            }
                        }
                        if (comp > 0 && comp <= 5 && 4400 < this.skc[this.f215b] && this.skc[this.f215b] < 4500) {
                            this.messageStr += "相手のスキルによりダメージが減少した！\n";
                            this.damage = (this.damage * ((float) (100 - (this.unitSt[this.f215b][this.f219us[this.f215b]][6] * (this.skc[this.f215b] % 100))))) / 100.0f;
                        }
                    }
                }
                if (2 <= this.btfl[this.f215b][0]) {
                    this.messageStr += "障壁がダメージを軽減した！\n";
                    this.damage /= 2.0f;
                }
                if (Math.round(this.damage) > 0) {
                    this.messageStr += this.unitName[this.f215b][this.f219us[this.f215b]] + "に" + Math.round(this.damage) + "のダメージ！\n";
                    this.damageReceiveFlg = true;
                    this.preDamage = Math.round(this.damage);
                    int[] iArr2 = this.unitSt[this.f215b][this.f219us[this.f215b]];
                    iArr2[8] = iArr2[8] - Math.round(this.damage);
                    if (this.spd[this.f214a][3] == 21 || this.spd[this.f214a][3] == 22 || this.spd[this.f214a][3] == 23) {
                        if (this.spd[this.f214a][3] == 23) {
                            tmp2 = Math.round(this.damage / 2.0f);
                        } else if (this.spd[this.f214a][3] == 22) {
                            tmp2 = Math.round(this.damage / 3.0f);
                        } else {
                            tmp2 = Math.round(this.damage / 4.0f);
                        }
                        this.messageStr += "更にＨＰを" + tmp + "吸収した！\n";
                        int[] iArr3 = this.unitSt[this.f214a][this.f219us[this.f214a]];
                        iArr3[8] = iArr3[8] + tmp;
                        if (this.unitSt[this.f214a][this.f219us[this.f214a]][9] < this.unitSt[this.f214a][this.f219us[this.f214a]][8]) {
                            this.unitSt[this.f214a][this.f219us[this.f214a]][8] = this.unitSt[this.f214a][this.f219us[this.f214a]][9];
                        }
                    } else if (this.spd[this.f214a][3] == 26 || this.spd[this.f214a][3] == 27 || this.spd[this.f214a][3] == 28) {
                        if (this.spd[this.f214a][3] == 28) {
                            tmp = Math.round(this.damage / 2.0f);
                        } else if (this.spd[this.f214a][3] == 27) {
                            tmp = Math.round(this.damage / 3.0f);
                        } else {
                            tmp = Math.round(this.damage / 4.0f);
                        }
                        this.messageStr += "更にＶＰを" + tmp + "吸収した！\n";
                        int[] iArr4 = this.unitSt[this.f214a][this.f219us[this.f214a]];
                        iArr4[10] = iArr4[10] + tmp;
                        if (this.unitSt[this.f214a][this.f219us[this.f214a]][11] < this.unitSt[this.f214a][this.f219us[this.f214a]][10]) {
                            this.unitSt[this.f214a][this.f219us[this.f214a]][10] = this.unitSt[this.f214a][this.f219us[this.f214a]][11];
                        }
                    }
                    if (3010 < this.skc[this.f214a] && this.skc[this.f214a] < 3020) {
                        tmp = Math.round(((this.damage * ((float) this.unitSt[this.f214a][this.f219us[this.f214a]][6])) * ((float) (this.skc[this.f214a] - 3010))) / 100.0f);
                        this.messageStr += "スキル効果で更にＶＰを" + tmp + "吸収した！\n";
                        int[] iArr5 = this.unitSt[this.f214a][this.f219us[this.f214a]];
                        iArr5[10] = iArr5[10] + tmp;
                        if (this.unitSt[this.f214a][this.f219us[this.f214a]][11] < this.unitSt[this.f214a][this.f219us[this.f214a]][10]) {
                            this.unitSt[this.f214a][this.f219us[this.f214a]][10] = this.unitSt[this.f214a][this.f219us[this.f214a]][11];
                        }
                    }
                    if (this.unitSt[this.f215b][this.f219us[this.f215b]][8] <= 0) {
                        this.unitSt[this.f215b][this.f219us[this.f215b]][8] = 0;
                        this.messageStr += this.unitName[this.f215b][this.f219us[this.f215b]] + "は倒れた！\n";
                        this.f218o = 10;
                        if (((500000 < this.questId && this.questId < 700000) || (800000 < this.questId && this.questId < 900000)) && this.f215b == 0) {
                            Cursor cursor = null;
                            try {
                                Cursor cursor2 = this.f216db.rawQuery("SELECT u.user_kodama_id FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id WHERE p.party_no = " + this.partyNo + " AND p.sort_no = " + (this.f219us[this.f215b] + 1), null);
                                if (cursor2.moveToNext() && cursor2.getInt(0) != 0) {
                                    this.f216db.execSQL("UPDATE user_kodama_t SET faint_flg=1 WHERE user_kodama_id=" + cursor2.getInt(0));
                                }
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                            } catch (Throwable th) {
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                    } else {
                        if (41 <= this.spd[this.f214a][3] && this.spd[this.f214a][3] < 50) {
                            if (this.spd[this.f214a][3] == 44) {
                                tmp = Math.round(this.damage / 3.0f);
                            } else if (this.spd[this.f214a][3] == 43) {
                                tmp = Math.round(this.damage / 4.0f);
                            } else if (this.spd[this.f214a][3] == 42) {
                                tmp = Math.round(this.damage / 6.0f);
                            } else if (this.spd[this.f214a][3] == 41) {
                                tmp = Math.round(this.damage / 8.0f);
                            }
                            this.messageStr += "更にＶＰに" + tmp + "のダメージ！\n";
                            int[] iArr6 = this.unitSt[this.f215b][this.f219us[this.f215b]];
                            iArr6[10] = iArr6[10] - tmp;
                            if (this.unitSt[this.f215b][this.f219us[this.f215b]][10] < 0) {
                                this.unitSt[this.f215b][this.f219us[this.f215b]][10] = 0;
                            }
                        }
                        if (3020 < this.skc[this.f214a] && this.skc[this.f214a] < 3030) {
                            int tmp4 = Math.round(((this.damage * ((float) this.unitSt[this.f214a][this.f219us[this.f214a]][6])) * ((float) (this.skc[this.f214a] - 3020))) / 100.0f);
                            this.messageStr += "スキル効果で更にＶＰに" + tmp4 + "のダメージ！\n";
                            int[] iArr7 = this.unitSt[this.f215b][this.f219us[this.f215b]];
                            iArr7[10] = iArr7[10] - tmp4;
                            if (this.unitSt[this.f215b][this.f219us[this.f215b]][10] < 0) {
                                this.unitSt[this.f215b][this.f219us[this.f215b]][10] = 0;
                            }
                        }
                        if (this.spd[this.f214a][3] == 72 && this.unitSt[this.f215b][this.f219us[this.f215b]][5] != 0) {
                            this.messageStr += "更に相手のスキルを無効化した！";
                            this.unitSt[this.f215b][this.f219us[this.f215b]][5] = 0;
                            this.skc[this.f215b] = 0;
                        }
                        if (this.spd[this.f214a][3] == 161) {
                            debuffExe(1, this.f215b, 12, 20);
                        } else if (this.spd[this.f214a][3] == 162) {
                            debuffExe(1, this.f215b, 13, 20);
                        } else if (this.spd[this.f214a][3] == 163) {
                            debuffExe(1, this.f215b, 14, 20);
                        } else if (this.spd[this.f214a][3] == 164) {
                            debuffExe(1, this.f215b, 12, 20);
                            debuffExe(1, this.f215b, 13, 20);
                        } else if (this.spd[this.f214a][3] == 165) {
                            debuffExe(1, this.f215b, 12, 20);
                            debuffExe(1, this.f215b, 14, 20);
                        } else if (this.spd[this.f214a][3] == 166) {
                            debuffExe(1, this.f215b, 13, 20);
                            debuffExe(1, this.f215b, 14, 20);
                        } else if (this.spd[this.f214a][3] == 167) {
                            debuffExe(1, this.f215b, 12, 20);
                            debuffExe(1, this.f215b, 13, 20);
                            debuffExe(1, this.f215b, 14, 20);
                        } else if (this.spd[this.f214a][3] == 171) {
                            debuffExe(1, this.f215b, 12, 30);
                        } else if (this.spd[this.f214a][3] == 172) {
                            debuffExe(1, this.f215b, 13, 30);
                        } else if (this.spd[this.f214a][3] == 173) {
                            debuffExe(1, this.f215b, 14, 30);
                        } else if (this.spd[this.f214a][3] == 174) {
                            debuffExe(1, this.f215b, 12, 30);
                            debuffExe(1, this.f215b, 13, 30);
                        } else if (this.spd[this.f214a][3] == 175) {
                            debuffExe(1, this.f215b, 12, 30);
                            debuffExe(1, this.f215b, 14, 30);
                        } else if (this.spd[this.f214a][3] == 176) {
                            debuffExe(1, this.f215b, 13, 30);
                            debuffExe(1, this.f215b, 14, 30);
                        } else if (this.spd[this.f214a][3] == 177) {
                            debuffExe(1, this.f215b, 12, 30);
                            debuffExe(1, this.f215b, 13, 30);
                            debuffExe(1, this.f215b, 14, 30);
                        } else if (this.spd[this.f214a][3] == 179) {
                            debuffExe(1, this.f215b, this.rnd.nextInt(3) + 12, 30);
                        } else if (this.spd[this.f214a][3] == 181) {
                            debuffExe(1, this.f215b, 12, 50);
                        } else if (this.spd[this.f214a][3] == 182) {
                            debuffExe(1, this.f215b, 13, 50);
                        } else if (this.spd[this.f214a][3] == 183) {
                            debuffExe(1, this.f215b, 14, 50);
                        } else if (this.spd[this.f214a][3] == 186) {
                            debuffExe(1, this.f215b, 13, 50);
                            debuffExe(1, this.f215b, 14, 50);
                        } else if (this.spd[this.f214a][3] == 191) {
                            debuffExe(1, this.f215b, 12, 80);
                        } else if (this.spd[this.f214a][3] == 192) {
                            debuffExe(1, this.f215b, 13, 80);
                        } else if (this.spd[this.f214a][3] == 193) {
                            debuffExe(1, this.f215b, 14, 80);
                        } else if (this.spd[this.f214a][3] == 1193) {
                            debuffExe(1, this.f215b, 14, 80);
                        } else if (this.spd[this.f214a][3] == 196) {
                            debuffExe(1, this.f215b, 13, 80);
                            debuffExe(1, this.f215b, 14, 80);
                        } else if (this.spd[this.f214a][3] == 210) {
                            this.messageStr += this.unitName[this.f215b][this.f219us[this.f215b]] + "の属性２を無効化した！\n";
                            this.unitSt[this.f215b][this.f219us[this.f215b]][16] = 30;
                        } else if (210 < this.spd[this.f214a][3] && this.spd[this.f214a][3] < 300) {
                            int tmp5 = this.spd[this.f214a][3] % 100;
                            this.messageStr += this.unitName[this.f215b][this.f219us[this.f215b]] + "を" + this.attrs[tmp5 - 10] + "属性に変化させた！\n";
                            this.unitSt[this.f215b][this.f219us[this.f215b]][15] = tmp5;
                            this.unitSt[this.f215b][this.f219us[this.f215b]][16] = 30;
                        }
                    }
                } else {
                    this.messageStr += this.unitName[this.f215b][this.f219us[this.f215b]] + "にダメージを与えられなかった。\n";
                }
                if (this.spd[this.f214a][3] == 73) {
                    this.messageStr += this.unitName[this.f214a][this.f219us[this.f214a]] + "も力尽きた。";
                    this.unitSt[this.f214a][this.f219us[this.f214a]][8] = 0;
                    if (((500000 < this.questId && this.questId < 700000) || (800000 < this.questId && this.questId < 900000)) && this.f214a == 0) {
                        Cursor cursorF = null;
                        try {
                            Cursor cursorF2 = this.f216db.rawQuery("SELECT u.user_kodama_id FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id WHERE p.party_no = " + this.partyNo + " AND p.sort_no = " + (this.f219us[this.f214a] + 1), null);
                            if (cursorF2.moveToFirst() && cursorF2.getInt(0) != 0) {
                                this.f216db.execSQL("UPDATE user_kodama_t SET faint_flg=1 WHERE user_kodama_id=" + cursorF2.getInt(0));
                            }
                            if (cursorF2 != null) {
                                cursorF2.close();
                            }
                        } catch (Throwable th2) {
                            if (cursorF != null) {
                                cursorF.close();
                            }
                            throw th2;
                        }
                    }
                }
                if (this.spd[this.f214a][3] == 101) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 10);
                } else if (this.spd[this.f214a][3] == 102) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 10);
                } else if (this.spd[this.f214a][3] == 103) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 14, 10);
                } else if (this.spd[this.f214a][3] == 104) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 10);
                    buffExe(this.f214a, 13, 10);
                } else if (this.spd[this.f214a][3] == 105) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 10);
                    buffExe(this.f214a, 14, 10);
                } else if (this.spd[this.f214a][3] == 106) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 10);
                    buffExe(this.f214a, 14, 10);
                } else if (this.spd[this.f214a][3] == 107) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 10);
                    buffExe(this.f214a, 13, 10);
                    buffExe(this.f214a, 14, 10);
                } else if (this.spd[this.f214a][3] == 111) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 20);
                } else if (this.spd[this.f214a][3] == 112) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 20);
                } else if (this.spd[this.f214a][3] == 113) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 14, 20);
                } else if (this.spd[this.f214a][3] == 114) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 20);
                    buffExe(this.f214a, 13, 20);
                } else if (this.spd[this.f214a][3] == 115) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 20);
                    buffExe(this.f214a, 14, 20);
                } else if (this.spd[this.f214a][3] == 116) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 20);
                    buffExe(this.f214a, 14, 20);
                } else if (this.spd[this.f214a][3] == 117) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 20);
                    buffExe(this.f214a, 13, 20);
                    buffExe(this.f214a, 14, 20);
                } else if (this.spd[this.f214a][3] == 121) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 30);
                } else if (this.spd[this.f214a][3] == 122) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 30);
                } else if (this.spd[this.f214a][3] == 123) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 14, 30);
                } else if (this.spd[this.f214a][3] == 124) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 30);
                    buffExe(this.f214a, 13, 30);
                } else if (this.spd[this.f214a][3] == 125) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 30);
                    buffExe(this.f214a, 14, 30);
                } else if (this.spd[this.f214a][3] == 126) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 30);
                    buffExe(this.f214a, 14, 30);
                } else if (this.spd[this.f214a][3] == 129) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, this.rnd.nextInt(3) + 12, 30);
                } else if (this.spd[this.f214a][3] == 131) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 50);
                } else if (this.spd[this.f214a][3] == 132) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 50);
                } else if (this.spd[this.f214a][3] == 133) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 14, 50);
                } else if (this.spd[this.f214a][3] == 134) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 50);
                    buffExe(this.f214a, 13, 50);
                } else if (this.spd[this.f214a][3] == 135) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 50);
                    buffExe(this.f214a, 14, 50);
                } else if (this.spd[this.f214a][3] == 136) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 50);
                    buffExe(this.f214a, 14, 50);
                } else if (this.spd[this.f214a][3] == 139) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, this.rnd.nextInt(3) + 12, 50);
                } else if (this.spd[this.f214a][3] == 141) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 100);
                } else if (this.spd[this.f214a][3] == 142) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 100);
                } else if (this.spd[this.f214a][3] == 143) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 14, 100);
                } else if (this.spd[this.f214a][3] == 144) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 100);
                    buffExe(this.f214a, 13, 100);
                } else if (this.spd[this.f214a][3] == 145) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 12, 100);
                    buffExe(this.f214a, 14, 100);
                } else if (this.spd[this.f214a][3] == 146) {
                    this.messageStr += "更に";
                    buffExe(this.f214a, 13, 100);
                    buffExe(this.f214a, 14, 100);
                }
                if (this.spd[this.f214a][3] == 501) {
                    debuffExe(2, this.f214a, 12, 30);
                } else if (this.spd[this.f214a][3] == 502) {
                    debuffExe(2, this.f214a, 13, 30);
                } else if (this.spd[this.f214a][3] == 503) {
                    debuffExe(2, this.f214a, 14, 30);
                } else if (this.spd[this.f214a][3] == 511) {
                    debuffExe(2, this.f214a, 12, 50);
                } else if (this.spd[this.f214a][3] == 512) {
                    debuffExe(2, this.f214a, 13, 50);
                } else if (this.spd[this.f214a][3] == 513) {
                    debuffExe(2, this.f214a, 14, 50);
                } else if (this.spd[this.f214a][3] == 521) {
                    debuffExe(2, this.f214a, 12, 80);
                } else if (this.spd[this.f214a][3] == 522) {
                    debuffExe(2, this.f214a, 13, 80);
                } else if (this.spd[this.f214a][3] == 523) {
                    debuffExe(2, this.f214a, 14, 80);
                }
            } else if (this.spd[this.f214a][3] == 1) {
                int tmp6 = Math.round((float) (this.unitSt[this.f214a][this.f219us[this.f214a]][11] / 5));
                this.messageStr += this.unitName[this.f214a][this.f219us[this.f214a]] + "のＶＰが" + tmp6 + "回復した。\n";
                int[] iArr8 = this.unitSt[this.f214a][this.f219us[this.f214a]];
                iArr8[10] = iArr8[10] + tmp6;
                if (this.unitSt[this.f214a][this.f219us[this.f214a]][11] < this.unitSt[this.f214a][this.f219us[this.f214a]][10]) {
                    this.unitSt[this.f214a][this.f219us[this.f214a]][10] = this.unitSt[this.f214a][this.f219us[this.f214a]][11];
                }
            } else if (11 <= this.spd[this.f214a][3] && this.spd[this.f214a][3] < 19) {
                this.btfl[this.f214a][0] = this.spd[this.f214a][3] - 7;
                this.messageStr += this.unitName[this.f214a][this.f219us[this.f214a]] + "は防護障壁を張った！\n";
            } else if (this.spd[this.f214a][3] == 61) {
                this.messageStr += "味方全員のＶＰがかなり回復した！\n";
                for (int i = 0; i < 6; i++) {
                    if (this.unitSt[this.f214a][i][8] > 0) {
                        int[] iArr9 = this.unitSt[this.f214a][i];
                        iArr9[10] = iArr9[10] + Math.round((float) (this.unitSt[this.f214a][i][11] / 2));
                        if (this.unitSt[this.f214a][i][11] < this.unitSt[this.f214a][i][10]) {
                            this.unitSt[this.f214a][i][10] = this.unitSt[this.f214a][i][11];
                        }
                    }
                }
            } else if (this.spd[this.f214a][3] == 62) {
                this.messageStr += this.unitName[this.f214a][this.f219us[this.f214a]] + "のＶＰが回復した！\n";
                this.unitSt[this.f214a][this.f219us[this.f214a]][10] = this.unitSt[this.f214a][this.f219us[this.f214a]][11];
            } else if (this.spd[this.f214a][3] == 63) {
                this.messageStr += this.unitName[this.f214a][this.f219us[this.f214a]] + "のＨＰが回復した！\n";
                int[] iArr10 = this.unitSt[this.f214a][this.f219us[this.f214a]];
                iArr10[8] = iArr10[8] + Math.round((float) (this.unitSt[this.f214a][this.f219us[this.f214a]][9] / 2));
                if (this.unitSt[this.f214a][this.f219us[this.f214a]][9] < this.unitSt[this.f214a][this.f219us[this.f214a]][8]) {
                    this.unitSt[this.f214a][this.f219us[this.f214a]][8] = this.unitSt[this.f214a][this.f219us[this.f214a]][9];
                }
            } else if (this.spd[this.f214a][3] == 64) {
                this.messageStr += this.unitName[this.f214a][this.f219us[this.f214a]] + "のＨＰが回復した！\n";
                this.unitSt[this.f214a][this.f219us[this.f214a]][8] = this.unitSt[this.f214a][this.f219us[this.f214a]][9];
            } else if (this.spd[this.f214a][3] == 181) {
                debuffExe(0, this.f215b, 12, 50);
            } else if (this.spd[this.f214a][3] == 182) {
                debuffExe(0, this.f215b, 13, 50);
            } else if (this.spd[this.f214a][3] == 183) {
                debuffExe(0, this.f215b, 14, 50);
            } else if (this.spd[this.f214a][3] == 184) {
                int tmp7 = (int) Math.ceil((double) (this.unitSt[this.f215b][this.f219us[this.f215b]][10] / 2));
                this.messageStr += this.unitName[this.f215b][this.f219us[this.f215b]] + "のＶＰが" + tmp7 + "下がった！\n";
                int[] iArr11 = this.unitSt[this.f215b][this.f219us[this.f215b]];
                iArr11[10] = iArr11[10] - tmp7;
                if (this.unitSt[this.f215b][this.f219us[this.f215b]][10] < 0) {
                    this.unitSt[this.f215b][this.f219us[this.f215b]][10] = 0;
                }
            } else if (this.spd[this.f214a][3] == 191) {
                debuffExe(0, this.f215b, 12, 80);
            } else if (this.spd[this.f214a][3] == 192) {
                debuffExe(0, this.f215b, 13, 80);
            } else if (this.spd[this.f214a][3] == 193) {
                debuffExe(0, this.f215b, 14, 80);
            } else if (this.spd[this.f214a][3] == 1193) {
                debuffExe(0, this.f215b, 14, 80);
            } else if (this.spd[this.f214a][3] == 121) {
                buffExe(this.f214a, 12, 30);
            } else if (this.spd[this.f214a][3] == 122) {
                buffExe(this.f214a, 13, 30);
            } else if (this.spd[this.f214a][3] == 123) {
                buffExe(this.f214a, 14, 30);
            } else if (this.spd[this.f214a][3] == 131) {
                buffExe(this.f214a, 12, 50);
            } else if (this.spd[this.f214a][3] == 132) {
                buffExe(this.f214a, 13, 50);
            } else if (this.spd[this.f214a][3] == 133) {
                buffExe(this.f214a, 14, 50);
            } else if (this.spd[this.f214a][3] == 134) {
                buffExe(this.f214a, 12, 50);
                buffExe(this.f214a, 13, 50);
            } else if (this.spd[this.f214a][3] == 135) {
                buffExe(this.f214a, 12, 50);
                buffExe(this.f214a, 14, 50);
            } else if (this.spd[this.f214a][3] == 141) {
                buffExe(this.f214a, 12, 100);
            } else if (this.spd[this.f214a][3] == 142) {
                buffExe(this.f214a, 13, 100);
            } else if (this.spd[this.f214a][3] == 143) {
                buffExe(this.f214a, 14, 100);
            } else if (this.spd[this.f214a][3] == 144) {
                buffExe(this.f214a, 12, 100);
                buffExe(this.f214a, 13, 100);
            } else if (this.spd[this.f214a][3] == 145) {
                buffExe(this.f214a, 12, 100);
                buffExe(this.f214a, 14, 100);
            } else if (this.spd[this.f214a][3] == 146) {
                buffExe(this.f214a, 13, 100);
                buffExe(this.f214a, 14, 100);
            } else if (210 < this.spd[this.f214a][3] && this.spd[this.f214a][3] < 300) {
                int tmp8 = this.spd[this.f214a][3] % 100;
                this.messageStr += this.unitName[this.f215b][this.f219us[this.f215b]] + "を" + this.attrs[tmp8 - 10] + "属性に変化させた！\n";
                this.unitSt[this.f215b][this.f219us[this.f215b]][15] = tmp8;
                this.unitSt[this.f215b][this.f219us[this.f215b]][16] = 30;
            }
        }
        this.messageLog += this.messageStr + "\n";
        this.messageText.detachSelf();
        this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        if (tiredFlg) {
            this.f217ef = -1;
        } else {
            this.f217ef = this.spd[this.f214a][4];
        }
        spellEffectCommon();
    }

    private void unitCopy(int cn) {
        Cursor cursor = this.f216db.rawQuery("SELECT m.code FROM skill_m m WHERE m.skill_id=" + this.unitSt[cn][this.f219us[cn]][5], null);
        if (cursor.moveToFirst()) {
            int an = 0;
            if (cn == 0) {
                an = 1;
            }
            if (cursor.getInt(0) == 6000) {
                this.messageStr += this.unitName[cn][this.f219us[cn]] + "は相手のスペルをコピーした！";
                this.unitSt[cn][this.f219us[cn]][1] = this.unitSt[an][this.f219us[an]][1];
                this.unitSt[cn][this.f219us[cn]][2] = this.unitSt[an][this.f219us[an]][2];
                this.unitSt[cn][this.f219us[cn]][3] = this.unitSt[an][this.f219us[an]][3];
                this.unitSt[cn][this.f219us[cn]][4] = this.unitSt[an][this.f219us[an]][4];
                if (this.unitSt[cn][this.f219us[cn]][5] == 140) {
                    this.unitSt[cn][this.f219us[cn]][5] = 141;
                }
            } else if (!(cursor.getInt(0) != 6001 || this.unitSt[an][this.f219us[an]][5] == 0 || this.unitSt[cn][this.f219us[cn]][5] == this.unitSt[an][this.f219us[an]][5])) {
                this.unitSt[cn][this.f219us[cn]][5] = this.unitSt[an][this.f219us[an]][5];
                this.messageStr += this.unitName[cn][this.f219us[cn]] + "は相手のスキルをコピーした！\n";
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    private void buffExe(int cn, int st, int v) {
        String str = "";
        int ce = 0;
        if (cn == 0) {
            ce = 1;
        }
        if (this.skc[ce] != 3200 || this.rnd.nextInt(100) >= this.unitSt[ce][this.f219us[ce]][6] * 20) {
            if (st == 12) {
                str = "攻撃";
            } else if (st == 13) {
                str = "防御";
            } else if (st == 14) {
                str = "速度";
            }
            int up = (int) Math.floor((double) ((this.unitSt[cn][this.f219us[cn]][st] * v) / 100));
            int org2 = this.unitSt[cn][this.f219us[cn]][st];
            int[] iArr = this.unitSt[cn][this.f219us[cn]];
            iArr[st] = iArr[st] + up;
            if (this.orgSt[cn][this.f219us[cn]][st - 12] * 2 < this.unitSt[cn][this.f219us[cn]][st]) {
                this.unitSt[cn][this.f219us[cn]][st] = this.orgSt[cn][this.f219us[cn]][st - 12] * 2;
                up = this.unitSt[cn][this.f219us[cn]][st] - org2;
            }
            this.messageStr += this.unitName[cn][this.f219us[cn]] + "の" + str + "が" + up + "上がった！\n";
            return;
        }
        this.messageStr += this.unitName[cn][this.f219us[cn]] + "は能力上昇を反転させられた！\n";
        debuffExe(0, cn, st, v);
    }

    private void debuffExe(int stf, int cn, int st, int v) {
        String str = "";
        if (this.skc[cn] != 3300 || this.rnd.nextInt(100) >= this.unitSt[cn][this.f219us[cn]][6] * 20) {
            if (this.skc[cn] == 3100 && this.rnd.nextInt(100) < this.unitSt[cn][this.f219us[cn]][6] * 20) {
                this.messageStr += this.unitName[cn][this.f219us[cn]] + "は能力減少を跳ね返した！\n";
                if (cn == 0) {
                    cn = 1;
                } else {
                    cn = 0;
                }
            } else if (stf == 1) {
                this.messageStr += "更に";
            } else if (stf == 2) {
                this.messageStr += "しかし";
            }
            if (st == 12) {
                str = "攻撃";
            } else if (st == 13) {
                str = "防御";
            } else if (st == 14) {
                str = "速度";
            }
            int down = (int) Math.floor((double) ((this.unitSt[cn][this.f219us[cn]][st] * v) / 100));
            int org2 = this.unitSt[cn][this.f219us[cn]][st];
            int[] iArr = this.unitSt[cn][this.f219us[cn]];
            iArr[st] = iArr[st] - down;
            if (this.unitSt[cn][this.f219us[cn]][st] < Math.round((float) (this.orgSt[cn][this.f219us[cn]][st - 12] / 5))) {
                this.unitSt[cn][this.f219us[cn]][st] = Math.round((float) (this.orgSt[cn][this.f219us[cn]][st - 12] / 5));
                down = org2 - this.unitSt[cn][this.f219us[cn]][st];
            }
            this.messageStr += this.unitName[cn][this.f219us[cn]] + "の" + str + "が" + down + "下がった！\n";
            return;
        }
        this.messageStr += this.unitName[cn][this.f219us[cn]] + "は能力減少を反転した！\n";
        buffExe(cn, st, v);
    }

    private void spellEffectCommon() {
        float time;
        int uniX = 70;
        if (this.f214a == 0) {
            uniX = 70 + 304;
        }
        if (this.f217ef < 10 || this.spl[this.f214a] == 1) {
            this.unitChargeImg = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/charge.png", 5, 1);
            this.unitChargeImg.setPosition((float) uniX, (float) 158);
            this.unitChargeImg.setScale(3.0f);
            attachChild(this.unitChargeImg);
            this.unitChargeImg.animate(new long[]{40, 40, 40, 40, 40}, 0, 4, false);
            time = 0.2f;
        } else {
            this.spellUseSound.play();
            this.unitChargeImg = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/hex.png", 17, 1);
            this.unitChargeImg.setPosition((float) uniX, (float) 158);
            this.unitChargeImg.setScale(3.0f);
            attachChild(this.unitChargeImg);
            this.unitChargeImg.animate(new long[]{60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60}, 0, 16, false);
            time = 1.02f;
        }
        this.handlerSpell1 = new TimerHandler(time, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.unitChargeImg.detachSelf();
                MainScene.this.spellEffect();
            }
        });
        registerUpdateHandler(this.handlerSpell1);
    }

    /* access modifiers changed from: private */
    public void spellEffect() {
        boolean damageFlg = false;
        float time = 1.0f;
        int posX = 70;
        int uniX = 70;
        if (this.f214a == 0) {
            uniX = 70 + 304;
        } else {
            posX = 70 + 304;
        }
        if (this.f217ef <= 0 || this.f217ef >= 10) {
            this.spellEffectFlg1 = true;
            if (this.f217ef == -1) {
                time = 1.1f;
                this.statusDownSound.play();
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/20.png", 5, 4);
                this.spellEffect1.setPosition((float) uniX, (float) 158);
                this.spellEffect1.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 19, false);
            } else if (this.f217ef == 11 || this.f217ef == 12 || this.f217ef == 13 || this.f217ef == 15 || this.f217ef == 16 || this.f217ef == 24 || this.f217ef == 25 || this.f217ef == 26 || this.f217ef == 28 || this.f217ef == 29 || this.f217ef == 30) {
                damageFlg = true;
                if (this.f217ef == 11) {
                    this.spellSound11.play();
                } else if (this.f217ef == 12) {
                    this.spellSound12.play();
                } else if (this.f217ef == 13) {
                    this.spellSound13.play();
                } else if (this.f217ef == 15) {
                    this.spellSound15.play();
                } else if (this.f217ef == 16) {
                    this.spellSound16.play();
                } else if (this.f217ef == 24) {
                    this.spellSound12.play();
                } else if (this.f217ef == 25) {
                    this.spellSound25.play();
                } else if (this.f217ef == 26) {
                    this.spellSound16.play();
                } else if (this.f217ef == 28) {
                    this.spellSound30.play();
                } else if (this.f217ef == 29) {
                    this.spellSound29.play();
                } else if (this.f217ef == 30) {
                    this.spellSound30.play();
                }
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 5);
                this.spellEffect1.setPosition((float) posX, (float) 158);
                this.spellEffect1.animate(new long[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40}, 0, 24, false);
            } else if (this.f217ef == 14 || this.f217ef == 18 || this.f217ef == 19) {
                damageFlg = true;
                if (this.f217ef == 14) {
                    this.spellSound14.play();
                } else if (this.f217ef == 18) {
                    this.spellSound18.play();
                } else if (this.f217ef == 19) {
                    this.spellSound19.play();
                }
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 4);
                this.spellEffect1.setPosition((float) posX, (float) 158);
                this.spellEffect1.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 19, false);
            } else if (this.f217ef == 35) {
                damageFlg = true;
                if (this.f217ef == 35) {
                    this.spellSound29.play();
                }
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 4);
                this.spellEffect1.setPosition((float) (posX - 12), (float) 158);
                this.spellEffect1.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 19, false);
            } else if (this.f217ef == 17) {
                damageFlg = true;
                time = 0.8f;
                if (this.f217ef == 17) {
                    this.spellSound15.play();
                }
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 3);
                this.spellEffect1.setPosition((float) posX, (float) 158);
                this.spellEffect1.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 14, false);
            } else if (this.f217ef == 32 || this.f217ef == 33) {
                damageFlg = true;
                time = 1.0f;
                if (this.f217ef == 32) {
                    this.spellSound13.play();
                }
                if (this.f217ef == 33) {
                    this.spellSound15.play();
                }
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 3);
                this.spellEffect1.setPosition((float) (posX - 48), (float) 110);
                this.spellEffect1.animate(new long[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40, 40}, 0, 14, false);
            } else if (this.f217ef == 27) {
                damageFlg = true;
                time = 0.5f;
                if (this.f217ef == 27) {
                    this.spellSound27.play();
                }
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 2);
                this.spellEffect1.setPosition((float) posX, (float) 158);
                this.spellEffect1.animate(new long[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40}, 0, 9, false);
            } else if (this.f217ef == 34) {
                damageFlg = true;
                time = 0.8f;
                if (this.f217ef == 34) {
                    this.spellSound13.play();
                }
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 2);
                this.spellEffect1.setPosition((float) posX, (float) 158);
                this.spellEffect1.animate(new long[]{40, 40, 40, 40, 40, 40, 40, 40, 40, 40}, 0, 9, true);
            } else if (this.f217ef == 36) {
                damageFlg = true;
                time = 0.84f;
                if (this.f217ef == 36) {
                    this.spellSound30.play();
                }
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 14, 1);
                this.spellEffect1.setPosition((float) (posX - 12), (float) 146);
                this.spellEffect1.animate(new long[]{60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60}, 0, 13, false);
            } else if (this.f217ef == 20 || this.f217ef == 120) {
                if (this.f217ef == 120) {
                    damageFlg = true;
                }
                time = 1.1f;
                this.statusDownSound.play();
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + (this.f217ef % 100) + ".png", 5, 4);
                this.spellEffect1.setPosition((float) posX, (float) 158);
                this.spellEffect1.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 19, false);
            } else if (this.f217ef == 31) {
                this.spellSound12.play();
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + (this.f217ef % 100) + ".png", 5, 2);
                this.spellEffect1.setPosition((float) uniX, (float) 158);
                this.spellEffect1.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 9, true);
            } else if (this.f217ef == 21) {
                this.extendSound.play();
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 3, 1);
                this.spellEffect1.setPosition((float) uniX, (float) 158);
                this.spellEffect1.animate(new long[]{60, 60, 60}, 0, 2, true);
            } else if (this.f217ef == 22) {
                this.statusDownSound.play();
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 3, 1);
                this.spellEffect1.setPosition((float) posX, (float) 158);
                this.spellEffect1.animate(new long[]{60, 60, 60}, 0, 2, true);
            } else {
                this.spellHealSound.play();
                this.spellEffect1 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 4);
                this.spellEffect1.setPosition((float) uniX, (float) 158);
                this.spellEffect1.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 19, false);
            }
            if (this.f217ef == 32 || this.f217ef == 33) {
                this.spellEffect1.setScale(1.0f);
            } else {
                this.spellEffect1.setScale(2.0f);
            }
            attachChild(this.spellEffect1);
        } else {
            damageFlg = true;
            this.shotSound.play();
            this.attackBullet = getBaseActivity().getResourceUtil().getSprite("battle/bullet" + this.f217ef + ".png");
            this.attackBullet.setPosition((float) (uniX + 20), (float) 198);
            attachChild(this.attackBullet);
            this.attackBullet.registerEntityModifier(new MoveModifier(0.5f, (float) (uniX + 4), (float) (posX + 4), (float) 162, (float) 162));
            time = 0.5f;
        }
        this.handlerSpell3 = new TimerHandler(time, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.unitStatusClose();
                MainScene.this.unitStatusOpen();
            }
        });
        registerUpdateHandler(this.handlerSpell3);
        if (damageFlg) {
            this.handlerSpell2 = new TimerHandler(time, new ITimerCallback() {
                public void onTimePassed(TimerHandler pTimerHandler) {
                    MainScene.this.attackSound.play();
                    if (MainScene.this.f217ef <= 0 || MainScene.this.f217ef >= 10) {
                        MainScene.this.spellEffect1.detachSelf();
                    } else {
                        MainScene.this.attackBullet.detachSelf();
                    }
                    if (MainScene.this.f214a == 0) {
                        MainScene.this.enImg.registerEntityModifier(new SequenceEntityModifier(new MoveByModifier(0.04f, 3.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -3.0f, 0.0f)));
                    } else {
                        MainScene.this.unImg.registerEntityModifier(new SequenceEntityModifier(new MoveByModifier(0.04f, 3.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -6.0f, 0.0f), new MoveByModifier(0.04f, 6.0f, 0.0f), new MoveByModifier(0.04f, -3.0f, 0.0f)));
                    }
                    MainScene.this.damageDisplay(MainScene.this.f215b, Math.round(MainScene.this.damage));
                }
            });
            registerUpdateHandler(this.handlerSpell2);
            time += 1.0f;
            if (this.unitSt[this.f215b][this.f219us[this.f215b]][8] <= 0) {
                this.handlerSpell4 = new TimerHandler(time, new ITimerCallback() {
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        MainScene.this.faintSound.play();
                        MainScene.this.unitImgCloseEffect(MainScene.this.f215b);
                    }
                });
                registerUpdateHandler(this.handlerSpell4);
                time += 0.5f;
            }
        } else {
            this.handlerSpell6 = new TimerHandler(time, new ITimerCallback() {
                public void onTimePassed(TimerHandler pTimerHandler) {
                    MainScene.this.spellEffect1.stopAnimation();
                    MainScene.this.spellEffect1.setAlpha(0.0f);
                    MainScene.this.spellEffect1.detachSelf();
                }
            });
            registerUpdateHandler(this.handlerSpell6);
        }
        this.handlerSpell5 = new TimerHandler(time, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                if (MainScene.this.unitSt[0][MainScene.this.f219us[0]][8] <= 0) {
                    MainScene.this.unImg.detachSelf();
                }
                if (MainScene.this.unitSt[1][MainScene.this.f219us[1]][8] <= 0) {
                    MainScene.this.enImg.detachSelf();
                }
                MainScene.this.f218o = MainScene.this.f218o + 1;
                MainScene.this.battleAdvance();
            }
        });
        registerUpdateHandler(this.handlerSpell5);
    }

    private void turnEndStart() {
        this.f214a = 0;
        this.f215b = 1;
        turnEndProc();
    }

    /* access modifiers changed from: private */
    public void turnEndProc() {
        if (this.unitSt[this.f214a][this.f219us[this.f214a]][8] > 0) {
            this.messageStr = "empty";
            int skl = this.skc[this.f214a];
            int slv = this.unitSt[this.f214a][this.f219us[this.f214a]][6];
            float time = 0.0f;
            int posX = 70;
            int uniX = 70;
            if (this.f214a == 0) {
                uniX = 70 + 304;
            } else {
                posX = 70 + 304;
            }
            this.f217ef = 0;
            if (1000 < skl && skl < 1200) {
                time = 1.0f;
                this.f217ef = 23;
                if (1000 < skl && skl < 1010) {
                    int tmp = Math.round((((float) this.unitSt[this.f214a][this.f219us[this.f214a]][9]) * ((float) ((skl % 10) * slv))) / 100.0f);
                    if (tmp < 0) {
                        tmp = 1;
                    }
                    this.messageStr = "スキル効果で\n" + this.unitName[this.f214a][this.f219us[this.f214a]] + "のＨＰが" + tmp + "回復した！\n";
                    int[] iArr = this.unitSt[this.f214a][this.f219us[this.f214a]];
                    iArr[8] = iArr[8] + tmp;
                    if (this.unitSt[this.f214a][this.f219us[this.f214a]][9] < this.unitSt[this.f214a][this.f219us[this.f214a]][8]) {
                        this.unitSt[this.f214a][this.f219us[this.f214a]][8] = this.unitSt[this.f214a][this.f219us[this.f214a]][9];
                    }
                } else if (1010 >= skl || skl >= 1020) {
                    if (1100 < skl && skl < 1110) {
                        int tmp2 = slv * (skl - 1100);
                        this.messageStr = "スキル効果で\n" + this.unitName[this.f214a][this.f219us[this.f214a]] + "のＶＰが" + tmp2 + "回復した！\n";
                        int[] iArr2 = this.unitSt[this.f214a][this.f219us[this.f214a]];
                        iArr2[10] = iArr2[10] + tmp2;
                        if (this.unitSt[this.f214a][this.f219us[this.f214a]][11] < this.unitSt[this.f214a][this.f219us[this.f214a]][10]) {
                            this.unitSt[this.f214a][this.f219us[this.f214a]][10] = this.unitSt[this.f214a][this.f219us[this.f214a]][11];
                        }
                    } else if (1110 < skl && skl < 1120) {
                        int tmp3 = slv * (skl - 1110);
                        this.messageStr = this.unitName[this.f214a][this.f219us[this.f214a]] + "のスキル効果で\n味方全員のＶＰが" + tmp3 + "回復した！\n";
                        for (int i = 0; i < 6; i++) {
                            if (this.unitSt[this.f214a][i][8] > 0) {
                                int[] iArr3 = this.unitSt[this.f214a][i];
                                iArr3[10] = iArr3[10] + tmp3;
                                if (this.unitSt[this.f214a][i][11] < this.unitSt[this.f214a][i][10]) {
                                    this.unitSt[this.f214a][i][10] = this.unitSt[this.f214a][i][11];
                                }
                            }
                        }
                    }
                }
                unitStatusClose();
                unitStatusOpen();
            } else if (1200 < skl && skl <= 1210) {
                if (this.unitSt[this.f215b][this.f219us[this.f215b]][8] > 0) {
                    time = 1.1f;
                    this.f217ef = 20;
                    int tmp4 = slv * (skl - 1200);
                    this.messageStr = this.unitName[this.f214a][this.f219us[this.f214a]] + "のスキル効果で\n相手のＶＰが" + tmp4 + "減少した！\n";
                    int[] iArr4 = this.unitSt[this.f215b][this.f219us[this.f215b]];
                    iArr4[10] = iArr4[10] - tmp4;
                    if (this.unitSt[this.f215b][this.f219us[this.f215b]][10] < 0) {
                        this.unitSt[this.f215b][this.f219us[this.f215b]][10] = 0;
                    }
                }
                unitStatusClose();
                unitStatusOpen();
            } else if (1300 >= skl || skl >= 1310) {
                if (1400 < skl && skl < 1500) {
                    int tmp5 = slv * (skl % 10);
                    time = 1.0f;
                    this.f217ef = 21;
                    this.messageStr = "スキル効果で\n";
                    if (skl < 1410) {
                        buffExe(this.f214a, 12, tmp5);
                    } else if (skl < 1420) {
                        buffExe(this.f214a, 13, tmp5);
                    } else if (skl < 1430) {
                        buffExe(this.f214a, 14, tmp5);
                    }
                } else if (1500 >= skl || skl >= 1600) {
                    if (1600 >= skl || skl >= 1700) {
                        if (skl == 1701) {
                            int i2 = 0;
                            while (true) {
                                if (i2 < 6) {
                                    if (this.unitSt[this.f214a][i2][5] == 147 && this.unitSt[this.f214a][i2][0] != this.unitSt[this.f214a][this.f219us[this.f214a]][0]) {
                                        int tmp6 = slv * 7;
                                        time = 1.0f;
                                        this.f217ef = 21;
                                        this.messageStr = "スキル効果で\n";
                                        buffExe(this.f214a, 12, tmp6);
                                        break;
                                    }
                                    i2++;
                                } else {
                                    break;
                                }
                            }
                        } else if (skl == 1702) {
                            int i3 = 0;
                            while (true) {
                                if (i3 < 6) {
                                    if (this.unitSt[this.f214a][i3][5] == 179 && this.unitSt[this.f214a][i3][0] != this.unitSt[this.f214a][this.f219us[this.f214a]][0]) {
                                        int tmp7 = slv * 7;
                                        time = 1.0f;
                                        this.f217ef = 21;
                                        this.messageStr = "スキル効果で\n";
                                        buffExe(this.f214a, 12, tmp7);
                                        break;
                                    }
                                    i3++;
                                } else {
                                    break;
                                }
                            }
                        }
                    } else if (this.unitSt[this.f214a][this.f219us[this.f214a]][8] > 0 && this.unitSt[this.f215b][this.f219us[this.f215b]][8] > 0 && this.rnd.nextInt(100) < slv * 20) {
                        time = 1.0f;
                        this.f217ef = 22;
                        this.messageStr = "相手のスキル効果で\n能力上昇が無効化された！";
                        for (int i4 = 12; i4 <= 14; i4++) {
                            if (this.orgSt[this.f215b][this.f219us[this.f215b]][i4 - 12] < this.unitSt[this.f215b][this.f219us[this.f215b]][i4]) {
                                this.unitSt[this.f215b][this.f219us[this.f215b]][i4] = this.orgSt[this.f215b][this.f219us[this.f215b]][i4 - 12];
                            }
                        }
                    }
                } else if (this.unitSt[this.f215b][this.f219us[this.f215b]][8] > 0) {
                    int tmp8 = slv * (skl % 10);
                    time = 1.0f;
                    this.f217ef = 22;
                    this.messageStr = "相手のスキル効果で\n";
                    if (skl < 1510) {
                        debuffExe(0, this.f215b, 12, tmp8);
                    } else if (skl < 1520) {
                        debuffExe(0, this.f215b, 13, tmp8);
                    } else if (skl < 1530) {
                        debuffExe(0, this.f215b, 14, tmp8);
                    }
                }
            } else if (this.unitSt[this.f215b][this.f219us[this.f215b]][8] > 0) {
                int tmp9 = (skl - 1300) * slv * 10;
                if (this.unitSt[this.f215b][this.f219us[this.f215b]][5] != 0 && this.rnd.nextInt(100) < tmp9) {
                    time = 1.1f;
                    this.f217ef = 20;
                    this.messageStr = this.unitName[this.f214a][this.f219us[this.f214a]] + "のスキル効果で\n相手のスキルを無効化した！\n";
                    this.skc[this.f215b] = 0;
                    this.unitSt[this.f215b][this.f219us[this.f215b]][5] = 0;
                }
            }
            if (this.f217ef == 20) {
                this.statusDownSound.play();
                this.spellEffect2 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + (this.f217ef % 100) + ".png", 5, 4);
                this.spellEffect2.setPosition((float) posX, (float) 158);
                this.spellEffect2.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 19, false);
            } else if (this.f217ef == 21) {
                this.extendSound.play();
                this.spellEffect2 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 3, 1);
                this.spellEffect2.setPosition((float) uniX, (float) 158);
                this.spellEffect2.animate(new long[]{60, 60, 60}, 0, 2, true);
            } else if (this.f217ef == 22) {
                this.statusDownSound.play();
                this.spellEffect2 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 3, 1);
                this.spellEffect2.setPosition((float) posX, (float) 158);
                this.spellEffect2.animate(new long[]{60, 60, 60}, 0, 2, true);
            } else if (this.f217ef == 122) {
                this.statusDownSound.play();
                this.spellEffect2 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + (this.f217ef - 100) + ".png", 3, 1);
                this.spellEffect2.setPosition((float) uniX, (float) 158);
                this.spellEffect2.animate(new long[]{60, 60, 60}, 0, 2, true);
            } else if (this.f217ef == 23) {
                this.spellHealSound.play();
                this.spellEffect2 = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/effect/" + this.f217ef + ".png", 5, 4);
                this.spellEffect2.setPosition((float) uniX, (float) 158);
                this.spellEffect2.animate(new long[]{50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50, 50}, 0, 19, false);
            }
            if (this.f217ef != 0) {
                this.spellEffect2.setScale(2.0f);
                attachChild(this.spellEffect2);
                this.spellEffectFlg2 = true;
            }
            if (!this.messageStr.equals("empty")) {
                this.messageText.detachSelf();
                this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.messageText);
                this.messageLog += this.messageStr + "\n";
            }
            if (1.0f <= time) {
                this.handlerTurnEnd1 = new TimerHandler(time, new ITimerCallback() {
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        MainScene.this.spellEffect2.stopAnimation();
                        MainScene.this.spellEffect2.setAlpha(0.0f);
                        MainScene.this.spellEffect2.detachSelf();
                        MainScene.this.f214a = MainScene.this.f214a + 1;
                        MainScene.this.f215b = MainScene.this.f215b - 1;
                        if (2 <= MainScene.this.f214a) {
                            MainScene.this.turnNext();
                        } else {
                            MainScene.this.turnEndProc();
                        }
                    }
                });
                registerUpdateHandler(this.handlerTurnEnd1);
                return;
            }
            this.f214a++;
            this.f215b--;
            if (2 <= this.f214a) {
                turnNext();
            } else {
                turnEndProc();
            }
        } else {
            this.f214a++;
            this.f215b--;
            if (2 <= this.f214a) {
                turnNext();
            } else {
                turnEndProc();
            }
        }
    }

    /* access modifiers changed from: private */
    public void turnNext() {
        this.messageStr = "";
        this.preDamage = 0;
        this.damageReceiveFlg = false;
        this.unitChangeFlg = false;
        for (int i = 0; i < 2; i++) {
            for (int s = 0; s < 6; s++) {
                if (s != this.f219us[i] && this.unitSt[i][s][8] > 0) {
                    float tmp = ((float) this.unitSt[i][s][11]) / 5.0f;
                    int[] iArr = this.unitSt[i][s];
                    iArr[10] = iArr[10] + Math.round(tmp);
                    if (this.unitSt[i][s][11] < this.unitSt[i][s][10]) {
                        this.unitSt[i][s][10] = this.unitSt[i][s][11];
                    }
                }
            }
            if (1 < this.btfl[i][0]) {
                int[] iArr2 = this.btfl[i];
                iArr2[0] = iArr2[0] - 1;
                if (2 < this.btfl[i][0]) {
                    this.messageStr += this.unitName[i][this.f219us[i]] + "は障壁に守られている（残り" + (this.btfl[i][0] - 1) + "）。\n";
                }
            }
        }
        this.messageLog += this.messageStr;
        this.cartainBottom.detachSelf();
        if (this.unitSt[1][this.f219us[1]][8] <= 0) {
            this.f219us[1] = -1;
            int i2 = 0;
            while (true) {
                if (i2 >= 6) {
                    break;
                } else if (this.unitSt[1][i2][8] > 0) {
                    this.messageStr = this.userName[1] + "は\n" + this.unitName[1][i2] + "（" + this.attrs[this.unitSt[1][i2][15] - 10] + "／" + this.attrs[this.unitSt[1][i2][16] - 10] + "）を\n繰り出そうとしているようだ。\n\nコダマを入れ替えますか？";
                    break;
                } else {
                    i2++;
                }
            }
            if (this.bottomMode == 101) {
                spellListClose();
                unitListOpen();
                this.bottomMode = 102;
            }
        } else {
            if (this.bottomMode == 101) {
                spellListClose();
                spellListOpen();
            } else if (this.bottomMode == 102) {
                unitListClose();
                unitListOpen();
            }
            if (this.unitSt[0][this.f219us[0]][8] > 0) {
                this.messageStr += this.unitName[0][this.f219us[0]] + "はどうする？";
            } else {
                this.f219us[0] = -1;
                if (this.bottomMode == 101) {
                    spellListClose();
                    unitListOpen();
                }
                this.messageStr += "戦闘に出すコダマを選択してください。";
            }
        }
        this.cartainBottom = new Rectangle(540.0f, 476.0f, 540.0f, 484.0f, getBaseActivity().getVertexBufferObjectManager());
        this.cartainBottom.setColor(0.0f, 0.0f, 0.0f);
        this.cartainBottom.setAlpha(0.5f);
        attachChild(this.cartainBottom);
        this.handlerTurnEnd2 = new TimerHandler(0.5f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.condSave();
                MainScene.this.turnEndChange();
            }
        });
        registerUpdateHandler(this.handlerTurnEnd2);
    }

    /* access modifiers changed from: private */
    public void turnEndChange() {
        this.battleMode = 11;
        this.messageText.detachSelf();
        this.messageText = new Text(10.0f, 306.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        this.cartainBottom.detachSelf();
        if (this.messageBoxFlg) {
            this.messageBox.detachSelf();
            this.messageBoxFlg = false;
        }
        this.waitFlg = false;
        if (this.f219us[0] >= 0 && this.f219us[1] < 0) {
            if (this.unitSt[0][this.f219us[0]][8] <= 0) {
                this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/consecutive_ng.png", "battle/button/consecutive_ng.png");
                this.buttonCons.setTag(99999999);
            } else {
                this.buttonCons = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/consecutive.png", "battle/button/consecutive_p.png");
                this.buttonCons.setTag(10000000 + this.f219us[0]);
            }
            this.buttonCons.setPosition(360.0f, 336.0f);
            this.buttonCons.setOnClickListener(this);
            attachChild(this.buttonCons);
            registerTouchArea(this.buttonCons);
            this.consecutiveFlg = true;
        }
        if (!this.messageLog.equals("")) {
            this.buttonLog = getBaseActivity().getResourceUtil().getButtonSprite("battle/button/log.png", "battle/button/log_p.png");
            this.buttonLog.setPosition(360.0f, 406.0f);
            this.buttonLog.setTag(99999991);
            this.buttonLog.setOnClickListener(this);
            attachChild(this.buttonLog);
            registerTouchArea(this.buttonLog);
            this.messageLogFlg = true;
        }
    }

    /* access modifiers changed from: private */
    public void clearEffect() {
        String str;
        if (this.bgm != null) {
            this.bgm.stop();
        }
        this.mapClear.play();
        this.bgBattleTextFlg = true;
        this.bgBattleText = getBaseActivity().getResourceUtil().getSprite("battle/BattleTextBg.png");
        this.bgBattleText.setPosition(0.0f, 100.0f);
        this.bgBattleText.setAlpha(0.0f);
        attachChild(this.bgBattleText);
        String str2 = "";
        if (this.userQuestBattle == 7) {
            str = "ステージクリア！";
        } else {
            str = "勝利！";
        }
        this.battleText = new Text(0.0f, 150.0f, (IFont) this.fontYellow, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.battleText.setPosition((540.0f - this.battleText.getWidth()) / 2.0f, 150.0f);
        this.battleText.setAlpha(0.0f);
        attachChild(this.battleText);
        if (this.userQuestBattle == 7) {
            this.bgBattleText.registerEntityModifier(new FadeInModifier(2.0f));
            this.battleText.registerEntityModifier(new ParallelEntityModifier(new ScaleModifier(1.5f, 5.0f, 1.0f), new FadeInModifier(1.5f)));
            this.fireworksFlg = true;
            this.fireworks = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                if (i < 3) {
                    this.fireworks.add(i, getBaseActivity().getResourceUtil().getAnimatedSprite("battle/fireworks" + (i + 1) + ".png", 11, 1));
                    ((AnimatedSprite) this.fireworks.get(i)).setPosition((float) (this.rnd.nextInt(108) + (i * 108)), (float) this.rnd.nextInt(108));
                } else {
                    this.fireworks.add(i, getBaseActivity().getResourceUtil().getAnimatedSprite("battle/fireworks" + (7 - i) + ".png", 11, 1));
                    ((AnimatedSprite) this.fireworks.get(i)).setPosition((float) (this.rnd.nextInt(108) + ((i - 3) * 162)), (float) (this.rnd.nextInt(108) + 192));
                }
            }
            this.handlerFireworks1 = new TimerHandler(1.0f, new ITimerCallback() {
                public void onTimePassed(TimerHandler pTimerHandler) {
                    for (int i = 0; i < 6; i++) {
                        MainScene.this.attachChild((IEntity) MainScene.this.fireworks.get(i));
                        ((AnimatedSprite) MainScene.this.fireworks.get(i)).setScale(2.0f);
                        ((AnimatedSprite) MainScene.this.fireworks.get(i)).animate(new long[]{60, 60, 60, 60, 60, 60, 60, 60, 60, 60, 60}, 0, 10, false);
                    }
                }
            });
            registerUpdateHandler(this.handlerFireworks1);
        } else {
            this.bgBattleText.registerEntityModifier(new FadeInModifier(1.0f));
            this.battleText.registerEntityModifier(new ParallelEntityModifier(new ScaleModifier(0.75f, 5.0f, 1.0f), new FadeInModifier(0.75f)));
        }
        this.handlerFireworks2 = new TimerHandler(2.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.clearResult();
            }
        });
        registerUpdateHandler(this.handlerFireworks2);
    }

    /* access modifiers changed from: private */
    public void clearResult() {
        int getItem;
        String str;
        Cursor cursor;
        String roadLog;
        String str2;
        String sql;
        boolean firstClearFlg = false;
        boolean lastStageClearFlg = false;
        boolean exFirstClearFlg = false;
        int difItem = 0;
        int getItem2 = 0;
        int preDif = 0;
        int addDif = 0;
        int bonusValue = 0;
        if (this.messageBoxFlg) {
            this.messageBox.detachSelf();
        }
        this.bgBattleText.detachSelf();
        this.battleText.detachSelf();
        if (this.userQuestBattle == 7) {
            for (int i = 0; i < 6; i++) {
                ((AnimatedSprite) this.fireworks.get(i)).detachSelf();
            }
        }
        battleClose();
        this.cartainBottom.detachSelf();
        String str3 = this.userName[1] + "との勝負に勝った！\n\n";
        String sqlQ = "empty";
        int tmpDif = 0;
        if (this.userQuestBattle == 7 && (this.questId < 500000 || (600000 < this.questId && this.questId < 900000))) {
            Cursor cursor2 = this.f216db.rawQuery("SELECT u.diff FROM user_progress_t u WHERE u.quest_id=" + this.questId, null);
            if (cursor2.moveToFirst()) {
                tmpDif = cursor2.getInt(0);
                if (cursor2.getInt(0) < this.userDifficult) {
                    if (tmpDif < 3 && this.userDifficult == 3) {
                        addDif = 1;
                    } else if (tmpDif < 3 && this.userDifficult == 4) {
                        addDif = 2;
                    } else if (tmpDif < 4 && this.userDifficult == 4) {
                        addDif = 1;
                    }
                    sqlQ = "UPDATE user_progress_t SET diff=" + this.userDifficult + " WHERE quest_id=" + this.questId;
                }
            } else {
                firstClearFlg = true;
                if (3 <= this.userDifficult) {
                    addDif = this.userDifficult - 2;
                }
                sqlQ = "INSERT INTO user_progress_t (quest_id, diff) VALUES (" + this.questId + ", " + this.userDifficult + ")";
            }
            if (600000 < this.questId && this.questId < 900000) {
                addDif = 0;
            }
        }
        int getExp = 0;
        for (int i2 = 0; i2 < 6; i2++) {
            if (this.unitSt[1][i2][0] != 0) {
                Cursor cursor3 = this.f216db.rawQuery("SELECT m.kodama_hp, m.kodama_atk, m.kodama_def, m.kodama_spd FROM kodama_m m WHERE m.kodama_id = " + this.unitSt[1][i2][0], null);
                if (cursor3.moveToNext()) {
                    int tmp = cursor3.getInt(0) + cursor3.getInt(1) + cursor3.getInt(2) + cursor3.getInt(3);
                    getExp += Math.round((float) ((tmp * 2) + ((this.mapLv * tmp) / 20)));
                }
            }
        }
        if (800000 < this.questId && this.questId < 900000) {
            getExp = 0;
        }
        int malletUserKodamaId = 0;
        Cursor cursor4 = this.f216db.rawQuery("SELECT u.user_kodama_id FROM user_kodama_t u WHERE u.equip=200001 AND u.kodama_id<>0", null);
        if (cursor4.moveToNext()) {
            malletUserKodamaId = cursor4.getInt(0);
        }
        int nextExp = 0;
        String[] sqlLv = new String[6];
        for (int i3 = 0; i3 < 6; i3++) {
            sqlLv[i3] = "empty";
        }
        if (getExp != 0) {
            if (malletUserKodamaId == 0) {
                sql = "SELECT u.user_kodama_id, u.kodama_id, u.name, u.lv, u.exp, u.max_lv FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id WHERE p.party_no = " + this.partyNo + " AND u.kodama_id<>0 ORDER BY p.sort_no ASC";
            } else {
                sql = "SELECT u.user_kodama_id, u.kodama_id, u.name, u.lv, u.exp, u.max_lv FROM user_kodama_t u WHERE u.user_kodama_id=" + malletUserKodamaId;
                getExp *= 6;
                str3 = str3 + "【打ち出の小槌発動中】\n";
            }
            cursor4 = this.f216db.rawQuery(sql, null);
            int no = 0;
            while (cursor4.moveToNext()) {
                int lv = cursor4.getInt(3);
                int exp = cursor4.getInt(4);
                int maxLv = cursor4.getInt(5);
                float tp = (float) getExp;
                int def = this.mapLv - lv;
                int upLv = lv;
                if (50 < def) {
                    tp *= 11.0f;
                } else if (def > 0) {
                    tp *= 1.0f + (0.2f * ((float) def));
                } else if (def < -9) {
                    tp /= 100.0f;
                } else if (def < -8) {
                    tp /= 81.0f;
                } else if (def < -7) {
                    tp /= 64.0f;
                } else if (def < -6) {
                    tp /= 49.0f;
                } else if (def < -5) {
                    tp /= 36.0f;
                } else if (def < -4) {
                    tp /= 25.0f;
                } else if (def < -3) {
                    tp /= 16.0f;
                } else if (def < -2) {
                    tp /= 9.0f;
                } else if (def < -1) {
                    tp /= 4.0f;
                } else if (def < 0) {
                    tp /= 2.0f;
                }
                int tmp2 = Math.round(tp);
                if (tmp2 < 0) {
                    tmp2 = 0;
                }
                if (this.questId == 200002 || this.questId == 200003) {
                    tmp2 *= 5;
                }
                int exp2 = exp + tmp2;
                int i4 = 0;
                while (true) {
                    if (i4 >= 100) {
                        break;
                    }
                    nextExp = (upLv + 1) * (upLv + 1) * (upLv + 1) * 8;
                    if (maxLv > upLv) {
                        if (nextExp - exp2 > 0) {
                            break;
                        }
                        upLv++;
                        i4++;
                    } else {
                        upLv = maxLv;
                        exp2 = upLv * upLv * upLv * 8;
                        nextExp = exp2;
                        break;
                    }
                }
                String str4 = str3 + "■" + cursor4.getString(2) + "は" + tmp2 + "の経験値を獲得。\n";
                if (lv < upLv) {
                    str3 = (str4 + "　★レベルが" + (upLv - lv) + "上がった！（Lv" + lv + "→" + upLv + "）\n") + "　[次のレベルまで：" + (nextExp - exp2) + "]\n";
                    lv = upLv;
                } else {
                    str3 = str4 + "　[次のレベルまで：" + (nextExp - exp2) + "]\n\n";
                }
                sqlLv[no] = "UPDATE user_kodama_t SET lv=" + lv + ", exp=" + exp2 + " WHERE user_kodama_id=" + cursor4.getInt(0);
                no++;
            }
        }
        if (this.userQuestBattle == 7 && this.questId < 500000 && firstClearFlg) {
            getItem2 = this.prizeNo;
        }
        if (this.userQuestBattle == 7 && this.questId < 500000) {
            Cursor cursor5 = this.f216db.rawQuery("SELECT u.diff FROM user_progress_t u WHERE 3 <= u.diff AND u.quest_id < 500000", null);
            while (cursor5.moveToNext()) {
                preDif += cursor5.getInt(0) - 2;
            }
            addDif += preDif;
            cursor4 = this.f216db.rawQuery("SELECT m.value, m.prize FROM bonus_m m WHERE " + preDif + " < m.value AND m.value <= " + addDif, null);
            if (cursor4.moveToNext()) {
                bonusValue = cursor4.getInt(0);
                difItem = cursor4.getInt(1);
            }
        }
        String sqlG = "empty";
        String sqlEX = "empty";
        if (getItem != 0 && this.questId < 500000) {
            cursor4 = this.f216db.rawQuery("SELECT m.name FROM item_m m WHERE m.item_id=" + getItem, null);
            if (cursor4.moveToFirst()) {
                if (firstClearFlg) {
                    str2 = str3 + "\n◆ステージ初クリア！\n";
                } else {
                    str2 = str3 + "\n◆クリア難易度更新！\n";
                }
                String str5 = str2 + "「" + cursor4.getString(0) + "」を" + 1 + "個獲得！\n";
                if (getItem != difItem) {
                    cursor4 = this.f216db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + getItem, null);
                    if (cursor4.moveToNext()) {
                        int tmp3 = cursor4.getInt(0) + 1;
                        if (999999 < tmp3) {
                            tmp3 = 999999;
                        } else {
                            str5 = str5 + "（所持数：" + (tmp3 - 1) + "→" + tmp3 + "）\n";
                        }
                        sqlG = "UPDATE user_item_t SET num=" + tmp3 + " WHERE item_id=" + getItem;
                    } else {
                        str5 = str5 + "（所持数：0→" + 1 + "）\n";
                        if (getItem != 0) {
                            sqlG = "INSERT INTO user_item_t (item_id, num) VALUES (" + getItem + ", " + 1 + ")";
                        }
                    }
                }
                str3 = str5 + "\n";
            }
        } else if (500000 < this.questId) {
            getItem = 0;
            int getNum = 1;
            if (this.userQuestBattle == 7) {
                if (800000 < this.questId && this.questId < 900000) {
                    getItem = 20010;
                    getNum = 0;
                    if (firstClearFlg) {
                        getNum = 4 <= this.userDifficult ? 10 : 3 <= this.userDifficult ? 6 : 3;
                    } else if (tmpDif <= 2 && this.userDifficult == 4) {
                        getNum = 7;
                    } else if (tmpDif == 3 && this.userDifficult == 4) {
                        getNum = 4;
                    } else if (tmpDif <= 2 && this.userDifficult == 3) {
                        getNum = 3;
                    }
                    if (800026 <= this.questId) {
                        getNum *= 2;
                    }
                    if (getNum == 0) {
                        getItem = 0;
                    }
                } else if (600000 < this.questId && this.questId < 800000) {
                    int getNum2 = 0;
                    if (firstClearFlg) {
                        getNum2 = 4 <= this.userDifficult ? 3 : 3 <= this.userDifficult ? 2 : 1;
                    } else if (tmpDif <= 2 && this.userDifficult == 4) {
                        getNum2 = 2;
                    } else if (tmpDif == 3 && this.userDifficult == 4) {
                        getNum2 = 1;
                    } else if (tmpDif <= 2 && this.userDifficult == 3) {
                        getNum2 = 1;
                    }
                    if (1 <= getNum) {
                        if (this.stageId != 7) {
                            getItem = 20010;
                        } else if (this.areaId == 600001) {
                            getItem = 190009;
                        } else if (this.areaId == 600002) {
                            getItem = 190016;
                        } else if (this.areaId == 600003) {
                            getItem = 190001;
                        } else if (this.areaId == 600004) {
                            getItem = 190017;
                        } else if (this.areaId == 600005) {
                            getItem = 190014;
                        } else if (this.areaId == 600006) {
                            getItem = 190006;
                        } else if (this.areaId == 600007) {
                            getItem = 190007;
                        } else if (this.areaId == 600008) {
                            getItem = 190005;
                        } else if (this.areaId == 600009) {
                            getItem = 190008;
                        } else if (this.areaId == 600010) {
                            getItem = 190002;
                        } else if (this.areaId == 600011) {
                            getItem = 190010;
                        } else if (this.areaId == 600012) {
                            getItem = 190003;
                        } else if (this.areaId == 700001) {
                            getItem = 191001;
                        } else if (this.areaId == 700002) {
                            getItem = 191002;
                        } else if (this.areaId == 700003) {
                            getItem = 191003;
                        } else if (this.areaId == 700004) {
                            getItem = 191004;
                        } else if (this.areaId == 700005) {
                            getItem = 191005;
                        }
                    }
                } else if (500000 < this.questId && this.questId < 600000) {
                    if (this.questId == 510000) {
                        getItem = 20010;
                        getNum = 4 <= this.userDifficult ? 5 : 3 <= this.userDifficult ? 4 : 3;
                    } else if (this.exProgressId == this.questId) {
                        exFirstClearFlg = true;
                        if (500020 <= this.questId) {
                            if ((this.exProgressId - 500000) % 2 == 1) {
                                getItem = 9;
                                if (4 <= this.userDifficult) {
                                    getNum = 3;
                                } else if (3 <= this.userDifficult) {
                                    getNum = 2;
                                }
                            } else {
                                getItem = 40001;
                                getNum = 4 <= this.userDifficult ? 6 : 3 <= this.userDifficult ? 4 : 2;
                            }
                        } else if (500015 <= this.questId) {
                            if ((this.exProgressId - 500000) % 3 == 0) {
                                getItem = 9;
                                if (4 <= this.userDifficult) {
                                    getNum = 3;
                                } else if (3 <= this.userDifficult) {
                                    getNum = 2;
                                }
                            } else {
                                getItem = 40001;
                                if (4 <= this.userDifficult) {
                                    getNum = 3;
                                } else if (3 <= this.userDifficult) {
                                    getNum = 2;
                                }
                            }
                        } else if ((this.exProgressId - 500000) % 3 == 0) {
                            getItem = 30008;
                            if (4 <= this.userDifficult) {
                                getNum = 2;
                            }
                        } else if ((this.exProgressId - 500000) % 3 != 1 || 500010 > this.questId) {
                            getItem = 40001;
                            if (4 <= this.userDifficult) {
                                getNum = 3;
                            } else if (3 <= this.userDifficult) {
                                getNum = 2;
                            }
                        } else {
                            getItem = 20010;
                            if (4 <= this.userDifficult) {
                                getNum = 3;
                            } else if (3 <= this.userDifficult) {
                                getNum = 2;
                            }
                        }
                        if (this.questId < 510000) {
                            this.exProgressId++;
                            sqlEX = "UPDATE user_item_t SET num=" + this.exProgressId + " WHERE item_id=500001";
                        }
                    }
                }
            }
            if (getItem != 0) {
                Cursor cursor22 = this.f216db.rawQuery("SELECT m.name FROM item_m m WHERE m.item_id=" + getItem, null);
                if (cursor22.moveToFirst()) {
                    if (this.questId == 510000) {
                        str = str3 + "\n◆ステージクリア！\n";
                    } else if (firstClearFlg || 600000 >= this.questId || this.questId >= 900000) {
                        str = str3 + "\n◆ステージ初クリア！\n";
                    } else {
                        str = str3 + "\n◆クリア難易度更新！\n";
                    }
                    str3 = str + "「" + cursor22.getString(0) + "」を" + getNum + "個獲得！\n";
                }
                Cursor cursor23 = this.f216db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + getItem, null);
                if (cursor23.moveToNext()) {
                    int tmp4 = cursor23.getInt(0) + getNum;
                    if (999999 < tmp4) {
                        tmp4 = 999999;
                    } else {
                        str3 = str3 + "（所持数：" + (tmp4 - getNum) + "→" + tmp4 + "）\n";
                    }
                    sqlG = "UPDATE user_item_t SET num=" + tmp4 + " WHERE item_id=" + getItem;
                } else {
                    int tmp5 = getNum;
                    str3 = str3 + "（所持数：0→" + tmp5 + "）\n";
                    if (getItem != 0) {
                        sqlG = "INSERT INTO user_item_t (item_id, num) VALUES (" + getItem + ", " + tmp5 + ")";
                    }
                }
                str3 = str3 + "\n";
                if (cursor23 != null) {
                    cursor23.close();
                }
            }
        }
        String sqlD = "empty";
        if (preDif != addDif && this.questId < 500000) {
            str3 = str3 + "【難易度ボーナス：" + preDif + "→" + addDif + "】\n";
            if (difItem != 0) {
                cursor4 = this.f216db.rawQuery("SELECT m.name FROM item_m m WHERE m.item_id=" + difItem, null);
                if (cursor4.moveToFirst()) {
                    String str6 = str3 + "◆ボーナス" + bonusValue + "達成！\n「" + cursor4.getString(0) + "」を1個獲得！\n";
                    int i5 = 1;
                    if (getItem == difItem) {
                        i5 = 2;
                    }
                    cursor4 = this.f216db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + difItem, null);
                    if (cursor4.moveToNext()) {
                        int tmp6 = cursor4.getInt(0) + i5;
                        if (999999 < tmp6) {
                            tmp6 = 999999;
                        } else {
                            str6 = str6 + "（所持数：" + (tmp6 - i5) + "→" + tmp6 + "）\n";
                        }
                        sqlD = "UPDATE user_item_t SET num=" + tmp6 + " WHERE item_id=" + difItem;
                    } else {
                        int tmp7 = i5;
                        str3 = str6 + "（所持数：0→" + tmp7 + "）\n";
                        if (difItem != 0) {
                            sqlD = "INSERT INTO user_item_t (item_id, num) VALUES (" + difItem + ", " + tmp7 + ")";
                        }
                    }
                }
            }
        }
        if (exFirstClearFlg && this.userQuestBattle == 7 && (this.questId == 500003 || this.questId == 500006 || this.questId == 500009 || this.questId == 500012 || this.questId == 500015 || this.questId == 500017 || this.questId == 500019 || this.questId == 500020 || this.questId == 500021 || this.questId == 500023 || this.questId == 500025 || this.questId == 500027 || this.questId == 500028 || this.questId == 500029 || this.questId == 500030 || this.questId == 500031 || this.questId == 500032 || this.questId == 500033)) {
            str3 = str3 + "\n【お知らせ】\nショップに商品が追加されたようです。\n\n";
        }
        if (firstClearFlg) {
            if (this.questId == 4 || this.questId == 16 || this.questId == 51 || this.questId == 63 || this.questId == 77 || this.questId == 91 || this.questId == 119 || this.questId == 147 || this.questId == 161 || this.questId == 189 || this.questId == 203 || this.questId == 217 || this.questId == 252) {
                str3 = str3 + "\n【お知らせ】\nショップに商品が追加されたようです。\n\n";
            }
            if (this.questId == 700002) {
                str3 = str3 + "\n【お知らせ】\nショップに商品が追加されたようです。\n\n";
            }
            if (this.questId == 30 || this.questId == 44 || this.questId == 70 || this.questId == 84) {
                str3 = str3 + "\n【お知らせ】\nサブエリアが追加されました。\n\n";
            }
            if (this.questId == 126 || this.questId == 196) {
                str3 = str3 + "\n【お知らせ】\n阿澄酒場に新ステージが追加されました。\n\n";
            }
            if (this.questId == 154) {
                str3 = str3 + "\n【お知らせ】\nオウカジムに新ステージが追加されました。\n\n";
            }
            if (this.questId == 245) {
                str3 = str3 + "\n【お知らせ】\n白瓏洞に新ステージが追加されました。\n\n";
            }
            if (this.questId == 182) {
                str3 = str3 + "\n【お知らせ】\nEXエリアが開放されました。\n\n";
            }
            if (this.questId == 273) {
                str3 = str3 + "\n【お知らせ】\nポケットタワーが開放されました。\n\n";
            }
            if (this.questId == 287) {
                str3 = str3 + "\n【お知らせ】\nPHエリアが開放されました。\n\n";
            }
            if (this.questId == 700035) {
                str3 = str3 + "\n【お知らせ】\nチャンピオンロードが開放されました。\n\n";
            }
            if (this.questId < 500000) {
                int i6 = this.areaId;
                cursor4 = this.f216db.rawQuery("SELECT q.area_id FROM quest_m q WHERE q.quest_id=" + (this.questId + 1), null);
                if (cursor4.moveToFirst()) {
                    int newAreaId = cursor4.getInt(0);
                    if (newAreaId != this.areaId) {
                        this.editor.putInt("areaSelect", newAreaId);
                        this.editor.commit();
                    }
                }
            }
            if (this.questId == 800030 && firstClearFlg) {
                str3 = str3 + "【最終ステージクリア！】\n　報酬アイテムが送付されました。\n\n";
            }
        }
        if (800000 < this.questId && this.questId < 900000) {
            String roadLog2 = "";
            int battleId = 3;
            if (4 <= this.userQuestBattle) {
                battleId = 4;
            }
            Cursor cursor6 = this.f216db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=" + battleId, null);
            if (cursor6.moveToFirst()) {
                String roadLog3 = roadLog2 + cursor6.getString(0);
                String[] equipName = new String[6];
                String[] kodamaName = new String[6];
                for (int i7 = 0; i7 < 6; i7++) {
                    equipName[i7] = "なし";
                    kodamaName[i7] = "";
                }
                cursor6 = this.f216db.rawQuery("SELECT p.sort_no, i.name, k.kodama_name FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id LEFT OUTER JOIN item_m i ON u.equip=i.item_id LEFT OUTER JOIN kodama_m k ON u.kodama_id=k.kodama_id WHERE p.party_no = " + this.partyNo + " ORDER BY p.sort_no ASC", null);
                while (cursor6.moveToNext()) {
                    int sortNo = cursor6.getInt(0) - 1;
                    if (sortNo >= 0 && sortNo < 6) {
                        equipName[sortNo] = cursor6.getString(1);
                        kodamaName[sortNo] = cursor6.getString(2);
                    }
                }
                String roadLog4 = roadLog3 + "▽バトル" + this.userQuestBattle + "\n";
                for (int i8 = 0; i8 < 6; i8++) {
                    if (this.unitSt[0][i8][0] != 0) {
                        if (this.unitSt[0][i8][8] == 0) {
                            roadLog = roadLog4 + "×";
                        } else {
                            roadLog = roadLog4 + "・";
                        }
                        roadLog4 = roadLog + "Lv." + this.unitSt[0][i8][7] + " " + kodamaName[i8] + "（" + equipName[i8] + "）\n";
                    }
                }
                this.f216db.execSQL("UPDATE user_battle_t SET data='" + roadLog4 + "' WHERE user_battle_id=" + battleId);
            }
            String roadLog5 = "";
            if (this.userQuestBattle == 7) {
                cursor = this.f216db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=5", null);
                if (cursor.moveToFirst()) {
                    String roadLog6 = roadLog5 + cursor.getString(0) + "\n";
                    int i9 = 0;
                    cursor = this.f216db.rawQuery("SELECT u.lv, k.kodama_name FROM user_kodama_t u LEFT OUTER JOIN kodama_m k ON u.kodama_id=k.kodama_id WHERE u.faint_flg=1 ORDER BY k.kodama_name ASC", null);
                    while (cursor.moveToNext()) {
                        if (i9 < 30) {
                            roadLog6 = roadLog6 + "・Lv." + cursor.getInt(0) + " " + cursor.getString(1) + "\n";
                        }
                        i9++;
                    }
                    if (i9 == 0) {
                        roadLog6 = roadLog6 + "\n気絶コダマなし … 【パーフェクト！】";
                    } else if (30 < i9) {
                        roadLog6 = roadLog6 + "\nその他 … 計" + i9 + "体";
                    }
                    this.f216db.execSQL("UPDATE user_battle_t SET data='" + roadLog6 + "' WHERE user_battle_id=5");
                }
                str3 = str3 + "\n\n■その他メニュー - ヘルプの\n　チャンピオンロード前回戦績が\n　更新されました。";
            }
        }
        this.userQuestBattle++;
        if (this.userQuestBattle == 8) {
            if (this.questId == 800030 && firstClearFlg) {
                lastStageClearFlg = true;
            }
            this.questId = 0;
            this.userQuestStatus = 1;
        } else {
            this.userQuestStatus = 2;
        }
        if (firstClearFlg && this.userDemoNo == 3) {
            this.userDemoNo = 4;
        }
        this.f216db.beginTransaction();
        try {
            if (!sqlQ.equals("empty")) {
                this.f216db.execSQL(sqlQ);
            }
            if (!sqlG.equals("empty")) {
                this.f216db.execSQL(sqlG);
            }
            if (!sqlD.equals("empty")) {
                this.f216db.execSQL(sqlD);
            }
            if (!sqlEX.equals("empty")) {
                this.f216db.execSQL(sqlEX);
            }
            for (int i10 = 0; i10 < 6; i10++) {
                if (!sqlLv[i10].equals("empty")) {
                    this.f216db.execSQL(sqlLv[i10]);
                }
            }
            this.f216db.execSQL("UPDATE user_t SET user_quest_id=" + this.questId + ", user_quest_status=" + this.userQuestStatus + ", user_quest_battle=" + this.userQuestBattle + ", user_demo_no=" + this.userDemoNo);
            this.f216db.execSQL("UPDATE enemy_m SET quest_id=0 WHERE enemy_id=100000");
            this.f216db.execSQL("UPDATE user_battle_t SET data=\"NULL\" WHERE user_battle_id=2");
            if (lastStageClearFlg) {
                cursor = this.f216db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + 200100 + ";", null);
                if (cursor.moveToFirst()) {
                    this.f216db.execSQL("UPDATE user_item_t SET num=" + (cursor.getInt(0) + 1) + " WHERE item_id=" + 200100 + ";");
                } else {
                    this.f216db.execSQL("INSERT INTO user_item_t VALUES (" + 200100 + ", 1);");
                }
            }
            this.f216db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.f216db.endTransaction();
        }
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/informationL.png");
        placeToCenterX(this.informationBox, 20.0f);
        attachChild(this.informationBox);
        this.messageText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ok.png", "button/information_ok_p.png");
        this.buttonOk.setPosition(170.0f, 810.0f);
        this.buttonOk.setTag(99999996);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        if (cursor != null) {
            cursor.close();
        }
    }

    private void destroyEffect() {
        if (this.bgm != null) {
            this.bgm.stop();
        }
        this.destroySound.play();
        this.bgBattleTextFlg = true;
        this.bgBattleText = getBaseActivity().getResourceUtil().getSprite("battle/BattleTextBg.png");
        this.bgBattleText.setPosition(0.0f, 100.0f);
        this.bgBattleText.setAlpha(0.0f);
        attachChild(this.bgBattleText);
        this.battleText = new Text(0.0f, 150.0f, (IFont) this.fontYellow, (CharSequence) "満身創痍…", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.battleText.setPosition((540.0f - this.battleText.getWidth()) / 2.0f, 150.0f);
        this.battleText.setAlpha(0.0f);
        attachChild(this.battleText);
        this.bgBattleText.registerEntityModifier(new FadeInModifier(2.0f));
        this.battleText.registerEntityModifier(new ParallelEntityModifier(new ScaleModifier(2.0f, 5.0f, 1.0f), new FadeInModifier(2.0f)));
        this.handlerDestroyEffect1 = new TimerHandler(2.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.destroyResult();
            }
        });
        registerUpdateHandler(this.handlerDestroyEffect1);
    }

    /* access modifiers changed from: private */
    public void destroyResult() {
        String str;
        this.bgBattleText.detachSelf();
        this.battleText.detachSelf();
        battleClose();
        this.cartainBottom.detachSelf();
        if (this.messageBoxFlg) {
            this.messageBox.detachSelf();
            this.messageBoxFlg = false;
        }
        String str2 = this.userName[0] + "は全滅してしまった…。\n\n";
        if (this.userLast < 1) {
            str = str2 + "※残機がなくなったため、\n　クエスト挑戦を中断して\n　メニュー画面に戻ります。";
        } else {
            str = str2 + "※残りコンティニュー回数：" + this.userLast + "回";
        }
        if (this.questId <= 3) {
            if (this.unitSt[0][1][0] == 0 || this.unitSt[0][2][0] == 0) {
                str = str + "\n\n■攻略ヒント\n手持ちコダマが足りないかも？\n\n一度マイページに戻って\n「アイテム」メニューをチェック！\n\n福袋からコダマカードを出して、\n新しいコダマと契約しよう！";
            } else {
                str = str + "\n\n■攻略ヒント\n属性には有利・不利があります。\n\n相手に有利なコダマを繰り出して、\n戦闘をうまく進めましょう。\n\n属性相性表や、相手の能力も\n確認してみてください。";
            }
        }
        this.userLast--;
        if (this.userLast < 0) {
            this.questId = 0;
            this.userQuestStatus = 1;
            this.userQuestBattle = 0;
            this.userLast = 0;
        } else {
            this.userQuestStatus = 2;
        }
        this.f216db.execSQL("UPDATE user_t SET user_quest_id=" + this.questId + ", user_quest_status=" + this.userQuestStatus + ", user_quest_battle=" + this.userQuestBattle + ", user_last=" + this.userLast);
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(this.informationBox, 20.0f);
        attachChild(this.informationBox);
        this.messageText = new Text(46.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ok.png", "button/information_ok_p.png");
        this.buttonOk.setPosition(170.0f, 750.0f);
        this.buttonOk.setTag(99999996);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
    }

    /* access modifiers changed from: private */
    public void damageDisplay(int act, int num) {
        int x = 80;
        if (act == 0) {
            x = 80 + 304;
        }
        String str = String.valueOf(num);
        this.damageText[act].detachSelf();
        this.damageText[act] = new Text((float) x, (float) 142, (IFont) this.bitmapFont, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.damageText[act].setX(((float) x) + ((128.0f - this.damageText[act].getWidth()) / 2.0f));
        attachChild(this.damageText[act]);
        this.damageText[act].registerEntityModifier(new SequenceEntityModifier(new MoveModifier(1.5f, this.damageText[act].getX(), this.damageText[act].getX(), this.damageText[act].getY(), this.damageText[act].getY() + 100.0f, (IEaseFunction) EaseBounceOut.getInstance()), new FadeOutModifier(0.5f)));
    }

    private String brInsert(String str, int num) {
        StringBuffer bf = new StringBuffer(str);
        int count = 0;
        for (int i = 0; i < bf.length(); i++) {
            if (bf.charAt(i) == 10) {
                count = 0;
            } else {
                count++;
                if (num <= count) {
                    bf = bf.insert(i, "\n");
                    count = 0;
                }
            }
        }
        return bf.substring(0);
    }

    public void unitDetailOpen(boolean self, int act) {
        Cursor cursor = null;
        int cn = 0;
        if (self) {
            this.unitDetailOpenFlg = true;
        } else {
            this.enemyDetailOpenFlg = true;
            cn = 1;
        }
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(this.informationBox, 20.0f);
        attachChild(this.informationBox);
        this.unImg = getBaseActivity().getResourceUtil().getSprite("kodama/" + this.unitSt[cn][act][0] + ".png");
        this.unImg.setPosition(46.0f, 80.0f);
        attachChild(this.unImg);
        this.unAttrImg1 = getBaseActivity().getResourceUtil().getSprite("attr/" + this.unitSt[cn][act][15] + ".png");
        this.unAttrImg1.setPosition(46.0f, 40.0f);
        attachChild(this.unAttrImg1);
        this.unAttrImg2 = getBaseActivity().getResourceUtil().getSprite("attr/" + this.unitSt[cn][act][16] + ".png");
        this.unAttrImg2.setPosition(94.0f, 40.0f);
        attachChild(this.unAttrImg2);
        String str = (((("■" + this.unitName[cn][act] + "（" + this.className[cn][act] + "）\n") + "Lv." + this.unitSt[cn][act][7] + "\n") + "ＨＰ：" + this.unitSt[cn][act][8] + "／" + this.unitSt[cn][act][9] + "\n") + "ＶＰ：" + this.unitSt[cn][act][10] + "／" + this.unitSt[cn][act][11] + "\n") + "攻撃：" + this.unitSt[cn][act][12];
        if (!(this.orgSt[cn][act][0] == 0 || this.unitSt[cn][act][12] == this.orgSt[cn][act][0])) {
            str = str + "（" + ((int) Math.floor((double) ((this.unitSt[cn][act][12] * 100) / this.orgSt[cn][act][0]))) + "％）";
        }
        String str2 = (str + "\n") + "防御：" + this.unitSt[cn][act][13];
        if (!(this.orgSt[cn][act][1] == 0 || this.unitSt[cn][act][13] == this.orgSt[cn][act][1])) {
            str2 = str2 + "（" + ((int) Math.floor((double) ((this.unitSt[cn][act][13] * 100) / this.orgSt[cn][act][1]))) + "％）";
        }
        String str3 = (str2 + "\n") + "速度：" + this.unitSt[cn][act][14];
        if (!(this.orgSt[cn][act][2] == 0 || this.unitSt[cn][act][14] == this.orgSt[cn][act][2])) {
            str3 = str3 + "（" + ((int) Math.floor((double) ((this.unitSt[cn][act][14] * 100) / this.orgSt[cn][act][2]))) + "％）";
        }
        String str4 = (str3 + "\n") + "\n";
        int[] reg = new int[20];
        for (int i = 0; i < 20; i++) {
            reg[i] = 5;
        }
        for (int t = 11; t <= 28; t++) {
            reg[t - 10] = attrCheck(t, cn, act, false);
        }
        String str5 = (str4 + "■属性相性\n") + "弱点：";
        int s = 0;
        for (int t2 = 1; t2 <= 18; t2++) {
            if (5 < reg[t2]) {
                if (1 <= s) {
                    str5 = str5 + ",";
                }
                s++;
            }
            if (reg[t2] == 7) {
                str5 = str5 + "【" + this.attrs[t2] + "】";
            } else if (reg[t2] == 6) {
                str5 = str5 + this.attrs[t2];
            }
        }
        if (s == 0) {
            str5 = str5 + "なし";
        }
        String str6 = str5 + "\n耐性：";
        int s2 = 0;
        for (int t3 = 1; t3 <= 18; t3++) {
            if (3 <= reg[t3] && reg[t3] < 5) {
                if (1 <= s2) {
                    str6 = str6 + ",";
                }
                s2++;
            }
            if (reg[t3] == 3) {
                str6 = str6 + "【" + this.attrs[t3] + "】";
            } else if (reg[t3] == 4) {
                str6 = str6 + this.attrs[t3];
            }
        }
        String str7 = str6 + "\n無効：";
        int s3 = 0;
        for (int t4 = 1; t4 <= 18; t4++) {
            if (reg[t4] == 0) {
                if (1 <= s3) {
                    str7 = str7 + ",";
                }
                str7 = str7 + this.attrs[t4];
                s3++;
            }
        }
        if (s3 == 0) {
            str7 = str7 + "なし";
        }
        if (this.unitSt[cn][act][5] != 0) {
            cursor = this.f216db.rawQuery("SELECT m.name, m.text FROM skill_m m WHERE m.skill_id=" + this.unitSt[cn][act][5], null);
            while (cursor.moveToNext()) {
                str7 = str7 + "\n\n■スキル\n「" + cursor.getString(0) + "」（SLv." + this.unitSt[cn][act][6] + "）\n" + brInsert(cursor.getString(1), 22) + "\n\n";
            }
        }
        this.messageText = new Text(46.0f, 220.0f, (IFont) this.fontWhite, (CharSequence) str7, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_spell.png", "button/button_spell_p.png");
        this.buttonOk.setPosition(LARGE_FONT_SIZE, 750.0f);
        this.buttonOk.setTag(10000000 + act);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        this.buttonNg.setPosition(280.0f, 750.0f);
        this.buttonNg.setTag(99999999);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
        if (cursor != null) {
            cursor.close();
        }
    }

    public void unitDetailClose() {
        this.informationBox.detachSelf();
        this.unImg.detachSelf();
        this.unAttrImg1.detachSelf();
        this.unAttrImg2.detachSelf();
        this.messageText.detachSelf();
        this.buttonOk.detachSelf();
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonOk);
        unregisterTouchArea(this.buttonNg);
        this.unitDetailOpenFlg = false;
        this.enemyDetailOpenFlg = false;
    }

    private void spellOpen(boolean self, int act) {
        String str;
        String str2 = "■未習得";
        Cursor cursor = null;
        int cn = 0;
        if (self) {
            this.unitSpellOpenFlg = true;
        } else {
            this.enemySpellOpenFlg = true;
            cn = 1;
        }
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(this.informationBox, 20.0f);
        attachChild(this.informationBox);
        for (int i = 1; i < 5; i++) {
            if (this.unitSt[cn][act][i] != 0) {
                cursor = this.f216db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, t.text FROM spell_m m LEFT OUTER JOIN spell_text_m t ON m.code=t.code WHERE m.spell_id=" + this.unitSt[cn][act][i], null);
                if (cursor.moveToFirst()) {
                    if (str2.equals("■未習得")) {
                        str2 = "";
                    }
                    String str3 = (str2 + "「" + cursor.getString(0) + "」\n") + this.attrs[cursor.getInt(1) - 10] + "属性／";
                    if (cursor.getInt(2) == 0 || cursor.getInt(2) == 999) {
                        str = str3 + "威力―";
                    } else {
                        str = str3 + "威力" + cursor.getInt(2);
                    }
                    int tmp = cursor.getInt(3);
                    if (this.unitSt[cn][act][17] == 200033) {
                        tmp = Math.round((float) ((tmp * 120) / 100));
                    }
                    str2 = (str + "／消費" + tmp + "\n") + brInsert(cursor.getString(4), 22) + "\n\n";
                } else {
                    popAlert("データ取得エラー", "spellOpen/spell_m\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                }
            }
        }
        this.messageText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        this.buttonNg.setPosition(170.0f, 750.0f);
        this.buttonNg.setTag(10000000 + act);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
        if (cursor != null) {
            cursor.close();
        }
    }

    private void spellClose() {
        this.informationBox.detachSelf();
        this.messageText.detachSelf();
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.unitSpellOpenFlg = false;
        this.enemySpellOpenFlg = false;
    }

    private void retireConfirmOpen() {
        String str;
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(this.informationBox, 20.0f);
        attachChild(this.informationBox);
        String str2 = "降参します。\nよろしいですか？\n\n";
        if (this.userLast == 0) {
            str = str2 + "※残機がなくなるため、\n　クエスト挑戦を中断して\n　メニュー画面に戻ります。";
        } else {
            str = str2 + "※残りコンティニュー回数：" + this.userLast + "回";
        }
        this.messageText = new Text(46.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.messageText);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
        this.buttonOk.setPosition(170.0f, 650.0f);
        this.buttonOk.setTag(99999993);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
        this.buttonNg.setPosition(170.0f, 750.0f);
        this.buttonNg.setTag(99999992);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
        this.retireConfirmFlg = true;
    }

    private void retireConfirmClose() {
        this.informationBox.detachSelf();
        this.messageText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.retireConfirmFlg = false;
    }

    private void retireExe() {
        this.retireConfirmFlg = false;
        this.userLast--;
        if (this.userLast < 0) {
            this.questId = 0;
            this.userQuestStatus = 1;
            this.userQuestBattle = 0;
            this.userLast = 0;
        } else {
            this.userQuestStatus = 2;
        }
        this.f216db.execSQL("UPDATE user_t SET user_quest_id=" + this.questId + ", user_quest_status=" + this.userQuestStatus + ", user_quest_battle=" + this.userQuestBattle + ", user_last=" + this.userLast);
        this.handlerEnd = new TimerHandler(1.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                MainScene.this.free();
                MainScene.this.destroy();
                ResourceUtil.getInstance(MainScene.this.getBaseActivity()).resetAllTexture();
            }
        });
        registerUpdateHandler(this.handlerEnd);
    }

    public void popAlert(String title, String text) {
        Sprite alertBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(alertBox, 20.0f);
        attachChild(alertBox);
        this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) "■" + title + "\n" + text, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
    }

    /* access modifiers changed from: private */
    public void arrowOpen() {
        this.arrowR = getBaseActivity().getResourceUtil().getAnimatedSprite("battle/arrowR.png", 1, 2);
        this.arrowR.setPosition(476.0f, 412.0f);
        this.arrowR.animate(new long[]{300, 300}, 0, 1, true);
        attachChild(this.arrowR);
        this.arrowFlg = true;
    }

    public void endConfirmOpen() {
        if (!this.endConfirmFlg) {
            this.endConfirmFlg = true;
            this.endDisplayFlg = true;
            this.endBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
            placeToCenterX(this.endBox, 30.0f);
            attachChild(this.endBox);
            this.endText = new Text((float) LARGE_FONT_SIZE, (float) LARGE_FONT_SIZE, (IFont) this.fontWhite, (CharSequence) "アプリを終了します。\nよろしいですか？", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.endText);
            placeToCenterX(this.endNg, 750.0f);
            this.endNg.setZIndex(99999999);
            placeToCenterX(this.endOk, 650.0f);
            this.endOk.setZIndex(99999998);
            sortChildren();
        }
    }

    public void endConfirmClose() {
        this.endBox.detachSelf();
        this.endText.detachSelf();
        placeToCenterX(this.endNg, 960.0f);
        placeToCenterX(this.endOk, 960.0f);
        this.endConfirmFlg = false;
        this.retireConfirmFlg = false;
    }

    public void tutorialBoxOpen(int tag, int no, String str) {
        this.endBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(this.endBox, 20.0f);
        attachChild(this.endBox);
        this.infoImg = getBaseActivity().getResourceUtil().getSprite("info/info" + no + ".png");
        placeToCenterX(this.infoImg, 50.0f);
        attachChild(this.infoImg);
        this.endText = new Text(40.0f, 400.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.endText);
        this.endOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ok.png", "button/information_ok_p.png");
        placeToCenterX(this.endOk, 520.0f);
        this.endOk.setTag(tag);
        this.endOk.setOnClickListener(this);
        attachChild(this.endOk);
        registerTouchArea(this.endOk);
    }

    public void tutorialBoxClose() {
        this.endBox.detachSelf();
        this.endText.detachSelf();
        this.infoImg.detachSelf();
        this.endOk.detachSelf();
        unregisterTouchArea(this.endOk);
    }

    public void condSave() {
        int[] log = new int[300];
        for (int i = 0; i < 300; i++) {
            log[i] = 0;
        }
        int no = 1;
        for (int i2 = 0; i2 < 2; i2++) {
            log[no] = this.f219us[i2];
            no++;
        }
        for (int i3 = 0; i3 < 2; i3++) {
            for (int s = 0; s < 6; s++) {
                for (int n = 0; n < 18; n++) {
                    log[no] = this.unitSt[i3][s][n];
                    no++;
                }
            }
        }
        for (int i4 = 0; i4 < 2; i4++) {
            for (int s2 = 0; s2 < 10; s2++) {
                log[no] = this.btfl[i4][s2];
                no++;
            }
        }
        String str = "0";
        for (int i5 = 1; i5 < 300; i5++) {
            str = str + "_" + log[i5];
        }
        this.f216db.execSQL("UPDATE user_battle_t SET data='" + str + "' WHERE user_battle_id = 1");
    }

    public void condLoad() {
        String[] log = new String[300];
        int no = 1;
        for (int i = 0; i < 300; i++) {
            log[i] = "0";
        }
        Cursor cursor = this.f216db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=1", null);
        if (cursor.moveToNext()) {
            String[] log2 = cursor.getString(0).split("_");
            for (int i2 = 0; i2 < 2; i2++) {
                this.f219us[i2] = Integer.parseInt(log2[no]);
                no++;
            }
            for (int i3 = 0; i3 < 2; i3++) {
                for (int s = 0; s < 6; s++) {
                    for (int n = 0; n < 18; n++) {
                        this.unitSt[i3][s][n] = Integer.parseInt(log2[no]);
                        no++;
                    }
                }
            }
            for (int i4 = 0; i4 < 2; i4++) {
                for (int s2 = 0; s2 < 10; s2++) {
                    this.btfl[i4][s2] = Integer.parseInt(log2[no]);
                    no++;
                }
            }
        } else {
            popAlert("データ取得エラー", "condLoad\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    public void free() {
        setOnSceneTouchListener(null);
        this.updateHandler.reset();
        unregisterUpdateHandler(this.updateHandler);
        this.updateHandler = null;
        this.handlerInit1 = null;
        this.handlerInit2 = null;
        this.handlerEnd = null;
        this.handlerUnitDisp1 = null;
        this.handlerUnitDisp2 = null;
        this.handlerBattleUnitChange = null;
        this.handlerSpell1 = null;
        this.handlerSpell2 = null;
        this.handlerSpell3 = null;
        this.handlerSpell4 = null;
        this.handlerSpell5 = null;
        this.handlerSpell6 = null;
        this.handlerSendOut1 = null;
        this.handlerSendOut2 = null;
        this.handlerSendOut3 = null;
        this.handlerFireworks1 = null;
        this.handlerFireworks2 = null;
        this.handlerDestroyEffect1 = null;
        this.handlerClearEffect = null;
        this.handlerTurnEnd1 = null;
        this.handlerTurnEnd2 = null;
        clearEntityModifiers();
    }

    public void destroy() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    KeyListenScene scene;
                    if (MainScene.this.bgm != null) {
                        MainScene.this.bgm.release();
                        MainScene.this.bgm = null;
                    }
                    MainScene.this.dropEraseSound.release();
                    MainScene.this.dropEraseSound = null;
                    MainScene.this.shotSound.release();
                    MainScene.this.shotSound = null;
                    MainScene.this.attackSound.release();
                    MainScene.this.attackSound = null;
                    MainScene.this.faintSound.release();
                    MainScene.this.faintSound = null;
                    MainScene.this.bossAppeal.release();
                    MainScene.this.bossAppeal = null;
                    MainScene.this.mapMove.release();
                    MainScene.this.mapMove = null;
                    MainScene.this.mapClear.release();
                    MainScene.this.mapClear = null;
                    MainScene.this.stageClear.release();
                    MainScene.this.stageClear = null;
                    MainScene.this.destroySound.release();
                    MainScene.this.destroySound = null;
                    MainScene.this.pressSound.release();
                    MainScene.this.pressSound = null;
                    MainScene.this.cancellSound.release();
                    MainScene.this.cancellSound = null;
                    MainScene.this.spellUseSound.release();
                    MainScene.this.spellUseSound = null;
                    MainScene.this.spellSound11.release();
                    MainScene.this.spellSound11 = null;
                    MainScene.this.spellSound12.release();
                    MainScene.this.spellSound12 = null;
                    MainScene.this.spellSound13.release();
                    MainScene.this.spellSound13 = null;
                    MainScene.this.spellSound14.release();
                    MainScene.this.spellSound14 = null;
                    MainScene.this.spellSound15.release();
                    MainScene.this.spellSound15 = null;
                    MainScene.this.spellSound16.release();
                    MainScene.this.spellSound16 = null;
                    MainScene.this.spellSound18.release();
                    MainScene.this.spellSound18 = null;
                    MainScene.this.spellSound19.release();
                    MainScene.this.spellSound19 = null;
                    MainScene.this.spellSound25.release();
                    MainScene.this.spellSound25 = null;
                    MainScene.this.spellSound27.release();
                    MainScene.this.spellSound27 = null;
                    MainScene.this.spellSound29.release();
                    MainScene.this.spellSound29 = null;
                    MainScene.this.spellSound30.release();
                    MainScene.this.spellSound30 = null;
                    MainScene.this.spellHealSound.release();
                    MainScene.this.spellHealSound = null;
                    MainScene.this.statusUpSound.release();
                    MainScene.this.statusUpSound = null;
                    MainScene.this.statusDownSound.release();
                    MainScene.this.statusDownSound = null;
                    MainScene.this.extendSound.release();
                    MainScene.this.extendSound = null;
                    MainScene.this.unitAppearSound.release();
                    MainScene.this.unitAppearSound = null;
                    MainScene.this.droidFontTexture1.unload();
                    MainScene.this.droidFontTexture1 = null;
                    MainScene.this.droidFontTexture2.unload();
                    MainScene.this.droidFontTexture2 = null;
                    MainScene.this.droidFontTexture3.unload();
                    MainScene.this.droidFontTexture3 = null;
                    MainScene.this.bitmapFont.unload();
                    MainScene.this.bitmapFont.unloadTextures();
                    MainScene.this.bitmapFont = null;
                    MainScene.this.fontBlack.unload();
                    MainScene.this.fontBlack = null;
                    MainScene.this.fontWhite.unload();
                    MainScene.this.fontWhite = null;
                    MainScene.this.fontYellow.unload();
                    MainScene.this.fontYellow = null;
                    MainScene.this.damageText = null;
                    MainScene.this.unitName = null;
                    MainScene.this.className = null;
                    MainScene.this.messageStr = null;
                    MainScene.this.unitBoxText = null;
                    MainScene.this.bgInitial.dispose();
                    MainScene.this.bgInitial = null;
                    MainScene.this.bgImg.dispose();
                    MainScene.this.bgImg = null;
                    MainScene.this.cartain.dispose();
                    MainScene.this.cartain = null;
                    MainScene.this.cartainBottom.dispose();
                    MainScene.this.cartainBottom = null;
                    MainScene.this.messageText.dispose();
                    MainScene.this.messageText = null;
                    MainScene.this.unAttrImg1.dispose();
                    MainScene.this.unAttrImg1 = null;
                    MainScene.this.unAttrImg2.dispose();
                    MainScene.this.unAttrImg2 = null;
                    MainScene.this.enAttrImg1.dispose();
                    MainScene.this.enAttrImg1 = null;
                    MainScene.this.enAttrImg2.dispose();
                    MainScene.this.enAttrImg2 = null;
                    MainScene.this.unStText.dispose();
                    MainScene.this.unStText = null;
                    MainScene.this.enStText.dispose();
                    MainScene.this.enStText = null;
                    MainScene.this.unVarImg.dispose();
                    MainScene.this.unVarImg = null;
                    MainScene.this.enVarImg.dispose();
                    MainScene.this.enVarImg = null;
                    MainScene.this.unImg.dispose();
                    MainScene.this.unImg = null;
                    MainScene.this.enImg.dispose();
                    MainScene.this.enImg = null;
                    if (MainScene.this.arrowFlg) {
                        MainScene.this.arrowR.dispose();
                    }
                    MainScene.this.arrowR = null;
                    if (MainScene.this.dispBallImgFlg) {
                        MainScene.this.dispBallImg.dispose();
                    }
                    MainScene.this.dispBallImg = null;
                    if (MainScene.this.bgBattleTextFlg) {
                        MainScene.this.bgBattleText.dispose();
                        MainScene.this.battleText.dispose();
                    }
                    MainScene.this.bgBattleText = null;
                    MainScene.this.battleText = null;
                    if (MainScene.this.endDisplayFlg) {
                        MainScene.this.endBox.dispose();
                        MainScene.this.endText.dispose();
                    }
                    MainScene.this.endBox = null;
                    MainScene.this.endText = null;
                    MainScene.this.endNg.dispose();
                    MainScene.this.endOk.dispose();
                    MainScene.this.endNg = null;
                    MainScene.this.endOk = null;
                    if (MainScene.this.attrDisplayFlg) {
                        MainScene.this.attrTable.dispose();
                    }
                    MainScene.this.attrTable = null;
                    MainScene.this.buttonOk.dispose();
                    MainScene.this.buttonOk = null;
                    MainScene.this.buttonCons.dispose();
                    MainScene.this.buttonCons = null;
                    MainScene.this.buttonNg.dispose();
                    MainScene.this.buttonNg = null;
                    MainScene.this.informationBox.dispose();
                    MainScene.this.informationBox = null;
                    MainScene.this.infoText.dispose();
                    MainScene.this.infoText = null;
                    for (int i = 0; i < 4; i++) {
                        ((ButtonSprite) MainScene.this.menuBottomButton.get(i)).dispose();
                    }
                    MainScene.this.menuBottomButton.clear();
                    MainScene.this.menuBottomButton = null;
                    for (int i2 = 0; i2 < 4; i2++) {
                        MainScene.this.unVar[i2].dispose();
                    }
                    MainScene.this.unVar = null;
                    for (int i3 = 0; i3 < 12; i3++) {
                        ((Sprite) MainScene.this.unBallImg.get(i3)).dispose();
                    }
                    MainScene.this.unBallImg.clear();
                    MainScene.this.unBallImg = null;
                    for (int i4 = 0; i4 < 6; i4++) {
                        ((Sprite) MainScene.this.boxBgList.get(i4)).dispose();
                        ((Sprite) MainScene.this.unitBoxImg.get(i4)).dispose();
                        ((Sprite) MainScene.this.unitBoxAttr1.get(i4)).dispose();
                        ((Sprite) MainScene.this.unitBoxAttr2.get(i4)).dispose();
                        ((ButtonSprite) MainScene.this.unitBoxBt1.get(i4)).dispose();
                        ((ButtonSprite) MainScene.this.unitBoxBt2.get(i4)).dispose();
                    }
                    MainScene.this.boxBgList.clear();
                    MainScene.this.boxBgList = null;
                    MainScene.this.unitBoxImg.clear();
                    MainScene.this.unitBoxImg = null;
                    MainScene.this.unitBoxAttr1.clear();
                    MainScene.this.unitBoxAttr1 = null;
                    MainScene.this.unitBoxAttr2.clear();
                    MainScene.this.unitBoxAttr2 = null;
                    MainScene.this.unitBoxBt1.clear();
                    MainScene.this.unitBoxBt1 = null;
                    MainScene.this.unitBoxBt2.clear();
                    MainScene.this.unitBoxBt2 = null;
                    if (MainScene.this.fireworksFlg) {
                        for (int i5 = 0; i5 < 6; i5++) {
                            ((AnimatedSprite) MainScene.this.fireworks.get(i5)).dispose();
                        }
                        MainScene.this.fireworks.clear();
                    }
                    MainScene.this.fireworks = null;
                    MainScene.this.attackBullet.dispose();
                    MainScene.this.attackBullet = null;
                    if (MainScene.this.spellEffectFlg1) {
                        MainScene.this.spellEffect1.dispose();
                    }
                    if (MainScene.this.spellEffectFlg2) {
                        MainScene.this.spellEffect2.dispose();
                    }
                    MainScene.this.spellEffect1 = null;
                    MainScene.this.spellEffect2 = null;
                    MainScene.this.ind = null;
                    MainScene.this.ord = null;
                    MainScene.this.spl = null;
                    MainScene.this.spn = null;
                    MainScene.this.spd = null;
                    MainScene.this.skc = null;
                    MainScene.this.espd = null;
                    MainScene.this.orgSt = null;
                    MainScene.this.userName = null;
                    MainScene.this.userIcon = null;
                    MainScene.this.f219us = null;
                    MainScene.this.userKodamaId = null;
                    MainScene.this.unitSt = null;
                    MainScene.this.unitName = null;
                    MainScene.this.className = null;
                    MainScene.this.btfl = null;
                    MainScene.this.attrs = null;
                    MainScene.this.rnd = null;
                    MainScene.this.pre = null;
                    MainScene.this.dbh = null;
                    MainScene.this.f216db = null;
                    MainScene.this.getBaseActivity().getSoundManager().releasePool();
                    if (MainScene.this.questId == 0) {
                        scene = new MenuScene(MainScene.this.getBaseActivity());
                    } else {
                        scene = new QuestScene(MainScene.this.getBaseActivity());
                    }
                    MainScene.this.getBaseActivity().getEngine().setScene(scene);
                    MainScene.this.getBaseActivity().appendScene(scene);
                }
            });
        } catch (Exception e) {
        }
    }

    private void hideAds1() {
        getBaseActivity().goneNativeViewFromId(C0436R.C0439id.adLayout1);
    }
}
