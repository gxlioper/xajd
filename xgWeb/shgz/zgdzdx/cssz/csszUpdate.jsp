<%@ page language="java" contentType="text/html; charset=GBK"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" />
		</title>
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
	function saveCssz(obj){
		if($("hddm").value == ""){
			alert("����Ʋ���Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if($("xn").value == ""){
			alert("ѧ�겻��Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if($("zd").value == ""){
			alert("�ֶβ���Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		if($("zdm").value == ""){
			alert("�ֶ�������Ϊ�գ���ȷ�ϣ���");
			return false;
		}
		showTips('���������У���ȴ�......');
		refreshForm('/xgxt/zgddShgzCssz.do?method=csszUpdate&doType=save');
		obj.disabled=true;
		
<%--		if($("hdV")&&$("hdV")!=null){--%>
<%--			url+="&hdV="+$("hdV").value;--%>
<%--		}--%>
<%--		if($("zdV")&&$("zdV")!=null){--%>
<%--			url+="&zdV="+$("zdV").value;--%>
<%--		}--%>
<%--		if($("xnV")&&$("xnV")!=null){--%>
<%--			url+="&xnV="+$("xnV").value;--%>
<%--		}--%>
	}
	
	function checkZd(obj,zbid){
		var pk = $("hddm").value+$("xn").value+zbid;
		getCsszDAO.getZd(pk,function(data){
			if (data != null) {
				if(data != "0"){
					alert("�û�У����ֶ��Ѿ����ڣ�����������");
					obj.value="";
					obj.focus();
				}
			}
		});
	}
	
	function chHd(hddm){
		var hddm = $("hddm").value;
		var xn = $("xn").value;
		if(hddm == "" || xn == ""){
			$("zd").disabled=true;
			$("zd").value= "";
		}else{
			$("zd").disabled=false;
		}
	}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCsszDAO.js'></script>
		<html:form action="/zgddShgzCssz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>��Ṥ�� - �������� - ��������</a>
				</p>
			</div>

			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />

				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ֶι���</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"Ϊ������
									</div>
									<div class="btn">
										<logic:notEqual name="doType" value="view">
											<button class="" onclick="saveCssz(this);" style="width:80px"
												id="buttonSave">
												����
											</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										</logic:notEqual>
										<button class="" onclick="Close();return false;" style="width:80px"
											id="buttonClose">
											�ر�
										</button>
									</div>
									</div>
								</td>
							</tr>
						</tfoot>


						<tbody>
							<tr>
								<td align="right" style="width:50%">
									�����
								</td>
								<td align="left" style="width:50%">
									<logic:equal name="doType" value="add">
										<html:select name="rs" property="hddm" style=""
											onchange="chHd()">
											<html:option value=""></html:option>
											<html:options collection="hdList" property="hddm"
												labelProperty="hdmc" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<input type="hidden" id="hdV" name="hdV"
											value="<bean:write name="rs" property="hddm"/>" />
										<html:select name="rs" property="hddm" style=""
											disabled="true">
											<html:option value=""></html:option>
											<html:options collection="hdList" property="hddm"
												labelProperty="hdmc" />
										</html:select>
									</logic:notEqual>
								</td>
							</tr>
							<tr>
								<td align="right" style="width:50%">
									ѧ��
								</td>
								<td align="left">
									<logic:equal name="doType" value="add">
										<html:select name="rs" property="xn" style=""
											onchange="chHd()">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</logic:equal>
									<logic:notEqual name="doType" value="add">
										<html:select name="rs" property="xn" style="" disabled="true">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</logic:notEqual>
									<input type="hidden" id="xnV" name="xnV"
										value="<bean:write name="rs" property="xn"/>" />
								</td>
							</tr>
							<tr>
								<td align="right">
									�ֶ�
								</td>
								<td align="left">
									<html:text name='rs' property="zd" styleId="zd"
										onblur="checkZd(this,this.value)" disabled="true" />
									<input type="hidden" id="zdV" name="zdV"
										value="<bean:write name="rs" property="zd"/>" />
								</td>
							</tr>
							<tr>
								<td align="right">
									�ֶ���
								</td>
								<td align="left">
									<html:text name='rs' property="zdm" styleId="zdm" />
								</td>
							</tr>
							<tr>
								<td align="right">
									�ֶ�����
								</td>
								<td align="left">
									<html:select name="rs" property="zdlx">
										<html:option value="001">�ı�</html:option>
										<html:option value="002">ʱ��</html:option>
										<html:option value="003">�ı���</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<font color="red">ע�ֶν�������ƴ����Ӣ����д������ָ����ʦ��Ϊzdls��������¼�봿���ֻ��֣�</font>
									<font color="red">�ֶ�������¼��������ĺ��ֻ�Ӣ�ġ�</font>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal name="result" value="yes">
					<script>
				    alert("�ύ�ɹ���");
				    dialogArgumentsQueryChick();
					Close();
				    </script>
				</logic:equal>
				<logic:equal name="result" value="no">
					<script>
				    alert("�ύʧ�ܣ�");
				    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
