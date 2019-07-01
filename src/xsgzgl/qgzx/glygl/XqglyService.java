package xsgzgl.qgzx.glygl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

public class XqglyService extends SuperServiceImpl<XqglyForm, XqglyDao> {
	/**
	 * 
	 * @����: ��֤У���Ƿ��ظ�
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-6 ����02:25:31
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param zgh
	 * @param xq
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean checkIsRepeat(String zgh,String xq){
		return dao.checkIsRepeat(zgh, xq);
	}
	
	/**
	 * 
	 * @����: ��ȡУ��
	 * @���ߣ�����Դ[���ţ�1206]
	 * @���ڣ�2017-9-6 ����02:28:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getXqList(){
		return dao.getXqList();
	}
	
	/**
	 *ɾ�� 
	 * @throws Exception 
	 */
	public boolean runDeletes(String[] ids) throws Exception{
		return dao.runDeletes(ids);
	}
}
