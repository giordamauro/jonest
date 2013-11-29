package com.jonest.printing;

import java.util.List;
import java.util.Map;

public interface PrintingAdapter {

	PrintableMap getPrintableMap(Map<String, Object> source, int depth);

	PrintableList getPrintableList(List<Object> source, int depth);

	Printable getPrintableObject(Object source, int depth);
}
