package xsgzgl.jxgl.hzsf.cssz;

import java.util.HashMap;

import xgxt.comm.MessageInfo;
/**
 * 军训管理-基础设置-参数设置
 * @author yeyipin
 * @since 2012.7.26
 */
public class JxglCsszService {
	JxglCsszDAO dao = new JxglCsszDAO();
	/**
	 * 获得参数设置
	 * @return
	 */
	public HashMap<String,String> getCssz() {
		HashMap<String,String> rs = dao.getCssz();
		if(rs==null || rs.size()==0){
			rs.put("lx", "jxbz");
		}
		return rs;
	}
	/**
	 * 保存参数设置
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveCssz(JxglCsszForm model) throws Exception {
		return dao.saveCssz(model)? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}

}
