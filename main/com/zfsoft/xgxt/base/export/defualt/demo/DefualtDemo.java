/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-20 ����09:18:32 
 */  
package com.zfsoft.xgxt.base.export.defualt.demo;

import java.util.ArrayList;
import java.util.List;

import com.zfsoft.xgxt.base.export.defualt.IDefualtData;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-20 ����09:18:32 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class DefualtDemo implements IDefualtData{
	public List<String[]> getDefualtData(String drmkdm) {
		List<String[]> list=new ArrayList<String[]>();
		String[] col=new String[2];
		col[0]="����Ĭ��1";
		col[1]="����Ĭ��2";
		list.add(col);
		return list;
	}

}
