package xgxt.xszz.zzxmtj;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.ExcelMethods;
import xgxt.utils.MakeQuery;

import com.zfsoft.basic.BasicService;

public class XszzZzxmService {
	
	/**
	 * 资助项目列表
	 */
	public List<HashMap<String,String>> getZzxmList(){
		XszzZzxmDao dao=new XszzZzxmDao();
		return dao.getZzxmList();
	} 
	
	/**
	 * 获取项目等级
	 * 
	 */
	public List<HashMap<String,String>>getXmdjList(String xmdm){
		XszzZzxmDao dao=new XszzZzxmDao();
		List<HashMap<String,String>>list=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>hashMap=new HashMap<String,String>();
		hashMap.put("mc", "---请选择---");
		hashMap.put("dm", "");
		list.add(hashMap);
		list.addAll(dao.getXmdjList(xmdm));
		return list;
	}
	
	/**
	 * 获取学生资助
	 * 周期
	 */
	public HashMap<String,String>getZzZq(String xmdm){
		XszzZzxmDao dao=new XszzZzxmDao();
		return dao.getZzZq(xmdm);
	}
	
	/**
	 * 获取项目表名
	 */
	public String getXmbName(String xmdm){
		XszzZzxmDao dao=new XszzZzxmDao();
		HashMap<String,String>hashMap=dao.getXmbName(xmdm);
		return hashMap.get("xmb");
	}
	
