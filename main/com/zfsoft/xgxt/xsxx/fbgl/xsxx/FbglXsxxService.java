/**
 * @部门:学工产品事业部
 * @日期：2014-1-26 上午09:32:22 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import xgxt.form.User;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理学生信息管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-26 上午09:32:22
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglXsxxService extends
		SuperServiceImplExtend<FbglXsxxForm, FbglXsxxDao> {
	public static final String _XSXX_LXCX_ALL = "all";// 所有学生
	public static final String _XSXX_LXCX_YBXH = "ybxh";// 已编学号
	public static final String _XSXX_LXCX_WBXH = "wbxh";// 未编学号
	FbglXsxxDao dao = new FbglXsxxDao();

	public FbglXsxxService() {
		this.setDao(dao);
	}

	@Override
	public FbglXsxxForm getModel(FbglXsxxForm t) throws Exception {
		return dao.getModel(t);
	}

	public boolean runUpdate(FbglXsxxForm t) throws Exception {
		//设置联合主键
		t.setPk(t.getNj() + "-" + t.getKsh());
		return dao.runUpdate(t);
	}
	public String getAllPk(){
		return getAllCols("pk",dao.getXsList());
	}
	/**
	 * 
	 * @描述: 根据联合主键获取对应学生
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-14 下午04:02:33
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 *            （b.nj||'-'||b.xydm||'-'||b.zydm||'-'||b.PYCC||'-'||b.xz）
	 * @param xsfw
	 *            学生范围
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getXsxx(String pk, String xsfw) {
		return dao.getXsxx(pk, xsfw);
	}

	public List<HashMap<String, String>> getXsxxForNjKsh(String pk, String xsfw) {
		return dao.getXsxxForNjKsh(pk, xsfw);
	}

	@Override
	public int runDelete(String value, User user) throws Exception {
		if (null != value && value.length() > 0) {
			return dao.rundelForFbglXsxx(value.split(","));
		}
		return super.runDelete(value, user);
	}

	/**
	 * 
	 * @描述: 根据pk批量删除学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 下午01:59:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 *            联合主键 请参考视图
	 * @return int 返回类型
	 */
	public int deleteXhForPk(String[] pks) {
		return dao.deleteXhForPk(pks);
	}
	
	

	/**
	 * 
	 * @描述: 批量更新学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 下午01:59:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param njKsh
	 *            主键 年级+考生号
	 * @return int 返回类型
	 */
	public int updateXh(String[] njKsh) {
		return dao.updateXh(njKsh);
	}
	public int updateTjzt(String pks,String tjzt) throws Exception {
		return dao.updateTjzt(pks,tjzt);
	}
	/**
	 * 
	 * @描述: 获取班级的学生
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午04:40:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return List<String> 返回类型
	 */
	public List<String> getXsForPk(String pk) {
		List<String> pks = new ArrayList<String>();
		List<HashMap<String, String>> list = dao.getXsForPk(pk);
		for (HashMap<String, String> hm : list) {
			pks.add(hm.get("nj") + FbglXsxxDao._NJ_KSH_FGF + hm.get("ksh"));
		}
		return pks;
	}
	/**
	 * 
	 * @描述: 根据班级代码获取学生信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-2 上午09:36:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm 班级代码
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getXsxxForBjdm(String bjdm){
		return dao.getXsxxForBjdm(bjdm);
	}
	/**
	 * 
	 * @描述: 修改分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午02:37:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param list
	 * @return boolean 返回类型
	 */
	public boolean updateFb(List<HashMap<String, String>> list, String pzgzid) {
		boolean isok = true;
		try {
			FbglXsxxForm fxf = new FbglXsxxForm();
			for (HashMap<String, String> hm : list) {
				BeanUtils.copyProperties(fxf, hm);
				fxf.setFbgz(pzgzid);
				isok = isok && dao.runUpdateForFb(fxf) > 0;
			}
		} catch (Exception e) {
			throw new RuntimeException("保存班级失败！");
		}
		return isok;
	}

	/**
	 * \
	 * 
	 * @描述: 更新分班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午03:42:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return boolean 返回类型
	 */
	public boolean runUpdateForFb(FbglXsxxForm t) {
		try {
			return dao.runUpdateForFb(t) > 0;
		} catch (Exception e) {
			throw new RuntimeException("更新分班信息失败!" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述: 取消分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午01:59:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fxf
	 * @return int 返回类型
	 */
	public boolean qxFb(FbglXsxxForm fxf) {
		return dao.qxFb(fxf) > 0;
	}
}
