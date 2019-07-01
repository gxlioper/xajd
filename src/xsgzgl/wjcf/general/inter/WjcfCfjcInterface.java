package xsgzgl.wjcf.general.inter;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.wjcf.general.WjcfGeneralForm;
import xsgzgl.wjcf.general.cfjcgl.WjcfCfjcModel;

public interface WjcfCfjcInterface {
	// ��ñ�ͷ�ļ������ֽ����
	public List<HashMap<String, String>> getWjcfCfjcTop(WjcfCfjcModel model,
			User user);
	
	// ��ý���������ֽ����
	public ArrayList<String[]> getWjcfCfjcList(WjcfGeneralForm myForm,
			WjcfCfjcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ��������������ֽ����
	public String createWjcfCfjcHTML(SearchRsModel rsModel,
			WjcfCfjcModel model, ArrayList<String[]> rsArrList, User user);
	
	//����
	public boolean saveJcSq(WjcfGeneralForm form) throws Exception;
	
	//�޸�
	public boolean updateJcSq(WjcfGeneralForm form) throws Exception;
	
	//����ɾ��
	public boolean delJcSq(String cfId) throws Exception;
	
	public HashMap<String, String> getJcSq(String cfId) ;
	
	public InputStream fjCx(WjcfGeneralForm form) throws Exception;
	
	// �Զ��嵼�������ֽ��ά����
	public List<HashMap<String,String>> getWjcfCfjcExportList(WjcfGeneralForm myForm,
			WjcfCfjcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	/** 
	 * @����:(�������߸���)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-8-30 ����02:37:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * InputStream �������� 
	 * @throws 
	 */
	public InputStream ssFjxz(WjcfGeneralForm myForm) throws Exception;
}
