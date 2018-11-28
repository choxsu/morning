package com.syc.service.scheduled;

import com.syc.model.entity.mybatis.entity.Orders;
import com.syc.model.result.Result;
import com.syc.service.dao.OrdersDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author choxsu
 * @date 2018/11/27
 */
@Slf4j
@Component
public class OrderTimeoutScheduled {

    @Resource
    private OrdersDao ordersDao;

    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(8);


    public Result timeoutAction(List<Orders> orders) {

        //下单成功后就将订单超时时间放入队列中，当支付成功后传入订单id，调用取消订单超时

        //当后台系统重启时，要去遍历一次数据库，并将已经超时的订单处理，并将未付款订单加入队列中去

        //遍历未付款的订单记录list，然后将时间作为
        int c = 0;

        if (orders == null || orders.size() == 0) {
            return Result.ok().setMsg("处理订单数量为null,或者数量为零");
        }
        //获取数据库配置的超时时间(单位：分钟) 转换为秒  乘以60
        int configTime = 15 * 60;
        //当前时间
        int nowTime = (int) (System.currentTimeMillis() / 1000L);
        Runnable runnable;
        for (Orders order : orders) {
            //下单时间
            Integer created = order.getCreated();
            if (created == null || created == 0) {
                log.error("\t\t订单号：{}, 创建时间异常，请查证", order.getOrderSn());
                continue;
            }
            //计数时间
            int time = nowTime - created;
            //算出剩余订单超时时间秒数
            int surplusTime = configTime - time;
            //如果间隔大于15分钟，需要马上处理此单数据，将time设置为0
            runnable = new OrderTimeOutRunnable(order, surplusTime);
            executor.schedule(runnable, time >= configTime ? 0 : surplusTime, TimeUnit.SECONDS);
            c++;
        }
        System.out.println("系统启动处理超时订单队列装载完毕,c:" + c);

        return Result.ok().setMsg("订单后台处理中...");
    }

    public class OrderTimeOutRunnable implements Runnable {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        private Orders orders;
        private int surplusTime;

        OrderTimeOutRunnable(Orders orders, int surplusTime) {
            this.orders = orders;
            this.surplusTime = surplusTime;
        }

        @Override
        public void run() {
            //需要先判断订单是否已经支付，并且订单必须是未付款，并且时间已经大于超时时间了。

            //这里就需要去更改订单的状态去取消,

            System.out.println("开始处理订单号：" + orders.getOrderSn() + ", 线程id:" + Thread.currentThread().getId());
            System.out.println("Work start, thread id:" + Thread.currentThread().getId() + " " + sdf.format(new Date()));

            ordersDao.updateStatusById(7, orders.getId());
            //这里需要处理订单日志记录

            System.out.println("订单：" + orders.getOrderSn() + "处理完成");
        }
    }


}
