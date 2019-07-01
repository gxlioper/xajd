package xgxt.jxgl.comm;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.CommForm;
import xgxt.comm.CommService;
import xgxt.form.RequestForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 军训管理_通用-service类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class JxglCommService extends CommService {

	/**
	 * 设置所需页面初始化列表
	 * 
	 * @param request
	 * @author 伟大的骆
	 * 
	 */
	public void setJxglOptionList(JxglCommForm jxglCommForm, RequestForm rForm,
			HttpServletRequest request) throws Exception {
		CommForm model = new CommForm();
		BeanUtils.copyProperties(model, jxglCommForm);
		setList(model, rForm, request);
	}
}
