/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-4-11 ����11:28:57 
 */
package com.zfsoft.xgxt.xsxx.fbgl.comm;

import java.util.Date;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ����������
 * @�๦������: �Գ�ʱ�������Զ�ֹͣ
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-11 ����11:28:57
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class ProgressThread extends Thread {
	private ProgressBar pb;
	private boolean isRuning = true;
	private Date date;

	public ProgressThread(ProgressBar pb) {
		this.pb = pb;
		this.date = new Date();
	}

	@Override
	public void run() {
		while (isRuning) {
			// �����������ɣ������
			if (pb.isFinish()) {
				isRuning = false;
			}
			Date nowdate = new Date();
			// �Ѿ���ʱ,maxtimeС��������֤��ʱ
			if (pb.getMaxTime()>0&&nowdate.getTime() - date.getTime() > pb.getMaxTime()) {
				isRuning = false;
				pb.finish();
				BarSorce.finishBar(pb.getKey());
			}
		}
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param dateҪ���õ�
	 *            date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}
