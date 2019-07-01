<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		
	<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/zjlg_xljk.do?method=xlzxsAdd&act=add";
		 var yzdz = "scfxV-bzV";
		 var yzcd = "400-400";
		 var mustFill = "xm-xb-nl";
		 //var sjhm = document.getElementById("sjhm").value;
		 var dzyx = document.getElementById("dzyx").value;
		 //if(!isNumber(sjhm)){
		//	alert("手机号码必须是数字");
		//	return false;
		// }
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
		 var dzyx = document.getElementById("dzyx").value;
	     if(dzyx!=""){
			if(!isEmail(dzyx)){
				alert("电子邮箱格式不正确！！");
				return false;
			}
	     }
		 var eles = mustFill.split("-");
	    	for (var i = 0; i < eles.length; i++) {
	    		if (document.getElementById(eles[i]) && document.getElementById(eles[i]).value == "") {
	    			alert("请将带\"*\"号的项目输入完整！");
	    			return false;
	    		}
	    	}
	    	var splitDz = "";
	    	var splitCd = "";
	    	if(yzdz != ""){
	    		splitDz = yzdz.split("-");
	    	}
	    	if(yzcd != ""){
	    		splitCd = yzcd.split("-");
	    	}
	    	for (i = 0; i < splitDz.length; i++) {
	    		var dzsjcd = document.getElementById(splitDz[i]).cells[1].getElementsByTagName('textarea')[0].value;
	    		var dzsjmc = document.getElementById(splitDz[i]).cells[0].innerHTML;
	    		if (dzsjcd.length>splitCd[i]) {
	    			alert(dzsjmc.replace("<BR>", "")+"不能超过"+splitCd[i]+"个字！");
	    			return false;
	    		}
	    	}
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	document.getElementById("buttonSave").disabled=true;
	 }
	 function printwmgylgl(){
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=wmgylprint";
			document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
	 }
	</script>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<html:form action="/zjlg_xljk" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="url" name="url" value="/zjlg_xljk.do?method=getxlxhhyforxh" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm" />
			<html:hidden name="rs" property="xh" />
			<input type="hidden" id="tableName" name="tableName" value="zjlg_xlxhhy" />
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="titleName"/></a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" class="formlist">
					<thead><tr><th colspan="4"><span>信息维护</span></th></tr></thead>
					<tbody>
					<tr>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>姓名 
						</th>
						<td>
							<logic:equal value="stu" name="userType" scope="session">
									<html:text name="rs" property="xm" style="width:85px" readonly="true"></html:text>
							</logic:equal>
							<logic:notEqual value="stu" name="userType" scope="session">
									<html:text name="rs" property="xm" readonly="true"  maxlength="30"></html:text>
									<logic:equal value="add" name="view">
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu" >
											选择
										</button>
									</logic:equal>
							</logic:notEqual>
							<html:hidden name="rs" property="id"/>
						</td>
<%--						<td>--%>
<%--							<logic:equal value="stu" name="userType" scope="session">--%>
<%--										<html:text name="rs" property="xm" style="width:85px" readonly="true"></html:text>--%>
<%--										</logic:equal>--%>
<%--										<logic:notEqual value="stu" name="userType" scope="session">--%>
<%--										<html:text name="rs" property="xm"  maxlength="30"></html:text>--%>
<%--										<html:hidden name="rs" property="xh" />--%>
<%--										<logic:equal value="add" name="view">--%>
<%--										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"--%>
<%--											class="btn_01" id="buttonFindStu" >--%>
<%--											选择--%>
<%--										</button>--%>
<%--										</logic:equal>--%>
<%--										</logic:notEqual>--%>
<%--							<html:hidden name="rs" property="id"/>--%>
<%--						</td>--%>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>性别
						</th>
						<td>
<%--							<html:select name="rs" property="xb">--%>
<%--								<html:option value=""></html:option>--%>
<%--								<html:option value="男">男</html:option>--%>
<%--								<html:option value="女">女</html:option>--%>
<%--							</html:select>--%>
								<html:text name="rs" property="xb" disabled="true"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>年龄
						</th>
						<td>
							<html:text name="rs" property="nl"  maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
							<logic:notEqual value="stu" name="userType" scope="session">
<%--								<logic:equal value="xy" name="userType" scope="session">--%>
<%--								<html:select  name="rs" property="xy" onchange="" --%>
<%--											styleClass="select" style="width:180px;display: none" styleId="xy">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<html:options collection="xyList" property="xydm"--%>
<%--												labelProperty="xymc" />--%>
<%--										</html:select>--%>
<%--										<html:text  name="rs" property="xymc" disabled="true"></html:text>--%>
<%--								</logic:equal>--%>
<%--								<logic:notEqual value="xy" name="userType" scope="session">--%>
<%--										<html:select name="rs" property="xy" onchange="initBjList();"--%>
<%--										styleClass="select" style="width:180px" styleId="xy">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<html:options collection="xyList" property="xydm"--%>
<%--												labelProperty="xymc" />--%>
<%--										</html:select>--%>
<%--										</logic:notEqual>--%>
											<html:text  name="rs" property="xymc" disabled="true"></html:text>
										</logic:notEqual>
										<logic:equal value="stu" name="userType" scope="session">
										<html:select  name="rs" property="xy" onchange="" 
											styleClass="select" style="width:180px;display: none" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
										</html:select>
										<html:text  name="rs" property="xymc" disabled="true"></html:text>
										</logic:equal>
						</td>
					</tr>
					<tr>
						<th>
							班级
						</th>
						<td>
							<logic:equal value="stu" name="userType" scope="session"> 
										<html:select name="rs" property="bj" style="width:180px;display: none" 
										styleClass="select" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
										</html:select>
										<html:text name="rs" property="bjmc" disabled="true"></html:text>
										</logic:equal>
											<logic:notEqual value="stu" name="userType" scope="session">
<%--										<html:select name="rs" property="bj" style="width:180px" --%>
<%--										styleClass="select" styleId="bj">--%>
<%--											<html:option value=""></html:option>--%>
<%--											<html:options collection="bjList" property="bjdm"--%>
<%--												labelProperty="bjmc" />--%>
<%--										</html:select>--%>
											<html:text name="rs" property="bjmc" disabled="true"></html:text>
										</logic:notEqual>
						</td>
						<th>
							联系方式
						</th>
						<td>
							<html:text name="rs" property="lxfs"  maxlength="50"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							电子邮箱
						</th>
						<td>
							<html:text name="rs" property="dzyx"  maxlength="50"></html:text>
						</td>
						<th>
							协会职务
						</th>
						<td>
							<html:text name="rs" property="xhzy"  maxlength="50"></html:text>
						</td>
					</tr>
					<tr id="scfxV">
						<th>
							专长
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="zc" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="bzV">
						<th>
							备注
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="bz" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <logic:notEqual value="view" name="view">
			          <div class="bz">"<span class="red">*</span>"为必填项</div>
			          </logic:notEqual>
			          <div class="btn">
						  <logic:notEqual name="view" value="view">
							<button onclick="dataSave();" id="buttonSave">
								保存
							</button>
						  </logic:notEqual>
						  <button name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
		<logic:equal value="yes" name="done">
			<script language="javascript">
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="yes" name="isexists">
			<script language="javascript">
					alert("该楼幢已经在本学年申请了，不必重复申请！");
					//Close();
					//window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="done">
				<script language="javascript">
						alert("操作失败！");
						Close();
						window.dialogArguments.document.getElementById('search_go').click();
				</script>
		</logic:equal>
	</body>
</html>
