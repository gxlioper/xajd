<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
			
		<script type="text/javascript">
			function sendInfo(){
				if(window.dialogArguments.document.getElementById('zymc')){

					window.dialogArguments.document.getElementById('xymc').value = curr_row.getElementsByTagName('input')[3].value;
					window.dialogArguments.document.getElementById('xydm').value = curr_row.getElementsByTagName('input')[2].value;
					
					window.dialogArguments.document.getElementById('zymc').value = curr_row.getElementsByTagName('input')[1].value;
					window.dialogArguments.document.getElementById('zydm').value = curr_row.getElementsByTagName('input')[0].value;

					window.dialogArguments.document.getElementById('bjmc').value = "";
					window.dialogArguments.document.getElementById('bjdm').value = "";
					
					
					Close();
				}
			}
<%----%>
<%--			function validate(){--%>
<%--				if(window.dialogArguments.document.getElementById('nj')){--%>
<%--					var nj = window.dialogArguments.document.getElementById('nj').value;--%>
<%--					if(nj != ""){--%>
<%--						$('nj').disabled = "disabled";--%>
<%--						var tmp = document.createElement("input");--%>
<%--						tmp.type = "hidden";--%>
<%--						tmp.name = "nj";--%>
<%--						tmp.value = nj;--%>
<%--						document.forms[0].appendChild(tmp);--%>
<%--					}--%>
<%--				}--%>
<%--			}--%>
		</script>
	</head>
	<body>
		<center>
			<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>רҵѡ��</a>
				</p>
			</div>
			<html:form action="/commXgInfo.do" method="post">
				<input type="hidden" name="xymc" value="${xymc }" />
				<input type="hidden" name="xydm" value="${xydm }" />
				
				<div class="searchtab">
					<table width="90%" class="" border="0">
						<tbody>
							<tr>
								<th>רҵ����</th>
								<td>
									<html:text property="zydm" styleId="zydm"></html:text>
								</td>
								<th>רҵ����</th>
								<td>
									<html:text property="zymc" styleId="zymc"></html:text>
								</td>
								<th><bean:message key="lable.xb" />����</th>
								<td>
								<html:text property="xymc" styleId="xymc"></html:text>
								</td>
							</tr>
						</tbody>
						
						<tfoot>
			        		<tr>
			          			<td colspan="6">
			            		<div class="btn">
			              		<input type="hidden" name="go" value="a" />
			              		<button type="button" id="search_go" 
			              		onclick="document.forms[0].go.value='go';refreshForm('/xgxt/commXgInfo.do?method=choiceZy');this.disabled=true;">
			              			��ѯ
			              		</button>
			             		 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
			              			����
			             		 </button>
			            		</div>
			          		</td>
			       			</tr>
			     		</tfoot>
					</table>
					</div>
					
				<div class="formbox">
					<logic:empty name="rs">
					    <h3 class="datetitle_01">
					    <span>
					    	��ѯ���&nbsp;&nbsp;
								<font color="red">δ�ҵ��κμ�¼��</font> 
					    </span>
					    </h3>
					 </logic:empty>
					<logic:notEmpty name="rs">
						<h3 class="datetitle_01">
							<span>
								��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font> 
							</span>
						</h3>
						<table width="100%" id="rsTable" class="dateline">
							<thead>
								<tr align="center" style="cursor:hand">
									<td onclick="tableSort(this)">
										רҵ����
									</td>
									<td onclick="tableSort(this)">
										רҵ����
									</td>
									<td onclick="tableSort(this)">
										<bean:message key="lable.xb" />����
									</td>
									<td onclick="tableSort(this)">
										<bean:message key="lable.xb" />����
									</td>
								</tr>
							</thead>
							<tbody>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this)" style="cursor:hand"
									ondblclick="sendInfo();">
									<logic:iterate id="v" name="s" offset="0">
										<td align="left">
											<input type="hidden" value="${v }"/>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						
						<!--��ҳ��ʾ-->
					     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=commForm"></jsp:include>
						<!--��ҳ��ʾ-->
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
				</logic:notEmpty>
				</div>
			</html:form>
		</center>
	</body>
</html>
