<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getBjlhGyglDAO.js'></script>
		<script language="javascript">	
	function qdbffr(){
		var checkBoxArr = document.getElementsByName("checkVal");
		var flag = false;
		var xhzgh = "";
		for(var i=0;i<checkBoxArr.length;i++){
			if(checkBoxArr[i].checked==true){
				xhzgh += checkBoxArr[i].value + "!!@@!!";
				flag = true;
			}
		}
		
		window.dialogArguments.document.forms[0].stuInfo.value=xhzgh;
		if(flag){
			//url+="&doType="+doType;
			if (confirm("确定要勾选人员为发放对象?")) {
				if(checkBoxArr.length>1000){
					alert("数据超过1000，保存速度有点慢，请耐心等待");
				}
				var url = "/xgxt/zjxyRcsw.do?method=bffrManage";
				url+="&doType=ff";
				window.dialogArguments.document.forms[0].action = url;
				window.dialogArguments.document.forms[0].submit();
				window.close();
				return true;		
			}
		}else{
			alert("请勾选要发放的人员信息!!");
			return false;
		}
	}
	</script>
	</head>
	<body onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>信息维护 - 被发放者信息</a>
			</p>
		</div>


		<html:form action="/zjxyRcsw" method="post">
			<%@ include file="/rcsw/hiddenValue.jsp"%>
			<input type="hidden" name="ffrq" id="ffrq" value="${ffrq }" />
			<input type="hidden" name="ffsj" id="ffsj" value="${ffsj }" />
			<input type="hidden" name="xn" id="xn" value="${xn }" />
			<input type="hidden" name="xq" id="xq" value="${xq }" />
			<input type="hidden" name="nd" id="nd" value="${nd }" />
			<input type="hidden" name="xmlx" id="xmlx" value="${xmlx }" />
			<logic:notEmpty name="msg">
				<div align="center">
					<font color="red" size="6"><bean:write name="msg" /> </font>
				</div>
			</logic:notEmpty>
			<logic:empty name="msg">

				<div class="toolbox">
					<div class="searchtab">
						<table width="100%" border="0">
							<tfoot>
								<tr>
									<td colspan="8">
										<div class="btn">
											<input type="hidden" name="go" value="a" />
											
											<logic:equal name="xxdm" value="13275">
													<button type="button" class="btn_cx" id="search_go"
														onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=ffrManage');">
														查 询
													</button>
												</logic:equal>
												<logic:notEqual name="xxdm" value="13275">
													<button type="button" class="btn_cx" id="search_go"
														onclick="allNotEmpThenGo('/xgxt/zjxyRcsw.do?method=bffrManage');">
														查 询
													</button>
												</logic:notEqual>
											<button type="button" class="btn_cz" id="btn_cz" onclick="qdbffr();">
												确定
											</button>
										</div>
									</td>
								</tr>
							</tfoot>
							<logic:equal name="mklx" value="xs">
								<tbody>
									<tr>
										<th>
											年级
										</th>
										<td>
											<html:select property="nj" 	onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="njList" property="nj"
													labelProperty="nj" />
											</html:select>
										</td>
										<th>
											学号
										</th>
										<td>
											<html:text property="xh" style="width:85px" maxlength="20" />
										</td>
										<th>
											姓名
										</th>
										<td>
											<html:text property="xm" style="width:85px" maxlength="20" />
										</td>
									</tr>
									<tr>
										<th>
											<bean:message key="lable.xsgzyxpzxy" />
										</th>
										<td>
											<html:select property="xydm" style="width:200px" styleId="xy"
												onchange="initZyList();initBjList();">
												<html:option value=""></html:option>
												<html:options collection="xyList" property="xydm"
													labelProperty="xymc" />
											</html:select>
										</td>
										<th>
											专业
										</th>
										<td>
											<html:select property="zydm" style="width:200px" styleId="zy"
												onchange="initBjList();">
												<html:option value=""></html:option>
												<html:options collection="zyList" property="zydm"
													labelProperty="zymc" />
											</html:select>
										</td>
										<th>
											班级
										</th>
										<td>
											<logic:equal value="true" name="fdyQx">
												<html:select property="bjdm" style="width:200px" styleId="bj">
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
											</logic:equal>
											<logic:notEqual value="true" name="fdyQx">
												<html:select property="bjdm" style="width:200px" styleId="bj">
													<html:option value=""></html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
											</logic:notEqual>
										</td>
									</tr>
									<tr>
										<th>
											是否发放
										</th>
										<td>
											<html:select property="sfff" >
												<html:option value=""></html:option>
												<html:option value="已发放">已发放</html:option>
												<html:option value="未发放">未发放</html:option>
											</html:select>
										</td>
										<td colspan="4">
									</tr>
							</logic:equal>
							<logic:equal name="mklx" value="ls">
								<tbody>
									<tr>
										<th>
											所属部门
										</th>
										<td>
											<logic:equal name="userType" value="xy">
											<html:select property="bmdm" style="width:200px" styleId="bmdm" disabled="true"
												value="${userDep }">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm"
													labelProperty="bmmc" />
											</html:select>
											<html:hidden property="bmdm" value="${userDep}"/>
											</logic:equal>
											<logic:notEqual name="userType" value="xy">
											<html:select property="bmdm" style="width:200px" styleId="bmdm">
												<html:option value=""></html:option>
												<html:options collection="bmList" property="bmdm"
													labelProperty="bmmc" />
											</html:select>
											</logic:notEqual>
										</td>
										<th>
											职位
										</th>
										<td>
											<html:select property="zw" style="" styleId="zw">
												<html:options collection="zwList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>
										<th>
											职工号
										</th>
										<td>
											<html:text property="zgh" style="width:85px" maxlength="20" />
										</td>
										<th>
											姓名
										</th>
										<td>
											<html:text property="xm" style="width:85px" maxlength="20" />
										</td>
									</tr>
									<tr>
										<th>
											是否发放
										</th>
										<td>
											<html:select property="sfff" >
												<html:option value=""></html:option>
												<html:option value="已发放">已发放</html:option>
												<html:option value="未发放">未发放</html:option>
											</html:select>
										</td>
										<td colspan="4">
									</tr>
								</tbody>
							</logic:equal>
						</table>
					</div>
					<div class="formbox">
						<h3 class="datetitle_01">
							<span> 查询结果&nbsp;&nbsp; <logic:empty name="rsList">
									<font color="red">未找到任何记录！</font>
								</logic:empty> </span>
						</h3>

						<logic:notEmpty name="rsList">
							<table summary="" class="dateline" align="" width="100%">
								<thead>
									<tr align="center" style="cursor:hand">
										<td>
											<input type="checkbox" id="selall" name="selall"
												onclick="selAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="0">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
										<td id="sfff" onclick="tableSort(this)" nowrap>
												是否发放
										</td>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr onclick="rowOnClick(this);" style="cursor:hand">
											<td align="center">
												<input type="checkbox" id="checkVal" name="checkVal"
													value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="1">
												<td align="left">
													<bean:write name="v" />
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
							<!--分页显示-->
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswForm"></jsp:include>
							<!--分页显示-->
						</logic:notEmpty>
					</div>
				</div>
			</logic:empty>
		</html:form>
	</body>
</html>
