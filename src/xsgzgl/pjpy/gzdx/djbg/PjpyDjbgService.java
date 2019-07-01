package xsgzgl.pjpy.gzdx.djbg;

import java.util.HashMap;

import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

public class PjpyDjbgService extends xsgzgl.pjpy.general.djbg.PjpyDjbgService {

	PjpyDjbgDAO dao = new PjpyDjbgDAO();
	
	public String getDjbg(PjpyDjbgModel model) {

		// �ǼǱ��
		String djbg = "djbg";
		
		// ��Ŀ����
		String xmmc = model.getXmmc();
		
		if ("���ݴ�ѧ�����ѧ��ҵ��".equalsIgnoreCase(xmmc)) {
			djbg = "xyxbys";
		} else if ("����ʡ��ͨ��У�����ѧ��ҵ��".equalsIgnoreCase(xmmc)){
			djbg = "syxbys";
		}

		return djbg;
	}

	
	
	
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model) {
		
		String xh = model.getXh();
		String xn = model.getXn();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		String sfwj = Integer.valueOf(dao.getSfcf(xh)) > 0 ? "��" : "��"; 
		
		resultMap.putAll(dao.getXsxxInfo(xh, xn));
		resultMap.putAll(dao.getZccjInfo(xh, xn));
		resultMap.putAll(dao.getXstjcjInfo(xh, xn));
		
		String nowTime = dao.getNowTime("YYYY��MM��DD��");
		resultMap.put("nowTime", nowTime);
		resultMap.put("pjnd", nowTime.substring(0, 4));
		resultMap.put("sfwj", sfwj);
		return resultMap;
	}
}
