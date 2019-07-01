package xsgzgl.pjpy.general.inter.pjsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.pjry.PjszPjryModel;

public interface PjszPjryInterface {

	// ��ñ�ͷ�ļ�(��������_������Ա)
	public List<HashMap<String, String>> getPjszPjryTop(PjszPjryModel model,
			User user);

	// ��ý����(��������_������Ա)
	public ArrayList<String[]> getPjszPjryList(PjpyGeneralForm myForm,
			PjszPjryModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(��������_������Ա)
	public String createPjszPjryHTML(SearchRsModel rsModel,
			PjszPjryModel model, ArrayList<String[]> rsArrList, User user);

	// ���������༶����
	public Boolean saveBjtz(PjszPjryModel model, User user);

	// ���������༶����
	public boolean disfrockPjry(PjszPjryModel model,User user) throws Exception;
	
	// �����������
	public Boolean saveSfcp(PjszPjryModel model, User user);
	
}
