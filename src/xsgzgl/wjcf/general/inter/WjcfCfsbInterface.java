package xsgzgl.wjcf.general.inter;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.cfsbgl.WjcfCfsbModel;

public interface WjcfCfsbInterface {
	// ��ñ�ͷ�ļ��������ϱ���
	public List<HashMap<String, String>> getWjcfCfsbTop(WjcfCfsbModel model,
			User user);
	
	// ��ý�����������ϱ���
	public ArrayList<String[]> getWjcfCfsbfList(WjcfGeneralForm myForm,
			WjcfCfsbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ����������������ϱ���
	public String createWjcfCfsbHTML(SearchRsModel rsModel,
			WjcfCfsbModel model, ArrayList<String[]> rsArrList, User user);
	
	//����
	public String saveCfsb(WjcfGeneralForm form,User user) throws Exception;
	
	//�޸ı���
	public boolean updateCfsb(WjcfGeneralForm form,User user,String cflbdmXt) throws Exception;
	
	public  HashMap<String, String> getCfsb(String cfid) throws Exception;
	
	//��ȡѧ�����ܴ�������б�
	public List<HashMap<String,String>> getYscfqk(String xh);
	
	public List<HashMap<String,String>> getCfsh(String cfid);
	
	//����ɾ��
	public boolean delWjsb(String[] pk);
	
	//��ȡ������������б�
	public List<HashMap<String,String>> getCfshxxList(String cfid);
	
	public InputStream fjCx(WjcfGeneralForm form) throws Exception;
	
	public String checkCfsb(WjcfGeneralForm model);
		
	
	public String[] getSpgwByCflb(String cflbdm);
	
	// �Զ��嵼������ 
	public List<HashMap<String,String>> getWjcfCfsbfExportList(WjcfGeneralForm myForm, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
}
