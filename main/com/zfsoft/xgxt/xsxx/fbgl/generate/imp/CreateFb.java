/**
 * @����:ѧ����Ʒ��ҵ��
 * @���ڣ�2014-3-7 ����10:17:13 
 */
package com.zfsoft.xgxt.xsxx.fbgl.generate.imp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.xsxx.fbgl.comm.BarSorce;
import com.zfsoft.xgxt.xsxx.fbgl.comm.ProgressBar;
import com.zfsoft.xgxt.xsxx.fbgl.fbgl.FbglService;
import com.zfsoft.xgxt.xsxx.fbgl.generate.BaseCreate;
import com.zfsoft.xgxt.xsxx.fbgl.gzsd.gzpz.FbglGzpzTjXxServer;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxService;

/**
 * @ϵͳ����: ѧ����������ϵͳ
 * @ģ������: �ְ����ģ��
 * @�๦������: �ְ�ʵ����
 * @���ߣ� �Ų�·[����:982]
 * @ʱ�䣺 2014-3-7 ����10:17:13
 * @�汾�� V1.0
 * @�޸ļ�¼: ���޸���-�޸�����-�޸�˵��
 */

public class CreateFb extends BaseCreate {
	private FbglService fs = new FbglService();
	private String barKey = "fb";

	/**
	 * 
	 * @����: ��õ�ǰ�༶��������
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-8 ����05:18:05
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return Integer ��������
	 */
	private int getNowFbBjdm() {
		int leastBjrs = 0;// ��С�༶����
		int nowBjrs = 0;// ��ǰ�༶����
		// ��ѯÿ���༶������
		StringBuffer sb = new StringBuffer();
		sb.append("	select bjdm,count(bjdm) bjrs");
		sb.append(" from (select * from XG_XSXX_FBGL_XSXX_LSB_BAK t");
		sb.append(" union");
		sb.append(" select * from XG_XSXX_FBGL_XSXX_LSB t)");
		sb.append(" where bjdm is not null");
		sb.append(" group by bjdm");
		List<HashMap<String, String>> list = DAO.getInstance().getListNotOut(
				sb.toString(), new String[] {});
		for (HashMap<String, String> hm : list) {
			nowBjrs = Integer.parseInt(hm.get("bjrs"));
			leastBjrs = leastBjrs >= nowBjrs ? nowBjrs : leastBjrs;
		}
		return leastBjrs;
	}

	/**
	 * 
	 * @����: ��ȡ��ǰӦ�ְ�༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-9 ����03:52:36
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param pk
	 * @return HashMap<String,String> ��������
	 */
	public HashMap<String, String> getFbbj(String pk) {
		StringBuffer sb = new StringBuffer();
		sb.append(" select bjdm,bjmc,rownum ");
		sb.append("  from ( select t.bjdm,t.bjmc,");
		sb.append("  nvl(t.bjrssx, 999999999) bjrssx,");
		sb.append("  nvl(a1.xss, 0) xss,t.bjbh");
		sb.append("  from XG_XSXX_FBBXHGL_BJDM_LSB t");
		sb.append("  left join (select count(*) xss, bjdm");
		sb.append("  from XG_XSXX_FBGL_XSXX_LSB");
		sb.append("  group by bjdm) a1");
		sb.append("  on a1.bjdm = t.bjdm");
		sb
				.append("   where t.nj || '-' || t.bmdm || '-' || t.zydm || '-' || t.PYCC || '-' || t.xz = ?");
		sb.append("       order by xss, to_number(t.bjbh)) a");
		sb.append("  where bjrssx > xss and rownum < 2");
		return DAO.getInstance().getMapNotOut(sb.toString(),
				new String[] { pk });
	}

