package com.fyj.serverspringdemo.controller;

import com.fyj.serverspringdemo.service.AsynTaskService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <Description> <br>
 *
 * @author 付永杰<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2019年08月22日 <br>
 * @see com.fyj.serverspringdemo.controller <br>
 * @since V1.0 <br>
 */
@RestController
@RequestMapping("/spring/demo")
public class TimerController {

    @Resource
    private AsynTaskService asynTaskService;

    @GetMapping("/asynTask")
    public void testAsynTask() {
        for (int i = 0; i < 10; i++) {
            // 执行异步任务
            asynTaskService.f1();
            asynTaskService.f2();
        }
    }
}
