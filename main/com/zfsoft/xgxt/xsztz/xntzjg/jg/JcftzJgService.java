/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2016-3-31 ����11:19:44 
 */  
package com.zfsoft.xgxt.xsztz.xntzjg.jg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.Constants;
import com.zfsoft.xgxt.comm.shlc.service.ShlcInterface;
import com.zfsoft.xgxt.comm.shlc.service.impl.CommShlcImpl;
import com.zfsoft.xgxt.xsztz.xntzjg.sh.JcftzShDao;
import com.zfsoft.xgxt.xsztz.xntzjg.sh.JcftzShService;
import com.zfsoft.xgxt.xsztz.xntzjg.sq.JcftzSqDao;

/** 
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: XXXX����ģ��
 * @�๦������: TODO(������һ�仰��������������) 
 * @���ߣ� ����[����:1282]
 * @ʱ�䣺 2016-3-31 ����11:19:44 
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��  
 */

public class JcftzJgService extends SuperServiceImpl<JcftzJgForm, JcftzJgDao>{
	private JcftzJgDao dao = new JcftzJgDao();
	private ShlcInterface shlc = new CommShlcImpl();
	
	/** 
	 * @����:�Ƿ��н����¼
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-31 ����11:35:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isHaveRecord(JcftzJgForm t){
		return dao.isHaveRecord(t);
	} 
	
	/** 
	 * @����:�����ͨ��ɾ��ԭ�м�¼
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-31 ����11:40:02
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delForSq(JcftzJgForm t) throws Exception{
		return dao.delForSq(t);
	}
	
	/** 
	 * @����:�����϶�״̬ 
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-3-31 ����11:51:12
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean updateRenDing(JcftzJgForm t) throws Exception {
		return dao.updateRenDing(t);
	}
	
	/** 
	 * @����:�õ���Ŀ�б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-5 ����10:24:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXmSelectList(JcftzJgForm t,User user){
       return dao.getXmSelectList(user,t);
	}
	
	/** 
	 * @����:�õ���Ҫ�϶���ѧ���б�
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-5 ����02:17:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsListForRenDing(JcftzJgForm t,User user) throws Exception{
		return dao.getXsListForRenDing(t, user);
	}
	
	/** 
	 * @����:����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-5 ����06:37:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean saveJg(JcftzJgForm t) throws Exception{
		boolean result = false;
		String csms = t.getCsms();
		
		String[] bz1s=t.getBz1s();
		String[] bz2s=t.getBz2s();
		String[] bz3s=t.getBz3s();
		String[] bz4s=t.getBz4s();
		String[] bz5s=t.getBz5s();
		
		if("save".equalsIgnoreCase(t.getType())){
			String[] xhs = t.getXhs();
			String[] jxdms = t.getJxdms();
			String[] tzhjcfs = t.getTzhjcfs();
			String[] sfqqs = t.getSfqqs();
			if("1".equals(csms)){
				for(int i=0;i<xhs.length;i++){				
					t.setJgid(null);				
					t.setXh(xhs[i]);
					if(jxdms == null || jxdms.length == 0){
						t.setJxdm("");
					}
					else if("0".equalsIgnoreCase(jxdms[i])){
						t.setJxdm("");
					}else{   
						t.setJxdm(jxdms[i]);
					}			
					t.setTzhjcf(tzhjcfs[i]);
					t.setSfqq(sfqqs[i]);
					
					t.setBz1(bz1s[i]);
					t.setBz2(bz2s[i]);
					t.setBz3(bz3s[i]);
					t.setBz4(bz4s[i]);
					t.setBz5(bz5s[i]);
					
					t.setXfrdjgzt(Constants.TG);
					result = dao.updateRenDing(t);
						result = dao.runInsert(t);
					
				}
			}else if("2".equals(csms)){
				t.setXfrdjgzt(Constants.TG);
				this.updateTtxx(t);
				result = dao.updateRenDing(t);
			}else{
				return false;
			}
			
		}else{
			String[] jgids = t.getJgids();
			String[] xhs = t.getXhs();
			String[] jxdms = t.getJxdms();
			String[] tzhjcfs = t.getTzhjcfs();
			String[] sfqqs = t.getSfqqs();
			for(int i=0;i<xhs.length;i++){				
				t.setJgid(jgids[i]);				
				t.setXh(xhs[i]);
				if(jxdms == null || jxdms.length == 0){
					t.setJxdm("");
				}
				else if("0".equalsIgnoreCase(jxdms[i])){
					t.setJxdm("");
				}else{
					t.setJxdm(jxdms[i]);
				}			
				t.setTzhjcf(tzhjcfs[i]);
				t.setSfqq(sfqqs[i]);
				t.setXfrdjgzt(Constants.TG);
				t.setBz1(bz1s[i]);
				t.setBz2(bz2s[i]);
				t.setBz3(bz3s[i]);
				t.setBz4(bz4s[i]);
				t.setBz5(bz5s[i]);
				result = dao.runUpdate(t);
			}
		}
		
		
		
		return result;
	}
	
	/** 
	 * @����:���ɾ����ר���ڽ����
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-8 ����01:54:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * @throws Exception
	 * boolean �������� 
	 * @throws 
	 */
	public boolean delForJg(JcftzJgForm t) throws Exception{
		return dao.delForJg(t);
	}
	
