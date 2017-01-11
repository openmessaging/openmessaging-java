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
package org.apache.openmessaging.samples.service.impl;

import java.util.concurrent.atomic.AtomicInteger;
import org.apache.openmessaging.samples.service.CallRequest;
import org.apache.openmessaging.samples.service.CallResponse;
import org.apache.openmessaging.samples.service.api.HelloService;

public class HelloServiceImpl implements HelloService {
    private AtomicInteger invokeTimes = new AtomicInteger(0);

    public CallResponse sayHello(CallRequest request) {
        String ret = String.format("arg: %s invokeTimes: %d", request.getValue(), this.invokeTimes.incrementAndGet());
        CallResponse response = new CallResponse();
        response.setValue(ret);
        return response;
    }

    public void throwException(CallRequest request) throws Exception {
        throw new Exception("a unknown exception happened");
    }
}
