package xgxt.jygl.tables;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.CommService;
import xgxt.utils.String.StringUtils;


public class JyglTablesService extends CommService{

	private JyglTablesDAO dao = new JyglTablesDAO();
	
	/**
	 * 查询基本学生信息列表 
	 * @param model
	 * @return
	 */
	public List<String[]> getStudents(JyglTablesForm model,String query,String[] input){
		
		try {
			return dao.getStudents(model,query,input);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/**
	 * 获取课程列表
	 * @return
	 */
	public List<HashMap<String,String>> getKcList(){
		
		return dao.getKcList();
	}
	
	/**
	 * 获取课程列表
	 * @return
	 */
	public List<HashMap<String,String>> getKcListByXh(String xh){
		
		return dao.getKcListByXh(xh);
	}
	
	/**
	 * 获取课程列表
	 * @return
	 */
	public List<HashMap<String,String>> getKcListByBjdm(String bjdm){
		
		return dao.getKcListByBjdm(bjdm);
	}
	
	/**
	 * 批量设置打印课程
	 * @param model
	 * @return
	 */
	public boolean savePlszDykc(JyglTablesForm model){
		
		boolean del = false;
		String[] xkkh = model.getXkkh();
		
		if (StringUtils.isNotNull(model.getBjdm())){
			try {
				del = dao.delDykcByBjdm(model.getBjdm());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (null != xkkh && xkkh.length > 0 && del){
			try {
				return dao.savePlszDykc(model);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 * 单个学生设置打印课程
	 * @param xh
	 * @param model
	 * @return
	 */
	public boolean saveDgszDykc(String xh,JyglTablesForm model){
		boolean del = false;
		String[] xkkh = model.getXkkh();
		
		if (StringUtils.isNotNull(xh)){
			try {
				del = dao.delDykcByXh(xh);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (null != xkkh && xkkh.length > 0 && del){
			try {
				return dao.saveDgszDykc(xh, xkkh);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	/**
	 * 查询所设置打印课程对应的成绩
	 * @param xh
	 * @return
	 */
	public List<HashMap<String,String>> getXscjByXh(String xh){
	
		List<HashMap<String,String>> cjList = dao.getXscjByXh(xh);

		while (cjList.size() < 24){
			HashMap<String,String> map = new HashMap<String,String>();
			map.put("xkkh", "");
			map.put("kcmc", "");
			map.put("cj", "");
			cjList.add(map);
		}
	
		return cjList;
	}
	
	/**
	 * 学生在校职务
	 * @param xh
	 * @return
	 * @throws SQLException
	 */
	public String getXszwByXh(String xh) throws SQLException{
		
		String[] bjgbmc = dao.getXszwByXh(xh);
		
		if (null != bjgbmc && bjgbmc.length > 0){
			return Arrays.toString(bjgbmc).replace("[", "").replace("]", "");
		} else {
			return "";
		}
		
	}
}
