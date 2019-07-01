<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script>
		jQuery(document).ready(function(){
			var sfsdgwcjsx=jQuery("#sfsdgwcjsx").val();
			// 基础配置 设置的薪酬上限
			var gwzgcjsx=jQuery("#gwzgcjsx").val();
			var sfkgggwcjsx=jQuery("#sfkgggwcjsx").val();
			// 岗位设置酬金上限
			var gwcjsx=jQuery("#gwcjsx").val();
			// 如果此岗位未设置
			if(gwcjsx==""){
				jQuery("#gwcjsx").val(gwzgcjsx);
				jQuery("#gwcjsxh").text(gwzgcjsx);
			}
			if("no"==sfsdgwcjsx){
				jQuery("#gwcjsxTr").hide();
			}else{
				jQuery("#gwcjsxTr").show();
				jQuery("#gwcjsx").hide();
				jQuery("#gwcjsxh").show();
				jQuery("#sxcolor").hide();
			}
		});
		//显示退岗信息
		function showTgxsxx(obj){
			jQuery(obj).parent().parent().hide();
			jQuery("#div_tgxsxx").show();
		}

		//新版查看弹出层
		function zxsxxView(xh) {
			showDialog("学生信息查询", 850, 500, "xsxx_xsxxgl.do?method=xsxxglCk&xh=" + xh
					+ "&xs");
		}
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
			
						
			<input type="hidden"  id="sfsdgwcjsx" value="${jcpz.sfsdgwcjsx}"/>
			<input type="hidden"  id="gwzgcjsx" value="${jcpz.gwzgcjsx}"/>
			<input type="hidden"  id="sfkgggwcjsx" value="${jcpz.sfkgggwcjsx}"/>
			<div style="height:450px;overflow-x:hidden;overflow-y:auto;padding-right:3px;">
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
								${rs.xqrs}(在岗人数：<font color="red" >${rs.zgrs}</font>)
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
				<div style="height:200px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<logic:empty name="rs" property="zgryHtml">
					<tbody>
						<tr>
							<td>该岗位无在岗人员！</td>
						</tr>
						<tr>
							<td>
								<a href="#" onclick="showTgxsxx(this);return false;">查看退岗学生信息...</a>
							</td>
						</tr>
					</tbody>
					</logic:empty>
					<logic:notEmpty name="rs" property="zgryHtml">
					<thead>
						<tr>
							<td width='10px'>行号</td>
							<td width='15%'>学号</td>
							<td width='15%'>姓名</td>
							<td width='30%'>班级</td>
							<td width='20%'>是否困难生</td>
							<td width='20%'>上岗日期</td>
						</tr>
					</thead>
					<tbody id="tbody_zgryxx">
						${rs.zgryHtml }
						<tr>
							<td colspan="6">
								<a href="#" onclick="showTgxsxx(this);return false;">查看退岗学生信息...</a>
							</td>
						</tr>
					</tbody>
					</logic:notEmpty>
				</table>
				<div id="div_tgxsxx" style="display: none;">
					<table border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>退岗学生信息</span>
								</th>
							</tr>
						</thead>
					</table>
					<table width="100%" border="0" class="datelist" style="margin:2px auto;">
						<logic:empty name="rs" property="tgryHtml">
						<tbody>
							<tr>
								<td colspan="4">该岗位无退岗人员！</td>
							</tr>
						</tbody>
						</logic:empty>
						<logic:notEmpty name="rs" property="tgryHtml">
						<thead>
							<tr>
								<td width='10px'>行号</td>
								<td width='15%'>学号</td>
								<td width='15%'>姓名</td>
								<td width='25%'>班级</td>
								<td width='15%'>是否贫困生</td>
								<td width='15%'>上岗日期</td>
								<td width='15%'>退岗日期</td>
							</tr>
						</thead>
						<tbody id="tbody_tgryxx">
							${rs.tgryHtml }
						</tbody>
						</logic:notEmpty>
					</table>
					</div>
				</div>
			</div>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="8">
							
								<div class="btn">
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
								</div>
							
							</td>
						</tr>
					</tfoot>
				</table>
			
		</html:form>
	</body>
</html>

