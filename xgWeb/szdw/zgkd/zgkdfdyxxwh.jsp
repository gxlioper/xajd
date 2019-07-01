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
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />	
	<script language="javascript">
	function saveFdyxx(mustFill,dzyx){
		var eles = mustFill.split("-");
    	var zgh=document.getElementById("xh").value;
    	var doType=document.getElementById("doType").value;
		for(i = 0;i<eles.length;i++){
			if(document.getElementById(eles[i]).value == ""){
				alert("�뽫��\"*\"�ŵ���Ŀ����������");
				return false;
			}
		}
		if(document.getElementById(dzyx).value!=""){
		   if(isEmail(document.getElementById(dzyx).value) == false){
				alert("���������ַ������");
				return false;
			}
		}
		if($("zyzz")){
			if (document.getElementById("zyzz").value.length >2000) {
			alert("��Ҫְ��ĳ��Ȳ��ܳ���2000����!");
			return false;
			}
		}
		if (document.getElementById("grhjqk").value.length >2000) {
			alert("���˻�����ĳ��Ȳ��ܳ���2000����!");
			return false;
		}
		if($("fblw")){
		if (document.getElementById("fblw").value.length >2000) {
			alert("�������ĵĳ��Ȳ��ܳ���2000����!");
			return false;
		}
		}
		if($("kyjl")){
		if (document.getElementById("kyjl").value.length >2000) {
			alert("���о����ĳ��Ȳ��ܳ���2000����!");
			return false;
		}
		}
		if (document.getElementById("gzjl").value.length >2000) {
			alert("���������ĳ��Ȳ��ܳ���2000����!");
			return false;
		}
		if (document.getElementById("bz").value.length >2000) {
			alert("��ע�ĳ��Ȳ��ܳ���2000����!");
			return false;
		}
		if(doType=="add"){	
	    getXjydInfo.getColumnEx("fdyxxb","zgh",zgh,function(data){				
			if(data==true){
				alert('��ְ�����Ѵ��ڣ�');
				document.getElementById("xh").focus();
				return false;
			}else{
			   refreshForm("/xgxt/fdyxxOne.do?act=save");
			}
	});
	}else{
	    refreshForm("/xgxt/fdyxxOne.do?act=save");
	}	
	} 
	
	function writable()
	{
		var userType =document.all['userType'].value;
		if("xy"==userType)
		{
			document.forms[0].buttonModi.style.display = "none";
		}
		else if("xx"==userType||"admin"==userType)
		{
			
		}
	}
	
	function getQtfgnjValue(){
  	 	 var getSelectValue = document.getElementById("qtfgnjChoose").value;
  	 	 for(var i = 0;i<document.getElementById("qtfgnjChoose").options.length;i++){
  	 	 	if(document.getElementById("qtfgnjChoose").options[i].value ==getSelectValue){
  	 	 		document.getElementById("qtfgnjChoose").options[i]=null;
  	 	 	}
  	 	 }
  	 	 if(document.getElementById("qtfgnj").value.length==0){
  		 	document.getElementById("qtfgnj").value = getSelectValue;
  		 }else{
  		 	document.getElementById("qtfgnj").value += ",";
  		 	document.getElementById("qtfgnj").value += getSelectValue;
  		 }         
	}
	
	function qtfgnjOptions(){
		if($("fgnj")){
		var getSelectValue = document.getElementById("fgnj").value;
  	 	 for(var i = 0;i<document.getElementById("qtfgnjChoose").options.length;i++){
  	 	 	if(document.getElementById("qtfgnjChoose").options[i].value ==getSelectValue){
  	 	 		document.getElementById("qtfgnjChoose").options[i]=null;
  	 	 	}
  	 	 }
  	 	 }
	}

