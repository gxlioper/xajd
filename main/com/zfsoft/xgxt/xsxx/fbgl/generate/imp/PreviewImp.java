/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-2-24 ����05:11:48 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.FbglTjgzForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����
 * @�๦������: Ĭ�Ͻ�������������
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-2-24 ����05:11:48
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class PreviewImp implements IGenerate {
	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf) {
		StringBuffer str = new StringBuffer();
		//����������Ϣ
		String xxlx = ftf.getXxlx();
		String zd = ftf.getTjszzd();
		String ylzpz = fgtxf.getYlz();
		String xxz = fgtxf.getXxz();
		String wsbl = fgtxf.getWsbl();// ĩβ�����־
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		str.append(ylzpz);
//		if (TJ_ZDY.equals(zd)) {
//			// ��Ԥ��ֵ
//			if (StringUtils.isNotNull(fgtxf.getSfkxg())) {
//				str.append(fgtxs.formateYlz(ylzpz, false));
//			} else {
//				str.append(fgtxf.getXxz());
//			}
//		} else if (TJ_LSH.equals(zd)) {
//			
//			//������ʱ�� λ������ٿ�
//			/*
//			 String lsh = "";
//			 String ws =xxz;
//			if(_XXLX_SELECT.equals(xxlx)){
//				ws=getSelectValue(xxz, ftf.getXxz());
//			}
//			for (int i = 1; i < Integer.parseInt(ws); i++) {
//				lsh += "0";
//			}
//			str.append(lsh + "1");*/
//			str.append("1");
//		} else if (_XXLX_DEFUALTE.equals(xxlx)) {
//			if (StringUtils.isNotNull(ylzpz)) {
//
//				str.append(fgtxs.formateYlz(ylzpz, false));
//			}
//		} else if (_XXLX_TEXT.equals(xxlx)) {
//			if (StringUtils.isNotNull(ylzpz)) {
//
//				str.append(fgtxs.formateYlz(ylzpz, false));
//			}
//		} else if (_XXLX_SELECT.equals(xxlx)) {
//			if (StringUtils.isNotNull(ylzpz)) {
//				String sjylz = fgtxs.formateYlz(ylzpz, false);
//				Integer xxzI = StringUtils.paseStringToInteger(xxz);
//				Integer length = xxzI > sjylz.length() ? sjylz.length() : xxzI;
//				// ��ѡ��ֵ��ȡ����λ��
//				str.append(sjylz.substring(sjylz.length() - length, sjylz
//						.length()));
//			}
//		} else if (_XXLX_REGION.equals(xxlx)) {
//			if (StringUtils.isNotNull(ylzpz)) {
//				String sjylz = fgtxs.formateYlz(ylzpz, false);
//				String qzj[] = xxz.split(_QJ_SPLIT);
//				Integer start = StringUtils.paseStringToInteger(qzj[0]);
//				Integer length = StringUtils.paseStringToInteger(qzj[1]);
//				Integer end = start + length > sjylz.length() ? sjylz.length()
//						: start + length;
//				// ������ֵ��ȡ����λ��
//				String nowYlz=sjylz.substring(start, end);
//				//����λ������ٿأ���ʱע��
//				/*//����λ��
//				for(int i=0;i<length-nowYlz.length();i++){
//					str.append("0");
//				}*/
//				str.append(nowYlz);
//			}
//		}
//		// �Ƿ���
//		if (SFBL_BL.equals(wsbl)) {
//			// ѡ��ֵ���
//			Integer ws = StringUtils.paseStringToInteger(xxz);
//			if (str.length() < ws) {
//				// �����Զ�����
//				int cs = ws - str.length();
//				for (int i = 0; i < cs; i++) {
//					str.insert(0, "0");
//				}
//			}
//		}
		//������ڵġ�null���ַ���
		String code=str.toString(); 
		code=StringUtils.isNull(code)||"null".equals(code)?"":code;
		return code;
	}
	private String getSelectValue(String select,String xxz){
		//��ʱ���� key��ֵƥ�䣬���� 2��2λ ֱ��ȡkey
		return select;
	}
	public String getCode(FbglTjgzForm ftf, FbglGzpzTjXxForm fgtxf,
			Object obj) {
		return getCode(ftf, fgtxf);
	}
	/*
	      ����: @see com.zfsoft.xgxt.xsxx.fbgl.generate.IGenerate#getErrorMessage()
	 */
	
	public String getErrorMessage() {
		// TODO �Զ����ɷ������
		return null;
	}
}
