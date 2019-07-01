/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-27 ����09:05:18 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.bbgl.FbglBbglForm;
import com.zfsoft.xgxt.xsxx.fbgl.generate.FbCheck;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: ���
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-2-27 ����09:05:18
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class CreateBjdm implements IGenerate {
	private FbCheck check = new FbCheck();
	private String messageError = null;


	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf) {
		IGenerate gg = new PreviewImp();
		return gg.getCode(ftf, fgtxf);
	}

	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf, Object obj) {
		FbglBbglForm fbf = (FbglBbglForm) obj;
		StringBuffer str = new StringBuffer();
		String xxlx = ftf.getXxlx();// ѡ������
		String zd = ftf.getTjszzd();// �ֶ�����
		String xxz = fgtxf.getXxz();// ѡ��ֵ
		String wsbl = fgtxf.getWsbl();// ĩβ�����־
		String ylz = ftf.getYlzqz();// Ԥ��ֵ
		String ylzs[] = null;
		String sfkxg = ftf.getSfkxg();// �Ƿ���޸�
		// �ֶε�ǰ�е�Ĭ��ֵ
		String mrz = "";
		// ����Ԥ��ֵ���ȡ���õ�Ԥ��ֵ������������޸���ȡ�ֶζ�Ӧֵ
		if (StringUtils.isNotNull(ylz)) {
			ylzs = ylz.split(_YLZ_SPLIT);
			// ��ȡԤ��ֵ
			mrz = getValue(ylzs[1], fbf.getPk());
		} else if (_SFKXG_KXG.equals(sfkxg) && !zd.startsWith(_TSZD)) {
			// ���޸�ֵ
			mrz = getValue(zd, fbf.getPk());
		}
		if (TJ_ZDY.equals(zd)) {
			str.append(fgtxf.getXxz());
		} else if (TJ_LSH.equals(zd)) {
			String maxBjbh="";
			Integer nowLsh=0;
			Map<String, String> map = fbf.getRowData();
			// �������
			String lsh = map.get("lsh");
			//�û�ǰ̨�����µ���ˮ����ȡ�û�����ֵ����֮ȡ���ݿ�Ĭ����ˮ��
			if("1".equals(fbf.getIsNewLsh())&&StringUtils.isNotNull(lsh)){
				nowLsh=StringUtils.paseStringToInteger(lsh);
				//��֤�û����õ���ˮ���Ƿ��ظ�
				if(CheckLshIsExist(fbf,lsh)){
					messageError = MessageKey.FBGL_CHECK_LSH_REPEART;
				}
				
			}else{
			 maxBjbh = geteMaxLsh(fbf);
			// ƫ��������ҳ��ɾ����ƫ������ҳ�����ӻ�ɾ����������δ��⣬������Ҫ�Դ���΢����
			String skewing = fbf.getSkewing();
			nowLsh = StringUtils.paseStringToInteger(maxBjbh) + 1
					+ StringUtils.paseStringToInteger(skewing);
			String bjbhStr = nowLsh.toString();
			}
			/*
			 * String lsh = ""; //�����趨��ˮ��λ�� ���� for (int i = bjbhStr.length(); i
			 * < Integer.parseInt(fgtxf.getXxz()); i++) { lsh += "0"; }
			 * str.append(lsh + nowLsh);
			 */
			str.append(nowLsh);
			// ��ˮ�����⴦��ͬʱ���µ� bjbh
			fbf.setBjbh(str.toString());
			if (!check.checkLength(nowLsh, Integer.parseInt(fgtxf.getXxz()))) {
				messageError = MessageKey.FBGL_CHECK_LSHBZ;
			}
		} else if (_XXLX_DEFUALTE.equals(xxlx)) {
			str.append(mrz);
		} else if (_XXLX_TEXT.equals(xxlx)) {
			str.append(mrz);
		} else if (_XXLX_SELECT.equals(xxlx)) {
			String sjylz = mrz;
			Integer xxzI = StringUtils.paseStringToInteger(xxz);
			// ���ѡ���ֵ�����ֶγ��ȣ����������Ϊ�ֶγ���
			Integer length = xxzI > sjylz.length() ? sjylz.length() : xxzI;
			// ��ѡ��ֵ��ȡ����λ��
			str
					.append(sjylz.substring(sjylz.length() - length, sjylz
							.length()));
		} else if (_XXLX_REGION.equals(xxlx)) {
			String sjylz = mrz;
			String qzj[] = xxz.split(_QJ_SPLIT);
			Integer start = StringUtils.paseStringToInteger(qzj[0]);
			Integer length = StringUtils.paseStringToInteger(qzj[1]);
			// ���ѡ���ֵ�����ֶγ��ȣ����������Ϊ�ֶγ���
			Integer end = start + length >= sjylz.length() ? sjylz.length()
					: start + length;
			// ������ֵ��ȡ����λ��
			str.append(sjylz.substring(start, end));
		}
		// ������޸ģ��鿴�Ƿ�����趨ֵ����������򸲸�ԭֵ
		if (_SFKXG_KXG.equals(fgtxf.getSfkxg())) {
			// ҳ�洫��ֵ
			String sdz = fbf.getRowData().get(zd);
			if (StringUtils.isNotNull(sdz)) {
				str.delete(0, str.length());
				str.append(sdz);
			}
		}
		// �Ƿ���
		if (SFBL_BL.equals(wsbl)) {
			// ѡ��ֵ���
			Integer ws = StringUtils.paseStringToInteger(xxz);
			if (str.length() < ws) {
				// �����Զ�����
				int cs = ws - str.length();
				for (int i = 0; i < cs; i++) {
					str.insert(0, "0");
				}
			}
		}
		return str.toString();
	}

	private String getValue(String zd, String pk) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("select " + zd + " from VIEW_FBGL_BBGL where pk=?");
			return DAO.getInstance().getOneRs(sb.toString(),
					new String[] { pk }, zd);
		} catch (Exception e) {
			// �����ѯ�޴��ֶ� ����Ĭ��ֵ
			return null;
		}
	}

	/**
	 * 
	 * @����: ��ȡ�����ˮ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-27 ����10:27:49
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param fbf
	 * @return String ��������
	 */
	private String geteMaxLsh(FbglBbglForm fbf) {
		List<String> param=new LinkedList<String>();
		StringBuffer sb = new StringBuffer();
		sb
				.append("select Max(to_number(bjbh)) maxlsh from xg_xsxx_fbbxhgl_bjdm_lsb where 1=1 ");
		if(StringUtils.isNotNull(fbf.getZydm())){
			sb.append(" and zydm=?");
			param.add(fbf.getZydm());
		}
		if(StringUtils.isNotNull(fbf.getBmdm())){
			sb.append(" and bmdm=?");
			param.add(fbf.getBmdm());
		}
		if(StringUtils.isNotNull(fbf.getPycc())){
			sb.append(" and pycc=?");
			param.add(fbf.getPycc());
		}
		if(StringUtils.isNotNull(fbf.getXz())){
			sb.append(" and xz=?");
			param.add(fbf.getXz());
		}
		if(StringUtils.isNotNull(fbf.getNj())){
			sb.append(" and nj=?");
			param.add(fbf.getNj());
		}
		return DAO.getInstance().getOneRs(
				sb.toString(),param.toArray(new String[]{}), "maxlsh");
	}
	private boolean CheckLshIsExist(FbglBbglForm fbf,String lsh) {
		List<String> param=new LinkedList<String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1)num from xg_xsxx_fbbxhgl_bjdm_lsb where bjbh=? ");
		param.add(lsh);
		if(StringUtils.isNotNull(fbf.getZydm())){
			sb.append(" and zydm=?");
			param.add(fbf.getZydm());
		}
		if(StringUtils.isNotNull(fbf.getBmdm())){
			sb.append(" and bmdm=?");
			param.add(fbf.getBmdm());
		}
		if(StringUtils.isNotNull(fbf.getPycc())){
			sb.append(" and pycc=?");
			param.add(fbf.getPycc());
		}
		if(StringUtils.isNotNull(fbf.getXz())){
			sb.append(" and xz=?");
			param.add(fbf.getXz());
		}
		if(StringUtils.isNotNull(fbf.getNj())){
			sb.append(" and nj=?");
			param.add(fbf.getNj());
		}
		return Integer.parseInt(DAO.getInstance().getOneRs(
				sb.toString(),param.toArray(new String[]{}), "num"))>0;
	}

	/*
	 * ����: @see com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate#getErrorMessage()
	 */

	public String getErrorMessage() {
		return messageError;
	}
}
