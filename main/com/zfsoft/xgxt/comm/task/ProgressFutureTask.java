/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016��10��14�� ����2:54:50 
 */  
package com.zfsoft.xgxt.comm.task;

import java.util.concurrent.FutureTask;


/**
 * 
 * @ϵͳ����: ѧ����������ϵͳ
 * @�๦������: ����������ִ�н��ȵ�Callable
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2016��10��14�� ����2:55:26 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 * @param <V>
 */

public class ProgressFutureTask<V> extends FutureTask<V> {

	private ProgressCallable<V> callable;
	
	public ProgressFutureTask(ProgressCallable<V> callable) {
		super(callable);
		this.callable=(ProgressCallable<V>) callable;
	}

	/**
	 * 
	 * @����: ����������Ȱٷֱ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����5:07:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 */
	public String getProgress(){
		return callable.getProgress();
	}
}
