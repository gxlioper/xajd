<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript">
		function xlkhxsftjl_save(){
			var xh=document.all["xh"].value;
			if ( xh==""){
				alert ("请根据选择按键选择好学生信息！");
				document.all["xh"].focus();
				return false;
			}
			var ftsj=document.all["ftsj"].value;
			if ( ftsj==""){
				alert ("请填写访谈日期！");
				document.all["ftsj"].focus();
				return false;
			}
			var ftdd=document.all["ftdd"].value;
			if ( ftdd==""){
				alert ("请填写访谈地点！");
				document.all["ftdd"].focus();
				return false;
			}
			var ftr=document.all["ftr"].value;
			if ( ftr==""){
				alert ("填写访谈人！");
				document.all["ftr"].focus();
				return false;
			}
			var ftjl=document.all["ftjl"].value;
			if ( ftjl==""){
				alert ("请填写访谈记录！");
				document.all["ftjl"].focus();
				return false;
			}
			var sjhm=document.all["sjhm"];
			sjhm.value = sjhm.value.replace(/[^(\d|\.)]/g,'');
			var inputStr = sjhm.value;
			if((inputStr != null && inputStr != "")&&!inputStr.match(/\d+/g)){		
				alert("手机号码请输入数字类型的数据！");
				sjhm.value = '';
				sjhm.focus();
				return false;
			}
			document.all["add_flag"].value="yes";
			underDealWith();
			refreshForm('/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=xlkhxsftjl_add');
		}
		function jd(){
			if($("jd")){
				$("jd").focus();
			}
		}
	</script>
	</head>
	<body onload="jd()">
		<html:form action="/xljk_xlkhxsftjl" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
			
			<input type="hidden" id="add_flag" name="add_flag" value="no" />
			<div class="tab">
			  <table width="100%"  border="0" class="formlist">
			    <thead>
			    	<tr>
			        	<th colspan="6"><span>增加心理困惑学生访谈记录</span></th>
			        </tr>
			    </thead>
				 <tfoot>
			      <tr>
			        <td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
			          <div class="btn">
			          	<button name="保存" onclick="xlkhxsftjl_save()" id="buttonSave">保 存</button>
			            <button name="关闭" onclick="Close();return false;" id="buttonClose">关 闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
				
			<tbody>
				<tr style="height:22px">
					<th align="right" colspan="2" nowrap="nowrap">
						<font color="red">*</font>被访学生学号
					</th>
					<td align="left">
						<html:text property="xh" styleId="xh" onblur="" onkeypress=""
							readonly="true" />
						<button
							onclick="showTopWin('/xgxt/xljk_xlkhxsftjl.do?act=xljk_xlkhxsftjl&doType=stu_info',750,550);"
							class="btn_01" id="buttonFindStu">
							选择
						</button>
					</td>

					<th align="right">
						姓名
					</th>
					<td align="left">
						<html:text property="xm" styleId="xm" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2" readonly="true">
						性别
					</th>
					<td align="left">
						<html:text property="xb" styleId="xb" readonly="true" />
					</td>
					<th align="right">
						<bean:message key="lable.xb" />
					</th>
					<td align="left">
						<html:text property="xymc" styleId="xymc" readonly="true" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" colspan="2">
						班级
					</th>
					<td align="left">
						<html:text property="bjmc" styleId="bjmc" readonly="true" />
					</td>
					<th align="right" nowrap="nowrap">
						<font color="red">*</font>访谈日期
					</th>
					<td align="left">
						<html:text style="cursor:hand;" styleId="dateF"
							property="ftsj" onclick="return showCalendar('dateF','y-mm-dd');"
							readonly="readonly" />
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						<font color="red">*</font>访谈地点
					</th>
					<td align="left">
						<html:text property="ftdd" maxlength="20"/>
					</td>
					<th align="right">
						<font color="red">*</font> 访谈人
					</th>
					<td align="left" colspan="4">
						<html:text property="ftr" maxlength="10"/>
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						手机号码
					</th>
					<td align="left">
						<html:text property="sjhm" maxlength="20" onkeyup="value=value.replace(/[^\d|-]/g,'') "/>
					</td>
					<th align="right">
					</th>
					<td align="left" colspan="4">
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						<font color="red">*</font>访谈记录
					</th>
					<td align="left" colspan="6">
						<html:textarea rows="20" style="width:98%" property="ftjl"
							styleId="a" onblur="chLeng(this,2000)"/>
					</td>
				</tr>
				<tr>
					<th align="right" colspan="2">
						备注
					</th>
					<td align="left" colspan="6">
						<html:textarea rows="3" style="width:98%" property="bz"
							styleId="a" onblur="chLeng(this,1000)"/>
					</td>
				</tr>
			</table>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		
	</body>
	<logic:notEmpty name="message">
		<logic:equal name="message" value="gcybh_exits">
			<script>alert("编号已经存在!增加失败")</script>
		</logic:equal>
		<logic:equal name="message" value="xh_exits">
			<script>alert("该学生已经存在!增加失败")</script>
		</logic:equal>
		<logic:equal name="message" value="insert_success">
			<script>
				alert("增加成功!");
				window.dialogArguments.document.getElementById("search_go1").click();
				Close();
			</script>
		</logic:equal>
		<logic:equal name="message" value="insert_fail">
			<script>
				alert("增加失败!");
				document.getElementById("tmpdiv").innerHTML = "";
			</script>
		</logic:equal>
	</logic:notEmpty>
</html>
