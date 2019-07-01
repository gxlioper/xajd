/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-24 ����04:37:43 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate;

import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: �������ɽӿ�
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-2-24 ����04:37:43
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public interface IGenerate {
	// 0���ޣ�1���ı���2��������;3:��ֹλ
	String _XXLX_DEFUALTE = "0";
	String _XXLX_TEXT = "1";
	String _XXLX_SELECT = "2";
	String _XXLX_REGION = "3";
	// Ԥ�������ָ���
	String _YLZ_SPLIT = "-";
	// ����ָ���
	String _QJ_SPLIT = "~";
	/**
	 * �Ƿ���޸� ���޸�
	 */
	String _SFKXG_KXG = "1";
	/**
	 * �Զ���
	 */
	String TJ_ZDY = "TJ_ZDY";
	/**
	 * ��ˮ��
	 */
	String TJ_LSH = "TJ_LSH";
	/**
	 * �����ֶ�
	 */
	String _TSZD = "TJ";
	/**
	 * λ�������Զ�����
	 */
	String SFBL_BL = "1";

	/**
	 * 
	 * @����: �������ù����ȡ �༶���루Ԥ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-27 ����09:01:48
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ftf
	 * @param fgtxf
	 * @return String ��������
	 */
	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf);

	/**
	 * 
	 * @����: ���ݹ������ú�ҳ���趨ֵ���ɰ༶����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-27 ����09:02:21
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ftf
	 * @param fgtxf
	 * @param fbf
	 * @return String ��������
	 */
	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf, Object data);

	/**
	 * 
	 * @����: ��ȡ������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����03:29:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 */
	public String getErrorMessage();
}
