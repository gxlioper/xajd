<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type='text/javascript' src='/xgxt/dwr/interface/zjlgZbglDAO.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXszzInfo.js'></script>
	<script language="javascript"  src="js/dtjs/dtjsFunction.js"></script>
	<script type="text/javascript">	
		function getFdyxm(){
		
			dwr.engine.setAsync(false);
				
			var tableName="view_zjlg_zbmc";
			var colList =["fdyxm"];
			var pk = "zbdm";
			var pkValue = $("zbdm").value;
		
			if(pkValue != ""){
				getXszzInfo.getXszzInfo(tableName,pk,pkValue,colList,function(data){
					if(data.fdyxm != "" && data.fdyxm != null){
						$("fdyxm").value=data.fdyxm;
					}else{
						$("fdyxm").value="";
					}
				});
			}else{
				$("fdyxm").value="";
			}
				
			dwr.engine.setAsync(true);
		
		}
		function addBjP(){
			var fromIndx = $("bj").selectedIndex;
			var toIndx = $("bjP").options.length;
			if(fromIndx < 0){
				return false;
			}
		
			for(var i=0;i<toIndx;i++){
				if($("bj").options[fromIndx].value == $("bjP").options[i].value){
					return false;
				}
			}
			$("bjP").options[$("bjP").options.length] = new Option($("bj").options[fromIndx].text,$("bj").options[fromIndx].value);
			$("bj").options[fromIndx] = null;
			if($("bj").options.length > 0){
				$("bj").options[0].selected = true;
			}else{		
		
			}
			if($("bjP").options.length > 0){
				$("bjP").options[0].selected = true;
			}
		}
		
		function delBjP(){
			var toIndx = $("bjP").selectedIndex;
			var fromIndx = $("bj").options.length;
			if(toIndx < 0){
				return false;
			}
			for(var i=0;i<fromIndx;i++){
				if($("bjP").options[toIndx].value == $("bj").options[i].value){
					$("bjP").options[toIndx] = null;
					return false;
				}
			}
			$("bj").options[$("bj").options.length] = new Option($("bjP").options[toIndx].text,$("bjP").options[toIndx].value);
			$("bjP").options[toIndx] = null;
			if($("dzbList").options.length > 0){
				$("bjP").options[0].selected = true;
			}else{		
		
			}	
			if($("bj").options.length > 0){
				$("bj").options[0].selected = true;
			}
		}
		
		function saveZb(){
			var xydm = $("xydm").value;
			var zydm = $("zydm").value;
			var zbdm = $("zbdm").value;
			
			if(xydm == "" ){
				alert("<bean:message key="lable.xsgzyxpzxy" />不能为空，请确认！！");
				return false;
			}
		
			if(zbdm == "" || zbdm == "null"){
				alert("支部名称不能为空，请确认！！");
				return false;
			}
			
			if($("bjP").options.length == 0){
				if (confirm("确定要清空该支部的数据?")) {
					refreshForm("/xgxt/zjlgDtjsZbgl.do?method=zbglManage&doType=clear");
				}
			}else{
				for(var i = 0 ; i < $("bjP").options.length; i++){
					var tmp = document.createElement("input");
					tmp.type = "hidden";
					tmp.name = "zbcy";
					tmp.value = $("bjP").options[i].value;
					document.forms[0].appendChild(tmp);
				}
				refreshForm("/xgxt/zjlgDtjsZbgl.do?method=zbglManage&doType=save");
			}
		}
		
		function getZbList(){
			var xydm = $("xydm").value;
			dwr.engine.setAsync(false);
			zjlgZbglDAO.getZbList(xydm,function(data){
				if (data != null && typeof data == 'object') {
					var objId = "zbdm";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"zbdm","zbmc");			
					}
					$(objId).options[0].value = "";
				}
			});
			dwr.engine.setAsync(true);
		}
		
		function getBjList(){
			var xydm = $("xydm").value;
			var zydm = $("zydm").value;
			dwr.engine.setAsync(false);
			zjlgZbglDAO.getBjList(xydm,zydm,function(data){
				if (data != null && typeof data == 'object') {
					var objId = "bj";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"bjdm","bjmc");			
					}
				}		
			});
			dwr.engine.setAsync(true);
		}
		
		function chZbList(zbdm){
			var xydm = $("xydm").value;
			var zydm = $("zydm").value;
			
			dwr.engine.setAsync(false);
			zjlgZbglDAO.getZbcyList(zbdm,function(data){
				if (data != null && typeof data == 'object') {
					var objId = "bjP";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"bjdm","bjmc");			
					}
				}		
			});
			
			zjlgZbglDAO.getWfpbjList(xydm,zydm,function(data){
				if (data != null && typeof data == 'object') {
					var objId = "bj";
					if($(objId) && $(objId).tagName.toLowerCase() == "select"){
							DWRUtil.removeAllOptions(objId);			
							DWRUtil.addOptions(objId,data,"bjdm","bjmc");			
					}
				}		
			});
			dwr.engine.setAsync(true);
		}
		
		function clearZb(){
			var zbdm = $("zbdm").value;
			if(zbdm == ""){
				alert("请确定需清空支部");
				return false;
			}
			var toIndx = $("bjP").selectedIndex;
			var num = $("bjP").options.length;
			var fromIndx = $("bj").options.length;
		
			for(var i=0;i<num;i++){
				$("bj").options[$("bj").options.length] = new Option($("bjP").options[i].text,$("bjP").options[i].value);
			}
			for(var i=0;i<num;i++){
				$("bjP").options[0] = null;
			}
		}
	</script>
	</head>
	<body onload="xyDisabled('xy');getFdyxm();">
		<html:form action="/zjlgDtjsZbgl">
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="ndV" id="ndV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="userType" id="userType" value="${userType }"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title" /></a>
				</p>
			</div>
			<div class="tab">
			<table class="formlist" id="rsTable">
				<thead>
					<tr>
						<th colspan="4">
							<span>支部设置</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th width="15%">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td>
						<html:select property="xydm" style="width:150px" styleId="xy" 
								onchange="initZyList();getZbList();getBjList();">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm" labelProperty="xymc" />
						</html:select>
					</td>
					<th width="15%">
						专业
					</th>
					<td align="left">
						<html:select property="zydm" style="width:150px" styleId="zy"
							onchange="getBjList();">
							<logic:equal name="isZbfzr" value="false">
								<html:option value=""></html:option>
							</logic:equal>
							<html:options collection="zyList" property="zydm" labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<th>
						支部名称
					</th>
					<td align="left">
						<html:select property="zbdm" onchange="getFdyxm();chZbList(this.value);" style="width:150px"
						styleClass="select" style="" styleId="zbdm">
							<html:options collection="zbList" property="zbdm"
								labelProperty="zbmc" />
						</html:select>
					</td>
					<th>
						负责人姓名
					</th>
					<td align="left">
						<input type="text" id="fdyxm" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td align="center" valign="middle">
						<p>
							班
						</p>
						<p>
							级
						</p>
					</td>
					<td width="40%">
						<html:select property="bjdm" styleId="bj"  size="12" style="width:99% " onmouseover="">
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
					<td width="10%" align="center">
						<button type="button" class="button2" style="width:50%" id="addBj" onclick="addBjP()">
							&gt;&nbsp;&gt;
						</button>
						<br />
						<br />
						<br />
						<button type="button" class="button2" style="width:50%" id="delBj"
							onclick="delBjP();">
							&lt;&nbsp;&lt;
						</button>
					</td>
					<td width="">
						<html:select property="bjdm" styleId="bjP" size="12" style="width:99% " onmouseover="">
							<html:options collection="dzbList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
					
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						<button type="button" id="buttonSave" onclick="saveZb()">
							保存
						</button>
						<button type="button" id="buttonReset" onclick="clearZb()">
							清空
						</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
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
