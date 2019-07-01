<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhZj.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body >
		<html:form action="/xszz_xmwh" method="post" styleId="form1">
			<div class="prompt">
					<h3>
						<span>提示：</span>
					</h3>
					<p>
						金额是否可调整为‘是’时：学生可填写资助金额，教师审核可修改，如：困难生临时补助、学费减免项目<br />
							为‘否’时：学生不能填写金额，教师审核不能修改，如：国家奖学金项目
					</p>
					<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div style='tab;width:100%;height:229px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>项目信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th>
								申请周期
							</th>
							<td>
								${dqzq }
							</td>
						</tr>
						<tr>
							<th width="30%">
								<font color="red">*</font>项目名称
							</th>
							<td width="70%" >
								<html:text property="xmmc" styleId="xmmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="30%">
								项目英文名称
							</th>
							<td width="70%" >
								<html:text property="ywmc" styleId="ywmc" maxlength="20" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>项目类别
							</th>
							<td>
								<html:select property="lbdm" styleId="lbdm" style="width:155px">
								<html:options collection="xmlbList" property="lbdm"
									labelProperty="lbmc" />
								</html:select>
							</td>
						</tr>
						<tr >
							<th>
								金额是否可调整
							</th>
							<td >
								<input type="radio" name="jesfxssq" value="1" />是
								<input type="radio" name="jesfxssq" value="0" checked="checked"/>否
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font><font id="jemc">固定金额</font>
							</th>
							<td>
								<html:text property="je" styleId="je" maxlength="10" styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th width="20%">项目说明<br/>
								<font color="red">(限制录入500字)</font></th>
							<td width="80%">
								<html:textarea property="xmsm" styleId="xmsm"  style="width:98%"  rows="5"/>
							</td>
						</tr>
<%--						<tr>--%>
<%--							<th>--%>
<%--								打印报表--%>
<%--							</th>--%>
<%--							<td>--%>
<%--								<html:select property="dybb" styleId="dybb" style="width:200px">--%>
<%--									<option></option>--%>
<%--									<html:options collection="bbList" labelProperty="bbmc" property="bbdm"/>--%>
<%--								</html:select>	--%>
<%--								<!-- 						--%>
<%--								<a href="javascript::">报表预览</a>--%>
<%--								 -->	--%>
<%--							</td>--%>
<%--						</tr>--%>
					</tbody>
				</table>
				</div>
				<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										保 存
									</button>
									<button type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>

