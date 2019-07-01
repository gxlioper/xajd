package xsgzgl.pjpy.general.inter.xmsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.xmsz.rssz.XmszRsszModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��Ŀ����_��������_Interface��
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

public interface XmszRsszInterface {

	// ��������Ƿ񳬹�����Ϣ
	public String getRsszMessage(XmszRsszModel model, User user);

	// ��ñ�ͷ�ļ�(��Ŀ����_��������)
	public List<HashMap<String, String>> getXmszRsszTop(XmszRsszModel model,
			User user);

	// ��ý����(��Ŀ����_��������)
	public ArrayList<String[]> getXmszRsszList(PjpyGeneralForm myForm,
			XmszRsszModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(��Ŀ����_��������)
	public String createXmszRsszHTML(SearchRsModel rsModel,
			XmszRsszModel model, ArrayList<String[]> rsArrList, User user);
	
	// ��ʼ��������������
	public void initRsszb(String xmdm, String szfw, String tsrq, User user);
	
	// �������ñ���
	public Boolean saveSzbl(PjpyGeneralForm myForm, XmszRsszModel model,
			User user, String saveLx);
	
	// ����ȷ������
	public Boolean saveQdrs(XmszRsszModel model, User user);
}
