package com.jonest.nest;

import java.util.List;

public interface NestedObjectHandler {

	Object getNestedObject(Object source, List<String> direction);
}
