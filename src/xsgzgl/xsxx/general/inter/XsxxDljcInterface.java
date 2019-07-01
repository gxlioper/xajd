package xsgzgl.xsxx.general.inter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlModel;
import xsgzgl.xsxx.cssz.XsxxCsszForm;
import xsgzgl.xsxx.general.XsxxGeneralForm;
import xsgzgl.xsxx.general.dljc.XsxxDljcModel;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ѧ����Ϣ_��¼���_Interface��
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

public interface XsxxDljcInterface {

	// ===================��ѯҳ�� begin=============================

	// ��ñ�ͷ�ļ�����¼��⡿
	public List<HashMap<String, String>> getXsxxDljclTop(XsxxDljcModel model,
			User user);

	// ��ý��������¼��⡿
	public ArrayList<String[]> getXsxxDljcList(XsxxGeneralForm myForm,
			XsxxDljcModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// �������������¼��⡿
	public String createXsxxDljcHTML(SearchRsModel rsModel,
			XsxxDljcModel model, ArrayList<String[]> rsArrList, User user);

	// ���õ�¼���
	public boolean resetDljc(XsxxGeneralForm myForm, XsxxDljcModel model,
			User user);

	// ===================��ѯҳ�� end=============================

	// ===================����ҳ�� begin=============================

	// �����ֶ�����Div
	public void createZdszDiv(XsxxDljcModel model, User user,
			HttpServletResponse response) throws IOException;

	// �����ֶ�����
	public boolean saveZdsz(XsxxDljcModel model, User user);

	// ===================����ҳ�� end=============================

	// ===================��Ϣ���� begin=============================

	// ��Ϣ�Ƿ�����
	public boolean isXxws(String xh);

	// ������Ϣ����Div
	public void createXxwsDiv(XsxxDljcModel model, User user,
			HttpServletResponse response) throws IOException;

	// ����������Ϣ
	public boolean saveXxws(User user, HttpServletRequest request);

	// ===================��Ϣ���� end=============================
}
