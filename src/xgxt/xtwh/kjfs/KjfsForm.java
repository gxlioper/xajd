package xgxt.xtwh.kjfs;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ϵͳά��_��ݷ�ʽ_formBean
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public class KjfsForm extends CommForm {

	private static final long serialVersionUID = 1L;

	// ϵͳ��ݷ�ʽͼƬ�б�
	public static List<HashMap<String, String>> picList = null;

	private String yhm;// �û���

	private String[] pic;// ͼƬ·��

	private String[] xssx;// ��ʾ˳��
	
	private String fwlb;//�������
	
	private String kjfs;//��ݷ�ʽ

	public String getKjfs() {
		return kjfs;
	}

	public void setKjfs(String kjfs) {
		this.kjfs = kjfs;
	}

	public String getFwlb() {
		return fwlb;
	}

	public void setFwlb(String fwlb) {
		this.fwlb = fwlb;
	}

	public String[] getPic() {
		return pic;
	}

	public void setPic(String[] pic) {
		this.pic = pic;
	}

	public String[] getXssx() {
		return xssx;
	}

	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}

	public String getYhm() {
		return yhm;
	}

	public void setYhm(String yhm) {
		this.yhm = yhm;
	}

	public static List<HashMap<String, String>> getPicList() {
		return picList;
	}

	public static void setPicList(List<HashMap<String, String>> picList) {
		KjfsForm.picList = picList;
	}
}
