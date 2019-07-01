/**
 * @部门:学工产品事业部
 * @日期：2014-2-14 上午09:55:53 
 */  
package xgxt.szdw.bjlh.fdykh;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.szdw.bjlh.SzkhCssz;
import xgxt.szdw.bjlh.cssz.BjlhCsszDAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 辅导员考核统计模块
 * @类功能描述: 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-2-14 上午09:55:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjlhFdykhNewDao extends SuperDAOImpl<BjlhFdykhForm> {

	/**
	 * 
	 * @描述:获取统计明细
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-19 上午09:20:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public Object[] getFdyKhMx(BjlhFdykhForm myForm , User user) throws Exception{
		
		List<HashMap<String , String>> xsmxList = null; //学生明细
		
		List<HashMap<String , String>> tmmxList = null; //题目明细
		
		String xsmxPjf = "";//学生明细平均分
		
		String tmmxZf = "";//题目明细总分
		
		DAO dao = DAO.getInstance();
		
		String sql = ""; 
		
		String tjlx = myForm.getTjlx();
		
		if(StringUtils.isEqual(BjlhFdykhAction.B_RS, tjlx)){
			sql = "select a.* from (select rownum r , a.* ,v.xm from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm, " +
				"       sum(case " +
				"             when t_4.pflx = '减分' then" + 
				"              to_char(- (to_number(t_3.fz))) " + 
				"             else	" + 
				"              t_3.fz	" + 
				"           end) fz	" + 
				"  from xg_szdw_bjlh_fdykhpfb t_3, " + 
				"       xg_szdw_bjlh_fdykhxmb t_4, " + 
				"       xg_szdw_bjlh_fdykhb   t_5, " + 
				"       view_xsxxb            v_1	" + 
				" where t_3.khbid = t_4.khbid " + 
				"   and t_3.xmid = t_4.xmid " + 
				"   and t_4.khbid = t_5.khbid " + 
				"   and t_5.pfdx = 'xs' " + 
				"   and t_3.yhm = v_1.xh " + 
				" group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm) a left join view_xsxxb v on a.yhm = v.xh where a.fdyid = ? and xn = ?" +
				"" +
				") a where 1=1 ";
			xsmxList = dao.getListNotOut(sql, new String[]{myForm.getFdyid() , myForm.getXn()});
			
			sql = " select rownum r , a.* from (  " + 
				" select  t3.xn , t1.xmid , t1.khbid,t1.fdyid , t2.khnr , round(avg(fz),2) xspjf from xg_szdw_bjlh_fdykhpfb t1 left join " + 
				"      xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join  " + 
				"      xg_szdw_bjlh_fdykhb t3   on t2.khbid = t3.khbid where t3.pfdx = 'xs' " + 
				"      group by t3.xn ,t1.khbid, t1.xmid , t1.fdyid , t2.khnr) a where a.fdyid = ? and a.xn = ? ";
			
			tmmxList = dao.getListNotOut(sql, new String[]{myForm.getFdyid() , myForm.getXn()});
			
			sql = "select round(avg(a.fz),2) xspjf from (select rownum r , a.* ,v.xm from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm, " +
				"       sum(case " +
				"             when t_4.pflx = '减分' then" + 
				"              to_char(- (to_number(t_3.fz))) " + 
				"             else	" + 
				"              t_3.fz	" + 
				"           end) fz	" + 
				"  from xg_szdw_bjlh_fdykhpfb t_3, " + 
				"       xg_szdw_bjlh_fdykhxmb t_4, " + 
				"       xg_szdw_bjlh_fdykhb   t_5, " + 
				"       view_xsxxb            v_1	" + 
				" where t_3.khbid = t_4.khbid " + 
				"   and t_3.xmid = t_4.xmid " + 
				"   and t_4.khbid = t_5.khbid " + 
				"   and t_5.pfdx = 'xs' " + 
				"   and t_3.yhm = v_1.xh " + 
				" group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm) a left join view_xsxxb v on a.yhm = v.xh where a.fdyid = ? and xn = ?) a where 1=1 ";
			
			xsmxPjf = dao.getOneRs(sql, new String[]{myForm.getFdyid() , myForm.getXn()} , "xspjf");
			
			sql ="select sum(case when b.pflx = '减分' then to_char(- (to_number(b.xspjf))) else to_char(b.xspjf) end) zf from ( " + 
				" select rownum r , a.* from (  " + 
				" select  t3.xn , t1.xmid , t2.pflx ,t1.khbid,t1.fdyid , t2.khnr , round(avg(fz),2) xspjf from xg_szdw_bjlh_fdykhpfb t1 left join " + 
				"      xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join  " + 
				"      xg_szdw_bjlh_fdykhb t3   on t2.khbid = t3.khbid where t3.pfdx = 'xs' " + 
				"      group by t3.xn ,t1.khbid,t2.pflx, t1.xmid , t1.fdyid , t2.khnr) a where a.fdyid = ? and a.xn = ? ) b";
			
			
			tmmxZf = dao.getOneRs(sql, new String[]{myForm.getFdyid() , myForm.getXn()} , "zf");
			
		}else if(StringUtils.isEqual(BjlhFdykhAction.B_BJ, tjlx)){
			sql = "select a.* from (select rownum r , a.* ,v.xm , v.bjdm from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm, " +
			"       sum(case " +
			"             when t_4.pflx = '减分' then" + 
			"              to_char(- (to_number(t_3.fz))) " + 
			"             else	" + 
			"              t_3.fz	" + 
			"           end) fz	" + 
			"  from xg_szdw_bjlh_fdykhpfb t_3, " + 
			"       xg_szdw_bjlh_fdykhxmb t_4, " + 
			"       xg_szdw_bjlh_fdykhb   t_5, " + 
			"       view_xsxxb            v_1	" + 
			" where t_3.khbid = t_4.khbid " + 
			"   and t_3.xmid = t_4.xmid " + 
			"   and t_4.khbid = t_5.khbid " + 
			"   and t_5.pfdx = 'xs' " + 
			"   and t_3.yhm = v_1.xh " + 
			" group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm) a left join view_xsxxb v on a.yhm = v.xh where a.fdyid = ? and a.xn = ? and v.bjdm = ?" +
			"" +
			") a where 1=1 ";
			
			xsmxList = dao.getListNotOut(sql, new String[]{myForm.getFdyid() , myForm.getXn() , myForm.getBjdm()});
			
			sql = " select rownum r , a.* from (  " + 
			" select  t3.xn , t1.xmid  ,t1.khbid,t1.fdyid , t2.khnr , round(avg(fz),2) xspjf ,v.bjdm from xg_szdw_bjlh_fdykhpfb t1 left join  " + 
			"      xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join   " + 
			"      xg_szdw_bjlh_fdykhb t3   on t2.khbid = t3.khbid left join view_xsxxb v on t1.yhm = v.xh where t3.pfdx = 'xs' " + 
			"      group by t3.xn ,t1.khbid, t1.xmid , t1.fdyid , t2.khnr , v.bjdm) a where  a.bjdm = ? and a.fdyid = ? and a.xn = ? ";
		
			tmmxList = dao.getListNotOut(sql, new String[]{myForm.getBjdm(), myForm.getFdyid() , myForm.getXn()});
			
			
			sql = "select round(avg(a.fz),2) xspjf from (select rownum r , a.* ,v.xm from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm, " +
			"       sum(case " +
			"             when t_4.pflx = '减分' then" + 
			"              to_char(- (to_number(t_3.fz))) " + 
			"             else	" + 
			"              t_3.fz	" + 
			"           end) fz	" + 
			"  from xg_szdw_bjlh_fdykhpfb t_3, " + 
			"       xg_szdw_bjlh_fdykhxmb t_4, " + 
			"       xg_szdw_bjlh_fdykhb   t_5, " + 
			"       view_xsxxb            v_1	" + 
			" where t_3.khbid = t_4.khbid " + 
			"   and t_3.xmid = t_4.xmid " + 
			"   and t_4.khbid = t_5.khbid " + 
			"   and t_5.pfdx = 'xs' " + 
			"   and t_3.yhm = v_1.xh " + 
			" group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm) a left join view_xsxxb v on a.yhm = v.xh where a.fdyid = ? and a.xn = ? and v.bjdm = ?" +
			"" +
			") a where 1=1 ";
			
			xsmxPjf = dao.getOneRs(sql, new String[]{myForm.getFdyid() , myForm.getXn(),myForm.getBjdm()} , "xspjf");
						
			sql = " select sum(case when a.pflx = '减分' then to_char(- (to_number(a.xspjf))) else to_char(a.xspjf) end) zf from (  " + 
			" select  t3.xn , t1.xmid  ,t1.khbid,t1.fdyid , t2.pflx, t2.khnr , round(avg(fz),2) xspjf ,v.bjdm from xg_szdw_bjlh_fdykhpfb t1 left join  " + 
			"      xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join   " + 
			"      xg_szdw_bjlh_fdykhb t3   on t2.khbid = t3.khbid left join view_xsxxb v on t1.yhm = v.xh where t3.pfdx = 'xs' " + 
			"      group by t3.xn ,t1.khbid, t1.xmid , t1.fdyid , t2.khnr , v.bjdm,t2.pflx) a where  a.bjdm = ? and a.fdyid = ? and a.xn = ? ";
		
		
			
			tmmxZf = dao.getOneRs(sql, new String[]{myForm.getBjdm() , myForm.getFdyid() , myForm.getXn()} , "zf");
		}else if(StringUtils.isEqual(BjlhFdykhAction.B_ZZ, tjlx)){
			sql = "select a.*, b.zzlbmc from  (select t1.* , t2.bjdm ,t2.xm from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm, " +
			"       sum(case " +
			"             when t_4.pflx = '减分' then" + 
			"              to_char(- (to_number(t_3.fz))) " + 
			"             else	" + 
			"              t_3.fz	" + 
			"           end) fz	" + 
			"  from xg_szdw_bjlh_fdykhpfb t_3, " + 
			"       xg_szdw_bjlh_fdykhxmb t_4, " + 
			"       xg_szdw_bjlh_fdykhb   t_5, " + 
			"       view_xsxxb            v_1	" + 
			" where t_3.khbid = t_4.khbid " + 
			"   and t_3.xmid = t_4.xmid " + 
			"   and t_4.khbid = t_5.khbid " + 
			"   and t_5.pfdx = 'xs' " + 
			"   and t_3.yhm = v_1.xh " + 
			" group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm) t1 left join view_xsxxb t2 on  t1.yhm = t2.xh) a left join (select t2.bjdm, t2.zgh, " +
			"               decode(sum(zzlb), " +
			"                      '3', " + 
			"                      '辅导员兼班主任', " + 
			"                      '2', " + 
			"                      '班主任', " + 
			"                      '1', " + 
			"                      '辅导员', " + 
			"                      '') zzlbmc " + 
			"          from (select f1.*, '1' zzlb " + 
			"                  from fdybjb f1 " + 
			"                union  " +
			"                select b1.*, '2' zzlb from bzrbbb b1) t2 " + 
			"         group by (t2.bjdm, t2.zgh)) b on a.bjdm = b.bjdm and a.fdyid = b.zgh where  a.fdyid=? and  a.xn = ?  and b.zzlbmc = ? ";
		xsmxList = dao.getListNotOut(sql, new String[]{myForm.getFdyid() , myForm.getXn() , myForm.getZzlbmc()});
		
		sql = "select  rownum r, a.* from ( select t3.xn , t1.xmid  ,t1.khbid,t1.fdyid , t2.khnr , t4.zzlbmc , round(avg(fz),2) xspjf from xg_szdw_bjlh_fdykhpfb t1 left join " + 
			"       xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join  " + 
			"            xg_szdw_bjlh_fdykhb t3   on t2.khbid = t3.khbid left join view_xsxxb v on t1.yhm = v.xh  " + 
			"            left join (select t2.bjdm, t2.zgh, " + 
			"                    decode(sum(zzlb), " + 
			"                      '3','辅导员兼班主任','2','班主任','1','辅导员','') zzlbmc " +
			"          from (select f1.*, '1' zzlb from fdybjb f1  union select b1.*, '2' zzlb from bzrbbb b1) t2 " +
			"         group by (t2.bjdm, t2.zgh)) t4 on v.bjdm = t4.bjdm " +
			"      where t3.pfdx = 'xs'  " +
			"      group by t3.xn ,t1.khbid, t1.xmid , t1.fdyid , t2.khnr , t4.zzlbmc) a  where a.fdyid = ? and  a.xn = ?  and zzlbmc = ? " ;
		
		
		tmmxList = dao.getListNotOut(sql, new String[]{myForm.getFdyid() , myForm.getXn() , myForm.getZzlbmc()});

		sql = "select round(avg(a.fz),2) xspjf from  (select t1.* , t2.bjdm ,t2.xm from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm, " +
		"       sum(case " +
		"             when t_4.pflx = '减分' then" + 
		"              to_char(- (to_number(t_3.fz))) " + 
		"             else	" + 
		"              t_3.fz	" + 
		"           end) fz	" + 
		"  from xg_szdw_bjlh_fdykhpfb t_3, " + 
		"       xg_szdw_bjlh_fdykhxmb t_4, " + 
		"       xg_szdw_bjlh_fdykhb   t_5, " + 
		"       view_xsxxb            v_1	" + 
		" where t_3.khbid = t_4.khbid " + 
		"   and t_3.xmid = t_4.xmid " + 
		"   and t_4.khbid = t_5.khbid " + 
		"   and t_5.pfdx = 'xs' " + 
		"   and t_3.yhm = v_1.xh " + 
		" group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm) t1 left join view_xsxxb t2 on  t1.yhm = t2.xh) a left join (select t2.bjdm, t2.zgh, " +
		"               decode(sum(zzlb), " +
		"                      '3', " + 
		"                      '辅导员兼班主任', " + 
		"                      '2', " + 
		"                      '班主任', " + 
		"                      '1', " + 
		"                      '辅导员', " + 
		"                      '') zzlbmc " + 
		"          from (select f1.*, '1' zzlb " + 
		"                  from fdybjb f1 " + 
		"                union  " +
		"                select b1.*, '2' zzlb from bzrbbb b1) t2 " + 
		"         group by (t2.bjdm, t2.zgh)) b on a.bjdm = b.bjdm and a.fdyid = b.zgh where  a.fdyid=? and  a.xn = ?  and b.zzlbmc = ? ";
		
		xsmxPjf = dao.getOneRs(sql, new String[]{myForm.getFdyid() , myForm.getXn() , myForm.getZzlbmc()} , "xspjf");
		
		sql = "select  sum(case when a.pflx = '减分' then to_char(- (to_number(a.xspjf))) else to_char(a.xspjf) end) zf  from ( select t3.xn , t2.pflx, t1.xmid  ,t1.khbid,t1.fdyid , t2.khnr , t4.zzlbmc , round(avg(fz),2) xspjf from xg_szdw_bjlh_fdykhpfb t1 left join " + 
		"       xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join  " + 
		"            xg_szdw_bjlh_fdykhb t3   on t2.khbid = t3.khbid left join view_xsxxb v on t1.yhm = v.xh  " + 
		"            left join (select t2.bjdm, t2.zgh, " + 
		"                    decode(sum(zzlb), " + 
		"                      '3','辅导员兼班主任','2','班主任','1','辅导员','') zzlbmc " +
		"          from (select f1.*, '1' zzlb from fdybjb f1  union select b1.*, '2' zzlb from bzrbbb b1) t2 " +
		"         group by (t2.bjdm, t2.zgh)) t4 on v.bjdm = t4.bjdm " +
		"      where t3.pfdx = 'xs'  " +
		"      group by t3.xn ,t1.khbid, t1.xmid , t2.pflx ,t1.fdyid , t2.khnr , t4.zzlbmc) a  where a.fdyid = ? and  a.xn = ?  and zzlbmc = ? " ;
		tmmxZf = dao.getOneRs(sql, new String[]{myForm.getFdyid() , myForm.getXn() , myForm.getZzlbmc()} , "zf");
		}
		
		return new Object[]{xsmxPjf , tmmxZf , xsmxList , tmmxList};
	}
	
	/**
	 * 
	 * @描述:获取统计明细列表
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-20 上午09:28:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * Object[] 返回类型 
	 * @throws
	 */
	public Object[] getFdyKhxxMxDetail(BjlhFdykhForm myForm , User user) throws Exception{
		
		List<HashMap<String , String>> xsmxList = null; //学生明细
		
		String tmmxZf = "";//题目明细总分
		
		DAO dao = DAO.getInstance();
		
		String sql = ""; 
		
		if(StringUtils.isEqual("1", myForm.getType())){
			sql = " select rownum r, a.* from (select t1.xmid , t1.fz, t1.yhm , t1.fdyid , t1.khbid , t4.xm , t2.khnr " + 
			"   from xg_szdw_bjlh_fdykhpfb t1 left join xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid " + 
			"    left join view_xsxxb t4 on t1.yhm = t4.xh) a where a.khbid = ? and a.yhm = ? and fdyid = ? ";
			
			xsmxList = dao.getListNotOut(sql, new String[]{myForm.getKhbid() , myForm.getYhm() , myForm.getFdyid()});
			
			sql = " select sum(a.fz) zf from (select t1.xmid , t1.fz, t1.yhm , t1.fdyid , t1.khbid , t4.xm" + 
			"   from xg_szdw_bjlh_fdykhpfb t1 " + 
			"    left join view_xsxxb t4 on t1.yhm = t4.xh) a where a.khbid = ? and a.yhm = ? and fdyid = ? ";
			
			tmmxZf = dao.getOneRs(sql, new String[]{myForm.getKhbid() , myForm.getYhm() , myForm.getFdyid()} , "zf");
		}else if(StringUtils.isEqual("RS_2", myForm.getType())){
			sql = " select rownum r , a.* from (select t1.xmid , t1.fz, t1.yhm , t1.fdyid , t1.khbid , t4.xm , t2.khnr , t3.pfdx " + 
			"from xg_szdw_bjlh_fdykhpfb t1 left join xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join  xg_szdw_bjlh_fdykhb t3 on t2.khbid = t3.khbid " + 
			"left join view_xsxxb t4 on t1.yhm = t4.xh) a where a.xmid = ?  and  a.fdyid = ? and a.pfdx = 'xs' ";
			
			xsmxList = dao.getListNotOut(sql, new String[]{myForm.getXmid(), myForm.getFdyid()});
			
			sql = " select  round(avg(a.fz),2) xspjf from (select t1.xmid , t1.fz, t1.yhm , t1.fdyid , t1.khbid , t4.xm , t2.khnr , t3.pfdx " + 
			"from xg_szdw_bjlh_fdykhpfb t1 left join xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join  xg_szdw_bjlh_fdykhb t3 on t2.khbid = t3.khbid " + 
			"left join view_xsxxb t4 on t1.yhm = t4.xh) a where a.xmid = ?  and  a.fdyid = ? and a.pfdx = 'xs' ";
			
			tmmxZf = dao.getOneRs(sql, new String[]{myForm.getXmid(), myForm.getFdyid()} , "xspjf");
		}else if(StringUtils.isEqual("BJ_2", myForm.getType())){
			sql = " select rownum r , a.* from (select t1.xmid , t1.fz, t1.yhm , t1.fdyid , t1.khbid , t4.xm , t2.khnr , t3.pfdx , t4.bjdm " + 
			"from xg_szdw_bjlh_fdykhpfb t1 left join xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join  xg_szdw_bjlh_fdykhb t3 on t2.khbid = t3.khbid " + 
			"left join view_xsxxb t4 on t1.yhm = t4.xh) a where a.xmid = ?  and  a.fdyid = ? and a.bjdm = ? and a.pfdx = 'xs' ";
			
			xsmxList = dao.getListNotOut(sql, new String[]{myForm.getXmid(), myForm.getFdyid() , myForm.getBjdm()});
			
			sql = " select  round(avg(a.fz),2) xspjf from (select t1.xmid , t1.fz, t1.yhm , t1.fdyid , t1.khbid , t4.xm , t2.khnr , t3.pfdx  , t4.bjdm " + 
			"from xg_szdw_bjlh_fdykhpfb t1 left join xg_szdw_bjlh_fdykhxmb t2 on t1.xmid = t2.xmid left join  xg_szdw_bjlh_fdykhb t3 on t2.khbid = t3.khbid " + 
			"left join view_xsxxb t4 on t1.yhm = t4.xh) a where a.xmid = ?  and  a.fdyid = ?  and a.bjdm = ? and a.pfdx = 'xs' ";
			
			tmmxZf = dao.getOneRs(sql, new String[]{myForm.getXmid(), myForm.getFdyid(), myForm.getBjdm()} , "xspjf");
		}else if(StringUtils.isEqual("ZZ_2", myForm.getType())){
			sql = "select rownum r , a.*  from (select a.* , b.zzlbmc from  " + 
			"       (select t1.xmid,t1.fz,t1.yhm,t1.fdyid, t1.khbid,t4.xm,t2.khnr,t3.pfdx, t4.bjdm " + 
			"           from xg_szdw_bjlh_fdykhpfb t1 " +
			"           left join xg_szdw_bjlh_fdykhxmb t2 " + 
			"             on t1.xmid = t2.xmid " +
			"           left join xg_szdw_bjlh_fdykhb t3 " +
			"             on t2.khbid = t3.khbid " +
			"           left join view_xsxxb t4 " +
			"             on t1.yhm = t4.xh where t3.pfdx = 'xs' ) a  left join (select t2.bjdm, t2.zgh, " +
			"               decode(sum(zzlb),'3','辅导员兼班主任','2','班主任','1','辅导员','') zzlbmc " +
			"          from (select f1.*, '1' zzlb from fdybjb f1 union select b1.*, '2' zzlb from bzrbbb b1) t2 " +
			"          group by (t2.bjdm, t2.zgh)) b " +
			"         on a.bjdm = b.bjdm and a.fdyid=b.zgh) a  " +
			"         where  a.zzlbmc = '辅导员' and a.xmid = ? and a.fdyid = ? and a.zzlbmc = ? ";
			
			xsmxList = dao.getListNotOut(sql, new String[]{myForm.getXmid(), myForm.getFdyid() , myForm.getZzlbmc()});
			
			sql = "select round(avg(a.fz),2) xspjf  from (select a.* , b.zzlbmc from  " + 
			"       (select t1.xmid,t1.fz,t1.yhm,t1.fdyid, t1.khbid,t4.xm,t2.khnr,t3.pfdx, t4.bjdm " + 
			"           from xg_szdw_bjlh_fdykhpfb t1 " +
			"           left join xg_szdw_bjlh_fdykhxmb t2 " + 
			"             on t1.xmid = t2.xmid " +
			"           left join xg_szdw_bjlh_fdykhb t3 " +
			"             on t2.khbid = t3.khbid " +
			"           left join view_xsxxb t4 " +
			"             on t1.yhm = t4.xh where t3.pfdx = 'xs' ) a  left join (select t2.bjdm, t2.zgh, " +
			"               decode(sum(zzlb),'3','辅导员兼班主任','2','班主任','1','辅导员','') zzlbmc " +
			"          from (select f1.*, '1' zzlb from fdybjb f1 union select b1.*, '2' zzlb from bzrbbb b1) t2 " +
			"          group by (t2.bjdm, t2.zgh)) b " +
			"         on a.bjdm = b.bjdm and a.fdyid=b.zgh) a  " +
			"         where  a.zzlbmc = '辅导员' and a.xmid = ? and a.fdyid = ? and a.zzlbmc = ? ";
			
			tmmxZf = dao.getOneRs(sql, new String[]{myForm.getXmid(), myForm.getFdyid(), myForm.getZzlbmc()} , "xspjf");
		}
		
		return new Object[]{xsmxList , tmmxZf};
	}
	
	
	/**
	 * 
	 * @描述:按人数
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-14 上午10:38:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByRS(BjlhFdykhForm myForm , User user) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl="";//教师考核评分比例
		String xskhpfbl="";//学生考核评分比例
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核时比例分配
			jskhpfbl="0";//教师考核评分比例
			xskhpfbl="100";//学生考核评分比例
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核时比例分配
			jskhpfbl="100";//教师考核评分比例
			xskhpfbl="0";//学生考核评分比例
		}else{
			jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
			xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		}
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		
		String sql = "select rownum r,	"+
		"     'RS!!one!!' || a.xn || '!!one!!' || a.zgh pk,	"+
		"	  a.*,"+
		"     round((a.xspjf * ("+ xskhpfbl +" / 100) + nvl(cpxzpjf, 0) * ("+ jskhpfbl +" / 100)), 2) zf,	"+
		"     nvl(xsdfrs, '0') || '/' ||  nvl(fdybjrs,'0') xspfqk, 	"+
		"	  nvl(cpxzpfrs, '0') || '/' || nvl(cpxzrs,'0') cpxzpfqk 	"+
		"from (select a.*,b.fdybjs,round(nvl(c.xspjf,'0') , 2) xspjf ,c.xsdfrs,round(nvl(d.cpxzpjf,'0'),2) cpxzpjf ,d.cpxzpfrs,	 e.cpxzrs, "+
		"             round((case	"+
		"                     when c.xsdfrs / a.fdybjrs > ("+ xspfyxbl +" / 100) then	"+
		"                      c.xspjf	"+
		"                     else	"+
		"                      0	"+
		"                   end),	"+
		"                   2) yxxspjf,	"+
		"             round(d.cpxzpjf, 2) yxcpxzpjf	"+
		"        from (select aa.*, bb.*	"+
		"                from (select distinct xn from xg_szdw_bjlh_fdykhb) aa,	"+
		"                     (select t_2.zgh,v_1.xm,v_1.szbm,v_1.bmmc,v_1.bmdm,count(1) fdybjrs	"+
		"                        from (view_fdybjb) t_2	" +
		"                        left join view_fdyxx v_1	"+
		"                          on t_2.zgh = v_1.zgh	"+
		"                        left join view_xsjbxx v_2	"+
		"                          on v_2.bjdm = t_2.bjdm	"+
		"                       group by t_2.zgh, v_1.xm, v_1.szbm, v_1.bmmc, v_1.bmdm) bb) a	"+
		"        left join (select a.zgh, count(1) fdybjs	"+
		"                    from (view_fdybjb) a	"+
		"                   group by a.zgh) b	"+
		"          on a.zgh = b.zgh	"+
		"        left join (select xn, khbid, fdyid, avg(fz) xspjf, count(1) xsdfrs	"+
		"                    from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm,	"+
		"                                 sum(case	"+
		"                                       when t_4.pflx = '减分' then	"+
		"                                        to_char(- (to_number(t_3.fz)))	"+
		"                                       else	"+
		"                                        t_3.fz	"+
		"                                     end) fz	"+
		"                            from xg_szdw_bjlh_fdykhpfb t_3,	"+
		"                                 xg_szdw_bjlh_fdykhxmb t_4,	"+
		"                                 xg_szdw_bjlh_fdykhb   t_5	"+
		"                           where t_3.khbid = t_4.khbid	"+
		"                             and t_3.xmid = t_4.xmid	"+
		"                             and t_4.khbid = t_5.khbid	"+
		"                             and t_5.pfdx = 'xs'	"+
		"                           group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm)	"+
		"                   group by xn, khbid, fdyid) c	"+
		"          on c.xn = a.xn	"+
		"         and c.fdyid = a.zgh	"+
		"        left join (select xn,khbid,fdyid,avg(fz) cpxzpjf,count(1) cpxzpfrs	"+
		"                    from (select c.xn,a.khbid,a.fdyid,a.yhm,	"+
		"                                 sum(case	"+
		"                                       when b.pflx = '减分' then	"+
		"                                        to_char(- (to_number(a.fz)))	"+
		"                                       else	"+
		"                                        a.fz	"+
		"                                     end) fz	"+
		"                            from xg_szdw_bjlh_fdykhpfb a	"+
		"                            left join xg_szdw_bjlh_fdykhxmb b	"+
		"                              on a.khbid = b.khbid	"+
		"                             and a.xmid = b.xmid	"+
		"                            left join xg_szdw_bjlh_fdykhb c	"+
		"                              on a.khbid = c.khbid	"+
		"                           where c.pfdx = 'pfxz'	"+
		"                           group by c.xn, a.khbid, a.fdyid, a.yhm)	"+
		"                   group by xn, khbid, fdyid) d	"+
		"          on d.xn = a.xn	"+
		"         and d.fdyid = a.zgh left join (select t.zgh, count(1) cpxzrs from XG_SZDW_BJLH_FDYKHRYB t group by t.zgh) e on a.zgh = e.zgh ) a	"+
		"where 1 = 1	";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser2(user, "","bmdm", null); 	//学院用户
		if("true".equalsIgnoreCase(user.getFdyQx())){			//辅导员用户
			searchTjQx = " and zgh = '"+user.getUserName()+"' ";
		}
		
		return getPageList(myForm, sql + searchTj + searchTjQx, inputV);
	}
	
	/**
	 * 
	 * @描述:按班级
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-17 上午10:34:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByBJ(BjlhFdykhForm myForm , User user) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl="";//教师考核评分比例
		String xskhpfbl="";//学生考核评分比例
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核时比例分配
			jskhpfbl="0";//教师考核评分比例
			xskhpfbl="100";//学生考核评分比例
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核时比例分配
			jskhpfbl="100";//教师考核评分比例
			xskhpfbl="0";//学生考核评分比例
		}else{
			jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
			xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		}
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		
		String sql = "select rownum r,	"+
		"     'BJ!!one!!' ||a.xn || '!!one!!' || a.bjdm || '!!one!!' || a.zgh pk,	"+
		"	  a.*,"+
		"     round((a.xspjf * ("+ xskhpfbl +" / 100) + nvl(cpxzpjf, 0) * ("+ jskhpfbl +" / 100)), 2) zf,	"+
		"     nvl(xsdfrs, '0') || '/' || nvl(bjrs,'0') xspfqk, 	"+
		"	  nvl(cpxzpfrs, '0') || '/' || nvl(cpxzrs,'0') cpxzpfqk  "+
		" from (select a.*, v2.xm, v2.bmdm, v2.bmmc ,b.bjrs,b.bjmc,round(nvl(c.xspjf,'0'),2) xspjf,c.xsdfrs,round(nvl(d.cpxzpjf,'0'),2) cpxzpjf,d.cpxzpfrs,e.cpxzrs,	"+
		"             round((case	"+
		"                     when c.xsdfrs / b.bjrs > ("+ xspfyxbl +" / 100) then	"+
		"                      c.xspjf	"+
		"                     else	"+
		"                      0	"+
		"                   end),	"+
		"                   2) yxxspjf "+
		"  from (select * from (select distinct xn from xg_szdw_bjlh_fdykhb) t1 , (select t2.bjdm,t2.zgh,	"+
		"               decode(sum(t2.zzlb),	"+
		"                      '3',	"+
		"                      '辅导员兼班主任',	" +
		"                      '2',	"+
		"                      '班主任',"+
		"                      '1',	"+
		"                      '辅导员',	"+
		"                      '辅导员兼班主任') zzlbmc	"+
		"           from (select f1.*, '1' zzlb from fdybjb f1  where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = f1.bjdm and xsb.SFZX = '在校')	"+
		"                union	"+
		"                select b1.*, '2' zzlb from bzrbbb b1  where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = b1.bjdm and xsb.SFZX = '在校') ) t2 "+
		"         group by (t2.bjdm, t2.zgh))) a	"+
		"  left join view_fdyxx v2	"+
		"    on a.zgh = v2.zgh	"+
		"  left join (select bjdm, bjmc,count(1) bjrs from view_xsjbxx v1 group by v1.bjdm,v1.bjmc) b	"+
		"    on a.bjdm = b.bjdm	"+
		"        left join (select a1.xn, a1.fdyid, b1.bjdm, avg(a1.fz) xspjf, count(1) xsdfrs	"+
		"                    from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm,	"+
		"                                 sum(case	"+
		"                                       when t_4.pflx = '减分' then	"+
		"                                        to_char(- (to_number(t_3.fz)))	"+
		"                                       else	"+
		"                                        t_3.fz	"+
		"                                     end) fz	"+
		"                            from xg_szdw_bjlh_fdykhpfb t_3,	"+
		"                                 xg_szdw_bjlh_fdykhxmb t_4,	"+
		"                                 xg_szdw_bjlh_fdykhb   t_5,	"+
		"								  view_xsxxb v_1	"+								
		"                           where t_3.khbid = t_4.khbid	"+
		"                             and t_3.xmid = t_4.xmid	"+
		"                             and t_4.khbid = t_5.khbid	"+
		"                             and t_5.pfdx = 'xs'	"+
		"							  and t_3.yhm = v_1.xh	"+
		"                           group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm)a1 left join xsxxb b1 on a1.yhm = b1.xh"+
		"                   group by a1.xn, a1.fdyid , b1.bjdm) c	"+
		"          on c.xn = a.xn	"+
		"         and c.fdyid = a.zgh	"+
		"		  and c.bjdm = a.bjdm	"+
		"        left join (select xn,khbid,fdyid,avg(fz) cpxzpjf,count(1) cpxzpfrs	"+
		"                    from (select c.xn,a.khbid,a.fdyid,a.yhm,	"+
		"                                 sum(case	"+
		"                                       when b.pflx = '减分' then	"+
		"                                        to_char(- (to_number(a.fz)))	"+
		"                                       else	"+
		"                                        a.fz	"+
		"                                     end) fz	"+
		"                            from xg_szdw_bjlh_fdykhpfb a	"+
		"                            left join xg_szdw_bjlh_fdykhxmb b	"+
		"                              on a.khbid = b.khbid	"+
		"                             and a.xmid = b.xmid	"+
		"                            left join xg_szdw_bjlh_fdykhb c	"+
		"                              on a.khbid = c.khbid	"+
		"                           where c.pfdx = 'pfxz'	"+
		"                           group by c.xn, a.khbid, a.fdyid, a.yhm)	"+
		"                   group by xn, khbid, fdyid) d	"+
		"          on d.xn = a.xn	"+
		"         and d.fdyid = a.zgh left join (select t.zgh, count(1) cpxzrs from XG_SZDW_BJLH_FDYKHRYB t group by t.zgh) e on a.zgh = e.zgh ) a	"+
		"where 1 = 1	";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser2(user, "","bmdm", null); 	//学院用户
		if("true".equalsIgnoreCase(user.getFdyQx())){			//辅导员用户
			searchTjQx = " and zgh = '"+user.getUserName()+"' ";
		}
		
		return getPageList(myForm, sql + searchTj + searchTjQx, inputV);
	}
	
	
	/**
	 * 
	 * @描述:按职责
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-18 下午02:07:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByZZ(BjlhFdykhForm myForm , User user) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl="";//教师考核评分比例
		String xskhpfbl="";//学生考核评分比例
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核时比例分配
			jskhpfbl="0";//教师考核评分比例
			xskhpfbl="100";//学生考核评分比例
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核时比例分配
			jskhpfbl="100";//教师考核评分比例
			xskhpfbl="0";//学生考核评分比例
		}else{
			jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
			xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		}
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		
		String sql = "select rownum r,'ZZ!!one!!' ||a.xn || '!!one!!' || a.zzlbmc || '!!one!!' || a.zgh pk,a.*,	"+
					 "round((case when a.xsdfrs / a.bjrs > ("+ xspfyxbl +" / 100) then a.xspjf else 0 end), 2) yxxspjf, "+
					 "round((a.xspjf * ("+ xskhpfbl +" / 100) + nvl(cpxzpjf, 0) * ("+ jskhpfbl +" / 100)), 2) zf, "+
					 "nvl(xsdfrs, '0') || '/' || nvl(bjrs,'0') xspfqk, " + 
					 "nvl(cpxzpfrs, '0') || '/' || nvl(cpxzrs,'0') cpxzpfqk " + 
					 "from (select a.*,round(nvl(b.xspjf,'0'),2) xspjf ,b.xsdfrs,round(nvl(d.cpxzpjf,'0'),2) cpxzpjf,d.cpxzpfrs,e.cpxzrs from (select * from (select a.* ,b.bjs , c.bmdm , c.bmmc , c.xm from (select a.zgh,a.zzlbmc , count(1) bjrs from  (select t2.bjdm,  t2.zgh, " +
					 " decode(sum(zzlb), '3','辅导员兼班主任','2','班主任', '1','辅导员','') zzlbmc " +
					 "from (select f1.*, '1' zzlb from fdybjb f1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = f1.bjdm and xsb.SFZX = '在校') union select b1.*, '2' zzlb from bzrbbb b1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = b1.bjdm and xsb.SFZX = '在校')) t2 " +
					 "group by (t2.bjdm, t2.zgh)) a left join view_xsxxb v1 on a.bjdm = v1.bjdm group by (a.zgh,a.zzlbmc) ) a left join (select zgh, zzlbmc, count(1) bjs " +
					 "from (select t2.bjdm,t2.zgh, decode(sum(zzlb),'3','辅导员兼班主任', '2','班主任','1','辅导员','') zzlbmc "+
					 "from (select f1.*, '1' zzlb from fdybjb f1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = f1.bjdm and xsb.SFZX = '在校') union select b1.*, '2' zzlb from bzrbbb b1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = b1.bjdm and xsb.SFZX = '在校')) t2 group by (t2.bjdm, t2.zgh)) " + 
					 "group by (zgh, zzlbmc)) b on a.zgh = b.zgh and a.zzlbmc = b.zzlbmc left join view_fdyxx c on a.zgh = c.zgh) t1 ,(select distinct xn from xg_szdw_bjlh_fdykhb) t2) a " +
					 " left join  (select t1.xn, t1.zzlbmc, t1.fdyid, avg(t1.fz) xspjf, count(1) xsdfrs from (select a.*, b.zzlbmc from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm,v_1.bjdm, " +
					 "sum(case when t_4.pflx = '减分' then to_char(- (to_number(t_3.fz))) else t_3.fz end) fz " + 
					 "from xg_szdw_bjlh_fdykhpfb t_3,xg_szdw_bjlh_fdykhxmb t_4,xg_szdw_bjlh_fdykhb   t_5,view_xsxxb            v_1 " + 
					 "where t_3.khbid = t_4.khbid and t_3.xmid = t_4.xmid and t_4.khbid = t_5.khbid and t_5.pfdx = 'xs' and t_3.yhm = v_1.xh group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm, v_1.bjdm) a "+
					 "left join (select t2.bjdm, t2.zgh,decode(sum(zzlb), '3','辅导员兼班主任', '2', '班主任','1','辅导员','') zzlbmc " + 
					 " from (select f1.*, '1' zzlb from fdybjb f1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = f1.bjdm and xsb.SFZX = '在校') union select b1.*, '2' zzlb from bzrbbb b1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = b1.bjdm and xsb.SFZX = '在校')) t2 group by (t2.bjdm, t2.zgh)) b on a.fdyid = b.zgh and a.bjdm = b.bjdm) t1 " + 
					 " group by t1.xn, t1.fdyid, t1.zzlbmc) b on a.xn = b.xn and a.zgh = b.fdyid and a.zzlbmc = b.zzlbmc left join (select xn, khbid,  fdyid, avg(fz) cpxzpjf, count(1) cpxzpfrs " + 
					 "  from (select c.xn,a.khbid,a.fdyid,a.yhm,sum(case when b.pflx = '减分' then to_char(- (to_number(a.fz))) else a.fz end) fz " + 
					 "   from xg_szdw_bjlh_fdykhpfb a left join xg_szdw_bjlh_fdykhxmb b on a.khbid = b.khbid  and a.xmid = b.xmid left join xg_szdw_bjlh_fdykhb c  on a.khbid = c.khbid " + 
					 "  where c.pfdx = 'pfxz' group by c.xn, a.khbid, a.fdyid, a.yhm) group by xn, khbid, fdyid) d on d.xn = a.xn and d.fdyid = a.zgh  " + 
					 " left join (select t.zgh, count(1) cpxzrs from XG_SZDW_BJLH_FDYKHRYB t group by t.zgh) e on a.zgh = e.zgh  ) a where 1=1 ";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser2(user, "","bmdm", null); 	//学院用户
		if("true".equalsIgnoreCase(user.getFdyQx())){			//辅导员用户
			searchTjQx = " and zgh = '"+user.getUserName()+"' ";
		}
		
		return getPageList(myForm, sql + searchTj + searchTjQx, inputV);
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjlhFdykhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjlhFdykhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根

	}

	
	
	//=========================导出================================//
	
	/**
	 * 
	 * @描述:按人数
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-14 上午10:38:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByRSAll(BjlhFdykhForm myForm , User user) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl="";//教师考核评分比例
		String xskhpfbl="";//学生考核评分比例
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核时比例分配
			jskhpfbl="0";//教师考核评分比例
			xskhpfbl="100";//学生考核评分比例
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核时比例分配
			jskhpfbl="100";//教师考核评分比例
			xskhpfbl="0";//学生考核评分比例
		}else{
			jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
			xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		}
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		
		String sql = "select rownum r,	"+
		"     'RS!!one!!' || a.xn || '!!one!!' || a.zgh pk,	"+
		"	  a.*,"+
		"     round((a.xspjf * ("+ xskhpfbl +" / 100) + nvl(cpxzpjf, 0) * ("+ jskhpfbl +" / 100)), 2) zf,	"+
		"     nvl(xsdfrs, '0') || '/' ||  nvl(fdybjrs,'0') xspfqk, 	"+
		"	  nvl(cpxzpfrs, '0') || '/' || nvl(cpxzrs,'0') cpxzpfqk 	"+
		"from (select a.*,b.fdybjs,nvl(c.xspjf,'0') xspjf ,c.xsdfrs,nvl(d.cpxzpjf,'0')cpxzpjf ,d.cpxzpfrs,	 e.cpxzrs, "+
		"             round((case	"+
		"                     when c.xsdfrs / a.fdybjrs > ("+ xspfyxbl +" / 100) then	"+
		"                      c.xspjf	"+
		"                     else	"+
		"                      0	"+
		"                   end),	"+
		"                   2) yxxspjf,	"+
		"             round(d.cpxzpjf, 2) yxcpxzpjf	"+
		"        from (select aa.*, bb.*	"+
		"                from (select distinct xn from xg_szdw_bjlh_fdykhb) aa,	"+
		"                     (select t_2.zgh,v_1.xm,v_1.szbm,v_1.bmmc,v_1.bmdm,count(1) fdybjrs	"+
		"                        from (select * from fdybjb union select * from bzrbbb) t_2	" +
		"                        left join view_fdyxx v_1	"+
		"                          on t_2.zgh = v_1.zgh	"+
		"                        left join view_xsjbxx v_2	"+
		"                          on v_2.bjdm = t_2.bjdm	"+
		"                       group by t_2.zgh, v_1.xm, v_1.szbm, v_1.bmmc, v_1.bmdm) bb) a	"+
		"        left join (select a.zgh, count(1) fdybjs	"+
		"                    from (select * from fdybjb union select * from bzrbbb) a	"+
		"                   group by a.zgh) b	"+
		"          on a.zgh = b.zgh	"+
		"        left join (select xn, khbid, fdyid, avg(fz) xspjf, count(1) xsdfrs	"+
		"                    from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm,	"+
		"                                 sum(case	"+
		"                                       when t_4.pflx = '减分' then	"+
		"                                        to_char(- (to_number(t_3.fz)))	"+
		"                                       else	"+
		"                                        t_3.fz	"+
		"                                     end) fz	"+
		"                            from xg_szdw_bjlh_fdykhpfb t_3,	"+
		"                                 xg_szdw_bjlh_fdykhxmb t_4,	"+
		"                                 xg_szdw_bjlh_fdykhb   t_5	"+
		"                           where t_3.khbid = t_4.khbid	"+
		"                             and t_3.xmid = t_4.xmid	"+
		"                             and t_4.khbid = t_5.khbid	"+
		"                             and t_5.pfdx = 'xs'	"+
		"                           group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm)	"+
		"                   group by xn, khbid, fdyid) c	"+
		"          on c.xn = a.xn	"+
		"         and c.fdyid = a.zgh	"+
		"        left join (select xn,khbid,fdyid,avg(fz) cpxzpjf,count(1) cpxzpfrs	"+
		"                    from (select c.xn,a.khbid,a.fdyid,a.yhm,	"+
		"                                 sum(case	"+
		"                                       when b.pflx = '减分' then	"+
		"                                        to_char(- (to_number(a.fz)))	"+
		"                                       else	"+
		"                                        a.fz	"+
		"                                     end) fz	"+
		"                            from xg_szdw_bjlh_fdykhpfb a	"+
		"                            left join xg_szdw_bjlh_fdykhxmb b	"+
		"                              on a.khbid = b.khbid	"+
		"                             and a.xmid = b.xmid	"+
		"                            left join xg_szdw_bjlh_fdykhb c	"+
		"                              on a.khbid = c.khbid	"+
		"                           where c.pfdx = 'pfxz'	"+
		"                           group by c.xn, a.khbid, a.fdyid, a.yhm)	"+
		"                   group by xn, khbid, fdyid) d	"+
		"          on d.xn = a.xn	"+
		"         and d.fdyid = a.zgh left join (select t.zgh, count(1) cpxzrs from XG_SZDW_BJLH_FDYKHRYB t group by t.zgh) e on a.zgh = e.zgh ) a	"+
		"where 1 = 1	";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser2(user, "","bmdm", null); 	//学院用户
		if("true".equalsIgnoreCase(user.getFdyQx())){			//辅导员用户
			searchTjQx = " and zgh = '"+user.getUserName()+"' ";
		}
		
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		
		return getPageList(myForm, sql + searchTj + searchTjQx, inputV);
	}
	
	/**
	 * 
	 * @描述:按班级
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-17 上午10:34:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByBJAll(BjlhFdykhForm myForm , User user) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl="";//教师考核评分比例
		String xskhpfbl="";//学生考核评分比例
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核时比例分配
			jskhpfbl="0";//教师考核评分比例
			xskhpfbl="100";//学生考核评分比例
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核时比例分配
			jskhpfbl="100";//教师考核评分比例
			xskhpfbl="0";//学生考核评分比例
		}else{
			jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
			xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		}
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		
		String sql = "select rownum r,	"+
		"     'BJ!!one!!' ||a.xn || '!!one!!' || a.bjdm || '!!one!!' || a.zgh pk,	"+
		"	  a.*,"+
		"     round((a.xspjf * ("+ xskhpfbl +" / 100) + nvl(cpxzpjf, 0) * ("+ jskhpfbl +" / 100)), 2) zf,	"+
		"     nvl(xsdfrs, '0') || '/' || nvl(bjrs,'0') xspfqk, 	"+
		"	  nvl(cpxzpfrs, '0') || '/' || nvl(cpxzrs,'0') cpxzpfqk  "+
		" from (select a.*, v2.xm, v2.bmdm, v2.bmmc ,b.bjrs,b.bjmc,round(nvl(c.xspjf,'0'),2) xspjf,c.xsdfrs,round(nvl(d.cpxzpjf,'0'),2) cpxzpjf,d.cpxzpfrs,e.cpxzrs,	"+
		"             round((case	"+
		"                     when c.xsdfrs / b.bjrs > ("+ xspfyxbl +" / 100) then	"+
		"                      c.xspjf	"+
		"                     else	"+
		"                      0	"+
		"                   end),	"+
		"                   2) yxxspjf "+
		"  from (select * from (select distinct xn from xg_szdw_bjlh_fdykhb) t1 , (select t2.bjdm,t2.zgh,	"+
		"               decode(sum(t2.zzlb),	"+
		"                      '3',	"+
		"                      '辅导员兼班主任',	" +
		"                      '2',	"+
		"                      '班主任',"+
		"                      '1',	"+
		"                      '辅导员',	"+
		"                      '辅导员兼班主任') zzlbmc	"+
		"           from (select f1.*, '1' zzlb from fdybjb f1  where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = f1.bjdm and xsb.SFZX = '在校')	"+
		"                union	"+
		"                select b1.*, '2' zzlb from bzrbbb b1  where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = b1.bjdm and xsb.SFZX = '在校') ) t2 "+
		"         group by (t2.bjdm, t2.zgh))) a	"+
		"  left join view_fdyxx v2	"+
		"    on a.zgh = v2.zgh	"+
		"  left join (select bjdm, bjmc,count(1) bjrs from view_xsjbxx v1 group by v1.bjdm,v1.bjmc) b	"+
		"    on a.bjdm = b.bjdm	"+
		"        left join (select a1.xn, a1.fdyid, b1.bjdm, avg(a1.fz) xspjf, count(1) xsdfrs	"+
		"                    from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm,	"+
		"                                 sum(case	"+
		"                                       when t_4.pflx = '减分' then	"+
		"                                        to_char(- (to_number(t_3.fz)))	"+
		"                                       else	"+
		"                                        t_3.fz	"+
		"                                     end) fz	"+
		"                            from xg_szdw_bjlh_fdykhpfb t_3,	"+
		"                                 xg_szdw_bjlh_fdykhxmb t_4,	"+
		"                                 xg_szdw_bjlh_fdykhb   t_5,	"+
		"								  view_xsxxb v_1	"+								
		"                           where t_3.khbid = t_4.khbid	"+
		"                             and t_3.xmid = t_4.xmid	"+
		"                             and t_4.khbid = t_5.khbid	"+
		"                             and t_5.pfdx = 'xs'	"+
		"							  and t_3.yhm = v_1.xh	"+
		"                           group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm)a1 left join xsxxb b1 on a1.yhm = b1.xh"+
		"                   group by a1.xn, a1.fdyid , b1.bjdm) c	"+
		"          on c.xn = a.xn	"+
		"         and c.fdyid = a.zgh	"+
		"		  and c.bjdm = a.bjdm	"+
		"        left join (select xn,khbid,fdyid,avg(fz) cpxzpjf,count(1) cpxzpfrs	"+
		"                    from (select c.xn,a.khbid,a.fdyid,a.yhm,	"+
		"                                 sum(case	"+
		"                                       when b.pflx = '减分' then	"+
		"                                        to_char(- (to_number(a.fz)))	"+
		"                                       else	"+
		"                                        a.fz	"+
		"                                     end) fz	"+
		"                            from xg_szdw_bjlh_fdykhpfb a	"+
		"                            left join xg_szdw_bjlh_fdykhxmb b	"+
		"                              on a.khbid = b.khbid	"+
		"                             and a.xmid = b.xmid	"+
		"                            left join xg_szdw_bjlh_fdykhb c	"+
		"                              on a.khbid = c.khbid	"+
		"                           where c.pfdx = 'pfxz'	"+
		"                           group by c.xn, a.khbid, a.fdyid, a.yhm)	"+
		"                   group by xn, khbid, fdyid) d	"+
		"          on d.xn = a.xn	"+
		"         and d.fdyid = a.zgh left join (select t.zgh, count(1) cpxzrs from XG_SZDW_BJLH_FDYKHRYB t group by t.zgh) e on a.zgh = e.zgh ) a	"+
		"where 1 = 1	";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser2(user, "","bmdm", null); 	//学院用户
		if("true".equalsIgnoreCase(user.getFdyQx())){			//辅导员用户
			searchTjQx = " and zgh = '"+user.getUserName()+"' ";
		}
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		return getPageList(myForm, sql + searchTj + searchTjQx, inputV);
	}
	
	
	/**
	 * 
	 * @描述:按职责
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-2-18 下午02:07:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFdyKhxxByZZAll(BjlhFdykhForm myForm , User user) throws Exception{
		HashMap<String,String> csMap=new BjlhCsszDAO().getCssz();//获取参数
		String jskhpfbl="";//教师考核评分比例
		String xskhpfbl="";//学生考核评分比例
		if("xs".equalsIgnoreCase(SzkhCssz.KHRY)){//只有学生考核时比例分配
			jskhpfbl="0";//教师考核评分比例
			xskhpfbl="100";//学生考核评分比例
		} else if("cpz".equalsIgnoreCase(SzkhCssz.KHRY)){//只有参评组考核时比例分配
			jskhpfbl="100";//教师考核评分比例
			xskhpfbl="0";//学生考核评分比例
		}else{
			jskhpfbl=csMap.get("jskhpfbl");//教师考核评分比例
			xskhpfbl=csMap.get("xskhpfbl");//学生考核评分比例
		}
		String xspfyxbl=csMap.get("xspfyxbl");//学生评分有效比例
		
		String sql = "select rownum r,'ZZ!!one!!' ||a.xn || '!!one!!' || a.zzlbmc || '!!one!!' || a.zgh pk,a.*,	"+
		 "round((case when a.xsdfrs / a.bjrs > ("+ xspfyxbl +" / 100) then a.xspjf else 0 end), 2) yxxspjf, "+
		 "round((a.xspjf * ("+ xskhpfbl +" / 100) + nvl(cpxzpjf, 0) * ("+ jskhpfbl +" / 100)), 2) zf, "+
		 "nvl(xsdfrs, '0') || '/' || nvl(bjrs,'0') xspfqk, " + 
		 "nvl(cpxzpfrs, '0') || '/' || nvl(cpxzrs,'0') cpxzpfqk " + 
		 "from (select a.*,round(nvl(b.xspjf,'0'),2) xspjf ,b.xsdfrs,round(nvl(d.cpxzpjf,'0'),2) cpxzpjf,d.cpxzpfrs,e.cpxzrs from (select * from (select a.* ,b.bjs , c.bmdm , c.bmmc , c.xm from (select a.zgh,a.zzlbmc , count(1) bjrs from  (select t2.bjdm,  t2.zgh, " +
		 " decode(sum(zzlb), '3','辅导员兼班主任','2','班主任', '1','辅导员','') zzlbmc " +
		 "from (select f1.*, '1' zzlb from fdybjb f1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = f1.bjdm and xsb.SFZX = '在校') union select b1.*, '2' zzlb from bzrbbb b1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = b1.bjdm and xsb.SFZX = '在校')) t2 " +
		 "group by (t2.bjdm, t2.zgh)) a left join view_xsxxb v1 on a.bjdm = v1.bjdm group by (a.zgh,a.zzlbmc) ) a left join (select zgh, zzlbmc, count(1) bjs " +
		 "from (select t2.bjdm,t2.zgh, decode(sum(zzlb),'3','辅导员兼班主任', '2','班主任','1','辅导员','') zzlbmc "+
		 "from (select f1.*, '1' zzlb from fdybjb f1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = f1.bjdm and xsb.SFZX = '在校') union select b1.*, '2' zzlb from bzrbbb b1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = b1.bjdm and xsb.SFZX = '在校')) t2 group by (t2.bjdm, t2.zgh)) " + 
		 "group by (zgh, zzlbmc)) b on a.zgh = b.zgh and a.zzlbmc = b.zzlbmc left join view_fdyxx c on a.zgh = c.zgh) t1 ,(select distinct xn from xg_szdw_bjlh_fdykhb) t2) a " +
		 " left join  (select t1.xn, t1.zzlbmc, t1.fdyid, avg(t1.fz) xspjf, count(1) xsdfrs from (select a.*, b.zzlbmc from (select t_3.khbid,t_5.xn,t_3.fdyid,t_3.yhm,v_1.bjdm, " +
		 "sum(case when t_4.pflx = '减分' then to_char(- (to_number(t_3.fz))) else t_3.fz end) fz " + 
		 "from xg_szdw_bjlh_fdykhpfb t_3,xg_szdw_bjlh_fdykhxmb t_4,xg_szdw_bjlh_fdykhb   t_5,view_xsxxb            v_1 " + 
		 "where t_3.khbid = t_4.khbid and t_3.xmid = t_4.xmid and t_4.khbid = t_5.khbid and t_5.pfdx = 'xs' and t_3.yhm = v_1.xh group by t_3.khbid, t_5.xn, t_3.fdyid, t_3.yhm, v_1.bjdm) a "+
		 "left join (select t2.bjdm, t2.zgh,decode(sum(zzlb), '3','辅导员兼班主任', '2', '班主任','1','辅导员','') zzlbmc " + 
		 " from (select f1.*, '1' zzlb from fdybjb f1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = f1.bjdm and xsb.SFZX = '在校') union select b1.*, '2' zzlb from bzrbbb b1 where exists (select 1 from view_xsbfxx xsb where xsb.bjdm = b1.bjdm and xsb.SFZX = '在校')) t2 group by (t2.bjdm, t2.zgh)) b on a.fdyid = b.zgh and a.bjdm = b.bjdm) t1 " + 
		 " group by t1.xn, t1.fdyid, t1.zzlbmc) b on a.xn = b.xn and a.zgh = b.fdyid and a.zzlbmc = b.zzlbmc left join (select xn, khbid,  fdyid, avg(fz) cpxzpjf, count(1) cpxzpfrs " + 
		 "  from (select c.xn,a.khbid,a.fdyid,a.yhm,sum(case when b.pflx = '减分' then to_char(- (to_number(a.fz))) else a.fz end) fz " + 
		 "   from xg_szdw_bjlh_fdykhpfb a left join xg_szdw_bjlh_fdykhxmb b on a.khbid = b.khbid  and a.xmid = b.xmid left join xg_szdw_bjlh_fdykhb c  on a.khbid = c.khbid " + 
		 "  where c.pfdx = 'pfxz' group by c.xn, a.khbid, a.fdyid, a.yhm) group by xn, khbid, fdyid) d on d.xn = a.xn and d.fdyid = a.zgh  " + 
		 " left join (select t.zgh, count(1) cpxzrs from XG_SZDW_BJLH_FDYKHRYB t group by t.zgh) e on a.zgh = e.zgh  ) a where 1=1 ";
		
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());
		
		//权限控制
		SearchService searchService=new SearchService();
		String searchTjQx = searchService.getSearchTjByUser2(user, "","bmdm", null); 	//学院用户
		if("true".equalsIgnoreCase(user.getFdyQx())){			//辅导员用户
			searchTjQx = " and zgh = '"+user.getUserName()+"' ";
		}
		myForm.getPages().setPageSize(Integer.MAX_VALUE);
		return getPageList(myForm, sql + searchTj + searchTjQx, inputV);
	}
	
}
