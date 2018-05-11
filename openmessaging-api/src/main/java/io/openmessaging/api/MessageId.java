/*
 * Copyright 2017 OpenMessaging
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.openmessaging.api;

import java.io.Serializable;
import java.util.Comparator;
import javax.print.attribute.standard.MediaSize.NA;

/**
 * An opaque unique identifier of a single message.
 */
public interface MessageId extends Comparable<MessageId>, Serializable {

    MessageId EARLIEST = new MessageId() {

        private static final String NAME = "earliest";

        @Override
        public int compareTo(MessageId o) {
            if (o == EARLIEST) {
                return 0;
            }
            return -1;
        }

        @Override
        public int hashCode() {
            return NAME.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj == EARLIEST;
        }

        @Override
        public String toString() {
            return NAME;
        }
    };

    MessageId LATEST = new MessageId() {

        private static final String NAME = "latest";

        @Override
        public int compareTo(MessageId o) {
            if (o == LATEST) {
                return 0;
            }
            return 1;
        }

        @Override
        public int hashCode() {
            return NAME.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return obj == LATEST;
        }

        @Override
        public String toString() {
            return NAME;
        }
    };

}
