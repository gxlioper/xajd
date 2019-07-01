/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-9-29 ����09:34:15 
 */  
package com.zfsoft.xgxt.zxdk.syddk;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ��ѧ����-��Դ�ش���
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2014-9-29 ����09:34:15 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SyddkService extends SuperServiceImpl<SyddkModel, SyddkDao> {

	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runInsert(java.lang.Object)
	 */
	
	@Override
	public boolean runInsert(SyddkModel t) throws Exception {
		
		t.setId(UniqID.getInstance().getUniqIDHash());
		boolean result = super.runInsert(t);
		
		if (result){
			result = insertDkxx(t);
		}
		
		return result;
	}

	private boolean insertDkxx(SyddkModel t)
			throws SQLException {
		String[] dkxn = t.getDkxn();
		String[] xf = t.getXf();
		String[] zsf = t.getZsf();
		String[] shf = t.getShf();
		boolean result = true;
		
		if (dkxn != null && dkxn.length > 0){
			List<String[]> params = new ArrayList<String[]>();
			for (int i = 0 ; i < dkxn.length ; i++){
				
//				if (StringUtil.isNull(xf[i])){
//					continue;
//				}
				
				String[] param = new String[]{t.getId(),dkxn[i],xf[i],zsf[i],shf[i]};
				params.add(param);
			}
			
			result= dao.insertDkxx(params);
		}
		return result;
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runUpdate(java.lang.Object)
	 */
	
	@Override
	public boolean runUpdate(SyddkModel t) throws Exception {

		boolean result = super.runUpdate(t);
		
		if (result){
			dao.delDkxx(new String[]{t.getId()});
			this.insertDkxx(t);
		}
		
		return super.runUpdate(t);
	}

	/*
	      ����: @see com.zfsoft.xgxt.base.service.impl.SuperServiceImpl#runDelete(java.lang.String[])
	 */
	
	@Override
	public int runDelete(String[] values) throws Exception {
		dao.delDkxx(values);
		return super.runDelete(values);
	}
	
	public List<HashMap<String,String>> getDkxxList(String id){
		return dao.getDkxxList(id);
	}
	
	
	/**
	 * 
	 * @����: ��ѧ�Ų�ѯ��Դ�ش���
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2014-10-27 ����09:31:10
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String,String>> getSydkList(String xh){
		
		return dao.getSydkList(xh);
	}
	
	/**
	 * 
	 * @����:�ظ��ж�
	 * @���ߣ�������[���ţ�1123]
	 * @���ڣ�2016-4-13 ����09:05:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @return
	 * @throws Exception
	 * String �������� 
	 * @throws
	 */
	public boolean getExists(String xh,String xn) throws Exception {
		String num = dao.getExists(xh, xn);
		return Integer.valueOf(num) > 0;	
	}

}
