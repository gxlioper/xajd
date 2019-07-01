/**
 * @部门:学工产品事业部
 * @日期：2014-3-6 下午05:06:24 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbgl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.extend.IDelete;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglService;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.generate.BaseCreate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.CreateFb;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-6 下午05:06:24
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglService extends FbglBbglService {
	private String barKey = "fb";

	/**
	 * 
	 * @描述: 获取选择的专业数量
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 下午05:24:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return String 返回类型
	 */
	public int getSelectZy(String pk) {
		String[] pks = pk.split(",");
		return dao.getSelectZy(pks).size();
	}

	/**
	 * 
	 * @描述: 获取选择的专业ids
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午02:53:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return String 返回类型
	 */
	public String getSelectZyIds(String pk) {
		StringBuffer zy = new StringBuffer();
		String[] pks = pk.split(",");
		List<HashMap<String, String>> list = dao.getSelectZy(pks);
		for (HashMap<String, String> hm : list) {
			zy.append(hm.get("zydm"));
			zy.append(",");
		}
		return zy.toString();
	}

	/**
	 * 
	 * @描述: 获取已分班条数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 下午05:44:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return String 返回类型
	 */
	public String getYfbTs(String pk) {
		String[] pks = pk.split(",");
		return dao.getFbxx(true, pks);
	}

	/**
	 * 
	 * @描述: 获取所有分班列表主键pk
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午03:30:14
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 */
	public String getAllPks() {
		return getAllCols("pk", dao.getFbList());
	}

	/**
	 * 
	 * @描述: 获取未分班条数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-6 下午05:44:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return String 返回类型
	 */
	public String getWfbTs(String pk) {
		String[] pks = pk.split(",");
		return dao.getFbxx(false, pks);
	}

	/**
	 * 
	 * @描述: 保存分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-28 上午09:16:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param zydms
	 * @return boolean 返回类型
	 */
	public boolean saveFb(String pzgzid, String pks[]) {
		boolean isok = true;
		// 初始化进度条
		ProgressBar pb=BarSorce.initProgressBar(barKey + pzgzid,Integer.parseInt(dao.getFbxx(false,pks)));
		List<HashMap<String, String>> njList = dao.getNj();
		if(null==njList||njList.size()<=0){
			//最好不要使用进度条提示，业务最好不要侵入。
			//这里系统对应错误信息提示不好用！
			pb.setMessage("分班之前必须先进行编班！");
			throw new SystemException("分班之前必须先进行编班！");
		}
		String nj = "";
		List<HashMap<String, String>> xsxx=null;
		for (HashMap<String, String> njmap : njList) {
			nj = njmap.get("nj");
			for (String pk : pks) {
				// 每个专业
				BaseCreate fg = new CreateFb();
				fg.addParam(pk);
				fg.addParam(nj);
				// 分班
				xsxx=fg.generate(pzgzid, pk);
			}
		}
		if(null==xsxx||xsxx.isEmpty()){
			pb.setMessage("不存在对应年级的班级，不能分班！");
			throw new SystemException("不存在对应年级的班级，不能分班！");
		}
		return isok;
	}

	/**
	 * 
	 * @描述: 获取专业的班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 上午11:35:41
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zydm
	 * @param nj
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getZyBj(String pk, String nj) {
		if (StringUtils.isNotNull(nj)) {
			return dao.getPkBj(pk, nj);
		}
		return dao.getPkBj(pk);
	}

	/**
	 * 
	 * @描述: 获取以分班学生数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 下午01:59:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return int 返回类型
	 */
	public int getYyxsx(String bjdm) {
		FbglXsxxService fxs = new FbglXsxxService();
		List<HashMap<String, String>> list = fxs.getXsxxForBjdm(bjdm);
		return null == list || list.size() <= 0 ? 0 : list.size();
	}

	/**
	 * 
	 * @描述: 获取调班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午03:00:53
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 * @return Map<String,String> 返回类型
	 */
	public Map<String, String> getTbParam(String[] pks) {
		Map<String, String> data = new HashMap<String, String>();
		try {
			FbglXsxxService fxs = new FbglXsxxService();
			FbglXsxxForm fxf = fxs.getModel(pks[0]);
			data.put("xh", fxf.getXh());
			data.put("yxxs", String.valueOf(pks.length));
			data.put("nj", fxf.getNj());
			data.put("xymc", fxf.getXy());
			data.put("zymc", fxf.getZymc());
		} catch (Exception e) {
			throw new RuntimeException("获取学生信息失败!" + e.getMessage());
		}
		return data;
	}

	/**
	 * 
	 * @描述: 调整班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午03:43:54
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pks
	 *            需调整的学生主键集合 nj+ksh
	 * @param bjdm
	 *            调整到的班级代码
	 * @param bjmc
	 *            调整到的班级名称
	 * @return boolean 返回类型
	 */
	public boolean tzbj(String[] pks, String bjdm, String bjmc) {
		boolean isok = true;
		try {
			FbglXsxxService fxs = new FbglXsxxService();
			FbglXsxxForm fxf = new FbglXsxxForm();
			String njksh[];
			for (String pk : pks) {
				njksh = pk.split(FbglXsxxDao._NJ_KSH_FGF);
				fxf.setNj(njksh[0]);
				fxf.setKsh(njksh[1]);
				fxf.setBjdm(bjdm);
				fxf.setBjmc(bjmc);
				fxf.setPzgzid("");
				isok = isok && fxs.runUpdateForFb(fxf);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isok;
	}

	/**
	 * 
	 * @描述: 删除分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-2 下午01:55:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @param fbzt
	 * @return
	 * @throws Exception
	 *             Map<String,Object> 返回类型
	 */
	public Map<String, Object> deleteFb(String[] ids, String fbzt)
			throws Exception {
		// 未选则，全部取消
		if (null == ids || ids.length <= 0) {
			Map<String, Object> message = new HashMap<String, Object>();
			int ts = dao.delFbxx();
			message.put(IDelete._CGTS, ts);
			return message;
		}
		if (FbglBbglService._TBZT_YFB.equals(fbzt)) {
			return deleteFb(ids);
		}
		return batchExecute(ids, new IDelete() {
			FbglXsxxService fxs = new FbglXsxxService();
			public boolean isCanDelete(String pk) {
				
				return true;
			}

			public Map<String, String> showMessage(String pk) throws Exception {
				Map<String, String> message = new HashMap<String, String>();
				FbglXsxxForm ff = fxs.getModel(pk);
				message.put("xh", ff.getXh());
				message.put("xm", ff.getXm());
				return message;
			}

			public int execute(String[] ids) throws Exception {
				int i = 0;
				FbglXsxxForm ff = new FbglXsxxForm();
				// 循环班级
				for (String bjpks : ids) {
					// 获取班级下的已编班学生
					List<String> pks = fxs.getXsForPk(bjpks);
					// 循环学生，取消分班
					for (String id : pks) {
						ff.setPk(id);
						if (fxs.qxFb(ff)) {
							i++;
						} else {
							break;
						}
					}
				}
				return i;
			}
		});
	}

	/**
	 * 
	 * @描述: 取消分班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午02:02:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 *             Map<String,Object> 返回类型
	 */
	public Map<String, Object> deleteFb(String[] ids) throws Exception {
		return batchExecute(ids, new IDelete() {
			FbglXsxxService fxs = new FbglXsxxService();

			public boolean isCanDelete(String pk) {
				return true;
			}

			public Map<String, String> showMessage(String pk) throws Exception {
				Map<String, String> message = new HashMap<String, String>();
				FbglXsxxForm ff = fxs.getModel(pk);
				message.put("xh", ff.getXh());
				message.put("xm", ff.getXm());
				return message;
			}

			public int execute(String[] ids) throws Exception {
				int i = 0;
				FbglXsxxForm ff = new FbglXsxxForm();
				// 循环学生，取消分班
				for (String id : ids) {
					ff.setPk(id);
					if (fxs.qxFb(ff)) {
						i++;
					} else {
						break;
					}
				}
				return i;
			}
		});
	}
}
