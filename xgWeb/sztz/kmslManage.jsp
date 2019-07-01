<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
	</head>

	<script type="text/javascript">
		function delkm(){
			var pkValues = document.getElementsByName('primarykey_cbv');
			var pkValue ="";
			for (var i = 0 ; i < pkValues.length ; i++){
				if (pkValues[i].checked){
					pkValue +=pkValues[i].value+"!@!";
				}
			}
			if(pkValue ==""){
				alert("������ѡ��һ����¼��");
				return false;
				}else{
					jQuery.post('sztz.do?method=checkDelKmdm',
							{pkValue:pkValue},
							function(data){
							if(data==false){
								alertError("��ʹ�õĿ�Ŀ����ɾ����");
								return false;
								}else{
									batchData('primarykey_cbv','sztz.do?method=kmslDel','del')
									}
						},'json')
					}
     			
		
			//batchData('primarykey_cbv','sztz.do?method=kmslDel','del')
		}
	</script>


	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		
		<input type="hidden" id="realTable" value=""/>
		
		<html:form action="/sztz" method="post">
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href='#'
								   class="btn_zj"
								   onclick="showTopWin('sztz.do?method=kmslUpdate',600,450)"
								   id="btn_zj">����</a>
							</li>
							<li>
								<a href="#" 
								   class="btn_xg"
								   onclick="showUpdateWindow('primarykey_cbv','sztz.do?method=kmslView',600,450)"
								   id="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" 
								   class="btn_sc"
								   onclick="delkm()"
								   id="btn_sc">ɾ��</a>
							</li>
							<li>
								<a href="#"
									id="btn_dr"
									onclick="jQuery('#realTable').val('xg_sztz_kmdmb');impAndChkData()"
									class="btn_dr"> �����Ŀ </a>
							</li>
							<li>
								<a href="#"
									id="btn_dr"
									onclick="jQuery('#realTable').val('xg_sztz_hxnlb');impAndChkData()"
									class="btn_dr"> ����������� </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>��Ŀ����</th>
								<td>
									<html:text property="querylike_kmdm"></html:text>
								</td>
								<th>��Ŀ����</th>
								<td>
									<html:text property="querylike_kmmc"></html:text>
								</td>
								<td colspan="2">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('sztz.do?method=kmslManage&doType=query')">
											�� ѯ
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������</font>
						</logic:notEmpty>
					</span>
				</h3>
				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v }
											</td>
										</logic:iterate>
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
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
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
									<logic:iterate id="t" name="topTr" offset="0" scope="request">
										<td>
											&nbsp;
										</td>
									</logic:iterate>
								</tr>
								<%
								}
								%>
							</logic:empty>
						</tbody>
					</table>
				</div>

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=sztzActionForm"></jsp:include>
			</div>
			
		</html:form>
	</body>
</html>
