package www.cput.ac.za.repository.subscriptions.Impl;

import www.cput.ac.za.domain.subscriptions.PaymentPlan;
import www.cput.ac.za.domain.subscriptions.Subscriptions;
import www.cput.ac.za.repository.subscriptions.PaymentPlanRepository;

import javax.naming.Context;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class PaymentPlanFactoryImpl extends SQLiteOpenHelper implements PaymentPlanRepository {

    public static final String TABLE_NAME = "payment";
    private SQLiteDatabase db;

    public static final String COLUMN_CLUBID = "clubID";
    public static final String COLUMN_TERM = "term";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_BALANCE = "balance";

    //Database sql creation statement
    private static final String DATABSE_CREATE = " CREATE TABLE"
            + TABLE_NAME + "("
            + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_TERM + " TEXT NOT NULL , "
            + COLUMN_AMOUNT + " TEXT NOT NULL , "
            + COLUMN_BALANCE + " TEXT NOT NULL);";

    public PaymentPlanFactoryImpl(Context context){

        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
    }

    public void open()throws SQLException {

        db = this.getWritableDatabse();
    }

    public void close(){

        this.close();
    }

    @Override
    public PaymentPlan findById(int clubID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query();
        TABLE_NAME,
                new String[]{
                        COLUMN_CLUBID,
                        COLUMN_TERM,
                        COLUMN_AMOUNT,
                        COLUMN_BALANCE},
                COLUMN_CLUBID + " =? ";
        new String[]{String.valueOf(clubID)},
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            final PaymentPlan PaymentPlan = new PaymentPlan.Builder()
                    .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                    .term(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TERM)))
                    .amount(cursor.getString(cursor.getCOLUMNIndex(COLUMN_AMOUNT)))
                    .balance(cursor.getString(cursor.getCOLUMNIndex(COLUMN_BALANCE)))
                    .build();
            return PaymentPlan;
        }
        else{
            return null;
        }
    }

    @Override
    public PaymentPlan save(PaymentPlan entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_TERM, entity.getTerm());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        values.put(COLUMN_BALANCE, entity.getBalance());
        int clubID = db.insertOrThrow(TABLE_NAME, null, values);
        PaymentPlan insertedEntity = new PaymentPlan.Builder()
                .copy(entity)
                .clubID(new int(clubID))
                .build();
        return insertedEntity;
    }

    @Override
    public PaymentPlan update(PaymentPlan entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_TERM, entity.getTerm());
        values.put(COLUMN_AMOUNT, entity.getAmount());
        values.put(COLUMN_BALANCE, entity.getBalance());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())}
        );
        return entity;
    }

    @Override
    public PaymentPlan delete(PaymentPlan entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())});
        return entity;
    }

    @Override
    public Set<PaymentPlan> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PaymentPlan> pPlan = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PaymentPlan pPlan = new PaymentPlan.Builder()
                        .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                        .term(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TERM)))
                        .amount(cursor.getString(cursor.getCOLUMNIndex(COLUMN_AMOUNT)))
                        .balance(cursor.getString(cursor.getCOLUMNIndex(COLUMN_BALANCE)))
                        .build();
                PaymentPlan.add(pPlan);
            } while (cursor.moveToNext());
        }
        return PaymentPlan;
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
