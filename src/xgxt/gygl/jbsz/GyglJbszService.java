package xgxt.gygl.jbsz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.GyglCommService;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��Ԣ����_��������_Service��
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

public class GyglJbszService extends GyglCommService {

	GyglJbszDAO dao = new GyglJbszDAO();

	/**
	 * ���湫Ԣ��������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public Boolean saveGyjbsz(GyglJbszForm model, User user,
			HttpServletRequest request) throws Exception {

		// �����
		String tableName = "xg_gygl_jbszb";
		// ����
		String pk = "1";
		// ����ֵ
		String[] pkValue = new String[] { "1" };
		// �����ֶ�
		String[] onezd = new String[] { "czxq", "czyq", "csgx", "fpdx" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, model, request);

	}

	/**
	 * ���ù�Ԣ��������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public void setGyjbszInfo(GyglJbszForm model) {

		HashMap<String, String> map = getGyjbsz();

		// '����У��';
		String czxq = map.get("czxq");
		if (!Base.isNull(czxq)) {
			model.setCzxq(czxq);
		}
		// '����԰��';
		String czyq = map.get("czyq");
		if (!Base.isNull(czyq)) {
			model.setCzyq(czyq);
		}
		// '������ϵ';
		String csgx = map.get("csgx");
		if (!Base.isNull(csgx)) {
			model.setCsgx(csgx);
		}
		// '�������';
		String fpdx = map.get("fpdx");
		if (!Base.isNull(fpdx)) {
			model.setFpdx(fpdx);
		}
	}

	/**
	 * ��ù�Ԣ��������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public HashMap<String, String> getGyjbsz() {

		// �����
		String tableName = "xg_gygl_jbszb";
		// ����
		String pk = "rownum";
		// ����ֵ
		String pkValue = "1";
		// ����ֶ�
		String[] colList = new String[] { "czxq", "czyq", "csgx", "fpdx",
				"fpfs" };

		return getRsInfo(tableName, pk, pkValue, colList);
	}
	
	/**
	 * ��ù�Ԣ��������
	 * 
	 * @author ΰ�����
	 * @throws Exception
	 */
	public GyglJbszForm getGyjbszModel() {

		GyglJbszForm model = new GyglJbszForm();
		HashMap<String, String> map = getGyjbsz();

		//�Ƿ��Ѿ�����
		if(!Base.isNull(map.get("fpdx"))){
			model.setSfsz("��");
		}
		
		// '����У��';
		String czxq = map.get("czxq");
		if (!Base.isNull(czxq)) {
			model.setCzxq(czxq);
		}
		// '����԰��';
		String czyq = map.get("czyq");
		if (!Base.isNull(czyq)) {
			model.setCzyq(czyq);
		}
		// '������ϵ';
		String csgx = map.get("csgx");
		if (!Base.isNull(csgx)) {
			model.setCsgx(csgx);
		}
		// '�������';
		String fpdx = map.get("fpdx");
		if (!Base.isNull(fpdx)) {
			model.setFpdx(fpdx);
		}
		// '���䷽ʽ';
		String fpfs = map.get("fpfs");
		if (!Base.isNull(fpfs)) {
			model.setFpfs(fpfs);
		}
		
		return model;
	}
}