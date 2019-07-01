<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/xsgbxx.do?method=gzzjAdd&act=add";
		 var yzdz = "gzzjV";
		 var yzcd = "800";
		 var mustFill = "xh-gzzjlx";
		 checkdataSave(url,yzdz,yzcd,mustFill);
		 }
	 function isNumber(s){
			var p = /^(-|\+)?\d+$/;
			return p.test(s); 
		    } 
	 function checkdataSave(url,yzdz,yzcd,mustFill){
	     
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
	    	showTips('处理数据中，请等待......');
	    	document.forms[0].action = url;
	    	document.forms[0].submit();
	    	document.getElementById("buttonSave").disabled=true;
	    	document.getElementById("buttonQx").disabled=true;
	 }
	 
	 function printwmgylgl(){
			document.forms[0].action = "/xgxt/jhzy_gygl.do?method=wmgylprint";
			document.forms[0].target = "_blank";
		    document.forms[0].submit();
		    document.forms[0].target = "_self";
	 }
	</script>
	</head>
	<body onload="cheangelx();">
		<html:form action="/xsgbxx" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>社会工作 - 学生干部 - 工作总结录入</a>
				</p>
			</div>
				<input type="hidden" name="zyV" id="zyV" />
				<input type="hidden" name="bjV" id="bjV" />
				<input type="hidden" id="tableName" name="tableName"
					value="zjlg_gzzjb" />
				<input type="hidden" id="username" name="username"
					value="<bean:write name="userType" scope="session"/>" />
				<input type="hidden" id="url" name="url"
					value="/xsgbxx.do?method=getxsInfo&forwardname=gzzj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh" />
				<input type="hidden" id="tjlx" name="tjlx" value="gzzj" />
				<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>字段维护</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>学号
								</th>
								<td>
									<html:hidden property="id" name="rs" />
									<html:text name="rs" property="xh" style="width: 200px"
										readonly="true"
										onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<logic:equal value="add" name="doType">
										<logic:notEqual value="stu" name="userName" scope="session">
											<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
												class="btn_01" id="buttonFindStu">
												选择
											</button>
										</logic:notEqual>
									</logic:equal>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text name="rs" property="xm" style="width: 200px"
										readonly="true" />
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td>
									<html:text name="rs" property="xb" style="width: 200px"
										maxlength="10" readonly="true" />
								</td>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:text name="rs" property="xydm"
										style="width: 200px;display: none" readonly="true" />
									<input value="<bean:write name="rs" property="xymc"/>"
										readonly="readonly" disabled="disabled"
										style="width: 250px;display: " />
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td>
									<html:text name="rs" property="bjdm" style="100%;display: none"
										readonly="true" />
									<input value="<bean:write name="rs" property="bjmc"/>"
										disabled="disabled" style="width: 200px;display: " />
								</td>
								<th>
									<font color="red">*</font>工作总结类型
								</th>
								<td>
									<html:select name="rs" property="gzzjlx" style="width:"
										styleId="gzzjlx" onchange="cheangelx();">
										<html:option value=""></html:option>
										<html:option value="学年">学年</html:option>
										<html:option value="学期">学期</html:option>
										<html:option value="月份">月份</html:option>
									</html:select>
								</td>
							</tr>
							<tr>

								<th>
									<span id="xnV1"> 学年 </span>
								</th>
								<td>
									<span id="xnV"> <html:select name="rs" property="xn"
											style="width:" styleId="xn">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select> </span>
								</td>
								<th>
									<span id="xqV1"> 学期 </span>
								</th>
								<td>
									<span id="xqV"> <html:select name="rs" property="xq"
											style="width:" styleId="xn">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqmc"
												labelProperty="xqmc" />
										</html:select> </span>
								</td>
							</tr>
							<tr id="yfV">
								<th align="right">
									月份
								</th>
								<td>
									<html:select name="rs" property="yf" style="width:">
										<html:option value=""></html:option>
										<html:options collection="yfList" property="yf"
											labelProperty="yf" />
									</html:select>
								</td>
								<td align="right">
								</td>
								<td></td>
							</tr>
							<tr id="gzzjV">
								<th align="center">
									工作总结<br/>
									<font color="red">(限制字数800)</font>
								</th>
								<td colspan="6">
									<html:textarea name="rs" property="gzzj" rows="8" onblur="chLeng(this,800);"
										style="word-break:break-all;width:99%" />
								</td>
							</tr>
						</tbody>
						<tfoot
							<logic:equal value="view" name="view">
					style="display: none"
					</logic:equal>>
							<tr>
								<td colspan="6">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button onclick="dataSave();" id="buttonSave">
											保存
										</button>
										<button onclick="Close();return false;" id="buttonQx">
											取消
										</button>
									</div>
								</td>
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
		<logic:equal value="yes" name="existx">
			<script language="javascript">
			alert("用户已存在！");
			//Close();
			//window.dialogArguments.document.getElementById('search_go').click();
	</script>
		</logic:equal>
		<script type="text/javascript">
		function cheangelx(){
			var cheangelx = document.getElementById("gzzjlx").value;
			if(cheangelx == "学年"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="none";
				document.getElementById("xnV1").style.display="inline";
				document.getElementById("xqV1").style.display="none";
				document.getElementById("yfV").style.display="none";
			}else if(cheangelx == "学期"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="inline";
				document.getElementById("xnV1").style.display="inline";
				document.getElementById("xqV1").style.display="inline";
				document.getElementById("yfV").style.display="none";
			}else if(cheangelx == "月份"){
				document.getElementById("xnV").style.display="inline";
				document.getElementById("xqV").style.display="inline";
				document.getElementById("xnV1").style.display="inline";
				document.getElementById("xqV1").style.display="inline";
				document.getElementById("yfV").style.display="inline";
			}else{
				document.getElementById("xnV").style.display="none";
				document.getElementById("xqV").style.display="none";
				document.getElementById("xnV1").style.display="none";
				document.getElementById("xqV1").style.display="none";
				document.getElementById("yfV").style.display="none";
			}
	 	}
	</script>
	</body>
</html>
