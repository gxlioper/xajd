<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->	
	<%@ include file="/syscommon/pagehead_V4.ini"%>		
	<script type="text/javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/stuinfoFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
	<script type="text/javascript">
		function Close() {
			var ua = navigator.userAgent;
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			if (ie) {
				var IEversion = parseFloat(ua.substring(ua.indexOf("MSIE ") + 5, ua.indexOf(";", ua.indexOf("MSIE "))));
				if (IEversion < 5.5) {
					var str = "<object id=noTipClose classid=\"clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11\">";
					str += "<param name=\"Command\" value=\"Close\"></object>";
					document.body.insertAdjacentHTML("beforeEnd", str);
					document.all.noTipClose.Click();
				} else {
					window.opener = null;
					window.close();
				}
			} else {
				window.close();
			}
		}
		
		function send(){	
			var ssbh = document.getElementById("ssbh").value;
			var ssch = document.getElementById("ssch").value;
			var zsrq = document.getElementById("zsrq").value;
			var zsjzrq = document.getElementById("zsjzrq").value;
			var ssdh = document.getElementById("ssdh").value;
			if(ssbh!=""){
				if(ssch==""){
					alert("宿舍床号不能为空！");
					return false;
				}
				if(ssch.length>1){
					alert("宿舍床号只能是一位字符!");
					return false;
				}
				
			}
			if(ssdh!=""){
				if(ssbh==null || ssbh==""){
					alert("宿舍号不能为空！");
					return false;
				}
			}
			if(zsrq > zsjzrq){
				alert("住宿截止日期晚于入住日期，请重新设置！！");
				return false;
			} 	
			//alert('123');
			stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");	
		}
	</script>
