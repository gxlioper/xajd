package xgxt.studentInfo.dao;

import java.util.List;

import xgxt.DAO.DAO;

public class XsxxXxtjglDAO extends DAO{
	
	/**
	 * 查询学院学生信息统计信息
	 * @param output 输出的字段 
	 * @param topLen 取的数据条数
	 * */
	public List<String[]> selectXyXsxxtj(String[] output, int topLen){
		StringBuilder sql = new StringBuilder("select xymc,xydm, ");
		sql.append("(select count(*) from view_xsjbxx b where a.xydm=b.xydm)zrs,");
        sql.append("(select count(*) from view_xsjbxx b where a.xydm=b.xydm and b.xb='男')boyrs,");
        sql.append("(select count(*) from view_xsjbxx b where a.xydm=b.xydm and b.xb='女')girlrs,");
        sql.append("(select count(*) from xsxxb b where a.xydm=b.xydm and b.sfzx='是')zxrs,");
        sql.append("(select count(*) from xsxxb b where a.xydm=b.xydm and b.sfzx='是' and (b.xb='男' or b.xb='1'))zxboyrs,");
        sql.append("(select count(*) from xsxxb b where a.xydm=b.xydm and b.sfzx='是' and (b.xb='女' or b.xb='2'))zxgirlrs ");
        sql.append("from view_njxyzybj	a group by xymc,xydm");
		
		return rsToVator(sql.toString(), new String[]{}, output);
	}
	
}
