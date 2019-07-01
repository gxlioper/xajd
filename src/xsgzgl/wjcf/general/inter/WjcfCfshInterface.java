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
import xsgzgl.wjcf.general.cfsbgl.WjcfCfshModel;

public interface WjcfCfshInterface {
	
	
	// ��ñ�ͷ�ļ���������ˡ�
	public List<HashMap<String, String>> getWjcfCfshTop(WjcfCfshModel model,
			User user);
	
	// ��ý������������ˡ�
	public ArrayList<String[]> getWjcfCfshList(WjcfGeneralForm myForm,
			WjcfCfshModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// �����������������ˡ�
	public String createWjcfCfshHTML(SearchRsModel rsModel,
			WjcfCfshModel model, ArrayList<String[]> rsArrList, User user);
	
	public  HashMap<String, String> getCfsb(String cfid) throws Exception;
	
	//��ȡѧ�����ܴ�������б�
	public List<HashMap<String,String>> getYscfqk(String xh);
	
	public List<HashMap<String,String>> getCfsh(String cfid);

	//��ȡ����������
	public List<HashMap<String, String>> getCflbList(WjcfCfshModel model,
			User user);
	
	//���ݴ����������ѯ��λ
	public void showShgwDiv(String cflbdm, User user,
			HttpServletResponse response) throws IOException ;
	
	//��ȡ��һ����λ
	public HashMap<String,String> getHigherUpSpMap(WjcfGeneralForm model,User user);
	
	
	//�ж��Ƿ�����޸�
	public boolean sfKyxg (WjcfGeneralForm model,User user);
	
	//��ȡ��߼���λ
	public HashMap<String,String> getMaxSpgw(WjcfGeneralForm model,User user) throws Exception;
	
	//��ȡһ���������
	public HashMap<String, String> getOnesCfsh(String cfid,String spgw) throws Exception;
	
	//��ȡ������������б�
	public List<HashMap<String,String>> getCfshxx(String cfid,String spgw);
	
	//������˴����ϱ�
	public boolean saveCfsh (WjcfGeneralForm form,User user) throws Exception;
	
	//�ύʱͳ��
	public List<HashMap<String,String>> tongjiList();
	
	//�ύ
	public boolean tjSh() throws Exception;
	
	//�ύ
	public boolean zjtjSh(WjcfGeneralForm form) throws Exception;
	
	//�ж��Ƿ���߼��û�
	public boolean isZgjyh(User user) throws Exception;
}
