package xgxt.studentInfo.dao;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.studentInfo.model.StudentInfoForm;


/**
 * ѧ����Ϣͳ�Ʊ���DAO��
 * @author Penghui Qu
 * @version 1.0
 */
public class StuInfoReportDAO extends DAO{

	
	/**
	 * ѧ����Ϣ������������Ϊ�յļ�¼��
	 * @return String
	 */
	public String getCountByMzmcIsNull() {
		
		String sql = "select count(*) count from view_xsxxb where mzmc is null";
		
		
		return getOneRs(sql, new String[] {}, "count");
	}
	
	
	/**
	 * ѧ����Ϣ���ﻧ������Ϊ�յļ�¼��
	 * @return String
	 */
	public String getCountByHkxzIsNull() {
		
		String sql = "select count(*) count from view_xsxxb where Hkxz is null";
		
		
		return getOneRs(sql, new String[] {}, "count");
	}
	
	
	/**
	 * ѧ����Ϣ�����Ա�Ϊ�յļ�¼��
	 * @return String
	 */
	public String getCountByXbIsNull() {
		
		String sql = "select count(*) count from view_xsxxb where xb is null";
		
		
		return getOneRs(sql, new String[] {}, "count");
	}
	
	
	/**
	 * ���꼶��רҵͳ��ѧ�����������
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
	 * ���༶ͳ��ѧ�����������
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
