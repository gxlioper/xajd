/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2015-7-13 ����09:12:57 
 */  
package xsgzgl.gygl.gybxgl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ChenQ[����:856]
 * @ʱ�䣺 2015-7-13 ����09:12:57 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class GybxRyglService extends SuperServiceImpl<GybxRyglForm, GybxRyglDao> {
	private GybxRyglDao dao = new GybxRyglDao();

	@SuppressWarnings("deprecation")
	public GybxRyglService() {
		super.setDao(dao);
	}
	
	public List<HashMap<String, String>> getBxlbYhList(GybxRyglForm model) throws Exception{
		return dao.getBxlbYhList(model);
	}
	public String saveBxry(GybxRyglForm model) throws Exception{
		String[] yhlist = model.getYhAll();
		String messageKey =  MessageKey.SYS_SAVE_SUCCESS;
		for(int i=0;i<yhlist.length;i++){
				boolean flag = dao.saveBxry(model.getBxlb(), yhlist[i]);
				if(!flag){
					messageKey =  MessageKey.SYS_SAVE_FAIL;
					break;
				}
		}
		return messageKey;
	}
	
	public String delBxry(GybxRyglForm model) throws Exception{
		String[] yhlist = model.getYhAll();
		String messageKey =  MessageKey.SYS_DEL_SUCCESS;
		for(int i=0;i<yhlist.length;i++){
			boolean flag = dao.delBxry(model.getBxlb(), yhlist[i]);
			if(!flag){
				messageKey =  MessageKey.SYS_DEL_FAIL;
				break;
			}
		}
		return messageKey;
	}
}
