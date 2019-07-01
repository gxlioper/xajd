/**
 * @部门:学工产品事业部
 * @日期：2014-2-27 上午09:05:18 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.generate.FbCheck;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理
 * @类功能描述: 编班
 * @作者： 张昌路[工号:982]
 * @时间： 2014-2-27 上午09:05:18
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class CreateBjdm implements IGenerate {
	private FbCheck check = new FbCheck();
	private String messageError = null;


	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf) {
		IGenerate gg = new PreviewImp();
		return gg.getCode(ftf, fgtxf);
	}

	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf, Object obj) {
		FbglBbglForm fbf = (FbglBbglForm) obj;
		StringBuffer str = new StringBuffer();
		String xxlx = ftf.getXxlx();// 选项类型
		String zd = ftf.getTjszzd();// 字段名称
		String xxz = fgtxf.getXxz();// 选项值
		String wsbl = fgtxf.getWsbl();// 末尾补零标志
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
			// 可修改值
			mrz = getValue(zd, fbf.getPk());
		}
		if (TJ_ZDY.equals(zd)) {
			str.append(fgtxf.getXxz());
		} else if (TJ_LSH.equals(zd)) {
			String maxBjbh="";
			Integer nowLsh=0;
			Map<String, String> map = fbf.getRowData();
			// 需编班个数
			String lsh = map.get("lsh");
			//用户前台设置新的流水号则取用户设置值，反之取数据库默认流水号
			if("1".equals(fbf.getIsNewLsh())&&StringUtils.isNotNull(lsh)){
				nowLsh=StringUtils.paseStringToInteger(lsh);
				//验证用户设置的流水号是否重复
				if(CheckLshIsExist(fbf,lsh)){
					messageError = MessageKey.FBGL_CHECK_LSH_REPEART;
				}
				
			}else{
			 maxBjbh = geteMaxLsh(fbf);
			// 偏移量处理（页面删除的偏移量，页面增加或删除的数据尚未入库，所以需要以此来微调）
			String skewing = fbf.getSkewing();
			nowLsh = StringUtils.paseStringToInteger(maxBjbh) + 1
					+ StringUtils.paseStringToInteger(skewing);
			String bjbhStr = nowLsh.toString();
			}
			/*
			 * String lsh = ""; //根据设定流水号位数 补零 for (int i = bjbhStr.length(); i
			 * < Integer.parseInt(fgtxf.getXxz()); i++) { lsh += "0"; }
			 * str.append(lsh + nowLsh);
			 */
			str.append(nowLsh);
			// 流水号特殊处理，同时更新到 bjbh
			fbf.setBjbh(str.toString());
			if (!check.checkLength(nowLsh, Integer.parseInt(fgtxf.getXxz()))) {
				messageError = MessageKey.FBGL_CHECK_LSHBZ;
			}
		} else if (_XXLX_DEFUALTE.equals(xxlx)) {
			str.append(mrz);
		} else if (_XXLX_TEXT.equals(xxlx)) {
			str.append(mrz);
		} else if (_XXLX_SELECT.equals(xxlx)) {
			String sjylz = mrz;
			Integer xxzI = StringUtils.paseStringToInteger(xxz);
			// 如果选择的值大于字段长度，设置其最大为字段长度
			Integer length = xxzI > sjylz.length() ? sjylz.length() : xxzI;
			// 按选项值截取所需位数
			str
					.append(sjylz.substring(sjylz.length() - length, sjylz
							.length()));
		} else if (_XXLX_REGION.equals(xxlx)) {
			String sjylz = mrz;
			String qzj[] = xxz.split(_QJ_SPLIT);
			Integer start = StringUtils.paseStringToInteger(qzj[0]);
			Integer length = StringUtils.paseStringToInteger(qzj[1]);
			// 如果选择的值大于字段长度，设置其最大为字段长度
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

	private String getValue(String zd, String pk) {
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
	private String geteMaxLsh(FbglBbglForm fbf) {
		List<String> param=new LinkedList<String>();
		StringBuffer sb = new StringBuffer();
		sb
				.append("select Max(to_number(bjbh)) maxlsh from xg_xsxx_fbbxhgl_bjdm_lsb where 1=1 ");
		if(StringUtils.isNotNull(fbf.getZydm())){
			sb.append(" and zydm=?");
			param.add(fbf.getZydm());
		}
		if(StringUtils.isNotNull(fbf.getBmdm())){
			sb.append(" and bmdm=?");
			param.add(fbf.getBmdm());
		}
		if(StringUtils.isNotNull(fbf.getPycc())){
			sb.append(" and pycc=?");
			param.add(fbf.getPycc());
		}
		if(StringUtils.isNotNull(fbf.getXz())){
			sb.append(" and xz=?");
			param.add(fbf.getXz());
		}
		if(StringUtils.isNotNull(fbf.getNj())){
			sb.append(" and nj=?");
			param.add(fbf.getNj());
		}
		return DAO.getInstance().getOneRs(
				sb.toString(),param.toArray(new String[]{}), "maxlsh");
	}
	private boolean CheckLshIsExist(FbglBbglForm fbf,String lsh) {
		List<String> param=new LinkedList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1)num from xg_xsxx_fbbxhgl_bjdm_lsb where bjbh=? ");
		param.add(lsh);
		if(StringUtils.isNotNull(fbf.getZydm())){
			sb.append(" and zydm=?");
			param.add(fbf.getZydm());
		}
		if(StringUtils.isNotNull(fbf.getBmdm())){
			sb.append(" and bmdm=?");
			param.add(fbf.getBmdm());
		}
		if(StringUtils.isNotNull(fbf.getPycc())){
			sb.append(" and pycc=?");
			param.add(fbf.getPycc());
		}
		if(StringUtils.isNotNull(fbf.getXz())){
			sb.append(" and xz=?");
			param.add(fbf.getXz());
		}
		if(StringUtils.isNotNull(fbf.getNj())){
			sb.append(" and nj=?");
			param.add(fbf.getNj());
		}
		return Integer.parseInt(DAO.getInstance().getOneRs(
				sb.toString(),param.toArray(new String[]{}), "num"))>0;
	}

	/*
	 * 描述: @see com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate#getErrorMessage()
	 */

	public String getErrorMessage() {
		return messageError;
	}
}
