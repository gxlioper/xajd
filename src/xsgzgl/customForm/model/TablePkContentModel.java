package xsgzgl.customForm.model;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Զ����_����_model��
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

public class TablePkContentModel {

	String id;// 'ID';

	String gnmk_id;// '����ģ��ID';

	String[] pk_id;// '����ID';

	String[] xssx;// '��ʾ˳��';

	public String getGnmk_id() {
		return gnmk_id;
	}

	public void setGnmk_id(String gnmk_id) {
		this.gnmk_id = gnmk_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String[] getPk_id() {
		return pk_id;
	}

	public void setPk_id(String[] pk_id) {
		this.pk_id = pk_id;
	}

	public String[] getXssx() {
		return xssx;
	}

	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}

}
