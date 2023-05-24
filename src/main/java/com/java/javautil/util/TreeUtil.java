package com.java.javautil.util;

import com.java.javautil.entity.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TreeUtil {

    /**
     * 不可被构造
     */
    public TreeUtil() {
        throw new IllegalStateException("The Utility class");
    }

    /**
     * List转parentId-children层级结构，通过
     * parentId为null时代表根节点
     * @param list 类
     * @param idGetter id获取函数接口
     * @param parentIdGetter 父id获取函数接口
     * @return 多层级List
     * @param <T> 泛型
     */
    public static <T> List<TreeNode<T>> buildTree(List<T> list, Function<T, Integer> idGetter, Function<T, Integer> parentIdGetter){
        List<TreeNode<T>> tree = new ArrayList<>();
        Map<Integer, TreeNode<T>> nodeMap = new HashMap<>();
        for (T item: list){
            Integer id = idGetter.apply(item);
            Integer parentId = parentIdGetter.apply(item);
            TreeNode<T> node = new TreeNode<T>() {
                @Override
                public Integer getParentId() {
                    return parentId;
                }

                @Override
                public Integer getId() {
                    return id;
                }
            };
            if (parentId == null){ // 添加根节点
                tree.add(node);
            }else {
                nodeMap.put(id, node);
            }
        }
        for (TreeNode<T> root: tree){
            Integer rootId = root.getId();
            for (Map.Entry<Integer, TreeNode<T>> entry: nodeMap.entrySet()){
                TreeNode<T> node = entry.getValue();
                if (rootId.equals(node.getParentId())){
                    node.addChild(node);
                }else {
                    TreeNode<T> parentNode = nodeMap.get(node.getParentId());
                    parentNode.addChild(node);
                }
            }
        }
        return tree;
    }
}
