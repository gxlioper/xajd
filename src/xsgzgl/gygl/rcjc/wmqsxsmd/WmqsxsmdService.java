/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-5-10 ����03:36:06 
 */  
package xsgzgl.gygl.rcjc.wmqsxsmd;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2018-5-10 ����03:36:06 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class WmqsxsmdService  extends SuperServiceImpl<WmqsxsmdForm,WmqsxsmdDao> {

	private WmqsxsmdDao dao= new WmqsxsmdDao();
	
	public WmqsxsmdService(){
		super.setDao(dao);
	 }
	
	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-10 ����05:06:03
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getWmqsxsmdById(WmqsxsmdForm myForm) {
		
		return  dao.getWmqsxsmdById(myForm);
	}

	/** 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ�lgx[���ţ�1553]
	 * @���ڣ�2018-5-11 ����10:24:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param model
	 * @return
	 * String �������� 
	 * @throws 
	 */
	public String countXs(WmqsxsmdForm model) {
		
		return dao.countXs(model);
	}

}
