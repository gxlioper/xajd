<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/zjlg_xljk.do?method=gzlxAdd&act=add";
		 var yzdz = "txdtV-txqkV-xycljgV";
		 var yzcd = "600-600-600";
		 var mustFill = "xlwyxm-xydm-bjdm";
		 //var sjhm = document.getElementById("sjhm").value;
		 //var dzyx = document.getElementById("dzyx").value;
		 //if(!isNumber(sjhm)){
		//	alert("手机号码必须是数字");
		//	return false;
		 //}
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
		 //var dzyx = document.getElementById("dzyx").value;
	     //if(dzyx!=""){
		//	if(!isEmail(dzyx)){
		//		alert("电子邮箱格式不正确！！");
		//		return false;
		//	}
	     //}
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
	    	document.getElementById("buttongzlxSave").disabled = true;
	 }
	 function printgzlx(){
			document.forms[0].action = "/xgxt/zjlg_xljk.do?method=getxsInfoprint";
			document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
	 }
	 function whoscheck(){
		var usertype = document.getElementById("userType").value;
		if(usertype == "stu"){
			document.getElementById("xycljgV").style.display = "none";
			document.getElementById("bzV").style.display = "none";
			document.getElementById("xycljgV2").style.display = "none";
		}
	 }
	</script>
	</head>
	
	<body onload="whoscheck();">
		<html:form action="/zjlg_xljk" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 工作联系 - 工作联系</a>
				</p>
			</div>
			
			<div align=center>
				<input type="hidden" id="tableName" name="tableName"
					value="zjlg_gzlxb" />
				<input type="hidden" id="userType" name="userType"
					value="<bean:write name="userType" scope="session"/>" />
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" id="url" name="url"
				value="/zjlg_xljk.do?method=getgzlxforxh" />
				<html:hidden name="rs" property="xh" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm" />
				<div class="tab">
					<table width="100%"  border="0" class="formlist">
					 <thead>
	    				<tr>
	        				<th colspan="6"><span>字段维护</span></th>
	        			</tr>
	   				 </thead>
	   				 <tbody>
					<tr>
						<th>
							<logic:notEqual value="view" name="view">
								<font color="red">*</font>
							</logic:notEqual>
							心理委员姓名
						</th>
						<td class="Normal">
							<logic:equal value="admin" name="userType" scope="session">
								<html:text name="rs" property="xlwyxm" maxlength="8"></html:text>
								<logic:equal value="add" name="view">
										<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
											class="btn_01" id="buttonFindStu" >
											选择
										</button>
										</logic:equal>
							</logic:equal>
							<logic:notEqual value="admin" name="userType" scope="session">
								<logic:equal value="stu" name="userType" scope="session">
									<html:text name="rs" property="xlwyxm" maxlength="8"
										readonly="true"></html:text>
								</logic:equal>
								<logic:notEqual value="stu" name="userType" scope="session">
									<html:text name="rs" property="xlwyxm" maxlength="8"></html:text>
								</logic:notEqual>
							</logic:notEqual>
							<html:hidden name="rs" property="id" />
						</td>
						<th class="Normal" align="right">
							<logic:notEqual value="view" name="view">
								<font color="red">*</font>
							</logic:notEqual>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td class="Normal">
<%--							<logic:equal value="admin" name="userType" scope="session">--%>
<%--								<html:select name="rs" property="xydm" onchange="initBjList();"--%>
<%--									styleClass="select" style="width:180px" styleId="xy">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="xyList" property="xydm"--%>
<%--										labelProperty="xymc" />--%>
<%--								</html:select>--%>
<%--							</logic:equal>--%>
<%--							<logic:notEqual value="admin" name="userType" scope="session">--%>
<%--								<html:select name="rs" property="xydm" onchange="initBjList();"--%>
<%--									styleClass="select" style="width:180px;display: none"--%>
<%--									styleId="xy">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="xyList" property="xydm"--%>
<%--										labelProperty="xymc" />--%>
<%--								</html:select>--%>
<%--								<input type="text"--%>
<%--									value="<bean:write name="bmmc" scope="session"/>"--%>
<%--									disabled="disabled" />--%>
<%--							</logic:notEqual>--%>
							<html:text name="rs" property="xymc" disabled="true"></html:text>
						</td>
					</tr>
					<tr>
						<th class="Normal" align="right">
							<logic:notEqual value="view" name="view">
								<font color="red">*</font>
							</logic:notEqual>
							班级
						</th>
						<td class="Normal">
