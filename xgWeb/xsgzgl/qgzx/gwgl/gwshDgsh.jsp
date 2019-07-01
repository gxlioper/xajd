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
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/qgzx/gwgl/gwsh.js" defer="defer"></script>
	</head>
	<body>
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xn" id ="xn" value ="${rs.xn}"/>
			<input type="hidden" name="yrdwdm" id ="yrdwdm" value ="${rs.yrdwdm}"/>
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<logic:equal name="doType" value="update">
						<tr>
							<th colspan="8">
								<span>岗位修改</span>
							</th>
						</tr>
						</logic:equal>
						<logic:notEqual name="doType" value="update">
						<tr>
							<th colspan="8">
								<span>岗位申请信息</span>
							</th>
						</tr>
						</logic:notEqual>
					</thead>
					
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								学年
							</th>
							<td width="34%" colspan="3" >
								<html:hidden name="rs" property="xn" styleId="xn"/>
								${rs.xn}
							</td>
							<th width="16%">
								用人部门
							</th>
							
							<td width="34%" colspan="3" >
								<html:hidden name="rs" property="yrdwdm" styleId="yrbm"/>
								${rs.yrdwmc}
							</td>
						</tr>
							<tr>
							<th width="16%">
								岗位名称
							</th>
							
							<td width="34%" colspan="3" >
								${rs.gwmc }
							</td>
							<th width="16%">
								岗位性质
							</th>
							<td width="34%" colspan="3" >
								${rs.gwxzmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								需求人数
							</th>
							<td width="34%" colspan="3" >
								${rs.xqrs }(人)
							</td>
							<th width="16%">
								困难生数
							</th>
							
							<td width="34%" colspan="3">
								${rs.knsrs }(人)
							</td>
						</tr>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>岗位酬金上限
							</th>
							<td colspan="7">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<tr>
							<th width="16%">
								申请人
							</th>
							<td width="34%" colspan="3" >
								${rs.sqr }
							</td>
							<th width="16%">
								申请时间
							</th>
							
							<td width="34%" colspan="3">
								${rs.sqsj }
							</td>
						</tr>
						<tr style="height:40px">
							<th align="right" >
								岗位描述
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwms }
							</td>
						</tr>
						<tr style="height:40px">
							<th align="right" >
								岗位人员要求
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwryyq }
							</td>
							
						</tr>
						<tr style="height:40px">
							<th align="right" >
								岗位预期人员效果
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.gwyqryxg }
							</td>
							
						</tr>
						<tr style="height:40px">
							<th align="right" >
								备注
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="8">
								<span>岗位审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height:40px">
							<th align="right" >
								<span class="red">*</span>审核意见<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=cdgl&id=shyj" />
								
								<html:textarea name="rs" property='shyj' styleId="shyj" style="word-break:break-all;width:97%;margin-top:5px" onblur="chLengs(this,1000);"
									rows='4'   />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px">
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="gwxxshBc(1);return false;">
										通过
									</button>
									<button type="button" onclick="gwxxshBc(2);return false;">
										不通过
									</button>
									<button type="button" onclick="gwxxshBc(3);return false;">
										退回重审
									</button>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

