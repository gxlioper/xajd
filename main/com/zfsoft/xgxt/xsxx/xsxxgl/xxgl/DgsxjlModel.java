/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-12-3 ����01:53:07
 */
package com.zfsoft.xgxt.xsxx.xsxxgl.xxgl;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ѧ����Ϣ
 * @�๦������: ����ʵϰ��Ϣ
 * @���ߣ�������[����:1123]
 * @ʱ�䣺 2016-3-17 ����11:00:38 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class DgsxjlModel implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String dgid; // ����ID
	private String xh;   // ѧ��
	private String ggsxdw; // ����ʵϰ��λ
	private String rzsj; // ��ְʱ��
	private String fxsj; // ��Уʱ��
	
	private String fjid; // ����id
	
	/**
	 * @return the fjid
	 */
	public String getFjid() {
		return fjid;
	}
	/**
	 * @param fjidҪ���õ� fjid
	 */
	public void setFjid(String fjid) {
		this.fjid = fjid;
	}
	public String getDgid() {
		return dgid;
	}
	public void setDgid(String dgid) {
		this.dgid = dgid;
	}
	public String getXh() {
		return xh;
	}
	public void setXh(String xh) {
		this.xh = xh;
	}
	public String getGgsxdw() {
		return ggsxdw;
	}
	public void setGgsxdw(String ggsxdw) {
		this.ggsxdw = ggsxdw;
	}
	public String getRzsj() {
		return rzsj;
	}
	public void setRzsj(String rzsj) {
		this.rzsj = rzsj;
	}
	public String getFxsj() {
		return fxsj;
	}
	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}		

}
