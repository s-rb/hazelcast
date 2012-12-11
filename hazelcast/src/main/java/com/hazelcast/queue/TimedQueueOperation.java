/*
 * Copyright (c) 2008-2012, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.queue;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public abstract class TimedQueueOperation extends QueueKeyBasedOperation {

    private long timeoutMillis;

    protected TimedQueueOperation() {
    }

    public long getTimeoutMillis() {
        return timeoutMillis;
    }

    protected TimedQueueOperation(String name, long timeoutMillis) {
        super(name);
        this.timeoutMillis = timeoutMillis;
    }

    @Override
    public void writeInternal(DataOutput out) throws IOException {
        super.writeInternal(out);
        out.writeLong(timeoutMillis);
    }

    @Override
    public void readInternal(DataInput in) throws IOException {
        super.readInternal(in);
        timeoutMillis = in.readLong();
    }
}
