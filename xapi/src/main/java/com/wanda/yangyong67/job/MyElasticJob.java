package com.wanda.yangyong67.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * Created by yangyong on 2017/8/29.
 */
public class MyElasticJob implements SimpleJob {

    public void execute(ShardingContext shardingContext) {
        int sharding = shardingContext.getShardingItem();
        System.out.println(sharding);
    }
}
