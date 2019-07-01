package xgxt.pjpy.zjsyzy;

import java.util.*;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 浙江商业职业技术学院评奖评优DAO
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-09-16</p>
 */
public class PjpyZjsyzyDAO extends DAO{
	List<String> values = new ArrayList<String>();// 用于存入页面条件值
	/**
	 * 加载WHERE条件查询语句
	 * 
	 * @param pjpyahjgxscjqryModel
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(PjpyZjsyzyForm model)
			throws Exception {
		StringBuffer whereSql = new StringBuffer(" where 1=1 ");
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String xn = model.getXn();
		String nj = model.getNj();
		String xh = DealString.toGBK(model.getXh());
		String xm = DealString.toGBK(model.getXm());
		String xq = model.getXq();
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}// end if
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}// end if
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}// end if
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and xn = ?");
			values.add(xn);
		}// end if
		if (!StringUtils.isNull(nj)) {
			whereSql.append(" and nj = ?");
			values.add(nj);
		}// end if
		if (!StringUtils.isNull(xh)) {
			whereSql.append(" and xh = ?");
			values.add(xh);
		}// end if
		if (!StringUtils.isNull(xm)) {
			whereSql.append(" and xm = ?");
			values.add(xm);
		}// end if
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and xq = ?");
			values.add(xq);
		}// end if
		return whereSql;
	}
	
	/**
	 * 获取学生的综合素质测评基本信息
	 * @param xh
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getZhszcpStuinfo(String xh){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xh,xm,xb,nj,xydm,zydm,bjdm,xymc,zymc,bjmc from view_xsjbxx where xh=?";
		String[] input = {"xh","xm","xb","nj","xydm","zydm","bjdm","xymc","zymc","bjmc"};
		String xn = Base.currXn;
		String xq = Base.currXq;
		String nd = Base.currNd;
		String dcj = "";
		String zcj = "";
		String tcj = "";
		String jnjf = "";
		//学生的基本信息
		map = getMap(sql, new String[]{xh}, input);
		
		//学生当前学年学期的德育总分
		sql = "select 70+to_number(ryjf,99.9)+to_number(rzjf,99.9)+to_number(bsjf,99.9)+to_number(qsbsjf,99.9)+to_number(cjhdjf,99.9)-to_number(wjkf,99.9)-to_number(jttbkf,99.9)+to_number(qtjjfxx,99.9) dcj " + 
         	  "from   zhszcp_dyszjfwh where xh=? and xn=? and xq=?";
		dcj = getOneRs(sql, new String[]{xh,xn,xq}, "dcj");
		
		//学生当前学年学期的智育总分
		sql = "select nvl(sum(maxcj(cj,bkcj,cxcj)*to_number(trim(xf),99.9))/sum(to_number(trim(xf),99.9)),0) xqjqpjf from cjb where xh=? and kcmc not like '%体育%' and xn=? and xq=?";
		String xqjqpjf = getOneRs(sql, new String[]{xh,xn,xq}, "xqjqpjf");
		sql = "select nvl(to_number(hjjf,99.9)+to_number(trim(fkjf),99.9)-to_number(trim(kccjbjgkf),99.9)-to_number(trim(qtzykf),99.9),0) zyfjf from zhszcp_dyszjfwh where xh=? and xn=? and xq=?";
		String zyfjf = getOneRs(sql, new String[]{xh,xn,xq}, "zyfjf");		
		xqjqpjf = xqjqpjf == null || "".equalsIgnoreCase(xqjqpjf) ? "0" : xqjqpjf;
		zyfjf = zyfjf == null || "".equalsIgnoreCase(zyfjf) ? "0" : zyfjf;
		zcj = getOneRs("select nvl(to_number(" + xqjqpjf + ",99.9),0) + nvl(to_number(" + zyfjf+ ",99.9),0) zcj from dual", new String[]{}, "zcj"); 
		
		//学生当前学年学期的体育总分
		sql = "select nvl(to_number(cj,999.99),0) tycj from cjb where  xh=? and kcmc like '%体育%' and xn=? and xq=?";
		String tycj = getOneRs(sql, new String[]{xh,xn,xq}, "tycj");
		tycj = tycj == null || tycj.equalsIgnoreCase("") ? "0" : tycj;
		sql = "select nvl(to_number(tybsjf,999.99),0) tyfjf from zhszcp_dyszjfwh where xh=? and xn=? and xq=?";
		String tyfjf = getOneRs(sql, new String[]{xh,xn,xq}, "tyfjf");
		tyfjf = tyfjf == null || tyfjf.equalsIgnoreCase("") ? "0" : tyfjf;
		tcj = getOneRs("select nvl(to_number(" + tycj +",999.99),0) + nvl(to_number(" + tyfjf + ",999.99),0)tcj from dual", new String[]{}, "tcj");
		
		//学生当前学年学期的技能加分
		sql = "select to_number(nvl(jnjf,0)) jnjf from zhszcp_dyszjfwh where xh=? and xn=? and xq=?";
		jnjf = getOneRs(sql, new String[]{xh,xn,xq}, "jnjf");
		
		map.put("dcj", dcj);
		map.put("zcj", zcj);
		map.put("tcj", tcj);
		map.put("jnjf", jnjf);
		map.put("xn", xn);
		map.put("xq", xq);
		map.put("nd", nd);
		return map;
	}
	
	/**
	 * 获取单个学生申请奖学金信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 **/
	public HashMap<String, String> getJxjshInfo(String pk, String pkValue,String userType){
		HashMap<String, String> map = new HashMap<String, String>();
		pk = "a.xn||a.nd||a.xh||a.jxjdm";
		String[] input = {"xh","xm","xb","nj","xydm","xymc","zymc","bjmc","nd","xn","xq","jxjmc","jxjdm","drzw","xxjl","kycg","sqly","yesNo","xxshyj","xyshyj","fdyyj","dcj","zcj","tcj","jnjf","kpf","dcjpm","zcjpm","tcjpm","jnjfpm","cpzfpm"};
		String sql = "select a.xh,a.xm,a.xb,a.nj,a.xymc,a.xydm,a.zymc,a.bjmc,a.nd,a.xn,a.xq,a.jxjmc,a.jxjdm,a.drzw,a.xxjl,a.kycg,a.sqly,a.xxsh yesNo,a.xxshyj,a.xyshyj,a.fdyyj,b.dcj,b.dcjpm,b.zcj,b.zcjpm,b.tcj,b.tcjpm,b.jnjf,b.jnjfpm,b.cpzf kpf,b.cpzfpm from view_xsjxjb a left join view_zhszcp b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq where " + pk + "= ?";
		if(userType != null && userType.equalsIgnoreCase("xy")){
			sql = "select a.xh,a.xm,a.xb,a.nj,a.xydm,a.xymc,a.zymc,a.bjmc,a.nd,a.xn,a.xq,a.jxjmc,a.jxjdm,a.drzw,a.xxjl,a.kycg,a.sqly,a.xysh yesNo,a.xxshyj,a.xyshyj,a.fdyyj,b.dcj,b.dcjpm,b.zcj,b.zcjpm,b.tcj,b.tcjpm,b.jnjf,b.jnjfpm,b.cpzf kpf,b.cpzfpm from view_xsjxjb a left join view_zhszcp b on a.xh=b.xh and a.xn=b.xn and a.xq=b.xq where " + pk + "= ?";
		}
//		//学生申请奖学金信息
		map = getMap(sql, new String[]{pkValue}, input);
//		
//		//学生综合素质测评信息
//		sql = "select dcj,dcjpm,zcj,zcjpm,tcj,tcjpm,jnjf,jnjfpm,cpzf kpf,cpzfpm from view_zhszcp where xh=? and xn=? and xq=?";
//		input = new String[]{"dcj","zcj","tcj","jnjf","kpf","dcjpm","zcjpm","tcjpm","jnjfpm","cpzfpm"};
//		map.putAll(getMap(sql, new String[]{map.get("xh"),map.get("xn"),map.get("xq")},input));
		return map;
	}
	
