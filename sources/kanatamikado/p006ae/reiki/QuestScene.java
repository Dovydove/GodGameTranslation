package kanatamikado.p006ae.reiki;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.p000v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.FadeOutModifier;
import org.andengine.entity.modifier.MoveModifier;
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

/* renamed from: kanatamikado.ae.reiki.QuestScene */
public class QuestScene extends KeyListenScene implements IOnSceneTouchListener, OnClickListener {
    private static final float FONT_SIZE = 24.0f;
    private static final float LARGE_FONT_SIZE = 48.0f;
    private static final float MIDDLE_FONT_SIZE = 36.0f;
    private final int ACTION_TAG = 99999996;
    private final int ATTR_CLOSE = 99999992;
    private final int ATTR_OPEN = 99999991;
    private final int BOX_LIMIT = 20;
    private final int BUTTON_LEFT = 91;
    private final int BUTTON_RIGHT = 92;
    private final int BUTTON_SORT = 90;
    private final int CHANGE_TAG = 99999994;
    private final long[] CHARA_ANIME = {300, 300, 300, 300};
    private final int[] CHARA_FRAME3 = {3, 4, 5, 4};
    private final int DETAIL_TAG = 99999993;
    private final long[] EMOTION_ANIME = {300, 300};
    private final int EQUIPS_TAG = 99999995;
    private final int GAME_END_NG = 99999998;
    private final int GAME_END_OK = 99999999;
    private final int HEADLINES_Y = 718;
    private final int HEADLINE_Y = 0;
    private final int ITEM_LIMIT = 999999;
    private final int MENU_MODE_MAIN = 11;
    private final int MENU_MODE_MOVE = 1;
    private final int MENU_MODE_PARTY_CHANGE = 21;
    private final int MENU_MODE_UNIT = 31;
    private final int MENU_MODE_UNIT_EQUIP = 32;
    private final int MENU_MODE_UNIT_EQUIP_CONFIRM = 33;
    private final int MYPAGE_TAG = 99999997;
    private final int NG_TAG = 99999990;
    private final int PARTY_LIMIT = 9;
    private final int SORT_MAX_NO = 3;
    private final int SORT_NO_PARTY = 2;
    private final int SORT_NO_UNIT = 1;
    /* access modifiers changed from: private */
    public Sound StartPressedSound;
    private int areaId = 1;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> attrList1;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> attrList2;
    /* access modifiers changed from: private */
    public String[] attrs = {"―", "無", "樹", "闘", "毒", "地", "風", "虫", "岩", "鋼", "霊", "水", "雷", "氷", "理", "炎", "神", "闇", "然", "―", "―"};
    /* access modifiers changed from: private */
    public Text battleText;
    /* access modifiers changed from: private */
    public Sprite bgBattleText;
    /* access modifiers changed from: private */
    public boolean bgBattleTextFlg = false;
    /* access modifiers changed from: private */
    public Sprite bgImg1;
    private int bgImg1No = 1;
    /* access modifiers changed from: private */
    public Sprite bgImg2;
    /* access modifiers changed from: private */
    public Sprite bgInitial;
    /* access modifiers changed from: private */
    public int bgPositionX = 0;
    /* access modifiers changed from: private */
    public Music bgm;
    private int bgmFlg = 1;
    private int bgmMapNo = 1;
    /* access modifiers changed from: private */
    public BitmapFont bitmapFontS;
    private int boxSelect = 1;
    /* access modifiers changed from: private */
    public ButtonSprite buttonAction;
    /* access modifiers changed from: private */
    public ButtonSprite buttonArrowL;
    /* access modifiers changed from: private */
    public ButtonSprite buttonArrowR;
    /* access modifiers changed from: private */
    public Sound buttonCanceledSound;
    /* access modifiers changed from: private */
    public ButtonSprite buttonEtc1;
    /* access modifiers changed from: private */
    public ButtonSprite buttonEtc2;
    /* access modifiers changed from: private */
    public ButtonSprite buttonEtc3;
    /* access modifiers changed from: private */
    public ArrayList<ButtonSprite> buttonList;
    /* access modifiers changed from: private */
    public Text[] buttonListText;
    /* access modifiers changed from: private */
    public ButtonSprite buttonNg;
    private int buttonNum;
    /* access modifiers changed from: private */
    public ButtonSprite buttonOk;
    /* access modifiers changed from: private */
    public Sound buttonPressedSound;
    /* access modifiers changed from: private */
    public ButtonSprite buttonSort;
    /* access modifiers changed from: private */
    public Rectangle cartain;
    /* access modifiers changed from: private */
    public Rectangle cartainBottom;
    TimerHandler commonHandler;
    TimerHandler commonHandler2;
    TimerHandler commonHandler3;
    /* access modifiers changed from: private */

    /* renamed from: db */
    public SQLiteDatabase f222db = this.dbh.getWritableDatabase();
    /* access modifiers changed from: private */
    public Database dbh = new Database(getBaseActivity());
    /* access modifiers changed from: private */
    public Text detailText;
    ITexture droidFontTexture1;
    ITexture droidFontTexture2;
    ITexture droidFontTexture3;
    private Editor editor;
    /* access modifiers changed from: private */
    public AnimatedSprite emImg;
    private int emNo = 1;
    /* access modifiers changed from: private */
    public Sprite endBox;
    private boolean endConfirmFlg = false;
    /* access modifiers changed from: private */
    public boolean endDisplayFlg = false;
    /* access modifiers changed from: private */
    public ButtonSprite endNg;
    /* access modifiers changed from: private */
    public ButtonSprite endOk;
    /* access modifiers changed from: private */
    public Text endText;
    /* access modifiers changed from: private */
    public boolean enemyEncountFlg = false;
    private int enemyIcon = 1;
    /* access modifiers changed from: private */
    public Font fontBlack;
    /* access modifiers changed from: private */
    public Font fontWhite;
    /* access modifiers changed from: private */
    public Font fontYellow;
    TimerHandler handlerBgScroll1;
    TimerHandler handlerBgScroll2;
    /* access modifiers changed from: private */
    public Sprite headlineBox;
    /* access modifiers changed from: private */
    public Text headlineBoxText;
    /* access modifiers changed from: private */
    public Text infoText;
    /* access modifiers changed from: private */
    public Sprite informationBox;
    private int listPage;
    private int listPageMax;
    /* access modifiers changed from: private */
    public AnimatedSprite mapIcon;
    private int mapLv = 1;
    /* access modifiers changed from: private */
    public Sound mapMove;
    /* access modifiers changed from: private */
    public int menuMode = 1;
    /* access modifiers changed from: private */
    public Sprite messageBox;
    /* access modifiers changed from: private */
    public String messageStr = "";
    /* access modifiers changed from: private */
    public Text messageText;
    /* access modifiers changed from: private */
    public Sprite miniMap;
    /* access modifiers changed from: private */
    public boolean mypageFlg = false;
    /* access modifiers changed from: private */
    public Sound pageMoveSound;
    private int partyNo = 1;
    private int partySortNo = 1;
    private SharedPreferences pre;
    private int questId = 1;
    private String questName = "";
    private TimerHandler questUpdateHandler = new TimerHandler(0.016666668f, true, new ITimerCallback() {
        public void onTimePassed(TimerHandler pTimerHandler) {
            if (QuestScene.this.targetCursorFlg) {
                QuestScene.this.targetCursorRotation = QuestScene.this.targetCursorRotation + 3;
                if (360 <= QuestScene.this.targetCursorRotation) {
                    QuestScene.this.targetCursorRotation = 0;
                }
                QuestScene.this.targetCursor.setRotation((float) QuestScene.this.targetCursorRotation);
            }
        }
    });
    private Random rnd = new Random();
    /* access modifiers changed from: private */

    /* renamed from: sb */
    public int[] f223sb = new int[4];
    /* access modifiers changed from: private */
    public ArrayList<Sprite> shadowList;
    private int sortChangeList = 1;
    private int sortUnitList = 1;
    private int soundFlg = 1;
    private int stageId = 1;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> statusList;
    /* access modifiers changed from: private */
    public Text[] statusText;
    /* access modifiers changed from: private */
    public Sprite targetCursor;
    /* access modifiers changed from: private */
    public boolean targetCursorFlg = false;
    /* access modifiers changed from: private */
    public int targetCursorRotation = 0;
    private int targetUserKodamaId = 0;
    /* access modifiers changed from: private */
    public boolean unitDetailDisplay = false;
    /* access modifiers changed from: private */
    public Sprite unitDetailIcon;
    /* access modifiers changed from: private */
    public AnimatedSprite unitImg1;
    /* access modifiers changed from: private */
    public AnimatedSprite unitImg2;
    /* access modifiers changed from: private */
    public int userDemoNo = 0;
    private int userIcon = 0;
    /* access modifiers changed from: private */
    public int userQuestBattle = 0;
    private int userQuestStatus = 0;
    /* access modifiers changed from: private */
    public boolean waitFlg = true;

    public QuestScene(MultiSceneActivity baseActivity) {
        super(baseActivity);
        init();
    }

