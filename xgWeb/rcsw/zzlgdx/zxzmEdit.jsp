<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script language="javascript">
		function yz(){
			var xh = document.getElementById('xh').value;
			var zxbxjwhcdbx = document.getElementById('zxbxjwhcdbx').value;
			
			if((xh == null) || (xh == "")){
				alert("学号不能为空!");
				return false;
			}
			if(zxbxjwhcdbx != null){
	         	if(zxbxjwhcdbx.replace(/[^\u0000-\u00ff]/g, "**").length > 4000){	         
	          		 alert("在校表现及文化程度鉴定情况不能大于2000个字");
	          		 return false;
	       		 }
	       	}
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=zxzmEditSave";
			document.forms[0].submit();
		}
		function yzdx(obj,str){
			document.getElementById(str).value = obj.value;
		}
		function toPrintOut(){//输出相应的打印页面
			document.forms[0].action = "/xgxt/zzlgdx_rcsw.do?method=zxzmDy";
			document.forms[0].target = "_blank";
	        document.forms[0].submit();
	        document.forms[0].target = "_self";
		}
	</script>
</head>

<body>
	<html:form action="zzlgdx_rcsw.do?method=zxzmEdit" method="post">
		<input type="hidden" id="url" name="url"
			value="/zzlgdx_rcsw.do?method=zxzmEdit" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-xymc-bjmc" />
		<input type="hidden" id="pkVal" name="pkVal"
			value="<bean:write name="rs" property="pkVal" />" />
		<input type="hidden" id="type" name="type"
			value="<bean:write name="type" />" />

		<logic:present name="ok">
			<logic:match value="ok" name="ok">
				<script language="javascript">
	         	alert("保存成功！");
	         	</script>
			</logic:match>
			<logic:match value="no" name="ok">
				<script language="javascript">
	         	alert("重复记录！");
	         	</script>
			</logic:match>
		</logic:present>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>在校表现及文化程度信息维护</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist" width="100%">
			<thead><tr><th colspan="4"><span>信息维护</span></th></tr></thead>
			<tbody>
			<tr>
				<th>
					<font color="red">*</font>学号
				</th>
				<td align="left" width="34%">
					<html:text name='rs' property="xh" styleId="xh" readonly="true"
						onkeypress="autoFillStuInfo(event.keyCode,this)" />
					<logic:notEqual name="type" value="modi">
						<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</logic:notEqual>
				</td>
				<th>
					姓名
				</th>
				<td width="34%">
					<input type="text" id="xm" name="xm" 
					value="<bean:write name="rs" property="xm"/>" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<th>
					性别
				</th>
				<td>
					<input type="text" id="xb" readonly="readonly" name="xb"
						value="<bean:write name="rs" property="xb"/>" />
				</td>
				<th>
					身份证号
				</th>
				<td>
					<input type="text" id="sfzh" name="sfzh" readonly="readonly"
						value="<bean:write name="rs" property="sfzh"/>" />
				</td>
			</tr>
			<tr>
				<th>
					民族
				</th>
				<td>
					<input type="text" id="mzmc" name="mzmc" readonly="readonly"
						value="<bean:write name="rs" property="mzmc"/>" />
				</td>
				<th>
					政治面貌
				</th>
				<td>
					<input type="text" id="zzmmmc" name="zzmmmc" readonly="readonly"
						value="<bean:write name="rs" property="zzmmmc"/>" />
				</td>
			</tr>
			<tr>
				<th>
					出生日期
				</th>
				<td>
					<input type="text" id="csrq" readonly="readonly" name="csrq"
						value="<bean:write name="rs" property="csrq"/>" />
				</td>
				<th>
					入学时间
				</th>
				<td>
					<input type="text" id="rxrq" name="rxrq" readonly="readonly"
						value="<bean:write name="rs" property="rxrq"/>" />
				</td>
			</tr>
			<tr>
				<th>
					年级
				</th>
				<td>
					<input type="text" id="nj" readonly="readonly" name="nj"
						value="<bean:write name="rs" property="nj"/>" />
				</td>
				<th>
					<bean:message key="lable.xsgzyxpzxy" />名称
				</th>
				<td>
					<input type="text" id="xymc" name="xymc" readonly="readonly"
						value="<bean:write name="rs" property="xymc"/>" />
				</td>
			</tr>
			<tr>
				<th>
					专业名称
				</th>
				<td>
					<input type="text" id="zymc" readonly="readonly" name="zymc"
						value="<bean:write name="rs" property="zymc"/>" />
				</td>
				<th>
					班级名称
				</th>
				<td>
					<input type="text" id="bjmc" name="bjmc" readonly="readonly"
						value="<bean:write name="rs" property="bjmc"/>" />
				</td>
			</tr>
			<tr>
				<th>
					学制
				</th>
				<td>
					<input type="text" id="xz" readonly="readonly" name="xz"
						value="<bean:write name="rs" property="xz"/>" />
				</td>
				<th>
					录入时间
				</th>
				<td>
					<input type="text" id="lrsj" name="lrsj" readonly="readonly"
						value="<bean:write name="rs" property="lrsj"/>" />
				</td>
			</tr>
			<tr>
				<th>
					在校表现及文化<br />程度鉴定情况
				</th>
				<td colspan="3">
					<html:textarea name="rs" property="zxbxjwhcdbx" rows='10'
						style="width: 95%;word-break:break-all;" onblur="yzdx(this,'zxbxjwhcdbx')" />
					<input type="hidden" id="zxbxjwhcdbx" name="zxbxjwhcdbx" value="" />
				</td>
			</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		          	<logic:notEqual name="type" value="view">
					<button type="button" id="buttonSave" onclick="yz();">
						提交
					</button>
					</logic:notEqual>
					<button type="button" onclick="toPrintOut();" id="buttonPrint">
						打印
					</button>
					 <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
	</div>
	</html:form>
</body>
</html>
