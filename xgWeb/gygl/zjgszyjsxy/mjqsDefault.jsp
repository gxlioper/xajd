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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/gyglShareData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body >
		
		<html:form action="/zjgszy_gygl" method="post">
<%--			<input type="hidden" name="qshV" id="qshV" />												--%>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��Ϣά�� - �����������
				</div>
			</div>
<%--			<input type="hidden" id="tableName" name="tableName"--%>
<%--				value="<bean:write name="tableName" scope="request"/>" />--%>
<%--			<input type="hidden" id="realTable" name="realTable"--%>
<%--				value="<bean:write name="realTable" scope="request"/>" />--%>

			<fieldset>
				<legend>
					��������
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select  property="xn" style="width:90px" styleId="xn" >
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ڣ�
								<html:select  property="xq" style="width:90px" styleId="xq" >
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
													
							</td>
							<td width="10"  rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go" 
										onclick="refreshForm('/xgxt/zjgszy_gygl.do?method=mjqsQuery');this.disabled=true;">
									��ѯ
								</button>
							</td>							
						</tr>
						<tr>
							<td align="left">
							У������
								<html:select property="xqdm" styleId="xqdm" style="width:150px"
									onchange="getLdLb()">
									<html:option value=""></html:option>
									<html:options collection="xiaoquList" property="dm"
										labelProperty="xqmc" />
								</html:select>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;¥����:
								<html:select property="lddm" style="width:120px"
										onchange="getqshLb()" styleId="lddm">
										<html:option value=""></html:option>
										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>								
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;���Һţ�
								<html:select property="qsh" style="width:110px" styleId="qsh">
										<html:option value=""></html:option>
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
								</html:select>		
							</td>
						</tr>					
					</thead>
				</table>
			</fieldset>
			
			<logic:empty name="rs">
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								style="cursor:hand;" >
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
						<button class="button2" onclick="mjqsAdd()"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="mjqsModi()"
							style="width:80px">
							�� ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="mjqsDel()"
							style="width:80px">
							ɾ ��
						</button>					
						</logic:equal>						
<%--						&nbsp;&nbsp;&nbsp;&nbsp;--%>
<%--						<button class="button2" onclick="dataExport()" style="width:80px">--%>
<%--							��������--%>
<%--						</button>--%>
					</div>
				</center>
		</html:form>
		<script language="javascript">
		document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
		document.getElementById("btn").style.width = "96%";
		window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>   
<script type="text/javascript">
function mjqsAdd(){
    var url = "/xgxt/zjgszy_gygl.do?method=mjqsAdd";
    showTopWin(url,'550','280');
}
function mjqsModi(){
    var url = "/xgxt/zjgszy_gygl.do?method=mjqsModi&pkValue=";
    if(curr_row == null){
        alert("��ѡ��Ҫ���������ݣ�\n��������Ӧ���У�");
		return false;
    }
    pkValue = curr_row.getElementsByTagName("input")[0].value;     
    url +=pkValue;                             
    if(confirm("ȷ��Ҫ�޸ĸ����ݣ�")){
         showTopWin(url,'550','280');
    }else{
        return false;
    }    
}
function mjqsDel(){
    var url = "/xgxt/zjgszy_gygl.do?method=mjqsDel&pkValue=";
    if(curr_row == null){
        alert("��ѡ��Ҫ���������ݣ�\n��������Ӧ���У�");
		return false;
    }
    pkValue = curr_row.getElementsByTagName("input")[0].value;     
    url +=pkValue;                             
    if(confirm("ȷ��Ҫ��ɾ���������ݣ�")){
        refreshForm(url);
    }else{
        return false;
    }    
}
</script>
<logic:equal value="true" name="done">
<script type="text/javascript">
alert("�����ɹ���");   
</script>
</logic:equal>
<logic:equal value="false" name="done">
<script type="text/javascript">
alert("����ʧ�ܣ�");
</script>
</logic:equal>
</html>
