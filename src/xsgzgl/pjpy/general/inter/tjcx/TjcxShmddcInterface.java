package xsgzgl.pjpy.general.inter.tjcx;

import java.io.OutputStream;
import xsgzgl.pjpy.general.tjcx.shmddc.TjcxShmddcModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_统计查询_综测班级名单_Interface类
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

public interface TjcxShmddcInterface {
	
	// 导出审核名单
	public void expShmddc(TjcxShmddcModel model, OutputStream os)
			throws Exception;
}
