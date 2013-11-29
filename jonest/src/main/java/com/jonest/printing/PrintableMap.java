package com.jonest.printing;

import java.util.HashMap;

public class PrintableMap extends HashMap<String, Printable> implements Printable {

	private static final long serialVersionUID = 1195513346186277233L;

	public static PrintableMap EMPTY_MAP = new PrintableMap();

}