    public void init() {
        String str;
        this.pre = getBaseActivity().getSharedPreferences(MainActivity.PREFERRENCES_FILE_NAME, 0);
        this.editor = this.pre.edit();
        this.boxSelect = this.pre.getInt("boxSelect", 1);
        this.partyNo = this.pre.getInt("partyNo", 1);
        this.sortUnitList = this.pre.getInt("sortUnitList", 1);
        this.sortChangeList = this.pre.getInt("sortChangeList", 1);
        this.bgmFlg = this.pre.getInt("bgmFlg", 1);
        this.soundFlg = this.pre.getInt("soundFlg", 1);
        this.shadowList = new ArrayList<>();
        this.attrList1 = new ArrayList<>();
        this.attrList2 = new ArrayList<>();
        this.statusList = new ArrayList<>();
        this.statusText = new Text[100];
        this.informationBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(this.informationBox, 30.0f);
        this.endNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
        placeToCenterX(this.endNg, 960.0f);
        this.endNg.setTag(99999998);
        this.endNg.setOnClickListener(this);
        attachChild(this.endNg);
        registerTouchArea(this.endNg);
        this.endOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
        placeToCenterX(this.endOk, 960.0f);
        this.endOk.setTag(99999999);
        this.endOk.setOnClickListener(this);
        attachChild(this.endOk);
        registerTouchArea(this.endOk);
        this.buttonList = new ArrayList<>();
        this.buttonListText = new Text[20];
        this.droidFontTexture1 = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontWhite = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture1, getBaseActivity().getResourceUtil().getTypeface(), (float) FONT_SIZE, true, Color.rgb(255, 255, 255));
        this.fontWhite.load();
        this.bitmapFontS = new BitmapFont(getBaseActivity().getTextureManager(), getBaseActivity().getAssets(), "font/numS.fnt");
        this.bitmapFontS.load();
        Cursor cursor = this.f222db.rawQuery("SELECT u.user_id, u.user_code, u.user_password, u.user_name, u.user_money, u.user_sp, u.user_max_sp, u.user_sp_restore_time, u.user_icon, u.user_difficult, u.user_quest_id, u.user_quest_status, u.user_quest_battle, u.user_demo_no FROM user_t u", null);
        if (cursor.moveToFirst()) {
            this.userIcon = cursor.getInt(8);
            this.questId = cursor.getInt(10);
            this.userQuestStatus = cursor.getInt(11);
            this.userQuestBattle = cursor.getInt(12);
            this.userDemoNo = cursor.getInt(13);
            if (this.userQuestStatus == 1) {
                this.userQuestStatus = 2;
            }
            if (this.questId == 510000) {
                if (this.userQuestBattle == 2) {
                    this.userQuestBattle = 4;
                } else if (this.userQuestBattle == 5) {
                    this.userQuestBattle = 7;
                }
                this.f222db.execSQL("UPDATE user_t SET user_quest_battle=" + this.userQuestBattle);
            }
            cursor = this.f222db.rawQuery("SELECT q.area_id, q.stage_id, q.img, q.quest_name, q.map_lv, q.bgm_map FROM quest_m q WHERE q.quest_id=" + this.questId, null);
            if (cursor.moveToFirst()) {
                this.areaId = cursor.getInt(0);
                this.stageId = cursor.getInt(1);
                this.bgImg1No = cursor.getInt(2);
                this.questName = cursor.getString(3);
                this.mapLv = cursor.getInt(4);
                this.bgmMapNo = cursor.getInt(5);
                try {
                    this.bgm = MusicFactory.createMusicFromAsset(getBaseActivity().getMusicManager(), getBaseActivity(), "music/map/" + this.bgmMapNo + ".mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                popAlert("データ取得エラー", "quest_m\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (this.mapLv <= 0) {
                cursor = this.f222db.rawQuery("SELECT u.quest_id FROM user_progress_t u WHERE u.quest_id < 100000 ORDER BY u.quest_id DESC", null);
                if (cursor.moveToFirst()) {
                    cursor = this.f222db.rawQuery("SELECT q.map_lv FROM quest_m q WHERE q.quest_id<=" + cursor.getInt(0) + " ORDER BY q.quest_id DESC", null);
                    if (cursor.moveToFirst()) {
                        this.mapLv = cursor.getInt(0) + this.mapLv;
                    }
                }
            }
        } else {
            popAlert("データ取得エラー", "uset_t\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
        }
        this.droidFontTexture2 = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontBlack = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture2, getBaseActivity().getResourceUtil().getTypeface(), (float) FONT_SIZE, true, Color.rgb(0, 0, 0));
        this.fontBlack.load();
        this.droidFontTexture3 = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        if ((176 > this.questId || this.questId > 182) && ((190 > this.questId || this.questId > 196) && ((225 > this.questId || this.questId > 231) && (274 > this.questId || this.questId > 280)))) {
            this.fontYellow = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture3, getBaseActivity().getResourceUtil().getTypeface(), (float) LARGE_FONT_SIZE, true, Color.rgb(244, 216, 119));
        } else {
            this.fontYellow = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture3, getBaseActivity().getResourceUtil().getTypeface(), (float) MIDDLE_FONT_SIZE, true, Color.rgb(244, 216, 119));
        }
        this.fontYellow.load();
        bgmChange(false);
        soundChange(false);
        this.bgInitial = getBaseActivity().getResourceUtil().getSprite("bg/initial.png");
        this.bgInitial.setPosition(0.0f, 0.0f);
        attachChild(this.bgInitial);
        this.bgPositionX = -356;
        menuQuestOpen(true);
        this.cartainBottom = new Rectangle(0.0f, 708.0f, 540.0f, 252.0f, getBaseActivity().getVertexBufferObjectManager());
        this.cartainBottom.setColor(0.0f, 0.0f, 0.0f);
        this.cartainBottom.setAlpha(0.5f);
        attachChild(this.cartainBottom);
        questEncount();
        if (this.userQuestBattle == 1) {
            this.bgBattleTextFlg = true;
            this.bgBattleText = getBaseActivity().getResourceUtil().getSprite("battle/BattleTextBg.png");
            this.bgBattleText.setPosition(0.0f, 214.0f);
            attachChild(this.bgBattleText);
            String str2 = "";
            if (this.areaId < 100000) {
                str = "エリア" + this.areaId + " " + this.questName + "-" + this.stageId;
            } else if (this.areaId < 500000) {
                str = this.questName + "-" + this.stageId;
            } else {
                str = this.questName;
            }
            this.battleText = new Text(0.0f, 0.0f, (IFont) this.fontYellow, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            this.battleText.setPosition((540.0f - this.battleText.getWidth()) / 2.0f, 270.0f);
            attachChild(this.battleText);
        }
        registerUpdateHandler(this.questUpdateHandler);
        this.infoText = new Text(0.0f, 0.0f, (IFont) this.fontWhite, (CharSequence) "test", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        this.cartain = new Rectangle(0.0f, 0.0f, 540.0f, 960.0f, getBaseActivity().getVertexBufferObjectManager());
        this.cartain.setColor(0.0f, 0.0f, 0.0f);
        attachChild(this.cartain);
        this.commonHandler = new TimerHandler(1.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                QuestScene.this.infoText.detachSelf();
                QuestScene.this.cartain.registerEntityModifier(new FadeOutModifier(1.0f));
                QuestScene.this.commonHandler2 = new TimerHandler(1.0f, new ITimerCallback() {
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        QuestScene.this.cartain.detachSelf();
                        if (QuestScene.this.userQuestBattle == 1) {
                            QuestScene.this.bgBattleText.registerEntityModifier(new FadeOutModifier(1.0f));
                            QuestScene.this.battleText.registerEntityModifier(new FadeOutModifier(1.0f));
                            QuestScene.this.commonHandler3 = new TimerHandler(1.0f, new ITimerCallback() {
                                public void onTimePassed(TimerHandler pTimerHandler) {
                                    QuestScene.this.bgBattleText.detachSelf();
                                    QuestScene.this.battleText.detachSelf();
                                }
                            });
                            QuestScene.this.registerUpdateHandler(QuestScene.this.commonHandler3);
                        }
                    }
                });
                QuestScene.this.registerUpdateHandler(QuestScene.this.commonHandler2);
                QuestScene.this.bgScroll();
                if (QuestScene.this.bgm != null) {
                    QuestScene.this.bgm.setLooping(true);
                    QuestScene.this.bgm.play();
                }
            }
        });
        registerUpdateHandler(this.commonHandler);
        if (cursor != null) {
            cursor.close();
        }
    }

    public void prepareSoundAndMusic() {
        try {
            this.mapMove = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MapMove.ogg");
            this.buttonPressedSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouSystem49.ogg");
            this.buttonCanceledSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouSystem43.ogg");
            this.StartPressedSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamDecisionStart.ogg");
            this.pageMoveSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouPaper01.ogg");
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
            this.mapMove.setVolume(0.0f);
            this.buttonPressedSound.setVolume(0.0f);
            this.buttonCanceledSound.setVolume(0.0f);
            this.StartPressedSound.setVolume(0.0f);
            this.pageMoveSound.setVolume(0.0f);
            return;
        }
        this.mapMove.setVolume(1.0f);
        this.buttonPressedSound.setVolume(1.0f);
        this.buttonCanceledSound.setVolume(1.0f);
        this.StartPressedSound.setVolume(1.0f);
        this.pageMoveSound.setVolume(1.0f);
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getAction() == 0 && e.getKeyCode() == 4 && !this.endConfirmFlg) {
            endConfirmOpen();
        }
        return false;
    }

    public void onDestroy() {
    }

    /* JADX INFO: finally extract failed */
    public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        Log.d("Debug", "QuestScene_onClick:" + pButtonSprite.getTag());
        String str = "";
        if (this.endConfirmFlg) {
            if (pButtonSprite.getTag() == 99999999) {
                ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
                getBaseActivity().finish();
            } else if (pButtonSprite.getTag() == 99999998) {
                this.buttonCanceledSound.play();
                endConfirmClose();
            }
        } else if (this.waitFlg) {
        } else {
            if (pButtonSprite.getTag() == 99999990) {
                this.buttonCanceledSound.play();
            } else if (pButtonSprite.getTag() == 90) {
                this.buttonPressedSound.play();
                sortChange();
            } else if (pButtonSprite.getTag() == 99999996) {
                this.buttonPressedSound.play();
                if (!this.enemyEncountFlg) {
                    this.buttonAction.detachSelf();
                    unregisterTouchArea(this.buttonAction);
                    this.emImg.detachSelf();
                    this.unitImg2.detachSelf();
                    this.messageText.detachSelf();
                    this.userQuestBattle++;
                    this.mapIcon.setPosition((float) (((7 - this.userQuestBattle) * 74) + 16), 8.0f);
                    questEncount();
                    bgScroll();
                } else if (partyCheck()) {
                    if (this.userDemoNo < 4) {
                        this.cartainBottom.detachSelf();
                    }
                    battleStart();
                } else {
                    this.buttonCanceledSound.play();
                }
            } else if (this.menuMode == 11) {
                if (pButtonSprite.getTag() == 99999997) {
                    this.mypageFlg = true;
                    battleStart();
                } else if (pButtonSprite.getTag() == 99999991) {
                    this.buttonPressedSound.play();
                    menuQuestClose();
                    attrTableOpen();
                } else if (pButtonSprite.getTag() == 99999992) {
                    this.buttonPressedSound.play();
                    attrTableClose();
                    menuQuestOpen(false);
                    if (this.userDemoNo < 4) {
                        this.cartainBottom = new Rectangle(0.0f, 708.0f, 540.0f, 252.0f, getBaseActivity().getVertexBufferObjectManager());
                        this.cartainBottom.setColor(0.0f, 0.0f, 0.0f);
                        this.cartainBottom.setAlpha(0.5f);
                        attachChild(this.cartainBottom);
                    }
                } else if (4 > this.userDemoNo) {
                } else {
                    if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                        this.pageMoveSound.play();
                        bottomPartyClose();
                        if (pButtonSprite.getTag() == 91) {
                            this.partyNo--;
                            if (this.partyNo < 1) {
                                this.partyNo = 9;
                            }
                        } else if (pButtonSprite.getTag() == 92) {
                            this.partyNo++;
                            if (9 < this.partyNo) {
                                this.partyNo = 1;
                            }
                        }
                        this.editor.putInt("partyNo", this.partyNo);
                        this.editor.commit();
                        bottomPartyOpen();
                    } else if (pButtonSprite.getTag() == 99999993 || pButtonSprite.getTag() == 99999995) {
                        Cursor cursor = null;
                        boolean engageFlg = false;
                        try {
                            Cursor cursor2 = this.f222db.rawQuery("SELECT u.equip FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id WHERE p.party_no=" + this.partyNo + " AND p.sort_no=" + this.partySortNo, null);
                            if (cursor2.moveToFirst() && 200100 < cursor2.getInt(0) && cursor2.getInt(0) < 200200) {
                                engageFlg = true;
                            }
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            try {
                                cursor2 = this.f222db.rawQuery("SELECT u.kodama_id FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id WHERE p.party_no=" + this.partyNo + " AND p.sort_no=" + this.partySortNo, null);
                                if (!cursor2.moveToFirst()) {
                                    this.buttonCanceledSound.play();
                                } else if (cursor2.getInt(0) == 0) {
                                    this.buttonCanceledSound.play();
                                } else if (pButtonSprite.getTag() == 99999993) {
                                    this.buttonPressedSound.play();
                                    menuQuestClose();
                                    menuUnitDetailOpen();
                                } else if (pButtonSprite.getTag() == 99999995) {
                                    if (engageFlg) {
                                        this.buttonCanceledSound.play();
                                    } else {
                                        this.buttonPressedSound.play();
                                        menuQuestClose();
                                        menuEquipSelectOpen();
                                    }
                                }
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                            } catch (Throwable th) {
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            if (cursor != null) {
                                cursor.close();
                            }
                            throw th2;
                        }
                    } else if (pButtonSprite.getTag() == 99999994) {
                        this.buttonPressedSound.play();
                        menuQuestClose();
                        menuPartyChangeOpen();
                    } else if (10000000 < pButtonSprite.getTag()) {
                        this.buttonPressedSound.play();
                        this.partySortNo = pButtonSprite.getTag() - 10000000;
                        this.targetCursor.setPosition((float) ((((this.partySortNo - 1) * 80) + 40) - 16), 774.0f);
                    }
                }
            } else if (this.menuMode == 31) {
                if (pButtonSprite.getTag() == 10000000) {
                    this.buttonCanceledSound.play();
                    menuUnitDetailClose();
                    menuQuestOpen(false);
                } else if (pButtonSprite.getTag() == 10000001) {
                    this.buttonPressedSound.play();
                    menuUnitDetailClose();
                    menuSpellOpen();
                } else if (pButtonSprite.getTag() == 10000002) {
                    this.buttonCanceledSound.play();
                    menuSpellClose();
                    menuQuestOpen(false);
                } else if (pButtonSprite.getTag() == 10000003) {
                    this.buttonPressedSound.play();
                    menuUnitDetailClose();
                    menuEquipSelectOpen();
                }
            } else if (this.menuMode == 21) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuPartyChangeClose();
                    if (pButtonSprite.getTag() == 91) {
                        this.boxSelect--;
                        if (this.boxSelect < 1) {
                            this.boxSelect = 20;
                        }
                    } else if (pButtonSprite.getTag() == 92) {
                        this.boxSelect++;
                        if (20 < this.boxSelect) {
                            this.boxSelect = 1;
                        }
                    }
                    this.editor.putInt("boxSelect", this.boxSelect);
                    this.editor.commit();
                    menuPartyChangeOpen();
                } else if (pButtonSprite.getTag() == 9999999) {
                    this.buttonCanceledSound.play();
                    menuPartyChangeClose();
                    menuQuestOpen(false);
                } else if (pButtonSprite.getTag() == 10000000) {
                    this.buttonCanceledSound.play();
                } else if (10000000 <= pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    partyChangeExe(pButtonSprite.getTag() - 10000000);
                }
            } else if (this.menuMode == 32) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuEquipSelectClose();
                    if (pButtonSprite.getTag() == 91) {
                        this.listPage--;
                        if (this.listPage < 0) {
                            this.listPage = this.listPageMax - 1;
                        }
                    } else {
                        this.listPage++;
                        if (this.listPageMax - 1 < this.listPage) {
                            this.listPage = 0;
                        }
                    }
                    menuEquipSelectOpen();
                } else if (pButtonSprite.getTag() == 10000000) {
                    this.buttonCanceledSound.play();
                    menuEquipSelectClose();
                    menuUnitDetailOpen();
                } else if (10000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.buttonPressedSound.play();
                    menuEquipSelectClose();
                    menuEquipDetailOpen(pButtonSprite.getTag() - 10000000);
                } else if (pButtonSprite.getTag() == 20000000) {
                    this.buttonPressedSound.play();
                    menuEquipExe(0);
                }
            } else if (this.menuMode != 33) {
            } else {
                if (pButtonSprite.getTag() == 10000000) {
                    this.buttonCanceledSound.play();
                    menuEquipDetailClose();
                    menuEquipSelectOpen();
                    return;
                }
                this.buttonPressedSound.play();
                menuEquipExe(pButtonSprite.getTag() - 10000000);
            }
        }
    }

    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        return true;
    }

    /* JADX INFO: finally extract failed */
    private void questEncount() {
        int getItem;
        int eK1;
        int eK2;
        int eK3;
        int eK4;
        String str = "";
        Cursor cursor = null;
        boolean cursor2 = null;
        enemyEncountCheck();
        if (this.enemyEncountFlg) {
            if ((100000 < this.questId && this.questId < 500000) || this.questId == 510000) {
                try {
                    cursor = this.f222db.rawQuery("SELECT e.quest_id FROM enemy_m e WHERE e.enemy_id=100000", null);
                    cursor2 = cursor.moveToFirst();
                    if (cursor2 && cursor.getInt(0) == 0) {
                        if (this.questId == 510000) {
                            int i = this.userQuestBattle;
                            if (i == 1 || i == 4) {
                                if (this.rnd.nextInt(100) < 65) {
                                    i++;
                                }
                                if (this.rnd.nextInt(100) < 50) {
                                    i++;
                                }
                            }
                            Cursor cursor22 = this.f222db.rawQuery("SELECT e.title, e.name, e.kana, e.k1, e.k2, e.k3, e.k4, e.k5, e.k6, e.img FROM enemy_m e WHERE 500000 < e.quest_id AND e.quest_id < 510000 AND e.battle=" + i + " ORDER BY RANDOM();", null);
                            cursor2 = cursor22.moveToFirst();
                            if (cursor2) {
                                String sql = "UPDATE enemy_m SET   quest_id=" + this.questId + ", battle=" + this.userQuestBattle + ", title=\"" + cursor22.getString(0) + "\", name=\"" + cursor22.getString(1) + "\", kana=\"" + cursor22.getString(2) + "\", k1=" + cursor22.getInt(3) + ", k2=" + cursor22.getInt(4) + ", k3=" + cursor22.getInt(5) + ", k4=" + cursor22.getInt(6) + ", k5=" + cursor22.getInt(7) + ", k6=" + cursor22.getInt(8) + ", img=" + cursor22.getInt(9) + " WHERE enemy_id=100000";
                                this.f222db.execSQL(sql);
                                this.f222db.execSQL("UPDATE user_battle_t SET data='" + sql + "' WHERE user_battle_id=2");
                            }
                            if (cursor22 != null) {
                                cursor22.close();
                            }
                        } else if (this.userQuestBattle == 7) {
                            String eTitle = "";
                            String eName = "";
                            String eKana = "";
                            int eK5 = 0;
                            int eImg = 0;
                            int[] eList1 = new int[6];
                            int[] eList2 = new int[18];
                            if (this.areaId == 100001) {
                                eTitle = "杜氏見習い";
                                eName = "律";
                                eKana = "りつ";
                                eImg = 2001;
                                eList1[0] = 45;
                                eList1[1] = 244;
                                eList1[2] = 262;
                                eList1[3] = 266;
                                eList1[4] = 73;
                                eList1[5] = 352;
                                eList2[0] = 47;
                                eList2[1] = 48;
                                eList2[2] = 245;
                                eList2[3] = 246;
                                eList2[4] = 247;
                                eList2[5] = 263;
                                eList2[6] = 264;
                                eList2[7] = 265;
                                eList2[8] = 430;
                                eList2[9] = 267;
                                eList2[10] = 268;
                                eList2[11] = 269;
                                eList2[12] = 74;
                                eList2[13] = 75;
                                eList2[14] = 454;
                                eList2[15] = 367;
                                eList2[16] = 353;
                                eList2[17] = 249;
                            } else if (this.areaId == 200001) {
                                eTitle = "ジムリーダー";
                                eName = "揚羽";
                                eKana = "あげは";
                                eImg = 2002;
                                eList1[0] = 97;
                                eList1[1] = 110;
                                eList1[2] = 214;
                                eList1[3] = 222;
                                eList1[4] = 348;
                                eList1[5] = 504;
                                eList2[0] = 111;
                                eList2[1] = 112;
                                eList2[2] = 98;
                                eList2[3] = 99;
                                eList2[4] = 215;
                                eList2[5] = 216;
                                eList2[6] = 217;
                                eList2[7] = 458;
                                eList2[8] = 224;
                                eList2[9] = 225;
                                eList2[10] = 427;
                                eList2[11] = 349;
                                eList2[12] = 447;
                                eList2[13] = 288;
                                eList2[14] = 431;
                                eList2[15] = 505;
                                eList2[16] = 215;
                                eList2[17] = 216;
                            } else if (this.areaId == 300001) {
                                eTitle = "霊具技師";
                                eName = "美月";
                                eKana = "みつき";
                                eImg = 2003;
                                eList1[0] = 91;
                                eList1[1] = 158;
                                eList1[2] = 235;
                                eList1[3] = 262;
                                eList1[4] = 348;
                                eList1[5] = 31;
                                eList2[0] = 92;
                                eList2[1] = 93;
                                eList2[2] = 159;
                                eList2[3] = 161;
                                eList2[4] = 236;
                                eList2[5] = 237;
                                eList2[6] = 238;
                                eList2[7] = 263;
                                eList2[8] = 264;
                                eList2[9] = 265;
                                eList2[10] = 430;
                                eList2[11] = 349;
                                eList2[12] = 447;
                                eList2[13] = 351;
                                eList2[14] = 446;
                                eList2[15] = 32;
                                eList2[16] = 33;
                                eList2[17] = 74;
                            } else if (this.areaId == 400001) {
                                eTitle = "店主";
                                eName = "輪華";
                                eKana = "りんか";
                                eImg = 2004;
                                eList1[0] = 138;
                                eList1[1] = 202;
                                eList1[2] = 270;
                                eList1[3] = 105;
                                eList1[4] = 86;
                                eList1[5] = 177;
                                eList2[0] = 139;
                                eList2[1] = 141;
                                eList2[2] = 204;
                                eList2[3] = 205;
                                eList2[4] = 271;
                                eList2[5] = 272;
                                eList2[6] = 273;
                                eList2[7] = 106;
                                eList2[8] = 107;
                                eList2[9] = 108;
                                eList2[10] = 87;
                                eList2[11] = 89;
                                eList2[12] = 178;
                                eList2[13] = 179;
                                eList2[14] = 170;
                                eList2[15] = 125;
                                eList2[16] = 101;
                                eList2[17] = 388;
                            }
                            if (60 <= this.mapLv) {
                                eK1 = eList2[this.rnd.nextInt(18)];
                                eK2 = eList2[this.rnd.nextInt(18)];
                                eK3 = eList2[this.rnd.nextInt(18)];
                                eK4 = eList2[this.rnd.nextInt(18)];
                                eK5 = eList2[this.rnd.nextInt(18)];
                            } else if (50 <= this.mapLv) {
                                eK1 = eList2[this.rnd.nextInt(18)];
                                eK2 = eList2[this.rnd.nextInt(18)];
                                eK3 = eList1[this.rnd.nextInt(6)];
                                eK4 = eList2[this.rnd.nextInt(18)];
                                eK5 = eList2[this.rnd.nextInt(18)];
                            } else if (40 <= this.mapLv) {
                                eK1 = eList1[this.rnd.nextInt(6)];
                                eK2 = eList2[this.rnd.nextInt(18)];
                                eK3 = eList1[this.rnd.nextInt(6)];
                                eK4 = eList2[this.rnd.nextInt(18)];
                                eK5 = eList2[this.rnd.nextInt(18)];
                            } else if (30 <= this.mapLv) {
                                eK1 = eList1[this.rnd.nextInt(6)];
                                eK2 = eList1[this.rnd.nextInt(6)];
                                eK3 = eList2[this.rnd.nextInt(18)];
                                eK4 = eList2[this.rnd.nextInt(18)];
                            } else {
                                eK1 = eList1[this.rnd.nextInt(6)];
                                eK2 = eList1[this.rnd.nextInt(6)];
                                eK3 = eList1[this.rnd.nextInt(6)];
                                eK4 = eList2[this.rnd.nextInt(18)];
                            }
                            String sql2 = "UPDATE enemy_m SET   quest_id=" + this.questId + ", battle=" + this.userQuestBattle + ", title=\"" + eTitle + "\", name=\"" + eName + "\", kana=\"" + eKana + "\", k1=" + eK1 + ", k2=" + eK2 + ", k3=" + eK3 + ", k4=" + eK4 + ", k5=" + eK5 + ", k6=" + 0 + ", img=" + eImg + " WHERE enemy_id=100000";
                            this.f222db.execSQL(sql2);
                            this.f222db.execSQL("UPDATE user_battle_t SET data='" + sql2 + "' WHERE user_battle_id=2");
                        } else {
                            int minRange = 3;
                            if (30 < this.mapLv) {
                                minRange = Math.round((float) (this.mapLv / 10)) + 1;
                            }
                            Cursor cursor23 = this.f222db.rawQuery("SELECT e.title, e.name, e.kana, e.k1, e.k2, e.k3, e.k4, e.k5, e.k6, e.img FROM quest_m q LEFT OUTER JOIN enemy_m e ON q.quest_id=e.quest_id WHERE " + (this.mapLv - minRange) + "<=q.map_lv AND q.map_lv<=" + (this.mapLv + 3) + " AND e.battle=" + this.userQuestBattle + " AND e.quest_id<100000 ORDER BY RANDOM();", null);
                            cursor2 = cursor23.moveToFirst();
                            if (cursor2) {
                                String sql3 = "UPDATE enemy_m SET   quest_id=" + this.questId + ", battle=" + this.userQuestBattle + ", title=\"" + cursor23.getString(0) + "\", name=\"" + cursor23.getString(1) + "\", kana=\"" + cursor23.getString(2) + "\", k1=" + cursor23.getInt(3) + ", k2=" + cursor23.getInt(4) + ", k3=" + cursor23.getInt(5) + ", k4=" + cursor23.getInt(6) + ", k5=" + cursor23.getInt(7) + ", k6=" + cursor23.getInt(8) + ", img=" + cursor23.getInt(9) + " WHERE enemy_id=100000";
                                this.f222db.execSQL(sql3);
                                this.f222db.execSQL("UPDATE user_battle_t SET data='" + sql3 + "' WHERE user_battle_id=2");
                            }
                            if (cursor23 != null) {
                                cursor23.close();
                            }
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                } finally {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                }
            }
            try {
                cursor = this.f222db.rawQuery("SELECT e.title, e.name, e.img, m1.kodama_attr1, m1.kodama_attr2, m2.kodama_attr1, m2.kodama_attr2, m3.kodama_attr1, m3.kodama_attr2, m4.kodama_attr1, m4.kodama_attr2, m5.kodama_attr1, m5.kodama_attr2, m6.kodama_attr1, m6.kodama_attr2, e.kana FROM enemy_m e LEFT OUTER JOIN kodama_m m1 ON e.k1=m1.kodama_id LEFT OUTER JOIN kodama_m m2 ON e.k2=m2.kodama_id LEFT OUTER JOIN kodama_m m3 ON e.k3=m3.kodama_id LEFT OUTER JOIN kodama_m m4 ON e.k4=m4.kodama_id LEFT OUTER JOIN kodama_m m5 ON e.k5=m5.kodama_id LEFT OUTER JOIN kodama_m m6 ON e.k6=m6.kodama_id WHERE e.quest_Id=" + this.questId + " AND e.battle=" + this.userQuestBattle, null);
                if (cursor.moveToFirst()) {
                    String str2 = cursor.getString(15);
                    if (!str2.equals("empty")) {
                        this.messageStr = cursor.getString(0) + "の" + cursor.getString(1) + "（" + str2 + "）が\n勝負を仕掛けてきた！\n\n";
                    } else {
                        this.messageStr = cursor.getString(0) + "の" + cursor.getString(1) + "が\n勝負を仕掛けてきた！\n\n";
                    }
                    this.enemyIcon = cursor.getInt(2);
                    this.messageStr += "【敵コダマ属性】\n";
                    int i2 = 3;
                    while (i2 < 15 && cursor.getInt(i2) != 30) {
                        if (i2 == 9) {
                            this.messageStr += "、\n";
                        } else if (i2 != 3) {
                            this.messageStr += "、";
                        }
                        this.messageStr += this.attrs[cursor.getInt(i2) - 10] + "／" + this.attrs[cursor.getInt(i2 + 1) - 10];
                        i2 += 2;
                    }
                    this.unitImg2 = getBaseActivity().getResourceUtil().getAnimatedSprite("chara/" + this.enemyIcon + ".png", 3, 4);
                    this.unitImg2.setPosition(-298.0f, 336.0f);
                    this.unitImg2.setCurrentTileIndex(7);
                    attachChild(this.unitImg2);
                    if (this.userQuestBattle == 7) {
                        this.emNo = 6;
                    } else {
                        this.emNo = this.rnd.nextInt(4) + 3;
                    }
                } else {
                    popAlert("データ取得エラー", "enemy_m\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                }
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th2) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th2;
            }
        } else if (3 > this.questId || !(this.userQuestBattle == 2 || this.userQuestBattle == 4 || this.userQuestBattle == 6)) {
            this.userQuestBattle++;
            this.f222db.execSQL("UPDATE user_t SET user_quest_battle=" + this.userQuestBattle);
            this.mapIcon.setPosition((float) (((7 - this.userQuestBattle) * 74) + 16), 8.0f);
            questEncount();
        } else {
            this.unitImg2 = getBaseActivity().getResourceUtil().getAnimatedSprite("quest/ball.png", 1, 1);
            this.unitImg2.setPosition(-186.0f, 336.0f);
            attachChild(this.unitImg2);
            int getNum = 1;
            int rv = this.rnd.nextInt(100);
            if (this.areaId < 100000) {
                if ((21 <= this.areaId && rv < 14) || ((16 <= this.areaId && rv < 11) || ((8 <= this.areaId && rv < 8) || rv < 5))) {
                    getItem = 90001;
                } else if (this.rnd.nextInt(100) < 60) {
                    getItem = 10001;
                    if (26 <= this.areaId) {
                        if (rv < 1) {
                            getItem = 10022;
                        } else if (rv < 3) {
                            getItem = 10021;
                        } else if (rv < 15) {
                            getItem = 10004;
                        } else if (rv < 40) {
                            getItem = 10003;
                        } else if (rv < 100) {
                            getItem = 10002;
                        }
                    } else if (13 <= this.areaId) {
                        if (rv < 2) {
                            getItem = 10021;
                        } else if (rv < 16) {
                            getItem = 10003;
                        } else if (rv < 60) {
                            getItem = 10002;
                        }
                    } else if (11 <= this.areaId) {
                        if (rv < 10) {
                            getItem = 10003;
                        } else if (rv < 60) {
                            getItem = 10002;
                        }
                    } else if (5 <= this.areaId) {
                        if (rv < 5) {
                            getItem = 10003;
                        } else if (rv < 40) {
                            getItem = 10002;
                        }
                    } else if (rv < 2) {
                        getItem = 10003;
                    } else if (rv < 20) {
                        getItem = 10002;
                    }
                } else {
                    if (26 <= this.areaId) {
                        getItem = 20005;
                    } else if (16 <= this.areaId) {
                        getItem = 20004;
                    } else if (11 <= this.areaId) {
                        getItem = 20003;
                    } else if (5 <= this.areaId) {
                        getItem = 20002;
                    } else {
                        getItem = 20001;
                    }
                    if (rv < 10) {
                        getItem += 3;
                    } else if (rv < 25) {
                        getItem += 2;
                    } else if (rv < 50) {
                        getItem++;
                    }
                    if (20008 < getItem) {
                        getItem = 20008;
                    }
                }
            } else if (this.areaId == 100001) {
                getItem = 10002;
                if (this.stageId == 8) {
                    getItem = 10022;
                    getNum = 3;
                } else if (this.stageId == 7) {
                    getItem = 10005;
                } else if (this.stageId == 6) {
                    getItem = 10022;
                } else if (this.stageId == 5) {
                    getItem = 10004;
                    if (this.userQuestBattle == 6) {
                        getItem = 10005;
                    } else if (rv < 10) {
                        getItem = 10005;
                    }
                } else if (this.stageId == 4) {
                    getItem = 10021;
                } else if (this.stageId == 3) {
                    getItem = 10004;
                } else if (this.stageId == 2) {
                    getItem = 10003;
                    if (rv < 50) {
                        getItem = 10004;
                    }
                } else if (rv < 10) {
                    getItem = 10004;
                } else if (rv < 40) {
                    getItem = 10003;
                }
            } else if (this.areaId == 200001) {
                getItem = this.stageId == 3 ? 40001 : 20001;
            } else if (this.areaId == 300001) {
                if (this.stageId == 5) {
                    getItem = 20010;
                } else if (this.stageId == 4) {
                    getItem = 20009;
                    if (this.userQuestBattle == 6 || rv < 20) {
                        getItem = 20010;
                    }
                } else if (this.stageId == 3) {
                    getItem = 20009;
                } else if (this.stageId == 2) {
                    getItem = 20008;
                    if (rv < 25) {
                        getItem = 20009;
                    }
                } else {
                    getItem = 20007;
                    if (rv < 5) {
                        getItem = 20009;
                    } else if (rv < 50) {
                        getItem = 20008;
                    }
                }
            } else if (this.areaId == 400001) {
                getItem = 10003;
                if (this.stageId == 3 || ((this.stageId == 2 && 4 <= this.userQuestBattle) || (this.stageId == 1 && 6 <= this.userQuestBattle))) {
                    int rv2 = 250;
                    int opl6 = 30;
                    int opl5 = 30;
                    int opl4 = 30;
                    int opl3 = 30;
                    int opl2 = 30;
                    try {
                        cursor = this.f222db.rawQuery("SELECT u.item_id, u.num FROM user_item_t u WHERE 1<=u.item_id AND u.item_id<=6 AND 1<=u.num", null);
                        while (cursor.moveToNext()) {
                            if (cursor.getInt(0) == 6) {
                                opl6 = 0;
                                rv2 -= 30;
                            }
                            if (cursor.getInt(0) == 5) {
                                opl5 = 0;
                                rv2 -= 30;
                            }
                            if (cursor.getInt(0) == 4) {
                                opl4 = 0;
                                rv2 -= 30;
                            }
                            if (cursor.getInt(0) == 3) {
                                opl3 = 0;
                                rv2 -= 30;
                            }
                            if (cursor.getInt(0) == 2) {
                                opl2 = 0;
                                rv2 -= 30;
                            }
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (rv2 < 100) {
                            rv2 = 100;
                        }
                        int rv3 = this.rnd.nextInt(rv2);
                        getItem = rv3 < opl6 + 10 ? 6 : rv3 < opl5 + 25 ? 5 : rv3 < opl4 + 45 ? 4 : rv3 < opl3 + 60 ? 3 : rv3 < opl2 + 80 ? 2 : 1;
                    } catch (Throwable th3) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th3;
                    }
                }
            } else {
                int getItem2 = 20003;
                if (rv < 10) {
                    getItem2 = 20003 + 3;
                } else if (rv < 30) {
                    getItem2 = 20003 + 2;
                } else if (rv < 60) {
                    getItem2 = 20003 + 1;
                }
                if (20008 < getItem) {
                    getItem = 20008;
                }
            }
            try {
                cursor = this.f222db.rawQuery("SELECT m.name FROM item_m m WHERE m.item_id=" + getItem, null);
                String sql4 = "empty";
                cursor2 = cursor.moveToFirst();
                if (cursor2) {
                    if (getNum == 1) {
                        this.messageStr = "「" + cursor.getString(0) + "」を見つけました。\n";
                    } else {
                        this.messageStr = "「" + cursor.getString(0) + "」を" + getNum + "個見つけました。\n";
                    }
                    cursor2 = this.f222db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + getItem, null);
                    cursor2 = cursor2.moveToNext();
                    if (cursor2) {
                        int tmp = cursor2.getInt(0) + getNum;
                        if (999999 < tmp) {
                            tmp = 999999;
                        } else {
                            this.messageStr += "（所持数：" + (tmp - getNum) + "→" + tmp + "）\n";
                        }
                        sql4 = "UPDATE user_item_t SET num=" + tmp + " WHERE item_id=" + getItem;
                    } else {
                        this.messageStr += "（所持数：0→" + getNum + "）\n";
                        sql4 = "INSERT INTO user_item_t (item_id, num) VALUES (" + getItem + ", " + getNum + ")";
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                this.f222db.beginTransaction();
                try {
                    if (!sql4.equals("empty")) {
                        this.f222db.execSQL(sql4);
                    }
                    this.f222db.execSQL("UPDATE user_t SET user_quest_status=" + this.userQuestStatus + ", user_quest_battle=" + (this.userQuestBattle + 1));
                    this.f222db.setTransactionSuccessful();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    this.f222db.endTransaction();
                }
                this.emNo = this.rnd.nextInt(3) + 2;
            } catch (Throwable th4) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th4;
            } finally {
                if (cursor2 != null) {
                    cursor2.close();
                }
            }
        }
    }

    private void enemyEncountCheck() {
        if (this.questId == 510000) {
            this.enemyEncountFlg = true;
        } else if (100000 >= this.questId || this.questId >= 500000) {
            Cursor cursor = this.f222db.rawQuery("SELECT e.enemy_id FROM enemy_m e WHERE e.quest_Id=" + this.questId + " AND e.battle=" + this.userQuestBattle, null);
            if (cursor.moveToFirst()) {
                this.enemyEncountFlg = true;
            }
            if (cursor != null) {
                cursor.close();
            }
        } else if (this.userQuestBattle == 1 || this.userQuestBattle == 3 || this.userQuestBattle == 5 || this.userQuestBattle == 7) {
            this.enemyEncountFlg = true;
        }
    }

    /* access modifiers changed from: private */
    public void bgScroll() {
        this.mapMove.play();
        this.waitFlg = true;
        this.unitImg1.animate(this.CHARA_ANIME, this.CHARA_FRAME3);
        if (!this.enemyEncountFlg) {
            this.unitImg2.registerEntityModifier(new MoveModifier(3.0f, -186.0f, 262.0f, 336.0f, 336.0f));
        } else if (this.enemyIcon == 17 || this.enemyIcon == 34 || this.enemyIcon == 38 || this.enemyIcon == 62 || this.enemyIcon == 73) {
            this.unitImg2.registerEntityModifier(new MoveModifier(3.0f, -298.0f, 150.0f, 324.0f, 324.0f));
        } else {
            this.unitImg2.registerEntityModifier(new MoveModifier(3.0f, -298.0f, 150.0f, 336.0f, 336.0f));
        }
        this.bgImg1.registerEntityModifier(new MoveModifier(3.0f, (float) this.bgPositionX, (float) (this.bgPositionX + 448), 80.0f, 80.0f));
        this.bgImg2.registerEntityModifier(new MoveModifier(3.0f, (float) (this.bgPositionX - 896), (float) (this.bgPositionX - 448), 80.0f, 80.0f));
        this.bgPositionX += 448;
        if (896 <= this.bgPositionX) {
            this.bgPositionX = -356;
            this.handlerBgScroll1 = new TimerHandler(3.0f, new ITimerCallback() {
                public void onTimePassed(TimerHandler pTimerHandler) {
                    QuestScene.this.bgImg1.setPosition((float) QuestScene.this.bgPositionX, 80.0f);
                    QuestScene.this.bgImg2.setPosition((float) (QuestScene.this.bgPositionX - 896), 80.0f);
                }
            });
            registerUpdateHandler(this.handlerBgScroll1);
        }
        this.handlerBgScroll2 = new TimerHandler(3.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                QuestScene.this.mapMove.stop();
                if (QuestScene.this.unitImg1.isAnimationRunning()) {
                    QuestScene.this.unitImg1.stopAnimation(4);
                }
                QuestScene.this.emotionOpen();
                QuestScene.this.messageText = new Text(10.0f, 538.0f, (IFont) QuestScene.this.fontBlack, (CharSequence) QuestScene.this.messageStr, new TextOptions(HorizontalAlign.LEFT), QuestScene.this.getBaseActivity().getVertexBufferObjectManager());
                QuestScene.this.attachChild(QuestScene.this.messageText);
                QuestScene.this.buttonActionOpen();
                if (QuestScene.this.enemyEncountFlg) {
                    QuestScene.this.topButtonOpen();
                    if (4 <= QuestScene.this.userDemoNo) {
                        QuestScene.this.cartainBottom.detachSelf();
                    }
                    QuestScene.this.menuMode = 11;
                }
                QuestScene.this.waitFlg = false;
            }
        });
        registerUpdateHandler(this.handlerBgScroll2);
    }

    /* access modifiers changed from: private */
    public void emotionOpen() {
        this.emImg = getBaseActivity().getResourceUtil().getAnimatedSprite("quest/emotion/e" + this.emNo + ".png", 1, 2);
        if (this.enemyEncountFlg) {
            this.emImg.setPosition(150.0f, 270.0f);
        } else {
            this.emImg.setPosition(326.0f, 270.0f);
        }
        this.emImg.animate(this.EMOTION_ANIME);
        attachChild(this.emImg);
    }

    /* access modifiers changed from: private */
    public void buttonActionOpen() {
        if (this.enemyEncountFlg) {
            this.buttonAction = getBaseActivity().getResourceUtil().getButtonSprite("button/button_battle.png", "button/button_battle_p.png");
        } else {
            this.buttonAction = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ok.png", "button/information_ok_p.png");
        }
        this.buttonAction.setPosition(320.0f, 624.0f);
        this.buttonAction.setTag(99999996);
        this.buttonAction.setOnClickListener(this);
        attachChild(this.buttonAction);
        registerTouchArea(this.buttonAction);
    }

    private void battleStart() {
        this.StartPressedSound.play();
        if (this.bgm != null) {
            this.bgm.stop();
        }
        menuQuestClose();
        this.bgInitial.registerEntityModifier(new FadeOutModifier(0.5f));
        this.commonHandler = new TimerHandler(1.0f, new ITimerCallback() {
            public void onTimePassed(TimerHandler pTimerHandler) {
                QuestScene.this.free();
                QuestScene.this.destroy();
                ResourceUtil.getInstance(QuestScene.this.getBaseActivity()).resetAllTexture();
            }
        });
        registerUpdateHandler(this.commonHandler);
    }

    /* JADX INFO: finally extract failed */
    private boolean partyCheck() {
        Cursor cursor = null;
        boolean partyFlg = false;
        try {
            Cursor cursor2 = this.f222db.rawQuery("SELECT u.user_kodama_id, u.kodama_id FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id WHERE p.party_no = " + this.partyNo + " AND u.faint_flg=0", null);
            while (true) {
                if (cursor2.moveToNext()) {
                    if (cursor2.getInt(1) > 0) {
                        partyFlg = true;
                        break;
                    }
                } else {
                    break;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            return partyFlg;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuQuestOpen(boolean first) {
        this.miniMap = getBaseActivity().getResourceUtil().getSprite("quest/map.png");
        this.miniMap.setPosition(0.0f, 0.0f);
        attachChild(this.miniMap);
        this.mapIcon = getBaseActivity().getResourceUtil().getAnimatedSprite("quest/mapIcon.png", 1, 2);
        this.mapIcon.setPosition((float) (((7 - this.userQuestBattle) * 74) + 16), 8.0f);
        this.mapIcon.animate(500);
        attachChild(this.mapIcon);
        this.bgImg1 = getBaseActivity().getResourceUtil().getSprite("bg/stage/" + this.bgImg1No + ".png");
        this.bgImg1.setPosition((float) this.bgPositionX, 80.0f);
        attachChild(this.bgImg1);
        this.bgImg2 = getBaseActivity().getResourceUtil().getSprite("bg/stage/" + this.bgImg1No + ".png");
        this.bgImg2.setPosition((float) (this.bgPositionX - 896), 80.0f);
        attachChild(this.bgImg2);
        this.messageBox = getBaseActivity().getResourceUtil().getSprite("quest/message.png");
        this.messageBox.setPosition(0.0f, 528.0f);
        attachChild(this.messageBox);
        this.unitImg1 = getBaseActivity().getResourceUtil().getAnimatedSprite("chara/" + this.userIcon + ".png", 3, 4);
        if (this.userIcon == 17 || this.userIcon == 34 || this.userIcon == 38 || this.userIcon == 62 || this.userIcon == 73) {
            this.unitImg1.setPosition(326.0f, 324.0f);
        } else if (this.userIcon == 121 || this.userIcon == 123 || this.userIcon == 124 || this.userIcon == 125 || this.userIcon == 127 || this.userIcon == 128 || this.userIcon == 129 || this.userIcon == 134 || this.userIcon == 135) {
            this.unitImg1.setPosition(326.0f, 330.0f);
        } else {
            this.unitImg1.setPosition(326.0f, 336.0f);
        }
        this.unitImg1.setCurrentTileIndex(4);
        attachChild(this.unitImg1);
        this.targetCursor = getBaseActivity().getResourceUtil().getSprite("quest/target.png");
        this.targetCursor.setPosition((float) ((((this.partySortNo - 1) * 80) + 40) - 16), 774.0f);
        attachChild(this.targetCursor);
        this.targetCursorFlg = true;
        bottomPartyOpen();
        arrowOpen(91, 760, false);
        arrowOpen(92, 760, true);
        this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("quest/button/detail.png", "quest/button/detail_p.png");
        this.buttonEtc1.setPosition(70.0f, 880.0f);
        this.buttonEtc1.setTag(99999993);
        this.buttonEtc1.setOnClickListener(this);
        attachChild(this.buttonEtc1);
        registerTouchArea(this.buttonEtc1);
        this.buttonEtc2 = getBaseActivity().getResourceUtil().getButtonSprite("quest/button/change.png", "quest/button/change_p.png");
        this.buttonEtc2.setTag(99999994);
        this.buttonEtc2.setPosition(220.0f, 880.0f);
        this.buttonEtc2.setOnClickListener(this);
        attachChild(this.buttonEtc2);
        registerTouchArea(this.buttonEtc2);
        if (500000 >= this.questId || this.questId >= 700000) {
            this.buttonEtc3 = getBaseActivity().getResourceUtil().getButtonSprite("quest/button/equip.png", "quest/button/equip_p.png");
            this.buttonEtc3.setTag(99999995);
        } else {
            this.buttonEtc3 = getBaseActivity().getResourceUtil().getButtonSprite("quest/button/equip_ng.png", "quest/button/equip_ng.png");
            this.buttonEtc3.setTag(99999990);
        }
        this.buttonEtc3.setPosition(370.0f, 880.0f);
        this.buttonEtc3.setOnClickListener(this);
        attachChild(this.buttonEtc3);
        registerTouchArea(this.buttonEtc3);
        if (!first) {
            this.menuMode = 11;
            topButtonOpen();
            emotionOpen();
            if (this.enemyEncountFlg) {
                this.unitImg2 = getBaseActivity().getResourceUtil().getAnimatedSprite("chara/" + this.enemyIcon + ".png", 3, 4);
                if (this.enemyIcon == 17 || this.enemyIcon == 34 || this.enemyIcon == 38 || this.enemyIcon == 62 || this.enemyIcon == 73) {
                    this.unitImg2.setPosition(150.0f, 324.0f);
                } else if (this.enemyIcon == 121 || this.enemyIcon == 123 || this.enemyIcon == 124 || this.enemyIcon == 125 || this.enemyIcon == 127 || this.enemyIcon == 128 || this.enemyIcon == 129 || this.enemyIcon == 134 || this.enemyIcon == 135) {
                    this.unitImg2.setPosition(150.0f, 330.0f);
                } else {
                    this.unitImg2.setPosition(150.0f, 336.0f);
                }
                this.unitImg2.setCurrentTileIndex(7);
                attachChild(this.unitImg2);
            } else {
                this.unitImg2 = getBaseActivity().getResourceUtil().getAnimatedSprite("quest/ball.png", 1, 1);
                this.unitImg2.setPosition(262.0f, 336.0f);
                attachChild(this.unitImg2);
            }
            this.messageText = new Text(10.0f, 538.0f, (IFont) this.fontBlack, (CharSequence) this.messageStr, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.messageText);
            buttonActionOpen();
        }
    }

    private void menuQuestClose() {
        this.miniMap.detachSelf();
        this.mapIcon.detachSelf();
        this.bgImg1.detachSelf();
        this.bgImg2.detachSelf();
        this.messageBox.detachSelf();
        this.unitImg1.detachSelf();
        this.unitImg2.detachSelf();
        this.emImg.detachSelf();
        this.messageText.detachSelf();
        this.targetCursorFlg = false;
        this.targetCursor.detachSelf();
        bottomPartyClose();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        this.buttonEtc1.detachSelf();
        unregisterTouchArea(this.buttonEtc1);
        this.buttonEtc2.detachSelf();
        unregisterTouchArea(this.buttonEtc2);
        this.buttonEtc3.detachSelf();
        unregisterTouchArea(this.buttonEtc3);
        this.buttonAction.detachSelf();
        unregisterTouchArea(this.buttonAction);
    }

    /* access modifiers changed from: private */
    public void topButtonOpen() {
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("quest/button/menu.png", "quest/button/menu_p.png");
        this.buttonOk.setPosition(330.0f, 90.0f);
        this.buttonOk.setTag(99999997);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("quest/button/attr.png", "quest/button/attr_p.png");
        this.buttonNg.setPosition(330.0f, 150.0f);
        this.buttonNg.setTag(99999991);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
    }

    private void attrTableOpen() {
        this.miniMap = getBaseActivity().getResourceUtil().getSprite("attr/table.png");
        this.miniMap.setPosition(0.0f, 0.0f);
        attachChild(this.miniMap);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        this.buttonOk.setPosition(170.0f, 600.0f);
        this.buttonOk.setTag(99999992);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        if (this.userDemoNo < 4) {
            this.cartainBottom.detachSelf();
        }
    }

    private void attrTableClose() {
        this.miniMap.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
    }

    /* JADX INFO: finally extract failed */
    private void bottomPartyOpen() {
        Cursor cursor = null;
        int posX = 40;
        this.headlineBox = getBaseActivity().getResourceUtil().getSprite("item/headlineBoxS.png");
        this.headlineBox.setPosition(0.0f, 718.0f);
        this.headlineBoxText = new Text(16.0f, 724.0f, (IFont) this.fontBlack, (CharSequence) "パーティー（" + this.partyNo + "/" + 9 + "）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.headlineBox);
        attachChild(this.headlineBoxText);
        try {
            cursor = this.f222db.rawQuery("SELECT u.user_kodama_id, u.kodama_id, u.lv, u.faint_flg, m.kodama_attr1, m.kodama_attr2 FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE p.party_no = " + this.partyNo + " ORDER BY p.sort_no ASC", null);
            int no = 0;
            while (cursor.moveToNext()) {
                int kodamaId = cursor.getInt(1);
                int kodamaLv = cursor.getInt(2);
                int faintFlg = cursor.getInt(3);
                int kodamaAttr1 = cursor.getInt(4);
                int kodamaAttr2 = cursor.getInt(5);
                this.shadowList.add(no, getBaseActivity().getResourceUtil().getSprite("item/shadow.png"));
                ((Sprite) this.shadowList.get(no)).setPosition((float) (posX - 4), (float) 836);
                attachChild((IEntity) this.shadowList.get(no));
                String fileName = "kodama_s/" + kodamaId + ".png";
                this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite(fileName, fileName));
                ((ButtonSprite) this.buttonList.get(no)).setPosition((float) posX, (float) 790);
                ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + no + 1);
                ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                if (faintFlg == 1) {
                    ((ButtonSprite) this.buttonList.get(no)).setAlpha(0.5f);
                } else {
                    ((ButtonSprite) this.buttonList.get(no)).setAlpha(1.0f);
                }
                attachChild((IEntity) this.buttonList.get(no));
                registerTouchArea((ITouchArea) this.buttonList.get(no));
                this.attrList1.add(no, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr1 + ".png"));
                this.attrList2.add(no, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr2 + ".png"));
                ((Sprite) this.attrList1.get(no)).setPosition((float) posX, (float) 790);
                ((Sprite) this.attrList2.get(no)).setPosition((float) posX, (float) 814);
                attachChild((IEntity) this.attrList1.get(no));
                attachChild((IEntity) this.attrList2.get(no));
                if (faintFlg == 1) {
                    this.statusList.add(no, getBaseActivity().getResourceUtil().getSprite("item/faint.png"));
                } else {
                    this.statusList.add(no, getBaseActivity().getResourceUtil().getSprite("item/clear.png"));
                }
                ((Sprite) this.statusList.get(no)).setPosition((float) (posX + 18), (float) 790);
                attachChild((IEntity) this.statusList.get(no));
                String str = String.valueOf(kodamaLv);
                if (kodamaId == 0) {
                    str = "";
                }
                this.statusText[no] = new Text((float) posX, 0.0f, (IFont) this.bitmapFontS, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                this.statusText[no].setPosition(this.statusText[no].getX() + ((64.0f - this.statusText[no].getWidth()) / 2.0f), (float) 834);
                attachChild(this.statusText[no]);
                posX += 80;
                no++;
            }
            this.buttonNum = no;
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void bottomPartyClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((Sprite) this.shadowList.get(i)).detachSelf();
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            ((Sprite) this.attrList1.get(i)).detachSelf();
            ((Sprite) this.attrList2.get(i)).detachSelf();
            ((Sprite) this.statusList.get(i)).detachSelf();
            this.statusText[i].detachSelf();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitDetailOpen() {
        int i;
        this.menuMode = 31;
        boolean engageFlg = false;
        int kodamaId = 0;
        String nickName = "";
        int lv = 0;
        int exp = 0;
        int equip = 0;
        int bp = 0;
        String kodamaName = "";
        int kodamaAttr1 = 10;
        int kodamaAttr2 = 10;
        int hp = 0;
        int vp = 0;
        int atk = 0;
        int def = 0;
        int spd = 0;
        int skill = 0;
        int slv = 0;
        int i2 = 0;
        while (i < 4) {
            this.f223sb[i] = 0;
            i2 = i + 1;
        }
        Cursor cursor = null;
        try {
            cursor = this.f222db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.name, u.lv, u.exp, u.equip, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, u.skill, u.slv, m.kodama_name, m.kodama_hp, m.kodama_atk, m.kodama_def, m.kodama_spd, m.kodama_attr1, m.kodama_attr2, i.hp, i.atk, i.def, i.spd FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id LEFT OUTER JOIN item_m i ON u.equip=i.item_id WHERE p.party_no=" + this.partyNo + " AND p.sort_no=" + this.partySortNo, null);
            if (cursor.moveToNext()) {
                this.targetUserKodamaId = cursor.getInt(0);
                kodamaId = cursor.getInt(2);
                nickName = cursor.getString(3);
                lv = cursor.getInt(4);
                exp = cursor.getInt(5);
                equip = cursor.getInt(6);
                i = 0;
                while (i < 4) {
                    this.f223sb[i] = cursor.getInt(i + 7);
                    i++;
                }
                bp = cursor.getInt(11);
                skill = cursor.getInt(12);
                slv = cursor.getInt(13);
                kodamaName = cursor.getString(14);
                int kodamaHp = cursor.getInt(15);
                int kodamaAtk = cursor.getInt(16);
                int kodamaDef = cursor.getInt(17);
                int kodamaSpd = cursor.getInt(18);
                kodamaAttr1 = cursor.getInt(19);
                kodamaAttr2 = cursor.getInt(20);
                int itemHp = cursor.getInt(21);
                int itemVp = 0;
                if (2000 < itemHp) {
                    itemVp = itemHp - 2000;
                    itemHp -= 2000;
                } else if (1000 < itemHp) {
                    itemVp = itemHp + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
                    itemHp = 0;
                }
                int itemAtk = cursor.getInt(22);
                hp = ((int) Math.floor((double) (((((kodamaHp + 10) * 2) * lv) / 100) + lv + 10))) + itemHp + this.f223sb[0];
                vp = (100 <= lv ? 100 : ((int) Math.floor((double) (lv / 2))) + 50) + itemVp;
                atk = ((int) Math.floor((double) (((((kodamaAtk + 10) * 2) * lv) / 100) + 5))) + itemAtk + this.f223sb[1];
                def = ((int) Math.floor((double) (((((kodamaDef + 10) * 2) * lv) / 100) + 5))) + cursor.getInt(23) + this.f223sb[2];
                spd = ((int) Math.floor((double) (((((kodamaSpd + 10) * 2) * lv) / 100) + 5))) + cursor.getInt(24) + this.f223sb[3];
                if (hp <= 0) {
                    hp = 1;
                }
                if (atk <= 0) {
                    atk = 1;
                }
                if (def <= 0) {
                    def = 1;
                }
                if (spd <= 0) {
                    spd = 1;
                }
            } else {
                popAlert("データ取得エラー", "menuUnitDetailOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            attachChild(this.informationBox);
            this.unitDetailIcon = getBaseActivity().getResourceUtil().getSprite("kodama/" + kodamaId + ".png");
            this.unitDetailIcon.setPosition(46.0f, 100.0f);
            attachChild(this.unitDetailIcon);
            this.attrList1.add(0, getBaseActivity().getResourceUtil().getSprite("attr/" + kodamaAttr1 + ".png"));
            ((Sprite) this.attrList1.get(0)).setPosition(46.0f, 60.0f);
            attachChild((IEntity) this.attrList1.get(0));
            this.attrList2.add(0, getBaseActivity().getResourceUtil().getSprite("attr/" + kodamaAttr2 + ".png"));
            ((Sprite) this.attrList2.get(0)).setPosition(94.0f, 60.0f);
            attachChild((IEntity) this.attrList2.get(0));
            String str = (nickName + "（" + kodamaName + "）\n") + "Lv." + lv + " Exp." + exp + "\n";
            int nextExp = (lv + 1) * (lv + 1) * (lv + 1) * 8;
            if (lv == 100) {
                nextExp = lv * lv * lv * 8;
            }
            this.infoText = new Text(200.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) ((((((str + "次のレベルまで：" + (nextExp - exp) + "\n") + "ＨＰ：" + hp + "\n") + "ＶＰ：" + vp + "\n") + "攻撃：" + atk + "\n") + "防御：" + def + "\n") + "速度：" + spd + "\n") + "ＢＰ：" + bp + "\n", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            String str2 = "";
            if (equip == 0) {
                str2 = str2 + "■装備\nなし\n\n";
            } else {
                try {
                    cursor = this.f222db.rawQuery("SELECT m.name, m.text, m.item_id FROM item_m m WHERE m.item_id=" + equip, null);
                    if (cursor.moveToFirst()) {
                        str2 = str2 + "■装備\n「" + cursor.getString(0) + "」\n" + cursor.getString(1) + "\n\n";
                        if (200100 < cursor.getInt(2) && cursor.getInt(2) < 200200) {
                            engageFlg = true;
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (skill != 0) {
                try {
                    Cursor cursor2 = this.f222db.rawQuery("SELECT m.name, m.text FROM skill_m m WHERE m.skill_id=" + skill, null);
                    while (cursor2.moveToNext()) {
                        str2 = str2 + "■スキル\n「" + cursor2.getString(0) + "」（Lv." + slv + "）\n" + cursor2.getString(1) + "\n\n";
                        i++;
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                } catch (Throwable th2) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th2;
                }
            }
            this.detailText = new Text(40.0f, 350.0f, (IFont) this.fontWhite, (CharSequence) brInsert(str2, 22), new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.detailText);
            this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_spell.png", "button/button_spell_p.png");
            this.buttonEtc1.setPosition(60.0f, 720.0f);
            this.buttonEtc1.setTag(10000001);
            this.buttonEtc1.setOnClickListener(this);
            attachChild(this.buttonEtc1);
            registerTouchArea(this.buttonEtc1);
            if (engageFlg || (500000 < this.questId && this.questId < 700000)) {
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_equip_ng.png", "button/button_equip_ng.png");
                this.buttonNg.setTag(99999990);
            } else {
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_equip.png", "button/button_equip_p.png");
                this.buttonNg.setTag(10000003);
            }
            this.buttonNg.setPosition(290.0f, 720.0f);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonOk.setPosition(170.0f, 800.0f);
            this.buttonOk.setTag(10000000);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.unitDetailDisplay = true;
        } catch (Throwable th3) {
            if (cursor != null) {
                cursor.close();
            }
            throw th3;
        }
    }

    private void menuUnitDetailClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.detailText.detachSelf();
        this.unitDetailIcon.detachSelf();
        ((Sprite) this.attrList1.get(0)).detachSelf();
        ((Sprite) this.attrList2.get(0)).detachSelf();
        this.buttonEtc1.detachSelf();
        unregisterTouchArea(this.buttonEtc1);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
    }

    /* JADX INFO: finally extract failed */
    private void menuSpellOpen() {
        String str;
        this.menuMode = 31;
        attachChild(this.informationBox);
        int[] spell = new int[4];
        String str2 = "";
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f222db.rawQuery("SELECT u.spell1, u.spell2, u.spell3, u.spell4 FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor2.moveToNext()) {
                for (int i = 0; i < 4; i++) {
                    spell[i] = cursor2.getInt(i);
                }
            } else {
                popAlert("データ取得エラー", "menuSpellOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            int y = 50;
            int i2 = 0;
            while (i2 < 4) {
                if (spell[i2] == 0) {
                    str2 = "■未習得";
                } else {
                    try {
                        cursor2 = this.f222db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, t.text FROM spell_m m LEFT OUTER JOIN spell_text_m t ON m.code=t.code WHERE m.spell_id=" + spell[i2], null);
                        if (cursor2.moveToFirst()) {
                            String str3 = brInsert(cursor2.getString(0), 15) + "\n" + this.attrs[cursor2.getInt(1) - 10] + "属性／";
                            if (cursor2.getInt(2) == 0 || cursor2.getInt(2) == 999) {
                                str = str3 + "威力―";
                            } else {
                                str = str3 + "威力" + cursor2.getInt(2);
                            }
                            str2 = (str + "／消費" + cursor2.getInt(3) + "\n") + cursor2.getString(4);
                        } else {
                            popAlert("データ取得エラー", "menuSpellOpen/spell_m\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                    } catch (Throwable th) {
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
                this.buttonList.add(i2, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base_spell.png", "button/button_base_spell.png"));
                ((ButtonSprite) this.buttonList.get(i2)).setPosition(40.0f, (float) y);
                ((ButtonSprite) this.buttonList.get(i2)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(i2));
                registerTouchArea((ITouchArea) this.buttonList.get(i2));
                ((ButtonSprite) this.buttonList.get(i2)).setTag(10080);
                this.buttonListText[i2] = new Text(54.0f, (float) (y + 10), (IFont) this.fontBlack, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.buttonListText[i2]);
                y += 180;
                i2++;
            }
            this.buttonNum = i2;
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonNg.setPosition(290.0f, 800.0f);
            this.buttonNg.setTag(10000002);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
        } catch (Throwable th2) {
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }

    private void menuSpellClose() {
        this.informationBox.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuEquipSelectOpen() {
        this.menuMode = 32;
        Cursor cursor = null;
        this.listPageMax = 0;
        try {
            Cursor cursor2 = this.f222db.rawQuery("SELECT count(u.item_id) FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE 0 < u.num AND 100 < m.type AND m.type < 300", null);
            if (cursor2.moveToFirst()) {
                this.listPageMax = (int) Math.ceil((double) (((float) cursor2.getInt(0)) / ((float) 10)));
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            headlineBoxOpen("装備選択（ページ" + (this.listPage + 1) + "／" + this.listPageMax + "）", true);
            int no = 0;
            int height = 60;
            try {
                Cursor cursor3 = this.f222db.rawQuery("SELECT u.item_id, u.num, m.name, m.text FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE 0 < u.num AND 100 < m.type AND m.type < 300 ORDER BY m.type DESC, m.kana ASC LIMIT " + (this.listPage * 10) + ", " + 10, null);
                while (cursor3.moveToNext()) {
                    this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                    ((ButtonSprite) this.buttonList.get(no)).setPosition(-500.0f, (float) height);
                    ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + cursor3.getInt(0));
                    ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(no));
                    registerTouchArea((ITouchArea) this.buttonList.get(no));
                    String str = cursor3.getString(3).replaceAll("します。", "").replaceAll("が", "");
                    if (cursor3.getInt(0) == 200001) {
                        str = "（経験値を吸収）";
                    }
                    String str2 = cursor3.getString(2) + "（" + cursor3.getInt(1) + "個）[" + str + "]";
                    if (21 < str2.length()) {
                        str2 = str2.substring(0, 21) + "...";
                    }
                    this.buttonListText[no] = new Text(-486.0f, (float) (height + 10), (IFont) this.fontBlack, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    attachChild(this.buttonListText[no]);
                    ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.6f, -500.0f, 40.0f, (float) height, (float) height));
                    this.buttonListText[no].registerEntityModifier(new MoveModifier(0.6f, -486.0f, 54.0f, (float) (height + 10), (float) (height + 10)));
                    height += 64;
                    no++;
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                this.buttonNum = no;
                arrowOpen(91, 786, false);
                arrowOpen(92, 786, true);
                this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                this.buttonEtc1.setTag(10000000);
                this.buttonEtc1.setPosition(275.0f, 778.0f);
                this.buttonEtc1.setOnClickListener(this);
                attachChild(this.buttonEtc1);
                registerTouchArea(this.buttonEtc1);
                this.buttonEtc2 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_off.png", "button/button_off_p.png");
                this.buttonEtc2.setTag(20000000);
                this.buttonEtc2.setPosition(65.0f, 778.0f);
                this.buttonEtc2.setOnClickListener(this);
                attachChild(this.buttonEtc2);
                registerTouchArea(this.buttonEtc2);
            } catch (Throwable th) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }

    private void menuEquipSelectClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        this.buttonEtc1.detachSelf();
        unregisterTouchArea(this.buttonEtc1);
        this.buttonEtc2.detachSelf();
        unregisterTouchArea(this.buttonEtc2);
    }

    private void menuEquipDetailOpen(int id) {
        boolean cursor2;
        this.menuMode = 33;
        String str = "";
        Cursor cursor22 = null;
        try {
            cursor22 = this.f222db.rawQuery("SELECT u.item_id, u.num, m.name, m.text FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id=" + id, null);
            cursor2 = cursor22.moveToNext();
            if (cursor2) {
                str = "■" + cursor22.getString(2) + "（" + cursor22.getInt(1) + "個）\n" + brInsert(cursor22.getString(3), 18);
                if (100001 <= id && id <= 200000) {
                    cursor2 = null;
                    Cursor cursor23 = this.f222db.rawQuery("SELECT c.kodama_id, m.kodama_name, m.kodama_attr1, m.kodama_attr2 FROM card_m c LEFT OUTER JOIN kodama_m m ON c.kodama_id=m.kodama_id WHERE c.item_id=" + id, null);
                    cursor2 = cursor23.moveToNext();
                    if (cursor2) {
                        str = str + "\n\nアイテムとして使用すると、\n「" + cursor23.getString(1) + "」（" + this.attrs[cursor23.getInt(2) - 10] + "／" + this.attrs[cursor23.getInt(3) - 10] + "）\nのコダマと契約できます。";
                    }
                    if (cursor23 != null) {
                        cursor23.close();
                    }
                }
            }
            if (cursor22 != null) {
                cursor22.close();
            }
            attachChild(this.informationBox);
            this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_equip_exe.png", "button/button_equip_exe_p.png");
            placeToCenterX(this.buttonOk, 650.0f);
            this.buttonOk.setTag(10000000 + id);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            placeToCenterX(this.buttonNg, 750.0f);
            this.buttonNg.setTag(10000000);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
            menuEquipSelectClose();
        } catch (Throwable th) {
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        } finally {
            if (cursor2 != null) {
                cursor2.close();
            }
        }
    }

    private void menuEquipDetailClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
    }

    /* JADX INFO: finally extract failed */
    private void menuEquipExe(int itemId) {
        Cursor cursor = null;
        int orgItem = 0;
        try {
            cursor = this.f222db.rawQuery("SELECT u.user_kodama_id, u.equip FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id WHERE p.party_no=" + this.partyNo + " AND p.sort_no=" + this.partySortNo, null);
            if (cursor.moveToNext()) {
                this.targetUserKodamaId = cursor.getInt(0);
                orgItem = cursor.getInt(1);
            } else {
                popAlert("データ取得エラー", "menuEquipExe\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            int orgNum = 0;
            if (orgItem != 0) {
                try {
                    cursor = this.f222db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + orgItem, null);
                    if (cursor.moveToFirst()) {
                        orgNum = cursor.getInt(0);
                    } else {
                        orgItem = 0;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            int itemNum = 0;
            try {
                Cursor cursor2 = this.f222db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + itemId, null);
                if (cursor2.moveToFirst()) {
                    itemNum = cursor2.getInt(0);
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (itemId != orgItem && (itemId == 0 || itemNum > 0)) {
                    this.f222db.beginTransaction();
                    if (orgItem != 0) {
                        int orgNum2 = orgNum + 1;
                        if (999999 < orgNum2) {
                            orgNum2 = 999999;
                        }
                        try {
                            this.f222db.execSQL("UPDATE user_item_t SET num=" + orgNum2 + " WHERE item_id=" + orgItem + ";");
                        } catch (SQLException e) {
                            e.printStackTrace();
                            this.f222db.endTransaction();
                        } catch (Throwable th2) {
                            this.f222db.endTransaction();
                            throw th2;
                        }
                    }
                    if (itemId != 0) {
                        int itemNum2 = itemNum - 1;
                        if (itemNum2 < 0) {
                            itemNum2 = 0;
                        }
                        this.f222db.execSQL("UPDATE user_item_t SET num=" + itemNum2 + " WHERE item_id=" + itemId + ";");
                    }
                    this.f222db.execSQL("UPDATE user_kodama_t SET equip=" + itemId + " WHERE user_kodama_id=" + this.targetUserKodamaId + ";");
                    this.f222db.setTransactionSuccessful();
                    this.f222db.endTransaction();
                }
                if (itemId == 0) {
                    menuEquipSelectClose();
                } else {
                    menuEquipDetailClose();
                }
                menuUnitDetailOpen();
            } catch (Throwable th3) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th3;
            }
        } catch (Throwable th4) {
            if (cursor != null) {
                cursor.close();
            }
            throw th4;
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuPartyChangeOpen() {
        this.menuMode = 21;
        Cursor cursor = null;
        String str = "u.sort_no ASC";
        int posX = 30;
        int posY = 56;
        int no = 0;
        if (this.boxSelect < 1) {
            this.boxSelect = 1;
            this.editor.putInt("boxSelect", this.boxSelect);
            this.editor.commit();
        }
        headlineBoxOpen("パーティー入れ替え（ボックス" + this.boxSelect + "／" + 20 + "）", false);
        String sort = sortGetString(2);
        int[] partyId = new int[6];
        try {
            Cursor cursor2 = this.f222db.rawQuery("SELECT u.user_kodama_id FROM user_party_t u WHERE u.party_no=" + this.partyNo, null);
            while (cursor2.moveToNext()) {
                partyId[no] = cursor2.getInt(0);
                no++;
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            int no2 = 0;
            try {
                cursor2 = this.f222db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.name, u.lv, u.exp, u.equip, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, u.faint_flg, m.kodama_attr1, m.kodama_attr2 FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE " + (this.boxSelect * 100) + "<u.sort_no AND u.sort_no<" + ((this.boxSelect + 1) * 100) + " ORDER BY " + sort, null);
                while (cursor2.moveToNext()) {
                    int userKodamaId = cursor2.getInt(0);
                    int kodamaId = cursor2.getInt(2);
                    String fileName = "kodama_s/" + kodamaId + ".png";
                    int kodamaLv = cursor2.getInt(4);
                    int faintFlg = cursor2.getInt(12);
                    int kodamaAttr1 = cursor2.getInt(13);
                    int kodamaAttr2 = cursor2.getInt(14);
                    this.shadowList.add(no2, getBaseActivity().getResourceUtil().getSprite("item/shadow.png"));
                    ((Sprite) this.shadowList.get(no2)).setPosition((float) (posX - 4), (float) (posY + 46));
                    attachChild((IEntity) this.shadowList.get(no2));
                    this.buttonList.add(no2, getBaseActivity().getResourceUtil().getButtonSprite(fileName, fileName));
                    ((ButtonSprite) this.buttonList.get(no2)).setPosition((float) posX, (float) posY);
                    if (faintFlg == 1) {
                        ((ButtonSprite) this.buttonList.get(no2)).setTag(99999990);
                        ((ButtonSprite) this.buttonList.get(no2)).setAlpha(0.5f);
                        this.statusList.add(no2, getBaseActivity().getResourceUtil().getSprite("item/faint.png"));
                    } else {
                        if (userKodamaId != 0) {
                            if (userKodamaId == partyId[0] || userKodamaId == partyId[1] || userKodamaId == partyId[2] || userKodamaId == partyId[3] || userKodamaId == partyId[4] || userKodamaId == partyId[5]) {
                                ((ButtonSprite) this.buttonList.get(no2)).setTag(10000000);
                                ((ButtonSprite) this.buttonList.get(no2)).setAlpha(0.5f);
                                if (kodamaId == 0) {
                                    this.statusList.add(no2, getBaseActivity().getResourceUtil().getSprite("item/clear.png"));
                                } else {
                                    this.statusList.add(no2, getBaseActivity().getResourceUtil().getSprite("item/organize.png"));
                                }
                            }
                        }
                        ((ButtonSprite) this.buttonList.get(no2)).setTag(10000000 + userKodamaId);
                        ((ButtonSprite) this.buttonList.get(no2)).setAlpha(1.0f);
                        this.statusList.add(no2, getBaseActivity().getResourceUtil().getSprite("item/clear.png"));
                    }
                    ((ButtonSprite) this.buttonList.get(no2)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(no2));
                    registerTouchArea((ITouchArea) this.buttonList.get(no2));
                    this.attrList1.add(no2, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr1 + ".png"));
                    ((Sprite) this.attrList1.get(no2)).setPosition((float) posX, (float) posY);
                    attachChild((IEntity) this.attrList1.get(no2));
                    this.attrList2.add(no2, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr2 + ".png"));
                    ((Sprite) this.attrList2.get(no2)).setPosition((float) posX, (float) (posY + 24));
                    attachChild((IEntity) this.attrList2.get(no2));
                    ((Sprite) this.statusList.get(no2)).setPosition((float) (posX + 18), (float) posY);
                    attachChild((IEntity) this.statusList.get(no2));
                    String str2 = String.valueOf(kodamaLv);
                    if (kodamaId == 0) {
                        str2 = "";
                    }
                    this.statusText[no2] = new Text((float) posX, 0.0f, (IFont) this.bitmapFontS, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    this.statusText[no2].setPosition(this.statusText[no2].getX() + ((64.0f - this.statusText[no2].getWidth()) / 2.0f), (float) (posY + 44));
                    attachChild(this.statusText[no2]);
                    posX += 80;
                    if (460 <= posX) {
                        posX = 30;
                        posY += 80;
                    }
                    no2++;
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                this.buttonNum = no2;
                arrowOpen(91, 486, false);
                arrowOpen(92, 486, true);
                sortButtonOpen(2);
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                this.buttonNg.setTag(9999999);
                placeToCenterX(this.buttonNg, 478.0f);
                this.buttonNg.setOnClickListener(this);
                attachChild(this.buttonNg);
                registerTouchArea(this.buttonNg);
            } catch (Throwable th) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }

    private void menuPartyChangeClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((Sprite) this.shadowList.get(i)).detachSelf();
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            ((Sprite) this.attrList1.get(i)).detachSelf();
            ((Sprite) this.attrList2.get(i)).detachSelf();
            ((Sprite) this.statusList.get(i)).detachSelf();
            this.statusText[i].detachSelf();
        }
        this.buttonSort.detachSelf();
        unregisterTouchArea(this.buttonSort);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
    }

    private void partyChangeExe(int id) {
        this.f222db.execSQL("UPDATE user_party_t SET user_kodama_id = " + id + " WHERE party_no=" + this.partyNo + " AND sort_no=" + this.partySortNo);
        menuPartyChangeClose();
        menuQuestOpen(false);
    }

    public void popAlert(String title, String text) {
        Sprite alertBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(alertBox, 0.0f);
        attachChild(alertBox);
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) "■" + title + "\n" + text, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
    }

    private void headlineBoxOpen(String str, boolean scroll) {
        this.headlineBox = getBaseActivity().getResourceUtil().getSprite("item/headlineBox.png");
        if (scroll) {
            this.headlineBox.setPosition(-540.0f, 0.0f);
            this.headlineBoxText = new Text(-524.0f, 6.0f, (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        } else {
            this.headlineBox.setPosition(0.0f, 0.0f);
            this.headlineBoxText = new Text(16.0f, 6.0f, (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        }
        attachChild(this.headlineBox);
        attachChild(this.headlineBoxText);
        if (scroll) {
            this.headlineBox.registerEntityModifier(new MoveModifier(0.6f, -540.0f, 0.0f, 0.0f, 0.0f));
            this.headlineBoxText.registerEntityModifier(new MoveModifier(0.6f, -524.0f, 16.0f, 6.0f, 6.0f));
        }
    }

    private void arrowOpen(int tag, int height, boolean r) {
        if (r) {
            this.buttonArrowR = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowR.png", "button/arrowR_p.png");
            this.buttonArrowR.setPosition(492.0f, (float) height);
            this.buttonArrowR.setTag(tag);
            this.buttonArrowR.setOnClickListener(this);
            attachChild(this.buttonArrowR);
            registerTouchArea(this.buttonArrowR);
            return;
        }
        this.buttonArrowL = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowL.png", "button/arrowL_p.png");
        this.buttonArrowL.setPosition(0.0f, (float) height);
        this.buttonArrowL.setTag(tag);
        this.buttonArrowL.setOnClickListener(this);
        attachChild(this.buttonArrowL);
        registerTouchArea(this.buttonArrowL);
    }

    private void sortButtonOpen(int act) {
        int sortNo = 1;
        String button = "get";
        if (act == 1) {
            sortNo = this.sortUnitList;
        } else if (act == 2) {
            sortNo = this.sortChangeList;
        }
        if (sortNo == 1) {
            button = "get";
        } else if (sortNo == 2) {
            button = "attr";
        } else if (sortNo == 3) {
            button = "lv";
        }
        this.buttonSort = getBaseActivity().getResourceUtil().getButtonSprite("button/sort/" + button + ".png", "button/sort/" + button + "_p.png");
        this.buttonSort.setPosition(430.0f, -4.0f);
        this.buttonSort.setTag(90);
        this.buttonSort.setOnClickListener(this);
        attachChild(this.buttonSort);
        registerTouchArea(this.buttonSort);
    }

    private void sortChange() {
        if (this.menuMode == 21) {
            menuPartyChangeClose();
            this.sortChangeList++;
            if (3 < this.sortChangeList) {
                this.sortChangeList = 1;
            }
            menuPartyChangeOpen();
            this.editor.putInt("sortChangeList", this.sortChangeList);
        }
        this.editor.commit();
    }

    private String sortGetString(int act) {
        int sortNo = 1;
        String str = "sort_no ASC";
        if (act == 2) {
            sortNo = this.sortChangeList;
        }
        if (sortNo == 1) {
            return "u.sort_no ASC";
        }
        if (sortNo == 2) {
            return "m.kodama_attr1 ASC, u.lv DESC, u.sort_no ASC";
        }
        if (sortNo == 3) {
            return "u.lv DESC, m.kodama_attr1 ASC, u.sort_no ASC";
        }
        return str;
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

    /* access modifiers changed from: private */
    public void free() {
        setOnSceneTouchListener(null);
        this.questUpdateHandler.reset();
        unregisterUpdateHandler(this.questUpdateHandler);
        this.questUpdateHandler = null;
        this.commonHandler = null;
        this.commonHandler2 = null;
        this.commonHandler3 = null;
        this.handlerBgScroll1 = null;
        this.handlerBgScroll2 = null;
        clearEntityModifiers();
    }

    /* access modifiers changed from: private */
    public void destroy() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    KeyListenScene scene;
                    if (QuestScene.this.bgm != null) {
                        QuestScene.this.bgm.release();
                        QuestScene.this.bgm = null;
                    }
                    QuestScene.this.mapMove.release();
                    QuestScene.this.mapMove = null;
                    QuestScene.this.buttonPressedSound.release();
                    QuestScene.this.buttonPressedSound = null;
                    QuestScene.this.buttonCanceledSound.release();
                    QuestScene.this.buttonCanceledSound = null;
                    QuestScene.this.StartPressedSound.release();
                    QuestScene.this.StartPressedSound = null;
                    QuestScene.this.pageMoveSound.release();
                    QuestScene.this.pageMoveSound = null;
                    QuestScene.this.bitmapFontS.unload();
                    QuestScene.this.bitmapFontS.unloadTextures();
                    QuestScene.this.bitmapFontS = null;
                    QuestScene.this.fontBlack.unload();
                    QuestScene.this.fontBlack = null;
                    QuestScene.this.fontWhite.unload();
                    QuestScene.this.fontWhite = null;
                    QuestScene.this.fontYellow.unload();
                    QuestScene.this.fontYellow = null;
                    QuestScene.this.droidFontTexture1.unload();
                    QuestScene.this.droidFontTexture1 = null;
                    QuestScene.this.droidFontTexture2.unload();
                    QuestScene.this.droidFontTexture2 = null;
                    QuestScene.this.droidFontTexture3.unload();
                    QuestScene.this.droidFontTexture3 = null;
                    QuestScene.this.cartain.dispose();
                    QuestScene.this.cartain = null;
                    QuestScene.this.bgInitial.dispose();
                    QuestScene.this.bgInitial = null;
                    QuestScene.this.buttonArrowL.dispose();
                    QuestScene.this.buttonArrowL = null;
                    QuestScene.this.buttonArrowR.dispose();
                    QuestScene.this.buttonArrowR = null;
                    QuestScene.this.buttonEtc1.dispose();
                    QuestScene.this.buttonEtc1 = null;
                    QuestScene.this.buttonEtc2.dispose();
                    QuestScene.this.buttonEtc2 = null;
                    QuestScene.this.buttonEtc3.dispose();
                    QuestScene.this.buttonEtc3 = null;
                    QuestScene.this.buttonSort = null;
                    QuestScene.this.headlineBox.dispose();
                    QuestScene.this.headlineBox = null;
                    QuestScene.this.headlineBoxText.dispose();
                    QuestScene.this.headlineBoxText = null;
                    QuestScene.this.informationBox.dispose();
                    QuestScene.this.informationBox = null;
                    QuestScene.this.buttonAction.dispose();
                    QuestScene.this.buttonAction = null;
                    QuestScene.this.messageText.dispose();
                    QuestScene.this.messageText = null;
                    QuestScene.this.buttonNg.dispose();
                    QuestScene.this.buttonNg = null;
                    QuestScene.this.buttonOk.dispose();
                    QuestScene.this.buttonOk = null;
                    QuestScene.this.infoText.dispose();
                    QuestScene.this.infoText = null;
                    QuestScene.this.miniMap.dispose();
                    QuestScene.this.miniMap = null;
                    QuestScene.this.mapIcon.dispose();
                    QuestScene.this.mapIcon = null;
                    QuestScene.this.unitImg1.dispose();
                    QuestScene.this.unitImg1 = null;
                    QuestScene.this.unitImg2.dispose();
                    QuestScene.this.unitImg2 = null;
                    QuestScene.this.emImg.dispose();
                    QuestScene.this.emImg = null;
                    QuestScene.this.messageBox.dispose();
                    QuestScene.this.messageBox = null;
                    QuestScene.this.cartainBottom.dispose();
                    QuestScene.this.cartainBottom = null;
                    if (QuestScene.this.bgBattleTextFlg) {
                        QuestScene.this.bgBattleText.dispose();
                        QuestScene.this.battleText.dispose();
                    }
                    QuestScene.this.bgBattleText = null;
                    QuestScene.this.battleText = null;
                    if (QuestScene.this.unitDetailDisplay) {
                        QuestScene.this.unitDetailIcon.dispose();
                    }
                    QuestScene.this.unitDetailIcon = null;
                    QuestScene.this.detailText = null;
                    if (QuestScene.this.endDisplayFlg) {
                        QuestScene.this.endBox.dispose();
                        QuestScene.this.endText.dispose();
                    }
                    QuestScene.this.endBox = null;
                    QuestScene.this.endText = null;
                    QuestScene.this.endNg.dispose();
                    QuestScene.this.endOk.dispose();
                    QuestScene.this.endNg = null;
                    QuestScene.this.endOk = null;
                    QuestScene.this.f223sb = null;
                    QuestScene.this.buttonListText = null;
                    QuestScene.this.statusText = null;
                    QuestScene.this.attrs = null;
                    QuestScene.this.shadowList.clear();
                    QuestScene.this.shadowList = null;
                    QuestScene.this.attrList1.clear();
                    QuestScene.this.attrList1 = null;
                    QuestScene.this.attrList2.clear();
                    QuestScene.this.attrList2 = null;
                    QuestScene.this.statusList.clear();
                    QuestScene.this.statusList = null;
                    QuestScene.this.buttonList.clear();
                    QuestScene.this.buttonList = null;
                    QuestScene.this.getBaseActivity().getSoundManager().releasePool();
                    QuestScene.this.dbh = null;
                    QuestScene.this.f222db = null;
                    if (QuestScene.this.mypageFlg) {
                        scene = new MenuScene(QuestScene.this.getBaseActivity());
                    } else {
                        scene = new MainScene(QuestScene.this.getBaseActivity());
                    }
                    QuestScene.this.getBaseActivity().getEngine().setScene(scene);
                    QuestScene.this.getBaseActivity().appendScene(scene);
                }
            });
        } catch (Exception e) {
        }
    }

    public void endConfirmOpen() {
        if (!this.endConfirmFlg) {
            this.endConfirmFlg = true;
            this.endDisplayFlg = true;
            this.endBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
            placeToCenterX(this.endBox, 30.0f);
            attachChild(this.endBox);
            this.endText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) "アプリを終了します。\nよろしいですか？", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.endText);
            placeToCenterX(this.endNg, 750.0f);
            this.endNg.setZIndex(99999999);
            placeToCenterX(this.endOk, 650.0f);
            this.endOk.setZIndex(99999998);
            sortChildren();
        }
    }

    private void endConfirmClose() {
        this.endBox.detachSelf();
        this.endText.detachSelf();
        placeToCenterX(this.endNg, 960.0f);
        placeToCenterX(this.endOk, 960.0f);
        this.endConfirmFlg = false;
    }
}
