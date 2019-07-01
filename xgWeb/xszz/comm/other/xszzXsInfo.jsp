<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>
<script language="javascript" src="js/xgutil.js"></script>
<script language="javascript" src="js/xszz/xszzFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>

<!-- 隐藏域 -->
<%@ include file="/xszz/hiddenValue.jsp"%>
<!-- 隐藏域 end-->
<div id="xszzDiv">
</div>
<br/>
<div>	

	<logic:equal value="88888" name="xxdm">
	<table  width='99%' class='dateline'>
		<thead>
			<tr>
				<td colspan="4">
					家庭情况
				</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td width="16%">
					低保家庭
				</td>
				<td width="34%">
					<span id="sfdb"></span>
				</td>
				<td width="16%">
					重残人家庭
				</td>
				<td width="34%">
					<span id="kzzd1"></span>
				</td>
			</tr>
			<tr>
				<td>
					单亲家庭
				</td>
				<td>
					<span id="sfdq"></span>
				</td>
				<td>
					烈士子女
				</td>
				<td>
					<span id="lszn"></span>
				</td>
			</tr>
			<tr>
				<td>
					优抚对象子女
				</td>
				<td>
					<span id="kzzd2"></span>
				</td>
				<td>
					低收入家庭学生
				</td>
				<td>
					<span id="kzzd3"></span>
				</td>
			</tr>
			<tr>
				<td>
					孤儿
				</td>
				<td>
					<span id="kzzd4"></span>
				</td>
				<td>
					残疾学生
				</td>
				<td>
					<span id="kzzd5"></span>
				</td>
			</tr>
			<tr>
				<td>
					通过绿色通道入学新生
				</td>
				<td>
					<span id="kzzd6"></span>
				</td>
				<td>
					是否家庭受灾的新生
				</td>
				<td>
					<span id="kzzd7"></span>
				</td>
			</tr>
		</tbody>
	</table>
	</logic:equal>
</div>
<script language="javascript" defer="defer">

	var xxdm = $("xxdm").value;
	var xh = $("zzxh").value;
	
	if(xh != ""){
		dwr.engine.setAsync(false);
		
		var dd_html = "";
		dd_html += "<table width='99%' class='dateline'>";
		dd_html += "<thead>";
		dd_html += "<tr><td colspan='5'>学生资助</td></tr>";
		dd_html += "<tr>";
		dd_html += "<td>资助项目</td>";
		dd_html += "<td>资助时间</td>";
		dd_html += "<td>申请时间</td>";
		dd_html += "<td>项目资助级别</td>";
		dd_html += "<td>资助金额</td>";
		dd_html += "</tr>";
		dd_html += "</thead>";
		
		var tableName = "xszz_zzxmb";
		var colList = ["xmdm","xmmc","xmb","sqzq","shjb"];
		var pk = "";
		var pkValue = ""; 
		var query = "";
		
		getOtherData.getTableListInfo(tableName,colList,pk,pkValue,query,function(data){
			if( data != null && data.length > 0){
				
				var xmdm = new Array();
				var xmmc = new Array();
				var xmb = new Array();
				var sqzq = new Array();
				var shjb = new Array();
				
				for(var i = 0; i < data.length; i++){
					//项目代码
					xmdm[i] = data[i].xmdm;
					//项目名称
					xmmc[i] = data[i].xmmc;
					//项目表
					xmb[i] = data[i].xmb;
					//申请周期
					sqzq[i] = data[i].sqzq;
					//审核级别
					shjb[i] = data[i].shjb;
				}
				
				getXszzInfo.getLnXszzList(xmb,xmdm,xmmc,sqzq,shjb,xh,function(zznr){
					for(var j = 0; j < zznr.length; j++){
						dd_html += "<tr>";
						dd_html += "<td>"+zznr[j].xmmc+"</td>";
						dd_html += "<td>"+zznr[j].zzsj+"</td>";
						dd_html += "<td>"+zznr[j].sqsj+"</td>";
						dd_html += "<td>"+zznr[j].xmzzjb+"</td>";
						dd_html += "<td>"+zznr[j].xmzzje+"</td>";
						dd_html += "</tr>";
						
					}
				});
			}
		});
		dd_html += "</table>";
		dwr.engine.setAsync(true);
		
		xszzDiv.innerHTML = dd_html;
	}
	
	xxdm=$("xxdm").value;
	if(xxdm=="88888"){
		
		//创建一个json对象
			var parameter={};
			
			
			//构建json对象
			parameter["xh"]=xh;
			
			//保存URL
			var url = "xsxx_ajax.do?method=jdqkInfo";
			
			//------------AJAX begin -------------
			jQuery.ajaxSetup({async:false});
				jQuery.post(url,
				parameter,
				function(result){
					var json=eval(result);
					
					$("sfdb").innerHTML=json[0].sfdb;
					$("kzzd1").innerHTML=json[0].kzzd1;
					$("sfdq").innerHTML=json[0].sfdq;
					$("lszn").innerHTML=json[0].lszn;
					$("kzzd2").innerHTML=json[0].kzzd2;
					$("kzzd3").innerHTML=json[0].kzzd3;
					$("kzzd4").innerHTML=json[0].kzzd4;
					$("kzzd5").innerHTML=json[0].kzzd5;
					$("kzzd6").innerHTML=json[0].kzzd6;
					$("kzzd7").innerHTML=json[0].kzzd7;
				}
			);
			
			jQuery.ajaxSetup({async:true});
			//------------AJAX end -------------
		
	
	}
</script>
