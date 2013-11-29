package com.jonest.printing;

public class PrintablePrimitive implements Printable {

	private final Object value;

	public PrintablePrimitive(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public String toString() {
		return String.valueOf(getValue());
	}
}
