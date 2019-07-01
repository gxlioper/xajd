<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>
</head>
<body>
	<html:form action="/wjcfzjlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 解除留校察看 - 审核 - 单个审核</a>
			</p>
		</div>

		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>单个审核</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<!-- 控制对于学校审核的<bean:message key="lable.xsgzyxpzxy" />不能再操作 -->
									<logic:notEqual value="no" name="writable">
									<button type="button" class="" id="btn_save" 
										onclick="saveinfo('wjcf_zjlg_LxckxxDgsh.do?operType=save','');">
										保 存
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
									<button type="button" class="" id="btn_close" onclick="window.close();return false;" >
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
			<tr style="height:23px">
				<th align="right" width="15%">
					学号
				</th>
				<td align="left" width="35%">
					${rs.xh }
				</td>
				<th align="right" width="15%">
					<font color="red">*</font>学年
				</th>
				<td align="left" width="35%">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					姓名
				</th>
				<td align="left">
					${rs.xm }
				</td>
				<th align="right">
					<font color="red">*</font>年度
				</th>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					性别
				</th>
				<td align="left">
					${rs.xb }
				</td>
				<th align="right">
					处分类别
				</th>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					年级
				</th>
				<td align="left">
					${rs.nj }
				</td>
				<th align="right">
					处分原因
				</th>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc }
				</td>
				<th align="right">
					处分学年年度
				</th>
				<td align="left">
					${rs.cfxn }/${rs.cfnd }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					专业
				</th>
				<td align="left">
					${rs.zymc }
				</td>
				<th align="right">
					处分文号
				</th>
				<td align="left">
					${rs.cfwh }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					班级
				</th>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<th align="right">
					处分时间
				</th>
				<td align="left">
					${rs.cfsj }
				</td>
			</tr>
		<tr style="height:23px">
				<th align="right">
					拟解除留校察看时间
				</th>
				<td align="left" colspan="">
					${rs.lxcksj }
				</td>
				<th align="right">
					审核
				</th>
				<td align="left">
					<html:select property="sh" styleId="sh">
						<html:options collection="shList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
		<logic:notEqual value="xy" name="userType">
			<tr style="height:23px">
				<th align="right">
					解除留校察看时间
				</th>
				<td align="left" colspan="">
					<html:text property="jcsj" styleId="jcsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('jcsj','y-mm-dd');" readonly="true"></html:text>
				</td>
				<th align="right">
					解除留校察看文号
				</th>
				<td align="left">
					<html:text property="jcwh" styleId="jcwh"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					解除留校察看结果
				</th>
				<td align="left" colspan="">
					<html:select property="jcjg" styleId="jcjg">
						<html:option value=""></html:option>
						<html:options collection="jcList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
				<th align="right">
					
				</th>
				<td align="left">
					
				</td>
			</tr>
		</logic:notEqual>
		<tr style="height:23px">
				<th align="right">
					留校察看期间表现
				</th>
				<td align="left" colspan="3">
					<html:textarea property="xsbx" styleId="xsbx" name="rs" rows="7" style="width:95%" disabled="true"></html:textarea>
				</td>
			</tr>
		<logic:equal value="11647" name="xxdm">
			<tr style="height:23px">
				<th align="right">
					有无需要提前<br/>解除留校察看期<br/>（需要提前解<br/>除请注明理由）
				</th>
				<td align="left" colspan="3">
					<html:textarea property="tqjcly" styleId="tqjcly" rows="5" name="rs" style="width:95%" disabled="true"></html:textarea>
				</td>
		</tr>
		</logic:equal>
		<logic:equal value="xy" name="userType">
			<tr style="height:23px">
				<th align="right">
					班主任意见
				</th>
				<td align="left" colspan="3">
					<html:textarea property="fdyjdyj" styleId="fdyjdyj" rows="5" style="width:95%" ></html:textarea>
				</td>
		</tr>
		</logic:equal>
		<tr style="height:23px">
				<th align="right">
					<logic:equal value="xy" name="userType"><logic:notEqual value="true" name="isFdy"><bean:message key="lable.xsgzyxpzxy" /></logic:notEqual></logic:equal><logic:notEqual value="xy" name="userType">学校</logic:notEqual>审核意见
				</th>
				<td align="left" colspan="3">
					<html:textarea property="yj" styleId="yj" rows="5" style="width:95%" ></html:textarea>
				</td>
			</tr>
			</tbody>
		</table>
		</div>
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
