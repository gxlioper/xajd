package xsgzgl.jygl.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.jygl.general.JyglGeneralForm;
import xsgzgl.jygl.general.sxjy.JyglSxjyModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��ҵ����_ʵϰ��ҵ_Interface��
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

public interface JyglSxjyInterface {

	// ��ʵϰ��ҵ����ñ�ͷ�ļ�
	public List<HashMap<String, String>> getSxjyTop(JyglSxjyModel model,
			User user);

	// ��ʵϰ��ҵ����ý����
	public ArrayList<String[]> getSxjyList(JyglGeneralForm myForm,
			JyglSxjyModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ��ʵϰ��ҵ�����������
	public String createSxjyHTML(SearchRsModel rsModel, JyglSxjyModel model,
			ArrayList<String[]> rsArrList, User user);

	// ��ʵϰ��ҵ�������ϸ��Ϣ
	public HashMap<String, String> getSxjyMap(JyglSxjyModel model)
			throws Exception;

	// ��ʵϰ��ҵ������
	public boolean saveSxjy(JyglSxjyModel model, User user,
			HttpServletRequest request);

	// ��ʵϰ��ҵ��ɾ��
	public boolean deleteSxjy(JyglSxjyModel model, User user,
			HttpServletRequest request);
}
