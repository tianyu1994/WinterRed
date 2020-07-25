package org.codeforworld.winterredserver.lang;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 接口返回分页通用类
 *
 * @param <T>
 * @author ghy
 * @date 2020-02-09
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Page<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页码
     */
    private int pageNo = 1;

    /**
     * 每页数量
     */
    private int pageSize = 20;

    /**
     * 总数量
     */
    private int totalCount = 0;

    /**
     * 结果集
     */
    private List<T> results = null;

    /**
     * 总页数
     */
    private int totalPages = 0;

    /**
     * 条件参数集
     */
    private Map<String, Object> params = null;

    /**
     * 多字段排序集
     */
    private List<Sort> sorts = null;

    public Page(int pageNo, int pageSize, int totalCount, List<T> results, int totalPages, Map<String, Object> params, List<Sort> sorts) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.results = results;
        this.totalPages = totalPages;
        this.params = params;
        this.sorts = sorts;
    }


    /**
     * 私有构造方法
     */
    private Page() {
    }

    /**
     * 私有构造方法
     * @param params
     */
    private Page(Map<String, Object> params) {
        if (params == null) {
            params = new HashMap<String, Object>();
        }
        results = new ArrayList<>();
        sorts = new ArrayList<>();
        this.params = params;
    }

    /**
     * 私有构造方法
     * @param pageNo
     * @param params
     */
    private Page(int pageNo, Map<String, Object> params) {
        this(params);
        this.pageNo = pageNo;
    }

    /**
     * 私有构造方法
     * @param pageNo
     * @param pageSize
     * @param params
     */
    private Page(int pageNo, int pageSize, Map<String, Object> params) {
        this(pageNo, params);
        this.pageSize = pageSize;
    }

    /**
     * 获取当前页码
     * @return
     */
    public int getPageNo() {
        return pageNo;
    }

    /**
     * 设置当前页码
     * @param pageNo
     * @return
     */
    public Page<T> setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    /**
     * 获取页大小
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置页大小
     * @param pageSize
     * @return
     */
    public Page<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 获取总记录数
     * @return
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 设置总记录数
     * @param totalCount
     * @return
     */
    public Page<T> setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 获取结果集
     * @return
     */
    public List<T> getResults() {
        return results;
    }

    /**
     * 设置结果集
     * @param results
     * @return
     */
    public Page<T> setResults(List<T> results) {
        this.results = results;
        return this;
    }

    /**
     * 获取条件参数集
     * @return
     */
    public Map<String, Object> getParams() {
        return params;
    }

    /**
     * 设置条件参数集
     * @param params
     * @return
     */
    public Page<T> setParams(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotalPages() {
        int pageSize = getPageSize();
        int totalCount = getTotalCount();
        totalPages = totalCount / pageSize;
        if ((totalCount % pageSize) > 0)
            ++totalPages;
        return totalPages;
    }

    /**
     * 设置总页数
     * @param totalPages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 获取排序字段集
     * @return
     */
    public List<Sort> getSorts() {
        return sorts;
    }

    /**
     * 设置排序字段集
     * @param sorts
     * @return
     */
    public Page<T> setSorts(List<Sort> sorts) {
        this.sorts = sorts;
        return this;
    }

    /**
     * 添加排序字段
     *
     * @param field 字段名称
     * @param sort  排序类型
     * @return
     */
    public Page<T> addSort(String field, SortEnum sort) {
        this.sorts.add(new Sort(field, sort));
        return this;
    }

    /**
     * 添加查询参数
     *
     * @param key 参数键
     * @param obj 参数值
     * @return
     */
    public Page<T> put(String key, Object obj) {
        this.params.put(key, obj);
        return this;
    }

    /**
     * 创建分页实例
     * @param <T>
     * @return
     */
    public static <T> Page<T> newInstance() {
        return new Page<T>();
    }

    /**
     * 创建分页实例
     * @param params
     * @param <T>
     * @return
     */
    public static <T> Page<T> newInstance(Map<String, Object> params) {
        return new Page<T>(params);
    }

    /**
     * 创建分页实例
     * @param pageNo
     * @param params
     * @param <T>
     * @return
     */
    public static <T> Page<T> newInstance(int pageNo, Map<String, Object> params) {
        return new Page<T>(pageNo, params);
    }

    /**
     * 创建分页实例
     * @param pageNo
     * @param pageSize
     * @param params
     * @param <T>
     * @return
     */
    public static <T> Page<T> newInstance(int pageNo, int pageSize, Map<String, Object> params) {
        return new Page<T>(pageNo, pageSize, params);
    }

    /**
     * 排序内部类
     */
    public class Sort implements Serializable {
        private static final long serialVersionUID = -9048497004035410678L;

        /**
         * 排序字段
         */
        private String field;

        /**
         * 排序类型
         */
        private SortEnum sort;

        private Sort(String field, SortEnum sort) {
            this.field = field;
            this.sort = sort;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public SortEnum getSort() {
            return sort;
        }

        public void setSort(SortEnum sort) {
            this.sort = sort;
        }

    }

    /**
     * 排序枚举类
     */
    public enum SortEnum {
        ASC("asc"), DESC("desc");
        private String sort = "desc";

        private SortEnum(String sort) {
            this.sort = sort;
        }

        public String getSort() {
            return sort;
        }
    }
}
