package xsgzgl.xsxx.general.inter.xxxg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.dwwh.DwwhModel;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �W����Ϣ_��Ϣ�޸�_�޸Č���_Interface��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public interface XxxgXgshInterface {

	// ��ʼ������
	public void initParameter();

	// ��ñ�ͷ�ļ����޸Č��ˡ�
	public List<HashMap<String, String>> getXgshTop(XgshModel model, User user);

	// ��ý�������޸Č��ˡ�
	public ArrayList<String[]> getXgshList(XsxxGeneralForm myForm,
			XgshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ������������޸Č��ˡ�
	public String createXgshHTML(SearchRsModel rsModel, XgshModel model,
			ArrayList<String[]> rsArrList, User user);

	// ��Ì�����Ϣ�б�
	public List<HashMap<String, String>> getXgshList(XgshModel model, User user);

	// ��ÌW���޸���Ϣ
	public HashMap<String, String> getXgshInfo(XgshModel model, User user);

	// ���挏�ˠ�B
	public boolean saveShzt(XgshModel model, User user);

	// �ύ�W����Ϣ�޸�
	public void submitXxxg(String[] sqid, User user);
}
