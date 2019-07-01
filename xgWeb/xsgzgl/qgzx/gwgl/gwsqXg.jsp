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
		<script type="text/javascript" src="js/qgzx/gwgl/gwsq.js"></script>
				<script type="text/javascript">
			jQuery(document).ready(function(){
				var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
				//基础配置 设置的薪酬上限
				var gwzgcjsx=jQuery("#gwzgcjsx").val();
				var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
				//岗位设置酬金上限
				var gwcjsx=jQuery("#gwcjsx").val();
				//如果此岗位未设置
				if(gwcjsx==""){
					jQuery("#gwcjsx").val(gwzgcjsx);
					jQuery("#gwcjsxh").text(gwzgcjsx);
				}
				if("no"==sfsdgwcjsx){
					jQuery("#gwcjsxTr").hide();
				}else{
					jQuery("#gwcjsxTr").show();
					if("no"==sfkgggwcjsx){
						jQuery("#gwcjsx").hide();
						jQuery("#gwcjsxh").show();
						jQuery("#sxcolor").hide();
					}else{
						jQuery("#gwcjsx").show();
						jQuery("#gwcjsxh").hide();
						jQuery("#sxcolor").show();
					}
				}

				var doType=jQuery("#doType").val();
				//查看处理
				if(doType!="update"){
					jQuery("#gwcjsx").hide();
					jQuery("#gwcjsxh").show();
					jQuery("#sxcolor").hide();
					jQuery(".bz").hide();
				}
			});
		</script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="oldGwxh" id="oldGwxh" value="${rs.gwxh }" />
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%>		
			<div style='tab；width:100%;overflow-x:hidden;overflow-y:auto;height:555px;'>
				<table border="0" class="formlist" >
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
						<logic:equal name="doType" value="update">
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
								<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font>岗位描述<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwms' styleId="gwms" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
						</tr>
						<tr style="height:80px">
							<th align="right" >
								<font class="red">*</font>岗位人员要求<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwryyq' styleId="gwryyq" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								岗位预期人员效果<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='gwyqryxg' styleId="gwyqryxg" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='6' />
							</td>
							
						</tr>
						<tr style="height:80px">
							<th align="right" >
								备注<br/><font color="red">(限1000字)</font>
							</th>
							<td colspan="7" >
							<html:textarea  name="rs" property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLengs(this,1000);"
									rows='4'   />
							</td>
						</tr>
						</logic:equal>
						<logic:notEqual name="doType" value="update">
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
							<td width="34%" colspan="7">
								<html:text  name="rs" property="gwcjsx" styleId="gwcjsx" size="10" maxlength="7" onblur="checkInt(this)"></html:text>
								<span id="gwcjsxh">${rs.gwcjsx}</span>
								<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
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
						</logic:notEqual>
					</tbody>
					<logic:notEqual name="doType" value="update">
					<thead>
						<tr>
							<th colspan="8">
								<span>岗位审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								审核状态
							</th>
							
							<td width="34%" colspan="3" >
								${rs.shzt }
							</td>
							<th width="16%">
								审核人
							</th>
							<td width="34%" colspan="3" >
								${rs.shr }
							</td>
						</tr>
						<tr>
							<th width="16%">
								审核时间
							</th>
							
							<td width="34%" colspan="3" >
								${rs.shsj }
							</td>
							<th width="16%">
							</th>
							<td width="34%" colspan="3" >
							</td>
						</tr>
						<tr style="height:40px">
							<th align="right" >
								审核意见
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.shyj }
							</td>
						</tr>
					</tbody>
					</logic:notEqual>
				</table>
			</div>
			<div style="height: 30px">
			</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="8">
							<div class="bz">"<span class="red">*</span>"为必填项</div>
								<div class="btn">
									<logic:equal name="doType" value="update">
										<button type="button" onclick="bcXgGwsq()">
											保 存
										</button>
									</logic:equal>
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

