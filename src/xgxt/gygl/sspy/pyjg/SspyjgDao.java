/**
 * @部门:学工产品(1)部
 * @日期：2018-4-28 上午09:25:32 
 */  
package xgxt.gygl.sspy.pyjg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 公寓管理管理模块
 * @类功能描述: 宿舍评优结果
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2018-4-28 上午09:24:38 
 * @版本： V5.18.26
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SspyjgDao extends SuperDAOImpl<SspyjgForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SspyjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SspyjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.guid,a.xh,a.sqsj,a.xn,a.xq,c.xqmc,a.lddm,b.ldmc,a.qsh,a.pyxmdm,e.pyxmmc, ");
		sql.append("a.sjly,a.filepath,a.sqly,a.bz,a.ywid,a.sjlrsj,a.sjlrr,f.xm sjlrrxm ");
		sql.append("from xg_gygl_sspyjgb a ");
		sql.append("left join view_xg_gygl_new_qsxx b on a.qsh = b.qsh and b.lddm = a.lddm ");
		sql.append("left join xqdzb c on a.xq = c.xqdm ");
		sql.append("left join view_xsxxb d on a.xh = d.xh ");
		sql.append("left join xg_gygl_new_sspyxmdmb e on a.pyxmdm = e.pyxmdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(")t where 1 = 1");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		this.setClass(SspyjgForm.class);
		this.setKey("guid");
		this.setTableName("xg_gygl_sspyjgb");
	}
	//审核时如果存在重复数据，删除
	public boolean deleteExist(SspyjgForm model) throws Exception {
		StringBuilder sql = new StringBuilder(
		" delete from xg_gygl_sspyjgb where  xn = ? and xq=? and lddm=? and qsh = ? and pyxmdm=?");
		return dao.runUpdate(sql.toString(), new String[] { model.getXn(),model.getXq(),model.getLddm(),model.getQsh(),model.getPyxmdm()});
	}
	
	/**
	 * @描述: 获取楼栋信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午01:40:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getLdxxList() throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from xg_gygl_new_ldxxb order by lddm");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/**
	 * @描述: 寝室信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午05:53:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param ch
	 * @param qsh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getQsxxForParam(String lddm,String ch,String qsh) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from view_xg_gygl_new_qsxx where lddm = ? and ch = ? and qsh = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{lddm,ch,qsh});
	}
	
	/**
	 * @描述: 验证唯一性:学年、学期、楼栋、寝室、评优项目
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-4-28 下午03:57:43
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean checkIsNotRepeat(SspyjgForm model){
		StringBuilder sql = new StringBuilder();
		List<String> paraList = new ArrayList<String>();
		sql.append("select count(*)cnt from xg_gygl_sspyjgb where xn = ? and xq = ? and lddm = ? and qsh = ? and pyxmdm = ?");
		paraList.add(model.getXn());
		paraList.add(model.getXq());
		paraList.add(model.getLddm());
		paraList.add(model.getQsh());
		paraList.add(model.getPyxmdm());
		if(StringUtils.isNotNull(model.getGuid())){
			sql.append(" and guid <> ? ");
			paraList.add(model.getGuid());
		}
		String cnt = dao.getOneRs(sql.toString(), paraList.toArray(new String[]{}), "cnt");
		/*存在数据时返回false，不存在时返回true*/
		return "0".equals(cnt) ? true : false;
	}
	
	/**
	 * @描述: 根据ID获取相关信息
	 * @作者： Meng.Wei[工号:1186]
	 * @日期： 2018-5-3 上午09:07:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getInfoByGuid(String guid) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select a.guid,a.xh,a.sqsj,a.xn,a.xq,c.xqmc,a.lddm,b.ldmc,a.qsh,a.pyxmdm,e.pyxmmc, ");
		sql.append("a.sjly,a.filepath,a.sqly,a.bz,a.ywid,a.sjlrsj,a.sjlrr,f.xm sjlrrxm ");
		sql.append("from xg_gygl_sspyjgb a ");
		sql.append("left join view_xg_gygl_new_qsxx b on a.qsh = b.qsh and b.lddm = a.lddm ");
		sql.append("left join xqdzb c on a.xq = c.xqdm ");
		sql.append("left join view_xsxxb d on a.xh = d.xh ");
		sql.append("left join xg_gygl_new_sspyxmdmb e on a.pyxmdm = e.pyxmdm ");
		sql.append("left join (select yhm,xm from yhb union select xh yhm,xm from xsxxb) f on a.sjlrr = f.yhm ");
		sql.append(")t where guid = ?");
		return dao.getMapNotOut(sql.toString(), new String[]{guid});
	}
}
