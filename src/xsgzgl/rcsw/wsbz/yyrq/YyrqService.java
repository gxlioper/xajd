/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 上午10:58:38 
 */
package xsgzgl.rcsw.wsbz.yyrq;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class YyrqService extends SuperServiceImpl<YyrqForm, YyrqDao> {
	private YyrqDao dao = new YyrqDao();
	public static String _BCZSCID = "-1";

	public YyrqService() {
		super.setDao(dao);
	}

	public List<HashMap<String, String>> getPageList(YyrqForm model)
			throws Exception {
		return dao.getPageList(model);
	}

	public boolean saveLxInfo(YyrqForm model, String type) throws Exception {
		boolean result = false;
		if ("add".equals(type)) {
			return dao.addLxInfo(model);
		} else if ("update".equals(type)) {
			return dao.updateLxInfo(model);
		}
		return result;
	}

	public boolean isExist(YyrqForm model) {
		return dao.isExist(model);
	}
	public boolean isxgExist(YyrqForm model) {
		return dao.isxgExist(model);
	}
}
