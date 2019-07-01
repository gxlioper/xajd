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
			alert("<bean:message key="lable.xsgzyxpzxy" />为空，请确认！");
			return false;
		}
		if(zbmc==""){
			alert("支部名称不能为空！");
			return false;
		}
		
		var url = "/xgxt/zjlgDtjsZbgl.do?method=zbwhUpdate&doType=save";
		showTips('处理数据中，请等待......');
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
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			
			<div class="tab">
			<table class="formlist" id="rsTable" style="width: 100%">
				<thead>
					<tr>
						<th colspan="2">
							<span>支部维护</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />名称
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
						<font color="red">*</font>支部名称
					</th>
					<td>
						<html:text name='rs' property="zbmc" styleId="zbmc" style="width:150px" maxlength="10"/>	
					</td>
				</tr>
				<tr>
					<th>
						支部负责人
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
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
						  <logic:notEqual name="doType" value="view">
							<button type="button" id="buttonSave" onclick="saveZbmc()">
								保存
							</button>
						  </logic:notEqual>
						  <button type="button" name="关闭" id="buttonClose" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<div id="tmpdiv1"></div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
