package xgxt.jxgl.zjcm;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.jxgl.JxglTyService;

public class ZjcmJxglService extends JxglTyService {

	ZjcmJxglDAO dao = new ZjcmJxglDAO();
	

	/**
	 * @author luo
	 * @describe 同步军训成绩
	 */
	public boolean tbJxcj(String xn) throws Exception {
		return dao.tbJxcj(xn);
	}
}
