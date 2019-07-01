package xgxt.pjpy.zjcm.xfjs;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.comm.search.SearchService;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.User;
import xgxt.studentInfo.dao.XsxxglDAO;
import xgxt.utils.GetTime;
import xgxt.utils.MathCommon;
import xgxt.utils.String.StringUtils;

public class PjpyXfjsDAO extends DAO {
	 ArrayList<String> values = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * @param PjpyXfjsForm model
	 * @return StringBuffer
	 * @throws Exception
	 * */
	public StringBuffer getWhereSql(PjpyXfjsForm model) throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xh = model.getXh();
		String xm = DealString.toGBK(model.getXm());
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj= model.getNj();
		String ccrq = DealString.toGBK(model.getCcrq());
		String jclxdm = model.getJclxdm();
		String xn = model.getXn();
		String xq = model.getXq();
		String wjlxdm = model.getWjlxdm();
		String qjlxdm = model.getQjlxdm();
		String sfyr = model.getSfyr();
		String sfzgdsjcl = model.getSfzgdsjcl();
		String sfcl = model.getSfcl();
		String xxsh = model.getXxsh();
		String ccyhlx = model.getCcyhlx();
		String ccrqks = model.getCcrqks();
		String ccrqjs = model.getCcrqjs();
		
		
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and a.xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and a.zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and a.bjdm = ?");
			values.add(bjdm);
		}
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and a.xh like ?");
			values.add("%" + xh + "%");
		}
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and a.nj = ?");
			values.add(nj);
		}
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and a.xm like ?");
			values.add("%" + xm + "%");
		}
		if (!StringUtils.isNull(ccrq)) {
			whereSql.append(" and a.ccrq like ?");
			values.add("%" + ccrq + "%");
		}		
		if (!StringUtils.isNull(jclxdm)) {
			whereSql.append(" and a.jclxdm = ?");
			values.add(jclxdm);
		}
		if (!StringUtils.isNull(qjlxdm)) {
			whereSql.append(" and a.qjlxdm = ?");
			values.add(qjlxdm);
		}
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and a.xn = ?");
			values.add(xn);
		}
		
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and a.xq = ?");
			values.add(xq);
		}
		if (!StringUtils.isNull(wjlxdm)) {
			whereSql.append(" and a.wjlxdm = ?");
			values.add(wjlxdm);
		}
		if (!StringUtils.isNull(ccyhlx)) {
			whereSql.append(" and a.ccyhlx = ?");
			values.add(ccyhlx);
		}		
		if (!StringUtils.isNull(ccrqks)) {
			whereSql.append(" and to_number(substr(a.ccrq,0,4)||substr(a.ccrq,6,2)||substr(a.ccrq,9,2)) >= to_number(?)");
			values.add(ccrqks.replaceAll("-", ""));
		}
		if (!StringUtils.isNull(ccrqjs)) {
			whereSql.append(" and to_number(substr(a.ccrq,0,4)||substr(a.ccrq,6,2)||substr(a.ccrq,9,2)) <= to_number(?)");
			values.add(ccrqjs.replaceAll("-", ""));
		}		
		
		//是否有人
		if (!StringUtils.isNull(sfyr) && "1".equalsIgnoreCase(sfyr)) {
			whereSql.append(" and to_number(nvl(qqrs,0))>0");
		}
		if (!StringUtils.isNull(sfyr) && "0".equalsIgnoreCase(sfyr)) {
			whereSql.append(" and to_number(nvl(qqrs,0))<=0");
		}
		
		//是否在规定的时间内处理
		if (!StringUtils.isNull(sfzgdsjcl) && "1".equalsIgnoreCase(sfzgdsjcl)) {
			whereSql.append(" and to_number(substr(nvl(fdyclsj,'3000-00-00'),0,4)||substr(nvl(fdyclsj,'3000-00-00'),6,2)||substr(nvl(fdyclsj,'3000-00-00'),9,2)) >=to_number(substr(nvl(fdysjclsj,to_char(sysdate,'yyyy-mm-dd')),0,4)||substr(nvl(fdysjclsj,to_char(sysdate,'yyyy-mm-dd')),6,2)||substr(nvl(fdysjclsj,to_char(sysdate,'yyyy-mm-dd')),9,2)) ");
		}
		if (!StringUtils.isNull(sfzgdsjcl) && "0".equalsIgnoreCase(sfzgdsjcl)) {
			whereSql.append(" and to_number(substr(nvl(fdyclsj,'3000-00-00'),0,4)||substr(nvl(fdyclsj,'3000-00-00'),6,2)||substr(nvl(fdyclsj,'3000-00-00'),9,2)) <to_number(substr(nvl(fdysjclsj,to_char(sysdate,'yyyy-mm-dd')),0,4)||substr(nvl(fdysjclsj,to_char(sysdate,'yyyy-mm-dd')),6,2)||substr(nvl(fdysjclsj,to_char(sysdate,'yyyy-mm-dd')),9,2)) ");
		}
		//是否处理
		if (!StringUtils.isNull(sfcl) && "1".equalsIgnoreCase(sfcl)) {
			whereSql.append(" and fdysjclsj is not null ");
		}
		if (!StringUtils.isNull(sfcl) && "0".equalsIgnoreCase(sfcl)) {
			whereSql.append(" and fdysjclsj is null ");
		}
		
		if (!StringUtils.isNull(xxsh)) {
			whereSql.append(" and xxsh=? ");
			values.add(xxsh);
		}	
		
		return whereSql;
	}
	
	/**
	 * 查询代码表信息
	 * @param String tableName
	 * @param String[] colList
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectDmList(String tableName, String[] colList){
		String sql = "select * from " + tableName;
		if ("pjpy_xfjs_wjlxdmb".equalsIgnoreCase(tableName)) {
			sql = "select * from " + tableName + " where sfxs is null";
		}
		return getList(sql, new String[]{}, colList);
	}
	
	/**
	 * 学生抽查情况表查询
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> selectXsccqkb(PjpyXfjsForm model,String[] colList,User user) throws Exception{
		int start = model.getPages().getStart();
		int page_size = model.getPages().getPageSize();
		StringBuffer  sb = getWhereSql(model);
		StringBuffer sql = new StringBuffer();
		
		
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		
		
		sql.append("select * from (select * from (select a.bjdm,a.bjmc,a.bz,a.ccrq,decode(a.ccyhlx,'xx','学校抽查','xy','学院抽查')ccyhlx,a.fdyclbz,a.fdyclsj,a.fdysjclsj,a.jclxdm,a.jclxmc,a.nj,a.qqrs,a.sdrs,a.wjrs,a.xn,a.xq,a.xqmc,a.xxsh,a.xydm,a.xymc,a.ydrs,a.zydm,a.zymc,rownum r,xn||xq||bjdm||ccrq||jclxdm||ccyhlx pk from view_pjpy_xfjs_bjccqkb a ");
		sql.append(sb);
		sql.append(searchTjByUser);
		sql.append(") where r<=");
		sql.append(start+page_size);
		sql.append(" ) where r> ");
		sql.append(start);
		
		//设置总记录数
		String sqlCount = "select count(*) count from view_pjpy_xfjs_bjccqkb a " + sb.toString() + searchTjByUser ;
		String count  = getOneRs(sqlCount, values != null ? values.toArray(new String[0]) : new String[]{}, "count");
		count = StringUtils.isNull(count) ? "0" : count;		
		model.getPages().setMaxRecord(Integer.parseInt(count));
		System.out.println("-------------------------"+sql+"-------------------------");
		if(values != null){
			for(int i = 0 ; i<values.size();i++){
				System.out.println("-------------------------"+values.get(i)+"-------------------------");
			}
			
		}
		
		return rsToVator(sql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, colList);
	}
	
	/**
	 * 学生抽查情况表查询(不分页)
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> selectXsccqkForExport(PjpyXfjsForm model,String[] colList) throws Exception{
		StringBuffer  sb = getWhereSql(model);
		StringBuffer sql = new StringBuffer();
		sql.append("select a.bjdm,a.bjmc,a.bz,a.ccrq,decode(a.ccyhlx,'xx','学校抽查','xy','学院抽查')ccyhlx,a.fdyclbz,a.fdyclsj,a.fdysjclsj,a.jclxdm,a.jclxmc,a.nj,a.qqrs,a.sdrs,a.wjrs,a.xn,a.xq,a.xqmc,a.xxsh,a.xydm,a.xymc,a.ydrs,a.zydm,a.zymc,rownum r,xn||xq||bjdm||ccrq||jclxdm pk from view_pjpy_xfjs_bjccqkb a ");
		sql.append(sb);
		
		return rsToVator(sql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, colList);
	}
	
	/**
	 * 学生考勤情况表查询
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> selectXskqjljcb(PjpyXfjsForm model,String[] colList) throws Exception{
		int start = model.getPages().getStart();
		int page_size = model.getPages().getPageSize();
		StringBuffer  sb = getWhereSql(model);
		StringBuffer sql = new StringBuffer();
		
		sql.append("select * from (select * from (select bjdm,a.bjmc,a.bz,a.ccrq,decode(a.ccyhlx,'xx','学校抽查','xy','学院抽查')ccyhlx,a.jclxdm,a.jclxmc,a.nj,a.qjlxdm,a.qjlxmc,a.wjcs,a.wjlxdm,a.wjlxmc,a.xb,a.xh,a.xm,a.xn,a.xq,a.xqmc,a.xydm,a.xymc,a.zydm,a.zymc,rownum r,xh||xn||xq||bjdm||ccrq||jclxdm||ccyhlx pk from view_pjpy_xfjs_xsjljcb a ");
		sql.append(sb);
		sql.append(") where r<=");
		sql.append(start+page_size);
		sql.append(" ) where r> ");
		sql.append(start);
		
		//设置总记录数
		String sqlCount = "select count(*) count from view_pjpy_xfjs_xsjljcb a " + sb.toString();
		String count  = getOneRs(sqlCount, values != null ? values.toArray(new String[0]) : new String[]{}, "count");
		count = StringUtils.isNull(count) ? "0" : count;		
		model.getPages().setMaxRecord(Integer.parseInt(count));
		
		return rsToVator(sql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, colList);
	}
	
	/**
	 * 学生考勤情况表查询(不分页)
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> selectXskqjljcbForExport(PjpyXfjsForm model,String[] colList) throws Exception{
		StringBuffer  sb = getWhereSql(model);
		StringBuffer sql = new StringBuffer();		
		sql.append("select bjdm,a.bjmc,a.bz,a.ccrq,decode(a.ccyhlx,'xx','学校抽查','xy','学院抽查')ccyhlx,a.jclxdm,a.jclxmc,a.nj,a.qjlxdm,a.qjlxmc,a.wjcs,a.wjlxdm,a.wjlxmc,a.xb,a.xh,a.xm,a.xn,a.xq,a.xqmc,a.xydm,a.xymc,a.zydm,a.zymc,rownum r,xh||xn||xq||bjdm||ccrq||jclxdm||ccyhlx pk from view_pjpy_xfjs_xsjljcb a ");
		sql.append(sb);
		
		return rsToVator(sql.toString(), values != null ? values.toArray(new String[0]) : new String[]{}, colList);
	}
	
	/**
	 * 根据主键查询班级抽查情况信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectBjccqkbByPk(String pkValue){
		String sql = "select a.* ,a.xn||a.xq||a.bjdm||a.ccrq||a.jclxdm||ccyhlx pk from view_pjpy_xfjs_bjccqkb a  where xn||xq||bjdm||ccrq||jclxdm||ccyhlx = ?";
		String[] outputValue = new String[]{"pk","xn","xq","bjdm","ccrq","jclxdm","ydrs","sdrs","qqrs","bjmc","xymc","xydm","zymc","zydm","nj","xqmc","jclxmc","wjrs","fdyclsj","bz","fdyclbz","fdysjclsj","xxsh","ccyhlx"};
		
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 根据班级抽查情况主键查询学生抽查情况列表
	 * @param String bjccPkValue
	 * @return ArrayList<String[]>
	 * */
	public ArrayList<String[]> selectXsccqkb(String bjccPkValue){
		String sql = "select a.*, a.xh||a.xn||a.xq||a.bjdm||a.ccrq||a.jclxdm||a.ccyhlx pk from view_pjpy_xfjs_xsjljcb a where xn||xq||bjdm||ccrq||jclxdm||ccyhlx=?";
		String[] outputValue = new String[]{"pk","xh","xm","wjlxmc","wjcs","qjlxmc","bz","xb","xn","xqmc","ccrq","jclxmc","xq","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jclxdm","wjlxdm","ccyhlx"};
		return rsToVator(sql, new String[]{bjccPkValue}, outputValue);
	}
	
	/**
	 * 根据班级抽查情况主键查询学生抽查情况列表
	 * @param String bjccPkValue
	 * @return ArrayList<String[]>
	 * */
	public List<HashMap<String, String>> selectXsccqkbByBj(String bjccPkValue){
		String sql = "select a.*, a.xh||a.xn||a.xq||a.bjdm||a.ccrq||a.jclxdm pk from view_pjpy_xfjs_xsjljcb a where xn||xq||bjdm||ccrq||jclxdm||ccyhlx=?";
		String[] outputValue = new String[]{"pk","xh","xm","wjlxmc","wjcs","bz","xb","xn","xqmc","ccrq","jclxmc","xq","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jclxdm","wjlxdm","qjlxdm","ccyhlx"};
		return getList(sql, new String[]{bjccPkValue}, outputValue);
	}
	
	/**
	 * 根据学生抽查情况表主键查询
	 * */
	public HashMap<String, String> selectXsccqkbByPk(String pkValue){
		String sql = "select a.*, a.xh||a.xn||a.xq||a.bjdm||a.ccrq||a.jclxdm||ccyhlx pk from view_pjpy_xfjs_xsjljcb a where xh||xn||xq||bjdm||ccrq||jclxdm||ccyhlx=?";
		String[] outputValue = new String[]{"pk","xn","xq","xqmc","xh","ccrq","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc","jclxdm","jclxmc","wjlxdm","wjlxmc","wjcs","bz","qjlxdm","qjlxmc","ccyhlx"};
		return getMap(sql, new String[]{pkValue}, outputValue);
	} 
	
	/**
	 * 班级抽查信息插入
	 * @param PjpyXfjsForm model
	 * @return boolean
	 * @throws SQLException
	 * */
	public boolean insertBjccqkb(PjpyXfjsForm model,HttpServletRequest request) throws SQLException{
		String[] columns = new String[]{"xn","xq","bjdm","ccrq","jclxdm","ydrs","sdrs","qqrs","wjrs","fdyclsj","ccyhlx","bz"};
		String[] values = new String[]{model.getXn(),model.getXq(),model.getBjdm(),model.getCcrq(),model.getJclxdm(),model.getYdrs(),model.getSdrs(),model.getQqrs(),model.getWjrs(),model.getFdyclsj(),model.getCcyhlx(),model.getBz()}; 
		
		boolean result = StandardOperation.insert("pjpy_xfjs_bjccqkb", columns, values, request); 
		return result;
	}
	
	/**
	 * 班级抽查信息修改
	 * @param PjpyXfjsForm model
	 * @return boolean
	 * @throws Exception 
	 * */
	public boolean updateBjccqkb(PjpyXfjsForm model,HttpServletRequest request) throws Exception{
		String[] columns = new String[]{"fdyclbz","fdysjclsj"};
		String[] values = new String[]{model.getFdyclbz(),GetTime.getSystemTime()}; 
		String primaryKey = "xn||xq||bjdm||ccrq||jclxdm||ccyhlx";
		String pkValue = model.getXn()+model.getXq()+model.getBjdm()+model.getCcrq()+model.getJclxdm()+model.getCcyhlx();
		
		boolean result = StandardOperation.update("pjpy_xfjs_bjccqkb", columns, values, primaryKey, pkValue, request); 
		return result;
	}
	
	
	/**
	 * 班级抽查情况审核
	 * @param PjpyXfjsForm model
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean audiBjccqkb(PjpyXfjsForm model){
		String[] pkV = model.getCbv();
		String pkStr = "xn||xq||bjdm||ccrq||jclxdm||ccyhlx";
		String[] sqlArr = new String[pkV.length];
		boolean result = true;
		for(int i=0; i<pkV.length; i++){
			if(!Base.isNull(pkV[i])){
				sqlArr[i] = "update pjpy_xfjs_bjccqkb set xxsh='" + model.getXxsh()+"' where " + pkStr + "='" + pkV[i] + "'";
			}
		}
		try{
			runBatch(sqlArr);			
		}catch(Exception e){
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 删除班级抽查情况表
	 * @param PjpyXfjsForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws Exception
	 * */
	public boolean deleteBjccqkb(PjpyXfjsForm model,HttpServletRequest request) throws Exception{
		String[] pkV = model.getCbv();
		String pkStr = "xn||xq||bjdm||ccrq||jclxdm||ccyhlx";
		for(int i=0; i<pkV.length; i++){
			boolean result = StandardOperation.delete("pjpy_xfjs_bjccqkb", pkStr, pkV[i], request);
			if(result){
				//删除该次检测的学生
				result = StandardOperation.delete("pjpy_xfjs_xsjljcb", pkStr, pkV[i], request);
			}
			if(!result){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 保存学生抽查情况信息
	 * @param PjpyXfjsForm model
	 * @param HttpServletRequest request
	 * @return boolean
	 * @throws SQLException 
	 * */
	public boolean saveXsccqkb(PjpyXfjsForm model, HttpServletRequest request) throws SQLException{
		boolean result = false;
		String[] sqlList = new String[model.getXhArr().length + 1];
		StringBuffer sql = null;
		try {
			//删除学生抽查情况信息
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXn()).append(model.getXq()).append(model.getCcrq()).append(model.getBjdm()).append(model.getJclxdm()).append(model.getCcyhlx());
			
			StandardOperation.delete("pjpy_xfjs_xsjljcb", "xn||xq||ccrq||bjdm||jclxdm||ccyhlx", buff.toString(),request);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getXhArr().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into pjpy_xfjs_xsjljcb(xh,bjdm,xn,xq,ccrq,jclxdm,ccyhlx,wjlxdm,wjcs,qjlxdm,bz)values('");
			sql.append(model.getXhArr()[i]);
			sql.append("','");
			sql.append(model.getBjdm());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(model.getCcrq());
			sql.append("','");
			sql.append(model.getJclxdm());
			sql.append("','");
			sql.append(model.getCcyhlx());
			sql.append("','");
			sql.append(model.getWjlxdmArr()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getWjcsArr()[i].replace("'","‘")));
			sql.append("','");
			sql.append(model.getQjlxdmArr()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getBzArr()[i].replace("'","‘")));
			sql.append("')");
			
			if (StringUtils.isNull(model.getXhArr()[i])) {// 为空则不记录
				sql = new StringBuffer("");
			}
			sqlList[i] = sql.toString();
		}		
		int[] res = runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			result = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!result)
				break;
		}
		return result;
	}
	
	/**
	 * 根据班级代码获取班级人数
	 * @param String bjdm
	 * @return int
	 * */
	public int selectBjrs(String bjdm){
		String sql = "select count(*) count from view_xsbfxx a where a.bjdm=? and sfzx= '在校'";
		String count = getOneRs(sql, new String[]{bjdm}, "count");
		count = StringUtils.isNull(count) ? "0" : count;
		
		return Integer.parseInt(count);
	}
	
	/**
	 * 判断班级抽查情况记录是否存在
	 * @param String value
	 * @return boolean
	 * */
	public boolean bjccqkExists(String value){
		String sql = "select count(*) num from  pjpy_xfjs_bjccqkb where xn||xq||ccrq||bjdm||jclxdm||ccyhlx=?";
		String count = getOneRs(sql, new String[]{value}, "num");
		count = StringUtils.isNull(count) ? "0" : count;
		
		return Integer.parseInt(count) >0 ? true : false;
	}
	
	/**
    * 查询接受检查班级的全部学生
    * @param String pk
    * @return ArrayList<String[]>
    * */
	public ArrayList<String[]> selectBjStuList(String pk){
		String sql = "select xh,xm,xb from view_xsbfxx where sfzx= '在校' and bjdm=(select distinct bjdm from pjpy_xfjs_bjccqkb where xn||xq||bjdm||ccrq||jclxdm||ccyhlx=?) order by xh";
		return rsToVator(sql, new String[]{pk}, new String[]{"xh","xm","xb"});
	}
	
	/**
	 * 根据旷课节数获取学生应受到何种处分
	 * @param String pkValue
	 * @return String
	 * */
	public HashMap<String, String> getWjxx(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String result = "";
		//基数
		String sql = "select round(count(*)/3) num from pjpy_xfjs_xsjljcb where xh||xn||xq||ccrq||jclxdm||bjdm=? group by wjlxdm";
		int js = Integer.parseInt(getOneRs(sql, new String[]{pkValue}, "num"));
		
		//旷课次数
		sql = "select sum(wjcs) kkjs from pjpy_xfjs_xsjljcb where xh||xn||xq||ccrq||jclxdm||bjdm=? and wjlxdm='001'";//旷课节数
		result = getOneRs(sql, new String[]{pkValue}, "kkjs");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		int kkjs = Integer.parseInt(result);
		
		int zkkjs = kkjs+js;
		//查询应该获得的处分
		
		sql = "select cf from pjpy_xfjs_kkcfszb where kkjs <? order by to_number(kkjs) desc";
		result = getOneRs(sql, new String[]{zkkjs+""}, "cf");
		
		map.put("kkzjs", zkkjs+"");
		map.put("cf", result);
		return map;
	}
	
	/**
	 * 抽查情况统计查询
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> selectBjccqktjxx(PjpyXfjsForm model,String[] colList) throws Exception{
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		StringBuffer qualification = new StringBuffer();
		StringBuffer pkValue = new StringBuffer();
		qualification.append(Base.isNull(model.getXn()) ? "" : model.getXn()+"学年");
		qualification.append(Base.isNull(model.getXq()) ? "" : xsxxglDao.selectXqmc(model.getXq())+"学期");
		qualification.append(Base.isNull(model.getNj()) ? "" : model.getNj()+"年级");
		qualification.append(Base.isNull(model.getXydm()) ? "" : model.getXymc());
		qualification.append(Base.isNull(model.getZydm()) ? "" : model.getZymc());
		qualification.append(Base.isNull(model.getBjdm()) ? "" : model.getBjmc());
		qualification.append(Base.isNull(model.getJclxdm()) ? "" : model.getJclxmc());
		
		pkValue.append(Base.isNull(model.getXn()) ? "" : model.getXn());
		pkValue.append(Base.isNull(model.getXq()) ? "" : model.getXq());
		pkValue.append(Base.isNull(model.getNj()) ? "" : model.getNj());
		pkValue.append(Base.isNull(model.getXydm()) ? "" : model.getXydm());
		pkValue.append(Base.isNull(model.getZydm()) ? "" : model.getZydm());
		pkValue.append(Base.isNull(model.getBjdm()) ? "" : model.getBjdm());
		pkValue.append(Base.isNull(model.getJclxdm()) ? "" : model.getJclxdm());
		
		String sql = "select '" + pkValue + "'||bjdm pkValue, '" + qualification + "' qualification,xymc,zymc,nj, bjdm,bjmc,sum(nvl(qqrs,0)+nvl(wjrs,0))wjcs,count(*) cccs from view_pjpy_xfjs_bjccqkb a " + getWhereSql(model).toString() + " and fdysjclsj is not null group by xymc,zymc,bjmc,nj,bjdm,bjmc";
		String[] inputValue = values != null ? values.toArray(new String[0]) : new String[]{};
		String[] outputValue = {"pkValue","qualification","xymc","zymc","bjmc","nj","wjcs","cccs"};		
		return rsToVator(sql, inputValue, outputValue);
	}
	
	/**
	 * 学生考勤情况统计信息查询
	 * @param PjpyXfjsForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * @throws Exception 
	 * */
	public List<String[]> selectXskqqktjxx(PjpyXfjsForm model,String[] colList) throws Exception{
		XsxxglDAO xsxxglDao = new XsxxglDAO();
		StringBuffer qualification = new StringBuffer();
		StringBuffer pkValue = new StringBuffer();
		qualification.append(Base.isNull(model.getXn()) ? "" : model.getXn()+"学年");
		qualification.append(Base.isNull(model.getXq()) ? "" : xsxxglDao.selectXqmc(model.getXq())+"学期");
		qualification.append(Base.isNull(model.getNj()) ? "" : model.getNj()+"年级");
		qualification.append(Base.isNull(model.getXydm()) ? "" : model.getXymc());
		qualification.append(Base.isNull(model.getZydm()) ? "" : model.getZymc());
		qualification.append(Base.isNull(model.getBjdm()) ? "" : model.getBjmc());
		qualification.append(Base.isNull(model.getJclxdm()) ? "" : model.getJclxmc());
		qualification.append(Base.isNull(model.getWjlxdm()) ? "" : model.getWjlxmc());
		qualification.append(Base.isNull(model.getQjlxdm()) ? "" : model.getQjlxmc());
		qualification.append(Base.isNull(model.getXh()) ? "" : "学号："+ model.getXh());
		
		pkValue.append(Base.isNull(model.getXn()) ? "" : model.getXn());
		pkValue.append(Base.isNull(model.getXq()) ? "" : model.getXq());
		pkValue.append(Base.isNull(model.getNj()) ? "" : model.getNj());
		pkValue.append(Base.isNull(model.getXydm()) ? "" : model.getXydm());
		pkValue.append(Base.isNull(model.getZydm()) ? "" : model.getZydm());
		pkValue.append(Base.isNull(model.getBjdm()) ? "" : model.getBjdm());
		pkValue.append(Base.isNull(model.getJclxdm()) ? "" : model.getJclxdm());
		pkValue.append(Base.isNull(model.getWjlxdm()) ? "" : model.getWjlxdm());
		pkValue.append(Base.isNull(model.getQjlxdm()) ? "" : model.getQjlxdm());
		pkValue.append(Base.isNull(model.getXh()) ? "" : model.getXh());
		
		String whereSql = getWhereSql(model).toString();	
		
		
		//查询出学生的违纪、请假、旷课次数和除旷课外的违纪次数
		String sql = StringUtils.joinStr("select '" , pkValue.toString(), "'||xh pkValue, '" , 
						qualification.toString() , 
						"' qualification,xh,xm,xymc,zymc,bjmc,nj,wjcs,qjcs,kkjs,ckkwwjcs from(" ,
						"select xh, xm,xymc,zymc,bjmc,nj,sum(wjcs) wjcs,sum(qjcs)qjcs,sum(kkjs)kkjs,sum(ckkwwjcs)ckkwwjcs from(" , 
						"select xh, xm,xymc,zymc,bjmc,nj,count(1) wjcs,0 qjcs,0 kkjs,0 ckkwwjcs from view_pjpy_xfjs_xsjljcb a " ,
						whereSql, 
						" and wjlxdm is not null group by xh,xm,xymc,zymc,bjmc,nj " ,
						" union " , 
						" select xh, xm,xymc,zymc,bjmc,nj,0 wjcs,count(1) qjcs,0 kkjs,0 ckkwwjcs from view_pjpy_xfjs_xsjljcb a " , 
						whereSql, 
						" and qjlxdm is not null group by xh,xm,xymc,zymc,bjmc,nj " ,
						" union " ,  
						" select xh, xm,xymc,zymc,bjmc,nj,0 wjcs,0 qjcs, sum(nvl(wjcs,1)) kkjs,0 ckkwwjcs from view_pjpy_xfjs_xsjljcb a " , 
						whereSql, 
						" and wjlxdm is not null and  wjlxdm=(select wjlxdm from pjpy_xfjs_wjlxdmb where wjlxmc='旷课') group by xh,xm,xymc,zymc,bjmc,nj " ,
						" union " ,
						" select xh,xm,xymc,zymc,bjmc,nj,0 wjcs,0 qjcs,0 kkjs,count(1) ckkwwjcs from view_pjpy_xfjs_xsjljcb a " , 
						whereSql, 
						" and not exists(select 1 from pjpy_xfjs_wjlxdmb b where a.wjlxdm=b.wjlxdm and b.wjlxmc='旷课') and qjlxdm is null group by xh,xm,xymc,zymc,bjmc,nj " ,
						" ) group by xh,xm,xymc,zymc,bjmc,nj) a ");
		String[] inputValue = values != null ? values.toArray(new String[0]) : new String[]{};
		String[] outputValue = {"pkValue","qualification","xh","xm","xymc","bjmc","nj","wjcs","qjcs","kkjs","ckkwwjcs"};
		
		//将条件值的数组复制四份到另一个数组
		String[] input = new String[inputValue.length*4];
		int count = 0;
		for(int j=0; j<4;j++){			
			for(int i=0; i<inputValue.length; i++){
				input[i+count] = inputValue[i];				
			}
			if(j!=0){
				count=inputValue.length*j;
			}
		}
		
		//查询结果
		List<String[]> list = rsToVator(sql, input, outputValue);
		List<String[]> rs = new ArrayList<String[]>();
		String[] tmp = null;
		for(int i=0; i<list.size(); i++){
			tmp = list.get(i);			
			tmp[9] = Math.round(Double.parseDouble(tmp[9])+ MathCommon.getDouble(Double.parseDouble(tmp[10])/3, 0)) + "";
			rs.add(tmp);
		}
		return rs;
	}
	
	/**
	 * 查询班级抽查记录中不同用户类型的记录是否已经处理过
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean selectBjccqkfkYhlxRever(String pkValue){
		String sql = "select count(*)num from pjpy_xfjs_bjccqkb a where exists(select 1 from pjpy_xfjs_bjccqkb b where a.xn=b.xn and a.xq=b.xq and a.bjdm=b.bjdm and a.ccrq=b.ccrq and a.jclxdm=b.jclxdm and a.ccyhlx<>b.ccyhlx and b.xn||b.xq||b.bjdm||b.ccrq||b.jclxdm||b.ccyhlx=?) and fdysjclsj is not null";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		
		return Integer.parseInt(Base.isNull(num) ? "0" : num) >0 ? true : false;
	}
	
	/**
	 * 查询班级抽查记录是否已经处理过
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean selectBjccqkfk(String pkValue){
		String sql = "select count(*)num from pjpy_xfjs_bjccqkb b where b.xn||b.xq||b.bjdm||b.ccrq||b.jclxdm||b.ccyhlx=? and b.fdysjclsj is not null";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		
		return Integer.parseInt(Base.isNull(num) ? "0" : num) >0 ? true : false;
	}
	
	/**
    * 查询学生的考勤情况
    * @param String xh
    * @param String xn
    * @param String xq
    * @param String jcjs
    * @return List<HashMap<String, String>>
    * @throws Exception
    * */
	public List<HashMap<String, String>> selectXskqqk(PjpyXfjsForm model) throws Exception{
		String whereSql = getWhereSql(model).toString();
		//查询出学生的违纪、请假、旷课次数和除旷课外的违纪次数
		String sql = "select xh,xm,wjcs,qjcs,kkjs,ckkwwjcs from(" +
					 "select xh, xm,sum(wjcs) wjcs,sum(qjcs)qjcs,sum(kkjs)kkjs,sum(ckkwwjcs)ckkwwjcs from(" + 
					 "select xh, xm,count(1) wjcs,0 qjcs,0 kkjs,0 ckkwwjcs from view_pjpy_xfjs_xsjljcb a " + whereSql + " and wjlxdm is not null group by xh,xm " +
					 " union " +  
					 " select xh, xm,0 wjcs,count(1) qjcs,0 kkjs,0 ckkwwjcs from view_pjpy_xfjs_xsjljcb a " + whereSql + " and qjlxdm is not null group by xh,xm " +
					 " union " +  
					 " select xh,xm,0 wjcs,0 qjcs, sum(nvl(wjcs,1)) kkjs,0 ckkwwjcs from view_pjpy_xfjs_xsjljcb a " + whereSql + " and wjlxdm is not null and  wjlxdm=(select wjlxdm from pjpy_xfjs_wjlxdmb where wjlxmc='旷课') group by xh,xm " +
					 " union " +
					 " select xh,xm,0 wjcs,0 qjcs,0 kkjs,count(1) ckkwwjcs from view_pjpy_xfjs_xsjljcb a " + whereSql + " and not exists(select 1 from pjpy_xfjs_wjlxdmb b where a.wjlxdm=b.wjlxdm and b.wjlxmc='旷课') and qjlxdm is null group by xh,xm " +
					 " ) group by xh,xm)";
		String[] inputValue = values != null ? values.toArray(new String[0]) : new String[]{};
		String[] outputValue = {"xh","xm","wjcs","qjcs","kkjs","ckkwwjcs"};
		
		//将条件值的数组复制四份到另一个数组
		String[] input = new String[inputValue.length*4];
		int count = 0;
		for(int j=0; j<4;j++){			
			for(int i=0; i<inputValue.length; i++){
				input[i+count] = inputValue[i];				
			}
			if(j!=0){
				count=inputValue.length*j;
			}
		}
		
		//查询结果
		List<HashMap<String,String>> list = getList(sql, input, outputValue);
		List<HashMap<String, String>> rs = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> tmp = null;
		for(int i=0; i<list.size(); i++){			
			tmp = list.get(i);			
			tmp.put("kkjs", Math.round(Double.parseDouble(tmp.get("kkjs"))
					+ MathCommon.getDouble(Double.parseDouble(tmp.get("ckkwwjcs"))/3, 0)) 
					+ "");
			rs.add(tmp);
		}
		
		return rs;
	}
	
	/**
	 * 查询班级抽查情况统计详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<String[]> selectBjccqktjDetails(String pk, String pkValue){
		List<String[]> rs = null;
		String[] output = {"bjmc","wjlxmc","rs","kkjs"};
		String tjStr = "";
		String sql = "";
		tjStr = !Base.isNull(pk) ? StringUtils.joinStr(" and " , pk , "='" , pkValue + "'") : tjStr;
		//查询违纪人数
		sql = StringUtils.joinStr("select wjlxdm,wjlxmc,bjdm, bjmc, count(*) rs,sum(wjcs) kkjs from ",
	               "view_pjpy_xfjs_xsjljcb where qjlxdm is null ",
	               tjStr,
	               " group by bjdm, bjmc, wjlxdm ,wjlxmc");
		rs = rsToVator(sql,new String[]{}, output);
		//查询请假人数
		sql = StringUtils.joinStr("select qjlxdm wjlxdm,qjlxmc wjlxmc, bjdm, bjmc, count(*) rs,'' kkjs from " ,
				"view_pjpy_xfjs_xsjljcb where wjlxdm is null " ,
				tjStr , 
				" group by bjdm, bjmc, qjlxdm ,qjlxmc");
		rs.addAll(rsToVator(sql, new String[]{}, output));	
		return rs;
	}
	
	/**
	 * 查询学生考勤情况统计详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<String[]> selectXskqtjDetails(String pk, String pkValue){
		String tjStr = !Base.isNull(pk) ? StringUtils.joinStr(" where " , pk , "='" , pkValue + "'") : "";
		String sql = StringUtils.joinStr("select * from view_pjpy_xfjs_xsjljcb ",tjStr);
		
		return rsToVator(sql, new String[]{}, new String[]{"xn","xqmc","xh","xm","xymc","bjmc","ccrq","jclxmc","wjlxmc","qjlxmc","wjcs"});
	}
}
