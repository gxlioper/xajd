package xsgzgl.xsxx.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.pjry.PjszPjryModel;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.lsxs.XsxxLsxsModel;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��ʷѧ��_Interface��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public interface XsxxLsxsInterface {

	// ��ñ�ͷ�ļ�(��ʷѧ��)
	public List<HashMap<String, String>> getXsxxLsxsTop(XsxxLsxsModel model,
			User user);

	// ��ý����(��ʷѧ��)
	public ArrayList<String[]> getXsxxLsxsList(XsxxGeneralForm myForm,
			XsxxLsxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(��ʷѧ��)
	public String createXsxxLsxsHTML(SearchRsModel rsModel,
			XsxxLsxsModel model, ArrayList<String[]> rsArrList, User user);

	// ���ѧ��������Ϣ
	public HashMap<String, String> getLsxsInfo(XsxxZxxsModel model, User user);

}
