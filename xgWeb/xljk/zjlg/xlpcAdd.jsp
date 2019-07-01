<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/zjlg_xljk.do?method=xlpcbAdd&act=add";
		 var yzdz = "zzjlV-zxjyV-wtgsV";
		 var yzcd = "1200-1200-1200";
		 var mustFill = "xh-xm-xydm-bjdm";
		 //var sjhm = document.getElementById("sjhm").value;
		 //var dzyx = document.getElementById("dzyx").value;
		 //if(!isNumber(sjhm)){
			//alert("手机号码必须是数字");
			//return false;
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
			//	return false;
		//	}
	    // }
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
	</head>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<html:form action="/zjlg_xljk" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="title"/></a>
				</p>
			</div>
			<input type="hidden" id="tableName" name="tableName" value="zjlg_xlcb" />
			<input type="hidden" id="url" name="url" value="/zjlg_xljk.do?method=getxsInfoforxh" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xh-xm" />	
			<div class="tab">
				<table width="100%" class="formlist">
					<thead><tr><th colspan="4"><span>心里普查</span></th></tr></thead>
					<tbody>
					<tr>			
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>学号	 
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xh" readonly="true" maxlength="20"></html:text>
							<logic:notEqual value="stu" name="userType" scope="session">
								<logic:equal value="add" name="view">
								<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									选择
								</button>
								</logic:equal>
							</logic:notEqual>
							<html:hidden name="rs" property="id"/>
						</td>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>姓名
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xm" readonly="true" maxlength="20"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual><bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td  class="Normal">
							<html:select name="rs" property="xydm" onchange="initBjList();"
										styleClass="select" style="width:180px;display: none" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm"
												labelProperty="xymc" />
							</html:select>
							<html:text name="rs" property="xymc" readonly="true" maxlength="20"></html:text>
						</td>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual> 班级
						</th>
						<td  class="Normal">
							<html:text name="rs" property="bjmc" readonly="true" maxlength="20"></html:text>
							<html:select name="rs" property="bjdm" style="width:180px;display: none" 
										styleClass="select" styleId="bj">
											<html:option value=""></html:option>
											<html:options collection="bjList" property="bjdm"
												labelProperty="bjmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							  年级
						</th>
						<td  class="Normal">
<%--							<html:select name="rs" property="nj" onchange="" style="width:90px;padding-left:80px">--%>
<%--									<html:option value=""></html:option>--%>
<%--									<html:options collection="njList" property="nj"--%>
<%--										labelProperty="nj" />--%>
<%--							</html:select>--%>
							<html:text name="rs" property="nj" readonly="true"></html:text>
						</td>
						<th>
							 性别
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xb" maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							  籍贯
						</th>
						<td  class="Normal" colspan="3">
							<html:text name="rs" property="jg"  maxlength="80" style="width: 100%"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							年龄
						</th>
						<td  class="Normal">
							<html:text name="rs" property="nl" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							 民族
						</th>
						<td  class="Normal">
							<html:text name="rs" property="mz"  maxlength="18"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							 是否独生子女
						</th>
						<td  class="Normal">
							<html:select name="rs" property="sfdszn">	
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
						<th>
							出生年月
						</th>
						<td  class="Normal">
							<html:text name="rs" property="csny"  maxlength="10" readonly="true" onclick="return showCalendar('csny','y-mm-dd');" onblur="dateFormatChg(this)"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							心理测试类别
						</th>
						<td  class="Normal">
<%--							<html:text name="rs" property="xlcslb" maxlength="30"></html:text>--%>
							<html:select name="rs" property="xlcslb">
								<html:option value=""></html:option>
								<html:options collection="xlcslbList" property="xlcslbdm" labelProperty="xlcslbmc"/>
							</html:select>
						</td>
						<th>
							是否困难生
						</th>
						<td  class="Normal">
							<html:select name="rs" property="sfkns">	
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							心理问题类型
						</th>
						<td  class="Normal">
<%--							<html:text name="rs" property="xlwtlx" maxlength="30"></html:text>--%>
							<html:select name="rs" property="xlwtlx">
								<html:option value=""></html:option>
								<html:options collection="xlwtlxList" property="xlwtlxdm" labelProperty="xlwtlxmc"/>
							</html:select>
						</td>
						<th>
							 躯体化标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="qthbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							焦虑标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="jlbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							抑郁标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="yybzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							自卑标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="zbbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							 社交退缩标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="sjtsbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap">
							性心理障碍标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xxlzabzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							 偏执标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="pzbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							强迫标准分
						</th>
						<td  class="Normal" colspan="">
							<html:text name="rs" property="qpbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th>
							依赖标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="ynbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap" onkeyup="value=value.replace(/[^\d]/g,'') ">
							冲动标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="cdbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
						<th nowrap="nowrap">
							 精神病倾向标准分
						</th>
						<td  class="Normal">
							<html:text name="rs" property="jsbqxbzf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="10"></html:text>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap" onkeyup="value=value.replace(/[^\d]/g,'') ">
							是否单亲
						</th>
						<td  class="Normal">
							<html:select name="rs" property="sfdq">	
								<html:option value=""></html:option>
								<html:option value="是">是</html:option>
								<html:option value="否">否</html:option>
							</html:select>
						</td>
						<th>
						</th>
						<td  class="Normal">
						</td>
					</tr>
					<tr id="wtgsV">
						<th>
							 问题概述
						</th>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="wtgs" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="zxjyV">
						<th>
							中心建议
						</th>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="zxjy" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="zzjlV">
						<th>
							追踪记录
						</th>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="zzjl" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4">
				          <logic:notEqual name="view" value="view">
				        	 <div class="bz">"<span class="red">*</span>"为必填项</div>
				          </logic:notEqual>
				          <div class="btn">
							  <logic:notEqual name="view" value="view">
								<button onclick="dataSave();" id="buttonSave">保存</button>
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
			alert("不必重复申请！");
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
