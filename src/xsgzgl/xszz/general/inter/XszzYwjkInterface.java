package xsgzgl.xszz.general.inter;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生资助_業務接口_Interface类
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

public interface XszzYwjkInterface {

	// 獲得學生资助列表
	public List<HashMap<String, Object>> getStuZzList(String xh);
}
