package xsgzgl.xsxx.model;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��������_Model��
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

public class CsszModel {

	private String sfsh;// '�Ƿ����';

	private String lcid;// '����ID';

	private String sqkssj;// '���뿪ʼʱ��';

	private String sqjssj;// '�������ʱ��';

	private String shkssj;// '��˿�ʼʱ��';

	private String shjssj;// '��˽���ʱ��';

	List<HashMap<String, String>> gwList;// '��λ�б�';

	public String getLcid() {
		return lcid;
	}

	public void setLcid(String lcid) {
		this.lcid = lcid;
	}

	public String getSfsh() {
		return sfsh;
	}

	public void setSfsh(String sfsh) {
		this.sfsh = sfsh;
	}

	public String getShjssj() {
		return shjssj;
	}

	public void setShjssj(String shjssj) {
		this.shjssj = shjssj;
	}

	public String getShkssj() {
		return shkssj;
	}

	public void setShkssj(String shkssj) {
		this.shkssj = shkssj;
	}

	public String getSqjssj() {
		return sqjssj;
	}

	public void setSqjssj(String sqjssj) {
		this.sqjssj = sqjssj;
	}

	public String getSqkssj() {
		return sqkssj;
	}

	public void setSqkssj(String sqkssj) {
		this.sqkssj = sqkssj;
	}

	public List<HashMap<String, String>> getGwList() {
		return gwList;
	}

	public void setGwList(List<HashMap<String, String>> gwList) {
		this.gwList = gwList;
	}
}
