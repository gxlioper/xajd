/**
 * @部门:学工产品事业部
 * @日期：2013-8-6 下午03:34:28 
 */
package com.zfsoft.xgxt.gygl.gypy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: (公寓物品管理)
 * @作者： cmj [工号：913]
 * @时间： 2013-8-6 下午03:34:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class GypyService extends SuperServiceImpl<GypyForm, GypyDAO> {
	private GypyDAO dao = new GypyDAO();
	/**
	 * 文明宿舍
	 */
	public static final String _WMSS = "1";
	/**
	 * 优秀辅导员
	 */
	public static final String _YXFDY = "2";
	/**
	 * 优秀学生
	 */
	public static final String _YXXS = "3";

	/**
	 * 
	 * @系统名称: 操作代码
	 * @模块名称: XXXX管理模块
	 * @类功能描述: TODO(这里用一句话描述这个类的作用)
	 * @作者： 张昌路[工号:982]
	 * @时间： 2013-8-21 下午07:53:19
	 * @版本： V1.0
	 * @修改记录: 类修改者-修改日期-修改说明
	 */
	enum czdm {
		list, add, update, del, detail, export
	}

	public GypyService() {
		super.setDao(dao);
	}

	/**
	 * 
	 * @描述:获取高级查询对应配置路径
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-21 上午10:58:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 * @throws
	 */
	public String getGjcxPath(GypyForm gf) {
		String path = "gypywmss.do";
		if (GypyService._YXFDY.equals(gf.getPylx())) {
			path = "gypyyxfdy.do";
		}
		if (GypyService._YXXS.equals(gf.getPylx())) {
			path = "gypyyxxs.do";
		}
		return path;
	}

	/**
	 * 
	 * @描述:根据操作方法获取要跳转的对应路径
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-21 下午07:50:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pylx
	 * @param czdm
	 * @return String 返回类型
	 * @throws
	 */
	public String getForwordPath(GypyForm gf, int czdm) {
		// 如果已经有关联代码（则认为是优秀辅导员操作）
		if (StringUtils.isNotNull(gf.getGldm())) {
			return "yxfdy" + getCzdm(czdm);
		}
		String pylx = gf.getPylx();
		String path = "gypy" + getCzdm(czdm);
		if (GypyService._YXFDY.equals(pylx)) {
			path = "yxfdy" + getCzdm(czdm);

		}
		if (GypyService._YXXS.equals(pylx)) {
			path = "yxxs" + getCzdm(czdm);
		}
		return path;
	}

	private String getCzdm(int czdm) {
		String cz = "lb";
		switch (czdm) {
		case 1:
			cz = "lb";
			break;
		case 2:
			cz = "zj";
			break;
		case 3:
			cz = "xg";
			break;
		case 4:
			cz = "sc";
			break;
		case 5:
			cz = "ck";
			break;
		default:
			break;
		}
		return cz;
	}

	/**
	 * @throws Exception 
	 * 
	 * @描述:添加文明宿舍
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-20 下午04:12:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gf
	 *            void 返回类型
	 * @throws
	 */
	public boolean add(GypyForm gf) throws Exception {
			return dao.runInsert(gf);
	}

	/**
	 * @throws Exception 
	 * 
	 * @描述:删除文明宿舍
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-20 下午04:14:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gf
	 *            void 返回类型
	 * @throws
	 */
	public int delete(String[] ids) throws Exception {
			return dao.runDelete(ids);
	}

	/**
	 * 
	 * @描述:查询
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-20 下午04:14:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gf
	 *            void 返回类型
	 * @throws
	 */
	public HashMap<String, String> query(GypyForm gf) {
		HashMap<String, String> data = null;
		data = dao.getModelMapForId(gf.getGypyid());
		// 获取数据类型
		String pylx = data.get("pylx");
		// 设置代码对应名称
		dao.setXyXqLdForDm(data);
		// 如果是辅导员操作加载辅导员信息
		if (GypyService._YXFDY.equals(pylx)) {
			dao.setFdyxx(data);
		}
		if (GypyService._YXXS.equals(pylx)) {
			data.putAll(dao.getXsxx(data.get("gldm")));
		} else {
			// 关联获取寝室其他信息
			data.putAll(dao.getQsxx(data.get("lddm"), data.get("ch"), data
					.get("qsh")));
		}
		gf.setPylx(pylx);
		return data;
	}

	/**
	 * @throws Exception
	 * 
	 * @描述: 修改文明宿舍
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-20 下午04:14:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gf
	 *            void 返回类型
	 * @throws
	 */
	public boolean update(GypyForm gf) throws Exception {
		return dao.runUpdate(gf);
	}

	public List<HashMap<String, String>> exportData(GypyForm gf)
			throws Exception {
		return dao.getData(gf, false);
	}

	public Map<String, String> getMaxQsh(String lddm, String ch) {
		return dao.getMaxQsh(lddm, ch);
	}

	/**
	 * 
	 * @描述:获取寝室相信信息
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-21 下午02:56:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lddm
	 * @param ch
	 * @param qsh
	 * @return Map<String,String> 返回类型
	 * @throws
	 */
	public Map<String, String> getQsxx(String lddm, String ch, String qsh) {
		return dao.getQsxx(lddm, ch, qsh);
	}

	public Map<String, String> getXsxx(String xh) {
		return dao.getXsxx(xh);
	}

	/**
	 * @throws Exception
	 * 
	 * @描述:检查数据唯一性
	 * @作者：张昌路[工号：982]
	 * @日期：2013-8-23 上午09:06:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gf
	 * @return boolean 返回类型
	 * @throws
	 */
	public boolean checkSoleData(GypyForm gf) throws Exception {
		Map<String, String> map = null;
		List<HashMap<String, String>> data = null;
		// 如果是新增才做验证
		if (StringUtils.isNull(gf.getGypyid())) {
			// 评优理由不做验证
			gf.setPyly(null);
			data = dao.getDataForBeFindEntity(gf);
		}
		if (null != data && data.size() > 0) {
			map = data.get(0);
			// 如果有数据且是同一年度和同一学期的数据 则不允许添加
			if (gf.getXn().equals(map.get("xn"))
					&& gf.getXqdm().equals(map.get("xqdm"))) {
				return false;
			}
		}
		return true;
	}
}
