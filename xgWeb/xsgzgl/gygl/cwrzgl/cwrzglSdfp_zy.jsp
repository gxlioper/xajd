<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xgservice.js"></script>
		<script type='text/javascript' src="js/moveDiv.js"></script>
		<script type='text/javascript' src="js/AjaxFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getOtherData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/messageFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>	
		<script type="text/javascript" src="js/comm/ymPrompt.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
		//��λ�ֶ������ѯ
		function allNotEmpThenGo(){
			var lddm=$("lddm").value;
			if(lddm==""){
				alertInfo("��ѡ��¥��!");
				return false;
			}

			//var bjdm=$("bjdm").value;
			var bjdm=jQuery("#bj").val();
			if(bjdm==""){
				alertInfo("��ѡ��༶!");
				return false;
			}
			
			var url="gyglnew_cwrzgl.do?method=cwrzglSdfp";
			refreshForm(url);
			BatAlert.showTips('���ڲ��������Ե�...');
		}

		//��λ�ֶ�����
		var doType_temp;
		function saveQscwfpxx(doType){
			doType_temp=doType;			
			var select_qs_count=0;//ѡ�е����Ҹ���
			var select_cw_count=0;//ѡ�еĴ�λ����
			var select_xs_count=0;//ѡ�е�ѧ������
			var checkbox=document.getElementsByName("checkbox_qsh");
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					select_qs_count++;
				}
			}
			checkbox=document.getElementsByName("checkbox_cwh");
			for(var i=0;i<checkbox.length;i++){
				if(doType=="qxfp"){
					if(checkbox[i].checked&&checkbox[i].parentNode.getElementsByTagName("div").length>0
							&&checkbox[i].parentNode.getElementsByTagName("div").length==1){ 
						select_cw_count++;
					}
				}else{
					if(checkbox[i].checked&&checkbox[i].parentNode.getElementsByTagName("input").length>1){ 
						select_cw_count++;
					}else{
						checkbox[i].checked=false;
					}
				}
			}
			checkbox=document.getElementsByName("checkbox_xh");
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					select_xs_count++;
				}
			}
			
			if(doType=="qxfp"){
				if(select_xs_count==0&&select_cw_count==0){
					alertInfo("��ѡ����Ҫȡ����ס��ѧ����λ��");
					return false;
				}
				$("th_span_lable").innerHTML="��ѡ������ʱ��";
				$("th_rzsj").innerHTML="����ʱ��";
				$("tr_tsyy").style.display="";
				$("tr_rzyy").style.display="none";
				$("tr_bz").style.display="";
				$("submit_bz").innerHTML="���ι�ѡ����ѧ����"+select_xs_count+"��,��λ����"+select_cw_count+"����ȷ��ȡ����ס��";
				//tipsWindown("������ʾ","id:tempDiv","350","200","true","","true","id");
				tipsWindownNew("������ʾ","id:tempDiv",350,200);
				//if(!confirm("���ι�ѡ����,ѧ����"+select_xs_count+"��,��λ����"+select_cw_count+"��\nȷ��ȡ����ס��")){
				//	return false;
				//}
			}else{
				if(select_cw_count==0){
					alertInfo("�밲����Ҫ��ס��ѧ������Ӧ�Ĵ�λ��");
					return false;
				}
				$("th_span_lable").innerHTML="��ѡ����סʱ��";
				$("th_rzsj").innerHTML="��סʱ��";
				$("tr_tsyy").style.display="none";
				$("tr_rzyy").style.display="";
				$("tr_bz").style.display="none";
				$("submit_bz").innerHTML="���ι���ס"+select_cw_count+"��ѧ����ȷ����ס��"+
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				//tipsWindown("������ʾ","id:tempDiv","400","200","true","","true","id");
				tipsWindownNew("������ʾ","id:tempDiv",400,200);
				//if(!confirm("���ι���ס"+select_cw_count+"��ѧ����\nȷ����ס��")){
				//	return false;
				//}
			}
		}	
		//����ȡ����λ������Ϣ�����ύ
		function saveQscwfpxx_submit(){
			var doType=doType_temp;
			var rzsj=$("rzsj").value;
			if("qxfp"==doType){
				if(rzsj==""){
					alertInfo("��ѡ������ʱ�䣡");
					return false;
				}
				var tsyy=$("tsyy").value;
				if(tsyy==""){
					alertInfo("��ѡ������ԭ��");
					return false;
				}
			}else{
				if(rzsj==""){
					alertInfo("��ѡ����סʱ�䣡");
					return false;
				}
			}
			//$("nj").value=$("hidden_nj").value;
			//$("xydm").value=$("hidden_xydm").value;
			$("bjdm").value=$("hidden_bjdm").value;
			$("xb").value=$("hidden_xb").value;
			$("lddm").value=$("hidden_lddm").value;
			var url="gyglnew_cwrzgl.do?method=cwrzglSdfp&doType="+doType;
			refreshForm(url);
			BatAlert.showTips('���ڱ��棬���Ե�...');
		}
		
		//���ò�ѯ��ʾ��λ��
		function setSearchMsgWz(left,top){
			$("input_mhcx_msg").style.left=left;
			$("input_mhcx_msg").style.top=top;
		}	

		//ѡ������
		function selectAll(obj,curr_type){
			var xb=$("xb").value;
			var ches;
			if(curr_type=="cw"){//��λ
				if(obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//�Ա𲻶�Ӧʱ������ѡ��
					obj.checked=false;
					return false;
				}
				selectCw(obj,curr_type,1);
				return false;
			}else if(curr_type=="qs"){//����
				ches=obj.parentNode.parentNode.getElementsByTagName("input");
			}else if(curr_type=="lc"){//¥��
				ches=obj.parentNode.parentNode.nextSibling.getElementsByTagName("input");
			}else if(curr_type=="ld"){//¥��
				ches=obj.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("input");
			}else{
				return false;
			}
			var new_cw_count=0;//������ѡ��Ĵ�λ����
			for(var i=0;i<ches.length;i++){
				if(ches[i].type=="checkbox"&&ches[i].disabled==false){
					//alertInfo(ches[i].parentNode.parentNode.childNodes[0].childNodes[0].value);
					if(ches[i].name=="checkbox_cwh"){
							if(ches[i].parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//�Ա𲻶�Ӧʱ������ѡ��
								continue;
							}
					}else if(ches[i].name=="checkbox_qsh"){
							if(ches[i].parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//�Ա𲻶�Ӧʱ������ѡ��
								if(curr_type=="qs"){//���ڴ�λ�����Ҳ�����ѡ��
									obj.checked=false;
								}
								continue;
							}
					}
					if(obj.checked&&!ches[i].checked&&ches[i].name=="checkbox_cwh"){//++������ѡ��Ĵ�λ��
						new_cw_count++;
					}
					ches[i].checked=obj.checked?true:false;
				}
			}
			//alertInfo("��������"+select_qs_count+"   ��λ����"+select_cw_count);
			selectCw(obj,curr_type,new_cw_count);//����Ԥ��ס
			qxNotAllCheckbox();
		}
		//ѡ�д�λ����ѧ����ס�Ĳ���
		function selectCw(obj,type,new_cw_count){
			var xss=document.getElementsByName("checkbox_xh");
			var cws=document.getElementsByName("checkbox_cwh");
			var xsnamediv=document.getElementById("table_ld").getElementsByTagName("div");
			if(obj.checked){
				var xs_index=0;
				var cw_index=0;
				var tr_xs;
				var new_xs_count=0;//��ǰѡ��δԤ��ס��ѧ��
				for(var i=0;i<cws.length;i++){
					if(cws[i].checked&&cws[i].parentNode.getElementsByTagName("div").length==0){ 
						for(;xs_index<xss.length;xs_index++){
							if(xss[xs_index].checked&&xss[xs_index].parentNode.parentNode.style.display==""
								&&xss[xs_index].parentNode.getElementsByTagName("input")[1].value=="δ��ס"){
								new_xs_count++;//++��ǰѡ��δԤ��ס��ѧ��
								break;
							}
						}
						if(!xss[xs_index]){
							if(new_xs_count==0){
								alertInfo("��ѡ����Ҫ��ס��ѧ����");
							}else{
								alertInfo("ѡ�еĿ���ס��λ��Ϊ<font color='red'>"+new_cw_count+"</font>����<br/>"+
										"��ס��ѡ��<font color='red'>"+new_xs_count+"</font>��ѧ����<br/>"+
										"����<font color='red'>"+(new_cw_count-new_xs_count)+"</font>����λ����ס��");
							}
							break
						};
						tr_xs=xss[xs_index].parentNode.parentNode;
						var div=document.createElement("div");
						div.name="xsxsnamediv";
						div.id="div_xs_"+xss[xs_index].value;
						div.onmouseover=function(){BatAlert.showInfo(tr_xs.cells[2].getElementsByTagName("input")[0].value);};
						div.onmouseout=BatAlert.showInfo;
						
						div.innerHTML="<input type='hidden' name='div_xs_xh' value='"+xss[xs_index].value+"'/><nobr>"+tr_xs.cells[2].innerText+"</nobr>"+tr_xs.cells[1].innerHTML;
						cws[i].parentNode.appendChild(div);
						document.getElementById("tr_xs_"+xss[xs_index].value).style.display="none";
						xs_index++;
					}
					cw_index++;
				}
				//ȡ������ѧ�������Ӧ�Ĵ�λ
				for(var i=cw_index;i<cws.length;i++){
					if(cws[i].parentNode.getElementsByTagName("div").length==0){
						cws[i].checked=false;
					}
				}
			}else{//��λ����ȡ��
				var xsnamediv;
				var xh;
				for(var i=0;i<cws.length;i++){
					xsnamediv=cws[i].parentNode.getElementsByTagName("div");
					if(!cws[i].checked&&xsnamediv.length==1){
					//alertInfo(xsnamediv[0].getElementsByTagName("input"));
						if(xsnamediv[0].getElementsByTagName("input").length>0){//����ס����Ĵ�λ��divû��������
							xh=xsnamediv[0].getElementsByTagName("input")[0].value;
							document.getElementById("tr_xs_"+xh).style.display="";
							xsnamediv[0].parentNode.removeChild(xsnamediv[0]);
						}
					}
				}
			}
		}
		//ȡ����ȫѡcheckbox
		function qxNotAllCheckbox(){
			var cws;
			var chs;
			var b=false;
			var qss=document.getElementsByName("checkbox_qsh");
			for(var i=0;i<qss.length;i++){
				b=false;
				cws=qss[i].parentNode.parentNode.getElementsByTagName("input");
				for(var j=0;j<cws.length;j++){
					if(cws[j].type=="checkbox"&&!cws[j].checked){
						b=true;
						break;
					}
				}
				if(b){
					qss[i].checked=false;//ȡ������ѡ��
				}
			}
		}
		//¥�㴲λ��Ϣ����ʾ������
		function show_hidden_lc(obj,type){
			if(type=="all"){//��������¥��
				var tbody_tr=obj.parentNode.parentNode.parentNode.nextSibling.childNodes;
				//alertInfo(tbody_tr.length);
				for(var i=0;i<tbody_tr.length;i++){
					//alertInfo(tbody_tr[i].tagName);
					if(i%2==0&&tbody_tr[i].getElementsByTagName("button")[0]){
						tbody_tr[i].getElementsByTagName("button")[0].innerHTML=obj.innerHTML=="����"?"չ��":"����";
					}else{
						tbody_tr[i].style.display=obj.innerHTML=="����"?"none":"";
					}
				}
			}else{
				var temp=obj.parentNode.parentNode.nextSibling;
				temp.style.display=temp.style.display==""?"none":"";
			}
			obj.innerHTML=obj.innerHTML=="����"?"չ��":"����";
		}
		//ѡ������
		function checkAll(obj,checkbox_name){
			var chs=document.getElementsByName(checkbox_name);
			for(var i=0;i<chs.length;i++){
				chs[i].checked=obj.checked;
			}
		}

		//����¥���б�
		function loadLdlist(){
			var bjdm=document.getElementById("bjdm").value;
			var xb=document.getElementById("xb").value;
			var url="gyglnew_cwfpgl.do?method=loadLdlist&x="+Math.random();
			jQuery.get(url, { xb:(xb=="��"?"1":"2"),bjdm:bjdm,bz:"zyloadldlist"},
					  function(data){
				  		var ldobj=document.getElementById("lddm");
					    var ldlist=data.split(";");
					    var t;
					    ldobj.options.length=0;
					    for(var i=0;i<ldlist.length;i++){
						    t=ldlist[i].split(",");
					    	ldobj.options[ldobj.options.length] = new Option(t[1], t[0]);
						}
					  }); 
		}
		function show_hidden_ldtj(){
			var ldtj = document.getElementById("ldtj");
			if(!ldtj){
				return false;
			}
			var btn_ldtj = document.getElementById("btn_ldtj");
			if(ldtj.style.display=="none"){
				ldtj.style.display="";
			}else{
				ldtj.style.display="none";
			}
			btn_ldtj.innerHTML=btn_ldtj.innerHTML=="����¥��ͳ��"?"�鿴¥��ͳ��":"����¥��ͳ��";
		}

		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ����-ס�޹���-��λ��ס</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				���Ҵ�λ��ɫ˵����
				<font color="green"><B>����ס��λ</B></font>��
				<font color="black"><B>δ��ס��λ</B></font>��<br>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
					�˴���д����˵��
				</span>
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/gyglnew_cwrzgl">			
			<!-- ������ -->
			<!-- ������� -->
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx }"/>
<%--			<input type="hidden" name="hidden_nj" id="hidden_nj" value="<bean:write name="rs" property="nj"/>"/>--%>
<%--			<input type="hidden" name="hidden_xydm" id="hidden_xydm" value="<bean:write name="rs" property="xydm"/>"/>--%>
			<input type="hidden" name="hidden_nj" value="<bean:write name="rs" property="nj"/>"/>
			<input type="hidden" name="hidden_xydm" value="<bean:write name="rs" property="xydm"/>"/>
			<input type="hidden" name="hidden_bjdm" id="hidden_bjdm" value="<bean:write name="rs" property="bjdm"/>"/>
			<input type="hidden" name="hidden_xb" id="hidden_xb" value="<bean:write name="rs" property="xb"/>"/>
			<input type="hidden" name="hidden_lddm" id="hidden_lddm" value="<bean:write name="rs" property="lddm"/>"/>
			<input type="hidden" name="act" value="${act}"/>
			
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			
			<logic:equal value="true" name="isFdy">
				<input type="hidden" id="isFdy" value="true"/>
			</logic:equal>
			<logic:equal value="true" name="isBzr">
				<input type="hidden" id="isBzr" value="true"/>
			</logic:equal>
			<input type="hidden" id="userType" value="${user.userStatus }"/>
			<input type="hidden" id="userName" value="${user.userName }"/>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- ��ס -->
						<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('save');return false;" class="btn_shtg"> ������ס </a></li>
						<!-- ȡ����ס -->
						<logic:equal value="true" name="isKqxrz">
							<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('qxfp');return false;" class="btn_shbtg"> ȡ����ס </a>	</li>
						</logic:equal>
						<logic:notEqual value="true" name="isKqxrz">
							<li><a href="#" id="btn_xg" title="��ǰ�꼶<bean:message key="lable.xsgzyxpzxy" />��ȡ����סδ����!" class="btn_shbtg"><font color="gray">ȡ����ס</font> </a>	</li>
						</logic:notEqual>
						<li><a href="#" id="btn_ldtj" onclick="show_hidden_ldtj()" class="btn_cx">����¥��ͳ��</a></li>	
						<li><a href="gyglnew_cwrzgl_cwrz.do" id="btn_fh" class="btn_fh">���� </a></li>	
					</ul>
				</div>
				<!-- ��ť end-->	
				
				<!-- �������� -->