</script>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body  onload="xyDisabled('szbm');writable();qtfgnjOptions();" >
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:present name="added">
				<logic:present name="ddxg">
				<logic:equal value="ok" name="added">
					<script>
			          alert("�����ɹ���");
					  refreshForm("fdyxxOne.do?act=view&type=one");
			        </script>
				</logic:equal>
				<logic:equal value="no" name="added">
					<script>
			          alert("����ʧ�ܣ�");
					  refreshForm("fdyxxOne.do?act=view&type=one");
			        </script>
				</logic:equal>
				</logic:present>
				<logic:notPresent name="ddxg">
				<logic:equal value="ok" name="added">
					<script>
			          alert("�����ɹ���");
					  dialogArgumentsQueryChick();
			          Close();
			        </script>
				</logic:equal>
				<logic:equal value="no" name="added">
					<script>
			          alert("����ʧ�ܣ�");
					  dialogArgumentsQueryChick();
			          Close();
			        </script>
				</logic:equal>
				</logic:notPresent>
			</logic:present>
			<logic:present name="ffdy">
				<script>
				alert("�����Ǹ���Ա�������޸ĵǼ��Լ�����Ϣ!");
    		 	location.href="about:blank";
    		 	 </script>
			</logic:present>
			<logic:notPresent name="added">
				<logic:empty name="rs">
					<p align="center">
						�д�������
					</p>
				</logic:empty>
				<logic:notEmpty name="rs">
					<logic:equal name="rs" property="stuExists" value="no">
						<script>
					    alert("�������ѧ����Ч!");
					    </script>
					</logic:equal>
					<input type="hidden" id="doType" name="doType"
						value="${doType }" />
					<input type="hidden" id="userType" name="userType"
						value="${userType }" />
					<input type="hidden" id="pkValue" name="pkValue"
						value="${pkValue }" />
					<input type="hidden" id="disableEle" name="disableEle" value="" />
					<input type="hidden" id="readonlyEle" name="readonlyEle"
						value="zgh" />
					<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
					<input type="hidden" id="url" name="url" value="/sjcz/xspxxxb.jsp" />
					<logic:present name="isAhxgOne">  
						<input type="hidden" id="xsgzyjfx" name="xsgzyjfx" value="<bean:write name='rs' property="xsgzyjfx"/>" />
						<input type="hidden" id="zyzz" name="zyzz" value="<bean:write name='rs' property="zyzz"/>" />
					</logic:present>
					<logic:present name="ddxg">
						<input type="hidden" id="ddxg" name="ddxg" value="yes" />
					</logic:present>
					<fieldset>
						<legend>
							˼��������Ϣά��
						</legend>
						<table width="100%" class="tbstyle">
							<tr>
								<logic:present name="ddxg">
									<td align="right">
										<font color="red">*</font>���ţ�
									</td>
									<td align="left">
										<html:text name='rs' property="zgh" readonly="true"
											styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									</td>
								</logic:present>
								<logic:notPresent name="ddxg">
								<td align="right">
									<font color="red">*</font>���ţ�
								</td>
								<td align="left">
									<html:text name='rs' property="zgh"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								</td>
								</logic:notPresent>
								<td align="right">
									<font color="red">*</font>������
								</td>
								<td align="left">
									<html:text name='rs' property="xm" styleId="xm" />
									<input name="buttonFindStu" type="hidden">
								</td>
								<td align="left" width="15%" rowspan="6">
									<img border="0" style="height:133px;width:100px;"
										src="/xgxt/pictures/fdyZp/<bean:write name="rs" property="zgh" />.jpg">
								</td>
							</tr>
							<tr>
								<td align="right">
									�Ա�
								</td>
								<td align="left">
									<html:select name='rs' property="xb" styleId="xb">
										<html:option value=""></html:option>
										<html:options collection="xbList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<logic:notEqual name="xxdm" value="10290" scope="session">
								<td align="right">
									<font color="red">*</font>��ϵ�绰��
								</td>
								<td align="left">
									<html:text name='rs' property="lxdh" styleId="lxdh" />
								</td>
								</logic:notEqual>
								<logic:equal name="xxdm" value="10290" scope="session">
								<td align="right">
									�칫�绰��
								</td>
								<td align="left">
									<html:text name='rs' property="bgdh" maxlength="15" styleId="bgdh" />
								</td>
								</logic:equal>
							</tr>
							<tr>
								<td align="right">
									�ƶ��绰��
								</td>
								<td align="left">
									<html:text name='rs' property="yddh" styleId="yddh" />
								</td>
								<td align="right">
									�������䣺
								</td>
								<td align="left">
									<html:text name='rs' property="dzyx" styleId="dzyx" />
								</td>
							</tr>
							<tr>
								<td align="right">
									���壺
								</td>
								<td align="left">
									<html:select name="rs" property="mz" style="width:140px"
										styleId="mz">
										<html:options collection="mzList" property="mzdm"
											labelProperty="mzmc" />
									</html:select>
								</td>
								<td align="right">
									���᣺
								</td>
								<td align="left">
									<html:select name="rs" property="jg" style="width:100px"
										styleId="zwdm">
										<html:options collection="sfList" property="sfdm"
											labelProperty="sfmc" />
									</html:select>
								</td>
							</tr>
							<tr>
		    					<td width="16%" height="20%"><div align="right">��������</div></td>
		    					<td width="37%" height="20%"><html:text name='rs' property="csrq" styleId="csrq" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('csrq','y-mm-dd');" />			
								</td>
		 						<td width="16%" height="20%"><div align="right">����Ա�ϸ�����</div></td>
		    					<td width="37%" height="20%"><html:text name='rs' property="lxgzsj" styleId="lxgzsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('lxgzsj','y-mm-dd');" />			
								</td>
							</tr>
							<tr>
								<td align="right">
									������ò:
								</td>
								<td align="left">
									<html:select name='rs' property="zzmm" styleId="zzmm">
										<html:option value=""></html:option>
										<html:options collection="JsZzmmList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td align="right">
									ѧ��:
								</td>
								<td align="left">
									<html:select name='rs' property="xl" styleId="xl">
										<html:option value=""></html:option>
										<html:options collection="JsXlList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
							<logic:present name="isZZSF">
							    <logic:equal value="yes" name="isZZSF">
							         <tr>
								<td align="right">
									ѧλ:
								</td>
								<td align="left">
									<html:text name='rs' property="xw" maxlength="15" styleId="xw" />
								</td>
								<td align="right">
									����ʱ��:
								</td>
								<td width="37%" height="20%"><html:text name='rs' property="rwsj" styleId="rwsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('rwsj','y-mm-dd');" />			
								</td>
							</tr>  
							    </logic:equal>
							</logic:present>
							<logic:notPresent name="isZZSF">
						    <tr>
								<td align="right">
									ѧλ:
								</td>
								<td align="left">
									<html:text name='rs' property="xw" maxlength="15" styleId="xw" />
								</td>
								<logic:notEqual name="xxdm" value="10290" scope="session">
								<td align="right">
									�ϼ���λ:
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="sjdw" maxlength="30" styleId="xw" />			
								</td>
								</logic:notEqual>
								<logic:equal name="xxdm" value="10290" scope="session">
								<td align="right">
									���棺
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="cz" maxlength="15" styleId="cz" />
								</td>
								</logic:equal>
							</tr>  
							</logic:notPresent>
							<logic:equal name="xxdm" value="10290" scope="session">
							<tr>
								<td align="right">
									��Ҫ�ֹ��꼶��
								</td>
								<td align="left">
									<html:select name = "rs" property="fgnj" style="width:60px" styleId="nj" onchange="qtfgnjOptions();"> 
	          							<html:option value=""></html:option> 
	          							<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        							</html:select>
								</td>
								<td align="right">
									�����ֹ��꼶��
								</td>
								<td align="left">
									<html:text name="rs"  property="qtfgnj" styleId="qtfgnj" style="width:150px;height:21px;font-size:10pt;" />
									<span style="width:18px;border:0px solid red;">
									<html:select name = "rs" property="qtfgnjChoose" style="margin-left:-150px;width:168px; background-color:#FFEEEE;" styleId="qtfgnjChoose" onchange="getQtfgnjValue();"> 
	          							<html:option value=""></html:option> 
	          							<html:options collection="njList" property="nj" labelProperty="nj" /> 
	        						</html:select>
	        						</span>
								</td>
							</tr>
							</logic:equal>
							<tr>
								<td align="right">
									��ҵԺУ��
								</td>
								<td align="left">
									<html:text name='rs' property="byyx" maxlength="15" styleId="byyx" />
								</td>
								<td align="right">
									��ѧרҵ��
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="sxzy" maxlength="15" styleId="sxzy" />
								</td>
							</tr>
							<logic:equal name="xxdm" value="10290" scope="session">
							<tr>
								<td align="right">
									�ڶ�ѧ����
								</td>
								<td align="left">
									<html:text name='rs' property="byyx" maxlength="15" styleId="zdxl" />
								</td>
								<td align="right">
									�ڶ�רҵ��
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="sxzy" maxlength="15" styleId="sxzy" />
								</td>
							</tr>
							</logic:equal>
							<tr>
								<td align="right">
									<font color="red">*</font>���ڲ��ţ�
								</td>
								<td align="left">
									<html:select name="rs" property="bmdm" style="width:140px"
										styleId="szbm">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select>
								</td>
								<td align="right">
									<font color="red">*</font>ְ��
								</td>
								<td align="left" colspan="3">
									<html:select name="rs" property="zw" style="width:100px"
										styleId="zwdm">
										<html:option value=""></html:option>
										<html:options collection="zwList" property="zwdm"
											labelProperty="zwmc" />
									</html:select>
								</td>
							</tr>
							<logic:equal value="10878" name="xxdm" scope="session">
							<tr>
								<td align="right">
									��ְ����
								</td>
								<td align="left" colspan="5">
									<html:text name='rs' property="jrgz" styleId="jrgz" style="width:90%" />
								</td>
							</tr>
							</logic:equal>
							<tr>
								<td align="right">
									��ְʱ�䣺
								</td>
								<td align="left">
									<html:text name='rs' property="zwrzsj" styleId="zwrzsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('zwrzsj','y-mm-dd');" />			
								</td>
								<td align="right">
									ְ�ƻ��ʱ��:
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="jsrzsj" styleId="jsrzsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('jsrzsj','y-mm-dd');" />			
								</td>
							</tr>		
							<tr>
								<td align="right">
									ְ�ƣ�
								</td>
								<td align="left">
									<html:text name='rs' property="zc" styleId="zc" />
								</td>
								<logic:equal name="xxdm" value="11407" scope="session">
								<td align="right">
									<font color="red">*</font>רҵ��
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="xsgzyjfx" styleId="xsgzyjfx" />
								</td>
								</logic:equal>
								<logic:notEqual name="xxdm" value="11407" scope="session">
								<logic:notPresent name="isAhxgOne">  
								<td align="right">
									ѧ�������о�����
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="xsgzyjfx" styleId="xsgzyjfx" />
								</td>
								</logic:notPresent>
								<logic:present name="isAhxgOne">  
								<td align="right">
								</td>
								<td align="left" colspan="3">
								</td>
								</logic:present>
								</logic:notEqual>
							</tr>							
							<tr>
								<td align="right">
									��ͥסַ��
									<br><font color="red" >(��Ҫ����40��)</font>
								</td>
								<td align="left" colspan="5">
									<html:text name='rs' property="jtzz" styleId="jtzz" style="width:90%" onblur="chLeng(this,40)" />
								</td>
							</tr>
							<tr>
								<td align="right">
									ͨѶ��ַ��
									<br><font color="red">(��Ҫ����40��)</font>
								</td>
								<td align="left" colspan="5">
									<html:text name='rs' property="txdz" styleId="txdz" style="width:90%" onblur="chLeng(this,40)"/>
								</td>
							</tr>
							<tr>
								<td align="right">
									�칫�ص㣺
									<br><font color="red">(��Ҫ����50��)</font>
								</td>
								<td align="left" colspan="5">
									<html:text name='rs' property="bgdd" styleId="bgdd" style="width:90%" onblur="chLeng(this,50)"/>
								</td>
							</tr>
							<logic:notEqual name="xxdm" value="10290" scope="session">
							<tr>
								<td align="right">
									�칫�绰��
								</td>
								<td align="left">
									<html:text name='rs' property="bgdh" maxlength="15" styleId="bgdh" />
								</td>
								<td align="right">
									���棺
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="cz" maxlength="15" styleId="cz" />
								</td>
							</tr>
							</logic:notEqual>
							<tr align="left">
								
								<td align="right">
									����ϵ��
								</td>
								<td>
									<logic:present name="fdyzyList">
									<logic:iterate id="s" name="fdyzyList">
	                    				&nbsp;<bean:write name="s" />&nbsp;&nbsp;
	      							</logic:iterate>
	      							</logic:present>
								</td>
								<td align="right">
									�ʱࣺ
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="yzbm" styleId="yzbm" maxlength="6" />
								</td>
							</tr> 
							<tr align="left">
								<td align="right">
									����༶:
								</td>
								<td colspan="5">
								<logic:present name="fdybjList">
									<logic:iterate id="s" name="fdybjList">
	                    				&nbsp;<bean:write name="s" />&nbsp;&nbsp;
	      							</logic:iterate>
	      						</logic:present>
								</td>
							</tr>
							<logic:present name="isZZSF">
							    <logic:equal value="yes" name="isZZSF">
							    <tr align="left">
								<td align="right">
									�����ֹ���
								</td>
								<td colspan="3">
									<html:textarea name='rs' property='gzfg' style="width:99%"
										rows='4' />
								</td>
							    </tr>
							    </logic:equal>
							</logic:present>  
							<logic:notPresent name="isAhxgOne">   
							<tr align="left">
								<td align="right">
									��Ҫְ��
									<br><font color="red">(��Ҫ����200��)</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='zyzz' styleId= "zyzz" style="width:99%"
										rows='4' onblur="chLeng(this,200)"/>
								</td>
							</tr>
							</logic:notPresent> 
							<tr align="left">
								<td align="right">
									���˻������
									<br><font color="red">(��Ҫ����500��)</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='grhjqk' styleId="grhjqk" style="width:99%"
										rows='5' onblur="chLeng(this,500)"/>
								</td>
							</tr> 
							<logic:equal name="xxdm" value="10290" scope="session">
							<tr align="left">
								<td align="right">
									��������ѧ��������
									<br><font color="red">(��Ҫ����400��)</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='gdxlzl' styleId="gdxlzl" style="width:99%"
										rows='5' onblur="chLeng(this,400)"/>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									��Ҫ�о��ɹ���
									<br><font color="red">(��Ҫ����400��)</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='zyyjjg' styleId="zyyjjg" style="width:99%"
										rows='5' onblur="chLeng(this,400)"/>
								</td>
							</tr>
							</logic:equal>
							<logic:notEqual name = "xxdm" value="10491" scope="session">
							<tr align="left">
								<td align="right">
									�������ģ�
									<br><font color="red">(��Ҫ����500��)</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='fblw' styleId="fblw" style="width:99%"
										rows='4' onblur="chLeng(this,500)"/>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									���о�����
									<br><font color="red">(��Ҫ����400��)</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='kyjl' styleId="kyjl" style="width:99%"
										rows='5' onblur="chLeng(this,400)"/>
								</td>
							</tr>
							</logic:notEqual>
							<logic:equal name = "xxdm" value="10491" scope="session">
							<tr align="left">
								<td align="right">
									�������ģ�
								</td>
								<td colspan="5">
									<logic:present name="lwxx">
									<logic:iterate id="lw" name ="lwxx">
										<bean:write name="lw" /></br>
									</logic:iterate>
									</logic:present>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									���о�����
								</td>
								<td colspan="5">
									<logic:present name="kyjl">
									<logic:iterate id="ky" name ="kyjl">
										<bean:write name="ky" /></br>
									</logic:iterate>
									</logic:present>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									����Ա������
								</td>
								<td colspan="5">
									<logic:present name="fdyzz">
									<logic:iterate id="zz" name ="fdyzz">
										<bean:write name="zz" /></br>
									</logic:iterate>
									</logic:present>
								</td>
							</tr>
							</logic:equal>
							<tr align="left">
								<td align="right">
									����������
									<br><font color="red">(��Ҫ����500��)</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='gzjl' styleId="gzjl" style="width:99%"
										rows='8' onblur="chLeng(this,500)"/>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									��ע��
									<br><font color="red">(��Ҫ����100��)</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='bz' styleId="bz" style="width:99%"
										rows='4' onblur="chLeng(this,100)"/>
								</td>
							</tr>
						</table>
					</fieldset>
					<div class="buttontool">
						
						<button type="button" class="button2" onclick="dataCanModi(true)"
							style="width:0px" id="buttonModi" >
							�� ��
						</button>
						<logic:present name="writeAble2">
						<button type="button" class="button2" onclick="saveFdyxx('xh-xm-bmdm-zw','dzyx');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>
						</logic:present>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
							id="buttonClose">
							�� ��
						</button>
					</div>
				</logic:notEmpty>
			</logic:notPresent>
		</html:form>
	</body>
</html>
