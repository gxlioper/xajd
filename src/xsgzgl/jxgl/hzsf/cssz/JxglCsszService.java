package xsgzgl.jxgl.hzsf.cssz;

import java.util.HashMap;

import xgxt.comm.MessageInfo;
/**
 * ��ѵ����-��������-��������
 * @author yeyipin
 * @since 2012.7.26
 */
public class JxglCsszService {
	JxglCsszDAO dao = new JxglCsszDAO();
	/**
	 * ��ò�������
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
	 * �����������
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String saveCssz(JxglCsszForm model) throws Exception {
		return dao.saveCssz(model)? MessageInfo.MESSAGE_SAVE_SUCCESS : MessageInfo.MESSAGE_SAVE_FALSE;
	}

}