<%--				<%@ include file="/comm/search/superSearchArea.jsp"%>--%>
				<!-- �������� end-->				
				<div class="searchtab">
					<logic:notEmpty name="xynjxbld_tjxx">
					<table id="ldtj" class="dateline" width="98%" style="display: ">
						<thead>
						<tr>
							<td align="left">¥��</td>
							<td align="left">��ǰ<bean:message key="lable.xsgzyxpzxy" />�ܴ�λ��</td>
							<td align="left">��ǰ<bean:message key="lable.xsgzyxpzxy" />�ѷ��䴲λ��</td>
							<td align="left">��ǰ<bean:message key="lable.xsgzyxpzxy" />δ���䴲λ��</td>
							<td align="left">��ǰרҵ��λ��</td>
							<td align="left">��ǰרҵ����ס��λ��</td>
							<td align="left">��ǰרҵδ��ס��λ��</td>
						</tr>
						</thead>
						<% String lddm=((HashMap<String,String>)request.getAttribute("rs")).get("lddm");//��ǰѡ�е�¥������ %>
						<logic:iterate id="s" name="xynjxbld_tjxx">
						<tr>
							<%if(((HashMap<String,String>)s).get("lddm").equals(lddm)){ %>
								<td style="color: red"><bean:write name="s" property="ldmc"/></td>
								<td style="color: red"><bean:write name="s" property="cws"/></td>
								<td style="color: red"><bean:write name="s" property="yfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="zycws"/></td>
								<td style="color: red"><bean:write name="s" property="zyyrzcws"/></td>
								<td style="color: red"><bean:write name="s" property="zywrzcws"/></td>
							<%}else{ %>
								<td><bean:write name="s" property="ldmc"/></td>
								<td><bean:write name="s" property="cws"/></td>
								<td><bean:write name="s" property="yfpcws"/></td>
								<td><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="zycws"/></td>
								<td style="color: red"><bean:write name="s" property="zyyrzcws"/></td>
								<td style="color: red"><bean:write name="s" property="zywrzcws"/></td>
							<%} %>
						</tr>
						</logic:iterate>
					</table>
					</logic:notEmpty>
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<td>��ǰ�����༶����</td>
<%--								<th width="6%"><bean:message key="lable.xsgzyxpzxy" /></th>--%>
<%--								<td width="20%">--%>
<%--									<logic:equal value="xy" name="user" property="userStatus">--%>
<%--									     <html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();loadLdlist();" disabled="true" style="width:150px;">--%>
<%--											<html:optionsCollection name="xyList" label="xymc" value="xydm"/>--%>
<%--										</html:select>--%>
<%--										<html:hidden property="xydm"/>--%>
<%--									</logic:equal>--%>
<%--									<logic:notEqual value="xy" name="user" property="userStatus">--%>
<%--									     <html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();loadLdlist();" style="width:150px;">--%>
<%--											<html:optionsCollection name="xyList" label="xymc" value="xydm"/>--%>
<%--										</html:select>--%>
<%--									</logic:notEqual>--%>
<%--								</td>--%>
<%--								<th width="6%">�꼶</th>--%>
<%--								<td width="10%">--%>
<%--									<html:select property="nj" styleId="nj" onchange="initZyList();initBjList();loadLdlist();" style="width:70px;">--%>
<%--										<html:optionsCollection name="njList" label="nj" value="nj"/>--%>
<%--									</html:select>--%>
<%--								</td>--%>
								<th width="6%">רҵ</th>
								<td width="20%">
									<html:select property="zydm" styleId="zy" onchange="initBjList();loadLdlist();" style="width:120px;">
											<html:option value=""></html:option>
											<logic:present name="zyList">
												<html:optionsCollection name="zyList" label="zymc" value="zydm"/>
											</logic:present>
										</html:select>
								</td>
								<th width="6%">�༶</th>
								<td width="10%">
									<html:select property="bjdm" styleId="bj" onchange="loadLdlist();" style="width:120px;">
											<html:option value=""></html:option>
											<logic:present name="bjList">
												<html:optionsCollection name="bjList" label="bjmc" value="bjdm"/>
											</logic:present>
										</html:select>
								</td>
								<th width="6%">�Ա�</th>
								<td width="10%">
									<html:select property="xb" styleId="xb" style="width:70px;" onchange="loadLdlist();">
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
									</html:select>
								</td>
								<td width="30%">
									������:<font color="red">${xynj_tjxx.num }</font>,
									����ס:<font color="red">${xynj_tjxx.yycws}</font>,
									δ��ס:<font color="red">${xynj_tjxx.wrzrs}</font>;<br/>
									�ѷ��䴲λ��:<font color="red">${xynj_tjxx.cws }</font>,
									ʣ�ലλ��:<font color="red">${xynj_tjxx.sycws }</font>
								</td>
							</tr>
							<tr>
								<td>��ǰ����¥������</td>	
								<th>¥��</th>
								<td>
									<html:select name="rs" property="lddm" styleId="lddm" style="width:120px;">
										<html:option value="">--��ѡ��--</html:option>
										<html:optionsCollection name="ldlist" label="ldmc" value="lddm"/>
									</html:select>
								</td>
								<th>��λ״̬</th>
								<td>
									<html:select property="cwzt" styleId="cwzt" style="width:120px;">
										<html:option value=""></html:option>
										<html:option value="δ��ס">δ��ס</html:option>
										<html:option value="����ס">����ס</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="8">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('showNews.do')">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>					
				</div>
				
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="ldjbxx">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="ldjbxx">
							&nbsp;&nbsp;
							<font color="blue">
							
							</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow">
				<!-- ������table start -->
				<table style="width:100%">
					<tr>
						<td width="25%" style="vertical-align: top;" >
							<table class="dateline" width="100%">
