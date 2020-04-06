package kanatamikado.p006ae.reiki;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.graphics.drawable.PathInterpolatorCompat;
import android.util.Log;

/* renamed from: kanatamikado.ae.reiki.Database */
public class Database extends SQLiteOpenHelper {
    private static final String DB_NAME = "gyokureiki.db";
    private static final int DB_VERSION = 1;
    private final int BOX_MAX_LIMIT = 20;
    private final int BOX_UNIT_LIMIT = 30;
    private final boolean DEBUG_LOG_FLG = true;
    private final int INITIAL_MONEY = 10000;
    private final int INITIAL_SP = PathInterpolatorCompat.MAX_NUM_POINTS;
    String sql;

    public Database(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d("Debug", "Database_onCreate");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public boolean unitDataSet(SQLiteDatabase db) {
        long current = System.currentTimeMillis();
        Log.d("Debug", "Database_unitDataSet_start");
        userDataDrop(db);
        userDataCreate(db);
        db.beginTransaction();
        try {
            if (!db.query("user_t", new String[]{"user_id"}, "user_id = ?", new String[]{"1"}, null, null, null).moveToFirst()) {
                db.execSQL("INSERT INTO user_t VALUES (0, 'null', 'null', '悠姫', 10000, 3000, 3000, " + current + ", 1, 2, 0, 0, 0, 10, 'null', 0, 0, 0, 0, " + current + ", 0)");
            }
            int no = 1;
            for (int i = 1; i <= 20; i++) {
                for (int s = 1; s <= 30; s++) {
                    no++;
                    db.execSQL("INSERT INTO user_kodama_t VALUES (" + no + ", " + ((i * 100) + s) + ", 0, \"null\", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100);");
                }
            }
            for (int i2 = 1; i2 <= 9; i2++) {
                for (int s2 = 1; s2 <= 6; s2++) {
                    db.execSQL("INSERT INTO user_party_t VALUES (" + i2 + ", " + s2 + ", 0);");
                }
            }
            db.execSQL("UPDATE user_party_t SET user_kodama_id=1 WHERE party_no=1 AND sort_no=1");
            db.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
        return true;
    }

    public void userDataDrop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS user_t");
        db.execSQL("DROP TABLE IF EXISTS user_kodama_t");
        db.execSQL("DROP TABLE IF EXISTS user_party_t");
        db.execSQL("DROP TABLE IF EXISTS user_item_t");
        db.execSQL("DROP TABLE IF EXISTS user_progress_t");
        db.execSQL("DROP TABLE IF EXISTS user_battle_t");
    }

    public void userDataCreate(SQLiteDatabase db) {
        this.sql = "CREATE TABLE IF NOT EXISTS user_t (  user_id INTEGER PRIMARY KEY, user_code TEXT, user_password TEXT, user_name TEXT, user_money INTEGER, user_sp INTEGER, user_max_sp INTEGER, user_sp_restore_time REAL, user_icon INTEGER, user_difficult INTEGER, user_quest_id INTEGER, user_quest_status INTEGER, user_quest_battle INTEGER, user_last INTEGER, last_login TEXT, user_sns_flg INTEGER, user_review_flg INTEGER, user_demo_no INTEGER, user_login_num INTEGER, create_time REAL, challenge_num INTEGER)";
        db.execSQL(this.sql);
        this.sql = "CREATE TABLE IF NOT EXISTS user_kodama_t (  user_kodama_id INTEGER PRIMARY KEY AUTOINCREMENT, sort_no INTEGER, kodama_id INTEGER, name TEXT, lv INTEGER, exp INTEGER, equip INTEGER, sb_hp INTEGER, sb_atk INTEGER, sb_def INTEGER, sb_spd INTEGER, bp INTEGER, skill INTEGER, slv INTEGER, spell1 INTEGER, spell2 INTEGER, spell3 INTEGER, spell4 INTEGER, create_time REAL, protect_flg INTEGER, faint_flg INTEGER, max_lv INTEGER)";
        db.execSQL(this.sql);
        this.sql = "CREATE TABLE IF NOT EXISTS user_party_t (  party_no INTEGER, sort_no INTEGER, user_kodama_id INTEGER)";
        db.execSQL(this.sql);
        this.sql = "CREATE TABLE IF NOT EXISTS user_item_t (  item_id INTEGER PRIMARY KEY, num INTEGER)";
        db.execSQL(this.sql);
        this.sql = "CREATE TABLE IF NOT EXISTS user_progress_t ( quest_id INTEGER PRIMARY KEY, diff INTEGER)";
        db.execSQL(this.sql);
        this.sql = "CREATE TABLE IF NOT EXISTS user_battle_t (  user_battle_id INTEGER PRIMARY KEY AUTOINCREMENT, data TEXT)";
        db.execSQL(this.sql);
    }
}
