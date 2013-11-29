package com.jonest.adapters.impl;

import com.jonest.adapters.ObjectAdapter;
import com.jonest.printing.Printable;
import com.jonest.printing.PrintingAdapter;

public class PrintingObjectAdapter implements ObjectAdapter<Object, Printable> {

	private final PrintingAdapter printingAdapter;

	private int depth;

	public PrintingObjectAdapter(PrintingAdapter printingAdapter, int depth) {
		this.printingAdapter = printingAdapter;
		this.depth = depth;
	}

	@Override
	public Printable adapt(Object sourceObject) {

		Printable printable = printingAdapter.getPrintableObject(sourceObject, depth);

		return printable;
	}

}
