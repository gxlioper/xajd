package xsgzgl.customForm.model;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Զ����_��ѯ����_model��
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

public class TableSearchContentModel {

	String gnmk_id;// '����ģ��ID';

	String[] content_id;// '����ID';

	String[] xssx;// '��ʾ˳��';

	public String[] getContent_id() {
		return content_id;
	}

	public void setContent_id(String[] content_id) {
		this.content_id = content_id;
	}

	public String getGnmk_id() {
		return gnmk_id;
	}

	public void setGnmk_id(String gnmk_id) {
		this.gnmk_id = gnmk_id;
	}

	public String[] getXssx() {
		return xssx;
	}

	public void setXssx(String[] xssx) {
		this.xssx = xssx;
	}

}
