package com.hhoa.vblog.common.api;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * 分页数据封装类.
 *
 * @author hhoa
 */
@Schema(description = "分页数据")
public class CommonPage<T> {
    /**
     * 当前页码.
     */
    @Schema(description = "页数")
    private Integer pageNum;
    /**
     * 每页数量.
     */
    @Schema(description = "单页大小")
    private Integer pageSize;
    /**
     * 总页数.
     */
    @Schema(description = "总页数")
    private Integer totalPage;
    /**
     * 总条数.
     */
    @Schema(description = "总条数")
    private Long total;
    /**
     * 分页数据.
     */
    @Schema(description = "分页数据")
    private List<T> list;

    /**
     * 将PageHelper分页后的list转为分页信息.
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<>();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getList());
        return result;
    }

    /**
     * 将SpringData分页后的list转为分页信息.
     */
    public static <T> CommonPage<T> restPage(Page<T> pageInfo) {
        CommonPage<T> result = new CommonPage<>();
        result.setTotalPage(pageInfo.getPages());
        result.setPageNum(pageInfo.getPageNum());
        result.setPageSize(pageInfo.getPageSize());
        result.setTotal(pageInfo.getTotal());
        result.setList(pageInfo.getResult());
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