<%--								<tr>--%>
<%--									<th>רҵ</th>--%>
<%--									<td>--%>
<%--										<html:select property="zydm" styleId="zy" onchange="initBjList();loadLdlist();" style="width:120px;">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<logic:present name="zyList">--%>
<%--												<html:optionsCollection name="zyList" label="zymc" value="zydm"/>--%>
<%--											</logic:present>--%>
<%--										</html:select>--%>
<%--									</td>--%>
<%--								</tr>--%>
<%--								<tr>--%>
<%--									<th>�༶</th>--%>
<%--									<td>--%>
<%--										<html:select property="bjdm" styleId="bj" onchange="loadLdlist();" style="width:120px;">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<logic:present name="bjList">--%>
<%--												<html:optionsCollection name="bjList" label="bjmc" value="bjdm"/>--%>
<%--											</logic:present>--%>
<%--										</html:select>--%>
<%--									</td>--%>
<%--								</tr>--%>
								<tr>
									<th>��ס���</th>
									<td>
										<html:select property="rzqk" styleId="rzqk" style="width:120px;">
											<html:option value=""></html:option>
											<html:option value="δ��ס">δ��ס</html:option>
											<html:option value="����ס">����ס</html:option>
										</html:select>
									</td>
								</tr>
								<tr>
									<th><nobr>ѧ��/����:</nobr></th>
									<td><html:textarea property="xhxm" rows="5" style="width:115px;"></html:textarea></td>
								</tr>
								<tr>
									<td colspan="2" align="right">
										<div class="btn">
											<button type="button" class="btn_cx" id="search_go" 
												onclick="allNotEmpThenGo('showNews.do')">
												�� ѯ
											</button>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<div style="height: 500px;overflow-x:hidden;overflow-y:auto;">
										<table id="table_xsxx" width="100%"> 
											<thead>
												<tr>
													<th><input type="checkbox" onclick="checkAll(this,'checkbox_xh')"/></th>
													<th>ѧ��</th>
													<th>����</th>
												</tr>
											</thead>
											<tbody>
											<% HashMap<String,Character> bjmark=new HashMap<String,Character>();
											   char curr_bjmark='@';
											   String curr_bjmc="";
											   String[] xs;%>
												<logic:iterate id="s" name="xsxxlist">
													<tr id="tr_xs_${s[0] }">
														<td><input type="checkbox" name="checkbox_xh" onclick="" value="${s[0]}"/><input type="hidden" value="${s[2]}"/></td>
														<td><nobr>${s[0] }</nobr></td>
														<td onmouseover="BatAlert.showInfo('${s[6]}');" onmouseout="BatAlert.showInfo();">
															<nobr>
															<input type="hidden" value="${s[6]}"/>															
															<%xs=(String[])s;
															if("����ס".equals(xs[2])){//����סѧ��������Ϊ��ɫ
															%>
															<font color="green">
															<%}
															if(!curr_bjmc.equals(xs[6])){
																curr_bjmc=xs[6];
																curr_bjmark++;
																bjmark.put(xs[6],curr_bjmark);
																}
															%>
															[<%=curr_bjmark%>]
															${s[1] }
															<%
															if("����ס".equals(xs[2])){
															%>
															</font>
															<%}%>
															&nbsp;&nbsp;</nobr>
														</td>
													</tr>
												</logic:iterate>
											</tbody>
										</table>
										</div>
									</td>
								</tr>
							</table>
						</td>
						<td width="75%" style="vertical-align: top;">
				<!-- ������table end1 -->	
				<div style="height: 718px;overflow-x:auto;overflow-y:auto;">
				<table class="dateline" id="table_ld" border="1px;" width="100%">
					<!-- ��ͷ -->
					<thead>
						<logic:notEmpty name="ldjbxx">
						<tr align="center" style="cursor:hand">
							<td>
							<input type="checkbox" id="checkbox_lddm" name="checkbox_lddm" onclick="selectAll(this,'ld');" 
							value="<bean:write name="ldjbxx" property="lddm"/>"/>
							¥������:<bean:write name="ldjbxx" property="ldmc"/>
							<button type="button"  onclick="show_hidden_lc(this,'all');">����</button>
							</td>
						</tr>
						</logic:notEmpty>
					</thead><tbody>
					<!-- ��ͷ end-->
					<!--���� -->
					<% 
					    String act=(String)request.getAttribute("act");//��ǰѡ���ѧԺ
					    String xydm=((HashMap<String,String>)request.getAttribute("rs")).get("xydm");//��ǰѡ���ѧԺ
						String nj=((HashMap<String,String>)request.getAttribute("rs")).get("nj");//��ǰѡ�е��꼶
						List<HashMap<String,String>> ldlcqscwxxb=(List<HashMap<String,String>>)request.getAttribute("ldlcqscwxxb");
						
						int index=0; //��¼ѭ����λ������
						String curr_ch="";//��ǰ���
						String curr_qsh="";//��ǰ���Һ�
						String curr_qsxy="";//��ǰ��������ѧԺ
						String curr_qsnj="";//��ǰ���������꼶
						HashMap<String,String> cwxx=null;//��λ��Ϣ
						boolean qs_top_mark=true;//���ҿ�ʼ��ǣ�������ʾ����ͷ��
						boolean qs_cw_end_mark=true;//���Ҵ�λĩβ���
						boolean ld_cw_end_mark=false;//¥����λ�Ƿ�ѭ�����
					%>
					<logic:iterate name="ldlcxx" id="s">
						<tr onclick="rowOnClick(this);" style="cursor:hand">
						<td>
							<%curr_ch=((HashMap<String,String>)s).get("ch");//�Ե�ǰѭ���Ĳ������ʱ��Ǹ�ֵ
							  qs_top_mark=true;%>
							  <input type="checkbox" name="checkbox_ch" onclick="selectAll(this,'lc');" 
							  value="<bean:write name="s" property="ch"/>" />
							<bean:write name="s" property="chmc"/> ��
							[								
								���䴲λ����<bean:write name="s" property="cws"/>��
								δ��ס��λ����<bean:write name="s" property="wfp_cws"/>��						
								����ס��λ����<font color="red"><bean:write name="s" property="yfp_cws"/></font>
							]
							<button type="button"  onclick="show_hidden_lc(this);">����</button>
						</td>
						</tr>
						<tr>
							<td>
								<table width="98%" style="border: 0px;">
									<tr>
									<%for(int i=0;i<10000&&index<ldlcqscwxxb.size();i++){//����ѭ�����ң����ݴ�λ�Ĳ�Ž����жϸô�λ�Ƿ��Ǹò�Ĵ�λ�������ڲ�ѯʱ������
										cwxx=ldlcqscwxxb.get(index);
										if(curr_ch.equals(cwxx.get("ch"))){//��λ��ź͵�ǰ��Ŷ�Ӧʱ�ſ�ѭ��
											if(curr_qsh.equals(cwxx.get("qsh"))){//���ҺŶ�Ӧʱ�����д�λ��ѭ��
												if(qs_top_mark){//��������ҿ�ʼѭ������Ҫ���������ͷ��
													%>
														<td width="15%">
															<input type="hidden" value="<%=cwxx.get("qsxb") %>"/>
															<input type="checkbox" name="checkbox_qsh" onclick="selectAll(this,'qs');" 
																<%=("to_fp".equals(act)&&cwxx.get("qsxydm")!=null)?"disabled='disabled'":"" %> 
															    value="<%=cwxx.get("qsh")%>"/><%=cwxx.get("qsh") %>
															</font>
														</td><td><table><tr>
													<%
													  qs_cw_end_mark=true;
												}
												%>
														<td width="70px" <%if(cwxx.get("xh")!=null){%>onmouseover="BatAlert.showInfo('<%=cwxx.get("bjmc")==null?"":cwxx.get("bjmc")%>');" onmouseout="BatAlert.showInfo();"<%}%></td>
															<input type="checkbox" name="checkbox_cwh"  onclick="selectAll(this,'cw');" 
																
															value="<%=cwxx.get("qsh")%>_<%=cwxx.get("cwh")%>"/><%=cwxx.get("cwh") %><%
																if((curr_qsxy!=null&&!curr_qsxy.equals(cwxx.get("xydm")))
																   		||(curr_qsnj!=null&&!curr_qsnj.equals(cwxx.get("nj")))){
																	%>
																	<%=cwxx.get("nj")==null?"":"("+cwxx.get("nj")+")"%><br>
																	<%=cwxx.get("xymc")==null?"":cwxx.get("xymc")%>
																	<%
																}
															%>
															</font>
															<%  if(cwxx.get("xh")!=null){
																%>
																<div style="color: green;">
																<%if(bjmark.get(cwxx.get("bjmc"))==null){
																	curr_bjmc=cwxx.get("bjmc");
																	curr_bjmark++;
																	bjmark.put(cwxx.get("bjmc"),curr_bjmark);
																}%>
																[<%=bjmark.get(cwxx.get("bjmc"))%>]																															
																<%=cwxx.get("xm")==null?"":cwxx.get("xm") %><br/>
																<%=cwxx.get("xh")==null?"":cwxx.get("xh") %>
																</div><%
															} %>
														</td>
													<%
												qs_top_mark=false;
												index++;//��λ���ʱ����λ����Ϣ����++
											}else{//���ҺŲ���Ӧ�Ǿͽ��и�ֵ��������һ�н�����һ������
												if(!"".equals(curr_qsh)&& qs_cw_end_mark){
													%></tr></table></td><%
												}
												curr_qsh=cwxx.get("qsh");
												curr_qsxy=cwxx.get("qsxydm");
												curr_qsnj=cwxx.get("qsnj");
												qs_top_mark=true;
												%></tr><tr><%
											}
										}else{//��Ų���Ӧ������������һ��¥��
											if(!"".equals(curr_qsh)&& qs_cw_end_mark){
												%></tr></table></td><%
											}
											qs_cw_end_mark=false;
											break;
										}
									} %>
									
									<%if(ldlcqscwxxb.size()>0&&index==ldlcqscwxxb.size()&&!ld_cw_end_mark){//���ڴ�λ��ѭ����ɣ���¥�㻹�е����
										%></tr></table></td><%
										ld_cw_end_mark=true;
									} %>
									</tr>
								</table>
							</td>
						</tr>
					</logic:iterate>
					</tbody>
					<!--���� end-->
					<!-- ������ -->
					<!-- ������ end-->
				</table>
				</div>
				<!-- ������table start1 -->
				</td>
					</tr>
				</table>
				<!-- ������table end1 -->
				</div>
			</div>
			<!-- ��ѯ��� end-->
			
			
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span id="th_span_lable">��ѡ����סʱ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th id="th_rzsj">
									��סʱ��
								</th>
								<td>
									<html:text property="rzsj" styleId="rzsj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"></html:text>
								</td>
							</tr>
							<tr id="tr_rzyy">
								<th >
									��סԭ��
								</th>
								<td>
									<html:select property="rzyy" styleId="rzyy">
										<html:optionsCollection name="rzyylist" label="rzyymc" value="rzyydm"/>
									</html:select>
								</td>
							</tr>
							<tr id="tr_tsyy">
								<th>
									����ԭ��
								</th>
								<td>
