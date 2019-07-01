package com.zfsoft.xgxt.qgzx.yjscjffgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cjgl.QgzxCjglService;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 勤工助学管理模块
 * @类功能描述: 经费酬金管理 研究生酬金发放业务处理
 * @作者： xiaxia
 * @时间：2016-05-04 上午09:46:37
 * @版本： V5.1.75
 * @修改记录:
 */
public class YjsCjffService extends SuperServiceImpl<YjsCjffForm, YjsCjffDAO> {

	private YjsCjffDAO dao = new YjsCjffDAO();

	public YjsCjffService() {
		super.setDao(dao);
	}
	
	/**
	 *  是否存在发放信息
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：xiaxia[工号：1104]
	 * @日期：2016-05-04 下午02:33:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param xn
	 * @param yrdwdm
	 * @param gwdm
	 * @return
	 * boolean 返回类型 
	 */
	public boolean isHaveFfxx(String guid,String xh,String xn,String yrdwdm,String gwmc,String ffny){
		return dao.isHaveFfxx(guid, xh, xn, yrdwdm, gwmc,ffny);
	}
	
	/**
	 * 查询条件列表
	 * 
	 * @param request
	 * @return
	 */
	public HashMap<String, String> getFfmrCs(HttpServletRequest request,
			YjsCjffForm myForm) {
		QgzxCjglService qservice = new QgzxCjglService();
		QgzxCjglForm model = new QgzxCjglForm();
		/**
		 * 获取查询条件 因为QgzxCjglService有写过为了避免重复，在这里进行转换获取
		 */
		model.setXh(myForm.getXh());
		model.setDoType(myForm.getType());
		model.setGs(myForm.getGs());
		model.setJe(myForm.getJe());
		model.setXn(myForm.getXn());
		model.setYrbm(myForm.getYrbm());
		// TODO 可以用这个替换以上赋值 BeanUtils.copyProperties(model, myForm);
		HashMap<String, String> rs = qservice.setFfmrCs(request, model);
		return rs;
	}

	
	

}
