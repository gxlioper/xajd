/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017��1��13�� ����2:26:41 
 */  
package com.zfsoft.xgxt.zxdk.rwfbybc.dcjg;

import java.util.ArrayList;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: �Զ�������ʼ���йض����ArrayList��������struts��form����List
 * @���ߣ� xuwen[����:1426]
 * @ʱ�䣺 2017��1��13�� ����2:26:41 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class AutoArrayList extends ArrayList{
	private Class itemClass;
	 public AutoArrayList(Class itemClass) {
	  this.itemClass = itemClass;
	 }
	 public Object get(int index) {
	  try {
	   while (index >= size()) {
	    add(itemClass.newInstance());
	   }
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  return super.get(index);
	 }
}
