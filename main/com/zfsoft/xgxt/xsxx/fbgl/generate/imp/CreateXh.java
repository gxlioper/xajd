/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-17 ����09:52:28 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.xsxx.fbgl.generate.BaseCreate;
import com.zfsoft.xgxt.xsxx.fbgl.generate.FbCheck;
import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ģ��
 * @�๦������: ����ѧ��
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-17 ����09:52:28
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class CreateXh extends BaseCreate implements IGenerate {
	private FbCheck check = new FbCheck();
	private String messageError = null;
	StringBuffer sb = new StringBuffer();
	public String getBaseSql() {
		sb.append("select * from (");
		sb
				.append("select t.*,t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz pk from xg_xsxx_fbgl_xsxx_lsb t where 1=1 ");
		sb.append(" and xh is null)");
		String pk = getParam().get(0);
		if (StringUtils.isNotNull(pk)) {
			sb.append(" where pk in(");
			for (String id : pk.split(",")) {
				sb.append("'");
				sb.append(id);
				sb.append("',");
			}
			sb.append("'-1')");
		}
		getParam().remove(0);
		return sb.toString();
	}

	@Override
	public List<HashMap<String, String>> generate(String pzgzid,
			List<HashMap<String, String>> bj) {
		List<HashMap<String, String>> tjPzxx = getTJpzxxId(pzgzid);
		tjPzxx = getGroupByData(tjPzxx);
		return tjPzxx;
	}

	protected String getValue(String zd, String pk) {
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
	private String geteMaxLsh(FbglXsxxForm fbf) {
		String pzgzid = getMapParam("pzgzid");
		//ѧ����ˮ�Ź���
		String xhGzlx =getXhgz();
		List<String> params = new ArrayList<String>();
		params.add(pzgzid);
		StringBuffer sb = new StringBuffer();
		sb.append("select Max(to_number(lsh)) maxlsh from XG_XSXX_FBGL_XSXX_LSB where bxhgz=?   ");
		if(_XHGZ_XY.equals(xhGzlx)){
			sb.append(" and xydm=?");
			params.add(fbf.getXydm());
		}else if(_XHGZ_ZY.equals(xhGzlx)){
			sb.append(" and xydm=? and zydm=?");
			params.add(fbf.getXydm());
			params.add(fbf.getZydm());
		}
		else{
			sb.append(" and xydm=? and zydm=? and bjdm = ?");
			params.add(fbf.getXydm());
			params.add(fbf.getZydm());
			params.add(fbf.getBjdm());
			
		}
		return DAO.getInstance().getOneRs(sb.toString(),
				params.toArray(new String[params.size()]), "maxlsh");
	}

	/**
	 * 
	 * @����: ��ȡ���ŵ���ˮ��
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-10 ����11:11:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return String ��������
	 */
	private synchronized String getThLsh() {
		String pzgzid = getMapParam("pzgzid");
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from xg_xsxx_fbgl_xsxx_lsb where bxhgz=? order by lsh");
		List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
				sb.toString(), new String[] { pzgzid });
		int nowLsh = 0;
		int upLsh = 0;
		for (HashMap<String, String> hm : list) {
			nowLsh = Integer.parseInt(hm.get("lsh"));
			// ��ǰ��ˮ��-1��������һ����ˮ��
			// ��ǰ��ˮ��Ϊ���ţ�
			if (nowLsh - 1 != upLsh) {
				return String.valueOf(upLsh);
			}else{
				upLsh=nowLsh;
			}
		}
		return null;
	}

	/**
	 * 
	 * @����: �Ƿ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-10 ����10:55:17
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 */
	private boolean isHaveTh(String maxLsh,FbglXsxxForm fxf) {
		String pzgzid = getMapParam("pzgzid");
		//ѧ����ˮ�Ź���
		String xhGzlx =getXhgz();
		List<String> params = new ArrayList<String>();
		params.add(pzgzid);
		int gzAllxs = 0;
		StringBuffer sb = new StringBuffer();
		sb.append("select count(xh) xss from XG_XSXX_FBGL_XSXX_LSB where xh is not null and bxhgz=? ");
		if(_XHGZ_XY.equals(xhGzlx)){
			sb.append(" and xydm=?");
			params.add(fxf.getXydm());
		}else if(_XHGZ_ZY.equals(xhGzlx)){
			sb.append(" and xydm=? and zydm=?");
			params.add(fxf.getXydm());
			params.add(fxf.getZydm());
		}
		else{
			sb.append(" and xydm=? and zydm=? and bjdm = ?");
			params.add(fxf.getXydm());
			params.add(fxf.getZydm());
			params.add(fxf.getBjdm());
			
		}
		try {
			gzAllxs = StringUtils.paseStringToInteger(DAO.getInstance()
					.getOneRs(sb.toString(), params.toArray(new String[params.size()]), "xss"));
		} catch (Exception e) {
			throw new RuntimeException("��ȡ��ǰ�����ѱ�ѧ��ѧ��ʧ��!", e);
		}
		// ��ѧ��ѧ��С�������ˮ�ţ����������
		if (gzAllxs <StringUtils.paseStringToInteger(maxLsh)) {
			return true;
		}
		return false;
	}

	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf) {
		// TODO �Զ����ɷ������
		return null;
	}

	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf, Object obj) {
		FbglXsxxForm fbf = (FbglXsxxForm) obj;
		StringBuffer str = new StringBuffer();
		String xxlx = ftf.getXxlx();// ѡ������
		String zd = ftf.getTjszzd();// �ֶ�����
		String xxz = fgtxf.getXxz();// ѡ��ֵ
		String wsbl = fgtxf.getWsbl();// ĩβ�����־
		String qsz  = fgtxf.getQsz();
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
			// ��ȡ�޸ĵ�ֵ
			mrz = getValue(zd, fbf.getPk());
		}
		// �Զ���
		if (TJ_ZDY.equals(zd)) {
			str.append(fgtxf.getXxz());
		} else if (TJ_LSH.equals(zd)) {
			// ��ˮ�Ŵ���
			String maxlsh = geteMaxLsh(fbf);
			// ��������
			if (isHaveTh(maxlsh,fbf)) {
				maxlsh = getThLsh();
			}
			// ƫ��������ҳ��ɾ����ƫ������ҳ�����ӻ�ɾ����������δ��⣬������Ҫ�Դ���΢����
			String skewing = fbf.getSkewing();
			// ��ȡ��ǰ��ˮ��
			Integer nowLsh = StringUtils.paseStringToInteger(maxlsh)
					+ StringUtils.paseStringToInteger(skewing)+1;
			String lshStr = nowLsh.toString();
			// ������ˮ��
			fbf.setLsh(lshStr);
			Integer xh = (nowLsh+StringUtils.paseStringToInteger(qsz)-1);
			String xhStr=xh.toString();
			String lsh="";
			// �����趨��ˮ��λ�� ����
			for (int i = xhStr.length(); i < Integer.parseInt(fgtxf.getXxz()); i++) {
				lsh += "0";
			}
			str.append(lsh + xhStr);
			//�����ˮ���Ƿ񳬳�
			if (!check.checkLength(xhStr, Integer.parseInt(fgtxf.getXxz()))) {
				messageError = MessageKey.FBGL_CHECK_LSHBZ;
				
			}
		} else if (_XXLX_DEFUALTE.equals(xxlx)) {
			// Ĭ������ֱ��ȡֵ
			str.append(mrz);
		} else if (_XXLX_TEXT.equals(xxlx)) {
			// �ı�����ֱ��ȡֵ
			str.append(mrz);
		} else if (_XXLX_SELECT.equals(xxlx)) {
			// ѡ�����ʹ���
			String sjylz = mrz;
			// ѡ���ֵ
			Integer xxzI = StringUtils.paseStringToInteger(xxz);
			// ���ѡ���ֵ�����ֶγ��ȣ����������Ϊ�ֶγ���
			Integer length = xxzI > sjylz.length() ? sjylz.length() : xxzI;
			// ��ѡ��ֵ��ȡ����λ��
			str
					.append(sjylz.substring(sjylz.length() - length, sjylz
							.length()));
		} else if (_XXLX_REGION.equals(xxlx)) {
			// ����ֵ����
			String sjylz = mrz;
			// ��������
			String qzj[] = xxz.split(_QJ_SPLIT);
			// ��ʼ����
			Integer start = StringUtils.paseStringToInteger(qzj[0]);
			// ��������
			Integer length = StringUtils.paseStringToInteger(qzj[1]);
			// �������ֵ�����ֶγ��ȣ����������Ϊ�ֶγ���
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
				// ɾ��ԭ��ֵ���趨Ϊ�޸�ֵ
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

	@Override
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid) {
		StringBuffer sb = new StringBuffer();
		sb
				.append("select * from xg_xsxx_fbbxhgl_tjgzpzsxb where pzgzid=? and tjgzid=? order by sx");
		return DAO.getInstance().getListNotOut(sb.toString(),
				new String[] { pzgzid, "BXHGZ_XHPX" });
	}

	/**
	 * 
	 * @����:��ȡѧ����ˮ�ŵĹ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-9-21 ����02:15:27
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getXhgz() {
		String gzsql = "select * from XG_XSXX_FBGL_JCPZB where gzdm='XHGZ'";
		return DAO.getInstance().getOneRs(gzsql, new String[] {}, "gzlx");
	}
	public String getErrorMessage() {
		return messageError;
	}
}
