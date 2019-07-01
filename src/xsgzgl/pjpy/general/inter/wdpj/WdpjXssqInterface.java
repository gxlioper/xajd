package xsgzgl.pjpy.general.inter.wdpj;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.comm.BasicModel;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.xssq.WdpjXssqModel;

public interface WdpjXssqInterface {

	// ��ñ�ͷ�ļ�(�ҵ�����_ѧ������)
	public List<HashMap<String, String>> getWdpjXssqTop(WdpjXssqModel model,
			User user);

	// ��ý����(�ҵ�����_ѧ������)
	public ArrayList<String[]> getWdpjXssqList(PjpyGeneralForm myForm,
			WdpjXssqModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(�ҵ�����_ѧ������)
	public String createWdpjXssqHTML(SearchRsModel rsModel,
			WdpjXssqModel model, ArrayList<String[]> rsArrList, User user);

	// ��ʾѧ�����루�޸ģ�DIV
	public void showXssqDiv(String opera,String xmdm, User user,
			HttpServletResponse response)throws IOException; 
	
	public String createOtherInfo(HashMap<String,String>csMap)throws IOException; 
	
	
//	// ����ѧ��������Ϣ
//	public void disfrockXssqInfo(String message,
//			HttpServletResponse response)throws IOException; 

	
	// ��ʼ��ҳ����ʾ(�ҵ�����_ѧ������)
	public Map<String,Object> defaultWdpjXssq(PjpyGeneralForm form,User user);
	
	// ����ѧ�������ҵ�������Ϣ
	public boolean saveXssqInfo(BasicModel mode,HashMap<String,String>valueMap,User user);
	
	// ����ѧ�������ҵ�������Ϣ
	public boolean updateXssqInfo(BasicModel mode,HashMap<String,String>valueMap,User user);
	
	// ��ǰ����ѧ��������Ϣ�鿴
	public List<HashMap<String,String>>getXssqByZq(WdpjXssqModel model,
			User user)throws Exception;
	
	// ��ǰ����ѧ���۲���Ϣ
	public List<Object>getZcxxByZq(WdpjXssqModel model,
			User user)throws Exception;
	
	// ���꽱ѧ�����������ѯ
	public List<HashMap<String,String>>getXssqInfo(WdpjXssqModel model,
			User user);
	
	// ���꽱ѧ�����������ѯ
	public List<String[]>getXszcInfo(WdpjXssqModel model,
			User user);
	
	public boolean saveWdpjShInfo(String xmdm, User user)throws Exception;

	// ��ʾѧ�����루�޸ģ�DIV(��������)
	public void showXssqDivForBJLH(String opera, String xmdm, User user, HttpServletResponse response) throws IOException;
}
