package xsgzgl.gyjc.jcsd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

import common.newp.ArrayUtil;

public class JcsdService extends SuperServiceImpl<JcsdForm, JcsdDao> {
	/**
	 * @throws Exception 
	 * 
	 * @����: ��ѯ��Ա����List
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-10 ����10:28:01
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getRyfpList(JcsdForm t, User user) throws  Exception{
		return dao.getRyfpList(t, user);
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: �����������Ա����(�������淽ʽ)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-10 ����11:39:41
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	@TransactionControl
	public boolean saveRyFp(JcsdForm t,String js) throws Exception{
		/**
		 * �������
		 */
		List<String[]> paraList = new ArrayList<String[]>();
		String[] zghs = t.getZghs();
		String xydm = t.getXydm();
		String jjlx = t.getJjlx();
		/**
		 * �������
		 */
		for (int i = 0; i < zghs.length; i++) {
			paraList.add(new String[]{xydm,zghs[i],jjlx,js});
		}
		boolean rs = dao.saveFpry(paraList);
		if(!rs){
			throw new SystemException(MessageKey.SYS_SAVE_FAIL);
		}
		return rs;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @����: ȡ������
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-4-10 ����01:55:54
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param js
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean cancelRyfp(JcsdForm t,String js) throws Exception{
		return dao.cancelRyfp(t, js);
	}
}
