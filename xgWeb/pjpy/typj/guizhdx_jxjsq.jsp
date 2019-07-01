<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript">
	function selectCj() {
		var text = document.getElementById('cj').style;
		if (text.display == ''){
			text.display = 'none';
		} else {
			text.display = '';
		}
	}
</script>
<body>
<input type="hidden" name="save_nd" value="无">
<input type="hidden" name="save_xq" value="无"/>

		<table class="formlist" width="100%">
		<thead>
				<tr><th colspan="4"><span>其它信息</span></th></tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="24%">
					违纪情况
				</th>
				<td align="left"  colspan="3">
						<logic:empty name="cfxx">
							本学年没有违纪记录！
						</logic:empty>
					<logic:present name="cfxx">
						<logic:notEmpty name="cfxx">
						<table class="tbstyle" width="85%">
							<thead>
							<tr>
								<th>学年</th>
								<th>学期</th>
								<th>处分类别</th>
								<th>处分原因</th>
								<th>处分时间</th>
								<th>处分文号</th>
							</tr>
							</thead>
							<logic:iterate id="s" name="cfxx">
								<tr>
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</logic:notEmpty>
					</logic:present>
				</td>
			</tr>	
			<tr>
				<td bgcolor="#CCCCCC" colspan="4" align="center" onclick="selectCj();">
					<div style="color:blue;cursor:hand"><b>各课成绩</b></div>
				</td>
			</tr>
			<tr id="cj" style="display :none">
				<th align="right" width="24%">
					各课成绩
				</th>
				<td colspan="3" align="left" >
					<logic:empty name="cjxx">
						没有记录!
					</logic:empty>
					<logic:present name="cjxx">
						<logic:notEmpty name="cjxx">
						<table class="tbstyle" width="85%"  >
							<thead>
							<tr>
								<th>学年</th>
								<th>学期</th>
								<th>课程名称</th>
								<th>课程性质</th>
								<th>成绩</th>
								<th>补考成绩</th>
							</tr>
							</thead>
							<logic:iterate id="s" name="cjxx">
								<tr>
									<logic:iterate id="v" name="s" offset="2">
										<td>
											<bean:write name="v"/>
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
						</logic:notEmpty>
					</logic:present>
				</td>
			</tr>
			<tr>
				<th align="right" width="24%">
					申请理由
					<br/>
					<font color="red">(限制在500字以内)</font>
				</th>
				<td colspan="3" width="76%">
					<html:textarea property="save_sqly" rows="8" style="width:90%;word-break:break-all;" value="${rs.sqly }" onblur="checkLen(this,500)"></html:textarea>
				</td>
			</tr>
			<tr guizhdx_jxjsq.jsp>
				<th align="right"  width="24%">
					银行卡号
				</th>
				<td width="26%">
					<html:text property="save_yhkh" maxlength="21" value="${rs.yhkh}" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
				</td>
				<th align="right"  width="24%">
					是否欠学费
				</th>
				<td width="26%">
					<html:select property="save_sfqf" value="${rs.sfqf }">
						<html:option value=""></html:option>
						<html:options collection="isNot" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			</tbody>
		</table>

</body>