package com.fyj.serverspringdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
@RequestMapping("/spring-demo")
public class EvalController {

    private int val = -1;

    public int getValue() {
        return val;
    }

    public void setValue(int val) {
        this.val = val;
    }

    @GetMapping
    public void getEval() throws ScriptException {
        String scriptFileName = "D:\\demo\\server-spring-demo\\src\\main\\java\\com\\fyj\\serverspringdemo\\js\\helloWorld.js";
        Path scriptPath = Paths.get(scriptFileName);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        try {
            Reader reader = Files.newBufferedReader(scriptPath);
            engine.eval(reader);
        } catch (IOException | ScriptException e) {
            e.printStackTrace();
        }

        EvalController eval = new EvalController();
        engine.put("result", eval);
        String script = "var v = 3 + 4; result.setValue(v);";
        engine.eval(script);
        int resultValue = eval.getValue();
        System.out.println("resultValue=" + resultValue);
    }
}
