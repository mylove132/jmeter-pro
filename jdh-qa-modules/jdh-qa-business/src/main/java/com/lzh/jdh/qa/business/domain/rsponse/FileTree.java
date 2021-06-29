package com.lzh.jdh.qa.business.domain.rsponse;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class FileTree implements Serializable {

     Integer id;
     String label;//文件夹或者文件名称
     String path;//全路径,或则部分路径,自己决定
     Integer parentId;//父节点id
     List<FileTree> children = new ArrayList<>();

    public FileTree(Integer id, String label, String path, Integer parentId) {
        this.id = id;
        this.label = label;
        this.path = path;
        this.parentId = parentId;
    }
}
