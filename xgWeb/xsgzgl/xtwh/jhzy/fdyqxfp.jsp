<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="/xgxt/dwr/interface/systemFunction.js"></script>		
		<script>
			function showModiUser() {
				if (document.forms[0].topGroup.selectedIndex < 0) {
					alert("请选择用户");
					return false;
				}
				document.getElementById('userID').value = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 0);
				document.getElementById('modiUserName').value = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 1);
				group = getStr(document.forms[0].topGroup.options[document.forms[0].topGroup.selectedIndex].text, 2);
				viewTempDiv('修改用户所属组','modiUser',350, 180);
				initList("allGroup", "select_userGroup", group);
			}
			
			function save(type) {
				if("group"==type){
					userGroup = document.getElementById('select_userGroup').value;
					yhm = document.getElementById('userID').value;
					if(userGroup == ""){
						alert("用户所属组不能为空！");
						document.getElementById('select_userGroup').focus();
						return false;
					}else{
						document.forms[0].action = "jhzy_xtwh.do?method=fdyqxfp&doType=save&yhm="+yhm;
						document.forms[0].submit();
					}
					if($("buttonSave")){
					   $("buttonSave").disabled=true;
					}
				}else if("qx"==type){
					var groupPower = new Array();
					if (document.forms[0].groupPower.options.length <= 0) {
						alert("用户权限不允许为空！！！");
						return false;
					}
					if (document.forms[0].topGroup.selectedIndex < 0) {
						alert("请选择用户");
						return false;
					}
					var yhm=document.getElementById('topGroup').value;
					for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
						p = document.forms[0].groupPower.options[i].value.substring(0, 1);
						q = document.forms[0].groupPower.options[i].value.substring(1, document.forms[0].groupPower.options[i].value.length);
						groupPower[groupPower.length] = p + ":" + q;
					}
					document.forms[0].power.value = groupPower;
					document.forms[0].action = 'jhzy_xtwh.do?method=fdyqxfp&doType=qx&yhm='+yhm;
					document.forms[0].submit();
					var tmpBut = document.getElementsByTagName("button");
					for (i = 0; i < tmpBut.length; i++) {
						tmpBut[i].disabled = true;
					}
				}
			}
			//显示用户权限
			function disUserPower(){

				var querry = "";
				var yhm = document.getElementById('topGroup').value;
				var sUserName = document.getElementById("sUserName").value;
				if(yhm == "" || yhm == null){
					yhm = "%";
				}else{
					yhm = yhm;
				}
				if(sUserName == "" || sUserName == null){
					sUserName = "%";
				}else{
					sUserName = sUserName;
				}
				querry += yhm;
				//加后缀
				querry += "_fdy!!-!!";
				querry += sUserName;
				if (powerChanged) {
					if (confirm("当前组的权限发生了变化，要保存吗？\n点击\"确定\"，保存数据；点击\"取消\"，将放弃更改！")) {
						if (document.forms[0].groupPower.options.length <= 0) {
							alert("组的权限不允许为空！！！");
							return false;
						}
						showTips();
						document.forms[0].action = 'jhzy_xtwh.do?method=fdyqxfp&doType=qx&yhm='+yhm;
						var groupPower = new Array();
						for (i = 0; i < document.forms[0].groupPower.options.length; i++) {
							p = document.forms[0].groupPower.options[i].value.substring(0, 1);
							q = document.forms[0].groupPower.options[i].value.substring(1, document.forms[0].groupPower.options[i].value.length);
							groupPower[groupPower.length] = p + ":" + q;
						}
						document.forms[0].power.value = groupPower;
						document.forms[0].submit();
					} else {
						powerChanged = false;
						GetListData.getUserPowerForJhzy(querry,TjUserPower);
						//加载子系统功能模块
						GetListData.getSubListForJhzy(querry,TjUnAllotSubList);
						//加载当前子系统功能模块
						GetListData.getUnAllotPowerForJhzy(querry,TjUnAllotPower);
					}
				} else {
					GetListData.getUserPowerForJhzy(querry,TjUserPower);
					//加载子系统功能模块
					GetListData.getSubListForJhzy(querry,TjUnAllotSubList);
					//加载当前子系统功能模块
					GetListData.getUnAllotPowerForJhzy(querry,TjUnAllotPower);
				}
				
			}
			//加载未授予用户的权限
			function loadSubPower() {
				document.forms[0].powerSub.options.length = 0;	
				var currentPower = document.forms[0].powerTop.value;
				var querry = "";
				var yhm = document.getElementById("topGroup").value;
				var sUserName = document.getElementById("sUserName").value;
				if(yhm == "" || yhm == null){
					yhm = "%";
				}else{
					yhm = yhm;
				}
				if(sUserName == "" || sUserName == null){
					sUserName = "%";
				}else{
					sUserName = sUserName;
				}
				querry += yhm;
				querry += "_fdy!!-!!";
				querry += sUserName;
				if (currentPower == "all") {
					//加载当前子系统功能模块
					GetListData.getUnAllotPower(querry,TjUnAllotPower);
					return true;
				}
				//加载当前子系统未分配给用户的功能模块
				GetListData.getUnAllotPowerById(querry,currentPower,TjUnAllotPower);
				if(document.forms[0].powerSub.options[0]){
					document.forms[0].powerSub.options[0].selected = true;
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/jhzy_xtwh" method="post">
				<input type="hidden" id="writeAble" name="writeAble"
					value="<bean:write name="writeAble" scope="request"/>" />
				<input type="hidden" id="sUserName" name="sUserName" value="<bean:write name="sUserName" scope="request"/>" />
			    
				<html:hidden property="userPower" styleId="allPower" />
				<html:hidden property="groupList" styleId="allGroup" />
				<input type="hidden" id="globlVar" name="globlVar" value="" />
				<input type="hidden" id="xxdm" name="xxdm"
					value="<bean:write name="xxdm" scope="request"/>" />
				<input type="hidden" name="power" value="" />
				<input type="hidden" name="newGroupName" value="" />
		
				<div id="tempdiv"></div>
		
				<div class="tab_cur" id="title">
					<p class="location">
							<em>您的当前位置:</em><a>系统维护 - 权限维护 - 辅导员权限维护</a>
					</p>
				</div>
				
				<div class="tab">
				  <table width="100%" border="0" class="permissionlist">
				    <thead>
				    	<tr>
				        	<td colspan="3"><span>辅导员权限维护</span></td>
				        </tr>
				    </thead>
				      <tr>
				        <td valign="top" width="35%">
				        	<table width="100%">
				            <tr><td>
				        	<div class="filter">
				        		<label>子系统</label>
				        		<select name="gnmkmc" 
										onchange="loadSubPower()" id="powerTop">
								</select>
				        		
				        	</div>
				            <div class="CNLTreeMenu2" id="CNLTreeMenu2">
				              	<select onmouseover="null"
				              			name="gnmkdm" style="width:100%;"
										size="26" id="powerSub">
								</select>
				            </div>
							<script type="text/javascript">
							<!--
							var MyCNLTreeMenu1=new CNLTreeMenu("CNLTreeMenu2","li");
							MyCNLTreeMenu1.InitCss("Opened","Closed","Child","../../s.gif");
							-->
							</script>
						</td></tr></table>
				        </td>
				        <td style="padding:0">
				        	<span id="btn1">
					        	<button type="button" class="right" onclick="addPower()"></button>
					        	 &nbsp;	
		    					<br/>
								<br/>
								<br/>
					        	<button type="button" class="left" onclick="delPower()"></button>
				        	</span>
				        </td>
				        <td valign="top" width="57%">
				          <table width="100%">
				          	   <tr>
				          	   	<td>
				          	   		模块功能：
				                  	<button type="button" onclick="showModiUser()">组划分</button>
				          	   	</td>
				          	   </tr>
				              <tr>
				                <td>
				                  <font color="red">对应说明:用户名/姓名/用户所属组</font>
				                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				                  <button type="button" onclick="showTopWin('jhzy_xtwh.do?method=userQuery&doType=fdy','350','350');">
										用户查询
									</button>
				                  <html:select property="userName" onchange="disUserPower()"
										 size="11" styleId="topGroup" styleClass="selectlist">
									<html:options collection="listGroup" labelProperty="xm" property="yhm" />
								 </html:select>
				                  
				               </td>
				              </tr>
				              <tr>
				                <td>
				                	<label>所选用户已拥有功能模块</label>
				                  <html:select property="userPower" styleClass="selectlist"
											 size="11" styleId="groupPower">
										<html:options collection="powerListG" labelProperty="gnmkmc" property="gnmkdm" />
								  </html:select>
				                </td>
				              </tr>
				            </table></td>
				          </tr>
				     <tfoot>
				      <tr>
				      	<td align="right" colspan="3">
				            <button type="button"  onclick="save('qx')">保 存</button>
				            <button type="button"  onclick="chgR()">所选设为只读</button>
				            <button type="button"  onclick="chgW()">所选设为可写</button>
				            <button type="button"  onclick="readOnlyAll()">全部只读</button>
				            <button type="button"  onclick="writeAll()">全部可写</button>
				      	</td>
				      </tr>
				    </tfoot>
				  </table>
				</div>
		<!-- 用户组划分 -->
		<div class="open_win01" style="display:none;" id="modiUser">
				<table width='80%' class="formlist">
					<tbody>
						<tr>
						<th width='25%'>
							<font color=red>*</font>用户名
						</th>
						<td align='left'>
							<input type='text' name='userID' disabled="true" id="userID"  class='text_nor' value=""/>
						</td>
					</tr>
					<tr>
						<th>
							<font color=red>*</font>姓名
						</th>
						<td align='left'>
							<input type='text' name='newUserName' disabled="true" id="modiUserName" class='text_nor'/>
						</td>
					</tr>
					<tr>
						<th>
							<font color=red>*</font>所属组
						</th>
						<td align='left'>
							<select id='select_userGroup' name='newUserGroup' style='width:152px;' ></select>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan='2' >
								<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class='btn'>
									<button type="button" class='button2' id='buttonSave' onclick="save('group');return false;">
										保存
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick="hiddenMessage(true,true);return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			</html:form>
		<script language="javascript">
			var powerChanged = false;
			var i = 1;
			var j = 1;
			var dpd = new Array();
			var txt = document.forms[0].allPower.value;
			var SplitSignOne = "!!SplitSignOne!!";
			var SplitSignTwo = "!!SplitSignTwo!!";
			var initStrToSplit = txt.split(SplitSignOne);
			document.forms[0].powerTop.options.length = 0;
			document.forms[0].powerTop.options[document.forms[0].powerTop.options.length] = new Option("----全部-----","all");
			for(i = 1;i<initStrToSplit.length;i++){
				dpd[i] = initStrToSplit[i].split(SplitSignTwo);
				if(dpd[i][1].length == 3){
					document.forms[0].powerTop.options[document.forms[0].powerTop.options.length] = new Option(dpd[i][1]+" | "+dpd[i][2],dpd[i][1]);
				}
				
			}
			
			listAll();
			init=null;
			for(i = 0;i<document.getElementsByTagName("select").length;i++){
			  if(document.getElementsByTagName("select")[i].length>0){
				if(document.getElementsByTagName("select")[i].selectedIndex < 0){
					document.getElementsByTagName("select")[i].options[0].selected = true;
				}
			  }
			}
			i=1;
			
	</script>
	<logic:present name="message">
		<script defer="defer">
			alert("${message}");
		</script>
	</logic:present>
	</body>
</html>
