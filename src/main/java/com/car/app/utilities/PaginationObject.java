package com.car.app.utilities;

import java.util.List;

public class PaginationObject<T> {

	public int totalElmenets;
	public int size;
	public int page;
	public List<T> data;
	public String sort;
	public String order;
	public PaginationObject(int totalElmenets, int size, int page, List<T> data, String sort, String order) {
		super();
		this.totalElmenets = totalElmenets;
		this.size = size;
		this.page = page;
		this.data = data;
		this.sort = sort;
		this.order = order;
	}
}
