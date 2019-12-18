package com.oray.arouter_annotation.model;

import javax.lang.model.element.Element;

/**
 * Created by ZY on 2019/12/16
 * DESC: class RouterBean
 * 路由路径Path的最终实体封装类
 * 比如：app分组中的MainActivity对象，这个对象有更多的属性
 */
public class RouterBean {

    public enum Type {
        ACTIVITY,
        CALL
    }

    private Type type;
    //类节点
    private Element element;
    //注解使用的类对象
    private Class<?> clazz;
    private String path;
    private String group;

    private RouterBean(Builder builder) {
        this.element = builder.element;
        this.path = builder.path;
        this.group = builder.group;
    }

    private RouterBean(Type type, Class<?> clazz, String path, String group) {
        this.type = type;
        this.clazz = clazz;
        this.path = path;
        this.group = group;
    }

    public static class Builder {
        private Element element;
        private String path;
        private String group;

        public Builder setElement(Element element) {
            this.element = element;
            return this;
        }

        public Builder setPath(String path) {
            this.path = path;
            return this;
        }

        public Builder setGroup(String group) {
            this.group = group;
            return this;
        }

        public RouterBean build() {
            if (path == null || path.length() == 0) {
                throw new IllegalArgumentException("path必填项为空，如：/app/MainActivity");
            }
            return new RouterBean(this);
        }
    }

    @Override
    public String toString() {
        return "RouterBean{" +
                "path='" + path + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
