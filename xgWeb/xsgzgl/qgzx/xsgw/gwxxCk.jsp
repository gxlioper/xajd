<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><div style='tab'>
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
								${rs.xqrs}(人)
							</td>
							<th width="16%">
								困难生数
							</th>
							<td width="34%">
								${rs.knsrs}(人)
							</td>
						</tr>
						<logic:equal name="isshow" value="true" >
							<tr id="gwcjsxTr">
								<th width="16%">
									岗位酬金上限
								</th>
								<td width="34%">
									<span id="gwcjsxh">${rs.gwcjsx}</span>
									<span>元/月  &nbsp;&nbsp;(该岗位每人每月酬金上限)</span>
								</td>
								<th width="16%">
									已录用人数
								</th>
								<td width="84%">
									${rs.zgrs}(人)
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="isshow" value="true">
							<tr>
								<th width="16%">
									已录用人数
								</th>
								<td width="84%" colspan="3">
									${rs.zgrs}(人)
								</td>
							</tr>
						</logic:notEqual>
						<tr style="height: 80px">
							<th align="right" >
								岗位描述
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwms}
							</td>
						</tr>
						<tr style="height: 80px">
							<th align="right" >
								岗位人员要求
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwryyq}
							</td>
							
						</tr>
						<tr  style="height: 80px">
							<th align="right" >
								备注
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr> 
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="Close();return false;" id="buttonClose">
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

