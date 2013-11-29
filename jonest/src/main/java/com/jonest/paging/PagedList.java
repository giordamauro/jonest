package com.jonest.paging;

import java.util.Collections;
import java.util.List;

public class PagedList implements Paged<List<?>> {

	private final int pageNumber;
	private final int totalElements;
	private final int elementsCount;
	private final List<?> elements;

	public PagedList(List<?> elements, int pageNumber, int totalElements) {
		this.pageNumber = pageNumber;
		this.totalElements = totalElements;
		this.elementsCount = elements.size();
		this.elements = Collections.unmodifiableList(elements);
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
	public List<?> getElements() {
		return elements;
	}

	public String toString() {
		return String.format("%s - pageNumber:%s, elementsCount:%s, totalElements:%s", getElements(), getPageNumber(), getElementsCount(), getTotalElements());
	}
}
