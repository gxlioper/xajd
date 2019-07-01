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
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript">	     
     function rychSqSave(){
        var xh = "";
        var rychdm = "";
        var bz  = "";
        if($("xh")){
           xh = $("xh").value;
        }
        if($("rychdm")){
           rychdm = $("rychdm").value;
        }
        if($("bz")){
          bz = $("bz").value;
        }
        if(xh==""){
          alert("ѧ�Ų���Ϊ�գ�");
          return false;
        }
        if(rychdm==""){
          alert("�����ƺŲ���Ϊ�գ�");
          return false;
        }  
        if(bz.length>1000){
          alert("��ע����������1000���ڣ�");
          return false;
        }      
        refreshForm("/xgxt/zjcm_rych.do?method=rychSq&doType=save");
        if($("buttonSave")){
          $("buttonSave").disabled=true;
        }
     }
     function rychBbPrint(){
         var rychdm ="";
         if($("rychdm")){rychdm = $("rychdm").value;}
         var xh     = "";
         if($("xh")){xh = $("xh").value;}
         var xn  = "";
         if($("xn")){ xn = $("xn").value;}
         if(rychdm==""){
            alert("��ѡ�������ƺţ�");
            return false;
         }
         var url = "/xgxt/zjcm_rych.do?method=rychDjb";
             url +="&xn="+xn;
         //"/xgxt/zjcm_rych.do?method=rychDjb&rychdm="+rychdm;
            // url += "&xh="+xh;
             
             //url +="&bz="+bz;
        document.forms[0].action = url;
	    document.forms[0].target = "_blank";
	    document.forms[0].submit();
	    document.forms[0].target = "_self";
     }
</script>
	<%--		<input type="hidden" id="printpk" value="${printpk }"/>--%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script language="javascript" src="js/pjpy/pjpy_zjsyzy.js"></script>
		<html:form action="/zjcm_rych" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã��������� - �����ƺ����� - ��д�����
				</div>
			</div>

			<logic:equal name="rs" property="stuExists" value="no">
				<script>
			    alert("�������ѧ����Ч!");
			    </script>
			</logic:equal>
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url"
				value="/zjcm_rych.do?method=rychSq" />
			<input type="hidden" id="pm" name="pm"
				value="<bean:write name='rsCj' property="zhpm" />" />
			<input type="hidden" id="stab" name="stab" value="stab" />
			<%--					<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }" />--%>
			<table width="99%" id="rsT" class="tbstyle">
				<thead>
					<tr style="height:22px">
						<td colspan="4" align="center">
							<b>��д�����</b>
						</td>
					</tr>
				</thead>
				<tr style="height:22px">
					<td align="right" style="width: 10%">
						<font color="red">*</font>ѧ�ţ�
					</td>
					<logic:equal name="userOnLine" value="teacher" scope="session">
						<td align="left">
							<html:text name='rs' property="xh" styleId="xh"
								onblur="dctStuXh()"
								onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</td>
					</logic:equal>
					<logic:equal name="userOnLine" value="student" scope="session">
						<td align="left">
							<html:hidden name='rs' property="xh" styleId="xh" />
							<bean:write name='rs' property="xh" />
						</td>
					</logic:equal>

					<td align="right" style="width: 10%">
						ѧ�꣺
					</td>
					<td align="left" style="width: 40%">
						<html:select name="rsOther" property="xn" disabled="true">
							<html:options collection="xnList" property="xn"
								labelProperty="xn" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						������
					</td>
					<td align="left">
						<bean:write name='rs' property="xm" />
					</td>
					<td align="right">
						ѧ�ڣ�
					</td>
					<td align="left">
						<html:select name="rsOther" property="xq" disabled="true">
							<html:options collection="xqList" property="xqdm"
								labelProperty="xqmc" />
						</html:select>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�Ա�
					</td>
					<td align="left">
						<bean:write name='rs' property="xb" />
					</td>
					<td align="right">
						<font color="red">*</font>�����ƺţ�
					</td>
					<td align="left">
						<html:select property="rychdm" styleId="rychdm">
							<option value=""></option>
							<html:options collection="rychList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>		
				</tr>
				<tr style="height:22px">
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />��
					</td>
					<td align="left">
						<bean:write name='rs' property="xymc" />
					</td>
					<td align="right">
						����ְ��
					</td>
					<td align="left">
						<bean:write name='rs' property="zw" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						רҵ��
					</td>
					<td align="left">
						<bean:write name='rs' property="zymc" />
					</td>
					<td align="right">
						������ò��
					</td>
					<td align="left">
						${rs.zzmmmc }
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�༶��
					</td>
					<td align="left">
						<bean:write name='rs' property="bjmc" />
					</td>
					<td align="right">
						����������ʣ�
					</td>
					<td align="left">
						<html:text property="zdlcql" maxlength="10"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						�۲�������
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zhpm" />/<bean:write name='rsOther' property="bjrs" />
					</td>
					<td align="right">
						�۲�֣�
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zhf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����������
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zyfpm" />/<bean:write name='rsOther' property="bjrs" />
					</td>
					<td align="right">
						�����֣�
					</td>
					<td align="left">
						<bean:write name='rsCj' property="zyf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						����������
					</td>
					<td align="left">
						<bean:write name='rsCj' property="dyfpm" />/<bean:write name='rsOther' property="bjrs" />
					</td>
					<td align="right">
						�����֣�
					</td>
					<td align="left">
						<bean:write name='rsCj' property="dyf" />
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��Ҫ�¼���
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="zysj" onblur="chLeng(this,400)"/>
					</td>
				</tr>
				<tr style="height:22px">
					<td align="right">
						��ע��
						<br />
						<span class="style1">(��400��)&nbsp;</span>
					</td>
					<td colspan="3" align="left">
						<html:textarea rows="8" style="width:98%" property="bz" onblur="chLeng(this,400)"/>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" id="buttonSave" onclick="if($('pm').value==''){alert('����û���ۺϳɼ�����������������')}else{rychSqSave();}">
					��  �� 
				</button>
				&nbsp;&nbsp;
				<button type="button" class="button2" id="buttonSave" onclick="rychBbPrint()">
					��  ��
				</button>
				&nbsp;&nbsp;
				<button type="button" id="btn_cj" class="button2" onclick="showTopWin('ahjg_xscjb.do?xh='+document.getElementById('xh').value,'500','400')">
							ѧ �� �� ��
				</button>
				&nbsp;&nbsp;
			</div>
			<logic:equal name="done" value="true">
				<script>
			          alert("����ɹ���");
			        </script>
			</logic:equal>
			<logic:equal name="done" value="false">
				<script>
			          alert("����ʧ�ܣ�");
			        </script>
			</logic:equal>
			<logic:equal name="isExist" value="no">
				<script>
			        alert("�������ƺ�������,����ͨ����ز������\n�����������,�����ٴ����룡");			    
			        </script>
			</logic:equal>
			<logic:equal name="pass" value="no">
				<script>
			        alert("������������������ƺ�����������");			    
			        </script>
			</logic:equal>
		</html:form>
	</body>


</html>
