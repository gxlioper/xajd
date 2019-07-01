package com.zfsoft.xgxt.qgzx.jfcjgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cjgl.QgzxCjglService;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述: 经费酬金管理 数据维护业务处理
 * @作者： zhangjw
 * @时间：2013-04-15 上午09:46:37
 * @版本： V5.1.75
 * @修改记录:
 */
public class CjffSjwhService extends SuperServiceImpl<CjffwhForm, CjffSjwhDAO> {

	private CjffSjwhDAO dao = new CjffSjwhDAO();

	public CjffSjwhService() {
		super.setDao(dao);
	}
	/**
	 * 
	 * @描述: 获取岗位酬金上限
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-23 上午09:53:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xn
	 * @param bmdm
	 * @param gwdm
	 * @return
	 * String 返回类型 
	 */
	public String getCjsxForGw(String xn, String bmdm, String gwdm) {
		String cjsx = null;
		// 当前岗位设置的酬金上限
		HashMap<String, String> gwxx = dao.getGwxx(xn, bmdm, gwdm);
		if(null!=gwxx){
			cjsx = gwxx.get("gwcjsx");
		}

		// 如果设置了酬金上限
		if (StringUtils.isNull(cjsx)) {
			QgzxCsszService qgzxCsszService = new QgzxCsszService();
			HashMap<String, String> map = qgzxCsszService.getCssz();
			//如果岗位没有设置工资上限，用基础设置中的工资上限
			cjsx = map.get("cjsx");
		}
		return cjsx;
	}
	/**
	 * @功能描述:获取时薪
	 * @auther: 王晨辉[1692]
	 */
	public String getSxForGw(String xn, String bmdm, String gwdm) {
		String sx = null;
		HashMap<String, String> gwxx = dao.getGwxx(xn, bmdm, gwdm);
		if(null!=gwxx){
			sx = gwxx.get("sx");
		}
		return sx;
	}
	/**
	 *  是否存在发放信息
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-23 下午02:33:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param yrdwdm
	 * @param gwdm
	 * @return
	 * boolean 返回类型 
	 */
	public boolean isHaveFfxx(String wbh,String xh,String xn,String yrdwdm,String gwdm,String ffsj){
		return dao.isHaveFfxx(wbh,xh, xn, yrdwdm, gwdm,ffsj);
	}
	/**
	 * 
	 * @描述: 更新经费发放信息
	 * @作者：张昌路[工号：982]
	 * @日期：2014-1-23 下午04:15:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param cf
	 * @return
	 * boolean 返回类型 
	 */
	public boolean updataFfxx(CjffwhForm cf){
		return dao.updataFfxx(cf);
	}
	/**
	 * 查询条件列表
	 * 
	 * @param request
	 * @return
	 */
	public HashMap<String, String> getFfmrCs(HttpServletRequest request,
			CjffwhForm myForm) {
		QgzxCjglService qservice = new QgzxCjglService();
		QgzxCjglForm model = new QgzxCjglForm();
		/**
		 * 获取查询条件 因为QgzxCjglService有写过为了避免重复，在这里进行转换获取
		 */
		model.setXh(myForm.getXh());
		model.setDoType(myForm.getType());
		model.setBz(myForm.getBz());
		model.setGs(myForm.getGs());
		model.setJe(myForm.getJe());
		model.setGwdm(myForm.getGwdm());
		model.setXn(myForm.getXn());
		model.setYrbm(myForm.getYrbm());
		model.setZgzt(myForm.getZgzt());
		// TODO 可以用这个替换以上赋值 BeanUtils.copyProperties(model, myForm);
		HashMap<String, String> rs = qservice.setFfmrCs(request, model);
		return rs;
	}

	/**
	 * 
	 * @描述:检测是否可以发放
	 * @作者：张昌路[工号：982]
	 * @日期：2013-12-24 上午10:29:16
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param yrdwdm
	 * @param nd
	 * @param ffje
	 * @param syje
	 * @return boolean 返回类型
	 */
	public boolean checkJfyg(String ffje, String syje) {
		if (StringUtils.isNull(ffje)) {
			return false;
		} else if (StringUtils.isNull(syje)) {
			return true;
		}
		if (StringUtils.compareStr(ffje, syje)) {
			return true;
		}
		return false;
	}

	@Override
	public CjffwhForm getModel(CjffwhForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" select * from (select b.wbh,  b.xh, b.xn ,b.xq,b.yrbm, b.sjbs,");
		sql
				.append(" d.gwmc, d.gwdm, c.bmmc yrdw,  decode(b.zgzt, 'zg', '在岗', 'tg', '退岗', '') zgzt,   b.gs,  b.je,  b.bz,   b.ffsj	 ");
		sql.append(" from xg_qgzx_cjff  b  	 ");
		sql.append(" left join view_xg_qgzx_yrdwdmb c on b.yrbm = c.bmdm	 ");
		sql
				.append(" left join xg_qgzx_gwxxb d on b.gwdm = d.gwdm and b.xn = d.xn and b.yrbm = d.yrdwdm  ) a  where 1 = 1	and a.wbh=? ");
		return dao.getModel(t, sql.toString(), new String[] { t.getWbh() });
	}
	
	
	/** 
	 * @描述:判断是否是贫困生，这里的判断与酬金发放处一致
	 * @作者：xuwen[工号：1426]
	 * @日期：2017年5月3日 上午11:47:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isPks(String xh) {
		boolean isPks = dao.isPks(xh);
		return isPks;
	}

}
