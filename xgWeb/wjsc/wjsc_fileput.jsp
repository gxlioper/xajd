<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<script language="javascript" src="js/wjsc.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript">
		     function refreshtheweb()
			  {
				document.forms[0].action = "wjffManage.do?method=fileQuery&doType=query";
	            document.forms[0].submit();
			  }
			function reWrite(){
				var inputs = document.getElementsByTagName('input');
				var textareas = document.getElementsByTagName('textarea');
				for(var i=0;i<inputs.length;i++){
					inputs[i].value='';
				}
				for(var j=0;j<textareas.length;j++){
					textareas[j].innerText='';
				}
			}
		
			function fileExpData(){
				document.forms[0].action = '/xgxt/fileExpData.do?act=fileExpData';
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		</script>
		<html:form action="/wjffManage" method="post"
			enctype="multipart/form-data">
			<input type="hidden" value="" id="yczdm"/>
			<input type="hidden" value="" id="yhms" name="yhms"/>
			<input type="hidden" value="" id="zdms" name="zdms"/>
			<input type="hidden" value="" id="zmcs" name="zmcs"/>
			<input type="hidden" value="view_scwjxx" id="tableName" name="tableName"/>
			<input type="hidden" value="wjsc_scwjxxb" id="realTable" name="realTable"/>
			<input type="hidden" value="fileManager.do" id="path"/>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="#"
									onclick="showTopWin('wjffManage.do?method=wjffAdd',750,500)"
									class="btn_zj"> ���� </a>
							</li>
							<li>
								<a href="#"
									onclick="showUpdateWindow('primarykey_cbv','wjffManage.do?method=wjffAdd&doType=update',750,500)"
									class="btn_xg"> �޸� </a>
							</li>
							<li>
								<a href="#"
									onclick="deletedata('wjffManage.do?method=wjffDel')"
									class="btn_sc"> ɾ�� </a>
							</li>
							<li><a href="#" onclick="fileExpData()" class="btn_dc"> ���� </a></li>
							<!-- 
							<li>
								<a href="#" class="btn_dc"
									onclick="configureExportData();return false;">����</a>
							</li>
							<li><a href="#" class="btn_qx"
								onclick="choiceFields();return false;">������ȷ��</a></li>
							 -->
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="refreshtheweb()">
											�� ѯ
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>�ļ���</th>
								<td>
									<html:text property="wjh" ></html:text>
								</td>
								<th>�ļ���</th>
								<td>
									<html:text property="wjm" ></html:text>
								</td>
								<th>������</th>
								<td>
									<html:text property="ffr" ></html:text>
								</td>
							</tr>
							<tr>
								<th>����ʱ��</th>
								<td colspan="5">
									<html:text property="wjffKssj" 
										styleClass="jssj"
										onclick="return showCalendar(this.id,'y-MM-dd');"
										readonly="true"
										styleId="wjffKssj"
									></html:text>
									��
									<html:text property="wjffJssj"
										styleClass="jssj"
										onclick="return showCalendar(this.id,'y-MM-dd');"
										readonly="true"
										styleId="wjffJssj"
									></html:text>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	�ļ��б�
			    </span>
			    </h3>
				
					 <table summary="" class="dateline" align="" width="100%">
			   			<thead>
						<tr bgcolor="#D0E0EE">
							<td>
								<input type="checkbox" disabled="true"/>
							</td>
							<td>
								�ļ���
							</td>
							<td>
								�ļ���
							</td>
							<td>
								���Ų���
							</td>
							<td>
								������
							</td>
							<td>
								����ʱ��
							</td>
							<td>
								��ִ
							</td>
						</tr>
						</thead>
						<tbody>
						<logic:notEmpty name="rs">
							<logic:iterate id="list" name="rs">
								<tr onmouseover="rowOnClick(this)" >
									<td>
										<input type="checkbox" value="${list.wjh }" name="primarykey_cbv"/>
									</td>
									<td align="left">
										<a
											href="fileView.do?wjh=<bean:write name="list" property="wjh"/>"
											target="_blank" style="color:blue"> <bean:write name="list" property="wjh" />
										</a>
	
										<logic:equal name="list" property="newmark" value="new">
											<img style="width:30px" src="images/newmark.gif" />
										</logic:equal>
									</td>
									<td align="left">
										<bean:write name="list" property="wjm" />
									</td>
									<td>
										<bean:write name="list" property="bmmc" />
									</td>
									<td>
										<bean:write name="list" property="ffr" />
									</td>
									<td>
										<bean:write name="list" property="wjffsj" />
									</td>
									<td>
										<a href="fileView.do?wjh=<bean:write name="list" property="wjh"/>&huizhi=1" target="_blank">��ִ</a>
									</td>
								</tr>
							</logic:iterate>
								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
								<%
								}
								%>
							
						</logic:notEmpty>
						<logic:empty name="rs">
							<%
										for (int i = 0; i < (Integer) request
										.getAttribute("maxNum"); i++) {
							%>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
								</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
								<td>&nbsp;</td>
							</tr>
							<%
							}
							%>
						</logic:empty>
						
						</tbody>
					</table>
				   <!--��ҳ��ʾ-->
			      	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
			  	  	<!--��ҳ��ʾ-->	
				
				</div><br><br>
				 
				
		</html:form>
	</body>
	<logic:notEmpty name="result">
		<logic:equal name="result" value="true">
		<script type="text/javascript">
			alert("�����ɹ���");
		</script>
		</logic:equal>
		<logic:notEqual name="result" value="true">
		<script type="text/javascript">
			alert("����ʧ�ܣ�");
		</script>
		</logic:notEqual>
	</logic:notEmpty>
</html>
