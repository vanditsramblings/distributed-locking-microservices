package com.rambler.distributed.lock;

import redis.clients.jedis.Jedis;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Abstract Multi instance Distributed lock implementation
 *
 * @author rambler
 */
public abstract class DistributedLock {

    public static final String SEP = "_";
    public static final String LOCK_PREFIX = "DISTRIBUTED_LOCK";

    String lockName;

    Executor lockAcquirer;
    LockAcquirerTask lockAcquirerTask;

    Jedis jedis;

    public DistributedLock(String name) {
        lockName = LOCK_PREFIX + SEP + name;
        lockAcquirer = Executors.newSingleThreadScheduledExecutor();
        jedis = redisClient();
        lockAcquirerTask = new LockAcquirerTask(jedis);

    }

    /**
     * Provides with jedis client instance.
     * This instance will be leveraged for persisting and emulating the distributed lock
     *
     * @return Jedis
     */
    protected abstract Jedis redisClient();


    /**
     * A scheduled task responsible to acquire the lock
     */
    class LockAcquirerTask implements Runnable {

        private final Jedis client;

        public LockAcquirerTask(Jedis client) {
            this.client = client;
        }

        @Override
        public void run() {

        }
    }


}
