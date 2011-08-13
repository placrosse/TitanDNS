package org.titandns.server.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum CLASS {

	/**
	 * Internet class.
	 */
	IN(1, "IN", "Internet"),

	/**
	 * CSNET class.
	 */
	CS(2, "CS", "CSNET"),

	/**
	 * CHAOS class.
	 */
	CH(3, "CH", "CHAOS"),

	/**
	 * Hesiod class.
	 */
	HS(4, "HS", "Hesiod");

	private static final Map<Integer, CLASS> LOOKUP = new HashMap<Integer, CLASS>();

	static {
		for (final CLASS clazz : EnumSet.allOf(CLASS.class))
			CLASS.LOOKUP.put(clazz.value(), clazz);
	}

	private int value;

	private String code;

	private String description;

	private CLASS(final int value, final String code, final String description) {
		this.value = value;
		this.code = code;
		this.description = description;
	}

	public int value() {
		return this.value;
	}

	public String code() {
		return this.code;
	}

	public String description() {
		return this.description;
	}

	public static CLASS fromValue(final int value) {
		return CLASS.LOOKUP.get(value);
	}
}
