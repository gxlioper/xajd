package xsgzgl.pjpy.general.inter.wdpj;

import xgxt.form.User;
import xsgzgl.pjpy.general.wdpj.PjpyWdpjModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_评奖条件_Interface类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public interface WdpjPjtjInterface {

	// 判断申请资格
	public String checkSqzg(PjpyWdpjModel model, User user) throws Exception;
	
	// 判断审核资格
	public String checkShzg(PjpyWdpjModel model, User user) throws Exception;
	
	// 判断指定项目的审核控制
	public boolean  checkShkz(String xmdm) throws Exception;
}
