package com.jonest.adapters.impl;

import java.util.List;
import java.util.Map;

import com.jonest.adapters.ObjectAdapter;
import com.jonest.paging.PaginationHandler;

public class PagingAdapter implements ObjectAdapter<Object, Object> {

	private final PaginationHandler pagingHandler;

	public PagingAdapter(PaginationHandler pagingHandler) {
		this.pagingHandler = pagingHandler;
	}

	// TODO incorporar de modo dinámico el pageNumber
	private int pageNumber = 0;

	@Override
	public Object adapt(Object sourceObject) {

		Object adapted = sourceObject;

		if (sourceObject instanceof Map) {
			@SuppressWarnings("unchecked")
			Map<String, ?> map = (Map<String, ?>) sourceObject;
			adapted = pagingHandler.getPagedMap(map, pageNumber);
		} else if (sourceObject instanceof List) {
			List<?> list = (List<?>) sourceObject;
			adapted = pagingHandler.getPagedList(list, pageNumber);
		}

		return adapted;
	}

}
