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
 * The {@code MessageHeader} class describes each OMS message header field.
 * A message¡¯s complete header is transmitted to all OMS clients that receive the
 * message.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public class MessageHeader {
    /**
     *The {@code MESSAGE_ID} header field contains a value that uniquely identifies
     * each message sent by a {@Producer}.
     * <p>
     * When a message is sent, MESSAGE_ID is ignored.
     * <p>
     * When the send method returns it contains a producer-assigned value.
     */
    public static final String MESSAGE_ID = "MessageId";

    /**
     * The {@code TOPIC} header field is the destination which the message is being sent.
     * <p>
     * When a message is sent this value is should be set properly.
     * <p>
     * When a message is received, its {@code TOPIC} value must be equivalent to the
     * value assigned when it was sent.
     */
    public static final String TOPIC = "Topic";

    /**
     * The {@code QUEUE} header field is the destination which the message is being sent.
     * <p>
     * When a message is sent this value is should be set properly.
     * <p>
     * When a message is received, its {@code QUEUE} value must be equivalent to the
     * value assigned when it was sent.
     */
    public static final String QUEUE = "Queue";

    /**
     * The {@code BORN_TIMESTAMP} header field contains the time a message was handed
     * off to a {@code Producer} to be sent.
     * When a message is sent, {@code BORN_TIMESTAMP} will be set with current timestamp
     * as the born timestamp of a message in client side.
     */
    public static final String BORN_TIMESTAMP = "BornTimestamp";

    /**
     * The {@code BORN_HOST} header field contains the born host info of a message in client side.
     */
    public static final String BORN_HOST = "BornHost";

    /**
     * The {@code STORE_TIMESTAMP} header field contains the store timestamp of a message in server side.
     * <p>
     * When a message is sent, STORE_TIMESTAMP is ignored.
     * <p>
     * When the send method returns it contains a server-assigned value.
     */
    public static final String STORE_TIMESTAMP = "StoreTimestamp";

    /**
     * The {@code STORE_HOST} header field contains the store host info of a message in server side.
     * <p>
     * When a message is sent, STORE_HOST is ignored.
     * <p>
     * When the send method returns it contains a server-assigned value.
     */
    public static final String STORE_HOST = "StoreHost";

    /**
     * The {@code START_TIME} header field contains the start timestamp that a message
     * can be delivered to consumer client.
     * <p>
     */
    public static final String START_TIME = "StartTime";
    public static final String STOP_TIME = "StopTime";

    //TTL
    public static final String TIMEOUT = "Timeout";


    public static final String PRIORITY = "Priority";


    public static final String RELIABILITY = "Reliability";

    public static final String SEARCH_KEY = "SearchKey";

    // coron expression
    public static final String SCHEDULE_EXPRESSION = "ScheduleExpression";


    public static final String SHARDING_KEY = "ShardingKey";

    public static final String SHARDING_PARTITION = "ShardingPartition";

    //other system.
    public static final String TRACE_ID = "TraceId";
}
