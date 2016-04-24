package www.cput.ac.za.repository.division.Impl;

import sun.rmi.runtime.Log;
import www.cput.ac.za.domain.division.Apparel;
import www.cput.ac.za.repository.division.ApparelRepository;

import javax.naming.Context;
import java.awt.*;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class ApparelImpl extends SQLiteOpenHelper implements ApparelRepository {

    public static final String TABLE_NAME = "apparel";
    private SQLiteDatabase db;

    public static final String COLUMN_CLUBID = "clubID";
    public static final String COLUMN_SOCKS = "socks";
    public static final String COLUMN_TSHIRTSIZE = "tShirtSize";
    public static final String COLUMN_TRACKSUITSIZE = "trackSuitSize";
    public static final String COLUMN_JACKETSIZE = "jacketSize";
    public static final String COLUMN_TOTALAMOUNT = "totalAmount";

    //Database sql creation statement
    private static final String DATABSE_CREATE = " CREATE TABLE"
            + TABLE_NAME + "("
            + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_SOCKS + " TEXT NOT NULL , "
            + COLUMN_TSHIRTSIZE + " TEXT NOT NULL"
            + COLUMN_TRACKSUITSIZE + " TEXT NOT NULL"
            + COLUMN_JACKETSIZE + " TEXT NOT NULL"
            + COLUMN_TOTALAMOUNT + " TEXT NOT NULL);";

    public ApparelImpl(Context context){

        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
    }

    public void open()throws SQLException {

        db = this.getWritableDatabse();
    }

    public void close(){

        this.close();
    }

    @Override
    public Apparel findById(int clubID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query();
        TABLE_NAME,
                new String[]{
                        COLUMN_CLUBID,
                        COLUMN_SOCKS,
                        COLUMN_TSHIRTSIZE,
                        COLUMN_TRACKSUITSIZE,
                        COLUMN_JACKETSIZE,
                        COLUMN_TOTALAMOUNT},
                COLUMN_CLUBID + " =? ";
        new String[]{String.valueOf(clubID)},
                null,
                null,
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            final Apparel Apparel = new Apparel.Builder()
                    .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                    .socks(cursor.getString(cursor.getCOLUMNIndex(COLUMN_SOCKS)))
                    .tShirtSize(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TSHIRTSIZE)))
                    .trackSuitSize(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TRACKSUITSIZE)))
                    .jacketSize(cursor.getString(cursor.getCOLUMNIndex(COLUMN_JACKETSIZE)))
                    .totalAmount(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TOTALAMOUNT)))
                    .build();
            return Apparel;
        }
        else{
            return null;
        }
    }

    @Override
    public Apparel save(Apparel entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_SOCKS, entity.getSocks());
        values.put(COLUMN_TSHIRTSIZE, entity.gettShirtSize());
        values.put(COLUMN_TRACKSUITSIZE, entity.getTrackSuitSize());
        values.put(COLUMN_JACKETSIZE, entity.getJacketSize());
        values.put(COLUMN_TOTALAMOUNT, entity.getTotalAmount());
        int clubID = db.insertOrThrow(TABLE_NAME, null, values);
        Apparel insertedEntity = new Apparel.Builder()
                .copy(entity)
                .clubID(new int(clubID))
                .build();
        return insertedEntity;
    }

    @Override
    public Apparel update(Apparel entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_SOCKS, entity.getSocks());
        values.put(COLUMN_TSHIRTSIZE, entity.gettShirtSize());
        values.put(COLUMN_TRACKSUITSIZE, entity.getTrackSuitSize());
        values.put(COLUMN_JACKETSIZE, entity.getJacketSize());
        values.put(COLUMN_TOTALAMOUNT, entity.getTotalAmount());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())}
        );
        return entity;
    }

    @Override
    public Apparel delete(Apparel entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())});
        return entity;
    }

    @Override
    public Set<Apparel> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Apparel> app = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Apparel app = new Apparel.Builder()
                        .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                        .socks(cursor.getString(cursor.getCOLUMNIndex(COLUMN_SOCKS)))
                        .tShirtSize(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TSHIRTSIZE)))
                        .trackSuitSize(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TRACKSUITSIZE)))
                        .jacketSize(cursor.getString(cursor.getCOLUMNIndex(COLUMN_JACKETSIZE)))
                        .totalAmount(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TOTALAMOUNT)))
                        .build();
                Apparel.add(app);
            } while (cursor.moveToNext());
        }
        return Apparel;
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
