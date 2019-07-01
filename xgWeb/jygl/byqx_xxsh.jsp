<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript">
		function jyglByqxSh() {
			 	document.forms[0].action = "/xgxt/jyglByqxSh.do?act=shenhe&doType=shenghe";
			 	document.forms[0].submit();
	    }
	    
	    function chLengs(obj,leng){
			if(obj.value.length > leng){
				alert("该项目最大字数为"+leng+",现已经超过，请确认！", function(){obj.focus();});
				//alert("字数过大，限"+leng+"字！");
			}
		}
		</script>
	</head>
	<body>
		<div class="tab_cur" id="jd">
			<p class="location">
				<em>您的当前位置:</em><a>就业管理 - 学生信息 - 毕业去向审核</a>
			</p>
		</div>
		<html:form action="/data_search" method="post">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<td align="left" colspan="4">
								学生类别
								<html:text property="xslb" name="rs" style="width:45px"
									readonly="true" />
								毕业年度
								<html:text property="bynd" name="rs" style="width:35px"
									readonly="true" />
								<bean:message key="lable.xsgzyxpzxy" />
								<html:text property="xymc" name="rs" style="width:130px"
									readonly="true" />
								提交时间
								<html:text name="rs" property="tjsj" style="width:140px"
									readonly="true" />
							</td>
						</tr>
					</thead>
					<tbody>
						<tr style="height:22px">
							<th width="15%">
								学号
							</th>
							<td align="left" width="35%">
								<html:text name="rs" property="xsxh" style="width=100%"
									readonly="true" />
							</td>
							<th width="15%">
								姓名
							</th>
							<td align="left" width="35%">
								<bean:write name="rs" property="name" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								性别
							</th>
							<td align="left">
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								身份证号
							</th>
							<td align="left">
								<bean:write name="rs" property="id" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								学校名称
							</th>
							<td align="left">
								${xxmc }
							</td>
							<th align="right">
								<bean:message key="lable.xb" />名称
							</th>
							<td align="left">
								<bean:write name="rs" property="xymc" />
							</td>

						</tr>
						<tr style="height:22px">
							<th align="right">
								专业名称
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc" />
							</td>
							<th align="right">
								班级名称
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								生源地区
							</th>
							<td align="left">
								<bean:write name="rs" property="sydq" />
							</td>
							<th align="right">
								培养方式
							</th>
							<td align="left">
								<bean:write name="rs" property="pyfs" />
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right">
								毕业去向
							</th>
							<td align="left">
								<bean:write name="rs" property="byqx" />
							</td>
							<th align="right">
								联系地址
							</th>
							<td align="left">
								<bean:write name="rs" property="lxdz" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								联系电话
							</th>
							<td>
								<bean:write name="rs" property="lxdh" />
							</td>
							<th>
								邮政编码
							</th>
							<td>
								<bean:write name="rs" property="yzbm" />
							</td>
						</tr>
						<tr style="height:22px">
							<th>
								移动电话
							</th>
							<td>
								<bean:write name="rs" property="yddh" />
							</td>
							<th>
								电子邮箱
							</th>
							<td>
								<bean:write name="rs" property="email" />
							</td>
						</tr>
						<logic:equal value="12453" name="xxdm" scope="session">

							<logic:equal value="xx" name="ldgxusertype" scope="request">
								<tr style="height:22px;">
									<th>
										学校审核
									</th>
									<td>
										<html:select name="rs" property="xxsh" style="width:100xp">
											<html:option value="未审核">未审核</html:option>
											<html:option value="未通过X">未通过X</html:option>
											<html:option value="已通过√">已通过√</html:option>
										</html:select>
									</td>
									<th>
										审核时间
									</th>
									<td>
										<html:text name="rs" property="shsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:55px">
									<th>
										修改意见
									</th>
									<td colspan="3">
										<html:textarea name="rs" property="xgyj" rows="3"
											style="width=100%" />
									</td>
								</tr>
								<tr style="height:22px">
									<td>
										审核人
									</td>
									<td>
										<html:text name="rs" property="shperson" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td>

									</td>
								</tr>
								<tr style="height:22px">
									<th>
										辅导员审核
									</th>
									<td align="left">
										<html:text name="rs" property="fdysh" style="width=100%"
											readonly="true" />
									</td>
									<th>
										审核时间
									</th>
									<td>
										<html:text name="rs" property="fdyshsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<th>
										审核人
									</th>
									<td align="left">
										<html:text name="rs" property="fdyshr" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td align="center">

									</td>
								</tr>
							</logic:equal>
							<logic:equal value="xy" name="ldgxusertype" scope="request">
								<tr style="height:22px;">
									<th>
										学校审核
									</th>
									<td>
										<html:text name="rs" property="xxsh" style="width=100%"
											readonly="true" />
									</td>
									<th>
										审核时间
									</th>
									<td>
										<html:text name="rs" property="shsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:55px">
									<th>
										修改意见
									</th>
									<td colspan="3">
										<html:textarea name="rs" property="xgyj" rows="3"
											style="width=100%" readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<th>
										审核人
									</th>
									<td>
										<html:text name="rs" property="shperson" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td>

									</td>
								</tr>
								<tr style="height:22px">
									<th>
										辅导员审核
									</th>
									<td>
										<html:select name="rs" property="fdysh" style="width:100xp">
											<html:option value="未审核">未审核</html:option>
											<html:option value="未通过X">未通过X</html:option>
											<html:option value="已通过√">已通过√</html:option>
										</html:select>
									</td>
									<th">
										审核时间
									</th>
									<td>
										<html:text name="rs" property="fdyshsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:22px">
									<th>
										审核人
									</th>
									<td align="left">
										<html:text name="rs" property="fdyshr" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td align="center">

									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						<logic:notEqual value="12453" name="xxdm" scope="session">
							<logic:equal value="xx" name="ldgxusertype" scope="request">
								<tr style="height:22px;">
									<th>
										学校审核
									</th>
									<td>
										<html:select name="rs" property="xxsh" style="width:100xp">
											<html:option value="未审核">未审核</html:option>
											<html:option value="未通过X">未通过X</html:option>
											<html:option value="已通过√">已通过√</html:option>
										</html:select>
									</td>
									<th>
										就业单位
									</th>
									<td>
										<bean:write name="rs" property="jydw"/>
									</td>
								</tr>
								<tr style="height:55px">
									<th>
										修改意见
									</th>
									<td colspan="3">
										<html:textarea name="rs" property="xgyj" rows="3"
											style="word-break:break-all;width:95%"
											onblur="chLengs(this,50);" />
									</td>
								</tr>
								<tr style="height:22px;display: none">
									<th>
										审核人
									</th>
									<td>
										<html:text name="rs" property="shperson" style="width=100%"
											readonly="true" />
									</td>
									<th>
										
									</th>
									<td align="center">
										
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="xy" name="ldgxusertype" scope="request">
								<tr style="height:22px">
									<th>
										学校审核
									</th>
									<td>
										<html:text name="rs" property="xxsh" style="width=100%"
											readonly="true" />
									</td>
									<th>
										审核时间
									</th>
									<td>
										<html:text name="rs" property="shsj" style="width=100%"
											readonly="true" />
									</td>
								</tr>
								<tr style="height:55px">
									<th>
										修改意见
									</th>
									<td colspan="3">
										<html:textarea name="rs" property="xgyj" rows="3"
											style="width=100%" />
									</td>
								</tr>
								<tr style="height:22px">
									<th>
										审核人
									</th>
									<td>
										<html:text name="rs" property="shperson" style="width=100%"
											readonly="true" />
									</td>
									<th>

									</th>
									<td align="center">

									</td>
								</tr>
							</logic:equal>

						</logic:notEqual>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button name="关闭" onclick="jyglByqxSh();return false;">
										审 核
									</button>
									<button onclick="Close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
		<logic:notEmpty name="shenhe">
			<logic:equal name="shenhe" value="ok">
				<script>
                     alert("操作成功！");
                     if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
               </script>
			</logic:equal>
			<logic:equal name="shenhe" value="no">
				<script>
                    alert("操作失败！");
                    if (window.dialogArguments) {
							window.close();
							window.dialogArguments.document.getElementById('search_go').click();
						}
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