<%--									<html:text property="tsyy" styleId="tsyy"></html:text>--%>
									<html:select property="tsyy" styleId="tsyy">
										<html:optionsCollection name="tsyylist" label="tsyymc" value="tsyymc"/>
									</html:select>
								</td>
							</tr>
							<tr id="tr_bz">
								<th>
									��ע
								</th>
								<td>
									<html:text property="bz" styleId="bz"></html:text>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2" nowrap="nowrap">
									<div id="submit_bz" class="bz" style="color: red;">
										
									</div>
									<div class="btn">
										<button type="button" name="����"  onclick="saveQscwfpxx_submit_browser()">
											ȷ �� 
										</button>
										<button type="button" name="ȡ��"  onclick="iFClose();return false;">
											ȡ ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
		</html:form>
		<logic:present name="back">
			<script>
			<logic:equal value="true" name="back">
				alertInfo("�����ɹ���");
			</logic:equal>
			<logic:equal value="false" name="back">
				alertInfo("����ʧ�ܣ�");
			</logic:equal>
			</script>
		</logic:present>
	</body>
</html>

<script type="text/javascript"> 
	var curTr = null; 
	var tb1 = document.getElementById('table_xsxx');
	var trs = tb1.getElementsByTagName('tr'); 
	tb1.onselectstart = function(){ 
		if(curTr){ 
			document.selection.empty(); return true; 
		} 
	}; 
	for(var i=1; i<trs.length; i++) { 
		trs[i].style.cursor = 'hand'; 
		trs[i].onmousedown = function(){ 
			curTr = this; 
			curTr.style.backgroundColor = '#6495ED'; 
		}; 
		trs[i].onmouseover = function() { 
			if(curTr && curTr != this) { 
				this.swapNode(curTr); 
			} 
		} 
	} 
	document.body.onmouseup = function(){ 
		if(curTr){ 
			curTr.style.backgroundColor = ''; 
			curTr = null; 
		} 
	}; 
</script> 