	/** 
	 * @����:�õ���Ҫ�޸��϶���ѧ�� 
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-8 ����03:47:09
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsListForUpdate(JcftzJgForm t, User user) throws Exception{
		return dao.getXsListForUpdate(t, user);
	}
	
	/** 
	 * @����:�õ���Ŀ��Ϣ
	 * @���ߣ�����[���ţ�1282]
	 * @���ڣ�2016-4-8 ����04:17:25
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXmxx(JcftzJgForm t){
		return dao.getXmxx(t);
	}
	
	/**
	 * 
	 * @����: �õ�Ҫ�϶�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-5 ����11:24:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getTtListForRenDing(JcftzJgForm t, User user) throws Exception {
	   return dao.getTtListForRenDing(t, user);
	}
		
	/**
	 * 
	 * @����: ������Ŀ�϶����ݲ�������
	 * @���ߣ�yxy[���ţ�1206]
	 * @���ڣ�2016-8-4 ����05:27:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param jcftzJgForm
	 * @throws Exception
	 * void �������� 
	 * @throws
	 */
	public void updateTtxx(JcftzJgForm t) throws Exception{
		List<HashMap<String,String>> list = new JcftzSqDao().getJcfTzTtCyxx(t.getXmdm());
		String[] jgids = t.getJgids();
		String[] jxdms = t.getJxdms();
		String[] tzhjcfs = t.getTzhjcfs();
		String[] sfqqs = t.getSfqqs();
		
		String[] bz1s=t.getBz1s();
		String[] bz2s=t.getBz2s();
		String[] bz3s=t.getBz3s();
		String[] bz4s=t.getBz4s();
		String[] bz5s=t.getBz5s();
		
		ArrayList<String[]> paralist = new ArrayList<String[]>();
		for(HashMap<String,String> map :list){
			String jgid = map.get("jgid");
			for (int i = 0; i < jgids.length; i++) {
				if(jgids[i].equals(jgid)){
					String jxdm = "";
					if(jxdms == null || jxdms.length == 0 || StringUtils.isNull(jxdms[0])){
						jxdm = "";
					}else if("0".equalsIgnoreCase(jxdms[i])){
						jxdm = "";
					}else{   
						jxdm = jxdms[i];
					}			
					paralist.add(new String[]{map.get("xmdm"),map.get("xh"),tzhjcfs[i],jxdm,sfqqs[i],t.getLylcywid(),"1",bz1s[i],bz2s[i],bz3s[i],bz4s[i],bz5s[i]});
					break;
				}
			}
		}
		new JcftzShDao().updateTtxx(t, paralist);
	}
}
