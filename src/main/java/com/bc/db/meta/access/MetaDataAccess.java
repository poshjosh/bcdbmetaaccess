/*
 * Copyright 2018 NUROX Ltd.
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

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Chinomso Bassey Ikwuagwu on Sep 25, 2018 5:47:09 PM
 */
public interface MetaDataAccess extends MetaDataNames {

    int [] fetchColumnDisplaySizes(String tableName) throws SqlRuntimeException;

    int [] fetchColumnDataTypes(String tableName) throws SqlRuntimeException;
    
    int [] fetchColumnNullables(String tableName) throws SqlRuntimeException;
    
    Set<String> fetchCatalogNames() throws SqlRuntimeException;

    default List<Integer> fetchIntMetaData(String tableName, int resultSetDataIndex) 
            throws SqlRuntimeException {
        return this.fetchIntMetaData(null, null, tableName, null, resultSetDataIndex);
    }
    
    List<Integer> fetchIntMetaData(String catalog, String schemaNamePattern, 
            String tableNamePattern, String columnNamePattern, int resultSetDataIndex) throws SqlRuntimeException;

    Set<String> fetchSchemaNames(); 

    Map<String, String> fetchSchemaToCatalogNameMap() throws SqlRuntimeException;

    default List<String> fetchStringMetaData(String tableName, int resultSetDataIndex) 
            throws SqlRuntimeException {
        return this.fetchStringMetaData(null, null, tableName, null, resultSetDataIndex);
    }

    List<String> fetchStringMetaData(String catalog, String schemaNamePattern, 
            String tableNamePattern, String columnNamePattern, int resultSetDataIndex) throws SqlRuntimeException;

    boolean isAnyTableExisting() throws SqlRuntimeException;
    
    Map<String, List<String>> fetchCatalogToTableNameMap() throws SqlRuntimeException;

    default List<Integer> fetchIntIndexInfo(String tableNamePattern, boolean unique, int resultSetDataIndex) {
        return fetchIntIndexInfo(null, null, tableNamePattern, unique, true, resultSetDataIndex);
    }
    
    List<Integer> fetchIntIndexInfo(String catalog, String schemaNamePattern, String tableNamePattern, 
            boolean unique, boolean approximate, int resultSetDataIndex);

    default List<String> fetchStringIndexInfo(String tableNamePattern, boolean unique, int resultSetDataIndex) {
        return fetchStringIndexInfo(null, null, tableNamePattern, unique, true, resultSetDataIndex);
    }

    List<String> fetchStringIndexInfo(String catalog, String schemaNamePattern, String tableNamePattern, boolean unique, boolean approximate, int resultSetDataIndex);
}
