<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js"></script>
	</head>
	<body onload="dispconf('cpfw')">
		<html:form action="/pjpyZjcmCssz" method="post">
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>奖学金比例批量设置</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button type="button" name="提交"
											onclick="saveinfo('pjpy_zjcm_jxjblPlsz.do?act=save','cpfw-jxjdm')">
											保存
										</button>
									</logic:notEqual>
									<button type="button" name="关闭" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="35%">
								学年
							</th>
							<td>
								<html:text name="rs" property="xn" styleId="xn"
									style="color:#666666" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								学期
							</th>
							<td>
								<html:text name="rs" property="xq" styleId="xq"
									style="color:#666666" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								年度
							</th>
							<td>
								<html:text name="rs" property="nd" styleId="nd"
									style="color:#666666" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>设置比例范围
							</th>
							<td>
								<html:select property="cpfw" styleId="cpfw" style="width:120px"
									onchange="dispconf('cpfw')">
									<html:option value="bj">班级</html:option>
									<!--非浙江传媒学院-->
									<logic:notEqual value="11647" name="xxdm">
										<html:option value="zy">专业</html:option>
										<html:option value="xy"><bean:message key="lable.xb" /></html:option>
									</logic:notEqual>
									<!--end非浙江传媒学院-->
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="nj" styleId="nj"
									onchange="initZyList();initBjList()" styleClass="select"
									style="width:90px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm"
									onchange="initZyList();initBjList()" styleClass="select"
									style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td align="left">
								<html:select property="zydm" onchange="initBjList()"
									style="width:180px" styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" style="width:180px"
									styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								奖学金类别
							</th>
							<td align="left">
								<html:select property="jxjlb" styleId="jxjlb"
									onchange="changeJxj('pjpy_zjcm_jxjblPlsz.do')">
									<html:options collection="lbList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>奖学金
							</th>
							<td>
								<html:select property="jxjdm" styleId="jxjdm">
									<html:options collection="dmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								建议人数比例
							</th>
							<td>
								<input type="text" style="width:60px" name="jxjbl" id="jxjbl"
									onkeypress="return numInputValue(this,5,event)" maxlength="4" />
								<font color="red">% （请输入 0 ～ 100 之间的数字）</font>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
	</body>