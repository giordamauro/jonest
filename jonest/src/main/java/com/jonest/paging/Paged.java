package com.jonest.paging;


public interface Paged<E> {

	E getElements();

	int getPageNumber();

	int getElementsCount();

	int getTotalElements();
}
