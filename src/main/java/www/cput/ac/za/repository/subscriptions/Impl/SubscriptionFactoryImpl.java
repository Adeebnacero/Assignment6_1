package www.cput.ac.za.repository.subscriptions.Impl;

import www.cput.ac.za.domain.subscriptions.Subscriptions;
import www.cput.ac.za.repository.subscriptions.SubscriptionRepository;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class SubscriptionFactoryImpl extends SQLiteOpenHelper implements SubscriptionRepository {

    public static final String TABLE_NAME = "subscription";
    private SQLiteDatabase db;

    public static final String COLUMN_CLUBID = "clubID";
    public static final String COLUMN_DIVISIONSTATUS = "firstName";
    public static final String COLUMN_AMOUNT = "lastName";
    public static final String COLUMN_INSTALLMENT = "firstName";

    //Database sql creation statement
    private static final String DATABSE_CREATE = " CREATE TABLE"
            + TABLE_NAME + "("
            + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_DIVISIONSTATUS + " TEXT NOT NULL , "
            + COLUMN_AMOUNT + " TEXT NOT NULL , "
            + COLUMN_INSTALLMENT + " TEXT NOT NULL);";

    public SubscriptionFactoryImpl(Context context){

        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
    }

    public void open()throws SQLException{

        db = this.getWritableDatabse();
    }

    public void close(){

        this.close();
    }

    @Override
    public Subscriptions findById(int clubID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query();
        TABLE_NAME,
                new String[]{
                        COLUMN_CLUBID,
                        COLUMN_DIVISIONSTATUS,
                        COLUMN_AMOUNT,
                        COLUMN_INSTALLMENT},
                COLUMN_CLUBID + " =? ";
        new String[]{String.valueOf(clubID)},
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            final Subscriptions Subscriptions = new Subscriptions.Builder()
                    .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                    .divisionStatus(cursor.getString(cursor.getCOLUMNIndex(COLUMN_DIVISIONSTATUS)))
                    .amount(cursor.getString(cursor.getCOLUMNIndex(COLUMN_AMOUNT)))
                    .installment(cursor.getString(cursor.getCOLUMNIndex(COLUMN_INSTALLMENT)))
                    .build();
            return Subscriptions;
        }
        else{
            return null;
        }
    }

    @Override
    public Subscriptions save(Subscriptions entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_DIVISIONSTATUS, entity.getDivisionStatus());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        values.put(COLUMN_INSTALLMENT, entity.getInstallment());
        int clubID = db.insertOrThrow(TABLE_NAME, null, values);
        Subscriptions insertedEntity = new Subscriptions.Builder()
                .copy(entity)
                .clubID(new int(clubID))
                .build();
        return insertedEntity;
    }

    @Override
    public Subscriptions update(Subscriptions entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_DIVISIONSTATUS, entity.getDivisionStatus());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        values.put(COLUMN_INSTALLMENT, entity.getInstallment());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())}
        );
        return entity;
    }

    @Override
    public Subscriptions delete(Subscriptions entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())});
        return entity;
    }

    @Override
    public Set<Subscriptions> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Subscriptions> subs = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Subscriptions subs = new Subscriptions.Builder()
                        .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                        .divisionStatus(cursor.getString(cursor.getCOLUMNIndex(COLUMN_DIVISIONSTATUS)))
                        .amount(cursor.getString(cursor.getCOLUMNIndex(COLUMN_AMOUNT)))
                        .installment(cursor.getString(cursor.getCOLUMNIndex(COLUMN_INSTALLMENT)))
                        .build();
                Subscriptions.add(subs);
            } while (cursor.moveToNext());
        }
        return Subscriptions;
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
