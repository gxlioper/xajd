/**
 * @部门:学工产品事业部
 * @日期：2014-1-27 上午10:09:43 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.utils.GetTime;
import com.zfsoft.xgxt.base.extend.IDelete;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsxx.fbgl.Config;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglService;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.PreviewImp;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglGzdmService;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-1-27 上午10:09:43
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglGzpzTjServer extends
		SuperServiceImplExtend<FbglGzpzTjForm, FbglGzpzTjDao> {
	FbglGzpzTjDao dao = new FbglGzpzTjDao();

	public FbglGzpzTjServer() {
		this.setDao(dao);
	}

	public boolean save(FbglGzpzTjForm ff) {
		try {
			ff.setGxsj(GetTime.getNowTime4());
			return runInsert(ff);
		} catch (Exception e) {
			throw new RuntimeException("保存条件规则失败!");
		}
	}

	/**
	 * 
	 * @描述: 获取规则配置
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-18 下午01:51:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return Map<String,Object> 返回类型
	 */
	public Map<String, Object> getGzpz(String pzgzid, boolean isFormat) {
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			FbglGzpzTjForm fgtf = getModel(pzgzid);
			// 规则类型
			FbglGzdmService fgs = new FbglGzdmService();
			// 设置规则名称
			fgtf.setGzmc(fgs.getGzmc(fgtf.getGzdm()));
			data.put("gzpztj", fgtf);
			List<HashMap<String, Object>> hmList = new ArrayList<HashMap<String, Object>>();
			FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
			// FbglTjgzService fts=new FbglTjgzService();
			for (HashMap<String, String> map : fgtxs.getGzpzTjxxLx(pzgzid)) {
				HashMap<String, Object> hm = new HashMap<String, Object>();
				// 规则类型名称
				hm.putAll(map);
				// 规则类型详细信息
				hm.put("tjxx", fgtxs.getGzpzTjxxForLx(map.get("tjgzid"),
						pzgzid, isFormat));
				hmList.add(hm);
			}
			data.put("gzpztjxx", hmList);
		} catch (Exception e) {
			throw new RuntimeException("获取配置规则失败！" + e.getMessage());
		}
		return data;
	}

	/**
	 * 
	 * @描述: 获取规则配置用于查看
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 上午09:15:08
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	public Map<String, Object> getGzpz(String pzgzid) {
		return getGzpz(pzgzid, true);
	}

	/**
	 * 
	 * @描述: 获取规则配置用于修改
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-19 上午09:15:50
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return Map<String,Object> 返回类型
	 * @throws
	 */
	public Map<String, Object> getGzpzForUpdate(String pzgzid) {
		return getGzpz(pzgzid, false);
	}

	/**
	 * 
	 * @描述: 复制规则
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-17 上午08:58:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return boolean 返回类型
	 */
	public boolean copy(String pzgzid) {
		boolean isok = false;
		FbglGzpzTjForm fgt;
		try {
			fgt = getModel(pzgzid);
			// 保存条件规则
			String zbid = UniqID.getInstance().getUniqIDHash();
			fgt.setPzgzid(zbid);
			fgt.setPzgzmc("复制 " + fgt.getPzgzmc());
			// fgt.setQyzt(_QYZT_BQY);
			isok = save(fgt);
			FbglGzpzTjXxServer fgs = new FbglGzpzTjXxServer();
			List<HashMap<String, String>> list = fgs.getTJpzxxId(pzgzid);
			for (HashMap<String, String> hm : list) {
				FbglGzpzTjXxForm fgf = new FbglGzpzTjXxForm();
				BeanUtils.copyProperties(fgf, hm);
				fgf.setPzgzid(zbid);
				isok = fgs.save(fgf);
			}
			return isok;
		} catch (Exception e) {
			throw new RuntimeException("复制错误!");
		}
	}

	/**
	 * 
	 * @描述: 批量删除
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-28 下午04:11:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 *             Map<String,Object> 返回类型
	 */
	public Map<String, Object> delete(String[] ids){
		return batchExecute(ids, new IDelete() {
			FbglGzpzTjForm fgtf;

			public boolean isCanDelete(String pk) throws Exception {
				fgtf = getModel(pk);
				// 启用或 已经使用
				if (Config._QYZT_QY.equals(fgtf.getQyzt()) || isUse(pk)) {
					return false;
				}else if(Config._SFNZ_YES.equals(fgtf.getSfnz())){
					return false;
				}
				return true;
			}

			public Map<String, String> showMessage(String pk) throws Exception {
				//FbglGzdmService fgs = new FbglGzdmService();
				Map<String, String> message = new HashMap<String, String>();
				message.put("pzgzmc",fgtf.getPzgzmc());
				message.put("qyzt",fgtf.getQyzt());
				message.put("sfnz",fgtf.getSfnz());
				return message;
			}

			// 执行删除
			public int execute(String[] ids) {
				try {
					return runDelete(ids);
				} catch (Exception e) {
					throw new RuntimeException("删除失败!");
				}
			}
		});
	}

	/**
	 * 
	 * @描述: 是否已经启用
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-1 上午10:04:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return boolean 返回类型
	 */
	public boolean isUse(String pzgzid) {
		try {
			FbglBbglService fgtxs = new FbglBbglService();
			List<HashMap<String, String>> syxx = fgtxs.getBbxxForPzgz(pzgzid);
			if (null != syxx && syxx.size() > 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new RuntimeException("获取对应详细配置信息失败！" + e.getMessage());
		}
	}

	/**
	 * 
	 * @描述: 获取提交内容配置
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-17 上午11:54:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @param tjgzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTjnrpz(String pzgzid, String tjgzid) {
		return dao.getGzpznr(pzgzid, tjgzid);
	}

	/**
	 * 
	 * @描述: 获取条件内容列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-17 上午11:57:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getTjnrList() {
		return dao.getTjnrList();
	}
	/**
	 * 
	 * @描述: 根据规则代码获取已启用规则list
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-2 上午09:24:25
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzdm
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 */
	public List<HashMap<String, String>> getYqyTjnrList(String gzdm) {
		return dao.getTjnrList(gzdm,Config._QYZT_QY);
	}
	/**
	 * 
	 * @描述: 获取预览值
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-26 上午10:46:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param gzpzid
	 * @param tjgzid
	 * @param lsh
	 * @return String 返回类型
	 */
	public List<String> getGzStr(String gzpzid, String tjgzid) {
		List<String> str = new ArrayList<String>();
		try {
			FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
			List<HashMap<String, String>> list = fgtxs.getGzpzTjxxForLx(tjgzid,
					gzpzid, false);
			FbglGzpzTjXxForm fgtxf = new FbglGzpzTjXxForm();
			FbglTjgzForm ftf = new FbglTjgzForm();
			for (HashMap<String, String> hm : list) {
				BeanUtils.copyProperties(fgtxf, hm);
				BeanUtils.copyProperties(ftf, hm);
				IGenerate gg = new PreviewImp();
				String code = gg.getCode(ftf, fgtxf);
				if (!"".equals(code)) {
					str.add(code);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("获取配置规则失败!" + e.getMessage());
		}
		return str;
	}

	public boolean sfQy(String gzdm) {
		// 暂时不做启用验证
		return false;
		 //return dao.sfQy(gzdm);
	}
}
