<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/gyglShareData.js'></script>
		<script language="javascript" src="js/gygl/gyglFunction.js"></script>
	</head>
	<body>
		<html:form action="/zjlg_gygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>公寓管理 - 特色寝室管理 - 审核 - 查看详细信息</a>
				</p>
			</div>
			<div class="tab">
			<table width="99%" class="formlist">
				<thead><tr><th colspan="4"><span>信息维护</span></th></tr></thead>
				<tbody>
				<tr>
					<th>
						宿舍编号
					</th>
					<td align="left">
						<bean:write name="rs" property="ssbh" />
					</td>
					<th>
						楼栋名称
					</th>
					<td align="left">
						<bean:write name="rs" property="ldmc" />
					</td>
				</tr>
				<tr>
					<th>
						楼层
					</th>
					<td align="left">
						<bean:write name="rs" property="cs" />
					</td>
					<th>
						寝室号
					</th>
					<td align="left">
						<bean:write name="rs" property="qsh" />
					</td>
				</tr>
				<tr>
					<th>
						学年
					</th>
					<td align="left">
						<bean:write name="rs" property="xn" />
					</td>
					<th>
						联系电话
					</th>
					<td align="left">
						<bean:write name="rs" property="lxdh" />
					</td>
				</tr>
				<tr>				
					<th>
						申请时间
					</th>
					<td align="left">
						<bean:write name="rs" property="sqsj" />
					</td>
					<th>
						<bean:message key="lable.xsgzyxpzxy" />审核
					</th>
					<td align="left">
						<bean:write name="rs" property="xysh" />
					</td>
				</tr>
				<tr>
					<th>
						学校审核
					</th>
					<td align="left">
						<bean:write name="rs" property="xxsh" />
					</td>
					<th>

					</th>
					<td align="left">
					</td>
				</tr>
				<tr>
					<th>
						寝室成员
					</th>
					<td colspan="3">
						<logic:present name="rs" property="qscy">
						<logic:iterate id="s" name="rs" property="qscy">
							<bean:write name="s"/>&nbsp;&nbsp;&nbsp;&nbsp;
						</logic:iterate>
						</logic:present>
					</td>
				</tr>
				<tr>
					<th>
						申报条件
					</th>
					<td colspan="2">
						<logic:present name="rs" property="qstj">
								&nbsp;${rs.qstj.xn }学年&nbsp;${rs.qstj.xq }&nbsp;学期第&nbsp;<bean:write name="rs" property="zs"/>&nbsp;周被批准为校文明寝室
						</logic:present>
					</td>
				</tr>
				<tr>
					<th>
						具体工作内容
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="jtgznr" cols="60" rows="8"></html:textarea>
					</td>
				</tr>
				<tr>
					<th>
						已开展工作情况
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="ykzgzqk" cols="60" rows="8"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4">
			          <div class="btn">
						  <button name="关闭" onclick="window.close();return false;">关闭</button>
			          </div></td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
