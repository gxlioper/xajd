<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<style>
			#xmList tr{ height:40px; }	
			#xmList td{ width:100px; }	
		</style>
		<script type="text/javascript">
			function saveForm(){
				
				var url = "xszz_xmwh.do?method=saveJfsz";
				
				ajaxSubFormWithFun("jfszForm",url,function(data){
					showAlertDivLayer(data["message"],{},{"clkFun":function(){
						refershParent();
					}});
				});
			}
		</script>
	</head>
	<body>
		<html:form action="/xszz_xmwh" method="post" styleId="jfszForm">
			<html:hidden property="xmdm" styleId="xmdm" />
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前设置项目为：<font color="red">${xmwhForm.xmmc}</font>
					<span id="spztTip" style="display:none;">
						<br/>
						<font color="red">当前项目已有学生申请，不允许修改</font>
					</span>
				</p>
				<p>
					只允许相同审核流之间的项目相互设置
				</p>
				<p>
					经费已设置项目不可勾选
				</p>
				<p>
					已勾选项目为经费合并使用的项目
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:360px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="dateline">
					<tbody id="xmList">
						<%
							List<HashMap<String,String>> others = (List<HashMap<String,String>>)request.getAttribute("others");
						
							for (int i = 0 , j = others.size() ; i < j ; i++){
								
								if(i%3 == 0){
									%>
									<tr>
									<%
								}
								String xmdm = others.get(i).get("xmdm");
								request.setAttribute("xmdm",xmdm);
									%>
								<td>
									<input type='checkbox' value='<%=xmdm %>'
										<logic:iterate id="s" name="sameGroupList">
											<logic:equal value="${s.xmdm}" name="xmdm" >
												checked="true"
											</logic:equal>
										</logic:iterate>
									<% 
									 if("1".equals(others.get(i).get("fzqk"))){
										 %>
										disabled="true"
										<% 
									 }
									 %>
									 name='groupXmdm'/>
									<%=others.get(i).get("xmmc") %>
								</td>
								
								<%
								if((i+1)%3 == 0){
									%>
									</tr>
									<%
								}
							}
						%>
					</tbody>
					<tbody>
						<tr>
							<td colspan="3">
								<table style="width:100%;">
									<thead>
										<tr>
											<th>学院</th>
											<th>金额</th>
										</tr>
									</thead>
									<tbody>
										<logic:iterate id="xy" name="xyList">
											<tr>
												<td>${xy.xymc }</td>
												<td>
													<input type="hidden" name="xydm" value="${xy.xydm }"/>
													<input type="text" name="je" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'')"
													value="<bean:write name="xmjfMap" property="${xy.xydm }"/>"/>
												</td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" id="saveBtn" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

