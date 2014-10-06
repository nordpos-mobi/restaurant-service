/**
 * Copyright (c) 2012-2014 Nord Trading Network.
 */
package mobi.nordpos.restaurant.dao.ormlite;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import mobi.nordpos.restaurant.dao.*;
import mobi.nordpos.restaurant.model.User;

/**
 * @author Andrey Svininykh <svininykh@gmail.com>
 */
public class UserPersist extends BaseDaoImpl<User, String> implements UserDAO {    

    Dao<User, String> userDao;

    public UserPersist(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, User.class);
    }    

    @Override
    public User read(String userName) throws SQLException {
        userDao = DaoManager.createDao(connectionSource, User.class);        
        QueryBuilder<User, String> statementBuilder = userDao.queryBuilder();
        SelectArg selectArg = new SelectArg();
        statementBuilder.where().like(User.NAME, selectArg);
        selectArg.setValue(userName);
        PreparedQuery<User> preparedQuery = statementBuilder.prepare();
        return userDao.queryForFirst(preparedQuery);
    }

}
