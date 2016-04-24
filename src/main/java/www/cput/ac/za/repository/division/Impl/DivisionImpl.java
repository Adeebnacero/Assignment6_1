package www.cput.ac.za.repository.division.Impl;

import www.cput.ac.za.domain.division.Division;
import www.cput.ac.za.repository.division.DivisionRepository;

import javax.naming.Context;
import java.awt.*;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class DivisionImpl extends SQLiteOpenHelper implements DivisionRepository {

    public static final String TABLE_NAME = "division";
    private SQLiteDatabase db;

    public static final String COLUMN_CLUBID = "clubID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DIVISIONSTATUS = "divisionStatus";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_DATE = "date";

    //Database sql creation statement
    private static final String DATABSE_CREATE = " CREATE TABLE"
            + TABLE_NAME + "("
            + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_DIVISIONSTATUS + " TEXT NOT NULL"
            + COLUMN_STATUS + " TEXT NOT NULL"
            + COLUMN_DATE + " DATE NOT NULL);";

    public DivisionImpl(Context context){

        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
    }

    public void open()throws SQLException {

        db = this.getWritableDatabse();
    }

    public void close(){

        this.close();
    }

    @Override
    public Division findById(int clubID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query();
        TABLE_NAME,
                new String[]{
                        COLUMN_CLUBID,
                        COLUMN_NAME,
                        COLUMN_DIVISIONSTATUS,
                        COLUMN_STATUS,
                        COLUMN_DATE,
                        COLUMN_DATE},
                COLUMN_CLUBID + " =? ";
        new String[]{String.valueOf(clubID)},
                null,
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            final Division Division = new Division.Builder()
                    .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                    .name(cursor.getString(cursor.getCOLUMNIndex(COLUMN_NAME)))
                    .divisionStatus(cursor.getString(cursor.getCOLUMNIndex(COLUMN_DIVISIONSTATUS)))
                    .status(cursor.getString(cursor.getCOLUMNIndex(COLUMN_STATUS)))
                    .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                    .build();
            return Division;
        }
        else{
            return null;
        }
    }

    @Override
    public Division save(Division entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_NAME, entity.getStreet());
        values.put(COLUMN_DIVISIONSTATUS, entity.getArea());
        values.put(COLUMN_STATUS, entity.getPostalCode());
        values.put(COLUMN_DATE, entity.getDate());
        int clubID = db.insertOrThrow(TABLE_NAME, null, values);
        Division insertedEntity = new Division.Builder()
                .copy(entity)
                .clubID(new int(clubID))
                .build();
        return insertedEntity;
    }

    @Override
    public Division update(Division entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_NAME, entity.getStreet());
        values.put(COLUMN_DIVISIONSTATUS, entity.getArea());
        values.put(COLUMN_STATUS, entity.getPostalCode());
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
    public Division delete(Division entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())});
        return entity;
    }

    @Override
    public Set<Division> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Division> div = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Division div = new Division.Builder()
                        .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                        .name(cursor.getString(cursor.getCOLUMNIndex(COLUMN_NAME)))
                        .divisionStatus(cursor.getString(cursor.getCOLUMNIndex(COLUMN_DIVISIONSTATUS)))
                        .status(cursor.getString(cursor.getCOLUMNIndex(COLUMN_STATUS)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .build();
                Division.add(div);
            } while (cursor.moveToNext());
        }
        return Division;
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
