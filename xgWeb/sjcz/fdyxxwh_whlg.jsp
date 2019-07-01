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
	function saveFdyxx(dzyx){
		var mustFill = "zgh-xb-yddh-xm-lxdh-dzyx-zhxwssxk-szbm-sfbl-zc-xsgzyjfx";
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
		if(doType=="add"){	
	    getXjydInfo.getColumnEx("fdyxxb","zgh",zgh,function(data){				
			if(data==true){
				alert('��ְ�����Ѵ��ڣ�');
				document.getElementById("xh").focus();
				return false;
			}else{
				beforeSubmit();
			    refreshForm("/xgxt/fdyxxOne_whlg.do?act=save");
			}
	});
	}else{
		beforeSubmit();
	    refreshForm("/xgxt/fdyxxOne_whlg.do?act=save");
	}	
	}
	
	function beforeSubmit(){
		var str = "xh-xm-xb-lxdh-yddh-dzyx-sfzh-cjgzrq-szbm-sfdm-csrq-szgzsj-zzmm-xl-xw-"
		+"lxgzsj-bkbyyx-bksxzy-ssbyyx-ssbyzy-bsbyyx-bsbyzy-zhxwssxk-csgz-sfbl-mzdm"
	    +"-zcdm-xsgzyjfx-jtzz-djsj-grhjqk-fblw-kyjl-gzjl-pxqk-bz-jb-zwdm-zyzz-csgz";
	    realiseElemDisabled(str);
	}
	function judgeUserJb(){
		var userJb = document.getElementById("userJb").value;
		//buttons
		//var buttonModi = document.getElementById("buttonModi");
		var buttonSave = document.getElementById("buttonSave");
		var buttonClose = document.getElementById("buttonClose");
		if(userJb == "fdy"){
			//buttonModi.style.display = "none";
			buttonClose.style.display = "none";
			var str = "zwdm-zyzz-csgz-jb-bz";
			setElemDisabled(str);
		}else if(userJb == "xy"){
			/*
			var act = document.getElementById("act").value;
			if(act == "view"){
				buttonSave.style.display = "none";
			}
			*/
			var str = "xh-xm-xb-lxdh-yddh-dzyx-sfzh-cjgzrq-szbm-sfdm-csrq-szgzsj-zzmm-xl-xw-"
				      +"lxgzsj-bkbyyx-bksxzy-ssbyyx-ssbyzy-bsbyyx-bsbyzy-zhxwssxk-csgz-sfbl-mzdm"
				      +"-zcdm-xsgzyjfx-jtzz-djsj-grhjqk-fblw-kyjl-gzjl-pxqk-bz-jb";
			setElemDisabled(str);	      
		}else if(userJb == "xx"){
			var doType=document.getElementById("act").value;
			//if(doType == "save"){
			//	buttonModi.style.display = "none";
			//}else if(doType == "modi"){
			//	buttonSave.style.display = "none";
			//}
		}else if (userJb == "stu"){
			//buttonModi.style.display = "none";
			buttonClose.style.display = "none";
			//buttonSave.style.display = "none";
		}
	}
	
	function setElemDisabled(str){
		var splitEle = str.split("-");
		for(var i=0;i<splitEle.length;i++){
			document.getElementById(splitEle[i]).disabled = true;
		}
	}
	
	function realiseElemDisabled(str){
		var splitEle = str.split("-");
		for(var i=0;i<splitEle.length;i++){
			document.getElementById(splitEle[i]).disabled = false;
		}
	}
