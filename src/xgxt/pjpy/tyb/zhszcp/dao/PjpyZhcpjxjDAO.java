package xgxt.pjpy.tyb.zhszcp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.GlobalsVariable;

import xgxt.DAO.DAO;
import xgxt.base.DealString;
import xgxt.form.User;
import xgxt.pjpy.lsxy.LsxyPjpyDAO;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhcpjxjActionForm;
import xgxt.pjpy.tyb.zhszcp.action.PjpyZhszcpwhActionForm;
import xgxt.pjpy.zjjd.JxjpdxxModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;
import xgxt.xtwh.xtwhOther.XtwhOtherDAO;

public class PjpyZhcpjxjDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * @param model
	 * @return StringBuilder
	 * */
	public StringBuilder getWhereSql(PjpyZhszcpwhActionForm model){
		value = new ArrayList<String>();
		String xh = model.getQuerylike_xh();
		String xm = model.getQuerylike_xm();
		String xn = model.getQueryequals_xn();
		String nd = model.getQueryequals_nd();
		String xq = model.getQueryequals_xq();
		String xydm = model.getQueryequals_xydm();
		String zydm = model.getQueryequals_zydm();
		String bjdm = model.getQueryequals_bjdm();
		String nj = model.getQueryequals_nj();
		
		StringBuilder sb = new StringBuilder("where 1=1 ");
		if(xydm !=null && !xydm.equalsIgnoreCase("")){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(zydm !=null && !zydm.equalsIgnoreCase("")){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(bjdm !=null && !bjdm.equalsIgnoreCase("")){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(nj !=null && !nj.equalsIgnoreCase("")){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(xh !=null && !xh.equalsIgnoreCase("")){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(xm !=null && !xm.equalsIgnoreCase("")){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
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
		return sb;
	}
	
	/**
	 * 获取综合测评代码列表
	 * @param dmjb
	 * @return sjbmdm
	 */
	public List<HashMap<String, String>> getZhcpdmList(String dmjb, String sjbmdm) {
		DAO dao = DAO.getInstance();
		String sql = "select distinct dm,mc from pjpy_zctjszb where 1=1";
		if(StringUtils.isNotNull(dmjb)){
			sql += StringUtils.joinStr(" and dmjb='",dmjb,"'");
		}
		if(StringUtils.isNotNull(sjbmdm)){
			sql += StringUtils.joinStr(" and fdm='",sjbmdm,"'");
		}
		
		return dao.getList(sql, new String[]{}, new String[]{"dm", "mc"});
	}
	
	/**
	 * 获取人数分配信息
	 * @param pk
	 * @param pkV
	 * @param shzd
	 * @param key
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getRsfpMap(String pk,String pkV, String shzd,String key){
		DAO dao = DAO.getInstance();
		
		//表名
		String tableName = "view_pjpy_ty_zhcpjxjsq";
		//主键值字段
		String pkStr = "xn||nd||xq||jxjdm||nj||xydm";
		//代码字段
		String dmStr = "jxjdm";
		
		String bm = "xydm";//部门
//		if("fdy".equalsIgnoreCase(shzd) 
//				|| "bzr".equalsIgnoreCase(shzd)){
//			bm = "bjdm";
//		}
		String sql = StringUtils.joinStr("select ",
										 pkStr,
										 " pkV,",
										 "(select count(*) from ",
										 tableName,
										 " b where a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and a.",
										 dmStr,
										 "=b.",
										 dmStr,
										 " and b.", 
										 shzd,
										 "='通过' and a." + bm + "=b." + bm + " and a.xh<>b.xh)tgrs from ",
										 tableName,
										 " a where ",
										 pk,
										 "=?");
		return dao.getMap(sql, new String[]{pkV}, new String[]{"pkV", "tgrs"});
	}
	
	/**
	 * 获取奖学金人数
	 * @param pk
	 * @param pkV
	 * @param shzd
	 * @param key
	 * @return String
	 * */
	public String getJxjrs(String pkV, String shzd,String key){
		DAO dao = DAO.getInstance();
		String pk = "xn||nd||xqdm||jxjdm";
		String sql = StringUtils.joinStr("select jxjrs from xyjxjrs where jxjrs is not null and key='",
				                          key,
				                          "' and ",
				                          pk,
				                          "=? and bmlb='xydm'");		
		return dao.getOneRs(sql, new String[]{pkV}, "jxjrs");
	}
	
	/**
	 * 查询综合测评奖学金上报信息
	 * @param pjzq
	 * @param jb
	 * @param map
	 * @param model
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]>  queryZhcpjxjsb(String pjzq, 
									           String jb,
									           HashMap<String, String> map,
									           String[] output,
									           PjpyZhcpjxjActionForm model, 
									           boolean isPage) throws Exception{
		PjpyZctjszDAO zctjszDao = new PjpyZctjszDAO();
		LsxyPjpyDAO dao = new LsxyPjpyDAO();
		//联合条件
		String unionStr = "";
		if("xn".equalsIgnoreCase(pjzq)){
			unionStr = " and a.xn=b.xn";
		}else if("xq".equalsIgnoreCase(pjzq)){
			unionStr = " and a.xn=b.xn and a.xq=b.xq";
		}
		
		String xmdm = map.get(jb);//项目代码
		String[] xmdmArray = new String[]{};
		if ("1".equalsIgnoreCase(jb)) {
			xmdmArray = new String[]{"999"};
		}
		if ("2".equalsIgnoreCase(jb)) {
			if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
				xmdm = map.get("2");
				xmdmArray = new String[]{xmdm};
			}
			
		} else if ("3".equalsIgnoreCase(jb)) {
			if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
				xmdm = map.get("3");
				xmdmArray = new String[]{xmdm};
			} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
				xmdm = map.get("2");
				xmdmArray = getArray("select dm from pjpy_zctjszb where fdm = ? and dmjb='3'", new String[]{xmdm}, "dm");
			}
			
		} else if ("4".equalsIgnoreCase(jb)) {
			if (StringUtils.isNotNull(model.getQueryequals_sidm())) {
				xmdm = map.get("4");	
				xmdmArray = new String[]{xmdm};
			} else if (StringUtils.isNotNull(model.getQueryequals_sjdm())) {
				xmdm = map.get("3");
				xmdmArray = getArray("select dm from pjpy_zctjszb where fdm = ? and dmjb='4'", new String[]{xmdm}, "dm");
			} else if (StringUtils.isNotNull(model.getQueryequals_ejdm())) {
				xmdm = map.get("2");
				xmdmArray = getArray("select dm from pjpy_zctjszb a where exists (select 1 from pjpy_zctjszb b where b.fdm = ? and b.dmjb='3' and a.fdm=b.dm)", new String[]{xmdm}, "dm");
			}
		}
		
		List<HashMap<String, String>> list = zctjszDao.queryZhcpXmxx(jb,xmdmArray);
		String viewName = "view_pjpy_zhcpjxjsb";//视图
		StringBuilder sql = new StringBuilder("select a.*,rownum r from (select a.* from (select pk,xh,xm,xydm,xymc,nj,zydm,zymc,bjdm,bjmc,xn,xqmc,nd,xq,jxjdm,jxjmc,nvl((select pjxfjd from view_ntzydx_cjb b where a.xh=b.xh ");
		sql.append(unionStr);
		sql.append("),0)平均学分绩点,max(xysh)xysh,max(xxsh)xxsh ");
		//动态查询字段
		int i=0;
		for(HashMap<String, String> xmMap : list){
			xmdm = xmMap.get("dm");
			output = StringUtils.joinStrArr(output, new String[]{"fs" + i, "pm" + i});
			
			sql.append(",max((select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) fs");
			sql.append(i);
			sql.append(",max((select pm from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
			sql.append(xmdm);
			sql.append("')) pm");
			sql.append(i++);			
		}
		//总分信息
		xmdm = "999";//默认的总分代码
		sql.append(",max((select fs from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
		sql.append(xmdm);
		sql.append("')) fs");
		sql.append(i);
		sql.append(",max((select pm from view_pjpy_zhszcpb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and b.dm='");
		sql.append(xmdm);
		sql.append("')) pm");
		sql.append(i);
		output = StringUtils.joinStrArr(output, new String[]{"fs" + i, "pm" + i});
		
		sql.append(" from ");
		sql.append(viewName);
		sql.append(" a ");		
		
		sql.append(" group by pk,xh,xm,xydm,xymc,nj,zydm,zymc,bjdm,bjmc,xn,xqmc,nd,xq,jxjdm,jxjmc) a");
		if ("1".equalsIgnoreCase(jb)) {
			sql.append(" order by xymc,nj,to_number(pm0)");
		}
		sql.append(") a "); 
		//查询条件
		PjpyZhszcpwhActionForm zhcpModel = new PjpyZhszcpwhActionForm();
		BeanUtils.copyProperties(zhcpModel, model);
		sql.append(getWhereSql(zhcpModel));
		
		if(StringUtils.isNotNull(model.getQuerygreaterequal_zf())){
			sql.append(" and a.fs");
			sql.append(i);
			sql.append(">=");
			sql.append(DealString.replaceImmitStr(model.getQuerygreaterequal_zf()));
		}
		if(StringUtils.isNotNull(model.getQuerylessequal_zfpm())){
			sql.append(" and a.pm");
			sql.append(i);
			sql.append("<=");
			sql.append(DealString.replaceImmitStr(model.getQuerylessequal_zfpm()));
		}
		//条件设置中设置的固定条件
		JxjpdxxModel jxjpdModel = new JxjpdxxModel();
		jxjpdModel.setJxjdm(model.getJxjdm());
		jxjpdModel.setXn(model.getQueryequals_xn());
		jxjpdModel.setXq(model.getQueryequals_xq());
		List<HashMap<String, String>> tjList = dao.getTjList(jxjpdModel,GlobalsVariable.PJPY_ZHCPJXJ);
		
		for(HashMap<String, String> tjMap : tjList){
			String tjzd = tjMap.get("tjzd");
			if("pjxfjd".equalsIgnoreCase(tjzd)){//平均学分绩点
				sql.append(" and exists(select 1 from view_ntzydx_cjb b where a.xh=b.xh ");
				sql.append(unionStr);
				sql.append(" and b.pjxfjd");
				sql.append(tjMap.get("tjlx"));
				sql.append(tjMap.get("tjz"));
				sql.append(" )");
			}else if ("zhcpcj".equals(tjzd)) {//综合测评成绩
				sql.append(" and a.fs");
				sql.append(i);
				sql.append(tjMap.get("tjlx"));
				sql.append(tjMap.get("tjz"));
			}
		}
		if (isPage) {			
			return CommonQueryDAO.commonQuery(sql.toString(), "", value != null ? value.toArray(new String[0]) : new String[]{}, output, model);
		}		
		return CommonQueryDAO.commonQueryNotFy(viewName, "" , value != null ? value.toArray(new String[0]) : new String[]{}, output, sql.toString(), model);
	}
	
	/**
	 * 保存综合测评奖学金上报信息
	 * @param pkValues
	 * @param jxjdm
	 * @param user
	 * @return boolean
	 * */
	public boolean saveZhcpjxjsb(String[] pkValues, String jxjdm, User user){
		boolean result = false;
		String[] sqlArr = new String[pkValues.length*2];
		int index = 0;
		for(int i=0; i<pkValues.length; i++){
			String pkV = getOneRs("select xh||xn||xq||nd pk from view_pjpy_zhcpjxjsb where pk=?", new String[]{pkValues[i]}, "pk");
			sqlArr[index++] = "delete from pjpy_ty_zhcpjxjsqb a where xh||xn||xq||nd||jxjdm='" + pkValues[i] + "'";
			sqlArr[index++] = "insert into pjpy_ty_zhcpjxjsqb(xh,xn,nd,xq,jxjdm,xysh)select xh,xn,nd,xq,'" + jxjdm +"','通过' from view_pjpy_zhcpjxjsb where pk='" + pkV + "' and rownum=1";
		}
				
		try {
			result = checkBatch(runBatch(sqlArr,"pjpy_ty_zhcpjxjsqb",user));			
		} catch (SQLException e) {
			result = true;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 取消学院综合测评奖学金上报
	 * @param pkValues
	 * @param user
	 * @return boolean
	 * */
	public boolean delZhcpjxjsb(String[] pkValues, User user){
		boolean result = false;
		String[] sqlArr = new String[pkValues.length];
		for(int i=0; i<pkValues.length; i++){
			sqlArr[i] = "delete from pjpy_ty_zhcpjxjsqb a where xh||xn||xq||nd||jxjdm='" + pkValues[i] + "'";
		}		
		
		try {
			result = checkBatch(runBatch(sqlArr,"pjpy_ty_zhcpjxjsqb",user));			
		} catch (SQLException e) {
			result = true;
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 检测是否可上报综合测评奖学金
	 * */
	public boolean checkJxjsb(String[] pkValues){
		boolean flag = true;
		for(int i=0; i<pkValues.length; i++){
			String num = getOneRs("select count(*)count from view_pjpy_zhcpjxjsb where pk=? and jxjdm is not null", new String[]{pkValues[i]}, "count");
			if(Integer.parseInt(num)>0){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 检测是否可取消综合测评奖学金上报
	 * */
	public boolean checkQxJxjsb(String[] pkValues){
		boolean flag = true;
		for(int i=0; i<pkValues.length; i++){
			String num = getOneRs("select count(*)count from view_pjpy_zhcpjxjsb where pk=? and jxjdm is not null and xxsh='通过'", new String[]{pkValues[i]}, "count");
			if(Integer.parseInt(num)>0){
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	/** 
	 * 查询条件系信息
	 * @param model
	 * @param xmlx
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectTjxxList(PjpyZhcpjxjActionForm model,String xmlx){
		StringBuilder sql = new StringBuilder("select distinct tj from view_zjcm_pjpy_tjsz where lx=?");
		if(StringUtils.isNotNull(model.getQueryequals_xn())){
			sql.append(" and xn='");
			sql.append(DealString.replaceImmitStr(model.getQueryequals_xn()));
			sql.append("'");
		}
		if(StringUtils.isNotNull(model.getQueryequals_xq())){
			sql.append(" and xq='");
			sql.append(DealString.replaceImmitStr(model.getQueryequals_xq()));
			sql.append("'");
		}
		if(StringUtils.isNotNull(model.getQueryequals_nd())){
			sql.append(" and nd='");
			sql.append(DealString.replaceImmitStr(model.getQueryequals_nd()));
			sql.append("'");
		}
		if(StringUtils.isNotNull(model.getJxjdm())){
			sql.append(" and jxjdm='");
			sql.append(DealString.replaceImmitStr(model.getJxjdm()));
			sql.append("'");
		}
		
		return getList(sql.toString(), new String[]{xmlx}, new String[]{"tj"});
	}
}