	/**
	 * @����: ִ�зְ�(ʹ��sql��ȡ�ɷ���༶)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-7 ����02:29:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjPzxx
	 *            ������Ϣ
	 * @param bj
	 *            �༶����
	 * @return List<HashMap<String,String>> �������� �ְ���ɵ�ѧ��
	 */
	public List<HashMap<String, String>> generate(String pzgzid, Object obj) {
		List<HashMap<String, String>> list=new ArrayList<HashMap<String,String>>();
		MessageResources message = MessageResources
						.getMessageResources("config.ApplicationResources");
				Base.YXPZXY_KEY = message.getMessage("lable.xb");
		// ��ȡ������
		ProgressBar pb = BarSorce.getProgressBar(barKey + pzgzid);
		try {
			FbglXsxxService fxs = new FbglXsxxService();
			FbglXsxxForm fxf = new FbglXsxxForm();
			List<HashMap<String, String>> tjPzxx = getTJpzxxId(pzgzid);
			// ��ȡ���ݹ�������������ѧ��
			tjPzxx = getGroupByData(tjPzxx);
			HashMap<String, String> bj;
			for (HashMap<String, String> xs : tjPzxx) {
				// �����ж�bj�Ƿ�Ϊ�գ�����ǿգ���ֱ�ӱ�����߷���=====================================================
				bj = getFbbj(obj.toString());
				if (null == bj || bj.size() <= 0) {
					String objs[]=obj.toString().split("-");
					pb.finish();
					pb.setMessage("["+objs[0]+"-"+objs[1]+"-"+objs[2]+"-"+xs.get("xm")+"]û�ɶ�Ӧ�ķ���༶�������������Ӧ�İ༶��");
					throw new RuntimeException("�ְ�֮ǰ�����Ƚ��б�࣡");
				}
				xs.put("bjdm", bj.get("bjdm"));
				xs.put("bjmc", bj.get("bjmc"));
				BeanUtils.copyProperties(fxf, xs);
				fxf.setFbgz(pzgzid);
				if (fxs.runUpdateForFb(fxf)) {
					list.add(xs);
					pb.change();
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("����ѧ����Ϣʧ�ܣ�", e);
		}
		return list;
	}

	/**
	 * 
	 * @����: ִ�зְ�
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-3-7 ����02:29:00
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param tjPzxx
	 *            ������Ϣ
	 * @param bj
	 *            �༶����
	 * @return List<HashMap<String,String>> �������� �ְ���ɵ�ѧ��
	 */
	public List<HashMap<String, String>> generate(String pzgzid,
			List<HashMap<String, String>> bj) {
		// ����ɵķְ�ѧ��
		List<HashMap<String, String>> yfbxsList = new ArrayList<HashMap<String, String>>();
		List<HashMap<String, String>> tjPzxx = getTJpzxxId(pzgzid);
		// ��ȡ���ݹ�������������ѧ��
		tjPzxx = getGroupByData(tjPzxx);
		// �ܰ༶��
		int bjs = bj.size();
		int i = 0;
		int j = 0;// ��ǰ����༶��ѧ������
		int bjxss = 0;// �༶ѧ������
		int bjrssx = 0;// �༶��������,0��������������
		int ymbjs = 0;// �Ѿ�������ɰ༶��
		String bjdm = null;// ��ǰ�༶����
		// ��¼��ǰ�ְ����ӵ����� key ��Ψһ�༶���룬value����ǰ�༶�ѷ���ѧ������
		Map<String, Integer> bjCrsl = new HashMap<String, Integer>();
		for (HashMap<String, String> xs : tjPzxx) {
			// ������а༶���Ѿ�������ɣ��Զ�������
			if (ymbjs == bj.size()) {
				break;
			}
			/*
			 * //�����ǰ�༶���ְܷ� while (!isCanFb(bj, i)) { i++; }
			 */
			// �༶
			HashMap<String, String> hm = bj.get(i);
			bjdm = hm.get("bjdm");
			// bjdm = hm.get("bjdm");
			// ��ǰ�༶��ѧ������
			bjxss = fs.getYyxsx(bjdm);
			// �༶��������,0��������������
			bjrssx = StringUtils.paseStringToInteger(hm.get("bjrssx"));
			// ��ǰ����༶��ѧ������
			j = null == bjCrsl.get(bjdm) ? 0 : bjCrsl.get(bjdm);
			// �����ǰ�༶ѧ�������ڰ༶�������� ���򲻶Դ˰༶���в���ѧ��
			// ������������� �����ж�
			if (bjrssx <= 0 || bjxss + j < bjrssx) {
				// ����ѧ���༶���������
				xs.put("bjdm", bjdm);
				xs.put("bjmc", hm.get("bjmc"));
				yfbxsList.add(xs);
				// ��¼��ǰ�༶�����ѧ������
				bjCrsl.put(bjdm, ++j);
			} else {
				// ���ӷ�����ɵİ༶����
				ymbjs++;
			}
			if (i == bjs - 1) {
				// ��ת�༶���� snake = =
				Collections.reverse(bj);
				// ����
				i = 0;
			} else {
				i++;
			}
		}
		return yfbxsList;
	}

	/**
	 * �Ƿ���ԶԵ�ǰ�༶�ְࣨ��֤�����ְ��λ�ã�
	 * 
	 * @����:TODO(������һ�仰�����������������)
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-9 ����02:38:57
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @param bj
	 * @param nowBjXb
	 * @return boolean ��������
	 */
	private boolean isCanFb(List<HashMap<String, String>> bj, int nowBjXb) {
		// ��ǰ�༶����
		String bjdm = bj.get(nowBjXb).get("bjdm");
		// ��ǰ�༶��ѧ������
		int bjxss = fs.getYyxsx(bjdm);
		HashMap<String, String> hm = null;
		int zsrs = getNowFbBjdm();
		// �༶��������
		if (bjxss > zsrs) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @����: �Ƿ��п��Էְ�İ༶
	 * @���ߣ��Ų�·[���ţ�982]
	 * @���ڣ�2014-4-4 ����02:36:15
	 * @�޸ļ�¼: �޸�������-�޸�����-�޸�����
	 * @return boolean ��������
	 */
	private boolean isHaveCanFbBj(List<HashMap<String, String>> bj) {
		int j = 0;// ��ǰ����༶��ѧ������
		int bjxss = 0;// �༶ѧ������
		int bjrssx = 0;// �༶��������,0��������������
		FbglService fs = new FbglService();
		for (HashMap<String, String> hm : bj) {
			// ��ǰ�༶��ѧ������
			bjxss = fs.getYyxsx(hm.get("bjdm"));
			// �༶��������,0��������������
			bjrssx = StringUtils.paseStringToInteger(hm.get("bjrssx"));
			// ��ǰ����༶��ѧ������
			j = StringUtils.paseStringToInteger(hm.get("crxs"));
			// ֻҪ��һ���༶���Լ����ְ࣬�ͷ���true
			if (bjxss + j < bjrssx) {
				return true;
			}
		}
		return false;
	}

	public String getBaseSql() {
		return "select * from xg_xsxx_fbgl_xsxx_lsb t where t.nj || '-' || t.xydm || '-' || t.zydm || '-' || t.PYCC || '-' ||t.xz =? and t.nj=? and t.bjdm is null";
	}

	@Override
	public List<HashMap<String, String>> getTJpzxxId(String pzgzid) {
		FbglGzpzTjXxServer fgtx = new FbglGzpzTjXxServer();
		List<HashMap<String, String>> tjPzxx = fgtx.getTJpzxxId(pzgzid);
		return tjPzxx;
	}
}
