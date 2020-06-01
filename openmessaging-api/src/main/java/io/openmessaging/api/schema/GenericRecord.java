/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.openmessaging.api.schema;

import java.util.List;

/**
 * Used for traverse the data in the message body when do not know the specific Schema.
 *
 * @version OMS 2.0.0
 * @since OMS 2.0.0
 */
public interface GenericRecord {

    /**
     * return schema id
     *
     * @return schema id
     */
    String getSchemaId();

    /**
     * return the list of fields associated with the record
     *
     * @return the list of fields associated with the record
     */
    List<Field> getFields();

    /**
     * retrieve the value of the provided field
     *
     * @param field the field to retrieve the value
     * @return the value object
     */
    Object getField(Field field);

    /**
     * retrieve the value of the provided fieldName
     * @param fieldName the field name to retrieve the value
     * @return the value object
     */
    Object getField(String fieldName);
}
