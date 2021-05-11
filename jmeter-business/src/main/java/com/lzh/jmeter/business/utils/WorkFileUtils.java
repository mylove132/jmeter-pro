package com.lzh.jmeter.business.utils;

import com.lzh.jmeter.business.domain.rsponse.FileTree;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkFileUtils {

    public static List<FileTree> list = new ArrayList<>();//用来存放数据
    private static Integer id = 0;

    public static void readFile (String filepath,int parentid) throws FileNotFoundException {
        File file = new File(filepath);
        if(!file.exists()){
            throw new FileNotFoundException("文件不存在");
        }
        //2.是文件该怎么执行
        if(file.isFile()){
            String name = file.getName();
            String path = file.getAbsolutePath();
            FileTree tree = new FileTree(id++,name,path,parentid);
            list.add(tree);
            return ;
        }
        //3.获取文件夹路径下面的所有文件递归调用；
        if(file.isDirectory()){
            String name = file.getName();
            String path = file.getAbsolutePath();
            FileTree tree = new FileTree(id++,name,path,parentid);
            list.add(tree);
            String[] list = file.list();
            String parent = file.getParent();
            for (int i = 0;i<list.length;i++){
                String s = list[i];
                String newFilePath = path+"\\"+s;//根据当前文件夹，拼接其下文文件形成新的路径
                readFile(newFilePath,tree.getId());
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile("D:\\liuzhanhui\\pc工作台\\UI-moonbox", 0);
        for (FileTree fileTree:list) {
            System.out.println(fileTree.toString());
        }
    }
}
