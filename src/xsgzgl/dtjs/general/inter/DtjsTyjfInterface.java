package xsgzgl.dtjs.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.dtjs.general.DtjsGeneralForm;
import xsgzgl.dtjs.general.tyjf.DtjsTyjfModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ���Ž���_��Ա�ɷ�_Interface��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public interface DtjsTyjfInterface {

	// ��ñ�ͷ�ļ�����Ա�ɷѡ�
	public List<HashMap<String, String>> getDtjsTyjfTop(DtjsTyjfModel model,
			User user);

	// ��ý��������Ա�ɷѡ�
	public ArrayList<String[]> getDtjsTyjfList(DtjsGeneralForm myForm,
			DtjsTyjfModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// �������������Ա�ɷѡ�
	public String createDtjsTyjfHTML(SearchRsModel rsModel,
			DtjsTyjfModel model, ArrayList<String[]> rsArrList, User user);
	
	// �������������Ա�ɷѡ�(�༭)
	public String createDtjsTyjfHTMLofEdit(SearchRsModel rsModel,
			DtjsTyjfModel model, ArrayList<String[]> rsArrList, User user);
	
	// ����ҳ����Ϣ����Ա�ɷѡ�
	public boolean saveTyjf (DtjsTyjfModel model, User user)throws Exception;
	
	// �༭����ҳ����Ϣ����Ա�ɷѡ�
	public boolean saveBjTyjf (DtjsTyjfModel model, User user)throws Exception;
}
