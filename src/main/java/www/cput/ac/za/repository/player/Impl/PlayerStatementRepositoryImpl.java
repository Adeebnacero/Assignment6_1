package www.cput.ac.za.repository.player.Impl;

import sun.rmi.runtime.Log;
import www.cput.ac.za.domain.player.PlayerStatement;
import www.cput.ac.za.repository.player.PlayerStatementRepository;

import javax.naming.Context;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adeebo on 2016/04/24.
 */
public class PlayerStatementRepositoryImpl extends SQLiteOpenHelper implements PlayerStatementRepository {

    public static final String TABLE_NAME = "playerstatement";
    private SQLiteDatabase db;

    public static final String COLUMN_CLUBID = "clubID";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_SUBSCRIPTIONS = "subscriptions";
    public static final String COLUMN_APPARELFEE = "apparelFee";
    public static final String COLUMN_DEDUCTIONS = "deductions";
    public static final String COLUMN_PAYMENTPLAN = "paymentPlan";
    public static final String COLUMN_TOTALDUE = "totalDue";
    public static final String COLUMN_PAYMENTDUEDATE = "paymentDueDate";

    //Database sql creation statement
    private static final String DATABSE_CREATE = " CREATE TABLE"
            + TABLE_NAME + "("
            + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_SUBSCRIPTIONS + " TEXT NOT NULL , "
            + COLUMN_APPARELFEE + " TEXT NOT NULL , "
            + COLUMN_DEDUCTIONS + " TEXT NOT NULL , "
            + COLUMN_PAYMENTPLAN + " TEXT NOT NULL , "
            + COLUMN_TOTALDUE + " TEXT NOT NULL , "
            + COLUMN_PAYMENTDUEDATE + " DATE NOT NULL);";

    public PlayerStatementRepository(Context context){

        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
    }

    public void open()throws SQLException {

        db = this.getWritableDatabse();
    }

    public void close(){

        this.close();
    }

    @Override
    public PlayerStatement findById(int clubID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query();
        TABLE_NAME,
                new String[]{
                        COLUMN_CLUBID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_SUBSCRIPTIONS,
                        COLUMN_APPARELFEE,
                        COLUMN_DEDUCTIONS,
                        COLUMN_PAYMENTPLAN,
                        COLUMN_TOTALDUE,
                        COLUMN_PAYMENTDUEDATE},
                COLUMN_CLUBID + " =? ";
        new String[]{String.valueOf(clubID)},
                null,
                null,
                null,
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            final PlayerStatement PlayerStatement = new PlayerStatement.Builder()
                    .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                    .firstName(cursor.getString(cursor.getCOLUMNIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getCOLUMNIndex(COLUMN_LASTNAME)))
                    .subscriptions(cursor.getString(cursor.getCOLUMNIndex(COLUMN_SUBSCRIPTIONS)))
                    .apparelFee(cursor.getString(cursor.getCOLUMNIndex(COLUMN_APPARELFEE)))
                    .deductions(cursor.getString(cursor.getCOLUMNIndex(COLUMN_DEDUCTIONS)))
                    .paymentPlan(cursor.getString(cursor.getCOLUMNIndex(COLUMN_PAYMENTPLAN)))
                    .totalDue(cursor.getString(cursor.getCOLUMNIndex(COLUMN_TOTALDUE)))
                    .paymentDueDate(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_PAYMENTDUEDATE)))
                    .build();
            return PlayerStatement;
        }
        else{
            return null;
        }
    }

    @Override
    public PlayerStatement save(PlayerStatement entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_SUBSCRIPTIONS, entity.getSubscriptions());
        values.put(COLUMN_APPARELFEE, entity.getApparelFee());
        values.put(COLUMN_DEDUCTIONS, entity.getDeductions());
        values.put(COLUMN_PAYMENTPLAN, entity.getPaymentPlan());
        values.put(COLUMN_TOTALDUE, entity.getTotalDue());
        values.put(COLUMN_PAYMENTDUEDATE, entity.getPaymentDueDate());
        int clubID = db.insertOrThrow(TABLE_NAME, null, values);
        PlayerStatement insertedEntity = new PlayerStatement.Builder()
                .copy(entity)
                .clubID(new int(clubID))
                .build();
        return insertedEntity;
    }

    @Override
    public PlayerStatement update(PlayerStatement entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_SUBSCRIPTIONS, entity.getSubscriptions());
        values.put(COLUMN_APPARELFEE, entity.getApparelFee());
        values.put(COLUMN_DEDUCTIONS, entity.getDeductions());
        values.put(COLUMN_PAYMENTPLAN, entity.getPaymentPlan());
        values.put(COLUMN_TOTALDUE, entity.getTotalDue());
        values.put(COLUMN_PAYMENTDUEDATE, entity.getPaymentDueDate());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())}
        );
        return entity;
    }

    @Override
    public PlayerStatement delete(PlayerStatement entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())});
        return entity;
    }

    @Override
    public Set<PlayerStatement> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PlayerStatement> pStatement = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PlayerStatement pStatement = new PlayerStatement.Builder()
                        .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                        .firstName(cursor.getString(cursor.getCOLUMNIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getCOLUMNIndex(COLUMN_LASTNAME)))
                        .subscriptions(cursor.getString(cursor.getCOLUMNIndex(COLUMN_LASTNAME)))
                        .apparelFee(cursor.getString(cursor.getCOLUMNIndex(COLUMN_LASTNAME)))
                        .deductions(cursor.getString(cursor.getCOLUMNIndex(COLUMN_LASTNAME)))
                        .paymentPlan(cursor.getString(cursor.getCOLUMNIndex(COLUMN_LASTNAME)))
                        .totalDue(cursor.getString(cursor.getCOLUMNIndex(COLUMN_LASTNAME)))
                        .paymentDueDate(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .build();
                PlayerStatement.add(pStatement);
            } while (cursor.moveToNext());
        }
        return PlayerStatement;
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
