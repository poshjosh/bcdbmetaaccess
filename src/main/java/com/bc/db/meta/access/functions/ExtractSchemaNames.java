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
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Chinomso Bassey Ikwuagwu on Oct 14, 2017 10:13:15 PM
 */
public class ExtractSchemaNames implements MetaDataParser<Set<String>> {

    @Override
    public Set<String> apply(DatabaseMetaData dbMetaData) {
        
        final Set<String> output = new LinkedHashSet();
        
        try{
            
            final ResultSet rs = dbMetaData.getSchemas();
            
            while(rs.next()) {
                
                output.add(rs.getString(1));
            }
        }catch(SQLException e){
            throw new SqlRuntimeException(e);
        }

        return output.isEmpty() ? Collections.EMPTY_SET : Collections.unmodifiableSet(output);
    }
}
