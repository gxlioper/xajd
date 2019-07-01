<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/comm/dragedTable.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		function showTbody(obj,objTbody,className1,className2,html1,html2){
			if(obj.className==className1){
				obj.className=className2;
				obj.innerHTML=html2;
				//obj.parentNode.parentNode.className="cur-tr";
				document.getElementById(objTbody).style.display="none";
			}else{
				obj.className=className1;
				obj.innerHTML=html1;
				//obj.parentNode.parentNode.className="";
				document.getElementById(objTbody).style.display="";
			}
		}
	
		//应用
		function yy(zdmc){
			var yy_id = "yy_" + zdmc;		
			var li_id = "li_" + zdmc;
			var sp_id = "sp_" + zdmc;
			var ra_id = "ra_" + zdmc;

			var zdsm = $(sp_id).innerHTML;
			
			var node = document.getElementById("ul_mkms");    

			if($(yy_id).checked){
		    	var li = document.createElement("li");   
		    	var value = "<input name='chyh' type='radio' id="+ra_id+" value='"+zdmc+"'/><label>&nbsp;"+zdsm+"</label>";
		    	value += "<input name='zds' value='" + zdmc + "' type='hidden'/>";
		    	value += "<input name='zdsms' value='" + zdsm + "' type='hidden'/>";
		    	li.innerHTML = value;
		    	li.id = li_id;
		        node.appendChild(li);
			}else {
				if($(li_id)){
					node.removeChild($(li_id));
				}
			} 
       }

	   function delDczd(){
		 var lis = document.getElementsByName('chyh');
		 var flag = false;
		 var zdmc = "";
		 var node = document.getElementById('ul_mkms');
		 
		 if(lis != null && lis.length>0){
			for(var i=0;i<lis.length;i++){
				if(lis[i].checked){
					flag = true;
					zdmc = lis[i].value;
				}
			}
		 }

		 if(!flag){
			alertInfo("没有选择要删除的导出字段！");
		 }else {

			if(confirm("确认删除该导出字段？")){
				var li_id = "li_" + zdmc;
				var yy_id = "yy_" + zdmc;
	
				$(yy_id).checked = "";
				node.removeChild($(li_id));
			}
		 }
	   }

	 //向上
	 function yhup(){
		var node = document.getElementById('ul_mkms');
		var lis = node.getElementsByTagName("li");
		var ras = document.getElementsByName('chyh');

		var index = -1;
		var temp = new Array();
		
		for(var i=0; i<lis.length; i++){
			temp[i] = lis[i];
		}

		for(var i=0; i<ras.length; i++){
			if(ras[i].checked){
				index = i;
				break;
			}
		}
		
		if(index>0){
			for(var i=0; i<temp.length; i++){
				node.removeChild(temp[i]);
			}
			
			var obj = temp[index];
			temp[index] = temp[index-1];
			temp[index-1] = obj;

			for(var i=0; i<temp.length; i++){
				node.appendChild(temp[i]);
			}

			ras[index-1].checked = "checked";
		}else if(index == -1){
			alertInfo('请选择要上移的字段！');
		}else{
			alertInfo('以处在第一项，不可再进行上移操作！');
		}

		
     }

     function yhdown(){
    	var node = document.getElementById('ul_mkms');
 		var lis = node.getElementsByTagName("li");
 		var ras = document.getElementsByName('chyh');

 		var index = -1;
 		var temp = new Array();
 		
 		for(var i=0; i<lis.length; i++){
 			temp[i] = lis[i];
 		}

 		for(var i=0; i<ras.length; i++){
 			if(ras[i].checked){
 				index = i;
 				break;
 			}
 		}
 		
 		if(index<lis.length-1 && index != -1){
 			for(var i=0; i<temp.length; i++){
 				node.removeChild(temp[i]);
 			}
 			
 			var obj = temp[index];
 			temp[index] = temp[index+1];
 			temp[index+1] = obj;

 			for(var i=0; i<temp.length; i++){
 				node.appendChild(temp[i]);
 			}

 			ras[index+1].checked = "checked";
 		}else if(index == -1){
 			alertInfo('请选择要下移的字段！');
 		}else{
 			alertInfo('以处在最后一项，不可再进行下移操作！');
 		}
     }

 	 function checAll(){
		var checs = document.getElementsByName('ch_zdmc');
		var ch = $('che');

		if(ch.checked){
			for(var i=0; i<checs.length; i++){
				if(!checs[i].checked){
					checs[i].checked = "checked"
<%--					yy(checs[i].value);--%>
				}
			}
			$('rev').checked="";
		}else {
			for(var i=0; i<checs.length; i++){
				if(checs[i].checked){
					checs[i].checked = ""
<%--					yy(checs[i].value);--%>
				}
			}
		}
 	 }

 	 function revAll(){
 		var checs = document.getElementsByName('ch_zdmc');
 		$('che').checked="";
		for(var i=0; i<checs.length; i++){
			checs[i].checked = (checs[i].checked ? "" : "checked");
<%--			yy(checs[i].value);--%>
		}
 	 }

 	function init(){
 		//注册可拖拽表格
 		new DragedTable("tableId");
 	}
	</script>
	
	</head>
	<body onload="init();">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>导出字段选择</a>
