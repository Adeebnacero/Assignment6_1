package www.cput.ac.za.repository.player.Impl;

import www.cput.ac.za.domain.player.PlayerContact;
import www.cput.ac.za.repository.player.PlayerContactRepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import javax.naming.Context;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adeebo on 2016/04/23.
 */

public class PlayerContactRepositoryImpl extends SQLiteOpenHelper implements PlayerContactRepository {

        public static final String TABLE_NAME = "playercontact";
        private SQLiteDatabase db;

        public static final String COLUMN_CLUBID = "clubID";
        public static final String COLUMN_CONTACTTYPE = "contactType";
        public static final String COLUMN_CONTACTNUMBER = "contactNumber";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_DATE = "date";

        //Database sql creation statement
        private static final String DATABSE_CREATE = " CREATE TABLE"
                + TABLE_NAME + "("
                + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + COLUMN_CONTACTTYPE + " TEXT NOT NULL , "
                + COLUMN_CONTACTNUMBER + " TEXT NOT NULL , "
                + COLUMN_STATUS + " TEXT NOT NULL,"
                + COLUMN_DATE + " DATE NOT NULL);";

        public PlayerContactRepositoryImpl(Context context){

            super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
        }

        public void open()throws SQLException {

            db = this.getWritableDatabse();
        }

        public void close(){

            this.close();
        }

        @Override
        public PlayerContact findById(int clubID){

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query();
            TABLE_NAME,
                    new String[]{
                            COLUMN_CLUBID,
                            COLUMN_CONTACTTYPE,
                            COLUMN_CONTACTNUMBER,
                            COLUMN_STATUS,
                            COLUMN_DATE},
                            COLUMN_CLUBID + " =? ";
            new String[]{String.valueOf(clubID)},
                    null,
                    null,
                    null,
                    null,
                    null);

            if(cursor.moveToFirst()) {
                final PlayerContact PlayerContact = new PlayerContact.Builder()
                        .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                        .contactType(cursor.getString(cursor.getCOLUMNIndex(COLUMN_CONTACTTYPE)))
                        .contactNumber(cursor.getString(cursor.getCOLUMNIndex(COLUMN_CONTACTNUMBER)))
                        .status(cursor.getString(cursor.getCOLUMNIndex(COLUMN_STATUS)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .build();
                return PlayerContact;
            }
            else{
                return null;
            }
        }

    @Override
    public PlayerContact save(PlayerContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_CONTACTTYPE, entity.getContactType());
        values.put(COLUMN_CONTACTNUMBER, entity.getContactNumber());
        values.put(COLUMN_STATUS, entity.getStatus());
        values.put(COLUMN_DATE, entity.getDate());
        int clubID = db.insertOrThrow(TABLE_NAME, null, values);
        PlayerContact insertedEntity = new PlayerContact.Builder()
                .copy(entity)
                .clubID(new int(clubID))
                .build();
        return insertedEntity;
    }

    @Override
    public PlayerContact update(PlayerContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_CONTACTTYPE, entity.getContactType());
        values.put(COLUMN_CONTACTNUMBER, entity.getContactNumber());
        values.put(COLUMN_STATUS, entity.getStatus());
        values.put(COLUMN_DATE, entity.getDate());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())}
        );
        return entity;
    }

    @Override
    public PlayerContact delete(PlayerContact entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())});
        return entity;
    }

    @Override
    public Set<PlayerContact> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PlayerContact> player = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PlayerContact player = new PlayerContact.Builder()
                        .clubID(cursor.getInt(cursor.getColumnIndex(COLUMN_CLUBID)))
                        .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString((cursor.getColumnIndex(COLUMN_LASTNAME)))
                                .build();
                PlayerContact.add(player);
            } while (cursor.moveToNext());
        }
        return PlayerContact;
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

