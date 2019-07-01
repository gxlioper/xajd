<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/zjcm/cssz/cssz.js"></script>
		<script type="text/javascript">
		function checkTzrsIsThanCprs() {
			var cprs = document.getElementById('cprs').value;
			var jxjrs = document.getElementById('jxjrs').value;
			cprs = parseFloat(cprs);
			jxjrs = parseFloat(jxjrs);
			if (jxjrs >= cprs) {
				alert("调整人数不能大于或等于该部门总人数!请重新调整.");
				return false;
			}
			return true;
		}
		function savedata() {
			if (checkTzrsIsThanCprs()) {
				saveinfo('pjpy_zjcm_rychrstz.do?act=save','jxjrs');
			} 
		}
		function dispButton() {
			var jxjbl = document.getElementById('jxjbl').value;
			if (jxjbl ==null||jxjbl=='') {
				if ($('btn_save')) {
					document.getElementById('btn_save').disabled = true;
				}
				document.getElementById('msg').style.display="block";
			}
		}
		</script>
	</head>
	<body onload="dispButton()">
		<html:form action="/pjpyZjcmCssz" method="post">
			<input type="hidden" name="pkValue" id="pkValue"
				value="${rs.pkValue }" />
			<input type="hidden" id="zyV" name="zyV" value="" />
			<input type="hidden" id="bjV" name="bjV" value="" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>荣誉称号比例人数单个调整</span>
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
										<button type="button" name="提交" onclick="savedata()">
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
								<html:text name="rs" property="xqmc" styleId="xq"
									style="color:#666666" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								年度
							</th>
							<td align="left">
								<html:text name="rs" property="nd" styleId="nd"
									style="color:#666666" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								设置比例范围
							</th>
							<td>
								<html:select name="rs" property="cpfw" styleId="cpfw"
									style="width:120px" disabled="true">
									<html:options collection="cpfwList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="nj" styleId="nj" name="rs"
									styleClass="select" style="width:90px" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								部门名称
							</th>
							<td>
								<html:text property="bmmc" name="rs" disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								参评人数
							</th>
							<td>
								<html:text property="cprs" name="rs" styleId="cprs"
									disabled="true"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								荣誉称号
							</th>
							<td>
								<html:select property="jxjdm" styleId="jxjdm" name="rs"
									disabled="true">
									<html:options collection="dmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								建议人数比：
							</th>
							<td>
								<input type="text" style="width:60px" name="jxjbl" id="jxjbl"
									value="${rs.jxjbl }" disabled="disabled" />
								<font color="red">(%)</font>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>调整人数
							</th>
							<td>
								<html:text property="jxjrs" styleId="jxjrs" maxlength="4"
									value="${rs.jxjrs }"
									onkeyup="chkIsNum(this);checkTzrsIsThanCprs();"></html:text>
								<font color="red">（请输入正整数）</font>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
		</html:form>
	</body>
</html>