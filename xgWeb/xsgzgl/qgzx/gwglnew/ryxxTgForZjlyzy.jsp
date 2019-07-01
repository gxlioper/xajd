<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_gwglnew" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="gwxzmc" id="gwxzmc" value="${rs.gwxzmc }" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<input type="hidden" name="xhs" id="xhs" value="" />
			<input type="hidden" name="sgsjs" id="sgsjs" value="" />
			<input type="hidden" name="sqbhs" id="sqbhs" value="" />
			<input type="hidden" name="xhTal" id="xhTal" value="" />
			<input type="hidden"  id="xxdm" value="${xxdm}"/>			
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="height:450px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								用人部门
							</th>
							<td width="34%" >
								<html:hidden property="yrdwdm" styleId="yrbm"/>
								${rs.yrdwmc}
							</td>
							<th width="16%">
								联系人
							</th>
							<td width="34%">
								<input type="hidden" property="lxr" id="lxr" />
								${rs.lxr}
							</td>
						</tr>
						<tr>
							<th width="16%">
								申报时间
							</th>
							<td width="34%" >
								<input type="hidden" id="sqsj" name="sqsj" />
								${rs.sqsj }
							</td>
							<th width="16%">
								联系电话
							</th>
							<td width="34%">
								<input type="hidden" id="lxPhone" name="lxPhone" />
								${rs.lxphone}
							</td>
						</tr>
					<thead>
							<tr>
								<th colspan="5">
									<span>岗位申请信息
									</span>
								</th>
							</tr>
					</thead>
						<tr>
							<th width="16%">
								岗位名称
							</th>
							
							<td width="34%">
								<html:hidden name="rs" property="gwmc" styleId="gwmc" value="${rs.gwmc}"/>
								${rs.gwmc}
							</td>
							<th width="16%">
								需求人数
							</th>
							<td width="34%">
								<html:hidden name="rs" property="xqrs" styleId="xqrs" value="${rs.xqrs}"/>
								${rs.xqrs}
							</td>
						</tr>
						<tr>
							<th width="16%">
								岗位性质
							</th>
							
							<td width="34%">
								<html:hidden name="rs" property="gwxzdm" styleId="gwxzdm" value="${rs.gwxzmc}"/>
								${rs.gwxzmc}
							</td>
							<th width="16%">
								困难生数
							</th>
							<td width="34%">
								<html:hidden name="rs" property="knsrs" styleId="knsrs" value="${rs.knsrs}"/>
								${rs.knsrs}
							</td>
						</tr>
						<tr>
								<th>岗位审核人</th>
								<td width="34%">
									<html:hidden name="rs" property="gwshr" styleId="gwshr" value="${rs.gwshr}"/>
									${rs.gwshr}
								</td>
								<th>岗位审核人姓名</th>
								<td width="34%">
									<html:hidden name="rs" property="gwshrxm" styleId="gwshrxm" value="${rs.gwshrxm}"/>
									${rs.gwshrxm}
								</td>
						</tr>
						<tr>
								<th>岗位要求</th>
								<td colspan="3">
									<html:hidden name="rs" property="gwryyq" styleId="gwryyq" />
									${rs.gwryyq}
								</td>
						</tr>
						<tr>
								<th align="right" >
									工作内容
								</th>
								<td colspan="3">
									<html:hidden name="rs" property="gwms" styleId="gwms" value="${rs.gwms}"/>
									${rs.gwms}
								</td>
						</tr>
						<logic:notEmpty name="rs" property="xn" >
							<tr>
								<th width="16%">
									在岗年度
								</th>
								<td colspan="3">
									<input type="hidden" id="xn" name="xn" />
									${rs.xn}${rs.xqmc}
								</td>
							</tr>
							<tr>
								<th width="16%">有效时设置</th>
								<td width="34%">
									${rs.yxsszmc}
								</td>
								<th width= 16%>是否受岗位申请数限制</th>
								<td width="34%">
									${rs.sfsgwsqsxzmc}
								</td>
							</tr>
								<th width="16%">岗位开始日期</th>
								<td width="34%">
									${rs.gwkssj}
								</td>
								<th width= 16%>岗位结束日期</th>
								<td width="34%">
									${rs.gwjssj}
								</td>
							</tr>
						</logic:notEmpty>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>在岗学生信息</span>
							</th>
						</tr>
					</thead>
				</table>
				<div style="height:240px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="zgryHtml">
						<tbody>
							<tr>
								<td>该岗位无在岗人员，无需退岗！</td>
							</tr>
						</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="zgryHtml">
					<thead>
						<tr>
							<td ><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td >学号</td>
							<td >姓名</td>
							<td >班级</td>
							<td >是否困难生</td>
							<td >是否在校</td>
							<td >目前勤工岗位数</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
					</tbody>
					</logic:notEmpty>
				</table>
				</div>
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:notEmpty name="rs" property="zgryHtml">
									<button type="button" onclick="tgRyxx()">
										退岗
									</button>
									</logic:notEmpty>
									<button type="button" onclick="refreshParentTg();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			
			<%@ include file="/comm/other/tsxx.jsp"%>
			<div id="xszgxxDiv" style="display: none;">
				<%@ include file="/xsgzgl/qgzx/gwglnew/ryxxCk.jsp"%>
			</div>
		</html:form>
	</body>
</html>

