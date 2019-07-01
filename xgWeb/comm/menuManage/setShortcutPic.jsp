<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			
			function setPicPath(obj){
				window.dialogArguments.document.getElementById('path').value = obj.value;
				window.dialogArguments.document.getElementById('picpath').src = stylePath+"/images/blue/54/"+obj.value;
			}
			
		</script>
	</head>
	<body>
		<html:form action="/menuManage" method="post">
			<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }" />
			
			
			<html:hidden property="save_gnmk" styleId="path"/>
			<button type="button" class="hide" id="search_go"
					onclick="allNotEmpThenGo('/xgxt/menuManage.do?method=setShortcutPic')">
			</button>

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>快捷方式图片设置</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button type="button" name="保存" onclick="window.close();return false;">
											确定
										</button>
									</logic:notEqual>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<%
							List<String[]> picList = (List<String[]>) request.getAttribute("rs");

							for (int i = 0; i < picList.size(); i++) {

								if (i != 0 && i % 4 == 0) {
						%>
						</tr>
						<%
								}
								if (i % 4 == 0) {
						%>
						<tr>
						<%
						}
						%>
						<th>
							<img src="<%=stylePath%>/images/blue/54/<%=picList.get(i)[0]%>" />
							<html:radio property="save_picpath" value="<%=picList.get(i)[0]%>" onclick="setPicPath(this)"></html:radio>
						</th>
						<%
							if (picList.size()%4 !=0  && i == picList.size()-1){
								for (int j = 0 ; j < 4 - picList.size()%4 ; j++){
								%>
									<th></th>
								<%
								}
								%>
								</tr>
								<%
							}
						
						}
						%>
						<tr>
							<td colspan="4">
								<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=menuManageForm"></jsp:include>
								<script type="text/javascript">
									$('choose').className="hide";
								</script>
							</td>
						</tr>
			
					</tbody>	
				</table>
			</div>
			<logic:present name="message">
				<script defer="defer">
				    alert("${message}");
				    if (window.dialogArguments) {
						window.close();
					}
				 </script>
			</logic:present>
		</html:form>
	</body>
</html>
