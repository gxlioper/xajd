<%@ page language="java" contentType="text/html; charset=GBK"%>

<script language="javascript">
 	var xh=$("fpcwxh").value;
 	dwr.engine.setAsync(false);
 	gyglCwgl.getFpcwXsxx(xh,function (data){
 		var html="";
 		html+="<table summary='' class='datelist nowrap' align='' width='100%' id='xsmd'>";
 		html+="<thead>";
		html+="<tr align='center' style='cursor:hand'>";
		html+="<td id='xh' nowrap>Ñ§ºÅ</td>";
		html+="<td id='xm' nowrap>ÐÕÃû</td>";
		html+="</tr>";
		html+="</thead>";
		
		html+="<tbody id='xsmdtbody'>";
		for(i=0;i<data.length;i++){
			var xsxh= data[i].xh;
			var xsxm= data[i].xm;
			var xsxb= data[i].xb;
			var xsxydm= data[i].xydm;
			var xsxymc= data[i].xymc;
			var xsbjdm= data[i].bjdm;
			var xsbjmc= data[i].bjmc;
			var xszydm= data[i].zydm;
			var xszymc= data[i].zymc;
			var xsnj= data[i].nj;
			html+="<tr onclick='rowOnClick(this);' style='cursor:hand' id='tr_"+i+"'  title='"+xsxb+"'>";
			html+="<td><span id='span_xh_"+i+"'> "+xsxh+" </span></td>";
			html+="<td><span id='span_xm_"+i+"'> "+xsxm+"</span>";
			html+="<input type='hidden' id='xh_"+i+"' name='xsxhArr' value='"+xsxh+"' />";
			html+="<input type='hidden' id='xm_"+i+"' name='xsxmArr' value='"+xsxm+"' />";
			html+="<input type='hidden' id='xb_"+i+"' name='xsxbArr' value='"+xsxb+"' />";
			html+="<input type='hidden' id='xydm_"+i+"' name='xsxydmArr' value='"+xsxydm+"' />";
			html+="<input type='hidden' id='xymc_"+i+"' name='xsxymcArr' value='"+xsxymc+"' />";
			html+="<input type='hidden' id='zydm_"+i+"' name='xszydmArr' value='"+xszydm+"' />";
			html+="<input type='hidden' id='zymc_"+i+"' name='xszymcArr' value='"+xszymc+"' />";
			html+="<input type='hidden' id='bjdm_"+i+"' name='xsbjdmArr' value='"+xsbjdm+"' />";
			html+="<input type='hidden' id='bjmc_"+i+"' name='xsbjmcArr' value='"+xsbjmc+"' />";
			html+="<input type='hidden' id='nj_"+i+"' name='xsnjArr' value='"+xsnj+"' />";
			html+="</td>";
		
		}
		html+="</tbody>";
		$("wfpcwxx").innerHTML=html;
 	})
 	dwr.engine.setAsync(true);
</script>
<div id="wfpcwxx"></div>


