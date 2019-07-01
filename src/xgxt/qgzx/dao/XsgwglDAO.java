package xgxt.qgzx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.form.CommanForm;
import xgxt.form.User;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生岗位管理DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-03-02</p>
 */
public class XsgwglDAO extends DAO {
ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 组合查询条件
	 * @param model
	 * @return StringBuffer
	 * */
	private StringBuffer getWhereSql(CommanForm model){
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String xm = model.getXm();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String yrdwdm = model.getYrdwdm();
		String gwdm = model.getGwdm();
		String xxyj = model.getXxyj();
		String xyyj = model.getXyyj();
		String fdyyj = model.getFdyyj();
		String gwxz = model.getGwxz();
		
		StringBuffer sb = new StringBuffer("where 1=1 ");
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
		if(yrdwdm !=null && !yrdwdm.equalsIgnoreCase("")){
			sb.append( " and yrdwdm=?");
			value.add(yrdwdm);
		}
		if(!StringUtils.isNull(gwdm)){
			sb.append( " and gwdm like '%'||?||'%'");
			value.add(gwdm);
		}
		if(!StringUtils.isNull(xxyj)){
			sb.append( " and xxyj=?");
			value.add(xxyj);
		}
		if(!StringUtils.isNull(xyyj)){
			sb.append( " and xyyj=?");
			value.add(xyyj);
		}
		if(!StringUtils.isNull(fdyyj)){
			sb.append( " and fdyyj=?");
			value.add(fdyyj);
		}
		if(!StringUtils.isNull(gwxz)){
			sb.append( " and gwxz=?");
			value.add(gwxz);
		}
		return sb;
	}
	
	/**
	 * 组合查询条件
	 * @param model
	 * @return StringBuffer
	 * */
	private StringBuffer getWhereSql(CommanForm model,User user){
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String xm = model.getXm();
		String xn = model.getXn();
		String nd = model.getNd();
		String xq = model.getXq();
		String yrdwdm = model.getYrdwdm();
		String gwdm = model.getGwdm();
		String xxyj = model.getXxyj();
		String xyyj = model.getXyyj();
		String fdyyj = model.getFdyyj();
		String gwxz = model.getGwxz();
				
		StringBuffer sb = new StringBuffer("where 1=1 ");
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
		if(yrdwdm !=null && !yrdwdm.equalsIgnoreCase("")){
			sb.append( " and yrdwdm=?");
			value.add(yrdwdm);
		}
		if(!StringUtils.isNull(gwdm)){
			sb.append( " and gwdm=?");
			value.add(gwdm);
		}
		if(!StringUtils.isNull(xxyj)){
			sb.append( " and xxyj=?");
			value.add(xxyj);
		}
		if(!StringUtils.isNull(xyyj)){
			sb.append( " and xyyj=?");
			value.add(xyyj);
		}
		if(!StringUtils.isNull(fdyyj)){
			sb.append( " and fdyyj=?");
			value.add(fdyyj);
		}
		if(!StringUtils.isNull(gwxz)){
			sb.append( " and gwxz=?");
			value.add(gwxz);
		}		
		return sb;
	}
	
	/**
	 * 按岗位设置岗位可申请时间情况先的可申请岗位列表
	 * @return List
	 * */
	public List<HashMap<String, String>> getKsqgw(){
		String sql = "select * from (select a.gwdm,a.gwdm||'-'||a.gwsbsj gwdmgwsbsj,gwsbsj  from view_gwxx a where a.shjg='通过' and a.gzjsrq>to_char(sysdate,'yyyymmdd')) a where exists(select b.gwmc from view_sqgwsj b where a.gwdm=b.gwmc and a.gwsbsj=b.gwsbsj and b.kssj<to_char(sysdate,'yyyymmddhh24miss') and b.jssj>=to_char(sysdate,'yyyymmddhh24miss'))";
		String[] output = {"gwdm", "gwdmgwsbsj", "gwsbsj"};
		return getList(sql, new String[]{}, output);
	}
	
