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
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script language="javascript" >
	   
	    function dataSave(){
	       var yesNo = "";
	       var xxcljg = "";
	       if($("yesNo")){
	          yesNo=$("yesNo").value;
	       }
	       if($("xxcljg")){
	          xxcljg=$("xxcljg").value;
	       }
	       if(xxcljg.length>100){
	          alert("ѧУ�������������");
	          return false;
	       }   
	       refreshForm("/xgxt/zjcmxy_Gygl.do?method=doCheck&doType=save");
	       
	    }
	</script>
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body >	
		<html:form action="zjcmxy_Gygl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow">��Ԣ���� - ס�޼��ɹ��� - �������</span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
<%--				<input type="hidden" id="doType" name="doType"--%>
<%--					value="<bean:write name="doType" scope="request"/>" />--%>
<%--				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />--%>
<%--				<input type="hidden" id="disableEle" name="disableEle" value="" />--%>
<%--				<input type="hidden" id="getStuInfo" name="getStuInfo"value="xm-xb-nj-xymc-zymc-bjmc" />--%>
<%--				<input type="hidden" id="url" name="url" value="/gygl_gzcj_zsjl.do" />--%>
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td colspan="4" align="center">
								ס�޼���Υ�ʹ������
							</td>
						</tr>
					</thead>					
					<tr>
						<td align="right" width="20%">
							¥����
						</td>
						<td align="left">
						${rs.ldmc}			
						</td>	
						<td align="right"  width="15%">
							ѧ�꣺
						</td>
						<td align="left">
						${rs.xn}	
						</td>									
					</tr>
					<tr>
						<td align="right">
							���Һţ�
						</td>
						<td align="left">
						${rs.qsh}	
						</td>
						<td align="right">
							ѧ�ڣ�
						</td>
						<td align="left">
						${rs.xqmc}	
						</td>			
					</tr>					
					<tr>
						<td align="right">
							Υ��ʱ�䣺
						</td>
						<td align="left">
						${rs.wjsj}
						</td>	
						<td align="right">
							Υ�����
						</td>
						<td align="left">
                         ${rs.wjlbmc}	
						</td>	
					</tr>
					<tr>
						<td align="right">
							Υ����Ϣ¼���ˣ�
						</td>
						<td align="left">
						 ${rs.lrrxm}	
						</td>
						<td align="right">
							
						</td>
						<td align="left" >
						
						</td>		
					</tr>
					<tr>
						<td align="right">
							Υ��˵����									
						</td>
						<td align="left" colspan="3">
						${rs.wjsy}	
						</td>
					</tr>
					<tr>
						<td align="right">
							���������									
						</td>
						<td align="left" colspan="3" >
							${rs.dcqk}		
						</td>
					</tr>
					<tr >
						<td align="right">
							�Ƿ���
						</td>
						<td align="left" >
							${rs.sfcf}	
						</td>
						<td align="right">
							��������
						</td>
						<td align="left" >
							${rs.xycljgmc}	
						</td>	
						
					</tr>
					<tr >
					
						<td align="right">
							
						</td>
						<td align="left">
                        
						</td>	
					<td align="right">
							<font color="red">���</font>��
						</td>
						<td align="left" >
								<html:select name="rs" property="yesNo" >
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
						</td>
					</tr>		
					<tr>
						<td align="right">
							ѧУ�����<br>
							<font color="red"><��100����></font>									
						</td>
						<td align="left" colspan="3">
							<textarea rows="7" cols="78" name="xxcljg" id="xxcljg" type="_moz">${rs.xxcljg}</textarea>			
						</td>
					</tr>
				</table>
				<table width="100%" class="tbstyle" >
					<thead>
						<tr onclick="if(document.getElementById('xswjxx').style.display == 'none'){document.getElementById('xswjxx').style.display = ''}else{document.getElementById('xswjxx').style.display = 'none'};">
							<td colspan="6" align="center">
								������Υ��ѧ����Ϣ
							</td>
						</tr>
					</thead>
					<tbody id="xswjxx" style="display: block">
						<tr bgcolor="#D0E0EE">
							<td align="center">ѧ��</td>
							<td align="center">����</td>
							<td align="center">�Ա�</td>
							<td align="center"><bean:message key="lable.xsgzyxpzxy" /></td>
							<td align="center">רҵ</td>
							<td align="center">�༶</td>
						</tr>
						<logic:iterate id="zsjlxx" name="zsjlXsList">
							<tr>
								<td><bean:write name="zsjlxx" property="xh"/></td>
								<td><bean:write name="zsjlxx" property="xm"/></td>
								<td><bean:write name="zsjlxx" property="xb"/></td>
								<td><bean:write name="zsjlxx" property="xymc"/></td>
								<td><bean:write name="zsjlxx" property="zymc"/></td>
								<td><bean:write name="zsjlxx" property="bjmc"/></td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				<div class="buttontool" align="center">
					<button class="button2" onclick="dataSave()" style="width:80px" id="buttonSave">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="Close();return false;" style="width:80px" id="buttonClose">
						�� ��
					</button>
				</div>
			</logic:notEmpty>
			<logic:equal value="true" name="done">
				<script>
					alert("�����ɹ�!");
					Close();
					dialogArgumentsQueryChick();
				</script>
			</logic:equal>
			<logic:equal value="fakse" name="done">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:equal>	
		</html:form>
  </body>
</html>
