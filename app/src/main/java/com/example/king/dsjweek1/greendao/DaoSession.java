package com.example.king.dsjweek1.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.king.dsjweek1.entity.Co_Goods;

import com.example.king.dsjweek1.greendao.Co_GoodsDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig co_GoodsDaoConfig;

    private final Co_GoodsDao co_GoodsDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        co_GoodsDaoConfig = daoConfigMap.get(Co_GoodsDao.class).clone();
        co_GoodsDaoConfig.initIdentityScope(type);

        co_GoodsDao = new Co_GoodsDao(co_GoodsDaoConfig, this);

        registerDao(Co_Goods.class, co_GoodsDao);
    }
    
    public void clear() {
        co_GoodsDaoConfig.clearIdentityScope();
    }

    public Co_GoodsDao getCo_GoodsDao() {
        return co_GoodsDao;
    }

}
