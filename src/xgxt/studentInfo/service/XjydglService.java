package xgxt.studentInfo.service;

import java.util.List;

import xgxt.form.CommanForm;
import xgxt.studentInfo.dao.XjydglDAO;
import xgxt.studentInfo.model.StudentInfoForm;


/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ѧ��ѧ���춯����Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: ����</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2010-06-1</p>
 */
public class XjydglService {
	
	/**
	 * ��ȡ�����ͼ�ֶε�ע����Ϣ
	 * @param tableName
	 * @param colList
	 * @return String[]
	 * */
	public String[] getColumnComment(String tableName, String[] colList){
		XjydglDAO dao = new XjydglDAO();
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * ��ѯѧ��ѧ���춯��Ϣ
	 * @param tableName
	 * @param model
	 * @param outputCol
	 * @return List<String[]>
	 * */
	public List<String[]> queryXjydxx(String tableName, 
									  StudentInfoForm model, 
			                          String[] outputCol){
		XjydglDAO dao = new XjydglDAO();
		return dao.selectXjydxx(tableName, model, outputCol);
	}
}
