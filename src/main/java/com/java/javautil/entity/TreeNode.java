package com.java.javautil.entity;

import lombok.Data;

import java.util.List;

/**
 * parentId-children List节点
 * 用于构造List的层级结构
 * @param <T> 泛型
 */
@Data
public abstract class TreeNode<T>{

    private T data;

    private List<TreeNode<T>> children;

    public TreeNode() {
    }

    public void addChild(TreeNode<T> child) {
        children.add(child);
    }

    public abstract Integer getParentId();

    public abstract Integer getId();
}
