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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function modi(){
			if (curr_row == null) {
				alert("��ѡ��Ҫ�޸ĵ����ݣ�\n��������Ӧ���У�");
				return false;
			}
			var url = "bdzbl.do?method=bdzblModi&pkValue=";
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			url += pkValue;
			showTopWin(url,700,500);
		}		
		
		function dataExport(){
			var url = "bdzbl.do?method=exportBdzbl";
			var filedArr = ["xh","xm","sjhm","nd","bdzlx","nj","bdzkwdwmc","bdzkcsj","sfklq","lqr"];
			url += "&xydm=" + val('xy');
			url += "&zydm=" + val('zy');
			url += "&bjdm=" + val('bj');
			for(var i=0; i<filedArr.length;i++){
				url += "&" + filedArr[i] + "=" + val(filedArr[i]);
			}
			window.open(url);
		}
	</script>
	<body>
		<html:form action="/bdzbl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ��: ��ҵ����- ����֤�������- ��ѯ
				</div>
			</div>
			<logic:notEqual value="stu" name="userType">
				<div class="rightcontent">
					<fieldset>
						<legend>
							�� ѯ
						</legend>
						<input type="hidden" name="zyV" id="zyV" />
						<input type="hidden" name="bjV" id="bjV" />
						<input type="hidden" id="userType" name="userType" value="${userType}" />	
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										��ȣ�
										<html:select property="nd" styleId="nd">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
										</html:select>
										&nbsp;&nbsp;ѧ�ţ�
										<html:text property="xh" style="width:120px"></html:text>
										&nbsp;&nbsp;������
										<html:text property="xm" style="width:85px"></html:text>
										&nbsp;&nbsp;�ֻ����룺
										<html:text property="sjhm" style="width:85px"></html:text>	
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<input type="hidden" name="tab" id="tab" value="qtjxj" />
										<button class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('bdzbl.do?method=bdzblSearch');">
											��ѯ
										</button>
									</td>
								</tr>
								<tr>
									<td align="left" nowrap>
										�꼶��
										<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
										</html:select>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
										<html:select property="xydm" style="width:180px" styleId="xy"
											onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<logic:notEqual value="xyhj" name="xyhjType">
										&nbsp;&nbsp;רҵ��
										<html:select property="zydm" style="width:180px" styleId="zy"
												onchange="initBjList()">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										&nbsp;&nbsp;�༶��
										<html:select property="bjdm" style="width:180px" styleId="bj">
												<html:option value=""></html:option>
												<html:options collection="bjList" property="bjdm"
													labelProperty="bjmc" />
											</html:select>
										</logic:notEqual>
									</td>
								</tr>
								<tr>
									<td>
										����֤���ͣ�
										<html:select property="bdzlx" styleId="bdzlx">
											<html:option value=""></html:option>
											<html:option value="��ԭ������֤">��ԭ������֤</html:option>
											<html:option value="�����˵�λ����֤">�����˵�λ����֤</html:option>
											<html:option value="���˹ҿ��˲��г�����֤">���˹ҿ��˲��г�����֤</html:option>
										</html:select>		
										&nbsp;&nbsp;����֤������
										<html:text property="bdzkwdwmc" style="width:85px"></html:text>		
										&nbsp;&nbsp;����֤����ʱ�䣺
										<html:text property="bdzkcsj" style="width:85px"></html:text>										
										&nbsp;&nbsp;�Ƿ����ȡ��
										<html:select property="sfklq">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
										<br/>��ȡ�ˣ�
										<html:text property="lqr" style="width:85px"></html:text>	
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					</logic:notEqual>
					<logic:empty name="rs">
						<p align="center">
							δ�ҵ��κμ�¼��
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								��ʾ��¼����
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"> ��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
							</legend>

							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td nowrap>
											<input type="checkbox" id="all" name="all" onclick="chec()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
									<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="modi()">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>">
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="0">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="rsTable1" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=bdzblForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
							<br />
							<br />
						</fieldset>
					</logic:notEmpty>
					<br />
					<br />
					<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<logic:notEqual value="stu" name="userType">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							<logic:notEqual value="student" name="userType">
							<logic:equal value="yes" name="writeAble" scope="request">			
								<button class="button2"
									onclick="modi()"
									style="width:80px">
									�� ��
								</button>	
								&nbsp;&nbsp;		
								<button class="button2"
									onclick="dataExport()"
									style="width:80px">
									��������
								</button>
								<script language="javascript">
											document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
											document.getElementById("btn").style.width = "96%";
											window.setInterval("initBTNTool('btn')",1);
								</script>
							</logic:equal>
							</logic:notEqual>
						</div>
					</center>
					</logic:notEqual>
				</div>			
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="result" value="true">
			<script language="javascript">
      				alert("�����ɹ���");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="false">
			<logic:notEmpty name="mes">
				<input name="mes" id="mes" value="${mes}" />
				<script>
					alert(document.getElementById('mes').value);
				</script>
			</logic:notEmpty>
			<logic:empty name="mes">
				<script language="javascript">
	  				alert("����ʧ��! ");
	  			</script>
			</logic:empty>
		</logic:equal>		
	</body>
</html>
