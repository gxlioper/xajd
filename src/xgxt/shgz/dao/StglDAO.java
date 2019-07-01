package xgxt.shgz.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.shgz.dao.common.CommonDAO;
import xgxt.shgz.model.XjqnzyzModel;

public class StglDAO extends CommonDAO{
	DAO dao = DAO.getInstance();
	public List<HashMap<String, String>> getComboList2(String xydm) {
		// 获取所属学院社团列表
		String sql = "select stdm,stmc from view_stsqdj where shzt = '通过' and xydm = ?";
		return dao.getList(sql, new String[] {xydm}, new String[] { "stdm", "stmc" });
	}
	public ArrayList<String[]>xjzyzQuerry(XjqnzyzModel model) {
		DAO dao = DAO.getInstance();
		String xydm = DealString.toGBK(model.getXydm());
		String nj = DealString.toGBK(model.getNj());
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		
		String xh = model.getXh();		
		String xm = model.getXm();			
		StringBuffer query = new StringBuffer();
		query.append(Base.isNull(xydm) ? " and 1=1" : " and b.xydm='"+xydm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and b.nj='"+nj+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and b.zydm='"+zydm+ "'");	
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and b.bjdm='"+bjdm+ "'");	
		query.append(Base.isNull(xh) ? " and 1=1" : " and b.xh = '"+xh.trim()+ "'");	
		query.append(Base.isNull(xm) ? " and 1=1" : " and b.xm like '%"+xm.trim()+"%'");	
		
		StringBuffer sql = new StringBuffer();
		/*  
		 “一星级青年志愿者”应正式参加青年志愿者服务一年以上，志愿服务时间累积40小时以上
         “二星级青年志愿者”应正式参加青年志愿者服务一年以上，志愿服务时间累积100小时以上
         “三星级青年志愿者”应正式参加青年志愿者服务两年以上，志愿服务时间累积150小时以上
        */
		sql.append(" select (case when (datedif>=730 and cout>=150) then '三星级青年志愿者'  when ");
		sql.append(" (datedif>=365 and cout>=100) then '二星级青年志愿者'  "); 
		sql.append(" when (datedif>=365 and cout>=40) then '一星级青年志愿者' else '无评级' end )dj, ");
		sql.append(" to_char(cjsj,'YYYY-MM-DD')cjsj,cout,b.xh,b.xm,b.xb,b.nj,b.xymc,b.zymc,b.bjmc ");
		sql.append(" from (select round(curdate-cjsj)datedif,cjsj,cout,xh from ( ");
		sql.append(" select to_date(to_char(sysdate,'yyyymmdd'),'YYYYMMDD')curdate, ");
		sql.append(" (select to_date(nvl(b.cjzyzfwsj,to_char(sysdate,'yyyymmdd')),'YYYYMMDD')  ");
		sql.append(" from zyzfwdjb b where b.xh=a.xh and rownum=1)cjsj, ");
		sql.append(" sum(is_ret_number(fwl))cout,xh from zyzfwdjb a  group by xh) ");
		sql.append(" )a,view_xsjbxx b where a.xh=b.xh ");
//		String[] colCn = new String[]{"学号","姓名","性别","院系","班级","加入志愿者时间","服务总时","评级"};
		return dao.rsToVator(sql+query.toString(), new String[]{},new String[]{"xh","xm","xb","xymc","bjmc","cjsj","cout","dj"});
	}
}
