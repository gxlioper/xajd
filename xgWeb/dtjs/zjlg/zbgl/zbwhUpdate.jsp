<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/pjpy/pjpy_szyqxy.js"></script>
	<script language="javascript"  src="js/sztzFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
	function saveZbmc(){
		var xydm = $("xydm").value;
		var zbmc = $("zbmc").value;
		
		if(xydm==""){
			alert("<bean:message key="lable.xsgzyxpzxy" />Ϊ�գ���ȷ�ϣ�");
			return false;
		}
		if(zbmc==""){
			alert("֧�����Ʋ���Ϊ�գ�");
			return false;
		}
		
		var url = "/xgxt/zjlgDtjsZbgl.do?method=zbwhUpdate&doType=save";
		showTips('���������У���ȴ�......');
		$("buttonSave").disabled=true;
		$("buttonClose").disabled=true;
		refreshForm(url);
	}
	
	</script>
	</head>
	
	<body onload="xyDisabled('xydm');">
		<html:form action="/zjlgDtjsZbgl">
			<input type="hidden" id="pk" name="pk" value="${pk }"/>
			<input type="hidden" id="url" name="url" value="/xgxt//zjlgDtjsZbgl.do?method=zbwhUpdate"/>
			<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<input type="hidden" id="doType" name="doType" value="${doType }"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			
			<div class="tab">
			<table class="formlist" id="rsTable" style="width: 100%">
				<thead>
					<tr>
						<th colspan="2">
							<span>֧��ά��</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td>
						<logic:equal name="doType" value="add">
						<html:select name="rs" property="xydm" style="width:155px" styleId="xydm">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
						</logic:equal>
						<logic:notEqual name="doType" value="add">
							<html:hidden name='rs' property="xydm" styleId="xydm"/>	
							<html:text name='rs' property="xymc" styleId="xymc" style="width:150px" readonly="true"/>	
						</logic:notEqual>
					</td>
				</tr>
				<tr>
					<th>
						<font color="red">*</font>֧������
					</th>
					<td>
						<html:text name='rs' property="zbmc" styleId="zbmc" style="width:150px" maxlength="10"/>	
					</td>
				</tr>
				<tr>
					<th>
						֧��������
					</th>
					<td>
						<html:hidden name='rs' property="zgh" styleId="zgh"/>	
						<html:text name='rs' property="fdyxm" styleId="fdyxm" style="width:150px" readonly="true"/>	
						<button type="button" onclick="showTopWin('/xgxt/commXszz.do?method=lsxxManage',800,600);">+</button>	
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" id="buttonSave" onclick="saveZbmc()">
								����
							</button>
						  </logic:notEqual>
						  <button type="button" name="�ر�" id="buttonClose" onclick="window.close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
