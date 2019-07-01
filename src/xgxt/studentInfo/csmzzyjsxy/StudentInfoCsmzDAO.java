package xgxt.studentInfo.csmzzyjsxy;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政职业技术学院学生信息模块DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-04</p>
 */
public class StudentInfoCsmzDAO {
	DAO dao = DAO.getInstance();
	
	/**
	* Method 根据主键查询某个字段信息
	* @param String xh
	* @return String csd
	* */	
	public String getInfoByPk(String xh,String tableName,String column){
		String sTemp = "";
		String sql = "select " + column + " from " + tableName + " where xh=?";
		
		sTemp = dao.getOneRs(sql, new String[]{xh}, column);
		return sTemp;
	}

	/**
	 * Method 查询档案是否在校
	 * @param String xh
	 * @return String String
	 * */
	public String getDasfzx(String xh){
		String sDasfzx = "";
		//在历届生档案中有记录的取历届生档案中的信息，没有记录的取在校生档案中的信息
		String sql = "select decode(nvl(a.dasfzx,(select b.gzdasfzx from stu_archives b where xh=?)),'0','不在校','1','在校') dasfzx from stu_history_lab a where xh=?";
		sDasfzx = dao.getOneRs(sql,new String[]{xh,xh}, "dasfzx");
		
		return sDasfzx;
	}

	/**
	 * Method 查询学生户口状态
	 * 
	 * @param condition
	 * @return List
	 */
	public List queryHkztInfo(String condition, String[] inputV) {
		List rs = null;
		String[] outputValue = { "xh", "xm", "nj", "xymc", "zymc", "bjmc",
				"hkztmc", "hkqcd", "hkqcsj" };

		String sql = "select xh,xm,nj,xymc,zymc,bjmc,hkztmc,hkqcsj,hkqcd from view_hkzt a "
				+ condition;
		rs = dao.rsToVator(sql, inputV, outputValue);
		return rs;
	}
	
	/**
	 * Method 查询学生户口状态  带分页
	 * 修改人：yjd
	 * @param condition
	 * @return List
	 */
	public List queryHkztInfoPage(String condition, String[] inputV,StudentHkztForm model) throws Exception {
		List rs = null;
		String[] outputValue = { "xh", "xm", "nj", "xymc", "zymc", "bjmc",
				"hkztmc", "hkqcd", "hkqcsj" };

		String sql = "select xh,xm,nj,xymc,zymc,bjmc,hkztmc,hkqcsj,hkqcd,rownum r from view_hkzt a "+condition;
		rs = dao.rsToVator(sql, inputV, outputValue);
		rs=CommonQueryDAO.commonQuery(sql, "", inputV, outputValue, model);
		return rs;
	}
	
	/**
	 * Method 获取户口状态列表
	 * @param condition
	 * @return List
	 * */
	public List getHkztList(){
		List hkztList = null;
		String sql = "select distinct hkztdm,hkztmc from hkztdmb";
		hkztList = dao.getList(sql, new String[]{}, new String[]{"hkztdm","hkztmc"});
		return hkztList;
	}
	
	/**
	 * Method 增加学生户口状态信息
	 * @param model
	 * @return boolean
	 * */
	public boolean addHkztInfo(StudentHkztForm model,HttpServletRequest request){
		boolean flag = false;
		String xh = model.getXh();
		String hkztdm = model.getHkztdm();
		String hkqcsj = model.getHkqcsj();
		String sfjf = model.getSfjf();
		
		String[] columns = {"xh", "hkztdm", "hkqrsj", "hkqcsj", "hkqcd","bz","sfjf"};
		String[] values = {xh, hkztdm, model.getHkqrsj(),hkqcsj, model.getHkqcd(),model.getBz(),sfjf};
		
		flag = StandardOperation.insert("hkztb", columns, values, request);
		
		return flag;
	}
	
	/**
	 * Method 修改学生户口状态信息
	 * @param model	
	 * @return boolean
	 * */
	public boolean modHkztInfo(StudentHkztForm model,HttpServletRequest request){
		boolean flag = false;
		String xh = model.getXh();
		String hkztdm = model.getHkztdm();
		String hkqcsj = model.getHkqcsj();
		String sfjf = model.getSfjf();
		
		String[] columns = {"xh", "hkztdm", "hkqrsj", "hkqcsj", "hkqcd","bz","sfjf"};
		String[] values = {xh, hkztdm, model.getHkqrsj(),hkqcsj, model.getHkqcd(),model.getBz(),sfjf};
		
		try {
			flag = StandardOperation.update("hkztb", columns, values, "xh", xh, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return flag;
	}
	
	/**
	 * Method 删除学生户口状态信息
	 * @param model
	 * @return boolean
	 * */
	public boolean delHkztInfo(StudentHkztForm model,HttpServletRequest request){
		boolean bflag = false;		
		String xh = model.getXh();
		try {
			bflag = StandardOperation.delete("hkztb", "xh", xh, request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		return bflag;
	}
	
	/**
	 * Method getHkztInfoByPk 根据主键查询学生户口状态信息 
	 * @param model
	 * @return String
	 * */
	public HashMap<String, String> getHkztInfoByPk(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from view_hkzt where xh=?";
		String[] outputValue = {"xh","xm","xb","xymc","zymc","bjmc","nj","sfzh","lxdh","syd","hkztdm","hkqcsj","sfjf","hkqrsj","hkqcd","bz"};
		
		map = dao.getMap(sql, new String[]{pkValue}, outputValue);
		return map;
	}
	
	
	/**
	 * 查询某条记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean 
	 * */
	public boolean chkExist(String tableName,String pk,String pkValue){
		boolean flag = false;
		String sql = "select count(*) num from " + tableName + " where " + pk + " ='" + pkValue + "'";
		int iCount = Integer.parseInt(dao.getOneRs(sql, new String[]{}, "num"));
		if(iCount>0){
			flag = true;
		}else{
			flag = false;
		}
			
		return flag;
	}
	
	/**
	 * 获取毕业证发放状态列表
	 * @return List
	 * */
	public List getByzffztList(){
		String sql = "select distinct byzffztdm,byzffztmc from byzffztdmb order by byzffztdm";
		return dao.getList(sql, new String[]{}, new String[]{"byzffztdm", "byzffztmc"});		
	}
}
