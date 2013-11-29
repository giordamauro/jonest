package com.jonest.paging;

import java.util.Collections;
import java.util.Map;

public class PagedMap implements Paged<Map<String, ?>> {

	private final int pageNumber;
	private final int totalElements;
	private final int elementsCount;
	private final Map<String, ?> elements;

	public PagedMap(Map<String, ?> elements, int pageNumber, int totalElements) {
		this.pageNumber = pageNumber;
		this.totalElements = totalElements;
		this.elementsCount = elements.size();
		this.elements = Collections.unmodifiableMap(elements);
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public int getElementsCount() {
		return elementsCount;
	}

	@Override
	public int getTotalElements() {
		return totalElements;
	}

	@Override
	public Map<String, ?> getElements() {
		return elements;
	}

	public String toString() {
		return String.format("%s - pageNumber:%s, elementsCount:%s, totalElements:%s", getElements(), getPageNumber(), getElementsCount(), getTotalElements());
	}

}
