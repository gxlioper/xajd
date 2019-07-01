<%@ page language="java" contentType="text/html; charset=gb2312"%>

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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/xljkFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script language="javascript">
    function setValue(){ 
         var yzmc = document.getElementById('yzdm').options[document.getElementById('yzdm').selectedIndex].text;
         document.getElementById('yzmc').value = yzmc;
    }
    function addItem(){ 
        if(document.forms[0].yzdm.value==null||document.forms[0].yzdm.value==""){
       		 alert("��������������ѡ����Ӧ�����ӣ�");
        	 return false;
      	} 
      	var stlsh = document.getElementById('stlsh');
      	if(stlsh.options.length==0){
        	alert("\'δ�����������б�\'�б�Ϊ�գ�");
         	return false;
     	}
     	var count = 0; 
     	var stlshValue = "";
        var stlshText = "";
        var s = document.forms[0].temp.options.length;
        for(var i=0;i<stlsh.options.length;i++){
		    if(stlsh.options[i].selected){
			    count++;
		    }
	    }
        if(count==0){
		     alert('�������\'δ�����������б�\'�б���ѡ��һ���������¼��');
		     return false;
	    }
        for(i=0;i<stlsh.options.length;i++){
             if(stlsh.options[i].selected){
             	stlshValue=stlsh.options[i].value;
                stlshText=stlsh.options[i].text;        
                document.forms[0].temp.options[s++] = new Option(stlshText,stlshValue);
                document.forms[0].stlsh.options[i]=null;
                i--;                    
             }
        }  
    }
    function deleteItem(){ 
    	var stlsh =  document.getElementById("stlsh");
    	var temp =  document.getElementById("temp");
    	var n = document.forms[0].stlsh.options.length;
    	var m = document.forms[0].temp.options.length;
		if(m==0){
			alert('\'�ѷ������б�\'�б�Ϊ�գ�');
			return false;
		}
        var count = 0;
        for(var i=0;i<temp.options.length;i++){
		    if(temp.options[i].selected){
				count++;
		    }
	    }
	    if(count==0){
		    alert('�����ұ�\'�ѷ������б�\'�б���ѡ��һ���������¼��');
		    return false;
	    }
	   for(i=0;i<temp.options.length;i++){
             if(document.forms[0].temp.options[i].selected){
             	var tempValue=document.forms[0].temp.options[i].value;
                var tempText=document.forms[0].temp.options[i].text;        
                document.forms[0].stlsh.options[n++] = new Option(tempText,tempValue);
                document.forms[0].temp.options[i]=null;
                i--;                    
             }
       }
    }
    function saveItem(){ 
    	showTips();
    	var temp = document.getElementById('temp');
    	var newValue = document.getElementById('newValue');
    	for(var i=0;i<temp.options.length;i++){
    		newValue.value =newValue.value+temp.options[i].value+',';
    	}
    	refreshForm('/xgxt/xljk_xlcs.do?act=tkwh&doType=yzfst&do=save');
    }
    </script>
	<base target="_self" />
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<body>
		<html:form action="/xljk_xlcs_tkwh" method="post">
			<input type="hidden" name="yzmc" id="yzmc"/>
			<input type="hidden" name="oldValue" id="oldValue" value="<bean:write name="oldValue"/>"/>
			<input type="hidden" name="newValue" id="newValue" value=""/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã������� - ���ӷ�����
				</div>
			</div>
			<div class="rightcontent" >
				<fieldset>
					<legend>
						��Ŀ������
					</legend>
					<table width="98%" align="center" class="tbstyle"  bgcolor="#D0E0EE">
						<tr>
							<td width="30%" align="left">
								&nbsp;&nbsp;&nbsp;&nbsp;��������
								<html:select property="yzdm" style="width:150px" styleId="yzdm"
									onchange="setValue();refreshForm('/xgxt/xljk_xlcs.do?act=tkwh&doType=yzfst')">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="yzList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;�������ͣ�
								<html:select property="stlxdm" style="width:150px" styleId="stlxdm"
									onchange="refreshForm('/xgxt/xljk_xlcs.do?act=tkwh&doType=yzfst')">
									<html:option value=""></html:option>
									<html:options collection="stlxList" property="STLXDM"
										labelProperty="STLXMC" />
								</html:select>
								<logic:iterate id="s" name="stlxList">
								<bean:write name="s" property="stlxdm"/>
									<bean:write name="s" property="stlxmc"/>
								</logic:iterate>
								&nbsp;&nbsp;&nbsp;&nbsp;������ʾ��ǣ�
								<html:select property="stxsbj" style="width:150px" styleId="stxsbj"
									onchange="refreshForm('/xgxt/xljk_xlcs.do?act=tkwh&doType=yzfst')">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
								
						    </td>
						 </tr>
						

						<tr align="center">
							<td rowspan="2" valign="top">
								<table width="97%" align="center" class="tbstyle">
									<tr align="center">
										<td align="center">
											����δ�����������б�
										</td>
										<td>
											 ������ʽ
										</td>
										<td align="center">
										<logic:equal value="yes" name="yfyz">
											�ѷ������б�
										</logic:equal>
										<logic:equal value="no" name="yfyz">
											<bean:write name="yzmc"/>���������б�
										</logic:equal>
										</td>
									</tr>

									<tr align="center">
										<td valign="top">
										<font color="red">��ʾ����סCtrl��(���������������ƶ�)<br>�ɽ��ж�ѡ</font>
											<br>
											��������/�������/�Ƿ���ʾ<br>
