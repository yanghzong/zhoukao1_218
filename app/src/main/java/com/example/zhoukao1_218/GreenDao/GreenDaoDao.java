package com.example.zhoukao1_218.GreenDao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.zhoukao1_218.entity.GreenDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "GREEN_DAO".
*/
public class GreenDaoDao extends AbstractDao<GreenDao, Void> {

    public static final String TABLENAME = "GREEN_DAO";

    /**
     * Properties of entity GreenDao.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", false, "ID");
        public final static Property CommodityId = new Property(1, int.class, "commodityId", false, "COMMODITY_ID");
        public final static Property CommodityName = new Property(2, String.class, "commodityName", false, "COMMODITY_NAME");
        public final static Property MasterPic = new Property(3, String.class, "masterPic", false, "MASTER_PIC");
        public final static Property Price = new Property(4, int.class, "price", false, "PRICE");
    }


    public GreenDaoDao(DaoConfig config) {
        super(config);
    }
    
    public GreenDaoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"GREEN_DAO\" (" + //
                "\"ID\" INTEGER," + // 0: id
                "\"COMMODITY_ID\" INTEGER NOT NULL ," + // 1: commodityId
                "\"COMMODITY_NAME\" TEXT," + // 2: commodityName
                "\"MASTER_PIC\" TEXT," + // 3: masterPic
                "\"PRICE\" INTEGER NOT NULL );"); // 4: price
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"GREEN_DAO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, GreenDao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCommodityId());
 
        String commodityName = entity.getCommodityName();
        if (commodityName != null) {
            stmt.bindString(3, commodityName);
        }
 
        String masterPic = entity.getMasterPic();
        if (masterPic != null) {
            stmt.bindString(4, masterPic);
        }
        stmt.bindLong(5, entity.getPrice());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, GreenDao entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getCommodityId());
 
        String commodityName = entity.getCommodityName();
        if (commodityName != null) {
            stmt.bindString(3, commodityName);
        }
 
        String masterPic = entity.getMasterPic();
        if (masterPic != null) {
            stmt.bindString(4, masterPic);
        }
        stmt.bindLong(5, entity.getPrice());
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public GreenDao readEntity(Cursor cursor, int offset) {
        GreenDao entity = new GreenDao( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // commodityId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // commodityName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // masterPic
            cursor.getInt(offset + 4) // price
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, GreenDao entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCommodityId(cursor.getInt(offset + 1));
        entity.setCommodityName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMasterPic(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPrice(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(GreenDao entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(GreenDao entity) {
        return null;
    }

    @Override
    public boolean hasKey(GreenDao entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}