package xsgzgl.pjpy.general.tjcx.xyhjtj;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

/**
 * <p>
 * Title: 学生工作管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * <p>
 * Author: wujian
 * </p>
 * <p>
 * Version: 1.0
 * </p>
 * <p>
 * Time:2012-7-28 下午14:19:22
 * </p>
 */

public class XyhjtjDAO extends CommDAO {

	/**
	 * 学院获奖统计首页数据查询
	 * @param xmjhAll2 
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getXyhjtjCx(XyhjtjForm myForm, ArrayList<String[]> xmjhAll) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		String[] pjsj = myForm.getSearchModel().getSearch_tj_pjzq();
		String[] xy=myForm.getSearchModel().getSearch_tj_xy();
		String[] xmjh = myForm.getSearchModel().getSearch_tj_pjlsxm();
		String xysql="";
		if(xy!=null){
			xysql+="where xydm in(";
			for (String string : xy) {
				xysql+="'"+string+"',";
			}
			xysql=xysql.substring(0, xysql.length()-1);
			xysql+=")";
		}
		int xmjhLength = xmjhAll.size();
		xmjh = new String[xmjhLength];
		for(int i=0;i<xmjhLength;i++){
			xmjh[i]=xmjhAll.get(i)[0];
		}
		//String[] colList = new String[]{"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","s","t","u","v","w","x","y","z"};
		// 用户属性
		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += "and pjsj=?";
		StringBuilder sql = new StringBuilder();
		String temp="select xmmc,pjsj,xh,xmje,xydm,xymc from xg_view_pjpy_xyhjtj union all select a.*,b.xydm,b.xymc from (select b.xmmc, case when a.pjxq = 'no' then a.pjxn else a.pjxn || '  ' || (select c.xqmc from xqdzb c where a.pjxq = c.xqdm) end pjsj, a.xh,b.xmje from (select * from xg_pjpy_pjxmsqb where sqjg='wxsh' or sqjg='tg') a left join xg_pjpy_pjxmwhb b  on a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq) a left join view_xsjbxx b on a.xh=b.xh";
		int i=0;
		sql.append(" select rownum r,z.xymc");
		for (String xm : xmjh) {
			i++;
			sql.append(",z.xm"+i);
			sql.append(",z.xmje"+i);
		}
		sql.append(",z.hjrs,z.hjje from (select a.pjsj,a.xymc");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",a.xm"+i);
			sql.append(",a.xmje"+i);
		}
		sql.append(",b.hjrs,b.hjje from (select pjsj,xydm, xymc ");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",sum(xm"+i+") xm"+i+",sum(xmje"+i+") xmje"+i);
		}
		sql.append(" from (select pjsj,xh,xydm,xymc");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",(case when xmmc = '"+xm+"' then 1 else 0 end) xm"+i);
			sql.append(",(case when xmmc = '"+xm+"' then to_number(xmje) else 0 end) xmje"+i);
		}
		sql.append(" from ("+temp+")"+xysql+") group by pjsj,xydm,xymc union all select pjsj,'11' xydm,'合计' hj");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",sum(xm"+i+") xm"+i+",sum(xmje"+i+") xmje"+i);
		}
		sql.append(" from (select pjsj,xh,xydm,xymc");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",(case when xmmc = '"+xm+"' then 1 else 0 end) xm"+i);
			sql.append(",(case when xmmc = '"+xm+"' then to_number(xmje) else 0 end) xmje"+i);
		}
		sql.append(" from ("+temp+")"+xysql+") group by pjsj) a left join(select pjsj,xydm,xymc,count(*) hjrs,sum(xmje) hjje from ("+temp+") group by pjsj,xydm,xymc union all select pjsj,'11' xydm,'合计' hj,count(*) hjrs,sum(xmje) hjje from ("+temp+")"+xysql+" group by pjsj) b on a.pjsj=b.pjsj and a.xydm=b.xydm");
//		sql.append(" select xymc, ");
//		int i=0;
//		for(String dqxm : xmjh){
//			sql.append(" (select count(distinct xh) from xg_view_pjpy_xyhjtj b where a.xymc=b.xymc and a.pjsj=b.pjsj and b.xmmc='"+dqxm+"' and pjsj='"+pjsj[0]+"') , ");
//			i++;
//			sql.append(" (select count(xh) from xg_view_pjpy_xyhjtj b where a.xymc=b.xymc and a.pjsj=b.pjsj and b.xmmc='"+dqxm+"' and pjsj='"+pjsj[0]+"') , ");
//			i++;
//		}
//		sql.append(" (select count(distinct xh) from xg_view_pjpy_xyhjtj b where a.xymc=b.xymc and a.pjsj=b.pjsj and pjsj='"+pjsj[0]+"' and (" );
//		for(int m=0;m<xmjh.length;m++){
//			sql.append(" b.xmmc = '"+xmjh[m]+"' ");
//			if(m<xmjh.length-1){
//				sql.append(" or ");
//			}
//		}
//		sql.append(")) , ");
//		i++;
//		sql.append(" (select count(xh) from xg_view_pjpy_xyhjtj b where a.xymc=b.xymc and a.pjsj=b.pjsj and pjsj='"+pjsj[0]+"'  and (" );
//		for(int m=0;m<xmjh.length;m++){
//			sql.append(" b.xmmc = '"+xmjh[m]+"' ");
//			if(m<xmjh.length-1){
//				sql.append(" or ");
//			}
//		}
//		sql.append(")) ");
//		sql.append(" from xg_view_pjpy_xyhjtj a ");
//		sql.append(query);
		sql.append(" ) z");
		sql.append(query);
//		String colListOther[] = new String[i+3];
//		colListOther[0]= "r";
//		colListOther[1]= "xymc";
//		for(int j=0;j<=i;j++){
//			colListOther[j+2]=colList[j];
//		}
		// ====================SQL拼装 end================================
		DAO dao = DAO.getInstance();
		ArrayList<String[]> list = (ArrayList<String[]>) dao.rsToListNotOut(sql.toString(), pjsj);
		
		return list;
	}

	public ArrayList<String[]> getXmjhAll(XyhjtjForm myForm) throws Exception{
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy("select distinct xmmc from xg_view_pjpy_xyhjtj union all select distinct b.xmmc from (select * from xg_pjpy_pjxmsqb where sqjg='wxsh' or sqjg='tg')a left join xg_pjpy_pjxmwhb b on a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq", "", new String[]{}, new String[]{"xmmc"}, myForm);
		return list;
	}
}