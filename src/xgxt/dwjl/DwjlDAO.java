package xgxt.dwjl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 对外交流模块DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2009-01-13</p>
 */
public class DwjlDAO extends DAO {
	ArrayList<String> value = new ArrayList<String>();
	/**
	 * 组合查询条件
	 * @param model
	 * @return StringBuffer
	 * */
	public StringBuffer getWhereSql(DwjlForm model){
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String xm = model.getXm();
		String sqrq = model.getSqrq();
		
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
			sb.append( " and xh=?");
			value.add(xh);
		}
		if(xm !=null && !xm.equalsIgnoreCase("")){
			sb.append( " and xm=?");
			value.add(xm);
		}
		if(sqrq !=null && !sqrq.equalsIgnoreCase("")){
			sb.append( " and sqrq=?");
			value.add(sqrq);
		}
		return sb;
	}
	
	/**
	 * 检测记录是否存在
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return boolean
	 * */
	public boolean checkExists(String tableName, String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String num = getOneRs(sql, new String[]{pkValue}, "num");
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;
		
		return Integer.parseInt(num) >0 ? true : false;
	}
	
	/**
	 * 查询学生对外交流奖学金历史信息
	 * @param xh
	 * @return List
	 * */
	public List getDwjljxjHisinfo(String xh){
//		String[] columns = {"nd", "xn", "xq", "dwjlxmmc", "dwjlfsmc","dwjllbmc","zzpzje"};
//		String sql = "select nd,xn,xq,dwjlxmmc,dwjlfsmc,dwjllbmc,zzpzje from view_dwjljxjsq where xh=? and xxsh='通过'";
		//李涛改于09-01-16   关于最终批准金额字段并没有在程序上面体现,这里应该是申请金额吧.
		String[] columns = {"nd", "xn", "xq", "dwjlxmmc", "dwjlfsmc","dwjllbmc","sqje"};
		String sql = "select nd,xn,xq,dwjlxmmc,dwjlfsmc,dwjllbmc,nvl(sqje,0) sqje from view_dwjljxjsq where xh=? and xxsh='通过'";
		return getList(sql, new String[]{xh}, columns);
	}
	
	/**
	 * 获取学生的基本信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuInfo(String xh){
		String[] columns = {"xh", "xm", "xb", "nj", "xymc", "zymc", "bjmc", "lxdh", "sjhm", "dzyx", "zzmmmc", "mzmc", "csrq", "jtdz", "jtdh","jg", "ssbh"};
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.zymc,a.bjmc,a.lxdh,a.sjhm,a.dzyx,a.zzmmmc,a.mzmc,a.csrq,a.jg,a.ssbh," +
				     "(select jtszd from view_xsjtxx b where a.xh=b.xh)jtdz,(select jtdh from view_xsjtxx b where a.xh=b.xh)jtdh from view_xsxxb a where xh=?";
		
		return getMap(sql, new String[]{xh}, columns);		
	}
	
	/**
	 * 查询对外交流项目信息
	 * @param dwjlxmdm
	 * @return HashMap<String, String> 
	 * */
	public HashMap<String, String> getDwjlxmInfo(String dwjlxmdm){
		HashMap<String,String> map = new HashMap<String, String>();
		String[] columns = { "dwjlxmmc","dwjllbmc", "dwjlfsmc", "nd", "xn", "xq", "pcsj", "jlqx", "jxjxe", "jlxxxx", "jlstj", "nwgjhdq", "pcsj" };
		String sql = "select dwjlxmmc,dwjllbmc,dwjlfsmc,nd,xn,xq,strtodatetime(pcsj) pcsj,strtodatetime(jlqx) jlqx,jlstj,jlxxxx,jxjxe,nwgjhdq from view_dwjlxx where xn||nd||xq||jlxmdm=?"; 
		map = getMap(sql, new String[] {dwjlxmdm},columns);
		return map;
	}
	
	/**
	 * 获取学生申请出国留学信息
	 * @param model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuCglxsq(DwjlForm model){
		String xh = model.getXh();
		String sqsj = model.getSqrq();
		String[] output = {"xh", "xm", "xb", "xymc", "zymc", "bjmc", "nj", "jg", "csrq", "ssbh", "xx", "xl", "cet", "tem", "toeft", "jzxm", "jzgzdw", "jtdz", "gj", "qqh", "dzyx", "lxdh", "sfzzlxxx", "jdxx", "xysh", "xxsh", "sqrq"};
		
		String sql = "select xh,xm,xb,xymc,zymc,bjmc,nj,jg,csrq,ssbh,xx,xl,cet,tem,toeft,jzxm,jzgzdw,jtdz,gj,qqh,dzyx,lxdh,sfzzlxxx,jdxx,xysh,xxsh,sqrq from view_cgsqxx where xh=? ";
		if(sqsj != null && !"".equalsIgnoreCase(sqsj)){
			sql += "and sqrq='" + sqsj + "'";
		}
		 
		sql += " order by sqrq desc";
		
		return getMap(sql, new String[]{xh}, output);
	}
	
	/**
	 * 出国留学申请信息查询
	 * @param model
	 * @return List
	 * */
	public List selectCglxsq(DwjlForm model){
		String[] outputValue = {"主键","xh","xm", "xb","xymc","bjmc","gj","xx","sqrq","xysh","xxsh"};
		String sql = "select xh||sqrq 主键 ,xh,xm,xb,xymc,bjmc,gj,xx,sqrq,xysh,xxsh from view_cgsqxx ";
		String whereSql = getWhereSql(model).toString();
		return rsToVator(sql + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 出国留学申请审核
	 * @param model
	 * @return List
	 * */
	public List selectCglxsqsh(DwjlForm model){
		String userType = model.getUserType();		
		String[] outputValue = {"color","主键","xh","xm", "xb","xymc","bjmc","gj","xx","sqrq","xysh","xxsh"};
		String sql = "select (case nvl(xysh,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) color,xh||sqrq 主键,xh,xm,xb,xymc,bjmc,gj,xx,sqrq,xysh,xxsh from view_cgsqxx ";		
		String whereSql = getWhereSql(model).toString();
		
		if(userType != null && ("xx".equalsIgnoreCase(userType) || "admin".equalsIgnoreCase(userType))){
			sql = "select (case nvl(xxsh,'未审核') when '通过' then '#99CCFF' when '未审核' then '#FFFFFF' else '#FF9999' end) color,xh||sqrq 主键,xh,xm,xb,xymc,bjmc,gj,xx,sqrq,xysh,xxsh from view_cgsqxx ";
			whereSql += " and xysh='通过'";
		}
		
		return rsToVator(sql + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 获取对外交流项目列表
	 * @return List
	 * */
	public List getDwjlxmList(){
		String sql = "select distinct dwjlxmdm, dwjlxmmc from dwjlxmdmb";		
		return getList(sql, new String[]{}, new String[]{"dwjlxmdm","dwjlxmmc"});
	}
	
	/**
	 * 获取对外交流发布的交流项目信息
	 * @param xn
	 * @param nd
	 * @param xq
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> queryDwjlxmList(String xn, String nd, String xq){		
		StringBuffer sql = new StringBuffer("select distinct jlxmdm, dwjlxmmc jlxmmc from view_dwjlxx where 1=1 ");
		if(StringUtils.isNotNull(xn)){
			sql.append(" and xn='");
			sql.append(xn);
			sql.append("'");
		}
		if(StringUtils.isNotNull(nd)){
			sql.append(" and nd='");
			sql.append(nd);
			sql.append("'");
		}
		if(StringUtils.isNotNull(xq)){
			sql.append(" and xq='");
			sql.append(xq);
			sql.append("'");
		}
		return getList(sql.toString(), new String[]{}, new String[]{"jlxmdm","jlxmmc"});
	}
	
	/**
	 * 获取学生申请的单位记录项目
	 * @param xh
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXsdwjlxmList(String xh){
		String sql = "select (xn||nd||xq||jlxmdm) jlxmdm,dwjlxmmc from view_dwjlxx a where exists(select 1 from dwjlsqb b where b.xn||b.nd||b.xq||b.jlxmdm=a.xn||a.nd||a.xq||a.jlxmdm and b.xh=? and b.XXZS = '通过')";
		return getList(sql, new String[]{xh}, new String[]{"jlxmdm", "dwjlxmmc"});
	}
	
	/**
	 * 查询对外交流奖学金申请信息
	 * @param xh
	 * @param xmdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String,String> selectDwjljxjsq(String xh, String xmdm){
		String sql = "select sqje,yhtc tc,sqsy sqly,bz,hjqk from view_dwjljxjsq where xn||nd||xq||jlxmdm||xh=?";
		String[] outputValue = {"sqje","tc","sqly","bz","hjqk"};
		return getMap(sql, new String[]{xmdm+xh}, outputValue);
	}
	
	/**
	 * 查询对外交流信息
	 * @param xmdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectDwjlxx(String xmdm){
		String sql = "select dwjllbmc jllb,dwjlfsmc jlfs,nd,xn,xq," +
				     "strtodatetime(pcsj) pcsj,strtodatetime(jlqx) jlqx,jlstj," +
				     "jlxxxx,jxjxe from view_dwjlxx where xn||nd||xq||jlxmdm=?";
		String[] outputValue = {"jllb", "jlfs", "nd", "xn", "xq", "pcsj", "jlqx", 
				                "jxjxe", "jlxxxx", "jlstj"};
		return getMap(sql, new String[]{xmdm}, outputValue);
	}
}
