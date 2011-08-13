package org.titandns.server.domain;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum TYPE {

	/**
	 * Address record.
	 */
	A(1, "A", "address"),

	/**
	 * Name server record.
	 */
	NS(2, "NS", "name server"),

	/**
	 * Canonical name record.
	 */
	CNAME(5, "CNAME", "canonical name"),

	/**
	 * Start of authority record.
	 */
	SOA(6, "SOA", "start of authority"),

	/**
	 * Pointer record.
	 */
	PTR(12, "PTR", "pointer"),

	/**
	 * Mail exchanger record.
	 */
	MX(15, "MX", "mail exchanger"),

	/**
	 * Text record.
	 */
	TXT(16, "TXT", "text"),

	/**
	 * Andrew File System Data Base record.
	 */
	AFSDB(18, "AFSDB", "Andrew File System Data Base");

	private static final Map<Integer, TYPE> LOOKUP = new HashMap<Integer, TYPE>();

	static {
		for (final TYPE type : EnumSet.allOf(TYPE.class))
			TYPE.LOOKUP.put(type.value(), type);
	}

	private final int value;

	private final String code;

	private final String description;

	private TYPE(final int value, final String code, final String description) {
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
	
	public static TYPE fromValue(final int value) {
		return TYPE.LOOKUP.get(value);
	}
}
