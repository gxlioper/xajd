/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2018-3-21 ����09:51:43 
 */  
package xgxt.action.yktyj;

import java.util.TimerTask;

import xgxt.DAO.DAO;
import xgxt.xtwh.XtwhService;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������:ϵͳ���á�ϵͳ��ʼ����һ��ͨԤ��������ʦ�����Ի���
 * �Զ�ִ�еķ�����
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� lgx[����:1553]
 * @ʱ�䣺 2018-3-21 ����09:51:43 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class AutoTask extends TimerTask {
	
	XtwhService service = new XtwhService();
	DAO dao = DAO.getInstance();
	@Override
	public void run() {
		String yjzt = service.getYjzt();
		if("1".equals(yjzt)){
			try {
				boolean rs = dao.runProcuder("{call PRO_XG_XSXX_YKTYJ()}",
					 new String[] {});
				System.out.println(rs);
			} catch (Exception e) {
				cancel();
				e.printStackTrace();
			}
		}else{
			System.out.println("---һ��ͨԤ���ѹر�---");
		}
	}

}
