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
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.openmessaging;

/**
 * @author vintagewang@apache.org
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public class MessageHeader {
    public static final String MESSAGE_ID = "MessageId";
    public static final String DESTINATION = "Destination";
    public static final String TOPIC = "Topic";
    public static final String QUEUE = "Queue";
    public static final String BORN_TIMESTAMP = "BornTimestamp";
    public static final String BORN_HOST = "BornHost";
    public static final String STORE_TIMESTAMP = "StoreTimestamp";
    public static final String STORE_HOST = "StoreHost";
    public static final String START_TIME = "StartTime";
    public static final String STOP_TIME = "StopTime";
    public static final String TIMEOUT = "Timeout";
    public static final String PRIORITY = "Priority";
    public static final String RELIABILITY = "Reliability";
    public static final String SEARCH_KEY = "SearchKey";
    public static final String SCHEDULE_EXPRESSION = "ScheduleExpression";
    public static final String SHARDING_KEY = "ShardingKey";
    public static final String SHARDING_PARTITION = "ShardingPartition";
    public static final String TRACE_ID = "TraceId";
}
