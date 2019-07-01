package xsgzgl.gygl.ntzd;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;


public class NtzdService extends SuperServiceImpl<NtzdForm, NtzdDAO>{
	
	NtzdDAO dao = new NtzdDAO();
	public NtzdService() {
		super.setDao(dao);
	}
	/** 
	 * @����:ȡ���¿���ϵ������ �б�
	 * @���ߣ�qlm
	 * @���ڣ�2013-9-26 
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getNykhxsPageList(NtzdForm myForm) throws Exception {
		return dao.getNykhxsPageList(myForm);
	}
	
	/** 
	 * @����:ȡ�ð༶�¿��˵÷� �б�
	 * @���ߣ�qlm
	 * @���ڣ�2013-9-26 
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getBjydfPageList(NtzdForm myForm) throws Exception {
		if(NtzdAction._EXPORT_FLGE.equals(myForm.getType())){//zcl ������
			return dao.getBjydfPageList(myForm, true);
		}
		return dao.getBjydfPageList(myForm);
	}

	/**
	 * 
	 * @����:�жϸ��������ݿ��Ƿ���ڣ�false:�����ڣ�true:���ڣ�
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-26 
	 * @param ny
	 * @return
	 * boolean �������� 
	 * @throws SQLException 
	 */
	public boolean getCountNykhxs(String ny) throws SQLException {
		int count = dao.getCountNykhxs(ny);
		if(count!=0){
			return true;
		}
		return false;
	}
	
	/** 
	 * @����:ȡ��ѧԺ�¿��˵÷� �б�
	 * @���ߣ�qlm
	 * @���ڣ�2013-9-26 
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getXyydfPageList(NtzdForm myForm) throws Exception {
		if(NtzdAction._EXPORT_FLGE.equals(myForm.getType())){//zcl ������
			return dao.getXyydfPageList(myForm, true);
		}
		return dao.getXyydfPageList(myForm);
	}
	
	/** 
	 * @����:ȡ���������� �б�
	 * @���ߣ�qlm
	 * @���ڣ�2013-9-26 
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws Exception 
	 */
	public List<HashMap<String, String>> getTsqstjPageList(NtzdForm myForm) throws Exception {
		if(NtzdAction._EXPORT_FLGE.equals(myForm.getType())){//zcl ������
			return dao.getTsqstjPageList(myForm, true);
		}
		return dao.getTsqstjPageList(myForm);
	}
}
