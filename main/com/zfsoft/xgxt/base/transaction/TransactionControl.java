/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-1-20 ����04:45:28 
 */  
package com.zfsoft.xgxt.base.transaction;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �������ע��
 * @���ߣ� xiaxia[����:1104]
 * @ʱ�䣺 2017-1-20 ����04:45:28 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TransactionControl {


}
