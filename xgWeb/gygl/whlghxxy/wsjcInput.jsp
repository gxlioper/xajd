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
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script language="javascript">
	function inputSave(){
	   var tabobj = document.getElementById("rsTable");
       var rowLen = tabobj.rows.length;
       var jcsj="";
	   var dj="";
	   var fs="";
	   var jcbm="";
	   var ssbhv=""; 
	   var num=0;
       for(j=1;j<rowLen;j++){
            jcbm =document.getElementById("rsTable").rows[j].cells[5].getElementsByTagName('select')[0].value;
            dj =document.getElementById("rsTable").rows[j].cells[6].getElementsByTagName('select')[0].value;
            fs=document.getElementById("rsTable").rows[j].cells[7].getElementsByTagName('input')[0].value;
            jcsj=document.getElementById("rsTable").rows[j].cells[8].getElementsByTagName('input')[0].value;
            
            if((jcsj!=""||dj!="")&&jcbm==""){
               alert("��"+j+"�м�鲿��Ϊ�գ�");
               return false;
               break;
            }
            if((jcsj!=""||jcbm!="")&&dj==""){
               alert("��"+j+"�еȼ�Ϊ�գ�");
               return false;
               break;
            }
            if((jcbm!=""||dj!="")&&jcsj==""){
               alert("��"+j+"�м��ʱ��Ϊ�գ�");
               return false;
               break;
            }
            if(jcbm ==""&&dj==""&&jcsj==""){
              num++;
            }
       }
       if(num==rowLen-1){
          alert("������������ݣ�");
          return false;
       }
	   refreshForm("/xgxt/whlghxxy_Gygl.do?method=inputSave");
	}
	function laodValue(){
	       var jczstr = $("jczstr").value;
	       var jcsjstr = $("jcsjstr").value;
	       var djstr = $("djstr").value;
	       var fsstr = $("fsstr").value;
	       var jcbmstr = $("jcbmstr").value;
	       var ssbhvstr = $("ssbhvstr").value;
	       if(ssbhvstr!=""){
	           var tabobj = document.getElementById("rsTable");
               var rowLen = tabobj.rows.length;
	           var ssbhv = new Array();
	           var jcz = new Array();
	           var jcsj = new Array();
	           var dj = new Array();
	           var fs = new Array();
	           var jcbm = new Array();
	           ssbhv = ssbhvstr.split("!!"); 
	           jcz = jczstr.split("!!"); 
	           dj = djstr.split("!!"); 
	           fs = fsstr.split("!!"); 
	           jcbm = jcbmstr.split("!!"); 
	           jcsj = jcsjstr.split("!!"); 
	           for(j=1;j<rowLen;j++){
	               document.getElementById("rsTable").rows[j].cells[5].getElementsByTagName('select')[0].value=jcz[j-1];
	               document.getElementById("rsTable").rows[j].cells[5].getElementsByTagName('select')[0].value=jcbm[j-1];
                   document.getElementById("rsTable").rows[j].cells[6].getElementsByTagName('select')[0].value=dj[j-1];
                   document.getElementById("rsTable").rows[j].cells[7].getElementsByTagName('input')[0].value=fs[j-1];
                   document.getElementById("rsTable").rows[j].cells[8].getElementsByTagName('input')[0].value=jcsj[j-1];                   
	           }
	       }	       
	}
	function search(){
	    var lddm = $("lddm").value;
	    if(lddm==""||lddm==null){
	       alert("��ѡ��¥����");
	       return false;
	    }
	    refreshForm('/xgxt/whlghxxy_Gygl.do?method=wsjcInput&go=go')
	}
	</script>
	<body onload="laodValue()">

		<html:form action="/whlghxxy_Gygl" method="post">
			<input type="hidden" id="showSelect" name="showSelect" value="yes"/>
			<input type="hidden" id="jczstr" name="jczstr"
				value="<bean:write name="jczstr" scope="request"/>" />
			<input type="hidden" id="jcsjstr" name="jcsjstr"
				value="<bean:write name="jcsjstr" scope="request"/>" />
			<input type="hidden" id="djstr" name="djstr"
				value="<bean:write name="djstr" scope="request"/>" />
			<input type="hidden" id="fsstr" name="fsstr"
				value="<bean:write name="fsstr" scope="request"/>" />
			<input type="hidden" id="jcbmstr" name="jcbmstr"
				value="<bean:write name="jcbmstr" scope="request"/>" />
			<input type="hidden" id="ssbhvstr" name="ssbhvstr"
				value="<bean:write name="ssbhvstr" scope="request"/>" />
			<input type="hidden" name="lcV" id="lcV" value="" />
			<input type="hidden" name="qshV" id="qshV" />
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���Ԣ���� - ��Ϣά�� - �������� -���
				</div>
			</div>

			<fieldset>
				<legend>
					��������
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								ѧ�꣺
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ѧ�ڣ�
								<html:select property="xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
								&nbsp;&nbsp;¥������
								<html:select property="lddm" styleId="lddm"
									onchange="getLcList()">

									<html:options collection="ldList" property="lddm"
										labelProperty="ldmc" />
								</html:select>
								&nbsp;&nbsp;¥�㣺
								<html:select property="lc" styleId="lc" onchange="getQshList2()">

									<html:options collection="lcList" property="dm"
										labelProperty="mc" />
								</html:select>
								&nbsp;&nbsp;���Һţ�
								<html:select property="qsh" styleId="qsh">

									<html:options collection="qshList" property="dm"
										labelProperty="mc" />
								</html:select>
								<input type="hidden" name="go" value="go" />
								<button class="button2" id="search_go" onclick="search()">
									��ѯ
								</button>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<p align="center">
					δ�ҵ��κμ�¼��
				</p>
			</logic:empty>

			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						��¼����
						<bean:write name="rsNum" />
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									�к�
								</td>
								<td>
									¥��
								</td>
								<td>
									¥��
								</td>
								<td>
									���Һ�
								</td>

								<td>
									�����
								</td>
								<td>
									��鲿��
								</td>
								<td>
									�ȼ�
								</td>
								<td>
									����
								</td>

								<td>
									���ʱ��
								</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s" indexId="index">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
								<td>
									<input type="hidden" name="ssbhv"
										value="<bean:write name="s"  property="ssbh" />" />
									<bean:write name="s" property="r" />
								</td>
								<td>
									<bean:write name="s" property="ldmc" />
								</td>
								<td>
									<bean:write name="s" property="cs" />
								</td>
								<td>
									<bean:write name="s" property="qsh" />
								</td>
								<td>
									<select name="jcz"
										id='jcz-<bean:write name="s" property="r" />' />
										<logic:iterate name="zsList" id="v">
											<option value="<bean:write name='v' property='en' />">
												<bean:write name='v' property='cn' />
											</option>
										</logic:iterate>
									</select>
								</td>
								<td>
									<select name="jcbm"
										id='jcbm-<bean:write name="s" property="r" />'>
										<option value=""></option>
										<logic:iterate name="bmList" id="v">
											<option value="<bean:write name='v' property='bmdm' />">
												<bean:write name='v' property='bmmc' />
											</option>
										</logic:iterate>
									</select>
								</td>
								<td>
									<select name="dj" id='dj-<bean:write name="s" property="r" />'
										onchange="GetFs2(this)">
										<option value=""></option>
										<logic:iterate name="djList" id="v">
											<option value="<bean:write name='v' property='nwwsdjdm' />">
												<bean:write name='v' property='nwwsdjmc' />
											</option>
										</logic:iterate>
									</select>
								</td>
								<td>
									<input type="text" name="fs"
										id='fs-<bean:write name="s" property="r" />'
										style="width:50px">
								</td>
								<td>
									<input type="text" name="jcsj"
										id='jcsj-<bean:write name="s" property="r" />'
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar(this.id,'y-mm-dd','aa');"
										readonly="true" />
								</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<br>
				<br>
				<br>
				<br>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<button class="button2" onclick="inputSave()" style="width:80px">
						�� ��
					</button>
				</div>
				<script language="javascript">
				document.getElementById("btn").style.pixelTop = document.body.clientHeight - 55;
				document.getElementById("btn").style.width = "96%";
				window.setInterval("initBTNTool('btn')",1);
			    </script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