<%--											�������ݣ�<input name="stnr" value="���س�����ѯ" id="stnr" style="color:a0a0a0" --%>
<%--											onfocus="document.getElementById('stnr').value=''" onkeypress="if(event.keyCode==13){getStlbList(this,'stlsh')}"/>--%>
											<html:select property="stlsh" style="width:100%;"
												size="19" styleId="stlsh" 
												multiple="multiple">
												<html:options collection="stList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
										<td valign="top">										     
                                           	
											<br>
											<br>
											<br>
											<br>
											<br>	
											<br>
											<br>
											<br>
											<br>										
											<font color="blue">����</font>
											<br>
											<button class="button2" onclick="addItem()"
												style="width:50px;height: 20px" title="������⻮��">
												&rarr;

											</button>
											<br>
											<br>
											<br>
											<br>
										    <font color="blue">�ͷ�</font>
										    <br>
											<button class="button2" onclick="deleteItem()"
												style="width:50px;height: 20px" title="��������ͷ�">
												&larr;
											</button>
										</td>
										<td valign="top">
										<font color="red">��ʾ����סCtrl��(���������������ƶ�)<br>�ɽ��ж�ѡ</font>
										<br>
											��������/�������/�Ƿ���ʾ
											<br>
											<html:select property="temp" style="width:100%;relative:down" size="19"
												styleId="temp" ondblclick=""multiple="multiple">
												<html:options collection="yzstList" labelProperty="mc"
													property="dm" />
											</html:select>
										</td>
									</tr>
									<tr>
										<td align="center" colspan="3">
											<input class="button2" type="button" name="button1"
												style="width:100px" value="ȷ ��"
												onclick="if(confirm('�Ƿ�Ҫ�ύ��ǰ�ѻ���������ݣ�\n���\'ȷ��\'���������ݣ�\n���\'ȡ��\'���������ύ��')){saveItem()}" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" class="button2" name="button2"
												style="width:100px" value="�� ��"
												onclick="refreshForm('/xgxt/xljk_xlcs.do?act=tkwh&doType=yzfst')" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</td>
									</tr>
								</table>
							</td>
						</tr>

					</table>
				</fieldset>				
			</div>		
		</html:form>
		<logic:notEmpty name="flag">
			<logic:equal name="flag" value="true">
				<script>
					alert("�����ɹ�!");					
				</script>
			</logic:equal>
			<logic:equal name="flag" value="false">
				<script>
					alert("����ʧ��!");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
