package xsgzgl.pjpy.general.wdpj.pjtj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommService;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company: zfsoft</p>
 * <p>Author: qlj</p>
 * <p>Version: 1.0</p>
 * <p>Time:2012-8-10 下午02:17:44</p>
 */
public class WdpjPjtjMethod extends CommService{
	
	
	// -------------------------前置奖学金相关条件 begin -------------------------
	/**
	 * 前置奖学金个性化修改
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkQzjxj(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		
		String condition =(String)map.get("condition");
		
		String[] jxjmc=condition.split("!!@@!!");
		// -------------------个性化业务 sql begin-------------------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xg_pjpy_pjxmsqb a,xg_pjpy_pjxmwhb b  ");
		sql.append(" where (a.sqjg = 'tg' or b.sfsh='no') and a.xmdm=b.xmdm");
		sql.append(" and a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd ");
		sql.append(" and xh =? ");
		
		sql.append(" and ( ");
		
		for(int i=0;i<jxjmc.length;i++){
			
			if(i!=0){
				sql.append(" or ");
			}
			sql.append(" xmmc like '"+jxjmc[i]+"' ");
		}
		
		sql.append(" )  ");
		// -------------------个性化业务 sql end-------------------------
		
		String[]pjzgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (pjzgXh==null || pjzgXh.length==0 ? false :true );
		
		if (!flag) {
			
			//不满足条件的话
			message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

		}
		return message;
	}
	
	// -------------------------前置奖学金相关条件 end---------------------------
	
	
	
	// -------------------------违纪处分条件相关 begin ---------------------
	/**
	 * 判断评奖学年是否有违纪处分
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkWjcfByPjXn(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//关联学年
		String glxn=(String)map.get("xn");
		//关联学期
		String glxq=(String)map.get("xq");	
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xh=? ");
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		
		sql.append(" and xxsh = '通过'  ");
		sql.append(" group by xh  ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			
			//不满足条件的话
			message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

		}
		return message;
	}
	
	/**
	 * 判断是否有违纪处分
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkWjcf(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xh =? ");
		sql.append(" and xxsh = '通过'  ");
		sql.append(" group by xh  ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (!flag) {
			
			//不满足条件的话
			message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

		}
		return message;
	}
	
	//  -------------------------违纪处分条件相关 end ---------------------
	
	
	
	// -------------------------不及格科目相关 begin--------------------------
	/**
	 * 判断是否存在不及格科目
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkBjgkm(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,min(cj)cj from view_zhhcjb ");
		sql.append(" where xh = ");
		sql.append("'"+xh+"' group by xh ");
		
		HashMap<String,String>search=dao.getMapNotOut(sql.toString(), new String[]{"xh","cj"});
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (search==null || search.size()==0 ? true : false);
		
		if (!flag) {
			
			int minCj=Integer.parseInt(search.get("cj"));
			
			if(minCj<0){
				//不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			}
		}
		
		return message;
	}
	
	/**
	 * 判断是否存在不及格科目(评奖学年)
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkBjgkmByXn(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		//关联学年
		String glxn=(String)map.get("xn");
		//关联学期
		String glxq=(String)map.get("xq");	
		
		String condition=map.get("condition");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,min(cj)cj from view_zhhcjb ");
		sql.append(" where xh = ");
		sql.append("'"+xh+"' ");
		
		if(!Base.isNull(condition)){
			sql.append(condition);
		}
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		sql.append(" group by xh ");
		
		HashMap<String,String>search=dao.getMapNotOut(sql.toString(), new String[]{});
		
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (search==null || search.size()==0 ? true : false);
		
		if (!flag) {
			
			double minCj=Double.parseDouble(search.get("cj"));
			
			if(minCj<60){
				//不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			}
		}
		
		return message;
	}
	// -------------------------不及格科目相关 end--------------------------
	
	
	// -------------------------困难生条件相关 begin--------------------------	
	/**
	 * 是否困难生
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkKnskz(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		//关联学年
		String glxn=(String)map.get("xn");
		//关联学期
		String glxq=(String)map.get("xq");	
		
		String condition=(String)map.get("condition");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xszz_knsb ");
		sql.append(" where xh =? ");
		
		sql.append(" and ( ");
		
		String[]knlb=condition.split("!!@@!!");
		for(int i=0;i<knlb.length;i++){
			
			if(i!=0){
				sql.append(" or ");
			}
			sql.append(" xmzzjb ='"+knlb[i]+"' ");
		}
		
		sql.append(" )  ");
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		
		sql.append(" and ((exists(select 1 from xszz_zzxmb where shjb='一级审核' and xmdm='5002') and shzt1='通过') ");
		sql.append(" or (exists(select 1 from xszz_zzxmb where shjb='两级审核' and xmdm='5002') and shzt2='通过') ");
		sql.append(" or (exists(select 1 from xszz_zzxmb where shjb='三级审核' and xmdm='5002') and shzt3='通过') ) ");
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		
		//条件说明
		String tjms ="家庭经济情况为"+condition.replaceAll("!!@@!!", "或");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (flag) {
				//不满足条件的话
			message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

		}
		return message;
	}


	/**
	 * 是否困难生
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkKns(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//关联学年
		String glxn=(String)map.get("xn");
		//关联学期
		String glxq=(String)map.get("xq");	
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xszz_shztb  ");
		sql.append(" where xmdm='5002' and xh=?  ");
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		
		sql.append(" and ((exists(select 1 from xszz_zzxmb where shjb='一级审核' and xmdm='5002') and shzt1='通过') ");
		sql.append(" or (exists(select 1 from xszz_zzxmb where shjb='两级审核' and xmdm='5002') and shzt2='通过') ");
		sql.append(" or (exists(select 1 from xszz_zzxmb where shjb='三级审核' and xmdm='5002') and shzt3='通过') ) ");
		
		
		String[]nozgXh=dao.getArray(sql.toString(), new String[]{xh}, "xh");
		//条件说明
		String tjms = (String)map.get("tjms");

		boolean flag = (nozgXh==null || nozgXh.length==0 ? true : false);
		
		if (flag) {
			
			//不满足条件的话
			message = "申请该项目需要：申请者为困难生,申请者不满足申请条件！";

		}
		return message;
	}

	// -------------------------困难生条件相关 begin--------------------------	
	
	
	// ----------------------------学校个性化条件 begin -----------------------------
	
	// ==========================贵州大学个性化条件 begin===============================
	
	/**
	 * 三好学生评奖条件（获得等级奖学金或平均学分绩点>20%）
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String shxsPjTjByGzdx(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//申请类型
		String sqlx=(String)map.get("sqlx");
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		
		String condition =(String)map.get("condition");
		
		String[] jxjmc=condition.split("!!@@!!");
		// -------------------个性化业务 sql begin-------------------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh from xg_pjpy_pjxmsqb a,xg_pjpy_pjxmwhb b  ");
		sql.append(" where (a.sqjg = 'tg' or b.sfsh='no') and a.xmdm=b.xmdm");
		sql.append(" and a.pjxn=b.pjxn and a.pjxq=b.pjxq and a.pjnd=b.pjnd ");
		sql.append(" and xh =? ");
		
		sql.append(" and ( ");
		
		for(int i=0;i<jxjmc.length;i++){
			
			if(i!=0){
				sql.append(" or ");
			}
			sql.append(" xmmc like '"+jxjmc[i]+"' ");
		}
		
		sql.append(" )  ");
		// -------------------个性化业务 sql end-------------------------
		
		String pjzgXh=dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
		boolean flag = (Base.isNull(pjzgXh) ? false :true );
		
		if(!flag){
			
			sql=new StringBuilder(" select * from xg_view_gzdx_jqpjfpm where xh=? and jqpjfpmbl <=2");
			pjzgXh=dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
		}
		
		//条件说明
		String tjms = (String)map.get("tjms");
		
		flag = (Base.isNull(pjzgXh) ? false :true );
		if (!flag) {
			
			//不满足条件的话
			message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

		}
		return message;
	}
	
	
	/**
	 * 不及格科目，补考通过
	 * 
	 * @param xh
	 * @param map
	 * @return
	 * @author CMJ
	 */
	public String judgebjgReverse(HashMap<String,String>map) throws SQLException{
		WdpjPjtjDAO dao = new WdpjPjtjDAO();
		WdpjPjtjService serive=new WdpjPjtjService();

		String message = "";

		// 条件说明
		String tjms = map.get("tjms");
		// 特殊格式
		String tsgs = map.get("tsgs");
		
		// 关系
		String gx = map.get("gx");
		// 条件值
		String tjz = map.get("tjz");
		
		tsgs = Base.isNull(tsgs) ? "" : tsgs;

		// 比较值
		String xh=map.get("xh");
		String bjz = dao.getBjz(xh, map, "count");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		boolean flag = false;

		flag = serive.compareTo(bjz, tjz, gx, true);
		if(flag){
			//是否通过补考
			flag=dao.isTgbk(xh);
		}

		// 不满足条件的话
		if (!flag) {
			message = "申请该项目需要：" + tjms + ",申请者为不满足该条件！";
		}

		return message;
	}
	

