package xgxt.jygl.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.jygl.form.BdzblForm;
import xgxt.utils.String.StringUtils;

public class BdzblDao extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * */
	public StringBuffer getWhereSql(BdzblForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String nd = model.getNd();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		String sjhm = model.getSjhm();
		String bdzkwdwmc = model.getBdzkwdwmc();
		String bdzlx = model.getBdzlx();
		String fdysh = model.getFdysh();
		String xysh = model.getXysh();
		String bdzkcsj = model.getBdzkcsj();
		String sfklq = model.getSfklq();
		String lqr = model.getLqr();
		String userName = model.getUserName();
		String isFdy = model.getIsFdy();
		String isBzr = model.getIsBzr();
		
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(nj)){
			sb.append( " and nj=?");
			value.add(nj);
		}
		if(!StringUtils.isNull(xydm)){
			sb.append( " and xydm=?");
			value.add(xydm);
		}
		if(!StringUtils.isNull(zydm)){
			sb.append( " and zydm=?");
			value.add(zydm);
		}
		if(!StringUtils.isNull(bjdm)){
			sb.append( " and bjdm=?");
			value.add(bjdm);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(sjhm)){
			sb.append( " and sjhm like '%'||?||'%'");
			value.add(sjhm);
		}
		if(!StringUtils.isNull(bdzkwdwmc)){
			sb.append( " and bdzkwdwmc like '%'||?||'%'");
			value.add(bdzkwdwmc);
		}
		if(!StringUtils.isNull(bdzlx)){
			sb.append( " and bdzlx =?");
			value.add(bdzlx);
		}
		if(!StringUtils.isNull(fdysh)){
			sb.append( " and fdysh =?");
			value.add(fdysh);
		}
		if(!StringUtils.isNull(xysh)){
			sb.append( " and xysh =?");
			value.add(xysh);
		}
		if(!StringUtils.isNull(bdzkcsj)){
			sb.append( " and bdzkcsj like '%'||?||'%'");
			value.add(bdzkcsj);
		}
		if(!StringUtils.isNull(sfklq)){
			sb.append( " and sfklq =?");
			value.add(sfklq);
		}if(!StringUtils.isNull(lqr)){
			sb.append( " and lqr like '%'||?||'%'");
			value.add(lqr);
		}
		if("true".equalsIgnoreCase(isFdy)){
			sb.append( " and exists(select 1 from view_fdybbj b where a.bjdm=b.bjdm and b.zgh=?)");
			value.add(userName);
		}
		if("true".equalsIgnoreCase(isBzr)){
			sb.append( " and exists(select 1 from bzrbbb b where a.bjdm=b.bjdm and b.zgh=?)");
			value.add(userName);
		}
		return sb;
	}
	
	/**
	 * 判断表的记录是否存在
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*)num from " + tableName + " where " + pk + "=?";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		return Integer.parseInt(StringUtils.isNull(num)? "0" : num) >0 ? true : false;
	}
	
	/**
	 * 根据主键查询报到证办理申请信息
	 * @param BdzblForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectBdzblsqbOne(BdzblForm model){
		String sql = "select * from view_bdzblsqb where xh=?";
		String[] colList = {"bdzh","bdzkcsj","bdzkwdwmc","bdzlx","bjdm","bjmc","dajwdwbm","dajwdwmc","fdysh","lqr","lqsj","lxdz","lxfs","lxyb","nd","nj","sfklq","sjhm","xb","xh","xm","xydm","xymc","xysh","xz","zydm","zymc"};
		return getMap(sql, new String[]{model.getXh()}, colList);
	}
	
	/**
	 * 报到证办理申请信息批量删除
	 * @param BdzblForm model
	 * @return boolean
	 * */
	public boolean deleteBdzblsqb(BdzblForm model){
		boolean result = true;
		String[] pkValue = model.getPkV();
		String[] sqlArr = new String[pkValue.length];
		for(int i=0; i<pkValue.length; i++){
			if(StringUtils.isNotNull(pkValue[i])){
				sqlArr[i] = "delete from view_bdzblsqb where xh='" + pkValue[i] + "'" ;
			}
		}
		try{
			runBatch(sqlArr);
		}catch(Exception ex){
			ex.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 报到证申请信息查询
	 * @param BdzblForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectBdzblsqb(BdzblForm model,String[] colList){
		String sql = "select a.* from view_bdzblsqb a " + getWhereSql(model);
		
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from (" + sql + ")", value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select a.* from (select a.*,rownum r from (" + sql + ") a)a where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize());
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, colList);
	}
	
	/**
	 * 报到证申请信息导出查询
	 * @param BdzblForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectBdzblsqForExport(BdzblForm model,String[] colList){
		String sql = "select a.* from view_bdzblsqb a " + getWhereSql(model);		
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, colList);
	}
	
	
	/**
	 * 查询学生的生源地信息
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSyd(String xh){
		String sql = "select syd from view_xsxxb where xh=?";
		return getMap(sql, new String[]{xh}, new String[]{"syd"});
	}
	
	/**
	 * 查询单位名称
	 * @param String xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getBdzkwdwmc(String xh){
		String sql = "select dwmc mc from jygl_jyxy where xsxh=? and xxsh='已通过√'";
		return getMap(sql, new String[]{xh}, new String[]{"mc"});
	}
}
