package xsgzgl.pjpy.general.inter;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_業務接口_Interface类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public interface PjpyYwjkInterface {

	// 獲得學生評獎列表
	public List<HashMap<String, Object>> getStuPjList(String xh);
}
