package xsgzgl.szdw.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.dwwh.DwwhJzgModle;
import xsgzgl.szdw.general.dwwh.DwwhModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_���S�o_Interface��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public interface SzdwDwwhInterface {

	// ��ʼ������
	public void initParameter();

	// ��ñ�ͷ�ļ�
	public List<HashMap<String, String>> getDwwhTop(DwwhModel model, User user);

	// ��ý����
	public ArrayList<String[]> getDwwhList(SzdwGeneralForm myForm,
			DwwhModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������
	public String createDwwhHTML(SearchRsModel rsModel, DwwhModel model,
			ArrayList<String[]> rsArrList, User user);
	
	// ����Ա������
	public String countFdyDbj(String zgh);
	
	// �����δ�����
	public String countBzrDbj(String zgh);

	// ��������ά��DIV
	public void createDwwhDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;
	// ��������ά��DIV
	public String createDwwhDivStr(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;
	// �������ά��
	public boolean saveDwwh(DwwhModel model, User user);

	// ɾ������ά��
	public boolean deleteDwwh(DwwhModel model, User user);

	// �����û���DIV
	public void createYhkDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// �����û����û���
	public boolean saveYhk(DwwhModel model, User user);

	// ����ԺУ����DIV
	public String createYxjrDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// �����༶��ϢDIV
	public void createBjxxDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// ����ԺУ����
	public boolean saveYxjr(DwwhModel model, User user);

	// ��ȡ˼��������Ϣ
	public HashMap<String, String> getDwwh(DwwhModel model, User user);

	// �����꼶Div
	public void createNjLvDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// ������������Div
	public void createOtherLvDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;

	// �������༶
	public boolean saveFpbj(DwwhModel model, User user);
	
	// ��������༶
	public boolean disfrockFpbj(DwwhModel model, User user);

	// ��ò����б�
	public List<HashMap<String, String>> getBmList();

	// ������ְ��Option
	public String createJzgOption(DwwhModel model) throws Exception;
	
	// ��ñ�ͷ�ļ�����ࡿ
	public List<HashMap<String, String>> getSetupTop(DwwhModel model, User user);

	// ��ý��������ࡿ
	public ArrayList<String[]> getSetupList(SzdwGeneralForm myForm,
			DwwhModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// �������������ࡿ
	public String createSetupHTML(SearchRsModel rsModel, DwwhModel model,
			ArrayList<String[]> rsArrList, User user);
	
	// ������ְ����ϢDIV
	public void createJzgxxDiv(DwwhModel model, User user,
			HttpServletResponse response) throws Exception;
	//��ѯ��ְ����Ϣ
	public DwwhJzgModle cxJzgxx(DwwhJzgModle model, User user,HttpServletRequest request) throws Exception;
	//�޸Ľ�ְ����Ϣ
	public Boolean updateJzgxx(HttpServletRequest request) throws Exception;
	
	// ����Ա��Ϣά���Զ��嵼��
	public List<HashMap<String,String>> getDwwhExportList(SzdwGeneralForm myForm,User user) throws Exception;
	//�޸ĵ�����ҳ��
	public ArrayList<String[]>  createBjxx(DwwhModel model, User user)throws Exception ;

	/** 
	 * @����:(�鿴��ְ����Ϣ)
	 * @���ߣ�cmj[���ţ�913]
	 * @���ڣ�2013-10-23 ����04:37:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> ckJzgxx(String zgh);
	
	/** 
	 * (�鿴��ְ����Ϣ)�㽭ҽѧ�ߵ�ר��ѧУ
	 */
	public HashMap<String, String> ckJzgxx_13023(String zgh);
	/**
	 * 
	 * @����:TODO(�鿴��ְ����Ϣ)����ʦ��
	 * @���ߣ�����[���ţ�1104]
	 * @���ڣ�2014-8-12 ����11:28:28
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> ckJzgxxJxsf(String zgh);
}
