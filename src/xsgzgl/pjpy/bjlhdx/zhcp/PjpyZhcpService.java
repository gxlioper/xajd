package xsgzgl.pjpy.bjlhdx.zhcp;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: ѧ������ϵͳ
 * </p>
 * <p>
 * Description: ��������_�ۺϲ���_ͨ��_Service��
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author qlj
 * 
 * @version 1.0
 */

public class PjpyZhcpService  extends xsgzgl.pjpy.general.zhcp.PjpyZhcpService {

	PjpyZhcpDAO dao=new PjpyZhcpDAO();
	
	/**
	 * �۲���Ŀ�ּ���
	 * �۲��ܷ֡��۲�ϵ��ּ���
	 * @param model
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean zcxmjs(PjpyGeneralForm model,User user) throws Exception {
		
		boolean blog=true;

		// =============������Ŀ����Ŀ�ּ��� begin===================
		List<HashMap<String,String>>zcjsList=dao.getZcjsSql(model, user);
		// =============������Ŀ����Ŀ�ּ��� end===================
		
		// -----------ִ�� begin---------------
		blog=zcxmfjs(zcjsList);
		
		//9��24�ո���ѧУ����ʦQQҪ��ȡ�������ת��
//		if(blog){
//			// �������㣨100����ת����5���ƣ�
//			blog=dao.updateZcf();
//			
//		}
		// -----------ִ�� end---------------
		
		return blog;
	}
}
