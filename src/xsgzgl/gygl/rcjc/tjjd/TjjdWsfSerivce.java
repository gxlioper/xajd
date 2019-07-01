/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-8-15 ����05:36:09 
 */  
package xsgzgl.gygl.rcjc.tjjd;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �����������ָ��Ի�ͳ�ƹ���ģ��
 * @�๦������: ��¥����ѧԺͳ��
 * @���ߣ� cq [����:785]
 * @ʱ�䣺 2017-8-15 ����05:36:09 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class TjjdWsfSerivce extends SuperServiceImpl<TjjdWsfForm, TjjdWsfDao> {
	
	private static final String LINK_LD = "ld"; //¥��
	private static final String LINK_XY = "xy"; //ѧԺ
	private TjjdWsfDao dao = new TjjdWsfDao();
	
	public TjjdWsfSerivce() {
		super.setDao(dao);
	}
	
	/**
	 * 
	 * @����:��¥��ͳ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-15 ����06:03:39
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getLdtjList(TjjdWsfForm t) throws Exception{
		return dao.getLdtjList(t);
	}
	
	/**
	 * 
	 * @����:��ѧԺͳ��������
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-15 ����06:03:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getXytjList(TjjdWsfForm t) throws Exception{
		return dao.getXytjList(t);
	}
	
	
	/**
	 * 
	 * @����:��ȡ������ͳ��
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2017-8-16 ����11:33:11
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getWsftjList(TjjdWsfForm t) throws Exception{
		//ͳ��ҳ�治��Ҫ��ҳ
		t.getPages().setPageSize(Integer.MAX_VALUE);
		if(LINK_XY.equals(t.getLink())){
			return getXytjList(t);
		}else{
			return getLdtjList(t);
		}
	}

}
