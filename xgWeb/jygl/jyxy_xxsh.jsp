<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>	
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script language="javascript">
	    function shgo(){
	    var fdysh = document.getElementById("fdysh").value;
	    var xxsh = document.getElementById("xxsh").value;
	    if(fdysh=="未通过X"&&xxsh=="未通过X"){
	    alert("你不必重复否决该协议！");
	    return false;
	    }
			 	document.forms[0].action = "/xgxt/jyglJyxyXxsh.do?act=shenhe&doType=shenghe";
			 	document.forms[0].submit();
	    }
	    
	    function chekhid(){
			var fdyval = document.getElementById("xxsh").value;
			var hidval = document.getElementById("btgtr");
			if(fdyval == "未通过X"){
				hidval.style.display = "inline";
			}else{
				hidval.style.display = "none";
			}
	    }
		</script>
	</head>
	
	<body onload="">
		<script language="javascript" src="js/function.js"></script>

		<html:form action="/data_search" method="post">
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
							学生类别
							<html:text property="xslb" name="rs" readonly="true"
								style="width:45px" />
							毕业年度
							<html:text property="bynd" name="rs" readonly="true"
								style="width:35px" />
							<bean:message key="lable.xsgzyxpzxy" />
							<html:text property="xymc" name="rs" readonly="true"
								style="width:120px" />
							提交时间
						        <html:text name="rs" property="tjsj" readonly="true" style="width:140px" />
						</td>
					</tr>
					<tr style="height:22px">
						<th width="19%">
							学号
						</th>
						<td width="31%">
							<html:text name="rs" property="xsxh" readonly="true" />
						</td>

						<th width="18%">
							姓名
						</th>
						<td width="32%">
							<bean:write name="rs" property="name" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							性别代码
						</th>
						<td >
							<bean:write name="rs" property="xbdm" />
						</td>
						<th>
							身份证号
						</th>
						<td>
							<bean:write name="rs" property="id" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							学校代码：
						</th>
						<td>
							<bean:write name="rs" property="xxdm" />
						</td>
						<th>
							学校名称
						</th>
						<td>
							<bean:write name="rs" property="xxmc" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							专业代码
						</th>
						<td align="left">
							<bean:write name="rs" property="zydm" />
						</td>
						<th>
							专业名称
						</th>
						<td>
							<bean:write name="rs" property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							学制代码：
						</th>
						<td>
							<bean:write name="rs" property="xzdm" />
						</td>
						<th>
							学历代码
						</th>
						<td align="left">
							<bean:write name="rs" property="xldm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							培养方式代码
						</th>
						<td align="left">
							<bean:write name="rs" property="pyfsdm" />
						</td>
						<th>
							生源地区代码
						</th>
						<td>
							<bean:write name="rs" property="sydqdm" />
						</td>
					</tr>

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>第二部分</b>
						</td>
					</tr>


					<tr style="height:22px">
						<th>
							毕业去向代码
						</th>
						<td>
							<bean:write name="rs" property="byqxdm" />
						</td>
						<th>
							生源地区名称
						</th>
						<td>
							<bean:write name="rs" property="sydq" />
						</td>

					</tr>
					<tr style="height:22px">
						<th>
							信息登记号
						</th>
						<td>
							<bean:write name="rs" property="xxdjh" />
						</td>
						<th>
							组织机构代码
						</th>
						<td>
							<bean:write name="rs" property="zzjgdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							单位名称
						</th>
						<td>
							<bean:write name="rs" property="dwmc" />
						</td>
						<th>
							政治面貌代码
						</th>
						<td>
							<bean:write name="rs" property="zzmmdm" />
						</td>

					</tr>
					<tr style="height:22px">
						<th>
							单位地区名称
						</th>
						<td>
							<bean:write name="rs" property="dwdq" />
						</td>
						<th>
							主管部门名称
						</th>
						<td>
							<bean:write name="rs" property="zgbm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							单位地区代码
						</th>
						<td>
							<bean:write name="rs" property="dwdqdm" />
						</td>
						<th>
							主管部门代码
						</th>
						<td>
							<bean:write name="rs" property="zgbmdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							未就业标志
						</th>
						<td align="left">
							<bean:write name="rs" property="wjybz" />
						</td>
						<th>
							自定义1
						</th>
						<td>
							<bean:write name="rs" property="bz1" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							未就业原因
						</th>
						<td align="left">
							<bean:write name="rs" property="wjyyy" />
						</td>
						<th>
							自定义2
						</th>
						<td>
							<bean:write name="rs" property="bz2" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							联系地址
						</th>
						<td align="left">
							<bean:write name="rs" property="lxdz" />
						</td>
						<th>
							自定义3
						</th>
						<td>
							<bean:write name="rs" property="bz3" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							邮编
						</th>
						<td>
							<bean:write name="rs" property="yb" />
						</td>
						<th>
							自定义4
						</th>
						<td align="left">
							<bean:write name="rs" property="bz4" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							电话
						</th>
						<td align="left">
							<bean:write name="rs" property="dh" />
						</td>
						<td>
							自定义5
						</td>
						<td>
							<bean:write name="rs" property="bz5" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							居住证或蓝表标志位
						</th>
						<td>
							<bean:write name="rs" property="jzzhlbbzwdm" />
						</td>
						<th>
							
						</th>
						<td align="left">
							
						</td>
						
					</tr>
					<tr style="height:22px">
						<th>
							生源地主管单位
						</th>
						<td>
							<bean:write name="rs" property="sydzgbm" />
						</td>
						<th>
							单位性质代码
						</th>
						<td>
							<bean:write name="rs" property="dwxzdm" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							蓝表批准文号
						</th>
						<td>
							<bean:write name="rs" property="blueno" />
						</td>
						<th>
							备注
						</th>
						<td>
							<bean:write name="rs" property="memo" />
						</td>
					</tr>

					<tr style="height:22px">
						<td colspan="4" align="center" bgcolor="DOEOEE">
							<b>第三部分</b>
						</td>
					</tr>


					<tr style="height:22px">
						<th>
							单位性质代码2
						</th>
						<td>
							<bean:write name="rs" property="dwxzdm2" />
						</td>
						<th>
							档案接收地
						</th>
						<td>
							<bean:write name="rs" property="dajsd" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							单位地址
						</th>
						<td >
							<bean:write name="rs" property="dwdz" />
						</td>
						<th>
							档案接收地地址
						</th>
						<td>
							<bean:write name="rs" property="dajsddz" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							单位电话
						</th>
						<td align="left">
							<bean:write name="rs" property="dwdh" />
						</td>
						<th>
							档案接收地邮编
						</th>
						<td>
							<bean:write name="rs" property="dajsdyb" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							单位邮编
						</th>
						<td>
							<bean:write name="rs" property="dwyb" />
						</td>
						<th>
							地区流向
						</th>
						<td>
							<bean:write name="rs" property="dqlx" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							违约金
						</th>
						<td >
							<bean:write name="rs" property="wyj" />
						</td>
						<th>
							行业分类
						</th>
						<td align="left">
							<bean:write name="rs" property="hyfl" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							第一年月平均工资
						</th>
						<td>
							<bean:write name="rs" property="dynypjgz" />
						</td>
						<th>
							专业对口
						</th>
						<td>
							<bean:write name="rs" property="zydk" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							报道开始时间
						</th>
						<td align="left">
								<html:text name="rs" readonly="true" property="bdkssj" maxlength="60" style="width: 210px;"></html:text>
						</td>
						<th>
							报道结束时间
						</th>
						<td align="left">
							<html:text name="rs" readonly="true" property="bdjssj" maxlength="60" style="width: 210px;"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							报道证号
						</th>
						<td align="left">
							<html:text name="rs" readonly="true" property="bdzh" style="width: 210px;" maxlength="60"></html:text>
						</td>
						<th>
						</th>
						<td>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
						
						<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />审核结果
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							辅导员审核结果
						</logic:notEqual>
						</th>
						<td>
							<html:text name="rs" property="fdysh" style="width=100%"
								readonly="true" />
						</td>
						<th align="right">
							学校审核
						</th>
						<td >
							<html:select name="rs" property="xxsh" onchange="">
								<html:option value="未审核">未审核</html:option>
								<html:option value="未通过X">未通过X</html:option>
								<html:option value="已通过√">已通过√</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
								<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />审核人
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							辅导员审核
							</logic:notEqual>
						</th>
						<td>
							<html:text name="rs" property="fdyshren" style="width=100%"
								readonly="true" />
						</td>
						<th>
							学校审核人
						</th>
						<td align="left">
							<html:text name="rs" property="xxshren" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
						<logic:equal value="12061" name="xxdm" scope="session">
							<bean:message key="lable.xsgzyxpzxy" />审核时间
						</logic:equal>
						<logic:notEqual value="12061" name="xxdm" scope="session">
							辅导员审核时间
						</logic:notEqual>
						</th>
						<td align="left">
							<html:text name="rs" property="fdyshsj" style="width=100%"
								readonly="true" />
						</td>
						<th>
							学校审核时间
						</th>
						<td align="left">
							<html:text name="rs" property="xxshsj" style="width=100%"
								readonly="true" />
						</td>
					</tr>
					<tr id="btgtr">
						<th>
							不通过原因
							<br>
							及修改意见
						</th>
						<td colspan="3">
							<html:textarea name="rs" property="btgyy" rows="4"
								style="width:99%" />
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
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button name="保存" onclick="shgo()" id="buttonSave">
										保 存
									</button>
									<button name="关闭" onclick="Close();return false;" id="buttonClose">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				</div>
		</html:form>
		<logic:notEmpty name="shenhe">
			<logic:equal name="shenhe" value="ok">
				<script>
                    alert("操作成功！");
					if(opener){
				 		opener.document.getElementById('query_go').onclick();
				 	}
					Close();
               </script>
			</logic:equal>
			<logic:equal name="shenhe" value="no">
				<script>
                    alert("操作失败！");
                    if(opener){
				 		opener.document.getElementById('query_go').onclick();
				 	}
					Close();
                </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>

