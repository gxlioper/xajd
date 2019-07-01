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
		 if(!isNumber(sjhm)){
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
	<body bgcolor="#FFFFFF" class="Normal" lang=ZH-CN>
		<html:form action="/zjlg_xljk" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：<bean:write name="titleName"/>
				</div>
			</div>
			<div align=center>
			<input type="hidden" id="tableName" name="tableName" value="zjlg_xlzxs" />
				<table border=1 cellspacing=0 cellpadding=0 width="100%" class="tbstyle">
					<tr>
						<td  class="Normal" align="right">
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>姓名 
						</td>
						<td  class="Normal">
							<html:text name="rs" property="xm"  maxlength="8"></html:text>
							<html:hidden name="rs" property="id"/>
						</td>
						<td  class="Normal" align="right">
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>性别
						</td>
						<td  class="Normal">
							<html:select name="rs" property="xb">
								<html:option value=""></html:option>
								<html:option value="男">男</html:option>
								<html:option value="女">女</html:option>
							</html:select>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							<logic:notEqual value="view" name="view"><font color="red">*</font></logic:notEqual>年龄
						</td>
						<td  class="Normal">
							<html:text name="rs" property="nl"  maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>
						<td class="Normal" align="right">
							单位
						</td>
						<td  class="Normal">
							<html:text name="rs" property="dw"  maxlength="50"></html:text>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							办公电话
						</td>
						<td  class="Normal">
							<html:text name="rs" property="bgdh" maxlength="20"></html:text>
						</td>
						<td class="Normal" align="right">
							手机
						</td>
						<td  class="Normal">
							<html:text name="rs" property="sjhm"  maxlength="15" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right">
							电子邮箱
						</td>
						<td  class="Normal">
							<html:text name="rs" property="dzyx"  maxlength="20"></html:text>
						</td>
						<td class="Normal" align="right">
							专业资质
						</td>
						<td  class="Normal">
							<html:text name="rs" property="zyzz"  maxlength="20"></html:text>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right" nowrap="nowrap">
							咨询工作年限
						</td>
						<td class="Normal">
							<html:text name="rs" property="zxgznx"  maxlength="20"></html:text>
						</td>
						<td class="Normal" align="right" nowrap="nowrap">
							咨询值班时间
						</td>
						<td colspan= class="Normal">
							<html:text name="rs" property="zxzbsj"  maxlength="20" readonly="true" onclick="return showCalendar('zxzbsj','y-mm-dd');"></html:text>
						</td>
					</tr>
					<tr>
						<td class="Normal" align="right" nowrap="nowrap">
							考勤统计
						</td>
						<td class="Normal">
							<html:text name="rs" property="kqtj"  maxlength="100"></html:text>
						</td>
						<td class="Normal" align="right" nowrap="nowrap">
						</td>
						<td colspan= class="Normal">
						</td>
					</tr>
					<tr id="scfxV">
						<td class="Normal" align="right">
							擅长方向
						</td>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="scfx" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="xgpxV">
						<td class="Normal" align="right">
							相关培训
						</td>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="xgpx" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
					<tr id="bzV">
						<td class="Normal" align="right">
							备注
						</td>
						<td  class="Normal" colspan="3">
							<html:textarea name="rs" property="bz" rows="5" style="width: 99%"></html:textarea>
						</td>
					</tr>
				</table>
			</div>
			
			<div class="buttontool" id="btn" align="center" 
			<logic:equal value="view" name="view">
			style="display: none"
			</logic:equal>>
				<button class="button2" onclick="dataSave();" style="width: 80px" id="buttonSave">
					保存
				</button>
				&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="window.Close();" style="width: 80px" id="buttonSave">
					关闭
				</button>
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
	<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "100%";
					window.setInterval("initBTNTool('btn')",1);
	</script>
</html>
