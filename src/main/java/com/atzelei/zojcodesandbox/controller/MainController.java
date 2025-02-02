package com.atzelei.zojcodesandbox.controller;

import com.atzelei.zojcodesandbox.JavaDockerCodeSandbox;
import com.atzelei.zojcodesandbox.JavaDockerCodeSandboxOld;
import com.atzelei.zojcodesandbox.JavaNativeCodeSandbox;
import com.atzelei.zojcodesandbox.model.ExecuteCodeRequest;
import com.atzelei.zojcodesandbox.model.ExecuteCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController("/")
public class MainController
{

    // 定义健全请求头和密钥
    private static final String AUTH_REQUEST_HEADER = "auth";

    private static final String AUTH_REQUEST_SECRET = "secretKey";

    @Resource
    private JavaNativeCodeSandbox javaNativeCodeSandbox;

    @Resource
    private JavaDockerCodeSandbox javaDockerCodeSandbox;

    @GetMapping("/hello")
    public String getL()
    {
        return "ok";
    }

    /**
     * 执行代码
     *
     * @param executeCodeRequest
     * @return
     */
    @PostMapping("/executeCode")
    public ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest, HttpServletRequest request, HttpServletResponse response)
    {
        String authHEeader = request.getHeader(AUTH_REQUEST_HEADER);
        if (!AUTH_REQUEST_SECRET.equals(authHEeader)) //这样写是因为authHEeader可能为空
        {
            response.setStatus(403);
            return null;
        }
        if (executeCodeRequest == null)
        {
            throw new RuntimeException("请求参数为空");
        }
        ExecuteCodeResponse executeCodeResponse = javaDockerCodeSandbox.executeCode(executeCodeRequest);
        return executeCodeResponse;
    }
}
