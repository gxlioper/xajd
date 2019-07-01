/**
 * @部门:学工产品事业部
 * @日期：2014-3-10 下午05:21:59 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bxh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.IDelete;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglService;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.generate.BaseCreate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.ContinueCreateXh;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.CreateXh;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-10 下午05:21:59
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglBxhService extends FbglBbglService {
	/**
	 * 已编学号
	 */
	public static final String _BXH_YBXH = "ybxh";
	/**
	 * 未编学号
	 */
	public static final String _BXH_WBXH = "wbxh";

	/**
	 * 
	 * @描述: 获取编学号列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 下午04:20:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fbf
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForBxh(FbglBbglForm fbf,
			User user) throws Exception {
		// 已编学号列表
		if (_BXH_YBXH.equals(fbf.getXhzt())) {
			return dao.getPageListForYbxh(fbf, user);
		}
		return dao.getPageListForBxh(fbf, user);
	}

	/**
	 * 
	 * @描述: 获取条件内容详细列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 下午04:20:20
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTjnrList() {
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		return fgts.getTjnrList();
	}

	/**
	 * 
	 * @描述: 获取所有学生
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-14 下午04:04:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return
	 */
	public int getXszs(String pk) {
		FbglXsxxService fxs = new FbglXsxxService();
		return fxs.getXsxx(pk, FbglXsxxService._XSXX_LXCX_ALL).size();
	}

	/**
	 * 
	 * @描述: 已编班学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-14 下午04:27:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return int 返回类型
	 */
	public int getYbxhXs(String pk) {
		FbglXsxxService fxs = new FbglXsxxService();
		return fxs.getXsxx(pk, FbglXsxxService._XSXX_LXCX_YBXH).size();
	}

	public Map<String, Object> deleteAllXh() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		FbglXsxxDao fxd = new FbglXsxxDao();
		map.put(IDelete._CGTS, String.valueOf(fxd.deleteAllXh()));
		map.put(IDelete._ERROE_OBJ, IDelete._UNHAVE_ERROE);
		return map;
	}

	/**
	 * 
	 * @描述: 获取已编学号学生数量
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 下午04:19:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return int 返回类型
	 */
	public int getYbxhForxs(String[] pks) {
		try {
			int i = 0;
			for (String pk : pks) {
				FbglXsxxService fxs = new FbglXsxxService();
				// 如果学号不为空
				if (StringUtils.isNotNull(fxs.getModel(pk).getXh())) {
					i++;
				}
			}
			return i;
		} catch (Exception e) {
			throw new RuntimeException("获取学生信息失败！");
		}
	}

	/**
	 * 
	 * @描述: 未编班学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-14 下午04:27:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return int 返回类型
	 * @throws
	 */
	public int getWbxhXs(String pk) {
		FbglXsxxService fxs = new FbglXsxxService();
		return fxs.getXsxx(pk, FbglXsxxService._XSXX_LXCX_WBXH).size();
	}

	/**
	 * 
	 * @描述: 生成学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 下午04:18:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @param pzgzid
	 * @param barKey
	 * @param bxhzt
	 * @return boolean 返回类型
	 */
	public String scxh(String pk, String pzgzid, String barKey, String bxhzt) {
		// 已编学号生成学号
		if (_BXH_YBXH.equals(bxhzt)) {
			return scxh(pk, pzgzid, barKey, true);
		}
		return scxh(pk, pzgzid, barKey, false);
	}

	/**
	 * 
	 * @描述: 生成学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 上午10:53:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @param pzgzid
	 * @param barKey
	 *            进度条唯一key
	 * @param isContinue
	 *            是否为继续分班（选中具体学生来分班）
	 * @return boolean 返回类型
	 */
	public String scxh(String pk, String pzgzid, String barKey,
			boolean isContinue) {
		boolean isok = true;
		// 当前选择的规则信息
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		// 按条件排序
		BaseCreate bc = new CreateXh();
		if (isContinue) {
			bc = new ContinueCreateXh();
		}
		bc.addParam(pk);
		//传递配置规则id
		bc.putMapParam("pzgzid",pzgzid);
		// 排序后的学生信息
		List<HashMap<String, String>> tjPzxx = bc.generate(pzgzid, null);
		// 进度条
		ProgressBar pb = BarSorce.initProgressBar(barKey, tjPzxx.size());
		if (null == tjPzxx || tjPzxx.size() <= 0) {
			BarSorce.cleanBar(barKey);
			return "true";
		}
		FbglXsxxForm fxf = new FbglXsxxForm();
		String tjgzid = Config._TJGZID_XH;
		String code = null;
		FbglXsxxService fxs = new FbglXsxxService();
		// 获取具体规则信息
		List<HashMap<String, String>> list = fgtxs.getGzpzTjxxForLx(
				tjgzid, pzgzid, false);
		FbglGzpzTjXxForm fgtxf = new FbglGzpzTjXxForm();
		FbglTjgzForm ftf = new FbglTjgzForm();
		// 生成规则
		IGenerate gg = (IGenerate) bc;
		String name ;
		try {
			// 生成对应编码
			for (HashMap<String, String> xsxx : tjPzxx) {
				if(null==xsxx.get("bjdm")||"".equals(xsxx.get("bjdm"))){
					pb.change();
					continue;
				}
				code="";
				BeanUtils.copyProperties(fxf, xsxx);
				// 设置对应规则配置信息和规则配置详细信息
				for (HashMap<String, String> hm : list) {
					BeanUtils.copyProperties(fgtxf, hm);
					BeanUtils.copyProperties(ftf, hm);
					// 生成对应规则的 code
					code += gg.getCode(ftf, fgtxf, fxf);
				}
				// 获取要设定的列明
				name = tjgzid.split(_TJGZSD_FGF)[1];
				fxf = (FbglXsxxForm) setCodeValue(fxf, name, code);
				// }
				fxf.setBxhgz(pzgzid);
				// 完成生成修改更新入库
				if(null!=gg.getErrorMessage()){
					return gg.getErrorMessage();
					
				}
				isok = isok && fxs.runUpdate(fxf);
				pb.change();
			}
		} catch (Exception e) {
			throw new RuntimeException("获取对应配置信息失败!" + e.getMessage());
		}
		return String.valueOf(isok);
	}

	/**
	 * 
	 * @描述: 删除学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 下午02:00:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param type
	 * @return
	 * @throws Exception
	 *             Map<String,Object> 返回类型
	 */
	public Map<String, Object> deleteXh(String[] ids, String type)
			throws Exception {
		// 已编学号
		if (_BXH_YBXH.equals(type)) {
			return deleteYbXh(ids);
		}
		return batchExecute(ids, new IDelete() {
			FbglXsxxService fxs = new FbglXsxxService();
			Map<String, String> message = new HashMap<String, String>();
			public boolean isCanDelete(String pk) {
				return true;
			}
			public Map<String, String> showMessage(String pk) {
				return message;
			}
			public int execute(String[] ids) {
				try {
					return fxs.deleteXhForPk(ids);
				} catch (Exception e) {
					throw new RuntimeException("删除失败!");
				}
			}
		});
	}

	/**
	 * 
	 * @描述: 删除已分班学号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-24 下午02:00:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return Map<String,Object> 返回类型
	 */
	public Map<String, Object> deleteYbXh(String[] ids) {
		return batchExecute(ids, new IDelete() {
			FbglXsxxService fxs = new FbglXsxxService();
			FbglXsxxForm fxf = new FbglXsxxForm();

			public Map<String, String> showMessage(String pk) throws Exception {
				Map<String, String> message = new HashMap<String, String>();
				if (null == fxf) {
					fxf = fxs.getModel(pk);
				}
				// 页面提示所需参数
				message.put("nj", fxf.getNj());
				message.put("xymc", fxf.getXy());
				message.put("bjmc", fxf.getBjmc());
				message.put("xm", fxf.getXm());
				return message;
			}

			public boolean isCanDelete(String pk) throws Exception {
				fxf = fxs.getModel(pk);
				// 已编学号才可以删除
				if (StringUtils.isNotNull(fxf.getXh())) {
					return true;
				}
				return false;
			}

			public int execute(String[] ids) throws Exception {
				return fxs.updateXh(ids);
			}
		});
	}
}
