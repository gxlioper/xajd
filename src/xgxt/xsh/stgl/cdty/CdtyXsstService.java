package xgxt.xsh.stgl.cdty;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class CdtyXsstService extends CommService{
	
	CdtyXsstDAO dao=new CdtyXsstDAO();
	/**
	 * 获取社团信息列表
	 * @return
	 */
	public List<HashMap<String,String>>getStxxList(CdtyXsstForm model){
		 
		return dao.getStxxList(model);
	}
	
	/**
	 * 
	 * @param model
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 */
	public ArrayList<String[]> getStxxInfo(CdtyXsstForm model)
			throws Exception{
		
		return dao.getStxxInfo(model);
	}
	
	/**
	 * 获取社团详细信息
	 * 
	 * @return
	 */
	public HashMap<String, String> getStInfoList(CdtyXsstForm model) {

		return dao.getStInfoList(model);
	}
	
	/**
	 * 根据类型获取表头
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getTopTr(String lx){
		
		DAO dao=DAO.getInstance();
		
		String[]en=null;
		String[]cn=null;
		
		//选择社团
		if("xzst".equalsIgnoreCase(lx)){
			en=new String[]{"stdm", "stmc", "stxz", "stclsj", "zdls"};
			cn=new String[]{"社团代码","社团名称","社团性质","社团成立时间","指导老师"};
		//学生查询
		}else if("sqcx".equalsIgnoreCase(lx)){
			en=new String[]{"pkValue","xh", "xm", "nj", "xymc", "zymc","bjmc","stmc","shzt"};
			cn=new String[]{"主键","学号","姓名","年级",Base.YXPZXY_KEY,"专业","班级","社团名称","审核状态"};
		}
		
		
		return dao.arrayToList(en, cn);
	}
	
	public boolean saveXsstInfo(CdtyXsstForm model) throws Exception{
		
		return dao.saveXsstInfo(model);
	}
	

	/**
	 * 获取学生申报社团信息
	 * 
	 * @return
	 */
	public ArrayList<String[]> getXsstInfo(CdtyXsstForm model)
			throws Exception {

		return dao.getXsstInfo(model);
	}
	
	/**
	 * 删除学生申请社团信息
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean delXsstInfo(CdtyXsstForm model) throws Exception {
		
		return dao.delXsstInfo(model);
	}
	
	/**
	 * 获取学生申请社团信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getXsstMap(CdtyXsstForm model){
		return dao.getXsstMap(model);
	}
	
	public boolean updateShzt(CdtyXsstForm model) throws Exception {
		return dao.updateShzt(model);
	}
	
	/**
	 * 社团干部列表获取
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getStgbList(CdtyXsstForm model){

		return dao.getStgbList(model);
	}
	
	/**
	 * 获取学生申请社团信息
	 * @param model
	 * @return
	 */
	public HashMap<String,String> getStxsByXh(CdtyXsstForm model){

		return dao.getStxsByXh(model);
	}
	
	/**
	 * 社团列表()
	 * @param model
	 * @return
	 */
	public List<HashMap<String,String>>getStByXh(CdtyXsstForm model){

		return dao.getStByXh(model);
	}
	
	public boolean saveXsstgb(CdtyXsstForm model) throws Exception{
		
		return dao.saveXsstgb(model);
	}
	
	/**
	 * 获取社团干部信息
	 * 
	 * @return
	 */
	public ArrayList<String[]> getStgbInfo(CdtyXsstForm model)
			throws Exception {

		return dao.getStgbInfo(model);
	}
	
	public boolean checkStgb(CdtyXsstForm model) {

		String num = dao.checkStgb(model);

		boolean blog = true;
		if (!"0".equalsIgnoreCase(num)) {
			blog = false;
		}
		return blog;

	}
}
