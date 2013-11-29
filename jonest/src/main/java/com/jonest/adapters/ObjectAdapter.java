package com.jonest.adapters;

public interface ObjectAdapter<A, B> {

	B adapt(A sourceObject);
}
