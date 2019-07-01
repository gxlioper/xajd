package com.zfsoft.xgxt.xsxx.fbgl.comm;

/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �Զ��������߳�
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-4-25 ����09:38:39 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */
public class AutoProgressBarThread extends Thread {
	private ProgressBar pb;
	private boolean isRuning = true;
	//Ĭ�ϸ��½�����������
	private long progress=1000;
	public AutoProgressBarThread(ProgressBar pb) {
		this.pb = pb;
		this.progress=1000*pb.getProgress();
	}
	@Override
	public void run() {
		while (isRuning) {
			try {
				
				//��������Ѿ���ɣ�����ٸ���ʱ�䣨�ӿ�������ٶȣ�
				if(pb.isAutoIsFinish()){
					progress-=100;
				}
				//��������Ѿ�������������߳�
				if(pb.isFinish()){
					isRuning=false;
					break;
				}
				//��������Ѿ�����90����û����ɣ����̹߳��𣬵ȴ�����ִ�����
				if(Float.parseFloat(pb.getRate())>=90&&!pb.isAutoIsFinish()){
					continue;
				}
				//���½���
				pb.change();
				if(progress<0){
					progress=100;
				}
				Thread.sleep(progress);
			} catch (InterruptedException e) {
				throw new RuntimeException("auto�������߳��쳣!");
			}
		}
	}
}
