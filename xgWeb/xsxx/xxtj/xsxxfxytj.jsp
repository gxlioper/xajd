<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
	<body onload="">
		<html:form action="/xsxxtj.do" method="post">			
			<div class="tab">
				<table width="100%" class="formlist" id="rsTable">
					<thead>
						<tr>
							<th>
								<sapn><bean:message key="lable.xb" />学生信息统计</sapn>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td>
							<div class="con_overlfow">
								<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
								<thead>
							      <tr>
									<logic:iterate id="tit" name="topTr" offset="0" scope="request">
										<td id="${tit.en}"
											onclick="tableSort(this)">
											${tit.cn}
										</td>
									</logic:iterate>
							      </tr>
							    </thead>
								<tbody>
							      <logic:iterate name="rs" id="s">
									<tr>
										<logic:iterate id="v" name="s" offset="0">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								  </logic:iterate>
							    </tbody>
								</table>
							</div>
						</td>
					</tr>	
					</tbody>
				</table>	
			</div>
		</html:form>
	</body>
</html>
