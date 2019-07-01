package xgxt.qgzx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生岗位管理DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-11-30</p>
 */
public class QgzxXskhDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * */
	public StringBuffer getWhereSql(QgzxForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String nd = model.getNd();
		String yf = model.getYf();
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		String gwdm = model.getGwdm();
		
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(yf)){
			sb.append( " and yf=?");
			value.add(yf);
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
		if(!StringUtils.isNull(gwdm)){
			sb.append( " and gwdm=?");
			value.add(gwdm);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(checkExists("yrdwdmb", "dlm", model.getUserName())){
			if(StringUtils.isNull(gwdm)){
				sb.append( " and exists(select 1 from yrdwdmb b where b.yrdwdm=a.sqdw and b.dlm=?)");
				value.add(model.getUserName());
			}
		}
		return sb;
	}
	
	/**
	 * 检测记录是否存在
	 * @param String tableName
	 * @param String pk
	 * @param String pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + " =?";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		num = StringUtils.isNull(num) ? "0" : num;
		return Integer.parseInt(num) >0 ? true : false;
	}
	
	/**
	 * 查询学生工作记录信息
	 * @param QgzxForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsgzjl(QgzxForm model) throws Exception{
		String sql = "select * from (select rownum r,xh||gwdm||'-'||gwsbsj||nd||yf||rq 主键,a.* from view_qgzx_xsgzjlb a " + getWhereSql(model).toString() + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize()) ;
		String[] outputValue = new String[]{"主键","xh","xm","bjmc","gwdm","nd","yf","rq","zgzsj","lxdh"};
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		System.out.println(sql);
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs("select count(*)num from view_qgzx_xsgzjlb a" + getWhereSql(model).toString(), input, "num") ) );
		return rsToVator(sql, input, outputValue);
	}
	
	/**
	 * 查询学生工作记录信息导出
	 * @param QgzxForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsgzjlForExport(QgzxForm model,String[] output) throws Exception{
		String sql = "select rownum r,xh||gwdm||'-'||gwsbsj||nd||yf||rq 主键,a.* from view_qgzx_xsgzjlb a " + getWhereSql(model).toString();
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		return rsToVator(sql, input, output);
	}
	
	/**
	 * 查询单条学生工作记录信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsgzjlxxOne(String pkValue){
		String sql = "select * from view_qgzx_xsgzjlb where xh||gwdm||'-'||gwsbsj||nd||yf||rq=?";
		String[] cols = {"bjdm","bjmc","gwdm","gwsbsj","gzjssj","gzkssj","gznr","lxdh","nd","nj","rq","xb","xh","xm","xydm","xymc","xz","yf","zgzsj","zydm","zymc"};
		return getMap(sql, new String[]{pkValue}, cols);
	}
	
	/**
	 * 查询学生工作考核信息
	 * @param QgzxForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsgzkhxx(QgzxForm model){
		String sql = "select * from (select rownum r,xh||gwdm||'-'||gwsbsj||nd||yf 主键,a.* from (select distinct xh,xm,nd,yf,gwdm,gwsbsj,xydm,zydm,bjdm,nj,xymc,zymc,bjmc,sqdw from view_qgzx_xsgzjlb) a " + getWhereSql(model).toString() + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart() + model.getPages().getPageSize()) ;
		String[] outputValue = new String[]{"主键","xh","xm","bjmc","gwdm","nd","yf"};
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs("select count(*) count from (select distinct xh,xm,nd,yf,gwdm,gwsbsj,xydm,zydm,bjdm,nj,xymc,zymc,bjmc,sqdw from view_qgzx_xsgzjlb) a "+getWhereSql(model).toString(), input, "count")));
		return rsToVator(sql, input, outputValue);
	}
	
	/**
	 * 查询学生工作考核信息导出
	 * @param QgzxForm model
	 * @param String[] colList
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsgzkhxxForExport(QgzxForm model, String[] colList){
		String sql = "select a.* from (select distinct xh,xm,xb,nj,xydm,xymc,zydm,zymc,bjdm,bjmc,xz,nd,yf,gwdm,gwsbsj,sqdw,lxdh,(select ygzsj from view_qgzx_xsgzkhb b where a.xh=b.xh and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and a.nd=b.nd and a.yf=b.yf) ygzsj,(select ffcjje from view_qgzx_xsgzkhb b where a.xh=b.xh and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and a.nd=b.nd and a.yf=b.yf) ffcjje,(select bz from view_qgzx_xsgzkhb b where a.xh=b.xh and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and a.nd=b.nd and a.yf=b.yf) bz from view_qgzx_xsgzjlb a) a " + getWhereSql(model).toString();
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		return rsToVator(sql, input, colList);
	}
	
	/**
	 * 查询学生月工作记录信息
	 * @param String pkValue
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsgzjlByYf(String pkValue){
		String sql = "select nd,yf,rq,gzkssj,gzjssj,zgzsj,gznr from view_qgzx_xsgzjlb where xh||gwdm||'-'||gwsbsj||nd||yf=? order by rq desc";
		String[] outPut = {"rq","gzkssj","gzjssj","zgzsj","gznr"};
		return rsToVator(sql, new String[]{pkValue}, outPut);
	}
	
	/**
	 * 查询学生月工作考核信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXsgzkhxxOne(String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select ygzsj,ffcjje,bz,lxdh,xymc,zymc,bjmc,gzbx,gzpj from view_qgzx_xsgzkhb a where xh||gwdm||'-'||gwsbsj||nd||yf=?";
		String[] outPut = {"ygzsj","ffcjje","bz","lxdh","xymc","zymc","bjmc","gzbx","gzpj"};
		map.putAll(getMap(sql, new String[]{pkValue}, outPut));
		//查询基本信息
		sql = "select xh,xm,bjmc,xymc,zymc,gwdm,gwsbsj,nd,yf,sum(zgzsj)sj,max((select kh from view_xsxxb b where a.xh=b.xh))kh,max((select ssbh from view_xsxxb b where a.xh=b.xh)) ssbh  from view_qgzx_xsgzjlb a where xh||gwdm||'-'||gwsbsj||nd||yf=? group by xh,xm,bjmc,xymc,zymc,gwdm,gwsbsj,nd,yf";
		map.putAll(getMap(sql, new String[]{pkValue}, new String[]{"xh","xm","bjmc","gwdm","gwsbsj","nd","yf","sj","xymc","zymc","kh","ssbh"}));
		return map;
	}
		
	/**
	 * 查询学生申请的岗位信息
	 * @param String xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectGwList(String xh){
		String sql = "select gwdm||'-'||gwsbsj xmdm ,gwdm xmmc from view_xsgwxx where xxyj='通过' and xh=? ";
		return getList(sql, new String[]{xh}, new String[]{"xmdm","xmmc"});
	}
	
	/**
	 * 学生工作记录信息删除
	 * @param QgzxForm model
	 * @return boolean
	 * */
	public boolean deleteXsgzjl(QgzxForm model){
		boolean result = true;
		String[] value = model.getPkV();
		String[] sqlArr = new String[value.length];
		for(int i=0; i<value.length; i++){
			sqlArr[i] = "delete from qgzx_xsgzjlb where xh||gwdm||'-'||gwsbsj||nd||yf||rq ='" + value[i] + "'";
		}
		try{
			runBatch(sqlArr);
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
	/**
	 * 批量插入学生工作记录信息
	 * @param QgzxForm model
	 * @param List<String[]> list
	 * @return boolean
	 * */
	public boolean insertXsgzjlBatch(QgzxForm model, List<String[]> list){
		boolean result = true;
		String xh = model.getXh();
		String gwdm = model.getGwdm();
		String gwsbsj = model.getGwsbsj();
		String nd = model.getNd();
		String yf = model.getYf();
		String rq = "";
		String gzkssj = "";
		String gzjssj = "";
		String zgzsj = "";
		String gznr = "";
		String[] values = new String[5];
		
		String[] delArr = new String[list.size()];
		String[] insArr = new String[list.size()];
		for(int i=0; i<list.size(); i++){
			values = list.get(i);
			rq = values[0];
			gzkssj =  values[1];
			gzjssj = values[2];
			zgzsj = values[3];
			gznr = values[4];
			delArr[i] = "delete from qgzx_xsgzjlb where xh||gwdm||gwsbsj||nd||yf||rq='" + xh+gwdm+gwsbsj+nd+yf+rq+"'";
			insArr[i] = "insert into qgzx_xsgzjlb (gwdm,gwsbsj,gzjssj,gzkssj,gznr,nd,rq,xh,yf,zgzsj) values('"+gwdm+"','"+gwsbsj+"','"+gzjssj+"','"+gzkssj+"','"+gznr+"','"+nd+"','"+rq+"','"+xh+"','"+yf+"','"+zgzsj+"')";
		}
		
		try{
			runBatch(delArr);
			runBatch(insArr);
		}catch(Exception e){
			e.printStackTrace();
			result = false;
		}
		return result;
	}
	
}
