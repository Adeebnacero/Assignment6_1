package www.cput.ac.za.repository.player.Impl;

import www.cput.ac.za.domain.player.NextOfKin;
import www.cput.ac.za.repository.player.NextOfKinRepository;

import javax.naming.Context;
import java.awt.*;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Adeebo on 2016/04/23.
 */
public class NextOfKinRepositoryImpl extends SQLiteOpenHelper implements NextOfKinRepository {

    public static final String TABLE_NAME = "nextofkin";
    private SQLiteDatabase db;

    public static final String COLUMN_CLUBID = "clubID";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_NNAME = "nName";
    public static final String COLUMN_NSURNAME = "nSurname";
    public static final String COLUMN_CONTACTNUMBER = "contactNumber";

    //Database sql creation statement
    private static final String DATABSE_CREATE = " CREATE TABLE"
            + TABLE_NAME + "("
            + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_SURNAME + " TEXT NOT NULL"
            + COLUMN_NNAME + " TEXT NOT NULL"
            + COLUMN_NSURNAME + " TEXT NOT NULL"
            + COLUMN_CONTACTNUMBER + " DATE NOT NULL);";

    public NextOfKinRepositoryImpl(Context context){

        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
    }

    public void open()throws SQLException {

        db = this.getWritableDatabse();
    }

    public void close(){

        this.close();
    }

    @Override
    public NextOfKin findById(int clubID){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query();
        TABLE_NAME,
                new String[]{
                        COLUMN_CLUBID,
                        COLUMN_NAME,
                        COLUMN_SURNAME,
                        COLUMN_NNAME,
                        COLUMN_NSURNAME,
                        COLUMN_CONTACTNUMBER},
                COLUMN_CLUBID + " =? ";
        new String[]{String.valueOf(clubID)},
                null,
                null,
                null,
                null,
                null,
                null);

        if(cursor.moveToFirst()) {
            final NextOfKin NextOfKin = new NextOfKin.Builder()
                    .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                    .name(cursor.getString(cursor.getCOLUMNIndex(COLUMN_NAME)))
                    .surname(cursor.getString(cursor.getCOLUMNIndex(COLUMN_SURNAME)))
                    .nName(cursor.getString(cursor.getCOLUMNIndex(COLUMN_NNAME)))
                    .nSurname(cursor.getString(cursor.getCOLUMNIndex(COLUMN_NSURNAME)))
                    .contactNumber(cursor.getString(cursor.getCOLUMNIndex(COLUMN_CONTACTNUMBER))))
                    .build();
            return NextOfKin;
        }
        else{
            return null;
        }
    }

    @Override
    public NextOfKin save(NextOfKin entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_NNAME, entity.getnName());
        values.put(COLUMN_NSURNAME, entity.getnSurname());
        values.put(COLUMN_CONTACTNUMBER, entity.getContactNumber());
        int clubID = db.insertOrThrow(TABLE_NAME, null, values);
        NextOfKin insertedEntity = new NextOfKin.Builder()
                .copy(entity)
                .clubID(new int(clubID))
                .build();
        return insertedEntity;
    }

    @Override
    public NextOfKin update(NextOfKin entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_CLUBID, entity.getClubID());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_NNAME, entity.getnName());
        values.put(COLUMN_NSURNAME, entity.getnSurname());
        values.put(COLUMN_CONTACTNUMBER, entity.getContactNumber());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())}
        );
        return entity;
    }

    @Override
    public NextOfKin delete(NextOfKin entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_CLUBID + " =? ",
                new String[]{String.valueOf(entity.getClubID())});
        return entity;
    }

    @Override
    public Set<NextOfKin> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<NextOfKin> nKin = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final NextOfKin nKin = new NextOfKin.Builder()
                        .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                        .street(cursor.getString(cursor.getCOLUMNIndex(COLUMN_STREET)))
                        .area(cursor.getString(cursor.getCOLUMNIndex(COLUMN_AREA)))
                        .postalCode(cursor.getString(cursor.getCOLUMNIndex(COLUMN_POSTALCODE)))
                        .postalAddress(cursor.getString(cursor.getCOLUMNIndex(COLUMN_POSTALADDRESS)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .build();
                NextOfKin.add(nKin);
            } while (cursor.moveToNext());
        }
        return NextOfKin;
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
