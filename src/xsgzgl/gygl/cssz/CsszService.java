package xsgzgl.gygl.cssz;

import java.util.HashMap;
import java.util.List;

import xsgzgl.gygl.comm.GyglNewService;

public class CsszService extends GyglNewService{
	private CsszDAO csszDao = new CsszDAO();
	
	public List<String[]> querySjsz(CsszModel model) throws Exception{
		return csszDao.querySjsz(model);
	}
	
	public boolean saveKqzt(CsszModel model){
		return csszDao.saveKqzt(model);
	}

	/** 
	 * @����:TODO(��ȡ����Ա������λʱ�䷶Χ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-20 ����02:18:08
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getSjfw() {
		// TODO �Զ����ɷ������
		return csszDao.getSjfw();
	}

	/**
	 * @throws Exception  
	 * @����:TODO(���渨��Ա������λʱ�䷶Χ)
	 * @���ߣ�cmj [���ţ�913]
	 * @���ڣ�2013-6-20 ����02:42:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveSjfw(CsszForm myForm) throws Exception {
		// TODO �Զ����ɷ������
		
		return csszDao.saveSjfw(myForm);
	}
}
