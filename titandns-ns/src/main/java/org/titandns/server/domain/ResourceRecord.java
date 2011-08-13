package org.titandns.server.domain;

public class ResourceRecord {

	private String name;

	private CLASS clazz;

	private TYPE type;

	private Integer ttl;

	private byte[] data;

	/**
	 * The default constructor.
	 */
	public ResourceRecord() {
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public CLASS getClazz() {
		return this.clazz;
	}

	public void setCLazz(final CLASS clazz) {
		this.clazz = clazz;
	}

	public TYPE getType() {
		return this.type;
	}

	public void setType(final TYPE type) {
		this.type = type;
	}
	
	public Integer getTtl() {
		return this.ttl;
	}
	
	public void setTtl(final Integer ttl) {
		this.ttl = ttl;
	}
	
	public byte[] getData() {
		return this.data;
	}
}
