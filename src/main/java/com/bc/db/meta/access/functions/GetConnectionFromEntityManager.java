package com.bc.db.meta.access.functions;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

/**
 * @author hp
 */
public class GetConnectionFromEntityManager implements Function<EntityManager, Connection>{
    
    /**
     * Attempt to get a {@link java.sql.Connection Connection} from the
     * {@link javax.persistence.EntityManager EntityManager}.
     * 
     * <p>
     * Since hibernate does not support unwrapping the EntityManager to a
     * Connection, uses reflection to get the value of 
     * {@link org.hibernate.internal.SessionImpl SessionImpl}#{@link org.hibernate.internal.SessionImpl#connection() connection()}
     * </p>
     * <b>Note:</b>
     * <p>
     * Make sure you call this method within a transaction, i.e call
     * <tt>EntityManager.getTransaction().begin()</tt> before and 
     * <tt>EntityManager.getTransaction().commit()</tt> after this method.
     * </p>
     * @see http://wiki.eclipse.org/EclipseLink/Examples/JPA/EMAPI#Getting_a_JDBC_Connection_from_an_EntityManager
     * @param entityManager
     * @return 
     */
    @Override
    public Connection apply(EntityManager entityManager) {
        try{
            return this.get(entityManager);
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public Connection getOrDefault(
            EntityManager entityManager, Connection defaultValue) {
        Connection connection;
        try{
            connection = this.get(entityManager);
        }catch(Exception e) {
            connection = null;
        }
        return connection == null ? defaultValue : connection;
    }

    public Connection get(EntityManager entityManager) 
            throws PersistenceException,
            ClassNotFoundException, IllegalAccessException, IllegalArgumentException, 
            NoSuchMethodException, SecurityException, InvocationTargetException{
        PersistenceException exception = null;
        Connection connection;
        try{
            connection = entityManager.unwrap(java.sql.Connection.class);
        }catch(PersistenceException e) {
            connection = null;
        }
        if(connection == null) {
            try{
                connection = this.getHibernateConnection(entityManager);
            }catch(ClassNotFoundException | IllegalAccessException | IllegalArgumentException | 
                    NoSuchMethodException | SecurityException | InvocationTargetException e) {
                if(exception != null) {
                    exception.addSuppressed(e);
                    throw exception;
                }else{
                    throw e;
                }
            }
        }
        return connection;
    }

    public Connection getHibernateConnection(EntityManager entityManager) 
            throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, 
            NoSuchMethodException, SecurityException, InvocationTargetException{
        final Class hibernateSessionType = Class.forName(
                "org.hibernate.internal.SessionImpl");
        final Object hibernateSessionInstance = entityManager
                .unwrap(hibernateSessionType);
        Connection connection = (java.sql.Connection)hibernateSessionType
                .getMethod("connection").invoke(hibernateSessionInstance);
        return connection;
    }
}
