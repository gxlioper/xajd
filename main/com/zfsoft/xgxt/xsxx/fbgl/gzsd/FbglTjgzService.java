/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-1-27 ����09:43:29 
 */
package com.zfsoft.xgxt.xsxx.fbgl.gzsd;

import java.util.HashMap;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import xgxt.utils.GetTime;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjDao;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjServer;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxForm;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ģ��
 * @�๦������: TODO(������һ�仰��������������)
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-1-27 ����09:43:29
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class FbglTjgzService extends
		SuperServiceImpl<FbglTjgzForm, FbglTjgzDao> {
	FbglTjgzDao dao = new FbglTjgzDao();

	public FbglTjgzService() {
		this.setDao(dao);
	}
	/**
	 * 
	 * @����: �޸Ĺ�������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-18 ����11:31:42
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param json
	 * @param fgt
	 * @return
	 * boolean �������� 
	 */
	
	public boolean updataGzpz(JSONObject json){
		String gzpzid=json.get("gzpzid").toString();
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		FbglGzpzTjForm fgt=new FbglGzpzTjForm();
		try {
			fgt=fgts.getModel(gzpzid);
			FbglGzpzTjDao fgtd=new FbglGzpzTjDao();
			if(fgtd.delGzpzXxtj(gzpzid)>0){
				return saveGzpz(json,fgt);
			}
		} catch (Exception e) {
			throw new RuntimeException("��ȡԴ����ʧ�ܣ�");
		}
		return false;
	}
	/**
	 * 
	 * @����:��֤���������Ƿ��Ѵ���
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-9-15 ����02:07:55
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pzgzid
	 * @param gzmc
	 * @return
	 * boolean �������� 
	 * @throws
	 */
	public boolean isHave(String pzgzid,String gzmc){
		return dao.isHave(pzgzid,gzmc);
	}
	/**
	 * 
	 * @����: �����������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-18 ����11:31:22
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param json
	 * @param fgt
	 * @return
	 * boolean �������� 
	 */
	public boolean saveGzpz(JSONObject json ,FbglGzpzTjForm fgt) {
		boolean isok = true;
		FbglGzpzTjServer fgts = new FbglGzpzTjServer();
		if(fgt==null){
			fgt=new FbglGzpzTjForm();
			fgt.setGzdm(json.get("gzdm").toString());
			fgt.setPzgzmc(json.get("pzgzmc").toString());
			fgt.setGxsj(GetTime.getNowTime4());
			// ������������
			fgt.setQyzt(json.get("qyzt").toString());
			String zbid = UniqID.getInstance().getUniqIDHash();
			fgt.setPzgzid(zbid);
			isok = fgts.save(fgt);
		}else{
			try {
				fgt.setGzdm(json.get("gzdm").toString());
				fgt.setPzgzmc(json.get("pzgzmc").toString());
				fgt.setGxsj(GetTime.getNowTime4());
				fgt.setQyzt(json.getString("qyzt").toString());
				isok=fgts.runUpdate(fgt);
			} catch (Exception e) {
				throw new RuntimeException("�޸Ĵ���!");
			}
		}
		// ��������������ϸ����
		JSONArray array = json.getJSONArray("gztjObject");
		FbglGzpzTjXxServer fgtxs = new FbglGzpzTjXxServer();
		for (int i = 0; i < array.length(); i++) {
			JSONObject jb = array.getJSONObject(i);
			FbglGzpzTjXxForm fgtx = new FbglGzpzTjXxForm();
			fgtx.setPzgzid(fgt.getPzgzid());
			fgtx.setTjgzid(jb.get("tjgzid").toString());
			fgtx.setTjszzd(jb.get("tjszzd").toString());
			fgtx.setSx(jb.get("sx").toString());
			String tjnr = jb.get("tjnr").toString();
			String[] tjnrs = tjnr.split(",");
			tjnrs=formatParam(tjnrs);
			// var defalut="0";// Ĭ��
			// var text="1";// �ı���
			// var select="2";// ������
			// var qzw="3";// ����
			int j=0;
			if("3".equals(tjnrs[0])){
				fgtx.setXxz(tjnrs[1] +"~"+tjnrs[2]);
				j++;
			}else{
				fgtx.setXxz(tjnrs[1]);
			}
			fgtx.setSfkxg(tjnrs[j+2]);
			fgtx.setWsbl(tjnrs[j+3]);
			String ylz=tjnrs[j+4];
			String qsz=tjnrs[tjnrs.length-1];
			if(null==ylz||"null".equals(ylz)){
				ylz="";
			}
			if(null==qsz||"null".equals(qsz)){
				qsz="";
			}
			fgtx.setQsz(qsz);
			fgtx.setYlz(ylz);
			isok = fgtxs.save(fgtx);
		}
		// {"pzgzmc":"c","gztjObject":[{"tjgzid":"BBGZ_BJDM","tjszzd":"TJ_ZDY","sx":0,"tjnr":"1_c_1_1_"},{"tjgzid":"BBGZ_BJMC","tjszzd":"TJ_ZDY","sx":1,"tjnr":"0_0_1_"}]}
		return isok;
	}
	/**
	 * @����: ��ʽ�����ݣ�����ҳ�洫�ݿ�ֵ���ܲ������ʿ�����-1������</br>
	 * 		  �����ʽ���ؿ�����
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-20 ����03:38:37
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjnrs
	 * @return
	 * String[] �������� 
	 * @throws
	 */
	private String[] formatParam(String[] tjnrs ){
		String[] newnrs=new String[tjnrs.length];
		int i=0;
		for(String str:tjnrs){
			//-1��Ϊ�ǿ�ֵ
			if("null"==str||str.equals("-1")){
				newnrs[i]=null;
			}else{
				newnrs[i]=str;
			}
			i++;
		}
		return newnrs;
	}
	/**
	 * 
	 * @����: ��ȡ��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-27 ����04:46:43
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTjlx(String tjgzid) {
		if (StringUtils.isNull(tjgzid)) {
			return null;
		}
		return dao.getTjlx(tjgzid.split(","));
	}

	/**
	 * 
	 * @����: ����������ϸ��Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-1-27 ����05:06:52
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @return List<HashMap<String,String>> ��������
	 */
	public List<HashMap<String, String>> getTjpzXx(String tjgzid) {
		return dao.getTjpzXx(tjgzid);
	}

	/**
	 * 
	 * @����:��ȡ��������������Ϣ
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-2-10 ����02:14:16
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjgzid
	 * @param tjszzd
	 * @return List<HashMap<String,String>> ��������
	 */
	public HashMap<String, String> getTjNrpzXx(String tjgzid, String tjszzd) {
		return dao.getTjNrpzXx(tjgzid, tjszzd);
	}
	/**
	 * 
	 * @����:��ȡĬ��Ԥ��ֵ
	 * @���ߣ�xiaxia[���ţ�1104]
	 * @���ڣ�2015-9-14 ����04:26:38
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param ylz
	 * @return
	 * String �������� 
	 * @throws
	 */
	public String getMrylz(String ylz) {
		String[] tableMsg = ylz.split("-");
		return dao.getMrylz(tableMsg);
	}
}
