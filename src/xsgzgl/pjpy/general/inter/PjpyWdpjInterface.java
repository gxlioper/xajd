package xsgzgl.pjpy.general.inter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;
import xsgzgl.szdw.general.dwwh.DwwhModel;

public interface PjpyWdpjInterface {

	// ��ñ�ͷ�ļ�(��������_�ҵ�����)
	public List<HashMap<String, String>> getPjpyWdpjTop(PjpyWdpjModel model,
			User user);

	// ��ý����(��������_�ҵ�����)
	public ArrayList<String[]> getPjpyWdpjList(PjpyGeneralForm myForm,
			PjpyWdpjModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(��������_�ҵ�����)
	public String createPjpyWdpjHTML(SearchRsModel rsModel,
			PjpyGeneralForm myForm, PjpyWdpjModel model,
			ArrayList<String[]> rsArrList, User user);

	// ��ʾ�ҵ�������ϸ��Ϣ
	public void showWdpjView(SearchRsModel rsModel, String xmdm, User user,
			HttpServletResponse response) throws IOException;

	// ��ʾ�ҵ�������ϸ��Ϣ
	public void showLnzcInfo(SearchRsModel rsModel, String xmdm, User user,
			HttpServletResponse response) throws IOException;

	// 
	public void showWdpjLssb(String xmdm, User user, String xh,
			HttpServletResponse response) throws IOException;

	// �����ҵ����DIV
	public void createWdshDiv(PjpyWdpjModel model, User user,
			HttpServletResponse response) throws Exception;

}
