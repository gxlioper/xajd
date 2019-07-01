<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="js/wjcfFuction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDtiaInfo.js'></script>
		<script type="text/javascript" src="pjpy/pjpyjs/pjpy.js"></script>
	</head>
<body>
	<html:form action="/wjcfzjlgwh" method="post">
	<input type="hidden" name="userType" id="userType" value="${userType }"/>
		<input type="hidden" name="failinfo" id="failinfo" value="${failinfo}"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 处分审核 - 单个处分审核</a>
			</p>
		</div>
		<div class="tab">
		<table class="formlist" width="99%">
			<thead>
				<tr style="height:23px">
					<td colspan="4">
						<span>单个处分审核</span>
					</td>
				</tr>
			</thead>
			<tr style="height:23px">
				<th width="15%">
					<font color="red">*</font>学号
				</td>
				<td width="35%">
					<html:text property="xh" name="rs" styleId="xh" readonly="true"></html:text>
				</td>
				<th align="right" width="15%">
					学年
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
					年度
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
					处理决定书或附件
				</th>
				<td align="left">
					<logic:notEmpty name="rs" property="fjsclj">
						<a title="处分文件下载" href="downloadfilewj.do?len=&wjsclj=${rs.fjsclj }" target="_blank">下载</a>
					</logic:notEmpty>
					<logic:empty name="rs" property="fjsclj">无</logic:empty>
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
					<font color="red">*</font>处分文号
				</th>
				<td align="left">
					<html:text property="cfwh" styleId="cfwh"></html:text>
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
					<font color="red">*</font>处分时间
				</th>
				<td align="left">
					<html:text property="cfsj" styleId="cfsj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('cfsj','y-mm-dd');" readonly="true"></html:text>
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					政治面貌
				</th>
				<td align="left" colspan="">
					${rs.zzmmmc }
				</td>
				<th align="right">
					违纪时间
				</th>
				<td align="left">
					${rs.wjsj }
				</td>
			</tr>
			
			<!-- 历史处分信息 -->
<%--			<tr>--%>
<%--				<td align="right" colspan="4">--%>
<%--					<table width="100%" border="1" class="formlist">--%>
<%--						<thead>--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									<span>历史违纪处分信息</span>--%>
<%--								</th>--%>
<%--							</tr>--%>
<%--						</thead>--%>
<%--					</table>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<tr>
				<td colspan="4">
					<div id="child4" style="display:none">
						<table width="100%" border="1" align="center" class="dateline">
						<thead>
							<tr>
								<td align="center" width="80px">
									学年
								</td>
								<td align="center" width="110px">
									处分类别
								</td>
								<td align="center" width="110px">
									处分原因
								</td>
								<td align="center" width="80px">
									处分时间
								</td>
								<td align="center" width="110px">
									处分文号
								</td>
								<td align="center" width="80px">
									违纪时间
								</td>
								<td align="center">
									违纪事实
								</td>
							</tr>
							</thead>
							<!-- 这里是通过DWR进行调用的 -->
							<tbody id="cfxx" align="center"></tbody>
						</table>
					</div>
				</td>
			</tr>
			
			<logic:equal value="yes" name="lxck" >
				<tr style="height:23px">
				<th>
					留校查看<br/>解除时间
				</th>
				<td align="left" colspan="">
					<html:text property="lxcksj" styleId="lxcksj" onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('lxcksj','y-mm-dd');" readonly="true"></html:text>
				</td>
				<td align="right">
					
				</td>
				<td align="left">
				
				</td>
			</tr>
			</logic:equal>
			<tr style="height:23px">
				<th>
					违纪事实
				</th>
				<td align="left" colspan="3">
					<html:textarea property="bz" name="rs" rows="5" styleId="bz" style="width:98%" disabled="true"></html:textarea>
				</td>
			</tr>
<%--			<tr style="height:23px">--%>
<%--				<td align="right">--%>
<%--					历史违纪记录--%>
<%--				</td>--%>
<%--				<td align="left" colspan="3">--%>
<%--					<html:textarea property="lswjjl" name="rs" rows="5" styleId="lswjjl" style="width:98%" disabled="true"></html:textarea>--%>
<%--				</td>--%>
<%--			</tr>--%>
			<tr style="height:23px">
				<th>
					系院意见
				</th>
				<td align="left" colspan="3">
					<html:textarea property="xyclyj" name="rs" rows="3" styleId="xyclyj" style="width:98%" disabled="true"></html:textarea>
				</td>
			</tr>
			<tr style="height:23px">
					<logic:equal value="xy" name="userType">
						<th>
							<bean:message key="lable.xsgzyxpzxy" />处理意见
						</th>
						<td align="left" colspan="3">
							<html:textarea property="xyyj" rows="5" styleId="xyyj" style="width:98%" ></html:textarea>
						</td>
					</logic:equal>
					<logic:notEqual value="xy" name="userType">
						<th>
							学校处理意见
						</th>
						<td align="left" colspan="3">
							<html:textarea property="xxclyj" rows="5" styleId="xxclyj" style="width:98%" ></html:textarea>
						</td>
					</logic:notEqual>
			</tr>
			<tfoot>
		      <tr>
		        <td colspan="4"><div class="bz">"<span class="red">*</span>"为必填项</div>
		          <div class="btn">
					 <logic:notEqual value="no" name="writable">
						<button type="button" name="提交" id="btn_save" onclick="saveinfo('wjcf_zjlg_cfxxdgsh.do?operType=save','cfwh-cfsj');">保存</button>
					  </logic:notEqual>
					  <button type="button" name="关闭" onclick="window.close();return false;">关闭</button>
		          </div></td>
		      </tr>
		    </tfoot>
		</table>
		</div>
				<!-- 保存后提示页面 -->	
		<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>
