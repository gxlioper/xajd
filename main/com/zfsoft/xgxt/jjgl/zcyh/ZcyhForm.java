/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-8-28 ����11:07:20 
 */  
package com.zfsoft.xgxt.jjgl.zcyh;

import com.zfsoft.xgxt.zxdk.rwfbybc.dcjg.AutoArrayList;
import org.apache.struts.action.ActionForm;

import xgxt.utils.Pages;

import java.util.ArrayList;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ��С��[����:1036]
 * @ʱ�䣺 2014-8-29 ����01:48:48 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
public class ZcyhForm extends ActionForm {

	/** 
	 * @���� serialVersionUID : TODO(��һ�仰�������������ʾʲô) 
	 */ 
	
	private Pages pages = new Pages();
	
	private static final long serialVersionUID = 1L;

	private String type;
	
	private String yhm;//�û���
	
	private String mm;//���� 
	
	private String xm;//����
	
	private String sfzh;//���֤��
	
	private String lxdh; //��ϵ�绰
	
	private String jtzz; //��ͥסַ
	
	private String gzdw; //������λ
	
	private String fj; //����;
	
	private String photo;//ͷ��
	
	private String zt;//ע����Ϣ״̬
	
	private String zcsj;//ע��ʱ��

	private String hmdsj;
	private String hmdyy;

	private String xb;
	private ArrayList<ZnxxModel> znxxModelList = new AutoArrayList(ZnxxModel.class);
	/**
	 * @return the yhm
	 */
	public String getYhm() {
		return yhm;
	}

	/**
	 * @param yhmҪ���õ� yhm
	 */
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	/**
	 * @return the mm
	 */
	public String getMm() {
		return mm;
	}

	/**
	 * @param mmҪ���õ� mm
	 */
	public void setMm(String mm) {
		this.mm = mm;
	}

	/**
	 * @return the xm
	 */
	public String getXm() {
		return xm;
	}

	/**
	 * @param xmҪ���õ� xm
	 */
	public void setXm(String xm) {
		this.xm = xm;
	}

	/**
	 * @return the sfzh
	 */
	public String getSfzh() {
		return sfzh;
	}

	/**
	 * @param sfzhҪ���õ� sfzh
	 */
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	/**
	 * @return the lxdh
	 */
	public String getLxdh() {
		return lxdh;
	}

	/**
	 * @param lxdhҪ���õ� lxdh
	 */
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	/**
	 * @return the jtzz
	 */
	public String getJtzz() {
		return jtzz;
	}

	/**
	 * @param jtzzҪ���õ� jtzz
	 */
	public void setJtzz(String jtzz) {
		this.jtzz = jtzz;
	}

	/**
	 * @return the gzdw
	 */
	public String getGzdw() {
		return gzdw;
	}

	/**
	 * @param gzdwҪ���õ� gzdw
	 */
	public void setGzdw(String gzdw) {
		this.gzdw = gzdw;
	}

	/**
	 * @return the fj
	 */
	public String getFj() {
		return fj;
	}

	/**
	 * @param fjҪ���õ� fj
	 */
	public void setFj(String fj) {
		this.fj = fj;
	}

	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}

	/**
	 * @param photoҪ���õ� photo
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	/**
	 * @return the zt
	 */
	public String getZt() {
		return zt;
	}

	/**
	 * @param ztҪ���õ� zt
	 */
	public void setZt(String zt) {
		this.zt = zt;
	}

	/**
	 * @return the zcsj
	 */
	public String getZcsj() {
		return zcsj;
	}

	/**
	 * @param zcsjҪ���õ� zcsj
	 */
	public void setZcsj(String zcsj) {
		this.zcsj = zcsj;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param typeҪ���õ� type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the pages
	 */
	public Pages getPages() {
		return pages;
	}

	/**
	 * @param pagesҪ���õ� pages
	 */
	public void setPages(Pages pages) {
		this.pages = pages;
	}

	/**
	 * @return the hmdyy
	 */
	public String getHmdyy() {
		return hmdyy;
	}

	/**
	 * @param hmdyyҪ���õ� hmdyy
	 */
	public void setHmdyy(String hmdyy) {
		this.hmdyy = hmdyy;
	}

	/**
	 * @return the hmdsj
	 */
	public String getHmdsj() {
		return hmdsj;
	}

	/**
	 * @param hmdsjҪ���õ� hmdsj
	 */
	public void setHmdsj(String hmdsj) {
		this.hmdsj = hmdsj;
	}

	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public ArrayList<ZnxxModel> getZnxxModelList() {
		return znxxModelList;
	}

	public void setZnxxModelList(ArrayList<ZnxxModel> znxxModelList) {
		this.znxxModelList = znxxModelList;
	}
}
