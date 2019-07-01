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
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		
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
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/stgl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ṥ�� - �������� - ���Ż���</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk" value="<bean:write name="pk" scope="request" />" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/stgl.do?method=sthp" />
				
				<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="4"><span>��������</span></th>
			        </tr>
			    </thead>
					<tbody>
						<tr>
							<td align="right">
								<font color="red">*</font>��������
							</td>
							<td align="left">
								<html:select  name = "rs2" property="stdm" style="width:90px" onchange="refreshForm('/xgxt/stgl.do?method=sthp')"
											styleId="stdm">
											<html:option value=""></html:option>
											<html:options collection="stList" property="stdm"
												labelProperty="stmc" />
								</html:select>
							</td>
							<td align="right">
								����������
							</td>
							<td align="left">
								<html:text name='rs2' property="xm" styleId="xm" readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								ָ����ʦ����
							</td>
							<td align="left">
								<html:text name='rs2' property="lsxm" styleId="lsxm"  readonly="true" />
							</td>
							<td align="right">
								ְ��
							</td>
							<td align="left">
								<html:text name='rs2' property="zwmc" styleId="zwmc"  readonly="true" />
							</td>
						</tr>
						<tr>
							<td align="right">
								����<bean:message key="lable.xsgzyxpzxy" />
							</td>
							<td align="left">
								<html:text name='rs2' property="xymc" styleId="xymc"  readonly="true" />
							</td>
							<td align="right">
								�������
							</td>
							<td align="left">
								<html:text name='rs2' property="lbmc" styleId="lbmc"  readonly="true" />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
				<div class="tab">	
				<table width="100%" id="rsTable" class="formlist">
				 <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			         <button class=""
						onclick="stmsJgDoSave('stgl.do?method=saveStPyJg');" id="buttonSave">
						����
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<logic:equal name="writeAble" value="yes">
						<button class=""
							onclick="showTopWin('stgl.do?method=stpyqzConf',500,450);"id="buttonSave">
							����Ȩ��
						</button>
					</logic:equal>
					</div>
					</div>
				</td>
			      </tr>
			    </tfoot>
							 <thead>
			    	<tr>
			        	<th colspan="4"><span>���ȳɼ������������˵�����ƽ���ֳ���Ȩ�صĽ��</span></th>
			        </tr>
			    </thead>
							<tbody>
								<tr align="center" style="cursor:hand">
										<td id="xmdm" align="center" >
											������Ŀ���
										</td>
										<td id="xmdm" align="center">
											������Ŀ����
										</td>
										<td id="xmdm" align="center">
											��ռȨ��
										</td>
										<td id="xmdm" align="center">
											�Ǽǳɼ�
										</td>
								</tr>
							<logic:iterate name="rs" id="s">
								<tr style="cursor:hand">
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
										<input type="hidden" name="mkdm" value="<bean:write name='v'/>" scope="request" />
									</td>
										<logic:iterate id="v" name="s" offset="1" length="2">
										<td align="center">
											<bean:write name="v" />
										</td>
										</logic:iterate>
									<logic:iterate id="v" name="s" offset="3">
										<td align="center">
											<input type="text"  name="fz" value="<bean:write name='v'/>" scope="request" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							</tbody>
							</div>
						</table>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("�ύ�ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
