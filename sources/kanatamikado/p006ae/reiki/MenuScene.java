package kanatamikado.p006ae.reiki;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.support.p000v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.KeyEvent;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import net.nend.android.NendAdRewardItem;
import net.nend.android.NendAdRewardedListener;
import net.nend.android.NendAdRewardedVideo;
import net.nend.android.NendAdVideo;
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

/* renamed from: kanatamikado.ae.reiki.MenuScene */
public class MenuScene extends KeyListenScene implements IOnSceneTouchListener, OnClickListener {
    private static final float FONT_SIZE = 24.0f;
    private final int BOX_LIMIT = 20;
    private final int BP_LIMIT = 9999999;
    private final int BP_MAX = 64;
    private final int BUTTON_LEFT = 91;
    private final int BUTTON_LEFT2 = 93;
    private final int BUTTON_RIGHT = 92;
    private final int BUTTON_RIGHT2 = 94;
    private final int BUTTON_SORT = 90;
    private final int BUTTON_TUTORIAL_1 = 81;
    private final int BUTTON_TUTORIAL_2 = 82;
    private final int BUTTON_TUTORIAL_3 = 83;
    private final int BUTTON_TUTORIAL_4 = 84;
    private final int CHALLENGE_AREA = 1000006;
    private final int CHALLENGE_LIMIT = 107;
    private final int CHALLENGE_SET = 106;
    private final int GAME_END_NG = 99999998;
    private final int GAME_END_OK = 99999999;
    private final int HEADLINE_TEXT_Y = 78;
    private final int HEADLINE_Y = 72;
    private final int HELP_BACK = 123;
    private final int HELP_BACKUP = 121;
    private final int HELP_BUTTON_1 = 101;
    private final int HELP_BUTTON_10 = 110;
    private final int HELP_BUTTON_2 = 102;
    private final int HELP_BUTTON_3 = 103;
    private final int HELP_BUTTON_4 = 104;
    private final int HELP_BUTTON_5 = 105;
    private final int HELP_BUTTON_6 = 106;
    private final int HELP_BUTTON_7 = 107;
    private final int HELP_BUTTON_8 = 108;
    private final int HELP_BUTTON_9 = 109;
    private final int HELP_BUTTON_NUM = 10;
    private final int HELP_RESTORE = 122;
    private final int ITEM_BUTTON_NUM = 6;
    private final int ITEM_LIMIT = 999999;
    private final int MENU_MODE_ITEM = 21;
    private final int MENU_MODE_ITEM_CARD = 24;
    private final int MENU_MODE_ITEM_LIST = 22;
    private final int MENU_MODE_ITEM_SHOP = 23;
    private final int MENU_MODE_MAX = 49;
    private final int MENU_MODE_OPTION = 41;
    private final int MENU_MODE_OPTION_HELP = 42;
    private final int MENU_MODE_OPTION_ICON = 43;
    private final int MENU_MODE_PARTY = 31;
    private final int MENU_MODE_PARTY_CHANGE = 32;
    private final int MENU_MODE_QUEST = 2;
    private final int MENU_MODE_QUEST_AREA = 1;
    private final int MENU_MODE_QUEST_PH = 4;
    private final int MENU_MODE_QUEST_TOWER = 3;
    private final int MENU_MODE_SPELL_LEARN = 15;
    private final int MENU_MODE_UNIT = 11;
    private final int MENU_MODE_UNIT_EQUIP = 12;
    private final int MENU_MODE_UNIT_ITEM = 13;
    private final int MENU_MODE_UNIT_MOVE = 14;
    private final long MONEY_LIMIT = 999999999;
    private final int NG_TAG = 99999997;
    private final int OPTION_BUTTON_BGM = 105;
    private final int OPTION_BUTTON_DIFFICULT = 114;
    private final int OPTION_BUTTON_HELP = 101;
    private final int OPTION_BUTTON_ICON = 104;
    private final int OPTION_BUTTON_MOVIE = 115;
    private final int OPTION_BUTTON_NAME = 102;
    private final int OPTION_BUTTON_NG = 113;
    private final int OPTION_BUTTON_REVIEW = 107;
    private final int OPTION_BUTTON_REVIEW_OK = 108;
    private final int OPTION_BUTTON_SND = 106;
    private final int OPTION_BUTTON_SNS = 109;
    private final int OPTION_BUTTON_SNS_NG = 112;
    private final int OPTION_BUTTON_SNS_OK1 = 110;
    private final int OPTION_BUTTON_SNS_OK2 = 111;
    private final int PARTY_LIMIT = 9;
    private final int SORT_MAX_NO = 3;
    private final int SORT_NO_PARTY = 2;
    private final int SORT_NO_UNIT = 1;
    private final float SOUL_HEAL_SECOND = 180.0f;
    private final int SP_LIMIT = 10000;
    /* access modifiers changed from: private */
    public Sound StartPressedSound;
    private final int TITLE_TEXT_Y = 136;
    private final int TITLE_Y = 130;
    private final int TUTORIAL_TAG = 99999996;
    private int areaSelect = 1;
    /* access modifiers changed from: private */
    public boolean attrFlg = false;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> attrList1;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> attrList2;
    /* access modifiers changed from: private */
    public Sprite attrTable;
    /* access modifiers changed from: private */
    public String[] attrs = {"―", "無", "樹", "闘", "毒", "地", "風", "虫", "岩", "鋼", "霊", "水", "雷", "氷", "理", "炎", "神", "闇", "然", "―", "―"};
    /* access modifiers changed from: private */
    public ArrayList<ButtonSprite> backButtonList;
    private String[] bagList1 = {"100001", "100002", "100003", "100004", "100005", "100006", "100007", "100008"};
    private String[] bagList2 = {"110001", "110003", "110004", "110005", "110008", "110009", "110010", "110014", "110015"};
    private String[] bagList3 = {"120001", "120002", "120003", "120004", "120005", "120006", "120007", "120008", "120009", "120010", "120011", "120012", "120013", "120014", "120015", "120016", "120017", "120018", "120019", "120020", "120021", "120022", "120023", "120024", "120025", "120026", "120027", "120028", "120029", "120030", "120031", "120032", "120033", "120034", "120035", "120036", "120037", "120038", "120039", "120040", "120041", "120042", "120043", "120044"};
    private String[] bagList4 = {"130001", "130002", "130003", "130004", "130005", "130006", "130007", "130008", "130009", "130010", "130011", "130012", "130013", "130014", "130015", "130016", "130017", "130018", "130019", "130020", "130021", "130022", "130023", "130024", "130025"};
    private String[] bagList5 = {"140001", "140002", "140003", "140004", "140005", "140006", "140007", "140008", "140009", "140010", "140011", "140012", "140013", "140014", "140015", "140017", "140018", "140019", "140020", "140021", "140022", "140023", "140024", "140025", "140026", "140027", "140028", "140029"};
    private String[] bagList6 = {"150003", "150005", "150006", "150008", "150011", "150012", "150014", "150015"};
    private String[] bagList7 = {"160001", "160002", "160003", "160004", "160005", "160006", "160007", "160008"};
    private String[] bagList8 = {"170001", "170002", "170003", "170004", "170005", "170006", "170007", "170008", "170009", "180001", "180002", "180003", "180004", "180005", "180006", "180007", "180008", "180009", "180010", "180012", "180013", "180014", "180015", "180016", "180017", "180018", "180011"};
    private String[] bagList9 = new String[0];
    /* access modifiers changed from: private */
    public Sprite bgImg;
    /* access modifiers changed from: private */
    public Music bgm;
    private int bgmFlg = 1;
    /* access modifiers changed from: private */
    public BitmapFont bitmapFontS;
    private int boxSelect = 1;
    /* access modifiers changed from: private */
    public ButtonSprite buttonArrowL;
    /* access modifiers changed from: private */
    public ButtonSprite buttonArrowLL;
    /* access modifiers changed from: private */
    public ButtonSprite buttonArrowR;
    /* access modifiers changed from: private */
    public ButtonSprite buttonArrowRR;
    /* access modifiers changed from: private */
    public ArrayList<ButtonSprite> buttonBottom;
    /* access modifiers changed from: private */
    public ButtonSprite buttonBp;
    /* access modifiers changed from: private */
    public Sound buttonCanceledSound;
    /* access modifiers changed from: private */
    public ButtonSprite buttonEtc1;
    private boolean buttonEtc1Flg = false;
    /* access modifiers changed from: private */
    public ButtonSprite buttonEtc2;
    /* access modifiers changed from: private */
    public ButtonSprite buttonEtc3;
    /* access modifiers changed from: private */
    public boolean buttonEtc3Flg = false;
    /* access modifiers changed from: private */
    public ButtonSprite buttonEtc4;
    /* access modifiers changed from: private */
    public boolean buttonEtc4Flg = false;
    /* access modifiers changed from: private */
    public ButtonSprite buttonItem;
    /* access modifiers changed from: private */
    public ArrayList<ButtonSprite> buttonList;
    /* access modifiers changed from: private */
    public Text[] buttonListText;
    /* access modifiers changed from: private */
    public ButtonSprite buttonName;
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
    public boolean buttonSortFlg = false;
    /* access modifiers changed from: private */
    public Rectangle cartain;
    private Rectangle cartainBottom;
    TimerHandler commonHandler;
    TimerHandler commonHandler2;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> confirmUnitList;
    /* access modifiers changed from: private */
    public boolean dArrowFlg = false;
    /* access modifiers changed from: private */

    /* renamed from: db */
    public SQLiteDatabase f220db = this.dbh.getWritableDatabase();
    /* access modifiers changed from: private */
    public Database dbh = new Database(getBaseActivity());
    /* access modifiers changed from: private */
    public Text detailText;
    ITexture droidFontTexture1;
    ITexture droidFontTexture2;
    /* access modifiers changed from: private */
    public Editor editor;
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
    private int exProgressId = 500001;
    /* access modifiers changed from: private */
    public Font fontBlack;
    private int fontCheckNo = 1;
    /* access modifiers changed from: private */
    public Font fontWhite;
    private boolean hallFlg = true;
    /* access modifiers changed from: private */
    public Sprite headlineBox;
    /* access modifiers changed from: private */
    public Text headlineBoxText;
    /* access modifiers changed from: private */
    public boolean healSpFlg = false;
    /* access modifiers changed from: private */
    public int healSpTime = 0;
    /* access modifiers changed from: private */
    public Text healSpTimeText;
    /* access modifiers changed from: private */
    public Text infoText;
    /* access modifiers changed from: private */
    public Sprite informationBox;
    /* access modifiers changed from: private */
    public Sound itemUseSound;
    private int listHead = 0;
    private int listPage = 0;
    private int listPageMax = 0;
    private int listPageRoad = 0;
    private int listPageRoadMax = 0;
    NendAdRewardedVideo mNendAdRewardedVideo;
    private boolean menuAttrOpenFlg = false;
    private boolean menuBoxBpListOpenFlg = false;
    private boolean menuBoxEquipListOpenFlg = false;
    private boolean menuBoxEvoListOpenFlg = false;
    private boolean menuCardChangeDetailOpenFlg = false;
    private boolean menuEquipDetailOpenFlg = false;
    private boolean menuHelpDetailOpenFlg = false;
    private boolean menuItemDetailOpenFlg = false;
    private int menuMode = 2;
    private boolean menuNameResetFlg = false;
    private boolean menuQuestConfirmFlg = false;
    private boolean menuShopDetailOpenFlg = false;
    private boolean menuSpellLearnConfirmFlg = false;
    private boolean menuTutorialOpenFlg = false;
    private boolean menuUnitDetailOpenFlg = false;
    private boolean menuUnitDetailSpellOpenFlg = false;
    private boolean menuUnitItemDetailOpenFlg = false;
    private TimerHandler menuUpdateHandler = new TimerHandler(1.0f, true, new ITimerCallback() {
        public void onTimePassed(TimerHandler pTimerHandler) {
            MenuScene.this.passageTime = MenuScene.this.passageTime + 1;
            if (MenuScene.this.healSpFlg && !MenuScene.this.timeStampCheckFlg) {
                MenuScene.this.passageCount = MenuScene.this.passageCount + 1;
                MenuScene.this.userSpText.detachSelf();
                MenuScene.this.userSpText = new Text(6.0f, 34.0f, (IFont) MenuScene.this.fontBlack, (CharSequence) "通信待機中...あと" + (60 - MenuScene.this.passageCount) + "秒", new TextOptions(HorizontalAlign.LEFT), MenuScene.this.getBaseActivity().getVertexBufferObjectManager());
                MenuScene.this.attachChild(MenuScene.this.userSpText);
                if (60 <= MenuScene.this.passageCount) {
                    MenuScene.this.passageCount = 0;
                    MenuScene.this.timeStampCheck();
                }
            } else if (MenuScene.this.healSpFlg && MenuScene.this.timeStampCheckFlg) {
                MenuScene.this.healSpTime = Math.round(((float) (MenuScene.this.userSpRestoreTime - (MenuScene.this.startTime + ((long) MenuScene.this.passageTime)))) + 180.0f);
                if (MenuScene.this.healSpTime <= 0) {
                    float healPt = (float) (((int) Math.floor((double) ((-1.0f * ((float) MenuScene.this.healSpTime)) / 180.0f))) + 1);
                    MenuScene.this.userSp = (int) (((float) MenuScene.this.userSp) + healPt);
                    if (MenuScene.this.userMaxSp <= MenuScene.this.userSp) {
                        MenuScene.this.healSpFlg = false;
                        MenuScene.this.userSp = MenuScene.this.userMaxSp;
                        MenuScene.this.userSpRestoreTime = 0;
                        MenuScene.this.healSpTime = 0;
                    } else {
                        MenuScene.this.userSpRestoreTime = MenuScene.this.userSpRestoreTime + ((long) Math.round(180.0f * healPt));
                        MenuScene.this.healSpTime = Math.round(((float) (MenuScene.this.userSpRestoreTime - (MenuScene.this.startTime + ((long) MenuScene.this.passageTime)))) + 180.0f);
                    }
                    MenuScene.this.updateSp();
                }
                if (MenuScene.this.healSpFlg) {
                    if (9999 < MenuScene.this.healSpTime) {
                        MenuScene.this.healSpTime = 0;
                        MenuScene.this.userSpRestoreTime = MenuScene.this.startTime;
                    }
                    MenuScene.this.healSpTimeText.setText("あと" + MenuScene.this.healSpTime + "秒");
                    MenuScene.this.healSpTimeText.setPosition((540.0f - MenuScene.this.healSpTimeText.getWidth()) - 6.0f, 34.0f);
                    return;
                }
                MenuScene.this.healSpTimeText.setText("　");
            }
        }
    });
    /* access modifiers changed from: private */
    public Sprite moneyImg;
    /* access modifiers changed from: private */
    public Text moneyText;
    /* access modifiers changed from: private */
    public int nendAdFlg = 0;
    /* access modifiers changed from: private */
    public boolean nendRewardAdOpenFlg = false;
    /* access modifiers changed from: private */
    public boolean newUnitDisplayFlg = false;
    /* access modifiers changed from: private */
    public Sprite newUnitImg;
    private boolean newUnitOpenFlg = false;
    private int optionButtonNum = 9;
    private int oukaLv = 1;
    /* access modifiers changed from: private */
    public Sound pageMoveSound;
    private int partyNo = 1;
    private int partySortNo = 1;
    /* access modifiers changed from: private */
    public int passageCount = 0;
    /* access modifiers changed from: private */
    public int passageTime = 0;
    private int phAreaMax = 0;
    private int phProgressId = 700001;
    private SharedPreferences pre;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> protectList;
    private int rdProgressId = 800001;
    private Random rnd = new Random();
    /* access modifiers changed from: private */

    /* renamed from: sb */
    public int[] f221sb = new int[4];
    private int selectQuestId = 1;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> shadowList;
    private int[] shopList;
    private int shopNum = 0;
    private int sortChangeList = 1;
    private int sortUnitList = 1;
    private int soundFlg = 1;
    /* access modifiers changed from: private */
    public String[] stName = {"ＨＰ", "攻撃", "防御", "速度"};
    /* access modifiers changed from: private */
    public long startTime = 0;
    /* access modifiers changed from: private */
    public Sprite statusBox;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> statusList;
    /* access modifiers changed from: private */
    public Text[] statusText;
    /* access modifiers changed from: private */
    public int targetUserKodamaId = 0;
    /* access modifiers changed from: private */
    public Text testText;
    /* access modifiers changed from: private */
    public boolean textTextFlg = false;
    /* access modifiers changed from: private */
    public boolean timeStampCheckFlg = false;
    /* access modifiers changed from: private */
    public Sprite titleBox;
    /* access modifiers changed from: private */
    public Text titleBoxText;
    /* access modifiers changed from: private */
    public StringBuilder tmpStr = new StringBuilder();
    /* access modifiers changed from: private */
    public int toScene = 0;
    private int twAreaMax = 0;
    private int unitCheckNo = 0;
    /* access modifiers changed from: private */
    public boolean unitDetailDisplay = false;
    /* access modifiers changed from: private */
    public Sprite unitDetailIcon;
    /* access modifiers changed from: private */
    public Sound unitGrowSound;
    private int unitNum = 0;
    /* access modifiers changed from: private */
    public ArrayList<Sprite> unitProtectIcon;
    /* access modifiers changed from: private */
    public int[] unitSort;
    /* access modifiers changed from: private */
    public int[] unitSortId;
    private int userChallengeNum = 0;
    private String userCode = "null";
    /* access modifiers changed from: private */
    public int userDemoNo = 0;
    private int userDifficult = 0;
    private int userIcon = 0;
    private int userLoginNum = 0;
    /* access modifiers changed from: private */
    public int userMaxSp = PathInterpolatorCompat.MAX_NUM_POINTS;
    private long userMoney;
    /* access modifiers changed from: private */
    public String userName = "悠姫";
    /* access modifiers changed from: private */
    public Text userNameText;
    private int userProgressArea = 1;
    /* access modifiers changed from: private */
    public int userProgressQuest = 0;
    private int userQuestId = 0;
    private int userQuestStatus = 0;
    private int userReviewFlg = 0;
    private int userSnsFlg = 0;
    /* access modifiers changed from: private */
    public int userSp = 0;
    /* access modifiers changed from: private */
    public long userSpRestoreTime;
    /* access modifiers changed from: private */
    public Text userSpText;
    /* access modifiers changed from: private */
    public Rectangle userSpVar;

    public MenuScene(MultiSceneActivity baseActivity) {
        super(baseActivity);
        init();
    }

    /* JADX INFO: finally extract failed */
    public void init() {
        Cursor cursor = null;
        this.pre = getBaseActivity().getSharedPreferences(MainActivity.PREFERRENCES_FILE_NAME, 0);
        this.editor = this.pre.edit();
        this.boxSelect = this.pre.getInt("boxSelect", 1);
        this.partyNo = this.pre.getInt("partyNo", 1);
        this.areaSelect = this.pre.getInt("areaSelect", 1);
        this.sortUnitList = this.pre.getInt("sortUnitList", 1);
        this.sortChangeList = this.pre.getInt("sortChangeList", 1);
        this.bgmFlg = this.pre.getInt("bgmFlg", 1);
        this.soundFlg = this.pre.getInt("soundFlg", 1);
        this.fontCheckNo = this.pre.getInt("fontCheckNo", 1);
        this.unitCheckNo = this.pre.getInt("unitCheckNo", 0);
        this.nendAdFlg = this.pre.getInt("nendAdFlg", 0);
        if (this.unitCheckNo != 0) {
            try {
                cursor = this.f220db.rawQuery("SELECT u.kodama_id, m.kodama_name FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE u.user_kodama_id=" + this.unitCheckNo, null);
                if (cursor.moveToFirst() && cursor.getInt(0) != 0) {
                    this.f220db.execSQL("UPDATE user_kodama_t SET name=\"" + cursor.getString(1) + "\" WHERE user_kodama_id=" + this.unitCheckNo);
                    this.unitCheckNo = 0;
                    this.editor.putInt("unitCheckNo", 0);
                    this.editor.commit();
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
        bgmChange(false);
        soundChange(false);
        this.droidFontTexture1 = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontWhite = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture1, getBaseActivity().getResourceUtil().getTypeface(), (float) FONT_SIZE, true, Color.rgb(255, 255, 255));
        this.fontWhite.load();
        this.droidFontTexture2 = new BitmapTextureAtlas(getBaseActivity().getTextureManager(), 1024, 1024, TextureOptions.BILINEAR);
        this.fontBlack = FontFactory.create(getBaseActivity().getFontManager(), this.droidFontTexture2, getBaseActivity().getResourceUtil().getTypeface(), (float) FONT_SIZE, true, Color.rgb(0, 0, 0));
        this.fontBlack.load();
        this.menuMode = 2;
        this.buttonBottom = new ArrayList<>();
        this.backButtonList = new ArrayList<>();
        this.buttonList = new ArrayList<>();
        this.buttonListText = new Text[20];
        this.confirmUnitList = new ArrayList<>();
        this.shadowList = new ArrayList<>();
        this.protectList = new ArrayList<>();
        this.attrList1 = new ArrayList<>();
        this.attrList2 = new ArrayList<>();
        this.statusList = new ArrayList<>();
        this.unitProtectIcon = new ArrayList<>();
        this.statusText = new Text[100];
        this.unitSort = new int[30];
        this.unitSortId = new int[30];
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
        this.shopList = new int[1000];
        for (int i = 0; i < 1000; i++) {
            this.shopList[i] = 0;
        }
        try {
            cursor = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=500001", null);
            if (cursor.moveToFirst()) {
                this.exProgressId = cursor.getInt(0);
            } else {
                this.exProgressId = 500001;
                this.f220db.execSQL("INSERT INTO user_item_t VALUES (500001, 500001);");
            }
            if (cursor != null) {
                cursor.close();
            }
            this.hallFlg = true;
            try {
                cursor = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=500002", null);
                if (cursor.moveToFirst()) {
                    this.hallFlg = true;
                } else {
                    this.hallFlg = false;
                }
                if (cursor != null) {
                    cursor.close();
                }
                try {
                    cursor = this.f220db.rawQuery("SELECT q.area_id, q.stage_id FROM user_progress_t u LEFT OUTER JOIN quest_m q ON u.quest_id=q.quest_id WHERE 700000 < u.quest_id AND u.quest_id < 800000 ORDER BY u.quest_id DESC", null);
                    if (cursor.moveToFirst()) {
                        if (cursor.getInt(1) == 7) {
                            this.phProgressId = cursor.getInt(0) + 1;
                        } else {
                            this.phProgressId = cursor.getInt(0);
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    try {
                        Cursor cursor2 = this.f220db.rawQuery("SELECT u.quest_id FROM user_progress_t u WHERE 800000 < u.quest_id AND u.quest_id < 900000 ORDER BY u.quest_id DESC", null);
                        if (cursor2.moveToFirst()) {
                            this.rdProgressId = cursor2.getInt(0) + 1;
                            this.listPageRoadMax = (int) Math.floor((double) (((this.rdProgressId - 800000) - 1) / 8));
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        Cursor cursor3 = this.f220db.rawQuery("SELECT u.user_id, u.user_code, u.user_password, u.user_name, u.user_money, u.user_sp, u.user_max_sp, u.user_sp_restore_time, u.user_icon, u.user_difficult, u.user_quest_id, u.user_quest_status, u.user_quest_battle, u.last_login, u.user_sns_flg, u.user_review_flg, u.user_demo_no, u.user_login_num FROM user_t u", null);
                        if (cursor3.moveToFirst()) {
                            this.userCode = cursor3.getString(1);
                            if (this.userCode.equals("null")) {
                                this.userCode = "なし";
                            } else if (this.userCode.length() <= 7) {
                                this.f220db.execSQL("UPDATE user_t SET user_code=\"null\", user_password=\"null\"");
                            }
                            if (!cursor3.getString(3).equals("empty")) {
                                this.userName = cursor3.getString(3);
                            }
                            this.userMoney = (long) cursor3.getInt(4);
                            this.userSp = cursor3.getInt(5);
                            this.userSpRestoreTime = cursor3.getLong(7);
                            this.userIcon = cursor3.getInt(8);
                            this.userDifficult = cursor3.getInt(9);
                            this.userQuestId = cursor3.getInt(10);
                            this.userQuestStatus = cursor3.getInt(11);
                            this.userSnsFlg = cursor3.getInt(14);
                            this.userReviewFlg = cursor3.getInt(15);
                            this.userDemoNo = cursor3.getInt(16);
                            this.userLoginNum = cursor3.getInt(17);
                            if (this.userQuestStatus <= 1) {
                                this.userQuestId = 0;
                            }
                            if (this.userMoney < 0) {
                                this.userMoney = 999999999;
                            }
                            if (99 <= this.userDemoNo) {
                                this.userChallengeNum = this.userDemoNo;
                                if (this.userChallengeNum < 106) {
                                    this.userChallengeNum = 106;
                                }
                            }
                            if (this.userQuestStatus <= 1) {
                                this.f220db.execSQL("UPDATE user_kodama_t SET faint_flg=0");
                            }
                            if (2 <= this.userSnsFlg) {
                                this.optionButtonNum = 7;
                            }
                            if (this.userSp < this.userMaxSp) {
                                this.healSpFlg = true;
                            }
                            cursor3 = this.f220db.rawQuery("SELECT u.quest_id FROM user_progress_t u WHERE u.quest_id < 100000 ORDER BY u.quest_id DESC", null);
                            if (cursor3.moveToFirst()) {
                                this.userProgressQuest = cursor3.getInt(0);
                                Cursor cursor4 = this.f220db.rawQuery("SELECT m.area_id FROM quest_m m WHERE m.quest_id <= " + (this.userProgressQuest + 1) + " ORDER BY m.quest_id DESC", null);
                                if (cursor4.moveToFirst()) {
                                    this.userProgressArea = cursor4.getInt(0);
                                }
                                cursor3 = this.f220db.rawQuery("SELECT q.map_lv FROM quest_m q WHERE q.quest_id<=" + this.userProgressQuest + " ORDER BY q.quest_id DESC", null);
                                if (cursor3.moveToFirst()) {
                                    this.oukaLv = cursor3.getInt(0);
                                }
                            }
                            int p = this.userProgressQuest;
                            int i2 = 0;
                            if (63 <= p) {
                                this.shopList[0] = 10011;
                                i2 = 0 + 1;
                            }
                            if (147 <= p) {
                                this.shopList[i2] = 1;
                                int i3 = i2 + 1;
                                this.shopList[i3] = 2;
                                int i4 = i3 + 1;
                                this.shopList[i4] = 3;
                                int i5 = i4 + 1;
                                this.shopList[i5] = 4;
                                int i6 = i5 + 1;
                                this.shopList[i6] = 5;
                                int i7 = i6 + 1;
                                this.shopList[i7] = 6;
                                i2 = i7 + 1;
                            }
                            if (203 <= p) {
                                this.shopList[i2] = 7;
                                i2++;
                            }
                            if (147 <= p) {
                                this.shopList[i2] = 8;
                                i2++;
                            }
                            if (700002 <= this.phProgressId) {
                                this.shopList[i2] = 9;
                                i2++;
                            }
                            if (500020 < this.exProgressId) {
                                this.shopList[i2] = 200019;
                                int i8 = i2 + 1;
                                this.shopList[i8] = 200028;
                                int i9 = i8 + 1;
                                this.shopList[i9] = 200030;
                                int i10 = i9 + 1;
                                this.shopList[i10] = 200031;
                                int i11 = i10 + 1;
                                this.shopList[i11] = 200032;
                                int i12 = i11 + 1;
                                this.shopList[i12] = 200033;
                                i2 = i12 + 1;
                            }
                            if (252 <= p) {
                                this.shopList[i2] = 200026;
                                int i13 = i2 + 1;
                                this.shopList[i13] = 200027;
                                int i14 = i13 + 1;
                                this.shopList[i14] = 200029;
                                int i15 = i14 + 1;
                                this.shopList[i15] = 200020;
                                int i16 = i15 + 1;
                                this.shopList[i16] = 200021;
                                int i17 = i16 + 1;
                                this.shopList[i17] = 200022;
                                int i18 = i17 + 1;
                                this.shopList[i18] = 200023;
                                int i19 = i18 + 1;
                                this.shopList[i19] = 200024;
                                int i20 = i19 + 1;
                                this.shopList[i20] = 200025;
                                i2 = i20 + 1;
                            }
                            if (161 <= p) {
                                this.shopList[i2] = 200007;
                                int i21 = i2 + 1;
                                this.shopList[i21] = 200008;
                                int i22 = i21 + 1;
                                this.shopList[i22] = 200009;
                                int i23 = i22 + 1;
                                this.shopList[i23] = 200010;
                                int i24 = i23 + 1;
                                this.shopList[i24] = 200013;
                                int i25 = i24 + 1;
                                this.shopList[i25] = 200014;
                                int i26 = i25 + 1;
                                this.shopList[i26] = 200015;
                                i2 = i26 + 1;
                            }
                            if (189 <= p) {
                                this.shopList[i2] = 200016;
                                int i27 = i2 + 1;
                                this.shopList[i27] = 200017;
                                int i28 = i27 + 1;
                                this.shopList[i28] = 200018;
                                i2 = i28 + 1;
                            }
                            if (77 <= p) {
                                this.shopList[i2] = 200002;
                                int i29 = i2 + 1;
                                this.shopList[i29] = 200003;
                                int i30 = i29 + 1;
                                this.shopList[i30] = 200004;
                                int i31 = i30 + 1;
                                this.shopList[i31] = 200005;
                                i2 = i31 + 1;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170001;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130001;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130002;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130003;
                                i2++;
                            }
                            if (500021 < this.exProgressId) {
                                this.shopList[i2] = 180008;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130004;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 160001;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120001;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140001;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140002;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140003;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120002;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140004;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120003;
                                i2++;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170002;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120004;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140005;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120005;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130005;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120006;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140006;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140007;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110001;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120007;
                                i2++;
                            }
                            if (500009 < this.exProgressId) {
                                this.shopList[i2] = 180004;
                                i2++;
                            }
                            if (500031 < this.exProgressId) {
                                this.shopList[i2] = 180016;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120008;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120009;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130006;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 160002;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110003;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140008;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120010;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140009;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110004;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120011;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130007;
                                i2++;
                            }
                            if (119 <= p) {
                                this.shopList[i2] = 150003;
                                i2++;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170003;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130008;
                                i2++;
                            }
                            if (500030 < this.exProgressId) {
                                this.shopList[i2] = 180015;
                                i2++;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170004;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130009;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130010;
                                i2++;
                            }
                            if (p >= 0) {
                                this.shopList[i2] = 100001;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120012;
                                i2++;
                            }
                            if (500015 < this.exProgressId) {
                                this.shopList[i2] = 180006;
                                i2++;
                            }
                            if (119 <= p) {
                                this.shopList[i2] = 150005;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120013;
                                i2++;
                            }
                            if (p >= 0) {
                                this.shopList[i2] = 100002;
                                i2++;
                            }
                            if (500003 < this.exProgressId) {
                                this.shopList[i2] = 180001;
                                i2++;
                            }
                            if (500032 < this.exProgressId) {
                                this.shopList[i2] = 180017;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130011;
                                i2++;
                            }
                            if (119 <= p) {
                                this.shopList[i2] = 150006;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120014;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140010;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140011;
                                i2++;
                            }
                            if (p >= 0) {
                                this.shopList[i2] = 100003;
                                i2++;
                            }
                            if (500017 < this.exProgressId) {
                                this.shopList[i2] = 180011;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140012;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120015;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130012;
                                i2++;
                            }
                            if (500027 < this.exProgressId) {
                                this.shopList[i2] = 180012;
                                i2++;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170005;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120016;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 160003;
                                i2++;
                            }
                            if (p >= 0) {
                                this.shopList[i2] = 100004;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120017;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130013;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120018;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130014;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140013;
                                i2++;
                            }
                            if (500019 < this.exProgressId) {
                                this.shopList[i2] = 180007;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120019;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120020;
                                i2++;
                            }
                            if (119 <= p) {
                                this.shopList[i2] = 150008;
                                i2++;
                            }
                            if (500029 < this.exProgressId) {
                                this.shopList[i2] = 180014;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120021;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120022;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140014;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110005;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130015;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130016;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120023;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140015;
                                i2++;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170006;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120024;
                                i2++;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170007;
                                i2++;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170008;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130017;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140017;
                                i2++;
                            }
                            if (500033 < this.exProgressId) {
                                this.shopList[i2] = 180018;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120025;
                                i2++;
                            }
                            if (p >= 0) {
                                this.shopList[i2] = 100005;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120026;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130018;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 160004;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140018;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130019;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130020;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140019;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120027;
                                i2++;
                            }
                            if (500023 < this.exProgressId) {
                                this.shopList[i2] = 180009;
                                i2++;
                            }
                            if (217 <= p) {
                                this.shopList[i2] = 170009;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120028;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140020;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 160005;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130021;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120029;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120030;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120031;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130022;
                                i2++;
                            }
                            if (500006 < this.exProgressId) {
                                this.shopList[i2] = 180003;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110008;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140021;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110009;
                                i2++;
                            }
                            if (500012 < this.exProgressId) {
                                this.shopList[i2] = 180005;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120032;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120033;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140022;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140023;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140024;
                                i2++;
                            }
                            if (119 <= p) {
                                this.shopList[i2] = 150011;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120034;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130023;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140025;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140026;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130024;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120035;
                                i2++;
                            }
                            if (119 <= p) {
                                this.shopList[i2] = 150012;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140027;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140028;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120036;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120037;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120038;
                                i2++;
                            }
                            if (500025 < this.exProgressId) {
                                this.shopList[i2] = 180010;
                                i2++;
                            }
                            if (p >= 0) {
                                this.shopList[i2] = 100006;
                                i2++;
                            }
                            if (p >= 0) {
                                this.shopList[i2] = 100007;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110010;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120039;
                                i2++;
                            }
                            if (500028 < this.exProgressId) {
                                this.shopList[i2] = 180013;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 160006;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 160007;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 160008;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120040;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120041;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110014;
                                i2++;
                            }
                            if (p >= 0) {
                                this.shopList[i2] = 100008;
                                i2++;
                            }
                            if (51 <= p) {
                                this.shopList[i2] = 130025;
                                i2++;
                            }
                            if (4 <= p) {
                                this.shopList[i2] = 110015;
                                i2++;
                            }
                            if (119 <= p) {
                                this.shopList[i2] = 150014;
                                i2++;
                            }
                            if (119 <= p) {
                                this.shopList[i2] = 150015;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120042;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120043;
                                i2++;
                            }
                            if (91 <= p) {
                                this.shopList[i2] = 140029;
                                i2++;
                            }
                            if (500006 < this.exProgressId) {
                                this.shopList[i2] = 180002;
                                i2++;
                            }
                            if (16 <= p) {
                                this.shopList[i2] = 120044;
                                i2++;
                            }
                            this.shopNum = i2;
                        } else {
                            popAlert("データ取得エラー", "\n\nuset_t\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                        }
                        try {
                            Cursor cursor5 = this.f220db.rawQuery("SELECT COUNT(u.user_kodama_id) FROM user_kodama_t u WHERE u.kodama_id<>0", null);
                            if (cursor5.moveToFirst()) {
                                this.unitNum = cursor5.getInt(0);
                                if (2 <= this.unitNum && this.userDemoNo < 99) {
                                    this.userDemoNo = 99;
                                    this.f220db.execSQL("UPDATE user_t SET user_demo_no=" + this.userDemoNo);
                                }
                            }
                            if (cursor5 != null) {
                                cursor5.close();
                            }
                            this.bgImg = getBaseActivity().getResourceUtil().getSprite("bg/initial.png");
                            this.bgImg.setPosition(0.0f, 0.0f);
                            attachChild(this.bgImg);
                            topHeaderOpen();
                            menuBottomButton();
                            if (this.unitNum == 0) {
                                this.menuTutorialOpenFlg = true;
                                this.pageMoveSound.play();
                                confirmBoxOpen("ある日突然、幻想郷から\n弾幕が失われました。\n\n代わりに少女達が得たモノは、\n彼女達によく似た\n小さな式神「コダマ」。\n\n人の呼びかけに応じて姿を現し、\nスペルも操る不思議な存在。", 81, 81, false);
                            } else {
                                if (this.userDemoNo == 11 || this.userDemoNo == 19) {
                                    this.userDemoNo++;
                                    menuItemListOpen();
                                } else {
                                    menuQuestOpen(true);
                                }
                                this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/clear.png", "button/clear.png");
                                this.buttonOk.setPosition(0.0f, 0.0f);
                                this.buttonOk.setTag(0);
                                attachChild(this.buttonOk);
                                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/clear.png", "button/clear.png");
                                this.buttonNg.setPosition(0.0f, 0.0f);
                                this.buttonNg.setTag(0);
                                attachChild(this.buttonNg);
                                this.infoText = new Text(0.0f, 0.0f, (IFont) this.fontWhite, (CharSequence) "test", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                                attachChild(this.infoText);
                                this.buttonOk.detachSelf();
                                this.buttonNg.detachSelf();
                                this.infoText.detachSelf();
                                if (this.userDemoNo < 4) {
                                    this.cartainBottom = new Rectangle(0.0f, 760.0f, 540.0f, 200.0f, getBaseActivity().getVertexBufferObjectManager());
                                    this.cartainBottom.setColor(0.0f, 0.0f, 0.0f);
                                    this.cartainBottom.setAlpha(0.5f);
                                    attachChild(this.cartainBottom);
                                }
                            }
                            this.bitmapFontS = new BitmapFont(getBaseActivity().getTextureManager(), getBaseActivity().getAssets(), "font/numS.fnt");
                            this.bitmapFontS.load();
                            registerUpdateHandler(this.menuUpdateHandler);
                            this.cartain = new Rectangle(0.0f, 0.0f, 540.0f, 960.0f, getBaseActivity().getVertexBufferObjectManager());
                            this.cartain.setColor(0.0f, 0.0f, 0.0f);
                            attachChild(this.cartain);
                            if (this.userDemoNo == 24) {
                                this.userDemoNo = 99;
                                this.f220db.execSQL("UPDATE user_t SET user_demo_no=" + this.userDemoNo);
                            }
                            this.commonHandler = new TimerHandler(2.0f, new ITimerCallback() {
                                public void onTimePassed(TimerHandler pTimerHandler) {
                                    if (MenuScene.this.userDemoNo == 4 && MenuScene.this.userProgressQuest == 1) {
                                        MenuScene.this.userDemoNo = 5;
                                        MenuScene.this.unitDetailIcon = MenuScene.this.getBaseActivity().getResourceUtil().getSprite("item/arrow.png");
                                        MenuScene.this.unitDetailIcon.setPosition(420.0f, 755.0f);
                                        MenuScene.this.attachChild(MenuScene.this.unitDetailIcon);
                                        MenuScene.this.confirmBoxOpen("初クエストお疲れ様でした！\n\n\nクエストをクリアすると、\n特別な報酬を\nもらえることがあります。\n\n早速、先ほど獲得したアイテムを\n使ってみましょう。\n\n\n「アイテム」をタップしてください。", 99999996, 0, false);
                                    } else if (MenuScene.this.userDemoNo == 12) {
                                        MenuScene.this.unitDetailIcon = MenuScene.this.getBaseActivity().getResourceUtil().getSprite("item/arrow.png");
                                        MenuScene.this.unitDetailIcon.setPosition(420.0f, 755.0f);
                                        MenuScene.this.attachChild(MenuScene.this.unitDetailIcon);
                                        MenuScene.this.confirmBoxOpen("コダマカードを使うと、\n新しいコダマと契約できます。\n\nそれではコダマカードを\n使用してみましょう。", 99999996, 0, false);
                                    } else if (MenuScene.this.userDemoNo == 20) {
                                        MenuScene.this.unitDetailIcon = MenuScene.this.getBaseActivity().getResourceUtil().getSprite("item/arrow.png");
                                        MenuScene.this.unitDetailIcon.setPosition(302.0f, 755.0f);
                                        MenuScene.this.attachChild(MenuScene.this.unitDetailIcon);
                                        MenuScene.this.confirmBoxOpen("新しいコダマを入手したら、\n編成画面でパーティーに\n組み込みましょう。", 99999996, 0, false);
                                    }
                                    MenuScene.this.cartain.registerEntityModifier(new FadeOutModifier(0.5f));
                                    MenuScene.this.commonHandler2 = new TimerHandler(0.5f, new ITimerCallback() {
                                        public void onTimePassed(TimerHandler pTimerHandler) {
                                            MenuScene.this.cartain.detachSelf();
                                        }
                                    });
                                    MenuScene.this.registerUpdateHandler(MenuScene.this.commonHandler2);
                                    if (MenuScene.this.bgm != null) {
                                        MenuScene.this.bgm.setLooping(true);
                                        MenuScene.this.bgm.play();
                                    }
                                }
                            });
                            registerUpdateHandler(this.commonHandler);
                            timeStampCheck();
                            if (cursor5 != null) {
                                cursor5.close();
                            }
                            nendRewardAdInit();
                            showAds1();
                        } catch (Throwable th2) {
                            if (cursor3 != null) {
                                cursor3.close();
                            }
                            throw th2;
                        }
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
            } catch (Throwable th5) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th5;
            }
        } catch (Throwable th6) {
            if (cursor != null) {
                cursor.close();
            }
            throw th6;
        }
    }

    public void prepareSoundAndMusic() {
        try {
            this.bgm = MusicFactory.createMusicFromAsset(getBaseActivity().getMusicManager(), getBaseActivity(), "music/AtttempoSennenGensoukyou.mp3");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            this.buttonPressedSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouSystem49.ogg");
            this.buttonCanceledSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouSystem43.ogg");
            this.StartPressedSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamDecisionStart.ogg");
            this.pageMoveSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/MaouPaper01.ogg");
            this.itemUseSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/LaboHeal.ogg");
            this.unitGrowSound = SoundFactory.createSoundFromAsset(getBaseActivity().getSoundManager(), getBaseActivity(), "sound/TamGrow.ogg");
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
            this.buttonPressedSound.setVolume(0.0f);
            this.buttonCanceledSound.setVolume(0.0f);
            this.StartPressedSound.setVolume(0.0f);
            this.pageMoveSound.setVolume(0.0f);
            this.itemUseSound.setVolume(0.0f);
            this.unitGrowSound.setVolume(0.0f);
            return;
        }
        this.buttonPressedSound.setVolume(1.0f);
        this.buttonCanceledSound.setVolume(1.0f);
        this.StartPressedSound.setVolume(1.0f);
        this.pageMoveSound.setVolume(1.0f);
        this.itemUseSound.setVolume(1.0f);
        this.unitGrowSound.setVolume(1.0f);
    }

    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getAction() == 0 && e.getKeyCode() == 4 && !this.endConfirmFlg) {
            endConfirmOpen();
        }
        return false;
    }

    public void onDestroy() {
        this.mNendAdRewardedVideo.releaseAd();
    }

    /* JADX WARNING: type inference failed for: r11v0, types: [kanatamikado.ae.reiki.MenuScene, org.andengine.entity.sprite.ButtonSprite$OnClickListener] */
    public void onClick(ButtonSprite pButtonSprite, float pTouchAreaLocalX, float pTouchAreaLocalY) {
        Log.d("Debug", "MenuScene_onClick:" + pButtonSprite.getTag());
        if (this.endConfirmFlg) {
            if (pButtonSprite.getTag() == 99999999) {
                ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
                getBaseActivity().finish();
            } else if (pButtonSprite.getTag() == 99999998) {
                this.buttonCanceledSound.play();
                endConfirmClose();
            }
        } else if (pButtonSprite.getTag() == 99999997) {
            this.buttonCanceledSound.play();
        } else if (this.menuTutorialOpenFlg) {
            if (pButtonSprite.getTag() == 81) {
                confirmBoxClose(false);
                this.pageMoveSound.play();
                confirmBoxOpen("それからしばらくした頃、\n幻想郷中で「新しい勝負」が\n流行りだしました。\n\n弾幕ではなくコダマで戦う、\n通称「コダマ遊び」。\n\n後に「玉姫異変」と呼ばれる、\nちょっと不思議なトラブルが\n幻想郷で幕を開けます。", 82, 82, false);
            } else if (pButtonSprite.getTag() == 82) {
                confirmBoxClose(false);
                this.pageMoveSound.play();
                initialUnitSet();
            } else if (pButtonSprite.getTag() == 83) {
                ((ButtonSprite) this.buttonList.get(0)).detachSelf();
                unregisterTouchArea((ITouchArea) this.buttonList.get(0));
                confirmBoxClose(true);
                this.buttonCanceledSound.play();
                initialUnitSet();
            } else if (pButtonSprite.getTag() == 84) {
                this.buttonPressedSound.play();
                confirmBoxClose(false);
                this.menuTutorialOpenFlg = false;
                menuBottomStart();
                menuQuestOpen(true);
                this.cartainBottom = new Rectangle(0.0f, 760.0f, 540.0f, 200.0f, getBaseActivity().getVertexBufferObjectManager());
                this.cartainBottom.setColor(0.0f, 0.0f, 0.0f);
                this.cartainBottom.setAlpha(0.5f);
                attachChild(this.cartainBottom);
            } else if (1000 < pButtonSprite.getTag()) {
                confirmBoxClose(true);
                this.buttonPressedSound.play();
                ((ButtonSprite) this.buttonList.get(0)).detachSelf();
                unregisterTouchArea((ITouchArea) this.buttonList.get(0));
                newUnitGet(pButtonSprite.getTag() + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED);
                confirmBoxOpen("お疲れ様でした、\n初期設定が完了しました！\n\nそれでは「クエスト」に出発しましょう。\n\n「ステージ1」と書かれたボタンを\nタップしてみてください。", 84, 84, false);
            } else {
                for (int i = 0; i < 6; i++) {
                    ((ButtonSprite) this.buttonList.get(i)).detachSelf();
                    unregisterTouchArea((ITouchArea) this.buttonList.get(i));
                }
                confirmBoxClose(false);
                this.buttonPressedSound.play();
                String str = "";
                if (pButtonSprite.getTag() == 73) {
                    str = "河童「ちびにとり」（水属性）";
                }
                if (pButtonSprite.getTag() == 110) {
                    str = "豊穣の神「ちびみのりこ」（樹属性）";
                }
                if (pButtonSprite.getTag() == 194) {
                    str = "火車「ちびりん」（炎属性）";
                }
                confirmBoxOpen(str + "\n\nこのコダマと契約します。\nよろしいですか？", pButtonSprite.getTag() + 1000, 83, true);
                this.buttonList.add(0, getBaseActivity().getResourceUtil().getButtonSprite("kodama/" + pButtonSprite.getTag() + ".png", "kodama/" + pButtonSprite.getTag() + ".png"));
                ((ButtonSprite) this.buttonList.get(0)).setPosition(206.0f, 300.0f);
                ((ButtonSprite) this.buttonList.get(0)).setTag(pButtonSprite.getTag() + 1000);
                ((ButtonSprite) this.buttonList.get(0)).setOnClickListener(this);
                registerTouchArea((ITouchArea) this.buttonList.get(0));
                attachChild((IEntity) this.buttonList.get(0));
            }
        } else if (this.userDemoNo == 5) {
            if (pButtonSprite.getTag() == 99999996) {
                this.buttonPressedSound.play();
                confirmBoxClose(false);
                menuBottomStart();
                this.userDemoNo = 6;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 6) {
            if (pButtonSprite.getTag() == 21) {
                this.buttonPressedSound.play();
                menuQuestClose();
                menuItemOpen();
                this.unitDetailIcon.setZIndex(99999999);
                sortChildren();
                this.unitDetailIcon.setPosition(412.0f, 226.0f);
                this.userDemoNo = 7;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 7) {
            if (pButtonSprite.getTag() == 10000002) {
                this.buttonPressedSound.play();
                this.listHead = pButtonSprite.getTag() - 10000000;
                this.listPage = 0;
                menuItemClose();
                menuItemListOpen();
                this.unitDetailIcon.setZIndex(99999999);
                sortChildren();
                this.unitDetailIcon.setPosition(412.0f, 98.0f);
                this.userDemoNo = 8;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 8) {
            if (pButtonSprite.getTag() == 10030001) {
                this.buttonPressedSound.play();
                menuItemListClose();
                menuItemDetailOpen(pButtonSprite.getTag() - 10000000);
                this.unitDetailIcon.setZIndex(99999999);
                sortChildren();
                this.unitDetailIcon.setPosition(340.0f, 528.0f);
                this.userDemoNo = 9;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 9) {
            if (pButtonSprite.getTag() == 10030001) {
                this.buttonPressedSound.play();
                this.unitDetailIcon.detachSelf();
                this.userDemoNo = 10;
                menuItemDetailClose();
                menuItemExe(pButtonSprite.getTag() - 10000000);
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 12) {
            if (pButtonSprite.getTag() == 99999996) {
                this.buttonPressedSound.play();
                confirmBoxClose(false);
                menuBottomStart();
                this.userDemoNo = 13;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 13) {
            if (pButtonSprite.getTag() == 21) {
                this.buttonPressedSound.play();
                menuItemListClose();
                menuItemOpen();
                this.unitDetailIcon.setZIndex(99999999);
                sortChildren();
                this.unitDetailIcon.setPosition(412.0f, 160.0f);
                this.userDemoNo = 14;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 14) {
            if (pButtonSprite.getTag() == 10000001) {
                this.buttonPressedSound.play();
                this.listHead = pButtonSprite.getTag() - 10000000;
                this.listPage = 0;
                menuItemClose();
                menuItemListOpen();
                this.unitDetailIcon.setZIndex(99999999);
                sortChildren();
                this.unitDetailIcon.setPosition(412.0f, 98.0f);
                this.userDemoNo = 15;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 15) {
            if (10100000 >= pButtonSprite.getTag() || pButtonSprite.getTag() >= 10200000) {
                this.buttonCanceledSound.play();
                return;
            }
            this.buttonPressedSound.play();
            this.unitDetailIcon.detachSelf();
            menuItemListClose();
            menuItemDetailOpen(pButtonSprite.getTag() - 10000000);
            this.userDemoNo = 16;
        } else if (this.userDemoNo == 16) {
            if (10100000 >= pButtonSprite.getTag() || pButtonSprite.getTag() >= 10200000) {
                this.buttonCanceledSound.play();
                return;
            }
            this.buttonPressedSound.play();
            this.unitDetailIcon.detachSelf();
            this.userDemoNo = 17;
            menuItemDetailClose();
            cardUseConfirmOpen(pButtonSprite.getTag() - 10000000);
        } else if (this.userDemoNo == 17) {
            if (40000000 >= pButtonSprite.getTag() || pButtonSprite.getTag() >= 50000000) {
                this.buttonCanceledSound.play();
                return;
            }
            this.buttonPressedSound.play();
            this.userDemoNo = 18;
            menuItemDetailClose();
            cardUseExe(pButtonSprite.getTag() - 40000000);
        } else if (this.userDemoNo == 20) {
            if (pButtonSprite.getTag() == 99999996) {
                this.buttonPressedSound.play();
                confirmBoxClose(false);
                menuBottomStart();
                this.userDemoNo = 21;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 21) {
            if (pButtonSprite.getTag() == 31) {
                this.buttonPressedSound.play();
                menuItemListClose();
                menuPartyOpen(true);
                this.unitDetailIcon.setZIndex(99999999);
                sortChildren();
                this.unitDetailIcon.setPosition(412.0f, 226.0f);
                this.userDemoNo = 22;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 22) {
            if (pButtonSprite.getTag() == 10000002) {
                this.buttonPressedSound.play();
                this.partySortNo = pButtonSprite.getTag() - 10000000;
                menuPartyClose();
                menuPartyChangeOpen();
                this.unitDetailIcon.setZIndex(99999999);
                sortChildren();
                this.unitDetailIcon.setPosition(160.0f, 120.0f);
                this.userDemoNo = 23;
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.userDemoNo == 23) {
            if (10000000 >= pButtonSprite.getTag() || pButtonSprite.getTag() >= 20000000) {
                this.buttonCanceledSound.play();
                return;
            }
            Cursor cursor = this.f220db.rawQuery("SELECT  u.user_kodama_id, u.kodama_id FROM user_kodama_t u WHERE u.user_kodama_id=" + (pButtonSprite.getTag() - 10000000), null);
            if (cursor.moveToNext()) {
                if (cursor.getInt(1) != 0) {
                    this.buttonPressedSound.play();
                    this.unitDetailIcon.detachSelf();
                    partyChangeExe(pButtonSprite.getTag() - 10000000);
                } else {
                    this.buttonCanceledSound.play();
                }
            }
            if (cursor != null) {
                cursor.close();
            }
        } else if (this.userDemoNo == 24) {
            if (pButtonSprite.getTag() == 99999996) {
                this.buttonPressedSound.play();
                confirmBoxClose(false);
                menuBottomStart();
                this.userDemoNo = 99;
                this.f220db.execSQL("UPDATE user_t SET user_demo_no=" + this.userDemoNo);
                menuPartyClose();
                menuQuestOpen(true);
                return;
            }
            this.buttonCanceledSound.play();
        } else if (this.menuQuestConfirmFlg) {
            if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                menuQuestConfirmClose();
                if (pButtonSprite.getTag() == 91) {
                    this.userDifficult--;
                    if (this.userDifficult < 1) {
                        this.userDifficult = 4;
                    }
                } else if (pButtonSprite.getTag() == 92) {
                    this.userDifficult++;
                    if (4 < this.userDifficult) {
                        this.userDifficult = 1;
                    }
                }
                this.f220db.execSQL("UPDATE user_t SET user_difficult= " + this.userDifficult);
                this.buttonPressedSound.play();
                menuQuestConfirmOpen();
            } else if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuQuestConfirmClose();
            } else if (10000000 < pButtonSprite.getTag()) {
                menuQuestStart(pButtonSprite.getTag() - 10000000);
            }
        } else if (this.menuBoxEquipListOpenFlg) {
            if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                menuUnitEquipListClose();
                this.pageMoveSound.play();
                if (pButtonSprite.getTag() == 91) {
                    this.listPage--;
                    if (this.listPage < 0) {
                        this.listPage = this.listPageMax;
                    }
                } else {
                    this.listPage++;
                    if (this.listPageMax < this.listPage) {
                        this.listPage = 0;
                    }
                }
                menuUnitEquipListOpen();
            } else if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuUnitEquipListClose();
                menuUnitOpen(false);
            }
        } else if (this.menuBoxBpListOpenFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuUnitBpListClose();
                menuUnitOpen(false);
            }
        } else if (this.menuBoxEvoListOpenFlg) {
            if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                menuUnitEvoListClose();
                this.pageMoveSound.play();
                if (pButtonSprite.getTag() == 91) {
                    this.listPage--;
                    if (this.listPage < 1) {
                        this.listPage = this.listPageMax;
                    }
                } else {
                    this.listPage++;
                    if (this.listPageMax < this.listPage) {
                        this.listPage = 1;
                    }
                }
                menuUnitEvoListOpen();
            } else if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuUnitEvoListClose();
                menuUnitOpen(false);
            }
        } else if (this.menuUnitDetailOpenFlg) {
            if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                for (int i2 = 0; i2 < 30; i2++) {
                    if (this.unitSort[i2] == this.targetUserKodamaId) {
                        int sort = i2;
                        for (int i3 = 0; i3 < 30; i3++) {
                            if (pButtonSprite.getTag() == 91) {
                                sort--;
                                if (sort < 0) {
                                    sort = 29;
                                }
                            } else if (pButtonSprite.getTag() == 92) {
                                sort++;
                                if (30 <= sort) {
                                    sort = 0;
                                }
                            }
                            if (this.unitSortId[sort] != 0) {
                                this.buttonPressedSound.play();
                                this.targetUserKodamaId = this.unitSort[sort];
                                if (this.menuUnitDetailSpellOpenFlg) {
                                    menuSpellClose();
                                    menuSpellOpen();
                                    return;
                                }
                                menuUnitDetailClose();
                                menuUnitDetailOpen();
                                return;
                            }
                        }
                        return;
                    }
                }
            } else if (pButtonSprite.getTag() == 0) {
                this.buttonCanceledSound.play();
                menuUnitDetailClose();
                menuUnitOpen(true);
                menuBottomStart();
            } else if (pButtonSprite.getTag() == 10000) {
                this.buttonCanceledSound.play();
            } else if (pButtonSprite.getTag() == 10001) {
                this.buttonPressedSound.play();
                menuUnitDetailClose();
                menuUnitSeparateConfirmOpen();
            } else if (pButtonSprite.getTag() == 10002) {
                if (2 <= this.unitNum) {
                    this.buttonPressedSound.play();
                    menuUnitSeparateExe();
                    return;
                }
                this.buttonCanceledSound.play();
            } else if (pButtonSprite.getTag() == 10003) {
                this.buttonCanceledSound.play();
                menuUnitSeparateConfirmClose();
                menuUnitDetailOpen();
            } else if (pButtonSprite.getTag() == 10011) {
                this.buttonPressedSound.play();
                nickInputDialogBuilder();
            } else if (pButtonSprite.getTag() == 10021) {
                this.buttonPressedSound.play();
                menuUnitDetailClose();
                menuSpellOpen();
            } else if (pButtonSprite.getTag() == 10022) {
                this.buttonCanceledSound.play();
                menuSpellClose();
                menuUnitDetailOpen();
            } else if (pButtonSprite.getTag() == 10023) {
                this.buttonPressedSound.play();
                menuSpellClose();
                this.listPage = 0;
                menuSpellLearnOpen();
            } else if (pButtonSprite.getTag() == 10031) {
                this.buttonPressedSound.play();
                menuUnitDetailClose();
                this.listPage = 0;
                menuEquipSelectOpen();
            } else if (pButtonSprite.getTag() == 10041) {
                this.buttonPressedSound.play();
                menuUnitDetailClose();
                this.listPage = 2;
                menuUnitItemOpen();
            } else if (pButtonSprite.getTag() == 10051) {
                this.buttonPressedSound.play();
                menuUnitDetailClose();
                menuBpOpen();
            } else if (pButtonSprite.getTag() == 10059) {
                this.buttonCanceledSound.play();
                menuBpClose();
                menuUnitDetailOpen();
            } else if (10060 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 10070) {
                this.buttonCanceledSound.play();
                menuBpExe(pButtonSprite.getTag() - 10060, false);
                menuBpClose();
                menuBpOpen();
            } else if (10070 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 10080) {
                this.unitGrowSound.play();
                menuBpExe(pButtonSprite.getTag() - 10070, true);
                menuBpClose();
                menuBpOpen();
            } else if (pButtonSprite.getTag() == 10080) {
            } else {
                if (10080 < pButtonSprite.getTag() && pButtonSprite.getTag() < 10090) {
                    this.buttonPressedSound.play();
                    menuSpellClose();
                    menuSpellDeleteConfirmOpen(pButtonSprite.getTag() - 10080);
                } else if (10100 < pButtonSprite.getTag() && pButtonSprite.getTag() < 10110) {
                    this.buttonPressedSound.play();
                    spellMove(pButtonSprite.getTag() - 10100, true);
                } else if (10110 < pButtonSprite.getTag() && pButtonSprite.getTag() < 10120) {
                    this.buttonPressedSound.play();
                    spellMove(pButtonSprite.getTag() - 10110, false);
                } else if (pButtonSprite.getTag() == 10120) {
                    this.buttonCanceledSound.play();
                    menuSpellDeleteConfirmClose();
                    menuSpellOpen();
                } else if (10120 < pButtonSprite.getTag() && pButtonSprite.getTag() < 10130) {
                    this.unitGrowSound.play();
                    menuSpellDeleteConfirmExe(pButtonSprite.getTag() - 10120);
                } else if (10130 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 10140) {
                    this.buttonPressedSound.play();
                    menuUnitProtectExe(pButtonSprite.getTag() - 10130);
                    menuUnitDetailClose();
                    menuUnitDetailOpen();
                } else if (10140 < pButtonSprite.getTag() && pButtonSprite.getTag() < 10150) {
                    this.buttonPressedSound.play();
                    menuSpellClose();
                    menuUnitBookConfirmOpen(pButtonSprite.getTag() - 10140);
                } else if (pButtonSprite.getTag() == 10140) {
                    this.buttonCanceledSound.play();
                    menuUnitBookConfirmClose();
                    menuSpellOpen();
                } else if (10150 < pButtonSprite.getTag() && pButtonSprite.getTag() < 10160) {
                    this.unitGrowSound.play();
                    menuUnitBookExe(pButtonSprite.getTag() - 10150);
                }
            }
        } else if (this.menuEquipDetailOpenFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuEquipDetailClose();
                menuEquipSelectOpen();
                return;
            }
            this.buttonPressedSound.play();
            menuEquipExe(pButtonSprite.getTag() - 10000000);
        } else if (this.menuUnitItemDetailOpenFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuUnitItemDetailClose();
                menuUnitItemOpen();
            } else if (10000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                menuUnitItemDetailClose();
                menuUnitItemExe(pButtonSprite.getTag() - 10000000);
            } else if (pButtonSprite.getTag() == 20000000) {
                this.buttonPressedSound.play();
                menuUnitItemExeClose();
                menuUnitItemOpen();
            }
        } else if (this.menuSpellLearnConfirmFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuSpellLearnConfirmClose();
                menuSpellLearnOpen();
            } else if (10000000 < pButtonSprite.getTag()) {
                this.unitGrowSound.play();
                menuSpellLearnConfirmExe(pButtonSprite.getTag() - 10000000);
            }
        } else if (this.menuHelpDetailOpenFlg) {
            if (pButtonSprite.getTag() == 20000) {
                this.buttonCanceledSound.play();
                menuHelpDetailClose();
            } else if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                menuHelpDetailClose();
                this.pageMoveSound.play();
                if (pButtonSprite.getTag() == 91) {
                    this.listPage--;
                    if (this.listPage < 1) {
                        this.listPage = this.listPageMax;
                    }
                } else {
                    this.listPage++;
                    if (this.listPageMax < this.listPage) {
                        this.listPage = 1;
                    }
                }
                menuHelpDetailOpen();
            }
        } else if (this.menuAttrOpenFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                attrTableClose();
                menuHelpOpen();
            }
        } else if (this.menuNameResetFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonPressedSound.play();
                unitNameResetExe();
                confirmBoxClose(true);
                menuHelpOpen();
                this.menuNameResetFlg = false;
            } else if (pButtonSprite.getTag() == 20000000) {
                this.buttonCanceledSound.play();
                confirmBoxClose(true);
                menuHelpOpen();
                this.menuNameResetFlg = false;
            }
        } else if (this.menuShopDetailOpenFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuShopConfirmClose();
                menuShopOpen();
            } else if (10000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                this.buttonPressedSound.play();
                menuShopConfirmClose();
                itemBuyExe(pButtonSprite.getTag() - 10000000);
            }
        } else if (this.menuCardChangeDetailOpenFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuCardChangeConfirmClose();
                menuCardChangeOpen();
            } else if (10000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                this.buttonPressedSound.play();
                menuCardChangeConfirmClose();
                menuCardChangeExe(pButtonSprite.getTag() - 10000000);
            } else if (pButtonSprite.getTag() == 20000000) {
                this.buttonCanceledSound.play();
                menuCardChangeExeClose();
                menuItemOpen();
            }
        } else if (this.menuItemDetailOpenFlg) {
            if (pButtonSprite.getTag() == 10000000) {
                this.buttonCanceledSound.play();
                menuItemDetailClose();
                menuItemListOpen();
            }
            if (pButtonSprite.getTag() == 10030000) {
                this.buttonCanceledSound.play();
                menuItemExeClose();
                menuItemListOpen();
            } else if (pButtonSprite.getTag() == 10090002) {
                this.buttonPressedSound.play();
                menuItemDetailClose();
                this.listPage = 0;
                menuCardChangeOpen();
            } else if (10030000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 10100000) {
                this.buttonPressedSound.play();
                menuItemDetailClose();
                menuItemExe(pButtonSprite.getTag() - 10000000);
            } else if (pButtonSprite.getTag() == 10100000) {
                this.buttonPressedSound.play();
                cardUseConfirmClose();
                menuItemListOpen();
            } else if (10100000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 10200000) {
                this.buttonPressedSound.play();
                menuItemDetailClose();
                cardUseConfirmOpen(pButtonSprite.getTag() - 10000000);
            } else if (20000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 30000000) {
                this.buttonPressedSound.play();
                menuItemDetailClose();
                itemSaleConfirmOpen(pButtonSprite.getTag() - 20000000);
            } else if (pButtonSprite.getTag() == 30000000) {
                this.buttonPressedSound.play();
                itemSaleConfirmClose();
                menuItemListOpen();
            } else if (30000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 40000000) {
                this.buttonPressedSound.play();
                itemSaleConfirmClose();
                itemSaleExe(pButtonSprite.getTag() - 30000000);
            } else if (pButtonSprite.getTag() == 40100000) {
                this.buttonPressedSound.play();
                cardUseExeClose();
                menuItemListOpen();
            } else if (40100000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 40200000) {
                this.buttonPressedSound.play();
                cardUseConfirmClose();
                cardUseExe(pButtonSprite.getTag() - 40000000);
            } else if (pButtonSprite.getTag() == 50000000) {
                this.buttonPressedSound.play();
                menuItemDetailClose();
                menuUnitOpen(true);
            } else if (60030000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 60100000) {
                this.buttonPressedSound.play();
                menuItemExeClose();
                menuItemExe(pButtonSprite.getTag() - 60000000);
            }
        } else if (pButtonSprite.getTag() == 90) {
            this.buttonPressedSound.play();
            sortChange();
        } else if (pButtonSprite.getTag() >= 49 || pButtonSprite.getTag() == this.menuMode) {
            if (this.menuMode == 1) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuAreaClose();
                    if (pButtonSprite.getTag() == 91) {
                        this.listPage--;
                        if (this.listPage < 1) {
                            this.listPage = this.listPageMax;
                        }
                    } else if (pButtonSprite.getTag() == 92) {
                        this.listPage++;
                        if (this.listPageMax < this.listPage) {
                            this.listPage = 1;
                        }
                    }
                    menuAreaOpen(true);
                } else if (pButtonSprite.getTag() == 10600001) {
                    this.listPage = 1;
                    this.buttonPressedSound.play();
                    this.areaSelect = 600001;
                    menuAreaClose();
                    this.editor.putInt("areaSelect", this.areaSelect);
                    this.editor.commit();
                    menuTowerOpen(true);
                } else if (pButtonSprite.getTag() == 10700001) {
                    this.listPage = 1;
                    this.buttonPressedSound.play();
                    this.areaSelect = 700001;
                    menuAreaClose();
                    this.editor.putInt("areaSelect", this.areaSelect);
                    this.editor.commit();
                    menuPhOpen(true);
                } else if (10000000 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.buttonPressedSound.play();
                    this.areaSelect = pButtonSprite.getTag() - 10000000;
                    menuAreaClose();
                    this.editor.putInt("areaSelect", this.areaSelect);
                    this.editor.commit();
                    menuQuestOpen(true);
                }
            } else if (this.menuMode == 3) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuTowerClose();
                    if (pButtonSprite.getTag() == 91) {
                        this.listPage--;
                        if (this.listPage < 1) {
                            this.listPage = this.listPageMax;
                        }
                    } else if (pButtonSprite.getTag() == 92) {
                        this.listPage++;
                        if (this.listPageMax < this.listPage) {
                            this.listPage = 1;
                        }
                    }
                    menuTowerOpen(true);
                } else if (10000000 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.buttonPressedSound.play();
                    this.areaSelect = pButtonSprite.getTag() - 10000000;
                    menuTowerClose();
                    this.editor.putInt("areaSelect", this.areaSelect);
                    this.editor.commit();
                    menuQuestOpen(true);
                }
            } else if (this.menuMode == 4) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuPhClose();
                    if (pButtonSprite.getTag() == 91) {
                        this.listPage--;
                        if (this.listPage < 1) {
                            this.listPage = this.listPageMax;
                        }
                    } else if (pButtonSprite.getTag() == 92) {
                        this.listPage++;
                        if (this.listPageMax < this.listPage) {
                            this.listPage = 1;
                        }
                    }
                    menuPhOpen(true);
                } else if (10000000 <= pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.buttonPressedSound.play();
                    this.areaSelect = pButtonSprite.getTag() - 10000000;
                    menuPhClose();
                    this.editor.putInt("areaSelect", this.areaSelect);
                    this.editor.commit();
                    menuQuestOpen(true);
                }
            } else if (this.menuMode == 2) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuQuestClose();
                    if (800000 >= this.areaSelect || this.areaSelect >= 900000) {
                        if (700000 >= this.areaSelect || this.areaSelect >= 800000) {
                            if (600000 >= this.areaSelect || this.areaSelect >= 700000) {
                                if (this.areaSelect == 500001) {
                                    this.areaSelect = 500001;
                                } else if (100000 < this.areaSelect) {
                                    this.areaSelect = this.userProgressArea;
                                } else if (pButtonSprite.getTag() == 91) {
                                    this.areaSelect--;
                                    if (this.areaSelect < 1) {
                                        this.areaSelect = this.userProgressArea;
                                    }
                                } else if (pButtonSprite.getTag() == 92) {
                                    this.areaSelect++;
                                    if (this.userProgressArea < this.areaSelect) {
                                        this.areaSelect = 1;
                                    }
                                }
                            } else if (pButtonSprite.getTag() == 91) {
                                this.areaSelect--;
                                if (this.areaSelect < 600001) {
                                    this.areaSelect = this.twAreaMax;
                                }
                            } else if (pButtonSprite.getTag() == 92) {
                                this.areaSelect++;
                                if (this.twAreaMax < this.areaSelect) {
                                    this.areaSelect = 600001;
                                }
                            }
                        } else if (pButtonSprite.getTag() == 91) {
                            this.areaSelect--;
                            if (this.areaSelect < 700001) {
                                this.areaSelect = this.phAreaMax;
                            }
                        } else if (pButtonSprite.getTag() == 92) {
                            this.areaSelect++;
                            if (this.phAreaMax < this.areaSelect) {
                                this.areaSelect = 700001;
                            }
                        }
                    } else if (pButtonSprite.getTag() == 91) {
                        this.listPageRoad--;
                        if (this.listPageRoad < 0) {
                            this.listPageRoad = this.listPageRoadMax;
                        }
                    } else if (pButtonSprite.getTag() == 92) {
                        this.listPageRoad++;
                        if (this.listPageRoadMax < this.listPageRoad) {
                            this.listPageRoad = 0;
                        }
                    }
                    this.editor.putInt("areaSelect", this.areaSelect);
                    this.editor.commit();
                    menuQuestOpen(true);
                } else if (10000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.buttonPressedSound.play();
                    this.selectQuestId = pButtonSprite.getTag();
                    menuQuestConfirmOpen();
                } else if (pButtonSprite.getTag() == 20000000) {
                    this.buttonPressedSound.play();
                    menuQuestClose();
                    this.listPage = 1;
                    menuAreaOpen(true);
                } else if (pButtonSprite.getTag() == 20000002) {
                    this.StartPressedSound.play();
                    if (this.bgm != null) {
                        this.bgm.stop();
                    }
                    menuQuestClose();
                    topHeaderClose();
                    menuBottomClose();
                    this.bgImg.registerEntityModifier(new FadeOutModifier(0.5f));
                    this.commonHandler = new TimerHandler(1.0f, new ITimerCallback() {
                        public void onTimePassed(TimerHandler pTimerHandler) {
                            MenuScene.this.destroy();
                            MenuScene.this.free();
                            ResourceUtil.getInstance(MenuScene.this.getBaseActivity()).resetAllTexture();
                        }
                    });
                    registerUpdateHandler(this.commonHandler);
                } else if (pButtonSprite.getTag() == 20000003) {
                    this.buttonPressedSound.play();
                    menuQuestClose();
                    menuBottomStop();
                    confirmBoxOpen("挑戦中のクエストを中止します。\nよろしいですか？", 20000005, 20000004, true);
                } else if (pButtonSprite.getTag() == 20000004) {
                    this.buttonCanceledSound.play();
                    confirmBoxClose(true);
                    menuBottomStart();
                    menuQuestOpen(true);
                } else if (pButtonSprite.getTag() == 20000005) {
                    this.buttonCanceledSound.play();
                    confirmBoxClose(true);
                    questReset();
                    menuBottomStart();
                    menuQuestOpen(true);
                }
            } else if (this.menuMode == 11) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuUnitClose(true);
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
                    menuUnitOpen(true);
                } else if (pButtonSprite.getTag() == 93 || pButtonSprite.getTag() == 94) {
                    this.pageMoveSound.play();
                    menuUnitClose(true);
                    if (pButtonSprite.getTag() == 93) {
                        this.boxSelect -= 2;
                        if (this.boxSelect < 1) {
                            this.boxSelect += 20;
                        }
                    } else if (pButtonSprite.getTag() == 94) {
                        this.boxSelect += 2;
                        if (20 < this.boxSelect) {
                            this.boxSelect -= 20;
                        }
                    }
                    this.editor.putInt("boxSelect", this.boxSelect);
                    this.editor.commit();
                    menuUnitOpen(true);
                } else if (10000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    this.buttonPressedSound.play();
                    this.targetUserKodamaId = pButtonSprite.getTag() - 10000000;
                    menuUnitClose(true);
                    menuUnitDetailOpen();
                } else if (pButtonSprite.getTag() == 20000000) {
                    this.buttonPressedSound.play();
                    menuUnitClose(false);
                    this.listPage = 0;
                    menuUnitEquipListOpen();
                } else if (pButtonSprite.getTag() == 30000000) {
                    this.targetUserKodamaId = 0;
                    this.buttonPressedSound.play();
                    menuUnitClose(true);
                    menuUnitMoveOpen();
                } else if (pButtonSprite.getTag() == 40000000) {
                    this.buttonPressedSound.play();
                    menuUnitClose(false);
                    this.listPage = 1;
                    menuUnitEvoListOpen();
                } else if (pButtonSprite.getTag() == 50000000) {
                    this.buttonPressedSound.play();
                    menuUnitClose(false);
                    this.listPage = 1;
                    menuUnitBpListOpen();
                }
            } else if (this.menuMode == 13) {
                if (pButtonSprite.getTag() == 10000000) {
                    this.buttonCanceledSound.play();
                    menuUnitItemClose();
                    menuUnitDetailOpen();
                } else if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuUnitItemClose();
                    if (pButtonSprite.getTag() == 91) {
                        this.listPage--;
                        if (this.listPage < 1) {
                            this.listPage = 2;
                        }
                    } else {
                        this.listPage++;
                        if (2 < this.listPage) {
                            this.listPage = 1;
                        }
                    }
                    menuUnitItemOpen();
                } else if (10000000 < pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    menuUnitItemClose();
                    menuUnitItemDetailOpen(pButtonSprite.getTag() - 10000000);
                }
            } else if (this.menuMode == 12) {
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
                    menuBottomStop();
                    menuEquipExe(0);
                }
            } else if (this.menuMode == 14) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuUnitMoveClose();
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
                    menuUnitMoveOpen();
                } else if (pButtonSprite.getTag() == 93 || pButtonSprite.getTag() == 94) {
                    this.pageMoveSound.play();
                    menuUnitMoveClose();
                    if (pButtonSprite.getTag() == 93) {
                        this.boxSelect -= 2;
                        if (this.boxSelect < 1) {
                            this.boxSelect += 20;
                        }
                    } else if (pButtonSprite.getTag() == 94) {
                        this.boxSelect += 2;
                        if (20 < this.boxSelect) {
                            this.boxSelect -= 20;
                        }
                    }
                    this.editor.putInt("boxSelect", this.boxSelect);
                    this.editor.commit();
                    menuUnitMoveOpen();
                } else if (10000000 < pButtonSprite.getTag() && pButtonSprite.getTag() < 20000000) {
                    if (this.targetUserKodamaId == 0) {
                        this.buttonPressedSound.play();
                        this.targetUserKodamaId = pButtonSprite.getTag() - 10000000;
                        menuUnitMoveClose();
                        menuUnitMoveOpen();
                        return;
                    }
                    this.buttonPressedSound.play();
                    menuUnitMoveExe(pButtonSprite.getTag() - 10000000);
                    this.targetUserKodamaId = 0;
                    menuUnitMoveClose();
                    menuUnitMoveOpen();
                }
            } else if (this.menuMode == 15) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
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
                    menuSpellLearnClose();
                    menuSpellLearnOpen();
                } else if (pButtonSprite.getTag() == 10000000) {
                    this.buttonPressedSound.play();
                    menuSpellLearnClose();
                    menuSpellOpen();
                } else if (10000000 < pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    menuSpellLearnClose();
                    menuSpellLearnConfirmOpen(pButtonSprite.getTag() - 10000000);
                }
            } else if (this.menuMode == 31) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    menuPartyClose();
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
                    menuPartyOpen(true);
                } else if (10000000 <= pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    this.partySortNo = pButtonSprite.getTag() - 10000000;
                    menuPartyClose();
                    menuPartyChangeOpen();
                }
            } else if (this.menuMode == 32) {
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
                } else if (pButtonSprite.getTag() == 10000000) {
                    this.buttonCanceledSound.play();
                    partyChangeExe(pButtonSprite.getTag() + 0);
                } else if (10000000 <= pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    partyChangeExe(pButtonSprite.getTag() - 10000000);
                }
            } else if (this.menuMode == 41) {
                if (pButtonSprite.getTag() == 105) {
                    menuOptionClose();
                    if (this.bgmFlg == 0) {
                        this.bgmFlg = 1;
                    } else {
                        this.bgmFlg = 0;
                    }
                    this.editor.putInt("bgmFlg", this.bgmFlg);
                    this.editor.commit();
                    bgmChange(false);
                    this.buttonPressedSound.play();
                    menuOptionOpen();
                } else if (pButtonSprite.getTag() == 106) {
                    menuOptionClose();
                    if (this.soundFlg == 0) {
                        this.soundFlg = 1;
                    } else {
                        this.soundFlg = 0;
                    }
                    this.editor.putInt("soundFlg", this.soundFlg);
                    this.editor.commit();
                    soundChange(false);
                    this.buttonPressedSound.play();
                    menuOptionOpen();
                } else if (pButtonSprite.getTag() == 114) {
                    if (2 <= this.userQuestStatus) {
                        this.buttonCanceledSound.play();
                        return;
                    }
                    this.buttonPressedSound.play();
                    menuOptionClose();
                    if (this.userDifficult == 1) {
                        this.userDifficult = 2;
                    } else if (this.userDifficult == 2) {
                        this.userDifficult = 3;
                    } else if (this.userDifficult == 3) {
                        this.userDifficult = 4;
                    } else {
                        this.userDifficult = 1;
                    }
                    this.f220db.execSQL("UPDATE user_t SET user_difficult= " + this.userDifficult);
                    menuOptionOpen();
                } else if (pButtonSprite.getTag() == 101) {
                    this.buttonPressedSound.play();
                    menuOptionClose();
                    menuHelpOpen();
                } else if (pButtonSprite.getTag() == 102) {
                    this.buttonPressedSound.play();
                    nameInputDialogBuilder();
                } else if (pButtonSprite.getTag() == 104) {
                    this.buttonPressedSound.play();
                    menuOptionClose();
                    this.listPage = 0;
                    menuIconChangeOpen();
                } else if (pButtonSprite.getTag() == 115) {
                    this.buttonPressedSound.play();
                    nendRewardAdOpen();
                } else if (pButtonSprite.getTag() == 107) {
                    this.buttonPressedSound.play();
                    menuOptionClose();
                    confirmBoxOpen("GooglePlayで当アプリの\nレビューを投稿して頂いた方に、\n初回だけ10万銭をお贈り致します。\n\nGooglePlayへ移動しますか？\n※この画面から移動して\n　レビューを投稿した場合のみ\n　特典が贈られます。", 108, 113, true);
                } else if (pButtonSprite.getTag() == 108) {
                    this.buttonPressedSound.play();
                    if (this.userReviewFlg == 0) {
                        this.userMoney += 100000;
                        this.f220db.execSQL("UPDATE user_t SET user_money=" + this.userMoney + ", user_review_flg=1");
                    }
                    confirmBoxClose(true);
                    getBaseActivity().browserStart("https://play.google.com/store/apps/details?id=kanatamikado.ae.reiki");
                } else if (pButtonSprite.getTag() == 109) {
                    this.buttonPressedSound.play();
                    menuOptionClose();
                    confirmSnsOpen();
                } else if (pButtonSprite.getTag() == 110) {
                    this.buttonPressedSound.play();
                    confirmSnsExe();
                    confirmSnsClose();
                    if (2 <= this.userSnsFlg) {
                        getBaseActivity().browserStart("https://twitter.com/yanagi_yuduki/status/556976274439041025");
                    } else {
                        getBaseActivity().browserStart("https://twitter.com/yanagi_yuduki/status/549486770320965633");
                    }
                } else if (pButtonSprite.getTag() == 111) {
                    this.buttonPressedSound.play();
                    confirmSnsExe();
                    confirmSnsClose();
                    getBaseActivity().browserStart("https://www.facebook.com/pages/%E6%9D%B1%E6%96%B9%E3%82%B2%E3%83%BC%E3%83%A0-%E6%9D%B1%E6%96%B9%E7%8E%89%E9%9C%8A%E5%A7%AB/758310367558067?ref=aymt_homepage_panel");
                } else if (pButtonSprite.getTag() == 113) {
                    this.buttonCanceledSound.play();
                    confirmBoxClose(true);
                    menuOptionOpen();
                } else if (pButtonSprite.getTag() == 112) {
                    this.buttonCanceledSound.play();
                    confirmSnsClose();
                    menuOptionOpen();
                }
            } else if (this.menuMode == 42) {
                if (pButtonSprite.getTag() == 106) {
                    this.buttonPressedSound.play();
                    for (int i4 = 0; i4 < 10; i4++) {
                        unregisterTouchArea((ITouchArea) this.buttonList.get(i4));
                    }
                    confirmBoxOpen("引き継ぎ用データを生成して\nサーバに保管します。\nデータ保管には通信が必要です。\n\nよろしいですか？\n\n※現在の引き継ぎ用ID：" + this.userCode + "\n　保管期間は約一ヶ月です。\n\n※現在の累計ログイン日数：" + this.userLoginNum + "日", 121, 123, true);
                } else if (pButtonSprite.getTag() == 107) {
                    this.buttonPressedSound.play();
                    for (int i5 = 0; i5 < 10; i5++) {
                        unregisterTouchArea((ITouchArea) this.buttonList.get(i5));
                    }
                    confirmBoxOpen("サーバに保管してある\n引き継ぎ用データをロードします。\nデータロードには通信が必要です。\n\nよろしいですか？\n\n※現在の引き継ぎ用ID：" + this.userCode + "\n　保管期間は約一ヶ月です。", 122, 123, true);
                } else if (pButtonSprite.getTag() == 123) {
                    this.buttonPressedSound.play();
                    for (int i6 = 0; i6 < 10; i6++) {
                        registerTouchArea((ITouchArea) this.buttonList.get(i6));
                    }
                    confirmBoxClose(true);
                    menuBottomStart();
                } else if (pButtonSprite.getTag() == 121) {
                    this.buttonPressedSound.play();
                    this.toScene = 1;
                    destroy();
                    free();
                    ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
                } else if (pButtonSprite.getTag() == 122) {
                    this.buttonPressedSound.play();
                    this.toScene = 2;
                    destroy();
                    free();
                    ResourceUtil.getInstance(getBaseActivity()).resetAllTexture();
                } else if (pButtonSprite.getTag() == 108) {
                    this.buttonPressedSound.play();
                    menuHelpClose();
                    attrTableOpen();
                } else if (pButtonSprite.getTag() == 109) {
                    this.buttonPressedSound.play();
                    menuHelpClose();
                    this.menuNameResetFlg = true;
                    confirmBoxOpen("ボックス" + this.boxSelect + "内の\n全てのコダマのニックネームを\n初期設定に戻します。\n\nよろしいですか？", 10000000, 20000000, true);
                } else {
                    this.buttonPressedSound.play();
                    this.listHead = pButtonSprite.getTag() - 100;
                    this.listPage = 1;
                    menuHelpDetailOpen();
                }
            } else if (this.menuMode == 43) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    this.pageMoveSound.play();
                    if (pButtonSprite.getTag() == 91) {
                        this.listPage--;
                        if (this.listPage < 0) {
                            this.listPage = this.listPageMax;
                        }
                    } else {
                        this.listPage++;
                        if (this.listPageMax < this.listPage) {
                            this.listPage = 0;
                        }
                    }
                    menuIconChangeClose();
                    menuIconChangeOpen();
                } else if (10000000 < pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    menuIconChangeExe(pButtonSprite.getTag() - 10000000);
                    menuIconChangeClose();
                    menuIconChangeOpen();
                }
            } else if (this.menuMode == 21) {
                if (pButtonSprite.getTag() == 10000005) {
                    this.listPage = 0;
                    menuItemClose();
                    this.buttonPressedSound.play();
                    menuShopOpen();
                } else if (10000000 <= pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    this.listHead = pButtonSprite.getTag() - 10000000;
                    this.listPage = 0;
                    menuItemClose();
                    menuItemListOpen();
                }
            } else if (this.menuMode == 22) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    menuItemListClose();
                    this.pageMoveSound.play();
                    if (pButtonSprite.getTag() == 91) {
                        this.listPage--;
                        if (this.listPage < 0) {
                            this.listPage = this.listPageMax;
                        }
                    } else {
                        this.listPage++;
                        if (this.listPageMax < this.listPage) {
                            this.listPage = 0;
                        }
                    }
                    menuItemListOpen();
                } else if (10000000 < pButtonSprite.getTag()) {
                    menuItemListClose();
                    menuItemDetailOpen(pButtonSprite.getTag() - 10000000);
                    this.buttonPressedSound.play();
                }
            } else if (this.menuMode == 23) {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    menuShopClose();
                    this.pageMoveSound.play();
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
                    menuShopOpen();
                } else if (10000000 < pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    menuShopClose();
                    menuShopConfirmOpen(pButtonSprite.getTag() - 10000000);
                }
            } else if (this.menuMode != 24) {
            } else {
                if (pButtonSprite.getTag() == 91 || pButtonSprite.getTag() == 92) {
                    menuCardChangeClose();
                    this.pageMoveSound.play();
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
                    menuCardChangeOpen();
                } else if (pButtonSprite.getTag() == 10000000) {
                    this.buttonCanceledSound.play();
                    menuCardChangeClose();
                    menuItemListOpen();
                } else if (10000000 < pButtonSprite.getTag()) {
                    this.buttonPressedSound.play();
                    menuCardChangeClose();
                    menuCardChangeConfirmOpen(pButtonSprite.getTag() - 10000000);
                }
            }
        } else if (this.userDemoNo < 4) {
            this.buttonCanceledSound.play();
        } else {
            this.buttonPressedSound.play();
            switch (this.menuMode) {
                case 1:
                    menuAreaClose();
                    allFlgReset();
                    break;
                case 2:
                    menuQuestClose();
                    allFlgReset();
                    break;
                case 3:
                    menuTowerClose();
                    allFlgReset();
                    break;
                case 4:
                    menuPhClose();
                    allFlgReset();
                    break;
                case 11:
                    allFlgReset();
                    menuUnitClose(true);
                    break;
                case 12:
                    allFlgReset();
                    menuEquipSelectClose();
                    break;
                case 13:
                    allFlgReset();
                    menuUnitItemClose();
                    break;
                case 14:
                    allFlgReset();
                    menuUnitMoveClose();
                    break;
                case 15:
                    allFlgReset();
                    menuSpellLearnClose();
                    break;
                case 21:
                    allFlgReset();
                    menuItemClose();
                    break;
                case 22:
                    allFlgReset();
                    menuItemListClose();
                    break;
                case 23:
                    allFlgReset();
                    menuShopClose();
                    break;
                case 24:
                    allFlgReset();
                    menuCardChangeClose();
                    break;
                case 31:
                    allFlgReset();
                    menuPartyClose();
                    break;
                case 32:
                    allFlgReset();
                    menuPartyChangeClose();
                    break;
                case 41:
                    allFlgReset();
                    menuOptionClose();
                    break;
                case 42:
                    allFlgReset();
                    menuHelpClose();
                    break;
                case 43:
                    allFlgReset();
                    menuIconChangeClose();
                    break;
            }
            this.menuMode = pButtonSprite.getTag();
            switch (pButtonSprite.getTag()) {
                case 2:
                    menuQuestOpen(true);
                    return;
                case 11:
                    menuUnitOpen(true);
                    return;
                case 21:
                    menuItemOpen();
                    return;
                case 31:
                    menuPartyOpen(true);
                    return;
                case 41:
                    menuOptionOpen();
                    return;
                default:
                    return;
            }
        }
    }

    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent) {
        return true;
    }

    private void topHeaderOpen() {
        this.statusBox = getBaseActivity().getResourceUtil().getSprite("item/statusBox.png");
        this.statusBox.setPosition(0.0f, 0.0f);
        attachChild(this.statusBox);
        if (this.fontCheckNo == 0) {
            this.userName = "悠姫";
        }
        this.editor.putInt("fontCheckNo", 0);
        this.editor.commit();
        this.userNameText = new Text(20.0f, 2.0f, (IFont) this.fontBlack, (CharSequence) this.userName, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.userNameText);
        this.editor.putInt("fontCheckNo", 1);
        this.editor.commit();
        this.userSpText = new Text(6.0f, 34.0f, (IFont) this.fontBlack, (CharSequence) "霊力." + this.userSp + "/" + this.userMaxSp, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.userSpText);
        if (this.userSp < this.userMaxSp) {
            this.healSpTimeText = new Text(0.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "　　　　　　　　　　　　　　　　現在日時取得中…", 128, new TextOptions(HorizontalAlign.RIGHT), getBaseActivity().getVertexBufferObjectManager());
        } else {
            this.healSpTimeText = new Text(0.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "　　　　　　　　　　　　　　　　　　　　　　　　", 128, new TextOptions(HorizontalAlign.RIGHT), getBaseActivity().getVertexBufferObjectManager());
        }
        this.healSpTimeText.setPosition((540.0f - this.healSpTimeText.getWidth()) - 6.0f, 34.0f);
        attachChild(this.healSpTimeText);
        if (this.userMaxSp < this.userSp || this.userMaxSp == 0) {
            this.userSpVar = new Rectangle(7.0f, 58.0f, 526.0f, 3.0f, getBaseActivity().getVertexBufferObjectManager());
        } else {
            this.userSpVar = new Rectangle(7.0f, 60.0f, (float) ((this.userSp * 526) / this.userMaxSp), 5.0f, getBaseActivity().getVertexBufferObjectManager());
        }
        this.userSpVar.setColor(0.573f, 0.788f, 0.965f);
        attachChild(this.userSpVar);
        this.moneyText = new Text(0.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) String.format("%,d", new Object[]{Long.valueOf(this.userMoney)}) + "銭", new TextOptions(HorizontalAlign.RIGHT), getBaseActivity().getVertexBufferObjectManager());
        this.moneyText.setPosition((540.0f - this.moneyText.getWidth()) - 6.0f, 2.0f);
        attachChild(this.moneyText);
        this.moneyImg = getBaseActivity().getResourceUtil().getSprite("item/moneyImg.png");
        this.moneyImg.setPosition(540.0f - (this.moneyText.getWidth() + 46.0f), -4.0f);
        attachChild(this.moneyImg);
    }

    private void topHeaderClose() {
        this.healSpFlg = false;
        this.healSpTimeText.detachSelf();
        this.moneyImg.detachSelf();
        this.moneyText.detachSelf();
        this.statusBox.detachSelf();
        this.userNameText.detachSelf();
        this.userSpText.detachSelf();
        this.userSpVar.detachSelf();
    }

    private void menuBottomButton() {
        for (int i = 0; i < 5; i++) {
            this.buttonBottom.add(i, getBaseActivity().getResourceUtil().getButtonSprite("button/menu" + (i + 1) + ".png", "button/menu" + (i + 1) + "_p.png"));
            ((ButtonSprite) this.buttonBottom.get(i)).setPosition((float) (i * 108), 760.0f);
            if (i == 0) {
                ((ButtonSprite) this.buttonBottom.get(i)).setTag(2);
            } else if (i == 1) {
                ((ButtonSprite) this.buttonBottom.get(i)).setTag(11);
            } else if (i == 2) {
                ((ButtonSprite) this.buttonBottom.get(i)).setTag(31);
            } else if (i == 3) {
                ((ButtonSprite) this.buttonBottom.get(i)).setTag(21);
            } else if (i == 4) {
                ((ButtonSprite) this.buttonBottom.get(i)).setTag(41);
            }
            ((ButtonSprite) this.buttonBottom.get(i)).setOnClickListener(this);
            attachChild((IEntity) this.buttonBottom.get(i));
            registerTouchArea((ITouchArea) this.buttonBottom.get(i));
        }
    }

    private void menuBottomClose() {
        for (int i = 0; i < 5; i++) {
            ((ButtonSprite) this.buttonBottom.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonBottom.get(i));
        }
    }

    /* access modifiers changed from: private */
    public void menuBottomStart() {
        for (int i = 0; i < 5; i++) {
            registerTouchArea((ITouchArea) this.buttonBottom.get(i));
            ((ButtonSprite) this.buttonBottom.get(i)).setPosition((float) (i * 108), 760.0f);
        }
    }

    private void menuBottomStop() {
        for (int i = 0; i < 5; i++) {
            unregisterTouchArea((ITouchArea) this.buttonBottom.get(i));
            ((ButtonSprite) this.buttonBottom.get(i)).setPosition((float) (i * 108), 960.0f);
        }
    }

    /* access modifiers changed from: private */
    public void confirmBoxOpen(String str, int okTag, int ngTag, boolean NgFlg) {
        String okFile;
        menuBottomStop();
        attachChild(this.informationBox);
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        String str2 = "";
        if (NgFlg) {
            okFile = "information_yes";
        } else {
            okFile = "information_ok";
        }
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/" + okFile + ".png", "button/" + okFile + "_p.png");
        if (NgFlg) {
            placeToCenterX(this.buttonOk, 650.0f);
        } else {
            placeToCenterX(this.buttonOk, 750.0f);
        }
        this.buttonOk.setTag(okTag);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        if (NgFlg) {
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
            placeToCenterX(this.buttonNg, 750.0f);
            this.buttonNg.setTag(ngTag);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
        }
    }

    private void confirmBoxClose(boolean ng) {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        if (ng) {
            this.buttonNg.detachSelf();
            unregisterTouchArea(this.buttonNg);
        }
    }

    private void confirmSnsOpen() {
        String str;
        menuBottomStop();
        attachChild(this.informationBox);
        String str2 = "";
        if (this.userSnsFlg == 0) {
            str = "Twitterで紹介ツイートをリツイート、\nまたはFacebookページの最新記事を\nシェアして下さった方に、\n「ほぼ全てのコダマカードが対象」の\n引換券をお贈り致します。\n\n本機能のご利用にはTwitterアカウント、\nまたはFacebookアカウントが必要です。\n\nSNSサイトへ移動しますか？\n\n※この画面から移動した場合のみ\n　特典が贈られます。";
        } else {
            str = "Twitterで記念ツイートを\nリツイートして下さった方に、\n「ほぼ全てのコダマカードが対象」の\n引換券をお贈り致します。\n\n本機能のご利用には\nTwitterアカウントが必要です。\n\nSNSへ移動しますか？\n\n※この画面から移動した場合のみ\n　特典が贈られます。";
        }
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        if (this.userSnsFlg == 0) {
            this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/sns_facebook.png", "button/sns_facebook_p.png");
            placeToCenterX(this.buttonEtc1, 550.0f);
            this.buttonEtc1.setTag(111);
            this.buttonEtc1.setOnClickListener(this);
            attachChild(this.buttonEtc1);
            registerTouchArea(this.buttonEtc1);
            this.buttonEtc1Flg = true;
        }
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/sns_twitter.png", "button/sns_twitter_p.png");
        placeToCenterX(this.buttonOk, 650.0f);
        this.buttonOk.setTag(110);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
        placeToCenterX(this.buttonNg, 750.0f);
        this.buttonNg.setTag(112);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
    }

    private void confirmSnsClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        if (this.buttonEtc1Flg) {
            this.buttonEtc1.detachSelf();
            unregisterTouchArea(this.buttonEtc1);
            this.buttonEtc1Flg = false;
        }
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
    }

    private void confirmSnsExe() {
        String sql;
        if (this.userSnsFlg < 2) {
            this.userSnsFlg++;
            Cursor cursor = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=90002", null);
            if (cursor.moveToNext()) {
                sql = "UPDATE user_item_t SET num=" + (cursor.getInt(0) + 1) + " WHERE item_id=90002;";
            } else {
                sql = "INSERT INTO user_item_t VALUES (90002, 1);";
            }
            this.f220db.beginTransaction();
            try {
                this.f220db.execSQL(sql);
                this.f220db.execSQL("UPDATE user_t SET user_sns_flg=" + this.userSnsFlg);
                this.f220db.setTransactionSuccessful();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                this.f220db.endTransaction();
            }
        }
    }

    private void questReset() {
        String str = "";
        this.userQuestId = 0;
        this.userQuestStatus = 1;
        this.f220db.execSQL("UPDATE user_t SET user_quest_id=0, user_quest_status=" + this.userQuestStatus + ", user_quest_battle=1");
        this.f220db.execSQL("UPDATE user_kodama_t SET faint_flg=0");
    }

    /* JADX INFO: finally extract failed */
    private void menuAreaOpen(boolean titleScroll) {
        this.menuMode = 1;
        String str = "";
        int posY = 140;
        float tmp = (float) this.userProgressArea;
        float tmpM = (float) 8;
        String sql = "SELECT DISTINCT q.area_id, q.quest_name  FROM quest_m q WHERE q.area_id<=" + this.userProgressArea;
        if (7 < this.userProgressArea) {
            sql = sql + " OR q.area_id=100001";
            tmp += 1.0f;
        }
        if (10 < this.userProgressArea) {
            sql = sql + " OR q.area_id=200001";
            tmp += 1.0f;
        }
        if (14 < this.userProgressArea) {
            sql = sql + " OR q.area_id=300001";
            tmp += 1.0f;
        }
        if (16 < this.userProgressArea) {
            sql = sql + " OR q.area_id=400001";
            tmp += 1.0f;
        }
        if (182 <= this.userProgressQuest) {
            sql = sql + " OR q.quest_id=500001";
            tmp += 1.0f;
        }
        if (273 <= this.userProgressQuest) {
            sql = sql + " OR q.quest_id=600001";
            tmp += 1.0f;
        }
        if (287 <= this.userProgressQuest) {
            sql = sql + " OR q.quest_id=700001";
            tmp += 1.0f;
        }
        if (700006 <= this.phProgressId) {
            sql = sql + " OR q.quest_id=800001";
            tmp += 1.0f;
        }
        this.listPageMax = (int) Math.ceil((double) (tmp / tmpM));
        headlineBoxOpen("エリア選択（" + this.listPage + "/" + this.listPageMax + "）", titleScroll);
        Cursor cursor = null;
        int no = 0;
        try {
            Cursor cursor2 = this.f220db.rawQuery(sql + " ORDER BY q.area_id DESC LIMIT " + ((this.listPage - 1) * 8) + ", " + 8, null);
            while (cursor2.moveToNext()) {
                int areaId = cursor2.getInt(0);
                if (areaId < 100000) {
                    str = "エリア" + areaId + " " + cursor2.getString(1);
                } else if (areaId < 500000) {
                    str = "サブエリア " + cursor2.getString(1);
                } else if (areaId == 500001) {
                    str = "EXエリア";
                } else if (areaId == 600001) {
                    str = "ポケットタワー";
                } else if (areaId == 700001) {
                    str = "PHエリア";
                } else if (areaId == 800001) {
                    str = "チャンピオンロード";
                }
                this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                ((ButtonSprite) this.buttonList.get(no)).setPosition(-500.0f, (float) posY);
                ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + areaId);
                ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(no));
                registerTouchArea((ITouchArea) this.buttonList.get(no));
                this.buttonListText[no] = new Text(-486.0f, (float) (posY + 10), (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.buttonListText[no]);
                ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) posY, (float) posY));
                this.buttonListText[no].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (posY + 10), (float) (posY + 10)));
                posY += 64;
                no++;
            }
            this.buttonNum = no;
            if (cursor2 != null) {
                cursor2.close();
            }
            arrowOpen(91, 686, false);
            arrowOpen(92, 686, true);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuAreaClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuTowerOpen(boolean titleScroll) {
        this.menuMode = 3;
        String str = "";
        Cursor cursor = null;
        String str2 = "";
        int posY = 140;
        float tmp = 0.0f;
        float tmpM = (float) 8;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT DISTINCT q.area_id FROM quest_m q WHERE 600000<q.area_id AND q.area_id<700000 ORDER BY q.area_id ASC", null);
            while (cursor2.moveToNext()) {
                tmp += 1.0f;
                this.twAreaMax = cursor2.getInt(0);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            this.listPageMax = (int) Math.ceil((double) (tmp / tmpM));
            headlineBoxOpen("ポケットタワー選択（" + this.listPage + "/" + this.listPageMax + "）", titleScroll);
            int no = 0;
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT DISTINCT q.area_id, q.quest_name FROM quest_m q WHERE 600000<q.area_id AND q.area_id<700000 ORDER BY q.area_id ASC LIMIT " + ((this.listPage - 1) * 8) + ", " + 8, null);
                while (cursor3.moveToNext()) {
                    int areaId = cursor3.getInt(0);
                    String str3 = "タワー" + (cursor3.getInt(0) - 600000) + " " + cursor3.getString(1);
                    this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                    ((ButtonSprite) this.buttonList.get(no)).setPosition(-500.0f, (float) posY);
                    ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + areaId);
                    ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(no));
                    registerTouchArea((ITouchArea) this.buttonList.get(no));
                    this.buttonListText[no] = new Text(-486.0f, (float) (posY + 10), (IFont) this.fontBlack, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    attachChild(this.buttonListText[no]);
                    ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) posY, (float) posY));
                    this.buttonListText[no].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (posY + 10), (float) (posY + 10)));
                    posY += 64;
                    no++;
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                this.buttonNum = no;
                arrowOpen(91, 686, false);
                arrowOpen(92, 686, true);
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

    private void menuTowerClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuPhOpen(boolean titleScroll) {
        this.menuMode = 4;
        String str = "";
        Cursor cursor = null;
        String str2 = "";
        int posY = 140;
        float tmp = 0.0f;
        float tmpM = (float) 8;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT DISTINCT q.area_id FROM quest_m q WHERE 700000 < q.area_id AND q.area_id <= " + this.phProgressId + " ORDER BY q.area_id ASC", null);
            while (cursor2.moveToNext()) {
                tmp += 1.0f;
                this.phAreaMax = cursor2.getInt(0);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            this.listPageMax = (int) Math.ceil((double) (tmp / tmpM));
            headlineBoxOpen("PHエリア（" + this.listPage + "/" + this.listPageMax + "）", titleScroll);
            int no = 0;
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT DISTINCT q.area_id, q.quest_name FROM quest_m q WHERE 700000 < q.area_id AND q.area_id <= " + this.phProgressId + " ORDER BY q.area_id ASC LIMIT " + ((this.listPage - 1) * 8) + ", " + 8, null);
                while (cursor3.moveToNext()) {
                    int areaId = cursor3.getInt(0);
                    String str3 = "PHエリア" + (cursor3.getInt(0) - 700000) + " " + cursor3.getString(1);
                    this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                    ((ButtonSprite) this.buttonList.get(no)).setPosition(-500.0f, (float) posY);
                    ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + areaId);
                    ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(no));
                    registerTouchArea((ITouchArea) this.buttonList.get(no));
                    this.buttonListText[no] = new Text(-486.0f, (float) (posY + 10), (IFont) this.fontBlack, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    attachChild(this.buttonListText[no]);
                    ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) posY, (float) posY));
                    this.buttonListText[no].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (posY + 10), (float) (posY + 10)));
                    posY += 64;
                    no++;
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                this.buttonNum = no;
                arrowOpen(91, 686, false);
                arrowOpen(92, 686, true);
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

    private void menuPhClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuQuestOpen(boolean titleScroll) {
        String sql;
        String sql2;
        String sql3;
        String sql4;
        String str;
        String str2;
        this.menuMode = 2;
        Cursor cursor = null;
        String questName = "エリア名";
        int posY = 186;
        headlineBoxOpen("クエスト選択", titleScroll);
        if (2 <= this.userQuestStatus) {
            questName = "クエスト再開";
            for (int no = 0; no < 2; no++) {
                int posY2 = posY + 36;
                this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                ((ButtonSprite) this.buttonList.get(no)).setPosition(-500.0f, (float) posY2);
                ((ButtonSprite) this.buttonList.get(no)).setTag(20000000 + no + 2);
                ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(no));
                registerTouchArea((ITouchArea) this.buttonList.get(no));
                if (no == 0) {
                    str2 = "クエスト再開（霊力は消費しません）";
                } else {
                    str2 = "クエスト挑戦を中止する";
                }
                this.buttonListText[no] = new Text(-486.0f, (float) (posY2 + 10), (IFont) this.fontBlack, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.buttonListText[no]);
                ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) posY2, (float) posY2));
                this.buttonListText[no].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (posY2 + 10), (float) (posY2 + 10)));
                posY = posY2 + 64;
            }
        } else {
            if (1000000 < this.areaSelect) {
                this.areaSelect = 1000006;
            }
            int twProgressQuest = 0;
            if (600000 < this.areaSelect && this.areaSelect < 700000) {
                try {
                    cursor = this.f220db.rawQuery("SELECT u.quest_id FROM user_progress_t u LEFT OUTER JOIN quest_m q ON u.quest_id=q.quest_id WHERE q.area_id=" + this.areaSelect + " ORDER BY u.quest_id DESC", null);
                    if (cursor.moveToFirst()) {
                        twProgressQuest = cursor.getInt(0);
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
            int phProgressQuest = 0;
            if (700000 < this.areaSelect && this.areaSelect < 800000) {
                try {
                    cursor = this.f220db.rawQuery("SELECT u.quest_id FROM user_progress_t u LEFT OUTER JOIN quest_m q ON u.quest_id=q.quest_id WHERE q.area_id=" + this.areaSelect + " ORDER BY u.quest_id DESC", null);
                    if (cursor.moveToFirst()) {
                        phProgressQuest = cursor.getInt(0);
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
            }
            int no2 = 0;
            String sql5 = "SELECT q.quest_id, q.quest_name, q.need_sp, q.map_lv, p.diff, q.stage_id FROM quest_m q LEFT OUTER JOIN user_progress_t p ON q.quest_id=p.quest_id WHERE q.area_id=" + this.areaSelect + " AND ";
            if (this.areaSelect < 100000) {
                sql = (sql5 + "q.quest_id <= " + (this.userProgressQuest + 1)) + " ORDER BY q.quest_id ASC";
            } else if (this.areaSelect == 500001) {
                int tmp = 500001;
                if (this.exProgressId != 500034 || this.hallFlg) {
                    if (500001 < this.exProgressId - 6) {
                        tmp = this.exProgressId - 6;
                    }
                    sql4 = sql5 + tmp + "<=q.quest_id AND q.quest_id<=" + this.exProgressId;
                } else {
                    sql4 = sql5 + "500027<=q.quest_id AND q.quest_id<=510000";
                }
                sql = sql4 + " ORDER BY q.quest_id DESC";
            } else if (600000 < this.areaSelect && this.areaSelect < 700000) {
                if (twProgressQuest == 0) {
                    sql3 = sql5 + "q.stage_id=1";
                } else {
                    sql3 = sql5 + "q.quest_id <= " + (twProgressQuest + 1);
                }
                sql = sql3 + " ORDER BY q.quest_id ASC";
            } else if (700000 < this.areaSelect && this.areaSelect < 800000) {
                if (phProgressQuest == 0) {
                    sql2 = sql5 + "q.stage_id=1";
                } else {
                    sql2 = sql5 + "q.quest_id <= " + (phProgressQuest + 1);
                }
                sql = sql2 + " ORDER BY q.quest_id ASC";
            } else if (this.areaSelect == 800001) {
                sql = sql5 + "800000 < q.quest_id AND q.quest_id <= " + this.rdProgressId + " ORDER BY q.quest_id DESC LIMIT " + (this.listPageRoad * 8) + ", 8";
            } else {
                int tmp2 = 100001;
                if (this.areaSelect == 100001) {
                    tmp2 = 100003;
                    if (32 < this.userProgressArea) {
                        tmp2 = 100008;
                    } else if (22 < this.userProgressArea) {
                        tmp2 = 100004;
                    }
                } else if (this.areaSelect == 200001) {
                    tmp2 = 200001;
                    if (700003 <= this.phProgressId) {
                        tmp2 = 200003;
                    } else if (26 < this.userProgressArea) {
                        tmp2 = 200002;
                    }
                } else if (this.areaSelect == 300001) {
                    tmp2 = 300003;
                    if (39 < this.userProgressArea) {
                        tmp2 = 300005;
                    }
                } else if (this.areaSelect == 400001) {
                    tmp2 = 400003;
                }
                sql = (sql5 + "q.quest_id <= " + tmp2) + " ORDER BY q.quest_id ASC";
            }
            try {
                cursor = this.f220db.rawQuery(sql, null);
                while (cursor.moveToNext()) {
                    int questId = cursor.getInt(0);
                    if (this.areaSelect < 100000) {
                        questName = "エリア" + this.areaSelect + " " + cursor.getString(1);
                    } else if (this.areaSelect < 500000) {
                        questName = "サブエリア " + cursor.getString(1);
                    } else if (this.areaSelect == 500001) {
                        questName = "EXエリア";
                    } else if (600000 < this.areaSelect && this.areaSelect < 700000) {
                        questName = "ポケットタワー" + (this.areaSelect - 600000) + " " + cursor.getString(1);
                    } else if (700000 < this.areaSelect && this.areaSelect < 800000) {
                        questName = "PHエリア" + (this.areaSelect - 700000) + " " + cursor.getString(1);
                    } else if (this.areaSelect == 800001) {
                        questName = "チャンピオンロード（" + (this.listPageRoad + 1) + "/" + (this.listPageRoadMax + 1) + "）";
                    }
                    int needSp = cursor.getInt(2);
                    int mapLv = cursor.getInt(3);
                    if (mapLv <= 0) {
                        mapLv += this.oukaLv;
                    }
                    this.buttonList.add(no2, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                    ((ButtonSprite) this.buttonList.get(no2)).setPosition(-500.0f, (float) posY);
                    ((ButtonSprite) this.buttonList.get(no2)).setTag(10000000 + questId);
                    ((ButtonSprite) this.buttonList.get(no2)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(no2));
                    registerTouchArea((ITouchArea) this.buttonList.get(no2));
                    String str3 = "";
                    if (this.areaSelect == 500001) {
                        if (cursor.getInt(0) < this.exProgressId) {
                            str3 = "★★★";
                        }
                        if (cursor.getInt(0) == 510000) {
                            str = str3 + "ボーナスステージ （Lv" + mapLv + "/消費霊力" + needSp + "）";
                        } else {
                            str = str3 + "ステージ" + (cursor.getInt(0) - 500000) + " （Lv" + mapLv + "/消費霊力" + needSp + "）";
                        }
                    } else {
                        if (cursor.getInt(4) == 1) {
                            str3 = "☆";
                        } else if (cursor.getInt(4) == 2) {
                            str3 = "★";
                        } else if (cursor.getInt(4) == 3) {
                            str3 = "★★";
                        } else if (cursor.getInt(4) == 4) {
                            str3 = "★★★";
                        }
                        if (this.areaSelect == 800001) {
                            str = str3 + "ステージ" + cursor.getInt(5) + " （Lv" + mapLv + "/消費霊力" + needSp + "）";
                        } else {
                            str = str3 + "ステージ" + (no2 + 1) + " （Lv" + mapLv + "/消費霊力" + needSp + "）";
                        }
                    }
                    this.buttonListText[no2] = new Text(-486.0f, (float) (posY + 10), (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    attachChild(this.buttonListText[no2]);
                    ((ButtonSprite) this.buttonList.get(no2)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) posY, (float) posY));
                    this.buttonListText[no2].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (posY + 10), (float) (posY + 10)));
                    posY += 60;
                    no2++;
                }
                if (cursor != null) {
                    cursor.close();
                }
                this.buttonNum = no2;
            } catch (Throwable th3) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th3;
            }
        }
        titleBoxOpen(questName, true);
        if (2 <= this.userQuestStatus) {
            this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_area_ng.png", "button/button_area_ng.png");
            this.buttonEtc1.setTag(99999997);
        } else {
            this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_area.png", "button/button_area_p.png");
            this.buttonEtc1.setTag(20000000);
        }
        placeToCenterX(this.buttonEtc1, 678.0f);
        this.buttonEtc1.setOnClickListener(this);
        attachChild(this.buttonEtc1);
        registerTouchArea(this.buttonEtc1);
        if (2 <= this.userQuestStatus) {
            arrowOpen(99999997, 686, false);
            arrowOpen(99999997, 686, true);
            return;
        }
        arrowOpen(91, 686, false);
        arrowOpen(92, 686, true);
    }

    private void menuQuestClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        this.titleBox.detachSelf();
        this.titleBoxText.detachSelf();
        this.buttonEtc1.detachSelf();
        unregisterTouchArea(this.buttonEtc1);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        if (2 <= this.userQuestStatus) {
            this.buttonNum = 2;
        }
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuQuestConfirmOpen() {
        String str;
        menuQuestClose();
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT q.quest_id, q.area_id, q.stage_id, q.quest_name, q.need_sp FROM quest_m q WHERE q.quest_id = " + (this.selectQuestId - 10000000), null);
            if (cursor.moveToFirst()) {
                int questId = cursor.getInt(0);
                int areaId = cursor.getInt(1);
                int stage = cursor.getInt(2);
                String questName = cursor.getString(3);
                int needSp = cursor.getInt(4);
                int staget = stage;
                String onText = "イージー";
                if (this.userDifficult == 2) {
                    onText = "ノーマル";
                } else if (this.userDifficult == 3) {
                    onText = "ハード";
                } else if (this.userDifficult == 4) {
                    onText = "ルナティック";
                }
                String str2 = "■";
                if (areaId == 500001) {
                    str2 = str2 + "EXエリア ";
                    staget = questId - 500000;
                } else if (800000 < areaId) {
                    str2 = str2 + "チャンピオンロード";
                } else if (700000 < areaId) {
                    str2 = str2 + "PHエリア" + (areaId - 700000);
                } else if (600000 < areaId) {
                    str2 = str2 + "ポケットタワー" + (areaId - 600000);
                } else if (areaId < 100000) {
                    str2 = str2 + "エリア" + areaId;
                }
                if (questId == 510000) {
                    str = str2 + " " + questName + "\n\n霊力を" + needSp + "消費して、\nステージ??に挑戦します。\nよろしいですか？\n\n[難易度：" + onText + "]";
                } else if (areaId == 800001) {
                    str = str2 + "\n\n霊力を" + needSp + "消費して、\nステージ" + staget + "に挑戦します。\nよろしいですか？\n\n[難易度：" + onText + "]";
                } else {
                    str = str2 + " " + questName + "\n\n霊力を" + needSp + "消費して、\nステージ" + staget + "に挑戦します。\nよろしいですか？\n\n[難易度：" + onText + "]";
                }
                if (1000000 < questId) {
                    int lastNum = 0;
                    if (107 - this.userChallengeNum > 0) {
                        lastNum = 107 - this.userChallengeNum;
                    }
                    str = str + "\n\n【期間限定エリア注意事項】\n・このクエストは1回しか挑戦できません\n　（残り挑戦回数：" + lastNum + "回）。\n・本クエスト挑戦中に倒れたコダマは、\n　クエスト挑戦が終わるまでの間\n　戦闘で使用できなくなります。\n・このクエストに挑戦している間は、\n　コダマの装備を変更できなくなります。\n・難易度ボーナスを獲得できませんが、\n　高難易度だと報酬が豪華になります。";
                } else if (questId == 510000) {
                    str = (str + "\n\n【EXエリア「異空穴」注意事項】") + "\n①本クエスト挑戦中に倒れたコダマは、\n　クエスト挑戦が終わるまでの間\n　戦闘で使用できなくなります。\n②このクエストに挑戦している間は、\n　コダマの装備を変更できなくなります。\n③クリア時に報酬アイテムを獲得できます。\n④難易度ボーナスを獲得できませんが、\n　高難易度だと報酬が豪華になります。\n⑤1日1回だけ挑戦できます。";
                } else if (500000 < questId && questId < 600000) {
                    str = (str + "\n\n【EXエリア注意事項】") + "\n①本クエスト挑戦中に倒れたコダマは、\n　クエスト挑戦が終わるまでの間\n　戦闘で使用できなくなります。\n②このクエストに挑戦している間は、\n　コダマの装備を変更できなくなります。\n③初クリア時のみ、ボス撃破の\n　報酬アイテムを獲得できます。\n④難易度ボーナスを獲得できませんが、\n　高難易度だと報酬が豪華になります。";
                } else if (600000 < questId && questId < 700000) {
                    str = (str + "\n\n【ポケットタワー注意事項】") + "\n①本クエスト挑戦中に倒れたコダマは、\n　クエスト挑戦が終わるまでの間\n　戦闘で使用できなくなります。\n②このクエストに挑戦している間は、\n　コダマの装備を変更できなくなります。\n③初クリア時のみ、ボス撃破の\n　報酬アイテムを獲得できます。\n④難易度ボーナスを獲得できませんが、\n　高難易度だと報酬が豪華になります。\n⑤低難易度でクリアした後でも、\n　高難易度でクリアし直すと\n　不足分の報酬は獲得できます。";
                } else if (700000 < questId && questId < 800000) {
                    str = (str + "\n\n【PHエリア注意事項】") + "\n①初クリア時のみ、ボス撃破の\n　報酬アイテムを獲得できます。\n②難易度ボーナスを獲得できませんが、\n　高難易度だと報酬が豪華になります。\n③低難易度でクリアした後でも、\n　高難易度でクリアし直すと\n　不足分の報酬は獲得できます。";
                } else if (800000 < questId && questId < 900000) {
                    str = (str + "\n\n【チャンピオンロード注意事項】") + "\n①本クエスト挑戦中に倒れたコダマは、\n　クエスト挑戦が終わるまでの間\n　戦闘で使用できなくなります。\n②初クリア時のみ、ボス撃破の\n　報酬アイテムを獲得できます。\n③難易度ボーナスを獲得できませんが、\n　高難易度だと報酬が豪華になります。\n④低難易度でクリアした後でも、\n　高難易度でクリアし直すと\n　不足分の報酬は獲得できます。\n⑤本クエストでは経験値を獲得できません。";
                } else if (questId == 200002 || questId == 200003) {
                    str = str + "\n\n※このクエストでは\n　獲得経験値が５倍になります。";
                }
                if (questId == 100001) {
                    str = str + "\n\n[ドロップ：ハードビールetc]";
                } else if (questId == 100002) {
                    str = str + "\n\n[ドロップ：ハードビールetc]";
                } else if (questId == 100003) {
                    str = str + "\n\n[ドロップ：ルナティックビール]";
                } else if (questId == 100004) {
                    str = str + "\n\n[ドロップ：エクストラビール]";
                } else if (questId == 100005) {
                    str = str + "\n\n[ドロップ：ルナティックビールetc]";
                } else if (questId == 100006) {
                    str = str + "\n\n[ドロップ：ファンタズムビール]";
                } else if (questId == 100007) {
                    str = str + "\n\n[ドロップ：ミレニアムビール]";
                } else if (questId == 100008) {
                    str = str + "\n\n[ドロップ：ファンタズムビール]";
                } else if (questId == 200003) {
                    str = str + "\n\n[ドロップ：禁呪の書]";
                } else if (questId == 300001) {
                    str = str + "\n\n[ドロップ：浮遊石etc]";
                } else if (questId == 300002) {
                    str = str + "\n\n[ドロップ：月天石etc]";
                } else if (questId == 300003) {
                    str = str + "\n\n[ドロップ：日緋色金]";
                } else if (questId == 300004) {
                    str = str + "\n\n[ドロップ：日緋色金etc]";
                } else if (questId == 300005) {
                    str = str + "\n\n[ドロップ：博麗の涙]";
                }
                confirmBoxOpen(str, 10000000 + questId, 10000000, true);
                if (this.userDemoNo < 24) {
                    this.buttonArrowL = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowL_p.png", "button/arrowL_p.png");
                    this.buttonArrowR = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowR_p.png", "button/arrowR_p.png");
                    this.buttonArrowL.setTag(99999997);
                    this.buttonArrowR.setTag(99999997);
                } else {
                    this.buttonArrowL = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowL.png", "button/arrowL_p.png");
                    this.buttonArrowR = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowR.png", "button/arrowR_p.png");
                    this.buttonArrowL.setTag(91);
                    this.buttonArrowR.setTag(92);
                }
                this.buttonArrowL.setPosition(340.0f, 192.0f);
                this.buttonArrowR.setPosition(404.0f, 192.0f);
                this.buttonArrowL.setOnClickListener(this);
                this.buttonArrowR.setOnClickListener(this);
                attachChild(this.buttonArrowL);
                attachChild(this.buttonArrowR);
                registerTouchArea(this.buttonArrowL);
                registerTouchArea(this.buttonArrowR);
                this.menuQuestConfirmFlg = true;
            } else {
                popAlert("データ取得エラー", "\n\nmenuQuestConfirmOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
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

    private void menuQuestConfirmClose() {
        this.menuQuestConfirmFlg = false;
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        confirmBoxClose(true);
        menuQuestOpen(false);
        menuBottomStart();
    }

    private void menuQuestStart(int tag) {
        int pt;
        String str;
        Cursor cursor = this.f220db.rawQuery("SELECT q.quest_id, q.need_sp FROM quest_m q WHERE q.quest_id = " + tag, null);
        if (cursor.moveToFirst()) {
            int questId = cursor.getInt(0);
            int needSp = cursor.getInt(1);
            confirmBoxClose(true);
            if (this.userSp < needSp) {
                this.buttonPressedSound.play();
                confirmBoxOpen("霊力が不足しています。\n\n霊力は時間経過または\n霊酒を使用することで\n回復できます。", 10000000, 10000000, false);
            } else if (1000000 >= questId || 107 - this.userChallengeNum > 0) {
                this.StartPressedSound.play();
                this.editor.putInt("areaSelect", this.areaSelect);
                this.editor.commit();
                if (1000000 < questId) {
                    this.userChallengeNum++;
                }
                if (800000 < questId && questId < 900000) {
                    String onText = "イージー";
                    if (this.userDifficult == 2) {
                        onText = "ノーマル";
                    } else if (this.userDifficult == 3) {
                        onText = "ハード";
                    } else if (this.userDifficult == 4) {
                        onText = "ルナティック";
                    }
                    if (this.f220db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=3", null).moveToFirst()) {
                        this.f220db.execSQL("UPDATE user_battle_t SET data='ステージ" + (questId - 800000) + "[" + onText + "]勝利コダマ一覧\n' WHERE user_battle_id=3");
                    } else {
                        this.f220db.execSQL("INSERT INTO user_battle_t (user_battle_id, data) VALUES(3, 'ステージ" + (questId - 800000) + "[" + onText + "]勝利コダマ一覧\n')");
                    }
                    if (this.f220db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=4", null).moveToFirst()) {
                        this.f220db.execSQL("UPDATE user_battle_t SET data='ステージ" + (questId - 800000) + "[" + onText + "]勝利コダマ一覧\n' WHERE user_battle_id=4");
                    } else {
                        this.f220db.execSQL("INSERT INTO user_battle_t (user_battle_id, data) VALUES(4, 'ステージ" + (questId - 800000) + "[" + onText + "]勝利コダマ一覧\n')");
                    }
                    cursor = this.f220db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=5", null);
                    if (cursor.moveToFirst()) {
                        this.f220db.execSQL("UPDATE user_battle_t SET data='ステージ" + (questId - 800000) + "[" + onText + "]気絶コダマ一覧\n' WHERE user_battle_id=5");
                    } else {
                        this.f220db.execSQL("INSERT INTO user_battle_t (user_battle_id, data) VALUES(5, 'ステージ" + (questId - 800000) + "[" + onText + "]気絶コダマ一覧\n')");
                    }
                }
                this.f220db.beginTransaction();
                try {
                    int preSp = this.userSp;
                    this.userSp -= needSp;
                    String sql = "UPDATE user_t SET user_sp=" + this.userSp;
                    if (this.userMaxSp <= preSp && this.timeStampCheckFlg && this.userSp < this.userMaxSp) {
                        sql = sql + ", user_sp_restore_time=" + (this.startTime + ((long) this.passageTime));
                    }
                    String sql2 = sql + ", user_quest_id=" + questId + ", user_quest_status=2, user_quest_battle=1, user_last=10";
                    if (100 <= this.userChallengeNum) {
                        sql2 = sql2 + ", user_demo_no=" + this.userChallengeNum;
                    }
                    this.f220db.execSQL(sql2);
                    this.f220db.execSQL("UPDATE enemy_m SET quest_id=0 WHERE enemy_id=100000");
                    this.f220db.execSQL("UPDATE user_battle_t SET data=\"NULL\" WHERE user_battle_id=2");
                    if (questId == 510000) {
                        this.f220db.execSQL("INSERT INTO user_item_t VALUES(500002, 1);");
                    }
                    this.f220db.setTransactionSuccessful();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    this.f220db.endTransaction();
                }
                if (this.bgm != null) {
                    this.bgm.stop();
                }
                topHeaderClose();
                menuBottomClose();
                this.buttonArrowL.detachSelf();
                unregisterTouchArea(this.buttonArrowL);
                this.buttonArrowR.detachSelf();
                unregisterTouchArea(this.buttonArrowR);
                this.bgImg.registerEntityModifier(new FadeOutModifier(0.5f));
                String str2 = "■Tips\n\n";
                float time = 2.0f;
                if (questId < 7) {
                    pt = questId - 1;
                    time = 4.0f;
                } else {
                    pt = this.rnd.nextInt(14);
                }
                if (pt == 0) {
                    str = str2 + "▽戦闘後に自動で回復\n\nＨＰとＶＰは、戦闘後に全回復します。\n\n戦闘不能も回復するので、\n後のことは考えなくても大丈夫。";
                } else if (pt == 1) {
                    str = str2 + "▽コダマが疲れたら交代\n\nＶＰが足りなくなると、\nコダマはスペルを\n使えなくなってしまいます。\n\nＶＰは控えにいると\n徐々に回復していきます。";
                } else if (pt == 2) {
                    str = str2 + "▽難易度ボーナス\n\n高難易度でステージクリアすると、\n「難易度ボーナス」を獲得できます。\n\n難易度ボーナスが\n一定値に達するごとに、\n報酬アイテムをもらえます。";
                } else if (pt == 3) {
                    str = str2 + "▽レベル差と獲得経験値\n\nマップレベルと比べて\nレベルの低いコダマは、\n経験値を多めに獲得できます。\n\n逆にレベルが高いコダマは、\n獲得経験値が少なくなります。";
                } else if (pt == 4) {
                    str = str2 + "▽属性一致スペル\n\n自分が持つ属性と\n同じ属性のスペルを使うと、\nスペル威力が1.5倍になります。";
                } else if (pt == 5) {
                    str = str2 + "▽ビールは偉大\n\nBPを消費して\nステータスボーナスに割り振ると、\nコダマの能力が強化されます。\n\nBPはイージービールなどの\nアイテムで上げることができます。";
                } else if (pt == 6) {
                    str = str2 + "▽スペルを覚えさせよう\n\nコダマのスペル確認画面から\nスペルを習得させることができます。\n\nただし、スペルの習得には\n銭が必要です。";
                } else if (pt == 7) {
                    str = str2 + "▽能力変化スペルは強力\n\nスペルの追加効果による\n能力の増加や減少は、\nバトル終了まで永続します。";
                } else if (pt == 8) {
                    str = str2 + "▽難しかったら難易度を下げよう\n\n「その他」メニューで難易度を下げると、\n敵のレベルが下がります。\n\nどうしても勝てない時には\n難易度を下げてみてください。";
                } else if (pt == 9) {
                    str = str2 + "▽コンティニューを活用\n\nバトルで負けたとしても、\n１回のクエスト挑戦につき\n10回までコンティニューできます。";
                } else if (pt == 10) {
                    str = str2 + "▽クエスト/バトルは再開可能\n\nクエスト挑戦中やバトル中に\nアプリを終了させても、\n同じところから再開できます。";
                } else if (pt == 11) {
                    str = str2 + "▽アプリは削除しないで\n\nアプリを削除すると、\nセーブデータも消えてしまうので\n注意してください。";
                } else {
                    str = str2 + "▽こまめにバックアップを\n\nデータをバックアップしておけば、\nスマートフォンやアプリが破損しても\nデータを復旧できるかも。";
                }
                attachChild(this.informationBox);
                this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.infoText);
                if (this.userDemoNo < 4) {
                    this.cartainBottom.detachSelf();
                }
                this.commonHandler = new TimerHandler(time, new ITimerCallback() {
                    public void onTimePassed(TimerHandler pTimerHandler) {
                        MenuScene.this.informationBox.detachSelf();
                        MenuScene.this.infoText.detachSelf();
                        MenuScene.this.destroy();
                        MenuScene.this.free();
                        ResourceUtil.getInstance(MenuScene.this.getBaseActivity()).resetAllTexture();
                    }
                });
                registerUpdateHandler(this.commonHandler);
            } else {
                this.buttonPressedSound.play();
                confirmBoxOpen("挑戦回数制限を超過しています。", 10000000, 10000000, false);
            }
        }
        if (cursor != null) {
            cursor.close();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitOpen(boolean head) {
        this.menuMode = 11;
        Cursor cursor = null;
        String str = "sort_no ASC";
        int posX = 30;
        int posY = 128;
        if (head) {
            headlineBoxOpen("コダマメニュー（ボックス" + this.boxSelect + "／" + 20 + "）", true);
        }
        for (int i = 0; i < 30; i++) {
            this.unitSort[i] = 0;
            this.unitSortId[i] = 0;
        }
        String sort = sortGetString(1);
        this.bitmapFontS = new BitmapFont(getBaseActivity().getTextureManager(), getBaseActivity().getAssets(), "font/numS.fnt");
        this.bitmapFontS.load();
        int no = 0;
        try {
            cursor = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.lv, m.kodama_attr1, m.kodama_attr2, u.protect_flg FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE " + (this.boxSelect * 100) + "<u.sort_no AND u.sort_no<" + ((this.boxSelect + 1) * 100) + " ORDER BY " + sort, null);
            while (cursor.moveToNext()) {
                int userKodamaId = cursor.getInt(0);
                int kodamaId = cursor.getInt(2);
                String fileName = "kodama_s/" + kodamaId + ".png";
                int kodamaLv = cursor.getInt(3);
                int kodamaAttr1 = cursor.getInt(4);
                int kodamaAttr2 = cursor.getInt(5);
                int protectFlg = cursor.getInt(6);
                if (no >= 0 && no < 30) {
                    this.unitSort[no] = userKodamaId;
                    this.unitSortId[no] = kodamaId;
                }
                this.shadowList.add(no, getBaseActivity().getResourceUtil().getSprite("item/shadow.png"));
                ((Sprite) this.shadowList.get(no)).setPosition((float) (posX - 4), (float) (posY + 46));
                attachChild((IEntity) this.shadowList.get(no));
                this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite(fileName, fileName));
                ((ButtonSprite) this.buttonList.get(no)).setPosition((float) posX, (float) posY);
                if (kodamaId == 0) {
                    ((ButtonSprite) this.buttonList.get(no)).setTag(10000000);
                } else {
                    ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + userKodamaId);
                }
                ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(no));
                registerTouchArea((ITouchArea) this.buttonList.get(no));
                this.attrList1.add(no, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr1 + ".png"));
                ((Sprite) this.attrList1.get(no)).setPosition((float) posX, (float) posY);
                attachChild((IEntity) this.attrList1.get(no));
                this.attrList2.add(no, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr2 + ".png"));
                ((Sprite) this.attrList2.get(no)).setPosition((float) posX, (float) (posY + 24));
                attachChild((IEntity) this.attrList2.get(no));
                String str2 = String.valueOf(kodamaLv);
                if (kodamaId == 0) {
                    str2 = "";
                }
                this.statusText[no] = new Text((float) posX, 0.0f, (IFont) this.bitmapFontS, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                this.statusText[no].setPosition(this.statusText[no].getX() + ((64.0f - this.statusText[no].getWidth()) / 2.0f), (float) (posY + 44));
                attachChild(this.statusText[no]);
                this.unitProtectIcon.add(no, getBaseActivity().getResourceUtil().getSprite("item/protect_s.png"));
                ((Sprite) this.unitProtectIcon.get(no)).setPosition((float) (posX + 51), (float) posY);
                if (protectFlg == 1) {
                    ((Sprite) this.unitProtectIcon.get(no)).setAlpha(1.0f);
                } else {
                    ((Sprite) this.unitProtectIcon.get(no)).setAlpha(0.0f);
                }
                attachChild((IEntity) this.unitProtectIcon.get(no));
                posX += 80;
                if (460 <= posX) {
                    posX = 30;
                    posY += 80;
                }
                no++;
            }
            if (cursor != null) {
                cursor.close();
            }
            this.buttonNum = no;
            sortButtonOpen(1);
            this.buttonArrowR = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowR.png", "button/arrowR_p.png");
            this.buttonArrowR.setPosition(412.0f, 540.0f);
            this.buttonArrowR.setTag(92);
            this.buttonArrowR.setOnClickListener(this);
            attachChild(this.buttonArrowR);
            registerTouchArea(this.buttonArrowR);
            this.buttonArrowL = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowL.png", "button/arrowL_p.png");
            this.buttonArrowL.setPosition(80.0f, 540.0f);
            this.buttonArrowL.setTag(91);
            this.buttonArrowL.setOnClickListener(this);
            attachChild(this.buttonArrowL);
            registerTouchArea(this.buttonArrowL);
            this.buttonArrowRR = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowRw.png", "button/arrowRw_p.png");
            this.buttonArrowRR.setPosition(476.0f, 540.0f);
            this.buttonArrowRR.setTag(94);
            this.buttonArrowRR.setOnClickListener(this);
            attachChild(this.buttonArrowRR);
            registerTouchArea(this.buttonArrowRR);
            this.buttonArrowLL = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowLw.png", "button/arrowLw_p.png");
            this.buttonArrowLL.setPosition(16.0f, 540.0f);
            this.buttonArrowLL.setTag(93);
            this.buttonArrowLL.setOnClickListener(this);
            attachChild(this.buttonArrowLL);
            registerTouchArea(this.buttonArrowLL);
            this.dArrowFlg = true;
            if (this.sortUnitList == 1) {
                this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/unit_move.png", "button/unit_move_p.png");
                this.buttonEtc1.setTag(30000000);
            } else {
                this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/unit_move_ng.png", "button/unit_move_ng.png");
                this.buttonEtc1.setTag(99999997);
            }
            if (28 <= this.userProgressQuest) {
                this.buttonEtc1.setPosition(65.0f, 604.0f);
            } else {
                this.buttonEtc1.setPosition(170.0f, 604.0f);
            }
            this.buttonEtc1.setOnClickListener(this);
            attachChild(this.buttonEtc1);
            registerTouchArea(this.buttonEtc1);
            this.buttonEtc2 = getBaseActivity().getResourceUtil().getButtonSprite("button/equip_list.png", "button/equip_list_p.png");
            this.buttonEtc2.setTag(20000000);
            this.buttonEtc2.setPosition(65.0f, 678.0f);
            this.buttonEtc2.setOnClickListener(this);
            attachChild(this.buttonEtc2);
            registerTouchArea(this.buttonEtc2);
            this.buttonEtc4 = getBaseActivity().getResourceUtil().getButtonSprite("button/bp_list.png", "button/bp_list_p.png");
            this.buttonEtc4.setTag(50000000);
            this.buttonEtc4.setPosition(275.0f, 678.0f);
            this.buttonEtc4.setOnClickListener(this);
            attachChild(this.buttonEtc4);
            registerTouchArea(this.buttonEtc4);
            this.buttonEtc4Flg = true;
            if (28 <= this.userProgressQuest) {
                this.buttonEtc3 = getBaseActivity().getResourceUtil().getButtonSprite("button/evo_check.png", "button/evo_check_p.png");
                this.buttonEtc3.setTag(40000000);
                this.buttonEtc3.setPosition(275.0f, 604.0f);
                this.buttonEtc3.setOnClickListener(this);
                attachChild(this.buttonEtc3);
                registerTouchArea(this.buttonEtc3);
                this.buttonEtc3Flg = true;
            }
            menuBottomStart();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuUnitClose(boolean head) {
        if (head) {
            this.headlineBox.detachSelf();
            this.headlineBoxText.detachSelf();
        }
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            ((Sprite) this.shadowList.get(i)).detachSelf();
            ((Sprite) this.attrList1.get(i)).detachSelf();
            ((Sprite) this.attrList2.get(i)).detachSelf();
            ((Sprite) this.unitProtectIcon.get(i)).detachSelf();
            this.statusText[i].detachSelf();
        }
        this.buttonEtc1.detachSelf();
        unregisterTouchArea(this.buttonEtc1);
        this.buttonEtc2.detachSelf();
        unregisterTouchArea(this.buttonEtc2);
        this.buttonEtc4.detachSelf();
        unregisterTouchArea(this.buttonEtc4);
        if (28 <= this.userProgressQuest) {
            this.buttonEtc3.detachSelf();
            unregisterTouchArea(this.buttonEtc3);
        }
        this.buttonSort.detachSelf();
        unregisterTouchArea(this.buttonSort);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        this.buttonArrowLL.detachSelf();
        unregisterTouchArea(this.buttonArrowLL);
        this.buttonArrowRR.detachSelf();
        unregisterTouchArea(this.buttonArrowRR);
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitMoveOpen() {
        this.menuMode = 14;
        Cursor cursor = null;
        String str = "sort_no ASC";
        int posX = 30;
        int posY = 128;
        if (this.targetUserKodamaId == 0) {
            headlineBoxOpen("移動するコダマを選択（ボックス" + this.boxSelect + "／" + 20 + "）", true);
        } else {
            headlineBoxOpen("移動先を選択（ボックス" + this.boxSelect + "／" + 20 + "）", true);
        }
        String sort = sortGetString(1);
        this.bitmapFontS = new BitmapFont(getBaseActivity().getTextureManager(), getBaseActivity().getAssets(), "font/numS.fnt");
        this.bitmapFontS.load();
        int no = 0;
        try {
            cursor = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.lv, m.kodama_attr1, m.kodama_attr2 FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE " + (this.boxSelect * 100) + "<u.sort_no AND u.sort_no<" + ((this.boxSelect + 1) * 100) + " ORDER BY " + sort, null);
            while (cursor.moveToNext()) {
                int userKodamaId = cursor.getInt(0);
                int kodamaId = cursor.getInt(2);
                String fileName = "kodama_s/" + kodamaId + ".png";
                int kodamaLv = cursor.getInt(3);
                int kodamaAttr1 = cursor.getInt(4);
                int kodamaAttr2 = cursor.getInt(5);
                this.shadowList.add(no, getBaseActivity().getResourceUtil().getSprite("item/shadow.png"));
                ((Sprite) this.shadowList.get(no)).setPosition((float) (posX - 4), (float) (posY + 46));
                attachChild((IEntity) this.shadowList.get(no));
                this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite(fileName, fileName));
                ((ButtonSprite) this.buttonList.get(no)).setPosition((float) posX, (float) posY);
                if (this.targetUserKodamaId == 0) {
                    if (kodamaId == 0) {
                        ((ButtonSprite) this.buttonList.get(no)).setTag(99999997);
                    } else {
                        ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + userKodamaId);
                    }
                } else if (userKodamaId == this.targetUserKodamaId) {
                    ((ButtonSprite) this.buttonList.get(no)).setTag(99999997);
                    ((ButtonSprite) this.buttonList.get(no)).setAlpha(0.5f);
                } else {
                    ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + userKodamaId);
                }
                ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(no));
                registerTouchArea((ITouchArea) this.buttonList.get(no));
                this.attrList1.add(no, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr1 + ".png"));
                ((Sprite) this.attrList1.get(no)).setPosition((float) posX, (float) posY);
                attachChild((IEntity) this.attrList1.get(no));
                this.attrList2.add(no, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr2 + ".png"));
                ((Sprite) this.attrList2.get(no)).setPosition((float) posX, (float) (posY + 24));
                attachChild((IEntity) this.attrList2.get(no));
                String str2 = String.valueOf(kodamaLv);
                if (kodamaId == 0) {
                    str2 = "";
                }
                this.statusText[no] = new Text((float) posX, 0.0f, (IFont) this.bitmapFontS, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                this.statusText[no].setPosition(this.statusText[no].getX() + ((64.0f - this.statusText[no].getWidth()) / 2.0f), (float) (posY + 44));
                attachChild(this.statusText[no]);
                posX += 80;
                if (460 <= posX) {
                    posX = 30;
                    posY += 80;
                }
                no++;
            }
            if (cursor != null) {
                cursor.close();
            }
            this.buttonNum = no;
            this.buttonArrowR = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowR.png", "button/arrowR_p.png");
            this.buttonArrowR.setPosition(412.0f, 600.0f);
            this.buttonArrowR.setTag(92);
            this.buttonArrowR.setOnClickListener(this);
            attachChild(this.buttonArrowR);
            registerTouchArea(this.buttonArrowR);
            this.buttonArrowL = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowL.png", "button/arrowL_p.png");
            this.buttonArrowL.setPosition(80.0f, 600.0f);
            this.buttonArrowL.setTag(91);
            this.buttonArrowL.setOnClickListener(this);
            attachChild(this.buttonArrowL);
            registerTouchArea(this.buttonArrowL);
            this.buttonArrowRR = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowRw.png", "button/arrowRw_p.png");
            this.buttonArrowRR.setPosition(476.0f, 600.0f);
            this.buttonArrowRR.setTag(94);
            this.buttonArrowRR.setOnClickListener(this);
            attachChild(this.buttonArrowRR);
            registerTouchArea(this.buttonArrowRR);
            this.buttonArrowLL = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowLw.png", "button/arrowLw_p.png");
            this.buttonArrowLL.setPosition(16.0f, 600.0f);
            this.buttonArrowLL.setTag(93);
            this.buttonArrowLL.setOnClickListener(this);
            attachChild(this.buttonArrowLL);
            registerTouchArea(this.buttonArrowLL);
            this.dArrowFlg = true;
            this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/unit_move_end.png", "button/unit_move_end_p.png");
            this.buttonEtc1.setTag(11);
            placeToCenterX(this.buttonEtc1, 592.0f);
            this.buttonEtc1.setOnClickListener(this);
            attachChild(this.buttonEtc1);
            registerTouchArea(this.buttonEtc1);
            menuBottomStart();
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuUnitMoveClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            ((Sprite) this.shadowList.get(i)).detachSelf();
            ((Sprite) this.attrList1.get(i)).detachSelf();
            ((Sprite) this.attrList2.get(i)).detachSelf();
            this.statusText[i].detachSelf();
        }
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        this.buttonArrowLL.detachSelf();
        unregisterTouchArea(this.buttonArrowLL);
        this.buttonArrowRR.detachSelf();
        unregisterTouchArea(this.buttonArrowRR);
        this.buttonEtc1.detachSelf();
        unregisterTouchArea(this.buttonEtc1);
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitMoveExe(int toUserKodamaId) {
        String str = "";
        Cursor cursor = null;
        int orgSort = 0;
        int newSort = 0;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.sort_no FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor2.moveToNext()) {
                orgSort = cursor2.getInt(0);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT u.sort_no FROM user_kodama_t u WHERE u.user_kodama_id=" + toUserKodamaId, null);
                if (cursor3.moveToNext()) {
                    newSort = cursor3.getInt(0);
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                if (orgSort != 0 && newSort != 0) {
                    this.f220db.beginTransaction();
                    try {
                        this.f220db.execSQL("UPDATE user_kodama_t SET sort_no=" + orgSort + " WHERE user_kodama_id=" + toUserKodamaId);
                        this.f220db.execSQL("UPDATE user_kodama_t SET sort_no=" + newSort + " WHERE user_kodama_id=" + this.targetUserKodamaId);
                        this.f220db.setTransactionSuccessful();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        this.f220db.endTransaction();
                    }
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
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitEquipListOpen() {
        this.menuMode = 11;
        menuBottomStop();
        String str = "";
        String str2 = "";
        String str3 = "sort_no ASC";
        this.listPageMax = 1;
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.name, u.equip, i.name, i.text FROM user_kodama_t u LEFT OUTER JOIN item_m i ON u.equip=i.item_id LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE u.kodama_id<>0 AND " + (this.boxSelect * 100) + "<u.sort_no AND u.sort_no<" + ((this.boxSelect + 1) * 100) + " ORDER BY " + sortGetString(1) + " LIMIT " + (this.listPage * 15) + ", " + 15, null);
            while (cursor2.moveToNext()) {
                String text = "　　（" + cursor2.getString(5).replaceAll("。", "") + "）";
                if (cursor2.getInt(3) == 0) {
                    text = "";
                } else if (cursor2.getInt(3) == 200001) {
                    text = "　　（経験値を吸収します）";
                }
                str = str + "■" + cursor2.getString(2) + "：" + cursor2.getString(4) + "\n" + text + "\n";
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            attachChild(this.informationBox);
            this.infoText = new Text(40.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            arrowOpen(91, 808, false);
            arrowOpen(92, 808, true);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonOk.setPosition(170.0f, 800.0f);
            this.buttonOk.setTag(10000000);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.menuBoxEquipListOpenFlg = true;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuUnitEquipListClose() {
        this.menuBoxEquipListOpenFlg = false;
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitBpListOpen() {
        this.menuMode = 11;
        menuBottomStop();
        String str = "sort_no ASC";
        String sort = sortGetString(1);
        String str2 = "" + "■コダマ名：HP/攻撃/防御/速度/BP/SLv\n";
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.name, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, u.skill, u.slv FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE u.kodama_id<>0 AND " + (this.boxSelect * 100) + "<u.sort_no AND u.sort_no<" + ((this.boxSelect + 1) * 100) + " ORDER BY " + sort, null);
            while (cursor2.moveToNext()) {
                String str3 = str2 + "■" + cursor2.getString(2) + "：" + cursor2.getInt(3) + "/" + cursor2.getInt(4) + "/" + cursor2.getInt(5) + "/" + cursor2.getInt(6) + "/" + cursor2.getInt(7);
                if (cursor2.getInt(8) != 0) {
                    str3 = str3 + "/" + cursor2.getInt(9);
                }
                str2 = str3 + "\n";
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            attachChild(this.informationBox);
            this.infoText = new Text(40.0f, 40.0f, (IFont) this.fontWhite, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonOk.setPosition(170.0f, 800.0f);
            this.buttonOk.setTag(10000000);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.menuBoxBpListOpenFlg = true;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuUnitBpListClose() {
        this.menuBoxBpListOpenFlg = false;
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitEvoListOpen() {
        this.menuMode = 11;
        menuBottomStop();
        String str = "【";
        this.listPageMax = 8;
        if (this.listPage == 1) {
            str = str + "不偏";
        } else if (this.listPage == 2) {
            str = str + "力";
        } else if (this.listPage == 3) {
            str = str + "技";
        } else if (this.listPage == 4) {
            str = str + "疾風";
        } else if (this.listPage == 5) {
            str = str + "祝福";
        } else if (this.listPage == 6) {
            str = str + "守";
        } else if (this.listPage == 7) {
            str = str + "一夜";
        } else if (this.listPage == 8) {
            str = str + "奇跡";
        }
        String str2 = str + "の霊珠】\n";
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.name, m.kodama_name, m.kodama_attr1, m.kodama_attr2 FROM user_kodama_t u INNER JOIN evo_m e ON u.kodama_id=e.kodama_id INNER JOIN kodama_m m ON e.evo_id=m.kodama_id WHERE u.kodama_id<>0 AND " + (this.boxSelect * 100) + "<u.sort_no AND u.sort_no<" + ((this.boxSelect + 1) * 100) + " AND e.evo_item=" + this.listPage + " ORDER BY u.sort_no", null);
            while (cursor2.moveToNext()) {
                str2 = str2 + "・" + cursor2.getString(0) + "→" + cursor2.getString(1) + "（" + this.attrs[cursor2.getInt(2) - 10] + "／" + this.attrs[cursor2.getInt(3) - 10] + "）\n";
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            attachChild(this.informationBox);
            this.infoText = new Text(40.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            arrowOpen(91, 808, false);
            arrowOpen(92, 808, true);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonOk.setPosition(170.0f, 800.0f);
            this.buttonOk.setTag(10000000);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.menuBoxEvoListOpenFlg = true;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuUnitEvoListClose() {
        this.menuBoxEvoListOpenFlg = false;
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public void menuUnitDetailOpen() {
        int i;
        this.menuMode = 11;
        menuBottomStop();
        Cursor cursor = null;
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
        int protectFlg = 0;
        int maxLv = 0;
        int i2 = 0;
        while (i < 4) {
            this.f221sb[i] = 0;
            i2 = i + 1;
        }
        try {
            cursor = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.name, u.lv, u.exp, u.equip, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, u.skill, u.slv, m.kodama_name, m.kodama_hp, m.kodama_atk, m.kodama_def, m.kodama_spd, m.kodama_attr1, m.kodama_attr2, i.hp, i.atk, i.def, i.spd, u.protect_flg, u.max_lv FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id LEFT OUTER JOIN item_m i ON u.equip=i.item_id WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToNext()) {
                kodamaId = cursor.getInt(2);
                nickName = cursor.getString(3);
                lv = cursor.getInt(4);
                exp = cursor.getInt(5);
                equip = cursor.getInt(6);
                i = 0;
                while (i < 4) {
                    this.f221sb[i] = cursor.getInt(i + 7);
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
                int itemDef = cursor.getInt(23);
                int itemSpd = cursor.getInt(24);
                protectFlg = cursor.getInt(25);
                maxLv = cursor.getInt(26);
                hp = ((int) Math.floor((double) (((((kodamaHp + 10) * 2) * lv) / 100) + lv + 10))) + itemHp + this.f221sb[0];
                vp = (100 <= lv ? 100 : ((int) Math.floor((double) (lv / 2))) + 50) + itemVp;
                atk = ((int) Math.floor((double) (((((kodamaAtk + 10) * 2) * lv) / 100) + 5))) + itemAtk + this.f221sb[1];
                def = ((int) Math.floor((double) (((((kodamaDef + 10) * 2) * lv) / 100) + 5))) + itemDef + this.f221sb[2];
                spd = ((int) Math.floor((double) (((((kodamaSpd + 10) * 2) * lv) / 100) + 5))) + itemSpd + this.f221sb[3];
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
                popAlert("データ取得エラー", "\n\nmenuUnitDetailOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
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
            String str = (nickName + "（" + kodamaName + "）\n") + "Lv." + lv;
            if (100 < maxLv) {
                str = str + "/" + maxLv;
            }
            String str2 = str + " Exp." + exp + "\n";
            int nextExp = (lv + 1) * (lv + 1) * (lv + 1) * 8;
            if (maxLv <= lv) {
                nextExp = lv * lv * lv * 8;
            }
            String str3 = ((((((str2 + "次のレベルまで：" + (nextExp - exp) + "\n") + "ＨＰ：" + hp + "\n") + "ＶＰ：" + vp + "\n") + "攻撃：" + atk + "\n") + "防御：" + def + "\n") + "速度：" + spd + "\n") + "ＢＰ：" + bp + "\n";
            this.editor.putInt("unitCheckNo", this.targetUserKodamaId);
            this.editor.commit();
            this.infoText = new Text(200.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            this.editor.putInt("unitCheckNo", 0);
            this.editor.commit();
            String str4 = "";
            if (equip == 0) {
                str4 = str4 + "■装備\nなし\n\n";
            } else {
                try {
                    cursor = this.f220db.rawQuery("SELECT m.name, m.text, m.item_id FROM item_m m WHERE m.item_id=" + equip, null);
                    if (cursor.moveToFirst()) {
                        str4 = str4 + "■装備\n「" + cursor.getString(0) + "」\n" + cursor.getString(1) + "\n\n";
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
                    Cursor cursor2 = this.f220db.rawQuery("SELECT m.name, m.text FROM skill_m m WHERE m.skill_id=" + skill, null);
                    while (cursor2.moveToNext()) {
                        str4 = str4 + "■スキル\n「" + cursor2.getString(0) + "」（SLv." + slv + "）\n" + cursor2.getString(1) + "\n\n";
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
            this.detailText = new Text(40.0f, 330.0f, (IFont) this.fontWhite, (CharSequence) brInsert(str4, 22), new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.detailText);
            this.buttonName = getBaseActivity().getResourceUtil().getButtonSprite("button/button_name.png", "button/button_name_p.png");
            this.buttonName.setPosition(50.0f, 240.0f);
            this.buttonName.setTag(10011);
            this.buttonName.setOnClickListener(this);
            attachChild(this.buttonName);
            registerTouchArea(this.buttonName);
            this.buttonItem = getBaseActivity().getResourceUtil().getButtonSprite("button/button_item.png", "button/button_item_p.png");
            this.buttonItem.setPosition(350.0f, 160.0f);
            this.buttonItem.setTag(10041);
            this.buttonItem.setOnClickListener(this);
            attachChild(this.buttonItem);
            registerTouchArea(this.buttonItem);
            this.buttonBp = getBaseActivity().getResourceUtil().getButtonSprite("button/button_bp.png", "button/button_bp_p.png");
            this.buttonBp.setPosition(350.0f, 240.0f);
            this.buttonBp.setTag(10051);
            this.buttonBp.setOnClickListener(this);
            attachChild(this.buttonBp);
            registerTouchArea(this.buttonBp);
            this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_spell.png", "button/button_spell_p.png");
            this.buttonEtc1.setPosition(60.0f, 720.0f);
            this.buttonEtc1.setTag(10021);
            this.buttonEtc1.setOnClickListener(this);
            attachChild(this.buttonEtc1);
            registerTouchArea(this.buttonEtc1);
            if (engageFlg || (500000 < this.userQuestId && this.userQuestId < 700000)) {
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_equip_ng.png", "button/button_equip_ng.png");
                this.buttonNg.setTag(99999997);
            } else {
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_equip.png", "button/button_equip_p.png");
                this.buttonNg.setTag(10031);
            }
            this.buttonNg.setPosition(280.0f, 720.0f);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
            if (protectFlg == 1) {
                this.buttonEtc2 = getBaseActivity().getResourceUtil().getButtonSprite("button/information_remove_ng.png", "button/information_remove_ng.png");
                this.buttonEtc2.setTag(99999997);
            } else {
                this.buttonEtc2 = getBaseActivity().getResourceUtil().getButtonSprite("button/information_remove.png", "button/information_remove_p.png");
                this.buttonEtc2.setTag(10001);
            }
            this.buttonEtc2.setPosition(60.0f, 800.0f);
            this.buttonEtc2.setOnClickListener(this);
            attachChild(this.buttonEtc2);
            registerTouchArea(this.buttonEtc2);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonOk.setPosition(280.0f, 800.0f);
            this.buttonOk.setTag(0);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            if (protectFlg == 0) {
                this.buttonEtc3 = getBaseActivity().getResourceUtil().getButtonSprite("item/protect_n.png", "item/protect_n.png");
                this.buttonEtc3.setTag(10131);
            } else {
                this.buttonEtc3 = getBaseActivity().getResourceUtil().getButtonSprite("item/protect.png", "item/protect.png");
                this.buttonEtc3.setTag(10130);
            }
            this.buttonEtc3.setPosition(46.0f, 110.0f);
            this.buttonEtc3.setOnClickListener(this);
            attachChild(this.buttonEtc3);
            registerTouchArea(this.buttonEtc3);
            this.buttonEtc3Flg = true;
            arrowOpen(91, 656, false);
            arrowOpen(92, 656, true);
            this.menuUnitDetailOpenFlg = true;
            this.unitDetailDisplay = true;
        } catch (Throwable th3) {
            if (cursor != null) {
                cursor.close();
            }
            throw th3;
        }
    }

    /* access modifiers changed from: private */
    public void menuUnitDetailClose() {
        this.menuUnitDetailOpenFlg = false;
        this.menuUnitDetailSpellOpenFlg = false;
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.detailText.detachSelf();
        this.unitDetailIcon.detachSelf();
        ((Sprite) this.attrList1.get(0)).detachSelf();
        ((Sprite) this.attrList2.get(0)).detachSelf();
        this.buttonName.detachSelf();
        unregisterTouchArea(this.buttonName);
        this.buttonItem.detachSelf();
        unregisterTouchArea(this.buttonItem);
        this.buttonBp.detachSelf();
        unregisterTouchArea(this.buttonBp);
        this.buttonEtc1.detachSelf();
        unregisterTouchArea(this.buttonEtc1);
        this.buttonEtc2.detachSelf();
        unregisterTouchArea(this.buttonEtc2);
        this.buttonEtc3.detachSelf();
        unregisterTouchArea(this.buttonEtc3);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitItemOpen() {
        this.menuMode = 13;
        menuBottomStart();
        String str = "アイテム選択";
        Cursor cursor = null;
        if (this.listPage == 1) {
            str = str + " - ページ2（霊珠）";
        } else if (this.listPage == 2) {
            str = str + " - ページ1（ビールetc）";
        }
        headlineBoxOpen(str, true);
        int no = 0;
        int height = 130;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.item_id, u.num, m.name, m.text FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id<>0 AND 0 < u.num AND m.type=" + this.listPage + " ORDER BY m.kana ASC", null);
            while (cursor2.moveToNext()) {
                this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                ((ButtonSprite) this.buttonList.get(no)).setPosition(-500.0f, (float) height);
                ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + cursor2.getInt(0));
                ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(no));
                registerTouchArea((ITouchArea) this.buttonList.get(no));
                this.buttonListText[no] = new Text(-486.0f, (float) (height + 10), (IFont) this.fontBlack, (CharSequence) cursor2.getString(2) + "（" + cursor2.getInt(1) + "個）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.buttonListText[no]);
                ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) height, (float) height));
                this.buttonListText[no].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (height + 10), (float) (height + 10)));
                height += 56;
                no++;
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            this.buttonNum = no;
            this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonEtc1.setTag(10000000);
            placeToCenterX(this.buttonEtc1, 678.0f);
            this.buttonEtc1.setOnClickListener(this);
            attachChild(this.buttonEtc1);
            registerTouchArea(this.buttonEtc1);
            arrowOpen(91, 686, false);
            arrowOpen(92, 686, true);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuUnitItemClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
        this.buttonEtc1.detachSelf();
        unregisterTouchArea(this.buttonEtc1);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x03e2, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:?, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x03e8, code lost:
        throw r1;
     */
    /* JADX WARNING: Incorrect condition in loop: B:71:0x0326 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void menuUnitItemDetailOpen(int r22) {
        /*
            r21 = this;
            r1 = 13
            r0 = r21
            r0.menuMode = r1
            r21.menuBottomStop()
            r13 = 0
            r12 = 0
            java.lang.String r15 = ""
            r18 = 100
            r8 = 0
            r0 = r21
            org.andengine.entity.sprite.Sprite r1 = r0.informationBox
            r0 = r21
            r0.attachChild(r1)
            r16 = 0
            java.lang.String r17 = ""
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT u.kodama_id, m.kodama_name, u.max_lv FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE u.user_kodama_id="
            java.lang.StringBuilder r1 = r1.append(r2)
            r0 = r21
            int r2 = r0.targetUserKodamaId
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r20 = r1.toString()
            r0 = r21
            android.database.sqlite.SQLiteDatabase r1 = r0.f220db     // Catch:{ all -> 0x0234 }
            r2 = 0
            r0 = r20
            android.database.Cursor r8 = r1.rawQuery(r0, r2)     // Catch:{ all -> 0x0234 }
            boolean r1 = r8.moveToNext()     // Catch:{ all -> 0x0234 }
            if (r1 == 0) goto L_0x0054
            r1 = 0
            int r16 = r8.getInt(r1)     // Catch:{ all -> 0x0234 }
            r1 = 1
            java.lang.String r17 = r8.getString(r1)     // Catch:{ all -> 0x0234 }
            r1 = 2
            int r18 = r8.getInt(r1)     // Catch:{ all -> 0x0234 }
        L_0x0054:
            if (r8 == 0) goto L_0x0059
            r8.close()
        L_0x0059:
            java.lang.String r5 = ""
            r19 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "SELECT u.item_id, u.num, m.name, m.type, m.text FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id="
            java.lang.StringBuilder r1 = r1.append(r2)
            r0 = r22
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r20 = r1.toString()
            r0 = r21
            android.database.sqlite.SQLiteDatabase r1 = r0.f220db     // Catch:{ all -> 0x0265 }
            r2 = 0
            r0 = r20
            android.database.Cursor r8 = r1.rawQuery(r0, r2)     // Catch:{ all -> 0x0265 }
            boolean r1 = r8.moveToNext()     // Catch:{ all -> 0x0265 }
            if (r1 == 0) goto L_0x0151
            r1 = 2
            java.lang.String r15 = r8.getString(r1)     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0265 }
            r1.<init>()     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "■"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            r2 = 2
            java.lang.String r2 = r8.getString(r2)     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "（"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            r2 = 1
            int r2 = r8.getInt(r2)     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "個）\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            r2 = 4
            java.lang.String r2 = r8.getString(r2)     // Catch:{ all -> 0x0265 }
            r3 = 18
            r0 = r21
            java.lang.String r2 = r0.brInsert(r2, r3)     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0265 }
            r1 = 0
            int r1 = r8.getInt(r1)     // Catch:{ all -> 0x0265 }
            r2 = 10011(0x271b, float:1.4028E-41)
            if (r1 != r2) goto L_0x026c
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0265 }
            r1.<init>()     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0265 }
            r1.<init>()     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "SELECT s.name, s.text FROM kodama_learn_m m LEFT OUTER JOIN skill_m s ON m.learn_id=s.skill_id WHERE m.kodama_id="
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            r0 = r16
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = " AND m.type=2"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            java.lang.String r20 = r1.toString()     // Catch:{ all -> 0x0265 }
            r9 = 0
            r0 = r21
            android.database.sqlite.SQLiteDatabase r1 = r0.f220db     // Catch:{ all -> 0x025e }
            r2 = 0
            r0 = r20
            android.database.Cursor r9 = r1.rawQuery(r0, r2)     // Catch:{ all -> 0x025e }
            boolean r1 = r9.moveToFirst()     // Catch:{ all -> 0x025e }
            if (r1 == 0) goto L_0x023b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r1.<init>()     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x025e }
            java.lang.String r2 = "\n\n秘伝の書を使用すると、\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x025e }
            r0 = r17
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x025e }
            java.lang.String r2 = "は\n「"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x025e }
            r2 = 0
            java.lang.String r2 = r9.getString(r2)     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x025e }
            java.lang.String r2 = "」\n（"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x025e }
            r2 = 1
            java.lang.String r2 = r9.getString(r2)     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x025e }
            java.lang.String r2 = "）\nを習得します。"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x025e }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x025e }
        L_0x014c:
            if (r9 == 0) goto L_0x0151
            r9.close()     // Catch:{ all -> 0x0265 }
        L_0x0151:
            if (r8 == 0) goto L_0x0156
            r8.close()
        L_0x0156:
            if (r19 != 0) goto L_0x0177
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r1 = r1.append(r5)
            java.lang.String r2 = "\n\n「"
            java.lang.StringBuilder r1 = r1.append(r2)
            r0 = r17
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r2 = "」に\n上記のアイテムを使用します。\n使用したアイテムは1つ減ります。\nよろしいですか？"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r5 = r1.toString()
        L_0x0177:
            org.andengine.entity.text.Text r1 = new org.andengine.entity.text.Text
            r2 = 1114636288(0x42700000, float:60.0)
            r3 = 1114636288(0x42700000, float:60.0)
            r0 = r21
            org.andengine.opengl.font.Font r4 = r0.fontWhite
            org.andengine.entity.text.TextOptions r6 = new org.andengine.entity.text.TextOptions
            org.andengine.util.HorizontalAlign r7 = org.andengine.util.HorizontalAlign.LEFT
            r6.<init>(r7)
            kanatamikado.ae.reiki.MultiSceneActivity r7 = r21.getBaseActivity()
            org.andengine.opengl.vbo.VertexBufferObjectManager r7 = r7.getVertexBufferObjectManager()
            r1.<init>(r2, r3, r4, r5, r6, r7)
            r0 = r21
            r0.infoText = r1
            r0 = r21
            org.andengine.entity.text.Text r1 = r0.infoText
            r0 = r21
            r0.attachChild(r1)
            kanatamikado.ae.reiki.MultiSceneActivity r1 = r21.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r1 = r1.getResourceUtil()
            java.lang.String r2 = "button/button_close.png"
            java.lang.String r3 = "button/button_close_p.png"
            org.andengine.entity.sprite.ButtonSprite r1 = r1.getButtonSprite(r2, r3)
            r0 = r21
            r0.buttonNg = r1
            if (r12 == 0) goto L_0x045b
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonNg
            r2 = 1145569280(0x44480000, float:800.0)
            r0 = r21
            r0.placeToCenterX(r1, r2)
        L_0x01c1:
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonNg
            r2 = 10000000(0x989680, float:1.4012985E-38)
            r1.setTag(r2)
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonNg
            r0 = r21
            r1.setOnClickListener(r0)
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonNg
            r0 = r21
            r0.attachChild(r1)
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonNg
            r0 = r21
            r0.registerTouchArea(r1)
            if (r19 == 0) goto L_0x0469
            kanatamikado.ae.reiki.MultiSceneActivity r1 = r21.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r1 = r1.getResourceUtil()
            java.lang.String r2 = "button/button_use_ng.png"
            java.lang.String r3 = "button/button_use_ng.png"
            org.andengine.entity.sprite.ButtonSprite r1 = r1.getButtonSprite(r2, r3)
            r0 = r21
            r0.buttonOk = r1
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonOk
            r2 = 99999997(0x5f5e0fd, float:2.3122337E-35)
            r1.setTag(r2)
        L_0x0206:
            if (r12 == 0) goto L_0x048b
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonOk
            r2 = 1144258560(0x44340000, float:720.0)
            r0 = r21
            r0.placeToCenterX(r1, r2)
        L_0x0213:
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonOk
            r0 = r21
            r1.setOnClickListener(r0)
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonOk
            r0 = r21
            r0.attachChild(r1)
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonOk
            r0 = r21
            r0.registerTouchArea(r1)
            r1 = 1
            r0 = r21
            r0.menuUnitItemDetailOpenFlg = r1
            return
        L_0x0234:
            r1 = move-exception
            if (r8 == 0) goto L_0x023a
            r8.close()
        L_0x023a:
            throw r1
        L_0x023b:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x025e }
            r1.<init>()     // Catch:{ all -> 0x025e }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x025e }
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x025e }
            r0 = r17
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x025e }
            java.lang.String r2 = "はスキルを習得できません。\n進化すると習得できるようになります。"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x025e }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x025e }
            r19 = 1
            goto L_0x014c
        L_0x025e:
            r1 = move-exception
            if (r9 == 0) goto L_0x0264
            r9.close()     // Catch:{ all -> 0x0265 }
        L_0x0264:
            throw r1     // Catch:{ all -> 0x0265 }
        L_0x0265:
            r1 = move-exception
            if (r8 == 0) goto L_0x026b
            r8.close()
        L_0x026b:
            throw r1
        L_0x026c:
            r1 = 0
            int r1 = r8.getInt(r1)     // Catch:{ all -> 0x0265 }
            r2 = 9
            if (r1 != r2) goto L_0x029e
            r1 = 110(0x6e, float:1.54E-43)
            r0 = r18
            if (r1 > r0) goto L_0x0151
            r19 = 1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0265 }
            r1.<init>()     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "\n\n「"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            r0 = r17
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "」はこれ以上\n最大Lvを上げることができません。"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0265 }
            goto L_0x0151
        L_0x029e:
            r1 = 3
            int r1 = r8.getInt(r1)     // Catch:{ all -> 0x0265 }
            r2 = 1
            if (r1 != r2) goto L_0x0151
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0265 }
            r1.<init>()     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x0265 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0265 }
            r1.<init>()     // Catch:{ all -> 0x0265 }
            java.lang.String r2 = "SELECT m.kodama_id FROM evo_m m WHERE m.kodama_id="
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x0265 }
            r0 = r16
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x0265 }
            java.lang.String r20 = r1.toString()     // Catch:{ all -> 0x0265 }
            r10 = 0
            r0 = r21
            android.database.sqlite.SQLiteDatabase r1 = r0.f220db     // Catch:{ all -> 0x03e9 }
            r2 = 0
            r0 = r20
            android.database.Cursor r10 = r1.rawQuery(r0, r2)     // Catch:{ all -> 0x03e9 }
            boolean r1 = r10.moveToNext()     // Catch:{ all -> 0x03e9 }
            if (r1 == 0) goto L_0x0439
            r19 = 1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03e9 }
            r1.<init>()     // Catch:{ all -> 0x03e9 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "\n「"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            r0 = r17
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "」は"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x03e9 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03e9 }
            r1.<init>()     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "SELECT e.evo_item, i.name, m.kodama_id, m.kodama_name, m.kodama_attr1, m.kodama_attr2 FROM evo_m e LEFT OUTER JOIN kodama_m m ON e.evo_id=m.kodama_id LEFT OUTER JOIN item_m i ON e.evo_item=i.item_id WHERE e.kodama_id="
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            r0 = r16
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x03e9 }
            java.lang.String r20 = r1.toString()     // Catch:{ all -> 0x03e9 }
            r11 = 0
            r0 = r21
            android.database.sqlite.SQLiteDatabase r1 = r0.f220db     // Catch:{ all -> 0x03e2 }
            r2 = 0
            r0 = r20
            android.database.Cursor r11 = r1.rawQuery(r0, r2)     // Catch:{ all -> 0x03e2 }
        L_0x0322:
            boolean r1 = r11.moveToNext()     // Catch:{ all -> 0x03e2 }
            if (r1 == 0) goto L_0x0398
            r1 = 0
            int r1 = r11.getInt(r1)     // Catch:{ all -> 0x03e2 }
            r0 = r22
            if (r1 != r0) goto L_0x0338
            r19 = 0
            r1 = 2
            int r13 = r11.getInt(r1)     // Catch:{ all -> 0x03e2 }
        L_0x0338:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03e2 }
            r1.<init>()     // Catch:{ all -> 0x03e2 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x03e2 }
            java.lang.String r2 = "\n・"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            r2 = 1
            java.lang.String r2 = r11.getString(r2)     // Catch:{ all -> 0x03e2 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            java.lang.String r2 = "で「"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            r2 = 3
            java.lang.String r2 = r11.getString(r2)     // Catch:{ all -> 0x03e2 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            java.lang.String r2 = "」（"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            r0 = r21
            java.lang.String[] r2 = r0.attrs     // Catch:{ all -> 0x03e2 }
            r3 = 4
            int r3 = r11.getInt(r3)     // Catch:{ all -> 0x03e2 }
            int r3 = r3 + -10
            r2 = r2[r3]     // Catch:{ all -> 0x03e2 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            java.lang.String r2 = "／"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            r0 = r21
            java.lang.String[] r2 = r0.attrs     // Catch:{ all -> 0x03e2 }
            r3 = 5
            int r3 = r11.getInt(r3)     // Catch:{ all -> 0x03e2 }
            int r3 = r3 + -10
            r2 = r2[r3]     // Catch:{ all -> 0x03e2 }
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            java.lang.String r2 = "）"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e2 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x03e2 }
            goto L_0x0322
        L_0x0398:
            if (r11 == 0) goto L_0x039d
            r11.close()     // Catch:{ all -> 0x03e9 }
        L_0x039d:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03e9 }
            r1.<init>()     // Catch:{ all -> 0x03e9 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "\nに進化できます。\n\n別タイプに進化し直すこともできますが、\n進化先で習得できないスペルは忘れます。\nまたスキルが変化することもあります。"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x03e9 }
            if (r19 == 0) goto L_0x03f0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03e9 }
            r1.<init>()     // Catch:{ all -> 0x03e9 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "\n\n「"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            r0 = r17
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "」は\n"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            java.lang.StringBuilder r1 = r1.append(r15)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "では進化できません。"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x03e9 }
        L_0x03db:
            if (r10 == 0) goto L_0x0151
            r10.close()     // Catch:{ all -> 0x0265 }
            goto L_0x0151
        L_0x03e2:
            r1 = move-exception
            if (r11 == 0) goto L_0x03e8
            r11.close()     // Catch:{ all -> 0x03e9 }
        L_0x03e8:
            throw r1     // Catch:{ all -> 0x03e9 }
        L_0x03e9:
            r1 = move-exception
            if (r10 == 0) goto L_0x03ef
            r10.close()     // Catch:{ all -> 0x0265 }
        L_0x03ef:
            throw r1     // Catch:{ all -> 0x0265 }
        L_0x03f0:
            r12 = 1
            r1 = 1
            r0 = r21
            r0.newUnitDisplayFlg = r1     // Catch:{ all -> 0x03e9 }
            r1 = 1
            r0 = r21
            r0.newUnitOpenFlg = r1     // Catch:{ all -> 0x03e9 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03e9 }
            r1.<init>()     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "kodama/"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            java.lang.StringBuilder r1 = r1.append(r13)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = ".png"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            java.lang.String r14 = r1.toString()     // Catch:{ all -> 0x03e9 }
            kanatamikado.ae.reiki.MultiSceneActivity r1 = r21.getBaseActivity()     // Catch:{ all -> 0x03e9 }
            kanatamikado.ae.reiki.ResourceUtil r1 = r1.getResourceUtil()     // Catch:{ all -> 0x03e9 }
            org.andengine.entity.sprite.Sprite r1 = r1.getSprite(r14)     // Catch:{ all -> 0x03e9 }
            r0 = r21
            r0.newUnitImg = r1     // Catch:{ all -> 0x03e9 }
            r0 = r21
            org.andengine.entity.sprite.Sprite r1 = r0.newUnitImg     // Catch:{ all -> 0x03e9 }
            r2 = 1129185280(0x434e0000, float:206.0)
            r3 = 1141964800(0x44110000, float:580.0)
            r1.setPosition(r2, r3)     // Catch:{ all -> 0x03e9 }
            r0 = r21
            org.andengine.entity.sprite.Sprite r1 = r0.newUnitImg     // Catch:{ all -> 0x03e9 }
            r0 = r21
            r0.attachChild(r1)     // Catch:{ all -> 0x03e9 }
            goto L_0x03db
        L_0x0439:
            r19 = 1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x03e9 }
            r1.<init>()     // Catch:{ all -> 0x03e9 }
            java.lang.StringBuilder r1 = r1.append(r5)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "\n\n「"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            r0 = r17
            java.lang.StringBuilder r1 = r1.append(r0)     // Catch:{ all -> 0x03e9 }
            java.lang.String r2 = "」はこれ以上進化できません。"
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x03e9 }
            java.lang.String r5 = r1.toString()     // Catch:{ all -> 0x03e9 }
            goto L_0x03db
        L_0x045b:
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonNg
            r2 = 1144750080(0x443b8000, float:750.0)
            r0 = r21
            r0.placeToCenterX(r1, r2)
            goto L_0x01c1
        L_0x0469:
            kanatamikado.ae.reiki.MultiSceneActivity r1 = r21.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r1 = r1.getResourceUtil()
            java.lang.String r2 = "button/button_use.png"
            java.lang.String r3 = "button/button_use_p.png"
            org.andengine.entity.sprite.ButtonSprite r1 = r1.getButtonSprite(r2, r3)
            r0 = r21
            r0.buttonOk = r1
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonOk
            r2 = 10000000(0x989680, float:1.4012985E-38)
            int r2 = r2 + r22
            r1.setTag(r2)
            goto L_0x0206
        L_0x048b:
            r0 = r21
            org.andengine.entity.sprite.ButtonSprite r1 = r0.buttonOk
            r2 = 1143111680(0x44228000, float:650.0)
            r0 = r21
            r0.placeToCenterX(r1, r2)
            goto L_0x0213
        */
        throw new UnsupportedOperationException("Method not decompiled: kanatamikado.p006ae.reiki.MenuScene.menuUnitItemDetailOpen(int):void");
    }

    private void menuUnitItemDetailClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        if (this.newUnitOpenFlg) {
            this.newUnitOpenFlg = false;
            this.newUnitImg.detachSelf();
        }
        this.menuUnitItemDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x038c, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x0392, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void menuUnitItemExe(int r33) {
        /*
            r32 = this;
            r2 = 13
            r0 = r32
            r0.menuMode = r2
            r32.menuBottomStop()
            r0 = r32
            org.andengine.entity.sprite.Sprite r2 = r0.informationBox
            r0 = r32
            r0.attachChild(r2)
            java.lang.String r30 = "empty"
            java.lang.String r6 = ""
            java.lang.String r21 = ""
            r26 = 100
            r10 = 0
            r2 = 9
            r0 = r33
            if (r0 != r2) goto L_0x023e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SELECT u.name, u.max_lv FROM user_kodama_t u WHERE u.user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r32
            int r3 = r0.targetUserKodamaId
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r29 = r2.toString()
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x0237 }
            r3 = 0
            r0 = r29
            android.database.Cursor r10 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x0237 }
            boolean r2 = r10.moveToFirst()     // Catch:{ all -> 0x0237 }
            if (r2 == 0) goto L_0x00a7
            r2 = 0
            java.lang.String r21 = r10.getString(r2)     // Catch:{ all -> 0x0237 }
            r2 = 1
            int r2 = r10.getInt(r2)     // Catch:{ all -> 0x0237 }
            int r26 = r2 + 5
            r2 = 110(0x6e, float:1.54E-43)
            r0 = r26
            if (r2 >= r0) goto L_0x005d
            r26 = 110(0x6e, float:1.54E-43)
        L_0x005d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0237 }
            r2.<init>()     // Catch:{ all -> 0x0237 }
            java.lang.String r3 = "UPDATE user_kodama_t SET max_lv="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0237 }
            r0 = r26
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x0237 }
            java.lang.String r3 = " WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0237 }
            r0 = r32
            int r3 = r0.targetUserKodamaId     // Catch:{ all -> 0x0237 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0237 }
            java.lang.String r30 = r2.toString()     // Catch:{ all -> 0x0237 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0237 }
            r2.<init>()     // Catch:{ all -> 0x0237 }
            java.lang.String r3 = "「"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0237 }
            r0 = r21
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x0237 }
            java.lang.String r3 = "」の最大Lvが\n"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0237 }
            r0 = r26
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x0237 }
            java.lang.String r3 = "になりました。"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0237 }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x0237 }
        L_0x00a7:
            if (r10 == 0) goto L_0x00ac
            r10.close()
        L_0x00ac:
            r19 = 0
            java.lang.String r2 = "empty"
            r0 = r30
            boolean r2 = r0.equals(r2)
            if (r2 != 0) goto L_0x07e6
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SELECT u.num FROM user_item_t u WHERE item_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r33
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r29 = r2.toString()
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x07d6 }
            r3 = 0
            r0 = r29
            android.database.Cursor r10 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x07d6 }
            boolean r2 = r10.moveToFirst()     // Catch:{ all -> 0x07d6 }
            if (r2 == 0) goto L_0x0138
            r2 = 0
            int r19 = r10.getInt(r2)     // Catch:{ all -> 0x07d6 }
            int r19 = r19 + -1
            if (r19 >= 0) goto L_0x00e9
            r19 = 0
        L_0x00e9:
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x07d6 }
            r2.beginTransaction()     // Catch:{ all -> 0x07d6 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLException -> 0x07c9 }
            r2.<init>()     // Catch:{ SQLException -> 0x07c9 }
            java.lang.String r3 = "UPDATE user_item_t SET num="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SQLException -> 0x07c9 }
            r0 = r19
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ SQLException -> 0x07c9 }
            java.lang.String r3 = " WHERE item_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ SQLException -> 0x07c9 }
            r0 = r33
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ SQLException -> 0x07c9 }
            java.lang.String r29 = r2.toString()     // Catch:{ SQLException -> 0x07c9 }
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ SQLException -> 0x07c9 }
            r0 = r29
            r2.execSQL(r0)     // Catch:{ SQLException -> 0x07c9 }
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ SQLException -> 0x07c9 }
            r0 = r30
            r2.execSQL(r0)     // Catch:{ SQLException -> 0x07c9 }
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ SQLException -> 0x07c9 }
            r2.setTransactionSuccessful()     // Catch:{ SQLException -> 0x07c9 }
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x07d6 }
            r2.endTransaction()     // Catch:{ all -> 0x07d6 }
        L_0x0131:
            r0 = r32
            org.andengine.audio.sound.Sound r2 = r0.itemUseSound     // Catch:{ all -> 0x07d6 }
            r2.play()     // Catch:{ all -> 0x07d6 }
        L_0x0138:
            if (r10 == 0) goto L_0x013d
            r10.close()
        L_0x013d:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r2 = r2.append(r6)
            java.lang.String r3 = "\n\nアイテム所持数："
            java.lang.StringBuilder r2 = r2.append(r3)
            int r3 = r19 + 1
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = "→"
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r19
            java.lang.StringBuilder r2 = r2.append(r0)
            java.lang.String r6 = r2.toString()
        L_0x0162:
            org.andengine.entity.text.Text r2 = new org.andengine.entity.text.Text
            r3 = 1114636288(0x42700000, float:60.0)
            r4 = 1114636288(0x42700000, float:60.0)
            r0 = r32
            org.andengine.opengl.font.Font r5 = r0.fontWhite
            org.andengine.entity.text.TextOptions r7 = new org.andengine.entity.text.TextOptions
            org.andengine.util.HorizontalAlign r8 = org.andengine.util.HorizontalAlign.LEFT
            r7.<init>(r8)
            kanatamikado.ae.reiki.MultiSceneActivity r8 = r32.getBaseActivity()
            org.andengine.opengl.vbo.VertexBufferObjectManager r8 = r8.getVertexBufferObjectManager()
            r2.<init>(r3, r4, r5, r6, r7, r8)
            r0 = r32
            r0.infoText = r2
            r0 = r32
            org.andengine.entity.text.Text r2 = r0.infoText
            r0 = r32
            r0.attachChild(r2)
            r2 = 9
            r0 = r33
            if (r0 != r2) goto L_0x0197
            r2 = 110(0x6e, float:1.54E-43)
            r0 = r26
            if (r0 < r2) goto L_0x01a3
        L_0x0197:
            r2 = 10000(0x2710, float:1.4013E-41)
            r0 = r33
            if (r2 >= r0) goto L_0x07ef
            r2 = 10100(0x2774, float:1.4153E-41)
            r0 = r33
            if (r0 >= r2) goto L_0x07ef
        L_0x01a3:
            if (r19 <= 0) goto L_0x07ef
            kanatamikado.ae.reiki.MultiSceneActivity r2 = r32.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r2 = r2.getResourceUtil()
            java.lang.String r3 = "button/button_use_again.png"
            java.lang.String r4 = "button/button_use_again_p.png"
            org.andengine.entity.sprite.ButtonSprite r2 = r2.getButtonSprite(r3, r4)
            r0 = r32
            r0.buttonOk = r2
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonOk
            r3 = 10000000(0x989680, float:1.4012985E-38)
            int r3 = r3 + r33
            r2.setTag(r3)
        L_0x01c5:
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonOk
            r3 = 1143111680(0x44228000, float:650.0)
            r0 = r32
            r0.placeToCenterX(r2, r3)
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonOk
            r0 = r32
            r2.setOnClickListener(r0)
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonOk
            r0 = r32
            r0.attachChild(r2)
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonOk
            r0 = r32
            r0.registerTouchArea(r2)
            kanatamikado.ae.reiki.MultiSceneActivity r2 = r32.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r2 = r2.getResourceUtil()
            java.lang.String r3 = "button/button_close.png"
            java.lang.String r4 = "button/button_close_p.png"
            org.andengine.entity.sprite.ButtonSprite r2 = r2.getButtonSprite(r3, r4)
            r0 = r32
            r0.buttonNg = r2
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonNg
            r3 = 1144750080(0x443b8000, float:750.0)
            r0 = r32
            r0.placeToCenterX(r2, r3)
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonNg
            r3 = 20000000(0x1312d00, float:3.2542052E-38)
            r2.setTag(r3)
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonNg
            r0 = r32
            r2.setOnClickListener(r0)
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonNg
            r0 = r32
            r0.attachChild(r2)
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonNg
            r0 = r32
            r0.registerTouchArea(r2)
            r2 = 1
            r0 = r32
            r0.menuUnitItemDetailOpenFlg = r2
            return
        L_0x0237:
            r2 = move-exception
            if (r10 == 0) goto L_0x023d
            r10.close()
        L_0x023d:
            throw r2
        L_0x023e:
            r2 = 1
            r0 = r33
            if (r2 > r0) goto L_0x049d
            r2 = 100
            r0 = r33
            if (r0 > r2) goto L_0x049d
            r20 = 0
            r2 = 4
            int[] r0 = new int[r2]
            r24 = r0
            r23 = 0
            java.lang.String r27 = ""
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SELECT u.kodama_id, u.name, u.skill, u.spell1, u.spell2, u.spell3, u.spell4, m.kodama_name FROM user_kodama_t u  LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE u.user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r32
            int r3 = r0.targetUserKodamaId
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r29 = r2.toString()
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x0385 }
            r3 = 0
            r0 = r29
            android.database.Cursor r10 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x0385 }
            boolean r2 = r10.moveToNext()     // Catch:{ all -> 0x0385 }
            if (r2 == 0) goto L_0x0432
            r2 = 0
            int r20 = r10.getInt(r2)     // Catch:{ all -> 0x0385 }
            r2 = 1
            java.lang.String r27 = r10.getString(r2)     // Catch:{ all -> 0x0385 }
            r2 = 2
            int r23 = r10.getInt(r2)     // Catch:{ all -> 0x0385 }
            r2 = 0
            r3 = 3
            int r3 = r10.getInt(r3)     // Catch:{ all -> 0x0385 }
            r24[r2] = r3     // Catch:{ all -> 0x0385 }
            r2 = 1
            r3 = 4
            int r3 = r10.getInt(r3)     // Catch:{ all -> 0x0385 }
            r24[r2] = r3     // Catch:{ all -> 0x0385 }
            r2 = 2
            r3 = 5
            int r3 = r10.getInt(r3)     // Catch:{ all -> 0x0385 }
            r24[r2] = r3     // Catch:{ all -> 0x0385 }
            r2 = 3
            r3 = 6
            int r3 = r10.getInt(r3)     // Catch:{ all -> 0x0385 }
            r24[r2] = r3     // Catch:{ all -> 0x0385 }
            r2 = 7
            java.lang.String r21 = r10.getString(r2)     // Catch:{ all -> 0x0385 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0385 }
            r2.<init>()     // Catch:{ all -> 0x0385 }
            java.lang.String r3 = "SELECT e.evo_id, m.kodama_name FROM evo_m e LEFT OUTER JOIN kodama_m m ON e.evo_id=m.kodama_id WHERE e.evo_item="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0385 }
            r0 = r33
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x0385 }
            java.lang.String r3 = " AND e.kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0385 }
            r0 = r20
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x0385 }
            java.lang.String r29 = r2.toString()     // Catch:{ all -> 0x0385 }
            r11 = 0
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x037e }
            r3 = 0
            r0 = r29
            android.database.Cursor r11 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x037e }
            boolean r2 = r11.moveToFirst()     // Catch:{ all -> 0x037e }
            if (r2 == 0) goto L_0x042d
            r2 = 0
            int r16 = r11.getInt(r2)     // Catch:{ all -> 0x037e }
            r2 = 1
            java.lang.String r17 = r11.getString(r2)     // Catch:{ all -> 0x037e }
            r12 = 0
            if (r23 == 0) goto L_0x0326
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x037e }
            r2.<init>()     // Catch:{ all -> 0x037e }
            java.lang.String r3 = "SELECT m.learn_id FROM kodama_learn_m m WHERE m.kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r16
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = " AND m.type=2"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r29 = r2.toString()     // Catch:{ all -> 0x037e }
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x0377 }
            r3 = 0
            r0 = r29
            android.database.Cursor r12 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x0377 }
            boolean r2 = r12.moveToFirst()     // Catch:{ all -> 0x0377 }
            if (r2 == 0) goto L_0x0374
            r2 = 0
            int r23 = r12.getInt(r2)     // Catch:{ all -> 0x0377 }
        L_0x0321:
            if (r12 == 0) goto L_0x0326
            r12.close()     // Catch:{ all -> 0x037e }
        L_0x0326:
            r18 = 0
        L_0x0328:
            r2 = 4
            r0 = r18
            if (r0 >= r2) goto L_0x0393
            r2 = r24[r18]     // Catch:{ all -> 0x037e }
            if (r2 == 0) goto L_0x0371
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x037e }
            r2.<init>()     // Catch:{ all -> 0x037e }
            java.lang.String r3 = "SELECT m.learn_id FROM kodama_learn_m m WHERE m.kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r16
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = " AND m.learn_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = r24[r18]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = " AND m.type=1"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r29 = r2.toString()     // Catch:{ all -> 0x037e }
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x038c }
            r3 = 0
            r0 = r29
            android.database.Cursor r12 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x038c }
            boolean r2 = r12.moveToFirst()     // Catch:{ all -> 0x038c }
            if (r2 != 0) goto L_0x036c
            r2 = 0
            r24[r18] = r2     // Catch:{ all -> 0x038c }
        L_0x036c:
            if (r12 == 0) goto L_0x0371
            r12.close()     // Catch:{ all -> 0x037e }
        L_0x0371:
            int r18 = r18 + 1
            goto L_0x0328
        L_0x0374:
            r23 = 0
            goto L_0x0321
        L_0x0377:
            r2 = move-exception
            if (r12 == 0) goto L_0x037d
            r12.close()     // Catch:{ all -> 0x037e }
        L_0x037d:
            throw r2     // Catch:{ all -> 0x037e }
        L_0x037e:
            r2 = move-exception
            if (r11 == 0) goto L_0x0384
            r11.close()     // Catch:{ all -> 0x0385 }
        L_0x0384:
            throw r2     // Catch:{ all -> 0x0385 }
        L_0x0385:
            r2 = move-exception
            if (r10 == 0) goto L_0x038b
            r10.close()
        L_0x038b:
            throw r2
        L_0x038c:
            r2 = move-exception
            if (r12 == 0) goto L_0x0392
            r12.close()     // Catch:{ all -> 0x037e }
        L_0x0392:
            throw r2     // Catch:{ all -> 0x037e }
        L_0x0393:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x037e }
            r2.<init>()     // Catch:{ all -> 0x037e }
            r0 = r27
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = "は\n「"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r17
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = "」に進化しました！"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x037e }
            r0 = r27
            r1 = r21
            boolean r2 = r0.equals(r1)     // Catch:{ all -> 0x037e }
            if (r2 == 0) goto L_0x0439
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x037e }
            r2.<init>()     // Catch:{ all -> 0x037e }
            java.lang.String r3 = "UPDATE user_kodama_t SET kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r16
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", skill="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r23
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", spell1="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = 0
            r3 = r24[r3]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", spell2="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = 1
            r3 = r24[r3]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", spell3="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = 2
            r3 = r24[r3]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", spell4="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = 3
            r3 = r24[r3]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", name=\""
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r17
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = "\" WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r32
            int r3 = r0.targetUserKodamaId     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r30 = r2.toString()     // Catch:{ all -> 0x037e }
        L_0x042d:
            if (r11 == 0) goto L_0x0432
            r11.close()     // Catch:{ all -> 0x0385 }
        L_0x0432:
            if (r10 == 0) goto L_0x00ac
            r10.close()
            goto L_0x00ac
        L_0x0439:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x037e }
            r2.<init>()     // Catch:{ all -> 0x037e }
            java.lang.String r3 = "UPDATE user_kodama_t SET kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r16
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", skill="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r23
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", spell1="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = 0
            r3 = r24[r3]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", spell2="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = 1
            r3 = r24[r3]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", spell3="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = 2
            r3 = r24[r3]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = ", spell4="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r3 = 3
            r3 = r24[r3]     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r3 = " WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            r0 = r32
            int r3 = r0.targetUserKodamaId     // Catch:{ all -> 0x037e }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x037e }
            java.lang.String r30 = r2.toString()     // Catch:{ all -> 0x037e }
            goto L_0x042d
        L_0x049d:
            r2 = 10001(0x2711, float:1.4014E-41)
            r0 = r33
            if (r2 > r0) goto L_0x0587
            r2 = 10005(0x2715, float:1.402E-41)
            r0 = r33
            if (r0 > r2) goto L_0x0587
            r31 = 0
            r2 = 10001(0x2711, float:1.4014E-41)
            r0 = r33
            if (r0 != r2) goto L_0x0557
            r31 = 100
        L_0x04b3:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SELECT u.name, u.bp FROM user_kodama_t u  WHERE u.user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r32
            int r3 = r0.targetUserKodamaId
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r29 = r2.toString()
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x0580 }
            r3 = 0
            r0 = r29
            android.database.Cursor r10 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x0580 }
            boolean r2 = r10.moveToFirst()     // Catch:{ all -> 0x0580 }
            if (r2 == 0) goto L_0x0550
            r2 = 0
            java.lang.String r21 = r10.getString(r2)     // Catch:{ all -> 0x0580 }
            r2 = 1
            int r2 = r10.getInt(r2)     // Catch:{ all -> 0x0580 }
            int r9 = r2 + r31
            r2 = 9999999(0x98967f, float:1.4012983E-38)
            if (r2 >= r9) goto L_0x04ef
            r9 = 9999999(0x98967f, float:1.4012983E-38)
        L_0x04ef:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0580 }
            r2.<init>()     // Catch:{ all -> 0x0580 }
            java.lang.String r3 = "UPDATE user_kodama_t SET bp="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            java.lang.StringBuilder r2 = r2.append(r9)     // Catch:{ all -> 0x0580 }
            java.lang.String r3 = " WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            r0 = r32
            int r3 = r0.targetUserKodamaId     // Catch:{ all -> 0x0580 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            java.lang.String r30 = r2.toString()     // Catch:{ all -> 0x0580 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0580 }
            r2.<init>()     // Catch:{ all -> 0x0580 }
            java.lang.String r3 = "「"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            r0 = r21
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x0580 }
            java.lang.String r3 = "」のＢＰが"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            r0 = r31
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x0580 }
            java.lang.String r3 = "増えました\n（"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            r3 = 1
            int r3 = r10.getInt(r3)     // Catch:{ all -> 0x0580 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            java.lang.String r3 = "→"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            java.lang.StringBuilder r2 = r2.append(r9)     // Catch:{ all -> 0x0580 }
            java.lang.String r3 = "）。"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0580 }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x0580 }
        L_0x0550:
            if (r10 == 0) goto L_0x00ac
            r10.close()
            goto L_0x00ac
        L_0x0557:
            r2 = 10002(0x2712, float:1.4016E-41)
            r0 = r33
            if (r0 != r2) goto L_0x0561
            r31 = 500(0x1f4, float:7.0E-43)
            goto L_0x04b3
        L_0x0561:
            r2 = 10003(0x2713, float:1.4017E-41)
            r0 = r33
            if (r0 != r2) goto L_0x056b
            r31 = 2000(0x7d0, float:2.803E-42)
            goto L_0x04b3
        L_0x056b:
            r2 = 10004(0x2714, float:1.4019E-41)
            r0 = r33
            if (r0 != r2) goto L_0x0575
            r31 = 10000(0x2710, float:1.4013E-41)
            goto L_0x04b3
        L_0x0575:
            r2 = 10005(0x2715, float:1.402E-41)
            r0 = r33
            if (r0 != r2) goto L_0x04b3
            r31 = 100000(0x186a0, float:1.4013E-40)
            goto L_0x04b3
        L_0x0580:
            r2 = move-exception
            if (r10 == 0) goto L_0x0586
            r10.close()
        L_0x0586:
            throw r2
        L_0x0587:
            r2 = 10011(0x271b, float:1.4028E-41)
            r0 = r33
            if (r0 != r2) goto L_0x0652
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SELECT u.kodama_id, u.name, u.skill FROM user_kodama_t u  WHERE u.user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r32
            int r3 = r0.targetUserKodamaId
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r29 = r2.toString()
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x0644 }
            r3 = 0
            r0 = r29
            android.database.Cursor r10 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x0644 }
            boolean r2 = r10.moveToNext()     // Catch:{ all -> 0x0644 }
            if (r2 == 0) goto L_0x05be
            r2 = 2
            int r2 = r10.getInt(r2)     // Catch:{ all -> 0x0644 }
            if (r2 == 0) goto L_0x05c5
            java.lang.String r6 = "既にスキルを習得済みです。"
        L_0x05be:
            if (r10 == 0) goto L_0x00ac
            r10.close()
            goto L_0x00ac
        L_0x05c5:
            r2 = 1
            java.lang.String r21 = r10.getString(r2)     // Catch:{ all -> 0x0644 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0644 }
            r2.<init>()     // Catch:{ all -> 0x0644 }
            java.lang.String r3 = "SELECT m.learn_id FROM kodama_learn_m m WHERE m.kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0644 }
            r3 = 0
            int r3 = r10.getInt(r3)     // Catch:{ all -> 0x0644 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0644 }
            java.lang.String r3 = " AND m.type=2"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0644 }
            java.lang.String r29 = r2.toString()     // Catch:{ all -> 0x0644 }
            r13 = 0
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x064b }
            r3 = 0
            r0 = r29
            android.database.Cursor r13 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x064b }
            boolean r2 = r13.moveToFirst()     // Catch:{ all -> 0x064b }
            if (r2 == 0) goto L_0x063d
            r2 = 0
            int r22 = r13.getInt(r2)     // Catch:{ all -> 0x064b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x064b }
            r2.<init>()     // Catch:{ all -> 0x064b }
            java.lang.String r3 = "UPDATE user_kodama_t SET skill="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x064b }
            r0 = r22
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x064b }
            java.lang.String r3 = " WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x064b }
            r0 = r32
            int r3 = r0.targetUserKodamaId     // Catch:{ all -> 0x064b }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x064b }
            java.lang.String r30 = r2.toString()     // Catch:{ all -> 0x064b }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x064b }
            r2.<init>()     // Catch:{ all -> 0x064b }
            java.lang.String r3 = "「"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x064b }
            r0 = r21
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x064b }
            java.lang.String r3 = "」が\nスキルを習得しました。"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x064b }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x064b }
        L_0x063d:
            if (r13 == 0) goto L_0x05be
            r13.close()     // Catch:{ all -> 0x0644 }
            goto L_0x05be
        L_0x0644:
            r2 = move-exception
            if (r10 == 0) goto L_0x064a
            r10.close()
        L_0x064a:
            throw r2
        L_0x064b:
            r2 = move-exception
            if (r13 == 0) goto L_0x0651
            r13.close()     // Catch:{ all -> 0x0644 }
        L_0x0651:
            throw r2     // Catch:{ all -> 0x0644 }
        L_0x0652:
            r2 = 10021(0x2725, float:1.4042E-41)
            r0 = r33
            if (r2 > r0) goto L_0x0766
            r2 = 10022(0x2726, float:1.4044E-41)
            r0 = r33
            if (r0 > r2) goto L_0x0766
            r25 = 3
            r2 = 10022(0x2726, float:1.4044E-41)
            r0 = r33
            if (r0 != r2) goto L_0x0668
            r25 = 5
        L_0x0668:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "SELECT u.kodama_id, u.name, u.skill, u.slv FROM user_kodama_t u  WHERE u.user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r32
            int r3 = r0.targetUserKodamaId
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r29 = r2.toString()
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x0701 }
            r3 = 0
            r0 = r29
            android.database.Cursor r10 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x0701 }
            boolean r2 = r10.moveToFirst()     // Catch:{ all -> 0x0701 }
            if (r2 == 0) goto L_0x06b2
            r2 = 2
            int r2 = r10.getInt(r2)     // Catch:{ all -> 0x0701 }
            if (r2 != 0) goto L_0x06b9
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0701 }
            r2.<init>()     // Catch:{ all -> 0x0701 }
            java.lang.String r3 = "「"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0701 }
            r0 = r21
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x0701 }
            java.lang.String r3 = "」は\nまだスキルを習得していません。\nスキル習得には「秘伝の書」が必要です。"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0701 }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x0701 }
        L_0x06b2:
            if (r10 == 0) goto L_0x00ac
            r10.close()
            goto L_0x00ac
        L_0x06b9:
            r2 = 1
            java.lang.String r21 = r10.getString(r2)     // Catch:{ all -> 0x0701 }
            r2 = 3
            int r28 = r10.getInt(r2)     // Catch:{ all -> 0x0701 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0701 }
            r2.<init>()     // Catch:{ all -> 0x0701 }
            java.lang.String r3 = "SELECT m.learn_id FROM kodama_learn_m m WHERE m.kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0701 }
            r3 = 0
            int r3 = r10.getInt(r3)     // Catch:{ all -> 0x0701 }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0701 }
            java.lang.String r3 = " AND m.type=2"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0701 }
            java.lang.String r29 = r2.toString()     // Catch:{ all -> 0x0701 }
            r14 = 0
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x075f }
            r3 = 0
            r0 = r29
            android.database.Cursor r14 = r2.rawQuery(r0, r3)     // Catch:{ all -> 0x075f }
            boolean r2 = r14.moveToFirst()     // Catch:{ all -> 0x075f }
            if (r2 == 0) goto L_0x06fb
            r0 = r25
            r1 = r28
            if (r0 > r1) goto L_0x0708
            java.lang.String r6 = "このアイテムでは\nこれ以上スキルレベルを\n上げられません。"
        L_0x06fb:
            if (r14 == 0) goto L_0x06b2
            r14.close()     // Catch:{ all -> 0x0701 }
            goto L_0x06b2
        L_0x0701:
            r2 = move-exception
            if (r10 == 0) goto L_0x0707
            r10.close()
        L_0x0707:
            throw r2
        L_0x0708:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x075f }
            r2.<init>()     // Catch:{ all -> 0x075f }
            java.lang.String r3 = "UPDATE user_kodama_t SET slv="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            int r3 = r28 + 1
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            java.lang.String r3 = " WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            r0 = r32
            int r3 = r0.targetUserKodamaId     // Catch:{ all -> 0x075f }
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            java.lang.String r30 = r2.toString()     // Catch:{ all -> 0x075f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x075f }
            r2.<init>()     // Catch:{ all -> 0x075f }
            java.lang.String r3 = "「"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            r0 = r21
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x075f }
            java.lang.String r3 = "」の\nスキルレベルが上がりました。\n（"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            r0 = r28
            java.lang.StringBuilder r2 = r2.append(r0)     // Catch:{ all -> 0x075f }
            java.lang.String r3 = "→"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            int r3 = r28 + 1
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            java.lang.String r3 = "）"
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x075f }
            java.lang.String r6 = r2.toString()     // Catch:{ all -> 0x075f }
            goto L_0x06fb
        L_0x075f:
            r2 = move-exception
            if (r14 == 0) goto L_0x0765
            r14.close()     // Catch:{ all -> 0x0701 }
        L_0x0765:
            throw r2     // Catch:{ all -> 0x0701 }
        L_0x0766:
            r2 = 19997(0x4e1d, float:2.8022E-41)
            r0 = r33
            if (r0 != r2) goto L_0x0787
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "UPDATE user_kodama_t SET lv=100, exp=8000000, sb_hp=64, sb_atk=64, sb_def=64, sb_spd=64, slv=5 WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r32
            int r3 = r0.targetUserKodamaId
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r30 = r2.toString()
            java.lang.String r6 = "最強化しました。"
            goto L_0x00ac
        L_0x0787:
            r2 = 19998(0x4e1e, float:2.8023E-41)
            r0 = r33
            if (r0 != r2) goto L_0x07a8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "UPDATE user_kodama_t SET lv=105, exp=9261000, sb_hp=64, sb_atk=64, sb_def=64, sb_spd=64, slv=5 WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r32
            int r3 = r0.targetUserKodamaId
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r30 = r2.toString()
            java.lang.String r6 = "最強化しました。"
            goto L_0x00ac
        L_0x07a8:
            r2 = 19999(0x4e1f, float:2.8025E-41)
            r0 = r33
            if (r0 != r2) goto L_0x00ac
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "UPDATE user_kodama_t SET lv=110, exp=10648000, sb_hp=64, sb_atk=64, sb_def=64, sb_spd=64, slv=5 WHERE user_kodama_id="
            java.lang.StringBuilder r2 = r2.append(r3)
            r0 = r32
            int r3 = r0.targetUserKodamaId
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r30 = r2.toString()
            java.lang.String r6 = "最強化しました。"
            goto L_0x00ac
        L_0x07c9:
            r15 = move-exception
            r15.printStackTrace()     // Catch:{ all -> 0x07dd }
            r0 = r32
            android.database.sqlite.SQLiteDatabase r2 = r0.f220db     // Catch:{ all -> 0x07d6 }
            r2.endTransaction()     // Catch:{ all -> 0x07d6 }
            goto L_0x0131
        L_0x07d6:
            r2 = move-exception
            if (r10 == 0) goto L_0x07dc
            r10.close()
        L_0x07dc:
            throw r2
        L_0x07dd:
            r2 = move-exception
            r0 = r32
            android.database.sqlite.SQLiteDatabase r3 = r0.f220db     // Catch:{ all -> 0x07d6 }
            r3.endTransaction()     // Catch:{ all -> 0x07d6 }
            throw r2     // Catch:{ all -> 0x07d6 }
        L_0x07e6:
            r0 = r32
            org.andengine.audio.sound.Sound r2 = r0.buttonCanceledSound
            r2.play()
            goto L_0x0162
        L_0x07ef:
            kanatamikado.ae.reiki.MultiSceneActivity r2 = r32.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r2 = r2.getResourceUtil()
            java.lang.String r3 = "button/button_use_again_ng.png"
            java.lang.String r4 = "button/button_use_again_ng.png"
            org.andengine.entity.sprite.ButtonSprite r2 = r2.getButtonSprite(r3, r4)
            r0 = r32
            r0.buttonOk = r2
            r0 = r32
            org.andengine.entity.sprite.ButtonSprite r2 = r0.buttonOk
            r3 = 99999997(0x5f5e0fd, float:2.3122337E-35)
            r2.setTag(r3)
            goto L_0x01c5
        */
        throw new UnsupportedOperationException("Method not decompiled: kanatamikado.p006ae.reiki.MenuScene.menuUnitItemExe(int):void");
    }

    private void menuUnitItemExeClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.menuUnitItemDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void menuBpOpen() {
        this.menuMode = 11;
        menuBottomStop();
        int kodamaId = 0;
        String nickName = "";
        int lv = 0;
        int exp = 0;
        int bp = 0;
        String kodamaName = "";
        int kodamaAttr1 = 10;
        int kodamaAttr2 = 10;
        int hp = 0;
        int vp = 0;
        int atk = 0;
        int def = 0;
        int spd = 0;
        for (int i = 0; i < 4; i++) {
            this.f221sb[i] = 0;
        }
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.name, u.lv, u.exp, u.equip, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, m.kodama_name, m.kodama_hp, m.kodama_atk, m.kodama_def, m.kodama_spd, m.kodama_attr1, m.kodama_attr2, i.hp, i.atk, i.def, i.spd FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id LEFT OUTER JOIN item_m i ON u.equip=i.item_id WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToNext()) {
                kodamaId = cursor.getInt(2);
                nickName = cursor.getString(3);
                lv = cursor.getInt(4);
                exp = cursor.getInt(5);
                for (int i2 = 0; i2 < 4; i2++) {
                    this.f221sb[i2] = cursor.getInt(i2 + 7);
                }
                bp = cursor.getInt(11);
                kodamaName = cursor.getString(12);
                int kodamaHp = cursor.getInt(13);
                int kodamaAtk = cursor.getInt(14);
                int kodamaDef = cursor.getInt(15);
                int kodamaSpd = cursor.getInt(16);
                kodamaAttr1 = cursor.getInt(17);
                kodamaAttr2 = cursor.getInt(18);
                int itemHp = cursor.getInt(19);
                int itemVp = 0;
                if (2000 < itemHp) {
                    itemVp = itemHp - 2000;
                    itemHp -= 2000;
                } else if (1000 < itemHp) {
                    itemVp = itemHp + NotificationManagerCompat.IMPORTANCE_UNSPECIFIED;
                    itemHp = 0;
                }
                int itemAtk = cursor.getInt(20);
                hp = ((int) Math.floor((double) (((((kodamaHp + 10) * 2) * lv) / 100) + lv + 10))) + itemHp + this.f221sb[0];
                vp = (100 <= lv ? 100 : ((int) Math.floor((double) (lv / 2))) + 50) + itemVp;
                atk = ((int) Math.floor((double) (((((kodamaAtk + 10) * 2) * lv) / 100) + 5))) + itemAtk + this.f221sb[1];
                def = ((int) Math.floor((double) (((((kodamaDef + 10) * 2) * lv) / 100) + 5))) + cursor.getInt(21) + this.f221sb[2];
                spd = ((int) Math.floor((double) (((((kodamaSpd + 10) * 2) * lv) / 100) + 5))) + cursor.getInt(22) + this.f221sb[3];
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
                popAlert("データ取得エラー", "\n\nmenuBpOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
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
            this.infoText = new Text(200.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) (((((((nickName + "（" + kodamaName + "）\n") + "Lv." + lv + " Exp." + exp + "\n") + "ＨＰ：" + hp + "\n") + "ＶＰ：" + vp + "\n") + "攻撃：" + atk + "\n") + "防御：" + def + "\n") + "速度：" + spd + "\n") + "ＢＰ：" + bp + "\n", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            String str = "";
            for (int i3 = 0; i3 < 4; i3++) {
                String str2 = str + "■" + this.stName[i3] + "：" + this.f221sb[i3] + "/" + 64 + "\n";
                if (this.f221sb[i3] < 64) {
                    str2 = str2 + "　あと" + ((this.f221sb[i3] + 1) * (this.f221sb[i3] + 1)) + "BP";
                }
                str = str2 + "\n\n";
            }
            this.detailText = new Text(40.0f, 320.0f, (IFont) this.fontWhite, (CharSequence) brInsert(str, 22), new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.detailText);
            int posX = 280;
            int posY = 320;
            for (int i4 = 0; i4 < 8; i4++) {
                if (this.f221sb[i4 % 4] <= 0) {
                    if (i4 < 4) {
                        this.buttonList.add(i4, getBaseActivity().getResourceUtil().getButtonSprite("button/arrowL_p.png", "button/arrowL_p.png"));
                    } else {
                        this.buttonList.add(i4, getBaseActivity().getResourceUtil().getButtonSprite("button/arrowLw_p.png", "button/arrowLw_p.png"));
                    }
                    ((ButtonSprite) this.buttonList.get(i4)).setTag(10000);
                } else {
                    if (i4 < 4) {
                        this.buttonList.add(i4, getBaseActivity().getResourceUtil().getButtonSprite("button/arrowL.png", "button/arrowL_p.png"));
                    } else {
                        this.buttonList.add(i4, getBaseActivity().getResourceUtil().getButtonSprite("button/arrowLw.png", "button/arrowLw_p.png"));
                    }
                    ((ButtonSprite) this.buttonList.get(i4)).setTag(i4 + 10060);
                }
                ((ButtonSprite) this.buttonList.get(i4)).setPosition((float) posX, (float) posY);
                ((ButtonSprite) this.buttonList.get(i4)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(i4));
                registerTouchArea((ITouchArea) this.buttonList.get(i4));
                posY += 72;
                if (i4 == 3) {
                    posX = 220;
                    posY = 320;
                }
            }
            int posX2 = 340;
            int posY2 = 320;
            for (int i5 = 8; i5 < 16; i5++) {
                if (64 <= this.f221sb[i5 % 4] || bp < (this.f221sb[i5 % 4] + 1) * (this.f221sb[i5 % 4] + 1)) {
                    if (i5 < 12) {
                        this.buttonList.add(i5, getBaseActivity().getResourceUtil().getButtonSprite("button/arrowR_p.png", "button/arrowR_p.png"));
                    } else {
                        this.buttonList.add(i5, getBaseActivity().getResourceUtil().getButtonSprite("button/arrowRw_p.png", "button/arrowRw_p.png"));
                    }
                    ((ButtonSprite) this.buttonList.get(i5)).setTag(10000);
                } else {
                    if (i5 < 12) {
                        this.buttonList.add(i5, getBaseActivity().getResourceUtil().getButtonSprite("button/arrowR.png", "button/arrowR_p.png"));
                    } else {
                        this.buttonList.add(i5, getBaseActivity().getResourceUtil().getButtonSprite("button/arrowRw.png", "button/arrowRw_p.png"));
                    }
                    ((ButtonSprite) this.buttonList.get(i5)).setTag((i5 + 10070) - 8);
                }
                ((ButtonSprite) this.buttonList.get(i5)).setPosition((float) posX2, (float) posY2);
                ((ButtonSprite) this.buttonList.get(i5)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(i5));
                registerTouchArea((ITouchArea) this.buttonList.get(i5));
                posY2 += 72;
                if (i5 == 11) {
                    posX2 = 400;
                    posY2 = 320;
                }
            }
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonOk.setPosition(170.0f, 800.0f);
            this.buttonOk.setTag(10059);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.menuUnitDetailOpenFlg = true;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuBpClose() {
        this.menuUnitDetailOpenFlg = false;
        this.menuUnitDetailSpellOpenFlg = false;
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.detailText.detachSelf();
        this.unitDetailIcon.detachSelf();
        ((Sprite) this.attrList1.get(0)).detachSelf();
        ((Sprite) this.attrList2.get(0)).detachSelf();
        for (int i = 0; i < 16; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
        }
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
    }

    /* JADX INFO: finally extract failed */
    private void menuBpExe(int act, boolean up) {
        int bp;
        int bp2 = 0;
        int max = 1;
        for (int i = 0; i < 4; i++) {
            this.f221sb[i] = 0;
        }
        if (4 <= act) {
            act -= 4;
            max = 10;
        }
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToNext()) {
                for (int i2 = 0; i2 < 4; i2++) {
                    this.f221sb[i2] = cursor.getInt(i2 + 1);
                }
                bp2 = cursor.getInt(5);
            } else {
                popAlert("データ取得エラー", "\n\nmenuBpExe\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            if (up) {
                int i3 = 0;
                while (true) {
                    if (i3 < max) {
                        if (64 > this.f221sb[act]) {
                            if (bp < (this.f221sb[act] + 1) * (this.f221sb[act] + 1)) {
                                break;
                            }
                            int[] iArr = this.f221sb;
                            iArr[act] = iArr[act] + 1;
                            bp -= this.f221sb[act] * this.f221sb[act];
                            if (bp < 0) {
                                bp = 0;
                            }
                            i3++;
                        } else {
                            this.f221sb[act] = 64;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            } else {
                for (int i4 = 0; i4 < max && this.f221sb[act] > 0; i4++) {
                    int bp3 = bp + (this.f221sb[act] * this.f221sb[act]);
                    if (9999999 < bp3) {
                        bp3 = 9999999;
                    }
                    int[] iArr2 = this.f221sb;
                    iArr2[act] = iArr2[act] - 1;
                }
            }
            String str = "";
            if (act == 0) {
                str = "hp";
            } else if (act == 1) {
                str = "atk";
            } else if (act == 2) {
                str = "def";
            } else if (act == 3) {
                str = "spd";
            }
            this.f220db.execSQL("UPDATE user_kodama_t SET sb_" + str + "=" + this.f221sb[act] + ", bp=" + bp + " WHERE user_kodama_id=" + this.targetUserKodamaId);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuSpellOpen() {
        String str;
        String str2;
        this.menuMode = 11;
        menuBottomStop();
        attachChild(this.informationBox);
        int[] spell = new int[4];
        int kodamaId = 0;
        int bookNum = 0;
        String str3 = "";
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.kodama_id, u.spell1, u.spell2, u.spell3, u.spell4 FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor2.moveToNext()) {
                kodamaId = cursor2.getInt(0);
                for (int i = 1; i < 5; i++) {
                    spell[i - 1] = cursor2.getInt(i);
                }
            } else {
                popAlert("データ取得エラー", "\n\nmenuSpellOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=40001", null);
                if (cursor3.moveToNext()) {
                    bookNum = cursor3.getInt(0);
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                int y = 50;
                for (int i2 = 0; i2 < 4; i2++) {
                    boolean bookFlg = false;
                    if (spell[i2] == 0) {
                        str3 = "未習得";
                    } else {
                        try {
                            cursor3 = this.f220db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, t.text FROM spell_m m LEFT OUTER JOIN spell_text_m t ON m.code=t.code WHERE m.spell_id=" + spell[i2], null);
                            if (cursor3.moveToFirst()) {
                                String str4 = brInsert(cursor3.getString(0), 15) + "\n" + this.attrs[cursor3.getInt(1) - 10] + "属性／";
                                if (cursor3.getInt(2) == 0 || cursor3.getInt(2) == 999) {
                                    str2 = str4 + "威力―";
                                } else {
                                    str2 = str4 + "威力" + cursor3.getInt(2);
                                }
                                str3 = (str2 + "／消費" + cursor3.getInt(3) + "\n") + cursor3.getString(4);
                            } else {
                                popAlert("データ取得エラー", "\n\nmenuSpellOpen/spell_m\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                            }
                            if (cursor3 != null) {
                                cursor3.close();
                            }
                            if (1 <= bookNum) {
                                try {
                                    cursor3 = this.f220db.rawQuery("SELECT m.learn_id FROM kodama_learn_m m WHERE m.kodama_id=" + kodamaId + " AND m.type=1 AND m.learn_id=" + (spell[i2] + 10000), null);
                                    if (cursor3.moveToFirst()) {
                                        bookFlg = true;
                                    }
                                    if (cursor3 != null) {
                                        cursor3.close();
                                    }
                                } catch (Throwable th) {
                                    if (cursor3 != null) {
                                        cursor3.close();
                                    }
                                    throw th;
                                }
                            }
                        } catch (Throwable th2) {
                            if (cursor3 != null) {
                                cursor3.close();
                            }
                            throw th2;
                        }
                    }
                    this.statusList.add(i2, getBaseActivity().getResourceUtil().getSprite("button/button_base_spell.png"));
                    ((Sprite) this.statusList.get(i2)).setPosition(40.0f, (float) y);
                    attachChild((IEntity) this.statusList.get(i2));
                    if (bookFlg) {
                        this.buttonList.add(i2, getBaseActivity().getResourceUtil().getButtonSprite("item/book.png", "item/book.png"));
                        ((ButtonSprite) this.buttonList.get(i2)).setTag(i2 + 10140 + 1);
                    } else {
                        this.buttonList.add(i2, getBaseActivity().getResourceUtil().getButtonSprite("item/clear.png", "item/clear.png"));
                        ((ButtonSprite) this.buttonList.get(i2)).setTag(10080);
                    }
                    ((ButtonSprite) this.buttonList.get(i2)).setPosition(352.0f, (float) y);
                    ((ButtonSprite) this.buttonList.get(i2)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(i2));
                    registerTouchArea((ITouchArea) this.buttonList.get(i2));
                    this.buttonListText[i2] = new Text(54.0f, (float) (y + 10), (IFont) this.fontBlack, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    attachChild(this.buttonListText[i2]);
                    y += 180;
                }
                int y2 = 50;
                int i3 = 4;
                while (i3 < 16) {
                    this.buttonList.add(i3, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base_s.png", "button/button_base_s_p.png"));
                    ((ButtonSprite) this.buttonList.get(i3)).setPosition(400.0f, (float) y2);
                    ((ButtonSprite) this.buttonList.get(i3)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(i3));
                    registerTouchArea((ITouchArea) this.buttonList.get(i3));
                    if (i3 < 8) {
                        if (spell[i3 - 4] == 0) {
                            ((ButtonSprite) this.buttonList.get(i3)).setTag(10000);
                        } else {
                            ((ButtonSprite) this.buttonList.get(i3)).setTag(((i3 + 10080) - 4) + 1);
                        }
                        str = "削除";
                    } else if (i3 < 12) {
                        ((ButtonSprite) this.buttonList.get(i3)).setTag(((i3 + 10100) - 8) + 1);
                        str = "上へ";
                    } else {
                        ((ButtonSprite) this.buttonList.get(i3)).setTag(((i3 + 10110) - 12) + 1);
                        str = "下へ";
                    }
                    this.buttonListText[i3] = new Text(424.0f, (float) (y2 + 16), (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    attachChild(this.buttonListText[i3]);
                    y2 += 180;
                    if (i3 == 7) {
                        y2 = 110;
                    } else if (i3 == 11) {
                        y2 = 170;
                    }
                    i3++;
                }
                this.buttonNum = i3;
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                this.buttonNg.setPosition(280.0f, 800.0f);
                this.buttonNg.setTag(10022);
                this.buttonNg.setOnClickListener(this);
                attachChild(this.buttonNg);
                registerTouchArea(this.buttonNg);
                this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_spell_learn.png", "button/button_spell_learn_p.png");
                this.buttonOk.setTag(10023);
                this.buttonOk.setPosition(60.0f, 800.0f);
                this.buttonOk.setOnClickListener(this);
                attachChild(this.buttonOk);
                registerTouchArea(this.buttonOk);
                arrowOpen(91, 808, false);
                arrowOpen(92, 808, true);
                this.menuUnitDetailOpenFlg = true;
                this.menuUnitDetailSpellOpenFlg = true;
            } catch (Throwable th3) {
                if (cursor2 != null) {
                    cursor2.close();
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

    private void menuSpellClose() {
        this.informationBox.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        for (int i = 0; i < 4; i++) {
            ((Sprite) this.statusList.get(i)).detachSelf();
        }
        for (int i2 = 0; i2 < this.buttonNum; i2++) {
            ((ButtonSprite) this.buttonList.get(i2)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i2));
            this.buttonListText[i2].detachSelf();
        }
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        this.menuUnitDetailOpenFlg = false;
        this.menuUnitDetailSpellOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitBookConfirmOpen(int id) {
        String str;
        String str2 = "";
        int spellId = 0;
        int bookNum = 0;
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT u.spell" + id + " FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToFirst()) {
                spellId = cursor.getInt(0);
            } else {
                popAlert("データ取得エラー", "\n\nmenuUnitBookConfirmOpen_1\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            try {
                Cursor cursor2 = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=40001", null);
                if (cursor2.moveToNext()) {
                    bookNum = cursor2.getInt(0);
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                try {
                    cursor2 = this.f220db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, t.text FROM spell_m m LEFT OUTER JOIN spell_text_m t ON m.code=t.code WHERE m.spell_id=" + (spellId + 10000), null);
                    if (cursor2.moveToFirst()) {
                        String str3 = ("「" + cursor2.getString(0) + "」\n") + this.attrs[cursor2.getInt(1) - 10] + "属性\n";
                        if (cursor2.getInt(2) == 0 || cursor2.getInt(2) == 999) {
                            str = str3 + "威力：―";
                        } else {
                            str = str3 + "威力：" + cursor2.getInt(2);
                        }
                        str2 = ((str + "\n消費ＶＰ：" + cursor2.getInt(3) + "\n") + brInsert(cursor2.getString(4), 22) + "\n\n") + "禁呪の書（残り：" + bookNum + "個）を1つ消費して、\nスペルを強化します。\nよろしいですか？";
                    } else {
                        popAlert("データ取得エラー", "\n\nmenuUnitBookConfirmOpen_2\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    confirmBoxOpen(str2, id + 10150, 10140, true);
                    this.menuUnitDetailOpenFlg = true;
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
        } catch (Throwable th3) {
            if (cursor != null) {
                cursor.close();
            }
            throw th3;
        }
    }

    private void menuUnitBookConfirmClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.menuUnitDetailOpenFlg = false;
        this.menuUnitDetailSpellOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitBookExe(int id) {
        int spellId = 0;
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT u.spell" + id + " FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToFirst()) {
                spellId = cursor.getInt(0) + 10000;
            } else {
                popAlert("データ取得エラー", "\n\nmenuUnitBookExe\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            try {
                cursor = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=40001", null);
                if (cursor.moveToNext()) {
                    int bookNum = cursor.getInt(0) - 1;
                    this.f220db.beginTransaction();
                    this.f220db.execSQL("UPDATE user_item_t SET num=" + bookNum + " WHERE item_id=40001;");
                    this.f220db.execSQL("UPDATE user_kodama_t SET spell" + id + "=" + spellId + " WHERE user_kodama_id=" + this.targetUserKodamaId + ";");
                    this.f220db.setTransactionSuccessful();
                    this.f220db.endTransaction();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                this.f220db.endTransaction();
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
            if (cursor != null) {
                cursor.close();
            }
            menuUnitBookConfirmClose();
            menuSpellOpen();
        } catch (Throwable th2) {
            if (cursor != null) {
                cursor.close();
            }
            throw th2;
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuSpellDeleteConfirmOpen(int id) {
        String str;
        String str2 = "";
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, t.text FROM user_kodama_t u LEFT OUTER JOIN spell_m m ON m.spell_id=u.spell" + id + " LEFT OUTER JOIN spell_text_m t ON m.code=t.code WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToFirst()) {
                String str3 = ("「" + cursor.getString(0) + "」\n") + this.attrs[cursor.getInt(1) - 10] + "属性\n";
                if (cursor.getInt(2) == 0 || cursor.getInt(2) == 999) {
                    str = str3 + "威力：―";
                } else {
                    str = str3 + "威力：" + cursor.getInt(2);
                }
                str2 = (str + "\n消費ＶＰ：" + cursor.getInt(3) + "\n") + brInsert(cursor.getString(4), 22) + "\n\n上記スペルを削除します。\nよろしいですか？";
            } else {
                popAlert("データ取得エラー", "\n\nmenuSpellDeleteConfirmOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            confirmBoxOpen(str2, id + 10120, 10120, true);
            this.menuUnitDetailOpenFlg = true;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuSpellDeleteConfirmClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.menuUnitDetailOpenFlg = false;
    }

    private void menuSpellDeleteConfirmExe(int id) {
        this.f220db.execSQL("UPDATE user_kodama_t SET spell" + id + "=0 WHERE user_kodama_id=" + this.targetUserKodamaId + ";");
        menuSpellDeleteConfirmClose();
        menuSpellOpen();
    }

    /* JADX INFO: finally extract failed */
    private void spellMove(int id, boolean up) {
        int target;
        int[] spell = new int[4];
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.spell1, u.spell2, u.spell3, u.spell4 FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor2.moveToNext()) {
                for (int i = 0; i < 4; i++) {
                    spell[i] = cursor2.getInt(i);
                }
            } else {
                popAlert("データ取得エラー", "\n\nspellMove\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            if (up) {
                target = id - 1;
            } else {
                target = id + 1;
            }
            if (target < 1) {
                target = 4;
            }
            if (4 < target) {
                target = 1;
            }
            this.f220db.execSQL("UPDATE user_kodama_t SET  spell" + id + "=" + spell[target - 1] + ", spell" + target + "=" + spell[id - 1] + " WHERE user_kodama_id=" + this.targetUserKodamaId + ";");
            menuSpellClose();
            menuSpellOpen();
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

    /* JADX INFO: finally extract failed */
    private void menuSpellLearnOpen() {
        this.menuMode = 15;
        int kodamaId = 0;
        int posY = 140;
        float tmpM = (float) 8;
        int[] bsp = new int[10];
        for (int i = 0; i < 10; i++) {
            bsp[i] = 0;
        }
        int[] spell = new int[4];
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT u.spell1, u.spell2, u.spell3, u.spell4, u.kodama_id FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToNext()) {
                for (int i2 = 0; i2 < 4; i2++) {
                    spell[i2] = cursor.getInt(i2);
                }
                kodamaId = cursor.getInt(4);
            } else {
                popAlert("データ取得エラー", "\n\nmenuSpellLearnOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            String sql = "SELECT COUNT(m.learn_id) FROM kodama_learn_m m WHERE m.kodama_id=" + kodamaId + " AND m.type=1 AND m.learn_id<10000";
            for (int i3 = 0; i3 < 4; i3++) {
                if (spell[i3] != 0) {
                    sql = sql + " AND m.learn_id<>" + spell[i3];
                }
            }
            try {
                Cursor cursor2 = this.f220db.rawQuery(sql, null);
                if (cursor2.moveToNext()) {
                    this.listPageMax = (int) Math.ceil((double) (((float) cursor2.getInt(0)) / tmpM));
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                headlineBoxOpen("習得スペル選択（" + (this.listPage + 1) + "/" + this.listPageMax + "）", false);
                int i4 = 0;
                String sql2 = "SELECT m.learn_id FROM kodama_learn_m m WHERE m.kodama_id=" + kodamaId + " AND m.type=1 AND 10000 < m.learn_id";
                Log.d("Debug", sql2);
                try {
                    Cursor cursor3 = this.f220db.rawQuery(sql2, null);
                    while (cursor3.moveToNext()) {
                        bsp[i4] = cursor3.getInt(0);
                        i4++;
                        if (10 <= i4) {
                            break;
                        }
                    }
                    if (cursor3 != null) {
                        cursor3.close();
                    }
                    String sql3 = "SELECT m.learn_id, s.name, s.attr, s.prize FROM kodama_learn_m m LEFT OUTER JOIN spell_m s ON m.learn_id=s.spell_id WHERE m.kodama_id=" + kodamaId + " AND m.type=1 AND m.learn_id<10000";
                    for (int i5 = 0; i5 < 4; i5++) {
                        if (spell[i5] != 0) {
                            sql3 = sql3 + " AND m.learn_id<>" + spell[i5];
                        }
                    }
                    try {
                        Cursor cursor4 = this.f220db.rawQuery(sql3 + " ORDER BY s.attr ASC, s.prize ASC, s.spell_id LIMIT " + (this.listPage * 8) + ", " + 8, null);
                        int i6 = 0;
                        while (cursor4.moveToNext()) {
                            this.buttonList.add(i6, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                            ((ButtonSprite) this.buttonList.get(i6)).setPosition(-500.0f, (float) posY);
                            ((ButtonSprite) this.buttonList.get(i6)).setOnClickListener(this);
                            attachChild((IEntity) this.buttonList.get(i6));
                            registerTouchArea((ITouchArea) this.buttonList.get(i6));
                            ((ButtonSprite) this.buttonList.get(i6)).setTag(10000000 + cursor4.getInt(0));
                            String str = "";
                            int s = 0;
                            while (true) {
                                if (s >= 10) {
                                    break;
                                } else if (bsp[s] == cursor4.getInt(0) + 10000) {
                                    str = str + "☆";
                                    break;
                                } else {
                                    s++;
                                }
                            }
                            String str2 = str + cursor4.getString(1);
                            if (10 < str2.length()) {
                                str2 = str2.substring(0, 10) + "...";
                            }
                            this.buttonListText[i6] = new Text(-486.0f, (float) (posY + 10), (IFont) this.fontBlack, (CharSequence) "【" + this.attrs[cursor4.getInt(2) - 10] + "】" + str2 + "（" + String.format("%,d", new Object[]{Integer.valueOf(cursor4.getInt(3))}) + "銭）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                            attachChild(this.buttonListText[i6]);
                            ((ButtonSprite) this.buttonList.get(i6)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) posY, (float) posY));
                            this.buttonListText[i6].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (posY + 10), (float) (posY + 10)));
                            posY += 64;
                            i6++;
                        }
                        if (cursor4 != null) {
                            cursor4.close();
                        }
                        this.buttonNum = i6;
                        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                        placeToCenterX(this.buttonNg, 678.0f);
                        this.buttonNg.setTag(10000000);
                        this.buttonNg.setOnClickListener(this);
                        attachChild(this.buttonNg);
                        registerTouchArea(this.buttonNg);
                        arrowOpen(91, 686, false);
                        arrowOpen(92, 686, true);
                        menuBottomStart();
                    } catch (Throwable th) {
                        if (cursor3 != null) {
                            cursor3.close();
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th2;
                }
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

    private void menuSpellLearnClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
    }

    /* JADX INFO: finally extract failed */
    private void menuSpellLearnConfirmOpen(int id) {
        String str;
        String str2;
        menuBottomStop();
        String str3 = "";
        int[] spell = new int[4];
        int kodamaId = 0;
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT u.spell1, u.spell2, u.spell3, u.spell4, u.kodama_id FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToNext()) {
                for (int i = 0; i < 4; i++) {
                    spell[i] = cursor.getInt(i);
                }
                kodamaId = cursor.getInt(4);
            } else {
                popAlert("データ取得エラー", "\n\nmenuSpellLearnConfirmOpen_1\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            int spellPrize = 99999999;
            try {
                cursor = this.f220db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, m.prize, t.text FROM spell_m m LEFT OUTER JOIN spell_text_m t ON m.code=t.code WHERE m.spell_id=" + id, null);
                if (cursor.moveToFirst()) {
                    String str4 = ("「" + cursor.getString(0) + "」\n") + this.attrs[cursor.getInt(1) - 10] + "属性\n";
                    if (cursor.getInt(2) == 0 || cursor.getInt(2) == 999) {
                        str2 = str4 + "威力：―";
                    } else {
                        str2 = str4 + "威力：" + cursor.getInt(2);
                    }
                    str3 = ((str2 + "\n消費ＶＰ：" + cursor.getInt(3) + "\n") + brInsert(cursor.getString(5), 20) + "\n\n") + cursor.getInt(4) + "銭を消費して、\n上記スペルを習得します。\nよろしいですか？";
                    spellPrize = cursor.getInt(4);
                } else {
                    popAlert("データ取得エラー", "\n\nmenuUnitSeparateConfirmOpen_2\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (id < 10000) {
                    boolean learnFlg = false;
                    try {
                        Cursor cursor2 = this.f220db.rawQuery("SELECT l.kodama_id FROM kodama_learn_m l WHERE l.type=1 AND l.kodama_id=" + kodamaId + " AND l.learn_id=" + (id + 10000), null);
                        while (true) {
                            if (cursor2.moveToFirst()) {
                                if (cursor2.getInt(0) == kodamaId) {
                                    learnFlg = true;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (learnFlg) {
                            try {
                                cursor2 = this.f220db.rawQuery("SELECT m.name, m.attr, m.atk, m.vp, m.prize, t.text FROM spell_m m LEFT OUTER JOIN spell_text_m t ON m.code=t.code WHERE m.spell_id=" + (id + 10000), null);
                                if (cursor2.moveToFirst()) {
                                    String str5 = (str3 + "\n\n\n※習得後に「禁呪の書」を使用すると\n　性能が変化します。\n\n▽禁呪性能\n") + this.attrs[cursor2.getInt(1) - 10] + "属性\n";
                                    if (cursor2.getInt(2) == 0 || cursor2.getInt(2) == 999) {
                                        str = str5 + "威力：―";
                                    } else {
                                        str = str5 + "威力：" + cursor2.getInt(2);
                                    }
                                    str3 = (str + "\n消費ＶＰ：" + cursor2.getInt(3) + "\n") + brInsert(cursor2.getString(5), 20) + "\n\n";
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
                    } catch (Throwable th2) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th2;
                    }
                }
                attachChild(this.informationBox);
                this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.infoText);
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
                placeToCenterX(this.buttonNg, 750.0f);
                this.buttonNg.setTag(10000000);
                this.buttonNg.setOnClickListener(this);
                attachChild(this.buttonNg);
                registerTouchArea(this.buttonNg);
                if (spell[0] != 0 && spell[1] != 0 && spell[2] != 0 && spell[3] != 0) {
                    this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_spell_limit.png", "button/button_spell_limit.png");
                    this.buttonOk.setTag(99999997);
                } else if (this.userMoney < ((long) spellPrize)) {
                    this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_learn_money_ng.png", "button/button_learn_money_ng.png");
                    this.buttonOk.setTag(99999997);
                } else {
                    this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
                    this.buttonOk.setTag(10000000 + id);
                }
                placeToCenterX(this.buttonOk, 650.0f);
                this.buttonOk.setOnClickListener(this);
                attachChild(this.buttonOk);
                registerTouchArea(this.buttonOk);
                this.menuSpellLearnConfirmFlg = true;
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

    private void menuSpellLearnConfirmClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.menuSpellLearnConfirmFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void menuSpellLearnConfirmExe(int id) {
        int spellPrize = 99999999;
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT m.prize FROM spell_m m WHERE m.spell_id=" + id, null);
            if (cursor.moveToFirst()) {
                spellPrize = cursor.getInt(0);
            } else {
                popAlert("データ取得エラー", "\n\nmenuSpellLearnConfirmExe\nspell_m\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            int target = -1;
            try {
                cursor = this.f220db.rawQuery("SELECT u.spell1, u.spell2, u.spell3, u.spell4 FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
                if (cursor.moveToNext()) {
                    int i = 0;
                    while (true) {
                        if (i >= 4) {
                            break;
                        } else if (cursor.getInt(i) == 0) {
                            target = i;
                            break;
                        } else {
                            i++;
                        }
                    }
                } else {
                    popAlert("データ取得エラー", "\n\nmenuSpellLearnConfirmExe\nuser_kodama_t\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (target >= 0) {
                    this.f220db.beginTransaction();
                    try {
                        this.userMoney -= (long) spellPrize;
                        if (this.userMoney < 0) {
                            this.userMoney = 0;
                        }
                        this.f220db.execSQL("UPDATE user_t SET user_money=" + this.userMoney + ";");
                        this.f220db.execSQL("UPDATE user_kodama_t SET spell" + (target + 1) + "=" + id + " WHERE user_kodama_id=" + this.targetUserKodamaId + ";");
                        this.f220db.setTransactionSuccessful();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        this.f220db.endTransaction();
                    }
                }
                this.moneyText.detachSelf();
                this.moneyText = new Text(0.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) String.format("%,d", new Object[]{Long.valueOf(this.userMoney)}) + "銭", new TextOptions(HorizontalAlign.RIGHT), getBaseActivity().getVertexBufferObjectManager());
                this.moneyText.setPosition((540.0f - this.moneyText.getWidth()) - 6.0f, 2.0f);
                attachChild(this.moneyText);
                this.moneyImg.setPosition(540.0f - (this.moneyText.getWidth() + 46.0f), -4.0f);
                menuSpellLearnConfirmClose();
                menuSpellOpen();
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
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

    private void menuUnitProtectExe(int act) {
        this.f220db.execSQL("UPDATE user_kodama_t SET protect_flg=" + act + " WHERE user_kodama_id=" + this.targetUserKodamaId);
    }

    /* JADX INFO: finally extract failed */
    private void menuUnitSeparateConfirmOpen() {
        menuBottomStop();
        int kodamaId = 1;
        String kodamaName = "empty";
        Cursor cursor = null;
        try {
            cursor = this.f220db.rawQuery("SELECT u.kodama_id, m.kodama_name FROM user_kodama_t u  LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor.moveToNext()) {
                kodamaId = cursor.getInt(0);
                kodamaName = cursor.getString(1);
            } else {
                popAlert("データ取得エラー", "\n\nmenuUnitSeparateConfirmOpen\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor != null) {
                cursor.close();
            }
            attachChild(this.informationBox);
            this.unitDetailIcon = getBaseActivity().getResourceUtil().getSprite("kodama/" + kodamaId + ".png");
            placeToCenterX(this.unitDetailIcon, 100.0f);
            attachChild(this.unitDetailIcon);
            this.infoText = new Text(40.0f, 300.0f, (IFont) this.fontWhite, (CharSequence) "「" + kodamaName + "」の契約を解除します。\n契約を解除したコダマは\nいなくなってしまいます。\nよろしいですか？\n\n※コダマを１体しか所持していない時は\n　契約を解除できません。", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
            placeToCenterX(this.buttonNg, 750.0f);
            this.buttonNg.setTag(10003);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
            placeToCenterX(this.buttonOk, 650.0f);
            this.buttonOk.setTag(10002);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.menuUnitDetailOpenFlg = true;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuUnitSeparateExe() {
        String str = "";
        Cursor cursor2 = null;
        boolean cursor22 = null;
        try {
            cursor2 = this.f220db.rawQuery("SELECT u.equip FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            cursor22 = cursor2.moveToFirst();
            if (cursor22) {
                int orgItem = cursor2.getInt(0);
                cursor22 = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + orgItem, null);
                cursor22 = cursor22.moveToNext();
                if (cursor22) {
                    this.f220db.execSQL("UPDATE user_item_t SET num=" + (cursor22.getInt(0) + 1) + " WHERE item_id=" + orgItem + ";");
                } else if (orgItem != 0) {
                    this.f220db.execSQL("INSERT INTO user_item_t VALUES (" + orgItem + ", 1);");
                }
                if (cursor22 != null) {
                    cursor22.close();
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            this.f220db.execSQL("UPDATE user_kodama_t SET kodama_id=0 WHERE user_kodama_id=" + this.targetUserKodamaId);
            this.unitNum--;
            this.informationBox.detachSelf();
            this.unitDetailIcon.detachSelf();
            this.infoText.detachSelf();
            this.buttonNg.detachSelf();
            unregisterTouchArea(this.buttonNg);
            this.buttonOk.detachSelf();
            unregisterTouchArea(this.buttonOk);
            this.menuUnitDetailOpenFlg = false;
            menuUnitOpen(true);
            menuBottomStart();
        } catch (Throwable th) {
            if (cursor22 != null) {
                cursor22.close();
            }
            throw th;
        } finally {
            if (cursor22 != null) {
                cursor22.close();
            }
        }
    }

    private void menuUnitSeparateConfirmClose() {
        this.informationBox.detachSelf();
        this.unitDetailIcon.detachSelf();
        this.infoText.detachSelf();
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.menuUnitDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void menuEquipSelectOpen() {
        this.menuMode = 12;
        Cursor cursor = null;
        this.listPageMax = 0;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT count(u.item_id) FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id<>0 AND 0 < u.num AND 100 < m.type AND m.type < 300", null);
            if (cursor2.moveToFirst()) {
                this.listPageMax = (int) Math.ceil((double) (((float) cursor2.getInt(0)) / ((float) 8)));
                if (this.listPageMax == 0) {
                    this.listPageMax = 1;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            headlineBoxOpen("装備選択（ページ" + (this.listPage + 1) + "／" + this.listPageMax + "）", true);
            int no = 0;
            int height = 130;
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT u.item_id, u.num, m.name, m.text FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE 0 < u.num AND 100 < m.type AND m.type < 300 ORDER BY m.type DESC, m.kana ASC LIMIT " + (this.listPage * 8) + ", " + 8, null);
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
                    ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) height, (float) height));
                    this.buttonListText[no].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (height + 10), (float) (height + 10)));
                    height += 64;
                    no++;
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                this.buttonNum = no;
                arrowOpen(91, 686, false);
                arrowOpen(92, 686, true);
                this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                this.buttonEtc1.setTag(10000000);
                this.buttonEtc1.setPosition(275.0f, 678.0f);
                this.buttonEtc1.setOnClickListener(this);
                attachChild(this.buttonEtc1);
                registerTouchArea(this.buttonEtc1);
                this.buttonEtc2 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_off.png", "button/button_off_p.png");
                this.buttonEtc2.setTag(20000000);
                this.buttonEtc2.setPosition(65.0f, 678.0f);
                this.buttonEtc2.setOnClickListener(this);
                attachChild(this.buttonEtc2);
                registerTouchArea(this.buttonEtc2);
                menuBottomStart();
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
        menuBottomStop();
        String str = "";
        Cursor cursor22 = null;
        try {
            cursor22 = this.f220db.rawQuery("SELECT u.item_id, u.num, m.name, m.text FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id=" + id, null);
            cursor2 = cursor22.moveToNext();
            if (cursor2) {
                str = "■" + cursor22.getString(2) + "（" + cursor22.getInt(1) + "個）\n" + brInsert(cursor22.getString(3), 18);
                if (100001 <= id && id <= 200000) {
                    cursor2 = null;
                    Cursor cursor23 = this.f220db.rawQuery("SELECT c.kodama_id, m.kodama_name, m.kodama_attr1, m.kodama_attr2 FROM card_m c LEFT OUTER JOIN kodama_m m ON c.kodama_id=m.kodama_id WHERE c.item_id=" + id, null);
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
            this.menuEquipDetailOpenFlg = true;
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
        this.menuEquipDetailOpenFlg = false;
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
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.user_kodama_id, u.equip FROM user_kodama_t u WHERE u.user_kodama_id=" + this.targetUserKodamaId, null);
            if (cursor2.moveToFirst()) {
                orgItem = cursor2.getInt(1);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            int orgNum = 0;
            if (orgItem != 0) {
                try {
                    cursor2 = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + orgItem, null);
                    if (cursor2.moveToFirst()) {
                        orgNum = cursor2.getInt(0);
                    } else {
                        orgItem = 0;
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
            int itemNum = 0;
            if (itemId != 0) {
                try {
                    Cursor cursor3 = this.f220db.rawQuery("SELECT u.num FROM user_item_t u WHERE u.item_id=" + itemId, null);
                    if (cursor3.moveToFirst()) {
                        itemNum = cursor3.getInt(0);
                    }
                    if (cursor3 != null) {
                        cursor3.close();
                    }
                } catch (Throwable th2) {
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th2;
                }
            }
            if (itemId != orgItem && (itemId == 0 || itemNum > 0)) {
                this.f220db.beginTransaction();
                if (orgItem != 0) {
                    int orgNum2 = orgNum + 1;
                    if (999999 < orgNum2) {
                        orgNum2 = 999999;
                    }
                    try {
                        this.f220db.execSQL("UPDATE user_item_t SET num=" + orgNum2 + " WHERE item_id=" + orgItem + ";");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        this.f220db.endTransaction();
                    } catch (Throwable th3) {
                        this.f220db.endTransaction();
                        throw th3;
                    }
                }
                if (itemId != 0) {
                    int itemNum2 = itemNum - 1;
                    if (itemNum2 < 0) {
                        itemNum2 = 0;
                    }
                    this.f220db.execSQL("UPDATE user_item_t SET num=" + itemNum2 + " WHERE item_id=" + itemId + ";");
                }
                this.f220db.execSQL("UPDATE user_kodama_t SET equip=" + itemId + " WHERE user_kodama_id=" + this.targetUserKodamaId + ";");
                this.f220db.setTransactionSuccessful();
                this.f220db.endTransaction();
            }
            if (itemId == 0) {
                menuEquipSelectClose();
            } else {
                menuEquipDetailClose();
            }
            menuUnitDetailOpen();
        } catch (Throwable th4) {
            if (cursor != null) {
                cursor.close();
            }
            throw th4;
        }
    }

    private void menuItemOpen() {
        this.menuMode = 21;
        headlineBoxOpen("アイテム", true);
        int height = 130;
        for (int i = 0; i < 6; i++) {
            this.buttonList.add(i, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
            ((ButtonSprite) this.buttonList.get(i)).setPosition(-500.0f, (float) height);
            ((ButtonSprite) this.buttonList.get(i)).setTag(10000000 + i);
            ((ButtonSprite) this.buttonList.get(i)).setOnClickListener(this);
            attachChild((IEntity) this.buttonList.get(i));
            registerTouchArea((ITouchArea) this.buttonList.get(i));
            ((ButtonSprite) this.buttonList.get(i)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) height, (float) height));
            height += 64;
        }
        this.buttonListText[0] = new Text(-486.0f, 196.0f, (IFont) this.fontBlack, (CharSequence) "霊石", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[1] = new Text(-486.0f, 260.0f, (IFont) this.fontBlack, (CharSequence) "装備/コダマカード", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[2] = new Text(-486.0f, 324.0f, (IFont) this.fontBlack, (CharSequence) "福袋/その他", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[3] = new Text(-486.0f, 388.0f, (IFont) this.fontBlack, (CharSequence) "育成用アイテム", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[4] = new Text(-486.0f, 452.0f, (IFont) this.fontBlack, (CharSequence) "霊珠", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[5] = new Text(-486.0f, 516.0f, (IFont) this.fontBlack, (CharSequence) "アイテムショップ", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        for (int i2 = 0; i2 < 6; i2++) {
            attachChild(this.buttonListText[i2]);
            this.buttonListText[i2].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) ((i2 * 64) + 140), (float) ((i2 * 64) + 140)));
        }
        menuBottomStart();
    }

    private void menuItemClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < 6; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuItemListOpen() {
        String sql;
        this.menuMode = 22;
        menuBottomStart();
        String sqlWhere = "";
        String str = "";
        int limit = 8;
        Cursor cursor = null;
        if (this.listHead == 0) {
            limit = 7;
        }
        if (this.listHead == 0) {
            sqlWhere = "(20000 < u.item_id AND u.item_id < 30000)";
        } else if (this.listHead == 1) {
            sqlWhere = "(100000 < u.item_id AND u.item_id < 300000)";
        } else if (this.listHead == 2) {
            sqlWhere = "((30000 < u.item_id AND u.item_id < 40000) OR (50000 < u.item_id AND u.item_id < 100000))";
        } else if (this.listHead == 3) {
            sqlWhere = "((10000 < u.item_id AND u.item_id < 20000) OR (40000 < u.item_id AND u.item_id < 50000))";
        } else if (this.listHead == 4) {
            sqlWhere = "u.item_id < 10000";
        }
        this.listPageMax = 0;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT count(u.item_id) FROM user_item_t u WHERE 0 < u.num AND u.item_id<>0 AND " + sqlWhere, null);
            if (cursor2.moveToFirst()) {
                this.listPageMax = (int) Math.floor((double) ((((float) cursor2.getInt(0)) - 1.0f) / ((float) limit)));
                if (this.listPageMax < 0) {
                    this.listPageMax = 0;
                }
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            if (this.listHead == 0) {
                str = "霊石";
            } else if (this.listHead == 1) {
                str = "装備/コダマカード";
            } else if (this.listHead == 2) {
                str = "福袋/その他";
            } else if (this.listHead == 3) {
                str = "育成用アイテム";
            } else if (this.listHead == 4) {
                str = "霊珠";
            }
            headlineBoxOpen(str + "（" + (this.listPage + 1) + "／" + (this.listPageMax + 1) + "）", true);
            int no = 0;
            int height = 130;
            if (this.listHead == 0) {
                this.buttonList.add(0, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                ((ButtonSprite) this.buttonList.get(0)).setPosition(-500.0f, (float) 130);
                ((ButtonSprite) this.buttonList.get(0)).setTag(10029999);
                ((ButtonSprite) this.buttonList.get(0)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(0));
                registerTouchArea((ITouchArea) this.buttonList.get(0));
                this.buttonListText[0] = new Text(-486.0f, (float) 140, (IFont) this.fontBlack, (CharSequence) "霊石をまとめて売却", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.buttonListText[0]);
                ((ButtonSprite) this.buttonList.get(0)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) 130, (float) 130));
                this.buttonListText[0].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) 140, (float) 140));
                height = 130 + 64;
                no = 0 + 1;
            }
            String sql2 = "SELECT u.item_id, u.num, m.name, m.text FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id <> 0 AND 0 < u.num AND " + sqlWhere;
            if (this.listHead == 1) {
                sql = sql2 + " ORDER BY m.type DESC, m.kana ASC";
            } else {
                sql = sql2 + " ORDER BY u.item_id ASC";
            }
            try {
                Cursor cursor3 = this.f220db.rawQuery(sql + " LIMIT " + (this.listPage * limit) + ", " + limit, null);
                while (cursor3.moveToNext()) {
                    this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                    ((ButtonSprite) this.buttonList.get(no)).setPosition(-500.0f, (float) height);
                    ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + cursor3.getInt(0));
                    ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(no));
                    registerTouchArea((ITouchArea) this.buttonList.get(no));
                    this.buttonListText[no] = new Text(-486.0f, (float) (height + 10), (IFont) this.fontBlack, (CharSequence) cursor3.getString(2) + "（" + cursor3.getInt(1) + "個）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    attachChild(this.buttonListText[no]);
                    ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) height, (float) height));
                    this.buttonListText[no].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (height + 10), (float) (height + 10)));
                    height += 64;
                    no++;
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                this.buttonNum = no;
                arrowOpen(91, 686, false);
                arrowOpen(92, 686, true);
                this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                this.buttonEtc1.setTag(21);
                placeToCenterX(this.buttonEtc1, 678.0f);
                this.buttonEtc1.setOnClickListener(this);
                attachChild(this.buttonEtc1);
                registerTouchArea(this.buttonEtc1);
                if (this.userDemoNo == 11) {
                    this.userDemoNo = 12;
                    this.unitDetailIcon = getBaseActivity().getResourceUtil().getSprite("item/arrow.png");
                    this.unitDetailIcon.setPosition(420.0f, 755.0f);
                    attachChild(this.unitDetailIcon);
                    confirmBoxOpen("コダマカードを使うと、\n新しいコダマと契約できます。\n\nそれではコダマカードを\n使用してみましょう。", 99999996, 0, false);
                } else if (this.userDemoNo == 19) {
                    this.userDemoNo = 20;
                    this.unitDetailIcon = getBaseActivity().getResourceUtil().getSprite("item/arrow.png");
                    this.unitDetailIcon.setPosition(302.0f, 755.0f);
                    attachChild(this.unitDetailIcon);
                    confirmBoxOpen("新しいコダマを入手したら、\n編成画面でパーティーに\n組み込みましょう。", 99999996, 0, false);
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
    }

    private void menuItemListClose() {
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
    }

    /* JADX INFO: finally extract failed */
    private void menuShopOpen() {
        this.menuMode = 23;
        menuBottomStart();
        Cursor cursor = null;
        this.listPageMax = (int) Math.ceil((double) (((float) this.shopNum) / ((float) 8)));
        if (this.listPageMax == 0) {
            this.listPageMax = 1;
        }
        headlineBoxOpen("アイテムショップ（" + (this.listPage + 1) + "／" + this.listPageMax + "）", true);
        int no = 0;
        int height = 130;
        String sql = "SELECT m.item_id, m.prize, m.name FROM item_m m WHERE m.item_id=" + this.shopList[this.listPage * 8];
        for (int i = (this.listPage * 8) + 1; i < (this.listPage + 1) * 8; i++) {
            if (this.shopList[i] != 0) {
                sql = sql + " OR m.item_id=" + this.shopList[i];
            }
        }
        try {
            cursor = this.f220db.rawQuery(sql, null);
            int i2 = this.listPage * 8;
            while (i2 < (this.listPage + 1) * 8 && this.shopList[i2] != 0) {
                this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                ((ButtonSprite) this.buttonList.get(no)).setPosition(-500.0f, (float) height);
                ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + this.shopList[i2]);
                ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(no));
                registerTouchArea((ITouchArea) this.buttonList.get(no));
                cursor.moveToPosition(-1);
                while (true) {
                    if (cursor.moveToNext()) {
                        if (cursor.getInt(0) == this.shopList[i2]) {
                            this.buttonListText[no] = new Text(-486.0f, (float) (height + 10), (IFont) this.fontBlack, (CharSequence) cursor.getString(2) + "（" + String.format("%,d", new Object[]{Integer.valueOf(cursor.getInt(1))}) + "銭）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                            attachChild(this.buttonListText[no]);
                            break;
                        }
                    } else {
                        break;
                    }
                }
                ((ButtonSprite) this.buttonList.get(no)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) height, (float) height));
                this.buttonListText[no].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (height + 10), (float) (height + 10)));
                height += 64;
                no++;
                i2++;
            }
            if (cursor != null) {
                cursor.close();
            }
            this.buttonNum = no;
            arrowOpen(91, 686, false);
            arrowOpen(92, 686, true);
            this.buttonEtc1 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            this.buttonEtc1.setTag(21);
            placeToCenterX(this.buttonEtc1, 678.0f);
            this.buttonEtc1.setOnClickListener(this);
            attachChild(this.buttonEtc1);
            registerTouchArea(this.buttonEtc1);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuShopClose() {
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
    }

    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01de, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01e4, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void menuShopConfirmOpen(int r15) {
        /*
            r14 = this;
            r1 = 1114636288(0x42700000, float:60.0)
            r13 = 1
            r14.menuBottomStop()
            org.andengine.entity.sprite.Sprite r0 = r14.informationBox
            r14.attachChild(r0)
            java.lang.String r4 = ""
            r10 = 999999999(0x3b9ac9ff, double:4.940656453E-315)
            r9 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "SELECT m.item_id, m.prize, m.name, m.text FROM item_m m WHERE m.item_id="
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r15)
            java.lang.String r12 = r0.toString()
            r7 = 0
            android.database.sqlite.SQLiteDatabase r0 = r14.f220db     // Catch:{ all -> 0x01d7 }
            r2 = 0
            android.database.Cursor r7 = r0.rawQuery(r12, r2)     // Catch:{ all -> 0x01d7 }
            boolean r0 = r7.moveToNext()     // Catch:{ all -> 0x01d7 }
            if (r0 == 0) goto L_0x0142
            r8 = 0
            r0 = 1
            int r0 = r7.getInt(r0)     // Catch:{ all -> 0x01d7 }
            long r10 = (long) r0     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d7 }
            r0.<init>()     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = "■"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            r2 = 2
            java.lang.String r2 = r7.getString(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = "（"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = "%,d"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x01d7 }
            r5 = 0
            java.lang.Long r6 = java.lang.Long.valueOf(r10)     // Catch:{ all -> 0x01d7 }
            r3[r5] = r6     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = java.lang.String.format(r2, r3)     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = "銭）\n"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            r2 = 3
            java.lang.String r2 = r7.getString(r2)     // Catch:{ all -> 0x01d7 }
            r3 = 18
            java.lang.String r2 = r14.brInsert(r2, r3)     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x01d7 }
            r0 = 100000(0x186a0, float:1.4013E-40)
            if (r0 >= r15) goto L_0x00fb
            r0 = 200000(0x30d40, float:2.8026E-40)
            if (r15 >= r0) goto L_0x00fb
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d7 }
            r0.<init>()     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = "SELECT c.kodama_id, m.kodama_name, m.kodama_attr1, m.kodama_attr2 FROM card_m c LEFT OUTER JOIN kodama_m m ON c.kodama_id=m.kodama_id WHERE c.item_id="
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r0 = r0.append(r15)     // Catch:{ all -> 0x01d7 }
            java.lang.String r12 = r0.toString()     // Catch:{ all -> 0x01d7 }
            android.database.sqlite.SQLiteDatabase r0 = r14.f220db     // Catch:{ all -> 0x01d0 }
            r2 = 0
            android.database.Cursor r8 = r0.rawQuery(r12, r2)     // Catch:{ all -> 0x01d0 }
            boolean r0 = r8.moveToNext()     // Catch:{ all -> 0x01d0 }
            if (r0 == 0) goto L_0x00f6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d0 }
            r0.<init>()     // Catch:{ all -> 0x01d0 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x01d0 }
            java.lang.String r2 = "\n\nアイテムとして使用すると、\n「"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d0 }
            r2 = 1
            java.lang.String r2 = r8.getString(r2)     // Catch:{ all -> 0x01d0 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d0 }
            java.lang.String r2 = "」（"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d0 }
            java.lang.String[] r2 = r14.attrs     // Catch:{ all -> 0x01d0 }
            r3 = 2
            int r3 = r8.getInt(r3)     // Catch:{ all -> 0x01d0 }
            int r3 = r3 + -10
            r2 = r2[r3]     // Catch:{ all -> 0x01d0 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d0 }
            java.lang.String r2 = "／"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d0 }
            java.lang.String[] r2 = r14.attrs     // Catch:{ all -> 0x01d0 }
            r3 = 3
            int r3 = r8.getInt(r3)     // Catch:{ all -> 0x01d0 }
            int r3 = r3 + -10
            r2 = r2[r3]     // Catch:{ all -> 0x01d0 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d0 }
            java.lang.String r2 = "）\nのコダマと契約できます。"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d0 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x01d0 }
        L_0x00f6:
            if (r8 == 0) goto L_0x00fb
            r8.close()     // Catch:{ all -> 0x01d7 }
        L_0x00fb:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d7 }
            r0.<init>()     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = "SELECT u.item_id, u.num FROM user_item_t u WHERE u.item_id="
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r0 = r0.append(r15)     // Catch:{ all -> 0x01d7 }
            java.lang.String r12 = r0.toString()     // Catch:{ all -> 0x01d7 }
            android.database.sqlite.SQLiteDatabase r0 = r14.f220db     // Catch:{ all -> 0x01de }
            r2 = 0
            android.database.Cursor r8 = r0.rawQuery(r12, r2)     // Catch:{ all -> 0x01de }
            boolean r0 = r8.moveToNext()     // Catch:{ all -> 0x01de }
            if (r0 == 0) goto L_0x0120
            r0 = 1
            int r9 = r8.getInt(r0)     // Catch:{ all -> 0x01de }
        L_0x0120:
            if (r8 == 0) goto L_0x0125
            r8.close()     // Catch:{ all -> 0x01d7 }
        L_0x0125:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d7 }
            r0.<init>()     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = "\n\n現在は"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.StringBuilder r0 = r0.append(r9)     // Catch:{ all -> 0x01d7 }
            java.lang.String r2 = "個所持しています。"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01d7 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x01d7 }
        L_0x0142:
            if (r7 == 0) goto L_0x0147
            r7.close()
        L_0x0147:
            org.andengine.entity.text.Text r0 = new org.andengine.entity.text.Text
            org.andengine.opengl.font.Font r3 = r14.fontWhite
            org.andengine.entity.text.TextOptions r5 = new org.andengine.entity.text.TextOptions
            org.andengine.util.HorizontalAlign r2 = org.andengine.util.HorizontalAlign.LEFT
            r5.<init>(r2)
            kanatamikado.ae.reiki.MultiSceneActivity r2 = r14.getBaseActivity()
            org.andengine.opengl.vbo.VertexBufferObjectManager r6 = r2.getVertexBufferObjectManager()
            r2 = r1
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r14.infoText = r0
            org.andengine.entity.text.Text r0 = r14.infoText
            r14.attachChild(r0)
            long r0 = r14.userMoney
            int r0 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1))
            if (r0 >= 0) goto L_0x01e5
            kanatamikado.ae.reiki.MultiSceneActivity r0 = r14.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r0 = r0.getResourceUtil()
            java.lang.String r1 = "button/button_buy_ng.png"
            java.lang.String r2 = "button/button_buy_ng.png"
            org.andengine.entity.sprite.ButtonSprite r0 = r0.getButtonSprite(r1, r2)
            r14.buttonNg = r0
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonNg
            r1 = 99999997(0x5f5e0fd, float:2.3122337E-35)
            r0.setTag(r1)
        L_0x0185:
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonNg
            r1 = 1143111680(0x44228000, float:650.0)
            r14.placeToCenterX(r0, r1)
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonNg
            r0.setOnClickListener(r14)
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonNg
            r14.attachChild(r0)
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonNg
            r14.registerTouchArea(r0)
            kanatamikado.ae.reiki.MultiSceneActivity r0 = r14.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r0 = r0.getResourceUtil()
            java.lang.String r1 = "button/button_close.png"
            java.lang.String r2 = "button/button_close_p.png"
            org.andengine.entity.sprite.ButtonSprite r0 = r0.getButtonSprite(r1, r2)
            r14.buttonOk = r0
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonOk
            r1 = 1144750080(0x443b8000, float:750.0)
            r14.placeToCenterX(r0, r1)
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonOk
            r1 = 10000000(0x989680, float:1.4012985E-38)
            r0.setTag(r1)
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonOk
            r0.setOnClickListener(r14)
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonOk
            r14.attachChild(r0)
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonOk
            r14.registerTouchArea(r0)
            r14.menuShopDetailOpenFlg = r13
            return
        L_0x01d0:
            r0 = move-exception
            if (r8 == 0) goto L_0x01d6
            r8.close()     // Catch:{ all -> 0x01d7 }
        L_0x01d6:
            throw r0     // Catch:{ all -> 0x01d7 }
        L_0x01d7:
            r0 = move-exception
            if (r7 == 0) goto L_0x01dd
            r7.close()
        L_0x01dd:
            throw r0
        L_0x01de:
            r0 = move-exception
            if (r8 == 0) goto L_0x01e4
            r8.close()     // Catch:{ all -> 0x01d7 }
        L_0x01e4:
            throw r0     // Catch:{ all -> 0x01d7 }
        L_0x01e5:
            kanatamikado.ae.reiki.MultiSceneActivity r0 = r14.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r0 = r0.getResourceUtil()
            java.lang.String r1 = "button/button_buy.png"
            java.lang.String r2 = "button/button_buy.png"
            org.andengine.entity.sprite.ButtonSprite r0 = r0.getButtonSprite(r1, r2)
            r14.buttonNg = r0
            org.andengine.entity.sprite.ButtonSprite r0 = r14.buttonNg
            r1 = 10000000(0x989680, float:1.4012985E-38)
            int r1 = r1 + r15
            r0.setTag(r1)
            goto L_0x0185
        */
        throw new UnsupportedOperationException("Method not decompiled: kanatamikado.p006ae.reiki.MenuScene.menuShopConfirmOpen(int):void");
    }

    private void menuShopConfirmClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.menuShopDetailOpenFlg = false;
    }

    private void itemBuyExe(int itemId) {
        boolean cursor2;
        int itemNum;
        Cursor cursor22 = null;
        attachChild(this.informationBox);
        int itemNum2 = -1;
        int itemPrize = 0;
        String itemName = "";
        try {
            cursor22 = this.f220db.rawQuery("SELECT m.item_id, m.prize, m.name FROM item_m m WHERE m.item_id=" + itemId, null);
            cursor2 = cursor22.moveToFirst();
            if (cursor2) {
                itemPrize = cursor22.getInt(1);
                itemName = cursor22.getString(2);
                cursor2 = null;
                Cursor cursor23 = this.f220db.rawQuery("SELECT u.item_id, u.num FROM user_item_t u WHERE u.item_id=" + itemId, null);
                cursor2 = cursor23.moveToNext();
                if (cursor2) {
                    itemNum2 = cursor23.getInt(1);
                }
                if (cursor23 != null) {
                    cursor23.close();
                }
            }
            if (cursor22 != null) {
                cursor22.close();
            }
            this.f220db.beginTransaction();
            if (itemNum2 < 0) {
                itemNum = 1;
                if (itemId != 0) {
                    try {
                        this.f220db.execSQL("INSERT INTO user_item_t VALUES (" + itemId + ", " + 1 + ");");
                    } catch (SQLException e) {
                        e.printStackTrace();
                        this.f220db.endTransaction();
                    } catch (Throwable th) {
                        this.f220db.endTransaction();
                        throw th;
                    }
                }
            } else {
                itemNum = itemNum2 + 1;
                if (999999 < itemNum) {
                    itemNum = 999999;
                }
                this.f220db.execSQL("UPDATE user_item_t SET num=" + itemNum + " WHERE item_id=" + itemId + ";");
            }
            this.userMoney -= (long) itemPrize;
            if (this.userMoney < 0) {
                this.userMoney = 0;
            }
            this.f220db.execSQL("UPDATE user_t SET user_money=" + this.userMoney + ";");
            this.f220db.setTransactionSuccessful();
            this.f220db.endTransaction();
            String str = "「" + itemName + "」を\n" + String.format("%,d", new Object[]{Integer.valueOf(itemPrize)}) + "銭で購入しました。\n\n" + itemName + "：" + (itemNum - 1) + "→" + itemNum + "\n所持金：" + String.format("%,d", new Object[]{Long.valueOf(this.userMoney + ((long) itemPrize))}) + "→" + String.format("%,d", new Object[]{Long.valueOf(this.userMoney)});
            this.moneyText.detachSelf();
            this.moneyText = new Text(0.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) String.format("%,d", new Object[]{Long.valueOf(this.userMoney)}) + "銭", new TextOptions(HorizontalAlign.RIGHT), getBaseActivity().getVertexBufferObjectManager());
            this.moneyText.setPosition((540.0f - this.moneyText.getWidth()) - 6.0f, 2.0f);
            attachChild(this.moneyText);
            this.moneyImg.setPosition(540.0f - (this.moneyText.getWidth() + 46.0f), -4.0f);
            this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            if (((long) itemPrize) <= this.userMoney) {
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_buy_again.png", "button/button_buy_again_p.png");
                this.buttonNg.setTag(10000000 + itemId);
            } else {
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_buy_ng.png", "button/button_buy_ng.png");
                this.buttonNg.setTag(99999997);
            }
            placeToCenterX(this.buttonNg, 650.0f);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
            placeToCenterX(this.buttonOk, 750.0f);
            this.buttonOk.setTag(10000000);
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.menuShopDetailOpenFlg = true;
        } catch (Throwable th2) {
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th2;
        } finally {
            if (cursor2 != null) {
                cursor2.close();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuItemDetailOpen(int id) {
        boolean cursor2;
        String str;
        menuBottomStop();
        String str2 = "";
        String str3 = "";
        Cursor cursor22 = null;
        long totalSales = 0;
        attachChild(this.informationBox);
        if (id == 29999) {
            try {
                cursor22 = this.f220db.rawQuery("SELECT u.item_id, u.num, m.name, m.text, m.prize FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE 0 < u.num AND 20001 <= u.item_id AND u.item_id <= 20010", null);
                while (cursor22.moveToNext()) {
                    str2 = str2 + "■" + cursor22.getString(2) + "（" + String.format("%,d", new Object[]{Integer.valueOf(cursor22.getInt(4))}) + "銭×" + cursor22.getInt(1) + "個）\n";
                    totalSales += ((long) cursor22.getInt(4)) * ((long) cursor22.getInt(1));
                }
                if (999999999 < this.userMoney + totalSales) {
                    str = (str2 + "\n合計：" + String.format("%,d", new Object[]{Long.valueOf(totalSales)}) + "銭") + "\n\n所持金が上限（" + String.format("%,d", new Object[]{Long.valueOf(999999999)}) + "銭）を\n超えてしまいます。";
                    totalSales = 0;
                } else if (str2.length() == 0) {
                    str = str2 + "霊石を所持していません。";
                } else {
                    str = str2 + "\n合計：" + String.format("%,d", new Object[]{Long.valueOf(totalSales)}) + "銭\n\n上記の霊石を全て売却します。\nよろしいですか？";
                }
                if (cursor22 != null) {
                    cursor22.close();
                }
            } catch (Throwable th) {
                if (cursor22 != null) {
                    cursor22.close();
                }
                throw th;
            }
        } else {
            try {
                cursor22 = this.f220db.rawQuery("SELECT u.item_id, u.num, m.name, m.text FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id=" + id, null);
                cursor2 = cursor22.moveToNext();
                if (cursor2) {
                    str2 = "■" + cursor22.getString(2) + "（" + cursor22.getInt(1) + "個）\n" + brInsert(cursor22.getString(3), 18);
                    if (1 <= id && id <= 10) {
                        str2 = str2 + "\n\n【霊珠の使い方】\n対象コダマの詳細画面で\n「アイテム」ボタンをタップ。\n\n下にある三角ボタンをタップした後、\nページ2（霊珠）画面にて\n使用する霊珠を選択してください。";
                    } else if (id == 40001) {
                        str2 = str2 + "\n\n【禁呪の書の使い方】\nスペル確認画面で\n本アイコンをタップしてください。\n\n習得できる禁呪の説明と、\n習得確認画面が表示されます。\n\nアイコンが表示されないスペルには\n使用することができません。";
                    } else if (100001 <= id && id <= 200000) {
                        cursor2 = null;
                        Cursor cursor23 = this.f220db.rawQuery("SELECT c.kodama_id, m.kodama_name, m.kodama_attr1, m.kodama_attr2 FROM card_m c LEFT OUTER JOIN kodama_m m ON c.kodama_id=m.kodama_id WHERE c.item_id=" + id, null);
                        cursor2 = cursor23.moveToNext();
                        if (cursor2) {
                            str2 = str2 + "\n\nアイテムとして使用すると、\n「" + cursor23.getString(1) + "」（" + this.attrs[cursor23.getInt(2) - 10] + "／" + this.attrs[cursor23.getInt(3) - 10] + "）\nのコダマと契約できます。";
                        }
                        if (cursor23 != null) {
                            cursor23.close();
                        }
                    }
                }
                if (cursor22 != null) {
                    cursor22.close();
                }
            } catch (Throwable th2) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                throw th2;
            } finally {
                if (cursor2 != null) {
                    cursor2.close();
                }
            }
        }
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        if (this.listHead < 0 || 4 < this.listHead) {
            this.listHead = 0;
        }
        if (this.listHead != 0) {
            if (90000 >= id || id >= 100000) {
                this.buttonEtc2 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_item_" + this.listHead + ".png", "button/button_item_" + this.listHead + "_p.png");
            } else {
                this.buttonEtc2 = getBaseActivity().getResourceUtil().getButtonSprite("button/button_item_1.png", "button/button_item_1_p.png");
            }
            placeToCenterX(this.buttonEtc2, 550.0f);
            if (this.listHead == 1) {
                this.buttonEtc2.setTag(10000000 + id);
            } else if (this.listHead == 2) {
                this.buttonEtc2.setTag(10000000 + id);
            } else if (this.listHead == 3) {
                this.buttonEtc2.setTag(50000000);
            } else if (this.listHead == 4) {
                this.buttonEtc2.setTag(50000000);
            }
            this.buttonEtc2.setOnClickListener(this);
            attachChild(this.buttonEtc2);
            registerTouchArea(this.buttonEtc2);
        }
        if (id == 90002 || id == 200001) {
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_sale_ng.png", "button/button_sale_ng.png");
            this.buttonNg.setTag(99999997);
        } else if (id != 29999) {
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_sale.png", "button/button_sale_p.png");
            this.buttonNg.setTag(20000000 + id);
        } else if (totalSales == 0) {
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_sale_ng.png", "button/button_sale_ng.png");
            this.buttonNg.setTag(99999997);
        } else {
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_sale.png", "button/button_sale_p.png");
            this.buttonNg.setTag(30000000 + id);
        }
        placeToCenterX(this.buttonNg, 650.0f);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        placeToCenterX(this.buttonOk, 750.0f);
        this.buttonOk.setTag(10000000);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.menuItemDetailOpenFlg = true;
    }

    private void menuItemDetailClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        if (this.listHead != 0) {
            this.buttonEtc2.detachSelf();
            unregisterTouchArea(this.buttonEtc2);
        }
        this.menuItemDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void menuItemExe(int itemId) {
        List<String> list;
        String str;
        String str2 = "";
        int itemNum = 0;
        Cursor cursor = null;
        attachChild(this.informationBox);
        if (itemId != 90001) {
            if (itemId == 30001) {
                list = Arrays.asList(this.bagList1);
            } else if (itemId == 30002) {
                list = Arrays.asList(this.bagList2);
            } else if (itemId == 30003) {
                list = Arrays.asList(this.bagList3);
            } else if (itemId == 30004) {
                list = Arrays.asList(this.bagList4);
            } else if (itemId == 30005) {
                list = Arrays.asList(this.bagList5);
            } else if (itemId == 30006) {
                list = Arrays.asList(this.bagList6);
            } else if (itemId == 30007) {
                list = Arrays.asList(this.bagList7);
            } else if (itemId == 30008) {
                list = Arrays.asList(this.bagList8);
            } else if (itemId == 30009) {
                list = Arrays.asList(this.bagList9);
            } else {
                list = Arrays.asList(this.bagList1);
            }
            int getCard = 0;
            if (itemId == 30008 || itemId == 30009) {
                if (itemId == 30008) {
                    getCard = Integer.valueOf(this.bagList8[this.rnd.nextInt(1000) % this.bagList8.length]).intValue();
                }
                if (itemId == 30009) {
                    getCard = Integer.valueOf(this.bagList9[this.rnd.nextInt(1000) % this.bagList9.length]).intValue();
                }
            } else {
                Collections.shuffle(list);
                getCard = Integer.valueOf(((String[]) list.toArray(new String[0]))[0]).intValue();
            }
            if (this.userDemoNo == 10) {
                getCard = 100001;
            }
            int cardNum = -1;
            try {
                cursor = this.f220db.rawQuery("SELECT u.item_id, u.num FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id=" + itemId + " OR m.item_id=" + getCard, null);
                while (cursor.moveToNext()) {
                    if (cursor.getInt(0) == itemId) {
                        itemNum = cursor.getInt(1);
                    } else {
                        cardNum = cursor.getInt(1);
                    }
                }
                if (cursor != null) {
                    cursor.close();
                }
                String cardName = "";
                String bagName = "";
                try {
                    cursor = this.f220db.rawQuery("SELECT m.item_id, m.name FROM item_m m WHERE m.item_id=" + itemId + " OR m.item_id=" + getCard, null);
                    while (cursor.moveToNext()) {
                        if (cursor.getInt(0) == itemId) {
                            bagName = cursor.getString(1);
                        } else {
                            cardName = cursor.getString(1);
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (getCard != 0) {
                        this.f220db.beginTransaction();
                        int itemNum2 = itemNum - 1;
                        if (itemNum2 < 0) {
                            itemNum2 = 0;
                        }
                        try {
                            this.f220db.execSQL("UPDATE user_item_t SET num=" + itemNum + " WHERE item_id=" + itemId + ";");
                            if (cardNum < 0) {
                                cardNum = 1;
                                if (getCard != 0) {
                                    this.f220db.execSQL("INSERT INTO user_item_t VALUES (" + getCard + ", " + 1 + ");");
                                }
                            } else {
                                cardNum++;
                                if (999999 < cardNum) {
                                    cardNum = 999999;
                                }
                                this.f220db.execSQL("UPDATE user_item_t SET num=" + cardNum + " WHERE item_id=" + getCard + ";");
                            }
                            if (this.userDemoNo == 10) {
                                this.userDemoNo++;
                                this.f220db.execSQL("UPDATE user_t SET user_demo_no=" + this.userDemoNo);
                            }
                            this.f220db.setTransactionSuccessful();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        } finally {
                            this.f220db.endTransaction();
                        }
                    }
                    str = bagName + "の中に\n「" + cardName + "」が\n入っていました！\n\n" + bagName + "：" + (itemNum + 1) + "→" + itemNum + "\n" + cardName + "：" + (cardNum - 1) + "→" + cardNum;
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th2;
            }
        } else if (10000 <= this.userSp) {
            str = "これ以上は使用できません。";
        } else {
            String itemName = "";
            try {
                Cursor cursor2 = this.f220db.rawQuery("SELECT u.item_id, u.num, m.name FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id=" + itemId, null);
                while (cursor2.moveToNext()) {
                    itemNum = cursor2.getInt(1);
                    itemName = cursor2.getString(2);
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                this.f220db.beginTransaction();
                itemNum--;
                if (itemNum < 0) {
                    itemNum = 0;
                }
                try {
                    this.f220db.execSQL("UPDATE user_item_t SET num=" + itemNum + " WHERE item_id=" + itemId + ";");
                    this.userSp += 100;
                    if (this.userMaxSp < this.userSp) {
                        this.healSpFlg = false;
                        this.userSpRestoreTime = 0;
                        this.healSpTime = 0;
                        this.healSpTimeText.setText("　");
                    }
                    this.f220db.execSQL("UPDATE user_t SET user_sp=" + this.userSp + ", user_sp_restore_time=" + this.userSpRestoreTime + ";");
                    this.f220db.setTransactionSuccessful();
                } catch (SQLException e2) {
                    e2.printStackTrace();
                } finally {
                    this.f220db.endTransaction();
                }
                this.userSpText.detachSelf();
                this.userSpText = new Text(6.0f, 34.0f, (IFont) this.fontBlack, (CharSequence) "霊力." + this.userSp + "/" + this.userMaxSp, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.userSpText);
                if (this.userMaxSp < this.userSp || this.userMaxSp == 0) {
                    this.userSpVar.setWidth(526.0f);
                } else {
                    this.userSpVar.setWidth((float) ((this.userSp * 526) / this.userMaxSp));
                }
                str = itemName + "を使用しました。\n霊力が少し回復しました。\n\n" + itemName + "：" + (itemNum + 1) + "→" + itemNum;
            } catch (Throwable th3) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th3;
            }
        }
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        if (itemNum > 0) {
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_use_again.png", "button/button_use_again_p.png");
            this.buttonOk.setTag(60000000 + itemId);
        } else {
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_use_again_ng.png", "button/button_use_again_ng.png");
            this.buttonOk.setTag(99999997);
        }
        placeToCenterX(this.buttonOk, 650.0f);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        placeToCenterX(this.buttonNg, 750.0f);
        this.buttonNg.setTag(10030000);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
        this.menuItemDetailOpenFlg = true;
    }

    private void menuItemExeClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.menuItemDetailOpenFlg = false;
    }

    private void cardUseConfirmOpen(int cardId) {
        String str = "このアイテムは使用できません。";
        Cursor cursor = null;
        int addId = boxEmptyId();
        attachChild(this.informationBox);
        if (addId < 0) {
            str = "選択中ボックスが一杯です。\n\n空きがあるボックスを選択するか、\nコダマと契約解除をするなどして\nボックス枠を増やしてください。";
            this.newUnitDisplayFlg = true;
            this.newUnitImg = getBaseActivity().getResourceUtil().getSprite("kodama/0.png");
            attachChild(this.newUnitImg);
        } else {
            try {
                Cursor cursor2 = this.f220db.rawQuery("SELECT c.kodama_id, m.kodama_name, m.kodama_attr1, m.kodama_attr2, i.name FROM card_m c LEFT OUTER JOIN kodama_m m ON c.kodama_id=m.kodama_id LEFT OUTER JOIN item_m i ON c.item_id=i.item_id WHERE c.item_id=" + cardId, null);
                if (cursor2.moveToNext()) {
                    str = "「" + cursor2.getString(4) + "」を使用して、\n「" + cursor2.getString(1) + "」（" + this.attrs[cursor2.getInt(2) - 10] + "／" + this.attrs[cursor2.getInt(3) - 10] + "属性）\n\n\n\n\n\n\n\nのコダマと契約します。\n\n使用したコダマカードはなくなります。\nよろしいですか？";
                    this.newUnitDisplayFlg = true;
                    this.newUnitImg = getBaseActivity().getResourceUtil().getSprite("kodama/" + cursor2.getString(0) + ".png");
                    this.newUnitImg.setPosition(60.0f, 120.0f);
                    attachChild(this.newUnitImg);
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
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        if (addId < 0) {
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes_ng.png", "button/information_yes_ng.png");
            this.buttonOk.setTag(99999997);
        } else {
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
            this.buttonOk.setTag(40000000 + cardId);
        }
        placeToCenterX(this.buttonOk, 650.0f);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
        placeToCenterX(this.buttonNg, 750.0f);
        this.buttonNg.setTag(10100000);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
        this.menuItemDetailOpenFlg = true;
    }

    private void cardUseConfirmClose() {
        this.newUnitImg.detachSelf();
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.menuItemDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void cardUseExe(int cardId) {
        String str = "";
        Cursor cursor = null;
        String cardName = "";
        int kodamaId = 0;
        attachChild(this.informationBox);
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT c.kodama_id, m.kodama_name, i.name FROM card_m c LEFT OUTER JOIN kodama_m m ON c.kodama_id=m.kodama_id LEFT OUTER JOIN item_m i ON c.item_id=i.item_id WHERE c.item_id=" + cardId, null);
            if (cursor2.moveToNext()) {
                kodamaId = cursor2.getInt(0);
                str = "「" + cursor2.getString(2) + "」を使用して、\n「" + cursor2.getString(1) + "」のコダマと契約しました！";
                cardName = cursor2.getString(2);
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            int cardNum = 0;
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT u.item_id, u.num FROM user_item_t u WHERE u.item_id=" + cardId, null);
                while (cursor3.moveToNext()) {
                    int cardNum2 = cursor3.getInt(1);
                    str = str + "\n\n" + cardName + "：" + cardNum2 + "→" + (cardNum2 - 1);
                    cardNum = cardNum2 - 1;
                    if (cardNum < 0) {
                        cardNum = 0;
                    }
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.infoText);
                if (kodamaId != 0) {
                    this.f220db.beginTransaction();
                    try {
                        this.f220db.execSQL("UPDATE user_item_t SET num=" + cardNum + " WHERE item_id=" + cardId + ";");
                        newUnitGet(kodamaId);
                        if (this.userDemoNo == 18) {
                            this.userDemoNo++;
                            this.f220db.execSQL("UPDATE user_t SET user_demo_no=" + this.userDemoNo);
                        }
                        this.f220db.setTransactionSuccessful();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        this.f220db.endTransaction();
                    }
                }
                this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ok.png", "button/information_ok_p.png");
                placeToCenterX(this.buttonOk, 750.0f);
                this.buttonOk.setTag(40100000);
                this.buttonOk.setOnClickListener(this);
                attachChild(this.buttonOk);
                registerTouchArea(this.buttonOk);
                this.menuItemDetailOpenFlg = true;
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

    private void cardUseExeClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.newUnitImg.detachSelf();
        this.menuItemDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void itemSaleConfirmOpen(int itemId) {
        int tmp;
        String str = "このアイテムは売却できません。";
        Cursor cursor = null;
        boolean ngFlg = false;
        attachChild(this.informationBox);
        try {
            cursor = this.f220db.rawQuery("SELECT u.item_id, u.num, m.type, m.name, m.prize FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id=" + itemId, null);
            if (cursor.moveToNext()) {
                if (100 < cursor.getInt(2)) {
                    tmp = cursor.getInt(4) / 50;
                } else if (cursor.getInt(2) == 3) {
                    tmp = cursor.getInt(4);
                } else {
                    tmp = cursor.getInt(4) / 10;
                }
                if (999999999 < this.userMoney + ((long) tmp)) {
                    str = "所持金が上限を超えるため、\nこれ以上売却できません。";
                    ngFlg = true;
                } else {
                    str = "「" + cursor.getString(3) + "」（" + cursor.getString(1) + "個所持）を\n" + String.format("%,d", new Object[]{Integer.valueOf(tmp)}) + "銭で売却します。\n\nよろしいですか？";
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
            attachChild(this.infoText);
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/information_yes.png", "button/information_yes_p.png");
            placeToCenterX(this.buttonOk, 650.0f);
            if (ngFlg) {
                this.buttonNg.setTag(30000000);
            } else {
                this.buttonOk.setTag(30000000 + itemId);
            }
            this.buttonOk.setOnClickListener(this);
            attachChild(this.buttonOk);
            registerTouchArea(this.buttonOk);
            this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/information_ng.png", "button/information_ng_p.png");
            placeToCenterX(this.buttonNg, 750.0f);
            this.buttonNg.setTag(30000000);
            this.buttonNg.setOnClickListener(this);
            attachChild(this.buttonNg);
            registerTouchArea(this.buttonNg);
            this.menuItemDetailOpenFlg = true;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void itemSaleConfirmClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.menuItemDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void itemSaleExe(int itemId) {
        int itemNum;
        String str;
        Cursor cursor = null;
        attachChild(this.informationBox);
        int itemNum2 = 0;
        int itemPrize = 0;
        String itemName = "";
        if (itemId == 29999) {
            try {
                Cursor cursor2 = this.f220db.rawQuery("SELECT u.item_id, u.num, m.name, m.text, m.prize FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE 20001 <= u.item_id AND u.item_id <= 20010", null);
                while (cursor2.moveToNext()) {
                    itemPrize += cursor2.getInt(4) * cursor2.getInt(1);
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
        } else {
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT u.item_id, u.num, m.type, m.name, m.prize FROM user_item_t u LEFT OUTER JOIN item_m m ON u.item_id=m.item_id WHERE u.item_id=" + itemId, null);
                while (cursor3.moveToNext()) {
                    itemNum2 = cursor3.getInt(1);
                    int itemType = cursor3.getInt(2);
                    itemName = cursor3.getString(3);
                    itemPrize = cursor3.getInt(4);
                    if (100 < itemType) {
                        itemPrize /= 50;
                    } else if (itemType != 3) {
                        itemPrize /= 10;
                    }
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
            } catch (Throwable th2) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th2;
            }
        }
        this.f220db.beginTransaction();
        if (itemId == 29999) {
            try {
                this.f220db.execSQL("UPDATE user_item_t SET num=0 WHERE 20001 <= item_id AND item_id <= 20010;");
            } catch (SQLException e) {
                e.printStackTrace();
                this.f220db.endTransaction();
            } catch (Throwable th3) {
                this.f220db.endTransaction();
                throw th3;
            }
        } else {
            itemNum--;
            if (itemNum < 0) {
                itemNum = 0;
            }
            this.f220db.execSQL("UPDATE user_item_t SET num=" + itemNum + " WHERE item_id=" + itemId + ";");
        }
        this.userMoney += (long) itemPrize;
        if (999999999 < this.userMoney) {
            this.userMoney = 999999999;
        }
        this.f220db.execSQL("UPDATE user_t SET user_money=" + this.userMoney + ";");
        this.f220db.setTransactionSuccessful();
        this.f220db.endTransaction();
        String str2 = "";
        if (itemId == 29999) {
            str = "霊石をまとめて\n" + String.format("%,d", new Object[]{Integer.valueOf(itemPrize)}) + "銭で売却しました。\n\n所持金：" + String.format("%,d", new Object[]{Long.valueOf(this.userMoney - ((long) itemPrize))}) + "→" + String.format("%,d", new Object[]{Long.valueOf(this.userMoney)});
        } else {
            str = "「" + itemName + "」を\n" + String.format("%,d", new Object[]{Integer.valueOf(itemPrize)}) + "銭で売却しました。\n\n" + itemName + "：" + (itemNum + 1) + "→" + itemNum + "\n所持金：" + String.format("%,d", new Object[]{Long.valueOf(this.userMoney - ((long) itemPrize))}) + "→" + String.format("%,d", new Object[]{Long.valueOf(this.userMoney)});
        }
        this.moneyText.detachSelf();
        this.moneyText = new Text(0.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) String.format("%,d", new Object[]{Long.valueOf(this.userMoney)}) + "銭", new TextOptions(HorizontalAlign.RIGHT), getBaseActivity().getVertexBufferObjectManager());
        this.moneyText.setPosition((540.0f - this.moneyText.getWidth()) - 6.0f, 2.0f);
        attachChild(this.moneyText);
        this.moneyImg.setPosition(540.0f - (this.moneyText.getWidth() + 46.0f), -4.0f);
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        if (itemId == 29999 || 1 > itemNum) {
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_sale_again_ng.png", "button/button_sale_again_ng.png");
            this.buttonOk.setTag(99999997);
        } else {
            this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_sale_again.png", "button/button_sale_again_p.png");
            this.buttonOk.setTag(30000000 + itemId);
        }
        placeToCenterX(this.buttonOk, 650.0f);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        placeToCenterX(this.buttonNg, 750.0f);
        this.buttonNg.setTag(30000000);
        this.buttonNg.setOnClickListener(this);
        attachChild(this.buttonNg);
        registerTouchArea(this.buttonNg);
        this.menuItemDetailOpenFlg = true;
    }

    /* JADX INFO: finally extract failed */
    private void menuCardChangeOpen() {
        this.menuMode = 24;
        int i = 0;
        int posY = 140;
        float tmpM = (float) 8;
        String str = "";
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT COUNT(m.item_id) FROM item_m m LEFT OUTER JOIN card_m c ON m.item_id=c.item_id WHERE ((100000 < m.item_id AND m.item_id < 160000) OR (180011 <= m.item_id AND m.item_id <=180018)) AND c.kodama_id<>0", null);
            if (cursor2.moveToNext()) {
                this.listPageMax = (int) Math.ceil((double) (((float) cursor2.getInt(0)) / tmpM));
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            headlineBoxOpen("コダマカード引き換え（" + (this.listPage + 1) + "/" + this.listPageMax + "）", false);
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT m.item_id, m.name, m.text, c.kodama_id FROM item_m m LEFT OUTER JOIN card_m c ON m.item_id=c.item_id WHERE ((100000 < m.item_id AND m.item_id < 160000) OR (180011 <= m.item_id AND m.item_id <=180018)) AND c.kodama_id<>0 ORDER BY m.kana ASC LIMIT " + (this.listPage * 8) + ", " + 8, null);
                while (cursor3.moveToNext()) {
                    this.buttonList.add(i, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
                    ((ButtonSprite) this.buttonList.get(i)).setPosition(-500.0f, (float) posY);
                    ((ButtonSprite) this.buttonList.get(i)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(i));
                    registerTouchArea((ITouchArea) this.buttonList.get(i));
                    ((ButtonSprite) this.buttonList.get(i)).setTag(10000000 + cursor3.getInt(0));
                    String str2 = cursor3.getString(1) + "（" + cursor3.getString(2).replaceAll("します。", "") + "）";
                    if (21 < str2.length()) {
                        str2 = str2.substring(0, 21) + "...";
                    }
                    this.buttonListText[i] = new Text(-486.0f, (float) (posY + 10), (IFont) this.fontBlack, (CharSequence) str2, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                    attachChild(this.buttonListText[i]);
                    this.shadowList.add(i, getBaseActivity().getResourceUtil().getSprite("kodama_s/" + cursor3.getInt(3) + ".png"));
                    ((Sprite) this.shadowList.get(i)).setPosition(436.0f, (float) (posY - 16));
                    attachChild((IEntity) this.shadowList.get(i));
                    ((ButtonSprite) this.buttonList.get(i)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) posY, (float) posY));
                    this.buttonListText[i].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) (posY + 10), (float) (posY + 10)));
                    posY += 64;
                    i++;
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                this.buttonNum = i;
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                placeToCenterX(this.buttonNg, 678.0f);
                this.buttonNg.setTag(10000000);
                this.buttonNg.setOnClickListener(this);
                attachChild(this.buttonNg);
                registerTouchArea(this.buttonNg);
                arrowOpen(91, 686, false);
                arrowOpen(92, 686, true);
                menuBottomStart();
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

    private void menuCardChangeClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
            ((Sprite) this.shadowList.get(i)).detachSelf();
        }
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x01ac, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x01b2, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void menuCardChangeConfirmOpen(int r13) {
        /*
            r12 = this;
            r1 = 1114636288(0x42700000, float:60.0)
            r11 = 1
            r12.menuBottomStop()
            org.andengine.entity.sprite.Sprite r0 = r12.informationBox
            r12.attachChild(r0)
            r9 = 0
            java.lang.String r4 = ""
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r2 = "SELECT m.item_id, m.prize, m.name, m.text FROM item_m m WHERE m.item_id="
            java.lang.StringBuilder r0 = r0.append(r2)
            java.lang.StringBuilder r0 = r0.append(r13)
            java.lang.String r10 = r0.toString()
            r7 = 0
            android.database.sqlite.SQLiteDatabase r0 = r12.f220db     // Catch:{ all -> 0x01a5 }
            r2 = 0
            android.database.Cursor r7 = r0.rawQuery(r10, r2)     // Catch:{ all -> 0x01a5 }
            boolean r0 = r7.moveToNext()     // Catch:{ all -> 0x01a5 }
            if (r0 == 0) goto L_0x0115
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r0.<init>()     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "■"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a5 }
            r2 = 2
            java.lang.String r2 = r7.getString(r2)     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "\n"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a5 }
            r2 = 3
            java.lang.String r2 = r7.getString(r2)     // Catch:{ all -> 0x01a5 }
            r3 = 18
            java.lang.String r2 = r12.brInsert(r2, r3)     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a5 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r0.<init>()     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "SELECT c.kodama_id, m.kodama_name, m.kodama_attr1, m.kodama_attr2 FROM card_m c LEFT OUTER JOIN kodama_m m ON c.kodama_id=m.kodama_id WHERE c.item_id="
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r0 = r0.append(r13)     // Catch:{ all -> 0x01a5 }
            java.lang.String r10 = r0.toString()     // Catch:{ all -> 0x01a5 }
            r8 = 0
            android.database.sqlite.SQLiteDatabase r0 = r12.f220db     // Catch:{ all -> 0x019e }
            r2 = 0
            android.database.Cursor r8 = r0.rawQuery(r10, r2)     // Catch:{ all -> 0x019e }
            boolean r0 = r8.moveToNext()     // Catch:{ all -> 0x019e }
            if (r0 == 0) goto L_0x00c9
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x019e }
            r0.<init>()     // Catch:{ all -> 0x019e }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x019e }
            java.lang.String r2 = "\n\nアイテムとして使用すると、\n「"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x019e }
            r2 = 1
            java.lang.String r2 = r8.getString(r2)     // Catch:{ all -> 0x019e }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x019e }
            java.lang.String r2 = "」（"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x019e }
            java.lang.String[] r2 = r12.attrs     // Catch:{ all -> 0x019e }
            r3 = 2
            int r3 = r8.getInt(r3)     // Catch:{ all -> 0x019e }
            int r3 = r3 + -10
            r2 = r2[r3]     // Catch:{ all -> 0x019e }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x019e }
            java.lang.String r2 = "／"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x019e }
            java.lang.String[] r2 = r12.attrs     // Catch:{ all -> 0x019e }
            r3 = 3
            int r3 = r8.getInt(r3)     // Catch:{ all -> 0x019e }
            int r3 = r3 + -10
            r2 = r2[r3]     // Catch:{ all -> 0x019e }
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x019e }
            java.lang.String r2 = "）\nのコダマと契約できます。"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x019e }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x019e }
        L_0x00c9:
            if (r8 == 0) goto L_0x00ce
            r8.close()     // Catch:{ all -> 0x01a5 }
        L_0x00ce:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r0.<init>()     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "SELECT u.item_id, u.num FROM user_item_t u WHERE u.item_id="
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r0 = r0.append(r13)     // Catch:{ all -> 0x01a5 }
            java.lang.String r10 = r0.toString()     // Catch:{ all -> 0x01a5 }
            android.database.sqlite.SQLiteDatabase r0 = r12.f220db     // Catch:{ all -> 0x01ac }
            r2 = 0
            android.database.Cursor r8 = r0.rawQuery(r10, r2)     // Catch:{ all -> 0x01ac }
            boolean r0 = r8.moveToNext()     // Catch:{ all -> 0x01ac }
            if (r0 == 0) goto L_0x00f3
            r0 = 1
            int r9 = r8.getInt(r0)     // Catch:{ all -> 0x01ac }
        L_0x00f3:
            if (r8 == 0) goto L_0x00f8
            r8.close()     // Catch:{ all -> 0x01a5 }
        L_0x00f8:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a5 }
            r0.<init>()     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r0 = r0.append(r4)     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "\n\n現在は"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a5 }
            java.lang.StringBuilder r0 = r0.append(r9)     // Catch:{ all -> 0x01a5 }
            java.lang.String r2 = "個所持しています。"
            java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x01a5 }
            java.lang.String r4 = r0.toString()     // Catch:{ all -> 0x01a5 }
        L_0x0115:
            if (r7 == 0) goto L_0x011a
            r7.close()
        L_0x011a:
            org.andengine.entity.text.Text r0 = new org.andengine.entity.text.Text
            org.andengine.opengl.font.Font r3 = r12.fontWhite
            org.andengine.entity.text.TextOptions r5 = new org.andengine.entity.text.TextOptions
            org.andengine.util.HorizontalAlign r2 = org.andengine.util.HorizontalAlign.LEFT
            r5.<init>(r2)
            kanatamikado.ae.reiki.MultiSceneActivity r2 = r12.getBaseActivity()
            org.andengine.opengl.vbo.VertexBufferObjectManager r6 = r2.getVertexBufferObjectManager()
            r2 = r1
            r0.<init>(r1, r2, r3, r4, r5, r6)
            r12.infoText = r0
            org.andengine.entity.text.Text r0 = r12.infoText
            r12.attachChild(r0)
            kanatamikado.ae.reiki.MultiSceneActivity r0 = r12.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r0 = r0.getResourceUtil()
            java.lang.String r1 = "button/information_yes.png"
            java.lang.String r2 = "button/information_yes_p.png"
            org.andengine.entity.sprite.ButtonSprite r0 = r0.getButtonSprite(r1, r2)
            r12.buttonNg = r0
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonNg
            r1 = 10000000(0x989680, float:1.4012985E-38)
            int r1 = r1 + r13
            r0.setTag(r1)
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonNg
            r1 = 1143111680(0x44228000, float:650.0)
            r12.placeToCenterX(r0, r1)
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonNg
            r0.setOnClickListener(r12)
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonNg
            r12.attachChild(r0)
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonNg
            r12.registerTouchArea(r0)
            kanatamikado.ae.reiki.MultiSceneActivity r0 = r12.getBaseActivity()
            kanatamikado.ae.reiki.ResourceUtil r0 = r0.getResourceUtil()
            java.lang.String r1 = "button/information_ng.png"
            java.lang.String r2 = "button/information_ng_p.png"
            org.andengine.entity.sprite.ButtonSprite r0 = r0.getButtonSprite(r1, r2)
            r12.buttonOk = r0
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonOk
            r1 = 1144750080(0x443b8000, float:750.0)
            r12.placeToCenterX(r0, r1)
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonOk
            r1 = 10000000(0x989680, float:1.4012985E-38)
            r0.setTag(r1)
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonOk
            r0.setOnClickListener(r12)
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonOk
            r12.attachChild(r0)
            org.andengine.entity.sprite.ButtonSprite r0 = r12.buttonOk
            r12.registerTouchArea(r0)
            r12.menuCardChangeDetailOpenFlg = r11
            return
        L_0x019e:
            r0 = move-exception
            if (r8 == 0) goto L_0x01a4
            r8.close()     // Catch:{ all -> 0x01a5 }
        L_0x01a4:
            throw r0     // Catch:{ all -> 0x01a5 }
        L_0x01a5:
            r0 = move-exception
            if (r7 == 0) goto L_0x01ab
            r7.close()
        L_0x01ab:
            throw r0
        L_0x01ac:
            r0 = move-exception
            if (r8 == 0) goto L_0x01b2
            r8.close()     // Catch:{ all -> 0x01a5 }
        L_0x01b2:
            throw r0     // Catch:{ all -> 0x01a5 }
        */
        throw new UnsupportedOperationException("Method not decompiled: kanatamikado.p006ae.reiki.MenuScene.menuCardChangeConfirmOpen(int):void");
    }

    private void menuCardChangeConfirmClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.buttonNg.detachSelf();
        unregisterTouchArea(this.buttonNg);
        this.menuCardChangeDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void menuCardChangeExe(int itemId) {
        boolean cursor2;
        int itemNum;
        Cursor cursor22 = null;
        attachChild(this.informationBox);
        int itemNum2 = -1;
        String itemName = "";
        try {
            cursor22 = this.f220db.rawQuery("SELECT m.item_id, m.prize, m.name FROM item_m m WHERE m.item_id=" + itemId, null);
            cursor2 = cursor22.moveToFirst();
            if (cursor2) {
                itemName = cursor22.getString(2);
                cursor2 = null;
                Cursor cursor23 = this.f220db.rawQuery("SELECT u.item_id, u.num FROM user_item_t u WHERE u.item_id=" + itemId, null);
                cursor2 = cursor23.moveToNext();
                if (cursor2) {
                    itemNum2 = cursor23.getInt(1);
                }
                if (cursor23 != null) {
                    cursor23.close();
                }
            }
            if (cursor22 != null) {
                cursor22.close();
            }
            int cardNum = 0;
            try {
                Cursor cursor = this.f220db.rawQuery("SELECT u.item_id, u.num FROM user_item_t u WHERE u.item_id=90002", null);
                if (cursor.moveToNext()) {
                    cardNum = cursor.getInt(1);
                }
                if (cursor != null) {
                    cursor.close();
                }
                this.f220db.beginTransaction();
                if (itemNum2 < 0) {
                    itemNum = 1;
                    if (itemId != 0) {
                        try {
                            this.f220db.execSQL("INSERT INTO user_item_t VALUES (" + itemId + ", " + 1 + ");");
                        } catch (SQLException e) {
                            e.printStackTrace();
                            this.f220db.endTransaction();
                        } catch (Throwable th) {
                            this.f220db.endTransaction();
                            throw th;
                        }
                    }
                } else {
                    itemNum = itemNum2 + 1;
                    if (999999 < itemNum) {
                        itemNum = 999999;
                    }
                    this.f220db.execSQL("UPDATE user_item_t SET num=" + itemNum + " WHERE item_id=" + itemId + ";");
                }
                int cardNum2 = cardNum - 1;
                if (cardNum2 < 0) {
                    cardNum2 = 0;
                }
                this.f220db.execSQL("UPDATE user_item_t SET num=" + cardNum2 + " WHERE item_id=90002;");
                this.f220db.setTransactionSuccessful();
                this.f220db.endTransaction();
                this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) "「" + itemName + "」と\nコダマカード引換券を交換しました。\n\n" + itemName + "：" + (itemNum - 1) + "→" + itemNum, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.infoText);
                this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                placeToCenterX(this.buttonOk, 750.0f);
                this.buttonOk.setTag(20000000);
                this.buttonOk.setOnClickListener(this);
                attachChild(this.buttonOk);
                registerTouchArea(this.buttonOk);
                this.menuCardChangeDetailOpenFlg = true;
            } catch (Throwable th2) {
                if (cursor22 != null) {
                    cursor22.close();
                }
                throw th2;
            }
        } catch (Throwable th3) {
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th3;
        } finally {
            if (cursor2 != null) {
                cursor2.close();
            }
        }
    }

    private void menuCardChangeExeClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.menuCardChangeDetailOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void menuPartyOpen(boolean scroll) {
        String str;
        this.menuMode = 31;
        int posY = 136;
        Cursor cursor = null;
        String str2 = "";
        headlineBoxOpen("パーティー編成（" + this.partyNo + "/" + 9 + "）", scroll);
        int no = 0;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.name, u.lv, u.exp, u.equip, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, m.kodama_name, m.kodama_attr1, m.kodama_attr2 FROM user_party_t p LEFT OUTER JOIN user_kodama_t u ON p.user_kodama_id=u.user_kodama_id LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE p.party_no = " + this.partyNo + " ORDER BY p.sort_no ASC", null);
            while (cursor2.moveToNext()) {
                int kodamaId = cursor2.getInt(2);
                String nickName = cursor2.getString(3);
                int kodamaLv = cursor2.getInt(4);
                String kodamaName = cursor2.getString(12);
                int kodamaAttr1 = cursor2.getInt(13);
                int kodamaAttr2 = cursor2.getInt(14);
                String fileName = "kodama_s/" + kodamaId + ".png";
                this.backButtonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("button/button_unit.png", "button/button_unit_p.png"));
                ((ButtonSprite) this.backButtonList.get(no)).setPosition((float) 60, (float) posY);
                ((ButtonSprite) this.backButtonList.get(no)).setTag(10000000 + no + 1);
                ((ButtonSprite) this.backButtonList.get(no)).setOnClickListener(this);
                attachChild((IEntity) this.backButtonList.get(no));
                registerTouchArea((ITouchArea) this.backButtonList.get(no));
                this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite(fileName, fileName));
                ((ButtonSprite) this.buttonList.get(no)).setPosition((float) 78, (float) (posY + 18));
                ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + no + 1);
                ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                attachChild((IEntity) this.buttonList.get(no));
                registerTouchArea((ITouchArea) this.buttonList.get(no));
                this.attrList1.add(no, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr1 + ".png"));
                this.attrList2.add(no, getBaseActivity().getResourceUtil().getSprite("attr/s/" + kodamaAttr2 + ".png"));
                ((Sprite) this.attrList1.get(no)).setPosition((float) 160, (float) (posY + 10));
                ((Sprite) this.attrList2.get(no)).setPosition((float) 184, (float) (posY + 10));
                attachChild((IEntity) this.attrList1.get(no));
                attachChild((IEntity) this.attrList2.get(no));
                if (kodamaId == 0) {
                    str = "■空き";
                } else {
                    str = "Lv." + kodamaLv + "\n" + nickName + "（" + kodamaName + "）";
                }
                this.statusText[no] = new Text((float) 160, (float) (posY + 36), (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
                attachChild(this.statusText[no]);
                posY += 100;
                no++;
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            this.buttonNum = no;
            menuBottomStart();
            arrowOpen(91, 686, false);
            arrowOpen(92, 686, true);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void menuPartyClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.backButtonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.backButtonList.get(i));
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            ((Sprite) this.attrList1.get(i)).detachSelf();
            ((Sprite) this.attrList2.get(i)).detachSelf();
            this.statusText[i].detachSelf();
        }
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
    }

    /* JADX INFO: finally extract failed */
    private void menuPartyChangeOpen() {
        this.menuMode = 32;
        Cursor cursor = null;
        String str = "u.sort_no ASC";
        int posX = 30;
        int posY = 128;
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
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.user_kodama_id FROM user_party_t u WHERE u.party_no=" + this.partyNo, null);
            while (cursor2.moveToNext()) {
                partyId[no] = cursor2.getInt(0);
                no++;
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            int no2 = 0;
            try {
                cursor2 = this.f220db.rawQuery("SELECT u.user_kodama_id, u.sort_no, u.kodama_id, u.name, u.lv, u.exp, u.equip, u.sb_hp, u.sb_atk, u.sb_def, u.sb_spd, u.bp, m.kodama_attr1, m.kodama_attr2, u.faint_flg FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE " + (this.boxSelect * 100) + "<u.sort_no AND u.sort_no<" + ((this.boxSelect + 1) * 100) + " ORDER BY " + sort, null);
                while (cursor2.moveToNext()) {
                    int userKodamaId = cursor2.getInt(0);
                    int kodamaId = cursor2.getInt(2);
                    String fileName = "kodama_s/" + kodamaId + ".png";
                    int kodamaLv = cursor2.getInt(4);
                    int kodamaAttr1 = cursor2.getInt(12);
                    int kodamaAttr2 = cursor2.getInt(13);
                    int faintFlg = cursor2.getInt(14);
                    this.shadowList.add(no2, getBaseActivity().getResourceUtil().getSprite("item/shadow.png"));
                    ((Sprite) this.shadowList.get(no2)).setPosition((float) (posX - 4), (float) (posY + 46));
                    attachChild((IEntity) this.shadowList.get(no2));
                    this.buttonList.add(no2, getBaseActivity().getResourceUtil().getButtonSprite(fileName, fileName));
                    ((ButtonSprite) this.buttonList.get(no2)).setPosition((float) posX, (float) posY);
                    if (faintFlg == 1) {
                        ((ButtonSprite) this.buttonList.get(no2)).setTag(99999997);
                        ((ButtonSprite) this.buttonList.get(no2)).setAlpha(0.2f);
                        this.statusList.add(no2, getBaseActivity().getResourceUtil().getSprite("item/faint.png"));
                    } else {
                        if (userKodamaId != 0) {
                            if (userKodamaId == partyId[0] || userKodamaId == partyId[1] || userKodamaId == partyId[2] || userKodamaId == partyId[3] || userKodamaId == partyId[4] || userKodamaId == partyId[5]) {
                                ((ButtonSprite) this.buttonList.get(no2)).setTag(99999997);
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
                arrowOpen(91, 686, false);
                arrowOpen(92, 686, true);
                sortButtonOpen(2);
                menuBottomStart();
                this.buttonNg = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
                this.buttonNg.setTag(31);
                this.buttonNg.setPosition(275.0f, 678.0f);
                this.buttonNg.setOnClickListener(this);
                attachChild(this.buttonNg);
                registerTouchArea(this.buttonNg);
                this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_off.png", "button/button_off_p.png");
                this.buttonOk.setTag(10000000);
                this.buttonOk.setPosition(65.0f, 678.0f);
                this.buttonOk.setOnClickListener(this);
                attachChild(this.buttonOk);
                registerTouchArea(this.buttonOk);
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
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
    }

    private void partyChangeExe(int id) {
        this.f220db.execSQL("UPDATE user_party_t SET user_kodama_id = " + id + " WHERE party_no=" + this.partyNo + " AND sort_no=" + this.partySortNo);
        menuPartyChangeClose();
        menuPartyOpen(false);
        if (this.userDemoNo == 23) {
            this.userDemoNo = 24;
            this.f220db.execSQL("UPDATE user_t SET user_demo_no=" + this.userDemoNo);
            confirmBoxOpen("クエストをクリアすると\n新しいステージが開放され、\n出会えるコダマも\nどんどん増えていきます。\n\n\nこの調子でパーティーを充実させつつ、\nまだ見ぬエリアや\nまだ見ぬコダマを求めて\nクエストを進めてみてください。", 99999996, 0, false);
        }
    }

    /* access modifiers changed from: private */
    public void menuOptionOpen() {
        this.menuMode = 41;
        headlineBoxOpen("オプション", true);
        int height = 130;
        for (int i = 0; i < this.optionButtonNum; i++) {
            if (i != 6 || this.nendAdFlg == 0) {
                this.buttonList.add(i, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
            } else {
                this.buttonList.add(i, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base_ng.png", "button/button_base_ng.png"));
            }
            ((ButtonSprite) this.buttonList.get(i)).setPosition(-500.0f, (float) height);
            if (i == 0) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(105);
            } else if (i == 1) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(106);
            } else if (i == 2) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(114);
            } else if (i == 3) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(102);
            } else if (i == 4) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(104);
            } else if (i == 5) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(101);
            } else if (i == 6) {
                if (this.nendAdFlg == 0) {
                    ((ButtonSprite) this.buttonList.get(i)).setTag(115);
                } else {
                    ((ButtonSprite) this.buttonList.get(i)).setTag(99999997);
                }
            } else if (i == 7) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(107);
            } else if (i == 8) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(109);
            }
            ((ButtonSprite) this.buttonList.get(i)).setOnClickListener(this);
            attachChild((IEntity) this.buttonList.get(i));
            registerTouchArea((ITouchArea) this.buttonList.get(i));
            ((ButtonSprite) this.buttonList.get(i)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) height, (float) height));
            height += 64;
        }
        String onText = "ＯＮ";
        if (this.bgmFlg == 0) {
            onText = "ＯＦＦ";
        }
        this.buttonListText[0] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "ＢＧＭ切り替え（現在：" + onText + "）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        String onText2 = "ＯＮ";
        if (this.soundFlg == 0) {
            onText2 = "ＯＦＦ";
        }
        this.buttonListText[1] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "効果音切り替え（現在：" + onText2 + "）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        if (2 <= this.userQuestStatus) {
            this.buttonListText[2] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "難易度変更（クエスト挑戦中は変更不可）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        } else {
            String onText3 = "イージー";
            if (this.userDifficult == 2) {
                onText3 = "ノーマル";
            } else if (this.userDifficult == 3) {
                onText3 = "ハード";
            } else if (this.userDifficult == 4) {
                onText3 = "ルナティック";
            }
            this.buttonListText[2] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "難易度変更（現在：" + onText3 + "）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        }
        this.buttonListText[3] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "主人公の名前を変更", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[4] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "主人公のアイコンを変更", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[5] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "ヘルプ/更新履歴/データ移行", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[6] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "[PR] CM視聴で霊力1000回復（1日1回）", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[7] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "レビューを投稿して特典ゲット", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        if (this.userSnsFlg == 0) {
            this.buttonListText[8] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "SNSでシェアしてカード引換券ゲット", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        } else if (this.userSnsFlg == 1) {
            this.buttonListText[8] = new Text(-486.0f, 0.0f, (IFont) this.fontBlack, (CharSequence) "[5万DL記念]リツイートで引換券ゲット", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        }
        for (int i2 = 0; i2 < this.optionButtonNum; i2++) {
            attachChild(this.buttonListText[i2]);
            this.buttonListText[i2].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) ((i2 * 64) + 140), (float) ((i2 * 64) + 140)));
        }
        menuBottomStart();
    }

    private void menuOptionClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.optionButtonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
    }

    /* JADX INFO: finally extract failed */
    private void menuIconChangeOpen() {
        this.menuMode = 43;
        Cursor cursor = null;
        int posX = 30;
        int posY = 140;
        this.listPageMax = 0;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT count(m.dress_id) FROM dress_m m", null);
            if (cursor2.moveToFirst()) {
                this.listPageMax = (int) Math.floor((double) ((((float) cursor2.getInt(0)) - 1.0f) / ((float) 36)));
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            headlineBoxOpen("　　　アイコン選択（" + (this.listPage + 1) + "／" + (this.listPageMax + 1) + "）", true);
            this.buttonList.add(0, getBaseActivity().getResourceUtil().getButtonSprite("charas/" + this.userIcon + ".png", "charas/" + this.userIcon + ".png"));
            ((ButtonSprite) this.buttonList.get(0)).setTag(10000000);
            ((ButtonSprite) this.buttonList.get(0)).setPosition(12.0f, 60.0f);
            ((ButtonSprite) this.buttonList.get(0)).setOnClickListener(this);
            attachChild((IEntity) this.buttonList.get(0));
            registerTouchArea((ITouchArea) this.buttonList.get(0));
            int no = 1;
            try {
                Cursor cursor3 = this.f220db.rawQuery("SELECT m.dress_id, m.dress_name FROM dress_m m ORDER BY m.sort ASC, m.dress_kana ASC, m.dress_id ASC LIMIT " + (this.listPage * 36) + ", " + 36, null);
                while (cursor3.moveToNext()) {
                    int id = cursor3.getInt(0);
                    this.buttonList.add(no, getBaseActivity().getResourceUtil().getButtonSprite("charas/" + id + ".png", "charas/" + id + ".png"));
                    ((ButtonSprite) this.buttonList.get(no)).setTag(10000000 + id);
                    ((ButtonSprite) this.buttonList.get(no)).setPosition((float) posX, (float) posY);
                    ((ButtonSprite) this.buttonList.get(no)).setOnClickListener(this);
                    attachChild((IEntity) this.buttonList.get(no));
                    registerTouchArea((ITouchArea) this.buttonList.get(no));
                    posX += 80;
                    if (460 <= posX) {
                        posX = 30;
                        posY += 80;
                    }
                    no++;
                }
                if (cursor3 != null) {
                    cursor3.close();
                }
                this.buttonNum = no;
                arrowOpen(91, 686, false);
                arrowOpen(92, 686, true);
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

    private void menuIconChangeClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < this.buttonNum; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
        }
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
    }

    private void menuIconChangeExe(int icon) {
        this.userIcon = icon;
        this.f220db.execSQL("UPDATE user_t SET user_icon= " + this.userIcon + ";");
    }

    private void menuHelpOpen() {
        this.menuMode = 42;
        menuBottomStart();
        headlineBoxOpen("ヘルプ/更新履歴/データ移行", true);
        int height = 130;
        for (int i = 0; i < 10; i++) {
            this.buttonList.add(i, getBaseActivity().getResourceUtil().getButtonSprite("button/button_base.png", "button/button_base_p.png"));
            ((ButtonSprite) this.buttonList.get(i)).setPosition(-500.0f, (float) height);
            if (i == 0) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(101);
            } else if (i == 1) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(102);
            } else if (i == 2) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(103);
            } else if (i == 3) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(108);
            } else if (i == 4) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(104);
            } else if (i == 5) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(105);
            } else if (i == 6) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(109);
            } else if (i == 7) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(106);
            } else if (i == 8) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(107);
            } else if (i == 9) {
                ((ButtonSprite) this.buttonList.get(i)).setTag(110);
            }
            ((ButtonSprite) this.buttonList.get(i)).setOnClickListener(this);
            attachChild((IEntity) this.buttonList.get(i));
            registerTouchArea((ITouchArea) this.buttonList.get(i));
            ((ButtonSprite) this.buttonList.get(i)).registerEntityModifier(new MoveModifier(0.4f, -500.0f, 40.0f, (float) height, (float) height));
            height += 60;
        }
        this.buttonListText[0] = new Text(-486.0f, 140.0f, (IFont) this.fontBlack, (CharSequence) "基本的な遊び方", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[1] = new Text(-486.0f, 200.0f, (IFont) this.fontBlack, (CharSequence) "コダマについて", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[2] = new Text(-486.0f, 260.0f, (IFont) this.fontBlack, (CharSequence) "お役立ち情報", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[3] = new Text(-486.0f, 320.0f, (IFont) this.fontBlack, (CharSequence) "属性相性表", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[4] = new Text(-486.0f, 380.0f, (IFont) this.fontBlack, (CharSequence) "ＢＧＭ一覧", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[5] = new Text(-486.0f, 440.0f, (IFont) this.fontBlack, (CharSequence) "更新履歴", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[6] = new Text(-486.0f, 500.0f, (IFont) this.fontBlack, (CharSequence) "コダマ名リセット", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[7] = new Text(-486.0f, 560.0f, (IFont) this.fontBlack, (CharSequence) "データバックアップ", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[8] = new Text(-486.0f, 620.0f, (IFont) this.fontBlack, (CharSequence) "データロード", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        this.buttonListText[9] = new Text(-486.0f, 680.0f, (IFont) this.fontBlack, (CharSequence) "チャンピオンロード前回戦績", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        for (int i2 = 0; i2 < 10; i2++) {
            attachChild(this.buttonListText[i2]);
        }
        for (int i3 = 0; i3 < 10; i3++) {
            this.buttonListText[i3].registerEntityModifier(new MoveModifier(0.4f, -486.0f, 54.0f, (float) ((i3 * 60) + 140), (float) ((i3 * 60) + 140)));
        }
    }

    private void menuHelpClose() {
        this.headlineBox.detachSelf();
        this.headlineBoxText.detachSelf();
        for (int i = 0; i < 10; i++) {
            ((ButtonSprite) this.buttonList.get(i)).detachSelf();
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
            this.buttonListText[i].detachSelf();
        }
    }

    private void attrTableOpen() {
        this.attrTable = getBaseActivity().getResourceUtil().getSprite("attr/table.png");
        this.attrTable.setPosition(0.0f, 72.0f);
        attachChild(this.attrTable);
        this.attrFlg = true;
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        this.buttonOk.setPosition(170.0f, 700.0f);
        this.buttonOk.setTag(10000000);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        menuBottomStop();
        this.menuAttrOpenFlg = true;
    }

    private void attrTableClose() {
        this.attrTable.detachSelf();
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        this.menuAttrOpenFlg = false;
    }

    /* JADX INFO: finally extract failed */
    private void unitNameResetExe() {
        Cursor cursor = null;
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.user_kodama_id, u.name, m.kodama_name FROM user_kodama_t u LEFT OUTER JOIN kodama_m m ON u.kodama_id=m.kodama_id WHERE " + (this.boxSelect * 100) + "<u.sort_no AND u.sort_no<" + ((this.boxSelect + 1) * 100) + " AND m.kodama_id<>0", null);
            while (cursor2.moveToNext()) {
                if (!cursor2.getString(1).equals(cursor2.getString(2))) {
                    this.f220db.execSQL("UPDATE user_kodama_t SET name=\"" + cursor2.getString(2) + "\" WHERE user_kodama_id=" + cursor2.getInt(0));
                }
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

    private void menuHelpDetailOpen() {
        String str;
        String str2;
        attachChild(this.informationBox);
        this.listPageMax = 1;
        String str3 = "";
        if (this.listHead == 1) {
            this.listPageMax = 9;
            str3 = "■基本的な遊び方（" + this.listPage + "／" + this.listPageMax + "）\n\n";
            if (this.listPage == 1) {
                str3 = ((((((((str3 + "▽目次\n\n") + "・東方玉霊姫とは\n") + "・霊力を消費してクエストに挑戦\n") + "・コダマ遊び（バトル）\n") + "・コダマ遊びの流れ\n") + "・コダマの攻撃とＶＰ\n") + "・コダマの交代\n") + "・コダマとスペルの属性\n") + "・属性一致スペル\n";
            } else if (this.listPage == 2) {
                str3 = (str3 + "▽東方玉霊姫とは\n\n") + "不思議な式神「コダマ」と共に\n幻想郷を巡り歩きながら、\n「コダマ遊び」で\n最強を目指すＲＰＧです。";
            } else if (this.listPage == 3) {
                str3 = (str3 + "▽霊力を消費してクエストに挑戦\n\n") + "クエストに挑戦すると、\nアイテムを獲得したり\nコダマを成長させたりできます。\n\nステージ初クリアの時は、\n普段と違う報酬がもらえます。\n\n更に新しいエリアやステージに\n挑戦できるようになることも。\n\n\nクエスト挑戦には「霊力」が必要です。\n\n霊力は３分につき１回復しますが、\nマイページを起動した時に\nオンラインである必要があります。";
            } else if (this.listPage == 4) {
                str3 = (str3 + "▽コダマ遊び（バトル）\n\n") + "幻想郷で最近流行っている、\nコダマ同士を戦わせる勝負のことです。\n\n\nコダマは最大６体まで\n同時に連れ歩けます。\n\n対戦は１対１で行い、\n弾幕やスペルで攻撃します。\n\n\nそして相手の全コダマを\n先に倒した（ＨＰを０にした）方が\n勝者となります。";
            } else if (this.listPage == 5) {
                str3 = (str3 + "▽コダマ遊びの流れ\n\n") + "まずは先鋒のコダマを決めます。\nお互いがコダマを繰り出したら\nバトル開始です。\n\n\n戦闘はターン制バトルです。\n\n１ターン内に行えるのは\n・攻撃（スペル）\n・コダマ交代\nのどちらかになります。\n\nお互いが攻撃または交代するか、\nどちらかのコダマが倒されると\n次のターンに移行します。";
            } else if (this.listPage == 6) {
                str3 = (str3 + "▽コダマの攻撃とＶＰ\n\n") + "弾幕またはスペルで\n相手を攻撃します。\n\nお互いが攻撃しようとした場合は、\n特別なスペルを使わない限り\n「速度」の高い方が先攻になります。\n\n\nスペルは強力ですが、\n使用する時に「ＶＰ」を消費します。\nＶＰが足りなくなると、\nスペルを使えなくなります。\n\nスペルは「やすむ」を使うか、\n控えにいると少しずつ回復していきます。";
            } else if (this.listPage == 7) {
                str3 = (str3 + "▽コダマの交代\n\n") + "戦闘中のコダマと\n控えのコダマを入れ替えます。\n\n交代は必ず「攻撃の前」に行います。\nまた交代すると１ターン消費します。\n";
            } else if (this.listPage == 8) {
                str3 = (str3 + "▽コダマとスペルの属性\n\n") + "各コダマとスペルは、\nそれぞれ固有の「属性」を持ちます。\n\n属性は１８種類。\nコダマは最大で２つ、\nスペルは１つの属性を持ちます。\n\n\n属性には相性があり、\n有利な属性･不利な属性があります。\n\n相手の弱点を突けば、\nダメージは２～３倍に増加。\n逆に不利な属性の場合は\n１／２～１／３に減少。\n\n\n相性はややこしいですが、\nうまく利用することで\nバトルを有利に運ぶことができます。";
            } else if (this.listPage == 9) {
                str3 = (str3 + "▽属性一致スペル\n\n") + "自分が持つ属性と\n同じ属性のスペルを使うと、\nスペル威力が1.5倍になります。\n\n同じ威力、同じ相性のスペルなら\n自分が持つ属性と同じ属性のスペルで\n攻撃させましょう。";
            }
        } else if (this.listHead == 2) {
            this.listPageMax = 9;
            str3 = "■コダマについて（" + this.listPage + "／" + this.listPageMax + "）\n\n";
            if (this.listPage == 1) {
                str3 = ((((((((str3 + "▽目次\n\n") + "・コダマとの契約\n") + "・コダマの能力\n") + "・コダマのレベルアップ\n") + "・ＢＰとステータスボーナス\n") + "・スペルの習得と☆\n") + "・コダマの進化\n") + "・スキルの習得\n") + "・閑話：コダマについて\n";
            } else if (this.listPage == 2) {
                str3 = (str3 + "▽コダマとの契約\n\n") + "「コダマカード」を使うと、\nコダマカードを1枚消費して\n新しいコダマと契約できます。\n\nコダマカードは、\nクエスト等で手に入る「福袋」を開けたり\nアイテムショップで購入することで\n入手できます。\n\n\nコダマカードは使う以外に、\nコダマに装備させることで\nコダマをパワーアップすることもできます。";
            } else if (this.listPage == 3) {
                str3 = (str3 + "▽コダマの能力\n\n") + "ＨＰ：コダマの体力です。\n　攻撃されると減少し、\n　０になると気絶します。\n\nVP：スペルを使うと減少します。\n　これが足りなくなると、\n　スペルが使えなくなります。\n\n攻撃：コダマの攻撃力です。\n　この数値が高いほど、\n　ダメージを与えやすくなります。\n\n防御：コダマの防御力です。\n　この数値が高いほど、\n　ダメージを受けにくくなります。\n\n速度：コダマの速さです。\n　この数値が高いほど、\n　戦闘で先手を取りやすくなります。";
            } else if (this.listPage == 4) {
                str3 = (str3 + "▽コダマのレベルアップ\n\n") + "コダマ遊びで勝つと、\nパーティー内のコダマは\n経験値を獲得できます。\n\n経験値が一定値に達すると\nコダマはレベルアップして、\n各能力値が増加します。\n\n\nまた獲得できる経験値は、\nマップLvとコダマLvの\n差に比例して変動します。";
            } else if (this.listPage == 5) {
                str3 = (str3 + "▽ＢＰとステータスボーナス\n\n") + "あるアイテムを使うことで、\nコダマはＢＰを獲得できます。\n\n更にＢＰを消費することで、\nコダマの能力をパワーアップできます。\n\nパワーアップの上限は64です。\n割り振ったＢＰは払い戻しもできるので、\n気軽にどんどんパワーアップさせましょう。\n";
            } else if (this.listPage == 6) {
                str3 = (str3 + "▽スペルの習得と☆\n\n") + "銭を消費することで、\nコダマはスペルを習得できます。\n\nまた選択画面に「☆」マークが\n表示されているスペルは、\nかなり後半で手に入るアイテム\n「禁呪の書」で強化できます。\n\nスペルは忘れることもできますが、\n銭が払い戻されたりはしないので\n注意してください。";
            } else if (this.listPage == 7) {
                str3 = (str3 + "▽コダマの進化\n\n") + "アイテム「霊珠」を使うことで、\n一部のコダマは進化することができます。\n\n進化すると能力が上昇し、\n属性や習得スペルが変化します\n（変化しないコダマもいます）。\n\n使うアイテムによって、\n進化先が変わるコダマもいます。";
            } else if (this.listPage == 8) {
                str3 = (str3 + "▽スキルの習得\n\n") + "進化させたコダマは、\nアイテム「秘伝の書」を使うことで\n「スキル」を習得できます。\n\nスキルは覚えているだけで、\n戦闘中に自動で効果を発揮します。\n\nうまく使いこなして、\n戦闘を有利に進めてください。\n\n \nまたあるアイテムを使うことで、\nコダマのスキルレベルを上昇できます。\n\nスキルレベルが上がると、\nスキルの効果がより強力になります。";
            } else if (this.listPage == 9) {
                str3 = (str3 + "▽閑話：コダマについて\n\n") + "幻想郷に突然現れた、\n幻想郷の少女やら何やらによく似た\n小さくて不思議な式神です。\n\nスキマ妖怪の紫（ゆかり）曰く\n「とある異界の守り神」だそうですが、\nなぜ幻想郷に出現したかは謎とのこと。\n\n\n身長は高くても腰丈ぐらいまで、\n体重は人間の子供よりも軽いです。\n\nまた会話はできませんが、\nわずかながらも記憶と感情を持っており、\n使い手の優しさが足りないと\n契約破棄されることもあったりするとか。";
            }
        } else if (this.listHead == 3) {
            this.listPageMax = 7;
            str3 = "■お役立ち情報（" + this.listPage + "／" + this.listPageMax + "）\n\n";
            if (this.listPage == 1) {
                str3 = ((((((str3 + "▽目次\n\n") + "・コンティニューを活用\n") + "・難易度ボーナス\n") + "・クエスト/バトルは再開可能\n") + "・能力上昇/減少効果について\n") + "・サブエリア\n") + "・アプリは削除しないで\n";
            } else if (this.listPage == 2) {
                str3 = (str3 + "▽コンティニューを活用\n\n") + "バトルで負けたとしても、\n１回のクエスト挑戦につき\n10回までコンティニューできます。\n\nうまくパーティーを入れ替えながら、\n何度も挑戦してみましょう。";
            } else if (this.listPage == 3) {
                str3 = (str3 + "▽難易度ボーナス\n\n") + "難易度を上げると敵が強くなりますが、\nハード以上の難易度でクリアすると\n「難易度ボーナス」を獲得できます。\n\n難易度ボーナスが一定値に達するごとに\n報酬アイテムをもらうことができるので、\n余裕があれば挑戦してみましょう。\n\nちなみに低難易度で初クリアした後、\n難易度を上げてから再度クリアしても\nボーナスをもらうことができます。";
            } else if (this.listPage == 4) {
                str3 = (str3 + "▽クエスト/バトルは再開可能\n\n") + "クエスト挑戦中やバトル中に\nアプリを終了させても、\n同じところから再開できます。";
            } else if (this.listPage == 5) {
                str3 = (str3 + "▽能力上昇/減少効果について\n\n") + "能力の上昇/減少効果は\n戦闘中ずっと効果が続きます。\n\n上昇値の上限は\n元の能力値の２倍まで。\n下限値は１／５です。";
            } else if (this.listPage == 6) {
                str3 = (str3 + "▽サブエリア\n\n") + "本編とは別口の育成用エリアです。\n\n基本的には通常クエスト同様ですが、\n・ドロップアイテムが特殊\n・本編クリア状況に応じてステージ解放\n・敵コダマ使いがランダムで出現\nなどの特徴があります。";
            } else if (this.listPage == 7) {
                str3 = (str3 + "▽アプリは削除しないで\n\n") + "アプリを削除すると、\nセーブデータも消えてしまうので\n注意してください。";
            }
        } else if (this.listHead == 4) {
            this.listPageMax = 8;
            String str4 = "■ＢＧＭ一覧（" + this.listPage + "／" + this.listPageMax + "）\n\n";
            this.tmpStr.setLength(0);
            if (this.listPage == 1) {
                str4 = str4 + "※注意事項\n当アプリにおける各楽曲は、\n容量の都合により、\n音質をかなり落とした上で\n利用させて頂いております。\n\n気になった曲がありましたら、\nぜひ制作主様のサイト等で\n原曲を聞いてみてください。";
            } else if (this.listPage == 2) {
                this.tmpStr.append("▽タイトルＢＧＭ\n");
                this.tmpStr.append("「神々が恋した幻想郷アレンジ」\n");
                this.tmpStr.append("a-TTTempo/紫苑様\n\n");
                this.tmpStr.append("▽通常戦闘ＢＧＭ\n");
                this.tmpStr.append("「恋色マスタースパークアレンジ」\n");
                this.tmpStr.append("a-TTTempo/紫苑様\n\n");
                this.tmpStr.append("▽ボス戦闘ＢＧＭ\n");
                this.tmpStr.append("「U.N.オーエンは彼女なのか？アレンジ」\n");
                this.tmpStr.append("a-TTTempo/紫苑様\n\n");
                this.tmpStr.append("▽阿澄酒場etc：道中ＢＧＭ\n");
                this.tmpStr.append("「今日も平和な聖輦船」\n");
                this.tmpStr.append("毛玉の地下基地/陰陽師斑鳩様\n\n");
                this.tmpStr.append("▽オウカジムetc：ボスＢＧＭ\n");
                this.tmpStr.append("「妖魔夜行」\n");
                this.tmpStr.append("Rainbow Records/kaleido sly様\n\n");
                this.tmpStr.append("▽白瓏洞etc：ボスＢＧＭ\n");
                this.tmpStr.append("「フライル」\n");
                this.tmpStr.append("毛玉の地下基地/陰陽師斑鳩様");
            } else if (this.listPage == 3) {
                this.tmpStr.append("▽エリア１etc：道中ＢＧＭ\n");
                this.tmpStr.append("「神々が恋した幻想郷アレンジ」\n");
                this.tmpStr.append("東方MIDI/氷結様\n\n");
                this.tmpStr.append("▽エリア２etc：道中ＢＧＭ\n");
                this.tmpStr.append("「ルーネイトエルフアレンジ」\n");
                this.tmpStr.append("東方MIDI/氷結様\n\n");
                this.tmpStr.append("▽エリア４etc：道中ＢＧＭ\n");
                this.tmpStr.append("「魔法使いの憂鬱」\n");
                this.tmpStr.append("こん茶/薙傘はる様\n\n");
                this.tmpStr.append("▽エリア４etc：ボスＢＧＭ\n");
                this.tmpStr.append("「暗天への灯火 ～ Heaven Desires」\n");
                this.tmpStr.append("東方偽神霊廟/Wanwan様他\n\n");
                this.tmpStr.append("▽エリア７etc：道中ＢＧＭ\n");
                this.tmpStr.append("「U.N.オーエンは彼女なのか？アレンジ」\n");
                this.tmpStr.append("東方MIDI/氷結様\n\n");
                this.tmpStr.append("▽エリア７etc：ボスＢＧＭ\n");
                this.tmpStr.append("「亡き王女の為のセプテット」\n");
                this.tmpStr.append("東方MIDI/氷結様\n\n");
            } else if (this.listPage == 4) {
                this.tmpStr.append("▽エリア９etc：道中ＢＧＭ\n");
                this.tmpStr.append("「少女が見た日本の原風景アレンジ」\n");
                this.tmpStr.append("東方MIDI/氷結様\n\n");
                this.tmpStr.append("▽エリア９etc：ボスＢＧＭ\n");
                this.tmpStr.append("「幽雅に咲かせ、墨染の桜\n　 ～ Border of Life」\n");
                this.tmpStr.append("七色工房/いづな様\n\n");
                this.tmpStr.append("▽エリア10etc：道中ＢＧＭ\n");
                this.tmpStr.append("「上海紅茶館アレンジ」\n");
                this.tmpStr.append("東方MIDI/氷結様\n\n");
                this.tmpStr.append("▽エリア10etc：ボスＢＧＭ\n");
                this.tmpStr.append("「少女幻葬 ～ Necro-Fantasy＆\n　ネクロファンタジア」\n");
                this.tmpStr.append("a-TTTempo/紫苑様\n\n");
                this.tmpStr.append("▽エリア11etc：ボスＢＧＭ\n");
                this.tmpStr.append("「ポイズンボディアレンジ」\n");
                this.tmpStr.append("毛玉の地下基地/陰陽師斑鳩様\n\n");
                this.tmpStr.append("▽エリア15etc：ボスＢＧＭ\n");
                this.tmpStr.append("「千年幻想郷アレンジ」\n");
                this.tmpStr.append("東方MIDI/氷結様\n\n");
            } else if (this.listPage == 5) {
                this.tmpStr.append("▽エリア21etc：道中ＢＧＭ\n");
                this.tmpStr.append("「風神少女アレンジ」\n");
                this.tmpStr.append("a-TTTempo/紫苑様\n\n");
                this.tmpStr.append("▽エリア21etc：ボスＢＧＭ\n");
                this.tmpStr.append("「ネイティブフェイス」\n");
                this.tmpStr.append("Rainbow Records/kaleido sly様\n\n");
                this.tmpStr.append("▽エリア22etc：道中ＢＧＭ\n");
                this.tmpStr.append("「彼は誰時の龍宮庭」\n");
                this.tmpStr.append("刻刻音樂館/samidare様\n\n");
                this.tmpStr.append("▽エリア22etc：ボスＢＧＭ\n");
                this.tmpStr.append("「反逆の烽火 ～ revolutionary war」\n");
                this.tmpStr.append("椿木の箱庭/深蒼穹様\n\n");
                this.tmpStr.append("▽エリア26etc：ボスＢＧＭ\n");
                this.tmpStr.append("「不調和世界 ～ broken doll_Full」\n");
                this.tmpStr.append("椿木の箱庭/深蒼穹様\n\n");
            } else if (this.listPage == 6) {
                this.tmpStr.append("▽エリア29etc：道中ＢＧＭ\n");
                this.tmpStr.append("「無間の鐘 ～ Infinite Nightmare」\n");
                this.tmpStr.append("こん茶/薙傘はる様\n\n");
                this.tmpStr.append("▽エリア30etc：道中ＢＧＭ\n");
                this.tmpStr.append("「ネメシスの要塞」\n");
                this.tmpStr.append("こん茶/薙傘はる様\n\n");
                this.tmpStr.append("▽エリア30etc：ボスＢＧＭ\n");
                this.tmpStr.append("「4面以降のBOSSっぽい曲」\n");
                this.tmpStr.append("刻刻音樂館/samidare様\n\n");
                this.tmpStr.append("▽エリア33etc：道中ＢＧＭ\n");
                this.tmpStr.append("「悔恨の洞穴 ～ \n　regret and confession」\n");
                this.tmpStr.append("椿木の箱庭/深蒼穹様\n\n");
            } else if (this.listPage == 7) {
                this.tmpStr.append("▽エリア33etc：ボスＢＧＭ\n");
                this.tmpStr.append("「大尉の愛した数式」\n");
                this.tmpStr.append("東方白塵記様\n\n");
                this.tmpStr.append("▽エリア40etc：道中ＢＧＭ\n");
                this.tmpStr.append("「霧の湖の大妖精」\n");
                this.tmpStr.append("毛玉の地下基地/陰陽師斑鳩様\n\n");
                this.tmpStr.append("▽EXエリア3etc：道中ＢＧＭ\n");
                this.tmpStr.append("「年中夢中の好奇心」\n");
                this.tmpStr.append("月之下遊歩道/葵様\n\n");
                this.tmpStr.append("▽EXエリア3etc：ボスＢＧＭ\n");
                this.tmpStr.append("「妖精大戦争 ～ Faily Wars」\n");
                this.tmpStr.append("こん茶/薙傘はる様\n\n");
                this.tmpStr.append("▽EXエリア4etc：道中ＢＧＭ\n");
                this.tmpStr.append("「幽酒の酒精」\n");
                this.tmpStr.append("刻刻音樂館/samidare様\n\n");
            } else if (this.listPage == 8) {
                this.tmpStr.append("▽ポケットタワー：ボスＢＧＭ\n");
                this.tmpStr.append("「波数高低 ～ Kayser Kaiser」\n");
                this.tmpStr.append("椿木の箱庭/深蒼穹様\n\n");
                this.tmpStr.append("▽EXエリア18etc：ボスＢＧＭ\n");
                this.tmpStr.append("「有頂天変 ～ Wonderful Heave」\n");
                this.tmpStr.append("刻刻音樂館/samidare様\n\n");
                this.tmpStr.append("▽EXエリア19etc：ボスＢＧＭ\n");
                this.tmpStr.append("「平安のエイリアン」\n");
                this.tmpStr.append("こん茶/薙傘はる様\n\n");
                this.tmpStr.append("▽EXエリア31etc：ボスＢＧＭ\n");
                this.tmpStr.append("「妖精たちの輪舞曲」\n");
                this.tmpStr.append("椿木の箱庭/深蒼穹様\n\n");
            }
            str3 = str4 + this.tmpStr.toString();
        } else if (this.listHead == 5) {
            this.listPageMax = 41;
            str3 = "■更新履歴（" + this.listPage + "／" + this.listPageMax + "）\n\n";
            if (this.listPage == this.listPageMax - 40) {
                str3 = ((str3 + "▽Ver2.4.6 [2018年10月17日]\n・バックアップ機能の不具合対応\n\n") + "▽Ver2.4.5 [2018年10月10日]\n・戦闘再開時の広告不具合対応\n\n") + "▽Ver2.4.3-4 [2018年10月1日]\n・Google規約対応に伴う内部修正\n　動画広告の仕様を変更\n　（回復量100→1000、\n　　1日1回までに変更）\n\n";
            } else if (this.listPage == this.listPageMax - 39) {
                str3 = (((str3 + "▽Ver2.4.2 [2017年06月13日]\n・登録直後に霊力が\n　回復しない不具合を修正\n\n") + "▽Ver2.4.0 [2017年05月31日]\n・霊力上限を変更（1000→3000）\n・動画広告による霊力回復を実装\n\n") + "▽Ver2.3.1 [2017年03月12日]\n・バックアップデータ破損時の\n　復旧用機能を改修\n\n") + "▽Ver2.3.0 [2017年01月04日]\n・アイテム名称の設定ミスを修正\n　（ミニ八卦路→ミニ八卦炉）\n・全体的に場面遷移時間を短縮\n・全体的にＢＧＭ音量を調整\n・バックアップ機能を改修\n　※バックアップ後、\n　　メニュー画面へ戻るように変更\n　※バックアップ時、\n　　ID/パスワードを変えないよう変更\n\n";
            } else if (this.listPage == this.listPageMax - 38) {
                str3 = ((str3 + "▽Ver2.2.1 [2016年7月5日]\n・自爆スペル使用時に\n　敵コダマを倒した場合、\n　そのまま続投できてしまう\n　不具合を修正") + "\n\n▽Ver2.2.0 [2016年4月13日]\n・spモードフィルタなどの対応のため\n　通信サーバを変更\n※上記に伴い、\n　Ver2.1.1以前のバックアップは\n　Ver2.2.0以降で\n　ロードできなくなります。") + "\n\n▽Ver2.1.0 [2016年4月6日]\n・チャンピオンロード26～30実装\n※現在はEXエリア33、\n　PHエリア5、\n　ロードエリア30まで公開中。\n　なおEXエリア等の追加エリアは\n　通常エリアを進めていくと\n　挑戦できるようになります。";
            } else if (this.listPage == this.listPageMax - 37) {
                str3 = str3 + "▽Ver2.0.3 [2016年3月24日]\n・チャンピオンロード23～25実装\n\n▽Ver2.0.2 [2016年3月4日]\n・チャンピオンロード19～22実装\n・バックアップ不具合を修正\n　※「oldFileCheck」エラー対応\n\n▽Ver2.0.1 [2016年2月23日]\n・チャンピオンロード13～18実装\n・通信不具合を一部改修\n\n▽Ver2.0.0 [2016年2月16日]\n・チャンピオンロード7～12実装\n・バックアップ機能を改修\n・「★天目一箇」の消費ＶＰを修正";
            } else if (this.listPage == this.listPageMax - 36) {
                str3 = str3 + "▽Ver1.8.0.3 [2016年2月9日]\n・習得できない禁呪の性能も\n　表示されてしまう不具合を修正\n・禁呪の書補填不具合を修正\n\n▽Ver1.8.0 [2016年2月6日]\n・チャンピオンロード実装\n　※PHエリア制覇で出現\n・EXエリア「異空穴」実装\n・サブエリアに新ステージを追加\n　※霊酒を余らせてる方向け\n・全体的なゲームバランスを調整\n　※詳細な変更内容は、\n　　PlayStoreからリンクされている\n　　専用ページにて確認できます。\n・紺珠伝ちびコダマを実装\n・コダマカード引換券対象に\n　紺珠伝キャラを追加\n・霊酒使用時の回復上限を変更\n・一部インターフェースを改修\n・ログインボーナスを増量\n　※霊酒：3個→5個\n・ヘルプに前回ロード戦績を追加";
            } else if (this.listPage == this.listPageMax - 35) {
                str3 = str3 + "▽Ver1.7.11 [2015年12月8日]\n・一部コダマの仕様変更、禁呪追加\n　（ＤＨキスメ/ＡＨＳ小悪魔/\n　　ＨＮＳスター/ゾンビＦ/\n　　ＡＮＴ大妖精/ＡＨＳてゐ/\n　　ＡＤＳ豊姫/ＡＤＮＳパチュリー/\n　　ＡＨＮＳ布都/ＡＮＳマミゾウ/\n　　ＤＨＴリリカ/輪妖精/ＤＮＳルナ/\n　　Ｓルナサ/ＡＤＮ霊夢/Ｎわかさぎ姫）";
            } else if (this.listPage == this.listPageMax - 34) {
                str3 = (str3 + "▽Ver1.7.10 [2015年12月1日]\n・PHエリア5を追加実装\n・一部コダマの仕様変更、禁呪追加\n　（ＳＴ幻月/玄爺/ＨＴこころ/Ｎ呪い子/\n　　ＡＤＮ神子/ＡＳ夢月/ＡＮＳＴ幽々子/\n　　ＡＮ理香子/蓮子/Ｓ靈夢）\n・相手の属性１で攻撃するスペルについて\n　属性一致判定は元属性で行うよう修正\n\n") + "▽Ver1.7.9 [2015年11月24日]\n・EXエリア33を追加実装\n・夢幻印の福袋に追加\n　（ヘカーティアカード）\n・一部コダマの仕様変更、禁呪追加\n　（ＡＮＳ文/ＡＮＳ輝夜/Ｈキクリ/\n　　Ｔ響子/ＮＨ朱鷺子/ＡＮＳにとり/\n　　ＮＳぬえ/ＡＴマガン/ＡＤ椛/\n　　ＡＴリリーＢ/ＨＮリリーＷ/\n　　Ｈルイズ）\n・ショップに新装備を追加\n　※通常エリア40クリアおよび\n　　EXエリア20で出現します。";
            } else if (this.listPage == this.listPageMax - 33) {
                str3 = str3 + "▽Ver1.7.8 [2015年11月17日]\n・EXエリア32を追加実装\n・夢幻印の福袋に追加（純狐カード）\n・一部コダマに禁呪を追加（ＡＳＴはたて/\n　ＡＳナズーリン/ＡＮＴ勇儀）\n・Ａ華扇/Ｎ勇儀にスペルを追加\n・一部コダマのスペル効果を変更\n　（Ａ華扇：★画竜点睛、\n　　Ｄ神玉：陰性招来/陽性招来、\n　　Ｎ橙：★奇門遁甲、\n　　Ｔはたて：★リバーサルムーン）\n・Ｔクラウンのスペル性能を一部変更\n　（スプライプドアビス：炎→反動付き然\n　　かすりの獄意：VPダメージスペル化）\n・阿求のスキル効果を変更\n・Ｎ正邪、Ｔ正邪、霖之助の\n　習得スペル･スキルを大幅に調整\nキューティ大千槍のVP設定ミス修正\n夢我夢中のエフェクト設定ミス修正";
            } else if (this.listPage == this.listPageMax - 32) {
                str3 = (str3 + "▽Ver1.7.7 [2015年11月10日]\n・ポケットタワー12を追加実装\n・ミスティアに新進化先を追加（Ａタイプ）\n・一部コダマに禁呪を追加（シュガー/\n　瀬笈葉/Ｈちゆり/ＡＤＨ雛/\n　ＨＮミスティア/ADミスティ/\n　ＡＤＮ幽香/ＡＳユウカ/Ｔ夢美）\n・Ｈミスティアにスペルを追加\n\n") + "▽Ver1.7.6 [2015年11月3日]\n・EXエリア31を追加実装\n・夢幻印の福袋に追加\n　（クラウンピースカード）\n・妹紅に新進化先を追加（Ａタイプ）\n・一部コダマに禁呪を追加（ＤＥＮ慧音/\n　ＨＮＴさとり/ＤＮ妹紅/ＨＮＴヤマメ/\n　ＥＨリグル）\n・Ｅ慧音/Ｈヤマメの能力設定ミスを修正\n・Ｅリグルにスペルを追加\n・EXエリア7以降のマップLvを下方修正\n・EXエリア20の敵アイコンの\n　設定ミスを修正（雛→穣子）\n・カード引換券対象に菫子カードを追加\n";
            } else if (this.listPage == this.listPageMax - 31) {
                str3 = str3 + "▽Ver1.7.5 [2015年10月24日]\n・EXエリア30を追加実装\n・夢幻印の福袋に追加\n　（サグメカード）\n・依姫に新進化先を追加（Ｎタイプ）\n・一部コダマに禁呪を追加（ＡＨ空/\n　ＤＳコンガラ/Ｔサラ/\n　ＡＤＨＮメディスン/ＡＳ依姫）\n・スペル「国士無双の薬」習得時に\n　強制終了する不具合を修正\n・エリア25クリア時に商品を追加\n・霊珠の売却額を変更\n・ルーミアカードの表示順を修正\n\n";
            } else if (this.listPage == this.listPageMax - 30) {
                str3 = (str3 + "▽Ver1.7.4.1 [2015年10月20日]\n・ポケットタワー11を追加実装\n・美鈴に新進化先を追加（Ｔタイプ）\n・一部コダマに禁呪を追加（ＡＮＳ一輪/\n　ＨＴオレンジ/ＡＤＨＴ萃香/\n　ＤＮＳ天子/ＥＮＨ美鈴/AD美鈴）\n・一部禁呪が動作しない不具合を修正\n\n") + "▽Ver1.7.3 [2015年10月13日]\n・EXエリア28、29を追加実装\n・夢幻印の福袋に追加\n　（鈴瑚/ドレミーカード）\n・鈴仙に新進化先を追加（Ｓタイプ）\n・一部コダマに禁呪を追加（ＡＨＳてゐ/\n　ADてゐ/ＡＮＳマミゾウ/ＡＮＴ鈴仙/\n　ＮＳレイセン）\n・星スペルの設定ミスを修正\n　（★正義の威光：消費ＶＰ20→40）\n・マミゾウのスペル効果を一部変更\n　（落葉化弾幕変化）\n\n";
            } else if (this.listPage == this.listPageMax - 29) {
                str3 = (str3 + "▽Ver1.7.2 [2015年10月6日]\n・ポケットタワー10を追加実装\n・星に新進化先を追加（Ｄタイプ）\n・一部コダマに禁呪を追加（ＨＴサリエル/\n　則紗/ＡＮ星/先代の巫女/ＡＳ魅魔）\n・神子スペルの誤記を修正\n　（詔を承けては必ず鎮め→慎め）\n\n") + "▽Ver1.7.1 [2015年9月28日]\n・EXエリア26、27を追加実装\n・夢幻印の福袋に追加（清蘭カード）\n・雷鼓に新進化先を追加（Ａタイプ）\n・一部コダマに禁呪を追加（Ｈ青娥/\n　Ｎ弁々/Ｄ八橋/ＡＨ芳香/Ｎ雷鼓）\n・芳香のスペル効果を一部変更\n　（ポイズンマーダー/死なない殺人鬼）\n・レイセン（玉兎）の衣装を追加\n・オウカジムにステージ3を追加\n　（PHエリア2クリアで登場します）\n\n";
            } else if (this.listPage == this.listPageMax - 28) {
                str3 = str3 + "▽Ver1.7.0 [2015年9月20日]\n・PHエリア4を追加実装\n・永琳に新進化先を追加（Ｓタイプ）\n・一部コダマに禁呪を追加\n　（ＨＮＴ永琳/大ナマズ/Ａ魔梨沙）\n・一部コダマの名称変更（Ｅ霊夢→Ｍ/\n　Ｅ妖夢→ＧＬ/Ｅ諏訪子→ＧＭ）\n・ＰＨエリアの仕様を大幅に変更\n　※気絶対象エリアから除外\n　※装備制限を削除\n　※難易度/マップLvを増加\n　※エリア１から順番に変更\n・PHエリア/ポケットタワーのみ\n　後から高難易度に挑戦しても\n　不足分の賞品がもらえるよう修正\n・PHエリア/ポケットタワー\n　ステージ７の報酬量を修正\n　（ノーマル以下：２個→１個）\n・PHエリア1クリア時に商品を追加\n・極致の霊珠の売却額を変更\n\n";
            } else if (this.listPage == this.listPageMax - 27) {
                str3 = (str3 + "▽Ver1.6.9 [2015年9月8日]\n・EXエリア24、25を追加実装\n・夢幻印の福袋に追加（リコカード）\n・レティに新進化先を追加（Ａ/Ｈタイプ）\n・一部コダマに禁呪を追加（ＤＮエリー/\n　Ａカナ/ＤＨＴ早苗/ＡＤ豊姫/\n　ADレティ/ＤＳレティ）\n\n") + "▽Ver1.6.10 [2015年9月15日]\n・ポケットタワー9を追加実装\n・豊姫に新進化先を追加（Ｓタイプ）\n・魔理沙に新進化先を追加（Ｎタイプ）\n・一部コダマに禁呪を追加（Ｔマイ/\n　ＡＥＳＴ魔理沙/ＡＤマリサ/\n　ミミちゃん/る～こと）\n・Ｓユキにスペルを追加\n・燐スペルの効果を強化（焦熱地獄）\n\n";
            } else if (this.listPage == this.listPageMax - 26) {
                str3 = (str3 + "▽Ver1.6.7 [2015年8月25日]\n・EXエリア22、23を追加実装\n・夢幻印の福袋に追加（命廟カード）\n・衣玖に新進化先を追加（Ｄタイプ）\n・村紗に新進化先を追加（Ｅタイプ）\n・一部コダマに禁呪を追加（ＮＡ衣玖/\n　ＡＴ華扇/ＤＴ屠自古/ＳＴ村紗）\n・ちびすわこがスペルを習得できなく\n　なっていた不具合を修正\n・ビビットのスペル設定ミスを修正\n　（マーキュリカルレガシー→\n　　　ビロードカーテン\n\n") + "▽Ver1.6.8 [2015年9月1日]\n・ポケットタワー8を追加実装\n・橙に新進化先を追加（Ａタイプ）\n・ルーミアに新進化先を追加（Ｔタイプ）\n・一部コダマに禁呪を追加（Ｔ響子/ＮＳ橙/\n　AD橙/ＮＳ呪い子/ＥＮＳルーミア）\n・Ｓ橙にスペルを追加\n・Ｈ芳香のスペル設定ミスを修正\n　（稼欲霊招来の習得金額）\n・Ｓ呪い子スペルエフェクト修正\n　（呪詛返し）\n\n";
            } else if (this.listPage == this.listPageMax - 25) {
                str3 = (str3 + "▽Ver1.6.5 [2015年8月11日]\n・ポケットタワー7を追加実装\n・小傘に新進化先を追加（Ｎタイプ）\n・にとりに新進化先を追加（Ｄタイプ）\n・一部コダマに禁呪を追加\n　（伊佐美/ＡＳ小傘/Ｎわかさぎ姫）\n・Ｓにとりの習得スペルを追加\n・小傘のスペル効果を一部変更\n　（超撥水かさかさお化け）\n") + "▽Ver1.6.6 [2015年8月18日]\n・PHエリア3を追加実装\n・パルスィに新進化先を追加（Ａ/Ｔタイプ）\n・一部コダマに禁呪を追加（ＡＤＨ神奈子/\n　ＡＤＳ諏訪子/ＨＳパルスィ）\n・Ａ神奈子にスペルを追加\n・パルスィのスペル効果を一部変更\n　（シロの灰）\n\n";
            } else if (this.listPage == this.listPageMax - 24) {
                str3 = str3 + "▽Ver1.6.4 [2015年8月2日]\n・EXエリア20、21を追加実装\n・夢幻印の福袋に追加（杏カード）\n・ルナサに新進化先を追加（Ｓタイプ）\n・メルランに新進化先を追加（Ｔタイプ）\n・リリカに新進化先を追加（Ｄタイプ）\n・一部コダマに禁呪を追加\n　（ＡＳメルラン/ＨＴリリカ/\n　　ＡＴルナサ/ＤＨレイラ）\n・Ｔリリカのスペル効果を一部変更\n　（★ペルツィーナ幻奏）\n・秋姉妹スペルのＶＰ設定ミスを修正\n　（★トワイライトガーデン）\n・「日月神旗」の売却額を変更\n・通常エリアの補填が抜けていたため、\n　不具合があったVer1.6.3.1～へ\n　更新されていた方に限り、\n　クリア状況に応じて補填アイテム\n　（博麗の涙）をお送り致しております。\n　この度は大変ご迷惑をお掛けしました。\n";
            } else if (this.listPage == this.listPageMax - 23) {
                str3 = str3 + "▽Ver1.6.3.3 [2015年7月29日]\n・売却機能全般の不具合を修正\n\n■売却機能に関する不具合について\n・Ver1.6.3以前において、\n　博麗の涙を約80個以上所持して\n　「まとめて売却」を行うと、\n　所持金がマイナスになる\n・Ver1.6.3.1において\n　「まとめて売却」を行うと、\n　博麗の涙がそのまま消失する\n上記２点の不具合が発生しました。\n\n補填としましてマイナス額の修正、\nまたVer1.6.3.1へ更新された方に\n塔/PHエリアのクリア状況に応じて\n補填アイテム「博麗の涙」を\n送付させて頂いております。\n\n";
            } else if (this.listPage == this.listPageMax - 22) {
                str3 = ((str3 + "▽Ver1.6.3 [2015年7月28日]\n・PHエリア2を追加実装\n・小悪魔に新進化先を追加（Ａタイプ）\n・パチュリーに新進化先を追加（Ｓタイプ）\n・一部コダマに禁呪を追加\n　（ＨＳ小悪魔/ＮＳ神綺）\n・Ｈ小悪魔にスペルを追加\n・コダマボックスを拡張（10→20）\n\n") + "▽Ver1.6.3.1 [2015年7月29日]\n・Startボタン押下直後に\n　稀に起動できなくなる不具合を修正\n・霊石売却額が一定値以上だった場合\n　所持金が減少する不具合を修正\n・「博麗の涙」を一時的に\n　まとめて売却できないよう変更\n\n") + "▽Ver1.6.3.2 [2015年7月29日]\n・売却不具合を一部修正\n\n";
            } else if (this.listPage == this.listPageMax - 21) {
                str3 = (str3 + "▽Ver1.6.1 [2015年7月14日]\n・EXエリア19を追加実装\n・夢幻印の福袋に追加（天夢カード）\n・PHエリア1を追加実装\n　※エリア45クリアで登場します\n・咲夜に新進化先を追加（Ｓ/Ｔタイプ）\n・一部コダマに禁呪を追加\n　（ＡＮ咲夜/ＡＨ夢子/ＮＳ明羅）\n・ポケットタワー開放条件を変更\n　※エリア41クリア→43クリア\n\n") + "▽Ver1.6.2 [2015年7月21日]\n・ポケットタワー6を追加実装\n・映姫に新進化先を追加（Ｈタイプ）\n・小町に新進化先を追加（Ｄタイプ）\n・一部コダマに禁呪を追加\n　（Ｔ映姫/Ｓ小兎姫/ＡＨ小町）\n\n";
            } else if (this.listPage == this.listPageMax - 20) {
                str3 = (str3 + "▽Ver1.5.9 [2015年6月29日]\n・エリア45を追加実装\n・EXエリア17を追加実装\n・夢幻印の福袋に追加（菫子カード）\n・サニーに新進化先を追加（Ｓタイプ）\n・スターに新進化先を追加（Ｓタイプ）\n・一部コダマに禁呪を追加（Ｓ影狼/\n　　ＡＴサニー/ＮＨスター/Ｎ赤蛮奇）\n・Ｄ/Ｓレミリアスペルの習得銭を修正\n　（習得済みの方には差額を送金）\n・エリア26～44、EX1～14のLvを下方修正\n\n") + "▽Ver1.5.10 [2015年7月7日]\n・EXエリア18を追加実装\n・ポケットタワー5を追加実装\n・チルノに新進化先を追加（Ａタイプ）\n・ルナに新進化先を追加（Ｓタイプ）\n・一部コダマに禁呪を追加（ＨＳチルノ/\n　　ADチルノ/ＡＴＮ大妖精/ＤＮルナ）\n・Ｓ菫子スペルの設定ミスを修正\n　（テレキネシス電波塔：消費30→50）\n・コダマメニューの矢印位置を調整\n・コダマ詳細、スペル確認画面に\n　スクロール矢印ボタンを追加\n\n";
            } else if (this.listPage == this.listPageMax - 19) {
                str3 = (str3 + "▽Ver1.5.7 [2015年6月15日]\n・EXエリア15を追加実装\n・夢幻印の福袋に追加（サラカカード）\n・ポケットタワー4を追加実装\n・妖夢に新進化先を追加（Ｄタイプ）\n・一部コダマに禁呪を追加\n　（ＡＨありす/ＢＨＮＴアリス/\n　　ADアリス/ＡＨＳ妖夢）\n\n") + "▽Ver1.5.8 [2015年6月23日]\n・エリア44を追加実装\n・EXエリア16を追加実装\n・フランに新進化先を追加（Ｎタイプ）\n・レミリアに新進化先を追加（Ｓタイプ）\n・一部コダマに禁呪を追加\n　（Ｎエリス/Ｓくるみ/\n　　ＡＳフラン/ＡＤＴレミリア）\n・Ｄレミリアにスペルを追加\n・一部スペルのエフェクト設定ミスを修正\n・クエスト開始前に難易度変更ボタン追加\n\n";
            } else if (this.listPage == this.listPageMax - 18) {
                str3 = ((str3 + "▽Ver1.5.4 [2015年6月1日]\n・EXエリア13を追加実装\n・ポケットタワー3を追加実装\n・藍に新進化先を追加（Ａタイプ）\n・穣子に新進化先を追加（Ｔタイプ）\n・一部コダマに習得禁呪を追加\n （ＡＮＳにとり/ＤＨ穣子/ＡＮＳ燐）\n・アイテム-霊石表示の配置を微調整\n・カード一覧の並びを名前順に変更\n\n") + "▽Ver1.5.5 [2015年6月8日]\n・エリア43を追加実装\n・EXエリア14を追加実装\n・一部コダマの能力設定ミスを修正\n　（Ｄ針妙丸/Ｄ天魔/Ａ夢月/Ｔ藍）\n・静葉に新進化先を追加（Ａタイプ）\n・針妙丸に新進化先を追加（Ｔタイプ）\n・一部コダマに禁呪を追加\n　（ＡＨＮＴこいし/ＡＨサリエル/\n　　ＨＴ静葉/ＤＳ針妙丸）\n\n") + "▽Ver1.5.6 [2015年6月8日]\n・保護されているコダマでも\n　契約解除できてしまう不具合を修正\n\n";
            } else if (this.listPage == this.listPageMax - 17) {
                str3 = (str3 + "▽Ver1.5.2 [2015年5月18日]\n・エリア41を追加実装\n・EXエリア10～12を追加実装\n・夢幻印の福袋に追加（百々カード）\n・阿澄酒場に新ステージ追加\n　※エリア32クリアで登場します\n・エリア40クリア時にショップ商品を追加\n・EXエリアの表示を変更\n　※過去数ステージまで再挑戦可能に\n・一部の銭表記にカンマ区切りを追加\n・一部装備の売却額を変更\n 魂鏡の欠片、空鏡の欠片、\n　月鏡の欠片、時鏡の欠片、\n　星鏡の欠片、夢鏡の欠片、\n　トランスリング、避来矢\n\n") + "▽Ver1.5.3 [2015年5月25日]\n・エリア42を追加実装\n・白瓏洞に新ステージ追加\n　※エリア39クリアで登場します\n\n";
            } else if (this.listPage == this.listPageMax - 16) {
                str3 = ((str3 + "▽Ver1.4.9 [2015年4月28日]\n・エリア38を追加実装\n・クエスト画面で禁呪スペル表記が\n　おかしくなっていた不具合を修正\n・装備選択時に文字数オーバーしていた\n　装備の説明文表記を微修正\n\n") + "▽Ver1.5.0 [2015年5月5日]\n・エリア39を追加実装\n・EXエリアを新規実装\n　※エリア30クリアで登場します\n・夢幻印の福袋の封入カードを追加\n　（シュガーサテラ/蓮子/メリー/薬子）\n\n") + "▽Ver1.5.1 [2015年5月12日]\n・エリア40を追加実装\n\n";
            } else if (this.listPage == this.listPageMax - 15) {
                str3 = ((str3 + "▽Ver1.4.6 [2015年4月10日]\n・エリア36を追加実装\n・限定エリア「月の見える丘」開放終了\n・新限定エリア開放\n・はたての新進化タイプを追加実装\n・シュガーサテラのアイコンを追加\n\n") + "▽Ver1.4.7 [2015年4月10日]\n・シュガーサテラ禁呪の設定ミスを修正\n\n") + "▽Ver1.4.8 [2015年4月21日]\n・エリア37を追加実装\n・限定エリア「妖精大戦争」開放終了\n・新限定エリア開放\n・データバックアップ/引き継ぎ前に\n　現在の引き継ぎ用ID表示を追加\n・輪華堂のドロップ率を微修正\n　（所持していない霊珠を出やすく）\n\n";
            } else if (this.listPage == this.listPageMax - 14) {
                str3 = (str3 + "▽Ver1.4.4 [2015年3月24日]\n・エリア34を追加実装\n・限定エリア「魔女達の舞踏会」開放終了\n・新限定エリア開放\n・カウンター禁呪の誤記を修正\n　（誤：2倍反撃→正：1.8倍反撃）\n・障壁禁呪が先攻にならない不具合を修正\n・クエスト中断前に確認を追加\n\n") + "▽Ver1.4.5 [2015年3月31日]\n・エリア35を追加実装\n・限定エリア「月の見える丘」開放延長\n・文の新進化タイプを追加実装\n・聖スペル「紫の雲路」禁呪と\n　静葉/秋姉妹スペル\n　「トワイライトガーデン」禁呪の\n　消費ＶＰを修正（50→40）\n・ちびアリスがスキル習得可能に\n　なっていた不具合を修正\n\n";
            } else if (this.listPage == this.listPageMax - 13) {
                str3 = str3 + "▽Ver1.4.3 [2015年3月17日]\n・エリア33を追加実装\n・限定エリア「剣戟の荒野」開放終了\n・新限定エリア開放\n・ＡＤキャラのアイコンを追加\n　（永琳/紫/橙/妹紅/藍/レティ）\n・ヘルプ「スペル」を加筆修正\n・一部コダマの上方修正を実施\n　▽Ｎエリー\n　　スペル性能調整、Ｄのみスペル追加\n　▽にとり\n　　スペル性能調整、Ａのみスペル追加\n　▽チルノ/魔天使\n　　スペル性能調整\n　▽青娥/Ｓ魅魔/Ｓ藍\n　　スペル追加\n\n";
            } else if (this.listPage == this.listPageMax - 12) {
                str3 = str3 + "▽Ver1.4.2 [2015年3月9日]\n・エリア32を追加実装\n・限定エリア「剣戟の荒野」開放延長\n・限定エリア挑戦回数リセット\n・一部コダマの上方修正を実施\n　▽キクリ\n　　スペル性能調整\n　▽Ａ星\n　　スペル追加\n　▽てゐ\n　　スペル性能調整\n　▽呪い子\n　　スペル性能調整\n　▽Ａ鈴仙\n　　スペル追加\n\n";
            } else if (this.listPage == this.listPageMax - 11) {
                str3 = (str3 + "▽Ver1.4.0 [2015年3月2日]\n・エリア31を追加実装\n・限定エリア「心綺楼祭囃子」開放終了\n・新限定エリア開放\n・一部エリアのボス戦にて、\n　敵キャラの表示位置が\n　おかしくなる不具合を修正\n・一部コダマの上方修正を実施\n　▽華扇\n　　スペル性能調整、Ｔのみスペル追加\n　▽天子\n　　スペル性能調整、スキル効果変更\n　▽レイセン\n　　スペル性能調整、スペル追加、\n　　ちび習得スペル変更\n　▽レティ\n　　スペル性能調整、Ｄのみスペル追加\n\n") + "▽Ver1.4.1 [2015年3月3日]\n・限定エリアの不具合を修正\n\n";
            } else if (this.listPage == this.listPageMax - 10) {
                str3 = str3 + "▽Ver1.3.12 [2015年2月26日]\n・期間限定エリアを中止した場合に\n　気絶状態が残る不具合を修正\n　※既に気絶コダマがいる場合は、\n　　クエストをクリアまたは中止して\n　　アプリを再起動してください\n・稀に能力上昇/減少エフェクトが\n　残り続けてしまう不具合を修正\n・レベル差による経験値上昇補正に\n　制限を追加（レベル差50までを加味）\n・椛カードの効果を説明分通りに修正\n・エリア30-7で登場する、現時点で\n　敵専用のコダマを微下方修正\n\n";
            } else if (this.listPage == this.listPageMax - 9) {
                str3 = (str3 + "▽Ver1.3.10 [2015年2月24日]\n・エリア29,30を追加実装\n・ログインボーナスの霊酒を増量\n・夢子の衣装を追加\n・オウカジムにステージ2を追加\n　（エリア26クリアで登場します）\n・Ｔフランの画像を微修正（透過漏れ対応）\n・期間限定エリアの挑戦を中止すると\n　装備変更できなくなる不具合を修正\n・期間限定エリア制限を変更（1回→2回）\n\n") + "▽Ver1.3.11 [2015年2月24日]\n・夢幻印の福袋から\n　カードが排出されない不具合を修正\n\n";
            } else if (this.listPage == this.listPageMax - 8) {
                str3 = str3 + "▽Ver1.3.9 [2015年2月17日]\n・エリア27,28を追加実装\n・コピースキルに関する不具合を修正\n　（ターン終了時→登場時にコピー）\n　（相手がスキルなしだと消えていた）\n・明羅のアイコンを追加\n・美鈴系統の進化先にＥ美鈴を追加\n・美鈴、妹紅のスキル効果を上方修正\n・ルーミアの岩スペルを上方修正\n・Ｎ/Ｓルーミアに闇スペルを追加\n・Ｓ橙に闘スペルを追加\n・霊酒の出現判定に不具合があったため、\n　再度修正させて頂きました。\n　最終的に以下の確率になっています。\n　エリア21～:14％、エリア16～20:11％、\n　エリア8～15:8％、エリア7以前:5％\n　※アイテムボールを見つける毎に判定";
            } else if (this.listPage == this.listPageMax - 7) {
                str3 = str3 + "▽Ver1.3.8 [2015年2月10日]\n・エリア26を追加実装\n・一部表記を修正\n・コダマメニューにソートを追加\n・特定属性ダメージ軽減スキルについて\n　正常に動作していなかった不具合を修正\n・Ｓユキ⇔Ｔマイの進化を不可に修正\n・ログインボーナス増加に伴い\n　霊酒ドロップ率を少しだけ下方修正\n・一部コダマのスペル/スキルを下方修正\n　▽Ｎ霊夢\n　　スキル：VP回復量6×SLv→5×SLv\n　　夢想天生：攻撃50％増→30％増\n　▽Ｔ藍\n　　御大師様の秘鍵：攻撃スペルに変更\n・一部装備の買値/売値を変更\n　（蓬莱の薬、布都御魂、八咫鏡、\n　　時渡りの羽根、陰陽玉、ミニ八卦路）\n・靈異伝キャラのアイコンを追加\n";
            } else if (this.listPage == this.listPageMax - 6) {
                str3 = str3 + "▽Ver1.3.7 [2015年2月2日]\n・エリア24,25を追加実装\n・ボタン表記と配置を全体的に見直し\n　（OK/NG→はい/いいえ、\n　　はいボタンを上部に統一etc）\n・コダマメニューにＢＰ一覧を追加\n・下記コダマの先攻スペルを上方修正\n　伊佐美、Ｎエリス、華扇、Ａ神奈子、\n　Ｔ幻月、こいし、Ｓチルノ、Ｔ魔理沙、\n　Ｄ神子、Ｄ八橋、Ｎ雷鼓、Ｔレミリア\n・バックボタン押下時の表記を修正\n・一部テキスト表記を修正\n・スペル「パラットイミテーション」\n　「妖怪オモカゲ変化」の不具合を修正\n・戦闘時の最大ＶＰ表示の誤記を修正\n";
            } else if (this.listPage == this.listPageMax - 5) {
                str3 = str3 + "▽Ver1.3.6 [2015年1月26日]\n・エリア22,23を追加実装\n・ログインボーナスを増量（霊酒を追加）\n・メインクエストにおける霊酒ドロップ率、\n　その他ドロップ内訳を微上方修正\n・ヘルプにコダマ名リセット機能を実装\n・時渡りの羽根の効果を上方修正\n・攻撃時にＶＰを減らすスキルを\n　習得しているコダマについて、\n　一部のスペル追加効果が\n　無効化されていた不具合を修正\n・霊夢スペルのエフェクトを一部修正\n";
            } else if (this.listPage == this.listPageMax - 4) {
                str3 = ((str3 + "▽Ver1.3.3 [2015年1月17日]\n・エリア18,19を追加実装\n・ログインボーナスを上方修正\n・スキル効果による能力補正の\n　上下限に関する不具合を修正\n・絵文字入力制限を追加\n\n") + "▽Ver1.3.4 [2015年1月21日]\n・エリア20,21を追加実装\n・サブエリア「白瓏洞」を実装\n　（エリア14クリアで登場）\n・[5万DL記念]リツイートで引換券ゲット\n・朱鷺子カードを追加実装\n・紫/靈夢/豊姫/依姫カードを\n　引換券の交換対象に追加\n・コダマ保護機能を実装\n・大解像度端末における表示位置を修正\n・酒虫の雫に関する不具合を修正\n・属性軽減スキル持ちコダマに対する\n　ＣＰＵの行動ロジックを修正\n・Ａありすスペルのエフェクトを一部修正\n\n") + "▽Ver1.3.5 [2015年1月22日]\n・データ引き継ぎに失敗する不具合を修正\n\n";
            } else if (this.listPage == this.listPageMax - 3) {
                str3 = (str3 + "▽Ver1.3.1 [2015年1月9日]\n・エリア14,15を追加実装\n・阿澄酒場の登場を\n　エリア7クリア時に変更\n・阿澄酒場のマップLvと\n　ステージ構成を変更\n・ヘルプに属性相性表を追加") + "▽Ver1.3.2 [2015年1月12日]\n・エリア16,17を追加実装\n・通常クエストの消費霊力を減少修正\n・サブエリアの出現敵について微修正\n・魔梨沙カードを追加実装\n・紫系統の能力値を微上方修正\n・芳香スキルの効果を変更\n　（ＶＰ吸収→霊属性ダメージ激減）\n・レベル差による経験値補正を上方修正\n・スペル習得数限界時でも\n　習得スペルを表示できるよう修正\n・ボックス内コダマの進化先一覧を追加\n　（初の霊珠取得以降のみ表示）\n・オウカジムのドロップ設定ミスを修正\n・霊珠一覧に空欄が出現する不具合を修正\n\n";
            } else if (this.listPage == this.listPageMax - 2) {
                str3 = str3 + "▽Ver1.3.0 [2015年1月6日]\n・サブエリア「オウカジム」を実装\n　（エリア10クリアで登場）\n・7-4報酬を「小吉印の福袋」から\n　「不偏の霊珠」に変更\n・9-5報酬を「中吉印の福袋」から\n　「力の霊珠」に変更\n・12-5報酬を「ルナティックビール」から\n　「秘伝の書」に変更\n・13-4報酬を「浮遊石」から\n　「疾風の霊珠」に変更\n・レイラスペルの設定ミスを修正\n　（エフェクト設定ミス）\n・全ステージの消費霊力を減少修正\n・霊酒の出現率を微減修正\n・1-3難易度を下方修正\n・サブエリア1「阿澄酒場」の登場を\n　エリア9クリア時に変更\n・ステータスボーナス上限を変更（50→64）\n・能力変化の上限を\n　元の数値の倍までに変更\n・難易度ハード以下における\n　敵の能力値を微減修正\n・一部コダマの性能を上方修正\n　（Ａカナ/Ｓ小悪魔/Ｎ聖/Ｎ神子/藍）。\n・装備選択画面に簡易装備効果を追記。\n";
            } else if (this.listPage == this.listPageMax - 1) {
                str3 = (((str3 + "▽Ver1.1.1 [2014年12月30日]\n・データ移管機能を実装\n\n") + "▽Ver1.2.0 [2014年12月30日]\n・アプリパッケージ名を変更\n\n") + "▽Ver1.2.1 [2014年12月30日]\n・データ移管後のクエストが\n　正しく動作しない不具合を修正\n\n") + "▽Ver1.2.2 [2015年1月1日]\n・エリア11～13を実装\n・コダマの名前を空欄で\n　登録できてしまう不具合を修正\n・響子スペルの設定ミスを修正\n　（相手攻撃減少→自分攻撃上昇）\n・ぬえスペルの設定ミスを修正\n　（エフェクト設定漏れ）\n\n";
            } else if (this.listPage == this.listPageMax) {
                str3 = (((str3 + "▽Ver1.0.0 [2014年12月26日]\n正式版リリース") + "▽Ver1.0.1 [2014年12月26日]\nエリア5-3で強制終了する不具合を修正\n\n") + "▽Ver1.0.2 [2014年12月27日]\n難易度ボーナスの上がり方が\nたまにおかしくなる不具合を修正\n\n") + "▽Ver1.1.0 [2014年12月29日]\n・エリア8～10、サブエリア1を実装\n・レイラカードを追加実装\n・装備一覧に効果を追記\n・敵気絶後、上部に「続投」ボタンを追加\n・編成画面の「外す」ボタンが\n　効かなかった不具合を修正\n・一部コダマ画像の表示位置を微修正\n・コダマカード引換時にカード効果と\n　契約可能なコダマ画像表記を追加\n・コダマカード引換時に「閉じる」が\n　反応しなかった不具合を修正\n・クエスト中、空き枠指定時は\n　「詳細」「装備」ボタンを無効化\n・クリア時表記を微修正\n・引換券説明文を微修正\n\n";
            }
        } else if (this.listHead == 10) {
            this.listPageMax = 3;
            str3 = "■前回チャンピオンロード戦績（" + this.listPage + "／" + this.listPageMax + "）\n";
            String str5 = "";
            Cursor cursor = null;
            if (this.listPage == 1) {
                try {
                    cursor = this.f220db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=3", null);
                    if (cursor.moveToFirst()) {
                        str3 = str3 + cursor.getString(0);
                    } else {
                        str3 = str3 + "\n本機能は最終エリアの\nチャンピオンロードをクリアすると\n利用できるようになります。";
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
            } else if (this.listPage == 2) {
                try {
                    cursor = this.f220db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=4", null);
                    if (cursor.moveToFirst()) {
                        str2 = str3 + cursor.getString(0);
                    } else {
                        str2 = str3 + "\n本機能は最終エリアの\nチャンピオンロードをクリアすると\n利用できるようになります。";
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
            } else if (this.listPage == 3) {
                try {
                    cursor = this.f220db.rawQuery("SELECT u.data FROM user_battle_t u WHERE u.user_battle_id=5", null);
                    if (cursor.moveToFirst()) {
                        str = str3 + cursor.getString(0);
                    } else {
                        str = str3 + "\n本機能は最終エリアの\nチャンピオンロードをクリアすると\n利用できるようになります。";
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th3) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th3;
                }
            }
        }
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) str3, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        arrowOpen(91, 736, false);
        arrowOpen(92, 736, true);
        this.buttonOk = getBaseActivity().getResourceUtil().getButtonSprite("button/button_close.png", "button/button_close_p.png");
        placeToCenterX(this.buttonOk, 800.0f);
        this.buttonOk.setTag(20000);
        this.buttonOk.setOnClickListener(this);
        attachChild(this.buttonOk);
        registerTouchArea(this.buttonOk);
        for (int i = 0; i < 10; i++) {
            unregisterTouchArea((ITouchArea) this.buttonList.get(i));
        }
        menuBottomStop();
        this.menuHelpDetailOpenFlg = true;
    }

    private void menuHelpDetailClose() {
        this.informationBox.detachSelf();
        this.infoText.detachSelf();
        this.buttonArrowL.detachSelf();
        unregisterTouchArea(this.buttonArrowL);
        this.buttonArrowR.detachSelf();
        unregisterTouchArea(this.buttonArrowR);
        this.buttonOk.detachSelf();
        unregisterTouchArea(this.buttonOk);
        for (int i = 0; i < 10; i++) {
            registerTouchArea((ITouchArea) this.buttonList.get(i));
        }
        menuBottomStart();
        this.menuHelpDetailOpenFlg = false;
    }

    public void popAlert(String title, String text) {
        Sprite alertBox = getBaseActivity().getResourceUtil().getSprite("bg/information.png");
        placeToCenterX(alertBox, 72.0f);
        attachChild(alertBox);
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) "■" + title + "\n" + text, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
    }

    private void allFlgReset() {
        this.menuQuestConfirmFlg = false;
        this.menuUnitDetailOpenFlg = false;
        this.menuUnitDetailSpellOpenFlg = false;
        this.menuUnitItemDetailOpenFlg = false;
        this.menuSpellLearnConfirmFlg = false;
        this.menuEquipDetailOpenFlg = false;
        this.menuItemDetailOpenFlg = false;
        this.menuHelpDetailOpenFlg = false;
        this.menuTutorialOpenFlg = false;
    }

    private void headlineBoxOpen(String str, boolean scroll) {
        this.headlineBox = getBaseActivity().getResourceUtil().getSprite("item/headlineBox.png");
        if (scroll) {
            this.headlineBox.setPosition(-540.0f, 72.0f);
            this.headlineBoxText = new Text(-524.0f, 78.0f, (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        } else {
            this.headlineBox.setPosition(0.0f, 72.0f);
            this.headlineBoxText = new Text(16.0f, 78.0f, (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        }
        attachChild(this.headlineBox);
        attachChild(this.headlineBoxText);
        if (scroll) {
            this.headlineBox.registerEntityModifier(new MoveModifier(0.4f, -540.0f, 0.0f, 72.0f, 72.0f));
            this.headlineBoxText.registerEntityModifier(new MoveModifier(0.4f, -524.0f, 16.0f, 78.0f, 78.0f));
        }
    }

    private void titleBoxOpen(String str, boolean scroll) {
        this.titleBox = getBaseActivity().getResourceUtil().getSprite("item/TitleBox.png");
        if (scroll) {
            this.titleBox.setPosition(-540.0f, 130.0f);
            this.titleBoxText = new Text(-502.0f, 136.0f, (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        } else {
            this.titleBox.setPosition(0.0f, 130.0f);
            this.titleBoxText = new Text(38.0f, 136.0f, (IFont) this.fontBlack, (CharSequence) str, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        }
        attachChild(this.titleBox);
        attachChild(this.titleBoxText);
        if (scroll) {
            this.titleBox.registerEntityModifier(new MoveModifier(0.4f, -540.0f, 0.0f, 130.0f, 130.0f));
            this.titleBoxText.registerEntityModifier(new MoveModifier(0.4f, -502.0f, 38.0f, 136.0f, 136.0f));
        }
    }

    private void arrowOpen(int tag, int height, boolean r) {
        if (r) {
            this.buttonArrowR = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowR.png", "button/arrowR_p.png");
            this.buttonArrowR.setPosition(476.0f, (float) height);
            this.buttonArrowR.setTag(tag);
            this.buttonArrowR.setOnClickListener(this);
            attachChild(this.buttonArrowR);
            registerTouchArea(this.buttonArrowR);
            return;
        }
        this.buttonArrowL = getBaseActivity().getResourceUtil().getButtonSprite("button/arrowL.png", "button/arrowL_p.png");
        this.buttonArrowL.setPosition(16.0f, (float) height);
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
        this.buttonSort.setPosition(430.0f, 68.0f);
        this.buttonSort.setTag(90);
        this.buttonSort.setOnClickListener(this);
        attachChild(this.buttonSort);
        registerTouchArea(this.buttonSort);
        this.buttonSortFlg = true;
    }

    private void sortChange() {
        if (this.menuMode == 11) {
            menuUnitClose(false);
            this.sortUnitList++;
            if (3 < this.sortUnitList) {
                this.sortUnitList = 1;
            }
            menuUnitOpen(false);
            this.editor.putInt("sortUnitList", this.sortUnitList);
        } else if (this.menuMode == 32) {
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
        String str = "u.sort_no ASC";
        if (act == 1) {
            sortNo = this.sortUnitList;
        } else if (act == 2) {
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

    private void newUnitGet(int id) {
        boolean cursor2;
        int addId = boxEmptyId();
        int spell = 0;
        Cursor cursor22 = null;
        try {
            cursor22 = this.f220db.rawQuery("SELECT m.kodama_id, m.kodama_name, m.kodama_hp, m.kodama_atk, m.kodama_def, m.kodama_spd, m.kodama_attr1, m.kodama_attr2 FROM kodama_m m WHERE m.kodama_id=" + id, null);
            cursor2 = cursor22.moveToFirst();
            if (cursor2) {
                cursor2 = null;
                Cursor cursor23 = this.f220db.rawQuery("SELECT s.spell_id FROM kodama_learn_m m LEFT OUTER JOIN spell_m s ON m.learn_id=s.spell_id WHERE m.kodama_id=" + id + " AND m.type=1 AND s.attr=" + cursor22.getInt(6) + " ORDER BY s.prize ASC", null);
                cursor2 = cursor23.moveToFirst();
                if (cursor2) {
                    spell = cursor23.getInt(0);
                }
                if (cursor23 != null) {
                    cursor23.close();
                }
                this.unitNum++;
                this.f220db.execSQL("UPDATE user_kodama_t SET kodama_id=" + id + ", name=\"" + cursor22.getString(1) + "\", lv=5, exp=1000, equip=0, sb_hp=0, sb_atk=0, sb_def=0, sb_spd=0, bp=0, skill=0, slv=1, spell1=" + spell + ", spell2=0, spell3=0, spell4=0, create_time=" + System.currentTimeMillis() + ", max_lv=100 WHERE user_kodama_id=" + addId);
            } else {
                popAlert("データ取得エラー", "\n\nnewUnitGet\nデータ読み込みに失敗しました。\n大変申し訳ありませんが、\nアプリを再起動してください。");
            }
            if (cursor22 != null) {
                cursor22.close();
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

    /* JADX INFO: finally extract failed */
    private int boxEmptyId() {
        int addId = -1;
        Cursor cursor = null;
        if (this.unitNum == 0) {
            return 1;
        }
        try {
            Cursor cursor2 = this.f220db.rawQuery("SELECT u.user_kodama_id FROM user_kodama_t u WHERE " + (this.boxSelect * 100) + " < u.sort_no AND u.sort_no < " + ((this.boxSelect + 1) * 100) + " AND u.kodama_id=0 ORDER BY u.sort_no ASC", null);
            if (cursor2.moveToFirst()) {
                addId = cursor2.getInt(0);
            }
            if (cursor2 == null) {
                return addId;
            }
            cursor2.close();
            return addId;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
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
    public void updateSp() {
        if (this.userSpText != null) {
            this.userSpText.detachSelf();
        }
        this.userSpText = new Text(6.0f, 34.0f, (IFont) this.fontBlack, (CharSequence) "霊力." + this.userSp + "/" + this.userMaxSp, new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.userSpText);
        if (this.userMaxSp < this.userSp || this.userMaxSp == 0) {
            this.userSpVar.setWidth(526.0f);
        } else {
            this.userSpVar.setWidth((float) ((this.userSp * 526) / this.userMaxSp));
        }
        this.f220db.execSQL("UPDATE user_t SET user_sp= " + this.userSp + ", user_sp_restore_time=" + this.userSpRestoreTime);
    }

    /* access modifiers changed from: private */
    public void free() {
        setOnSceneTouchListener(null);
        this.menuUpdateHandler.reset();
        unregisterUpdateHandler(this.menuUpdateHandler);
        this.menuUpdateHandler = null;
        this.commonHandler = null;
        this.commonHandler2 = null;
        clearEntityModifiers();
    }

    /* access modifiers changed from: private */
    public void destroy() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    KeyListenScene scene;
                    if (MenuScene.this.bgm != null) {
                        MenuScene.this.bgm.release();
                        MenuScene.this.bgm = null;
                    }
                    MenuScene.this.tmpStr = null;
                    MenuScene.this.buttonPressedSound.release();
                    MenuScene.this.buttonPressedSound = null;
                    MenuScene.this.buttonCanceledSound.release();
                    MenuScene.this.buttonCanceledSound = null;
                    MenuScene.this.StartPressedSound.release();
                    MenuScene.this.StartPressedSound = null;
                    MenuScene.this.pageMoveSound.release();
                    MenuScene.this.pageMoveSound = null;
                    MenuScene.this.itemUseSound.release();
                    MenuScene.this.itemUseSound = null;
                    MenuScene.this.unitGrowSound.release();
                    MenuScene.this.unitGrowSound = null;
                    MenuScene.this.bitmapFontS.unload();
                    MenuScene.this.bitmapFontS.unloadTextures();
                    MenuScene.this.bitmapFontS = null;
                    MenuScene.this.fontBlack.unload();
                    MenuScene.this.fontBlack = null;
                    MenuScene.this.fontWhite.unload();
                    MenuScene.this.fontWhite = null;
                    MenuScene.this.droidFontTexture1.unload();
                    MenuScene.this.droidFontTexture1 = null;
                    MenuScene.this.droidFontTexture2.unload();
                    MenuScene.this.droidFontTexture2 = null;
                    MenuScene.this.cartain.dispose();
                    MenuScene.this.cartain = null;
                    MenuScene.this.bgImg.dispose();
                    MenuScene.this.bgImg = null;
                    MenuScene.this.buttonArrowL.dispose();
                    MenuScene.this.buttonArrowL = null;
                    MenuScene.this.buttonArrowR.dispose();
                    MenuScene.this.buttonArrowR = null;
                    if (MenuScene.this.dArrowFlg) {
                        MenuScene.this.buttonArrowLL.dispose();
                        MenuScene.this.buttonArrowLL = null;
                        MenuScene.this.buttonArrowRR.dispose();
                        MenuScene.this.buttonArrowRR = null;
                    }
                    MenuScene.this.buttonEtc1.dispose();
                    MenuScene.this.buttonEtc1 = null;
                    MenuScene.this.headlineBox.dispose();
                    MenuScene.this.headlineBox = null;
                    MenuScene.this.headlineBoxText.dispose();
                    MenuScene.this.headlineBoxText = null;
                    MenuScene.this.healSpTimeText.dispose();
                    MenuScene.this.healSpTimeText = null;
                    MenuScene.this.informationBox.dispose();
                    MenuScene.this.informationBox = null;
                    MenuScene.this.moneyImg.dispose();
                    MenuScene.this.moneyImg = null;
                    MenuScene.this.moneyText.dispose();
                    MenuScene.this.moneyText = null;
                    MenuScene.this.statusBox.dispose();
                    MenuScene.this.statusBox = null;
                    MenuScene.this.titleBox.dispose();
                    MenuScene.this.titleBox = null;
                    MenuScene.this.titleBoxText.dispose();
                    MenuScene.this.titleBoxText = null;
                    MenuScene.this.userNameText.dispose();
                    MenuScene.this.userNameText = null;
                    MenuScene.this.userSpText.dispose();
                    MenuScene.this.userSpText = null;
                    MenuScene.this.userSpVar.dispose();
                    MenuScene.this.userSpVar = null;
                    MenuScene.this.buttonNg.dispose();
                    MenuScene.this.buttonNg = null;
                    MenuScene.this.buttonOk.dispose();
                    MenuScene.this.buttonOk = null;
                    if (MenuScene.this.buttonSortFlg) {
                        MenuScene.this.buttonSort.dispose();
                    }
                    MenuScene.this.buttonSort = null;
                    if (MenuScene.this.textTextFlg) {
                        MenuScene.this.testText.dispose();
                    }
                    MenuScene.this.testText = null;
                    MenuScene.this.infoText = null;
                    if (MenuScene.this.newUnitDisplayFlg) {
                        MenuScene.this.newUnitImg.dispose();
                    }
                    MenuScene.this.newUnitImg = null;
                    if (MenuScene.this.unitDetailDisplay) {
                        MenuScene.this.unitDetailIcon.dispose();
                        MenuScene.this.buttonEtc2.dispose();
                        MenuScene.this.buttonName.dispose();
                        MenuScene.this.buttonItem.dispose();
                        MenuScene.this.buttonBp.dispose();
                    }
                    MenuScene.this.unitDetailIcon = null;
                    MenuScene.this.buttonEtc2 = null;
                    MenuScene.this.buttonName = null;
                    MenuScene.this.buttonItem = null;
                    MenuScene.this.buttonBp = null;
                    MenuScene.this.detailText = null;
                    if (MenuScene.this.buttonEtc3Flg) {
                        MenuScene.this.buttonEtc3.dispose();
                    }
                    if (MenuScene.this.buttonEtc4Flg) {
                        MenuScene.this.buttonEtc4.dispose();
                    }
                    MenuScene.this.buttonEtc3 = null;
                    MenuScene.this.buttonEtc4 = null;
                    if (MenuScene.this.endDisplayFlg) {
                        MenuScene.this.endBox.dispose();
                        MenuScene.this.endText.dispose();
                    }
                    MenuScene.this.endBox = null;
                    MenuScene.this.endText = null;
                    MenuScene.this.endNg.dispose();
                    MenuScene.this.endOk.dispose();
                    MenuScene.this.endNg = null;
                    MenuScene.this.endOk = null;
                    if (MenuScene.this.attrFlg) {
                        MenuScene.this.attrTable.dispose();
                    }
                    MenuScene.this.attrTable = null;
                    MenuScene.this.f221sb = null;
                    MenuScene.this.unitSort = null;
                    MenuScene.this.unitSortId = null;
                    MenuScene.this.buttonListText = null;
                    MenuScene.this.statusText = null;
                    MenuScene.this.attrs = null;
                    MenuScene.this.stName = null;
                    MenuScene.this.buttonBottom.clear();
                    MenuScene.this.buttonBottom = null;
                    MenuScene.this.confirmUnitList.clear();
                    MenuScene.this.confirmUnitList = null;
                    MenuScene.this.shadowList.clear();
                    MenuScene.this.shadowList = null;
                    MenuScene.this.protectList.clear();
                    MenuScene.this.protectList = null;
                    MenuScene.this.attrList1.clear();
                    MenuScene.this.attrList1 = null;
                    MenuScene.this.attrList2.clear();
                    MenuScene.this.attrList2 = null;
                    MenuScene.this.statusList.clear();
                    MenuScene.this.statusList = null;
                    MenuScene.this.unitProtectIcon.clear();
                    MenuScene.this.unitProtectIcon = null;
                    MenuScene.this.backButtonList.clear();
                    MenuScene.this.backButtonList = null;
                    MenuScene.this.buttonList.clear();
                    MenuScene.this.buttonList = null;
                    MenuScene.this.getBaseActivity().getSoundManager().releasePool();
                    MenuScene.this.dbh = null;
                    MenuScene.this.f220db = null;
                    if (MenuScene.this.toScene == 2) {
                        scene = new DataRestore(MenuScene.this.getBaseActivity());
                    } else if (MenuScene.this.toScene == 1) {
                        scene = new DataBackup(MenuScene.this.getBaseActivity());
                    } else {
                        scene = new QuestScene(MenuScene.this.getBaseActivity());
                    }
                    MenuScene.this.getBaseActivity().getEngine().setScene(scene);
                    MenuScene.this.getBaseActivity().appendScene(scene);
                    MenuScene.this.hideAds1();
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

    private void initialUnitSet() {
        attachChild(this.informationBox);
        this.infoText = new Text(60.0f, 60.0f, (IFont) this.fontWhite, (CharSequence) "最初に契約するコダマを選んでください。\n\n特にこだわりがなければ、\n好みで選んでしまってＯＫです。\n\n※ここで選ばなかったコダマも、\n　後で入手することができます。", new TextOptions(HorizontalAlign.LEFT), getBaseActivity().getVertexBufferObjectManager());
        attachChild(this.infoText);
        int[] numList = {12, 25, 21, 110, 194, 73};
        for (int i = 0; i < 3; i++) {
            this.buttonList.add(i, getBaseActivity().getResourceUtil().getButtonSprite("attr/" + numList[i] + ".png", "attr/" + numList[i] + ".png"));
            ((ButtonSprite) this.buttonList.get(i)).setTag(numList[i + 3]);
        }
        for (int i2 = 3; i2 < 6; i2++) {
            this.buttonList.add(i2, getBaseActivity().getResourceUtil().getButtonSprite("kodama/" + numList[i2] + ".png", "kodama/" + numList[i2] + ".png"));
            ((ButtonSprite) this.buttonList.get(i2)).setTag(numList[i2]);
        }
        ((ButtonSprite) this.buttonList.get(0)).setPosition(58.0f, 300.0f);
        ((ButtonSprite) this.buttonList.get(3)).setPosition(58.0f, 300.0f);
        ((ButtonSprite) this.buttonList.get(1)).setPosition(206.0f, 300.0f);
        ((ButtonSprite) this.buttonList.get(4)).setPosition(206.0f, 300.0f);
        ((ButtonSprite) this.buttonList.get(2)).setPosition(354.0f, 300.0f);
        ((ButtonSprite) this.buttonList.get(5)).setPosition(354.0f, 300.0f);
        for (int i3 = 0; i3 < 6; i3++) {
            ((ButtonSprite) this.buttonList.get(i3)).setOnClickListener(this);
            registerTouchArea((ITouchArea) this.buttonList.get(i3));
            attachChild((IEntity) this.buttonList.get(i3));
        }
    }

    /* access modifiers changed from: private */
    public void timeStampCheck() {
        try {
            getBaseActivity().runOnUpdateThread(new Runnable() {
                public void run() {
                    URI url = null;
                    try {
                        url = new URI("http://www.tohofes.com/com/time.html");
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    List<NameValuePair> postParams = new ArrayList<>();
                    postParams.add(new BasicNameValuePair("user_id", "test"));
                    postParams.add(new BasicNameValuePair("user_pw", "password"));
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
                                        String tmpRes = EntityUtils.toString(response.getEntity(), "UTF-8");
                                        if (tmpRes.length() >= 16) {
                                            MenuScene.this.healSpTimeText.setText("通信失敗");
                                            break;
                                        } else {
                                            MenuScene.this.startTime = Long.valueOf(tmpRes).longValue();
                                            MenuScene.this.timeStampCheckFlg = true;
                                            if (MenuScene.this.userSpRestoreTime == 0 && MenuScene.this.userSp < MenuScene.this.userMaxSp) {
                                                MenuScene.this.userSpRestoreTime = MenuScene.this.startTime + ((long) MenuScene.this.passageTime);
                                                MenuScene.this.f220db.execSQL("UPDATE user_t SET user_sp_restore_time=" + (MenuScene.this.startTime + ((long) MenuScene.this.passageTime)));
                                            }
                                            if (MenuScene.this.userMaxSp <= MenuScene.this.userSp) {
                                                MenuScene.this.healSpTimeText.setText("　");
                                                break;
                                            }
                                        }
                                        break;
                                    case 404:
                                        MenuScene.this.healSpTimeText.setText("通信失敗：日時を取得できませんでした");
                                        MenuScene.this.healSpTimeText.setPosition((540.0f - MenuScene.this.healSpTimeText.getWidth()) - 6.0f, 34.0f);
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

    private void showAds1() {
        getBaseActivity().visibleNativeViewFromId(C0436R.C0439id.adLayout1);
    }

    /* access modifiers changed from: private */
    public void hideAds1() {
        getBaseActivity().goneNativeViewFromId(C0436R.C0439id.adLayout1);
    }

    private void nameInputDialogBuilder() {
        getBaseActivity().runOnUiThread(new Runnable() {
            public void run() {
                new StringInputDialogBuilder(MenuScene.this.getBaseActivity(), C0436R.string.user_regist_title, C0436R.string.user_regist_detail, C0436R.string.user_regist_error, C0436R.C0438drawable.ic_launcher, new Callback<String>() {
                    public void onCallback(String pCallbackValue) {
                        String newName = pCallbackValue;
                        if (8 < newName.length()) {
                            newName = newName.substring(0, 8);
                        } else if (newName.length() <= 0) {
                            newName = "悠姫";
                        }
                        String newName2 = newName.replaceAll("\n", "").replaceAll("\"", "”");
                        MenuScene.this.f220db.execSQL("UPDATE user_t SET user_name=\"" + newName2 + "\"");
                        MenuScene.this.userNameText.detachSelf();
                        MenuScene.this.userNameText = new Text(20.0f, 2.0f, (IFont) MenuScene.this.fontBlack, (CharSequence) newName2, new TextOptions(HorizontalAlign.LEFT), MenuScene.this.getBaseActivity().getVertexBufferObjectManager());
                        MenuScene.this.attachChild(MenuScene.this.userNameText);
                    }
                }, new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                        MenuScene.this.userNameText.detachSelf();
                        MenuScene.this.userNameText = new Text(20.0f, 2.0f, (IFont) MenuScene.this.fontBlack, (CharSequence) MenuScene.this.userName, new TextOptions(HorizontalAlign.LEFT), MenuScene.this.getBaseActivity().getVertexBufferObjectManager());
                        MenuScene.this.attachChild(MenuScene.this.userNameText);
                        MenuScene.this.f220db.execSQL("UPDATE user_t SET user_name=\"" + MenuScene.this.userName + "\"");
                    }
                }).create().show();
            }
        });
    }

    private void nickInputDialogBuilder() {
        getBaseActivity().runOnUiThread(new Runnable() {
            public void run() {
                new StringInputDialogBuilder(MenuScene.this.getBaseActivity(), C0436R.string.user_regist_title, C0436R.string.unit_regist_detail, C0436R.string.user_regist_error, C0436R.C0438drawable.ic_launcher, new Callback<String>() {
                    public void onCallback(String pCallbackValue) {
                        String newName = pCallbackValue;
                        if (6 < newName.length()) {
                            newName = newName.substring(0, 6);
                        }
                        if (newName.length() > 0) {
                            MenuScene.this.menuUnitDetailClose();
                            String newName2 = newName.replaceAll("\n", "").replaceAll("\"", "”");
                            if (MenuScene.this.textTextFlg) {
                                MenuScene.this.testText.detachSelf();
                                MenuScene.this.textTextFlg = true;
                            }
                            MenuScene.this.testText = new Text(0.0f, 960.0f, (IFont) MenuScene.this.fontBlack, (CharSequence) newName2, new TextOptions(HorizontalAlign.LEFT), MenuScene.this.getBaseActivity().getVertexBufferObjectManager());
                            MenuScene.this.attachChild(MenuScene.this.testText);
                            MenuScene.this.f220db.execSQL("UPDATE user_kodama_t SET name=\"" + newName2 + "\" WHERE user_kodama_id=" + MenuScene.this.targetUserKodamaId);
                            MenuScene.this.menuUnitDetailOpen();
                        }
                    }
                }, new OnCancelListener() {
                    public void onCancel(DialogInterface dialog) {
                    }
                }).create().show();
            }
        });
    }

    private void nendRewardAdInit() {
        this.mNendAdRewardedVideo = new NendAdRewardedVideo(getBaseActivity(), 907515, "92db9978fe90ea162dc67876d63249ec42782ffc");
        this.mNendAdRewardedVideo.setAdListener(new NendAdRewardedListener() {
            public void onRewarded(NendAdVideo nendAdVideo, NendAdRewardItem rewardedItem) {
                MenuScene.this.userSp = MenuScene.this.userSp + 1000;
                if (10000 < MenuScene.this.userSp) {
                    MenuScene.this.userSp = 10000;
                }
                if (MenuScene.this.userMaxSp < MenuScene.this.userSp) {
                    MenuScene.this.healSpFlg = false;
                }
                MenuScene.this.updateSp();
                MenuScene.this.mNendAdRewardedVideo.releaseAd();
                MenuScene.this.nendAdFlg = 1;
                MenuScene.this.editor.putInt("nendAdFlg", MenuScene.this.nendAdFlg);
                MenuScene.this.editor.commit();
                if (MenuScene.this.nendRewardAdOpenFlg) {
                    MenuScene.this.nendRewardAdOpenFlg = false;
                    MenuScene.this.menuOptionOpen();
                    MenuScene.this.menuBottomStart();
                }
            }

            public void onLoaded(NendAdVideo nendAdVideo) {
                if (MenuScene.this.mNendAdRewardedVideo.isLoaded()) {
                    MenuScene.this.mNendAdRewardedVideo.showAd(MenuScene.this.getBaseActivity());
                }
            }

            public void onFailedToLoad(NendAdVideo nendAdVideo, int errorCode) {
                Log.d("Debug", "MenuScene_errorCode:" + errorCode);
                if (MenuScene.this.nendRewardAdOpenFlg) {
                    MenuScene.this.nendRewardAdOpenFlg = false;
                    MenuScene.this.menuOptionOpen();
                    MenuScene.this.menuBottomStart();
                }
            }

            public void onFailedToPlay(NendAdVideo nendAdVideo) {
                if (MenuScene.this.nendRewardAdOpenFlg) {
                    MenuScene.this.nendRewardAdOpenFlg = false;
                    MenuScene.this.menuOptionOpen();
                    MenuScene.this.menuBottomStart();
                }
            }

            public void onShown(NendAdVideo nendAdVideo) {
            }

            public void onClosed(NendAdVideo nendAdVideo) {
                if (MenuScene.this.nendRewardAdOpenFlg) {
                    MenuScene.this.nendRewardAdOpenFlg = false;
                    MenuScene.this.menuOptionOpen();
                    MenuScene.this.menuBottomStart();
                }
            }

            public void onStarted(NendAdVideo nendAdVideo) {
            }

            public void onStopped(NendAdVideo nendAdVideo) {
                if (MenuScene.this.nendRewardAdOpenFlg) {
                    MenuScene.this.nendRewardAdOpenFlg = false;
                    MenuScene.this.menuOptionOpen();
                    MenuScene.this.menuBottomStart();
                }
            }

            public void onCompleted(NendAdVideo nendAdVideo) {
                if (MenuScene.this.nendRewardAdOpenFlg) {
                    MenuScene.this.nendRewardAdOpenFlg = false;
                    MenuScene.this.menuOptionOpen();
                    MenuScene.this.menuBottomStart();
                }
            }

            public void onAdClicked(NendAdVideo nendAdVideo) {
            }

            public void onInformationClicked(NendAdVideo nendAdVideo) {
            }
        });
    }

    private void nendRewardAdOpen() {
        menuOptionClose();
        menuBottomStop();
        this.nendRewardAdOpenFlg = true;
        this.mNendAdRewardedVideo.loadAd();
    }
}
