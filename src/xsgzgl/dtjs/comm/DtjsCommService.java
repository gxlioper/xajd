package xsgzgl.dtjs.comm;

import xgxt.action.Base;
import xgxt.comm.CommService;

public class DtjsCommService extends CommService{
	/**
	 * ��ȡtopTr
	 * @param mk
	 * @return
	 */
	public String[] getTopTr(String mk){
		String[] topTr = null;
	
		if("dtxxgl".equalsIgnoreCase(mk)){
			topTr = new String[]{"ѧ��","����&nbsp;&nbsp;&nbsp;","�Ա�","������ò",Base.YXPZXY_KEY,"רҵ","�༶","��ǰ�׶�����","��ʼʱ��"};
		}else if("dtxxcx".equalsIgnoreCase(mk)){
			topTr = new String[]{"ѧ��","����","�Ա�","������ò",Base.YXPZXY_KEY,"רҵ","�༶","�׶�����","��ʼʱ��","�Ƿ�ǰ�׶�"};
		}
		
		return topTr;
	}
}
