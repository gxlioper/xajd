package xgxt.pjpy.ghxy.stxf;

import javax.servlet.http.HttpServletRequest;

import xgxt.action.Base;
import xgxt.form.SaveForm;
import xgxt.szdw.ghxy.njzrwh.GhxyNjzrwhService;

public class GhxyStxfService {
	
	public void stxfWh(HttpServletRequest request,
			GhxyStxfForm ghxyStxfForm) throws Exception{
		
		GhxyNjzrwhService service =new GhxyNjzrwhService();
		
		String xn=Base.currXn;
		//进行数据操作 的表名
		String realTable ="ghxy_stfb";
		
		//保存查询出的所有信息的XH主键
		String[] zgh = ghxyStxfForm.getPrimarykey_cbv();
		//保存已选择的信息的主键
		String[] checkVal = ghxyStxfForm.getCheckVal();
		
		String[] pkValue=new String[checkVal.length];
		for(int i=0;i<checkVal.length;i++){
			pkValue[i]=checkVal[i]+xn;
		}
		
		
		String[] zf= ghxyStxfForm.getZf();
		//判断操作是否成功
		boolean reslut = false;
		
		//批量增加及取消
		if (checkVal != null && checkVal.length > 0) {
			String pk = "xh || xn ";
			String[] arrzd = new String[] {"xh","xn","zf"};
			String[] sxh = new String[checkVal.length];
			int m = 0;
			for (int i = 0; i < checkVal.length; i++) {
				if (zgh != null && zgh.length > 0) {
					for (int j = 0; j < zgh.length; j++) {
						if (zgh[j].equalsIgnoreCase(checkVal[i])) {
							sxh[i] = checkVal[i];
							m++;
							break;
						}else{
							sxh[i]="";
						}
					}
				}
			}
			
			String[] xhArr = new String[m];
			String[] xns=new String[m];
			String[] zfs=new String[m];
			int f = 0;
			for(int i=0; i<sxh.length;i++){
				if(!Base.isNull(sxh[i])){
				xhArr[f] = sxh[i];
				zfs[f]=zf[i];
				xns[f]=xn;
				f++;
				}
			}
			SaveForm saveForm = new SaveForm();
			saveForm.setTableName(realTable);
			saveForm.setPk(pk);
			saveForm.setPkValue(pkValue);
			saveForm.setArrzd(arrzd);
			
			GhxyStxfModel model=new GhxyStxfModel();
			model.setXh(xhArr);
			model.setXn(xns);
			model.setZf(zfs);
			reslut = service.saveTyxx(saveForm, model);
			request.setAttribute("result", reslut);
		}
	}
}
