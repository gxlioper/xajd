/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-6 ����10:00:52 
 */
package com.zfsoft.xgxt.base.extend;

import java.util.Map;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-6 ����10:00:52
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public interface IDelete {
	/**
	 * �ɹ�ɾ������key
	 */
	String _CGTS = "successDel";
	/**
	 * ������Ϣ����key
	 */
	String _ERROE_OBJ = "errorObject";
	/**
	 * �����ڴ�����Ϣ
	 */
	String _UNHAVE_ERROE = "-1";

	/**
	 * 
	 * @����: �Ƿ��ɾ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����10:31:04
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 *            ����
	 * @return boolean ��������
	 */
	boolean isCanDelete(String pk) throws Exception;

	/**
	 * @����: ����ɾ����ʾ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-6 ����10:31:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return Map<String,String> ��������
	 */
	Map<String, String> showMessage(String pk)throws Exception;
	/**
	 * 
	 * @����: ��չ�������޸ĵȲ���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-7 ����05:34:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * int �������� 
	 */
	int execute(String[] ids) throws Exception;
}
