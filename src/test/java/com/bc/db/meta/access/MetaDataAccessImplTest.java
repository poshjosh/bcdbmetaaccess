/*
 * Copyright 2019 NUROX Ltd.
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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Josh
 */
public class MetaDataAccessImplTest {
    
    private final String catalog = null;
    private final String schemaNamePattern = null;
    private final String tableNamePattern = "appointment";

    private final String columnNamePattern = null;
    
    boolean unique = true;
    boolean approximate = true;

    private static EntityManagerFactory emf;
    
    public MetaDataAccessImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("bcdbmetaaccess_pu");
    }
    
    public MetaDataAccess getInstance() {
        return new MetaDataAccessImpl(emf);
    }

    /**
     * Test of fetchColumnDisplaySizes method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchColumnDisplaySizes() {
        System.out.println("fetchColumnDisplaySizes");
        MetaDataAccess instance = this.getInstance();
        int[] result = instance.fetchColumnDisplaySizes(tableNamePattern);
        System.out.println("Display Sizes: " + Arrays.toString(result));
    }

    /**
     * Test of fetchColumnDataTypes method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchColumnDataTypes() {
        System.out.println("fetchColumnDataTypes");
        MetaDataAccess instance = this.getInstance();
        int[] result = instance.fetchColumnDataTypes(tableNamePattern);
        System.out.println("Data Types: " + Arrays.toString(result));
    }

    /**
     * Test of fetchColumnNullables method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchColumnNullables() {
        System.out.println("fetchColumnNullables");
        MetaDataAccess instance = this.getInstance();
        int[] result = instance.fetchColumnNullables(tableNamePattern);
        System.out.println("Column Nullablility: " + Arrays.toString(result));
    }

    /**
     * Test of fetchSchemaNames method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchSchemaNames() {
        System.out.println("fetchSchemaNames");
        MetaDataAccess instance = this.getInstance();
        Set<String> result = instance.fetchSchemaNames();
        System.out.println("Schema names: " + result);
    }

    /**
     * Test of fetchCatalogNames method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchCatalogNames() {
        System.out.println("fetchCatalogNames");
        MetaDataAccess instance = this.getInstance();
        Set<String> result = instance.fetchCatalogNames();
        System.out.println("Catalog names: " + result);
    }

    /**
     * Test of fetchSchemaToCatalogNameMap method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchSchemaToCatalogNameMap() {
        System.out.println("fetchSchemaToCatalogNameMap");
        MetaDataAccess instance = this.getInstance();
        Map<String, String> result = instance.fetchSchemaToCatalogNameMap();
        System.out.println("Schema to catalog name map: " + result);
    }

    /**
     * Test of fetchIntMetaData method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchIntMetaData_5args() {
        System.out.println("fetchIntMetaData");
        int resultSetDataIndex = MetaDataAccess.COLUMN_SIZE;
        MetaDataAccess instance = this.getInstance();
        List<Integer> result = instance.fetchIntMetaData(catalog, schemaNamePattern, tableNamePattern, columnNamePattern, resultSetDataIndex);
        System.out.println("Column sizes: " + result);
    }

    /**
     * Test of fetchStringMetaData method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchStringMetaData() {
        System.out.println("fetchStringMetaData");
        int resultSetDataIndex = MetaDataAccess.COLUMN_TYPE_NAME;
        MetaDataAccess instance = this.getInstance();
        List<String> result = instance.fetchStringMetaData(catalog, schemaNamePattern, tableNamePattern, columnNamePattern, resultSetDataIndex);
        System.out.println("Column type names: " + result);
    }

    /**
     * Test of fetchIntIndexInfo method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchIntIndexInfo() {
        System.out.println("fetchIntIndexInfo");
        int resultSetDataIndex = MetaDataAccess.INDEX_ORDINAL_POSITION;
        MetaDataAccess instance = this.getInstance();
        List<Integer> result = instance.fetchIntIndexInfo(catalog, schemaNamePattern, tableNamePattern, unique, approximate, resultSetDataIndex);
        System.out.println("Index ordinal positions: " + result);
    }

    /**
     * Test of fetchStringIndexInfo method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchStringIndexInfo() {
        System.out.println("fetchStringIndexInfo");
        int resultSetDataIndex = MetaDataAccess.INDEX_NAME;
        MetaDataAccess instance = this.getInstance();
        List<String> result = instance.fetchStringIndexInfo(catalog, schemaNamePattern, tableNamePattern, unique, approximate, resultSetDataIndex);
        System.out.println("Index names: " + result);
    }

    /**
     * Test of isAnyTableExisting method, of class MetaDataAccessImpl.
     */
    @Test
    public void testIsAnyTableExisting() {
        System.out.println("isAnyTableExisting");
        MetaDataAccess instance = this.getInstance();
        boolean result = instance.isAnyTableExisting();
        System.out.println("Is any table existing: " + result);
    }

    /**
     * Test of fetchCatalogToTableNameMap method, of class MetaDataAccessImpl.
     */
    @Test
    public void testFetchCatalogToTableNameMap() {
        System.out.println("fetchCatalogToTableNameMap");
        MetaDataAccess instance = this.getInstance();
        Map<String, List<String>> result = instance.fetchCatalogToTableNameMap();
        System.out.println("Catalog to table name map: " + result);
    }
}
