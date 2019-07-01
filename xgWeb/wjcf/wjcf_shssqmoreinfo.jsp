<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<div class="formbox">
			<h3 class="datetitle_01">
				<span> 查询结果&nbsp;&nbsp; <logic:empty name="rswj">
						<font color="red"> 暂无材料或证明附件</font>
					</logic:empty> </span>
			</h3>

			<logic:notEmpty name="rswj">
				<table summary="" class="dateline" width="100%">
					<logic:iterate id="list" name="rswj">
						<tr onmouseover="rowOnClick(this)">
							<td>
								<a
									href="downloadfilewj.do?len=<bean:write name="list" property="len"/>&wjsclj=<bean:write name="list" property="wjsclj"/>">下载
								</a>
							</td>
							<td>
								<bean:write name="list" property="cfwh" />
							</td>
							<td>
								<bean:write name="list" property="cflbmc" />
							</td>
							<td>
								<bean:write name="list" property="cfyymc" />
							</td>
							<td>
								<bean:write name="list" property="sssj" />
							</td>
						</tr>
					</logic:iterate>
				</table>

			</logic:notEmpty>
		</div>
		<div class="tab">
			<table width="100%" border="0" class="formlist" id="rsTable">
				<thead>
					<tr>
						<th colspan="5">
							<span><bean:write name="tips" scope="request" /> </span>
						</th>
					</tr>
				</thead>
				<tfoot>
							<tr>
								<td colspan="5">
									<div class="btn">
										
										<button type="button" onclick="Close();return false;" id="buttonClose">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
				<tbody>
					<tr>
						<th width="20%">
							学号
						</th>
						<td width="30%">
							<bean:write name="rs" property="xh" />
						</td>
						<th width="18%">
							处分文件号
						</th>
						<td align="left">
							<bean:write name="rs" property="cfwh" />
						</td>
						<td align="left" width="15%" rowspan="5">
							<img
								src="<%=request.getContextPath()%>/stuPic.jsp?xh=<bean:write name="rs" property="xh" />"
								border="0" align="absmiddle" style="width:140;height:160" />
						</td>
					</tr>
					<tr>
						<th>
							姓名
						</th>
						<td align="left">
							<bean:write name="rs" property="xm" />
						</td>
						<th>
							年度
						</th>
						<td align="left">
							<bean:write name="rs" property="nd" />
						</td>
					</tr>
					<tr>
						<th>
							性别
						</th>
						<td align="left">
							<bean:write name="rs" property="xb" />
						</td>
						<th>
							学年
						</th>
						<td align="left">
							<bean:write name="rs" property="xn" />
						</td>
					</tr>
					<tr>
						<th>
							年级
						</th>
						<td align="left">
							<bean:write name="rs" property="nj" />
						</td>
						<th>
							学期
						</th>
						<td align="left">
							<bean:write name="rs" property="xq" />
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />

						</th>
						<td align="left">
							<bean:write name="rs" property="xymc" />
						</td>
						<th>
							处分时间
						</th>
						<td align="left">
							<bean:write name="rs" property="cfsj" />
						</td>
					</tr>
					<tr>
						<th>
							专业
						</th>
						<td align="left">
							<bean:write name="rs" property="zymc" />
						</td>
						<th>
							处分类别
						</th>
						<td align="left" colspan="2">
							<bean:write name="rs" property="cflbmc" />
						</td>
					</tr>
					<tr>
						<th>
							班级
						</th>
						<td align="left">
							<bean:write name="rs" property="bjmc" />
						</td>
						<th>
							处分事由
						</th>
						<td align="left" colspan="2">
							<bean:write name="rs" property="cfyymc" />
						</td>
					</tr>
					<tr>
						<th>
							联系地址
						</th>
						<td align="left">
							<bean:write name="rs" property="dz" />
						</td>
						<th>
							<logic:present name="isZJJDZYJSXY">
					    申诉/解除申请时：
					    </logic:present>
							<logic:notPresent name="isZJJDZYJSXY">
					       申诉时间
					     </logic:notPresent>
						</th>
						<td align="left" colspan="2">
							<bean:write name="rs" property="sssj" />
						</td>
					</tr>
					<tr>
						<th>
							邮政编码
						</th>
						<td align="left">
							<bean:write name="rs" property="yb" />
						</td>
						<th>
							联系电话
						</th>
						<td align="left" colspan="2">
							<bean:write name="rs" property="lxdh" />
						</td>
					</tr>
					<logic:present name="isZJJDZYJSXY">
						<tr>
							<th>
								申请处分改为
							</th>
							<td align="left" colspan="4">
								<bean:write name="rs" property="cfxg" />
							</td>
						</tr>
					</logic:present>
					<tr>
						<th>
							<logic:present name="isZJJDZYJSXY">
					    申诉/解除<br />
								申请理由
							<br />
							</logic:present>
							<logic:notPresent name="isZJJDZYJSXY">
					   改变<bean:message key="lable.xsgzyxpzxy" />&nbsp;&nbsp;&nbsp;<br />
								处分要求 
						</logic:notPresent>

						</th>
						<td colspan="4" style="word-break:break-all;">
							<bean:write name="rs" property="yq" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>
