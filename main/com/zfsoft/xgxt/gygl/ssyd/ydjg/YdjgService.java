package com.zfsoft.xgxt.gygl.ssyd.ydjg;

import java.util.HashMap;
import java.util.List;
import xgxt.utils.GetTime;
import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.gygl.ssyd.ydsh.YdshDAO;
import com.zfsoft.xgxt.gygl.ssyd.ydsq.YdsqService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: ��Ԣ����
 * @�๦������:�����춯���
 * @���ߣ� qilm
 * @ʱ�䣺2013-9-29
 * @�汾�� V1.0
 */
public class YdjgService extends SuperServiceImpl<YdjgForm, YdjgDAO> {
	/**
	 * ������Դ��0[�����׷��]
	 */
	private static final String SJLY_JG = "0";

	/**
	 *  ����
	 */
	public static String YDLX_TS = "00";
	/**
	 *  �������
	 */
	public static String YDLX_SSTZ = "01";
	/**
	 *  ��ס
	 */
	public static String YDLX_SSRZ = "03";

	
	/**
	 *  ��ǰѧ��ѧ��FLG (0:��ǰѧ�ڣ�
	 */
	public static final String CURR_XNXQ_FLG_Y = "0";

	/**
	 *  ��ǰѧ��ѧ��FLG (1:���ǵ�ǰѧ�ڣ�
	 */
	public static final String CURR_XNXQ_FLG_N = "1";
	
	/**
	 *  �Ƿ������¼FLG (0:�ǣ�
	 */
	public static final String SFZJJL_FLG_Y = "0";
	/**
	 * �Ƿ�λ��ʼ��[0:��ʼ��]
	 */
	private static final String  SFCWCSH_CSH = "0";
	/**
	 * �Ƿ�λ��ʼ��[1:����ʼ��]
	 */
	private static final String  SFCWCSH_BCSH = "1";

	
	private YdjgDAO dao = new YdjgDAO();
	
	public YdjgService() {
		super.setDao(dao);
	}
	