<%--				<a>--%>
<%--					<span>导出字段选择</span>--%>
<%--				</a>--%>
			</p>
		</div>
		<html:form action="configureExportData" method="post">
		<input type="hidden" name="tableName" value="${tableName }"/>
		
			
		<div class="prompt" id="div_help">
	       <h3><span>提示：</span></h3>
	       <p>对需要导出的字段可以进行排序操作，即鼠标点击某个需要调整顺序的字段，直接拖动该字段所在的单元格至需要放的位置,导出Excel时将以排好的顺序导出！<br/></p>
	   	</div>
			<!-- 伸缩按钮 -->
			<div class="opencon" id="myTbody">
				<table border="0" class="formlist" id="tableId" width="95%">
					<thead>
						<tr>
							<th colspan="4">
								<span>数据字段</span>
								
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4" class="noMove" id="footId">
								<div class="choose">
									<input name="全选" id="che" type="checkbox" value="全选" onclick="checAll();"/>
									全选
									<input name="反选" id="rev" type="checkbox" value="反选" onclick="revAll();" />
									反选
								</div>
								<div align="right">
								<button type="button" name="保存" class="" onclick="refreshForm('configureExportData.do?method=saveExportFields');">
									保 存
								</button>
								<button type="button" name="取消" class="" onclick="window.close();return false;">
									关 闭
								</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody id="tbodyId">
						<logic:iterate id="field" name="fields" indexId="index">
							<%
								if (index % 4 == 0) {
							%>
							<tr>
								<%
									}
								%>
								<td>
<%-- <logic:equal name="field" property="mrdc" value="是">--%>
<%--  	checked = "checked"--%>
<%--  </logic:equal> --%>
									<input type="checkbox" name="ch_zdmc"
										<logic:present name="field" property="checked">
											checked
										</logic:present>
										value="${field.zdmc }!split!${field.zdsm}" id="yy_${field.zdmc }" />
										${field.zdsm }
								</td>

								<%
									if (index % 4 == 3) {
								%>
							</tr>
							<%
								}
							%>

						</logic:iterate>
					</tbody>
				</table>
			</div>
			
		
		<!--功能列表-->
<%--			<div class="chooselist" align="left" id="chooselist" style="display: none">--%>
<%--				<h3>--%>
<%--					<span>排序列表</span>--%>
<%--				</h3>--%>
<%--				<div class="choosebtn">--%>
<%--					<button type="button" class="chooseup" value="上移" onclick="yhup();">--%>
<%--						上移--%>
<%--					</button>--%>
<%--					<button type="button" class="choosedown" value="下移" onclick="yhdown();">--%>
<%--						下移--%>
<%--					</button>--%>
<%--					<button type="button" class="delete" onclick="delDczd();">--%>
<%--						删除--%>
<%--					</button>--%>
<%--				</div>--%>
<%--				<ul id="ul_mkms">--%>
<%--					<logic:iterate id="dczd" name="dczds">--%>
<%--						<li id="li_${dczd.zd }">--%>
<%--							<input name="chyh" type="radio" id="ra_${dczd.zd }" value="${dczd.zd }"/><label>&nbsp;${dczd.zdsm }</label>--%>
<%--							<input type="hidden" name="zdsms" value="${dczd.zdsm }"/>--%>
<%--							<input type="hidden" name="zds" value="${dczd.zd }"/>--%>
<%--						</li>--%>
<%--					</logic:iterate>--%>
<%--				</ul>--%>
<%--			</div>--%>
		</html:form>
		
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo(jQuery('#message').val(), function(){
				Close();
			});
			</script>
		</logic:present>
	</body>
</html>

