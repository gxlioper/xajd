<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/szdw_bjxwjltj" method="post" styleId="bjxwjltjForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>�꼶</th>
							<td>${bjxx.nj}</td>
							<th>�༶</th>
							<td>${bjxx.bjmc}</td>
						</tr>
						<tr>
							<th><bean:message key="lable.xb" /></th>
							<td>${bjxx.xymc}</td>
							<th>רҵ  </th>
							<td>${bjxx.zymc}</td>
						</tr>
						<tr>
							<th>����Ա</th>
							<td>${bjxx.fdylbxx}</td>
							<th>У�ڵ�ʦ</th>
							<td>${bjxx.bzrlbxx}</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ɲ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>��ɲ�</th>
							<td colspan="3">
								<table width="100%" >
									<tbody>
										<logic:empty name="gbxx">
											<td colspan="2" align="center" >
												������Ϣ!
											</td>
										</logic:empty>
										<logic:notEmpty name="gbxx">
											<logic:iterate id="r" name="gbxx">
												<tr>
													<td>${r.zwmc}</td>
													<td>${r.xm}</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ϊ��¼��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>����Ա��¼</th>
							<td colspan="3">
								<table  width="100%" >
								<thead>
									<tr>
										<td  width="25%">��¼��</td>
										<td  width="55%">����</td>
										<td  width="20%">��¼ʱ��</td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<logic:empty name="fdyjlxx">
											<td colspan="3" align="center" >
												������Ϣ!
											</td>
										</logic:empty>
										<logic:notEmpty name="fdyjlxx">
											<logic:iterate id="r" name="fdyjlxx">
												<tr>
													<td width="25%">${r.xm}</td>
													<td  width="55%">${r.jlnr}</td>
													<td  width="20%">${r.jlsj}</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tr>
								</tbody>
							</table>
							</td>
						</tr>
						<tr>
							<th>У�ڵ�ʦ</th>
							<td colspan="3">
								<table  width="100%" >
								<thead>
									<tr>
										<td  width="25%">��¼��</td>
										<td  width="55%">����</td>
										<td  width="20%">��¼ʱ��</td>
									</tr>
								</thead>
								<tbody>
									<tr>
										<logic:empty name="bzrjlxx">
											<td colspan="3" align="center" >
												������Ϣ!
											</td>
										</logic:empty>
										<logic:notEmpty name="bzrjlxx">
											<logic:iterate id="r" name="bzrjlxx">
												<tr>
													<td  width="25%">${r.xm}</td>
													<td  width="55%">${r.jlnr}</td>
													<td  width="20%">${r.jlsj}</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</tr>
								</tbody>
							</table>
							</td>
						</tr>
					</tbody>
					</table>
					
					<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="�� ��" onclick="iFClose();">
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

