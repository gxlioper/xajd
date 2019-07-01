package xsgzgl.pjpy.general.tjcx.bjhjtj;

import java.util.ArrayList;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
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

public class BjhjtjDAO extends CommDAO {

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
	public ArrayList<String[]> getBjhjtjCx(BjhjtjForm myForm, ArrayList<String[]> xmjhAll) throws Exception {
		// 高级查询MODEL
		SearchModel searchModel = myForm.getSearchModel();
		//String[] pjsj = myForm.getSearchModel().getSearch_tj_pjzq();
		//String[] xy=myForm.getSearchModel().getSearch_tj_xy();
		String[] xmjh = myForm.getSearchModel().getSearch_tj_pjlsxm();
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
		User user=myForm.getUser();
		String userType=user.getUserStatus();
		String s="";
		String userDep=user.getUserDep();
		if("xy".equalsIgnoreCase(userType)){
			s=" where xydm='"+userDep+"' ";
		}
		String query = " where 1 = 1 ";
		query += searchTj;
		StringBuilder sql = new StringBuilder();
		String temp="select a.xmmc, case when a.xq = 'no' then a.xn else a.xn || '  ' || (select c.xqmc from xqdzb c where a.xq = c.xqdm) end pjsj, a.xh, a.xmje, b.bjdm, b.bjmc,b.xydm from xg_pjpy_pjlsxxb a left join view_xsjbxx b on a.xh=b.xh union all select a.*,b.bjdm,b.bjmc,b.xydm from (select b.xmmc, case when a.pjxq = 'no' then a.pjxn else a.pjxn || '  ' || (select c.xqmc from xqdzb c where a.pjxq = c.xqdm) end pjsj, a.xh,b.xmje from (select * from xg_pjpy_pjxmsqb where sqjg='wxsh' or sqjg='tg') a left join xg_pjpy_pjxmwhb b  on a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq) a left join view_xsjbxx b on a.xh=b.xh";
		int i=0;
		sql.append(" select * from (select a.*,b.zrs,b.zje from(select pjsj,bjdm,bjmc");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",sum(xm"+i+") xm"+i+",sum(xmje"+i+") xmje"+i);
		}
		sql.append(" from(select pjsj,xh,bjdm,bjmc");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",(case when xmmc = '"+xm+"' then 1 else 0 end) xm"+i);
			sql.append(",(case when xmmc = '"+xm+"' then to_number(xmje) else 0 end) xmje"+i);
		}
		sql.append(" from("+temp+")"+s+")group by pjsj,bjdm,bjmc");
		sql.append(" union all select pjsj,'11' bjdm,'合计' bjmc");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",sum(xm"+i+") xm"+i+",sum(xmje"+i+") xmje"+i);
		}
		sql.append(" from(select pjsj,xh,bjdm,bjmc");
		i=0;
		for (String xm : xmjh) {
			i++;
			sql.append(",(case when xmmc = '"+xm+"' then 1 else 0 end) xm"+i);
			sql.append(",(case when xmmc = '"+xm+"' then to_number(xmje) else 0 end) xmje"+i);
		}
		sql.append(" from("+temp+")"+s+") group by pjsj");
		sql.append(" ) a left join (select count(*) zrs,sum(xmje) zje,pjsj,bjdm,bjmc from ("+temp+")"+s+" group by pjsj,bjdm,bjmc");
		sql.append("   union all select count(*),sum(xmje),pjsj,'11'bjdm,'合计' bjmc from ("+temp+")"+s+" group by pjsj)b on a.pjsj=b.pjsj and a.bjdm=b.bjdm");
		sql.append(" ) z");
		sql.append(query);
		// ====================SQL拼装 end================================
		DAO dao = DAO.getInstance();
		ArrayList<String[]> list = (ArrayList<String[]>) dao.rsToListNotOut(sql.toString(), inputV);
		
		return list;
	}

	public ArrayList<String[]> getXmjhAll(BjhjtjForm myForm) throws Exception{
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO.commonQueryNotFy("select distinct xmmc from xg_view_pjpy_xyhjtj union all select distinct b.xmmc from (select * from xg_pjpy_pjxmsqb where sqjg='wxsh' or sqjg='tg')a left join xg_pjpy_pjxmwhb b on a.xmdm=b.xmdm and a.pjxn=b.pjxn and a.pjxq=b.pjxq", "", new String[]{}, new String[]{"xmmc"}, myForm);
		return list;
	}
}