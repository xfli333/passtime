package dao;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 11-7-13
 * Time: 下午10:37
 * To change this template use File | Settings | File Templates.
 */
public class PMF {
    private static final PersistenceManagerFactory pmfInstance = JDOHelper.getPersistenceManagerFactory("transactions-optional");

    private PMF() {
    }

    public static PersistenceManagerFactory get() {
        return pmfInstance;
    }
}
