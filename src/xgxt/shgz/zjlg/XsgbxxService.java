package xgxt.shgz.zjlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The Class XljkZjlgService.
 */
public class XsgbxxService {

	/** The dao. */
	XsgbxxDao dao = new XsgbxxDao();

	/**
	 * Ser_xlzxs add.ѧ���ɲ���Ϣ
	 * @throws Exception 
	 */
	public Boolean ser_xlzxsAdd(XsgbxxModel model,String tableName,HttpServletRequest request) throws Exception {
		return dao.dao_xlzxsAdd(model,tableName,request);
	}
	/**
	 * Ser_xlzxs add.ѧ���ɲ���Ϣ
	 */
	public ArrayList<String[]> ser_xlzxsQuery(XsgbxxModel model,String tableName) throws Exception {
		return dao.dao_xlzxsQuery(model,tableName);
	}
	/**
	 * Ser_xlzxs add.ѧ���ɲ���Ϣ
	 */
	public List<HashMap<String,String>> getToptrTitle(String tableName) throws Exception {
		return dao.getToptrTitle(tableName);
	}
	/**
	 * Ser_xlzxs add.����id��ѯ��Ϣ
	 */
	public HashMap<String, String> ser_idforQuery(String pk,String tableName) throws Exception {
		return dao.dao_idforQuery(pk,tableName);
	}
	/**
	 * .����ɾ��
	 */
	public String dao_AllDelList(String pks,String tableName) throws Exception {
		return dao.dao_AllDelList(pks,tableName);
	}
	/**
	 * .���ݵ���
	 */
	public void dao_expData(String tabName,HttpServletResponse response,XsgbxxModel model) throws Exception {
		 dao.dao_expData(tabName,response,model);
	}
	/**
	 * Ser_xlzxs add.���ѧ����Ϣ��ϯ�ſ�����Ա
	 * @throws Exception 
	 */
	public HashMap<String, String> ser_getxsInfo(String xh) throws Exception {
		return dao.dao_getxsInfo(xh);
	}
	
	public boolean serv_isexistsxlwy(XsgbxxModel model) throws Exception {
		return dao.dao_isexistsxlwy(model);
	}
	/**
	 * Ser_xlzxs add.�Ƿ���ϯ�ſ�����Ա
	 * @throws Exception 
	 */
	public String serv_iszxtkhry(String yhm,String userType) throws Exception {
		return dao.dao_iszxtkhry(yhm,userType);
	}
	/**
	 * Ser_xlzxs add.���������ѯ
	 */
	public ArrayList<String[]> ser_pdyjQuery(XsgbxxModel model,String tableName) throws Exception {
		return dao.dao_pdyjQuery(model,tableName);
	}
	/**
	 * Ser_xlzxs add.�������
	 * @throws Exception 
	 */
	public Boolean ser_pdyjAdd(XsgbxxModel model,String tableName,HttpServletRequest request) throws Exception {
		return dao.dao_pdyjAdd(model,tableName,request);
	}
	/**
	 * Ser_xlzxs add.�Ƿ������Ƭ�ϴ���Ƭ
	 * @throws Exception 
	 */
	public String[] ser_ixexistxzp(String xh) throws Exception {
		return dao.dao_ixexistxzp(xh);
	}
	/**
	 * Ser_xlzxs add.������Ƭ�ϴ���Ƭ
	 * @throws Exception 
	 */
	public boolean ser_addzp(String xh,String savepath,HttpServletRequest request) throws Exception {
		return dao.dao_addzp(xh,savepath,request);
	}
}
