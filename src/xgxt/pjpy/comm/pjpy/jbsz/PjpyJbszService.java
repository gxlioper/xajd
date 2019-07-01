package xgxt.pjpy.comm.pjpy.jbsz;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.RequestForm;
import xgxt.form.SaveForm;
import xgxt.form.User;
import xgxt.gygl.gywh.DelDetectModel;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_Service��
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

public class PjpyJbszService extends PjpyCommService {

	PjpyJbszDAO dao = new PjpyJbszDAO();

	/**
	 * ��ѯ����������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public HashMap<String, String> getPjpyJbsz() {

		// �����
		String tableName = "xg_pjpy_xtszb";
		// ����
		String pk = "rownum";
		// ����ֵ
		String pkValue = "1";
		// ��һ�ֶ�
		String[] colList = new String[] { "pjxn", "pjxq", "pjnd", "rsszfs" };

		HashMap<String, String> map = getRsInfo(tableName, pk, pkValue, colList);

		// �������䷽ʽ
		String rsszfs = map.get("rsszfs");

		if (Base.isNull(rsszfs)) {
			map.put("rsszfs", "xx");
		}

		return map;
	}

	/**
	 * �����������Ż�������
	 * 
	 * @author ΰ�����
	 */
	public Boolean savePjpyJbsz(PjpyJbszForm model, RequestForm rForm,
			User user, HttpServletRequest request) throws Exception {

		boolean flag = savePjpySzxx(model, rForm, request);

		if (flag) {
			// ------��������ϵͳ���ö���----by ³��-------
			// PjxtszModel.pjxtszModel.setPjxn(model.getPjxn());
			// PjxtszModel.pjxtszModel.setPjnd(model.getPjnd());
			// PjxtszModel.pjxtszModel.setPjxq(model.getPjxn());
			PjxtszModel.pjxtszModel.setRsszfs(model.getRsszfs());
			// ----------------end-------------------
			
			flag = savePjpyTjk(model, rForm, request);
		}

		return flag;
	}

	/**
	 * ������������������Ϣ
	 * 
	 * @author ΰ�����
	 */
	public Boolean savePjpySzxx(PjpyJbszForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {

		// �����
		String tableName = "xg_pjpy_xtszb";
		// ����
		String pk = "1";
		// ����ֵ
		String[] pkValue = new String[] { "1" };
		// ��һ�ֶ�
		String[] onezd = new String[] { "pjxn", "pjxq", "pjnd", "rsszfs" };

		SaveForm saveForm = new SaveForm();
		saveForm.setTableName(tableName);
		saveForm.setPk(pk);
		saveForm.setPkValue(pkValue);
		saveForm.setOnezd(onezd);

		return saveInfoToDb(saveForm, model, request);
	}

	/**
	 * ������������������
	 * 
	 * @author ΰ�����
	 */
	public Boolean savePjpyTjk(PjpyJbszForm model, RequestForm rForm,
			HttpServletRequest request) throws Exception {

		return dao.savePjpyTjk(model);
	}
	
	
	/**
	 * ��ѯ������������״̬
	 * @return
	 */
	public String getPjpyPjzqSfsz(){
		
		return dao.getPjpyPjzqSfsz();
	}
	
	
	
	/**
	 * �޸����������Ƿ�����
	 * @param sfsz
	 * @return
	 */
	public boolean updatePjzqSfsz(String sfsz){
		
		try {
			return dao.updatePjzqSfsz(sfsz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * ��ʼ��������~
	 */
	public boolean startPjpy(){
		//��ʼ������������״̬Ϊ��0��;
		boolean result = updatePjzqSfsz("0");
		//�������ó�ʼ��
		return result ? dao.rsszCsh() : result;
	}
	
	
	/**
	 * ��ǰ���������۲����ü�¼��
	 * @param zczq
	 * @return
	 */
	public String getZczqSfsz(String zczq){
		
		return dao.getZczqSfsz(zczq);
	}
	
	
	/**
	 * ��ǰ�������ڵ�������Ŀ����
	 * @return
	 */
	public String getPjxmSfwh(){
		
		return dao.getPjxmSfwh();
	}
	
	
	 /**
	  * ��ǰ�������ڵ�������Ա�Ƿ�����
	 * @return
	 */
	public String getPjrySfsz(){
		
		return dao.getPjrySfsz();
	}
	
	/**
	 * ɾ����������
	 * @return
	 * @throws Exception 
	 */
	public boolean delRssz(DelDetectModel model){
		return dao.delRssz(model);
	}
}