package xsgzgl.pjpy.general.inter.pjsz;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.action.Base;
import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.cpxz.PjszCpxzModel;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqDAO;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_��������_������Ŀ_Interface��
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

public interface PjszPjxmInterface {

	// ��ñ�ͷ�ļ�(��������_������Ŀ)
	public List<HashMap<String, String>> getPjszPjxmTop(PjszPjxmModel model,
			User user);

	// ��ý����(��������_������Ŀ)
	public ArrayList<String[]> getPjszPjxmList(PjpyGeneralForm myForm,
			PjszPjxmModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(��������_������Ŀ)
	public String createPjszPjxmHTML(SearchRsModel rsModel,
			PjszPjxmModel model, ArrayList<String[]> rsArrList, User user);

	// ��ʼ��������Ŀ����
	public void defaultPjxmSetting(PjszPjxmModel model, User user,
			HttpServletResponse response) throws IOException;

	// ��ʼ��������Ŀ����
	public void defaultPjxmUpdate(PjszPjxmModel model, User user,
			HttpServletResponse response) throws Exception;

	
	// ����������Ŀ
	public Boolean savePjxm(PjszPjxmModel model, User user);
	
	// ����������Ŀ
	public Boolean updatePjxm(PjszPjxmModel model, User user);
	
	// ���������Ŀ�����Ϣ(Model)
	public PjszPjxmModel getPjxmModel(String xmdm, User user);
	
	// ���������Ŀ�����Ϣ(Map)
	public HashMap<String,String> getPjxmMap(String xmdm, User user);
	
	// �����Ŀ����
	public boolean checkXmmc(PjszPjxmModel model);
	
	// �����ʦ������Ŀ�б�
	public List<HashMap<String, String>> getLssbXmList();
	
	// ɾ��������Ŀ
	public Boolean deletePjxm(PjszPjxmModel model, User user);
	
	
	public String checkDelete(PjszPjxmModel model, User user) throws Exception;
	
	public boolean checkXssq(PjszPjxmModel model, User user) throws Exception;
	
	public boolean checkRssz(PjszPjxmModel model, User user) throws Exception;
}
