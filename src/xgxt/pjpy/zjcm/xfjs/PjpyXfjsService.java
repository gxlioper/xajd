package xgxt.pjpy.zjcm.xfjs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.form.User;

public class PjpyXfjsService {
	PjpyXfjsDAO dao = new PjpyXfjsDAO();
	
	/**
	 * ��ȡ���е�ע��
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList, String tableName){
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * ��ñ�ͷ
	 * @param String tableName
	 * @param String[] colList
	 * @return  List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getTopTr(String tableName,String[] colList) {	
		dao = new PjpyXfjsDAO();
		String[] colListCN = dao.getColumnNameCN(colList, tableName);
		List<HashMap<String, String>> topTr = dao.arrayToList(colList,colListCN);
		
		return topTr;
	}
	
	/**
	 * ��ѯ������ʹ����
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryJclxList(){
		dao = new PjpyXfjsDAO();
		String[] col = new String[]{"jclxdm","jclxmc"};
		return dao.selectDmList("pjpy_xfjs_jclxdmb", col);
	}
	
	/**
	 * ��ѯ������ʹ����
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryQjlxList(){
		dao = new PjpyXfjsDAO();
		String[] col = new String[]{"qjlxdm","qjlxmc"};
		return dao.selectDmList("pjpy_xfjs_qjlxdmb", col);
	}
	
	/**
	 * ��ѯΥ�����ʹ����
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryWjlxList(){
		dao = new PjpyXfjsDAO();
		String[] col = new String[]{"wjlxdm","wjlxmc"};
		return dao.selectDmList("pjpy_xfjs_wjlxdmb", col);
	}
	
	/**
	 * ��ѯѧ����������
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryXsccqkb(PjpyXfjsForm model,String[] colList,User user) throws Exception{	
		dao = new PjpyXfjsDAO();
		return dao.selectXsccqkb(model,colList,user);
	}
	
	/**
	 * ��ѯѧ����������(����ҳ)
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryXsccqkForExport(PjpyXfjsForm model,String[] colList) throws Exception{	
		dao = new PjpyXfjsDAO();
		return dao.selectXsccqkForExport(model,colList);
	}
	
	/**
	 * ������ͳ�Ʋ�ѯ
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryBjccqktjxx(PjpyXfjsForm model,String[] colList) throws Exception{	
		dao = new PjpyXfjsDAO();
		return dao.selectBjccqktjxx(model,colList);
	}
	
	
	/**
	 * ��ѯѧ�����������
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryXskqjljcb(PjpyXfjsForm model,String[] colList) throws Exception{	
		dao = new PjpyXfjsDAO();
		return dao.selectXskqjljcb(model,colList);
	}
	
	/**
	 * ��ѯѧ�����������(����ҳ)
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryXskqxxForExport(PjpyXfjsForm model,String[] colList) throws Exception{	
		dao = new PjpyXfjsDAO();
		return dao.selectXskqjljcbForExport(model,colList);
	}
	
	/**
	 * ѧ������ͳ�Ʋ�ѯ
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> queryXskqqktjxx(PjpyXfjsForm model,String[] colList) throws Exception{	
		dao = new PjpyXfjsDAO();
		return dao.selectXskqqktjxx(model,colList);
	}
	
	/**
	 * �༶����������
	 * @param PjpyXfjsForm model
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean bjccqkSave(PjpyXfjsForm model,HttpServletRequest request) throws Exception{
		boolean result = false;
		try {
			model.setCbv(new String[]{model.getXn()+model.getXq()+model.getBjdm()+model.getCcrq()+model.getJclxdm()+model.getCcyhlx()});
			dao.deleteBjccqkb(model, request);
			result = dao.insertBjccqkb(model,request);
		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ����������ѯ�༶��������Ϣ
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryBjccqkbByPk(String pkValue){
		return dao.selectBjccqkbByPk(pkValue);
	}
	
	/**
	 * ���ݰ༶������������ѯѧ���������б�
	 * @param String bjccPkValue
	 * @return ArrayList<String[]>
	 * */
	public ArrayList<String[]> queryXsccqkbByBj(String bjccPkValue){
		return dao.selectXsccqkb(bjccPkValue);
	}
	
	/**
	 * ����ѧ����������������ѯ
	 * @param String pkValue
	 * @return List<HashMap<String, String>>
	 * */
	public HashMap<String, String> queryXsccqkbByPk(String pkValue){
		return dao.selectXsccqkbByPk(pkValue);
	} 
	
	/**
	 * �༶������ɾ��
	 * @param PjpyXfjsForm model
	 * @return boolean
	 * */
	public boolean deleteXsccqkb(PjpyXfjsForm model,HttpServletRequest request){
		boolean result = false;
		try {
			result = dao.deleteBjccqkb(model,request);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * �༶���������
	 * @param PjpyXfjsForm model
	 * @return boolean
	 * */
	public boolean ccqkAuditing(PjpyXfjsForm model){
		boolean result = false;
		try {
			result = dao.audiBjccqkb(model);
		} catch (Exception e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * ����ѧ����������Ϣ
	 * @param PjpyXfjsForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * */
	public boolean saveXsccqk(PjpyXfjsForm model,HttpServletRequest request){
		boolean result = false;
		try{
			if(model.getXhArr() != null && model.getXhArr().length>0){
				result = dao.saveXsccqkb(model,request);
			}else{
				result = true;
			}
					
			//�޸İ༶��������
			if(result){
				dao.updateBjccqkb(model, request);
			}
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result; 
	}
	
   /**
    * ��ѯ���ܼ��༶��ȫ��ѧ��
    * @param String pk
    * @return ArrayList<String[]>
    * */
   public ArrayList<String[]> queryBjStuList(String pk){
	   return dao.selectBjStuList(pk);
   }
   
   /**
	 * ��ѯ�༶����¼�в�ͬ�û����͵ļ�¼�Ƿ��Ѿ������
	 * @param pkValue
	 * @return boolean
	 * */
   public boolean checkOther(String pkValue){
	   return dao.selectBjccqkfkYhlxRever(pkValue);
   }
   
   /**
    * ��ѯѧ���Ŀ������
    * @param String xh
    * @param String xn
    * @param String xq
    * @param String jcjs ����ɫ ��ѧԺ-xy;ѧУ-xx��
    * @return List<HashMap<String, String>>
    * @throws Exception
    * */
   public List<HashMap<String, String>> getXskqqk(String xh,String xn,String xq,String jcjs) throws Exception{
		PjpyXfjsForm model = new PjpyXfjsForm();
		PjpyXfjsDAO dao = new PjpyXfjsDAO();
		model.setXh(xh);
		model.setXn(xn);
		model.setXq(xq);
		model.setCcyhlx(jcjs);		
		
		return dao.selectXskqqk(model);
	}
   
   /**
	 * ��ѯ�༶������ͳ����ϸ��Ϣ
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
   public List<String[]> queryBjccqktjDetails(String pk, String pkValue){
	   return dao.selectBjccqktjDetails(pk,pkValue);
   }
   
   /**
	 * ��ѯѧ���������ͳ����ϸ��Ϣ
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
  public List<String[]> queryXskqtjDetails(String pk, String pkValue){
	   return dao.selectXskqtjDetails(pk,pkValue);
  }
   
}
