package xsgzgl.wjcf.general.inter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.cfjcgl.WjcfCfjcshModel;

public interface WjcfCfjcshInterface {
	// ��ñ�ͷ�ļ������ֽ����
	public List<HashMap<String, String>> getWjcfCfjcshTop(WjcfCfjcshModel model,
			User user);
	
	// ��ý���������ֽ����
	public ArrayList<String[]> getWjcfCfjcshList(WjcfGeneralForm myForm,
			WjcfCfjcshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ��������������ֽ����
	public String createWjcfCfjcshHTML(SearchRsModel rsModel,
			WjcfCfjcshModel model, ArrayList<String[]> rsArrList, User user);

	//���ݴ����������ѯ��λ
	public void showShgwDiv(User user,
			HttpServletResponse response) throws IOException ;

	//��ȡ��һ����λ
	public HashMap<String,String> getHigherUpSpMap(WjcfGeneralForm model,User user);
	
	//��ȡ������������б�
	public List<HashMap<String,String>> getCfjcshxx(String cfid,String spgw);
	
	//��ȡһ�����ֽ���������
	public HashMap<String, String> getOnesCfjcsh(String cfid,String spgw) throws Exception;
	
	//��ȡ��߼���λ
	public HashMap<String,String> getMaxSpgw(WjcfGeneralForm model,User user) throws Exception;
	
	//��ȡ������Ϣ
	public HashMap<String,String> getCfxx(String cfId) throws Exception;
	
	//��ȡ��������������Ϣ
	public HashMap<String,String> getOnesCfss(String cfId) throws Exception;
	
	//��ȡ���ֽ��������Ϣ
	public HashMap<String,String> getOnesCfjc(String cfId) throws Exception;
	
	//������˴����ϱ�
	public boolean saveCfjcsh (WjcfGeneralForm form,User user) throws Exception;
	
	//�ύʱͳ��
	public List<HashMap<String,String>> tongjiList();
	
	//�ύ
	public boolean tjSh() throws Exception;
	
	//��ȡ������������б�
	public List<HashMap<String,String>> getCfjcshxxList(String cfid);
	
	
	//��ѯ������˸�λ
	public List<HashMap<String,String>> getSpgwList(User user);
	
	//�Ƿ���߼��û�
	public boolean isZgjyh(User user) throws Exception ;
	
	//�ύ
	public boolean zjtjSh(WjcfGeneralForm form) throws Exception;

}