	// ==========================广东水利水电个性化条件 begin===============================
	/**
	 * 判断广东水利水电进步奖
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	public String checkGdslsdJbj(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		
		// -------------------个性化业务 sql begin-------------------------
		StringBuilder sql=new StringBuilder();
		sql.append(" select xh,xn,xq,nd,bjdm,pmjbbl,bxkpjf,xxkpjf,pm from ( ");
		sql.append(" select xh,xn,xq,nd,bjdm,pmjbbl,bxkpjf,xxkpjf, ");
		sql.append(" (rank() over(partition by bjdm order by pmjbbl desc, ");
		sql.append(" bxkpjf desc,xxkpjf desc)) pm ");
		sql.append(" from (select a.xh, a.xn, a.xq, a.nd, a.bjdm, a.pmjbbl, b.bxkpjf, c.xxkpjf ");
		sql.append(" from (select a.xh,a.xn,'no' xq,'no' nd,a.bjdm, ");
		sql.append(" round((b.bjpm - a.bjpm) / a.num * 100, 2) pmjbbl from ( ");
		sql.append(" select xh,(select pjxn from xg_pjpy_xtszb where rownum = 1) xn,bjdm,bjpm,num ");
		sql.append(" from (select xh,a.bjdm,b.num,(rank() over(partition by a.bjdm order by avgcj desc)) bjpm ");
		sql.append(" from (select a.xh, a.bjdm, nvl(avgcj, 0) avgcj from (select xh, bjdm  from xg_pjpy_pjrykb a ");
		sql.append(" where exists (select 1  from xg_pjpy_pjrykb b where a.bjdm = b.bjdm)) a ");
		sql.append(" left join (select xh, round(avg(cj), 2) avgcj from view_zhhcjb ");
		sql.append(" where xn =(select pjxn  from xg_pjpy_xtszb) ");
		sql.append(" and xq =(select xqdm  from xqdzb   where xqjb = '1' and rownum = 1) ");
		sql.append(" group by xh, xn) b on a.xh = b.xh) a ");
		sql.append(" left join (select bjdm, count(1) num  from xg_pjpy_pjrykb group by bjdm) b on a.bjdm = b.bjdm)) a ");
		sql.append(" left join (select xh,  (select pjxn  from xg_pjpy_xtszb  where rownum = 1) xn, bjdm, bjpm ");
		sql.append(" from (select xh, a.bjdm,  b.num, (rank() over(partition by a.bjdm order by avgcj desc)) bjpm ");
		sql.append(" from (select a.xh, a.bjdm,nvl(avgcj, 0) avgcj from (select xh, bjdm  from xg_pjpy_pjrykb a ");
		sql.append(" where exists (select 1 from xg_pjpy_pjrykb b  where a.bjdm = b.bjdm)) a ");
		sql.append(" left join (select xh,  round(avg(cj), 2) avgcj  from view_zhhcjb where xn =  (select pjxn from xg_pjpy_xtszb) ");
		sql.append(" and xq = (select xqdm from xqdzb  where xqjb = '2'   and rownum = 1) ");
		sql.append(" group by xh, xn) b on a.xh = b.xh) a left join (select bjdm, count(1) num ");
		sql.append(" from xg_pjpy_pjrykb group by bjdm) b on a.bjdm = ");
		sql.append(" b.bjdm)) b on a.xh = b.xh and a.xn = b.xn where b.bjpm - a.bjpm >= 5) a ");
		sql.append(" left join (select xh, round(avg(cj), 2) bxkpjf from view_zhhcjb where kcxz = '必修课' ");
		sql.append(" and xn = (select pjxn from xg_pjpy_xtszb) group by xh) b on a.xh = b.xh ");
		sql.append(" left join (select xh, round(avg(cj), 2) xxkpjf from view_zhhcjb where kcxz = '限选课' ");
		sql.append(" and xn = (select pjxn from xg_pjpy_xtszb) ");
		sql.append(" group by xh) c on a.xh = c.xh)) where pm<=2  ");

		sql.append(" and  xh=?  ");
		// -------------------个性化业务 sql end-------------------------
		
		String pjzgXh=dao.getOneRs(sql.toString(), new String[]{xh}, "xh");
		boolean flag = (Base.isNull(pjzgXh) ? false :true );
		
		//条件说明
		String tjms = (String)map.get("tjms");
		
		flag = (Base.isNull(pjzgXh) ? false :true );
		if (!flag) {
			
			//不满足条件的话
			message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

		}
		return message;
	}
	
	
	// ==========================浙江邮电个性化条件 begin===============================
	/**
	 * 
	 * @描述:浙江邮电职业技术学院 月五星级寝室评选次数
	 * @作者：陶钢军[工号：1075]
	 * @日期：2015-12-31 下午02:31:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param map
	 * @return
	 * @throws SQLException
	 * String 返回类型 
	 * @throws
	 */
	public String checkYwxjqsCount(HashMap<String,String>map) throws SQLException{
		
		DAO dao=DAO.getInstance();
		WdpjPjtjService serive = new WdpjPjtjService();
		
		//消息
		String message="";
		//申请或者上报学生学号
		String xh=map.get("xh");
		//关联学年
		String glxn=(String)map.get("xn");
		//关联学期
		String glxq=(String)map.get("xq");	
		
		String condition=map.get("condition");
		
		StringBuilder sql=new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select nvl(sum(b.cnt),0) cnt ");
		sql.append(" from xg_gygl_new_cwxxb a ");
		sql.append(" left join (select LDDM, QSH, xn, xq, count(1) cnt ");
		sql.append(" from (select LDDM, QSH, DJ, xn, xq, jcyf, tjzt ");
		sql.append(" from VIEW_XG_GYGL_NEW_WSJC_QSFSB_YF where 1 = 1 ");
		
		if(!Base.isNull(condition)){
			sql.append(condition);
		}
		
		if("pjxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select pjxn from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxn".equalsIgnoreCase(glxn)){
			sql.append(" and xn = (select dqxn from xtszb where rownum = 1)  ");
		}
		
		if("pjxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select pjxq from xg_pjpy_xtszb where rownum = 1)  ");
		}else if("dqxq".equalsIgnoreCase(glxq)){
			sql.append(" and xq = (select dqxq from xtszb where rownum = 1)  ");
		}
		sql.append(" and dj = '5星') ");
		sql.append(" group by LDDM, QSH, xn, xq) b ");
		sql.append(" on a.lddm = b.lddm ");
		sql.append(" and a.qsh = b.qsh ");
		sql.append(" where a.xh = ? ");
		params.add(xh);
		
		HashMap<String,String> search = dao.getMapNotOut(sql.toString(), params.toArray(new String[]{}));
		
		//条件说明
		String tjms = (String)map.get("tjms");
		
		// 关系
		String gx = (String)map.get("gx");
		// 条件值
		String tjz = (String)map.get("tjz");
		
		// 比较值
		String bjz = search.get("cnt");
		bjz = Base.isNull(bjz) ? "0" : bjz;

		boolean flag = false;

		flag = serive.compareTo(bjz, tjz, gx, true);

		// 不满足条件的话
		if (!flag) {
			message = "申请该项目需要：" + tjms + gx + tjz + ",申请者为不满足该条件！";
		}

		return message;
	}

	// ----------------------------学校个性化条件 end   -----------------------------
}
