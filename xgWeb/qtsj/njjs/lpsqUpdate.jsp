<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%><!-- 头文件 -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/String.js"></script>
<script type="text/javascript">
	function valueOf(array,str) {
		for ( var i = 0 ; i< array.length; i ++) {
			if (array[i].trim()==str.trim()) {
				return true;
			}
		}
		return false;
	}
	

	function setLpcl(text) {
		var lpclArr = $('lpcls').value.replace('[','').replace(']','').split(',');
		
		lpcl.innerHTML = "";
		GetListData.getLpcl(text,function(data){
			
			if (data.length>0) {
				dd_html = "";
				for (var i = 0 ; i < data.length ; i ++ ) {
					
					dd_html += data[i].mc;
					dd_html += ":<input type='checkbox' name='save_lpcl'";
					
					if (valueOf(lpclArr,data[i].dm)) {
						dd_html += "checked=true";
					}
					
					if ('sh' == $('doType').value) {
						dd_html += " disabled=true ";
					}
					dd_html += "value='";
					dd_html += data[i].dm;
					dd_html +="'/>&nbsp;&nbsp;";
				}
				
				lpcl.innerHTML=dd_html;
			}
			
		});
	}
</script>

<body onload="setLpcl('${rs.lpxm }')">
	<html:form action="/bxxx" method="post">
		<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：${title }
				</div>
		</div>
		<input type="hidden" id="url" name="url" value="/bxxx.do?method=lpsqUpdate" />
		<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-nj-zymc-xymc-bjmc" />
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="save_sqsj" value="${nowTime }"/>
		<input type="hidden" id="lpcls" value="${rs.lpcl }" />
		<input type="hidden" id="doType" name="doType" value="${doType }"/>
		
		<table class="tbstyle" width="100%">
			<tr>
				<td align="right" width="16%">
					<font color="red">*</font>学号：
				</td>
				<td align="left" width="34%">
					<html:text  property="save_xh" styleId="xh" readonly="true" value="${rs.xh}"/>
					<logic:notEqual value="stu" name="userType">
						<button onclick="showTopWin('/xgxt/stu_info.do',800,600);" class="btn_01" id="buttonFindStu">
							选择
						</button>
					</logic:notEqual>
				</td>
				<td width="16%">
					<div align="right">
						姓名：
					</div>
				</td>
				<td width="34%">
					${rs.xm }
				</td>
			</tr>
			<tr>
				<td align="right">性别：</td>
				<td>${rs.xb }</td>
				<td align="right"><bean:message key="lable.xsgzyxpzxy" />：</td>
				<td>${rs.xymc }</td>
			</tr>
			<tr>
				<td align="right">专业：</td>
				<td>${rs.zymc }</td>
				<td align="right">班级：</td>
				<td>${rs.bjmc }</td>
			</tr>
			<tr>
				<td align="right"><font color="red">*</font>理赔项目：</td>
				<td>
					<html:select property="save_lpxm" value="${rs.lpxm }" onchange="setLpcl(this.value);">
						<html:options collection="lpxmList" property="dm" labelProperty="mc"/>
					</html:select>
				</td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td align="right">理赔材料：</td>
				<td colspan="3">
					<div id="lpcl">
						
					</div>
				</td>
			</tr>
			<tr>
				<td align="right">具体说明：</td>
				<td colspan="3">
					<html:textarea property="save_jtsm" onblur="checkLen(this,500)" rows="8" style="width:85%" value="${rs.jtsm }"></html:textarea>
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:100%">
			<button class="button2" id="buttonSave" onclick="saveUpdate('/xgxt/bxxx.do?method=lpsqUpdate&doType=save','save_xh-save_lpxm');">
				保&nbsp;&nbsp;存
			</button>
		</div>
	</html:form>
	<logic:present name="result">
			<script>
				alert(''+$('message').value);
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
