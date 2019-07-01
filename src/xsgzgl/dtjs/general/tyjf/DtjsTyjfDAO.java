package xsgzgl.dtjs.general.tyjf;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.dtjs.general.DtjsGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 党团建设_团员缴费_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class DtjsTyjfDAO extends CommDAO {
	DAO dao = DAO.getInstance();
	
	public ArrayList<String[]> getTyjfList(DtjsGeneralForm myForm,DtjsTyjfModel model) 
				throws IllegalArgumentException, SecurityException, 
				IllegalAccessException, InvocationTargetException, 
				NoSuchMethodException{
		List<String> colList = new ArrayList<String>();
		String[] colArr = new String[] {"pk","xn","xh","xm", "nj", "bjmc","yjtf","sjtf","qf"};
		
		// 高级查询输入值
		SearchModel searchModel = myForm.getSearchModel();
		String[] xn = searchModel.getSearch_tj_xn();
		
		//初始默认当前年度
		if(null==xn||xn.length==0){
			searchModel.setSearch_tj_xn(new String[] {Base.currXn});
		}

		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query = " where 1 = 1 ";
		query += searchTj;
		
		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from (");
		sql.append(" select a.xh pk,a.xh,a.xm,a.nj,a.xydm,a.zydm,a.bjdm,a.bjmc,nvl(b.xn,"+"'"+Base.currXn+"'"+") xn," +
				"nvl(b.yjtf, 0) yjtf,nvl(b.sjtf, 0) sjtf," +
				"(case when substr(nvl(round(to_number(b.yjtf) - to_number(b.sjtf), 2),0),0,1)='.' " +
				"then '0'||nvl(round(to_number(b.yjtf) - to_number(b.sjtf), 2),0) else" +
				" ''||nvl(round(to_number(b.yjtf) - to_number(b.sjtf), 2),0) end) qf, " +
				" (case when to_number(b.yjtf) - to_number(b.sjtf)>0 then '是' else '否' end) sfqf");
		sql.append(" from (select b.xh, b.xm, b.nj, b.xydm, b.zydm, b.bjdm, b.bjmc,a.jddm,a.dqjdbj  from (select *"+
                  "from xg_dtjs_xsdtxxjlb a where a.jddm = '02' "+
                   "and a.dqjdbj = '1') a left join view_xsxxb b " +
                 "on a.xh = b.xh ) a");
		sql.append(" left join (select b.xh, b.yjtf, b.sjtf ,b.xn" );
		sql.append(" from xg_dtjs_tyjfb b where xn = "+"'"+xn[0]+"'"+") b on a.xh = b.xh");

		sql.append(" order by a.xh)a " );
		sql.append(query);
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
		.commonPageQuery(myForm.getPages(), sql.toString(), inputV, dao
				.unionArray(colArr, colList.toArray(new String[] {})));
		return list;
	}
	
	//保存
	public boolean saveTyjf(DtjsTyjfModel model) throws Exception {
		String xh = model.getXh();
		
		String xhs[] = xh.split(",");
		boolean flag=false;
		if(null!=xhs&&xhs.length>0){
			String[] str=new String[xhs.length*2];
			for(int i = 0;i<xhs.length;i++){
				model.setXh(xhs[i]);
				str[i*2]="delete from xg_dtjs_tyjfb t where t.xn = "+"'"+Base.currXn+"'"+" and t.xh = "+"'"+model.getXh()+"'";
				str[i*2+1]="insert into xg_dtjs_tyjfb(xn,xh,yjtf,sjtf) values("+"'"+Base.currXn+"'"+","+"'"+model.getXh()+"'"+
				","+"'"+model.getYjtf()+"'"+","+"'"+model.getSjtf()+"'"+")";
			}
			flag=dao.saveArrDate(str);
		}
		return flag;

	}

}
