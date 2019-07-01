/**
 * @部门:学工产品事业部
 * @日期：2016-12-26 上午09:42:29 
 */  
package xsgzgl.qgzx.zjdx.tjcx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.formula.functions.T;
import org.apache.struts.action.ActionForward;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.jfgl.QgzxJfglService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2016-12-26 上午09:42:29 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TjcxDAO extends SuperDAOImpl<TjcxForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TjcxForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(TjcxForm t, User user)
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
		this.setKey("id");
		this.setTableName("xg_qgzx_zjdx_cjffb");
		this.setClass(TjcxForm.class);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 校区报酬发放统计查询
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-26 上午09:54:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getXqbcFfTj(TjcxForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select t.dm,   ");
		sql.append(" t.xqmc,");
		sql.append(" count(id) rc,");
		sql.append(" nvl(sum(bcje), 0) bcje");
		sql.append(" from (select t.dm,");
		sql.append(" t.xqmc,");
		sql.append(" t1.id,");
		sql.append(" t1.bcje");
//		sql.append(" substr(t1.ffndyf, 0, 4) nd,");
//		sql.append(" substr(t1.ffndyf, 6, 2) yf");
		sql.append(" from dm_zju_xq t");
		sql.append(" left join (select *");
		sql.append(" from xg_qgzx_zjdx_cjffb");
		if(StringUtils.isNotNull(t.getYf())){
			sql.append(" where ffndyf = ? ||'-' || ?");
			paraList.add(t.getNd());
			paraList.add(t.getYf());
		}else{
			sql.append(" where ffndyf like ? ");
			paraList.add("%"+t.getNd()+"-"+"%");
		}
		sql.append(" and sftj = '1' ) t1");
		sql.append(" on t.dm = t1.xqdm) t ");
		sql.append(" group by t.dm, t.xqmc order by t.dm");
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 校区统计最后一行合计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-28 上午11:17:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getXqbcFfTjSum(TjcxForm t){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();

		sql.append(" select nvl(sum(bcje),0) bcje,nvl(count(1),0) rc from xg_qgzx_zjdx_cjffb where sftj = '1' ");
		if(StringUtils.isNotNull(t.getYf())){
			sql.append(" and ffndyf = ? ||'-' || ?");
			paraList.add(t.getNd());
			paraList.add(t.getYf());
		}else{
			sql.append(" and ffndyf like ? ");
			paraList.add("%"+t.getNd()+"-"+"%");
		}
		HashMap<String, String> dataMap = dao.getMapNotOut(sql.toString(),paraList.toArray(new String[]{}) );
		if(dataMap.isEmpty()){
			dataMap.put("rc", "0");
			dataMap.put("bcje", "0");
			
		}
		return dataMap;
	}
	
	/**
	 * 
	 * @描述:用人单位发放统计查询（横向按月展开）
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-26 下午04:44:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrdwFfTj(TjcxForm t) throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append("select * from (");
		sql.append(" select t.bmdm, ");
		sql.append(" t.bmmc,");
		sql.append(" t.bmlb,");
		sql.append(" nvl(t1.bcje, 0) janje,");
		sql.append(" nvl(t1.rc, 0) janrc,");
		sql.append(" nvl(t2.bcje, 0) febje,");
		sql.append(" nvl(t2.rc, 0) febrc,");
		sql.append(" nvl(t3.bcje, 0) marchje,");
		sql.append(" nvl(t3.rc, 0) marchrc,");
		sql.append(" nvl(t4.bcje, 0) aprje,");
		sql.append(" nvl(t4.rc, 0) aprrc,");
		sql.append(" nvl(t5.bcje, 0) mayje,");
		sql.append(" nvl(t5.rc, 0) mayrc,");
		sql.append(" nvl(t6.bcje, 0) junje,");
		sql.append(" nvl(t6.rc, 0) junrc,");
		sql.append(" nvl(t7.bcje, 0) julje,");
		sql.append(" nvl(t7.rc, 0) julrc,");
		sql.append(" nvl(t8.bcje, 0) augje,");
		sql.append(" nvl(t8.rc, 0) augrc,");
		sql.append(" nvl(t9.bcje, 0) sepje,");
		sql.append(" nvl(t9.rc, 0) seprc,");
		sql.append(" nvl(t10.bcje, 0) octje,");
		sql.append(" nvl(t10.rc, 0) octrc,");
		sql.append(" nvl(t11.bcje, 0) novje,");
		sql.append(" nvl(t11.rc, 0) novrc,");
		sql.append(" nvl(t12.bcje, 0) decmje,");
		sql.append(" nvl(t12.rc, 0) decmrc,");
		sql.append(" nvl(t1.bcje,0) + nvl(t2.bcje,0) + nvl(t3.bcje,0) + nvl(t4.bcje,0) + nvl(t5.bcje,0) + nvl(t6.bcje,0) +nvl(t7.bcje,0) + nvl(t8.bcje,0) + nvl(t9.bcje,0) + nvl(t10.bcje,0)  +nvl(t11.bcje,0) +nvl(t12.bcje,0) rowje,");
		sql.append(" nvl(t1.rc,0) + nvl(t2.rc,0) + nvl(t3.rc,0) + nvl(t4.rc,0) + nvl(t5.rc,0) + nvl(t6.rc,0) +nvl(t7.rc,0) + nvl(t8.rc,0) + nvl(t9.rc,0) + nvl(t10.rc,0)  +nvl(t11.rc,0) +nvl(t12.rc,0) rowrc");
		sql.append(" from (select t.bmdm,");
		sql.append(" t.bmmc,");
		sql.append(" t.bmlb,");
		sql.append(" '01' Jan,");
		sql.append(" '02' Feb,");
		sql.append(" '03' March,");
		sql.append(" '04' Apr,");
		sql.append(" '05' May,");
		sql.append(" '06' Jun,");
		sql.append(" '07' Jul,");
		sql.append(" '08' Aug,");
		sql.append(" '09' Sep,");
		sql.append(" '10' Oct,");
		sql.append(" '11' Nov,");
		sql.append(" '12' Decm,");
		sql.append(" ? nd");
		paraList.add(t.getNd());
		sql.append(" from ZXBZ_XXBMDM t) t");
		sql.append(" left join (view_yrdw_cj_tj) t1");
		sql.append(" on t.bmdm = t1.yrdwdm");
		sql.append(" and t.jan = t1.yf and t.nd = t1.nd");
		sql.append(" left join (view_yrdw_cj_tj) t2");
		sql.append(" on t.bmdm = t2.yrdwdm");
		sql.append(" and t.Feb = t2.yf and t.nd = t2.nd");
		sql.append(" left join (view_yrdw_cj_tj) t3");
		sql.append(" on t.bmdm = t3.yrdwdm");
		sql.append(" and t.March = t3.yf and t.nd = t3.nd");
		sql.append(" left join (view_yrdw_cj_tj) t4");
		sql.append(" on t.bmdm = t4.yrdwdm");
		sql.append(" and t.Apr = t4.yf and t.nd = t4.nd");
		sql.append(" left join (view_yrdw_cj_tj) t5");
		sql.append(" on t.bmdm = t5.yrdwdm");
		sql.append(" and t.May = t5.yf and t.nd = t5.nd");
		sql.append(" left join (view_yrdw_cj_tj) t6");
		sql.append(" on t.bmdm = t6.yrdwdm");
		sql.append(" and t.Jun = t6.yf and t.nd = t6.nd");
		sql.append(" left join (view_yrdw_cj_tj) t7");
		sql.append(" on t.bmdm = t7.yrdwdm");
		sql.append(" and t.Jul = t7.yf and t.nd = t7.nd");
		sql.append(" left join (view_yrdw_cj_tj) t8");
		sql.append(" on t.bmdm = t8.yrdwdm");
		sql.append(" and t.Aug = t8.yf and t.nd = t8.nd");
		sql.append(" left join (view_yrdw_cj_tj) t9");
		sql.append(" on t.bmdm = t9.yrdwdm");
		sql.append(" and t.Sep = t9.yf and t.nd = t9.nd");
		sql.append(" left join (view_yrdw_cj_tj) t10");
		sql.append(" on t.bmdm = t10.yrdwdm");
		sql.append(" and t.Oct = t10.yf and t.nd = t10.nd");
		sql.append(" left join (view_yrdw_cj_tj) t11");
		sql.append(" on t.bmdm = t11.yrdwdm");
		sql.append(" and t.Nov = t11.yf and t.nd = t11.nd");
		sql.append(" left join (view_yrdw_cj_tj) t12");
		sql.append(" on t.bmdm = t12.yrdwdm");
		sql.append(" and t.Decm = t12.yf and t.nd = t12.nd");
		sql.append(") t where rowrc != 0 ");
		if(StringUtils.isNotNull(t.getYrdwdm())){
			sql.append(" and bmdm = ?");
			paraList.add(t.getYrdwdm());
		}
		if(StringUtils.isNotNull(t.getBmlb())){
			sql.append(" and bmlb = ?");
			paraList.add(t.getBmlb());
		}
		sql.append(" order by bmmc ");
		return getPageList(t, sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 用人单位最后一行合计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-27 上午11:36:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getYrdwFfTjSum(TjcxForm t)throws Exception{
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select ");
		sql.append(" sum(janje) janje,");
		sql.append(" sum(janrc) janrc,");
		sql.append(" sum(febje) febje,");
		sql.append(" sum(febrc) febrc,");
		sql.append(" sum(marchje) marchje,");
		sql.append(" sum(marchrc) marchrc,");
		sql.append(" sum(aprje) aprje,");
		sql.append(" sum(aprrc) aprrc,");
		sql.append(" sum(mayje) mayje,");
		sql.append(" sum(mayrc) mayrc,");
		sql.append(" sum(junje) junje,");
		sql.append(" sum(junrc) junrc,");
		sql.append(" sum(julje) julje,");
		sql.append(" sum(julrc) julrc,");
		sql.append(" sum(augje) augje,");
		sql.append(" sum(augrc) augrc,");
		sql.append(" sum(sepje) sepje,");
		sql.append(" sum(seprc) seprc,");
		sql.append(" sum(octje) octje,");
		sql.append(" sum(octrc) octrc,");
		sql.append(" sum(novje) novje,");
		sql.append(" sum(novrc) novrc,");
		sql.append(" sum(decmje) decmje,");
		sql.append(" sum(decmrc) decmrc,");
		sql.append(" sum(rowje) rowje,");
		sql.append(" sum(rowrc) rowrc");
		sql.append(" from (");
		sql.append(" select t.bmdm, ");
		sql.append(" t.bmmc,");
		sql.append(" t.bmlb,");
		sql.append(" nvl(t1.bcje, 0) janje,");
		sql.append(" nvl(t1.rc, 0) janrc,");
		sql.append(" nvl(t2.bcje, 0) febje,");
		sql.append(" nvl(t2.rc, 0) febrc,");
		sql.append(" nvl(t3.bcje, 0) marchje,");
		sql.append(" nvl(t3.rc, 0) marchrc,");
		sql.append(" nvl(t4.bcje, 0) aprje,");
		sql.append(" nvl(t4.rc, 0) aprrc,");
		sql.append(" nvl(t5.bcje, 0) mayje,");
		sql.append(" nvl(t5.rc, 0) mayrc,");
		sql.append(" nvl(t6.bcje, 0) junje,");
		sql.append(" nvl(t6.rc, 0) junrc,");
		sql.append(" nvl(t7.bcje, 0) julje,");
		sql.append(" nvl(t7.rc, 0) julrc,");
		sql.append(" nvl(t8.bcje, 0) augje,");
		sql.append(" nvl(t8.rc, 0) augrc,");
		sql.append(" nvl(t9.bcje, 0) sepje,");
		sql.append(" nvl(t9.rc, 0) seprc,");
		sql.append(" nvl(t10.bcje, 0) octje,");
		sql.append(" nvl(t10.rc, 0) octrc,");
		sql.append(" nvl(t11.bcje, 0) novje,");
		sql.append(" nvl(t11.rc, 0) novrc,");
		sql.append(" nvl(t12.bcje, 0) decmje,");
		sql.append(" nvl(t12.rc, 0) decmrc,");
		sql.append(" nvl(t1.bcje,0) + nvl(t2.bcje,0) + nvl(t3.bcje,0) + nvl(t4.bcje,0) + nvl(t5.bcje,0) + nvl(t6.bcje,0) +nvl(t7.bcje,0) + nvl(t8.bcje,0) + nvl(t9.bcje,0) + nvl(t10.bcje,0)  +nvl(t11.bcje,0) +nvl(t12.bcje,0) rowje,");
		sql.append(" nvl(t1.rc,0) + nvl(t2.rc,0) + nvl(t3.rc,0) + nvl(t4.rc,0) + nvl(t5.rc,0) + nvl(t6.rc,0) +nvl(t7.rc,0) + nvl(t8.rc,0) + nvl(t9.rc,0) + nvl(t10.rc,0)  +nvl(t11.rc,0) +nvl(t12.rc,0) rowrc");
		sql.append(" from (select t.bmdm,");
		sql.append(" t.bmmc,");
		sql.append(" t.bmlb,");
		sql.append(" '01' Jan,");
		sql.append(" '02' Feb,");
		sql.append(" '03' March,");
		sql.append(" '04' Apr,");
		sql.append(" '05' May,");
		sql.append(" '06' Jun,");
		sql.append(" '07' Jul,");
		sql.append(" '08' Aug,");
		sql.append(" '09' Sep,");
		sql.append(" '10' Oct,");
		sql.append(" '11' Nov,");
		sql.append(" '12' Decm,");
		sql.append(" ? nd");
		paraList.add(t.getNd());
		sql.append(" from ZXBZ_XXBMDM t) t");
		sql.append(" left join (view_yrdw_cj_tj) t1");
		sql.append(" on t.bmdm = t1.yrdwdm");
		sql.append(" and t.jan = t1.yf and t.nd = t1.nd");
		sql.append(" left join (view_yrdw_cj_tj) t2");
		sql.append(" on t.bmdm = t2.yrdwdm");
		sql.append(" and t.Feb = t2.yf and t.nd = t2.nd");
		sql.append(" left join (view_yrdw_cj_tj) t3");
		sql.append(" on t.bmdm = t3.yrdwdm");
		sql.append(" and t.March = t3.yf and t.nd = t3.nd");
		sql.append(" left join (view_yrdw_cj_tj) t4");
		sql.append(" on t.bmdm = t4.yrdwdm");
		sql.append(" and t.Apr = t4.yf and t.nd = t4.nd");
		sql.append(" left join (view_yrdw_cj_tj) t5");
		sql.append(" on t.bmdm = t5.yrdwdm");
		sql.append(" and t.May = t5.yf and t.nd = t5.nd");
		sql.append(" left join (view_yrdw_cj_tj) t6");
		sql.append(" on t.bmdm = t6.yrdwdm");
		sql.append(" and t.Jun = t6.yf and t.nd = t6.nd");
		sql.append(" left join (view_yrdw_cj_tj) t7");
		sql.append(" on t.bmdm = t7.yrdwdm");
		sql.append(" and t.Jul = t7.yf and t.nd = t7.nd");
		sql.append(" left join (view_yrdw_cj_tj) t8");
		sql.append(" on t.bmdm = t8.yrdwdm");
		sql.append(" and t.Aug = t8.yf and t.nd = t8.nd");
		sql.append(" left join (view_yrdw_cj_tj) t9");
		sql.append(" on t.bmdm = t9.yrdwdm");
		sql.append(" and t.Sep = t9.yf and t.nd = t9.nd");
		sql.append(" left join (view_yrdw_cj_tj) t10");
		sql.append(" on t.bmdm = t10.yrdwdm");
		sql.append(" and t.Oct = t10.yf and t.nd = t10.nd");
		sql.append(" left join (view_yrdw_cj_tj) t11");
		sql.append(" on t.bmdm = t11.yrdwdm");
		sql.append(" and t.Nov = t11.yf and t.nd = t11.nd");
		sql.append(" left join (view_yrdw_cj_tj) t12");
		sql.append(" on t.bmdm = t12.yrdwdm");
		sql.append(" and t.Decm = t12.yf and t.nd = t12.nd");
		sql.append(") t where 1=1 ");
		if(StringUtils.isNotNull(t.getYrdwdm())){
			sql.append(" and bmdm = ?");
			paraList.add(t.getYrdwdm());
		}
		if(StringUtils.isNotNull(t.getBmlb())){
			sql.append(" and bmlb = ?");
			paraList.add(t.getBmlb());
		}
		return dao.getMapNotOut(sql.toString(), paraList.toArray(new String[]{}));
	}
	
	/**
	 * 
	 * @描述: 获取用人部门List集合
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-28 下午01:40:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getYrbmList(String bmlb){
		StringBuffer sql = new StringBuffer();
		List<String> paraList = new ArrayList<String>();
		sql.append(" select bmdm,bmpy||bmmc bmmc,bmjb,bmlb,bz from ( select bmdm,bmmc,f_pinyin(substr(bmmc,0,1))bmpy,bmjb,bmlb,bz from ZXBZ_XXBMDM order by bmmc ) ");
		if(StringUtils.isNotNull(bmlb)){
			sql.append(" where bmlb = ?");
			paraList.add(bmlb);
		}
		return dao.getListNotOut(sql.toString(),paraList.toArray(new String[]{}) );
	}
	
	/**
	 * @throws Exception 
	 * @描述: 学生勤工明细统计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-29 上午10:50:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getStudentQgDetailTjCx(TjcxForm t,User user) throws Exception{
		StringBuffer sql = new StringBuffer();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String userType = user.getUserType();
		QgzxJfglService service = new QgzxJfglService();
		
		String Fglyyh = " select sum(bcje) bcje,xh,yf,nd from (select substr(ffndyf,6,2) yf,xh,bcje,substr(ffndyf,0,4) nd from xg_qgzx_zjdx_cjffb where sftj = '1' ";
		if(!service.sfQggly(user.getUserName())){
			Fglyyh+=" and yrdwdm = '"+user.getUserDep()+"' ";
		} else {
			String[] bmV = t.getSearchModel().getSearch_tj_bm();
			// 存在条件
			if (bmV != null && bmV.length > 0) {
				Fglyyh+=" and yrdwdm in (";
				for (int j = 0; j < bmV.length; j++) {
					Fglyyh+= "'" + bmV[j] + "'";
					if(j != bmV.length - 1){
						Fglyyh+= ",";
					}
				}
				Fglyyh+=")";
			}
		}
		Fglyyh+= " ) group by xh,yf,nd ";
		
		sql.append(" select  t.* from ( ");
		sql.append(" select distinct t.xh, ");
		sql.append(" t.xm, ");
		sql.append(" t.nd, ");
		sql.append(" t.nj, ");
		sql.append(" t.bjmc, ");
		sql.append(" t.bjdm, ");
		sql.append(" t.zymc, ");
		sql.append(" t.zydm, ");
		sql.append(" t.xydm, ");
		sql.append(" t.xymc, ");
		sql.append(" nvl(t1.bcje, 0) janje, ");
		sql.append(" nvl(t2.bcje, 0) febje, ");
		sql.append(" nvl(t3.bcje, 0) marchje, ");
		sql.append(" nvl(t4.bcje, 0) aprje, ");
		sql.append(" nvl(t5.bcje, 0) mayje, ");
		sql.append(" nvl(t6.bcje, 0) junje, ");
		sql.append(" nvl(t7.bcje, 0) julje, ");
		sql.append(" nvl(t8.bcje, 0) augje, ");
		sql.append(" nvl(t9.bcje, 0) sepje, ");
		sql.append(" nvl(t10.bcje, 0) octje, ");
		sql.append(" nvl(t11.bcje, 0) novje, ");
		sql.append(" nvl(t12.bcje, 0) decmje, ");
		sql.append(" nvl(t1.bcje,0) + nvl(t2.bcje,0) + nvl(t3.bcje,0) + nvl(t4.bcje,0) + nvl(t5.bcje,0) + nvl(t6.bcje,0) +nvl(t7.bcje,0) + nvl(t8.bcje,0) + nvl(t9.bcje,0) + nvl(t10.bcje,0)  +nvl(t11.bcje,0) +nvl(t12.bcje,0) rowje ");
		sql.append(" from ( ");
		sql.append(" select distinct xh,xm,nd,yf,XYDM,XYMC,ZYDM,ZYMC,NJ,BJDM,BJMC,Jan,Feb,March,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Decm from (select t.xh,t.yrdwdm, ");
		sql.append(" t.xm, ");
		sql.append(" substr(t.ffndyf,0,4) nd, ");
		sql.append(" substr(t.ffndyf,6,2) yf, ");
		sql.append(" t1.XYDM, ");
		sql.append(" t1.XYMC, ");
		sql.append(" t1.ZYDM, ");
		sql.append(" t1.ZYMC, ");
		sql.append(" t1.NJ, ");
		sql.append(" t1.BJDM, ");
		sql.append(" t1.BJMC, ");
		sql.append(" '01' Jan, ");
		sql.append(" '02' Feb, ");
		sql.append(" '03' March, ");
		sql.append(" '04' Apr, ");
		sql.append(" '05' May, ");
		sql.append(" '06' Jun, ");
		sql.append(" '07' Jul, ");
		sql.append(" '08' Aug, ");
		sql.append(" '09' Sep, ");
		sql.append(" '10' Oct, ");
		sql.append(" '11' Nov, ");
		sql.append(" '12' Decm ");
		sql.append(" from xg_qgzx_zjdx_cjffb t ");
		sql.append(" left join view_xsxxb t1 ");
		sql.append(" on t.xh = t1.XH ");
		/* 勤工管理员可以看到所有数据范围
		 * 部门人员只能看到本用人单位学生数据
		 * 学生只能看到自己的数据
		 */
		if("stu".equalsIgnoreCase(userType)){
			sql.append(" where t.xh = '"+user.getUserName()+"' ");
		}else if(!service.sfQggly(user.getUserName())){
			sql.append(" where t.yrdwdm = '"+user.getUserDep()+"' ");
		}
		sql.append(" ) x where 1=1 ");
		sql.append(searchTj);
		sql.append(" ) t");
		sql.append(" left join ("+Fglyyh+") t1 ");
		sql.append(" on t.xh = t1.XH and t.nd = t1.nd and t.Jan = t1.yf ");
		sql.append(" left join ("+Fglyyh+") t2 ");
		sql.append(" on t.xh = t2.XH and t.nd = t2.nd and t.Feb = t2.yf ");
		sql.append(" left join ("+Fglyyh+") t3 ");
		sql.append(" on t.xh = t3.XH and t.nd = t3.nd and t.March = t3.yf ");
		sql.append(" left join ("+Fglyyh+") t4 ");
		sql.append(" on t.xh = t4.XH and t.nd = t4.nd and t.Apr = t4.yf ");
		sql.append(" left join ("+Fglyyh+") t5 ");
		sql.append(" on t.xh = t5.XH and t.nd = t5.nd and t.May = t5.yf ");
		sql.append(" left join ("+Fglyyh+") t6 ");
		sql.append(" on t.xh = t6.XH and t.nd = t6.nd and t.Jun = t6.yf ");
		sql.append(" left join ("+Fglyyh+") t7 ");
		sql.append(" on t.xh = t7.XH and t.nd = t7.nd and t.Jul = t7.yf ");
		sql.append(" left join ("+Fglyyh+") t8 ");
		sql.append(" on t.xh = t8.XH and t.nd = t8.nd and t.Aug = t8.yf ");
		sql.append(" left join ("+Fglyyh+") t9 ");
		sql.append(" on t.xh = t9.XH and t.nd = t9.nd and t.Sep = t9.yf ");
		sql.append(" left join ("+Fglyyh+") t10 ");
		sql.append(" on t.xh = t10.XH and t.nd = t10.nd and t.Oct = t10.yf ");
		sql.append(" left join ("+Fglyyh+") t11 ");
		sql.append(" on t.xh = t11.XH and t.nd = t11.nd and t.Nov = t11.yf ");
		sql.append(" left join ("+Fglyyh+") t12 ");
		sql.append(" on t.xh = t12.XH and t.nd = t12.nd and t.Decm = t12.yf ");
		sql.append(" ) t where 1=1 ");
		sql.append(" order by xh,xydm,bjdm,nj ");
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @描述: 学生个人勤工明细合计
	 * @作者：yxy[工号：1206]
	 * @日期：2016-12-29 上午11:27:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getStudentQgDetailTjCxSum(TjcxForm t,User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		//String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		HashMap<String, String> yfdzMap = new HashMap<String, String>();
		/**
		 * 构建月份对应的键值对
		 */
		yfdzMap.put("jan", "01");
		yfdzMap.put("feb", "02");
		yfdzMap.put("march","03");
		yfdzMap.put("apr", "04");
		yfdzMap.put("may", "05");
		yfdzMap.put("jun", "06");
		yfdzMap.put("jul", "07");
		yfdzMap.put("aug", "08");
		yfdzMap.put("sep", "09");
		yfdzMap.put("oct", "10");
		yfdzMap.put("nov", "11");
		yfdzMap.put("decm", "12");
		
		sql.append(" select yf,sum(bcje) je from (");
		sql.append(" select t.*,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.NJ,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.ZYMC,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.BJMC,");
		sql.append(" substr(t.ffndyf,6,2) yf,");
		sql.append(" substr(t.ffndyf,0,4) nd");
		sql.append(" from xg_qgzx_zjdx_cjffb t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH where t.sftj = '1' ) t where 1=1");
		sql.append(searchTj);
		//权限管理
		QgzxJfglService service = new QgzxJfglService();
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			sql.append(" and t.xh = '"+user.getUserName()+"' ");
		}else if(!service.sfQggly(user.getUserName())){
			sql.append(" and t.yrdwdm = '"+user.getUserDep()+"' ");
		}
		sql.append(" group by yf ");
		List<HashMap<String, String>> dataList = dao.getListNotOut(sql.toString(), inputV);
		Set<String> set = yfdzMap.keySet();
	    Iterator<String> it = set.iterator();
	    HashMap<String, String> dataMap = new HashMap<String, String>();
	    /**
	     * 手动构建hashmap,即最后一行总计行
	     */
		if(dataList.isEmpty()){
			/**
			 * 假如dataList为空人为置入
			 */
			  
			    while(it.hasNext())
			    {
			       String key = it.next();
			       dataMap.put(key, "0");
			     }
		}else{
			for (HashMap<String, String> map : dataList) {
				dataMap.put(map.get("yf"),map.get("je") );
			}
			float row = 0;
		    while(it.hasNext())
		    {
		    
		       String key = it.next();
		       String value = yfdzMap.get(key);
		       /**
		        * 判断：如果datamap中不存在该月份，那么该月份是不存在的，手动置为0
		        */
		       if(StringUtils.isNull(dataMap.get(value))){
		    	   dataMap.put(key, "0");
		       }else{
		    	   row = row + Float.parseFloat(dataMap.get(value));
		    	   dataMap.put(key, dataMap.get(value));
		       }
		     }
		    dataMap.put("row", row+"");
		}
		return dataMap;
	}
	
	/**
	 * @描述: 个人报酬发放统计查询页面
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:02:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getStucjffgrtj(TjcxForm t,User user) throws Exception{
		StringBuffer sql = new StringBuffer();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from ( ");
		sql.append(" select a.xh, a.xm, b.bmmc, a.bcje, a.bz, a.yrdwdm, b.bmdm xydm, substr(a.ffndyf,0,4)nd,substr(a.ffndyf,6,2)yf ");
		sql.append(" from xg_qgzx_zjdx_cjffb a ");
		sql.append(" left join zxbz_xxbmdm b on a.yrdwdm = b.bmdm  ");
		sql.append(" where a.sftj = '1' order by b.bmmc,a.lrsj,a.xh ");
		sql.append(" )t where 1 = 1 ");
		
		//管理员、非管理员、学生权限的数据
		QgzxJfglService service = new QgzxJfglService();
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			sql.append(" and t.xh = '"+user.getUserName()+"' ");
		}else if(!service.sfQggly(user.getUserName())){
			sql.append(" and t.yrdwdm = '"+user.getUserDep()+"' ");
		}
		
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 个人报酬发放统计根据高级查询条件计算报酬发放总数
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:08:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getStucjffgrtjSum(TjcxForm t,User user) throws Exception{
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(nvl(bcje,0))zje from ( ");
		sql.append(" select * from ( ");
		sql.append(" select a.xh, a.xm, b.bmmc, a.bcje, a.bz, a.yrdwdm,b.bmdm xydm,substr(a.ffndyf,0,4)nd,substr(a.ffndyf,6,2)yf ");
		sql.append(" from xg_qgzx_zjdx_cjffb a ");
		sql.append(" left join zxbz_xxbmdm b ");
		sql.append(" on a.yrdwdm = b.bmdm ");
		sql.append("  where a.sftj = '1') t where 1 = 1 ");
		
		//管理员、非管理员、学生权限的数据
		QgzxJfglService service = new QgzxJfglService();
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			sql.append(" and t.xh = '"+user.getUserName()+"' ");
		}else if(!service.sfQggly(user.getUserName())){
			sql.append(" and t.yrdwdm = '"+user.getUserDep()+"' ");
		}
		sql.append(searchTj);
		sql.append(" ) where 1= 1 ");
		return dao.getMapNotOut(sql.toString(),inputV);
	}
	
	/**
	 * @描述: 个人报酬发放统计导出
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2017-1-18 上午11:23:22
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * ActionForward 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> exportDataStucjffgrtj(TjcxForm t,User user) throws Exception{
		StringBuffer sql = new StringBuffer();
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		sql.append(" select * from ( ");
		sql.append(" select a.xh, a.xm, b.bmmc, a.bcje, a.bz, a.yrdwdm, b.bmdm xydm, substr(a.ffndyf,0,4)nd,substr(a.ffndyf,6,2)yf ");
		sql.append(" from xg_qgzx_zjdx_cjffb a ");
		sql.append(" left join zxbz_xxbmdm b on a.yrdwdm = b.bmdm  ");
		sql.append(" where a.sftj = '1' order by b.bmmc,a.lrsj,a.xh ");
		sql.append(" )t where 1 = 1 ");
		
		//管理员、非管理员、学生权限的数据
		QgzxJfglService service = new QgzxJfglService();
		String userType = user.getUserType();
		if("stu".equalsIgnoreCase(userType)){
			sql.append(" and t.xh = '"+user.getUserName()+"' ");
		}else if(!service.sfQggly(user.getUserName())){
			sql.append(" and t.yrdwdm = '"+user.getUserDep()+"' ");
		}
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 取参数设置表中的月份
	 * @作者： Meng.Wei[工号：1186]
	 * @日期：2017-1-20 下午01:55:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getCsszYf() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select substr(ksyf,5.2)yf from xg_qgzx_csszb ");
		return dao.getOneRs(sql.toString(), new String[] {}, "yf");
	}
}
