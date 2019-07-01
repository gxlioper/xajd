<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script language="javascript" defer="defer">
		//��λ�ֶ������ѯ
		function allNotEmpThenGo(){
			var lddm=$("lddm").value;
			if(lddm==""){
				alertInfo("��ѡ��¥��!");
				return false;
			}
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp";
			refreshForm(url);
			BatAlert.showTips('���ڲ��������Ե�...');
		}

		//��λ�ֶ�����
		function saveQscwfpxx(doType){
			var select_qs_count=0;//ѡ�е����Ҹ���
			var select_cw_count=0;//ѡ�еĴ�λ����
			var checkbox=document.getElementsByName("checkbox_qsh");
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					select_qs_count++;
				}
			}
			checkbox=document.getElementsByName("checkbox_cwh");
			for(var i=0;i<checkbox.length;i++){
				if(checkbox[i].checked){
					select_cw_count++;
				}
			}
			
			if(doType=="qxfp"){
				if(select_qs_count==0&&select_cw_count==0){
					alertInfo("��ѡ����Ҫȡ������Ĵ�λ��");
					return false;
				}
				confirmInfo("���ι�ѡ���˴�λ����"+select_cw_count+"��,\nȷ��ȡ��������",function(ok){
					if(ok=="ok"){
						saveQscwfpxx_submit(doType);
					}
				});
				//if(!confirm("���ι�ѡ���˴�λ����"+select_cw_count+"��\nȷ��ȡ��������")){
				//	return false;
				//}
			}else{
				if(select_qs_count==0&&select_cw_count==0){
					alertInfo("��ѡ����Ҫ����Ĵ�λ��");
					return false;
				}
				confirmInfo("���ι�ѡ���˴�λ����"+select_cw_count+"��,\nȷ��������",function(ok){
					if(ok=="ok"){
						saveQscwfpxx_submit(doType);
					}
				});
				//if(!confirm("���ι�ѡ���˴�λ����"+select_cw_count+"��\nȷ��������")){
				//	return false;
				//}
			}
		}	

		function saveQscwfpxx_submit(doType){
			$("nj").value=$("hidden_nj").value;
			$("bjdm").value=$("hidden_bjdm").value;
			$("xb").value=$("hidden_xb").value;
			$("lddm").value=$("hidden_lddm").value;
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&doType="+doType;
			refreshForm(url);
			BatAlert.showTips('���ڱ��棬���Ե�...');
		}
		
		//ҳ���л�
		function pageqh(act){
			var url="gyglnew_cwfpgl.do?method=cwfpglSdfp&pageqh=pageqh&act="+act;
			refreshForm(url);
			BatAlert.showTips('������ת�����Ե�...');
		}
		
		//���ò�ѯ��ʾ��λ��
		function setSearchMsgWz(left,top){
			if($("input_mhcx_msg")){
				$("input_mhcx_msg").style.left=left;
				$("input_mhcx_msg").style.top=top;
			}
		}	

		//��̬��ʾѡ������Ҵ�λ����
		function dyn_show_qs_cw_count(){
			$("dyn_show_qs_cw_count").innerHTML="��ǰ��ѡ����������<font color='red'>"+dyn_qs_count+"</font>����"+
			                                              "��λ����<font color='red'>"+dyn_cw_count+"</font>��";	
		}

		var dyn_qs_count=0;//��̬ѡ������Ҹ���
		var dyn_cw_count=0;//��̬ѡ��Ĵ�λ����
		//ѡ������
		function selectAll(obj,curr_type){
			var xb=$("xb").value;
			var ches;
			if(curr_type=="cw"){//��λ
				if(obj.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//�Ա𲻶�Ӧʱ������ѡ��
					obj.checked=false;
				}
				obj.checked?dyn_cw_count++:dyn_cw_count--;//�ı䶯̬ѡ��Ĵ�λ����
				ches=new Array();
				//return false;
			}else if(curr_type=="qs"){//����
				ches=obj.parentNode.parentNode.getElementsByTagName("input");
			}else if(curr_type=="lc"){//¥��
				ches=obj.parentNode.parentNode.nextSibling.getElementsByTagName("input");
			}else if(curr_type=="ld"){//¥��
				ches=obj.parentNode.parentNode.parentNode.parentNode.getElementsByTagName("input");
			}else{
				return false;
			}

			for(var i=0;i<ches.length;i++){
				if(ches[i].type=="checkbox"&&ches[i].disabled==false){
					if(ches[i].name=="checkbox_cwh"){
							if(ches[i].parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//�Ա𲻶�Ӧʱ������ѡ��
								continue;
							}
							obj.checked?(ches[i].checked?"":dyn_cw_count++):(ches[i].checked?dyn_cw_count--:"");//�ı䶯̬ѡ��Ĵ�λ����
					}else if(ches[i].name=="checkbox_qsh"){
							if(ches[i].parentNode.parentNode.childNodes[0].childNodes[0].value!=xb){//�Ա𲻶�Ӧʱ������ѡ��
								if(curr_type=="qs"){//���ڴ�λ�����Ҳ�����ѡ��
									obj.checked=false;
								}
								continue;
							}
							if(curr_type=="qs"){
								obj.checked?dyn_qs_count++:dyn_qs_count--;
							}else{
								obj.checked?(ches[i].checked?"":dyn_qs_count++):(ches[i].checked?dyn_qs_count--:"");//�ı䶯̬ѡ������Ҹ���
							}
					}
					ches[i].checked=obj.checked?true:false;
				}
			}
			dyn_show_qs_cw_count();
		}

		function show_hidden_lc(obj,type){
			if(type=="all"){//��������¥��

				if(jQuery(obj).text() =="չ��"){
					jQuery(".con_overlfow table.dateline>tbody>tr").show();
					jQuery(".con_overlfow table.dateline>tbody>tr button").text("����");
					
				}else{
					jQuery(".con_overlfow table.dateline>tbody>tr:odd").hide();
					jQuery(".con_overlfow table.dateline>tbody>tr button").text("չ��");
				}
			
					//hide();
				
				/*
				var tbody_tr=obj.parentNode.parentNode.parentNode.nextSibling.childNodes;
				
				//alertInfo(tbody_tr.length);
				for(var i=0;i<tbody_tr.length;i++){
					//alertInfo(tbody_tr[i].tagName);
					alert(tbody_tr[i].jQuery("#button"));
					if(i%2==0&&tbody_tr[i].getElementsByTagName("button")[0]){
						tbody_tr[i].getElementsByTagName("button")[0].innerHTML=obj.innerHTML=="����"?"չ��":"����";
					}else{
						tbody_tr[i].style.display=obj.innerHTML=="����"?"none":"";
					}
				}
				*/
			}else{
				//alert(jQuery(obj).text());
				if(jQuery(obj).text() =="չ��"){
					jQuery(obj).parents("tr").next().show();
					
				}else{
					jQuery(obj).parents("tr").next().hide();
				}
				
				
				/*
				var temp=obj.parentNode.parentNode.nextSibling;
				temp.style.display=temp.style.display==""?"none":"";

				var tbody_tr=$("button_all").parentNode.parentNode.parentNode.nextSibling.childNodes;
				//alertInfo(tbody_tr.length);
				if(obj.innerHTML=="չ��"){
					for(var i=0;i<tbody_tr.length;i++){
						if(temp!=tbody_tr[i]){
							if(i%2==0&&tbody_tr[i].getElementsByTagName("button")[0]){
								tbody_tr[i].getElementsByTagName("button")[0].innerHTML="չ��";
							}else{
								tbody_tr[i].style.display="none";
							}
						}
					}
				}
				*/
				
			}
			obj.innerHTML=obj.innerHTML=="����"?"չ��":"����";
		}

		function loadLdlist(){
			var xb=document.getElementById("xb").value;
			var xydm=document.getElementById("xydm").value;
			var nj=document.getElementById("nj").value;
			var url="gyglnew_cwfpgl.do?method=loadLdlist";
			jQuery.get(url, {xydm:xydm,nj:nj, xb:(xb=="��"?"1":"2")},
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
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ����-ס�޹���-<logic:equal value="to_qxfp" name="act">ȡ��</logic:equal>��λ����</a>
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
			<p>���Ҵ�λ��ɫ˵��:
				<font color="green"><B>��ǰ�༶�����Ҵ�λ</B></font>;
				<font color="red"><B>�ǵ��༶�����Ҵ�λ</B></font>;
				<font color="black"><B>δ��������Ҵ�λ</B></font>��<br>
				����ƶ������Ͻ�<font color="blue">��������</font>���ɲ鿴��ģ������˵����</br>
				<span id="div_help" style="display: none">
					�˴���д����˵��
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';setSearchMsgWz('130px','85px')"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/gyglnew_cwfpgl">			
			<!-- ������ -->
			<!-- ������� -->
			<input type="hidden" name="fpdx" id="fpdx" value="${fpdx }"/>
			<input type="hidden" name="hidden_nj" id="hidden_nj" value="<bean:write name="rs" property="nj"/>"/>
			<input type="hidden" name="xydm" id="xydm" value="<bean:write name="rs" property="xydm"/>"/>
			<input type="hidden" name="hidden_bjdm" id="hidden_bjdm" value="<bean:write name="rs" property="bjdm"/>"/>
			<input type="hidden" name="hidden_xb" id="hidden_xb" value="<bean:write name="rs" property="xb"/>"/>
			<input type="hidden" name="hidden_lddm" id="hidden_lddm" value="<bean:write name="rs" property="lddm"/>"/>
			<input type="hidden" name="act" value="${act}"/>
			
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="to_fp" name="act">
							<!-- ������� -->
							<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('save');return false;" class="btn_shtg"> ������� </a></li>
							<li><a href="#" id="btn_xg" onclick="pageqh('to_qxfp');return false;" class="btn_sx"> ת��ȡ��������� </a></li>
						</logic:equal>
						<logic:equal value="to_qxfp" name="act">
							<!-- ȡ������ -->
							<li><a href="#" id="btn_xg" onclick="saveQscwfpxx('qxfp');return false;" class="btn_shbtg">ȡ������</a></li>			
							<li><a href="#" id="btn_xg" onclick="pageqh('to_fp');return false;" class="btn_sx"> ת������������ </a></li>
						</logic:equal>
						<li><a href="#" id="btn_ldtj" onclick="show_hidden_ldtj()" class="btn_cx">����¥��ͳ��</a></li>	
						<li><a href="gyglnew_cwfpgl_cwfp.do" id="btn_fh" class="btn_fh">���� </a></li>	
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
							<td>¥��</td>
							<td>�ܴ�λ��</td>
							<td>�ѷ��䴲λ��</td>
							<td>δ���䴲λ��</td>
							<td>���䵱ǰ�༶��λ��</td>
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
								<td style="color: red"><bean:write name="s" property="xycws"/></td>
							<%}else{ %>
								<td><bean:write name="s" property="ldmc"/></td>
								<td><bean:write name="s" property="cws"/></td>
								<td><bean:write name="s" property="yfpcws"/></td>
								<td><bean:write name="s" property="wfpcws"/></td>
								<td style="color: red"><bean:write name="s" property="xycws"/></td>
							<%} %>
						</tr>
						</logic:iterate>
					</table>
					</logic:notEmpty>				
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<td>��ǰ�����༶����</td>
								<th width="6%">�༶</th>
								<td width="20%">
									<html:select name="rs" property="bjdm" styleId="bj" style="width:120px;">
										<html:optionsCollection name="bjList" label="bjmc" value="bjdm"/>
									</html:select>
								</td>
								<th width="6%">רҵ</th>
								<td width="20%">
									<html:select name="rs" property="zydm" styleId="zy" style="width:120px;" onchange="initBjallList();">
										<html:option value="">--��ѡ��--</html:option>
										<html:optionsCollection name="zyList" label="zymc" value="zydm"/>
									</html:select>
								</td>
								<th width="6%">�꼶</th>
								<td width="10%">
									<html:select name="rs" property="nj" styleId="nj" style="width:60px;" onchange="initBjallList();loadLdlist();">
										<html:optionsCollection name="njList" label="nj" value="nj"/>
									</html:select>
								</td>
								<th width="6%">�Ա�</th>
								<td width="10%">
									<html:select name="rs" property="xb" styleId="xb" style="width:60px;" onchange="loadLdlist();">
										<html:option value="��">��</html:option>
										<html:option value="Ů">Ů</html:option>
									</html:select>
								</td>	
								<td width="30%">
									������:<font color="red">${xynj_tjxx.num }</font>;
									�ѷ��䴲λ��Ϊ:<font color="red">${xynj_tjxx.cws }</font>
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
										<html:option value="δ����">δ����</html:option>
										<html:option value="�ѷ���">�ѷ���</html:option>
									</html:select>
								</td><%--
								<td colspan="2">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go" 
											onclick="allNotEmpThenGo('showNews.do')">
											�� ѯ
										</button>
									</div>
								</td>
							--%></tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="10">
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
					<span id="dyn_show_qs_cw_count"></span>
				</h3>
				
				<div class="con_overlfow" style="min-height: 500px;">
				<table summary="" class="dateline" align="" border="1px;" width="100%">
					<!-- ��ͷ -->
					<thead>
						<logic:notEmpty name="ldjbxx">
						<tr align="center" style="cursor:hand">
							<td>
							<input type="checkbox" name="checkbox_lddm" onclick="selectAll(this,'ld');" 
							value="<bean:write name="ldjbxx" property="lddm"/>"/>
							¥������:<bean:write name="ldjbxx" property="ldmc"/><%--
							[
								������<bean:write name="ldjbxx" property="ldcs"/>
								��������<bean:write name="ldjbxx" property="qss"/>
								��λ����<bean:write name="ldjbxx" property="cws"/>
								¥���Ա�<bean:write name="ldjbxx" property="ldxb"/>
								<font color="blue">
								δ����������/��λ����
								<bean:write name="ldjbxx" property="wfp_qss"/>/<bean:write name="ldjbxx" property="wfp_cws"/>
								</font>
							]
							--%><button type="button"  id="button_all" onclick="show_hidden_lc(this,'all');">����</button>
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
					    String bjdm=((HashMap<String,String>)request.getAttribute("rs")).get("bjdm");//��ǰѡ��İ༶
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
								������/��λ����<bean:write name="s" property="qss"/>/<bean:write name="s" property="cws"/>
								δ���䴲λ����<bean:write name="s" property="wfp_cws"/>								
								���䵱ǰ�༶��λ����<font color="red"><bean:write name="s" property="bxy_cws"/></font>
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
														<td width="20%">
															<input type="hidden" value="<%=cwxx.get("qsxb") %>"/>
															<input type="checkbox" name="checkbox_qsh" onclick="selectAll(this,'qs');" 
															    value="<%=cwxx.get("qsh")%>"/>
															<%
																if(xydm.equals(cwxx.get("qsxydm"))&&nj.equals(cwxx.get("qsnj"))){//���Ӧ
																	%><font color="green"><%
																}else if(cwxx.get("qsxydm")==null){//δ����
																	%><font color=""><%
																}else{//����Ӧ
																	%><font color="red"><%
																}
															%>
															<%=cwxx.get("qsh") %>
															[<%=cwxx.get("qsxb") %>]
															<%=cwxx.get("qsnj")==null?"":"("+cwxx.get("qsnj")+")" %><%--�꼶 --%>
															<div align="center"><%=cwxx.get("qsxymc")==null?"":cwxx.get("qsxymc") %></div><%--ѧԺ --%>
															</font>
														</td><td><table><tr>
													<%
													  qs_cw_end_mark=true;
												}
												%>
														<td width="90px">
															<input type="checkbox" name="checkbox_cwh"  onclick="selectAll(this,'cw');" 
																<%=(("to_fp".equals(act)&&cwxx.get("bjdm")!=null)||cwxx.get("xh")!=null||("to_qxfp".equals(act)&&cwxx.get("bjdm")==null))?"disabled='disabled'":""%> 
															value="<%=cwxx.get("qsh")%>_<%=cwxx.get("cwh")%>"/>
															<%
																//curr_qsxy!=null&&curr_qsxy.equals(cwxx.get("xydm"))
																   		//&&curr_qsnj!=null&&curr_qsnj.equals(cwxx.get("nj"))
																if(bjdm.equals(cwxx.get("bjdm"))){//��λѧԺ������ѧԺ��Ӧ
																	%><font color="green" ><%=cwxx.get("cwh") %><%
																}else if(cwxx.get("bjdm")==null){//δ����
																	%><font color="" ><b><%=cwxx.get("cwh") %></b><%
																}else{//����ѧԺ
																	%><font color="red" >
																	<%=cwxx.get("cwh") %>
																	<%
																}
																
															%>
															<%=cwxx.get("cwbjmc")==null?"":cwxx.get("cwbjmc")%>
															</font><br><%=cwxx.get("xm")==null?"":cwxx.get("xm") %>
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
			</div>
			<!-- ��ѯ��� end-->
			
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