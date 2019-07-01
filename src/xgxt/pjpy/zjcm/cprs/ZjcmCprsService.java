package xgxt.pjpy.zjcm.cprs;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.pjpy.ghxy.xxxf.GhxyXxxfForm;
import xgxt.pjpy.ghxy.xxxf.GhxyXxxfModel;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;

public class ZjcmCprsService {
	/**
	 * 浙江传媒 参评人数维护
	 * method xxxfWh
	 * @param request
	 * @param ghxyStxfForm
	 * @throws Exception
	 */
	public void cprsWh(HttpServletRequest request,
			ZjcmCprsForm zjcmCprsForm) throws Exception{
		
		GhxyNjzrwhService service =new GhxyNjzrwhService();
		
		String xn=Base.currXn;
	    //进行数据操作 的表名
		String realTable ="zjcm_pjpy_cprssz";
		
		//保存查询出的所有信息的XH主键
		String[] zgh = zjcmCprsForm.getPrimarykey_cbv();
		//保存已选择的信息的主键
		String[] checkVal = zjcmCprsForm.getCheckVal();
		
		String[] pkValue=new String[checkVal.length];
		for(int i=0;i<checkVal.length;i++){
			pkValue[i]=checkVal[i];
		}
		
		
		String[] rs= zjcmCprsForm.getRs();
		//判断操作是否成功
		boolean reslut = false;
		
		//批量增加及取消
		if (checkVal != null && checkVal.length > 0) {
			String pk = "bjdm ";
			String[] arrzd = new String[] {"bjdm","rs"};
			String[] sbj = new String[checkVal.length];
			int m = 0;
			for (int i = 0; i < checkVal.length; i++) {
				if (zgh != null && zgh.length > 0) {
					for (int j = 0; j < zgh.length; j++) {
						if (zgh[j].equalsIgnoreCase(checkVal[i])) {
							sbj[i] = checkVal[i];
							m++;
							break;
						}else{
							sbj[i]="";
						}
					}
				}
			}
			
			String[] bjArr = new String[m];
			String[] rss=new String[m];
			int f = 0;
			for(int i=0; i<sbj.length;i++){
				if(!Base.isNull(sbj[i])){
				bjArr[f] = sbj[i];
				rss[f]=rs[i];
				f++;
				}
			}
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);
			
			ZjcmCprsModel model=new ZjcmCprsModel();
			model.setBjdm(bjArr);
			model.setRs(rss);
			reslut = service.saveTyxx(saveForm, model);
			request.setAttribute("result", reslut);
		}
	}
}
