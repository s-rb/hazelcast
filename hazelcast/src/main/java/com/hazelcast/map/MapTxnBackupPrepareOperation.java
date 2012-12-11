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

package com.hazelcast.map;

import com.hazelcast.spi.AbstractOperation;
import com.hazelcast.spi.ResponseHandler;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MapTxnBackupPrepareOperation extends AbstractOperation {
    TransactionLog txnLog;

    public MapTxnBackupPrepareOperation(TransactionLog txnLog) {
        this.txnLog = txnLog;
    }

    public MapTxnBackupPrepareOperation() {
    }

    public void run() {
        int partitionId = getPartitionId();
        MapService mapService = (MapService) getService();
        System.out.println(getNodeService().getThisAddress() + " backupPrepare " + txnLog.txnId);
        mapService.getPartitionContainer(partitionId).putTransactionLog(txnLog.txnId, txnLog);
        ResponseHandler responseHandler = getResponseHandler();
        responseHandler.sendResponse(null);
    }

    @Override
    public void writeInternal(DataOutput out) throws IOException {
        super.writeData(out);
        txnLog.writeData(out);
    }

    @Override
    public void readInternal(DataInput in) throws IOException {
        super.readData(in);
        txnLog = new TransactionLog();
        txnLog.readData(in);
    }
}
