package xsgzgl.xsxx.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.xjyd.XsxxXjydModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_ѧ���춯_Interface��
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

public interface XsxxXjydInterface {

	// ��ñ�ͷ�ļ���ѧ���춯��
	public List<HashMap<String, String>> getXsxxXjydTop(XsxxXjydModel model,
			User user);

	// ��ý������ѧ���춯��
	public ArrayList<String[]> getXsxxXjydList(XsxxGeneralForm myForm,
			XsxxXjydModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// �����������ѧ���춯��
	public String createXsxxXjydHTML(SearchRsModel rsModel,
			XsxxXjydModel model, ArrayList<String[]> rsArrList, User user);
	
	// �ύѧ���춯��Ϣ��ѧ���춯��
	public boolean submitXjyd(XsxxXjydModel model, User user)throws Exception ;
}
