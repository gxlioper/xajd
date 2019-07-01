<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
			<script type="text/javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/comm/ymPrompt.js" ></script>
	<script type='text/javascript' src="js/comm/message.js"></script>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?skin=iblue"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<html:form action="/zyszpjwh" method="post"
		 styleId="form"> 
		<input type="hidden" name="url" id="url"
			value="zyszpjwh.do?method=add">
			<input type="hidden" name="tableName" id="tableName"
				value="view_xsxxb">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" width="15%">
								学号：
							</td>
							<td align="left" width="40%">
								<html:hidden property="zyszid" value="${zyszid}"/>
								<html:hidden property="xn" value="${xn}"/>
								<html:hidden property="xq" value="${xq}"/>
								${stuInfo.xh}
							</td>
							<td align="right" width="15%">
								姓名：
							</td>
							<td align="left">
								${stuInfo.xm}
							</td>
						</tr>
						<tr>
							<td align="right">
								年级：
							</td>
							<td align="left">
								${stuInfo.nj}
							</td>
							<td align="right">
								<bean:message key="lable.xb" />：
							</td>
							<td align="left">
								${stuInfo.xymc}
							</td>
						</tr>
						<tr>
							<td align="right">
								专业：
							</td>
							<td align="left">
								${stuInfo.zymc}
							</td>
							<td align="right">
								班级：
							</td>
							<td align="left">
								${stuInfo.bjmc}
							</td>
						</tr>
						<tr>
							<td align="right" width="10%">
								学年：
							</td>
							<td align="left">
								${data.xn}
							</td>
							<td align="right">
								学期：
							</td>
							<td align="left">
								${data.xqmc}
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>职业素质活动过程 </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>
								序号
							</td>
							<td>
								子项目
							</td>
							<td>
								时间
							</td>
							<td>
								地点
							</td>
							<td>
								活动内容
							</td>
							<td>
								参与及获奖情况
							</td>
						</tr>
							<tbody id="tbody_add">
							<logic:notEmpty name="zxm">
							<logic:iterate name="zxm" id="s" indexId="i">
								<tr>
									<td>
										${i+1}
										<input type="hidden" id="zxm" name="zxm" value="${s.zxmId}"/>
									</td>
									<td width="120px">
										${s.xmlbmc}
									</td>
									<td width="120px">
										${s.sj}
									</td>
									<td width="120px">
									${s.dd}
									</td>
									<td width="120px">
									${s.hdnr}
									</td>
									<td width="120px">
										${s.cyjhjqk}
									</td>
								</tr>
							</logic:iterate>
							</logic:notEmpty>
						</tbody>
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>自评信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td width="15%" height="120px">内容</td>
							<td width="85%">${data.zpxx}</td>
						</tr>
					</tbody>
				</table>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>互评信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px">内容</td>
								<td width="85%">${data.hpxx}</td>
							</tr>
							<tr>
								<td width="15%" height="20px">评价人</td>
								<td width="85%">${data.hpr}</td>
							</tr>
						</tbody>
					</table>
					<logic:equal name="query" value="brcx">
						<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>师评信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px">内容</td>
								<td width="85%">${data.spxx}</td>
							</tr>
								<tr>
								<td width="15%" height="20px">评价等级</td>
								<td width="85%">${data.pjdj}</td>
							</tr>
						</tbody>
					</table>
					</logic:equal>
					<logic:equal name="query" value="lscx">
						<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="6">
									<span>师评信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="15%" height="120px">内容</td>
								<td width="85%">${data.spxx}</td>
							</tr>
								<tr>
								<td width="15%" height="20px">评价等级</td>
								<td width="85%">${data.pjdj}</td>
							</tr>
						</tbody>
						</table>
					</logic:equal>
					<table width="100%" border="0" class="formlist">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
	</html:form>
</html>