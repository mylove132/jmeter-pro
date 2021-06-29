package com.lzh.jdh.qa.business.utils;

import com.alibaba.fastjson.JSONObject;
import com.lzh.jdh.qa.business.domain.rsponse.FileTree;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuildTree {
    public static List<FileTree> buildTree(List<FileTree> zoneList) {
        List<FileTree> result = new ArrayList<>();
        for (FileTree zone : zoneList) {
            if (zone.getParentId() == 0) {
                result.add(zone);
                setChildren(zoneList, zone);
            }

        }

        return result;

    }

    public static List<FileTree> hello(List<FileTree> list){

        Map<Integer, FileTree> mapTmp = new HashMap<>();
        for (FileTree current : list) {
            mapTmp.put(current.getId(), current);
        }

        List<FileTree> finalList = new ArrayList<>();

        mapTmp.forEach((k, v) -> {
            if(v.getParentId() == null || v.getParentId() == 0) {
                finalList.add(v);
            } else {
                mapTmp.get(v.getParentId()).getChildren().add(v);
            }
        });
        return finalList;
    }

    public static void setChildren(List<FileTree> list, FileTree parent) {
        for (FileTree zone: list) {
            if(parent.getId() == zone.getParentId()){
                parent.getChildren().add(zone);
            }
        }
        if (parent.getChildren().isEmpty()) {
            return;
        }
        for (FileTree zone: parent.getChildren()) {
            setChildren(list, zone);
        }
    }
    public static List<FileTree> buildTree3(List<FileTree> zoneList) {
        Map<Integer, List<FileTree>> zoneByParentIdMap = new HashMap<>();
        zoneList.forEach(zone -> {
            List<FileTree> children = zoneByParentIdMap.getOrDefault(zone.getParentId(), new ArrayList<>());
            children.add(zone);
            zoneByParentIdMap.put(zone.getParentId(), children);
        });

        zoneList.forEach(zone -> {
            List<FileTree> children = zoneByParentIdMap.getOrDefault(zone.getParentId(), new ArrayList<>());
            children.add(zone);
            zoneByParentIdMap.put(zone.getParentId(), children);
        });
        return zoneList.stream()
                .filter(v -> v.getParentId() == 0)
                .collect(Collectors.toList());

    }

//    public static List<FileTree> buildTree3(List<FileTree> zoneList) {
//        Map<Integer, List<FileTree>> zoneByParentIdMap = zoneList.stream().collect(Collectors.groupingBy(FileTree::getParentId));
//        zoneList.forEach(zone -> zone.getChildren() = zoneByParentIdMap.get(zone.getId()));
//        return zoneList.stream().filter(v -> v.getParentId() == 0).collect(Collectors.toList());
//    }

    public static void main(String[] args) throws FileNotFoundException {
        WorkFileUtils.readFile("D:\\liuzhanhui\\pc工作台\\UI-moonbox", 0);
        List<FileTree> fileTrees = hello(WorkFileUtils.list);
        System.out.println(JSONObject.toJSONString(fileTrees));
        System.out.println(fileTrees);
    }
}
