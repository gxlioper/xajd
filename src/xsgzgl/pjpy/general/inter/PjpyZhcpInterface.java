package xsgzgl.pjpy.general.inter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.zhcp.PjpyZhcpModel;

public interface PjpyZhcpInterface {

	// ��ó�ʼ����Ŀ
	public List<HashMap<String, String>> getCshXmList(User user);

	// ��ñ�ͷ�ļ�(�ۺϲ���_�۲���Ϣ)
	public List<HashMap<String, String>> getZhcpZcxxTop(PjpyZhcpModel model,
			User user);

	// ��ý����(�ۺϲ���_�۲���Ϣ)
	public ArrayList<String[]> getZhcpZcxxList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(�ۺϲ���_�۲���Ϣ)
	public String createZhcpZcxxHTML(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user);
	
	// �����ۺϲ�����Ϣ
	public boolean saveZhcpInfo(PjpyGeneralForm model,HttpServletRequest request, User user);
	
	//	����ѧУ���Ի�������Ϣ��������
	public String createKidneyDiv(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user);
	
	public boolean account(PjpyGeneralForm myForm,User user) throws Exception;
	
	// ---------------------�ۺϲ��������ѯ 2012.4.11 qlj begin-------------------------------
	
	// ��ñ�ͷ�ļ�(�ۺϲ���_�۲���Ϣ)
	public List<HashMap<String, String>> getZhcpResultTop(PjpyGeneralForm myForm,User user);

	// ��ý����(�ۺϲ���_�۲���Ϣ)
	public ArrayList<String[]> getZhcpResultList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user) throws Exception;

	// ���������(�ۺϲ���_�۲���Ϣ)
	public String createZhcpResultHTML(SearchRsModel rsModel,
			PjpyZhcpModel model, ArrayList<String[]> rsArrList, User user);
	
	// ---------------------�ۺϲ��������ѯ 2012.4.11 qlj end-------------------------------
	
	// ---------------------�ۺϲ�����ѡ 2012.4.11 qlj begin-------------------------------
	public List<HashMap<String, String>> getKindChoose(PjpyGeneralForm model, User user);
	
	public List<HashMap<String, String>> getCheckKind(PjpyGeneralForm model, User user);
	
	public boolean saveKindChoose(PjpyZhcpModel model, User user)throws Exception;
	
	public boolean updateLybInfo(PjpyGeneralForm model, User user)throws Exception;
	
	public List<HashMap<String,String>>getLybInfo();
	
	public void showZdxgDiv(String zd, String zdid, User user,
			HttpServletResponse response) throws IOException ;
	
	// ---------------------�ۺϲ�����ѡ 2012.4.11 qlj end-------------------------------
	
	// ---------------------��ʼ�汾1.0----------------------------------------
	// ��ñ�ͷ�ļ�(�ۺϲ���ά��)
	public List<HashMap<String, String>> getZhcpMaintainTop(
			PjpyGeneralForm model, User user);

	// ��ý����(�ۺϲ���ά��)
	public ArrayList<String[]> getZhcpMaintainInfo(PjpyGeneralForm model,
			User user) throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException;

	// ���������(�ۺϲ���ά��)
	public String createZhcpMaintainRs(SearchRsModel rsModel,
			PjpyGeneralForm model, ArrayList<String[]> rsArrList, User user);
	// ---------------------��ʼ�汾1.0 end----------------------------------------

}
