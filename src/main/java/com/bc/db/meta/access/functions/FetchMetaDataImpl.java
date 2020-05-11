/*
 * Copyright 2017 NUROX Ltd.
 *
 * Licensed under the NUROX Ltd Software License (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.looseboxes.com/legal/licenses/software.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bc.db.meta.access.functions;

import com.bc.db.meta.access.SqlRuntimeException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Function;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author Chinomso Bassey Ikwuagwu on Oct 14, 2017 9:45:01 PM
 */
public class FetchMetaDataImpl<T> implements FetchMetaData<T> {

    private final EntityManagerFactory entityManagerFactory;
    private final Function<EntityManager, Connection> getConnection;

    public FetchMetaDataImpl(
            EntityManagerFactory emf, 
            Function<EntityManager, Connection> getConnection) {
        this.entityManagerFactory = Objects.requireNonNull(emf);
        this.getConnection = Objects.requireNonNull(getConnection);
    }
    
    @Override
    public T apply(MetaDataParser<T> action) {
        
        final T output;
        
        final EntityManager em = this.entityManagerFactory.createEntityManager();
        
        java.sql.Connection conn;
        
        try{
            em.getTransaction().begin();
            
            conn = this.getConnection.apply(em);

            final DatabaseMetaData metaData = conn.getMetaData();
            
            output = action.apply(metaData);
            
            em.getTransaction().commit();
            
        }catch(SQLException e) {
            throw new SqlRuntimeException(e);
        }finally{
            if(em.isOpen()) {
                em.close();
            }
        }
        
        return output;
    }
}
