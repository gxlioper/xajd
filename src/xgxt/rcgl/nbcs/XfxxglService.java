package xgxt.rcgl.nbcs;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class XfxxglService {
	XfxxglDAO dao = new XfxxglDAO();
	
	/**
	 * ��ȡ��������
	 * @param String tableName
	 * @param String[] col
	 * @return String[]
	 * */
	public String[] getColCN(String tableName,String[] col){
		return dao.getColumnNameCN(col, tableName);
	}
	
	/**
	 * ��ȡѧ��ѧ����Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getXsxfxxbTopTr(){
		String[] colList = {"����","nd","xh","xm","bjmc","qjxf","qtf","qjdgf","Ƿ�ɺϼ�","jfzt"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_nbcs_xsxfxxb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ȡ�̲ķѽ�����Ϣ��ѯ��ͷ
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getJcfjsbTopTr(){
		String[] colList = {"����","nd","xh","xm","nj","xymc","zymc","bjmc","jyje"};
		String[] colListCN = dao.getColumnNameCN(colList, "view_jcfjsb");
		return dao.arrayToList(colList, colListCN);
	}
	
	/**
	 * ��ѯѧ��ѧ����Ϣ
	 * @param XfxxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsxfxxb(XfxxglForm model){
		return dao.selectXsxfxxb(model,null);
	}
	
	/**
	 * ��ѯѧ��ѧ����Ϣ����
	 * @param XfxxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsxfxxbForExp(XfxxglForm model){
		return dao.selectXsxfxxbForExp(model);
	}
	
	
	/**
	 * ����������ѯѧ��ѧ����Ϣ
	 * @param XfxxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryXsxfxxbOne(XfxxglForm model){
		return dao.selectXsxfxxbOne(model);
	}
	
	/**
	 * ����ѧ��ѧ����Ϣ
	 * @param XfxxglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveXsxfxxb(XfxxglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "nbcs_xsxfxxb";
		String primaryKey = "nd||xh";
		String pkValue = model.getNd()+model.getXh();
		String[] columns = {"nd","xh","pycc","xxxs","zkzh","qjxf","qtf","qjdgf","jfzt"};
		String[] values = {model.getNd(),model.getXh(),model.getPycc(),model.getXxxs(),model.getZkzh(),model.getQjxf(),model.getQtf(),model.getQjdgf(),model.getJfzt()};
		
		if(dao.checkExists(tableName, primaryKey,pkValue )){//��¼�Ѿ�����			
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{
			result = StandardOperation.insert(tableName, columns, values, request);
		}		
		return result;
	}
	
	/**
	 * ɾ��ѧ��ѧ����Ϣ
	 * @param XfxxglForm model
	 * @return boolean
	 * */
	public boolean delXsxfxxb(XfxxglForm model){
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];
		boolean result = true;
		
		for(int i=0; i<pkV.length; i++){
			if(StringUtils.isNotNull(pkV[i])){
				sqlArr[i] = "delete from nbcs_xsxfxxb where nd||xh='"  + pkV[i] + "'";
			}
		}
		
		try{
			dao.runBatch(sqlArr);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}
		
		return result;
	}	

	/**
	 * ��ѯ�̲ķѽ�����Ϣ
	 * @param XfxxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryJcfjsb(XfxxglForm model){
		return dao.selectJcfjsb(model,null);
	}
	
	/**
	 * ��ѯ�̲ķѽ�����Ϣ����
	 * @param XfxxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> queryJcfjsbForExp(XfxxglForm model){
		return dao.selectJcfjsbForExp(model);
	}
	
	
	/**
	 * ����������ѯ�̲ķѽ�����Ϣ
	 * @param XfxxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryJcfjsbOne(XfxxglForm model){
		return dao.selectJcfjsbOne(model);
	}
	
	/**
	 * ����̲ķѽ�����Ϣ
	 * @param XfxxglForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean saveJcfjsb(XfxxglForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		String tableName = "jcfjsb";
		String primaryKey = "nd||xh";
		String pkValue = model.getNd()+model.getXh();
		String[] columns = {"nd","xh","jyje"};
		String[] values = {model.getNd(),model.getXh(),model.getJyje()};
		
		if(dao.checkExists(tableName, primaryKey,pkValue )){//��¼�Ѿ�����			
			result = StandardOperation.update(tableName, columns, values, primaryKey, pkValue, request);
		}else{
			result = StandardOperation.insert(tableName, columns, values, request);
		}		
		return result;
	}
	
	/**
	 * ɾ���̲ķѽ�����Ϣ
	 * @param XfxxglForm model
	 * @return boolean
	 * */
	public boolean delJcfjsb(XfxxglForm model){
		String[] pkV = model.getPkV();
		String[] sqlArr = new String[pkV.length];
		boolean result = true;
		
		for(int i=0; i<pkV.length; i++){
			if(StringUtils.isNotNull(pkV[i])){
				sqlArr[i] = "delete from jcfjsb where nd||xh='"  + pkV[i] + "'";
			}
		}
		
		try{
			dao.runBatch(sqlArr);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}
		
		return result;
	}
}
