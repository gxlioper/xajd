package xsgzgl.xsxx.cssz;

import xsgzgl.comm.form.CommForm;
import xsgzgl.xsxx.model.ZdqxModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��������_Form��
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

public class XsxxCsszForm extends CommForm {

	private static final long serialVersionUID = 1L;

	private String id = "1";// '��ĿID';

	// ======================������Ϣ�޸� begin=============================

	ZdqxModel zdqxModel;
	
	private String sfsh = "��";// '�Ƿ����';

	private String lcid;// '����ID';

	private String sqkssj;// '���뿪ʼʱ��';

	private String sqjssj;// '�������ʱ��';

	private String shkssj;// '��˿�ʼʱ��';

	private String shjssj;// '��˽���ʱ��';

	private String over;// '���ý���';
	
	// ======================������Ϣ�޸� end===============================

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public ZdqxModel getZdqxModel() {
		return zdqxModel;
	}

	public void setZdqxModel(ZdqxModel zdqxModel) {
		this.zdqxModel = zdqxModel;
	}

	public String getOver() {
		return over;
	}

	public void setOver(String over) {
		this.over = over;
	}

}
