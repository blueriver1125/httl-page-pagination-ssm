package com.httl.demo.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Page<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int total = 1;
	private int pageCurrent = 1;
	private int rowSum;
	private int pageRows = 5;
	private int rowsCurrent;
	private int pageFrom = 1;
	private int pageTo = 1;
	private int pageGroupBy = 10;
	private List<T> rows;

	public Page() {
	}

	public Page(Map<String, Object> parameter) {
		if (parameter.containsKey("pageRows")) {
			String pageRows = (String) parameter.get("pageRows");
			if ("".equals(pageRows) == false) {
				setPageRows(Integer.parseInt(pageRows));
			}
		}
		if (parameter.containsKey("pageCurrent")) {
			String pageCurrent = (String) parameter.get("pageCurrent");
			if ("".equals(pageCurrent) == false) {
				setPageCurrent(Integer.parseInt(pageCurrent));
			}
		}
		if (parameter.containsKey("rowSum")) {
			String rowSum = (String) parameter.get("rowSum");
			if ("".equals(rowSum) == false) {
				setRowSum(Integer.parseInt(rowSum));
			}
		}
		if (parameter.containsKey("pageGroupBy")) {
			String pageGroupBy = (String) parameter.get("pageGroupBy");
			if ("".equals(pageGroupBy) == false) {
				setPageGroupBy(Integer.parseInt("pageGroupBy"));
			}
		}
	}

	public void setRowSum(int rowSum) {
		this.rowSum = rowSum;
		if (rowSum != 0) {
			if (pageRows == 0) {
				total = 1;
			} else {
				total = (rowSum % pageRows) == 0 ? rowSum / pageRows : rowSum
						/ pageRows + 1;
				if (pageCurrent < 1) {
					pageCurrent = 1;
				} else if (pageCurrent > total) {
					pageCurrent = total;
				}
			}
		} else {
			total = 1;
			pageCurrent = 1;
		}
		if (pageRows == 0) {
			rowsCurrent = rowSum;
		} else {
			if (pageCurrent >= total) {
				rowsCurrent = (rowSum % pageRows) == 0 ? pageRows : rowSum
						% pageRows;
			} else {
				rowsCurrent = pageRows;
			}
		}
		if (pageCurrent % pageGroupBy == 0) {
			pageFrom = pageCurrent - pageGroupBy + 1;
		} else {
			pageFrom = pageCurrent - pageCurrent % pageGroupBy + 1;
		}
		if (pageFrom < 1) {
			pageFrom = 1;
		}
		if (pageFrom + pageGroupBy - 1 > total) {
			pageTo = total;
		} else {
			pageTo = pageFrom + pageGroupBy - 1;
		}
		System.out.println("==============>"+rowsCurrent);
	}

	public void setPageRows(int pageRows) {
		if (pageRows < 0) {
			return;
		}
		this.pageRows = pageRows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public int getRowsCurrent() {
		return rowsCurrent;
	}

	public void setRowsCurrent(int rowsCurrent) {
		this.rowsCurrent = rowsCurrent;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getRowSum() {
		return rowSum;
	}

	public int getPageRows() {
		return pageRows;
	}

	public int getPageFrom() {
		return pageFrom;
	}

	public void setPageFrom(int pageFrom) {
		this.pageFrom = pageFrom;
	}

	public int getPageTo() {
		return pageTo;
	}

	public void setPageTo(int pageTo) {
		this.pageTo = pageTo;
	}

	public int getPageGroupBy() {
		return pageGroupBy;
	}

	public void setPageGroupBy(int pageGroupBy) {
		this.pageGroupBy = pageGroupBy;
	}
}
