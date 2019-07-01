package xsgzgl.gygl.rcjc.xswsjc;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import xgxt.DAO.DAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.List;

public class XswsjcDao extends SuperDAOImpl<XswsjcForm> {
	
	@Override
	protected void setTableInfo() { //仅用作提示，不调用相关方法
		super.setTableName("XG_GYGL_NEW_WSJC_XSFSB");
		super.setKey("jcrcid,xh");
		super.setClass(XswsjcForm.class);
	}
	
	
	@Override
	public List<HashMap<String, String>> getPageList(XswsjcForm model,User user) throws Exception {
		SearchModel searchmodel = model.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchmodel);
		String[] inputV = SearchService.getTjInput(searchmodel);
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (select a.*,b.jcrq,d.fdyxm,c.bjdm,c.bjmc,c.zydm,c.zymc,c.xydm,c.xymc,c.nj,c.xm,c.pycc,s.pyccmc,e.lddm,e.ldmc,c.yxdm xqdm,r.xqmc,f.yqdm,f.yqmc,g.cwh,g.qsh,b.jcbm ");
		sql.append("from XG_GYGL_NEW_WSJC_XSFSB a ");
		sql.append("left join Xg_Gygl_New_Cwxxb g on a.xh=g.xh ");
		sql.append("left join Xg_Gygl_New_Wsjc_Qsfsb b on a.jcrcid = b.guid and b.lddm=g.lddm and b.qsh=g.qsh ");
		sql.append("left join view_xsjbxx c on a.xh=c.xh ");
		sql.append("left join (select a.bjdm, WM_CONCAT(b.xm) fdyxm  from fdybjb a ");
		sql.append("left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) d on c.bjdm = d.bjdm ");
		sql.append("left join XG_GYGL_NEW_LDXXB e on b.lddm=e.lddm ");
		sql.append("left join zxbz_ssyqdm f on e.yqdm=f.yqdm ");
		sql.append("left join DM_ZJU_XQ r on r.dm=c.yxdm ");
		sql.append("left join xg_xsxx_pyccdmb s on c.pycc = s.pyccdm ");
		sql.append(" order by f.yqdm,e.lddm,g.qsh,g.cwh ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(XswsjcForm t) throws Exception {
		return null;
	}

	/** 
	 * @描述：
	 * @throws Exception 
	 * @作者：卓耐[工号:1391]
	 * @日期：2017年5月11日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * boolean 返回类型 
	 */
	public boolean updateXswsjc(XswsjcForm myForm) throws Exception {
		if(StringUtils.isNull(myForm.getJcrcid())||StringUtils.isNull(myForm.getXh())){
			return false;
		}
		StringBuilder sql=new StringBuilder();
		sql.append("update XG_GYGL_NEW_WSJC_XSFSB set fs=? ,djbz=? where jcrcid=? and xh=? ");
		return dao.runUpdate(sql.toString(), new String[]{myForm.getFs(),myForm.getDjbz(),myForm.getJcrcid(),myForm.getXh()});
	}

	
	@Override
	public XswsjcForm getModel(XswsjcForm t) throws Exception {
		String sql=" select * from XG_GYGL_NEW_WSJC_XSFSB where jcrcid=? and xh= ?  and rownum='1' ";
		return getModel(new XswsjcForm(),sql,new String[]{t.getJcrcid(),t.getXh()});
	}
	
	
	/**
	 * @描述：中医药学生文明督察情况通报表
	 * @作者：zhuon[工号:1391]
	 * @日期：2017年8月30日
	 * @修改记录: 修改者名字-修改日期-修改内容
	 */
	public List<HashMap<String,String>> getWsjcdj_10344(String jcrqSta,String jcrqEnd,User user){

		String userStatus = user.getUserStatus();
		String userDep = user.getUserDep();
		String yhsjfwSql;
		if(!"xx".equalsIgnoreCase(userStatus)){
			yhsjfwSql = "and xydm = '" + userDep + "' ";
		}else {
			yhsjfwSql = "";
		}

		if(StringUtils.isNull(jcrqSta)){
			jcrqSta="00000000";
		}
		if(StringUtils.isNull(jcrqEnd)){
			jcrqEnd="20991230";
		}
		StringBuffer sql =new StringBuffer();
		sql.append("select * from ( ");
		sql.append(" select decode(fdyxm,'nullfdy','学院总评',xymc) xymc0,decode(fdyxm,'nullfdy','',fdyxm) fdyxm0,t.* from ");
		sql.append(" (select R,m.xydm,m.xymc,m.fdyxm,q.pyccmc,r.xqmc,n.wsdjd,n.wsdjc,n.wsdjb,n.wsdja,n.wsyxl,n.wsbhgl,o.ydxsrs,o.sjcqrs,o.cql,p.ybgs,p.wg from ");
		//学院、辅导员、培养层次、校区 主表
		sql.append(" (select rownum R,nvl(xydm,'nullxy')xydm,xymc,nvl(fdyxm,'nullfdy')fdyxm,nvl(pycc,'nullpycc')pycc,nvl(yxdm,'nullpycc')yxdm from ");
		sql.append(" (select b.xydm,b.xymc,a.fdyxm,b.pycc,b.yxdm ");
		sql.append(" from (select a.bjdm, WM_CONCAT(b.xm) fdyxm from FDYBJB a left join FDYXXB b on a.zgh = b.zgh group by a.bjdm) a ");
		sql.append(" left join VIEW_ZXSJBXX b on a.bjdm=b.bjdm ");
		sql.append(" where b.xydm is not null and b.pycc is not null and b.yxdm is not null and a.fdyxm is not null ");
		sql.append(" group by rollup ((b.xydm,b.xymc),(a.fdyxm,b.pycc,b.yxdm)))) m ");
		sql.append(" left join ");
		//卫生检查等级表
		sql.append(" (select nvl(xydm,'nullxy')xydm,xymc,nvl(fdyxm,'nullfdy')fdyxm,nvl(pycc,'nullpycc')pycc, ");
		sql.append(" nvl(yxdm,'nullpycc')yxdm,wsdjd,wsdjc,wsdjb,wsdja,wsyxl,wsbhgl from ");
		sql.append(" (select t.*,to_char(t.wsdjA / rs*100,'fm990.00')||'%' wsyxl,to_char(t.wsdjD / rs*100,'fm990.00')||'%'wsbhgl from ");
		sql.append(" (select sum(1) rs, xydm, xymc, yxdm, fdyxm, pycc, ");
		sql.append(" sum(decode(instr(fs,'A'),0,0,1)) wsdjA, ");
		sql.append(" sum(decode(instr(fs,'B'),0,0,1)) wsdjB, ");
		sql.append(" sum(decode(instr(fs,'C'),0,0,1)) wsdjC, ");
		sql.append(" sum(decode(instr(fs,'D'),0,0,1)) wsdjD ");
		sql.append(" from (select a.xh, b.jcrq, d.fdyxm, e.bjdm, e.xydm, e.xymc, c.xm, c.yxdm, g.cwh, ");
		sql.append(" g.qsh, c.pycc, a.fs ");
		sql.append(" from XG_GYGL_NEW_WSJC_XSFSB a ");
		sql.append(" left join XG_GYGL_NEW_CWXXB g on a.xh = g.xh ");
		sql.append(" left join XG_GYGL_NEW_WSJC_QSFSB b on a.jcrcid = b.guid and b.lddm = g.lddm and b.qsh = g.qsh ");
		sql.append(" left join XSXXB c on a.xh = c.xh ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) fdyxm from FDYBJB a left join FDYXXB b on a.zgh = b.zgh group by a.bjdm) d on c.bjdm = d.bjdm ");
		sql.append(" left join VIEW_NJXYZYBJ_ALL e on e.bjdm = c.bjdm ");
		sql.append(" where c.pycc is not null and c.yxdm is not null and e.xydm is not null and d.fdyxm is not null and a.fs != 'N') ");
		sql.append(" where jcrq >= '"+jcrqSta+"' and jcrq <= '"+jcrqEnd+"' ");
		sql.append(" group by rollup ((xydm, xymc), (yxdm, fdyxm, pycc))) t)) n ");
		sql.append(" on m.xydm=n.xydm and m.fdyxm=n.fdyxm and m.pycc=n.pycc and m.yxdm=n.yxdm ");
		sql.append(" left join ");
		//上课出勤率表
		sql.append(" (select nvl(xydm,'nullxy')xydm,nvl(fdyxm,'nullfdy')fdyxm,nvl(pycc,'nullpycc')pycc, ");
		sql.append(" nvl(yxdm,'nullpycc')yxdm,ydxsrs,sjcqrs,to_char(sjcqrs/ydxsrs*100,'fm990.00')||'%' cql from ");
		sql.append(" (select b.xydm,c.fdyxm,b.pycc,b.yxdm,sum(a.ydxsrs)ydxsrs,sum(a.sjcqrs)sjcqrs from xg_szdw_xfjsb a ");
		sql.append(" left join (select bjdm,max(xydm)xydm,max(pycc)pycc,max(yxdm)yxdm from VIEW_ZXSJBXX group by bjdm)b on a.bjdm=b.bjdm ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) fdyxm from FDYBJB a ");
		sql.append(" left join FDYXXB b on a.zgh = b.zgh group by a.bjdm) c on c.bjdm = a.bjdm ");
		sql.append(" where a.jcsj>=to_char(to_date('"+jcrqSta+"','yyyyMMdd'),'yyyy-MM-dd') and a.jcsj<=to_char(to_date('"+jcrqEnd+"','yyyyMMdd'),'yyyy-MM-dd') and fdyxm is not null ");
		sql.append(" group by rollup (b.xydm,(c.fdyxm,b.pycc,b.yxdm)))) o ");
		sql.append(" on m.xydm=o.xydm and m.fdyxm=o.fdyxm and m.pycc=o.pycc and m.yxdm=o.yxdm ");
		sql.append(" left join ");
		//夜不归宿、晚归表
		sql.append(" (select nvl(xydm,'nullxy')xydm,nvl(fdyxm,'nullfdy')fdyxm,nvl(pycc,'nullpycc')pycc, ");
		sql.append(" nvl(yxdm,'nullpycc')yxdm,to_number(substr(ybgswg, -10, 5)) ybgs,to_number(substr(ybgswg, -5, 5)) wg from ( ");
		sql.append(" select xydm,fdyxm,pycc,yxdm,lpad(sum(ybgswg),10,'0')ybgswg from ");
		sql.append(" (select a.xh,case when b.rcxwlbmc like '%夜不归宿%' then '100000' when b.rcxwlbmc like '%晚归%' then '1' end ybgswg, ");
		sql.append(" c.xydm,c.pycc,c.yxdm,d.fdyxm from XG_RCSW_RCXWJG a ");
		sql.append(" left join XG_RCSW_RCXWLBDMB b on a.rcxwlbdm = b.rcxwlbdm ");
		sql.append(" left join VIEW_ZXSJBXX c on a.xh=c.xh ");
		sql.append(" left join (select a.bjdm, WM_CONCAT(b.xm) fdyxm from FDYBJB a ");
		sql.append(" left join FDYXXB b on a.zgh = b.zgh group by a.bjdm) d on c.bjdm=d.bjdm ");
		sql.append(" where a.fssj>=to_char(to_date('"+jcrqSta+"','yyyyMMdd'),'yyyy-MM-dd') and a.fssj<=to_char(to_date('"+jcrqEnd+"','yyyyMMdd'),'yyyy-MM-dd') ");
		sql.append(" and d.fdyxm is not null and c.pycc is not null and c.yxdm is not null) ");
		sql.append(" group by rollup (xydm,(fdyxm,pycc,yxdm)))) p ");
		sql.append(" on m.xydm=p.xydm and m.fdyxm=p.fdyxm and m.pycc=p.pycc and m.yxdm=p.yxdm ");
		//培养层次代码表、校区代码表
		sql.append(" left join XG_XSXX_PYCCDMB q on q.pyccdm=m.pycc ");
		sql.append(" left join DM_ZJU_XQ r on r.dm=m.yxdm)t ");
		sql.append(" where xydm <> 'nullxy' ");
		sql.append(" )where 1=1 ");
		sql.append(yhsjfwSql);
		sql.append("  and (wsdja is not null or ydxsrs is not null or ybgs is not null) order by R ");
		return DAO.getInstance().getListNotOut(sql.toString(), new String[]{});
	}

}
