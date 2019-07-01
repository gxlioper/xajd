package xsgzgl.szdw.general.inter;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.szdw.general.SzdwGeneralForm;
import xsgzgl.szdw.general.szbb.SzbbModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ˼������_˼�����_Interface��
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

public interface SzdwSzbbInterface {

	// ��ñ�ͷ�ļ�
	public List<HashMap<String, String>> getSzbbTop(SzbbModel model, User user);
	public List<HashMap<String, String>> getSzBzrbbTop(SzbbModel model, User user);
	
	
	public List<HashMap<String, String>> getSzbbSetTop(SzbbModel model, User user);
	public List<HashMap<String, String>> getSzbbSetTop1(SzbbModel model, User user);

	// ��ý����
	public ArrayList<String[]> getSzbbList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ��ý����
	public ArrayList<String[]> getSzBzrbbList(SzdwGeneralForm myForm,
										   SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������
	public String createSzbbHTML(SearchRsModel rsModel, SzbbModel model,
			ArrayList<String[]> rsArrList, User user,boolean isfdy);
	
	/**
	 * ���ݰ༶��ȡ�ѷ�����İ�����
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getYfpFdy(String bjdm);

	/**
	 * �����ѷ��丨��Աhtml
	 * @param bjdm
	 * @return
	 */
	public String createYfpFdyHTML(String bjdm);
	
	/**
	 * ���ݰ༶��ȡ�ѷ�����İ�����
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getYfpBzr(String bjdm);
	
	/**
	 * �����ѷ��������html
	 * @param bjdm
	 * @return
	 */
	public String createYfpBzrHTML(String bjdm);
	
	/**
	 * ���ݰ༶��ȡδ������İ�����
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getWfpFdy(String bjdm);
	
	/**
	 * ���ݰ༶��ȡδ������İ�����
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getWfpBzr(String bjdm);
	
	/**
	 * ��û��˼�����S�o�����
	 * 
	 * @date 2013-01-09
	 * @author ΰ�����
	 */
	public List<String[]> getWfpFdyList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
	
	/**
	 * ����δ����༶����Ա�����
	 * @date 2013-01-09
	 * @author qlj
	 */
	public String createWfpFdyHTML(SearchRsModel rsModel, SzbbModel model,
			ArrayList<String[]> rsArrList, User user);
	
	/**
	 * ��ȡδ����༶�����ν����
	 * @date 2013-01-05
	 * @author qlj
	 */
	public List<String[]> getWfpBzrList(SzdwGeneralForm myForm,
			SzbbModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
	
	/**
	 * ����δ����༶�����ν����
	 * @date 2013-01-15
	 * @author qlj
	 */
	public String createWfpBzrHTML(SearchRsModel rsModel, SzbbModel model,
			ArrayList<String[]> rsArrList, User user);
	
	/**
	 * ȡ����Ա���
	 * @date 2013-01-15
	 * @author qlj
	 */
	public boolean  cancelFdybb(String zgh,String bjdm) throws Exception;
	
	/**
	 * ȡ�������α��
	 * @date 2013-01-15
	 * @author qlj
	 */
	public boolean  cancelBzrbb(String zgh,String bjdm) throws Exception;
		
	/**
	 * ����Ա���
	 * @date 2013-01-15
	 * @author qlj 
	 */
	public boolean  setFdybb(String zgh,String bjdm) throws Exception;
	
	/**
	 * �����α��
	 * @date 2013-01-15
	 * @author qlj
	 */
	public boolean  setBzrbb(String zgh,String bjdm) throws Exception;
	
	public List<HashMap<String,String>> getSzdwbbExportList(SzdwGeneralForm myForm,User user) throws Exception;
}
