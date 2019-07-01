package xsgzgl.qgzx.cssz;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 勤工助学-基础设置-参数设置
 * 
 * @author yeyipin
 * @since 2012.7.16
 */
public class QgzxCsszService extends SuperServiceImpl<QgzxCsszForm,QgzxCsszDAO>{
//	public QgzxCsszDAO dao = new QgzxCsszDAO();

	/**
	 * 提供接口 获得酬金标准
	 * 
	 * @return
	 */
	public String getCjbz() {
		HashMap<String, String> map = getCssz();
		String cjbz = map.get("cjbz");
		if (Base.isNull(cjbz)) {
			return "0";
		}
		return cjbz;
	}

	/**
	 * 提供接口 获得每月酬金上限
	 * 
	 * @return
	 */
	public String getMyCjsx() {
		HashMap<String, String> map = getCssz();
		String cjsx = map.get("cjsx");
		if (Base.isNull(cjsx)) {
			return "0";
		}
		return cjsx;
	}

	/**
	 * 提供接口 获得发放开始时间
	 * 
	 * @return
	 */
	public String getFfkssj() {
		HashMap<String, String> map = getCssz();
		String kssj = map.get("gzsqkssj");
		if (Base.isNull(kssj)) {
			return "20120901";
		}
		return kssj;
	}

	/**
	 * 提供接口 获得发放结束时间
	 * 
	 * @return
	 */
	public String getFfjssj() {
		HashMap<String, String> map = getCssz();
		String jssj = map.get("gzsqjssj");
		if (Base.isNull(jssj)) {
			return "20120901";
		}
		return jssj;
	}

	/**
	 * 提供接口 获得发放结束时间
	 * 
	 * @return
	 */
	public String getFfksyf() {
		HashMap<String, String> map = getCssz();
		String ksyf = map.get("ksyf");
		if (Base.isNull(ksyf)) {
			return "201209";
		}
		return ksyf;
	}

	/**
	 * 提供接口 获得发放结束时间
	 * 
	 * @return
	 */
	public String getFfjsyf() {
		HashMap<String, String> map = getCssz();
		String jsyf = map.get("jsyf");
		return jsyf;
	}

	/**
	 * 获得参数设置信息Map(cjbz,cjsx)
	 * 
	 * @return
	 */
	public HashMap<String, String> getCssz() {
		return dao.getCssz();
	}

	public HashMap<String, String> getKfsqMap(){
		return dao.sfkfsq();
	}
	
	public HashMap<String, String> getSplcCssz() {
		return dao.getSplcCssz();
	}

	/**
	 * 保存参数设置信息
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public boolean saveCssz(QgzxCsszForm model) throws Exception {
		boolean isok = false;
//		XsgwshService xs = new XsgwshService();
		// 保存配置后重置人数控制
		if(StringUtil.isNull(model.getId())){
			model.setId(UniqID.getInstance().getUniqIDHash());
		}
		isok = dao.runUpdate(model);
//		if (isok && isClean(model)) {
//			isok = xs.resetRsKz();
//		}
		/*if(isok&&"1".equals(new QgzxCsszService().getCjffsh())){
			isok=dao.saveSplcCssz(model);
		}*/
		if(isok){
			isok=dao.saveSplcCssz(model);
		}
		return isok;
	}

	/**
	 * 
	 * @描述:是否需要清空
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-17 下午06:03:18
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 */
	public boolean isClean(QgzxCsszForm model) {
		Map<String, String> map = dao.getCssz();
		String rskzjb = map.get("rskzjb");
		//如果人数控制做了更改则清空
		return !rskzjb.equals(model.getRskzjb());
	}

	/**
	 * 
	 * @描述:人数基本范围是否更改
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-13 下午03:21:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return boolean 返回类型
	 */
	private boolean isChange(QgzxCsszForm model) {
		Map<String, String> map = dao.getCssz();
		if (null == map || map.size() <= 0 || null == model) {
			return true;
		}
		String rskzjb = map.get("rskzjb");
		String qxfw = map.get("qxfw");
		return StringUtils.isNull(rskzjb) || !rskzjb.equals(model.getRskzjb())
				|| !qxfw.equals(model.getQxfw());
	}
	
	/**
	 * @描述：获取参数配置表的 "经费划拨方式" 值
	 * @作者：卓耐[工号:1391]
	 * @日期：2016年11月5日 
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return false：0,按年划拨 true：1,按月划拨
	 * String 返回类型
	 */
	public String getJfhbfs() {
		return dao.getCsz("jfhbfs");
	}
	
	public String getCjffsh() {
		return dao.getCsz("cjffsh");
	}
	
	public String getSqkg() {
		return dao.getSqkg();
	}
	
	/**
	 * 浙大新勤工保存方法
	 */
	public boolean saveJcsz(QgzxCsszForm model) throws Exception {
		boolean isok = false;
		isok = dao.saveZjdxCssz(model);
		return isok;
	}

	public HashMap<String,String> getSplc(){
		return dao.getSplc();
	}
}
