package com.java.domain;

import java.util.List;

/**
 * Created by liuf on 2017/3/5.
 */
public class City {
    private Integer id;
    private String name;
    private Integer parentId;
    private List<City> childs;
    private List<Integer> childIds;

    public List<Integer> getChildIds() {
        return childIds;
    }

    public void setChildIds(List<Integer> childIds) {
        this.childIds = childIds;
    }

    public List<City> getChilds() {
        return childs;
    }

    public void setChilds(List<City> childs) {
        this.childs = childs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", childs=" + childs +
                ", childIds=" + childIds +
                '}';
    }
}
