package com.example.king.dsjweek1.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.king.dsjweek1.entity.Co_Goods;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CO__GOODS".
*/
public class Co_GoodsDao extends AbstractDao<Co_Goods, Long> {

    public static final String TABLENAME = "CO__GOODS";

    /**
     * Properties of entity Co_Goods.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "goods_id");
        public final static Property CommodityId = new Property(1, String.class, "commodityId", false, "COMMODITY_ID");
        public final static Property CommodityName = new Property(2, String.class, "commodityName", false, "COMMODITY_NAME");
        public final static Property MasterPic = new Property(3, String.class, "masterPic", false, "MASTER_PIC");
        public final static Property Price = new Property(4, String.class, "price", false, "PRICE");
        public final static Property SaleNum = new Property(5, String.class, "saleNum", false, "SALE_NUM");
    }


    public Co_GoodsDao(DaoConfig config) {
        super(config);
    }
    
    public Co_GoodsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CO__GOODS\" (" + //
                "\"goods_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE ," + // 0: id
                "\"COMMODITY_ID\" TEXT," + // 1: commodityId
                "\"COMMODITY_NAME\" TEXT," + // 2: commodityName
                "\"MASTER_PIC\" TEXT," + // 3: masterPic
                "\"PRICE\" TEXT," + // 4: price
                "\"SALE_NUM\" TEXT);"); // 5: saleNum
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CO__GOODS\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Co_Goods entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String commodityId = entity.getCommodityId();
        if (commodityId != null) {
            stmt.bindString(2, commodityId);
        }
 
        String commodityName = entity.getCommodityName();
        if (commodityName != null) {
            stmt.bindString(3, commodityName);
        }
 
        String masterPic = entity.getMasterPic();
        if (masterPic != null) {
            stmt.bindString(4, masterPic);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(5, price);
        }
 
        String saleNum = entity.getSaleNum();
        if (saleNum != null) {
            stmt.bindString(6, saleNum);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Co_Goods entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String commodityId = entity.getCommodityId();
        if (commodityId != null) {
            stmt.bindString(2, commodityId);
        }
 
        String commodityName = entity.getCommodityName();
        if (commodityName != null) {
            stmt.bindString(3, commodityName);
        }
 
        String masterPic = entity.getMasterPic();
        if (masterPic != null) {
            stmt.bindString(4, masterPic);
        }
 
        String price = entity.getPrice();
        if (price != null) {
            stmt.bindString(5, price);
        }
 
        String saleNum = entity.getSaleNum();
        if (saleNum != null) {
            stmt.bindString(6, saleNum);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Co_Goods readEntity(Cursor cursor, int offset) {
        Co_Goods entity = new Co_Goods( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // commodityId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // commodityName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // masterPic
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // price
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5) // saleNum
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Co_Goods entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setCommodityId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCommodityName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMasterPic(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPrice(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setSaleNum(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Co_Goods entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Co_Goods entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Co_Goods entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}