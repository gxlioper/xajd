package xsgzgl.pjpy.general.inter.wdpj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;

public interface WdpjJgcxInterface {

	// ��ñ�ͷ�ļ�(�ҵ�����_��������)
	public List<HashMap<String, String>> getWdpjBcpjTop(WdpjJgcxModel model,
			User user);

	// ��ý����(�ҵ�����_��������)
	public ArrayList<String[]> getWdpjBcpjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(�ҵ�����_��������)
	public String createWdpjBcpjHTML(SearchRsModel rsModel,
			WdpjJgcxModel model, ArrayList<String[]> rsArrList, User user);
	
	// ������������Ϣ
	public HashMap<String, Object> getBcpjMap(WdpjJgcxModel model);

	//===================���ҵ�����_��ʷ������begin=============================
	
	// ���ҵ�����_��ʷ��������ñ�ͷ�ļ�
	public List<HashMap<String, String>> getWdpjLspjTop(WdpjJgcxModel model,
			User user);

	// ���ҵ�����_��ʷ��������ý����
	public ArrayList<String[]> getWdpjLspjList(PjpyGeneralForm myForm,
			WdpjJgcxModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���ҵ�����_��ʷ���������������
	public String createWdpjLspjHTML(SearchRsModel rsModel,
			WdpjJgcxModel model, ArrayList<String[]> rsArrList, User user);

	// ���ҵ�����_��ʷ�����������ϸ��Ϣ
	public HashMap<String, String> getLspjMap(WdpjJgcxModel model)
			throws Exception;

	// ���ҵ�����_��ʷ����������������ʷ��Ϣ
	public boolean savePjlsxx(WdpjJgcxModel model, User user,
			HttpServletRequest request);
	
	// ���ҵ�����_��ʷ����������������ʷ��Ϣ
	public boolean deletePjlsxx(WdpjJgcxModel model, User user,
			HttpServletRequest request);
	
	// ���ҵ�����_��ʷ���������������ʷ��Ϣ
	public List<HashMap<String, String>> getLspjList(String xh);
	
	//===================���ҵ�����_��ʷ������end=============================
	
	// ��ʼ��������Ŀ
	public List<HashMap<String, String>> getCshXmList(WdpjJgcxModel model,
			User user);

	// ��ñ�ͷ�ļ�(�ҵ�����_�����ѯ)
	public List<HashMap<String, String>> getWdpjJgcxTop(WdpjJgcxModel model,
			User user);

	// ��ý����(�ҵ�����_�����ѯ)
	public ArrayList<String[]> getWdpjJgcxList(WdpjJgcxModel model, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	// ���������(�ҵ�����_�����ѯ)
	public String createWdpjJgcxHTML(WdpjJgcxModel model,
			ArrayList<String[]> rsArrList, User user);

	// ɾ��ѧ������������Ϣ(�ҵ�����_�����ѯ)
	public boolean deleteXssqInfo(WdpjJgcxModel model,
			HttpServletRequest request, User user);

	// ��ȡ������˵���ϸ��Ϣ
	public Map<String, Object> defaultWdpjXssq(WdpjJgcxModel form, User user);
}
