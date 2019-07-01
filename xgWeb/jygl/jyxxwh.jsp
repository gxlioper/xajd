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
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<script type="text/javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript">
	    function jyxxQuery(){
	    	var nd = document.getElementById('nd').value;
	    	if(nd == ''){
	    		alert('��Ȳ���Ϊ�գ�');
	    		return  false;
	    	}
		 	document.forms[0].action = "/xgxt/jyxxlr.do?doType=query";
		 	document.forms[0].submit();
        }
        
		function jyxxDelete(){
			var url = "/xgxt/jyxxlr.do?doType=delete";
			var array = document.getElementsByName('pkV');
			var pkvs = '!@!';
			var num = 0;
			for(var i=0;i<array.length;i++){
				if(array[i].checked== true){
					pkvs += array[i].value+'!@!';
					num++;
				}	
			}
			
			if(num==0){
				alert('�빴ѡ��Ҫɾ���ļ�¼��');
				return false;
			}else{
				document.getElementById('pkStr').value = pkvs;
				document.forms[0].action = url;
			 	document.forms[0].submit();
			}
		}
		
		
		function jyxxUpdate(){
			if (null == curr_row) {
				alert('��ѡ��һ����¼��');
				return false;
			}
		
			var isqy =  curr_row.cells[9].innerText.trim();
			var userType = document.getElementById('userType').value;
			
			if ('��'==isqy && !( 'xx'==userType || 'admin'==userType )) {
				alert('�Ǿ�ҵ���û������޸���ǩԼ���ݣ�');
				return false;
			} 
			var pkV = curr_row.getElementsByTagName('input')[0].value;
			showTopWin("/xgxt/jyxxlr.do?doType=update&pk="+pkV, 650, 450);
			
		}

		function jyxxView(obj){
	       var pk = obj.getElementsByTagName('input')[0].value;
	       showTopWin("/xgxt/jyxxlr.do?doType=view&pk="+pk, 650, 450);
        }
		</script>
		<html:form action="/jyxxlr" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="pkStr" id="pkStr" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name='tableName' scope="request" />" />
			<input type="hidden" id="isFdy" name="isFdy" value="<bean:write name="isFdy" scope="session"/>"/>
			<input type="hidden" id="userType" name="userType" value="${userType}"/>
			<input type="hidden" id="userName" name=" userName " value="${ userName }"/>
			<div class="title">
				<div class="title_img" id="title_m">
					��ǰ����λ�ã���ҵ���� - ��ҵЭ�鷽�� - ��ҵ��Ϣ¼��
				</div>
			</div>
			<fieldset>
				<legend>
					�� ѯ
				</legend>
					<table width="100%" class="tbstyle">
						<thead>
							<tr style="cursor:hand">
								<td>
									 ��ȣ�
								    <html:select property="nd" style="width:100px" styleId="nd">
											<html:options collection="ndList" property="nd"
												labelProperty="nd" />
									</html:select>
									&nbsp;&nbsp;�꼶��
								    <html:select property="nj" style="width:100px" onchange="initZyList();initBjList()">
								    		<html:option value=""></html:option>
											<html:options collection="njList" property="nj"
												labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />��
									<logic:equal value="xy" name="userType">
										<html:select property="xydm" style="width:200px" styleId="xy" disabled="true">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
								 		</html:select>
									</logic:equal>
									<logic:notEqual value="xy" name="userType">
										<html:select property="xydm" style="width:200px" styleId="xy" onchange="initZyList();initBjList()">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
								 		</html:select>
									</logic:notEqual>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<button class="button2" style="height:40px"
										onclick="jyxxQuery()" id="query_go">
										��ѯ
									</button>
								</td>
							</tr>
							<tr>
								<td>
									רҵ��
								   	<html:select property="zydm" style="width:220px" styleId="zy" onchange="initBjList()">
											<html:option value=""></html:option>
											<html:options collection="zyList" property="zydm"
												labelProperty="zymc" />
								 	</html:select>
								 	&nbsp;&nbsp;�༶��
								   	<html:select property="bjdm" style="width:240px" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
								 	</html:select>
								</td>
							</tr>
							<tr>
								<td>
									ѧ�ţ�
									<html:text property="xh" style="width:100px"/>
									
									&nbsp;&nbsp;������
									<html:text property="xm" style="width:100px"/>
									&nbsp;&nbsp;�Ƿ��ҵ��
								   	<html:select property="sfjy" style="width:60px">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
								 	</html:select>
								 	&nbsp;&nbsp;�Ƿ�ǩԼ��
								   	<html:select property="sfqy" style="width:60px">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
								 	</html:select>
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
						<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" onclick="chec()"/>
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
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="jyxxView(this)">
								<td>
									<logic:notEqual value="yes" name="fdy">
										<input type="checkbox" name="pkV" value="${s.xh}"  />
									</logic:notEqual>
									<logic:equal value="yes" name="fdy">	 
											<logic:notEqual value="��" name="s" property="sfqy">
										<input type="checkbox" name="pkV" value="${s.xh}"  />
										</logic:notEqual>
										<logic:equal value="��" name="s" property="sfqy">
											<input type="checkbox" name="pkV" value="${s.xh}"   disabled/>
										</logic:equal>
									</logic:equal>
								</td>
								<td>
									${s.xh}
								</td>
								<td>
									${s.xm}
								</td>
								<td>
									${s.bynd}
								</td>
								<td>
									${s.nj}
								</td>
								<td>
									${s.xymc}
								</td>
								<td>
									${s.zymc}
								</td>
								<td>
									${s.bjmc}
								</td>
								<td>
									${s.dwmc}
								</td>
								<td>
									${s.lxdh}
								</td>
								<td>
									<logic:equal value="" property="sfjy"  name="s">
										��
									</logic:equal>
									<logic:notEqual value="" property="sfjy"  name="s">
										${s.sfjy}
									</logic:notEqual>
								</td>
								<td>
									<logic:equal value="" property="sfqy"  name="s">
										��
									</logic:equal>
									<logic:notEqual value="" property="sfqy"  name="s">
										${s.sfqy}
									</logic:notEqual>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
				<div class="buttontool" id="btn"
					style="position: absolute;left:0px;top:100px" width="100%">
					<button class="button2" onclick="jyxxUpdate()"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="jyxxDelete()"
						style="width:80px">
						ɾ ��
					</button>
					<logic:equal value="xx" name="userType">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
					</logic:equal>
					<logic:equal value="admin" name="userType">
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button class="button2"
							onclick="impAndChkData();"
							style="width:80px">
							��������
						</button>
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="dataExport()"
							style="width:80px">
							��������
					</button>			
				</div>
			<logic:notEmpty name="result">
				<logic:equal name="result" value="true">
					<script>
                      alert("�����ɹ�!");
                    </script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
                      alert("����ʧ��");
                    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
