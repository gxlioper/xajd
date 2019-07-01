package xsgzgl.pjpy.general.inter.pjsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.cpxz.PjszCpxzModel;

public interface PjszCpxzInterface {
	
	// ��ñ�ͷ�ļ�(��������_����С��)
	public List<HashMap<String, String>> getPjszCpxzTop(PjszCpxzModel model,
			User user);

	// ��ý����(��������_����С��)
	public ArrayList<String[]> getPjszCpxzList(PjpyGeneralForm myForm,
			PjszCpxzModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(��������_����С��)
	public String createPjszCpxzHTML(SearchRsModel rsModel,
			PjszCpxzModel model, ArrayList<String[]> rsArrList, User user);
	
	// �������С��
	public Boolean saveCpxz(PjpyGeneralForm myForm, PjszCpxzModel model,
			User user, String saveLx);
	
	// ɾ������С��
	public Boolean deleteCpxz(PjpyGeneralForm myForm, PjszCpxzModel model,
			User user, String saveLx);

	// �������С���Զ�����
	public boolean saveCpxzZdsz(PjszCpxzModel model, User user);
	
	// ������С���ύ
	public String checkCpxzSubmit(PjszCpxzModel model, User user);
}
