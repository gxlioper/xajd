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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
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
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/lrh_new_js.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="JavaScript">
		
		function rcgl_sfbd_view()
		{
			var xn_id=curr_row.cells[0].getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_view&xn_id="+xn_id,525,300);
		}
		
		function rcgl_sfbd_add()
		{
			showTopWin("/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_add",550,425);
		}
		
		function rcgl_sfbd_del()
		{
			var len = document.getElementsByName("xz").length;
			var str = "";
			var s1="!!SplitSignOne!!";
			var strxx = new Array();
           	for(i=0;i<len;i++){
              if(document.getElementsByName("xz")[i].checked){
                 	str=str+document.getElementsByName("xz")[i].value+s1;
              	}
  	 		}
  	 		str = str.substring(0,str.length-16);
  	 		strxx = str.split(s1);
  	 		if(1!=strxx.length)
  	 		{
  	 			alert("�����ɾ����ֻ�ܵ���ɾ����");
  	 			return false;
  	 		}
  	 		else
  	 		{
  	 			refreshForm("/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_del&xzstr="+str);
  	 		}
		}
		
		 function select_all(){
  			 for(i=0;i<document.getElementsByName("xz").length;i++)
   			 document.getElementsByName("xz")[i].checked=document.getElementsByName("qbxz")[0].checked;
		}
		
		function sfbd_qrbd()
		{
			 var len = document.getElementsByName("xz").length;
			 var str = "";
			 var s1="!!SplitSignOne!!";
           		for(i=0;i<len;i++){
              		if(document.getElementsByName("xz")[i].checked){
                 		str += document.getElementsByName("xz")[i].value;
                 		str += s1;
              			}
          			 }
           		if(str == 0){
              			alert("����ѡ��һ����¼��");
              			return false;
           			}
           		else{
              			refreshForm('/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_qrbd&xzstr='+str);
          			 }
				}
				
		function sfbd_qxbd()
		{
			 var len = document.getElementsByName("xz").length;
			 var str = "";
			 var s1="!!SplitSignOne!!";
           		for(i=0;i<len;i++){
              		if(document.getElementsByName("xz")[i].checked){
                 		str += document.getElementsByName("xz")[i].value;
                 		str += s1;
              			}
          			 }
           		if(str == 0){
              			alert("����ѡ��һ����¼��");
              			return false;
           			}
           		else{
              			refreshForm('/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_qxbd&xzstr='+str);
          			 }
				}
	</script>
	<body>
		<html:form action="/rcgl_sfbd" method="post">
		<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã��ճ���Ϊ��Ϣ���� - ѧ���ռٱ�����Ϣ					
				</div>
			    </div>
			<fieldset>
				<legend>
					��ѯ
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								�Ƿ񱨵���
								<html:select property="sfbd_dm" style="width:100px" styleId="sfbd_dm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="sfbdList" property="XN_ID"
										labelProperty="MKMC" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								ѧ�꣺
								<html:select property="xn_dm" style="width:100px" styleId="xn_dm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="XN_ID"
										labelProperty="MKMC" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								ѧ�ڣ�
								<html:select property="xq_dm" style="width:100px" styleId="xq_dm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="XN_ID"
										labelProperty="MKMC" />
								</html:select>
								ѧ�ţ�
								<html:text property="xh" styleId="xh"/>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px;" id="search_go" 
									onclick="allNotEmpThenGo('/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_search')">
									��ѯ
								</button>
								<button class="button2" style="height:40px;display:none;" id="search_go1" 
									onclick="refreshForm('/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_search')">
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />��
								<html:select property="xydm" style="width:180px" styleId="xy"
									onchange="refreshForm('/xgxt/rcgl_sfbd.do?act=sfbd')">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;�༶��
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								������
								<html:text property="xm"  styleId="xm"/>
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
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ�������Ըı����״̬��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="center" width="3%">
									<input type="checkbox" name="qbxz" value="all" onclick="select_all()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand;background-color:" ondblclick="rcgl_sfbd_view()">	
									<td align="center">
										<input type="checkbox" name="xz" value="<bean:write name="s" property="XN_ID"/>">
									</td>
									<td>
										<input type="hidden" id="xn_id" name="xn_id"
										value="<bean:write name="s" property="XN_ID"/>" />
										<bean:write name="s" property="XH"/>
									</td>
									<td>
										<bean:write name="s" property="XM"/>
									</td>
									<td>
										<bean:write name="s" property="XB"/>
									</td>
									<td>
										<bean:write name="s" property="XYMC"/>
									</td>
									<td>
										<bean:write name="s" property="BJMC"/>
									</td>
									<td>
										<bean:write name="s" property="XQ"/>
									</td>
									<td>
										<bean:write name="s" property="XN"/>
									</td>
									<td>
										<bean:write name="s" property="SFBD"/>
									</td>
							</tr>
					   </logic:iterate>
					</table>
				</fieldset>	
			</logic:notEmpty>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="yes" name="writeAble" scope="request">
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="rcgl_sfbd_add()"
								style="width:100px">
								����
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="rcgl_sfbd_del()"
								style="width:100px">
								ɾ��
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="sfbd_qrbd()"
								style="width:100px">
								ȷ������
							</button>
							&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="sfbd_qxbd()"
								style="width:100px">
								ȡ������
							</button>
						</div>
					</center>
			</logic:equal>
			<logic:notEmpty name="message">
					<logic:equal name="message" value="del_success">
						<script>
							alert("ɾ���ɹ�!");
							document.getElementById("search_go1").click();
						</script>
					</logic:equal>
					<logic:equal name="message" value="update_success">
						<script>
							alert("���³ɹ�!");
							document.getElementById("search_go1").click();
						</script>
					</logic:equal>
					<logic:equal name="message" value="no">
						<script>alert("����ʧ��!��ѯʦ����Ѿ����ڣ�")</script>
					</logic:equal>
			</logic:notEmpty>
		</html:form>
			<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>
