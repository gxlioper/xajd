package xsgzgl.wjcf.cfsjtj;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchService;
import xgxt.utils.CommonQueryDAO;

public class WjcfCfsjtjDao extends CommDAO {

	public List<String[]> getXycftj(WjcfCfsjtjActionForm myForm,
			List<HashMap<String, String>> list) throws Exception{
		// TODO 自动生成方法存根
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] input = SearchService.getTjInput(myForm.getSearchModel());
		String[] inputV;
		String query="where 1=1 ";;
		if(input.length>0){
			query+=searchTj;
			inputV=new String[3*input.length];
			for(int i=0;i<3*input.length;i++){
				for(int j=0;j<input.length;j++){
					inputV[i]=input[j];
				}
			}
		}else{
			inputV=new String[0];
		}
		
		String[] colList = new String[4+list.size()];
		
		colList[0]="xymc";
		colList[1]="zrs";
		for (int i = 0; i < list.size(); i++) {
			colList[i+2]="cf"+(i+1);
		}
		colList[2+list.size()]="cfrs";
		colList[3+list.size()]="per_cf";
		StringBuilder sql = new StringBuilder();
		sql.append("select a.xymc,b.zrs,");
		for(int i = 0; i < list.size(); i++) {
			sql.append("b.cf"+(i+1)+",");
		}
		sql.append("b.cfrs,b.per_cf from (select distinct xydm,xymc from view_njxyzybj )a left join ");
		sql.append("(select a.xydm,a.zrs,");
		for(int i = 1; i <= list.size(); i++) {
			sql.append("(case when b.cf"+i+" is null then 0 else b.cf"+i+" end)cf"+i+",");
		}
		sql.append("(case when b.cfrs is null then 0 else b.cfrs end)cfrs,(case when cfrs is  not null then concat(to_char(cfrs/zrs*100,'990.99'),'%') else '0%' end) per_cf from");
		sql.append("(select count(a.xh) zrs,a.xydm from(");
		sql.append(Base.xsxxb);
		sql.append(")a group by xydm)a left join");
		sql.append("(select a.xydm,");
		for(int i = 1; i <= list.size(); i++) {
			sql.append("a.cf"+i+",");
		}
		sql.append("b.cfrs from (select a.xydm,");
		for(int i = 1; i <= list.size(); i++) {
			if(i<list.size()){
				sql.append("sum(a.cf"+i+") cf"+i+",");
			}else{
				sql.append("sum(a.cf"+i+") cf"+i);
			}
		}
		sql.append(" from (select a.xh,a.xm,a.xydm,a.xn,a.CFLBMC,");
		for(int i = 1; i <= list.size(); i++) {
			if(i<list.size()){
				sql.append("(case when a.CFLBMC = '"+list.get(i-1).get("mc")+"' then 1 else 0 end) cf"+i+",");
			}else{
				sql.append("(case when a.CFLBMC = '"+list.get(i-1).get("mc")+"' then 1 else 0 end) cf"+i);
			}
		}
		sql.append(" from (select a.xh, a.xm, b.xydm, a.xn, a.cflbmc from xg_view_wjcf_wjcfb a left join ("+Base.xsxxb+") b");
		sql.append(" on a.xh = b.XH) a "+query+") a group by xydm) a left join");
		sql.append("(select a.xydm,count(a.CFLBMC) cfrs from (select b.xydm, a.cflbmc from xg_view_wjcf_wjcfb a left join ("+Base.xsxxb+") b ");
		sql.append(" on a.xh = b.XH)a group by a.xydm) b on a.xydm=b.xydm ) b on a.xydm=b.xydm) b on a.xydm=b.xydm");
		sql.append(" union all ");
		sql.append("select '总计' xymc,a.zrs,");
		for(int i = 1; i <= list.size(); i++) {
			sql.append("b.cf"+i+",");
		}
		sql.append("b.cfrs,concat(to_char(b.cfrs/a.zrs*100,'990.99'),'%') per_cf from (select count(a.xh) zrs from (");
		sql.append(Base.xsxxb);
		sql.append(")a)a,(select ");
		for(int i = 1; i <= list.size(); i++) {
			sql.append("a.cf"+i+",");
		}
		sql.append("b.cfrs from(select ");
		for(int i = 1; i <= list.size(); i++) {
			if(i<list.size()){
				sql.append("sum(a.cf"+i+")cf"+i+",");
			}else{
				sql.append("sum(a.cf"+i+")cf"+i);
			}
		}
		sql.append(" from (select ");
		for(int i = 1; i <= list.size(); i++) {
			if(i<list.size()){
				sql.append("(case when a.CFLBMC = '"+list.get(i-1).get("mc")+"' then 1 else 0 end) cf"+i+",");
			}else{
				sql.append("(case when a.CFLBMC = '"+list.get(i-1).get("mc")+"' then 1 else 0 end) cf"+i);
			}
		}
		sql.append(" from xg_view_wjcf_wjcfb a "+query+")a)a,(select count(xh)cfrs from xg_view_wjcf_wjcfb "+query+")b )b");
		return CommonQueryDAO.commonQueryNotFy(sql.toString(),"",inputV,
				 colList, myForm);
	}

	public List<String[]> getYltj(WjcfCfsjtjActionForm myForm) throws Exception{
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		String query="where 1=1 ";
		query+=searchTj;
		String sql="select xh,xm,xb,xymc,bjmc,wjsj,cfwh,cfsj,cflbmc,cfyymc from xg_view_wjcf_wjcfb "+query;
		String[] colList=new String[]{"xh","xm","xb","xymc","bjmc","wjsj","cfwh","cfsj","cflbmc","cfyymc"};
		
		return CommonQueryDAO.commonQueryNotFy(sql.toString(),"",inputV,
				 colList, myForm);
	}

}
