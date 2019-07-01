/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 上午10:17:13 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.fbgl.FbglService;
import com.zfsoft.xgxt.xsxx.fbgl.generate.BaseCreate;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 分班管理模块
 * @类功能描述: 分班实现类
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-7 上午10:17:13
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class CreateFb extends BaseCreate {
	private FbglService fs = new FbglService();
	private String barKey = "fb";

	/**
	 * 
	 * @描述: 获得当前班级最少人数
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-8 下午05:18:05
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return Integer 返回类型
	 */
	private int getNowFbBjdm() {
		int leastBjrs = 0;// 最小班级人数
		int nowBjrs = 0;// 当前班级人数
		// 查询每个班级的人数
		StringBuffer sb = new StringBuffer();
		sb.append("	select bjdm,count(bjdm) bjrs");
		sb.append(" from (select * from XG_XSXX_FBGL_XSXX_LSB_BAK t");
		sb.append(" union");
		sb.append(" select * from XG_XSXX_FBGL_XSXX_LSB t)");
		sb.append(" where bjdm is not null");
		sb.append(" group by bjdm");
		List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
				sb.toString(), new String[] {});
		for (HashMap<String, String> hm : list) {
			nowBjrs = Integer.parseInt(hm.get("bjrs"));
			leastBjrs = leastBjrs >= nowBjrs ? nowBjrs : leastBjrs;
		}
		return leastBjrs;
	}

	/**
	 * 
	 * @描述: 获取当前应分班班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-9 下午03:52:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param pk
	 * @return HashMap<String,String> 返回类型
	 */
	public HashMap<String, String> getFbbj(String pk) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bjdm,bjmc,rownum ");
		sb.append("  from ( select t.bjdm,t.bjmc,");
		sb.append("  nvl(t.bjrssx, 999999999) bjrssx,");
		sb.append("  nvl(a1.xss, 0) xss,t.bjbh");
		sb.append("  from XG_XSXX_FBBXHGL_BJDM_LSB t");
		sb.append("  left join (select count(*) xss, bjdm");
		sb.append("  from XG_XSXX_FBGL_XSXX_LSB");
		sb.append("  group by bjdm) a1");
		sb.append("  on a1.bjdm = t.bjdm");
		sb
				.append("   where t.nj || '-' || t.bmdm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz = ?");
		sb.append("       order by xss, to_number(t.bjbh)) a");
		sb.append("  where bjrssx > xss and rownum < 2");
		return DAO.getInstance().getMapNotOut(sb.toString(),
				new String[] { pk });
	}

	/**
	 * @描述: 执行分班(使用sql获取可分配班级)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午02:29:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjPzxx
	 *            规则信息
	 * @param bj
	 *            班级集合
	 * @return List<HashMap<String,String>> 返回类型 分班完成的学生
	 */
	public List<HashMap<String, String>> generate(String pzgzid, Object obj) {
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		MessageResources message = MessageResources
						.getMessageResources("config.ApplicationResources");
				Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// 获取进度条
		ProgressBar pb = BarSorce.getProgressBar(barKey + pzgzid);
		try {
			FbglXsxxService fxs = new FbglXsxxService();
			FbglXsxxForm fxf = new FbglXsxxForm();
			List<HashMap<String, String>> tjPzxx = getTJpzxxId(pzgzid);
			// 获取根据规则配置排序后的学生
			tjPzxx = getGroupByData(tjPzxx);
			HashMap<String, String> bj;
			for (HashMap<String, String> xs : tjPzxx) {
				// 少了判断bj是否为空，如果是空，就直接报错或者返回=====================================================
				bj = getFbbj(obj.toString());
				if (null == bj || bj.size() <= 0) {
					String objs[]=obj.toString().split("-");
					pb.finish();
					pb.setMessage("["+objs[0]+"-"+objs[1]+"-"+objs[2]+"-"+xs.get("xm")+"]没可对应的分配班级！请先生成其对应的班级！");
					throw new RuntimeException("分班之前必须先进行编班！");
				}
				xs.put("bjdm", bj.get("bjdm"));
				xs.put("bjmc", bj.get("bjmc"));
				BeanUtils.copyProperties(fxf, xs);
				fxf.setFbgz(pzgzid);
				if (fxs.runUpdateForFb(fxf)) {
					list.add(xs);
					pb.change();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("设置学生信息失败！", e);
		}
		return list;
	}

	/**
	 * 
	 * @描述: 执行分班
	 * @作者：张昌路[工号：982]
	 * @日期：2014-3-7 下午02:29:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param tjPzxx
	 *            规则信息
	 * @param bj
	 *            班级集合
	 * @return List<HashMap<String,String>> 返回类型 分班完成的学生
	 */
	public List<HashMap<String, String>> generate(String pzgzid,
			List<HashMap<String, String>> bj) {
		// 已完成的分班学生
		List<HashMap<String, String>> yfbxsList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> tjPzxx = getTJpzxxId(pzgzid);
		// 获取根据规则配置排序后的学生
		tjPzxx = getGroupByData(tjPzxx);
		// 总班级数
		int bjs = bj.size();
		int i = 0;
		int j = 0;// 当前插入班级的学生数量
		int bjxss = 0;// 班级学生数量
		int bjrssx = 0;// 班级人数上限,0代表无上限限制
		int ymbjs = 0;// 已经分配完成班级数
		String bjdm = null;// 当前班级代码
		// 记录当前分班增加的数量 key ：唯一班级代码，value：当前班级已分配学生数量
		Map<String, Integer> bjCrsl = new HashMap<String, Integer>();
		for (HashMap<String, String> xs : tjPzxx) {
			// 如果所有班级都已经分配完成，自动跳过。
			if (ymbjs == bj.size()) {
				break;
			}
			/*
			 * //如果当前班级不能分班 while (!isCanFb(bj, i)) { i++; }
			 */
			// 班级
			HashMap<String, String> hm = bj.get(i);
			bjdm = hm.get("bjdm");
			// bjdm = hm.get("bjdm");
			// 当前班级的学生数量
			bjxss = fs.getYyxsx(bjdm);
			// 班级人数上限,0代表无上限限制
			bjrssx = StringUtils.paseStringToInteger(hm.get("bjrssx"));
			// 当前插入班级的学生数量
			j = null == bjCrsl.get(bjdm) ? 0 : bjCrsl.get(bjdm);
			// 如果当前班级学生数大于班级人数上限 ，则不对此班级进行插入学生
			// 如果无上限限制 则不做判断
			if (bjrssx <= 0 || bjxss + j < bjrssx) {
				// 设置学生班级代码和名称
				xs.put("bjdm", bjdm);
				xs.put("bjmc", hm.get("bjmc"));
				yfbxsList.add(xs);
				// 记录当前班级插入的学生数量
				bjCrsl.put(bjdm, ++j);
			} else {
				// 增加分配完成的班级数量
				ymbjs++;
			}
			if (i == bjs - 1) {
				// 反转班级排序 snake = =
				Collections.reverse(bj);
				// 重置
				i = 0;
			} else {
				i++;
			}
		}
		return yfbxsList;
	}

	/**
	 * 是否可以对当前班级分班（验证继续分班的位置）
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-9 下午02:38:57
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bj
	 * @param nowBjXb
	 * @return boolean 返回类型
	 */
	private boolean isCanFb(List<HashMap<String, String>> bj, int nowBjXb) {
		// 当前班级代码
		String bjdm = bj.get(nowBjXb).get("bjdm");
		// 当前班级的学生数量
		int bjxss = fs.getYyxsx(bjdm);
		HashMap<String, String> hm = null;
		int zsrs = getNowFbBjdm();
		// 班级最少人数
		if (bjxss > zsrs) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @描述: 是否还有可以分班的班级
	 * @作者：张昌路[工号：982]
	 * @日期：2014-4-4 下午02:36:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return boolean 返回类型
	 */
	private boolean isHaveCanFbBj(List<HashMap<String, String>> bj) {
		int j = 0;// 当前插入班级的学生数量
		int bjxss = 0;// 班级学生数量
		int bjrssx = 0;// 班级人数上限,0代表无上限限制
		FbglService fs = new FbglService();
		for (HashMap<String, String> hm : bj) {
			// 当前班级的学生数量
			bjxss = fs.getYyxsx(hm.get("bjdm"));
			// 班级人数上限,0代表无上限限制
			bjrssx = StringUtils.paseStringToInteger(hm.get("bjrssx"));
			// 当前插入班级的学生数量
			j = StringUtils.paseStringToInteger(hm.get("crxs"));
			// 只要有一个班级可以继续分班，就返回true
			if (bjxss + j < bjrssx) {
				return true;
			}
		}
		return false;
	}

	public String getBaseSql() {
		return "select * from xg_xsxx_fbgl_xsxx_lsb t where t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' ||t.xz =? and t.nj=? and t.bjdm is null";
	}

	@Override
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid) {
		FbglGzpzTjXxServer fgtx = new FbglGzpzTjXxServer();
		List<HashMap<String, String>> tjPzxx = fgtx.getTJpzxxId(pzgzid);
		return tjPzxx;
	}
}