	/**
	 * 学生资助是否有金额
	 */
	public boolean getSfje(String xmdm){
		XszzZzxmDao dao=new XszzZzxmDao();
		HashMap<String,String>hashMap=dao.getSfje(xmdm);
		if("需要".equals(hashMap.get("sfje"))){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断资助项目是否分级别
	 */
	public String checkSffj(String xmdm){
		XszzZzxmDao dao = new XszzZzxmDao();
		HashMap<String,String>hashMap=dao.checkSffj(xmdm);
		if("分级".equals(hashMap.get("sffj"))){
			return "true";
		}
		return "false";
	}
	
	/**
	 * 根据资助DM获取名称
	 */
	public String getZzmc(String zzdm){
		XszzZzxmDao dao = new XszzZzxmDao();
		HashMap<String,String>hashMap=dao.getZzmc(zzdm);
		return hashMap.get("xmmc");
	}
	
	/**
	 * @throws Exception 
	 * printZzxmTjbb
	 * 资助项目统计
	 */
	public void printZzxmTjbb(XszzZzxmtjForm xszzZzxmForm,
			HttpServletRequest request, WritableWorkbook wwb,String lx,String userName)
			throws Exception {
		XszzZzxmService service=new XszzZzxmService();
		service.zzxmTjCx(xszzZzxmForm, request,lx,userName);
		List<String[]>list=(List<String[]>)request.getAttribute("rs");
		String[]topTrs=(String[])request.getAttribute("topTrs");
		
		try {
			int xywz=0;
			int zywz=0;
			int bjwz=0;
			int njwz=0;
			int rswz=0;
			int jewz=0;
			for(int i=0;i<topTrs.length;i++){
				if(topTrs[i].equals("学院")){
					xywz=i;
				}else if(topTrs[i].equals("专业")){
					zywz=i;
				}else if(topTrs[i].equals("班级")){
					bjwz=i;
				}else if(topTrs[i].equals("年级")){
					njwz=i;
				}else if(topTrs[i].equals("人数")){
					rswz=i;
				}else if(topTrs[i].equals("金额")){
					jewz=i;
				}
			}
			
			// 创建xls中SHEET对象
			WritableSheet ws = wwb.getSheet(0);
			WritableCellFormat wcfTytle = new WritableCellFormat();
			// 设置对齐方式
			Alignment alignMent = Alignment.CENTRE;
			VerticalAlignment vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);

			WritableFont wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(18);
			wcfTytle.setFont(wfTytle);
			ws.mergeCells(0, 0, topTrs.length-1, 0);
			ws.addCell(new Label(0,0,service.getZzmc(xszzZzxmForm.getZzxm()),wcfTytle));

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.LEFT;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setBoldStyle(WritableFont.BOLD);
			wfTytle.setPointSize(12);
			wcfTytle.setFont(wfTytle);

			wcfTytle = new WritableCellFormat();
			alignMent = Alignment.CENTRE;
			vag = VerticalAlignment.CENTRE;

			wcfTytle.setVerticalAlignment(vag);
			wcfTytle.setAlignment(alignMent);
			wcfTytle.setWrap(true);
//			 设置表格边框
			 wcfTytle.setBorder(Border.ALL, BorderLineStyle.THIN);

			wfTytle = new WritableFont(WritableFont.ARIAL);
			wfTytle.setPointSize(10);
			wcfTytle.setFont(wfTytle);
			for (int i = 0; i<topTrs.length;i++){
				ws.addCell(new Label(i, 1, topTrs[i], wcfTytle));
			}
			
			int rstj=0;//人数统计
			float jetj=0.0f;//金额统计
			String jetjStr="";
			for (int i = 0; i < list.size(); i++) {
				String[]str=list.get(i);
				for(int j=0; j<str.length;j++){
					ws.addCell(new Label(j, 2 + i, str[j], wcfTytle));
					
				}
				if(jewz!=0){
					jetj+=Float.parseFloat(str[jewz]);
				}
				
				if(rswz!=0){
					rstj+=Integer.parseInt(str[rswz]);
				}
			}
			
			ws.addCell(new Label(0,list.size()+2,"总计",wcfTytle));
			if(jewz!=0){
				jetjStr="金额："+jetj;
			}
			ws.addCell(new Label(rswz,list.size()+2,""+rstj,wcfTytle));
			
			ws.mergeCells(0, list.size()+2, topTrs.length-1, list.size()+2);
			ws.addCell(new Label(0,list.size()+2,"总计       人数："+rstj+"   "+jetjStr,wcfTytle));
			
			String xzx=xszzZzxmForm.getXzx();
			if(list.size()>0){
				if("nj".equalsIgnoreCase(xzx)){//年级
					
						int []njline=service.getLines(list, 2, njwz);
						
						for(int i=0;i<njline.length;i++){
							if(njline.length==1){
								ws.mergeCells(njwz, njline[i], njwz, 2+list.size()-1);
							}else if(i==njline.length-1){
								ws.mergeCells(njwz, njline[i], njwz, njline[i]);
							}else{
								ws.mergeCells(njwz, njline[i], njwz, njline[i+1]-1);
							}
						}
						
				}else if("xydm".equalsIgnoreCase(xzx)){//学院
					int []xyline=service.getLines(list, 2, xywz);
					for(int i=0;i<xyline.length;i++){
						if(xyline.length==1){
							ws.mergeCells(xywz, xyline[i], xywz, 2+list.size()-1);
						}else if(i==xyline.length-1){
							ws.mergeCells(xywz, xyline[i], xywz, xyline[i]);
						}else{
							ws.mergeCells(xywz, xyline[i], xywz, xyline[i+1]-1);
						}
					}
	
				}else if("zydm".equalsIgnoreCase(xzx)){//专业
					int []zyline=service.getLines(list, 2, zywz);
					
					for(int i=0;i<zyline.length;i++){
						if(zyline.length==1){
							ws.mergeCells(zywz, zyline[i], zywz, 2+list.size()-1);
						}else if(i==zyline.length-1){
							ws.mergeCells(zywz, zyline[i], zywz, zyline[i]);
						}else{
							ws.mergeCells(zywz, zyline[i], zywz, zyline[i+1]-1);
						}
					}
					int []xyline=service.getLines(list, 2, xywz);
					for(int i=0;i<xyline.length;i++){
						if(xyline.length==1){
							ws.mergeCells(xywz, xyline[i], xywz, 2+list.size()-1);
						}else if(i==xyline.length-1){
							ws.mergeCells(xywz, xyline[i], xywz, xyline[i]);
						}else{
							ws.mergeCells(xywz, xyline[i], xywz, xyline[i+1]-1);
						}
					}
				}else if("bjdm".equalsIgnoreCase(xzx)){//班级
					int []bjline=service.getLines(list, 2, bjwz);
					
					for(int i=0;i<bjline.length;i++){
						if(bjline.length==1){
							ws.mergeCells(bjwz, bjline[i], bjwz, 2+list.size()-1);
						}else if(i==bjline.length-1){
							ws.mergeCells(bjwz, bjline[i], bjwz, bjline[i]);
						}else{
							ws.mergeCells(bjwz, bjline[i], bjwz, bjline[i+1]-1);
						}
					}
					int []zyline=service.getLines(list, 2, zywz);
					
					for(int i=0;i<zyline.length;i++){
						if(zyline.length==1){
							ws.mergeCells(zywz, zyline[i], zywz, 2+list.size()-1);
						}else if(i==zyline.length-1){
							ws.mergeCells(zywz, zyline[i], zywz, zyline[i]);
						}else{
							ws.mergeCells(zywz, zyline[i], zywz, zyline[i+1]-1);
						}
					}
					int []xyline=service.getLines(list, 2, xywz);
					for(int i=0;i<xyline.length;i++){
						if(xyline.length==1){
							ws.mergeCells(xywz, xyline[i], xywz, 2+list.size()-1);
						}else if(i==xyline.length-1){
							ws.mergeCells(xywz, xyline[i], xywz, xyline[i]);
						}else{
							ws.mergeCells(xywz, xyline[i], xywz, xyline[i+1]-1);
						}
					}
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 向客户端输出
		ExcelMethods.submitWritableWorkbookOperations(wwb);
	}
	
	/**
	 * 统计查询
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws IllegalArgumentException 
	 * 
	 */
	public void zzxmTjCx(XszzZzxmtjForm form,HttpServletRequest request,
			String lx,String userName) throws Exception{
		XszzZzxmService service =new XszzZzxmService();
		BasicService basicService=new BasicService();
		String xmb=service.getXmbName(form.getZzxm());//项目表名
		StringBuilder sb=new StringBuilder();
		MakeQuery mQuery=new MakeQuery();
		String []colList={"xn","xq","nd","nj","xydm","zydm","bjdm","xmzzjb"};
		String []colLikeList={"xm"};
		mQuery.makeQuery(colList, colLikeList, form);
		
		String xszd=" distinct(a.bjmc),a.nj,a.zymc,a.xymc,b.rs ";//显示字段
		String groupzd=" count(*)rs,b.bjdm ";
		String grop=" b."+form.getXzx();
		String []topTr={"年级","学院名称","专业名称","班级名称","人数"};
		String []output={"nj","xymc","zymc","bjmc","rs"};
		String query="";
		String xhQuery="";
		String order="order by "+form.getXzx();
		String njzd="(case when b.nj is null then '无所属年级' else to_char(b.nj) end)";
		StringBuilder yhqx=new StringBuilder();
		query="a."+form.getXzx()+"=b."+form.getXzx();
		if(!Base.isNull(form.getXh()) && !"".equals(form.getXh())){
			xhQuery=" and  a.xh like '%"+form.getXh()+"%' ";
		}
		
		
		if ("fdy".equalsIgnoreCase(lx)) {
			yhqx.append(" and exists(select 1 from fdybjb d ");
			yhqx.append(" where b.bjdm = d.bjdm ");
			yhqx.append(" and d.zgh = '" + userName + "')");
		} else if ("bzr".equalsIgnoreCase(lx)) {
			yhqx.append(" and exists(select 1 from bzrbbb d ");
			yhqx.append(" where b.bjdm = d.bjdm");
			yhqx.append(" and d.zgh = '" + userName + "')");
		} else if("jd".equalsIgnoreCase(lx)){
			yhqx.append(" and (");
			yhqx.append(" exists(select 1 from fdybjb d");
			yhqx.append(" where b.bjdm = d.bjdm");
			yhqx.append(" and d.zgh = '" + userName + "')");
			
			yhqx.append(" or exists(select 1 from bzrbbb d ");
			yhqx.append(" where b.bjdm = d.bjdm");
			yhqx.append(" and d.zgh = '" + userName + "')");
			
			yhqx.append(" )");
		}
		
		if("bjdm".equals(form.getXzx())){
			 xszd=" distinct(a.bjmc),a.zymc,a.xymc,b.rs ";
			 groupzd=" count(*)rs,b.bjdm ";
			 topTr=new String[]{"学院","专业","班级","人数"};
			 output=new String[]{"xymc","zymc","bjmc","rs"};
			 order="order by xymc,zymc,bjmc ";
		}else if("zydm".equals(form.getXzx())){
			 xszd=" distinct(a.zymc),a.xymc,b.rs ";
			 groupzd=" count(*)rs,b.zydm ";
			 topTr=new String[]{"学院","专业","人数"};
			 output=new String[]{"xymc","zymc","rs"};
			 order="order by xymc,zymc ";
		}else if("xydm".equals(form.getXzx())){
			 xszd=" distinct(a.xymc),b.rs ";
			 groupzd=" count(*)rs,b.xydm ";
			 topTr=new String[]{"学院","人数"};
			 output=new String[]{"xymc","rs"};
			 order="order by xymc ";
		}else if("nj".equals(form.getXzx())){
			if(!Base.isNull(form.getBjdm())&& !"".equals(form.getBjdm())){
				xszd=" distinct("+njzd+"||a.zymc),"+njzd+"nj,b.rs,a.xymc,a.zymc,a.bjmc ";
				 groupzd=" count(*)rs,b.nj,b.xydm,b.zydm,b.bjdm ";
				 topTr=new String[]{"年级","学院","专业","班级","人数"};
				 output=new String[]{"nj","xymc","zymc","bjmc","rs"};
				 grop+=" ,b.bjdm,b.zydm,b.xydm ";
				 query=" a."+form.getXzx()+"=b."+form.getXzx()+" and a.bjdm=b.bjdm and a.zydm=b.zydm and a.xydm=b.xydm ";
				 order="order by xymc,zymc, bjmc ";
			}else if(!Base.isNull(form.getZydm())&& !"".equals(form.getZydm())){
				 xszd=" distinct("+njzd+"||a.zymc),"+njzd+"nj,b.rs,a.xymc,a.zymc ";
				 groupzd=" count(*)rs,b.nj,b.xydm,b.zydm ";
				 topTr=new String[]{"年级","学院","专业","人数"};
				 output=new String[]{"nj","xymc","zymc","rs"};
				 grop+=" ,b.zydm,b.xydm ";
				 query=" a."+form.getXzx()+"=b."+form.getXzx()+"    and  a.zydm=b.zydm ";
				 order="order by xymc,zymc ";
			}else if(!Base.isNull(form.getXydm())&& !"".equals(form.getXydm())){
				 xszd=" distinct("+njzd+"||a.xymc),"+njzd+"nj,b.rs,a.xymc ";
				 groupzd=" count(*)rs,b.nj,b.xydm ";
				 topTr=new String[]{"年级","学院","人数"};
				 output=new String[]{"nj","xymc","rs"};
				 grop+=" ,b.xydm ";
				 query=" a."+form.getXzx()+"=b."+form.getXzx()+"    and a.xydm=b.xydm ";
				 order="order by xymc ";
			}else {
				 xszd=" distinct("+njzd+")nj,b.rs ";
				 groupzd=" count(*)rs,b.nj ";
				 topTr=new String[]{"年级","人数"};
				 output=new String[]{"nj","rs"};
				 query=" a."+form.getXzx()+"=b."+form.getXzx()+"   ";
			}
		}
		
		
		if(service.getSfje(form.getZzxm())){//资助项目需要金额
			if("bjdm".equals(form.getXzx())){
				 xszd=" distinct(a.bjmc),a.zymc,a.xymc,b.rs,b.je ";
				 groupzd=" count(*)rs,sum(xmzzje)je,b.bjdm ";
				 topTr=new String[]{"学院","专业","班级","人数","金额"};
				 output=new String[]{"xymc","zymc","bjmc","rs","je"};
				 order="order by xymc,zymc,bjmc ";
			}else if("zydm".equals(form.getXzx())){
				 xszd=" distinct(a.zymc),a.xymc,b.rs,b.je ";
				 groupzd=" count(*)rs,sum(xmzzje)je,b.zydm ";
				 topTr=new String[]{"学院","专业","人数","金额"};
				 output=new String[]{"xymc","zymc","rs","je"};
				 order="order by xymc,zymc ";
			}else if("xydm".equals(form.getXzx())){
				 xszd=" distinct(a.xymc),b.rs,b.je ";
				 groupzd=" count(*)rs,sum(xmzzje)je,b.xydm ";
				 topTr=new String[]{"学院","人数","金额"};
				 output=new String[]{"xymc","rs","je"};
				 order="order by xymc ";
				
			}else if("nj".equals(form.getXzx())){
				if(!Base.isNull(form.getBjdm())&& !"".equals(form.getBjdm())){
					xszd=" distinct("+njzd+"||a.zymc),"+njzd+"nj,b.rs,b.je,a.xymc,a.zymc,a.bjmc ";
					 groupzd=" count(*)rs,sum(xmzzje)je,b.nj,b.xydm,b.zydm,b.bjdm ";
					 topTr=new String[]{"年级","学院","专业","班级","人数","金额"};
					 output=new String[]{"nj","xymc","zymc","bjmc","rs","je"};
					 grop+=" ,b.bjdm,b.zydm,b.xydm ";
					 query=" a."+form.getXzx()+"=b."+form.getXzx()+"    and a.bjdm=b.bjdm and a.zydm=b.zydm and a.xydm=b.xydm ";
				}else if(!Base.isNull(form.getZydm())&& !"".equals(form.getZydm())){
					 xszd=" distinct("+njzd+"||a.zymc),"+njzd+"nj,b.rs,b.je,a.xymc,a.zymc ";
					 groupzd=" count(*)rs,sum(xmzzje)je,b.nj,b.xydm,b.zydm ";
					 topTr=new String[]{"年级","学院","专业","人数","金额"};
					 output=new String[]{"nj","xymc","zymc","rs","je"};
					 grop+=" ,b.zydm,b.xydm ";
					 query=" a."+form.getXzx()+"=b."+form.getXzx()+"    and a.zydm=b.zydm and a.xydm=b.xydm ";
				}else if(!Base.isNull(form.getXydm())&& !"".equals(form.getXydm())){
					 xszd=" distinct("+njzd+"||a.xymc),"+njzd+"nj,b.rs,b.je,a.xymc ";
					 groupzd=" count(*)rs,sum(xmzzje)je,b.nj,b.xydm ";
					 topTr=new String[]{"年级","学院","人数","金额"};
					 output=new String[]{"nj","xymc","rs","je"};
					 grop+=" ,b.xydm ";
					 query=" a."+form.getXzx()+"=b."+form.getXzx()+"   and a.xydm=b.xydm ";
				}else{
					 xszd=" distinct("+njzd+")nj,b.rs,b.je ";
					 groupzd=" count(*)rs,sum(xmzzje)je,b.nj ";
					 topTr=new String[]{"年级","人数","金额"};
					 output=new String[]{"nj","rs","je"};
					 query=" a."+form.getXzx()+"=b."+form.getXzx()+"   ";
				}
			}
		}
		//SQL拼接
		sb.append("(select a.*,rownum r from( ");
		if("xszz_comm_zzwsb".equalsIgnoreCase(xmb)){//外设表
			
			sb.append("select "+xszd+" from  (select "+groupzd+" from ");
			sb.append("(select  *  from "+xmb+" a,view_xsjbxx b,xszz_zzxmb c "+mQuery.getQueryString()+xhQuery);
			if(!Base.isNull(form.getKssj())){
				sb.append(" and sqsj>="+form.getKssj());
			}
			if(!Base.isNull(form.getJssj())){
				sb.append(" and sqsj<="+form.getJssj());
			}
			sb.append(" and a.xmdm='"+form.getZzxm()+"' and a.xh=b.xh and a.xmdm=c.xmdm and (c.shjb='三级审核' and a.shzt3='通过' or c.shjb='两级审核' and a.shzt2='通过' or c.shjb='一级审核' and a.shzt1='通过' or c.shjb='无需审核') "+yhqx.toString()+")b  ");
			sb.append(" group by "+grop+")b");//根据选择项GROUP BY
			sb.append(" left join view_njxyzybj a on "+query+" )a "+order+")");
		}else{
			sb.append("select "+xszd+" from  (select "+groupzd+" from ");
			sb.append("(select  *  from "+xmb+" a,view_xsjbxx b,xszz_zzxmb c "+mQuery.getQueryString()+xhQuery);
			if(!Base.isNull(form.getKssj())){
				sb.append(" and sqsj>='"+form.getKssj()+"' ");
			}
			if(!Base.isNull(form.getJssj())){
				sb.append(" and sqsj<='"+form.getJssj()+"' ");
			}
			sb.append(" and a.xh=b.xh and a.xmdm=c.xmdm and (c.shjb='三级审核' and a.shzt3='通过' or c.shjb='两级审核' and a.shzt2='通过' or c.shjb='一级审核' and a.shzt1='通过' or c.shjb='无需审核') "+yhqx.toString()+")b  ");
			sb.append(" group by "+grop+")b");//根据选择项GROUP BY
			sb.append(" left join view_njxyzybj a on "+query+" )a "+order+")");
		}

		List<String[]>list=CommonQueryDAO.commonQueryNotFy(sb.toString(), "", mQuery.getInputList(),
				output,null);
		request.setAttribute("rs",  list);
		request.setAttribute("rsNum",  list.size());
		request.setAttribute("topTr", basicService.getTopTr("",topTr));
		request.setAttribute("topTrs", topTr);
	}
	
	public int[] getLines(List<String[]>list,int len,int wz){
		int [] length =new int[list.size()+1];
		int m=len;
		int n=0;
		length[n]=m;
		n++;
		if(list.size()>1){
			for(int i=0;i<list.size()-1;i++){
				String[]wzsi=list.get(i);//
				String[]wzsj=list.get(i+1);
				if(i==list.size()-1){
					length[n]=++m;	
				}
				else{
					if(wzsi[wz].equals(wzsj[wz])){
						length[n]=++m;
					}else{
						length[n]=++m;
						n++;
					}
				}
			}
		}

		int [] getLen= new int[n+1];
		for(int i=0;i<n;i++){
			getLen[i]=length[i];
		}
		getLen[n]=list.size()+len;
		return getLen;
	}
	
	public String getSfxsG(String zmc){
		XszzZzxmDao dao=new XszzZzxmDao();
		HashMap<String,String>hashMap=dao.getSfxsG(zmc);
		String sfxs=hashMap.get("sfxs");
		return sfxs;
	}
	
}
