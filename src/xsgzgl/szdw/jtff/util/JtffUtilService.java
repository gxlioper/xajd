/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-9 ����10:47:03 
 */  
package xsgzgl.szdw.jtff.util;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����Դ[����:1206]
 * @ʱ�䣺 2016-3-9 ����10:47:03 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JtffUtilService extends SuperServiceImpl<JtffUtilForm, JtffUtilDao> {
	public HashMap<String, String> getJsjbxx(String zgh){

		if (StringUtil.isNull(zgh)) {
			logger.error("zgh is null !");
			throw new NullPointerException();
		}

		try {
			return dao.getJsjbxx(zgh);
		} catch (Exception e) {
			throw new NullPointerException();
		}
	}
	
	public List<HashMap<String, String>> getJsxxList(JtffUtilForm t, User user)
	  throws Exception{
		return dao.getJsxxList(t, user);
	}
	
	/**����������Ա����ʱ�ظ��ж�
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-9 ����03:45:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExists(String zgh){
		return dao.isExists(zgh);
	}
	
	/**�̶�������Ա����ʱ�ظ��ж�
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2016-3-9 ����03:45:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isExists1(String zgh){
		
		return dao.isExists1(zgh);
	}
	
	/**
	 * ����ְ���Ż�ȡ��Աά����Ϣ
	 */
	public HashMap<String, String> getRywhxx(String zgh){
		return dao.getRywhxx(zgh);
	}
}

