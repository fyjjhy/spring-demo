package com.fyj.serverspringdemo.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * <Description> <br>
 *
 * @author 付永杰<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2019年08月09日 <br>
 * @see com.fyj.serverspringdemo.controller <br>
 * @since V1.0 <br>
 */
@RestController
@RequestMapping("/spring/demo")
public class UrlController {

    @GetMapping("/uri")
    public void getUri() {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            String uri = request.getRequestURI();
            StringBuffer url = request.getRequestURL();
            System.out.println("uri" + uri);
            System.out.println("url" + url.toString());
        }
    }

    @GetMapping("/match/uri")
    public void getMatchUri() {
        AntPathMatcher matcher = new AntPathMatcher();
        boolean flag = matcher.match("/spring/demo/**", "/spring/demo/match/uri");
        System.out.println("flag=" + flag);
    }

    @GetMapping("/eval/uri")
    public void getEvalUri() throws ScriptException {
        HttpServletRequest request = getHttpServletRequest();
        if (request != null) {
            String uri = request.getRequestURI();
            System.out.println("uri:" + uri);
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("JavaScript");
            AntPathMatcher matcher = new AntPathMatcher();
            engine.put("matcher", matcher);
            engine.put("uri", uri);
            String script = "matcher.match('/spring/demo/match/uri/**', uri)";
            boolean result = (boolean) engine.eval(script);
            System.out.println("eval:" + result);
        }
    }

    @GetMapping("/eval/method")
    public void getEvalMethod() throws ScriptException {
        String method = "com.hbasesoft.vcc.sgp.ability.integration.server.controller.UploadController<queryFddContractMessage>(java.lang.String)";
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        engine.put("method", method);
        List<String> nameList = new ArrayList<>();
        nameList.add("com.hbasesoft.vcc.sgp.ability.integration.server.controller.UploadController");
        nameList.add("com.hbasesoft.vcc.sgp.ability.integration.server.controller.LoadController");
        String script = "method.indexOf('com.hbasesoft.vcc.sgp.ability.integration.server.controller.UploadController') != -1";
        boolean result = (boolean) engine.eval(script);
        System.out.println("eval:" + result);
    }

    @GetMapping("/eval/params")
    public void getEvalParams() throws ScriptException {
        String params = "[{\\\"contractCode\\\":\\\"prod01\\\",\\\"customerCode\\\":\\\"28D03CBAB37EDB5B72931756395E2565\\\",\\\"docTitle\\\":\\\"\\u82CF\\u5DDE\\u6E2F\\u534E\\u7528\\u6C14\\u5408\\u540C\\\",\\\"parameterMap\\\":{\\\"companySignDay\\\":\\\"06\\\",\\\"companySignMonth\\\":\\\"06\\\",\\\"companySignYear\\\":\\\"2019\\\",\\\"personSignDay\\\":\\\"06\\\",\\\"personSignMonth\\\":\\\"06\\\",\\\"personSignName\\\":\\\"\\u5F90\\u5468\\\",\\\"personSignYear\\\":\\\"2019\\\"},\\\"returnUrl\\\":\\\"http://www.baidu.com\\\",\\\"state\\\":\\\"100ccssss\\\",\\\"templateCode\\\":\\\"HTMB190605000009\\\"}]";
        params = StringEscapeUtils.unescapeJava(params);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        engine.put("params", params);
        String script = "params.indexOf('\"contractCode\":\"prod01\"') != -1";
        boolean result = (boolean) engine.eval(script);
        System.out.println("eval:" + result);
    }

    @GetMapping("/json/params")
    public void getJsonParams() throws ScriptException {
        // String params = "[{\\\"contractCode\\\":\\\"prod01\\\",\\\"customerCode\\\":\\\"28D03CBAB37EDB5B72931756395E2565\\\",\\\"docTitle\\\":\\\"\\u82CF\\u5DDE\\u6E2F\\u534E\\u7528\\u6C14\\u5408\\u540C\\\",\\\"parameterMap\\\":{\\\"companySignDay\\\":\\\"06\\\",\\\"companySignMonth\\\":\\\"06\\\",\\\"companySignYear\\\":\\\"2019\\\",\\\"personSignDay\\\":\\\"06\\\",\\\"personSignMonth\\\":\\\"06\\\",\\\"personSignName\\\":\\\"\\u5F90\\u5468\\\",\\\"personSignYear\\\":\\\"2019\\\"},\\\"returnUrl\\\":\\\"http://www.baidu.com\\\",\\\"state\\\":\\\"100ccssss\\\",\\\"templateCode\\\":\\\"HTMB190605000009\\\"}]";
        String params = "[]";
        params = StringEscapeUtils.unescapeJava(params);
        System.out.println("params:" + params);
        JSONArray array = JSONArray.fromObject(params);
        if (array.size() > 0) {
            for (int i = 0, len = array.size(); i < len; i++) {
                JSONObject object = array.getJSONObject(i);
                System.out.println(object.get("contractCode") + "=");
            }
        }
    }

    private HttpServletRequest getHttpServletRequest() {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes != null) {
            return servletRequestAttributes.getRequest();
        }
        return null;
    }
}
