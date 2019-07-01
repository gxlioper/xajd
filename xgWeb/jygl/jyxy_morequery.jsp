<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		
		<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>就业管理 - 就业协议方案 - 就业协议</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
				<tbody>
				<tr style="height:22px">
					<td colspan="4" align="center" bgcolor="DOEOEE">
						<b>第一部分</b>
					</td>
				</tr>
				<tr bgcolor="DOEOEE">
					<td align="left" colspan="4">
						学生类别:
						<html:text property="xslb" name="rs" readonly="true"
							style="width:45px" />
						&nbsp;&nbsp;毕业年度:
						<html:text property="bynd" name="rs" readonly="true"
							style="width:35px" />
						&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />:
						<html:text property="xymc" name="rs" readonly="true"
							style="width:120px" />
						&nbsp;&nbsp;提交时间:
						<html:text name="rs" property="tjsj" readonly="true"
							style="width:140px" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right" width="17%">
						学号
					</th>
					<td align="left" width="33%">
						<bean:write name="rs" property="xsxh" />
					</td>

					<th align="right" width="17%">
					</th>
					<td align="left" width="33%">
					</td>
				</tr>
				<tr style="height:22px">
						<th align="right">
							协议书编号
						</th>
						<td align="left">
							<bean:write name="rs" property="xysbh" />
						<th align="right" style="width=20%">
							姓名
						</th>
						<td align="left">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
				<tr style="height:22px">
					<th align="right">
						性别代码
					</th>
					<td align="left">
						<bean:write name="rs" property="xbdm" />
					<th align="right">
						身份证号
					</th>
					<td align="left">
						<bean:write name="rs" property="id" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						学校代码
					</th>
					<td align="left">
						<bean:write name="rs" property="xxdm" />
					</td>
					<th align="right">
						学校名称
					</th>
					<td align="left">
						<bean:write name="rs" property="xxmc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						专业代码
					</th>
					<td align="left">
						<bean:write name="rs" property="zydm" />
					</td>
					<th align="right">
						专业名称
					</th>
					<td align="left">
						<bean:write name="rs" property="zymc" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						学制代码
					</th>
					<td align="left">
						<bean:write name="rs" property="xzdm" />
					</td>
					<th align="right">
						学历代码
					</th>
					<td align="left">
						<bean:write name="rs" property="xldm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						培养方式代码
					</th>
					<td align="left">
						<bean:write name="rs" property="pyfsdm" />
					</td>
					<th align="right">
						生源地区代码
					</th>
					<td align="left">
						<bean:write name="rs" property="sydqdm" />
					</td>
				</tr>

				<tr style="height:22px">
					<td colspan="4" align="center" bgcolor="DOEOEE">
						<b>第二部分</b>
					</td>
				</tr>


				<tr style="height:22px">
					<th align="right">
						毕业去向代码
					</th>
					<td align="left">
						<bean:write name="rs" property="byqxdm" />
					</td>
					<th align="right">
						生源地区名称
					</th>
					<td align="left">
						<bean:write name="rs" property="sydq" />
					</td>

				</tr>
				<tr style="height:22px">
					<th align="right">
						单位地区名称
					</th>
					<td align="left">
						<bean:write name="rs" property="dwdq" />
					</td>

					<th align="right">
						主管部门名称
					</th>
					<td align="left">
						<bean:write name="rs" property="zgbm" />
					</td>

				</tr>
				<tr style="height:22px">
					<th align="right">
						单位地区代码
					</th>
					<td align="left">
						<bean:write name="rs" property="dwdqdm" />
					</td>
					<th align="right">
						主管部门代码
					</th>
					<td align="left">
						<bean:write name="rs" property="zgbmdm" />
					</td>


				</tr>
				<tr style="height:22px">
					<th align="right">
						信息登记号
					</th>
					<td align="left">
						<bean:write name="rs" property="xxdjh" />
					</td>
					<th align="right">
						组织机构代码
					</th>
					<td align="left">
						<bean:write name="rs" property="zzjgdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						单位名称
					</th>
					<td align="left">
						<bean:write name="rs" property="dwmc" />
					</td>
					<th align="right">
						政治面貌代码
					</th>
					<td align="left">
						<bean:write name="rs" property="zzmmdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						未就业标志
					</th>
					<td align="left">
						<bean:write name="rs" property="wjybz" />
					</td>
					<th align="right">
						自定义1
					</th>
					<td align="left">
						<bean:write name="rs" property="bz1" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						未就业原因
					</th>
					<td align="left">
						<bean:write name="rs" property="wjyyy" />
					</td>
					<th align="right">
						自定义2
					</th>
					<td align="left">
						<bean:write name="rs" property="bz2" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						联系地址
					</th>
					<td align="left">
						<bean:write name="rs" property="lxdz" />
					</td>
					<th align="right">
						自定义3
					</th>
					<td align="left">
						<bean:write name="rs" property="bz3" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						邮编
					</th>
					<td align="left">
						<bean:write name="rs" property="yb" />
					</td>
					<th align="right">
						自定义4
					</th>
					<td align="left">
						<bean:write name="rs" property="bz4" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						电话
					</th>
					<td align="left">
						<bean:write name="rs" property="dh" />
					</td>
					<th align="right">
						自定义5
					</th>
					<td align="left">
						<bean:write name="rs" property="bz5" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						
					</th>
					<td align="left">
						
					</td>
					<th align="right">
						居住证或蓝表标志位
					</th>
					<td align="left">
						<bean:write name="rs" property="jzzhlbbzwdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						生源地主管单位
					</th>
					<td align="left">
						<bean:write name="rs" property="sydzgbm" />
					</td>
					<th align="right">
						单位性质代码
					</th>
					<td align="left">
						<bean:write name="rs" property="dwxzdm" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						蓝表批准文号
					</th>
					<td align="left">
						<bean:write name="rs" property="blueno" />
					</td>
					<th align="right">
						备注
					</th>
					<td align="left">
						<bean:write name="rs" property="memo" />
					</td>
				</tr>

				<tr style="height:22px">
					<td colspan="4" align="center" bgcolor="DOEOEE">
						<b>第三部分</b>
					</td>
				</tr>


				<tr style="height:22px">
					<th align="right">
						单位性质代码2
					</th>
					<td align="left">
						<bean:write name="rs" property="dwxzdm2" />
					</td>
					<th align="right">
							<logic:equal value="10856" name="xxdm">
								档案接收单位名称
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								档案接收地
							</logic:notEqual>
					</th>
					<td align="left">
						<bean:write name="rs" property="dajsd" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						单位地址
					</th>
					<td align="left">
						<bean:write name="rs" property="dwdz" />
					</td>
					<th align="right">
						档案接收地地址
					</th>
					<td align="left">
						<bean:write name="rs" property="dajsddz" />
					</td>
				</tr>
				<tr style="height:22px">
						<th align="right">
							单位联系人
						</th>
						<td align="left">
							<bean:write name="rs" property="dwlxr" />
						</td>
						<th align="right">
							档案接收单位名称
						</th>
						<td align="left">
							<bean:write name="rs" property="dajsddwmc" />
						</td>
					</tr>
				<tr style="height:22px">
					<th align="right">
						单位电话
					</th>
					<td align="left">
						<bean:write name="rs" property="dwdh" />
					</td>
					<th align="right">
						档案接收地邮编
					</th>
					<td align="left">
						<bean:write name="rs" property="dajsdyb" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						单位邮编
					</th>
					<td align="left">
						<bean:write name="rs" property="dwyb" />
					</td>
					<th align="right">
							<logic:equal value="10856" name="xxdm">
								单位所在地区
							</logic:equal>
							<logic:notEqual value="10856" name="xxdm">
								地区流向
							</logic:notEqual>
					</th>
					<td align="left">
						<bean:write name="rs" property="dqlx" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						违约金
					</th>
					<td align="left">
						<bean:write name="rs" property="wyj" />
					</td>
					<th align="right">
						行业分类
					</th>
					<td align="left">
						<bean:write name="rs" property="hyfl" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						第一年月平均工资
					</th>
					<td align="left">
						<bean:write name="rs" property="dynypjgz" />
					</td>
					<th align="right">
						专业对口
					</th>
					<td align="left">
						<bean:write name="rs" property="zydk" />
					</td>
				</tr>
				<tr style="height:22px">
						<th align="right">
							单位规模
						</th>
						<td align="left">
							<bean:write name="rs" property="dwgm" />
						</td>
						<th align="right">
							单位注册资金
						</th>
						<td align="left">
							<bean:write name="rs" property="dwzczj" />
						</td>
					</tr>
						<tr style="height:22px">
						<th align="right">
							报道开始时间
						</th>
						<td align="left">
								<bean:write name="rs" property="bdkssj" />
						</td>
						<th align="right">
							报道结束时间
						</th>
						<td align="left">
							<bean:write name="rs" property="bdjssj" />
						</td>
					</tr>
					<tr style="height:22px">
						<th align="right">
							报道证号
						</th>
						<td align="left">
							<bean:write name="rs" property="bdzh" />
						</td>
						<th align="right">
						</th>
						<td align="left">
						</td>
					</tr>
				<tr style="height:22px">
					<th align="right">
						学校审核结果
					</th>
					<td align="left">
						<html:text name="rs" property="xxsh" readonly="true"
							style="width=100%" />
					</td>
					<th align="right">
						学校审核人
					</th>
					<td align="left">
						<html:text name="rs" property="xxshren" readonly="true"
							style="width=100%" />
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						学校审核时间
					</th>
					<td align="left" colspan="3">
						<html:text name="rs" property="xxshsj" readonly="true"
							style="width=100%" />
					</td>
				</tr>

				<tr>
					<th align="right">
						不通过原因及修改意见
					</th>
					<td colspan="3">
						<html:textarea name="rs" property="btgyy" rows="4"
							style="word-break:break-all;width:99%" readonly="true" />
					</td>
				</tr>
				<!-- 判断是否有附件上传 -->
				<logic:present name="youFj">
					<th align="right">
						书面材料或附件下载
					</th>
					<td colspan="3">
						<a href="downloadfilewj.do?wjsclj=${rs.wjdz }" target="_self">下载</a>
					</td>
				</logic:present>
			</table>
		</fieldset>
	</body>
</html>
