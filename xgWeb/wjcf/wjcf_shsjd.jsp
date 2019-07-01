<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript">
		function saveCfxx() {
			var xxdm = document.getElementById('xxdm').value;
			
			var sh = document.getElementById('sh').value;
			var sj = document.getElementById('dateF').value;
			var wh = document.getElementById('sswh').value;
			
			if (sh == null || sh == '' || wh == null || wh == '' || sj == null || sj == '') {
				alert("带\*号字段必须填写！");
				return false;
			}
			
			if (xxdm != '12861') {
				if (document.getElementById('sh').value !='更改处分' && document.getElementById('ycflb').value != '') {
					alert("委员会决定为更改处分时才能修改处分更改项!");
					return false;
				} else {
					var cfxx = document.getElementById('ycflb').value;
					if (document.getElementById('sh').value =='更改处分' && (cfxx==null || cfxx=='')) {
						alert("请选择要更改的处分类别!");
						return false;
					}
				}
				refreshForm('/xgxt/wjcf_shscheck.do?doType=save&cflbmc=' + document.getElementById('ycflb').options[document.getElementById('ycflb').selectedIndex].text);
			} else {
				refreshForm('/xgxt/wjcf_shscheck.do?doType=save');
			}
		}
		function changeView() {
			var gzcf = document.getElementById('sh').value;

			if (gzcf=='更改处分') {
				document.getElementById('czid').style.display="";
				document.getElementById('xxid').style.display="";
			} else {
				document.getElementById('czid').style.display="none";
				document.getElementById('xxid').style.display="none";
				document.getElementById('ycflb').selectedIndex = 0;
			}
		}
		//验证输入字数据不能超过限制字数
		function checkLength(obj, len) {
			if (obj.value != null && obj.value.length >= len) {
				obj.value = obj.value.substring(0,len-1);
				alert("输入内容长度不能超过" + len + "字!");
				obj.focus();
				return false;
			}
		}
	</script>
	</head>
	<body onload="changeView()">

		<div class="tab_cur">
			<p class="location">
				<logic:equal value="11049" name="xxdm">
					<em>您的当前位置:</em>
					<a>违纪处理 - 申诉处理 - 学校处理决定</a>
				</logic:equal>
				<logic:notEqual value="11049" name="xxdm">
					<logic:equal value="10338" name="xxdm">
						<em>您的当前位置:</em>
						<a>违纪处分 - 委员会处理 - 申诉申请处理</a>
					</logic:equal>
					<logic:notEqual value="10338" name="xxdm">
						<em>您的当前位置:</em>
						<a>违纪处分 - 学生申诉管理 - 申诉审核</a>
					</logic:notEqual>
				</logic:notEqual>
			</p>
		</div>

		<html:form action="/wjcf_shscheck" method="post">
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="xh" name="xh"
				value="<bean:write name="xh" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }" />
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
		
					<logic:notEmpty name="rswj">
						<div class="tab">
						<table  class="formlist" align="" width="100%">
						<thead>
							<tr>
								<th colspan="5">
									<span>材料证明结果</span>
								</th>
							</tr>
						</thead>
							<tbody>
								<logic:iterate id="list" name="rswj">
									<tr onmouseover="rowOnClick(this)">
										<td align="center">
											<a
												href="downloadfilewj.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj" />"
												target="_blank">下载 </a>
										</td>
										<td align="center">
											<bean:write name="list" property="cfwh" />
										</td>
										<td align="center">
											<bean:write name="list" property="cflbmc" />
										</td>
										<td align="center">
											<bean:write name="list" property="cfyymc" />
										</td>
										<td align="center">
											<bean:write name="list" property="sssj" />
										</td>
									</tr>
								</logic:iterate>
							</tbody>
						</table>
						</div>
					</logic:notEmpty>
					<logic:empty name="rswj">
					<div class="tab">
					<table summary="" class="formlist" align="" width="100%">
					<thead>
							<tr>
								<th>
									<span>材料证明结果</span>
								</th>
							</tr>
						</thead>
							<tbody>
							<tr><td align="center">
								<font color="red"> 暂无材料或证明附件</font>
								</td>
								</tr>
								</tbody>
								</table>
								</div>
							</logic:empty> 
				<div class="tab">
					<table width="100%" border="0" class="formlist" id="rsTable">
						<thead>
							<tr>
								<th colspan="5">
									<span>委员会决定</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="5">
									<div class="btn">
										<button type="button" onclick="saveCfxx()" id="buttonSave">
											保 存
										</button>
										<button type="button" onclick="Close();return false;" id="buttonClose">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="19%">
									学号
								</th>
								<td align="left" width="30%">
									<bean:write name="rs" property="xh" />
								</td>
								<th width="18%">
									处分文件号
								</th>
								<td align="left">
									<bean:write name="rs" property="cfwh" />
								</td>
								<td align="left" width="15%" rowspan="5">
									<img
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
										border="0" align="absmiddle" style="width:100px;height:120px" />
								</td>
							</tr>
							<tr>
								<th>
									姓名
								</th>
								<td align="left">
									<bean:write name="rs" property="xm" />
								</td>
								<th>
									年度
								</th>
								<td align="left">
									<bean:write name="rs" property="nd" />
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td align="left">
									<bean:write name="rs" property="xb" />
								</td>
								<th>
									学年
								</th>
								<td align="left">
									<bean:write name="rs" property="xn" />
								</td>
							</tr>

							<tr>
								<th>
									年级
								</th>
								<td align="left">
									<bean:write name="rs" property="nj" />
								</td>
								<th>
									学期
								</th>
								<td align="left">
									<bean:write name="rs" property="xq" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />

								</th>
								<td align="left">
									<bean:write name="rs" property="xymc" />
								</td>
								<th>
									处分时间
								</th>
								<td align="left">
									<bean:write name="rs" property="cfsj" />
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td align="left">
									<bean:write name="rs" property="zymc" />
								</td>
								<th>
									处分类别
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="cflbmc" />
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td align="left">
									<bean:write name="rs" property="bjmc" />
								</td>
								<th>
									处分事由
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="cfyymc" />
								</td>
							</tr>
							<tr>
								<th>
									联系地址
								</th>
								<td align="left">
									<bean:write name="rs" property="dz" />
								</td>
								<th>
									<logic:present name="isZJJDZYJSXY">
					    申诉申请时间
					    </logic:present>
									<logic:notPresent name="isZJJDZYJSXY">
					       申诉时间
					     </logic:notPresent>
								</th>
								<td align="left" colspan="2">
									<bean:write name="rs" property="sssj" />
								</td>
							</tr>

							<tr>
								<th>
									邮政编码
								</th>
								<td align="left">
									<bean:write name="rs" property="yb" />
								</td>
								<th>
									<logic:equal value="11049" name="xxdm">学校处理决定</logic:equal>
									<logic:notEqual value="11049" name="xxdm">
								<font color="red">*</font>审核
								</logic:notEqual>
								</th>
								<td align="left" colspan="2">
									<html:select name="rs" property="jd" style="width:100px"
										styleId="sh" onchange="changeView()">
										<html:options collection="jdList" property="jd"
											labelProperty="jd" />
									</html:select>

									<span style="display: none;" id="xxid">
										&nbsp;&nbsp;处分更改为 <html:select property="ycflb"
											styleId="ycflb" name="rs">
											<html:option value=""></html:option>
											<html:options collection="cflbList" property="cflbdm"
												labelProperty="cflbmc" />
										</html:select> </span>

									<span id="czid" style="display: none"> </span>
								</td>
							</tr>
							<tr>
								<logic:present name="isZJJDZYJSXY">
									<th>
										申请处分改为
									</th>
									<td align="left">
										<bean:write name="rs" property="cfxg" />
									</td>
								</logic:present>
								<logic:notPresent name="isZJJDZYJSXY">

									<th>
										联系电话
									</th>
									<td align="left">
										<bean:write name="rs" property="lxdh" />
									</td>
								</logic:notPresent>

								<th>
									<font color="red">*</font>申诉处理文号
								</th>
								<td align="left" colspan="2">
									<html:text name="rs" property="jdwh" maxlength="15" styleId="sswh"></html:text>
								</td>
							</tr>

							<tr>

								<th>
									<font color="red">*</font>申诉处理时间
								</th>
								<td align="left" colspan="">
									<html:text name="rs" style="cursor:hand; width:85px;"
										styleId="dateF" property="jdsj" onblur="dateFormatChg(this)"
										style="cursor:hand;"
										onclick="return showCalendar('dateF','y-mm-dd');" />
								</td>
							<th colspan="1">
									
								</th>
								<td colspan="2"></td>
							</tr>
							<tr>
								<th>
									<logic:present name="isZJJDZYJSXY">
					    申诉/解除
												申请理由 
									</logic:present>
									<logic:notPresent name="isZJJDZYJSXY">
					   改变<bean:message key="lable.xsgzyxpzxy" />处分要求 
										</logic:notPresent>
								</th>
								<td align="left" colspan="4"  style="word-break:break-all;">
									<bean:write name="rs" property="yq" />
								</td>
							</tr>

							<tr>
								<th>
									决定结果
									<br />
									内容或理由
									<br />
									<font color="red">(限制在500字以内)</font>
								</th>
								<td align="left" colspan="4" style="word-break:break-all;">
									<html:textarea name="rs" property="jdly" rows="7"
										style="width:550px" styleId="jdly"
										onkeyup="checkLength(this,500)">
									</html:textarea>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:equal value="yes" name="done">
				<script>
				alert("操作成功!");
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				   window.dialogArguments.document.getElementById('search_go').click(); 
				}
			</script>
			</logic:equal>
			<logic:equal value="no" name="done">
				<script>
				alert("操作失败!");
				Close();
				if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
				   window.dialogArguments.document.getElementById('search_go').click(); 
				   }
			</script>
			</logic:equal>
		</html:form>
	</body>
</html>
