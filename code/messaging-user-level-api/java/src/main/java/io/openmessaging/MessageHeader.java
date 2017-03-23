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
    /**
     * MessageId is a unique identification of a message.
     */
    public static final String MESSAGE_ID = "MessageId";

    /**
     * TODO : Consider to remove it from headers.
     */
    public static final String DESTINATION = "Destination";

    /**
     * The specified topic that a message belongs to.
     */
    public static final String TOPIC = "Topic";

    /**
     * The specified queue that a message belongs to.
     */
    public static final String QUEUE = "Queue";

    /**
     * The born timestamp of a message in client side.
     */
    public static final String BORN_TIMESTAMP = "BornTimestamp";

    /**
     * The born host of a message in client side.
     */
    public static final String BORN_HOST = "BornHost";

    /**
     * The store timestamp of a message in server side.
     */
    public static final String STORE_TIMESTAMP = "StoreTimestamp";

    /**
     * The store host of a message in server side.
     */
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
