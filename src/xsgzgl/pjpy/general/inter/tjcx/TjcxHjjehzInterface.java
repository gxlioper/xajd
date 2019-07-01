package xsgzgl.pjpy.general.inter.tjcx;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletOutputStream;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.hjjehz.TjcxHjjehzModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʋ�ѯ_�񽱽�����_Interface��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author ΰ�����
 * @version 1.0
 */

public interface TjcxHjjehzInterface {

	// ��ñ�ͷ�ļ�
	public List<HashMap<String, String>> getHjjehzTop(TjcxHjjehzModel model,
			User user);

	// ��ý����
	public ArrayList<String[]> getHjjehzList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������
	public String createHjjehzHTML(SearchRsModel rsModel,
			TjcxHjjehzModel model, ArrayList<String[]> rsArrList, User user);

	// �����񽱽�����
	public void expHjjehz(TjcxHjjehzModel model, OutputStream os)
			throws Exception;

	
	// ��ý�����������
	public ArrayList<String[]> getCMHJMDHZList(PjpyGeneralForm myForm,
			TjcxHjjehzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;
	// ��ñ�ͷ�ļ�
	public List<HashMap<String, String>> getCMHJMDHZTop(TjcxHjjehzModel model,
			User user);

	// ������ý����������
	public void expCmhjmchz(TjcxHjjehzModel model, OutputStream os)
			throws Exception;
}
