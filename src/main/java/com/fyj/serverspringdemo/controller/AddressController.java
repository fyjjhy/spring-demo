package com.fyj.serverspringdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <Description> <br>
 *
 * @author 付永杰<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2019年08月15日 <br>
 * @see com.fyj.serverspringdemo.controller <br>
 * @since V1.0 <br>
 */
@RestController
@RequestMapping("/spring-demo")
public class AddressController {

    @GetMapping("/addr")
    public void getAddress() {
        HttpServletRequest request = getRequest();
        String path = request.getSession().getServletContext().getRealPath("/");
        System.out.println(path);
    }

    protected HttpServletRequest getRequest() {
        RequestAttributes requestAttr = RequestContextHolder.getRequestAttributes();
        return ((ServletRequestAttributes)requestAttr).getRequest();
    }
}
