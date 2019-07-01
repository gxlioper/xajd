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
	<base target="_self">
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
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
	<script language="javascript">
	function tzxmInfoTo(){
    if($('xh').value==""){
        alert("ѧ�Ų���Ϊ�գ�");
        return false;
    }else{
        var url = "/xgxt/sztz_xm_query.do?xh="+$('xh').value+"&url="+$('url').value;        
        showTopWin(url,750,550);            
    }
    }
    function xfsbSave(){
    	 var elements = new Array();
	     elements = $("xn").value.split("-");
	     var nd_val = $("nd").value;
	     if (!inArray(nd_val, elements)) {
			alert("ѧ�ꡢ��Ȳ�һ��,������Ҫ��������ݣ�");
			return false;
		 }      
         SztzDataDoSaveApply('/xgxt/sztz_applySave.do','xn-nd-xq-xmdm','sztz_xfsbb','xh-nd-xn-xq-xmdm-lydm');      
    }
    
    </script>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/sztz_xf_sb" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰλ�ã�������չ - ��չѧ�ָ����걨 - ��д�걨��
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:equal value="1" name="sqsjTag">
				<br />
				<br />
				<p align="center">
					<font color="red">�����趨ʱ�䷶Χ��,�ݲ������걨!</font>
				</p>
			</logic:equal>
			<logic:notEqual value="1" name="sqsjTag">
				<logic:notEmpty name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
    					alert("�������ѧ����Ч!");
    				</script>
					</logic:equal>
					<logic:equal name="dataSaved" value="ok" scope="request">
						<script>
    					alert("����ɹ���");
    				</script>
					</logic:equal>
					<input type="hidden" id="disableEle" name="disableEle"
						value="xm-xb-xy-nj-zy-bj" />
					<input type="hidden" id="getStuInfo" name="getStuInfo"
						value="xm-xb-xymc-nj-zymc-bjmc" />
					<input type="hidden" id="url" name="url" value="/sztz_xf_sb.do" />
					<fieldset>
						<legend>
							��д�걨��
						</legend>
						<table width="100%" id="rsT" class="tbstyle">
							<thead>
								<tr style="height:22px">
									<td colspan="4" align="center">
										<b>��д�걨��</b>
									</td>
								</tr>
							</thead>
							<tr style="height:22px">
								<logic:equal name="userOnLine" value="teacher" scope="session">
									<td align="right" style="width: 5%">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left" style="width: 20%">
										<html:text name='rs' property="xh" styleId="xh"
											readonly="true"
											onkeypress="if(event.keyCode == 13) return false;" />
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu">
											ѡ��
										</button>
									</td>
								</logic:equal>
								<logic:equal name="userOnLine" value="student" scope="session">
									<td align="right" style="width: 8%">
										<font color="red">*</font>ѧ�ţ�
									</td>
									<td align="left" style="width: 27%">
										<input type="text" id="xh" name="xh"
											value="<bean:write name='rs' property="xh" />"
											readonly="readonly" />
									</td>
								</logic:equal>
								<td align="right">
									<font color="red">*</font>��չ�(��Ŀ)��
								</td>
								<td align="left">
									<input type="hidden" name="xmdm"
										value="<bean:write name="xmdm" scope="request"/>" id="xmdm">
									<html:text name='rs' property="xmmc" styleId="xmmc"
										readonly="true" style="width:250px" />
									<button onclick="tzxmInfoTo()" class="button2">
										ѡ��
									</button>
								</td>
								<%--								<td align="right" style="width: 10%">--%>
								<%--									<font color="red">*</font>��ȣ�--%>
<%--								</td>--%>
<%--								<td align="left" style="width: 20%">--%>
<%--									<html:select property="nd"--%>
<%--										style="width:90px;background-color:#FFFFFF" styleId="nd">--%>
<%--										<html:options collection="xnList" property="nd"--%>
<%--											labelProperty="nd" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
							</tr>
							<tr style="height:22px">
								<td align="right">
									������
								</td>
								<td align="left">
									<bean:write name='rs' property="xm" />
								</td>
								<td align="right">
									<font color="red">*</font>ѧ�꣺
								</td>
								<td align="left">
									<html:text name="rs" property="xn" readonly="true"  styleId="xn"></html:text>
								</td>
								<%--								<td align="right">--%>
<%--									<font color="red">*</font>ѧ�꣺--%>
<%--								</td>--%>
<%--								<td align="left">--%>
<%--									<html:select property="xn"--%>
<%--										onchange="refreshForm('/xgxt/sztz_xf_sb.do')"--%>
<%--										style="width:90px;background-color:#FFFFFF" styleId="xn">--%>
<%--										<html:options collection="xnList" property="xn"--%>
<%--											labelProperty="xn" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<bean:write name='rs' property="xb" />
								</td>
								<td align="right">
									<font color="red">*</font>ѧ�ڣ�
								</td>
								<td align="left">
									<html:text name="rs" property="xqmc" readonly="true" styleId="xqmc"></html:text>									
                                    <input type="hidden" id="xq" name="xq" value="${rs.xq}" />
								</td>								
