
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
		<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">	
   	function getData(){
   		var bj="";
   		if($("bj")){
   			bj=$("bj").value;
   		}
   		if(bj!=""){
   			refreshForm("/xgxt/ghxyBjryf.do?method=bjryfTj&doType=new");
   		}
  
   	}
   	 function sqSave(){
        var pc = "";
        var bj="";
        var xmdm="";
        var xmfz="";
        if($("bj")){
           bj = $("bj").value;
        }
        if($("pc")){
           pc = $("pc").value;
        }
        if($("xmdm")){
          xmdm = $("xmdm").value;
        }
        if($("xmfz")){
          xmfz = $("xmfz").value;
        }
        if(bj==""){
          alert("�༶����Ϊ�գ�");
          return false;
        }
        if(pc==""){
          alert("���β���Ϊ�գ�");
          return false;
        } 
        if(xmdm==""){
          alert("ָ�����ݲ���Ϊ�գ�");
          return false;
        } 
        if(xmfz==""){
          alert("ָ���ֵ����Ϊ�գ�");
          return false;
        }  
           
        refreshForm("/xgxt/ghxyBjryf.do?method=bjryfXx&doType=modi");
        showTips("�����У����Ե�...");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
	</script>
	<body>
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
		   <html:form action="/ghxyBjryf" method="post">
		   	<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="url" name="url" value="/nbtyJtjjkns.do?method=jtjjknsSq&doType=view" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" name="message" id="message" value="${message }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - �༶�����ֲ�ѯ - ��д��
				</div>
			</div>
			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
						<logic:equal name="notSqsj" value="no">
							<b>��ͥ��������ѧ����������</b>
						</logic:equal>
						<logic:equal name="notSqsj" value="yes">
							<font color="red">Ŀǰ��������ʱ�䷶Χ�ڣ��ݲ��������룡</font>
						</logic:equal>
						</td>
					</tr>
				</thead>
				<tr>
				<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left">
						<bean:write name="rs" property="xn"/>
						<html:hidden property="save_xn" value="${rs.xn}"/>
						</td>
					<td align="right" style="width: 10%">
						ѧ�ڣ�
					</td>
					<td align="left" style="width: 40%">
						<bean:write  name="rs" property="xq"/>	
						<html:hidden property="save_xq"  value="${rs.xq}"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�꼶��
					</td>
					<td align="left">
						<bean:write name="rs" property="nj"/>	
					</td>
					<td align="right">
						<bean:message key="lable.xb" />��
					</td>
					<td align="left">
						<bean:write name="rs" property="xymc"/>	
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name="rs" property="zymc"/>	
					</td>
					<td align="left">
						�༶��
					</td>
					<td>
						<bean:write name="rs" property="bjmc"/>	
						<html:hidden property="save_plbjdm" value="${rs.plbjdm}" styleId="bj"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						<font color="red"></font>���Σ�
					</td>
					<td align="left">
						<bean:write name="rs" property="pcmc"/>
						<html:hidden property ="save_pc" value="${rs.pc}" styleId="pc"/>
					</td>		
					<td align="right">
						ָ�����ݣ�
					</td>
					<td align="left">
						<bean:write name="rs" property="xmmc"/>
						<html:hidden property ="save_xmdm" value="${rs.xmdm}" styleId="xmdm"/>
					</td>	
				</tr>
				<tr>
					<td align="right">
						��ֵ��
					</td>
					<td align="left">
						<html:text property="save_xmfz" styleId="xmfz" value="${rs.xmfz}" onkeypress="return onlyNum(this,20)"/>
					</td>
					<td>
						
					</td>
					<td>
					
					</td>
				</tr>
			</table>
			<logic:notEmpty name="rsNum">
				<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��������ͷ��������</font>
					</legend>
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0" indexId="index">
										<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td nowrap>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				</logic:notEmpty>
			</logic:notEmpty>
			<div class="buttontool" id="btn"  width="100%">	
				<logic:notEqual name="write" value="no">
					<button type="button" class="button2" id="buttonSave" onclick="sqSave();" style="width:80px">
						��  �� 
					</button>
				</logic:notEqual>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button type="button" class="button2" onclick="Close();return false;" style="width:80px" id="buttonSave">
						�� ��
					</button>
			</div>
		</html:form>
		<logic:present name="result">
		<input type="hidden" id="message" value="${message}"/>
		<script>
				alert(document.getElementById('message').value);
				if(opener){
			 		opener.document.getElementById('search_go').onclick();
			 	}
				Close();
			</script>
		</logic:present>
	</body>


</html>

