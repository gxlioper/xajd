/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-6-12 ����11:04:49 
 */  
package com.zfsoft.extend.model;

import java.io.Serializable;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2015-6-12 ����11:04:49 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class ExtendStoreData implements Serializable {
	
	private static final long serialVersionUID = 351210211797104143L;

	private String id;
	
	private String zjz;
	
	private String mid;
	
	private String meid;
	
	private String gid;
	
	private String zd;
	
	private String zdz;
	
	private String rn;

	
	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 * @param id
	 * @param zjz
	 * @param mid
	 * @param meid
	 * @param gid
	 * @param zd
	 * @param zdz
	 * @param rn
	 */
	public ExtendStoreData(String id, String zjz, String mid, String meid,
			String gid, String zd, String zdz, String rn) {
		super();
		this.id = id;
		this.zjz = zjz;
		this.mid = mid;
		this.meid = meid;
		this.gid = gid;
		this.zd = zd;
		this.zdz = zdz;
		this.rn = rn;
	}

	/**
	 * @���� ��TODO�����µ�ǰ���췽��
	 */
	public ExtendStoreData() {
		super();
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param idҪ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the zjz
	 */
	public String getZjz() {
		return zjz;
	}

	/**
	 * @param zjzҪ���õ� zjz
	 */
	public void setZjz(String zjz) {
		this.zjz = zjz;
	}

	/**
	 * @return the mid
	 */
	public String getMid() {
		return mid;
	}

	/**
	 * @param midҪ���õ� mid
	 */
	public void setMid(String mid) {
		this.mid = mid;
	}

	/**
	 * @return the meid
	 */
	public String getMeid() {
		return meid;
	}

	/**
	 * @param meidҪ���õ� meid
	 */
	public void setMeid(String meid) {
		this.meid = meid;
	}

	/**
	 * @return the gid
	 */
	public String getGid() {
		return gid;
	}

	/**
	 * @param gidҪ���õ� gid
	 */
	public void setGid(String gid) {
		this.gid = gid;
	}

	/**
	 * @return the zd
	 */
	public String getZd() {
		return zd;
	}

	/**
	 * @param zdҪ���õ� zd
	 */
	public void setZd(String zd) {
		this.zd = zd;
	}

	/**
	 * @return the zdz
	 */
	public String getZdz() {
		return zdz;
	}

	/**
	 * @param zdzҪ���õ� zdz
	 */
	public void setZdz(String zdz) {
		this.zdz = zdz;
	}

	/**
	 * @return the rn
	 */
	public String getRn() {
		return rn;
	}

	/**
	 * @param rnҪ���õ� rn
	 */
	public void setRn(String rn) {
		this.rn = rn;
	}
}
