/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2017-6-1 ����04:12:05 
 */  
package xsgzgl.gygl.wsjc.fslr;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.transaction.TransactionControl;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2017-6-1 ����04:12:05 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class KflrService extends SuperServiceImpl<KflrForm, KflrDao>{
	private KflrDao dao = new KflrDao();
	
	/** 
	 * @����:��ȡ�������ҿ۷���ϸ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-1 ����04:13:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String,String>> getKfmx(KflrForm t){
		return dao.getKfmx(t);
	}
	
	/**
	 * @throws Exception 
	 * @throws SQLException  
	 * @����:��������(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-1 ����05:12:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean plInsert(KflrForm t) throws Exception{
		boolean result = true;		
		if(null != t.getDelArr() && t.getDelArr().length>0){			
			result = dao.plSc(t);		
		}if(null != t.getKfArr() && t.getKfArr().length>0){
			result = dao.plInsert(t);
		}
		dao.scFslr(t);//ɾ�����ҷ�����
		return result;		
	}
	
	/**
	 * @throws UnsupportedEncodingException 
	 * @throws SQLException  
	 * @����:����¼��۷�����(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-5 ����02:05:53
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean kflrUpdateKfyj(String jcrcid,String kfyjStr) throws SQLException, UnsupportedEncodingException{
		String[] str = kfyjStr.split("!!");
		String[] target = new String[str.length];
		List<String[]> dels = new ArrayList<String[]>();
		for(int i = 0;i<str.length;i++){
			StringBuilder sq = new StringBuilder();
			String[] strr1 = str[i].split("!@");
			if(strr1[1].split("_").length<2){
				String del = jcrcid + "_" + strr1[0] + "_" + ((strr1[1]).split("_"))[0];
				dels.add(del.split("_"));
				continue;
			}
			sq.append(((strr1[1]).split("_"))[1].replaceAll("��", ","));//���ÿ۷�����
			sq.append("_");
			sq.append(jcrcid);//���ü���ճ�id
			sq.append("_");
			sq.append(strr1[0]);//����¥������
			sq.append("_");
			sq.append(((strr1[1]).split("_"))[0]);//�������Һ�
			target[i] = sq.toString();
		}
		if(dels.size()>1){
			dao.plScFskf(dels);
		}
		return dao.plUpdateKfyj(target);
	}
	
	/** 
	 * @����:�ж��Ƿ�Ϊ�������(������һ�仰�����������������)
	 * @���ߣ����� [���ţ�1282]
	 * @���ڣ�2017-6-6 ����10:17:32
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isBcfs(KflrForm t){
		return dao.isBcfs(t);
	}
	
	/**
	 * @throws SQLException  
	 * @����:ɾ���۷���ϸ(������һ�仰�����������������)
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2017-6-8 ����10:01:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcrcid
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	@TransactionControl
	public boolean plscJcrc(String[] jcrcid) throws SQLException{
		boolean result;
		result = dao.scKfmx(jcrcid);
		if(result){
			dao.plScJcrc(jcrcid);
		}
		return result;
		
	}
	
}
