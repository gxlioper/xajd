package xsgzgl.customForm.model;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �Զ����_table_model��
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

public class TableModel {

	String gnmk_id;//����ģ��ID
	
	String[] id;// 'ID';

	String[] title;// '����';

	String[] row_all;// '����'

	String[] create_time;// '����ʱ��'

	public String[] getId() {
		return id;
	}

	public void setId(String[] id) {
		this.id = id;
	}

	public String[] getRow_all() {
		return row_all;
	}

	public void setRow_all(String[] row_all) {
		this.row_all = row_all;
	}

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}

	public String[] getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String[] create_time) {
		this.create_time = create_time;
	}

	public String getGnmk_id() {
		return gnmk_id;
	}

	public void setGnmk_id(String gnmk_id) {
		this.gnmk_id = gnmk_id;
	}
}
