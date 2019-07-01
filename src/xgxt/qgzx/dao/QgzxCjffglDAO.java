package xgxt.qgzx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import common.Globals;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.qgzx.form.QgzxForm;
import xgxt.utils.GetTime;
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
public class QgzxCjffglDAO extends DAO {
	
	ArrayList<String> value = new ArrayList<String>();
	
	public QgzxCjffglDAO(){}
	
	public QgzxCjffglDAO(DataSource ds){
		super(ds);
	}
	
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
		String ffsj = model.getFfsj();
		String sqdw = model.getYrdwdm();
		String ffsjks = model.getFfsjks();
		String ffsjjs = model.getFfsjjs();
		String xxsh = model.getXxsh();
		
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
		if(!StringUtils.isNull(xxsh)){
			sb.append( " and xxsh=?");
			value.add(xxsh);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(ffsj)){
			sb.append( " and ffsj like '%'||?||'%'");
			value.add(ffsj);
		}
		if(!StringUtils.isNull(ffsjks)){
			sb.append( " and to_number(substr(ffsj,0,8)) >= to_number(?)");
			value.add(ffsjks.replaceAll("-", ""));
		}
		if(!StringUtils.isNull(ffsjjs)){
			sb.append( " and to_number(substr(ffsj,0,8)) <= to_number(?)");
			value.add(ffsjjs.replaceAll("-", ""));
		}
		if(!StringUtils.isNull(sqdw)){
			sb.append( " and sqdw = ?");
			value.add(sqdw);
		}
		if(checkExists("yrdwdmb", "dlm", model.getUserName())){
			if(StringUtils.isNull(sqdw)){
				sb.append( " and exists(select 1 from yrdwdmb b where b.yrdwdm=a.sqdw and b.dlm=?)");
				value.add(model.getUserName());
			}
		}
		return sb;
	}
	
	/**
	 * 获取查询条件
	 * */
	public StringBuffer getLscjWhereSql(QgzxForm model){
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
		String ffsj = model.getFfsj();
		String sqdw = model.getYrdwdm();
		String ffsjks = model.getFfsjks();
		String ffsjjs = model.getFfsjjs();
		String xxsh = model.getXxsh();
		
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
		if(!StringUtils.isNull(xxsh)){
			sb.append( " and xxsh=?");
			value.add(xxsh);
		}
		if(!StringUtils.isNull(xh)){
			sb.append( " and xh like '%'||?||'%'");
			value.add(xh);
		}
		if(!StringUtils.isNull(xm)){
			sb.append( " and xm like '%'||?||'%'");
			value.add(xm);
		}
		if(!StringUtils.isNull(ffsj)){
			sb.append( " and ffsj like '%'||?||'%'");
			value.add(ffsj);
		}
		if(!StringUtils.isNull(ffsjks)){
			sb.append( " and to_number(substr(ffsj,0,4)||substr(ffsj,6,2)||substr(ffsj,9,2)) >= to_number(?)");
			value.add(ffsjks.replaceAll("-", ""));
		}
		if(!StringUtils.isNull(ffsjjs)){
			sb.append( " and to_number(substr(ffsj,0,4)||substr(ffsj,6,2)||substr(ffsj,9,2)) <= to_number(?)");
			value.add(ffsjjs.replaceAll("-", ""));
		}
		if(!StringUtils.isNull(sqdw)){
			sb.append( " and sqdw = ?");
			value.add(sqdw);
		}
		if(checkExists("yrdwdmb", "dlm", model.getUserName())){
			if(StringUtils.isNull(sqdw)){
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
	 * 查询学生酬金发放信息导出
	 * @param QgzxForm model
	 * String[] output
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> selectXscjffxx(QgzxForm model,String[] output) throws Exception{		
		String sql = "select a.*,a.xh||a.gwdm||a.sqsj||nd||yf||fflx 主键  from view_xscjff a " + getWhereSql(model).toString();
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		return rsToVator(sql, input, output);
	}

	/**
	 * 查询学生临时岗位酬金发放信息
	 * @param QgzxForm model
	 * String[] output
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> selectXslsgzffxx(QgzxForm model,String[] output) throws Exception{
		String sql = "select a.*,a.xh||a.gwdm||a.gwsbsj||nd||yf 主键  from view_xslscjff a " + getLscjWhereSql(model).toString();
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		return rsToVator(sql, input, output);
	}
	
	/**
	 * 查询中国地质大学学生除临时岗位酬金发放信息
	 * @param QgzxForm model
	 * String[] output
	 * @return List<String[]>
	 * @throws Exception
	 * */
	public List<String[]> selectZgdzdxXscjffxx(QgzxForm model,String[] output) throws Exception{
		String sql = "select a.*,a.xh||a.gwdm||a.sqsj||nd||yf||fflx 主键  from view_xscjff a " 
			         + getWhereSql(model).toString();
		String fflx = model.getFflx();
		if("补发工资".equalsIgnoreCase(fflx)){
			sql += StringUtils.joinStr(" and fflx='补发'");
		}else{
			sql += StringUtils.joinStr(" and fflx is null ");
		}		
		
		String[] input = value != null ? value.toArray(new String[0]) : new String[]{};
		return rsToVator(sql, input, output);
	}
	
	
	/**
	 * 查询单条酬金发放信息
	 * @param String pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectXscjffOne(String pkValue){
		String sql = "select * from view_xscjff where xh||gwdm||sqsj||nd||yf||fflx=?";
		String[] outputValue = {"bjdm","bjmc","bz","cjje","fflx","ffsj","gwdm","gwxzmc","gzpj","gzsj","nd","nj","sqdw","sqsj","xb","xh","xm","xn","xq","xqmc","xxsh","xydm","xymc","yf","yhkh","yhmc","yrdwmc","zydm","zymc"};
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 获取酬金发放剩余金额
	 * @param HashMap<String, String> paramMap
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> selectCjffSyjf(HashMap<String, String> paramMap){
		HashMap<String, String> resultMap = new HashMap<String, String>();
		String nd = paramMap.get("nd");
		String yrdwdm = paramMap.get("sqdw");
		String yrdwmc = paramMap.get("yrdwmc");
		String gwxzdm = paramMap.get("gwxz");
		String gwxzmc = paramMap.get("gwxzmc");
		String xxdm = paramMap.get("xxdm");
		// 剩余经费=划拨金额+增拨金额-发放金额(划拨金额+增拨金额=hbje)
		String sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=? and (gwxzdm=? or gwxzdm is null) ),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and (gwxzmc=? or gwxzmc is null)),0) syjf from dual";
		resultMap = getMap(sql, 
				           new String[] {nd, yrdwdm, gwxzdm,  nd, yrdwdm, gwxzmc }, 
				           new String[] { "syjf" });
		if(xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)){
			sql = "select nvl((select sum(hbje) from jfhbb where nd=? and yrdwdm=?),0)-nvl((select sum(cjje) from view_xscjff where nd=? and sqdw=? and yrdwmc=?),0) syjf from dual";
			resultMap = getMap(sql, 
					           new String[] { nd, yrdwdm, nd, yrdwdm, yrdwmc}, 
					           new String[] { "syjf" });
		}
		
		return resultMap;
	}
	
	public List<HashMap<String, String>> selectXscjff(String pkValue, 
			                                          HashMap<String, String>paramMap){
		String xxdm = paramMap.get("xxdm");
		String nd = paramMap.get("nd");
		String yf = paramMap.get("yf");
		String sql = "";
		String[] inputValue = {};
		String[] outputValue = {};
		if (xxdm.equalsIgnoreCase(Globals.XXDM_BJLHDX)) {
			//北京联合
			//查询该岗位在岗的需要发放酬金的学生
			sql = "select a.*,b.bz,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh," 
				  + "d.yje cjje,d.ysj gzsj  from (" 
				  + "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xyyj='通过' and xxyj='通过') a " 
				  + "left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				  + " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=a.xh left join (select xh,ysj,yje,time from xskhyb where sftj = 'yes') d on a.xh = d.xh and d.time =? ";
			inputValue = new String[]{pkValue,nd,yf,nd+yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj"};

		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZGDZDX)){
			//中国地质大学
			sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
				+ "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
				+ "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xyyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh";
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh"};
			
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJJDZYJSXY)){
			//浙江机电职业技术学院			
			sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
				+ "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
				+ "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh";
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh"};
			
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_NBLGXY)){
			//宁波理工学院
			sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
				  + "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
				  + "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				  + " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh";
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh"};
			
		}else if(xxdm.equalsIgnoreCase(Globals.XXDM_ZJLG)){
			//浙江理工大学
			sql = "select a.*," 
				+ "decode(b.gzsj,'',(select ygzsj from view_qgzx_xsgzkhb c where a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj and a.xh=c.xh and c.nd=b.nd and c.yf=b.yf),b.gzsj)gzsj," 
				+ "decode(b.cjje,'',(select ffcjje from view_qgzx_xsgzkhb c where a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj and a.xh=c.xh and c.nd=b.nd and c.yf=b.yf),b.cjje)cjje," 
				+ "b.bz,b.khqk,b.xxsh ,(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
				+ "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xyyj='通过' and xxyj='通过') a " 
				+ "left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh";
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh"};
			
		}else if(Globals.XXDM_GZDX.equalsIgnoreCase(xxdm)){
			//广州大学
			sql = "select a.*,b.gzsj," 
				+ "(select ygzsj from view_qgzx_xsgzkhb c where c.nd=b.nd and c.yf=b.yf and a.xh=c.xh and a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) khgzsj," 
				+ "b.cjje,(select ffcjje from view_qgzx_xsgzkhb c where c.nd=b.nd and c.yf=b.yf and a.xh=c.xh and a.gwdm=c.gwdm and a.gwsbsj=c.gwsbsj) khcjje," 
				+ "b.bz,b.khqk,b.xxsh,b.gzpj ,(select kh from view_xsxxb c where a.xh=c.xh) kh  from (" 
				+ "select rownum, a.* from(select xh, xm, bjmc, to_char(gwsbsj) gwsbsj,to_char(gwdm) gwdm" 
				+ " from view_xsgwxx where  sfyx = '有效' union select qgxh xh,(select xm from view_xsjbxx where a.qgxh=xh) xm ,"
				+ "(select bjmc from view_xsjbxx where a.qgxh=xh) bjmc,lsgwsbsj gwsbsj,lsgwmc gwdm"
				+ " from gzdx_lsgwb a  ) a where gwdm || gwsbsj =?)a"
				+ " left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=?";	
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh", "khgzsj", "khcjje", "gzpj"};
		}else if(Globals.XXDM_ZJCMXY.equalsIgnoreCase(xxdm)){
			//浙江传媒学院
			sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
				+ "nvl(b.yhkh,(select yhkh from xsxxb c where a.xh=c.xh)) kh," 
				+ "nvl(b.yhmc,(select yhmc from view_xsxxb c where a.xh=c.xh))yhmc  from (" 
				+ "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xyyj='通过' and xxyj='通过') a " 
				+ "left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=?";
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", 
					                   "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", 
					                   "xxsh", "yhmc"};
			
		}else if(Globals.XXDM_CZXXZYJSXY.equalsIgnoreCase(xxdm)){
			//常州信息职业技术学院 离职的学生不发放酬金
			sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
				+ "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
				+ "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where " 
				+ "(lzsj is null or (lzsj is not null and to_number(substr(lzsj,1,4)||substr(lzsj,6,2)||substr(lzsj,9,2))>to_char(sysdate,'yyyymmdd'))) and gwdm||gwsbsj=? and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh";
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh"};
		}else if(Globals.XXDM_ZJKJXY.equalsIgnoreCase(Base.xxdm)){
			//浙江科技
//			sql = "select rownum,a.xh,a.xm,a.bjmc,a.gwsbsj,a.gwdm,(case when a.cjje is null and a.gzsj is null then to_char(b.ygzsj) else to_char(a.gzsj) end)gzsj,(case when a.cjje is null and a.gzsj is null then to_char(b.ffcjje) else to_char(a.cjje) end)cjje,a.bz,a.khqk,a.xxsh,a.kh,b.ygzsj,b.ffcjje from (select rownum,a.xh,a.xm,a.bjmc,a.gwsbsj,a.gwdm,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
//				+ "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
//				+ "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
//				+ " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh) a,qgzx_xsgzkhb b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and a.xh=b.xh and b.nd=? and b.yf=?";
			sql = "select rownum,a.xh,a.xm,a.bjmc,a.gwsbsj,a.gwdm,a.gzsj,a.cjje,a.bz,a.khqk,a.xxsh,a.kh,b.ygzsj,b.ffcjje from (select rownum,a.xh,a.xm,a.bjmc,a.gwsbsj,a.gwdm,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
				+ "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
				+ "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh) a,qgzx_xsgzkhb b where a.gwdm=b.gwdm and a.gwsbsj=b.gwsbsj and a.xh=b.xh and b.nd=? and b.yf=?";
			inputValue = new String[]{pkValue,nd,yf,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh","ygzsj","ffcjje"};
			
		} else if(Globals.XXDM_ZJXY.equalsIgnoreCase(Base.xxdm)){
			sql = "select a.*,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
				+ "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
				+ "select rownum,xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh";
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh"};		
		} else {
			sql = "select rownum,a.xh,a.xm,a.bjmc,a.gwsbsj,a.gwdm,b.gzsj,b.cjje,b.bz,b.khqk,b.xxsh ," 
				+ "(case when c.sfsh='通过' then c.xghkh else c.xgqkh end) kh  from (" 
				+ "select xh,xm,bjmc,gwsbsj,gwdm from view_xsgwxx where gwdm||gwsbsj=? and xyyj='通过' and xxyj='通过') a left join xscjffb b on a.xh=b.xh and a.gwsbsj=b.sqsj and a.gwdm=b.gwdm"
				+ " and b.nd=? and b.yf=? left join khxgsqb c on c.xh=b.xh";
			inputValue = new String[]{pkValue,nd,yf};
			outputValue = new String[]{"rownum", "xh", "xm", "bjmc", "gwsbsj", "gwdm", "bz", "kh", "cjje", "gzsj", "khqk", "xxsh"};			
		}
		
		return getList(sql, inputValue, outputValue);
	}
	
	/**
	 * 查询用人单位信息
	 * @param user
	 * @return List<HashMap<String, String>>
	 * */
	public List<HashMap<String, String>> getYrdwList(User user){
		QgzxDao qDao = new QgzxDao();
		String sql = "select distinct yrdwdm,yrdwmc from yrdwdmb ";
		if(qDao.isYrdwUser(user.getUserName())){
			sql += " where dlm='" + user.getUserName() + "'";
		}
		return getList(sql, new String[]{}, new String[]{"yrdwdm", "yrdwmc"});
	}
	
	/**
	 * 根据学期代码查询学期名称
	 * @param xq
	 * @return String
	 * */
	public String getXqmc(String xq){
		String sql = "select xqmc from xqdzb where xqdm=?";
		return getOneRs(sql, new String[]{xq}, "xqmc");
	}
	
	/**
	 * 查询用人单位下的学生酬金发放信息
	 * @param model
	 * @return List<HashMap<String, String>> 
	 * */
	public List<HashMap<String, String>> getXscjffList(QgzxForm model){
		String sql = "select yrdwmc,gwdm,xh,xm,xymc,zymc,bjmc,gzsj,cjje from view_xscjff where xn=? and xq=? and sqdw=?";
		String[] output = new String[]{"yrdwmc","gwdm","xh","xm","xymc","zymc","bjmc","gzsj","cjje"};
		return getList(sql, new String[]{model.getQueryequals_xn(),model.getQueryequals_xueqi(),model.getYrdwdm()}, output);
	}
	
	
	/**
	 * 工资发放数据
	 * @param model
	 * @return List<String[]>
	 */
	public List<String[]> getGzffList(QgzxForm model){
		
		HashMap<String, Object> map = getGzffSQL(model);
		StringBuilder sql = new StringBuilder();
		
		sql.append("select xymc,gwdm,count(*) rs,sum(cjje) je from (")
		   .append("select xymc,gwdm,nvl(cjje,0) cjje from view_xscjff where 1=1 ")
		   .append(map.get("sql"))
		   .append(") group by xymc,gwdm");
		
		return this.rsToVator(sql.toString(), (String[])map.get("input"), new String[]{"xymc","gwdm","rs","je"});
	}
	
	
	
	/**
	 * 工资发放过滤条件
	 * @param model
	 * @return List<String[]>
	 */
	private HashMap<String, Object> getGzffSQL(QgzxForm model){
		StringBuilder sql = new StringBuilder();
		List<String> input = new ArrayList<String>();
		
		if (StringUtils.isNotNull(model.getQueryequals_xn())){
			sql.append(" and xn=?");
			input.add(model.getQueryequals_xn());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_nd())){
			sql.append(" and nd=?");
			input.add(model.getQueryequals_nd());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_xueqi())){
			sql.append(" and xq=?");
			input.add(model.getQueryequals_xueqi());
		}
		
		if (StringUtils.isNotNull(model.getQueryequals_gwdm())){
			sql.append(" and gwdm=?");
			input.add(model.getQueryequals_gwdm());
		}
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("sql", sql);
		map.put("input", input.toArray(new String[]{}));
		
		return map;
	}
}
