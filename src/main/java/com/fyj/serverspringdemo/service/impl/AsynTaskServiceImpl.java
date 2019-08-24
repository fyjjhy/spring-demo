package com.fyj.serverspringdemo.service.impl;

import com.fyj.serverspringdemo.service.AsynTaskService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
 * <Description> <br>
 *
 * @author 付永杰<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2019年08月23日 <br>
 * @see com.fyj.serverspringdemo.service.impl <br>
 * @since V1.0 <br>
 */
@Service // 注解的方式把AsynTaskServiceImpl交给Spring来管理
public class AsynTaskServiceImpl implements AsynTaskService {

    @Override
    @Async // 标注为异步任务，在执行此方法的时候，会单独开启线程来执行
    public void f1() {
        System.out.println("f1: " + Thread.currentThread().getName() + "  " + UUID.randomUUID().toString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Async
    public void f2() {
        System.out.println("f2: " + Thread.currentThread().getName() + "  " + UUID.randomUUID().toString());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
