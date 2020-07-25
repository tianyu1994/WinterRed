package org.codeforworld.winterredserver.queryParam;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询对象
 * @author ghy
 * @date 2020-02-09
 * @param <T>
 */
@SuppressWarnings("serial")
@Setter
@Getter
public class QueryParamVo<T> implements Serializable {
	/**
	 * 查询的对象
	 */
	private T paramobj;
	
	/**
	 * 模糊查询的对象
	 */
	private T likeobj;
	
	/**
	 * 其他参数Map集
	 */
	private Map<String, Object> otherParam = new HashMap<String, Object>();

	/**
	 * 当前页码
	 */
	private int pageNo = 1;

	/**
	 * 每页数量
	 */
	private int pageSize = 20;

	/**
	 * 起始页面
	 */
	private int pageBegin = 0;

	/**
	 * 总数量
	 */
	private int totalCount = 0;

	/**
	 * 排序字段集
	 */
	private List<Sort> sorts = new ArrayList<>();

	/**
	 * 构造方法
	 */
	public QueryParamVo() {
		super();
	}

	/**
	 * 构造方法
	 * @param paramobj
	 * @param pageNo
	 * @param pageSize
	 */
	public QueryParamVo(T paramobj, int pageNo, int pageSize) {
		super();
		this.paramobj = paramobj;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	/**
	 * 获取起始页码
	 * @return
	 */
	public int getPageBegin() {
		if (this.pageBegin != 0) {
			return this.pageBegin;
		} else {
			return (pageNo - 1) * pageSize;
		}
	}

	/**
	 * 排序类
	 */
	@Setter
	@Getter
	public class Sort implements Serializable {
		/**
		 * 排序字段
		 */
		private String field;

		/**
		 * 排序类型
		 */
		private SortEnum sort;

		public Sort(String field, SortEnum sort) {
			this.field = field;
			this.sort = sort;
		}

		/**
		 * 构造方法
		 * @param field
		 */
		public Sort(String field) {
			this.field = field;
			this.sort = SortEnum.DESC;
		}

	}

	/**
	 * 排序枚举类
	 */
	@Getter
	public enum SortEnum {
		ASC("asc"), DESC("desc");

		private String sort = "desc";

		private SortEnum(String sort) {
			this.sort = sort;
		}
	}

	/**
	 * 添加排序字段
	 * 
	 * @param field 字段名称
	 * @param sort 排序类型
	 * @return
	 */
	public QueryParamVo<T> addSort(String field, SortEnum sort) {
		this.sorts.add(new Sort(field, sort));
		return this;
	}

	/**
	 * 添加排序字段
	 * 
	 * @param field 字段名称
	 * @param sort 排序类型
	 * @return
	 */
	public QueryParamVo<T> addSort(int index, String field, SortEnum sort) {
		this.sorts.add(index, new Sort(field, sort));
		return this;
	}

	/**
	 * 添加查询参数
	 * @param key
	 * @param value
	 */
	public void addParam(String key, Object value) {
		otherParam.put(key, value);
	}

}
