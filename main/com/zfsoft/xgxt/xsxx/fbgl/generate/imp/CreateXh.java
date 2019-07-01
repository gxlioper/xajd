/**
 * @部门:学工产品事业部
 * @日期：2014-3-17 上午09:52:28 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.generate.BaseCreate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.FbCheck;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理模块
 * @类功能描述: 生成学号
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-17 上午09:52:28
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class CreateXh extends BaseCreate implements IGenerate {
	private FbCheck check = new FbCheck();
	private String messageError = null;
	StringBuffer sb = new StringBuffer();
	public String getBaseSql() {
		sb.append("select * from (");
		sb
				.append("select t.*,t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz pk from xg_xsxx_fbgl_xsxx_lsb t where 1=1 ");
		sb.append(" and xh is null)");
		String pk = getParam().get(0);
		if (StringUtils.isNotNull(pk)) {
			sb.append(" where pk in(");
			for (String id : pk.split(",")) {
				sb.append("'");
				sb.append(id);
				sb.append("',");
			}
			sb.append("'-1')");
		}
		getParam().remove(0);
		return sb.toString();
	}

	@Override
	public List<HashMap<String, String>> generate(String pzgzid,
			List<HashMap<String, String>> bj) {
		List<HashMap<String, String>> tjPzxx = getTJpzxxId(pzgzid);
		tjPzxx = getGroupByData(tjPzxx);
		return tjPzxx;
	}

	protected String getValue(String zd, String pk) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select " + zd + " from VIEW_FBGL_BBGL where pk=?");
			return DAO.getInstance().getOneRs(sb.toString(),
					new String[] { pk }, zd);
		} catch (Exception e) {
			// 如果查询无此字段 则无默认值
			return null;
		}
	}

	/**
	 * 
	 * @描述: 获取最大流水号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-2-27 上午10:27:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param fbf
	 * @return String 返回类型
	 */
	private String geteMaxLsh(FbglXsxxForm fbf) {
		String pzgzid = getMapParam("pzgzid");
		//学号流水号规则
		String xhGzlx =getXhgz();
		List<String> params = new ArrayList<String>();
		params.add(pzgzid);
		StringBuffer sb = new StringBuffer();
		sb.append("select Max(to_number(lsh)) maxlsh from XG_XSXX_FBGL_XSXX_LSB where bxhgz=?   ");
		if(_XHGZ_XY.equals(xhGzlx)){
			sb.append(" and xydm=?");
			params.add(fbf.getXydm());
		}else if(_XHGZ_ZY.equals(xhGzlx)){
			sb.append(" and xydm=? and zydm=?");
			params.add(fbf.getXydm());
			params.add(fbf.getZydm());
		}
		else{
			sb.append(" and xydm=? and zydm=? and bjdm = ?");
			params.add(fbf.getXydm());
			params.add(fbf.getZydm());
			params.add(fbf.getBjdm());
			
		}
		return DAO.getInstance().getOneRs(sb.toString(),
				params.toArray(new String[params.size()]), "maxlsh");
	}

	/**
	 * 
	 * @描述: 获取跳号的流水号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-10 上午11:11:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return String 返回类型
	 */
	private synchronized String getThLsh() {
		String pzgzid = getMapParam("pzgzid");
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from xg_xsxx_fbgl_xsxx_lsb where bxhgz=? order by lsh");
		List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
				sb.toString(), new String[] { pzgzid });
		int nowLsh = 0;
		int upLsh = 0;
		for (HashMap<String, String> hm : list) {
			nowLsh = Integer.parseInt(hm.get("lsh"));
			// 当前流水号-1不等于上一个流水号
			// 则当前流水号为跳号，
			if (nowLsh - 1 != upLsh) {
				return String.valueOf(upLsh);
			}else{
				upLsh=nowLsh;
			}
		}
		return null;
	}

	/**
	 * 
	 * @描述: 是否存在跳号
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-10 上午10:55:17
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 */
	private boolean isHaveTh(String maxLsh,FbglXsxxForm fxf) {
		String pzgzid = getMapParam("pzgzid");
		//学号流水号规则
		String xhGzlx =getXhgz();
		List<String> params = new ArrayList<String>();
		params.add(pzgzid);
		int gzAllxs = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("select count(xh) xss from XG_XSXX_FBGL_XSXX_LSB where xh is not null and bxhgz=? ");
		if(_XHGZ_XY.equals(xhGzlx)){
			sb.append(" and xydm=?");
			params.add(fxf.getXydm());
		}else if(_XHGZ_ZY.equals(xhGzlx)){
			sb.append(" and xydm=? and zydm=?");
			params.add(fxf.getXydm());
			params.add(fxf.getZydm());
		}
		else{
			sb.append(" and xydm=? and zydm=? and bjdm = ?");
			params.add(fxf.getXydm());
			params.add(fxf.getZydm());
			params.add(fxf.getBjdm());
			
		}
		try {
			gzAllxs = StringUtils.paseStringToInteger(DAO.getInstance()
					.getOneRs(sb.toString(), params.toArray(new String[params.size()]), "xss"));
		} catch (Exception e) {
			throw new RuntimeException("获取当前规则已编学号学生失败!", e);
		}
		// 编学号学生小于最大流水号，则存在跳号
		if (gzAllxs <StringUtils.paseStringToInteger(maxLsh)) {
			return true;
		}
		return false;
	}

	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf) {
		// TODO 自动生成方法存根
		return null;
	}

	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf, Object obj) {
		FbglXsxxForm fbf = (FbglXsxxForm) obj;
		StringBuffer str = new StringBuffer();
		String xxlx = ftf.getXxlx();// 选项类型
		String zd = ftf.getTjszzd();// 字段名称
		String xxz = fgtxf.getXxz();// 选项值
		String wsbl = fgtxf.getWsbl();// 末尾补零标志
		String qsz  = fgtxf.getQsz();
		String ylz = ftf.getYlzqz();// 预览值
		String ylzs[] = null;
		String sfkxg = ftf.getSfkxg();// 是否可修改
		// 字段当前行的默认值
		String mrz = "";
		// 存在预览值则获取配置的预览值，否则如果可修改则取字段对应值
		if (StringUtils.isNotNull(ylz)) {
			ylzs = ylz.split(_YLZ_SPLIT);
			// 获取预览值
			mrz = getValue(ylzs[1], fbf.getPk());
		} else if (_SFKXG_KXG.equals(sfkxg) && !zd.startsWith(_TSZD)) {
			// 获取修改的值
			mrz = getValue(zd, fbf.getPk());
		}
		// 自定义
		if (TJ_ZDY.equals(zd)) {
			str.append(fgtxf.getXxz());
		} else if (TJ_LSH.equals(zd)) {
			// 流水号处理
			String maxlsh = geteMaxLsh(fbf);
			// 存在跳号
			if (isHaveTh(maxlsh,fbf)) {
				maxlsh = getThLsh();
			}
			// 偏移量处理（页面删除的偏移量，页面增加或删除的数据尚未入库，所以需要以此来微调）
			String skewing = fbf.getSkewing();
			// 获取当前流水号
			Integer nowLsh = StringUtils.paseStringToInteger(maxlsh)
					+ StringUtils.paseStringToInteger(skewing)+1;
			String lshStr = nowLsh.toString();
			// 设置流水号
			fbf.setLsh(lshStr);
			Integer xh = (nowLsh+StringUtils.paseStringToInteger(qsz)-1);
			String xhStr=xh.toString();
			String lsh="";
			// 根据设定流水号位数 补零
			for (int i = xhStr.length(); i < Integer.parseInt(fgtxf.getXxz()); i++) {
				lsh += "0";
			}
			str.append(lsh + xhStr);
			//检查流水号是否超出
			if (!check.checkLength(xhStr, Integer.parseInt(fgtxf.getXxz()))) {
				messageError = MessageKey.FBGL_CHECK_LSHBZ;
				
			}
		} else if (_XXLX_DEFUALTE.equals(xxlx)) {
			// 默认类型直接取值
			str.append(mrz);
		} else if (_XXLX_TEXT.equals(xxlx)) {
			// 文本类型直接取值
			str.append(mrz);
		} else if (_XXLX_SELECT.equals(xxlx)) {
			// 选择类型处理
			String sjylz = mrz;
			// 选择的值
			Integer xxzI = StringUtils.paseStringToInteger(xxz);
			// 如果选择的值大于字段长度，设置其最大为字段长度
			Integer length = xxzI > sjylz.length() ? sjylz.length() : xxzI;
			// 按选项值截取所需位数
			str
					.append(sjylz.substring(sjylz.length() - length, sjylz
							.length()));
		} else if (_XXLX_REGION.equals(xxlx)) {
			// 区间值处理
			String sjylz = mrz;
			// 区间数组
			String qzj[] = xxz.split(_QJ_SPLIT);
			// 开始区间
			Integer start = StringUtils.paseStringToInteger(qzj[0]);
			// 结束区间
			Integer length = StringUtils.paseStringToInteger(qzj[1]);
			// 如果区间值大于字段长度，设置其最大为字段长度
			Integer end = start + length >= sjylz.length() ? sjylz.length()
					: start + length;
			// 按区间值截取所需位数
			str.append(sjylz.substring(start, end));
		}
		// 如果可修改，查看是否存在设定值，如果存在则覆盖原值
		if (_SFKXG_KXG.equals(fgtxf.getSfkxg())) {
			// 页面传递值
			String sdz = fbf.getRowData().get(zd);
			if (StringUtils.isNotNull(sdz)) {
				// 删除原来值，设定为修改值
				str.delete(0, str.length());
				str.append(sdz);
			}
		}
		// 是否补零
		if (SFBL_BL.equals(wsbl)) {
			// 选项值存放
			Integer ws = StringUtils.paseStringToInteger(xxz);
			if (str.length() < ws) {
				// 不足自动补零
				int cs = ws - str.length();
				for (int i = 0; i < cs; i++) {
					str.insert(0, "0");
				}
			}
		}
		return str.toString();
	}

	@Override
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=? and tjgzid=? order by sx");
		return DAO.getInstance().getListNotOut(sb.toString(),
				new String[] { pzgzid, "BXHGZ_XHPX" });
	}

	/**
	 * 
	 * @描述:获取学号流水号的规则
	 * @作者：xiaxia[工号：1104]
	 * @日期：2015-9-21 下午02:15:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String getXhgz() {
		String gzsql = "select * from XG_XSXX_FBGL_JCPZB where gzdm='XHGZ'";
		return DAO.getInstance().getOneRs(gzsql, new String[] {}, "gzlx");
	}
	public String getErrorMessage() {
		return messageError;
	}
}
