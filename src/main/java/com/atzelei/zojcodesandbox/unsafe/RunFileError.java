package com.atzelei.zojcodesandbox.unsafe;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * 运行其他程序(运行危险木马)
 */
public class RunFileError
{
    public static void main(String[] args) throws IOException {
        //获取用户资源文件目录
        String userDir = System.getProperty("user.dir");

        String filePath = "D:\\Javaideaporject\\zoj-code-sandbox\\src\\main\\resources\\asdf.bat";
        Process runProcess = Runtime.getRuntime().exec(filePath);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(runProcess.getInputStream()));
        // 逐行读取
        String compileOutputLine;
        while ((compileOutputLine = bufferedReader.readLine()) != null) {
            System.out.println("compileOutputLine = " + compileOutputLine);
        }



        System.out.println("执行程序成功");

    }
}
