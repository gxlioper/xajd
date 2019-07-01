/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-18 ����03:45:22 
 */
package com.zfsoft.xgxt.xsxx.fbgl.comm;

import java.util.HashMap;
import java.util.Map;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-18 ����03:45:22
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class BarSorce {
	private static Map<String, ProgressBar> bar = new HashMap<String, ProgressBar>();

	/**
	 * 
	 * @����: ��ȡ�µĽ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����09:18:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @deprecated ��ʱ���������������ڹرպ����´� ����ͬ����̨ʵ�ʽ������⣬������ʱû�кõİ취�ж��Ƿ�ͬһ������ִ�С�
	 * @param key
	 *            ������Ψһkey
	 * @param all
	 *            �ܴ�����������
	 * @return ProgressBar ��������
	 */
	public static ProgressBar getProgressBar(String key, int all) {
		ProgressBar pb = bar.get(key);
		if (null == pb) {
			pb = new ProgressBar(key, all);
		}
		bar.put(key, pb);
		return pb;
	}

	/**
	 * 
	 * @����: ��ȡ�µĽ�����<һ����ģ���ʱ����ʱʹ��>
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����09:18:07
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 *            ������Ψһkey
	 * @param all
	 *            �ܴ�����������
	 * @return ProgressBar ��������
	 */
	public static ProgressBar initProgressBar(String key, int all) {
		ProgressBar pb = bar.get(key);
		// ���������key���ڶ�Ӧ��������������������
		if (null != bar.get(key) && !pb.isFinish()) {
			ProgressUniqueKeyException puke=new ProgressUniqueKeyException();
			pb.setMessage(puke.getMessage());
			throw puke;

		}
		pb = new ProgressBar(key, all);
		bar.put(key, pb);
		return pb;
	}
	/**
	 * 
	 * @����: �����Զ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-25 ����09:34:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key ������key
	 * @return
	 * ProgressBar ��������
	 */
	public static ProgressBar initAutoProgressBar(String key) {
		return initAutoProgressBar(key, 10, 1);
	}
	/**
	 * 
	 * @����: �����Զ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-25 ����09:35:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key ������key
	 * @param alltime ������ִ��Ԥ��ʱ��(��)
	 * @return
	 * ProgressBar ��������
	 */
	public static ProgressBar initAutoProgressBar(String key, int alltime) {
		return initAutoProgressBar(key, alltime,1);
	}
	/**
	 * 
	 * @����: �����Զ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-25 ����09:35:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key ������key
	 * @param alltime ������ִ��Ԥ��ʱ��(��)
	 * @param ptime   ����������ʱ��(��)
	 * @return
	 * ProgressBar ��������
	 */
	public static ProgressBar initAutoProgressBar(String key, int alltime, int ptime) {
		ProgressBar pb = bar.get(key);
		// ���������key���ڶ�Ӧ��������������������
		if (null != bar.get(key) && !pb.isFinish()) {
			throw new ProgressUniqueKeyException();
		}
		pb = new ProgressBar(key);
		pb.setAllTime(alltime);
		pb.setProgress(ptime);
		bar.put(key, pb);
		pb.autoRun();
		return pb;
	}
	/**
	 * 
	 * @����: ��ȡ��Ӧ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����09:17:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 *            ������Ψһkey
	 * @return ProgressBar ��������
	 */
	public static ProgressBar getProgressBar(String key) {
		ProgressBar pb = bar.get(key);
		if (null == pb) {
			// ����һ����δ��ʼ����������
			return new ProgressBar(key, 0);
		}
		return pb;
	}

	/**
	 * 
	 * @����: ��գ�������������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-25 ����09:17:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 *            ������Ψһkey
	 * @return ProgressBar ��������
	 */
	public static ProgressBar cleanBar(String key) {
		ProgressBar pb = getProgressBar(key);
		pb.finish();
		return bar.put(key, pb);
	}
	/**
	 * 
	 * @����: ���������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-25 ����09:37:46
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param key
	 * void ��������
	 */
	public static void finishBar(String key) {
		bar.remove(key);
	}
}
