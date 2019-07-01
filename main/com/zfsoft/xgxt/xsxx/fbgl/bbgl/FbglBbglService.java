/**
 * @部门:学工产品事业部
 * @日期：2014-2-17 上午09:44:06 
 */
package com.zfsoft.xgxt.xsxx.fbgl.bbgl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.extend.IDelete;
import com.zfsoft.xgxt.base.extend.SuperServiceImplExtend;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.imp.CreateBjdm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 编班管理
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-2-17 上午09:44:06
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglBbglService extends
		SuperServiceImplExtend<FbglBbglForm, FbglBbglDao> {
	// 编班进度条key
	private String barkey = "fbglbb";
	// 进度条对象
	private ProgressBar pb;
	// 进度条当前执行进度行
	private int now = 1;
	private String checkMess=null;
	/**
	 * 未提交
	 */
	public static final String _SFYTJ_WTJ = "0";
	/**
	 * 已经提交
	 */
	public static final String _SFYTJ_YTJ = "1";
	public FbglBbglDao dao = new FbglBbglDao();
	/**
	 * 可修改信息分隔符
	 */
	public static final String _SPLIT_KXG = ",";
	public static final String _SPLIT_MC = ":";
	// 条件规则设定对应的 ‘列名’分割值，例如 bbgz_bjdm对应为bjdm
	public static final String _TJGZSD_FGF = "_";
	public static final String _BBZT_YBB = "ybb";
	public static final String _TBZT_YFB = "yfb";

	public FbglBbglService() {
		this.setDao(dao);
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglBbglForm t, User user)
			throws Exception {
		if (_BBZT_YBB.equals(t.getBbzt())) {
			return dao.getPageListForYbb(t, user);
		}
		return super.getPageList(t, user);
	}

	/**
	 * 
	 * @描述: 获取分班列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-3 下午03:26:03
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 *             List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getPageListForFb(FbglBbglForm t,
			User user) throws Exception {
		if (_TBZT_YFB.equals(t.getFbzt())) {
			return dao.getPageListForYfb(t, user);
		}
		return dao.getPageListForFb(t);
	}

	/**
	 * 
	 * @描述: 获取可修改信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-21 下午03:20:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pzgzid
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public String getKxgxx(String pzgzid) {
		if (StringUtils.isNull(pzgzid)) {
			return null;
		}
		StringBuffer str = new StringBuffer();
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		for (HashMap<String, String> hm : fgtxs.getKxgxx(pzgzid)) {
			String ylzs = hm.get("ylzstr");
			str.append(hm.get("tjszmc"));
			str.append(_SPLIT_MC);
			str.append(ylzs);
			str.append(_SPLIT_KXG);
		}
		return str.toString();
	}

	/**
	 * 
	 * @描述: 保存调班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-5 下午05:30:29
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param array
	 * @param pzgzid
	 * @return boolean 返回类型
	 */
	public boolean saveTbxx(JSONArray array, String pzgzid, String pk) {
/*		boolean isok = true;
		dao.delAllFbxx(pk);
		try {
			FbglBbglForm fbf = new FbglBbglForm();
			fbf.setPzgzid(pzgzid);
			for (int i = 0; i < array.length(); i++) {
				JSONObject jb = array.getJSONObject(i);
				fbf.setBjbh(String.valueOf(i + 1));
				fbf.setPk(jb.get("pk").toString());
				fbf.setBjdm(jb.get("bjdm").toString());
				fbf.setBjmc(jb.get("bjmc").toString());
				fbf.setBjrssx(jb.get("bjrssx").toString());
				isok = dao.runInsert(fbf);
			}
		} catch (Exception e) {
			throw new RuntimeException("调班错误!" + e.getMessage());
		}
		return true;*/
		boolean isok = true;
		dao.delAllFbxx(pk);
		try {
			List<String[]> params=new ArrayList<String[]>();
			FbglBbglForm fbf = new FbglBbglForm();
			//fbf.setPzgzid(pzgzid);
			for (int i = 0; i < array.length(); i++) {
				List<String> p=new ArrayList<String>();
				JSONObject jb = array.getJSONObject(i);
				fbf.setPk(jb.get("pk").toString());
				fbf.setBjrssx(jb.get("bjrssx").toString());
				p.add(pzgzid);
				p.add(String.valueOf(i + 1));
				//p.add(jb.get("pk").toString());
				p.add(jb.get("bjdm").toString());
				p.add(jb.get("bjmc").toString());
				p.add(fbf.getBjrssx());
				p.add(fbf.getNj());
				p.add(fbf.getBmdm());
				p.add(fbf.getPycc());
				p.add(fbf.getXz());
				p.add(fbf.getZydm());
				params.add(p.toArray(new String[]{}));
				//isok = dao.runInsert(fbf);
			}
			isok=dao.batchInsert(params);
		} catch (Exception e) {
			throw new RuntimeException("调班错误!" + e.getMessage());
		}
		return isok;
	}

	/**
	 * 
	 * @描述: 保存编班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-28 下午03:18:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param array
	 *            json数据
	 * @param pzgzid
	 *            配置规则
	 * @param all
	 *            进度条总数据参数（为了效率从页面传递）
	 * @return
	 * @throws Exception boolean 返回类型
	 */
	public synchronized String save(JSONArray array, String pzgzid, String all)
			throws Exception {
		checkMess = MessageKey.FBGL_CHECK_OK;
		try {
			FbglBbglForm fbf = new FbglBbglForm();
			fbf.setPzgzid(pzgzid);
			pb = BarSorce.initProgressBar(barkey + pzgzid, StringUtils
					.paseStringToInteger(all));
			// 进度条当前进度
			//now += array.length();
			for (int i = 0; i < array.length(); i++) {
				fbf.clean();// 避免重复生成FbglBbglForm对象，每次清空
				// 设置分班管理属性
				fbf.setIsNewLsh("1");
				JSONObject jb = array.getJSONObject(i);
				String pk = jb.get("pk").toString();
				BeanUtils.copyProperties(fbf, getBbxx(pk));
				JSONArray trArray = jb.getJSONArray("tr");
				// 通过设定的值生成班级代码（以及对应的规则）
				Map<String, String> xgzd = new HashMap<String, String>();
				for (int j = 0; j < trArray.length(); j++) {
					JSONObject obj = trArray.getJSONObject(j);
					xgzd.put(obj.get("name").toString(), obj.getString("value")
							.toString());
				}
				// 传递数据 保存进RowData
				fbf.setRowData(xgzd);
				// 格式化处理 并保存
				checkMess = formartCodeAndSave(fbf, false);
				if(!MessageKey.FBGL_CHECK_OK.equals(checkMess)){
					pb.finish();
					pb.stop();
					return checkMess;
				}
			}
		} catch (Exception e) {
			pb.autoFinish();
			pb.stop();
			throw new RuntimeException("解析编班数据错误！" + e.getMessage());
		}
		return checkMess;
	}

	/**
	 * 
	 * @描述: 根据规则和设定值 保存对应编班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-27 上午11:34:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fbf
	 * @param isRecursion
	 *            是否是递归操作（流水号生成班级时为递归操作）
	 * @throws Exception void 返回类型
	 */
	public synchronized String formartCodeAndSave(FbglBbglForm fbf,
			boolean isRecursion) throws Exception {
		// 获取班级Code
		fbf = getFbglBbglCode(fbf);
		if(null==fbf){
			return MessageKey.FBGL_CHECK_LSHBZ;
		}
		checkMess = dao.checkBb(fbf);
		if(!MessageKey.FBGL_CHECK_OK.equals(checkMess)){
			return checkMess;
		}
		return saveFormartCode(fbf, isRecursion);
	}

	/**
	 * 
	 * @描述: 获取下一个代码信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 下午03:19:51
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @param pzgzid
	 * @return FbglBbglForm 返回类型
	 * @throws
	 */
	public FbglBbglForm fbglBbglNextCodexx(String pk, String pzgzid,
			String index) {
		FbglBbglForm fbf = new FbglBbglForm();
		try {
			BeanUtils.copyProperties(fbf, getBbxx(pk));
			fbf.setPk(pk);
			fbf.setPzgzid(pzgzid);
			// 原方法是自己获取数据库最大值 页面删除班级后，数据库并没有删除，所以skewing记录的是页面删除的数量，
			// 以用来对当前生成的code进行微调。
			fbf.setSkewing(index);
		} catch (Exception e) {
			throw new RuntimeException("获取对应编班信息失败!" + e.getMessage());
		}
		return getFbglBbglCode(fbf);
	}

	/**
	 * 
	 * @描述: 获取班级相关生成code
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 下午02:52:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fbf
	 * @return
	 * @throws Exception
	 *             FbglBbglForm 返回类型
	 * @throws
	 */
	public synchronized FbglBbglForm getFbglBbglCode(FbglBbglForm fbf) {
		try {
			if (StringUtils.isNull(fbf.getPk())
					|| StringUtils.isNull(fbf.getPzgzid())) {
				return null;
			}
			// 当前选择的规则信息
			FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
			List<HashMap<String, String>> tjList = fgtxs.getTjgzxx(fbf
					.getPzgzid());
			IGenerate gg = new CreateBjdm();
			// 获取对应规则的code
			for (HashMap<String, String> tjmap : tjList) {
				String code = "";
				String tjgzid = tjmap.get("tjgzid");
				List<HashMap<String, String>> list = fgtxs.getGzpzTjxxForLx(
						tjgzid, fbf.getPzgzid(), false);
				// 对象
				FbglGzpzTjXxForm fgtxf = new FbglGzpzTjXxForm();
				FbglTjgzForm ftf = new FbglTjgzForm();
				// 循环根据配置获取生成code
				for (HashMap<String, String> hm : list) {
					BeanUtils.copyProperties(fgtxf, hm);
					BeanUtils.copyProperties(ftf, hm);
					code += gg.getCode(ftf, fgtxf, fbf);
				}
				// 获取要设定的列明
				String name = tjgzid.split(_TJGZSD_FGF)[1];
				//恶心的提示侵入
				checkMess=gg.getErrorMessage();
				if(StringUtils.isNotNull(checkMess)){
					return null;
				}
				fbf = (FbglBbglForm) setCodeValue(fbf, name, code);
			}
			return fbf;
		} catch (Exception e) {
			throw new RuntimeException("获取对应配置信息失败!",e);
		}
	}

	/**
	 * 
	 * @描述: 格式化生成班级编号并保存
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-28 下午02:14:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fbf
	 * @param isRecursion
	 * @return boolean 返回类型
	 */
	private synchronized String saveFormartCode(FbglBbglForm fbf,
			boolean isRecursion) {
		try {
			this.runInsert(fbf);
			pb.change();
			Integer bjbh = StringUtils.paseStringToInteger(fbf.getBjbh());
			Map<String, String> map = fbf.getRowData();
			// 需编班个数
			String bjgs = map.get("bjgs");
			for (int i = 1; i < Integer.parseInt(bjgs) && !isRecursion; i++) {
				// 递增
				bjbh++;
				// 设置新编号
				fbf.setBjbh(bjbh.toString());
				// 递减生成班级个数
				Integer newBjgs = (Integer.parseInt(bjgs) - 1);
				map.put("bjgs", newBjgs.toString());
				fbf.setRowData(map);
				fbf.setIsNewLsh("0");//第一个班级编班取前台设置值，后面的为递增流水号
				String message = formartCodeAndSave(fbf, true);
				if(!MessageKey.FBGL_CHECK_OK.equals(message)){
					pb.autoFinish();
					return message;
				}
				// 进度条进度更新
				//pb.change();
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException("保存编班信息错误!" + e.getMessage());
		}
		return MessageKey.FBGL_CHECK_OK;
	}

	/**
	 * 
	 * @描述: 设定对应列明
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-27 上午11:32:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fbf
	 * @param name
	 * @param value
	 * @return FbglBbglForm 返回类型
	 */
	protected Object setCodeValue(Object fbf, String name, String value) {
		try {
			Class<?> cla = fbf.getClass();
			String methodName = "set" + name.substring(0, 1).toUpperCase()
					+ name.substring(1, name.length()).toLowerCase();
			Method method = cla.getMethod(methodName, String.class);
			method.invoke(fbf, value);
		} catch (Exception e) {
			throw new RuntimeException("设置对应值[" + name + "]失败" + e.getMessage());
		}
		return fbf;
	}

	/**
	 * 
	 * @描述: 獲取編班具体信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-28 下午02:17:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getBbxx(String pk) {
		return dao.getBbxx(pk);
	}

	/**
	 * 
	 * @描述: 获取动态列生成（后续可更改到前台实现，暂时没有找到相关方法）
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-24 上午09:38:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param kxg
	 * @return String 返回类型
	 */
	public String getAutoGrid(String kxg) {
		if (StringUtils.isNull(kxg)) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		StringBuffer formatter = new StringBuffer();
		String[] kxgs = kxg.split(_SPLIT_KXG);
		String ylzs[];
		String mc, ylzcs[];
		for (String ylz : kxgs) {
			ylzs = ylz.split(_SPLIT_MC);
			mc = ylzs[0];
			ylzcs = ylzs[1].split(FbglGzpzTjXxServer._SFKXG_SPLIC);
			// 不合法数据
			if (ylzcs.length < 2) {
				continue;
			}
			// 拼接生成html
			formatter.append(" var v=rowObject['" + ylzcs[2].toLowerCase()
					+ "'];");
			formatter.append(" var html=\"<input name='" + ylzcs[1]
					+ "' type='text' size='15px;' value='\"+v+\"'/>\";");
			formatter.append(" return html;");
			sb.append("{label:'" + mc + "',name:'" + ylzcs[1] + "', index: '"
					+ ylzcs[1] + "',formatter:function(cellValue,rowObject){"
					+ formatter + "}},");
			formatter.delete(0, formatter.length());
		}
		return sb.toString();
	}

	/**
	 * 
	 * @描述: 获取调班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 上午10:23:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return HashMap<String,String> 返回类型
	 * @throws
	 */
	public HashMap<String, String> getTbxx(String pk) {
		return dao.getTbxx(pk);
	}

	/**
	 * 
	 * @描述: 获取具体需调整班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 上午10:29:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getTbJtBj(String pk) {
		return dao.getTbJtBj(pk);
	}

	/**
	 * 
	 * @描述: 获取调整班级的配置规则id
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 上午10:47:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return String 返回类型
	 * @throws
	 */
	public String getTbPzgzid(String pk) {
		List<HashMap<String, String>> list = dao.getTbJtBj(pk);
		if (null != list && list.size() > 0) {
			return list.get(0).get("pzgzid");
		}
		return null;
	}

	/**
	 * 
	 * @描述: 获取编班信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-4 上午10:26:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param myForm
	 * @return List<HashMap<String,String>> 返回类型
	 * @throws
	 */
	public List<HashMap<String, String>> getBbxx(FbglBbglForm myForm) {
		return dao.getBbxx(myForm);
	}

	public List<HashMap<String, String>> getBbxxForPzgz(String pzgzid) {
		return dao.getBbxxForPzgz(pzgzid);
	}

	/**
	 * 
	 * @描述: 获取对应学生数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 下午03:27:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return Integer 返回类型
	 */
	public Integer getXss(String bjdm) {
		FbglXsxxService fxs = new FbglXsxxService();
		List<HashMap<String, String>> list = fxs.getXsxxForBjdm(bjdm);
		return null == list ? null : list.size();
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
			// 是否可以删除
			public boolean isCanDelete(String pk) {
				try {
					FbglBbglForm ff = getModel(pk);
					Integer xss = getXss(ff.getBjdm());
					// 不能删除,存在学生
					if (null != xss && xss > 0) {
						return false;
					}
					return true;
				} catch (Exception e) {
					throw new RuntimeException("获取对应要删除编班失败!");
				}
			}

			// 提示不能删除的原因
			public Map<String, String> showMessage(String pk) {
				Map<String, String> message = new HashMap<String, String>();
				try {
					FbglBbglForm ff = getModel(pk);
					message.put("bjmc", ff.getBjmc());
				} catch (Exception e) {
					throw new RuntimeException("获取对应要删除编班失败!");
				}
				return message;
			}

			// 执行删除
			public int execute(String[] ids) {
				try {
					// 执行删除
					return runDelete(ids);
				} catch (Exception e) {
					throw new RuntimeException("删除失败!");
				}
			}
		});
	}

	/**
	 * 
	 * @描述: 获取当前班级列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-10 下午03:09:01
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getDqBjList(FbglBbglForm myForm) {
		return dao.getDqBjList(myForm);
	}
}
