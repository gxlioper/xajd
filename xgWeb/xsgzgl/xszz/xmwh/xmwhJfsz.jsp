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
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ������ĿΪ��<font color="red">${xmwhForm.xmmc}</font>
					<span id="spztTip" style="display:none;">
						<br/>
						<font color="red">��ǰ��Ŀ����ѧ�����룬�������޸�</font>
					</span>
				</p>
				<p>
					ֻ������ͬ�����֮�����Ŀ�໥����
				</p>
				<p>
					������������Ŀ���ɹ�ѡ
				</p>
				<p>
					�ѹ�ѡ��ĿΪ���Ѻϲ�ʹ�õ���Ŀ
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
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
											<th>ѧԺ</th>
											<th>���</th>
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
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
										�� ��
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

