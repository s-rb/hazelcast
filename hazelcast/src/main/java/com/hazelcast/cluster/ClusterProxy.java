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

package com.hazelcast.cluster;

import com.hazelcast.core.Cluster;
import com.hazelcast.core.Member;
import com.hazelcast.core.MembershipListener;

import java.util.Set;

/**
 * @mdogan 8/26/12
 */
public class ClusterProxy implements Cluster {

    private final ClusterService clusterService;

    public ClusterProxy(final ClusterService clusterService) {this.clusterService = clusterService;}

    public Member getLocalMember() {
        return clusterService.getLocalMember();
    }

    public Set<Member> getMembers() {
        return clusterService.getMembers();
    }

    public long getClusterTime() {
        return clusterService.getClusterTime();
    }

    public void addMembershipListener(MembershipListener listener) {
        clusterService.addMembershipListener(listener);
    }

    public void removeMembershipListener(MembershipListener listener) {
        clusterService.removeMembershipListener(listener);
    }
}
