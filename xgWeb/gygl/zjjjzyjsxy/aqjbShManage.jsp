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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<base target="_self">
	<body>
		<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
		<script language="javascript" src="js/function.js"></script>	
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>	
		<html:form action="/zjjjzy_Gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��� - ÿ�ܼ���� 
				</div>
			</div>
			<fieldset>
				<legend>
					��������
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
						<td >
						   ���״̬
						   <html:select property="yesNo">
									<html:option value="δ���">δ���</html:option>
									<html:option value="ͨ��">ͨ��</html:option>
									<html:option value="��ͨ��">��ͨ��</html:option>
                            </html:select>
                           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							���⣺<html:text property="title" styleId="title"/>
						   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							ʱ��Σ�
							<html:text property="rzrq" styleId="rzrq"
								 style="cursor:hand;width: 80px"
								onclick="return showCalendar('rzrq','y-mm-dd');" />
							--
							<html:text property="lzrq" styleId="lzrq"
								style="cursor:hand;width: 80px"
								onclick="return showCalendar('lzrq','y-mm-dd');" />
							</td>
							<td width="10"  align="center" valign="middle">
								<input type="hidden" name="go" value="go" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zjjjzy_Gygl.do?method=aqjbShManage')">
									��ѯ
								</button>
							</td>
						</tr>													
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��ǰ��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
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
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="mzjbView()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td nowrap>
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
						<logic:equal value="yes" name="writeAble">
						<button class="button2" onclick="mzjbSh('yes')"
							style="width:80px">
							ͨ ��
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="mzjbSh('no')"
							style="width:80px">
							��ͨ��
						</button>						
						</logic:equal>
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
	       function mzjbView(){	      
	            url="/xgxt/zjjjzy_Gygl.do?method=aqjbShow&documentId=";	           	
	            url+=(curr_row.cells[0].getElementsByTagName("input"))[0].value ;                
	            showTopWin(url,"800","600");	          
	       }
	       function mzjbSh(shType){
	           if (curr_row == null) {
		          alert("��ѡҪ��˵ļ�¼��\n����һ�м�¼����");
		          return false;
	           } else {	           
	                url="/xgxt/zjjjzy_Gygl.do?method=aqjbSh&documentId=";	           	
	                url+=(curr_row.cells[0].getElementsByTagName("input"))[0].value ;
	                url+="&shType="+shType;
	                var clinText = "";
	                if(shType=="yes"){
	                   clinText="ͨ��";
	                }else{
	                   clinText="��ͨ��";
	                }
	                if(confirm("ȷ�� "+clinText+" �ü򱨼�¼��" )){
	                   refreshForm(url);
	                }  
	          }              	            
	       }
	</script>
</html>