<%--							<logic:equal value="stu" name="userType" scope="session">--%>
<%--								<html:select name="rs" property="bjdm"--%>
<%--									style="width:180px;display: none" styleId="bj">--%>
<%--									<logic:notEqual value="yes" name="isBzr">--%>
<%--										<html:option value=""></html:option>--%>
<%--									</logic:notEqual>--%>
<%--									<html:options collection="bjList" property="bjdm"--%>
<%--										labelProperty="bjmc" />--%>
<%--								</html:select>--%>
<%--								<input type="text"--%>
<%--									value="<bean:write name="rs" property="bjmc"/>"--%>
<%--									disabled="disabled" />--%>
<%--							</logic:equal>--%>
<%--							<logic:notEqual value="stu" name="userType" scope="session">--%>
<%--								<html:select name="rs" property="bjdm" style="width:180px"--%>
<%--									styleId="bj">--%>
<%--									<logic:notEqual value="yes" name="isBzr">--%>
<%--										<html:option value=""></html:option>--%>
<%--									</logic:notEqual>--%>
<%--									<html:options collection="bjList" property="bjdm"--%>
<%--										labelProperty="bjmc" />--%>
<%--								</html:select>--%>
<%--							</logic:notEqual>--%>
							<html:text name="rs" property="bjmc" disabled="true"></html:text>
						</td>
						<th class="Normal" align="right" nowrap="nowrap">
							联系方式
						</th>
						<td class="Normal">
							<html:text name="rs" property="lxfs" maxlength="49"></html:text>
						</td>
					</tr>
					<tr>
						<th class="Normal" align="right" nowrap="nowrap">
							记录时段
						</th>
						<td class="Normal">
							<html:text name="rs" property="jlsd" maxlength="49"></html:text>
						</td>
						<th class="Normal" align="right">

						</th>
						<td class="Normal">
						</td>
					</tr>
					<tr id="txdtV">
						<th>
							班级情况及
							<br/>
							同学动态记录
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="txdt" rows="5"
								style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="txqkV">
						<th>
							需要关注的
							<br/>
							同学情况记录
						</th>
						<td class="Normal" colspan="3">
							<html:textarea name="rs" property="txqk" rows="5"
								style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="xycljgV">
						<th class="Normal" align="right" nowrap="nowrap" rowspan="2">
							<bean:message key="lable.xsgzyxpzxy" />处理结果
						</th>
						<td class="Normal" colspan="3">
							<html:textarea name="rs" property="xycljg" rows="5"
								style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="xycljgV2">
						<td class="Normal" align="" nowrap="nowrap" colspan="3">
							<bean:message key="lable.xsgzyxpzxy" />心理健康指导老师：
							<html:select name="rs" property="xyxljkzdls">
								<html:option value=""></html:option>
								<html:options collection="zdlsList" property="xm"/>
							</html:select>
						</td>
					</tr>
					<tr id="bzV">
						<th class="Normal" align="right">
							是否需要反
							<br/>
							馈校心理健
							<br/>
							康教育中心
						</th>
						<td class="Normal" colspan="3">
							<html:select name="rs" property="sffk">
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
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
							 <logic:equal value="view" name="view">
								<button  onclick="printgzlx();" style="width: 80px"
									id="buttonSave">
									打印
								</button>
							</logic:equal>
							<logic:notEqual value="view" name="view">
								<button  onclick="dataSave();" style="width: 80px"
									id="buttongzlxSave">
									保存
								</button>
								&nbsp;&nbsp;&nbsp;
								<button  onclick="window.Close();"
									style="width: 80px" id="buttonSave">
									关闭
								</button>
							</logic:notEqual>
				          </div></td>
				      </tr>
					</tfoot>
				</table>
			</div>
			
		</html:form>
		<logic:present name="notxlwy">
			<logic:equal value="yes" name="notxlwy">
				<script language="javascript">
			alert("你不是心里委员，不能进行此操作！！");
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
	</script>
			</logic:equal>
		</logic:present>

		<logic:present name="done">
			<logic:equal value="yes" name="done">
				<script language="javascript">
			alert("操作成功！");
			Close();
			window.dialogArguments.document.getElementById('search_go').click();
	</script>
			</logic:equal>
			<logic:equal value="no" name="done">
				<script language="javascript">
				alert("操作失败！");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
		</script>
			</logic:equal>
		</logic:present>
		<logic:present name="isexists">
			<logic:equal value="yes" name="isexists">
				<script language="javascript">
			alert("该楼幢已经在本学年申请了，不必重复申请！");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
