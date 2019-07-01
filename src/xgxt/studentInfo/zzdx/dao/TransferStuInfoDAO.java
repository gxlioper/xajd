package xgxt.studentInfo.zzdx.dao;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 学生信息转换DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李容</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-08</p>
 */
public class TransferStuInfoDAO {	
	
	DAO dao = DAO.getInstance();
	/**
	 * 用于dwr验证学生基本信息临时表中不能为空的字段是否为空
	 * @return String
	 * */
	public String ckeckNullOfStuinfo(){
		String nd = Base.currNd;
		String sResult = "";
		String[] value = new String[7];
		String sql = "";
		//学号是否有空记录
		sql = "select count(*) count from xsjbxxlsb where xh is null and dqszj=?";
		sResult = dao.getOneRs(sql, new String[]{nd}, "count");
		value[0] = Integer.parseInt(sResult)>0 ? "学号为空" : "";
		//学院是否有空记录
		sql = "select count(*) count from xsjbxxlsb where xy is null and dqszj=?";
		sResult = dao.getOneRs(sql, new String[]{nd}, "count");
		value[1] = Integer.parseInt(sResult)>0 ? Base.YXPZXY_KEY+"为空" : "";
		//专业是否有空记录
		sql = "select count(*) count from xsjbxxlsb where zymc is null and dqszj=?";
		sResult = dao.getOneRs(sql, new String[]{nd}, "count");
		value[2] = Integer.parseInt(sResult)>0 ? "专业为空" : "";
		//班级是否有空的记录
		sql = "select count(*) count from xsjbxxlsb where xzb is null and dqszj=?";
		sResult = dao.getOneRs(sql, new String[]{nd}, "count");
		value[3] = Integer.parseInt(sResult)>0 ? "班级为空" : "";
		//是否有学院不存在
		sql = "select count(*)count from xsjbxxlsb a where not exists(select 1 from view_njxyzybj b where a.xy=b.xymc) and dqszj=?";
		sResult = dao.getOneRs(sql, new String[]{nd}, "count");
		value[4] = Integer.parseInt(sResult)>0 ? Base.YXPZXY_KEY+"不存在" : "";
		//是否有专业不存在
		sql = "select count(*) count from xsjbxxlsb a where not exists(select 1 from view_njxyzybj b where a.zymc=b.zymc) and dqszj=?";
		sResult = dao.getOneRs(sql, new String[]{nd}, "count");
		value[5] = Integer.parseInt(sResult)>0 ? "专业不存在" : "";
		//是否有班级不存在
		sql = "select count(*) count from xsjbxxlsb a where not exists(select 1 from view_njxyzybj b where a.xzb=b.bjmc) and dqszj=?";
		sResult = dao.getOneRs(sql, new String[]{nd}, "count");
		value[6] = Integer.parseInt(sResult)>0 ? "班级不存在" : "";
		
		return sResult;
	}
	
	/**
	 * 当前所在级的不在学生基本信息中的转入学生基本信息中	
	 * @param nd
	 * @param request
	 * @return boolean
	 * */
	public boolean xsjbxxlsbToXsxxb(String nd,HttpServletRequest request){
		boolean flag = true;
		String sql = "insert into xsxxb(xh,xm,xb,xy,xydm,zymc,zydm,bjmc,bjdm,nj,syd,csrq,sfzh,mz,zzmm,lxdh,dzyx,sg,tz,kslb,rxfs,pycc,xjztm,xz,jkzk,kh,rxrq)" + 
                     "(select distinct xh,xm,xb,xy,d.xydm,d.zymc,d.zydm," + 
                     "xzb,d.bjdm,dqszj,lydq,csrq,sfzh,(select distinct mzdm  from mzdmb b where a.mz=b.mzmc),(select distinct zzmmdm  from zzmmdmb b where a.zzmm=b.zzmmmc)," + 
                     "lxdh,dzyxdz,sg,tz,kslb,rxfs,cc,xjzt,xz,jkzk,kh,rxrq from xsjbxxlsb a left join view_njxyzybj d on a.xzb=d.bjmc and a.xy=d.xymc and a.dqszj=d.nj" +
                     " and a.zymc=d.zymc where a.dqszj='" + nd + "' and not exists(select 1 from xsxxb b where a.xh=b.xh) and xh is not null)";
		try {
			flag = StandardOperation.update("xsxxb", sql, request);
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
}
