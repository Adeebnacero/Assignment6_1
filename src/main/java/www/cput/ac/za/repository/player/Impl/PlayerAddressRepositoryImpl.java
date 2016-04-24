package www.cput.ac.za.repository.player.Impl;

import www.cput.ac.za.domain.player.PlayerAddress;
import www.cput.ac.za.repository.player.PlayerAddressRepository;

import javax.naming.Context;
import java.awt.*;
import java.sql.SQLException;
import java.util.Set;


/**
 * Created by Adeebo on 2016/04/23.
 */
public class PlayerAddressRepositoryImpl extends SQLiteOpenHelper implements PlayerAddressRepository {

        public static final String TABLE_NAME = "playeraddress";
        private SQLiteDatabase db;

        public static final String COLUMN_CLUBID = "clubID";
        public static final String COLUMN_STREET = "street";
        public static final String COLUMN_AREA = "area";
        public static final String COLUMN_POSTALCODE = "postalCode";
        public static final String COLUMN_POSTALADDRESS = "postalAddress";
        public static final String COLUMN_DATE = "date";


        //Database sql creation statement
        private static final String DATABSE_CREATE = " CREATE TABLE"
                + TABLE_NAME + "("
                + COLUMN_CLUBID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + COLUMN_STREET + " TEXT NOT NULL , "
                + COLUMN_AREA + " TEXT NOT NULL"
                + COLUMN_POSTALCODE + " TEXT NOT NULL"
                + COLUMN_POSTALADDRESS + " TEXT NOT NULL"
                + COLUMN_DATE + " DATE NOT NULL);";

        public PlayerAddressRepositoryImpl(Context context){

            super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABSE_VERSION);
        }

        public void open()throws SQLException {

            db = this.getWritableDatabse();
        }

        public void close(){

            this.close();
        }

        @Override
        public PlayerAddress findById(int clubID){

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query();
            TABLE_NAME,
                    new String[]{
                            COLUMN_CLUBID,
                            COLUMN_STREET,
                            COLUMN_AREA,
                            COLUMN_POSTALCODE,
                            COLUMN_POSTALADDRESS,
                            COLUMN_DATE, },
                            COLUMN_CLUBID + " =? ";
            new String[]{String.valueOf(clubID)},
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);

            if(cursor.moveToFirst()) {
                final PlayerAddress PlayerAddress = new PlayerAddress.Builder()
                        .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                        .street(cursor.getString(cursor.getCOLUMNIndex(COLUMN_STREET)))
                        .area(cursor.getString(cursor.getCOLUMNIndex(COLUMN_AREA)))
                        .postalCode(cursor.getString(cursor.getCOLUMNIndex(COLUMN_POSTALCODE)))
                        .postalAddress(cursor.getString(cursor.getCOLUMNIndex(COLUMN_POSTALADDRESS)))
                        .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                        .build();
                return PlayerAddress;
            }
            else{
                return null;
            }
        }

        @Override
        public PlayerAddress save(PlayerAddress entity) {
            open();
            ContentValues values = new ContentValues();
            values.put(COLUMN_CLUBID, entity.getClubID());
            values.put(COLUMN_STREET, entity.getStreet());
            values.put(COLUMN_AREA, entity.getArea());
            values.put(COLUMN_POSTALCODE, entity.getPostalCode());
            values.put(COLUMN_POSTALADDRESS, entity.getPostalAddress());
            values.put(COLUMN_DATE, entity.getDate());
            int clubID = db.insertOrThrow(TABLE_NAME, null, values);
            PlayerAddress insertedEntity = new PlayerAddress.Builder()
                    .copy(entity)
                    .clubID(new int(clubID))
                    .build();
            return insertedEntity;
        }

        @Override
        public PlayerAddress update(PlayerAddress entity) {
            open();
            ContentValues values = new ContentValues();
            values.put(COLUMN_CLUBID, entity.getClubID());
            values.put(COLUMN_STREET, entity.getStreet());
            values.put(COLUMN_AREA, entity.getArea());
            values.put(COLUMN_POSTALCODE, entity.getPostalCode());
            values.put(COLUMN_POSTALADDRESS, entity.getPostalAddress());
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
        public PlayerAddress delete(PlayerAddress entity) {
            open();
            db.delete(
                    TABLE_NAME,
                    COLUMN_CLUBID + " =? ",
                    new String[]{String.valueOf(entity.getClubID())});
            return entity;
        }

        @Override
        public Set<PlayerAddress> findAll() {
            SQLiteDatabase db = this.getReadableDatabase();
            Set<PlayerAddress> pAddress = new HashSet<>();
            open();
            Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
            if (cursor.moveToFirst()) {
                do {
                    final PlayerAddress pAddress = new PlayerAddress.Builder()
                            .clubID(cursor.getInt(cursor.getCOLUMNIndex(COLUMN_CLUBID)))
                            .street(cursor.getString(cursor.getCOLUMNIndex(COLUMN_STREET)))
                            .area(cursor.getString(cursor.getCOLUMNIndex(COLUMN_AREA)))
                            .postalCode(cursor.getString(cursor.getCOLUMNIndex(COLUMN_POSTALCODE)))
                            .postalAddress(cursor.getString(cursor.getCOLUMNIndex(COLUMN_POSTALADDRESS)))
                            .date(AppUtil.getDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE))))
                            .build();
                    PlayerAddress.add(pAddress);
                } while (cursor.moveToNext());
            }
            return PlayerAddress;
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
