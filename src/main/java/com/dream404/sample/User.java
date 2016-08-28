package com.dream404.sample;

/**
 * @description	
 * @author	dream404
 * @date	2016年8月20日
 *
 */
public class User {
	private String id;
	
	private String name;
	
	private Integer age;
	
	private String address;
	
	public String sex;
	
	/**
	 * 无参构造
	 */
	public User() {
		
	}
	
	/**
	 * 全参构造
	 * @param id
	 * @param name
	 * @param age
	 * @param address
	 */
	public User(String id, String name, Integer age, String address) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.address = address;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id:"+this.id+",name:"+this.name+",age:"+this.age+",address:"+this.address+",sex:"+this.sex;
	}
}
