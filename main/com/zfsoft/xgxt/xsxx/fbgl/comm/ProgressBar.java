package com.zfsoft.xgxt.xsxx.fbgl.comm;

/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-18 ����02:50:42 
 */
import java.text.NumberFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ������
 * @�๦������: ������������
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-18 ����02:50:42
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ProgressBar {
	public enum BarState {
		none, work, finish;
	}

	private BarState state = BarState.none;// ״̬
	private String rate;// ����
	private int all;// ����
	private AtomicLong now = new AtomicLong(0);// ��ǰ��
	private String key;// mykey
	// Ĭ��-1 ����Ϣ
	private String message = "-1";
	private boolean isFinish = false;
	// ������ִ�м��������Գ�ʱ��δ��Ӧ�Ľ����������Զ�����
	private ProgressThread barThread = null;
	// ��������������������������˴�������Ȼû�н��ȸ��£����Զ��˳�
	private int maxTime = 30000;
	/******* �Զ�ִ�н��������� ***************/
	// Ĭ������ִ������
	private int allTime = 100;
	// Ĭ�ϸ��½���������
	private int progress = 1;
	private boolean autoIsFinish = false;
	/******* �Զ�ִ�н���������end ***************/
	/**
	 * �Զ�ִ�з�ʽ������
	 */
	public ProgressBar(String key) {
		this.key = key;
		this.all = allTime;
		//�Զ����أ����ó�ʱʱ�䲻ʹ�ó�ʱ
		this.setMaxTime(-1);
		init();
	}
	public void autoRun(){
		new AutoProgressBarThread(this).start();
	}
	public ProgressBar(String key, int all) {
		this.key = key;
		this.all = all;
		init();
	}
	/**
	 * 
	 * @����: ��ʼ������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-25 ����09:32:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 */
	private void init() {
		this.state = BarState.work;
		this.rate = "0";
		// ���ó�ʱ����
		barThread = new ProgressThread(this);
		barThread.start();
	}
	public void stop() {
		barThread.interrupt();
	}

	/**
	 * 
	 * @����: ���ý�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����11:27:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 */
	public void reset() {
		all = 0;
		now.set(0);
		isFinish = false;
		rate = "0";
		state = BarState.none;
	}

	/**
	 * 
	 * @����: ���µ�ǰ����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-28 ����03:59:24
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param now
	 * @return String ��������
	 */
	public String change(int now) {
		// ���ó�ʱʱ��
		barThread.setDate(new Date());
		this.now.set(now);
		// ����һ����ֵ��ʽ������
		NumberFormat numberFormat = NumberFormat.getInstance();
		// ���þ�ȷ��С�����2λ
		numberFormat.setMaximumFractionDigits(2);
		rate = numberFormat.format((float) now / (float) all * 100);
		state = BarState.work;
		System.out.println("thread:"+now);
		if (now == all) {
			rate = "100";
			finish();
		}
		return getRate();
	}

	/**
	 * 
	 * @����: ���������ȸ��£������Ҫָ�����½��ȣ���ʹ��change(now)��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����11:26:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 */
	public String change() {
		int nowint = now.intValue() + 1;
		return change(nowint);
	}

	/**
	 * 
	 * @����: ������ִ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-11 ����11:26:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸����� void ��������
	 */
	public void finish() {
		state = BarState.finish;
		rate = "100";
		isFinish = true;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @return the all
	 */
	public int getAll() {
		return all;
	}

	/**
	 * @return the now
	 */
	public int getNow() {
		return now.intValue();
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the state
	 */
	public BarState getState() {
		return state;
	}

	/**
	 * @return the isFinish
	 */
	public boolean isFinish() {
		return isFinish;
	}

	/**
	 * @deprecated ��ʱδȷ��
	 */
	@Override
	public boolean equals(Object obj) {
		if (this.equals(obj)) {
			ProgressBar pb = (ProgressBar) obj;
			if (this.all == pb.all && this.key.equals(pb.key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @deprecated ��ʱδȷ��
	 */
	public boolean equals(String key, int all) {
		ProgressBar pb = new ProgressBar(key, all);
		return equals(pb);
	}

	/**
	 * @return the maxTime
	 */
	public int getMaxTime() {
		return maxTime;
	}

	/**
	 * @param maxTimeҪ���õ�
	 *            maxTime
	 */
	public void setMaxTime(int maxTime) {
		this.maxTime = maxTime;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param messageҪ���õ�
	 *            message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the autoIsFinish
	 */
	public boolean isAutoIsFinish() {
		return autoIsFinish;
	}

	/**
	 * 
	 * @����: �Զ����ؽ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-25 ����09:39:40
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * void ��������
	 */
	public void autoFinish() {
		this.autoIsFinish = true;
	}

	/**
	 * @return the allTime
	 */
	public int getAllTime() {
		return allTime;
	}

	/**
	 * @param allTime
	 *            the allTime to set
	 */
	public void setAllTime(int allTime) {
		this.allTime = allTime;
	}

	/**
	 * @return the progress
	 */
	public int getProgress() {
		return progress;
	}

	/**
	 * @param progress
	 *            the progress to set
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}
}
