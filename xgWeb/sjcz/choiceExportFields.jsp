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
	
		//Ӧ��
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
			alertInfo("û��ѡ��Ҫɾ���ĵ����ֶΣ�");
		 }else {

			if(confirm("ȷ��ɾ���õ����ֶΣ�")){
				var li_id = "li_" + zdmc;
				var yy_id = "yy_" + zdmc;
	
				$(yy_id).checked = "";
				node.removeChild($(li_id));
			}
		 }
	   }

	 //����
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
			alertInfo('��ѡ��Ҫ���Ƶ��ֶΣ�');
		}else{
			alertInfo('�Դ��ڵ�һ������ٽ������Ʋ�����');
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
 			alertInfo('��ѡ��Ҫ���Ƶ��ֶΣ�');
 		}else{
 			alertInfo('�Դ������һ������ٽ������Ʋ�����');
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
 		//ע�����ק���
 		new DragedTable("tableId");
 	}
	</script>
	
	</head>
	<body onload="init();">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�����ֶ�ѡ��</a>
<%--				<a>--%>
<%--					<span>�����ֶ�ѡ��</span>--%>
<%--				</a>--%>
			</p>
		</div>
		<html:form action="configureExportData" method="post">
		<input type="hidden" name="tableName" value="${tableName }"/>
		
			
		<div class="prompt" id="div_help">
	       <h3><span>��ʾ��</span></h3>
	       <p>����Ҫ�������ֶο��Խ�������������������ĳ����Ҫ����˳����ֶΣ�ֱ���϶����ֶ����ڵĵ�Ԫ������Ҫ�ŵ�λ��,����Excelʱ�����źõ�˳�򵼳���<br/></p>
	   	</div>
			<!-- ������ť -->
			<div class="opencon" id="myTbody">
				<table border="0" class="formlist" id="tableId" width="95%">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ֶ�</span>
								
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4" class="noMove" id="footId">
								<div class="choose">
									<input name="ȫѡ" id="che" type="checkbox" value="ȫѡ" onclick="checAll();"/>
									ȫѡ
									<input name="��ѡ" id="rev" type="checkbox" value="��ѡ" onclick="revAll();" />
									��ѡ
								</div>
								<div align="right">
								<button type="button" name="����" class="" onclick="refreshForm('configureExportData.do?method=saveExportFields');">
									�� ��
								</button>
								<button type="button" name="ȡ��" class="" onclick="window.close();return false;">
									�� ��
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
<%-- <logic:equal name="field" property="mrdc" value="��">--%>
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
			
		
		<!--�����б�-->
<%--			<div class="chooselist" align="left" id="chooselist" style="display: none">--%>
<%--				<h3>--%>
<%--					<span>�����б�</span>--%>
<%--				</h3>--%>
<%--				<div class="choosebtn">--%>
<%--					<button type="button" class="chooseup" value="����" onclick="yhup();">--%>
<%--						����--%>
<%--					</button>--%>
<%--					<button type="button" class="choosedown" value="����" onclick="yhdown();">--%>
<%--						����--%>
<%--					</button>--%>
<%--					<button type="button" class="delete" onclick="delDczd();">--%>
<%--						ɾ��--%>
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

