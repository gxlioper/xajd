/**
 * @部门:学工产品事业部
 * @日期：2013-8-6 下午03:21:09 
 */
package com.zfsoft.xgxt.gygl.gypy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


import xgxt.action.Base;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Arrays2;
import xgxt.utils.String.StringUtils;
import xsgzgl.gygl.comm.GyglNewService;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理-公寓评优
 * @类功能描述: (公寓评优)
 * @作者： 张昌路[工号:982]
 * @时间： 2013-8-20 下午04:17:49
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class GypyDAO extends SuperDAOImpl<GypyForm> {

	@Override
	public List<HashMap<String, String>> getPageList(GypyForm t)
			throws Exception {
		return getData(t, true);
	}
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-22 下午09:14:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param isOpenPage
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getData(GypyForm t,boolean isOpenPage) throws Exception {
		List<HashMap<String, String>> list = null;

		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer base=new StringBuffer();
		base.append(" select gypyid,xn,xqdm,pylx,pyly,gldm,lddm,case when ch<0 then 'B'||replace(ch,'-','') else ch end ch,xydm,qsh from xg_gygl_new_gypy ");
		//获取关联楼信息
		if (GypyService._WMSS.equals(t.getPylx())
				|| StringUtils.isNull(t.getPylx())) {
			inputV=Arrays2.addObject(inputV, GypyService._WMSS);
			base.append(" where 1=1");
		}
		//获取关联辅导员信息
		if (GypyService._YXFDY.equals(t.getPylx())) {
			inputV=Arrays2.addObject(inputV, GypyService._YXFDY);
			base.append("a,(select zgh,xm,xb,bmdm,(select bmmc from zxbz_xxbmdm e where b.bmdm=e.bmdm)bmmc,(select xb from dmk_xb e where b.xb=e.xbdm) xbmc");
			base.append(" from fdyxxb b) c");
			base.append(" where c.zgh=a.gldm");
			
		}
		//获取关联学生信息
		if (GypyService._YXXS.equals(t.getPylx())) {
			inputV=Arrays2.addObject(inputV, GypyService._YXXS);
			base.delete(0,base.length());
			base.append("select * from(select a.gypyid,a.xn,a.xqdm,a.pylx,a.pyly,a.gldm,a.ch,b.xh,b.xm,b.xb,b.ldmc,b.lddm,b.qsh,b.xydm,b.xymc from xg_gygl_new_gypy ");
			base.append("a,xg_view_gygl_new_xszsgl b where a.gldm=b.xh) where 1=1 ");
			base.append(t.getOtherFilter());
		}
		base.append(searchTj);
		base.append(" and pylx=?");
		//是否开启分页
		if(isOpenPage){
			list = getPageList(t, base.toString(), inputV);
		}else{
			list = dao.getListNotOut(base.toString(), inputV);
		}
		for (HashMap<String, String> hm : list) {
			this.setXyXqLdForDm(hm);
			this.setFdyxx(hm);
		}
		return list;
	}
	/**
	 * 
	 * @描述:根据id获取整行信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-21 下午04:45:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String>  getModelMapForId(String id){
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_gygl_new_gypy where GYPYID=?");
		return dao.getMapNotOut(sb.toString(), new String[]{id});
	}
	/**
	 * 
	 * @描述:设置辅导员信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-23 上午11:02:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param dm
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> setFdyxx(HashMap<String, String> dm) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select a.*, rownum r  ");
		sql.append(" from (select zgh,xm,xb,bmdm,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc,(select xb from dmk_xb e where a.xb=e.xbdm) xbmc");
		sql.append(" from fdyxxb a) a where 1 = 1");
		sql.append(" and zgh=?");
		//根据职工号获取辅导员详细信息
		HashMap<String, String> hm=dao.getMapNotOut(sql.toString(),new String[]{dm.get("gldm")});
		dm.putAll(hm);
		return hm;
	}
	/**
	 * 
	 * @描述:设置学院学期楼栋名称
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-21 上午10:05:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> setXyXqLdForDm(HashMap<String, String> dm) {
		String xydm = dm.get("xydm");
		String lddm = dm.get("lddm");
		String xqdm = dm.get("xqdm");
		HashMap<String, String> addMap = new HashMap<String, String>();
		// 获取学院名称
		for (HashMap<String, String> hm : Base.getXyList()) {
			if (hm.get("xydm").equals(xydm)) {
				addMap.put("xymc", hm.get("xymc"));
				break;
			}
		}
		// 获取学期名称
		for (HashMap<String, String> hm : Base.getXqList()) {
			if (hm.get("xqdm").equals(xqdm)) {
				addMap.put("xqmc", hm.get("xqmc"));
				break;
			}
		}
		// 获取楼栋名称
		for (HashMap<String, String> hm : getLdList()) {
			if (hm.get("lddm").equals(lddm)) {
				addMap.put("ldmc", hm.get("ldmc"));
				break;
			}
		}
		dm.putAll(addMap);
		return dm;
	}

	@Override
	public List<HashMap<String, String>> getPageList(GypyForm t, User user)
			throws Exception {
		return null;
	}

	/**
	 * 
	 * @描述:获取辅导员详细信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-20 下午05:23:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getFdyxx(String id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*, rownum r  ");
		sql
				.append(" from (select zgh,xm,xb,bmdm,(select bmmc from zxbz_xxbmdm e where a.bmdm=e.bmdm)bmmc,(select xb from dmk_xb e where a.xb=e.xbdm) xbmc");
		sql.append(" from fdyxxb a) a where 1 = 1 and zgh=?");
		return dao.getMapNotOut(sql.toString(), new String[] { id });
	}

	/**
	 * 
	 * @描述:获取楼栋list
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-21 上午10:20:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getLdList() {
		StringBuilder sql = new StringBuilder();
		sql.append("select lddm,ldmc from xg_gygl_new_ldxxb");
		return dao.getListNotOut(sql.toString(), null);
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_gygl_new_gypy");
		super.setKey("gypyid");
		super.setClass(GypyForm.class);
	}

	/**
	 * 获得指定楼栋指定层数下最大的寝室号
	 * 
	 * @param lddm
	 * @param ch
	 * @return
	 */
	public Map<String, String> getMaxQsh(String lddm, String ch) {
		String sql = "select max(qsh) maxQsh from xg_gygl_new_qsxxb where lddm=? and ch=?";
		return dao.getMapNotOut(sql, new String[] { lddm, ch });
	}
	public Map<String, String> getQsxx(String lddm, String ch,String qsh) {
		StringBuffer sql = new StringBuffer("select * from xg_gygl_new_qsxxb where 1=1");
		List<String> param=new ArrayList<String>();
		if(StringUtils.isNotNull(lddm)){
			sql.append(" and lddm=?");
			param.add(lddm);
		}
		if(StringUtils.isNotNull(ch)){
			sql.append(" and ch=?");
			param.add(ch);
		}
		if(StringUtils.isNotNull(qsh)){
			sql.append(" and qsh=?");
			param.add(qsh);
		}
		return dao.getMapNotOut(sql.toString(), param.toArray(new String[param.size()]));
	} 
	/**
	 * 
	 * @描述:获得学生详细信息-楼栋寝室等
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-22 下午05:15:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getXsxx(String xh){
		String[] inputValue = new String[]{xh};
		StringBuilder sql = new StringBuilder();
		String[] outputValue = new String[] { "xh","xh","xm","xb","nj","xymc","bjmc","zymc","ldmc","qsh","cwh","qsdh" };
		sql.append("select a.* from xg_view_gygl_new_xszsgl a where sfzs='是' and xh = ? order by xh asc");
		return dao.getMap(sql.toString(), inputValue, outputValue);
	}
	/**
	 * 
	 * @描述:根据Form对象属性 获取数据库对应对象属性（所有）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-23 上午10:57:11
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gf	对象
	 * @param bindKey 要关联的对象属性(如果为空验证全部)
	 * @return		
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDataForBeFindEntity(GypyForm gf,String[] bindKey) throws Exception{
		StringBuffer sb=new StringBuffer();
		sb.append("select * from xg_gygl_new_gypy");
		sb.append(" where 1=1");
		List<String> param=new LinkedList<String>();
		if(null==bindKey){
			bindKey=dao.getColumnName(sb.toString());
		}
		for(String str:bindKey){
			String keyValue = (String) gf.getClass().getMethod("get"+str.substring(0, 1).toUpperCase()+str.substring(1).toLowerCase()).invoke(gf);
			if(StringUtils.isNotNull(keyValue)){
				sb.append(" and ");
				sb.append(str);
				sb.append("=?");
				param.add(keyValue);
			}
		}
		return dao.getListNotOut(sb.toString(),param.toArray(new String[param.size()]));
	}
	/**
	 * @描述:根据Form对象属性 获取数据库对应对象属性（所有）
	 * 		 <br>暂不支持Form中“大”写属性（xxxId,要求属性全部为小写）
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-23 上午10:59:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gf
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDataForBeFindEntity(GypyForm gf) throws Exception{
		return this.getDataForBeFindEntity(gf,null);
	}
}