	/**
	 * 
	 * @����:���������춯���
	 * @���ߣ�qilm
	 * @���ڣ�2013-9-9 ����06:46:35
	 * @param qf
	 * @return boolean ��������
	 * @throws Exception
	 */
	public boolean save(YdjgForm model) throws Exception {

		boolean returnFlg = true;
		YdsqService ydsqService = new YdsqService();
		YdshDAO ydshdao = new YdshDAO();
		String guid = UniqID.getInstance().getUniqIDHash();

		// �Ƿ�λ��ʼ��
		String sfcwcsh = model.getSfcwcsh();
		
		//�����춯ID
		model.setSsydid(guid);
		// ����ʱ��
		model.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
		// ������Դ
		model.setSjly(SJLY_JG);
		
		// �춯ǰ��λ��Ϣ
		HashMap<String,String> ydqCwxx = ydsqService.getCwxxForXh(model.getXh());
		model.setYdqlddm(ydqCwxx.get("lddm"));
		model.setYdqldmc(ydqCwxx.get("ldmc"));
		model.setYdqqsh(ydqCwxx.get("qsh"));
		model.setYdqcwh(ydqCwxx.get("cwh"));
		model.setYdqqsrzsj(ydqCwxx.get("rzsj"));
		
		// ����
		if(YDLX_TS.equals(model.getSsydlx())){

			// ���� �����
			returnFlg = super.runInsert(model);
			
			if(returnFlg){
				HashMap<String, String> cwxxMap = new HashMap<String, String>();
	
				cwxxMap.put("xh", "");
				cwxxMap.put("lddm", model.getYdqlddm());
				cwxxMap.put("qsh", model.getYdqqsh());
				cwxxMap.put("cwh", model.getYdqcwh());
				cwxxMap.put("rzsj", "");//��סʱ��
				
				// �Ƿ��ʼ��
				if(SFCWCSH_CSH.equals(sfcwcsh)){
	
					cwxxMap.put("nj", "");
					cwxxMap.put("bjdm", "");
					cwxxMap.put("zydm", "");
					cwxxMap.put("xydm", "");
				}
				
				// ��λ��Ϣ����
				returnFlg =  ydshdao.updateCwxxb(cwxxMap);
			}
			
			// �������
		}else if(YDLX_SSTZ.equals(model.getSsydlx())){
			
			//������λ��Ϣ
			String ydhcwxx = model.getCwxx();
			
			if(ydhcwxx!=null && !"".equals(ydhcwxx)){
				String[] cwxx = ydhcwxx.split("_");
				
				// ��ѯ
				HashMap<String, String> cwxxMapGet = ydsqService.getCwxx(cwxx[0], cwxx[1], cwxx[2]);

				// �춯��λ��Ϣ
				String ydhlddm = cwxxMapGet.get("lddm");
				String ydhldmc = cwxxMapGet.get("ldmc");
				String ydhqsh = cwxxMapGet.get("qsh");
				String ydhcwh = cwxxMapGet.get("cwh");
				String ydhrzsj = cwxxMapGet.get("rzsj");
				
				//������λ��Ϣ
				model.setYdhlddm(ydhlddm);
				model.setYdhldmc(ydhldmc);
				model.setYdhqsh(ydhqsh);
				model.setYdhcwh(ydhcwh);
				
				// �춯������ԭ����
				model.setYdhnj(cwxxMapGet.get("nj"));
				model.setYdhxydm(cwxxMapGet.get("xydm"));
				model.setYdhzydm(cwxxMapGet.get("zydm"));
				model.setYdhbjdm(cwxxMapGet.get("bjdm"));

				// �O���{���r�g/ԭ��
				model.setTstzsj(model.getTzsssj());
				model.setTstzyy(model.getTzssyy());

				// ���� �����
				returnFlg = super.runInsert(model);
				
				HashMap<String, String> cwxxMap = new HashMap<String, String>();
				
				// �춯��Ĵ�λ��Ϣ����
				cwxxMap.put("lddm", ydhlddm);
				cwxxMap.put("qsh", ydhqsh);
				cwxxMap.put("cwh", ydhcwh);
				cwxxMap.put("xh", model.getXh());
				HashMap<String, String> xsxx = dao.getXsInfo(model.getXh());
				cwxxMap.put("nj", xsxx.get("nj"));
				cwxxMap.put("bjdm", xsxx.get("bjdm"));
				cwxxMap.put("zydm", xsxx.get("zydm"));
				cwxxMap.put("xydm", xsxx.get("xydm"));
				cwxxMap.put("rzsj", model.getTstzsj());//��סʱ��
				
				// ��λ��Ϣ����
				returnFlg = ydshdao.updateCwxxb(cwxxMap);

				// �춯����ѧ��
				String ydglxh = cwxxMapGet.get("xh")==null ?"":cwxxMapGet.get("xh");
				
				// ѧ���Ե�������Ե��춯�����
				if(!"".equals(ydglxh)){

					YdjgForm ydjgFormGl = new YdjgForm();
					String guidGl = UniqID.getInstance().getUniqIDHash();

					ydjgFormGl.setSsydid(guidGl);
					ydjgFormGl.setXh(ydglxh);
					ydjgFormGl.setCzsj(GetTime.getTimeByFormat("yyyy-mm-dd hh24:mi:ss"));
					ydjgFormGl.setXn(model.getXn());
					ydjgFormGl.setXq(model.getXq());
					ydjgFormGl.setSsydlx(model.getSsydlx());
					ydjgFormGl.setTstzsj(model.getTstzsj());
					ydjgFormGl.setTstzyy(model.getTstzyy());
					ydjgFormGl.setBz(model.getBz());
					//����ǰ��λ��Ϣ
					ydjgFormGl.setYdqlddm(ydhlddm);
					ydjgFormGl.setYdqldmc(ydhldmc);
					ydjgFormGl.setYdqqsh(ydhqsh);
					ydjgFormGl.setYdqcwh(ydhcwh);
					//�����ᴲλ��Ϣ
					ydjgFormGl.setYdhlddm(model.getYdqlddm());
					ydjgFormGl.setYdhldmc(model.getYdqldmc());
					ydjgFormGl.setYdhqsh(model.getYdqqsh());
					ydjgFormGl.setYdhcwh(model.getYdqcwh());		
					ydjgFormGl.setSfcwcsh(SFCWCSH_BCSH);//�Ƿ�λ��ʼ��
					ydjgFormGl.setSjly(SJLY_JG);		//�趨������Դ
					ydjgFormGl.setSsydsqid("");
					ydjgFormGl.setYdqqsrzsj(ydhrzsj);//�춯ǰ������סʱ��

					// ���� �����
					returnFlg = super.runInsert(ydjgFormGl);

					cwxxMap = new HashMap<String, String>();
					
					// �춯ǰ�Ĵ�λ��Ϣ����
					cwxxMap.put("lddm", model.getYdqlddm());
					cwxxMap.put("qsh", model.getYdqqsh());
					cwxxMap.put("cwh", model.getYdqcwh());
					cwxxMap.put("xh", ydglxh);
					HashMap<String, String> xsxxGl = dao.getXsInfo(ydglxh);
					
					cwxxMap.put("nj", xsxxGl.get("nj"));
					cwxxMap.put("bjdm", xsxxGl.get("bjdm"));
					cwxxMap.put("zydm", xsxxGl.get("zydm"));
					cwxxMap.put("xydm", xsxxGl.get("xydm"));
					cwxxMap.put("rzsj", model.getTstzsj());//��סʱ��
					returnFlg = ydshdao.updateCwxxb(cwxxMap);
					
					//�޹���ѧ��
				}else if( returnFlg){
					
					cwxxMap =  new HashMap<String, String>() ;
					cwxxMap.put("xh", "");
					cwxxMap.put("lddm", model.getYdqlddm());
					cwxxMap.put("qsh", model.getYdqqsh());
					cwxxMap.put("cwh", model.getYdqcwh());
					cwxxMap.put("rzsj", "");//��סʱ��
					
					// �Ƿ��ʼ��
					if(SFCWCSH_CSH.equals(sfcwcsh)){
		
						cwxxMap.put("nj", "");
						cwxxMap.put("bjdm", "");
						cwxxMap.put("zydm", "");
						cwxxMap.put("xydm", "");
					}
					returnFlg = ydshdao.updateCwxxb(cwxxMap);
				}
			}
		}else if(YDLX_SSRZ.equals(model.getSsydlx())){
			
			//������λ��Ϣ
			String ydhcwxx = model.getRzcwxx();
			
			// �춯��λ��Ϣ
			String ydhlddm = "";
			String ydhldmc = "";
			String ydhqsh = "";
			String ydhcwh = "";
			
			if(ydhcwxx!=null && !"".equals(ydhcwxx)){
				String[] cwxx = ydhcwxx.split("_");
				
				// ��ѯ
				HashMap<String, String> cwxxMapGet = ydsqService.getCwxx(cwxx[0], cwxx[1], cwxx[2]);

				// �춯��λ��Ϣ
				ydhlddm = cwxxMapGet.get("lddm");
				ydhldmc = cwxxMapGet.get("ldmc");
				ydhqsh = cwxxMapGet.get("qsh");
				ydhcwh = cwxxMapGet.get("cwh");
//				String ydhrzsj = cwxxMapGet.get("rzsj");
				
				//������λ��Ϣ
				model.setYdhlddm(ydhlddm);
				model.setYdhldmc(ydhldmc);
				model.setYdhqsh(ydhqsh);
				model.setYdhcwh(ydhcwh);
				
				// �춯������ԭ����
				model.setYdhnj(cwxxMapGet.get("nj"));
				model.setYdhxydm(cwxxMapGet.get("xydm"));
				model.setYdhzydm(cwxxMapGet.get("zydm"));
				model.setYdhbjdm(cwxxMapGet.get("bjdm"));
			}

			// �O���{���r�g/ԭ��
			model.setTstzsj(model.getRzsssj());
			model.setTstzyy(model.getRzssyy());

			// ���� �����
			returnFlg = super.runInsert(model);
			
			HashMap<String, String> cwxxMap = new HashMap<String, String>();
			
			// �춯��Ĵ�λ��Ϣ����
			cwxxMap.put("lddm", ydhlddm);
			cwxxMap.put("qsh", ydhqsh);
			cwxxMap.put("cwh", ydhcwh);
			cwxxMap.put("xh", model.getXh());
			HashMap<String, String> xsxx = dao.getXsInfo(model.getXh());
			cwxxMap.put("nj", xsxx.get("nj"));
			cwxxMap.put("bjdm", xsxx.get("bjdm"));
			cwxxMap.put("zydm", xsxx.get("zydm"));
			cwxxMap.put("xydm", xsxx.get("xydm"));
			cwxxMap.put("rzsj", model.getTstzsj());//��סʱ��
			cwxxMap.put("rzyydm", model.getTstzyy());
			
			// ��λ��Ϣ����
			ydshdao.updateCwxxb(cwxxMap);
		}
		
		return returnFlg;
	}



