package com.choxsu.demo.d3;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @author choxsu
 * @date 2018/11/27
 */
public class RedisTest {
    private static final String ADDR = "127.0.0.1";

    private static final int PORT = 6379;

    private static final String PASSWORD = "choxsu-redis-pwd";

    private static JedisPool jedis = new JedisPool(new GenericObjectPoolConfig(), ADDR, PORT, 0, PASSWORD);

    private static RedisSub sub = new RedisSub();

    public static void init() {

        new Thread(() -> {
            jedis.getResource().subscribe(sub, "__keyevent@0__:expired");//__keyevent@0__:expired是redis关于事件监听的东西
        }).start();

    }

    /**
     * 在redis.conf中，加入一条配置
     * notify-keyspace-events Ex
     *
     * redis的pub/sub机制存在一个硬伤，官网内容如下:
     * 原:Because Redis Pub/Sub is fire and forget currently there is no way to use this feature if your application demands
     * reliable notification of events, that is, if your Pub/Sub client disconnects, and reconnects later, all the events
     * delivered during the time the client was disconnected are lost.
     *
     * 翻: Redis的发布/订阅目前是即发即弃(fire and forget)模式的，因此无法实现事件的可靠通知。也就是说，如果发布/订阅的客户端断链之后又重连，
     * 则在客户端断链期间的所有事件都丢失了。
     * 因此，不是太推荐。当然，如果你对可靠性要求不高，可以使用。
     * @param args
     */
    public static void main(String[] args) {

        init();

        for (int i = 0; i < 100; i++) {

            String orderId = "OID000000" + i;
            System.out.println(System.currentTimeMillis() + "ms:" + orderId + "订单生成");
            jedis.getResource().setex(orderId, 3, orderId);
        }

    }

    static class RedisSub extends JedisPubSub {

        @Override

        public void onMessage(String channel, String message) {
            System.out.println(System.currentTimeMillis() + "ms:" + message + "订单取消");
        }

        @Override
        public void onSubscribe(String channel, int subscribedChannels) {    //订阅了频道会调用
            System.out.println(String.format("订阅RISIS频道成功, channel %s, subscribedChannels %d",
                    channel, subscribedChannels));
        }
        @Override
        public void onUnsubscribe(String channel, int subscribedChannels) {   //取消订阅 会调用
            System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                    channel, subscribedChannels));

        }

    }

}
