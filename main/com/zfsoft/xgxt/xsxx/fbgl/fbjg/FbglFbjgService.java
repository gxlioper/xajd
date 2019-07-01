/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 上午10:42:31 
 */
package com.zfsoft.xgxt.xsxx.fbgl.fbjg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.MessageResources;

import xgxt.action.Base;
import xgxt.form.User;
import xgxt.utils.GetTime;

import com.zfsoft.xgxt.base.export.excel.ExcelUtils;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglService;
import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.bak.FbglXsxxBakService;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxForm;
import com.zfsoft.xgxt.xsxx.xsgl.XsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-24 上午10:42:31
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglFbjgService extends FbglXsxxService {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	/* 检测类型 */
	public static enum CheckType {
		none, success, fail,repeat
	}

	/**
	 * 不存在已分班编学号学生信息
	 */
	public static final String _TJ_BCZYBXH = "0";
	/**
	 * 提交失败
	 */
	public static final String _TJ_TJSB = "-1";
	public static final String _TJ_TJCF = "2";//学号重复
	/**
	 * 提交成功
	 */
	public static final String _TJ_TJCG = "1";
	/**
	 * 已提交
	 */
	public static final String _SFYTJ_YTJ = "ytj";
	/**
	 * 未提交
	 */
	public static final String _SFYTJ_WTJ = "wtj";
	/**
	 * 进度条key
	 */
	public static final String _BAR_KEY = "tjzsk";
	/**
	 * 操作类型提交
	 */
	private static final String CZLX_TJ="0";
	/**
	 * 操作类型撤销
	 */
	private static final String CZLX_CX="1";
	// 默认excel存放目录
	public String errorPath = this.getClass().getResource("/").getPath()
			+ "/fbglerror.xls";

	FbglFbjgDao dao = new FbglFbjgDao();

	public FbglFbjgService() {
		this.setDao(dao);
	}

	@Override
	public List<HashMap<String, String>> getPageList(FbglXsxxForm t, User user)
			throws Exception {
		if (_SFYTJ_YTJ.equals(t.getTjzt())) {
			return dao.getPageListYtj(t, user);
		}
		return super.getPageList(t, user);
	}
	/**
	 * 
	 * @描述: 获取年级列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-14 下午05:03:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getNjList() throws Exception {
		return dao.getNjList();
	}
	/**
	 * 
	 * @描述: 获取已提交年级列表
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-14 下午05:03:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String, String>> getYtjNjList() throws Exception {
		return dao.getYtjNjList();
	}
	/**
	 * 
	 * @描述: 获取学生数量
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-14 下午05:04:28
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param isTj 是否是已提交的学生
	 * @param nj   年级
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getXsxxSl(boolean isTj,String nj) throws SQLException{
		return isTj?dao.getYtj(nj):dao.getWtj(nj);
	}
	/**
	 * 
	 * @描述: 检查提交正式库数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-25 下午02:56:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @return boolean 返回类型
	 */
	public boolean checkTj(String nj, List<HashMap<String, String>> bjlist,
			List<HashMap<String, String>> xslist) {
				Base.YXPZXY_KEY = message.getMessage("lable.xb");
		boolean isok = true;
		List<String[]> errorData = new ArrayList<String[]>();
		// check班级
		String[] title = new String[] { "有冲突的班级：" };
		errorData.add(title);
		errorData.add(new String[] { "年级", Base.YXPZXY_KEY, "专业", "班级代码", "班级名称" });
		String col[];
		for (HashMap<String, String> hm : bjlist) {
			col = new String[5];
			if (checkBj(hm.get("bjdm"))) {
				putValue(col, hm, true);
				isok = false;
			}
			if (!isok) {
				errorData.add(col);
			}
			// 还原初始值
			isok = true;
		}
		//如果错误集合不存在班级错误信息，则清空
		if(errorData.size()==2){
			errorData.clear();
		}
		// check学号
		title = new String[] { "有冲突的学号：" };
		errorData.add(title);
		errorData.add(new String[] { "学号", "考生号", "年级", "姓名", "性别", Base.YXPZXY_KEY, "专业",
				"班级" });
		for (HashMap<String, String> hm : xslist) {
			col = new String[8];
			// 是否存在此学号学生
			if (checkXh(hm.get("xh"))) {
				putValue(col, hm, false);
				isok = false;
			}
			if (!isok) {
				errorData.add(col);
			}
			// 还原初始值
			isok = true;
		}
		if (errorData.size() > 2) {
			try {
				ExcelUtils.createExcel(errorData, errorPath);
			} catch (Exception e) {
				throw new RuntimeException("创建excle失败！" + e.getMessage());
			}
		}
		return errorData.size() <= 2;
	}

	/**
	 * 
	 * @描述: 撤销正式库
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午04:06:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @return
	 * @throws Exception boolean 返回类型
	 * @throws
	 */
	public boolean cxzsk(String nj,User user) throws Exception {
		String barKey = "cxzsk" + nj;
		boolean isok = false;
		isok = true;
		List<HashMap<String, String>> list = dao.getBjxxForNj(nj,
				FbglBbglService._SFYTJ_YTJ);
		List<HashMap<String, String>> xsxxlist = dao.getLsbXsxxInsertForNj(nj);
		// 初始化进度条
		ProgressBar pb = BarSorce.initProgressBar(barKey, xsxxlist.size());
		int now = 1;

		FbglBbglService fbs = new FbglBbglService();
		FbglBbglForm fbf = new FbglBbglForm();

		for (HashMap<String, String> hm : list) {
			if (isok) {
				// 删除对应班级信息
				isok = dao.deleteBjxx(hm);
				// 修改为未提交
				BeanUtils.copyProperties(fbf, hm);
				fbf.setSfytj(FbglBbglService._SFYTJ_WTJ);
				isok = fbs.runUpdate(fbf);
			} else {
				break;
			}
		}
		// 撤销学生
		for (HashMap<String, String> hm : xsxxlist) {
			cxXssj(hm);
			// 更新进度
			pb.change(now++);
		}
		//保存操作日志
		dao.saveLog(nj,GetTime.getNowTime4(), CZLX_CX, user.getUserName());
		return isok;
	}

	/**
	 * 
	 * @描述: 提交年级信息到正式库
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:32:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @return boolean 返回类型
	 */
	public Map<String, String> tjzskForMessage(String nj,User user) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		CheckType ct = tjzsk(nj,user);
		switch (ct) {
		case none:
			// 不存在已分班编学号学生信息
			map.put("message", _TJ_BCZYBXH);
			break;
		case fail:
			map.put("message", _TJ_TJSB);
			break;
		case repeat:
			map.put("message", _TJ_TJCF);
			break;
		default:
			map.put("message", _TJ_TJCG);
			break;
		}
		return map;
	}

	/**
	 * 
	 * @描述: 提交年级信息到正式库
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:32:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param nj
	 * @return boolean 返回类型
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	public CheckType tjzsk(String nj,User user) throws Exception {
		String barKey = _BAR_KEY + nj;
		boolean isok = false;

		List<HashMap<String, String>> list = dao.getBjxxForNj(nj,
				FbglBbglService._SFYTJ_WTJ);
		List<HashMap<String, String>> xsxxlist = dao.getXsxxForNj(nj);
		
		// 不能存在对应已分班 编学号的学生
		if (null == xsxxlist || xsxxlist.size() <= 0) {
			return CheckType.none;
		}
		List<String> checkList   =new ArrayList<String>();
		for (int i = 0; i < xsxxlist.size(); i++) {
			if(null==xsxxlist.get(i).get("xh")||!checkList.contains(xsxxlist.get(i).get("xh"))){
				checkList.add(xsxxlist.get(i).get("xh"));
			}
		}
		if(checkList.size()!=xsxxlist.size()){
			return CheckType.repeat;
		}
		// 是否有冲突
		if (checkTj(nj, list, xsxxlist)) {
			isok = true;
			// 提交班级
			// 初始化进度条
			ProgressBar pb = BarSorce.initProgressBar(barKey,xsxxlist.size());
			FbglBbglService fbs = new FbglBbglService();
			FbglBbglForm fbf = new FbglBbglForm();
			for (HashMap<String, String> hm : list) {
				if (isok) {
					// 保存到正式库
					isok = dao.saveBjxx(hm);
					// 修改为已经提交
					BeanUtils.copyProperties(fbf, hm);
					fbf.setSfytj(FbglBbglService._SFYTJ_YTJ);
					isok = fbs.runUpdate(fbf);
				} else {
					break;
				}
			}
			// 提交学生
			for (HashMap<String, String> hm : xsxxlist) {
				tjXssj(hm);
				// 更新进度
				pb.change();
			}
		} else {
			return CheckType.fail;
		}
		//保存操作日志
		dao.saveLog(nj,GetTime.getNowTime4(), CZLX_TJ, user.getUserName());
		return CheckType.success;
	}

	/**
	 * 
	 * @描述: 提交学生数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-25 下午02:54:48
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hm
	 *            void 返回类型
	 */
	public void tjXssj(HashMap<String, String> hm) throws Exception {
		// xsxxb学生信息表、bks_bjdm班级代码表
		XsxxService xs = new XsxxService();
		XsxxForm xf = new XsxxForm();
		BeanUtils.copyProperties(xf, hm);
		// 提交学生
		xs.runInsert(xf);
		// 备份到bak
		FbglXsxxBakService fxbs = new FbglXsxxBakService();
		FbglXsxxForm fxf = new FbglXsxxForm();
		BeanUtils.copyProperties(fxf, hm);
		fxbs.runInsert(fxf);
		// 修改学生临时表
		FbglXsxxService fxs = new FbglXsxxService();
		
		fxs.updateTjzt(fxf.getNj() + FbglXsxxDao._NJ_KSH_FGF
				+ fxf.getKsh(),"1");
	}

	/**
	 * 
	 * @描述: 撤销提交的数据
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-26 下午02:32:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param hm
	 *            void 返回类型
	 */
	public void cxXssj(HashMap<String, String> hm) throws Exception {
		// xsxxb学生信息表、bks_bjdm班级代码表
		XsxxService xs = new XsxxService();
		XsxxForm xf = new XsxxForm();
		FbglXsxxBakService fxbs = new FbglXsxxBakService();
		FbglXsxxForm fxf = new FbglXsxxForm();
		BeanUtils.copyProperties(xf, hm);
		// 撤销学生
		xs.runDelete(new String[] { xf.getXh() });
		// 撤销新增学生时触发器增加的学生密码
		// trigger xsmmcsh after insert on xsxxb for each row
		dao.deleteXsmm(xf.getXh());
		// 撤销备份
		BeanUtils.copyProperties(fxf, hm);
		fxbs.runDelete(new String[] { fxf.getXh() });
		// 还原到临时表
		FbglXsxxService fxs = new FbglXsxxService();
		
		fxs.updateTjzt(fxf.getNj() + FbglXsxxDao._NJ_KSH_FGF
				+ fxf.getKsh(),"0");
	}
	/**
	 * 
	 * @描述: 设置对应值
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 下午04:10:31
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param col
	 * @param hm
	 * @param isBj
	 * void 返回类型
	 */
	private void putValue(String[] col, HashMap<String, String> hm, boolean isBj) {
		//班级对应值
		if (isBj) {
			col[0] = hm.get("nj");
			col[1] = hm.get("xymc");
			col[2] = hm.get("zymc");
			col[3] = hm.get("bjdm");
			col[4] = hm.get("bjmc");
		} else {
			col[0] = hm.get("xh");
			col[1] = hm.get("ksh");
			col[2] = hm.get("nj");
			col[3] = hm.get("xm");
			col[4] = hm.get("xb");
			col[5] = hm.get("xymc");
			col[6] = hm.get("zymc");
			col[7] = hm.get("bjmc");
		}
	}

	/**
	 * 
	 * @描述: 检查是否存在学生
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-25 下午02:55:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return boolean 返回类型
	 */
	public boolean checkXh(String xh) {
		try {
			XsxxService xs = new XsxxService();
			XsxxForm xf = xs.getModel(xh);
			return null != xf;
		} catch (Exception e) {
			throw new RuntimeException("获取学生信息失败！" + e.getMessage());
		}

	}
	public void saveLog(String tjnj,String czsj,String czlx,String czr){
		
	}
	/**
	 * 
	 * @描述: 检查是否存在班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-25 下午02:55:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bjdm
	 * @return boolean 返回类型
	 */
	public boolean checkBj(String bjdm) {
		List<HashMap<String, String>> list = dao.getBjxx(bjdm);
		return null != list && list.size() > 0;
	}
	/**
	 * 
	 * @描述: 下载冲突信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-25 下午02:55:44
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param request
	 * @param response
	 *            void 返回类型
	 */
	public void download(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			File file = new File(errorPath);
			String path = file.getPath();
			//临时文件路径
			String fileName = path.substring(path.lastIndexOf("\\") + 1, path
					.length());
			//读取
			InputStream is = new FileInputStream(file);
			byte[] b = new byte[2048];
			//设置下载类型
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			//输出
			OutputStream os = response.getOutputStream();
			int len;
			while ((len = is.read(b)) > 0) {
				os.write(b, 0, len);
			}
			os.flush();
			os.close();
			is.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("文件路径错误，无法读取！", e);
		} catch (IOException e) {
			throw new RuntimeException("文件不能转换为流进行输出！", e);
		}
	}
}
