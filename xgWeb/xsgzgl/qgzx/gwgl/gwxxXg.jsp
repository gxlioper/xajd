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
		<script type="text/javascript" src="js/qgzx/gwgl/gwgl.js"></script>
		<script type="text/javascript">

			jQuery(function() {
				initData();
			});
		
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="oldGwxh" id="oldGwxh" value="${rs.gwxh }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			
			<!-- 提示信息 -->
			<div class="prompt" id="div_help" >
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					<span>
					需求人数修改时需<font color="blue">大于或等于</font>在岗人数
					</span>
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>岗位修改</span>
							</th>
						</tr>
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
								<html:text name="rs" property="gwmc" styleId="gwmc" maxlength="50" style="width:200px" ></html:text>
							</td>
							<th width="16%">
								岗位性质
							</th>
							
							<td width="34%" colspan="3" >
								<html:select name="rs" property="gwxzdm" styleId="gwxzdm" style="width:200px" >
									<html:option value="">---------请选择---------</html:option>
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font class="red">*</font>需求人数
							</th>
							<td width="34%" colspan="3" >
								<html:text name="rs" property="xqrs" styleId="xqrs" size="10" maxlength="3" onblur="checkInputNum(this)"></html:text>
								<html:hidden name="rs" property="zgrs" styleId="zgrs"/>
								(在岗人数：<font color="red" >${rs.zgrs}</font>)
								
							</td>
							<th width="16%">
								<font class="red">*</font>困难生数
							</th>
							
							<td width="34%" colspan="3">
								<html:text name="rs" property="knsrs" styleId="knsrs" size="10" maxlength="3" onblur="checkXqrs(this)"></html:text>  (人)
							</td>
						</tr>
						<tr id="gwcjsxTr">
							<th width="16%">
								<font id="sxcolor" color="red">*</font>岗位酬金上限
							</th>
							<td width="34%" colspan="6">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>元/月 &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font>岗位描述<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwms' styleId="gwms" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='4' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font>岗位人员要求<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='4' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								岗位预期人员效果<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwyqryxg' styleId="gwyqryxg" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='4' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								备注<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='4'   />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<button type="button" onclick="bcXgGwxx()">
										保 存
									</button>
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

