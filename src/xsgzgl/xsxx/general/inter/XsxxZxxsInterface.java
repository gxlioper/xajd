package xsgzgl.xsxx.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.zxxs.XsxxZxxsModel;


/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��Уѧ��_Interface��
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

public interface XsxxZxxsInterface {

	// ��ñ�ͷ�ļ�(��Уѧ��)
	public List<HashMap<String, String>> getXsxxZxxsTop(XsxxZxxsModel model,
			User user);

	// ��ý����(��Уѧ��)
	public ArrayList<String[]> getXsxxZxxsList(XsxxGeneralForm myForm,
			XsxxZxxsModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(��Уѧ��)
	public String createXsxxZxxsHTML(SearchRsModel rsModel,
			XsxxZxxsModel model, ArrayList<String[]> rsArrList, User user);
	
	// ���ѧ��������Ϣ
	public HashMap<String, String> getZxxsInfo(XsxxZxxsModel model, User user);
	
	// ����ѧ����Ϣ
	public boolean saveXsxx(XsxxZxxsModel model, User user);
	
	// �����ҵ����
	public boolean saveBycl(XsxxZxxsModel model, User user)throws Exception;
}
