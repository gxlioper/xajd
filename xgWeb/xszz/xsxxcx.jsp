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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" /></title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="������� zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
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
		function yz(titName){
			var xh = document.getElementById('xh').value;
			if((xh == null) || (xh == "")){
				alert("ѧ�Ų���Ϊ��!");
				return false;
			}
			document.forms[0].action = "/xgxt/xsxxcx.do?jxjlbType=xsxxcx&doType=add&titName=" + titName;
			document.forms[0].submit();
		}
	</script>
</head>

<body onload="loadPage()">
	<div class="title">
		<div class="title_img" id="title_m">
			��ǰ����λ�ã�ѧ������ - ��ѧ��������
		</div>
	</div>
	<logic:equal name="sfksq" value="-1">
		<center>
			<p>
				���ڲ�������ʱ���ڣ�����
			</p>
		</center>
	</logic:equal>
	<logic:equal name="sfksq" value="1">
		<html:form action="zxdksq.do" method="post">
			<input type="hidden" id="titName" name="titName"
				value="<bean:write name="titName" scope="request" />">
			<input type="hidden" id="url" name="url"
				value="/xsxxcx.do?jxjlbType=xsxxcx" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-bjmc" />


			<logic:present name="ok">
				<logic:match value="ok" name="ok">
					<script language="javascript">
	         	alert("����ɹ���");
	         	</script>
				</logic:match>
				<logic:match value="no" name="ok">
					<script language="javascript">
	         	alert("����ʧ�ܣ�");
	         	</script>
				</logic:match>
			</logic:present>
			<logic:notEmpty name="isStu">
				<logic:equal name="isStu" value="is">
				<script language="javascript">
				alert("����Ȩ�޸�ѧ����Ϣ!");
				</script>
				</logic:equal>
			</logic:notEmpty>
			<logic:notEmpty name="isNULL">
				<logic:equal name="isNULL" value="is">
				<script language="javascript">
				alert("�޴�ѧ����Ϣ!");
				</script>
				</logic:equal>
			</logic:notEmpty>
			<div class="xxk">
				<logic:notEmpty name="pages">
					<logic:iterate id="card" name="pages" scope="request">
						<ul>
							<li id="<bean:write name='card' property='en'/>l"
								class="xxk_off_l"></li>
							<li id="<bean:write name='card' property='en'/>m" onclick=""
								class="xxk_off_m">
								&nbsp;
								<bean:write name='card' property='cn' />
								&nbsp;
							</li>
							<li id="<bean:write name='card' property='en'/>r"
								class="xxk_off_r"></li>
						</ul>
					</logic:iterate>
				</logic:notEmpty>
			</div>

			<table class="tbstyle" width="100%">
				<tr>
					<td>
						<table width="100%" border="1" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main1" style="color:blue;cursor:hand"
										onclick="document.all.child1.style.display=(document.all.child1.style.display =='none')?'':'none'">
										<div align="center" class="style1 style3">
											<strong>ѧ��������Ϣ</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<div id="child1">
							<table class="tbstyle" width="100%">
								<tr>
									<logic:equal name="userOnLine" value="teacher" scope="session">
										<td align="center" width="16%" scope="col">
											<font color="red">*</font>ѧ�ţ�
										</td>
										<td align="left" width="34%" scope="col">
											<html:text name='rs' property="xh" styleId="xh"
												onkeypress="autoFillStuInfo(event.keyCode,this)" readonly="true"/>
											<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu">
												ѡ��
											</button>
										</td>
									</logic:equal>
									<logic:equal name="userOnLine" value="student" scope="session">
										<td align="center" width="16%" scope="col">
											<font color="red">*</font>ѧ�ţ�
										</td>
										<td align="left" width="34%" scope="col">
											<input type="text" id="xh" name="xh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xh" />"
												readonly="true">
										</td>
									</logic:equal>
									<td width="16%" scope="col">
										<div align="center">
											������
										</div>
									</td>
									<td width="34%" scope="col">
										<div align="left">
											<input type="text" id="ksh" name="ksh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ksh" />"
												>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="xm" name="xm"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xm" />"
												>
										</div>
									</td>
									<td>
										<div align="center">
											�Ա�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="xb" name="xb"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xb" />"
												>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											���֤��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="sfzh" name="sfzh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="sfzh" />"
												>
										</div>
									</td>
									<td>
										<div align="center">
											����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="mzmc" name="mzmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="mzmc" />"
												>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											ѧУ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="xxmc" name="xxmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xxmc" />"
												>
										</div>
									</td>
									<td>
										<div align="center">
											Ժ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="xymc" name="xymc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xymc" />"
												>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											ϵ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="xmc" name="xmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xmc" />"
												>
										</div>
									</td>
									<td>
										<div align="center">
											����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="kh" name="kh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="kh" />"
												readonly="readonly">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="csrq" name="csrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="csrq" />"
												>
										</div>
									</td>
									<td>
										<div align="center">
											ѧ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="xz" name="xz"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xz" />"
												>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��ѧ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="rxny" name="rxny"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="rxny" />"
												>
										</div>
									</td>
									<td>
										<div align="center">
											��ҵ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="byny" name="byny"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="byny" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											ѧ������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="xslx" name="xslx"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xslx" />">
										</div>
									</td>
									<td>
										<div align="center">
											��������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="yzbm" name="yzbm"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="yzbm" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											����绰
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="sedh" name="sedh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="sedh" />">
										</div>
									</td>
									<td>
										<div align="center">
											�����ܶ�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="dkze" name="dkze"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="dkze" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											ѧ�Ѵ�����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="xfdks" name="xfdks"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="xfdks" />">
										</div>
									</td>
									<td>
										<div align="center">
											����Ѵ�����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="shfdks" name="shfdks"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="shfdks" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											ס�޷Ѵ�����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="zsfdks" name="zsfdks"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="zsfdks" />">
										</div>
									</td>
									<td>
										<div align="center">
											�ѻ������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="yhdkje" name="yhdkje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="yhdkje" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�״α�ҵȥ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="scbyqx" name="scbyqx"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="scbyqx" />">
										</div>
									</td>
									<td>
										<div align="center">
											��Ч��ϵ��ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="yxlxfs" name="yxlxfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="yxlxfs" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��ǰ������λ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="dqgzdw" name="dqgzdw"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="dqgzdw" />">
										</div>
									</td>
									<td>
										<div align="center">
											��ǰ������λ��ַ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="dqgzdwdz" name="dqgzdwdz"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="dqgzdwdz" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��ǰ������λ�ʱ�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="dqgzdwyb" name="dqgzdwyb"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="dqgzdwyb" />">
										</div>
									</td>
									<td>
										<div align="center">
											��ǰ������λ�绰
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="dqgzdwdh" name="dqgzdwdh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="dqgzdwdh" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��Уԭ��
										</div>
									</td>
									<td colspan="3" >
										<div align="left">
											<input type="text" id="lxyy" name="lxyy"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="lxyy" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��ͥסַ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="jtzz" name="jtzz"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="jtzz" />">
										</div>
									</td>
									<td>
										<div align="center">
											��ͥ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="jtsr" name="jtsr"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="jtsr" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="fqxm" name="fqxm"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="fqxm" />">
										</div>
									</td>
									<td>
										<div align="center">
											������ϵ�绰
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="fqlxdh" name="fqlxdh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="fqlxdh" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											���׹�����λ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="fqgzdw" name="fqgzdw"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="fqgzdw" />">
										</div>
									</td>
									<td>
										<div align="center">
											�������֤��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="fqsfzh" name="fqsfzh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="fqsfzh" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											ĸ������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="mqxm" name="mqxm"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="mqxm" />">
										</div>
									</td>
									<td>
										<div align="center">
											ĸ����ϵ�绰
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="mqlxdh" name="mqlxdh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="mqlxdh" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											ĸ�׹�����λ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="mqgzdw" name="mqgzdw"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="mqgzdw" />">
										</div>
									</td>
									<td>
										<div align="center">
											ĸ�����֤��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="mqsfzh" name="mqsfzh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="mqsfzh" />">
										</div>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="1" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main2" style="color:blue;cursor:hand"
										onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
										<div align="center" class="style1 style3">
											<strong>��ͬ1</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<div id="child2" style="display:none">
							<table width="100%" border="1" align="center" class="tbstyle">
								<tr>
									<td scope="row" width="16%">
										<div align="center">
											��ͬ��
										</div>
									</td>
									<td width="34%">
										<div align="left">
											<input type="text" id="hth1" name="hth1"
												style="width:100%;heigh:100%" readonly="readonly"
												value="<bean:write name='rs' property="hth1" />">
										</div>
									</td>
									<td width="16%">
										<div align="center">
											������ڻ���
										</div>
									</td>
									<td width="34%">
										<div align="left">
											<input type="text" id="ht1_jbjrjg" name="ht1_jbjrjg"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_jbjrjg" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��֧��������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_fzjgmc" name="ht1_fzjgmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_fzjgmc" />">
										</div>
									</td>
									<td>
										<div align="center">
											��׼����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_pzrq" name="ht1_pzrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_pzrq" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�ܽ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_zje" name="ht1_zje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_zje" />">
										</div>
									</td>
									<td>
										<div align="center">
											����Ա
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_jby" name="ht1_jby"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_jby" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��׼�г�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_pzhz" name="ht1_pzhz"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_pzhz" />">
										</div>
									</td>
									<td>
										<div align="center">
											���ʼ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_hkksrq" name="ht1_hkksrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_hkksrq" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�����ֹ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_hkjzrq" name="ht1_hkjzrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_hkjzrq" />">
										</div>
									</td>
									<td>
										<div align="center">
											չ��ʱ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_zqsj" name="ht1_zqsj"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_zqsj" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											չ������
										</div>
									</td>
									<td colspan="3">
										<div align="left">
											<textarea id="ht1_zqly" name="ht1_zqly"
												style="width:100%;heigh:100%;heigh:100%" value="" rows="5"
												type="_moz">
								<bean:write name="rs" property="ht1_zqly" />
							</textarea>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											���ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_dkfs" name="ht1_dkfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_dkfs" />">
										</div>
									</td>
									<td>
										<div align="center">
											���ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_hkfs" name="ht1_hkfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_hkfs" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�����������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_hkjzmc" name="ht1_hkjzmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_hkjzmc" />">
										</div>
									</td>
									<td>
										<div align="center">
											��������ʺ�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_hkjzzh" name="ht1_hkjzzh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_hkjzzh" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��ǷӦ����Ϣ���
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_tqyhbxje" name="ht1_tqyhbxje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_tqyhbxje" />">
										</div>
									</td>
									<td>
										<div align="center">
											��Ƿ��ֹʱ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht1_tqjzsj" name="ht1_tqjzsj"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht1_tqjzsj" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��Ƿ��ע
										</div>
									</td>
									<td colspan="3">
										<div align="left">
											<textarea id="ht1_tqbz" name="ht1_tqbz"
												style="width:100%;heigh:100%;heigh:100%" value="" rows="5"
												type="_moz">
								<bean:write name="rs" property="ht1_tqbz" />
							</textarea>
										</div>
									</td>
								</tr>

							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="1" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main3" style="color:blue;cursor:hand"
										onclick="document.all.child3.style.display=(document.all.child3.style.display =='none')?'':'none'">
										<div align="center" class="style1 style3">
											<strong>��ͬ2</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<div id="child3" style="display:none">
							<table width="100%" border="1" align="center" class="tbstyle">


								<tr>
									<td scope="row" width="16%">
										<div align="center">
											��ͬ��
										</div>
									</td>
									<td width="34%">
										<div align="left">
											<input type="text" id="hth2" name="hth2"
												style="width:100%;heigh:100%" readonly="readonly"
												value="<bean:write name='rs' property="hth2" />">
										</div>
									</td>
									<td width="16%">
										<div align="center">
											������ڻ���
										</div>
									</td>
									<td width="34%">
										<div align="left">
											<input type="text" id="ht2_jbjrjg" name="ht2_jbjrjg"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_jbjrjg" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��֧��������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_fzjgmc" name="ht2_fzjgmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_fzjgmc" />">
										</div>
									</td>
									<td>
										<div align="center">
											��׼����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_pzrq" name="ht2_pzrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_pzrq" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�ܽ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_zje" name="ht2_zje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_zje" />">
										</div>
									</td>
									<td>
										<div align="center">
											����Ա
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_jby" name="ht2_jby"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_jby" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��׼�г�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_pzhz" name="ht2_pzhz"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_pzhz" />">
										</div>
									</td>
									<td>
										<div align="center">
											���ʼ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_hkksrq" name="ht2_hkksrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_hkksrq" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�����ֹ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_hkjzrq" name="ht2_hkjzrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_hkjzrq" />">
										</div>
									</td>
									<td>
										<div align="center">
											չ��ʱ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_zqsj" name="ht2_zqsj"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_zqsj" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											չ������
										</div>
									</td>
									<td colspan="3">
										<div align="left">
											<textarea id="ht2_zqly" name="ht2_zqly"
												style="width:100%;heigh:100%;heigh:100%" value="" rows="5"
												type="_moz">
								<bean:write name="rs" property="ht2_zqly" />
							</textarea>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											���ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_dkfs" name="ht2_dkfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_dkfs" />">
										</div>
									</td>
									<td>
										<div align="center">
											���ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_hkfs" name="ht2_hkfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_hkfs" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�����������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_hkjzmc" name="ht2_hkjzmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_hkjzmc" />">
										</div>
									</td>
									<td>
										<div align="center">
											��������ʺ�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_hkjzzh" name="ht2_hkjzzh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_hkjzzh" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��ǷӦ����Ϣ���
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_tqyhbxje" name="ht2_tqyhbxje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_tqyhbxje" />">
										</div>
									</td>
									<td>
										<div align="center">
											��Ƿ��ֹʱ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht2_tqjzsj" name="ht2_tqjzsj"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht2_tqjzsj" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��Ƿ��ע
										</div>
									</td>
									<td colspan="3">
										<div align="left">
											<textarea id="ht2_tqbz" name="ht2_tqbz"
												style="width:100%;heigh:100%;heigh:100%" value="" rows="5"
												type="_moz">
								<bean:write name="rs" property="ht2_tqbz" />
							</textarea>
										</div>
									</td>
								</tr>

							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="1" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main4" style="color:blue;cursor:hand"
										onclick="document.all.child4.style.display=(document.all.child4.style.display =='none')?'':'none'">
										<div align="center" class="style1 style3">
											<strong>��ͬ3</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<div id="child4" style="display:none">
							<table width="100%" border="1" align="center" class="tbstyle">

								<tr>
									<td scope="row" width="16%">
										<div align="center">
											��ͬ��
										</div>
									</td>
									<td width="34%">
										<div align="left">
											<input type="text" id="hth3" name="hth3"
												style="width:100%;heigh:100%" readonly="readonly"
												value="<bean:write name='rs' property="hth3" />">
										</div>
									</td>
									<td width="16%">
										<div align="center">
											������ڻ���
										</div>
									</td>
									<td width="34%">
										<div align="left">
											<input type="text" id="ht3_jbjrjg" name="ht3_jbjrjg"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_jbjrjg" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��֧��������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_fzjgmc" name="ht3_fzjgmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_fzjgmc" />">
										</div>
									</td>
									<td>
										<div align="center">
											��׼����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_pzrq" name="ht3_pzrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_pzrq" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�ܽ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_zje" name="ht3_zje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_zje" />">
										</div>
									</td>
									<td>
										<div align="center">
											����Ա
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_jby" name="ht3_jby"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_jby" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��׼�г�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_pzhz" name="ht3_pzhz"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_pzhz" />">
										</div>
									</td>
									<td>
										<div align="center">
											���ʼ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_hkksrq" name="ht3_hkksrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_hkksrq" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�����ֹ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_hkjzrq" name="ht3_hkjzrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_hkjzrq" />">
										</div>
									</td>
									<td>
										<div align="center">
											չ��ʱ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_zqsj" name="ht3_zqsj"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_zqsj" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											չ������
										</div>
									</td>
									<td colspan="3">
										<div align="left">
											<textarea id="ht3_zqly" name="ht3_zqly"
												style="width:100%;heigh:100%;heigh:100%" value="" rows="5"
												type="_moz">
								<bean:write name="rs" property="ht3_zqly" />
							</textarea>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											���ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_dkfs" name="ht3_dkfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_dkfs" />">
										</div>
									</td>
									<td>
										<div align="center">
											���ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_hkfs" name="ht3_hkfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_hkfs" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�����������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_hkjzmc" name="ht3_hkjzmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_hkjzmc" />">
										</div>
									</td>
									<td>
										<div align="center">
											��������ʺ�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_hkjzzh" name="ht3_hkjzzh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_hkjzzh" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��ǷӦ����Ϣ���
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_tqyhbxje" name="ht3_tqyhbxje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_tqyhbxje" />">
										</div>
									</td>
									<td>
										<div align="center">
											��Ƿ��ֹʱ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht3_tqjzsj" name="ht3_tqjzsj"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht3_tqjzsj" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��Ƿ��ע
										</div>
									</td>
									<td colspan="3">
										<div align="left">
											<textarea id="ht3_tqbz" name="ht3_tqbz"
												style="width:100%;heigh:100%;heigh:100%" value="" rows="5"
												type="_moz">
								<bean:write name="rs" property="ht3_tqbz" />
							</textarea>
										</div>
									</td>
								</tr>

							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="1" class="tbstyle">
							<tr>
								<td bgcolor="#CCCCCC">
									<div id="main5" style="color:blue;cursor:hand"
										onclick="document.all.child5.style.display=(document.all.child5.style.display =='none')?'':'none'">
										<div align="center" class="style1 style3">
											<strong>��ͬ4</strong>
										</div>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFFFF">
						<div id="child5" style="display:none">
							<table width="100%" border="1" align="center" class="tbstyle">

								<tr>
									<td scope="row" width="16%">
										<div align="center">
											��ͬ��
										</div>
									</td>
									<td width="34%">
										<div align="left">
											<input type="text" id="hth4" name="hth4"
												style="width:100%;heigh:100%" readonly="readonly"
												value="<bean:write name='rs' property="hth4" />">
										</div>
									</td>
									<td width="16%">
										<div align="center">
											������ڻ���
										</div>
									</td>
									<td width="34%">
										<div align="left">
											<input type="text" id="ht4_jbjrjg" name="ht4_jbjrjg"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_jbjrjg" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��֧��������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_fzjgmc" name="ht4_fzjgmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_fzjgmc" />">
										</div>
									</td>
									<td>
										<div align="center">
											��׼����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_pzrq" name="ht4_pzrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_pzrq" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�ܽ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_zje" name="ht4_zje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_zje" />">
										</div>
									</td>
									<td>
										<div align="center">
											����Ա
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_jby" name="ht4_jby"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_jby" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��׼�г�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_pzhz" name="ht4_pzhz"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_pzhz" />">
										</div>
									</td>
									<td>
										<div align="center">
											���ʼ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_hkksrq" name="ht4_hkksrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_hkksrq" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�����ֹ����
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_hkjzrq" name="ht4_hkjzrq"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_hkjzrq" />">
										</div>
									</td>
									<td>
										<div align="center">
											չ��ʱ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_zqsj" name="ht4_zqsj"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_zqsj" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											չ������
										</div>
									</td>
									<td colspan="3">
										<div align="left">
											<textarea id="ht4_zqly" name="ht4_zqly"
												style="width:100%;heigh:100%;heigh:100%" value="" rows="5"
												type="_moz">
								<bean:write name="rs" property="ht4_zqly" />
							</textarea>
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											���ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_dkfs" name="ht4_dkfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_dkfs" />">
										</div>
									</td>
									<td>
										<div align="center">
											���ʽ
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_hkfs" name="ht4_hkfs"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_hkfs" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											�����������
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_hkjzmc" name="ht4_hkjzmc"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_hkjzmc" />">
										</div>
									</td>
									<td>
										<div align="center">
											��������ʺ�
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_hkjzzh" name="ht4_hkjzzh"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_hkjzzh" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��ǷӦ����Ϣ���
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_tqyhbxje" name="ht4_tqyhbxje"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_tqyhbxje" />">
										</div>
									</td>
									<td>
										<div align="center">
											��Ƿ��ֹʱ��
										</div>
									</td>
									<td>
										<div align="left">
											<input type="text" id="ht4_tqjzsj" name="ht4_tqjzsj"
												style="width:100%;heigh:100%"
												value="<bean:write name='rs' property="ht4_tqjzsj" />">
										</div>
									</td>
								</tr>
								<tr>
									<td scope="row">
										<div align="center">
											��Ƿ��ע
										</div>
									</td>
									<td colspan="3">
										<div align="left">
											<textarea id="ht4_tqbz" name="ht4_tqbz"
												style="width:100%;heigh:100%;heigh:100%" value="" rows="5">
								<bean:write name="rs" property="ht4_tqbz" />
							</textarea>
										</div>
									</td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="1" align="center" class="tbstyle">
							<tr>
								<td scope="row">
									<div align="center">
										��ע
									</div>
								</td>
								<td colspan="3">
									<div align="left">
										<textarea id="bz" name="bz"
											style="width:100%;heigh:100%;heigh:100%" value="" rows="5"
											type="_moz">
								<bean:write name="rs" property="bz" />
							</textarea>
									</div>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<div class="buttontool" id="btn" style="position: absolute;width:90%">
				<button class="button2"
					onClick="yz(document.getElementById('titName').value);">
					��&nbsp;&nbsp;&nbsp;&nbsp;��
				</button>
			</div>

		</html:form>
	</logic:equal>
</body>
</html:html>
