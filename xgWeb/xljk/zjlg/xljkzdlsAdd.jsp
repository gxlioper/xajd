<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript">
	 function dataSave(){
		 var url = "/xgxt/zjlg_xljk.do?method=xlzxsAdd&act=add";
		 var yzdz = "scfxV-xgpxV-bzV";
		 var yzcd = "400-400-400";
		 var mustFill = "xm-xb-nl";
		 var sjhm = document.getElementById("sjhm").value;
		 var dzyx = document.getElementById("dzyx").value;
		 if(sjhm!="" && !isNumber(sjhm)){
			alert("手机号码必须是数字");
			return false;
		 }
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
	</head>
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN onload="">
		<html:form action="/zjlg_xljk" method="post">
			<input type="hidden" id="tableName" name="tableName" value="zjlg_xljkzdls" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="titleName"/></a>
				</p>
			</div>
			<div class="searchtab">
				<table width="100%" class="formlist" border="0">
					<thead><tr><th colspan="4"><span>信息维护</span></th></tr></thead>
					<tbody>
					<tr>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>姓名 
						</th>
						<td>
							<logic:equal value="admin" name="userType" scope="session">
								<html:text name="rs" property="xm"  maxlength="8"></html:text>
							</logic:equal>
							<logic:notEqual value="admin" name="userType" scope="session">
								<html:text name="rs" property="xm"  maxlength="8"></html:text>
							</logic:notEqual>
							<html:hidden name="rs" property="id"/>
						</td>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>性别
						</th>
						<td>
							<html:select name="rs" property="xb">
								<html:option value=""></html:option>
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>年龄
						</th>
						<td>
							<html:text name="rs" property="nl" maxlength="3"  onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td>
						<logic:equal value="admin" name="userType" scope="session">
							<html:select name="rs" property="xy" onchange=""
								styleClass="select" style="width:180px" styleId="xy">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
							</logic:equal>
							<logic:notEqual value="admin" name="userType" scope="session">
								<html:select name="rs" property="xy" onchange="" 
									styleClass="select" style="width:180px;display: none" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								<input type="text" value="<bean:write name="bmmc" scope="session"/>" disabled="disabled"/>
							</logic:notEqual>
						</td>
					</tr>
					<tr>
						<th>
							办公电话
						</th>
						<td class="Normal">
							<html:text name="rs" property="bgdh" maxlength="20"></html:text>
						</td>
						<th>
							手机
						</th>
						<td  class="Normal">
							<html:text name="rs" property="sjhm"  maxlength="15"  onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>
					</tr>
					<tr>
						<th>
							电子邮箱
						</th>
						<td  class="Normal">
							<html:text name="rs" property="dzyx"  maxlength="50"></html:text>
						</td>
						<th>
							学历
						</th>
						<td  class="Normal">
							<html:text name="rs" property="xl"  maxlength="30"></html:text>
						</td>
					</tr>
					<tr>
						<th>
							毕业学校
						</th>
						<td class="Normal">
							<html:text name="rs" property="byxy"  maxlength="50"></html:text>
						</td>
						<th  nowrap="nowrap"> 
							毕业专业
						</th>
						<td>
							<html:text name="rs" property="byzy"  maxlength="50"></html:text>
						</td>
					</tr>
					<tr>
						<th nowrap="nowrap">
							咨询值班时间
						</th>
						<td class="Normal">
							<html:text name="rs" property="zxzbsj"  maxlength="20"></html:text>
						</td>
						<th nowrap="nowrap">
							考勤统计
						</th>
						<td class="Normal">
							<html:text name="rs" property="kqtj"  maxlength="100"></html:text>
						</td>
					</tr>
					<tr id="scfxV">
						<th>
							擅长
						</th>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="sc" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="xgpxV">
						<th>
							相关培训
						</th>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="xgpx" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="bzV">
						<th>
							备注
						</th>
						<td  class="Normal" colspan="3">
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
