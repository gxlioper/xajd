/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2013-12-13 ����11:39:22 
 */  
package xgxt.szdw.bjlh;

import java.util.HashMap;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ˼������
 * @�๦������: ˼�����鿼�˲������ó�ʼ��
 * @���ߣ� ������
 * @ʱ�䣺 2013-12-13 ����11:39:22 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class SzkhCssz {
	public static String KHDX ="all";   //���˶���fdy������Ա���ˡ�bzr�������ο��ˡ�all������Ա�����ο��ˣ�Ĭ��all;
	public static String KHRY ="all";   //������Ա��xs.ѧ������;cpz.�����鿼�ˣ�all.ѧ���������鿼�ˣ�Ĭ��all;
	
	static{
		csInit();
	}
	public static void csInit(){
		SzkhCsszDao dao=new SzkhCsszDao(); 
		HashMap<String, String> map=dao.getSzkhCssz();
		
		if(map.get("khry") !=null && !"".equalsIgnoreCase(map.get("khry"))){
			KHRY = map.get("khry");
		}
		if(map.get("khdx") !=null && !"".equalsIgnoreCase(map.get("khdx"))){
			KHDX = map.get("khdx");
		}
	}
}
