package xgxt.studentInfo.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.studentInfo.model.StudentInfoForm;


/**
 * 学生信息统计报表DAO层
 * @author Penghui Qu
 * @version 1.0
 */
public class StuInfoReportDAO extends DAO{

	
	/**
	 * 学生信息表里民族名称为空的记录数
	 * @return String
	 */
	public String getCountByMzmcIsNull() {
		
		String sql = "select count(*) count from view_xsxxb where mzmc is null";
		
		
		return getOneRs(sql, new String[] {}, "count");
	}
	
	
	/**
	 * 学生信息表里户口性质为空的记录数
	 * @return String
	 */
	public String getCountByHkxzIsNull() {
		
		String sql = "select count(*) count from view_xsxxb where Hkxz is null";
		
		
		return getOneRs(sql, new String[] {}, "count");
	}
	
	
	/**
	 * 学生信息表里性别为空的记录数
	 * @return String
	 */
	public String getCountByXbIsNull() {
		
		String sql = "select count(*) count from view_xsxxb where xb is null";
		
		
		return getOneRs(sql, new String[] {}, "count");
	}
	
	
	/**
	 * 按年级、专业统计学生各类别人数
	 * @return List
	 */
	public List<HashMap<String, String>> getStuInfoReportByNjAndZy() {

		String sql = "select a.* from xg_view_xsxx_reportbyzyandnj a  order by xymc,nj,zymc ";

		return super.getList(sql, new String[] {}, new String[] { "xymc", "nj",
				"zymc", "zrs", "zxrs", "fzxrs", "hzrs", "ssmzrs", "wqdmzrs",
				"czrs","ncrs","wqdhk",
				"nsrs", "vsrs", "wqdxbrs", "dyrs", "tyrs", "qtzzmm", "knsrs"});
	}
	
	
	/**
	 * 按班级统计学生各类别人数
	 * @return
	 */
	public List<HashMap<String, String>> getStuInfoReportByBj(
			StudentInfoForm model) {

		String sql = "select a.* from xg_view_xsxx_reportbybj a where exists (select 1 from view_njxyzybj where xydm=? and zydm=? and bjmc=a.bjmc ) ";

		return super.getList(sql, new String[] {model.getXydm(),model.getZydm()}, new String[] { "bjmc","zrs",
				"zxrs", "fzxrs", "hzrs", "ssmzrs", "wqdmzrs", "czrs", "ncrs",
				"wqdhk", "nsrs", "vsrs", "wqdxbrs", "dyrs", "tyrs", "qtzzmm",
				"knsrs" });
	}
	
	
	public List<HashMap<String, String>> getStuInfoReportByXyAndNj() {

		String sql = "select a.* from xg_view_xsxx_reportbyxyandnj a  order by xymc,nj ";

		return super.getList(sql, new String[] {}, new String[] { "xymc", "nj",
				"zrs", "zxrs", "fzxrs", "hzrs", "ssmzrs", "wqdmzrs", "czrs",
				"ncrs", "wqdhk", "nsrs", "vsrs", "wqdxbrs", "dyrs", "tyrs",
				"qtzzmm", "knsrs" });
	}
}
