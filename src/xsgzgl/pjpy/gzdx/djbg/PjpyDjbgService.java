package xsgzgl.pjpy.gzdx.djbg;

import java.util.HashMap;

import xsgzgl.pjpy.general.djbg.PjpyDjbgModel;

public class PjpyDjbgService extends xsgzgl.pjpy.general.djbg.PjpyDjbgService {

	PjpyDjbgDAO dao = new PjpyDjbgDAO();
	
	public String getDjbg(PjpyDjbgModel model) {

		// 登记表格
		String djbg = "djbg";
		
		// 项目名称
		String xmmc = model.getXmmc();
		
		if ("贵州大学优秀大学毕业生".equalsIgnoreCase(xmmc)) {
			djbg = "xyxbys";
		} else if ("贵州省普通高校优秀大学毕业生".equalsIgnoreCase(xmmc)){
			djbg = "syxbys";
		}

		return djbg;
	}

	
	
	
	public HashMap<String, Object> getDjbgInfo(PjpyDjbgModel model) {
		
		String xh = model.getXh();
		String xn = model.getXn();
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		String sfwj = Integer.valueOf(dao.getSfcf(xh)) > 0 ? "是" : "否"; 
		
		resultMap.putAll(dao.getXsxxInfo(xh, xn));
		resultMap.putAll(dao.getZccjInfo(xh, xn));
		resultMap.putAll(dao.getXstjcjInfo(xh, xn));
		
		String nowTime = dao.getNowTime("YYYY年MM月DD日");
		resultMap.put("nowTime", nowTime);
		resultMap.put("pjnd", nowTime.substring(0, 4));
		resultMap.put("sfwj", sfwj);
		return resultMap;
	}
}
