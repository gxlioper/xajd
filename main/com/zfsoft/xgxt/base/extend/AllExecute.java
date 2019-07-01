/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-19 ����10:46:32 
 */
package com.zfsoft.xgxt.base.extend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��������
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2013-12-19 ����10:46:32
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class AllExecute {
	public static final String _EXECUTE_TRUE = "true";
	/**
	 * ͳһ��֤
	 */
	public static final String _EXECUTE_ALLCHECK_ERROR="allerror";
	/**
	 * ������֤
	 */
	public static final String _EXECUTE_ROWCHECK_ERROR="rowerror";
	/**
	 * ��֤��������key
	 */
	public static final String _EXECUTE_CHECK_TYPE="checktype";
	private Integer cgts=0;
	boolean isHaveError = false;

	/**
	 * 
	 * @����: ������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-19 ����02:49:18
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ie
	 *            ��������ӿ�
	 * @param data
	 *            �����������
	 * @param isSuccessMes
	 *            �Ƿ񱣴�ɹ���Ϣ
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> execute(IExecute ie,
			List<Object> data, boolean isSuccessMes) {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String ywMessage = null;
		ywMessage=ie.allCheck(data);
		//������֤�ɹ��Ž���������֤
		if(_EXECUTE_TRUE.equals(ywMessage)){
			for (Object o : data) {
				HashMap<String, String> hm = new HashMap<String, String>();
				ywMessage = ie.execute(o);
				// ִ��ҵ�񲻳ɹ�
				if (!_EXECUTE_TRUE.equals(ywMessage)) {
					hm.put("message", ie.message(o, ywMessage));
					//��עΪ����֤����
					hm.put(_EXECUTE_CHECK_TYPE, _EXECUTE_ROWCHECK_ERROR);
					isHaveError = true;
				} else if (isSuccessMes) {// �����Ҫ��¼�ɹ���Ϣ
					hm.put("success", ie.message(o, ywMessage));
				}else{
					cgts++;
				}
				if (hm.size() > 0) {
					list.add(hm);
				}
			}
		}else{
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("message", ywMessage);
			//��עΪͳһ��֤����
			hm.put(_EXECUTE_CHECK_TYPE, _EXECUTE_ALLCHECK_ERROR);
			isHaveError=true;
			list.add(hm);
		}
		return list;
	}

	public boolean isHaveError() {
		return isHaveError;
	}
	public Integer getSuccessNumber(){
		return cgts;
	}
}