</script>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body  onload="judgeUserJb()">&nbsp; 
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sxjyFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
	    <script type="text/javascript" src="/xgxt/dwr/engine.js"></script> 
	    <script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<html:form action="/fdyxxOne_whlg" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã� ˼������ - ��Ϣά�� - ˼��������Ϣά�� - ���Ӹ���Ա��Ϣ
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
						value="<bean:write name="doType" scope="request"/>" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
					<input type="hidden" id="pkValue" name="pkValue"
						value="<bean:write name="pkValue" scope="request"/>" />
					<input type="hidden" id="disableEle" name="disableEle" value="" />
					<input type="hidden" id="readonlyEle" name="readonlyEle"
						value="zgh" />
					<input type="hidden" id="act" name="act"
						value="<bean:write name="act" scope="request"/>" />	
					<input type="hidden" id="userJb" name="userJb" value="<bean:write name="userJb"/>"/>	
					<input type="hidden" id="getStuInfo" name="getStuInfo" value="" />
					<input type="hidden" id="url" name="url" value="/sjcz/xspxxxb.jsp" />
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
										<font color="red">*</font>ְ���ţ�
									</td>
									<td align="left">
										<html:text name='rs' property="zgh" readonly="true"
											styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									</td>
								</logic:present>
								<logic:notPresent name="ddxg">
								<td align="right">
									<font color="red">*</font>ְ���ţ�
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
									<font color="red">*</font>�Ա�
								</td>
								<td align="left">
									<html:select name='rs' property="xb" styleId="xb" style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="xbList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td align="right">
									<font color="red">*</font>��ϵ�绰��
								</td>
								<td align="left">
									<html:text name='rs' property="lxdh" styleId="lxdh" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<font color="red">*</font>�ƶ��绰��
								</td>
								<td align="left">
									<html:text name='rs' property="yddh" styleId="yddh" />
								</td>
								<td align="right">
									<font color="red">*</font>�������䣺
								</td>
								<td align="left">
									<html:text name='rs' property="dzyx" styleId="dzyx" />
								</td>
							</tr>
							<tr>
								<td align="right">
									���֤�ţ�
								</td>
								<td align="left">
									<html:text name='rs' property="sfzh" styleId="sfzh" />
								</td>
								<td align="right">
									�μӹ������ڣ�
								</td>
								<td width="37%" height="20%"><html:text name='rs' property="cjgzrq" styleId="cjgzrq" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cjgzrq','y-mm-dd');" />			
								</td>
							</tr>
							<tr>
								<td align="right">
									���壺
								</td>
								<td align="left">
									<html:select name="rs" property="mz" style="width:140px"
										styleId="mzdm">
										<html:options collection="mzList" property="mzdm"
											labelProperty="mzmc" />
									</html:select>
								</td>
								<td align="right">
									���᣺
								</td>
								<td align="left">
									<html:select name="rs" property="jg" style="width:140px"
										styleId="sfdm">
										<html:options collection="sfList" property="sfdm"
											labelProperty="sfmc" />
									</html:select>
								</td>
							</tr>
							<tr>
		    					<td width="16%" height="20%"><div align="right">�������ڣ�</div></td>
		    					<td width="37%" height="20%"><html:text name='rs' property="csrq" styleId="csrq" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('csrq','y-mm-dd');" />			
								</td>
		 						<td width="16%" height="20%"><div align="right">����˼���������ڣ�</div></td>
		    					<td width="37%" height="20%"><html:text name='rs' property="szgzsj" styleId="szgzsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('szgzsj','y-mm-dd');" />			
								</td>
							</tr>
							<tr>
								<td align="right">
									������ò��
								</td>
								<td align="left">
									<html:select name='rs' property="zzmm" styleId="zzmm" style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="JsZzmmList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td align="right">
									ѧ����
								</td>
								<td align="left" colspan="2">
									<html:select name='rs' property="xl" styleId="xl" style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="xlList" property="xldm"
											labelProperty="xlmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									ѧλ��
								</td>
								<td align="left">
									<html:select name='rs' property="xw" styleId="xw" style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="xwList" property="xwdm"
											labelProperty="xwmc" />
									</html:select>
								</td>
								<td align="right">
									��У����ʱ�䣺
								</td>
								<td width="37%" height="20%" colspan="2"><html:text name='rs' property="lxgzsj" styleId="lxgzsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('lxgzsj','y-mm-dd');" />			
								</td>
							</tr>  
							<tr>
								<td align="right">
									���Ʊ�ҵԺУ��
								</td>
								<td align="left">
									<html:text name='rs' property="bkbyyx" maxlength="15" styleId="bkbyyx" />
								</td>
								<td align="right">
									������ѧרҵ��
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="bksxzy" maxlength="15" styleId="bksxzy" />
								</td>
							</tr>
							<tr>
								<td align="right">
									˶ʿ��ҵԺУ��
								</td>
								<td align="left">
									<html:text name='rs' property="ssbyyx" maxlength="15" styleId="ssbyyx" />
								</td>
								<td align="right">
									˶ʿ��ѧרҵ��
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="ssbyzy" maxlength="15" styleId="ssbyzy" />
								</td>
							</tr>
							<tr>
								<td align="right">
									��ʿ��ҵԺУ��
								</td>
								<td align="left">
									<html:text name='rs' property="bsbyyx" maxlength="15" styleId="bsbyyx" />
								</td>
								<td align="right">
									��ʿ��ѧרҵ��
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="bsbyzy" maxlength="15" styleId="bsbyzy" />
								</td>
							</tr>
							<tr>
								<td align="right">
									<font color="red">*</font>���ѧλ����ѧ�ƣ�
								</td>
								<td align="left">
									<html:select name='rs' property="zhxwssxk" styleId="zhxwssxk" style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="xkList" property="xkdm"
											labelProperty="xkmc" />
									</html:select>
								</td>
								<td align="right">
									���¹�����
								</td>
								<td align="left" colspan="3">
									<html:select name='rs' property="csgz" styleId="csgz" style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="gzList" property="gzdm"
											labelProperty="gzmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<font color="red">*</font>���ڲ��ţ�
								</td>
								<td align="left">
									<html:select name="rs" property="bmdm" style="width:140px"
										styleId="szbm">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<td align="right">
									ְ��
								</td>
								<td align="left" colspan="3">
									<html:select name="rs" property="zw" style="width:140px"
										styleId="zwdm">
										<html:option value=""></html:option>
										<html:options collection="zwList" property="zwdm"
											labelProperty="zwmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<td align="right">
									<font color="red">*</font>�Ƿ�B�ࣺ
								</td>
								<td align="left">
									<html:select name="rs" property="sfbl" style="width:140px"
										styleId="sfbl">
										<html:option value=""></html:option>
										<html:options collection="sfblList" property="en"
											labelProperty="cn" />
									</html:select>			
								</td>
								<td align="right">
									����
								</td>
								<td align="left" colspan="2">
									<html:select name="rs" property="jb" style="width:140px"
										styleId="jb">
										<html:option value=""></html:option>
										<html:options collection="jbList" property="jbdm"
											labelProperty="jbmc" />
									</html:select>			
								</td>
							</tr>		
							<tr>
								<td align="right">
									<font color="red">*</font>ְ�ƣ�
								</td>
								<td align="left">
									<html:select name="rs" property="zc" style="width:140px"
										styleId="zcdm">
										<html:option value=""></html:option>
										<html:options collection="zcList" property="zcdm"
											labelProperty="zcmc" />
									</html:select>
								</td>
								<td align="right">
									<font color="red">*</font>ѧ�������о�����
								</td>
								<td align="left" colspan="3">
									<html:text name='rs' property="xsgzyjfx" styleId="xsgzyjfx" style="width:90%"/>
								</td>
							</tr>							
							<tr>
								<td align="right">
									��ͥסַ��
								</td>
								<td align="left" colspan="5">
									<html:text name='rs' property="jtzz" styleId="jtzz" style="width:100%"/>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									����༶��
								</td>
								<td colspan="4">
								<logic:present name="fdybjList">
										<logic:iterate id="s" name="fdybjList">
	                    				&nbsp;<bean:write name="s" />&nbsp;&nbsp;
	      							</logic:iterate>
	      						</logic:present>
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									����ѧ��������
								</td>
								<td align="left">
									<bean:write name="rs" property="sdxsrs" scope="request"/>&nbsp;&nbsp;λѧ��
								</td>
								<td align="right">
									�������ڣ�
								</td>
								<td width="37%" height="20%" colspan="2">
								<html:text name='rs' property="djsj" styleId="djsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('djsj','y-mm-dd');" />			
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									��Ҫְ��<br>
									<font color="red">��250���ַ�</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='zyzz' style="width:99%" styleId="zyzz"
										rows='4' />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									���˻������<br>
									<font color="red">��400���ַ�</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='grhjqk' style="width:99%" styleId="grhjqk"
										rows='5' />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									�������ģ�<br>
									<font color="red">��800���ַ�</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='fblw' style="width:99%" styleId="fblw"
										rows='4' />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									���о�����<br>
									<font color="red">��800���ַ�</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='kyjl' style="width:99%" styleId="kyjl"
										rows='5' />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									����������<br>
									<font color="red">��800���ַ�</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='gzjl' style="width:99%" styleId="gzjl"
										rows='8' />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									��ѵ�����<br>
									<font color="red">��600���ַ�</font>
								</td>
								<td colspan="5">
									<html:textarea name='rs' property='pxqk' style="width:99%" styleId="pxqk"
										rows='8' />
								</td>
							</tr>
							<tr align="left">
								<td align="right">
									��ע��<br>
									<font color="red">��800���ַ�</font>
								</td>
								<td colspan="5"><html:textarea name='rs' property='bz' style="width:99%" styleId="bz"
										rows='4' />
								</td>
							</tr>
						</table>
					</fieldset>
					<div class="buttontool">
					<%--
					<button type="button" class="button2" onclick="saveFdyxx('dzyx');"
							style="width:80px" id="buttonModi">
							�� ��
					</button>&nbsp;&nbsp;&nbsp;&nbsp;		
					--%>
					
						<button type="button" class="button2" onclick="saveFdyxx('dzyx');"
							style="width:80px" id="buttonSave">
							�� ��
						</button>&nbsp;&nbsp;&nbsp;&nbsp;
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
