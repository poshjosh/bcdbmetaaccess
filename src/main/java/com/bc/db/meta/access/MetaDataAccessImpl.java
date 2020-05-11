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

package com.bc.db.meta.access;

import com.bc.db.meta.access.functions.ExtractColumnData;
import com.bc.db.meta.access.functions.ExtractIndexInfo;
import com.bc.db.meta.access.functions.ExtractResultIntegerList;
import com.bc.db.meta.access.functions.ExtractResultStringList;
import com.bc.db.meta.access.functions.ExtractSchemaNames;
import com.bc.db.meta.access.functions.ExtractSchemaToCatalogMappings;
import com.bc.db.meta.access.functions.FetchMetaDataImpl;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import com.bc.db.meta.access.functions.FetchMetaData;
import com.bc.db.meta.access.functions.GetConnectionFromEntityManager;
import java.sql.Connection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 * @author Chinomso Bassey Ikwuagwu on Oct 13, 2017 9:42:12 PM
 */
public class MetaDataAccessImpl implements MetaDataAccess, Serializable {

    private transient static final Logger LOG = Logger.getLogger(MetaDataAccessImpl.class.getName());

    private final EntityManagerFactory emf;
    private final Function<EntityManager, Connection> getConnection;

    public MetaDataAccessImpl(EntityManagerFactory emf) {
        this(emf, new GetConnectionFromEntityManager());
    }
    
    public MetaDataAccessImpl(
            EntityManagerFactory emf,
            Function<EntityManager, Connection> getConnection) {
        this.emf = Objects.requireNonNull(emf);
        this.getConnection = Objects.requireNonNull(getConnection);
    }

    @Override
    public int [] fetchColumnDisplaySizes(String tableName) {
        return this.fetchIntArrMetaData(tableName, MetaDataAccess.COLUMN_SIZE);
    }
    
    @Override
    public int [] fetchColumnDataTypes(String tableName) {
        return this.fetchIntArrMetaData(tableName, MetaDataAccess.COLUMN_DATA_TYPE);
    }
    
    @Override
    public int [] fetchColumnNullables(String tableName) {
        return this.fetchIntArrMetaData(tableName, MetaDataAccess.COLUMN_NULLABLE);
    }

    public int [] fetchIntArrMetaData(String tableName, int resultSetDataIndex) {
        
        final List<Integer> fetched = this.fetchIntMetaData(tableName, resultSetDataIndex);
        
        final int [] output = new int[fetched.size()];
        
        int i = 0;
        for(Integer integer : fetched) {
            output[i++] = integer;
        }
        
        return output;
    }

    @Override
    public Set<String> fetchSchemaNames() {
        
        final FetchMetaData<Set<String>> fetchMetaData = new FetchMetaDataImpl(
                this.emf, this.getConnection);
                
        final Set<String> output = fetchMetaData.apply(new ExtractSchemaNames());

        return output;
    }

    @Override
    public Set<String> fetchCatalogNames() {
        return new LinkedHashSet(this.fetchSchemaToCatalogNameMap().values());
    }
    
    @Override
    public Map<String, String> fetchSchemaToCatalogNameMap() {
        
        final FetchMetaData<Map<String, String>> fetchMetaData = 
                new FetchMetaDataImpl(this.emf, this.getConnection);
                
        final Map<String, String> output = fetchMetaData.apply(new ExtractSchemaToCatalogMappings());

        return output;
    }

    @Override
    public List<Integer> fetchIntMetaData(
            String catalog, String schemaNamePattern, 
            String tableNamePattern, String columnNamePattern, 
            int resultSetDataIndex) {
        
        final FetchMetaData<List<Integer>> fetchMetaData = new FetchMetaDataImpl(
                this.emf, this.getConnection);

        final List<Integer> output = fetchMetaData.apply(new ExtractColumnData(
                catalog, schemaNamePattern, tableNamePattern, 
                columnNamePattern, resultSetDataIndex,
                new ExtractResultIntegerList()
        ));
        
        LOG.finer(() -> "Catalog: " + catalog + ", schema: " + schemaNamePattern + 
                ", table: " + tableNamePattern + ", column: " + columnNamePattern +
                ", resultset index: " + resultSetDataIndex + "\nOutput: " + output);

        return output;
    }

