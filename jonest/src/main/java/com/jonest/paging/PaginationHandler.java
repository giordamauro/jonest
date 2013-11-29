package com.jonest.paging;

import java.util.List;
import java.util.Map;

public interface PaginationHandler {

	PagedMap getPagedMap(Map<String, ?> completeMap, int pageNumber);

	PagedList getPagedList(List<?> completeList, int pageNumber);
}
