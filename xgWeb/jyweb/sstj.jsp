<%@ page language="java" contentType="text/html; charset=GBK"%>
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

<html>
	<head>
		<title><%=session.getAttribute("xxmc")%>��ҵ��</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="zh-CN" />
		<meta content="all" name="robots" />
		<meta name="author" content="�����������ӹ������޹�˾ hzzfsoft@126.com" />
		<meta name="Copyright" content="Copyright" />
		<meta name="description" content="description" />
		<meta name="keywords" content="description" />

		<link id="csss" rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link href="jyweb/style/module.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/base.css" rel="stylesheet" type="text/css">
		<link href="jyweb/style/forms.css" rel="stylesheet" type="text/css">

		<script language="javascript" src="js/function.js"></script>

	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body>
		<jsp:include flush="true" page="head.jsp"></jsp:include>
        <input type="hidden" name="webtype" value="sstj" />
		<div class="mainframe">
			<div class="ny_midframe">
				<div class="leftframe">
					<jsp:include flush="true" page="contrl.jsp"></jsp:include>
					<div class="rdxw">
						<h1></h1>
					</div>
					<div class="yqlj">
						<h1></h1>
						<span></span>
					</div>
				</div>
				<div class="ny_rightframe">
					<div class="ny_con">
							<h3>
								��ǰλ�ã�
								<a href="index.do">��ҳ</a>ѡ�� ʵʱͳ��
							</h3>
						<table width="70%" align="center" class="tbborder">
							<tr height="30">
								<td>
									<font size="4"><b>ͳ����Ŀ</b> </font>
								</td>
								<td width="130">
									<font size="4"><b>���</b> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									��ע�ᵥλ�û���
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="dwnum" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									�ѵǼǸ��˼�����
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="grjlnum" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									��Ƹ��Ϣ��������
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="zpxxfbnum" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									������Ƹ��Ϣ��λ����
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="fbzpxxdws" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									ѧ���ۼ�Ͷ�ݼ�����
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="grjltdnum" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									��λ�ۼƻظ�ѧ����
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="dwhfnum" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									ѧ���ۼ������Ƹ��Ϣ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="zpxxllnum" /> </font>
								</td>
							</tr>
							<tr>
								<td colspan="2" bgcolor="#4795E5">
									&nbsp;
								</td>
							</tr>
							<tr height="25">
								<td>
									������������ĸ�λ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="hotllgw" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									��ü������ĸ�λ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="hottdgw" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									���ղ����ĸ�λ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="hotscgw" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									�����ŵ�λ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="hotdw" /> </font>
								</td>
							</tr>
							<tr>
								<td colspan="2" bgcolor="#4795E5">
									&nbsp;
								</td>
							</tr>
							<tr height="25">
								<td>
									������������ٵĸ�λ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="coolllgw" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									��ü������ٵĸ�λ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="cooltdgw" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									���ղ����ٵĸ�λ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="coolscgw" /> </font>
								</td>
							</tr>
							<tr height="25">
								<td>
									�����ŵ�λ��
								</td>
								<td>
									<font color="red" size="3"><bean:write name="rs"
											property="cooldw" /> </font>
								</td>
							</tr>
						</table>
						<br>
						<br>

					</div>
					<h2></h2>
				</div>
			</div>
		</div>

		<jsp:include flush="true" page="foot.jsp"></jsp:include>
	</body>
</html>
