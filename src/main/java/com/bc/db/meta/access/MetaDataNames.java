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

/**
 * @author Chinomso Bassey Ikwuagwu on Sep 25, 2018 5:47:51 PM
 */
public interface MetaDataNames {

    /**
     * table catalog (may be null)
     */
    int TABLE_CATALOG = 1;
    
    /**
     * table schema (may be null)
     */
    int TABLE_SCHEMA = 2;
    
    
    int TABLE_NAME = 3;
    
    int COLUMN_NAME = 4;

    /**
     * SQL type from java.sql.Types
     */
    int COLUMN_DATA_TYPE = 5;
    
    /**
     * Data source dependent type name, for a UDT the type name is fully qualified
     */
    int COLUMN_TYPE_NAME = 6;
    
    int COLUMN_SIZE = 7;
    
    /**
     * The number of fractional digits. Null is returned for data types where DECIMAL_DIGITS is not applicable.
     */
    int DECIMAL_DIGITS = 9;
    
    /**
     * Radix (typically either 10 or 2)
     */
    int NUM_PREC_RADIX = 10;
    
    /**
     * is NULL allowed.
     * columnNoNulls - might not allow NULL values
     * columnNullable - definitely allows NULL values
     * columnNullableUnknown - nullability unknown
     */
    int COLUMN_NULLABLE = 11;
    
    /**
     * String => comment describing column (may be null)
     */
    int REMARKS = 12;

    /**
     * String => default value for the column, which should be interpreted as a string when the value is enclosed in single quotes (may be null)
     */
    int COLUMN_DEF = 13;   

    /**
     * String => Indicates whether this column is auto incremented.<br>
     * YES --- if the column is auto incremented<br>
     * NO --- if the column is not auto incremented<br>
     * Empty string --- if it cannot be determined whether the column is auto incremented
     */
    int IS_AUTOINCREMENT = 23; 

    /**
     * String => Indicates whether this is a generated column.<br>
     * YES --- if this a generated column<br>
     * NO --- if this not a generated column<br>
     * Empty string --- if it cannot be determined whether this is a generated column
     */
    int IS_GENERATEDCOLUMN = 24;
        
/**
 * 
14 - SQL_DATA_TYPE int => unused
15 - SQL_DATETIME_SUB int => unused
16 - CHAR_OCTET_LENGTH int => for char types the maximum number of bytes in the column
17 - ORDINAL_POSITION int => index of column in table (starting at 1)
18 - IS_NULLABLE String => ISO rules are used to determine the nullability for a column.
YES --- if the column can include NULLs
NO --- if the column cannot include NULLs
empty string --- if the nullability for the column is unknown
19 - SCOPE_CATALOG String => catalog of table that is the scope of a reference attribute (null if DATA_TYPE isn't REF)
20 - SCOPE_SCHEMA String => schema of table that is the scope of a reference attribute (null if the DATA_TYPE isn't REF)
21 - SCOPE_TABLE String => table name that this the scope of a reference attribute (null if the DATA_TYPE isn't REF)
22 - SOURCE_DATA_TYPE short => source type of a distinct type or user-generated Ref type, SQL type from java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
* 
**/

    /**
     * String => table catalog (may be null)
     */    
    int INDEX_TABLE_CAT = 1;

    /**
     * String => table schema (may be null)
     */
    int INDEX_TABLE_SCHEM = 2;

    /**
     * String => table name
     */
    int INDEX_TABLE_NAME = 3; 

    /**
     * boolean => Can index values be non-unique. false when TYPE is tableIndexStatistic
     */
    int INDEX_NON_UNIQUE = 4;

    /**
     * String => index catalog (may be null); null when TYPE is tableIndexStatistic
     */
    int INDEX_QUALIFIER = 5;

    /**
     *  String => index name; null when TYPE is tableIndexStatistic
     */
    int INDEX_NAME = 6;

    /**
     * short => index type:
     * tableIndexStatistic - this identifies table statistics that are returned in conjuction with a table's index descriptions
     * tableIndexClustered - this is a clustered index
     * tableIndexHashed - this is a hashed index
     * tableIndexOther - this is some other style of index
     */
    int INDEX_TYPE = 7; 

    /**
     * short => column sequence number within index; zero when TYPE is tableIndexStatistic
     */
    int INDEX_ORDINAL_POSITION = 8;

    /**
     * String => column name; null when TYPE is tableIndexStatistic
     */
    int INDEX_COLUMN_NAME = 9;

    /**
     * String => column sort sequence, "A" => ascending, "D" => descending, may be null if sort sequence is not supported; null when TYPE is tableIndexStatistic
     */
    int INDEX_ASC_OR_DESC = 10; 

    /**
     * long => When TYPE is tableIndexStatistic, then this is the number of rows in the table; otherwise, it is the number of unique values in the index.
     */
    int INDEX_CARDINALITY = 11;

    /**
     * long => When TYPE is tableIndexStatisic then this is the number of pages used for the table, otherwise it is the number of pages used for the current index.
     */
    int INDEX_PAGES = 12;

    /**
     * String => Filter condition, if any. (may be null)
     */
    int INDEX_FILTER_CONDITION = 13;
}