	/**
	 * 奖学金审核通过的人数
	 * @param pk
	 * @param pkValue
	 * @param userT
	 * @return int
	 * */
	public int getCountOfShtg( String xydm, String jxjdm, String nj){
		int count = 0;
		String result = "";
		result = getOneRs("select count(*) num from view_xsjxjb where xydm=? and xn=? and nd=? and jxjdm=? and nj=? and xysh='通过'", new String[]{xydm,Base.getJxjsqxn(), Base.getJxjsqnd(),jxjdm,nj}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		count = Integer.parseInt(result);
		return count;
	}
	
	/**
	 * 奖学金限制的人数
	 * @param pk
	 * @param pkValue
	 * @param userType
	 * @return int
	 * */
	public int getCountOfXz(String jxjdm, String xydm, String nj){
		int count = 0;
		String jxjrs = "";
		jxjrs = getOneRs("select jxjrs from xyjxjrs where jxjdm=? and xn=? and nd=? and bmdm=? and bmlb='xydm' and nj=?", new String[]{jxjdm,Base.getJxjsqxn(),Base.getJxjsqnd(),xydm,nj}, "jxjrs");
		jxjrs = jxjrs == null || "".equalsIgnoreCase(jxjrs) ? "0" : jxjrs;
		count = Integer.parseInt(jxjrs);
		return count;
	}

	/**
	 * 获取单位名称
	 * @param pk
	 * @param pkValue
	 * @param userType
	 * @return String
	 * */
	public String getShdw(String pk, String pkValue,String userType){
		String result = "";
		String sql = "select zymc from view_xsjxjb where " + pk + " = ?";
		if(userType.equalsIgnoreCase("xy")){
			result = getOneRs(sql, new String[]{pkValue}, "zymc");
		}else{
			sql = "select xymc from view_xsjxjb where " + pk + " = ?";
			result = getOneRs(sql, new String[]{pkValue}, "xymc");
		}
		return result;
	}
	
	/**
	 * 获取已经获得的奖学金
	 * @param xh
	 * @param xn
	 * @param nd
	 * @return String
	 * */
	public String getHdjxjmc(String xh,String xn,String nd){
		String result = "";
		String sql = "select jxjmc from view_xsjxjb where xh=? and xn=? and nd=? and xxsh='通过' and rownum=1";
		
		result = getOneRs(sql, new String[]{xh,xn,nd}, "jxjmc");
		return result;
	}
	
	/**
	 * 根据奖学金代码获取奖学金名称
	 * @param jxjdm
	 * @return String
	 * */
	public String getJxjmcByJxjdm(String jxjdm){
		String sql = "select jxjmc from jxjdmb where jxjdm=?";
		return getOneRs(sql, new String[]{jxjdm}, "jxjmc");
	}
	
	/**
	 * 获取学生的综合素质测评总分
	 * @param xh
	 * @param xn
	 * @param nd
	 * @return String
	 * */
	public String getZhszcpcj(String xh,String xn,String nd){
		String sql = "select cpzf kpf from view_zhszcp where xh=? and xn=? and nd=?";
		
		return getOneRs(sql, new String[]{xh,xn,nd}, "kpf");
	}
	
	/**
	 * 智育成绩是否达标
	 * @param xh
	 * @param xn
	 * @param nd
	 * @return boolean
	 * */
	public boolean Zcjdb(String xh,String xn,String xq){
		boolean flag = false;
		String sql = "select count(*)num from cjb where maxcj(cj,bkcj,cxcj)<85 and xh=? and xn=? and xq=?";
		String cj = getOneRs(sql, new String[] {xh,xn,xq}, "num");
		cj = cj == null || cj.equalsIgnoreCase("")? "0" : cj;
		
		if(Integer.parseInt(cj)>0){
			flag = false;
		}else{
			flag = true;
		}		
		return flag;
	}
	
	/**
	 * 体育成绩
	 * @param xh
	 * @param xn
	 * @param nd
	 *  @return String 
	 * */
	public String getTycj(String xh, String xn, String xq){
		String result = "";
		String sql = "select nvl(to_number(cj,999.99),0) tycj from cjb where  xh=? and kcmc like '%体育%' and xn=? and xq=?";
		result = getOneRs(sql, new String[]{xh,xn,xq}, "tycj");
		return result;
	}
	
	/**
	 * 获取德育成绩
	 * @param xh
	 * @param xn
	 * @param nd
	 * @return String
	 * */
	public String getDycj(String xh, String xn, String nd){
		String result = "";
		String sql = "select dcj from view_zhszcp where xh=? and xn=? and nd=?";
		result = getOneRs(sql, new String[]{xh,xn,nd}, "dcj");
		return result;
	}
	
	/**
	 * 判断必修课是否有一门不及格
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return boolean
	 * */
	public boolean checkBxk(String xh, String xn, String xq){
		boolean flag = false;
		String sql = "select nvl(count(*),0) bxzms from cjb where xh=? and (zpcj1='不及格' or zpcj1<'60' or zpcj1='不及') and kclx like '%必修%' and xn=? and xq=?";
		String result = getOneRs(sql, new String[]{xh,xn,xq}, "bxzms");
		flag = Integer.parseInt(result) >0 ? true : false;
		return flag;
	}
	
	/**
	 * 判断学年内是否有不及格成绩
	 * @param xh
	 * @param xn
	 * @return boolean
	 * */
	public boolean isFail(String xh, String xn){
		boolean flag = false;
		String sql = "select nvl(count(*),0) bxzms from cjb where xh=? and (zpcj1='不及格' or zpcj1<'60' or zpcj1='不及') and xn=?";
		String result = getOneRs(sql, new String[]{xh,xn}, "bxzms");
		flag = Integer.parseInt(result) >0 ? true : false;
		return flag;
	}
	
	/**
	 * 判断每学期是否均为奖学金获得者
	 * @param xh
	 * @return boolean
	 * */
	@SuppressWarnings("unchecked")
	public boolean priseEveryTerm(String xh){
		boolean flag = true;
		String nj = getStuDetails(xh).get("nj");
		String dqnd = Base.currNd;
		String dqxq = Base.currXq;
		String sql = "";
		String result = "";
		
		List xqList = Base.getXqList();
		
		int step = Integer.parseInt(dqnd) - Integer.parseInt(nj== null || "".equalsIgnoreCase(nj) ? "0" : nj);
		if(step>0){//非当前年度入学学生
			for(int i=0; i<step+1; i++){				
				for(int j=0; j<xqList.size(); j++){
					HashMap<String, String> map = (HashMap<String, String>) xqList.get(j);
					if(Integer.parseInt(nj) +i == Integer.parseInt(dqnd)){
						if(dqxq.equals("01")&& i==1){//当前年度第二学期不计算
							break;
						}
					}
					sql = "select count(*)num from view_xsjxjb where nd=? and xq=? and xh=? and xyshyj='通过' and xxshyj='通过'";
					result = getOneRs(sql, new String[]{String.valueOf(Integer.parseInt(nj) +i),map.get("xqdm"), xh}, "num");
					result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
					if(Integer.parseInt(result)<1){
						flag = false;
					}
				}
			}
		}else{//当前年度入学学生 或 年级为空或有误
			for(int i=0; i<xqList.size(); i++){
				if(dqxq.equalsIgnoreCase("01") && i==1){//当前年度第二学期不计算
					break;
				}else{
					sql = "select count(*) from view_xsjxjb where nd=? and xq=? and xh=? and xyshyj='通过' and xxshyj='通过'";
					result = getOneRs(sql, new String[]{dqnd,"01",xh}, "num");
					result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
					if(Integer.parseInt(result)<1){
						flag = false;
					}
				}
			}
		}
		return flag;
	}
	
	/**
	 * 判断综合素质测评成绩是否在全班前15%
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return boolean 
	 * */
	public boolean checkPm(String xh, String xn, String xq){
		boolean flag = false;
		String pm = "";
		String aqpm = "";
		String sql = "select dense_rank()over(partition by bjdm order by cpzf desc nulls last)pm from view_zhszcp  where xh=? and xn=? and xq=?";
		pm = getOneRs(sql, new String[]{xh,xn,xq}, "pm");
		pm = pm == null || "".equalsIgnoreCase(pm) ? "0" : pm;
		
		sql = "select count(1)*0.15 aqpm from view_zhszcp a where bjdm exists( select 1 from view_xsjbxx b where a.xh=b.xh and b.xh=?)";
		aqpm = getOneRs(sql, new String[]{xh}, "aqpm");
		aqpm = aqpm == null || "".equalsIgnoreCase(aqpm) ? "0" : aqpm;
		if(Integer.parseInt(pm)>Integer.parseInt(aqpm)){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 检测体育成绩是否达标
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return boolean
	 * */
	public boolean checkTycj(String xh, String xn, String xq){
		boolean flag = false;
		String sql = " select nvl(count(*),0) tycj from cjb where  xh=? and kcmc like '%体育%' and xn=? and xq=? and (zpcj1='不及格' or zpcj1<'60' or zpcj1='不及')";
		String result = getOneRs(sql, new String[]{xh,xn,xq}, "tycj");
		flag = Integer.parseInt(result)>0 ? true : false;
		return flag;
	}
	
	/**
	 * 获取学期平均分
	 * @param xn
	 * @param nd
	 * @param xq
	 * @return String
	 * */
	public String getPjcj(String xh,String xn, String xq){
		String sql = "select trunc(sum(zpcj1)/count(*),1) num from cjb where xh =? and xn=? and xq=?";
		String result = getOneRs(sql, new String[]{xh,xn,xq}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;		
		return result;
	}
	
	/**
	 * 获取荣誉称号审核信息
	 * @param PjpyZjsyzyForm model
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getRychshInfo(PjpyZjsyzyForm model){
		HashMap<String, String> map = new HashMap<String, String>();
		String[] input = {"xh","xm","xb","xymc","zymc","bjmc","nj","xn","nd","xq","rychdm","rychmc","yesNo"};
		String pk = model.getPk();
		String pkValue = model.getPkValue();
		String userType = model.getUserType();
		//学生荣誉称号信息
		String sql = "select xh,xm,xb,xymc,zymc,bjmc,nj,xn,nd,xq,rychdm,rychmc,xxsh yesNo from view_xsrychb where " + pk + " = ?";
		if(userType.equalsIgnoreCase("xy")){
			sql = "select xh,xm,xb,xymc,zymc,bjmc,nj,xn,nd,xq,rychdm,rychmc,xysh yesNo from view_xsrychb where " + pk + " = ?";
		}
		map = getMap(sql, new String[]{pkValue}, input);
		
		//学生综合素质测评信息
		input = new String[]{"dcj","zcj","tcj","jnjf"};
		sql = "select dcj,zcj,tcj,jnjf,cpzf from view_zhszcp where xh=? and xn=? and nd=?";
		map.putAll(getMap(sql, new String[]{map.get("xh"),map.get("xn"),map.get("nd")}, input));
		return map;
	}
	
	/**
	 * 计算限制人数
	 * @param xydm
	 * @return int
	 * */
	public int getRychXzrs(String xydm){
		int num = 0;
		String sql = "select round(count(*)*0.03)num from view_xsjbxx where xydm=?";
		String rs = getOneRs(sql, new String[]{xydm}, "num");
		
		rs = rs == null || rs.equalsIgnoreCase("") ? "0" : rs;
		
		num = Integer.parseInt(rs);		
		return num;
	}
	
	/**
	 * 计算荣誉称号审核通过人数
	 * @param xydm
	 * @param model
	 * @return int
	 * */
	public int getRychShtgrs(String xydm, PjpyZjsyzyForm model){
		int num = 0;
		String xn = model.getXn();
		String nd = model.getNd();
		String rychdm = model.getRychdm();
		String userType = model.getUserType();
		String sql = " select count(*) num from view_xsrychb where xn=? and nd=? and rychdm=? and xydm = ? and xxsh='通过'";
		if(userType != null && userType.equalsIgnoreCase("xy")){
			sql = " select count(*) num from view_xsrychb where xn=? and nd=? and rychdm=? and xydm=? and xysh='通过'";
		}
		String count = getOneRs(sql, new String[]{xn,nd,rychdm,xydm}, "num");
		count = count == null && count.equalsIgnoreCase("") ? "0" : count;
		num = Integer.parseInt(count);
		return num;
	}
	
	/**
	 * 获取所属加分项目列表
	 * @return list
	 * */
	public List getSsjfxmList(){		
		String[] tmpValue = {"德育成绩","智育成绩","体育成绩","技能加分"};		
		return arrayToList(tmpValue, tmpValue);
	}
	
	/**
	 * 获取操作类型列表
	 * @return List
	 * */
	public List getCzlxList(){
		String[] tmpColumn = {"加分","扣分"};
		return arrayToList(tmpColumn, tmpColumn);
	}
	
	/**
	 * 比赛项目模糊查询
	 * @param model
	 * @return List
	 * */
	public List querryBsxm(PjpyZjsyzyForm model,String tableName,String[] columns){
		String sql = "select * from " + tableName +" where 1=1";
		String ssjfxm = model.getSsjfxm();
		String czlx = DealString.toGBK(model.getCzlx());
		String userType = model.getUserType();
		
		if(userType != null && "xy".equalsIgnoreCase(userType)){
			sql += " and xydm='" + model.getUserDep() + "'";
		}
		if(ssjfxm!=null && !"".equalsIgnoreCase(ssjfxm)){
			sql += " and ssjfxm='" + ssjfxm + "'";
		}
		if(czlx != null && !"".equalsIgnoreCase(czlx)){
			sql += " and czlx='" + czlx + "'";
		}
		return rsToVator(sql, new String[]{}, columns);
	}
	
	/**
	 * 检测记录是否存在表中
	 * @param pk
	 * @param pkValue
	 * @param tableName
	 * @return boolean
	 * */
	public boolean checkExists(String pk,String pkValue,String tableName){
		String sql = "select count(*)num from " + tableName + " where " + pk + "=?";
		String result = getOneRs(sql, new String[]{pkValue}, "num");
		result = result ==null ||"".equalsIgnoreCase(result) ? "0" : result;
		
		return Integer.parseInt(result)>0 ? true : false;
	}
	
	/**
	 * 根据主键查询项目信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> querryBxsmInfo(String pk,String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select * from bsxmb where " + pk + " = ?";
		map = getMap(sql, new String[]{pkValue}, new String[]{"xmdm","xmmc","ssjfxm","czlx"});
		return map;
	}
	
	/**
	 * 根据主键查询项目信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> querryXyBxsmInfo(String pk,String pkValue){
		HashMap<String, String> map = new HashMap<String, String>();
		String sql = "select xmdm,xmjf,ssjfxm,(select czlx from bsxmb b where a.xmdm=b.xmdm)czlx from view_xybsxmjfxxb a where " + pk + " = ?";
		map = getMap(sql, new String[]{pkValue}, new String[]{"xmdm","xmjf","ssjfxm","czlx"});
		return map;
	}
	
	/**获取代码信息列表
	 * @param tableName
	 * @param columns
	 * @return List
	 * */
	public List getDmList(String tableName, String[] columns,String tj){
		String sql = "select distinct ";
		for(int i=0; i<columns.length;i++){
			if(i==columns.length-1){
				sql += columns[i];
			}else{
				sql += columns[i] + ",";
			}			
		}
		sql += " from " + tableName ;	
		if(tj != null && !"".equalsIgnoreCase(tj)){
			sql += tj;
		}
		if(tj == null || "".equalsIgnoreCase(tj)){
			sql += " where 1=1 ";
		}
		sql += " and xmdm<>'0001' and xmdm<>'0002' ";
		return getList(sql, new String[]{}, columns);
	}
	/////////////////////////////
	
	/**
	 * 查询学生素质积分维护信息
	 * @param model
	 * @return List
	 * @throws Exception
	 * */
	public List querrySzjf(PjpyZjsyzyForm model) throws Exception{
		String tableName = model.getTableName();
		int start = model.getPages().getStart();
		int pagesize = model.getPages().getPageSize();
		
		String countSql = "select a.*,a.xn||a.xq||a.xh||a.xmlbdm||a.sj 主键 from " + tableName + " a ";
		String sql = "select * from (select * from (select a.*,a.xn||a.xq||a.xh||a.xmlbdm||a.sj 主键, rownum r from " + tableName + " a ";
		String whereSql = getWhereSql(model).toString();//获取查询条件
		String[] outputValue = {"主键","xn","xq","xh","xm","bjmc","xmlbmc","ssjfxm","czlx","xmjf"};
		
		countSql = "select count(*) num from (" + countSql + whereSql + ")";
		String num = getOneRs(countSql, values != null ? values.toArray(new String[0]) : new String[] {}, "num");
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;
		
		sql = sql + whereSql + ") where r<=" + (start + pagesize) + ") where r>" + start;
		
		model.getPages().setMaxRecord(Integer.parseInt(num));		
		return rsToVator(sql, values != null ? values.toArray(new String[0]) : new String[] {}, outputValue);
	}
	
	/**
	 *  查询学生详细信息
	 *  @param xh
	 *  @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuDetails(String xh){
		String sql = "select * from view_xsxxb where xh=?";
		return getMap(sql, new String[]{xh}, new String[]{"xh","xm","xb","nj","xydm","xymc","zydm","zymc","bjdm","bjmc"});
	}
	
	/**
	 * 查询单个学生素质积分信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getSzjfInfo(String pkValue){
		pkValue = DealString.toGBK(pkValue);
		String[] outputValue = {"xh","xm","xb","xymc","zymc","bjmc","nj","xn","xq","jfxm","xmmc","zgdw",
				"xmlbdm","sj","bz","xmmc"};
		String sql = "select * from view_xsszjf where xn||xq||xh||xmlbdm||sj=?" ;
		
		return getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	/**
	 * 判断数据是否存在
	 * @param tablename
	 * @param pk
	 * @param pkValue
	 * @return boolean 
	 * */
	public boolean isExists(String tableName,String pk, String pkValue){
		String sql = "select count(*) num from " + tableName + " where " + pk + "=?";
		String result = getOneRs(sql, new String[]{pkValue}, "num");
		result = result == null || "".equalsIgnoreCase(result) ? "0" : result;
		
		return Integer.parseInt(result) >0 ? true : false;
	}
	
	/**
	 * 查询评奖评优黑名单信息
	 * @param model
	 * @return List
	 * */
	public List querryPjpyhmd(PjpyZjsyzyForm model) throws Exception{
		String tableName = "view_pjpyhmd";
		String sql = "select a.*,a.xh||a.xmmc 主键 from " + tableName + " a ";
		String whereSql = getWhereSql(model).toString();
		String[] outputValue = {"主键","xh","xm","bjmc","xmmc","kssj","jssj"};
		
		return rsToVator(sql + whereSql, values != null ? values.toArray(new String[0]) : new String[]{}, outputValue);
	}
	
	/**
	 * 查询单个学生的评奖评优黑名单记录信息
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getPjpyhmdInfo(String pkValue){
		pkValue = DealString.toGBK(pkValue);
		String tableName = "view_pjpyhmd";
		String pk = "xh||xmmc";
		String sql = "select * from " + tableName + " where " + pk + "=?";
		String[] outputValue = {"xh","xm","xb","nj","xymc","zymc","bjmc","xmmc","kssj","jssj","yy","bz"};
		
		return getMap(sql, new String[]{pkValue}, outputValue);		
	}
	
	/**
	 * 检测是否在黑名单中
	 * @param xh
	 * @param xmmc
	 * @return boolean
	 * */
	public boolean checkIsHmd(String xh,String xmmc){
		boolean flag = false;
		String sql = "select to_char(sysdate,'yyyy-mm-dd') time from dual";
		String time = getOneRs(sql, new String[]{}, "time");
		if(isExists("pjpyhmd", "xh||xmmc", xh+xmmc)){
			//存在判断是否还在限制的时间之内
			sql = "select kssj, jssj from pjpyhmd where xh=?";
			String[] values = getOneRs(sql, new String[]{xh}, new String[]{"kssj","jssj"});
			time = time.replaceAll("-", "");
			values[0] = values[0] == null || values[0].equalsIgnoreCase("") ? "0" : values[0].replaceAll("-", "");
			values[1] = values[1] == null || values[1].equalsIgnoreCase("") ? "0" : values[1].replaceAll("-", "");
			
			if(Integer.parseInt(values[0])<Integer.parseInt(time) 
					&& Integer.parseInt(values[1])>Integer.parseInt(time)){
				flag = false;
			}else{
				flag = true;
			}
		}else{
			flag = true;
		}		
		return flag;
	}	
	
	/**
	 * 获取学生奖学金信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getStuJxjInfo(String pk, String pkValue){
		String sql = "select xh,xn,nd,xq,xh,jxjdm,jxjmc,nj,xydm,xysh,xxsh from view_xsjxjb where " + pk + "=?";
		String[] columns = {"xh","xn","nd","xq","xh","jxjdm","jxjmc","nj","xydm","xysh","xxsh"};
		
		return getMap(sql, new String[]{pkValue},columns);
	}
	
	/**
	 * 四川建筑职业学院 评奖检查学生单科成绩，平均成绩是否符合申请条件
	 * @param xh
	 * @param jxjdm
	 * @param zdcj
	 * @param pjcj
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqTj(String jxjdm, String zdcj, String pjcj) throws Exception {
		String pjcjs = getOneRs("select tj from jxjhdtj where jxjdm=? and tjzdm='pjcj'",new String[]{jxjdm},"tj");
		String dkcjs = getOneRs("select tj from jxjhdtj where jxjdm=? and tjzdm='dkcj'",new String[]{jxjdm},"tj");
		double dPjcj = StringUtils.isNull(pjcjs) ? 0 : Double.parseDouble(pjcjs.trim());
		double dDkcj = StringUtils.isNull(dkcjs) ? 0 : Double.parseDouble(dkcjs.trim());
		double tZdcj = StringUtils.isNull(zdcj) ? 0 : Double.parseDouble(zdcj.trim());
		double tPjcj = StringUtils.isNull(pjcj) ? 0 : Double.parseDouble(pjcj.trim());
		if ((tZdcj>=dDkcj) && (tPjcj>=dPjcj)) {
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * 中国矿业大学 检查学院素质测评是否已汇总
	 * @param xn
	 * @param xydm
	 * @return
	 * @throws Exception
	 */
	public boolean chkxySzrs(String xn, String xydm) throws Exception {
		boolean bFlag = false;
		String num = getOneRs(
				"select count(*) num from view_zgkd_zhszcp where xn=? and xydm=?",
				new String[] { xn, xydm }, "num");
		num = StringUtils.isNull(num) ? "0" : num.trim();
		if (!"0".equalsIgnoreCase(num)) {
			return true;
		}
		return bFlag;
	}
	
	/**
	 * 上海出版印刷检查学生申请奖学金条件
	 * @param xh
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String chkjxjSqtj(String xh, String jxjdm, String jxjlb) throws Exception {
		jxjlb = StringUtils.isNull(jxjlb) ? getOneRs("select jxjlb from jxjdmb where jxjdm=?", new String[]{jxjdm}, "jxjlb") : jxjlb;
		String bFlag = "";
		boolean bChk = chkSqjxjExists(xh, jxjdm, jxjlb);//是否有兼得除其它类以外的奖学金
		if ("023".equalsIgnoreCase(jxjdm)) {//优秀作品奖可以多次申请
			return "";
		}
		if (bChk) {
			String jxjmc = getOneRs("select jxjmc from jxjdmb where jxjdm=?", new String[]{jxjdm}, "jxjmc");
			jxjmc = StringUtils.isNull(jxjmc) ? "" : jxjmc.trim();
			String nj = getOneRs(
					"select to_number(to_number(xz) + to_number(nj) - to_number('"
							+ Base.getJxjsqnd()
							+ "')) nj from view_xsjbxx where xh=?",
					new String[] { xh }, "nj");
			int iNj = StringUtils.isNull(nj) ? 0 : Integer.parseInt(nj.trim());
			
			if (iNj<=0) {//三年级学生只能申请学业奖学金   三年级学生
				if (jxjmc.indexOf("学业") != -1) {
					return "";
				} else if ("020".equalsIgnoreCase(jxjdm)
						|| "021".equalsIgnoreCase(jxjdm)
						|| "022".equalsIgnoreCase(jxjdm)
						|| "019".equalsIgnoreCase(jxjdm)) {//其它类奖学金三年级可以申请
					return "";
				} else if (jxjmc.indexOf("优秀") != -1) {//优秀作品奖艺术系三年级学生可以申请
					String xymc = getOneRs("select xymc from view_xsjbxx where xh=?", new String[]{xh}, "xymc");
					if (xymc.indexOf("艺术") != -1) {
						bFlag = "";
					} else {
						bFlag = "ys";
					}
				} else {
					if (iNj<0) {
						return "by";
					}
					bFlag = "xy";
				}
			} else {//该生是一、二年级学生可以申请奖学金  一,二年级学生
				if (jxjmc.indexOf("学业") != -1) {
					bFlag = "sxy";
				} else if (jxjmc.indexOf("优秀") != -1) {//优秀作品奖只能艺术系申请
					String xymc = getOneRs("select xymc from view_xsjbxx where xh=?", new String[]{xh}, "xymc");
					if (xymc.indexOf("艺术") != -1) {
						bFlag = "";
					} else {
						bFlag = "ys";
					}
				} else {
					bFlag = "";
				}
			}
		} else {//兼得同类奖学金
			bFlag = "jd";
		}
		return bFlag;
	}
	
	/**
	 * 上海出版印刷申请时检查是否有申请除其它类的奖学金，如果有返回FALSE，不能再申请.
	 * @param xh
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public boolean chkSqjxjExists(String xh, String jxjdm, String jxjlb) throws Exception {
		ArrayList<String[]> rs = rsToVator(
				"select b.jxjlb from xsjxjb a left join jxjdmb b on a.jxjdm=b.jxjdm where xh=? and xn=? and xq=?",
				new String[] { xh, Base.getJxjsqxn(), Base.getJxjsqxq() },
				new String[] { "jxjlb" });
		if (rs != null && rs.size()>0) {
			for (int i=0;i<rs.size();i++) {
				String[] tmp = rs.get(i);
				if (tmp != null && tmp.length>0) {
					if (!"005".equalsIgnoreCase(tmp[0]) && !"005".equalsIgnoreCase(jxjlb)) {
						return false;
					}else {
						continue;
					}
				} else {
					continue;
				}
			}
			return true;
		} else {
			return true;
		}
	}
	
	/**
	 * 计算学院调整人数后的奖学金金额四川建筑学院用
	 * @param pk
	 * @param tzrs
	 * @param jxjdm
	 * @param userDep
	 * @return
	 */
	public String xyJxjtzje(String pk, String tzrs, String jxjdm, String userDep) {
		tzrs = StringUtils.isNull(tzrs) ? "0" : tzrs.trim();
		String tzje = getOneRs(
				"select sum(to_number(rstz*(select b.jlje from jxjdmb b where a.jxjdm=b.jxjdm))) jsje from xyjxjfpb a where xn=? and nd=? and bmdm=? and jxjdm=? and xn||nd||nj||fpbm||jxjdm!=?",
				new String[] {Base.getJxjsqxn(),Base.getJxjsqnd(),userDep,jxjdm,pk}, "jsje");
		String tzjeTmp = getOneRs("select "+tzrs+"*jlje je from jxjdmb where jxjdm=?", new String[]{jxjdm}, "je");
		double iTzje = !StringUtils.isNull(tzje) ? Integer.parseInt(tzje) : 0;
		double iTzjeTmp = !StringUtils.isNull(tzjeTmp) ? Integer.parseInt(tzjeTmp) : 0;
		return (iTzje+iTzjeTmp) + "";
	}
	
	/**
	 * 华夏学院奖学金申请检查
	 * @param xh
	 * @return
	 */
	public boolean chksqtj(String xh) {
		String xn = Base.getJxjsqxn();
		String wjnum = getOneRs("select count(*) num from wjcfb where xh=? and xn=? and xxsh='通过'",
				new String[] { xh, xn }, "num");
		if (!StringUtils.isNull(wjnum) && !"0".equalsIgnoreCase(wjnum)) {
			return false;
		}
		String cjnum =  getOneRs("select count(*) num from cjb where zpcj2<60 and xh=? and xn=? and kclx like '%必修%' and kcmc not like '%体育%'",
				new String[] { xh, xn }, "num");
		if (!StringUtils.isNull(cjnum) && !"0".equalsIgnoreCase(cjnum)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 根据奖学金代码查询类别和金额
	 * @param jxjdm
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> queryJxjInfo(String jxjdm){
		String sql = "select jxjlb,jlje from jxjdmb where jxjdm=?";
		String[] outputValue = {"jxjlb","jlje"};
		HashMap<String, String> map = new HashMap<String, String>();
		map = getMap(sql, new String[]{jxjdm}, outputValue);
		
		map.put("jxjlb", map.get("jxjlb")== null ? "" : map.get("jxjlb"));
		map.put("jlje", map.get("jlje")== null ? "" : map.get("jlje"));
		
		return map;
	}
	
	/**
	 * 查询学生奖学金申请信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * @throws Exception 
	 * */
	public HashMap<String, String> selectJxjsqOne(String pk, String pkValue) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		boolean nfhdtdjxj = false;
		String sql = "select a.*,(select xqmc from xqdzb b where a.xq=b.xqdm) xqmc,(select cpzfpm from view_zhszcp b where a.xh=b.xh and a.nd=b.nd and a.xn=b.xn)cpzfpm, jxjdm xmdm from view_xsjxjb a  where " + pk + "=?";
		String[] outputValue = {"xh","xm","xb","nj","xymc","zymc","bjmc","xn","nd","xq","xqmc","jxjdm","jxjlb","jlje","drzw","xxjl","kycg","sqly","cpzfpm","xmdm"};
		
		map = getMap(sql, new String[]{pkValue}, outputValue);
		
		if(map != null && map.size()>0){
			nfhdtdjxj = nfhdtdjxj(map.get("xh"), map.get("xn"),map.get("nd"), map.get("xq"));
			
			map.put("cpcppm", getCpcppm(map.get("xh"), map.get("xn"), map.get("nd"), map.get("xq")));
			map.put("nfhdtdjxj", nfhdtdjxj == true ? "是 " : "否");
		}
	    return 	map;		
	}
	
	/**
	 * 查询学生在评奖学年的违纪处分信息 返回学年,学期,处分类别名称,处分原因名称
	 * 
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuWjcfxx(String xh, String xn, String xq)
			throws Exception {
		return rsToVator(
				"select xn,(select b.xqmc from xqdzb b where a.xq=b.xqdm) xq,cflbmc,cfyymc from view_wjcf a where xh=? and xn=? and xq=? and xxsh='通过'",
				new String[] { xh, xn, xq }, new String[] { "xn", "xq",
						"cflbmc", "cfyymc" });
	}
	
	/**
	 * 查询学生在评奖学年的违纪处分信息 返回违纪记录数
	 * 
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public int getStuWjcfxxCount(String xh, String xn, String xq)
			throws Exception {
		return rsToVator(
				"select xn,xq,cflbmc,cfyymc from view_wjcf where xh=? and xn=? and xq=? and xxsh='通过'",
				new String[] { xh, xn, xq }, new String[] { "xn", "xq",
						"cflbmc", "cfyymc" }).size();
	}

	/**
	 * 查询学生在评奖学年的学生成绩 返回:不及格显示背景色,课程名称,课程类型,成绩
	 * 
	 * @param jwbb
	 *            is true 则查询 过渡版成绩表反之学分版成绩表
	 * @param xh
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getStuKccjxx(boolean jwbb,String xh, String xn, String xq) throws Exception {
		if (jwbb) {//过渡版成绩表
			return rsToVator("select (case when cj<60 then '#DDDDDD' else '#FFFFFF' end) dis,kcmc,kcxz,cj from view_cjb where xh=? and xn=? and xq=?", new String[]{xh, xn, xq}, new String[]{"dis","kcmc", "kcxz", "cj"});
		} else {//学分版成绩表
			return rsToVator("select (case when zpcj2<60 then '#DDDDDD' else '#FFFFFF' end) dis,kcmc,kclx,zpcj2 from view_cjb where xh=? and xn=? and xq=?", new String[]{xh, xn, xq}, new String[]{"dis","kcmc", "kclx", "zpcj2"});
		}
	}
	
	/**
	 * 查询学生基本信息和当前奖学金申请学年年度学期信息
	 * @param xh
	 * @return HashMap<String, String> 
	 * @throws Exception 
	 * */
	public HashMap<String, String> selectStuBase(String xh) throws Exception{
		String sql = "select * from view_xsxxb where xh=?";
		HashMap<String, String> map = new HashMap<String, String>();
		String[] outputValue = {"xh","xm","xb","nj","xymc","zymc","bjmc"};
		String cpcppm = "";
		boolean  nfhdtdjxj = false;
		
		map = getMap(sql, new String[]{xh}, outputValue);
		map.put("xn", Base.getJxjsqxn());
		map.put("xq", Base.getJxjsqxq());
		map.put("xqmc", Base.getJxjsqxqmc());
		map.put("nd", Base.getJxjsqnd());
		
		sql = "select cpzfpm from view_zhszcp where xh=? and xn=? and nd=?";
		String cpzfpm = getOneRs(sql, new String[]{xh,map.get("xn"),map.get("nd")}, "cpzfpm");
		
		nfhdtdjxj = nfhdtdjxj(map.get("xh"),map.get("xn"), map.get("nd"), map.get("xq"));
		cpcppm = getCpcppm(map.get("xh"), map.get("xn"), map.get("nd"), map.get("xq"));
		
		map.put("cpzfpm", cpzfpm);
		map.put("cpcppm", cpcppm);
		if(map.get("xh") != null && !"".equalsIgnoreCase(map.get("xh"))){
			map.put("nfhdtdjxj", nfhdtdjxj == true ? "能" : "否");
		}
		return map;
	}
	
	/**
	 * 获取测评参评排名
	 * @param xh
	 * @param xn
	 * @param nd
	 * @param xq
	 * @return String
	 * @throws Exception 
	 * */
	public String getCpcppm(String xh,String xn,String nd,String xq) throws Exception{
		List<HashMap<String, String>> list = getJxjPassList("特等奖学金", xh, nd, xn, xq);
		String xhArray = "";
		String sql = "";
		
		if(list != null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				HashMap<String, String> map = new HashMap<String, String>();
				map = list.get(i);
				if(i==list.size()-1){
					xhArray += "'" + map.get("xh") + "'";
				}else{
					xhArray += "'" + map.get("xh") + "',";
				}
			}
		}
		
		if(xhArray != null && !"".equalsIgnoreCase(xhArray)){
			sql = "select * from (select xh, dense_rank() over (partition by a.bjdm order by a.kpf desc nulls last)cpcppm from (select xh,kpf,bjdm from view_zhszcp where xn=? and nd=? and xq=? and xh not in (" + xhArray + ")order by ZHSZCPZFPM desc )a )where xh=?";
		}else{
			sql = "select xh, cpzfpm cpcppm from view_zhszcp where xn=? and nd=? and xq=? and xh=?";
		}
		
		return getOneRs(sql, new String[]{xn,nd,xq,xh}, "cpcppm");
	}
	
	/**
	 * 判断能否获得特等奖学金
	 * @param xh
	 * @param xn
	 * @param nd
	 * @param xq
	 * @throws Exception 
	 * */
	public boolean nfhdtdjxj(String xh,String xn, String nd, String xq) throws Exception{
		boolean result = false;
		String tdjxjmc = "特等奖学金";
		String tdjxjdm = getOneRs("select jxjdm from jxjdmb where jxjmc='特等奖学金'", new String[]{}, "jxjdm");
		
		result = pjpyTj_validate(tdjxjdm, tdjxjmc, xh, nd, xn, xq);
		return result;
	}
	
	/**
	 * 	查询学生所在班级综合素质成绩及排名信息
	 * @param xh
	 * @return
	 */
	public List<String[]> getBjZhszcpinfo(String xh,String xn,String xq) {
		return rsToVator("select a.*,rownum from (select xn,xq,xh,xm,dcj,dcjpm,zcj,zcjpm,tcj,tcjpm,jnjf,jnjfpm,cpzf," +
				"cpzfpm from view_zhszcp where bjdm=(select distinct bjdm from " +
				"view_xsjbxx where xh=?) and xn=? and xq=? order by cpzfpm,dcjpm,zcjpm,tcjpm) a",
				 new String[]{xh,xn,xq}, new String[]{"rownum","xn", "xq", "xh", "xm", "dcj", "dcjpm", "zcj", "zcjpm", "tcj", "tcjpm","jnjf","jnjfpm", "cpzf", "cpzfpm"});
	}
	
	public String getStubjmc(String xh) {
		return getOneRs("select bjmc from view_xsjbxx where xh=?", new String[]{xh}, "bjmc");
	}
		
	/**
	 * 浙江商业职业技术学院评奖评优条件验证 验证单个学生
	 * @param jxjdm奖学金代码
	 * @param jxjmc 奖学金名
	 * @param xh 学号
	 * @param nd 年度
	 * @param xn 学年
	 * @param xq 学期
	 * @return Boolean 
	 * @throws Exception 
	 */
	public boolean pjpyTj_validate(String jxjdm,String jxjmc,String xh,String nd,String xn,String xq) throws Exception{
		DAO dao = DAO.getInstance();
		boolean result = true;
		List<HashMap<String, String>> Conditions = dao.getList("select tjzdm || tj || zdbj tj from jxjhdtj where tjzdm<>'cpzfpm' and jxjdm='"+jxjdm+"' and xn='"+xn+"' ",new String[]{},new String[]{"tj"});
		String whereSql = "";
		
		if(Conditions != null && Conditions.size()>0){			
			for(int i=0; i< Conditions.size();i++){
				HashMap<String, String> tj = (HashMap<String, String>)Conditions.get(i);
				whereSql += " and " +  tj.get("tj");
			}
		}
		if(jxjmc != null && !"特等奖学金".equalsIgnoreCase(jxjmc)){
			//综合测评排名比例
			String cpzfpmbl = dao.getOneRs("select zdbj from jxjhdtj where tjzdm='cpzfpm' and jxjdm='"+jxjdm+"' and xn='"+xn+"' ",new String[]{},"zdbj");
			if(cpzfpmbl != null && !"".equalsIgnoreCase(cpzfpmbl)){
				String bjzpm = dao.getOneRs("select max(cpzfpm) bjzpm from view_zhszcp a where exists(select 1 from view_xsjbxx b where a.bjdm=b.bjdm and b.xh=?)", new String[]{xh}, "bjzpm");
				bjzpm = bjzpm == null || "".equalsIgnoreCase(bjzpm) ? "0" : bjzpm;
				String cppm = getCpcppm(xh,xn,nd,xq);
				if(cppm != null && !"".equalsIgnoreCase(cppm)){
					int bcprs = getJxjPassList("特等奖学金",xh,nd,xn,xq).size();
					String zrs = dao.getOneRs("select count(*) num from view_xsjbxx a where exists(select 1 from view_xsjbxx b where a.bjdm=b.bjdm and b.xh=?)",new String[]{xh},"num");
					zrs = zrs == null || zrs.equalsIgnoreCase("") ? "0" : zrs;
					int cprs = Integer.parseInt(zrs) - bcprs;
					whereSql += " and "+Integer.parseInt(cppm)+"<=" + Math.round((cprs*Double.parseDouble(cpzfpmbl)/100));
				}
			}
		}
		
		if("特等奖学金".equalsIgnoreCase(jxjmc) || "特等奖".equalsIgnoreCase(jxjmc)){
			//住宿卫生分数达标验证
			String gywsjcf = dao.getOneRs(" select count(*) gywsnum from gywsjcb a where exists(select 1 from view_xsjbxx b where a.ssbh=b.ssbh and b.xh=?) and xn=? and xq=? and fs<60 ", new String[]{xh,xn,xq},"gywsnum");
			gywsjcf = gywsjcf == null || "".equalsIgnoreCase(gywsjcf) ? "0" : gywsjcf;
			if(Integer.parseInt(gywsjcf)>0){
				result = false;
			}
			//违纪情况达标验证
			int wjcfnum =getStuWjcfxxCount(xh, xn, xq);
			if(wjcfnum>0){
				result = false;
			}
			//各科成绩均在85分以上
			if(!checkKccj(xh,xn,xq)){
				result = false;
			}
		}		
		//综合测评达标验证
		if(result){ 
			String num = dao.getOneRs("select  count(*) num from view_zhszcp where xh=? and xn=? and xq=? and nd=? "+whereSql,new String[]{xh,xn,xq,nd},"num");
			if(Integer.parseInt(num)<=0){
				result =  false;
			}
		}
		return result;
	}
	
	/**
	 * 判断各科成绩是否均在85分以上
	 * @param xh
	 * @param xn
	 * @return boolean
	 * */
	public boolean checkKccj(String xh, String xn,String xq){
		String sql = "select count(*)num from view_cjb where zpcj2<85 and xh=? and xn=? and xq=?";
		String num = getOneRs(sql, new String[]{xh,xn,xq}, "num");
		num = num == null || "".equalsIgnoreCase(num) ? "0" : num;
		
		sql = "select count(*)num from view_cjb where xh=? and xn=? and xq=?";
		String count = getOneRs(sql,new String[]{xh,xn,xq},"num");
		count = count == null || "".equalsIgnoreCase(count) ? "0" : count;
		return Integer.parseInt(num) >0 || Integer.parseInt(count)<1 ? false : true;
	}
	/**
	 * 验证班级学生
	 * @param jxjmc 奖学金名称
	 * @param xh 学号
	 * @param nd 年度
	 * @param xn 学年
	 * @param xq 学期
	 * @return 返回该生所在班级符合该项奖学金要求的学生列表
	 * @throws Exception 
	 */
	public List<HashMap<String,String>> getJxjPassList(String jxjmc,String xh,String nd,String xn,String xq) throws Exception{
		DAO dao = DAO.getInstance();
		ArrayList<HashMap<String, String>> jxjXsList = new ArrayList<HashMap<String, String>>();
		String jxjdm = dao.getOneRs(" select jxjdm from jxjdmb where jxjmc=? and rownum=1 ",new String[]{jxjmc},"jxjdm");
		StringBuffer sql = new StringBuffer();
		boolean flag = false;		
		List<HashMap<String, String>> Conditions = dao.getList("select tjzdm || tj || zdbj tj from jxjhdtj where tjzdm<>'cpzfpm' and jxjdm='"+jxjdm+"' and xn='"+xn+"' ",new String[]{},new String[]{"tj"});
		String whereSql = "";
		
		if(Conditions != null && Conditions.size()>0){			
			for(int i=0; i< Conditions.size();i++){
				HashMap<String, String> tj = (HashMap<String, String>)Conditions.get(i);
				whereSql += " and " +  tj.get("tj");
			}
		}

		sql.append("select (select count(xh) num from view_zhszcp where xh=a.xh and xn=a.xn and xq=a.xq and nd=a.nd "+whereSql+" )zhcpnum, ");
		sql.append("(select count(xh) from wjcfb where xh=a.xh and xn=a.xn and xq=a.xq and xxsh='通过')wjcfnum, ");
		sql.append("(select count(ssbh) gywsnum from gywsjcb b where xh=a.xh and xn=a.xn and xq=a.xq and fs<60)gywssum, ");
		sql.append(" nd,xn,xq,xh from(select ? nd,? xn,? xq,xh from view_xsjbxx a where bjdm=(select bjdm from view_xsjbxx where xh=?))a "); 	    
		
		List<HashMap<String, String>>  xsL = dao.getList(sql.toString(),new String[]{nd,xn,xq,xh},new String[]{"zhcpnum","wjcfnum","gywssum","xh"});
		if(xsL != null && xsL.size()>0){
			for(int i=0;i<xsL.size();i++){
				flag = true;
				boolean tdjxjtj = false;
				boolean wjcf = false;
				boolean zhcp = false;
				HashMap<String,String> map = new HashMap<String,String>();
				HashMap<String, String> xsMap = xsL.get(i);
				if("特等奖学金".equalsIgnoreCase(jxjmc)){
					//公寓达标判断
					if(Integer.parseInt(xsMap.get("gywssum"))<=0){
						tdjxjtj = true;
					}
					//是否有违纪信息
					if(Integer.parseInt(xsMap.get("wjcfnum"))<=0){
						wjcf = true;
					}
					//各科成绩均在85分以上
					if(!checkKccj(xsMap.get("xh"),xn,xq)){
						flag = false;
					}
				}		
				
				if(Integer.parseInt(xsMap.get("zhcpnum"))>0){
					zhcp =true;
				}
				if(zhcp && wjcf && tdjxjtj && flag){
					flag = true;
				}else{
					flag = false;
				}
				
	            if(flag){
	            	map.put("xh",xsMap.get("xh"));
	            }
	            if(!(map == null || map.size()<1)){
	            	jxjXsList.add(map);
	            }
			}
		}
		return jxjXsList;
	}
	
	/**
	 * 获取学院积分项目列表
	 * @param xydm
	 * @return List
	 * */
	public List getBxsmListByXy(String xydm){
		//0001和0002为默认的成绩减分项目，不能修改，不能加入到素质积分维护中提供给用户维护
		String sql = "select xmdm,(select distinct xmmc from bsxmb b where a.xmdm=b.xmdm) xmmc from (select distinct xmdm from xybsxmjfxxb where xydm=? and xmdm<>'0001' and xmdm<>'0002') a";
		String[] outputValue = {"xmdm","xmmc"};
		return getList(sql, new String[]{xydm}, outputValue);
	}
	
	/**
	 * dwr 获取操作类型
	 * @param xmdm
	 * @return String
	 * */
	public String changeCzlx(String xmdm){
		String czlx = "";
		czlx = getOneRs("select czlx from bsxmb where xmdm=?", new String[]{xmdm},"czlx");
		czlx = czlx == null ? "" : czlx;		
		return czlx;
	}
}