    @Override
    public List<String> fetchStringMetaData(
            String catalog, String schemaNamePattern, 
            String tableNamePattern, String columnNamePattern, 
            int resultSetDataIndex) {
        
        final FetchMetaData<List<String>> fetchMetaData = new FetchMetaDataImpl(
                this.emf, this.getConnection);

        final List<String> output = fetchMetaData.apply(new ExtractColumnData(
                catalog, schemaNamePattern, tableNamePattern, 
                columnNamePattern, resultSetDataIndex,
                new ExtractResultStringList()
        ));

        LOG.finer(() -> "Catalog: " + catalog + ", schema: " + schemaNamePattern + 
                ", table: " + tableNamePattern + ", column: " + columnNamePattern +
                ", resultset index: " + resultSetDataIndex + "\nOutput: " + output);
        return output;
    }

    @Override
    public List<Integer> fetchIntIndexInfo(
            String catalog, String schemaNamePattern, 
            String tableNamePattern, boolean unique, boolean approximate,
            int resultSetDataIndex) {
        
        final FetchMetaData<List<Integer>> fetchMetaData = new FetchMetaDataImpl(
                this.emf, this.getConnection);

        final List<Integer> output = fetchMetaData.apply(new ExtractIndexInfo(
                catalog, schemaNamePattern, tableNamePattern, 
                unique, approximate, resultSetDataIndex,
                new ExtractResultIntegerList()
        ));
        
        LOG.finer(() -> "Catalog: " + catalog + ", schema: " + schemaNamePattern + 
                ", table: " + tableNamePattern + ", unique: " + unique + ", approximate: " + approximate +
                ", resultset index: " + resultSetDataIndex + "\nOutput: " + output);

        return output;
    }

    @Override
    public List<String> fetchStringIndexInfo(
            String catalog, String schemaNamePattern, 
            String tableNamePattern, boolean unique, boolean approximate, 
            int resultSetDataIndex) {
        
        final FetchMetaData<List<String>> fetchMetaData = new FetchMetaDataImpl(
                this.emf, this.getConnection);

        final List<String> output = fetchMetaData.apply(new ExtractIndexInfo(
                catalog, schemaNamePattern, tableNamePattern, 
                unique, approximate, resultSetDataIndex,
                new ExtractResultStringList()
        ));

        LOG.finer(() -> "Catalog: " + catalog + ", schema: " + schemaNamePattern + 
                ", table: " + tableNamePattern + ", unique: " + unique + ", approximate: " + approximate +
                ", resultset index: " + resultSetDataIndex + "\nOutput: " + output);
        
        return output;
    }

    @Override
    public boolean isAnyTableExisting() {
        boolean output = false;
        final Set<String> catalogNames = this.fetchCatalogNames();
        for(String catalogName : catalogNames) {
            final List<String> tableNames = this.fetchStringMetaData(
                    catalogName, null, null, null, TABLE_NAME);
            if(!tableNames.isEmpty()) {
                output = true;
                break;
            }
        }
        return output;
    }
    
    @Override
    public Map<String, List<String>> fetchCatalogToTableNameMap() {
        final Map<String, List<String>> output = new HashMap();
        final Set<String> catalogNames = this.fetchCatalogNames();
        for(String catalogName : catalogNames) {
            final List<String> tableNames = this.fetchStringMetaData(
                    catalogName, null, null, null, TABLE_NAME);
            output.put(catalogName, tableNames);
        }
        return Collections.unmodifiableMap(output);
    }
}