</head>
	<body>		
		<html:form action="/stu_info_add" method="post">
		<input type="hidden" value="${oper}" id="oper" />			
		<input type="hidden" name="url" id="url" value="/sjcz/stu_info_modify.jsp"/>
		<input type="hidden" name="variable" id="variable" value="ydinfo"/>
		<input type="hidden" name="redirect" id="redirect" value="true"/>
		<input type="hidden" name="notnull" id="notnull" value="xh-xm-bjdm-zydm-xydm-nj"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>个人信息-学生信息维护</a>
			</p>
		</div>
		<div class="tab">
			<table width="100%" class="formlist">
				<thead>
					<tr>
						<th colspan="5">
							<span>学生个人信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th><span class="red">*</span>学号</th>
					<td>
						<logic:equal value="update" name="oper">
							<html:text name="rs" styleId="xh" property="xh" readonly="true"
								style="cursor:hand" />
						</logic:equal>
						<logic:equal value="add" name="oper">
							<html:text name="rs" property="xh" styleId="xh" onkeyup="value=value.replace(/[^\d]/g,'') "
							maxlength="20"/>
						</logic:equal>
					</td>
					<th><span class="red">*</span>年级</th>
					<td>
						<html:select name="rs" property="nj" styleId="nj"
							style="width:90px"
							onchange="initZyList();initBjList();">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
					<td align="left" width="15%" rowspan="6">
						<img border="0" style="height:133px;width:100px;" src="/xgxt/pictures/${rs.xh}.jpg" id="pic"/>
					</td>
				</tr>
				<tr>
					<th><span class="red">*</span>姓名</th>
					<td>
						<html:text name="rs" property="xm" styleId="xm" maxlength="16"/>
					</td>
					<th><span class="red">*</span><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						<html:select name="rs" property="xydm" styleId="xy"
							style="width:180px"
							onchange="initZyList();initBjList()">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
						<input type="hidden" name="xyV" value="<bean:write name="xydm" scope="request"/>"/>						
					</td>
				</tr>
				<tr>
					<th>性别</th>
					<td>
						<html:radio name="rs" property="xb" value="1">男</html:radio>
						<html:radio name="rs" property="xb" value="2">女</html:radio>
					</td>
					<th><span class="red">*</span>专业</th>
					<td>
						<html:select name="rs" property="zydm" style="width:180px"
							styleId="zy"
							onchange="initBjList();">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
						<input type="hidden" name="zyV" value="<bean:write name="zydm" scope="request"/>"/>
					</td>
				</tr>
				<tr>
					<th>民族</th>
					<td>
						<html:select name="rs" property="mz" styleId="mz"
							style="width:150px">
							<html:options collection="mzList" property="mzdm"
								labelProperty="mzmc" />
						</html:select>
					</td>
					<th><span class="red">*</span>班级</th>
					<td>
						<html:select name="rs" property="bjdm" style="width:180px"
							styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
						<input type="hidden" name="bjV" value="<bean:write name="bjdm" scope="request"/>"/>
						
					</td>
				</tr>
				<tr>
					<th>政治面貌</th>
					<td>
						<html:select name="rs" property="zzmm" styleId="mz"
							style="width:150px">
							<html:options collection="zzmmList" property="zzmmdm"
								labelProperty="zzmmmc" />
						</html:select>
					</td>
					<th>学籍状态</th>
					<td>
						<html:select name="rs" property="xjzt" style="width:150">	
						<html:option value=""></html:option>
						<html:options collection="xjztList" property="zxdmmc" labelProperty="zxdmmc"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th>姓名拼音</th>
					<td>
						<html:text name="rs" property="xmpy" maxlength="64"/>
					</td>
					<th>身高</th>
					<td>
						<html:text name="rs" property="sg" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="3"/>(cm)						
					</td>
				</tr>
				<tr>
					<th>曾用名</th>
					<td>
						<html:text name="rs" property="cym" maxlength="64"/>
					</td>
					<th>体重</th>
					<td colspan="2">
						<html:text name="rs" property="tz" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="3"/>
						(kg)
					</td>
				</tr>
				<tr>
					<th>出生日期</th>
					<td>
						<html:text name="rs" property="csrq"
							onclick="return showCalendar('csrq','y-mm-dd');" styleId="csrq" readonly="true"/>
					</td>
					<th>特长</th>
					<td colspan="2">
						<html:text name="rs" property="tc" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<th>培养方式</th>
					<td>
						<html:text name="rs" property="pyfs" maxlength="32"/>
					</td>
					<th>培养层次</th>
					<td colspan="2">
						<html:text name="rs" property="pycc" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<th>入学方式</th>
					<td>
						<html:text name="rs" property="rxfs" maxlength="32"/>
					</td>
					<th>考生类别</th>
					<td colspan="2">
						<html:text name="rs" property="kslb" maxlength="32"/>
					</td>
				</tr>
				<tr>
					<th>身份证号</th>
					<td>
						<html:text name="rs" property="sfzh" styleId="sfzh"/>
					</td>
					<th>电子邮箱</th>
					<td colspan="2">
						<html:text name="rs" property="dzyx" styleId="dzyx"/>
					</td>
				</tr>
				<tr>
					<th>来源地区</th>
					<td>
						<html:text name="rs" property="syd" maxlength="25"/>
					</td>
					<th>联系电话</th>
					<td colspan="2">
						<html:text name="rs" property="lxdh" onkeyup="value=value.replace(/[^\d]/g,'') "
						maxlength="13"/>
					</td>
				</tr>
				<tr>
					<th>籍贯</th>
					<td>
						<html:text name="rs" property="jg" maxlength="10"/>
					</td>
					<th>手机号码</th>
					<td colspan="2">
						<html:text name="rs" property="sjhm" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="11"/>
					</td>
				</tr>
				<tr>
					<th>学制</th>
					<td >
						<html:text name="rs" property="xz" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="1"/>年						
					</td>
					<th>血型 </th>
					<td colspan="2">
						<html:select property="xx" name="rs">
						<html:option value=""></html:option>
						<html:option value="A">A型</html:option>
						<html:option value="B">B型</html:option>
						<html:option value="AB">AB型</html:option>
						<html:option value="O">O型</html:option>	
						</html:select>
					</td>
				</tr>
				<tr>
					<th>毕业时间</th>
					<td>
						<html:text property="byny" name="rs" maxlength="10" onkeyup="value=value.replace(/[^\d]/g,'') "/>
					</td>
					<th>是否毕业生</th>
					<td colspan="2">
						<html:select property="sfbys" name="rs" style="width:120px">
							<html:option value=""></html:option>
							<html:option value="是">是</html:option>
							<html:option value="否">否</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
				  <th>家庭住址（省）</th>
				  <td>
				  	<html:text name="rs" property="jtdzs" maxlength="32"/>
				  </td>
				  <th>家庭住址（市县）</th>
				  <td colspan="2">
				  	<html:text name="rs" property="jtdzx" maxlength="32"/>
				  </td>
			  </tr>
				<tr>
					<th>宿舍号</th>
					<td>
						<html:text property="ssbh" name="rs" styleId="ssbh" readonly="true"/>
					</td>
					<th>宿舍床号</th>
					<td colspan="2">
						<html:text name="rs" property="ssch" readonly="true"/>
					</td>
				</tr>
				<tr>				  
				  <th>苑区 </th>
				  <td>
				  	<html:text name="rs" property="ssyq" maxlength="32"/>
				  </td>
				  <th>楼栋 </th>
				  <td colspan="2">
				  	<html:text name="rs" property="ssld" maxlength="32"/>
				  </td>
			  </tr>
			  <tr>
				  <th>宿舍电话</th>
				  <td colspan="4">
				  	<html:text name="rs" property="ssdh" readonly="true" style="width:100%"/>
				  </td>
			  </tr>
				<tr>
					<th>住宿日期</th>
					<td>
						<html:text name="rs" property="zsrq" styleId="zsrq" readonly="true"/>
					</td>
					<th>住宿截止日期</th>
					<td colspan="2">
						<html:text name="rs" property="zsjzrq" styleId="zsjzrq" readonly="true"/>
					</td>
				</tr>
				<tr>
					<th>住宿信息备注</th>
					<td colspan="4">
						<html:textarea name="rs" property="ssbz" style="width:100%;height:100%" readonly="true"></html:textarea>
					</td>
				</tr>
			</tbody>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
		           	<logic:notPresent name="details">
						<logic:equal value="yes" name="writeAble">
							<button class="button2" style="height:20px;width:80px" id="buttonSave"
								onclick="send();">
								保 存
							</button>
						</logic:equal>
						<logic:equal value="no" name="writeAble">
							<button class="button2" style="height:20px;width:80px" disabled="disabled"
								onclick="">
								保 存
							</button>
						</logic:equal>
					</logic:notPresent>
					<button class="button2" style="height:20px;width:80px"
						onclick="Close();return false;">
						关 闭
					</button>
		          </div>
		        </td>
		      </tr>
		    </tfoot>
		</table>
		</div>

		<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
				<script>
					alert("操作成功！");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();						
				</script>
			</logic:equal>
			<logic:equal name="result" value="false">
				<script>
					alert("操作失败!");
				</script>
			</logic:equal>
		</logic:notEmpty>
		</html:form>
	</body>
</html>
