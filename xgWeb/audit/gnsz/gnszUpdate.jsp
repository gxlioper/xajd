<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
	</head>
	<body >
		<html:form action="/xtwhGnsz" method="post">
			<input type="hidden" name="pkValue" value="${rs.pkValue }"/>
		
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
                                    <span>审批流程信息</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button class="button2" onclick="saveData('xtwhGnsz.do?method=gnszUpdate&doType=save','')">
											保存
										</button>
										<button class="button2" onclick="Close();return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
                        <tbody>
							<tr>
								<th>
									功能名称
								</th>
								<td>
									<html:text name="rs" property="save_gnmc" readonly="true"/>
								</td>
							</tr>
							<tr>
								<th>
									流程名称
								</th>
								<td>
									<logic:iterate id="l" name="lcList" offset="1">
										<input type="radio" name="save_lcid" value="${l.dm }"/>${l.mc }<br/>
									</logic:iterate>
								</td>
							</tr>
						</tbody>
					</table>
				</div>


			<logic:notEmpty name="message">
				<script language="javascript">		
					alert('${message}');
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form></body>
</html>