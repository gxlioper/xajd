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
	 * 获取表列的注释
	 * @param String[] colList
	 * @param String tableName
	 * @return String[]
	 * */
	public String[] getColumnNameCN(String[] colList, String tableName){
		return dao.getColumnNameCN(colList, tableName);
	}
	
	/**
	 * 获得表头
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
	 * 查询检查类型代码表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryJclxList(){
		dao = new PjpyXfjsDAO();
		String[] col = new String[]{"jclxdm","jclxmc"};
		return dao.selectDmList("pjpy_xfjs_jclxdmb", col);
	}
	
	/**
	 * 查询检查类型代码表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryQjlxList(){
		dao = new PjpyXfjsDAO();
		String[] col = new String[]{"qjlxdm","qjlxmc"};
		return dao.selectDmList("pjpy_xfjs_qjlxdmb", col);
	}
	
	/**
	 * 查询违纪类型代码表
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryWjlxList(){
		dao = new PjpyXfjsDAO();
		String[] col = new String[]{"wjlxdm","wjlxmc"};
		return dao.selectDmList("pjpy_xfjs_wjlxdmb", col);
	}
	
	/**
	 * 查询学生抽查情况表
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
	 * 查询学生抽查情况表(不分页)
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
	 * 抽查情况统计查询
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
	 * 查询学生考勤情况表
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
	 * 查询学生考勤情况表(不分页)
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
	 * 学生考勤统计查询
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
	 * 班级抽查情况保存
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
	 * 根据主键查询班级抽查情况信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryBjccqkbByPk(String pkValue){
		return dao.selectBjccqkbByPk(pkValue);
	}
	
	/**
	 * 根据班级抽查情况主键查询学生抽查情况列表
	 * @param String bjccPkValue
	 * @return ArrayList<String[]>
	 * */
	public ArrayList<String[]> queryXsccqkbByBj(String bjccPkValue){
		return dao.selectXsccqkb(bjccPkValue);
	}
	
	/**
	 * 根据学生抽查情况表主键查询
	 * @param String pkValue
	 * @return List<HashMap<String, String>>
	 * */
	public HashMap<String, String> queryXsccqkbByPk(String pkValue){
		return dao.selectXsccqkbByPk(pkValue);
	} 
	
	/**
	 * 班级抽查情况删除
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
	 * 班级抽查情况审核
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
	 * 保存学生抽查情况信息
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
					
			//修改班级抽查情况表
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
    * 查询接受检查班级的全部学生
    * @param String pk
    * @return ArrayList<String[]>
    * */
   public ArrayList<String[]> queryBjStuList(String pk){
	   return dao.selectBjStuList(pk);
   }
   
   /**
	 * 查询班级抽查记录中不同用户类型的记录是否已经处理过
	 * @param pkValue
	 * @return boolean
	 * */
   public boolean checkOther(String pkValue){
	   return dao.selectBjccqkfkYhlxRever(pkValue);
   }
   
   /**
    * 查询学生的考勤情况
    * @param String xh
    * @param String xn
    * @param String xq
    * @param String jcjs 检查角色 （学院-xy;学校-xx）
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
	 * 查询班级抽查情况统计详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
   public List<String[]> queryBjccqktjDetails(String pk, String pkValue){
	   return dao.selectBjccqktjDetails(pk,pkValue);
   }
   
   /**
	 * 查询学生考勤情况统计详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
  public List<String[]> queryXskqtjDetails(String pk, String pkValue){
	   return dao.selectXskqtjDetails(pk,pkValue);
  }
   
}