	/**
	 * 获取勤工助学参数设置信息
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getQgzxConfig(){
		String sql = "select xn,nd,xq,(select distinct xqmc from xqdzb b where b.xqdm=a.xq)xqmc,kssj,jssj,knsbl,mxsbc,mtzgxs,myzgxs,myzgbc,xwkssj,xwjssj from gwsqsjb a";
		String[] output = {"xn","nd","xq","xqmc","kssj","jssj","knsbl","mxsbc","mtzgxs","myzgxs","myzgbc","xwkssj","xwjssj"};
		return getMap(sql, new String[]{}, output);
	}
	
	/**
	 * 根据主键获取岗位信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getGwxxByPk(String pkValue){
		String sql = "select gwdm, gwsbsj,(select distinct yrdwmc from yrdwdmb b where a.sqdw=b.yrdwdm) yrdwmc from gwxxb a where gwdm||'-'||gwsbsj=?";
		String[] output = {"gwdm", "gwsbsj","yrdwmc"};
		return getMap(sql, new String[]{pkValue}, output);
	}
	
	/**
	 * 获取学生申请岗位的详细信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuGwxx(QgzxForm model){
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		
		String sql = StringUtils.joinStr("select gwdm||'-'||gwsbsj xmdm, xh, xm,",
										"xb,xymc,zymc,bjmc,nj,nd,xn,xq,gwdm,",
										"gwsbsj,jsjsp,lxdh,sffcfp,yhtc," ,
										"(select xqmc from xqdzb b where a.xq=b.xqdm)xqmc,",
										"mqqgzxqk, kcjqgzxsj,sfgr,sfdbh,sfdq,bz,",
										"sfyfdx,yjshf,xssq from view_xsgwxx a where ",
										pk ,
										"=?");
		String[] output = {"xmdm", "xh", "xm", "xb", "xymc", "zymc", "bjmc", "nj", 
				           "nd", "xn", "xq", "gwdm", "gwsbsj", "jsjsp", "lxdh", 
				           "sffcfp", "yhtc", "xqmc", "mqqgzxqk","kcjqgzxsj", 
				           "sfgr","sfdbh","sfdq","sfyfdx","yjshf", "xssq", "bz"};
		return getMap(sql, new String[]{pkValue}, output);
	}
	
	/**
	 * 获取学生空闲时间标志
	 * @param xh
	 * @return List
	 * @throws Exception 
	 * */
	public List<HashMap<String, String>> getFreeTimeList(String xh) throws Exception{
		String[] sj = {"第1节", "第2节", "第3节", "第4节", "第5节", "第6节", "第7节", "第8节" };
		String[] xq = { "mon", "tue", "wed", "thu", "fri", "sat", "sun" };
		List<HashMap<String,String>> kxList = new ArrayList<HashMap<String, String>>();
		String sql = "select kxbz from xsqgzxsjb where xh = ?  order by xq,sj ";
		String[] kxbz = getRs(sql, new String[] { xh }, "kxbz");
		if(kxbz != null && kxbz.length>55){
			if (kxbz != null && kxbz.length != 0) {
				String[] kx = new String[7];
				int j = 0;
				for (int i = 0; i < 8; i++) {
					for (int x = 0; x < 7; x++) {
						kx[x] = kxbz[x + j];
					}
					j += 7;
					HashMap<String, String> map2 = new HashMap<String, String>();
					for (int p = 0; p < 7; p++) {
						map2.put(xq[p], String.valueOf(kx[p]));
					}
					map2.put("sj", sj[i]);
					map2.put("sjIndex", String.valueOf(i));
					kxList.add(map2);
				}
			}
		}else{
			for (int i = 0; i < sj.length; i++) {
				HashMap<String, String> map2 = new HashMap<String, String>();
				map2.put("sj", sj[i]);
				map2.put("sjIndex", String.valueOf(i));
				kxList.add(map2);
			}
		}
			
		return kxList;
	}
	
	/**
	 * 查询学生成绩信息
	 * @param String xh
	 * @return List<String[]>
	 * */
	public List<String[]> selectXscj(String xh){
		String sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc from cjb a where xh = ? order by xn,nd,xq";
		String[] outputValue = {"xn","nd","xqmc","kcmc","cj","bkcj","cxcj","zpcj1"};
		return rsToVator(sql, new String[]{xh}, outputValue);
	}
	
	/**
	 * 查询学生违纪信息
	 * @param String xh
	 * @return List<String[]>
	 * */
	public List<String[]> selectWjcf(String xh){
		String sql = "select a.* from view_wjcf a where xh = ? and cfwh is not null order by xn,nd,xq";
		String[] outputValue = {"xn","nd","xqmc","cfwh","wjsj","cfyymc","cflbmc","cfjb","cxclwh"};
		return rsToVator(sql, new String[]{xh}, outputValue);
	}
	
