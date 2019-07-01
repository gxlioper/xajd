package xsgzgl.pjpy.general.inter.tjcx;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.hjmdhz.TjcxHjmdhzModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʋ�ѯ_����������_Interface��
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

public interface TjcxHjmdhzInterface {

	// ��ñ�ͷ�ļ�
	public List<HashMap<String, String>> getHjmdhzTop(TjcxHjmdhzModel model,
			User user);

	// ��ý����
	public ArrayList<String[]> getHjmdhzList(PjpyGeneralForm myForm,
			TjcxHjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������
	public String createHjmdhzHTML(SearchRsModel rsModel,
			TjcxHjmdhzModel model, ArrayList<String[]> rsArrList, User user);

	// ��������������
	public void expHjmdhz(TjcxHjmdhzModel model, OutputStream os)
			throws Exception;
}
