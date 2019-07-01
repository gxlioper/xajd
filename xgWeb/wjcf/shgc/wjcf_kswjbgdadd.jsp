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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
		function chkValue(doType)
		{
			var xh=document.getElementById("xh").value;

			if (xh==''){
				alert("��ɫ*����ϢΪ������ٴ���д��ȷ�ϣ�");
			}else{
				refreshForm('wjcf_shgc_bgdsave.do?doType='+doType);
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}
</script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<html:form action="/shgcwjcfwh" method="post">
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/wjcf_shgc_bgdadd.do" />
			<input type="hidden" id="tz" name="tz" value="${tz }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">Υ�ʹ��� - ����ά�� - ����Υ�ʹ��ֲ��鵵</span>
				</div>
			</div>
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							����Υ�ʹ��ֲ��鵵����
						</td>
					</tr>
				</thead>
				<tr>
					<td align="right" style="width:15%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<td align="left" style="width:35%">
						<html:text name="rs" property="xh" styleId="xh"
							onkeypress="autoFillStuInfo(event.keyCode,this)" />
						<button type="button"
							onclick="showTopWin('/xgxt/wjcf_stuinfo.do?realtable=kswjcfb&doType=load&xh=',800,525);"
							class="btn_01" id="buttonFindStu">
							ѡ��
						</button>
					</td>
					<td align="right" style="width:15%">
						������
					</td>
					<td align="left" style="width:35%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						${rs.xymc}
					</td>
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						${rs.zymc}
					</td>
				</tr>
				<tr>
					<td align="right">
						�༶��
					</td>
					<td align="left">
						${rs.bjmc }
					</td>
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						${rs.nj }
					</td>
				</tr>
				<tr>
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						${rs.xb }
					</td>
					<td align="right">
						������ò��
					</td>
					<td align="left">
						${rs.zzmmmc }
					</td>
				</tr>
				<tr>
					<td align="right">
						���壺
					</td>
					<td align="left">
						${rs.mzmc}
					</td>
					<td align="right">
						���᣺
					</td>
					<td align="left">
						${rs.jg }
					</td>
				</tr>
				<tr>
					<td align="right">
						�������£�
					</td>
					<td align="left" colspan="3">
						${rs.csrq }
					</td>
				</tr>
				<tr>
					<td align="right">
						���ֵȼ���
					</td>
					<td align="left">
						${rs.cfjb }
						<input type="hidden" name="cfjb" value="${rs.cfjb }"/>
					</td>
					<td align="right">
						����ԭ��
					</td>
					<td align="left">
						${rs.cfyymc }
					<input type="hidden" name="cfyy" value="${rs.cfyy }"/>
					<input type="hidden" name="cflb" value="${rs.cflb }"/>
					</td>
				</tr>
				<tr>
					<td align="right">
						���ֺ�&nbsp;&nbsp;&nbsp;&nbsp;<br/>��ʵ���֣�
					</td>
					<td colspan="3">
						<html:textarea property="cfhbx" styleId="cfhbx" style="width:95%" rows="3">
						</html:textarea>
					</td>
				</tr>
				<tr>
					<td align="right">
						����&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/>���ͼ�¼��
					</td>
					<td colspan="3">
						<html:textarea property="jcjl" styleId="jcjl" style="width:95%" rows="3"></html:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
							<table style="width:99%" border="1" class="tbstyle">
								<tr>
									<td bgcolor="#CCCCCC">
										<div id="main2" style="color:blue;cursor:hand"
											onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">
											<div align="center" class="style1 style3">
												<strong>���������</strong>
											</div>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div id="child2" style="display:none">
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													����Ա���������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="bzryj" styleId="bzryj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													<bean:message key="lable.xsgzyxpzxy" />������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="xyyj" styleId="xyyj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													��첿��������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="kbbmyj" styleId="kbbmyj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													ѧ����������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="xscyj" styleId="xscyj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
								<table style="width:99%" border="1" align="center" class="tbstyle">
									<thead>
										<tr>
											<td>
												<div align="center" class="style2">
													У������
												</div>
											</td>
										</tr>
									</thead>
										<tr>
											<td>
												<html:textarea property="xxyj" styleId="xxyj" style="width:95%" rows="3"></html:textarea>
											</td>
										</tr>
								</table>
							</div>
						</td>
					</tr>
					<tr>
					<td align="right">
						��ע��
					</td>
					<td colspan="3">
						<html:textarea property="bz" styleId="bz" style="width:95%" rows="3"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="chkValue('save')"
					style="width:80px" id="buttonSave">
					�� ��
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					�� ��
				</button>
			</div>

		</html:form>
		<logic:equal value="ok" name="done">
			<logic:equal value="true" name="tz">
				<script language="javascript">
				alert("�����ɹ���");
				Close();
				window.dialogArguments.document.getElementById('tzurl').click(); 
				</script>
			</logic:equal>
			<logic:notEqual value="true" name="tz">
				<script language="javascript">
				alert("�����ɹ���");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();   
				</script>
			</logic:notEqual>
		</logic:equal>
		<logic:equal value="no" name="done">
		<logic:equal value="true" name="tz">
				<script language="javascript">
  				alert("����ʧ�ܣ�ԭ������Ǹ���Ϣ�����ݿ����Ѵ���! \n ������ϸ�˶�!��");
				Close();
				window.dialogArguments.document.getElementById('tzurl').click();  
			</script>
			</logic:equal>
			<logic:notEqual value="true" name="tz">
				<script language="javascript">
  				alert("����ʧ�ܣ�ԭ������Ǹ���Ϣ�����ݿ����Ѵ���! \n ������ϸ�˶�!��");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();   
			</script>
			</logic:notEqual>
			<logic:equal name="isBgd" value="no">
		<script language="javascript">
			alert("���������ϲ��鵵��������,ֻ�б�ҵ���ŷ��ϴ�����,�����º˶�!");
		</script>
	</logic:equal>	
	</logic:equal>
	</body>
</html>
