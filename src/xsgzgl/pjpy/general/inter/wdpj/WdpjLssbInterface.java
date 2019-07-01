package xsgzgl.pjpy.general.inter.wdpj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.pjpy.general.wdpj.lssb.WdpjLssbModel;
import xsgzgl.pjpy.general.wdpj.pjtj.WdpjPjtjDAO;

public interface WdpjLssbInterface {

	// ----------------------��ʦ�ϱ�����������Ϣ begin------------------
	
	// ��ñ�ͷ�ļ�(�ҵ�����_��ʦ�ϱ�)
	public List<HashMap<String, String>> getWdpjLssbTop(PjpyWdpjModel model,
			User user);

	// ��ý����(�ҵ�����_��ʦ�ϱ�)
	public ArrayList<String[]> getWdpjLssbList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(�ҵ�����_��ʦ�ϱ�)
	public String createWdpjLssbHTML(SearchRsModel rsModel,
			PjpyWdpjModel model,HashMap<String,String>qdrsMap,
			ArrayList<String[]> rsArrList, User user);
	
	// ���������(�ҵ�����_��ʦ�ϱ�)
	public String createKidneyDiv(SearchRsModel rsModel,
			PjpyWdpjModel model,HashMap<String,String>qdrsMap,
			ArrayList<String[]> rsArrList, User user);
	
	// ----------------------��ʦ�ϱ�����������Ϣ end------------------
	
	
	// ----------------------��ʦ�ϱ� begin------------------	
	// ������Ŀ�ϱ���Ϣ
	public Boolean saveXmsb(BasicModel mode,User user);
	
	// �޸��ϱ���Ϣ
	public Boolean updateLssbInfo(BasicModel mode,User user);
	
	// ����������˱���Ϣ
	public boolean saveLssbShInfo(String xmdm, String xh) throws Exception;
	// ----------------------��ʦ�ϱ� end------------------	
	
	
	// ----------------------��ʦ�ϱ�����������Ϣ begin------------------
	// ��ʾ��ʦ�ϱ���Ϣ
	public void showLssbDiv(User user,String opera,String xmdm, String xh,
			HttpServletResponse response)throws IOException;
	
//	 ��ʾ��ʦ�ϱ���Ϣ
	public List<HashMap<String,String>>  getXscjList(String xh) throws IOException;
	
	// ѧ��������Ϣ(�γ̳ɼ����۲�ɼ�)
	public void showXsxxDiv(User user,String xmdm, String xh, HttpServletResponse response) throws IOException;

	// ��ȡ�������Ʒ�Χ
	public HashMap<String,String>getQdrsByBj(String xmdm,String bjdm);
	// ----------------------��ʦ�ϱ�����������Ϣ end------------------
}
