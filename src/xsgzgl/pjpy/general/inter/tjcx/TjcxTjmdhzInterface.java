package xsgzgl.pjpy.general.inter.tjcx;

import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.tjcx.tjmdhz.TjcxTjmdhzModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_ͳ�Ʋ�ѯ_�Ƽ���������_Interface��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * @version 1.0
 */

public interface TjcxTjmdhzInterface {

	// ��ñ�ͷ�ļ�
	public List<HashMap<String, String>> getTjmdhzTop(TjcxTjmdhzModel model,
			User user);

	// ��ý����
	public ArrayList<String[]> getTjmdhzList(PjpyGeneralForm myForm,
			TjcxTjmdhzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������
	public String createTjmdhzHTML(SearchRsModel rsModel,
			TjcxTjmdhzModel model, ArrayList<String[]> rsArrList, User user);

	// �����񽱽�����
	public void expTjmdhz(PjpyGeneralForm myForm,TjcxTjmdhzModel model,User user, OutputStream os)
			throws Exception;
}
