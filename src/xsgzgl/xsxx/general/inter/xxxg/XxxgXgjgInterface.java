package xsgzgl.xsxx.general.inter.xxxg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xxxg.xgjg.XgjgModel;
import xsgzgl.xsxx.general.xxxg.xgsh.XgshModel;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: �W����Ϣ_��Ϣ�޸�_�޸ĽY��_Interface��
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

public interface XxxgXgjgInterface {

	// ��ʼ������
	public void initParameter();

	// ��ñ�ͷ�ļ����޸ĽY����
	public List<HashMap<String, String>> getXgjgTop(XgjgModel model, User user);

	// ��ý�������޸ĽY����
	public ArrayList<String[]> getXgjgList(XsxxGeneralForm myForm,
			XgjgModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ������������޸ĽY����
	public String createXgjgHTML(SearchRsModel rsModel, XgjgModel model,
			ArrayList<String[]> rsArrList, User user);
}