	/**
	 * 查询广州大学岗位详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectGzdxXsgwxxb(String pk, String pkValue){
		String[] outputValue = {"xh","xm","xb","nj","xn","nd","xq","xqmc","xymc","zymc","bjmc","gwdm","gwsbsj","sqsj","sfyx","yhtc","xyyj","xxyj","lxdh","kcsgz","jjqk","bz"};
		String sql = "select xh,xm,xb,nj,xn,nd,xq,xqmc,xymc,zymc,bjmc,gwdm,gwsbsj,sqsj,sfyx,yhtc,xyyj,xxyj," +
				"(select lxdh from qgzxsqb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and a.gwxz=b.gwxzdm)lxdh," +
				"(select kcsgz from qgzxsqb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and a.gwxz=b.gwxzdm)kcsgz," +
				"(select jjqk from qgzxsqb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and a.gwxz=b.gwxzdm)jjqk," +
				"(select bz from qgzxsqb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and a.xq=b.xq and a.gwxz=b.gwxzdm)bz " +
				"from view_xsgwxx a where " + pk +" = ?";
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 查询地质大学岗位详细信息
	 * @param String pk
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectZgdzdxXsgwxxb(String pk, String pkValue){
		String[] outputValue = {"xh","xm","xb","nj","xn","nd","xq","xqmc","xymc","zymc","bjmc","gwdm","gwsbsj","sqsj","xssq","yhtc","xyyj","xxyj","lxdh","zzmm","bz"};
		String sql = "select xh,xm,xb,nj,xn,nd,xq,xqmc,xymc,zymc,bjmc,gwdm,gwsbsj,sqsj,xssq,yhtc,xyyj,xxyj," +
				"lxdh,zzmm,bz from view_xsgwxx a where " + pk +" = ?";
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	public String[] getXsgwxxbColList(){
		String xxdm = StandardOperation.getXxdm();
		String[] colList = {"主键","nd", "xn", "xh", "xm", "xzb", "gwdm", "sqsj","fdyyj","xyyj","xxyj","xscyj","kh" };
		if(Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)){
			//常州信息职业技术学院
			colList = new String[]{"主键","nd", "xn", "xh", "xm", "bjmc", "gwdm", "bdsj", "lzsj","fjwjmc"};
		}
		return colList;
	}
	
	/**
	 * 学生申请岗位信息查询
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXsgwxxb(CommanForm model){
		String sql = "select a.xh||a.gwdm||a.sqsj 主键, a.* from view_xsgwxx a ";
		String[] colList = getXsgwxxbColList();
		String query = getWhereSql(model).toString();
		sql = sql + query;
		
		model.getPages().setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from (" + sql + ")", value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (select rownum r,a.* from (" + sql+")a) where r>" + model.getPages().getStart()+ " and r<=" + model.getPages().getPageSize();
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 在岗学生岗位信息导出查询
	 * @param CommanForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectZgxsxxForExport(CommanForm model,String[] colList){
		String sql = "select a.xh||a.gwdm||a.sqsj 主键, a.* from view_xsgwxx a ";
		String query = getWhereSql(model).toString();
		
		return rsToVator(sql + query, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--用户为第一级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForFirst(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		//查询总记录数
		String sql=" select count(1) num "
				   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz from xsgwxxb a left join "
				   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm  and a.gwsbsj=c.gwsbsj) a "+whereSql;

		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.fdyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from ",
			    tableName,
			    " a  order by fdyyj desc) a ",
				whereSql,
				")a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--用户为第一级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForTowStepFirst(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		//查询总记录数
		String sql=" select count(1) num "
				   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz from xsgwxxb a left join "
				   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) a "+whereSql;

		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));

		
		//查询分页信息
		sql = StringUtils.joinStr(" select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.fdyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from  ",
			    tableName,
			    " a   order by xyyj desc)a ",
				whereSql,
				")a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
	
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--用户为第二级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForSecond(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		whereSql += "  and fdyyj='通过' ";
		
		//查询总记录数
		String sql=" select count(1) num "
				   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz,fdyyj from xsgwxxb a left join "
				   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) a "+whereSql;
		
		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.xyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor, ",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from ",
			    tableName,
			    " a  order by xyyj desc) a ",
				whereSql,
				") a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--学校为第二级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForXxSecond(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		whereSql += "  and fdyyj='通过' ";
		
		//查询总记录数
		String sql = "select count(*) num from (select a.* from " + tableName + " a, view_gwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj) " + whereSql;
		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from ",
			    tableName,
			    " a,view_gwxx b",				
				" where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj order by xxyj desc) a ",
				whereSql,
				")a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--学校为第二级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForTowStepXxSecond(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		if(!Globals.XXDM_ZJXY.equalsIgnoreCase(Base.xxdm)){
			whereSql += "  and xyyj='通过' ";
		}
		
		//查询总记录数
		String sql=" select count(1) num "
			   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz,xyyj from xsgwxxb a left join "
			   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) a "+whereSql;
		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from ",
			    tableName,
			    " a  order by xxyj desc) a ",
				whereSql,
				") a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--学校为第三级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForXxThird(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		whereSql += "  and a.xyyj='通过' ";
		
//		查询总记录数
		String sql=" select count(1) num "
				   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz,xyyj from xsgwxxb a left join "
				   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) a "+whereSql;
		
		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from ",
			    tableName,
			    " a  order by xxyj desc) a ",
				whereSql,
				")a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--勤工助学管理员审核
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForQgglyThird(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		
		//查询总记录数
		String sql=" select count(1) num "
				   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz from xsgwxxb a left join "
				   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) a "+whereSql;
		
		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from ",
			    tableName,
			    " a  order by xxyj desc) a ",
				whereSql,
				")a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--中国地质大学
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForZgdzdx(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		//是否是用人单位用户
		//用户类型
		String userType = user.getUserType();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		String sql = "";
		if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){			
			sql = "select rownum 行号,rownum r,(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"			
				+ " a.* from(select "
				+ model.getPk()
				+ " 主键,a.* from "
				+ tableName
				+ " a,view_gwxx b,qgzxsqb c "				
				+" where a.xh=c.xh "
				+"and c.xxsh='通过' "
				+"and a.xn=c.xn "
				+"and a.nd=c.nd and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj "
				+ " ) a " 
				+ whereSql;			
		}else if("xy".equalsIgnoreCase(userType)){				
			sql = "select rownum 行号,rownum r,(case nvl(a.xyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"			
				+ " a.* from(select "
				+ model.getPk()
				+ " 主键,a.* from "
				+ tableName
				+ " a,view_gwxx b,qgzxsqb c "				
				+" where a.xh=c.xh "
				+"and c.xxsh='通过' "
				+"and a.xn=c.xn "
				+"and a.nd=c.nd and a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj "
				+ ") a "
				+ whereSql;
		}
		
		//查询总记录数
		String coutSql = "select count(*) num from (" + sql +")";
		String countNum = getOneRs(coutSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (",
				sql ,
				") a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--湖南工业大学
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForHngydx(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		//是否是用人单位用户
		//用户类型
		String userType = user.getUserType();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		String sql = "";
		if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){			
			sql = "select rownum 行号,(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
				+ " a.* from(select "
				+ model.getPk()
				+ " 主键,a.* from "
				+ tableName
				+ " a,view_gwxx b"
				+ whereSql + " order by xxyj desc) a where fdyyj='通过' and xyyj<>'通过'";
		}else if("xy".equalsIgnoreCase(userType)){	
			//TODO fdyyj是哪一级啊？？？？？？？ 此处去掉了用人单位用户
			sql = "select rownum 行号,(case nvl(a.fdyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
				+ " a.* from(select "
				+ model.getPk()
				+ " 主键,a.* from "
				+ tableName
				+ " a,view_gwxx b"
				+ whereSql + " and xxyj<>'通过' and xyyj<>'通过' order by fdyyj desc) a";
		}
		//查询总记录数
		String coutSql = "select count(*) num from (" + sql +")";
		String countNum = getOneRs(coutSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (",
				sql ,
				") a) where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--云南艺术学院
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForYnysxy(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		//是否是用人单位用户
		//用户类型
		String userType = user.getUserType();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		String sql = "";
		if("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType)){			
			//非学院
			sql = "select rownum 行号,(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
				+ " a.* from(select "
				+ model.getPk()
				+ " 主键,a.* from "
				+ tableName
				+ " a,view_gwxx b"
				+ whereSql + " order by xxyj desc) a where xyyj='通过'";
		}else if("xy".equalsIgnoreCase(userType)){		
			//学院
			sql = "select rownum 行号,(case nvl(a.xyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
				+ " a.* from(select "
				+ model.getPk()
				+ " 主键,a.* from "
				+ tableName
				+ " a,view_gwxx b"
				+ whereSql + " order by xyyj desc) a";
		}
		
		//查询总记录数
		String coutSql = "select count(*) num from (" + sql +")";
		String countNum = getOneRs(coutSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (",
				sql ,
				") a) where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--浙江机电职业技术学院
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForZjjdzyjsxy(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		//是否是用人单位用户
		//用户类型
		String userType = user.getUserType();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		String sql = "";
		sql = "select rownum 行号,(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
			+ " a.* from(select "
			+ model.getPk()
			+ " 主键,a.* from "
			+ tableName
			+ " a,view_gwxx b"
			+ whereSql + ") a";
		
		
		//查询总记录数
		String coutSql = "select count(*) num from (" + sql +")";
		String countNum = getOneRs(coutSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (",
				sql ,
				") a) where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--北京联合大学
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForBjlhdx(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		//是否是用人单位用户
		//用户类型
		String userType = user.getUserType();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		String sql = "";
		sql = "select rownum 行号,(case nvl(a.xyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
			+ " a.* from(select "
			+ model.getPk()
			+ " 主键,a.* from "
			+ tableName
			+ " a,view_gwxx b"
			+ whereSql
			+ ") a";
		
		//查询总记录数
		String coutSql = "select count(*) num from (" + sql +")";
		String countNum = getOneRs(coutSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (",
				sql ,
				") a) where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--淮阴工学院
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForHygxy(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		//是否是用人单位用户
		//用户类型
		String userType = user.getUserType();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		String sql = "";
		sql = "select rownum 行号,(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
			+ " a.* from(select "
			+ model.getPk()
			+ " 主键,a.* from "
			+ tableName
			+ " a,view_gwxx b"
			+ whereSql
			+ ") a";
		
		//查询总记录数
		String coutSql = "select count(*) num from (" + sql +")";
		String countNum = getOneRs(coutSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (",
				sql ,
				") a) where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--浙江科技
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForZjkjxy(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		QgzxDao qgzxDao = new QgzxDao();
		boolean isYrdw = qgzxDao.isYrdwUser(user.getUserName());
		//是否是用人单位用户
		//用户类型
		String userType = user.getUserType();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		String sql = "";
		if(isYrdw){
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd","xqmc",
					 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj", "已录用岗位" };
			//用人单位审核
			sql = StringUtils.joinStr("select rownum 行号,rownum r,",
					"(case nvl(a.xyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
					" a.* from(select ",
					model.getPk(),
				    " 主键,a.*,",
				     "(select distinct gwdm from xsgwxxb b where a.xh=b.xh and a.xn=b.xn and a.nd=b.nd and b.xyyj='通过' and rownum=1)已录用岗位 ",
				    "from ",
				    tableName,
				    " a,view_gwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj",
				    ") a ",whereSql," and exists (select 1 from yrdwdmb b where a.yrdwdm=b.yrdwdm and b.dlm='",user.getUserName(),"')");

		}else{
			//学校审核
			sql = "select rownum 行号,rownum r,(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
				+ " a.* from(select "
				+ model.getPk()
				+ " 主键,a.* from "
				+ tableName
				+ " a,view_gwxx b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj)a "
				+ whereSql;
			//2010.11.26 by lr  学校审核是看看到用人单位未通过的记录
//				+ " and xyyj='通过'";
		}
		
		//查询总记录数
		String coutSql = "select count(*) num from (" + sql +")";
		String countNum = getOneRs(coutSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (",
				sql ,
				") a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--成都体育学院
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForCdtyxy(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		QgzxDao qgzxDao = new QgzxDao();
		boolean isYrdw = qgzxDao.isYrdwUser(user.getUserName());
		//是否是用人单位用户
		
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		
		String sql = "";
		if(isYrdw){
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd","xqmc",
					 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj", "xyyj", "xxyj" };
			//用人单位审核
			sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
					"(case nvl(a.fdyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
					" a.* from(select ",
				    model.getPk(),
				    " 主键,a.* from ",
				    tableName,
				    " a,gwxxb b",
					" where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and b.xyyrdwsh='是' and a.xxyj='未审核' order by xyyj desc)a ",
					whereSql,
					")a where r>",
					model.getPages().getStart()+"",
					" and r<=",
					(model.getPages().getStart()+model.getPages().getPageSize())+"");

		}else{
			colList = new String[] { "bgcolor", "主键", "行号", "xn", "nd","xqmc",
					 "xh", "xm", "bjmc", "sfpks", "gwdm", "sqsj",  "xxyj" };
			//学校审核
			sql = "select rownum 行号,rownum r,(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,"
				+ " a.* from(select "
				+ model.getPk()
				+ " 主键, a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zydm, " 
				+ " a.zymc,a.bjdm,a.bjmc,a.xzb,a.zzmmm,a.zzmm,a.zwxzdm, " 
				+ " a.zwxzmc,a.xn,a.nd,a.xq, a.xqmc,a.yrdwdm, a.gwdm,a.sqsj, " 
				+ " a.xssq,a.fdyyj,case when xyyrdwsh='否' then '无需审核' else  a.xyyj end xyyj, "
				+ " a.xxyj,a.gwsbsj,a.sfpks from "
				+ tableName
				+ " a,gwxxb b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj" 
				+ " and (b.xyyrdwsh='是' and a.xyyj='通过' or b.xyyrdwsh='否') )a "
				+ whereSql;
			//2010.11.26 by lr  学校审核是看看到用人单位未通过的记录
//				+ " and xyyj='通过'";
		}
		
		//查询总记录数
		String coutSql = "select count(*) num from (" + sql +")";
		String countNum = getOneRs(coutSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (",
				sql ,
				") a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	
	/**
	 * 学生岗位审核查询--浙江交通职业技术学院--户为第一级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForFirstZjjtzyjsxy(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		
		//查询总记录数
		String sql=" select count(1) num "
				   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz from xsgwxxb a left join "
				   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) a "+whereSql;

		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));

		
		//查询分页信息
		sql = StringUtils.joinStr(" select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.fdyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
				"(case when a.xyyj<>'未审核' or a.xxyj<>'未审核' then 'disabled' else '' end) 标记, ",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from  ",
			    tableName,
			    " a   order by xyyj desc)a ",
				whereSql,
				")a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	/**
	 * 学生岗位审核查询--浙江交通职业技术学院--用户为第二级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForSecondZjjtzyjsxy(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		whereSql += "  and fdyyj='通过' ";
		
		//查询总记录数
		String sql=" select count(1) num "
				   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz,fdyyj from xsgwxxb a left join "
				   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) a "+whereSql;
		
		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.xyyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor, ",
				"(case when a.fdyyj<>'通过' or a.xxyj<>'未审核' then 'disabled' else '' end) 标记, ",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from ",
			    tableName,
			    " a  order by xyyj desc) a ",
				whereSql,
				") a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
	/**
	 * 学生岗位审核查询--浙江交通职业技术学院--学校为第三级别
	 * @param model
	 * @param colList
	 * @return List<String[]>
	 * */
	public List<String[]> queryXsgwshForXxThirdZjjtzyjsxy(CommanForm model, User user, String[] colList){
		String tableName = model.getTableName();
		value = new ArrayList<String>();
		//查询条件
		String whereSql = getWhereSql(model).toString();
		if("true".equalsIgnoreCase(user.getFdyQx())){
			whereSql += " and exists (select * from view_xsjbxx c where a.xh=c.xh and exists (select * from fdybjb d where d.zgh='" + user.getUserName() + "' and c.bjdm=d.bjdm ))";
		}
		whereSql += "  and a.xyyj='通过' ";
		
//		查询总记录数
		String sql=" select count(1) num "
				   +" from (select a.xh,xydm,zydm,bjdm,nj,xn,nd,sqdw yrdwdm,gwxz,xyyj from xsgwxxb a left join "
				   +" view_xsjbxx b on a.xh=b.xh left join gwxxb c on a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) a "+whereSql;
		
		String countNum = getOneRs(sql, value != null ? value.toArray(new String[0]) : new String[]{}, "num");
		model.getPages().setMaxRecord(Integer.parseInt(StringUtils.isNull(countNum) ? "0" : countNum));
		
		//查询分页信息
		sql = StringUtils.joinStr("select a.* from (select rownum 行号,rownum r,",
				"(case nvl(a.xxyj,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) bgcolor,",
				"(case when a.xyyj<>'通过' or a.fdyyj<>'通过' then 'disabled' else '' end) 标记, ",
				" a.* from(select ",
			    model.getPk(),
			    " 主键,a.* from ",
			    tableName,
			    " a  order by xxyj desc) a ",
				whereSql,
				")a where r>",
				model.getPages().getStart()+"",
				" and r<=",
				(model.getPages().getStart()+model.getPages().getPageSize())+"");
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{},colList);
	}
}
