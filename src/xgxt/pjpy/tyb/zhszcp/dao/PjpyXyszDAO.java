package xgxt.pjpy.tyb.zhszcp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.form.User;
import xgxt.pjpy.guizhdx.GuizhdxForm;
import xgxt.pjpy.tyb.zhszcp.action.PjpyXyszActionForm;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZctjszModel;
import xgxt.pjpy.tyb.zhszcp.model.PjpyZhszcpModel;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class PjpyXyszDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * @param model
	 * @return StringBuilder
	 * */
	public StringBuilder getWhereSql(PjpyXyszActionForm model){
		value = new ArrayList<String>();
		String xn = model.getQueryequals_xn();
		String nd = model.getQueryequals_nd();
		String xq = model.getQueryequals_xq();
		String xydm = model.getQueryequals_xydm();
		String nj = model.getQueryequals_nj();
		String jxjje1 = model.getQuerygreaterequal_jxjje1();
		String jxjje2 = model.getQuerylessequal_jxjje2();
		
		StringBuilder sb = new StringBuilder("where 1=1 ");
		if(xydm !=null && !xydm.equalsIgnoreCase("")){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(nj !=null && !nj.equalsIgnoreCase("")){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(xn !=null && !xn.equalsIgnoreCase("")){
			sb.append( " and xn=?");
			value.add(xn);
		}
		if(nd !=null && !nd.equalsIgnoreCase("")){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(xq !=null && !xq.equalsIgnoreCase("")){
			sb.append( " and xq=?");
			value.add(xq);
		}
		if(StringUtils.isNotNull(jxjje1)){
			sb.append( " and to_number(jxjje) >=?");
			value.add(jxjje1);
		}
		if(StringUtils.isNotNull(jxjje2)){
			sb.append( " and to_number(jxjje) <=?");
			value.add(jxjje2);
		}
		return sb;
	}
	/**
	 * 奖学金金额设置信息查询
	 * @param model
	 * @param output
	 * @param user
	 * @return List<String[]>
	 * */
	public List<String[]> selectJxjjesz(PjpyXyszActionForm model, String[] output, User user){
		Pages pages = model.getPages();
		String whereSql = getWhereSql(model).toString();
		String sql = "";
		String mainSql = getJxjszMainSql(model.getQueryequals_xn(),model.getQueryequals_nd(),model.getQueryequals_xq());
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		
		//查询记录总数
		sql = "select count(*) num from(" + mainSql + ")a " + whereSql;
		pages.setMaxRecord(Integer.parseInt(getOneRs(sql, input, "num")));

		//查询数据
		sql = StringUtils.joinStr("select * from (select a.*,rownum r from(",mainSql,")a ",
				whereSql,
				") a where r>",
				pages.getStart()+"",
				" and r<=",
				(pages.getStart()+pages.getPageSize())+"");		
		return rsToVator(sql, input, output);
	}
	
	/**
	 * 获取奖学金金额设置主查询语句
	 * @return String
	 * */
	public String getJxjszMainSql(String xn, String nd, String xq){
		return StringUtils.joinStr("select xn||nj||xydm pk, a.xydm,a.xymc,a.nj,a.xn,a.nd,a.xq,",
				"(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,jxjje from(",
				"select a.xydm,a.xymc,a.nj,a.xn,a.nd,a.xq,b.jxjje from (select distinct xydm,xymc,nj,'",StringUtils.isNull(xn) ? Base.getJxjsqxn() : xn,
				"'xn,'",StringUtils.isNull(nd) ? Base.getJxjsqnd() : nd,
				"'nd,'",StringUtils.isNull(xq) ? Base.getJxjsqxq() : xq,
				"'xq from view_njxyzybj a) a ",
				"left join pjpy_ty_xyjxjjeszb b on a.xydm=b.xydm and a.nj=b.nj and a.xn=b.xn",
				") a order by xydm,nj ");
	}
	
	/**
	 * 奖学金金额设置保存
	 * @param model
	 * @param user
	 * @return boolean
	 * */
	public boolean saveJxjjesz(PjpyXyszActionForm model, User user){
		String[] pkV = model.getPrimarykey_cbv();
		String[] jxjje = model.getKey_jxjzje();
		String[] sqlArr = new String[pkV.length*2];
		
		boolean result = true;
		int flagNum = 0;
		for(int i=0; i<pkV.length; i++){
			String sql = "select xn,nj,xydm from (" + getJxjszMainSql(model.getQueryequals_xn(),model.getQueryequals_nd(), model.getQueryequals_xq()) + ") where pk=?";
			HashMap<String, String> map = getMap(sql, new String[]{pkV[i]}, new String[]{"xn","nj","xydm"});
			if(StringUtils.isNotNull(jxjje[i])){
				sqlArr[flagNum ++ ] = "delete from pjpy_ty_xyjxjjeszb where xn||nj||xydm='" + DealString.replaceImmitStr(pkV[i])+ "'";
				sqlArr[flagNum ++ ] = "insert into pjpy_ty_xyjxjjeszb(xn,nj,xydm,jxjje) values('" + map.get("xn")+ "','" + map.get("nj")+"','" + map.get("xydm")+ "','" + jxjje[i] + "')";
			}
		}
		try{
			int[] resultNum = runBatch(sqlArr,"pjpy_ty_xyjxjjeszb",user);
			result = checkBatch(resultNum);
		}catch (Exception e){
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
}
