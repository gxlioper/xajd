package xgxt.studentInfo.ahjzgyxy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Title: ѧ����������ϵͳ</p>
 * <p>Description: ���ս���ְҵ����ѧԺѧ����Ϣservice
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-09-08</p>
 */
public class XsxxAhjzgyxyService {
	/**
	 * ѧ����������ѯ��ͷ
	 */
	public  List<HashMap<String, String>> serv_getStuTypeManageTitle(){
		XsxxAhjzgyxyDAO dao = new XsxxAhjzgyxyDAO();
		return dao.getStuTypeManageTitle();
	}
	/**
	 * ѧ����������ѯ
	 */
	public  ArrayList<List> serv_getStuTypeManage(StuTypeModel model,String userType){
		XsxxAhjzgyxyDAO dao = new XsxxAhjzgyxyDAO();
		return dao.getStuTypeManage(model,userType);
	}
	/**
	 * ��ȡ���ʹ���id
	 */
	public String getLxChecked(StuTypeModel model) throws Exception {
		XsxxAhjzgyxyDAO dao = new XsxxAhjzgyxyDAO();
		return dao.getLxChecked(model);
	}
	/**
	 * ѧ�����ౣ��
	 * @throws SQLException 
	 */
	public boolean serv_stuTypeSave(StuTypeModel model) throws SQLException{
		XsxxAhjzgyxyDAO dao = new XsxxAhjzgyxyDAO();
		return dao.dao_stuTypeSave(model);
	}
}
