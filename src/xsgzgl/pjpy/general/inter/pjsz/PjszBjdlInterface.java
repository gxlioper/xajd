package xsgzgl.pjpy.general.inter.pjsz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.SearchRsModel;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.bjdl.PjszBjdlModel;

public interface PjszBjdlInterface {

	// ��ñ�ͷ�ļ�(��������_�༶����)
	public List<HashMap<String, String>> getPjszBjdlTop(PjszBjdlModel model,
			User user);

	// ��ý����(��������_�༶����)
	public ArrayList<String[]> getPjszBjdlList(PjpyGeneralForm myForm,
			PjszBjdlModel model, User user) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException;

	// ���������(��������_�༶����)
	public String createPjszBjdlHTML(SearchRsModel rsModel,
			PjszBjdlModel model, ArrayList<String[]> rsArrList, User user);

	// ����༶����
	public Boolean saveBjdl(PjpyGeneralForm myForm, PjszBjdlModel model,
			User user, String saveLx);

	// ɾ���༶����
	public Boolean deleteBjdl(PjpyGeneralForm myForm, PjszBjdlModel model,
			User user, String saveLx);

}
