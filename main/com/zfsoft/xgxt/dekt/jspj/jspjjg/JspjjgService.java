/**
 * <p>Coyright (R) 2014 ��������ɷ����޹�˾��<p>
 */
package com.zfsoft.xgxt.dekt.jspj.jspjjg;

import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class JspjjgService extends SuperServiceImpl<JspjjgForm, JspjjgDao>{

	public List<String[]> getPjxqInfo(String xh) {
		String[] inputValue = new String[]{xh};
		String[] outputValue = new String[]{"xn","xqmc","pjjsxm","rstjmc","xpjsj"};
		return dao.getPjxqInfo(inputValue, outputValue);
	}

	
	
}
