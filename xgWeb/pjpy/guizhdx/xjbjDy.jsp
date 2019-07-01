<%@ page language="java" contentType="text/html;charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html>
<base target="_self">
<head>
	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" http-equiv="no-cache" />
	<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
	<meta http-equiv="Expires" http-equiv="0" />
	<meta name="Copyright" content="������� zfsoft" />
</head>
<%
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
%>
<link rel="icon" href="images/icon.ico" type="image/x-icon" />
<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
	type="text/css" media="all" />
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
</script>
<body>
	<html:form action="/zzlgdx_rcsw" method="post">
		<table width="100%" border="0" id="theTable">
			<tr height="930px">
				<td scope="col">
					<div align="center">
						&nbsp;<p/>
						&nbsp;<p/>
						<h1>
							��&nbsp;��&nbsp;��&nbsp;��&nbsp;��<p/>��<p/>��<p/>��
						</h1>
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
							<table border="0">
								<tr>
									<td><h3><bean:message key="lable.xb" /></h3></td>
									<td><h4><u>&nbsp;${rs.xymc }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
									<td><h3>רҵ</h3></td>
									<td><h4><u>&nbsp;&nbsp;${rs.zymc }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
								</tr>
								<tr>
									<td><h3>�༶</h3></td>
									<td><h4><u>&nbsp;${rs.bjmc }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
									<td><h3>���</h3></td>
									<td><h4><u>&nbsp;${rs.nd }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
								</tr>
								<tr>
									<td><h3>�꼶</h3></td>
									<td><h4><u>&nbsp;${rs.nj }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u></h4></td>
									<td><h3>����ʱ��</h3></td>
									<td><h4><u>&nbsp;${rs.sqsj }&nbsp;&nbsp; <logic:empty name="rs">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:empty></u> </h4></td>
								</tr>
							</table>
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<h2>��&nbsp;&nbsp;��&nbsp;&nbsp;��&nbsp;&nbsp;ѧ</h2>
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
						<p/>&nbsp;
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<br />
					<br />
					&nbsp;
				</td>
			</tr>
			<tr>
				<td scope="col">
					<table width="90%" class="tbstyle" align="center">
						<tr height="60px">
							<td width="23%" align="center">
									����������
							</td>
							<td width="23%" align="center">
									${rs.bzrxm }
							</td>
							<td width="23%" align="center">
									�༶��Ա��
							</td>
							<td width="23%" align="center">
									${rs.tyrs }
							</td>
						</tr>
						<tr height="60px">
							<td align="center">
									�༶����
							</td>
							<td align="center">
									${rs.bjrs }
							</td>
							<td align="center">
									�༶��Ա��
							</td>
							<td align="center">
								${rs.dyrs }
							</td>
						</tr>
						<tr height="60px">
							<td align="center">
									���ΰ༶����ѧ���ɲ�����
							</td>
							<td align="center">
									${rs.gbrs }
							</td>
							<td align="center">
									���2000Ԫ�����������
							</td>
							<td align="center">
								${rs.zzqk }
							</td>
						</tr>
						<tr height="85px">
							<td align="center">
									��ѧ���ܴ������
							</td>
							<td align="center" colspan="3">
									<logic:empty name="cfList">
										&nbsp;
									</logic:empty>
									<logic:notEmpty name="cfList">
										<input type="hidden" id="cfqk" value="0">
										<table width="90%" id="rsTable" class="tbstyle">
											<tr style="cursor:hand">
												<td>ѧ��</td>
												<td>����</td>
												<td>ѧ��</td>
												<td>ѧ��</td>
												<td>�������</td>
												<td>����ԭ��</td>
												<td>����ʱ��</td>
												<td>�����ĺ�</td>
											</tr>
										<logic:iterate id="s" name="cfList">
											<tr style="cursor:hand">
												<logic:iterate id="v" name="s">
													<td>
														<bean:write name="v"/>
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>
										</table>
									</logic:notEmpty>
							</td>
						</tr>
						<tr height="85px">
							<td align="center">
								��ѧ�ڲ����ʲ����˴�
							</td>
							<td colspan="3">
								<logic:empty name="bkxx">
									&nbsp;
								</logic:empty>
								<logic:notEmpty name="bkxx">
									<table width="90%" id="rsTable" class="tbstyle">
										<tr style="cursor:hand">
											<td>ѧ��</td>
											<td>ѧ��</td>
											<td>�༶����</td>
											<td>��������</td>
											<td>������</td>
										</tr>
										<logic:iterate id="s" name="bkxx">
											<tr style="cursor:hand">
												<td>
													<bean:write property="xn" name="s"/>
												</td>
												<td>
													<bean:write property="xq" name="s"/>
												</td>
												<td>
													<bean:write property="bjrs" name="s"/>
												</td>
												<td>
													<bean:write property="rs" name="s"/>
												</td>
												<td>
													<bean:write property="bkl" name="s"/>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</logic:notEmpty>
							</td>
						</tr>
						<tr height="85px">
							<td align="center">
								УԺ�������ұ���
							</td>
							<td colspan="3">
								${rs.wmqs }
							</td>
						</tr>
						<tr height="85px">
							<td align="center">
								Ƿ��ѧ�����������
							</td>
							<td colspan="3">
								${rs.jfqk}
							</td>
						</tr>
						<tr>
							<td align="center">
								�뵳���뵳�������ӵ�����������
							</td>
							<td colspan="3">
								�뵳������${rs.dyrs }<br/>
								�뵳����������${rs.dybl }<br/>
								�뵳��������������${rs.jjfzrs } <br/>
								�뵳�������ӱ�����${rs.jjfzbl }
							</td>
						</tr>
						<tr height="80px">
							<td align="center">
								����������������
							</td>
							<td colspan="3">
								${rs.dyqk}
							</td>
						</tr>
						<tr height="280px">
							<td align="center">
								��ѧ���ܸ��ֽ�����������Ƽ�������
							</td>
							<td colspan="3">
								<logic:empty name="pjpyxx">
									&nbsp;
								</logic:empty>
								<logic:notEmpty name="pjpyxx">
									<table width="90%" id="rsTable" class="tbstyle">
											<tr style="cursor:hand">
												<td>ѧ��</td>
												<td>�༶</td>
												<td>�񽱽���</td>
												<td>������</td>
											</tr>
											<logic:iterate id="s" name="pjpyxx">
												<tr style="cursor:hand">
													<logic:iterate id="v" name="s">
														<td>
															<bean:write name="v"/>
														</td>
													</logic:iterate>
												</tr>
											</logic:iterate>
										</table>
								</logic:notEmpty>
							</td>
						</tr>
					</table><br/>
					
					<table width="90%" class="tbstyle" align="center" >
						<tr height="945px">
							<td>
								<div align="center"  style="height:10px" >�༶��Ҫ�¼�</div>
								<logic:equal value="" property="zysj" name="rs">
									<p style="height:900px"/>
								</logic:equal>
								<logic:notEqual value="" property="zysj" name="rs">
								<p style="height:900px" align="left">
									&nbsp;&nbsp;&nbsp;&nbsp;${rs.zysj }
								</p>
								</logic:notEqual>
							</td>
						</tr>
					</table>
					<br/>
					<table width="90%" class="tbstyle" align="center">
						<tr height="945px">
							<td>
								<div align="center">�༶��Ҫ�¼�</div>
								<p style="height:900px" align="center"/>
							</td>
						</tr>
					</table>
					<br/>
					<table width="90%" class="tbstyle" align="center">
						<tr height="280px">
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr height="140px">
							<td rowspan="2" width="15%" align="center">������<br/><br/>����Ա<br/><br/>��&nbsp;&nbsp;  ��<br/><br/>�� &nbsp;&nbsp; ��</td>
							<td>
								<p style="height:100px">
									&nbsp;&nbsp; &nbsp; &nbsp;${rs.bzryj }
								</p>
								<p align="right">
									 ǩ�� &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;      ��&nbsp;&nbsp; &nbsp; &nbsp;     �� &nbsp;&nbsp; &nbsp; &nbsp;    ��&nbsp;&nbsp; &nbsp; &nbsp; 
								</p>
							</td>
						</tr>
						<tr height="140px">
							<td>
								<p style="height:100px">
								&nbsp;
								</p>
								<p align="right">
									 ǩ�� &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp;    &nbsp;&nbsp; &nbsp; &nbsp;   ��&nbsp;&nbsp; &nbsp; &nbsp;     �� &nbsp;&nbsp; &nbsp; &nbsp;    ��&nbsp;&nbsp; &nbsp; &nbsp; 
								</p>
							</td>
						</tr>
						<tr height="150px">
							<td align="center">ѧ&nbsp;&nbsp;Ժ<br/><br/>��&nbsp;&nbsp;ί<br/><br/>��&nbsp;&nbsp;��<br/><br/>��&nbsp;&nbsp;��</td>
							<td>
								<p style="height:100px">
								&nbsp;&nbsp; &nbsp; &nbsp;${rs.xyyj }
								</p>
								<p align="right">
									 ǩ�� &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp;  &nbsp;&nbsp; &nbsp; &nbsp;�����£� &nbsp;&nbsp; &nbsp; &nbsp;      ��&nbsp;&nbsp; &nbsp; &nbsp;     �� &nbsp;&nbsp; &nbsp; &nbsp;    ��&nbsp;&nbsp; &nbsp; &nbsp; 
								</p>
							</td>
						</tr>
						<tr height="150px">
							<td align="center">ѧ&nbsp;&nbsp;У<br/><br/>��&nbsp;&nbsp;��<br/><br/>��&nbsp;&nbsp;��</td>
							<td>
								<p style="height:100px">
								&nbsp;&nbsp; &nbsp; &nbsp;${rs.xxyj }
								</p>
								<p align="right">
									 �����£� &nbsp;&nbsp; &nbsp; &nbsp;      ��&nbsp;&nbsp; &nbsp; &nbsp;     �� &nbsp;&nbsp; &nbsp; &nbsp;    ��&nbsp;&nbsp; &nbsp; &nbsp; 
								</p>
							</td>
						</tr>
					</table>
					<br/>
					<div style="width:95%" align="right">
						���ݴ�ѧѧ���������Ʊ�
					</div>
					<p/>
					<center>
					<div style="width:95%" align="left">
						ע��1������һ��ʹ�ô�ӡ�ĸ塣<br/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2���༶��Ҫ�¼�һ��������дͻ���¼���ͬʱ�����֤�����ϡ�
					</div>
					</center>
				</td>
			</tr>
		</table>
	</html:form>
</body>
</html:html>
