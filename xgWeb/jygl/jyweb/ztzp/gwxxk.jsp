<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript">
			function saveTempData(){
				var tempDwmc = "";
				var tempGwValue = "";
				var gwList = $('yxz').options;
				var tempHtml = "<table style='width:100%'><thead><tr><td>��λ����</td><td>רҵ/��λ����</td></tr></thead><tbody>";

				for(var i = 0 ; i < gwList.length ; i++){
					var tempGwxx = gwList[i].text.split("(");
					tempHtml+="<tr><td>";
					tempHtml+=tempGwxx[1].replace(")","");
					tempHtml+="</td><td>";
					tempHtml+=tempGwxx[0];
					tempHtml+="</td></tr>";

					tempDwmc+=tempGwxx[1].replace(")","");
					tempGwValue+=gwList[i].value;
					if (i != gwList.length-1){
						tempDwmc+="!!@!!";
						tempGwValue+="!!@!!";
					}
					
				} 
				tempHtml+="</tbody></table>";
				
				window.dialogArguments.document.getElementById('gwxx').innerHTML = tempHtml;
				window.dialogArguments.document.getElementById('tempDwmc').value = tempDwmc;
				window.dialogArguments.document.getElementById('tempGwValue').value = tempGwValue;
				
				//������ҳ��߶�
				var obj = window.dialogArguments.parent.document.body.document.getElementById("mainFrame");
				obj.style.height = obj.contentWindow.document.body.scrollHeight;
				
				window.close();
			}
		</script>
	</head>
	<body>
		<html:form action="/jyweb">
			<div style="width: 600px; margin: 5px auto;">
				<table class="fieldlist" width="580">
					<tr>
						<td>
							��λ����
							<html:text property="dwmc"></html:text>
						</td>
						<td>
							��λ����
							<html:text property="zpzw"></html:text>
						</td>
						<td>
							<div class="btn" style="float: left">
								<button onclick="allNotEmpThenGo('/xgxt/jyweb.do?method=gwxxk')">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>
				</table>
				<table class="fieldlist" width="580">
					<tbody>
						<tr>
							<td colspan="3">
								<font color="red">ע��</font>������ʾ��ȫ��Ϊ<font color="red">��Ч���ڲ������ͨ��</font>����Ƹ��Ϣ
								
							</td>
						</tr>
						<tr>
							<td width="45%">
								<div class=tab_box style="height: 400px; width: 240px">
									<h3>
										��ѡ��
									</h3>
									<div class="CNLTreeMenu2" id="CNLTreeMenu2"
										style="width: 225px">
										<ul>
											<logic:iterate id="s" name="dwList">
												<li>
													<a href="#">
														<input type="checkbox" value="${s.gsmc }"  onclick="selectSubNode(this)"/>${s.gsmc }</a>
													<ul>
														<logic:iterate id="m" name="gwList">
															<logic:equal value="${s.gsmc }" name="m" property="gsmc">
																<li class="Child">
																	<input name="gwdm" type="checkbox"
																		value="${m.zpzwmc }!!@!!${m.zpzw }${m.gsmc}${m.fbsj}!!@!!${s.gsmc }" />
																	${m.zpzwmc }
																</li>
															</logic:equal>
														</logic:iterate>
													</ul>
												</li>
											</logic:iterate>
										</ul>
									</div>
								</div>
								<script type="text/javascript">
								<!--
								var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu2","li");
								MyCNLTreeMenu1.InitCss("Opened","Closed","Child","<%=stylePath %>images/s.gif");
								-->
								</script>
							</td>
							<td width="10%">
								<div class="btn_choose">
									<button class="allright" onclick="allRight();"></button>
									<br />
									<button class="right" onclick="turnRight();"></button>
									<br />
									<button class="left" onclick="turnLeft();"></button>
									<br />
									<button class="allleft" onclick="allleft();"></button>
								</div>
							</td>
							<td width="45%">
								<div class="tab_box" style="height: 400px; width: 240px">
									<select name="yxz" id="yxz" class="selectlist" size="26"
										style="width: 100%" onmouseover="null">

									</select>
								</div>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="3">
								<div class="btn">
									<button onclick="showTopWin('jywebUseCheckSession.do?method=workAdd&doType=add','700','500');">����</button>
									<button onclick="saveTempData();">����</button>
									<button onclick="window.close();return false;">�ر�</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>