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
		<script type="text/javascript" src="js/qgzx/gwgl/gwgl.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xhs" id="xhs" value="" />
			<input type="hidden" name="sgsjs" id="sgsjs" value="" />
			<input type="hidden" name="sqbhs" id="sqbhs" value="" />
			<input type="hidden" name="xhTal" id="xhTal" value="" />
						
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
								学年
							</th>
							<td width="34%">
								${rs.xn}
							</td>
							<th width="16%">
								用人部门
							</th>
							
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								岗位名称
							</th>
							
							<td width="34%">
								${rs.gwmc}
							</td>
							<th width="16%">
								岗位性质
							</th>
							
							<td width="34%">
								${rs.gwxzmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								需求人数
							</th>
							<td width="34%">
								${rs.xqrs}(在岗人数：<font class="red" >${rs.zgrs}</font>)
							</td>
							<th width="16%">
								困难生数
							</th>
							<td width="34%">
								${rs.knsrs}
							</td>
						</tr>
						<logic:equal value="10052" name="xxdm">
						<tr>
							<th width="16%">
								岗位序号
							</th>
							<td width="34%" colspan="6">
								${rs.gwxh}
							</td>
						</tr>
						</logic:equal>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>岗位酬金上限
							</th>
							<td width="34%" colspan="6">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<tr>
							<th align="right" >
								岗位描述
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwms}
							</td>
						</tr>
						<tr>
							<th align="right" >
								岗位人员要求
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwryyq}
							</td>
						</tr>
						<tr>
							<th align="right" >
								岗位预期人员效果
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwyqryxg}
							</td>
						</tr>
						<tr>
							<th align="right" >
								备注
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
							</td>
						</tr>
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
							<td width='10px'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
							<td width='30%'>班级</td>
							<td width='20%'>是否困难生</td>
							<td width='20%'>目前勤工岗位数</td>
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
			<div id="tempDiv" style="display: none">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>岗位退岗</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								退岗人员
							</th>
							<td>
								<font id="tgry"></font>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>退岗原因
							</th>
							<td>
								<textarea name='tgyy' id="tgyy" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='5'></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" name="保存" onclick="bcTgryxx();">
										保存
									</button>
									<button type="button" name="取消" onclick="closeWindown();return false;">
										取 消
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<div id="xszgxxDiv" style="display: none;">
				<%@ include file="/xsgzgl/qgzx/gwgl/ryxxCk.jsp"%>
			</div>
		</html:form>
	</body>
</html>

