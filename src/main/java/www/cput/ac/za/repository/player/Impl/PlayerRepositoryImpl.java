package www.cput.ac.za.repository.player.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import www.cput.ac.za.domain.player.Player;
import www.cput.ac.za.repository.player.PlayerRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adeebo on 2016/04/19.
 */

public class PlayerRepositoryImpl extends SQLiteOpenHelper implements PlayerRepository {

    public static final String TABLE_NAME = "player";
    private SQLiteDatabase db;

    public static final String COLUMN_CLUBID = "clubID";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";

    //Database sql creation statement
    private static final String DATABSE_CREATE = " CREATE TABLE"
            + TABLE_NAME + "("
            + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL);";

    public PlayerRepositoryImpl(Context context){

        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
    }

    public void open()throws SQLException{

        db = this.getWritableDatabse();
    }

    public void close(){

        this.close();
    }

    @Override
    public Player findById(int clubID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query();
            TABLE_NAME,
            new String[]{
                    COLUMN_CLUBID,
                    COLUMN_FIRSTNAME,
                    COLUMN_LASTNAME},
            COLUMN_CLUBID + " =? ";
            new String[]{String.valueOf(clubID)},
            null,
            null,
            null);

        if(cursor.moveToFirst()) {
            final Player Player = new Player.Builder()
                    .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                    .firstName(cursor.getString(cursor.getCOLUMNIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getCOLUMNIndex(COLUMN_LASTNAME)))
                    .build();
            return Player;
        }
        else{
            return null;
        }
    }

    @Override
    public Player save(Player entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        int clubID = db.insertOrThrow(TABLE_NAME, null, values);
        Player insertedEntity = new Player.Builder()
                .copy(entity)
                .clubID(new int(clubID))
                .build();
        return insertedEntity;
    }

     @Override
    public Player update(Player entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())}
        );
        return entity;
    }

    @Override
    public Player delete(Player entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())});
        return entity;
    }

    @Override
    public Set<Player> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Player> player = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Player player = new Player.Builder()
                        .clubID(cursor.getInt(cursor.getColumnIndex(COLUMN_CLUBID)))
                        .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString((cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .build();
                player.add(player);
            } while (cursor.moveToNext());
        }
        return player;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