<%--								<td align="right">--%>
<%--									<font color="red">*</font>ѧ�ڣ�--%>
<%--								</td>--%>
<%--								<td align="left">--%>
<%--									<html:select property="xq"--%>
<%--										onchange="refreshForm('/xgxt/sztz_xf_sb.do')"--%>
<%--										style="width:90px;background-color:#FFFFFF" styleId="xq">--%>
<%--										<html:option value=""></html:option>--%>
<%--										<html:options collection="xqList" property="xqdm"--%>
<%--											labelProperty="xqmc" />--%>
<%--									</html:select>--%>
<%--								</td>--%>
							</tr>
							<tr style="height:22px">
								<td align="right">
									�꼶��
								</td>
								<td align="left">
									<input type="hidden" id="xsnj" name="xsnj"
										value="<bean:write name='rs' property="nj" />" />
									<bean:write name='rs' property="nj" />
								</td>
								<td align="right" style="width: 10%">
									<font color="red">*</font>��ȣ�
								</td>
								<td align="left" style="width: 20%">
									<html:select property="nd"
										style="width:90px;background-color:#FFFFFF" styleId="nd">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</td>
								<%--								<td align="right">--%>
<%--									<font color="red">*</font>��չ�(��Ŀ)��--%>
<%--								</td>--%>
<%--								<td align="left">--%>
<%--									<html:select property="xmdm" styleId="xmdm"--%>
<%--										onchange="refreshForm('/xgxt/sztz_xf_sb.do')"--%>
<%--										style="background-color:#FFFFFF">--%>
<%--										<html:option value=""></html:option>--%>
<%--										<html:options collection="tzxmList" property="xmdm"--%>
<%--											labelProperty="xmmc" />--%>
<%--									</html:select>--%>
<%--							 <input type="hidden" name="xmdm" value="<bean:write name="xmdm" scope="request"/>">--%>
<%--						     <html:text name='rs' property="xmmc" styleId="xmmc" readonly="true" style="width:250px"/>--%>
<%--							  <button onclick="tzxmInfoTo()"class="button2" >--%>
<%--										ѡ��--%>
<%--							  </button>--%>
<%--								</td>--%>
							</tr>
							<tr style="height:22px">
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />��
								</td>
								<td align="left">
									<bean:write name='rs' property="xymc" />
								</td>
								<td align="right">
									������Ŀ��
								</td>
								<td align="left">
									<html:text name="rs" property="kmm" readonly="true"
										style="cursor:hand" />
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
									<font color="red">*</font>�걨���ɣ�
								</td>
								<td>
									<html:select property="lydm" styleId="lydm"
										onchange="refreshForm('/xgxt/sztz_xf_sb.do')"
										style="background-color:#FFFFFF">
										<html:option value=""></html:option>
										<html:options collection="lyList" property="lydm"
											labelProperty="lymc" />
									</html:select>
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
									��չ������
								</td>
								<td align="left">
									<html:select property="jbdm"
										style="width:90px;background-color:#FFFFFF" styleId="jbdm"
										onchange="refreshForm('/xgxt/sztz_xf_sb.do')">
										<html:option value=""></html:option>
										<html:options collection="hjjbList" property="jbdm"
											labelProperty="jbmc" />
									</html:select>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									�����
								</td>
								<td align="left">
									<html:select property="cjtzxmjb"
										style="width:90px;background-color:#FFFFFF" styleId="cjtzxmjb">
										<html:option value=""></html:option>
										<html:options collection="cjxmjbList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td align="right">
									����ѧ�֣�
								</td>
								<td align="left">
									<html:text name="rs" property="xf" readonly="true"
										styleId="pxjssj" onkeypress="return sztzNumInputValue(this,4,event)"
										onblur="chkInput(this,event)" maxlength="4" style="width:90px;" />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									�μ����ʣ�
								</td>
								<td align="left" colspan="3" width="90%">
									<html:select property="tzxmcjsf"
										style="width:90px;background-color:#FFFFFF" styleId="tzxmcjsf">
										<html:option value=""></html:option>
										<html:options collection="tzxmcjsfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									���ɣ�
								</td>
								<td colspan="3">
									<bean:write name="rs" property="lynr" />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									�ɹ�������
								</td>
								<td colspan="3">
									<html:textarea property='cgms' styleId="cgms" style="width:99%"
										rows='5' />
								</td>
							</tr>
						</table>
						<div class="buttontool" align="center">
							<button class="button2" id="buttonSave"
								onclick="xfsbSave()">
								�� �� �� ��
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
								onclick="expAppTab('rsT','������չѧ�ָ����걨��','')">
								�� ӡ �� ��
							</button>
						</div>
					</fieldset>
				</logic:notEmpty>
				<logic:present name="isPASS">
					<logic:match value="is" name="isPASS">
						<script>
    					alert("����ȡ�ѧ�ꡢѧ�ڸ���չ��Ŀ�ɼ��걨\n��������л���ͨ�����������걨��");
    				</script>
					</logic:match>
				</logic:present>
			</logic:notEqual>
		</html:form>
	</body>
</html>
