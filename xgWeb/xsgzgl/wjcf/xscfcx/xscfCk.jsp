<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
		
	</head>
	<body >
		<html:form action="/wjcfCfshwh_cfsjwh" method="post" enctype='multipart/form-data'>	
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>学生信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
						学号
						</th>
						<td align="left" width="30%">
							${rs.xh }
						</td>
						<th align="right" width="20%">
							姓名
						</th>
						<td align="left" width="30%">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							性别
						</th>
							<td align="left">
							${rs.xb}
						</td>
						<th align="right">
							年级
						</th>
							<td align="left">
							${rs.nj}
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
						<th align="right">
							专业
						</th>
							<td align="left">
							${rs.zymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							班级
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
						<th align="right">
							政治面貌
						</th>
							<td align="left">
							${rs.zzmmmc}
						</td>
						
					</tr>
					
					</tbody>
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>处分上报信息</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<th align="right" width="20%">
							处分学年
						</th>
						<td align="left" width="30%">
							${rs.xn }
						</td>
						<th align="right" width="20%">
							处分学期
						</th>
						<td align="left" width="30%">
							${rs.xqmc }
						</td>
					</tr>
					<tr>
						<th align="right">
							处分原因
						</th>
						<td align="left">
							${rs.cfyymc }
						</td>
						<th align="right">
							处分类别
						</th>
						<td align="left">
							${rs.cflbmc }
						</td>
					</tr>
					
					<tr>
						<th align="right">
							处分文号
						</th>
						<td align="left">
							${rs.cfwh }
						</td>
						<th align="right">
							处分时间
						</th>
						<td align="left">
							${rs.cfsj }
						</td>
					</tr>
					
					<tr>
						<th align="right">
							违纪时间
						</th>
						<td align="left">
							${rs.wjsj }
						</td>
						<th align="right">
							处理决定书面材料或附件
						</th>
						<td align="left">
							<logic:notEmpty name="rs" property="fjmc">
								<a  href="fjxz.do?cfid=${rs.cfid }&fjmc=${rs.fjmc }" class="name"
												target="_self">下载附件</a>
							</logic:notEmpty>
							<logic:empty name="rs" property="fjmc">
							无上传处分附件
							</logic:empty>
						</td>
					</tr>
					<tr>
						<th align="right">
							违纪事实经过
						</th>
						<td colspan="9" style="word-break:break-all;width:100%">
								${rs.wjssjg }
						</td>
					</tr>
						<tr>
						<th align="right">
							备注
						</th>
						<td colspan="9" style="word-break:break-all;width:100%" >
								${rs.bz }
						</td>
					</tr>
					</tbody>
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
