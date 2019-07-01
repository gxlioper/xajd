package com.zfsoft.xgxt.qgzx.jfcjgl;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import xgxt.utils.String.StringUtils;
import xsgzgl.qgzx.cjgl.QgzxCjglForm;
import xsgzgl.qgzx.cjgl.QgzxCjglService;
import xsgzgl.qgzx.cssz.QgzxCsszService;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ڹ���ѧ����ģ��
 * @�๦������: ���ѳ����� ����ά��ҵ����
 * @���ߣ� zhangjw
 * @ʱ�䣺2013-04-15 ����09:46:37
 * @�汾�� V5.1.75
 * @�޸ļ�¼:
 */
public class CjffSjwhService extends SuperServiceImpl<CjffwhForm, CjffSjwhDAO> {

	private CjffSjwhDAO dao = new CjffSjwhDAO();

	public CjffSjwhService() {
		super.setDao(dao);
	}
	/**
	 * 
	 * @����: ��ȡ��λ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����09:53:58
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xn
	 * @param bmdm
	 * @param gwdm
	 * @return
	 * String �������� 
	 */
	public String getCjsxForGw(String xn, String bmdm, String gwdm) {
		String cjsx = null;
		// ��ǰ��λ���õĳ������
		HashMap<String, String> gwxx = dao.getGwxx(xn, bmdm, gwdm);
		if(null!=gwxx){
			cjsx = gwxx.get("gwcjsx");
		}

		// ��������˳������
		if (StringUtils.isNull(cjsx)) {
			QgzxCsszService qgzxCsszService = new QgzxCsszService();
			HashMap<String, String> map = qgzxCsszService.getCssz();
			//�����λû�����ù������ޣ��û��������еĹ�������
			cjsx = map.get("cjsx");
		}
		return cjsx;
	}
	/**
	 * @��������:��ȡʱн
	 * @auther: ������[1692]
	 */
	public String getSxForGw(String xn, String bmdm, String gwdm) {
		String sx = null;
		HashMap<String, String> gwxx = dao.getGwxx(xn, bmdm, gwdm);
		if(null!=gwxx){
			sx = gwxx.get("sx");
		}
		return sx;
	}
	/**
	 *  �Ƿ���ڷ�����Ϣ
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����02:33:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @param xn
	 * @param yrdwdm
	 * @param gwdm
	 * @return
	 * boolean �������� 
	 */
	public boolean isHaveFfxx(String wbh,String xh,String xn,String yrdwdm,String gwdm,String ffsj){
		return dao.isHaveFfxx(wbh,xh, xn, yrdwdm, gwdm,ffsj);
	}
	/**
	 * 
	 * @����: ���¾��ѷ�����Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-23 ����04:15:23
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param cf
	 * @return
	 * boolean �������� 
	 */
	public boolean updataFfxx(CjffwhForm cf){
		return dao.updataFfxx(cf);
	}
	/**
	 * ��ѯ�����б�
	 * 
	 * @param request
	 * @return
	 */
	public HashMap<String, String> getFfmrCs(HttpServletRequest request,
			CjffwhForm myForm) {
		QgzxCjglService qservice = new QgzxCjglService();
		QgzxCjglForm model = new QgzxCjglForm();
		/**
		 * ��ȡ��ѯ���� ��ΪQgzxCjglService��д��Ϊ�˱����ظ������������ת����ȡ
		 */
		model.setXh(myForm.getXh());
		model.setDoType(myForm.getType());
		model.setBz(myForm.getBz());
		model.setGs(myForm.getGs());
		model.setJe(myForm.getJe());
		model.setGwdm(myForm.getGwdm());
		model.setXn(myForm.getXn());
		model.setYrbm(myForm.getYrbm());
		model.setZgzt(myForm.getZgzt());
		// TODO ����������滻���ϸ�ֵ BeanUtils.copyProperties(model, myForm);
		HashMap<String, String> rs = qservice.setFfmrCs(request, model);
		return rs;
	}

	/**
	 * 
	 * @����:����Ƿ���Է���
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2013-12-24 ����10:29:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param yrdwdm
	 * @param nd
	 * @param ffje
	 * @param syje
	 * @return boolean ��������
	 */
	public boolean checkJfyg(String ffje, String syje) {
		if (StringUtils.isNull(ffje)) {
			return false;
		} else if (StringUtils.isNull(syje)) {
			return true;
		}
		if (StringUtils.compareStr(ffje, syje)) {
			return true;
		}
		return false;
	}

	@Override
	public CjffwhForm getModel(CjffwhForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql
				.append(" select * from (select b.wbh,  b.xh, b.xn ,b.xq,b.yrbm, b.sjbs,");
		sql
				.append(" d.gwmc, d.gwdm, c.bmmc yrdw,  decode(b.zgzt, 'zg', '�ڸ�', 'tg', '�˸�', '') zgzt,   b.gs,  b.je,  b.bz,   b.ffsj	 ");
		sql.append(" from xg_qgzx_cjff  b  	 ");
		sql.append(" left join view_xg_qgzx_yrdwdmb c on b.yrbm = c.bmdm	 ");
		sql
				.append(" left join xg_qgzx_gwxxb d on b.gwdm = d.gwdm and b.xn = d.xn and b.yrbm = d.yrdwdm  ) a  where 1 = 1	and a.wbh=? ");
		return dao.getModel(t, sql.toString(), new String[] { t.getWbh() });
	}
	
	
	/** 
	 * @����:�ж��Ƿ���ƶ������������ж����𷢷Ŵ�һ��
	 * @���ߣ�xuwen[���ţ�1426]
	 * @���ڣ�2017��5��3�� ����11:47:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param xh
	 * @return
	 * boolean �������� 
	 * @throws 
	 */
	public boolean isPks(String xh) {
		boolean isPks = dao.isPks(xh);
		return isPks;
	}

}
