<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
	
		<html:form action="/XsGyGlLogic?method=sdCbXxTj" method="post">
		
		
		
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
			
				<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th>
								<span>
								<bean:write name="nd" scope="request" />
								��
								<bean:write name="yf" scope="request" />
								��
								����ˮ�糬���¶Ȼ��ܱ�</span>
							</th>
						</tr>
					</thead>
<%--					<tfoot>--%>
<%--						<tr>--%>
<%--							<td>--%>
<%--								<div class="btn">--%>
<%--									<button onclick="expTab('rsTable','','')" name="button_print">��ӡ</button>--%>
<%--								</div>--%>
<%--							</td>--%>
<%--						</tr>--%>
<%--					</tfoot>--%>
					<tbody>
						<tr>
							<td>
								<table width="99%" class="tbstyle">
							<thead>
								<tr>
									<th>
										¥��
									</th>
									<th>
										����ˮ�����֣�
									</th>
									<th>
										����ˮ�ѣ�Ԫ��
									</th>
									<th>
										����������ȣ�
									</th>
									<th>
										�����ѣ�Ԫ��
									</th>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<logic:iterate id="v" name="s" property="list">
									<tr>
										<td nowrap align="center">
											<bean:write name="v" property="ldmc" />
										</td>
										<td nowrap>
											<bean:write name="v" property="sumsl" />
										</td>
										<td nowrap>
											<bean:write name="v" property="sumsf" />
										</td>
										<td nowrap>
											<bean:write name="v" property="sumdl" />
										</td>
										<td nowrap>
											<bean:write name="v" property="sumdf" />
										</td>
									</tr>
								</logic:iterate>
								<logic:iterate id="v" name="s" property="list2">
									<tr>
										<td nowrap align="center">
											�ϼ�
										</td>
										<td nowrap>
											<bean:write name="v" property="zsl" />
										</td>
										<td nowrap>
											<bean:write name="v" property="zsf" />
										</td>
										<td nowrap>
											<bean:write name="v" property="zdl" />
										</td>
										<td nowrap>
											<bean:write name="v" property="zdf" />
										</td>
									</tr>
								</logic:iterate>
							</logic:iterate>
						</table>
							
							</td>
						</tr>
						<tr>
							<td align="right">
								<bean:write name="xxmc" scope="request" />ָ����&nbsp;&nbsp;&nbsp;
								<br/>
								�����ˣ�&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<br/>
								�� &nbsp;&nbsp;&nbsp; �� &nbsp;&nbsp;&nbsp;
								��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</tbody>
				</table>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>


