/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016��10��14�� ����4:37:28 
 */  
package com.zfsoft.xgxt.comm.task;

import java.util.concurrent.Callable;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: ������������ȵ�Callable
 * @���ߣ� �����[����:445]
 * @ʱ�䣺 2016��10��14�� ����4:37:29 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public interface ProgressCallable<V> extends Callable<V> {

	
	/**
	 * 
	 * @����: ��ȡ���Ȱٷֱ�
	 * @���ߣ������[���ţ�445]
	 * @���ڣ�2016��10��14�� ����4:38:35
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getProgress();
}
