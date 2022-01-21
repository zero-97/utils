package com.southgis.ibasem.business.bgyf.service;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 文件路径转树状结构
 */
public class PathToTreeUtil {

    /**
     * 文件路径转树状结构
     * @param arr 文件路径列表
     * @return TreeNode 树状结构
     */
    public TreeNode toTree(List<String> arr) {
        // 自定义根目录
        TreeNode forest = new TreeNode("root", "root");
        TreeNode current = forest;

        for(String path : arr) {
            // 文件夹路径
            String[] dir = path.split("/");

            TreeNode root = forest;

            for(int i=0; i<dir.length; i++) {
                String data = dir[i];
                // 排除路径中的空字符串
                if(StringUtils.isBlank(data)) {
                    continue;
                }
                current = current.child(data);
            }

            current = root;
        }

        // 如果子目录下只有一个文件夹，进行合并
        current.merge();

        // 设置绝对路径
        current.absolutePath("");

        return current;
    }

    static class TreeNode {
        // 当前文件夹名称
        private String data;
        // 相对路径
        private String view;
        // 绝对路径
        private String path;
        private Set<TreeNode> children = new LinkedHashSet<>();

        public TreeNode(String data, String view) {
            this.data = data;
            this.view = view;
        }

        TreeNode child(String data) {
            for (TreeNode child : children) {
                if (child.data.equals(data)) {
                    return child;
                }
            }
            return child(new TreeNode(data, data));
        }

        TreeNode child(TreeNode child) {
            children.add(child);
            return child;
        }

        // 如果子目录下只有一个文件夹，进行合并
        public void merge() {
            if(children.size() == 0) {
                return;
            }
            while(children.size() < 2 && children.iterator().hasNext()) {
                TreeNode childNode = children.iterator().next();
                view = view + "/" + childNode.view;
                data = childNode.data;
                children = childNode.children;

            }

            Iterator<TreeNode> i = children.iterator();
            while (i.hasNext()) {
                i.next().merge();
            }
        }

        // 设置绝对路径
        public void absolutePath(String parentPath) {
            path = parentPath+"/"+view;

            if(children.size() == 0) {
                return;
            }

            Iterator<TreeNode> i = children.iterator();
            while (i.hasNext()) {
                i.next().absolutePath(path);
            }
        }

        public Set<TreeNode> getChildren() {
            return children;
        }

        public void setChildren(Set<TreeNode> children) {
            this.children = children;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
