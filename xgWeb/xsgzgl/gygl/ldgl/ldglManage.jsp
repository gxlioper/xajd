<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript">
		
		function addLdsj(){
			var url="gyglnew_ldgl.do?method=ldxxwh&doType=add";
			showDialog("����¥����Ϣ", 760, 505, url);
		}

		function chec_page(){
		      for(i=0;i<document.getElementsByName("checkVal").length;i++){
		    	if(document.getElementsByName("checkVal")[i].disabled == false){
		    		document.getElementsByName("checkVal")[i].checked=document.getElementsByName("all")[0].checked;
		    	}
		      }
		}
	
		
		//�޸�
		function modiLdsj(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			
			//if(n>1){
				//viewTempDiv("������Ϣ����","plUpdate",410,245);
			//}else 
			if(n==1 || curr_row){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				pkValue = encodeURIComponent(pkValue);//�����ַ�URL����
				var url = 'gyglnew_ldgl.do?method=ldxxwh&doType=update&lddm='+pkValue;
				showDialog("�޸�¥����Ϣ", 760, 505, url);
				
			}else if (null == curr_row) {
					alertInfo('��ѡ��һ��');
					return false;
			}
		}

		function delLdsj(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				confirmInfo("ȷ��Ҫɾ��ѡ�еļ�¼��?",function(ok){
					if(ok=="ok"){
						var mklx=$("mklx").value;
						var url="gyglnew_ldgl.do?method=ldglManage";
						url+="&doType=delete";
						refreshForm(url);
						hiddenMessage(true,true);
						BatAlert.showTips('���ڲ��������Ե�...');						
					}
				});
			}else{
				alertInfo("û����Ҫɾ���ļ�¼!");
				return false;
			}
		}
		
		function fpLdsj(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			var str = "";
			
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
					str+="&checkVal="+encodeURIComponent(pkV[i].value);//�����ַ�URL����
				}
			}
			if(blog){
				var url="gyglnew_ldgl.do?method=ldfpUpdateNew";
				url+="&doType=fp"+str;
				
				showDialog("���乫Ԣ����Ա", 760, 525, url);
				hiddenMessage(true,true);
			}else{
				alertInfo("û����Ҫ����ļ�¼!");
				return false;
			}
		}
		function fpLdsj_12861(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			var str = "";
			
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
					str+="&checkVal="+encodeURIComponent(pkV[i].value);//�����ַ�URL����
				}
			}
			if(blog){
				var url="gyglnew_ldgl.do?method=ldfpUpdate_12861";
				url+="&doType=fp"+str;
				
				showDialog("���乫Ԣ����Ա", 760, 525, url);
				hiddenMessage(true,true);
			}else{
				alertInfo("û����Ҫ����ļ�¼!");
				return false;
			}
		}
		
		function searchRs(){
			allNotEmpThenGo('gyglnew_ldgl_ldgl.do');
		}

		function modi(url,h,w){
			if(curr_row != null){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				pkValue = encodeURIComponent(pkValue);//�����ַ�URL����
				showDialog("�鿴¥����Ϣ",h,w,url + '&pkValue='+pkValue);
				return true;
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}

		//��������ά��
		function qsplwh(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			
			if(n==1 || curr_row){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var url = 'gyglnew_ldgl.do?method=ldglQsxxplwh&lddm='+pkValue;
				showOpenWindow(url,1024,768);
			}else if (null == curr_row) {
					alertInfo('��ѡ��һ��');
					return false;
			}
		}
		
		function ldxxglExportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport("gyglnew_ldgl_ldgl.do", ldxxglExportData);
		}
			
		
			
		// ��������
		function ldxxglExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyglnew_ldgl.do?method=ldxxglExportData&dcclbh=" + "gyglnew_ldgl_ldgl.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>

		<html:form action="/gyglnew_ldgl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="text" id="nouse" style="display: none"/>
			<!-- ������ -->
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help" >
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
			
			<!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��¥���еĴ�λ�ѷ��䡢����ס����������Ϣʱ��¥������ɾ��
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<!-- ģ������ -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>						
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="addLdsj();return false;" class="btn_zj"> ���� </a></li>
						<li><a href="#" onclick="modiLdsj();return false;" class="btn_xg"> �޸� </a></li>
						<li><a href="#" onclick="delLdsj();return false;" class="btn_sc"> ɾ�� </a></li>
						<%-- 
						<li><a href="#" onclick="qsplwh();return false;" class="btn_sq"> ��������ά�� </a></li>
						--%>
						<li><a href="#" onclick="fpLdsj();return false;" class="btn_sq"> ���乫Ԣ����Ա </a></li>
						<logic:equal name="xxdm" value="12861">
							<li><a href="#" onclick="fpLdsj_12861();return false;" class="btn_sq"> ���乫Ԣ����Ա </a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">��������</a></li>--%>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">��������</a></li>
						--%>
						<li><a href="#" class="btn_dc" onclick="ldxxglExportConfig();return false;">����</a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="blue">���ɹ�ѡ��¥��Ϊ���д�λ�������ס</font>
					</span>
				</h3>

				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec_page()" />

								</td>
								<logic:iterate id="tit" name="topTr"   indexId="index">
									<td>
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<% String xxdm = (String) request.getAttribute("xxdm"); %>
							 <% if("12861".equals(xxdm)){ %>
								 <logic:iterate name="rsList" id="s" indexId="index">	
								<%int num = Integer.valueOf(request.getAttribute("colnum").toString())-3;%>					
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="modi('gyglnew_xxtj.do?method=xxtjDetail',1024,505);">
										<td>
											<input type="checkbox" name="checkVal" id="pkV" 
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											/>
										</td>
										<logic:iterate id="v" name="s" offset="0" length="<%=String.valueOf(num)%>">
											<td nowrap>
												<bean:write name="v"/>
											</td>
										</logic:iterate>
										<td title="<%=((String[])s)[num+1]==null?"":((String[])s)[num+1] %>">
											<%
												String[] strs=(String[])s;
												%><%=strs[num]%><%
												num=num+2;
											%>
										</td>
										<td title="<%=((String[])s)[num+1]==null?"":((String[])s)[num+1] %>">
											<%
												%><%=strs[num]%><%
												num=num+2;
											%>
										</td>
										<td title="<%=((String[])s)[num]==null?"":((String[])s)[num].replace("\r","").replace("\n","") %>">
											<%
												if(strs[num]!=null&&strs[num].length()>10){
													%><%=strs[num].substring(0,10)%>...<%
												}else{
													%><%=strs[num]%><%
												}
											%>
										</td>
									</tr>
								</logic:iterate>
							 <% }else{ %>
								<logic:iterate name="rsList" id="s" indexId="index">	
								<%int num = Integer.valueOf(request.getAttribute("colnum").toString())-2;%>					
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="modi('gyglnew_xxtj.do?method=xxtjDetail',1024,505);">
										<td>
											<input type="checkbox" name="checkVal" id="pkV" 
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											/>
										</td>
										<logic:iterate id="v" name="s" offset="0" length="<%=String.valueOf(num)%>">
											<td nowrap>
												<bean:write name="v"/>
											</td>
										</logic:iterate>
										<td title="<%=((String[])s)[num+1]==null?"":((String[])s)[num+1] %>">
											<%
												String[] strs=(String[])s;
												%><%=strs[num]%><%
												num=num+2;
											%>
										</td>
										<td title="<%=((String[])s)[num]==null?"":((String[])s)[num].replace("\r","").replace("\n","") %>">
											<%
												if(strs[num]!=null&&strs[num].length()>10){
													%><%=strs[num].substring(0,10)%>...<%
												}else{
													%><%=strs[num]%><%
												}
											%>
										</td>
									</tr>
								</logic:iterate>
							<% } %>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("����ʧ�ܣ��ѷ��������ס��¥������ɾ��!");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("�����ɹ���");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
