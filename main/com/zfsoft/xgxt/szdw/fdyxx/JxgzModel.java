/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-24 ����09:13:10 
 */  
package com.zfsoft.xgxt.szdw.fdyxx;

import java.io.Serializable;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼���������ģ��
 * @�๦������: ����Ա��Ϣ-��ѧ����
 * @���ߣ� ������[����:1123]
 * @ʱ�䣺 2015-12-16 ����10:45:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class JxgzModel implements Serializable{

	private static final long serialVersionUID = -8692278407785586702L;
	
	private String jxid;
	private String qzsj;
	private String skmc;
	private String skcc;
	private String jxgzl;
	private String pjjl;
	private String ndkhjl;
	
	
	public String getJxid() {
		return jxid;
	}
	public void setJxid(String jxid) {
		this.jxid = jxid;
	}
	public String getQzsj() {
		return qzsj;
	}
	public void setQzsj(String qzsj) {
		this.qzsj = qzsj;
	}
	public String getSkmc() {
		return skmc;
	}
	public void setSkmc(String skmc) {
		this.skmc = skmc;
	}
	public String getSkcc() {
		return skcc;
	}
	public void setSkcc(String skcc) {
		this.skcc = skcc;
	}
	public String getJxgzl() {
		return jxgzl;
	}
	public void setJxgzl(String jxgzl) {
		this.jxgzl = jxgzl;
	}
	public String getPjjl() {
		return pjjl;
	}
	public void setPjjl(String pjjl) {
		this.pjjl = pjjl;
	}
	public String getNdkhjl() {
		return ndkhjl;
	}
	public void setNdkhjl(String ndkhjl) {
		this.ndkhjl = ndkhjl;
	}

}
