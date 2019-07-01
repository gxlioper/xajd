<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/wjcfFuction.js"></script>	
</head>

<body>
	<html:form action="/wjcfzjcmwh" method="post">
	<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>违纪处分 - 数据维护 - 个人违纪信息</a>
			</p>
		</div>
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>解除留校察看详细信息</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="button2" id="btn_close" onclick="window.close();return false;" style="width:80px">
											关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
			<tr style="height:23px">
				<th align="right" width="15%">
					<font color="red">*</font>学号
				</th>
				<td align="left" width="35%">
					${rs.xh }
				</td>
				<th align="right" width="15%">
					<font color="red">*</font>学年
				</th>
				<td align="left" width="35%">
					${rs.xn }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					姓名
				</th>
				<td align="left">
					${rs.xm }
				</td>
				<th align="right">
					<font color="red">*</font>年度
				</th>
				<td align="left">
					${rs.nd }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					性别
				</th>
				<td align="left">
					${rs.xb }
				</td>
				<th align="right">
					处分类别
				</th>
				<td align="left">
					${rs.cflbmc }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					年级
				</th>
				<td align="left">
					${rs.nj }
				</td>
				<th align="right">
					处分原因
				</th>
				<td align="left">
					${rs.cfyymc }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc }
				</td>
				<th align="right">
					处分学年/年度
				</th>
				<td align="left">
					${rs.cfxn }/${rs.cfnd }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					专业
				</th>
				<td align="left">
					${rs.zymc }
				</td>
				<th align="right">
					处分文号
				</th>
				<td align="left">
					${rs.cfwh }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					班级
				</th>
				<td align="left" colspan="">
					${rs.bjmc }
				</td>
				<th align="right">
					处分时间
				</th>
				<td align="left">
					${rs.cfsj }
				</td>
			</tr>
		<tr style="height:23px">
				<th align="right">
					拟解除留校察看时间
				</th>
				<td align="left" colspan="">
					${rs.lxcksj }
				</td>
				<th align="right">
					
				</th>
				<td align="left">
					
				</td>
			</tr>
		<tr style="height:23px">
				<th align="right">
					留校察看期间学生表现签定
				</th>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="xsbx" styleId="xsbx" rows="7" style="width:95%"></html:textarea>
				</td>
			</tr>
			<logic:equal value="11647" name="xxdm">
			<tr style="height:23px">
				<th align="right">
					有无需要提前解除留校察看期<br/>（需要提前解除请注明理由）
				</th>
				<td align="left" colspan="3">
					<html:textarea name="rs" property="tqjcly" styleId="tqjcly" rows="5" style="width:95%"></html:textarea>
				</td>
		</tr>
		</logic:equal>
		<tr style="height:23px">
				<th align="right">
					解除留校察看时间
				</th>
				<td align="left" colspan="">
					${rs.jcsj }
				</td>
				<th align="right">
					解除留校察看文号
				</th>
				<td align="left">
					${rs.jcwh }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					解除留校察看结果
				</th>
				<td align="left" colspan="">
					${rs.jcjg }
				</td>
				<th align="right">
				
				</th>
				<td align="left">
				
				</td>
			</tr>
		</table>
	</html:form>
</body>
