package com.jonest.printing;

import java.util.ArrayList;

public class PrintableList extends ArrayList<Printable> implements Printable {

	private static final long serialVersionUID = 7137416845276504347L;

	public static PrintableList EMPTY_LIST = new PrintableList();
}
