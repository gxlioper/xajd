package xgxt.rcgl.nbcs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;

public class XsfwzdzxglDAO extends DAO{
	ArrayList<String> value = new ArrayList<String>();
	
	/**
	 * 获取查询条件
	 * */
	public StringBuffer getWhereSql(XsfwzdzxglForm model){
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		String nj = model.getNj();
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xh = model.getXh();
		String xm = model.getXm();
		String slryhm = model.getSlryhm();
		String slrxm = model.getSlrxm();
		String slbmdm = model.getSlbmdm();
		String nd = model.getNd();
		String yf = model.getYf();
		String bjsj = model.getBjsj();
		String fwzl = model.getFwzl();
		
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
		if(!StringUtils.isNull(slryhm)){
			sb.append( " and slryhm like '%'||?||'%'");
			value.add(slryhm);
		}
		if(!StringUtils.isNull(slrxm)){
			sb.append( " and slrxm like '%'||?||'%'");
			value.add(slrxm);
		}
		if(!StringUtils.isNull(slbmdm)){
			sb.append( " and slbmdm=?");
			value.add(slbmdm);
		}
		if(!StringUtils.isNull(nd)){
			sb.append( " and nd=?");
			value.add(nd);
		}
		if(!StringUtils.isNull(yf)){
			sb.append( " and yf=?");
			value.add(yf);
		}
		if(!StringUtils.isNull(bjsj)){
			sb.append( " and bjsj=?");
			value.add(bjsj);
		}
		if(!StringUtils.isNull(fwzl)){
			sb.append( " and fwzl=?");
			value.add(fwzl);
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
		String sql = "select count(*)num from " + tableName + " where " + pk + " =?";
		String result = getOneRs(sql, new String[]{pkValue},"num");
		return Integer.parseInt(StringUtils.isNull(result) ? "0" : result) >0 ? true : false;
	}
	
	/**
	 * 查询学生来访受理部门信息
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXslfslbmdmb(){
		String sql = "select distinct slbmdm,slbmmc from xslfslbmdmb order by slbmdm";
		String[] col = {"slbmdm","slbmmc"};
		return getList(sql, new String[]{},col);
	}
	
	/**
	 * 查询学生来访部门受理人信息
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXslfbmslrb(XsfwzdzxglForm model){
		String[] outputValue = {"pk","slbmmc","slryhm","slrxm","slrlxdh"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.* from (select slbmdm||slryhm pk,slbmdm,slbmmc,slryhm,slrxm,slrlxdh from view_xslfbmslrb a" + whereSql + ")a";
		Pages paganitionModel = model.getPages();
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_xslfbmslrb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + model.getPages().getStart() + " and r<=" + (model.getPages().getStart()+model.getPages().getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询学生来访部门受理人信息导出
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXslfbmslrbForExp(XsfwzdzxglForm model){
		String[] outputValue = {"slbmdm","slbmmc","slryhm","slrxm","slrlxdh"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select rownum r,slbmdm||slryhm pk,slbmdm,slbmmc,slryhm,slrxm,slrlxdh from view_xslfbmslrb";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	
	/**
	 * 根据主键查询学生来访部门受理人信息
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXslfbmslrbOne(XsfwzdzxglForm model){
		String[] outputValue = {"pk","slbmdm","slbmmc","slryhm","slrxm","slrlxdh"};
		String sql = "select slbmdm||slryhm pk,slbmdm,slbmmc,slryhm,slrxm,slrlxdh from view_xslfbmslrb where slbmdm||slryhm=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * 检测学生来访部门受理人信息是否已经存在
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkXslfbmlxr(HashMap<String, String> map){		
		return checkExists("xslfbmslrb", "slbmdm||slryhm", map.get("slbmdm")+map.get("slryhm"));
	}
	
	/***
	 * 根据用户名查询用户姓名
	 * @param String yhm
	 * @return String
	 * */
	public String checkYhm(String yhm){
		String sql = "select xm from view_yhxx where yhm=?";
		String xm = getOneRs(sql, new String[]{yhm}, "xm");
		if(StringUtils.isNull(xm)){
			sql = "select xm from view_xsjbxx where xh=?";
			xm = getOneRs(sql, new String[]{yhm}, "xm");
		}
		return xm;
	}
	
	/**
	 * 查询学生来访信息
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXslfglb(XsfwzdzxglForm model){
		String[] outputValue = {"pk","xh","nd","yf","slrxm","slsj","lfsy","hfr","hfsj","bjsj","fwzl"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.* from (select xh||lfrq pk,xh,nd,yf,slr,slrxm,slsj,lfsy,hfr,hfsj,bjsj,fwzl from view_xslfglb a" + whereSql + ")a";
		
		Pages paganitionModel = model.getPages();		
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_xslfglb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + paganitionModel.getStart() + " and r<=" + (paganitionModel.getStart()+paganitionModel.getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询学生来访信息导出
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXslfglbForExp(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","xh","xm","xb","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","lffs","lfrq","lfsy","slbmdm","slbmmc","slr","slrxm","slsj","hfr","hfsj","bjsj","fwzl","lxdh","bz"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select nd,yf,xh,xm,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,lffs,lfrq,lfsy,slbmdm,slbmmc,slr,slrxm,slsj,hfr,hfsj,bjsj,fwzl,lxdh,bz from view_xslfglb";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询学生来访未办理或吧满意信息
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXslfglbForWbl(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","xh","xm","xb","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","lffs","lfrq","lfsy","slbmdm","slbmmc","slr","slrxm","slsj","hfr","hfsj","bjsj","fwzl","lxdh","bz"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select nd,yf,xh,xm,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,lffs,lfrq,lfsy,slbmdm,slbmmc,slr,slrxm,slsj,hfr,hfsj,bjsj,fwzl,lxdh,bz from view_xslfglb";
		
		return getList(sql+whereSql+" and (bjsj='未办' or bjsj='不及时' or fwzl='不满意')", value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 根据主键查询学生来访信息
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXslfglbOne(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","xh","xm","xb","xz","xydm","xymc","zydm","zymc","bjdm","bjmc","nj","lffs","lfrq","lfsy","slbmdm","slbmmc","slr","slrxm","slsj","hfr","hfsj","bjsj","fwzl","lxdh","bz"};
		String sql = "select nd,yf,xh,xm,xb,xz,xydm,xymc,zydm,zymc,bjdm,bjmc,nj,lffs,lfrq,lfsy,slbmdm,slbmmc,slr,slrxm,slsj,hfr,hfsj,bjsj,fwzl,lxdh,bz from view_xslfglb where xh||lfrq=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * 查询回访信息打印统计表
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectHfxx(XsfwzdzxglForm model){
		String nd = model.getNd();
		String yf = model.getYf();
		String[] outputValue = {"slbmmc","slzs","hfzs","js","bjs","wbl","my","jbmy","bmy","ld","lr"};
		String sql = "select slbmdm, slbmmc,(select count(*) from xslfglb b where b.slbmdm = a.slbmdm and nd = ?　and yf = ?) slzs," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and hfr is not null) hfzs," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and bjsj = '及时') js," +
				"(select count(*)  from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and bjsj = '不及时') bjs," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and bjsj = '未办') wbl," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and fwzl = '满意') my," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and fwzl = '基本满意') jbmy," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and fwzl = '不满意') bmy," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and lffs = '来电') ld," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and yf = ?　and lffs = '来人') lr" +
				" from xslfslbmdmb a";
		
		return getList(sql, new String[]{nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf}, outputValue);
	}
	
	/**
	 * 查询年度回访信息打印统计表
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectNdHfxx(XsfwzdzxglForm model){
		String nd = model.getNd();
		String[] outputValue = {"slbmmc","slzs","hfzs","js","bjs","wbl","my","jbmy","bmy","ld","lr"};
		String sql = "select slbmdm, slbmmc,(select count(*) from xslfglb b where b.slbmdm = a.slbmdm and nd = ?) slzs," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and hfr is not null) hfzs," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and bjsj = '及时') js," +
				"(select count(*)  from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and bjsj = '不及时') bjs," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and bjsj = '未办') wbl," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and fwzl = '满意') my," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and fwzl = '基本满意') jbmy," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and fwzl = '不满意') bmy," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and lffs = '来电') ld," +
				"(select count(*)　from xslfglb b　where b.slbmdm = a.slbmdm　and nd = ?　and lffs = '来人') lr" +
				" from xslfslbmdmb a";
		
		return getList(sql, new String[]{nd,nd,nd,nd,nd,nd,nd,nd,nd,nd}, outputValue);
	}
	
	
	
	/**
	 * 查询回访合计信息
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public HashMap<String, String> selectHfhjxx(XsfwzdzxglForm model){
		String nd = model.getNd();
		String yf = model.getYf();
		String[] outputValue = {"slzs","hfzs","js","bjs","wbl","my","jbmy","bmy","ld","lr","ykfts"};
		String sql = "select (select count(*) from xslfglb b where nd = ?　and yf = ?) slzs," +
				"(select count(*)　from xslfglb b　where nd = ?　and yf = ?　and hfr is not null) hfzs," +
				"(select count(*)　from xslfglb b　where nd = ?　and yf = ?　and bjsj = '及时') js," +
				"(select count(*)  from xslfglb b　where nd = ?　and yf = ?　and bjsj = '不及时') bjs," +
				"(select count(*)　from xslfglb b　where nd = ?　and yf = ?　and bjsj = '未办') wbl," +
				"(select count(*)　from xslfglb b　where nd = ?　and yf = ?　and fwzl = '满意') my," +
				"(select count(*)　from xslfglb b　where nd = ?　and yf = ?　and fwzl = '基本满意') jbmy," +
				"(select count(*)　from xslfglb b　where nd = ?　and yf = ?　and fwzl = '不满意') bmy," +
				"(select count(*)　from xslfglb b　where nd = ?　and yf = ?　and lffs = '来电') ld," +
				"(select count(*)　from xslfglb b　where nd = ?　and yf = ?　and lffs = '来人') lr," +
				"(select max(ykfts) from xszdfwzxcqb where nd=? and yf=?)ykfts "+
				" from dual a";
		
		return getMap(sql, new String[]{nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf,nd,yf}, outputValue);
	}
	
	/**
	 * 查询年度回访合计信息
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public HashMap<String, String> selectNdHfhjxx(XsfwzdzxglForm model){
		String nd = model.getNd();
		String[] outputValue = {"slzs","hfzs","js","bjs","wbl","my","jbmy","bmy","ld","lr","ykfts"};
		String sql = "select (select count(*) from xslfglb b where nd = ?) slzs," +
				"(select count(*)　from xslfglb b　where nd = ?　and hfr is not null) hfzs," +
				"(select count(*)　from xslfglb b　where nd = ?　and bjsj = '及时') js," +
				"(select count(*)  from xslfglb b　where nd = ?　and bjsj = '不及时') bjs," +
				"(select count(*)　from xslfglb b　where nd = ?　and bjsj = '未办') wbl," +
				"(select count(*)　from xslfglb b　where nd = ?　and fwzl = '满意') my," +
				"(select count(*)　from xslfglb b　where nd = ?　and fwzl = '基本满意') jbmy," +
				"(select count(*)　from xslfglb b　where nd = ?　and fwzl = '不满意') bmy," +
				"(select count(*)　from xslfglb b　where nd = ?　and lffs = '来电') ld," +
				"(select count(*)　from xslfglb b　where nd = ?　and lffs = '来人') lr," +
				"(select max(ykfts) from xszdfwzxcqb where nd=? )ykfts "+
				" from dual a";
		
		return getMap(sql, new String[]{nd,nd,nd,nd,nd,nd,nd,nd,nd,nd,nd}, outputValue);
	}
	
	/**
	 * 获取部门受理人信息
	 * @param String slbmdm
	 * @return HashMap<String, String>
	 * */
	public List<HashMap<String, String>> getBmslr(String slbmdm){
		String sql = "select distinct slryhm,slrxm from xslfbmslrb where slbmdm=?";
		return getList(sql, new String[]{slbmdm}, new String[]{"slryhm","slrxm"});
	}
	
	/**
	 * 检测学生来访信息是否存在
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkXslfxx(HashMap<String, String> map){
		return checkExists("xslfglb", "xh||lfrq", map.get("xh")+map.get("lfrq"));
	}	
	
	/**
	 * 查询出勤信息
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXszdfwzxcqb(XsfwzdzxglForm model){
		String[] outputValue = {"pk","nd","yf","slbmmc","ykfts","jscqts","xscqts","bz"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select  rownum r, a.* from (select nd||yf||slbmdm pk,nd,yf,slbmdm,slbmmc,ykfts,jscqts,xscqts,bz from view_xszdfwzxcqb a" + whereSql + ")a";
		
		Pages paganitionModel = model.getPages();		
		paganitionModel.setMaxRecord(Integer.parseInt(getOneRs("select count(*) num from view_xszdfwzxcqb" + whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, "num")));
		sql = "select * from (" + sql + ") where r>" + paganitionModel.getStart() + " and r<=" + (paganitionModel.getStart()+paganitionModel.getPageSize()); 
		return rsToVator(sql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询出勤信息导出
	 * @param XsfwzdzxglForm model
	 * @return List<String[]>
	 * */
	public List<String[]> selectXszdfwzxcqbForExp(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","slbmdm","slbmmc","ykfts","jscqts","xscqts","bz"};
		String whereSql = getWhereSql(model).toString();
		String sql = "select nd,yf,slbmdm,slbmmc,ykfts,jscqts,xscqts,bz from view_xszdfwzxcqb";
		
		return rsToVator(sql+whereSql, value != null ? value.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询出勤信息导出
	 * @param XsfwzdzxglForm model
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> selectXszdfwzxcqbForPrint(XsfwzdzxglForm model){
		String[] outputValue = {"slbmmc","ykfts","jscqts","xscqts","bz"};
		String sql = "select a.slbmmc,b.ykfts,b.jscqts,b.xscqts,b.bz from xslfslbmdmb a left join (select * from xszdfwzxcqb where nd=? and yf=?) b on a.slbmdm=b.slbmdm order by a.slbmdm ";
		
		return getList(sql, new String[]{model.getNd(),model.getYf()}, outputValue);
	}
	
	/**
	 * 根据主键查询出勤信息
	 * @param XsfwzdzxglForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXszdfwzxcqbOne(XsfwzdzxglForm model){
		String[] outputValue = {"nd","yf","slbmdm","slbmmc","ykfts","jscqts","xscqts","bz"};
		String sql = "select nd,yf,slbmdm,slbmmc,ykfts,jscqts,xscqts,bz from view_xszdfwzxcqb where nd||yf||slbmdm=?";
		
		return getMap(sql, new String[]{model.getPkValue()}, outputValue);
	}
	
	/**
	 * 检测出勤信息是否存在
	 * @param HashMap<String, String> map
	 * @return boolean
	 * */
	public boolean checkCqxx(HashMap<String, String> map){
		return checkExists("xszdfwzxcqb", "nd||yf||slbmdm", map.get("nd")+map.get("yf")+map.get("slbmdm"));
	}
}
