package xsgzgl.pjpy.general.inter.wdpj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.RequestForm;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.jgcx.WdpjJgcxModel;
import xsgzgl.pjpy.general.wdpj.xmsh.WdpjXmshModel;

public interface WdpjXmshInterface {
	
	// ===========================������Ŀ��� ����� begin =============================
	
	// ��ʼ��������Ŀ
	public List<HashMap<String, String>> getCshXmList(WdpjXmshModel model,
			User user);
	
	// ��ʼ��������Ŀ(������˿��ؼ�ʱ�����)
	public List<HashMap<String, String>> getShxmList(WdpjXmshModel model,
			User user);
	
	// ��ñ�ͷ�ļ�(�ҵ�����_ѧ������)
	public List<HashMap<String, String>> getWdpjXmshTop(WdpjXmshModel model,
			User user);

	// ��ý����(�ҵ�����_ѧ������)
	public ArrayList<String[]> getWdpjXmshList(PjpyGeneralForm myForm,
			WdpjXmshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(�ҵ�����_ѧ������)
	public String createWdpjXmshHTML(SearchRsModel rsModel,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user);
	
	// �����ֶ�(����������)
	public String createKidneyDiv(SearchRsModel rsModel,RequestForm rForm,
			WdpjXmshModel model, ArrayList<String[]> rsArrList, User user) throws Exception;
	
	// ===========================������Ŀ��� ����� end =============================
	
	
	// �������״̬
	public boolean updateShzt(WdpjXmshModel model,HttpServletRequest request,  User user)throws Exception;
	
	// ��ȡ������˵���ϸ��Ϣ
	public HashMap<String,Object>defaultWdpjXmsh(WdpjXmshModel form,User user)throws Exception;
	
	// �����Ŀ��˸�λ
	public List<HashMap<String,String>> getSpgwList(WdpjXmshModel model,User user);
	
	public boolean checkFirstSpgw(WdpjXmshModel model, User user);
	
	//�����Ŀ��˸�λ 
	public void showShgwDiv(WdpjXmshModel model,User user,HttpServletResponse response) throws IOException;
}