	/** 
	 * @����:ѧ�������һ�������춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����10:07:58
	 * @param myForm
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXsydInfo(YdjgForm myForm) {

		return dao.getXsydInfo(myForm);
	}

	/**
	 * @throws Exception  
	 * @����:ѧ������ĸ��������춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����10:07:58
	 * @param myForm
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getXsYdList(YdjgForm myForm) throws Exception {
		
		return dao.getXsYdList(myForm);
	}

	/** 
	 * @����:��ѧ��ѧ��ȡ����λ�춯��Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-8 ����05:14:15
	 * @param myForm
	 * @param currXnxqFlgY
	 * @return
	 * List<HashMap<String,String>> �������� 
	 * @throws 
	 */
	public List<HashMap<String, String>> getQsYdList(YdjgForm myForm,
			String currXnxqFlg) throws Exception {
		
		return dao.getQsYdList(myForm,currXnxqFlg);
	}
	
	/**
	 * 
	 * @����:ȡ�������춯�����Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-9 ����10:41:59
	 * @param model
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYdjg(YdjgForm model) throws Exception {
		return dao.getYdjg(model);
	}


	/**
	 * 
	 * @����:ɾ���������Ӧ�������춯�����
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param ssydsqid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public int runDeleteYdjg(String ssydsqid) throws Exception  {
		return dao.runDeleteYdjg(ssydsqid);
	}
	
	/**
	 * 
	 * @����:��������IDȡ���춯���
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10
	 * @param ssydsqid
	 * @return
	 * @throws Exception
	 * int �������� 
	 * @throws
	 */
	public List<HashMap<String, String>> getYdjg(String ssydsqid) throws Exception{
		return dao.getYdjg(ssydsqid);
	}
	/** 
	 * @����:ѧ�������Ϣ
	 * @���ߣ�qilm
	 * @���ڣ�2013-10-10 ����11:59:38
	 * @param model
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws 
	 */
	public HashMap<String, String> getXsInfo(String xh) {
		return dao.getXsInfo(xh);
	}
	
	/**
	 * 
	 * @����:���������춯ID��ȡ�춯��Ϣ
	 * @���ߣ�cq [���ţ�785]
	 * @���ڣ�2014-6-3 ����05:14:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ssydid
	 * @return
	 * HashMap<String,String> �������� 
	 * @throws
	 */
	public HashMap<String, String> getYdxx(String ssydid){
		
		return dao.getYdxx(ssydid);
	}
	
}
