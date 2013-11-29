package com.jonest.paging.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jonest.paging.PagedList;
import com.jonest.paging.PagedMap;
import com.jonest.paging.PaginationHandler;

public class PaginationHandlerImpl implements PaginationHandler {

	private int elementsCount;

	public PaginationHandlerImpl(int elementsCount) {
		this.elementsCount = elementsCount;
	}

	public int getElementsCount() {
		return this.elementsCount;
	}

	public void setElementsCount(int elementsCount) {
		this.elementsCount = elementsCount;
	}

	@Override
	public PagedMap getPagedMap(Map<String, ?> completeMap, int pageNumber) {

		int totalElements = completeMap.size();
		validatePageNumber(totalElements, pageNumber);

		int offset = pageNumber * elementsCount;
		int limit = getLimit(pageNumber, elementsCount, totalElements);

		Map<String, Object> elements = new HashMap<String, Object>();
		Set<String> keySet = completeMap.keySet();
		List<String> keyList = new ArrayList<String>(keySet);

		while (offset < limit) {
			String key = keyList.get(offset);
			Object value = completeMap.get(key);

			elements.put(key, value);

			offset++;
		}
		PagedMap pagedMap = new PagedMap(elements, pageNumber, totalElements);

		return pagedMap;
	}

	@Override
	public PagedList getPagedList(List<?> completeList, int pageNumber) {

		int totalElements = completeList.size();
		validatePageNumber(totalElements, pageNumber);

		int offset = pageNumber * elementsCount;
		int limit = getLimit(pageNumber, elementsCount, totalElements);

		List<Object> elements = new ArrayList<Object>();

		while (offset < limit) {
			Object value = completeList.get(offset);
			elements.add(value);

			offset++;
		}
		PagedList pagedList = new PagedList(elements, pageNumber, totalElements);

		return pagedList;
	}

	private void validatePageNumber(int totalElements, int pageNumber) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException(String.format("PageNumber value '%s' must be 0 or greater", pageNumber));
		}
		if (pageNumber * elementsCount > totalElements) {
			int mod = totalElements % elementsCount;
			int totalPages = (totalElements - mod) / elementsCount;

			throw new IllegalStateException(String.format("PageNumber '%s' grouped by '%s' elements exceeds the totalElements '%s'. PageNumber should be between [0:%s]", pageNumber, elementsCount,
					totalElements, totalPages));
		}
	}

	private int getLimit(int pageNumber, int elementsCount, int totalElements) {

		int limit = (pageNumber + 1) * elementsCount;
		if (limit > totalElements) {
			limit = totalElements;
		}
		return limit;
	}
}
