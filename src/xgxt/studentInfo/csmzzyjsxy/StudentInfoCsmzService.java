package xgxt.studentInfo.csmzzyjsxy;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.base.Excel2Oracle;
import xgxt.comm.search.SearchService;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 长沙民政职业技术学院学生信息模块Service</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-06-04</p>
 */
public class StudentInfoCsmzService {
	StudentInfoCsmzDAO stuDao = new StudentInfoCsmzDAO();
	/**
	 * Method 获取学生的详细信息
	 * @param String xh
	 * @return HashMap map 
	 * */
	public HashMap<String, String> getDetailsInfoSelf(String xh){
		HashMap<String, String> map = new HashMap<String, String>();		
		map.put("hkqcsj", stuDao.getInfoByPk(xh, "view_hkzt", "hkqcsj"));
		map.put("dasfzx", stuDao.getDasfzx(xh));
		return map;
	}
	
	/**
	 * Method getBjKey 组合查询班级的参数值
	 * @param model
	 * @return String
	 * */
	public String getBjKey(StudentHkztForm model){
		String bjKey = "";
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String nj = model.getNj();
		
		xydm = xydm==null ||"".equalsIgnoreCase(xydm) ? "" : xydm;
		zydm = zydm==null ||"".equalsIgnoreCase(zydm) ? "" : zydm;
		nj = nj==null ||"".equalsIgnoreCase(nj) ? "" : nj;
		bjKey = xydm+ "!!" + zydm + "!!" + nj;
		
		return bjKey;
	}
	
	public String getQueryCondition(StudentHkztForm model){
		String sql = "";
		
		String xh = model.getXh();
		String xm = model.getXm();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String hkztdm = model.getHkztdm();
		String sfjf = model.getSfjf();
		String hkqcsj = model.getHkqcsj();
		
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		sb.append(xh==null || "".equalsIgnoreCase(xh) ? "" : " and xh like '%" + DealString.replaceImmitStr(xh) + "%'");
		sb.append(xm==null || "".equalsIgnoreCase(xm) ? "" : " and xm like '%" + DealString.replaceImmitStr(xm) + "%'");
		sb.append(xydm==null || "".equalsIgnoreCase(xydm) ? "" : " and xydm='" + xydm + "'");
		sb.append(zydm==null || "".equalsIgnoreCase(zydm) ? "" : " and zydm='" + zydm + "'");
		sb.append(bjdm==null || "".equalsIgnoreCase(bjdm) ? "" : " and bjdm='" + bjdm + "'");
		sb.append(nj==null || "".equalsIgnoreCase(nj) ? "" : " and nj='" + nj + "'");
		sb.append(hkztdm==null || "".equalsIgnoreCase(hkztdm) ? "" : " and hkztdm='" + hkztdm + "'");
		sb.append(sfjf==null || "".equalsIgnoreCase(sfjf) ? "" : " and sfjf='" + sfjf + "'");
		sb.append(hkqcsj==null || "".equalsIgnoreCase(hkqcsj) ? "" : " and hkqcsj='" + hkqcsj + "'");
		
		sql = sb.toString();		
		return sql;		
	}
	
	/**
	 * Method queryHkztInfo 模糊查询学生户口状态信息
	 * 
	 * @param model
	 * @return String
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public StudentHkztForm queryHkztInfo(StudentHkztForm model,
			HttpServletRequest request) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException ,Exception {
		DAO dao = DAO.getInstance();
		List rs = null;
		String sCondition = "";
		String[] colList = { "xh", "xm", "nj", "xymc", "zymc", "bjmc",
				"hkztmc", "hkqcd", "hkqcsj" };
		String[] colListCN = dao.getColumnNameCN(colList, "view_hkzt");
		List topTr = dao.arrayToList(colList, colListCN);

		sCondition = this.getQueryCondition(model);

		// 高级查询
		SearchService searchService = new SearchService();
		String searchTjByUser = searchService.getSearchTjByUser(request, "a", "xydm", "bjdm");

		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());

		sCondition += searchTj;
		sCondition += searchTjByUser;
		
		//rs = stuDao.queryHkztInfo(sCondition,inputV);
		rs = stuDao.queryHkztInfoPage(sCondition,inputV,model);
		model.setRs(rs);
		model.setTopTr(topTr);

		return model;
	}
	
	/**
	 * Method queryHkztInfo 模糊查询学生户口状态信息 
	 * @param model
	 * @return String
	 * */
	public HashMap<String, String> getHkztInfoByPk(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		map = stuDao.getHkztInfoByPk(pkValue);
		return map;
	}
	
	/**
	 * Method  获取户口状态列表
	 * @return List
	 * */
	public List getHkztList(){
		List hkztList = null;
		hkztList = stuDao.getHkztList();
		return hkztList;
	}
	
	/**
	 * Method 增加学生户口状态信息
	 * @param model
	 * @return boolean
	 * */
	public boolean addHkztInfo(StudentHkztForm model,HttpServletRequest request){
		boolean flag = false;
		flag = stuDao.addHkztInfo(model,request);
		return flag;
	}
	
	/**
	 * Method 修改学生户口状态信息
	 * @param model
	 * @return boolean
	 * */
	public StudentHkztForm modHkztInfo(StudentHkztForm model,HttpServletRequest request){
		boolean bflag = false;		
		bflag = stuDao.modHkztInfo(model,request);
		model.setFlag(bflag);
		model.setMap(this.getHkztInfoByPk(model.getXh()));		
		return model;
	}
	
	/**
	 * Method 删除学生户口状态信息
	 * @param model
	 * @return boolean
	 * */
	public StudentHkztForm delHkztInfo(StudentHkztForm model,HttpServletRequest request){
		boolean bflag = false;		
		bflag = stuDao.delHkztInfo(model,request);
		model.setFlag(bflag);			
		return model;
	}
	
	/**
	 * Method 导出学生户口状态信息
	 * @param model
	 * @return boolean
	 * */
	public StudentHkztForm expDate(StudentHkztForm model,HttpServletResponse response){
		String sql = "select * from view_hkzt " + this.getQueryCondition(model);
		response.reset();
		response.setContentType("application/vnd.ms-excel");
		try {
			Excel2Oracle.exportData(sql, "view_hkzt", response.getOutputStream());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 获取毕业证发放状态列表
	 * @return List
	 * */
	public List getByzffztList(){
		return stuDao.getByzffztList();
	}
}
