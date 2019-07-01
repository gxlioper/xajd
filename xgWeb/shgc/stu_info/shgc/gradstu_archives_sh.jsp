<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
	</head>
	<body onload="checkWinType();">
		<html:form action="/data_search" method="post">
		<input type="hidden" name="realTable" id="realTable" value="stu_archives_auditing"/>	
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>学生信息 - 毕业生档案　-　转档审核</a>
				</p>
			</div>
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				 <thead>
    				<tr>
        				<th colspan="6"><span>单个审核</span></th>
        			</tr>
   				 </thead>
   				 <tbody>
				<tr style="height:22px">
					<th align="right">
						学号
					</th>
					<td align="left">
						<bean:write name="rs" property="xh" />
						<input type="hidden" value="<bean:write name="rs" property="xh" />" name="xh" id="xh"/>
					</td>
					<th align="right">
						入校时间
					</th>
					<td align="left">
						<bean:write name="rs" property="rxsj" />						
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						姓名
					</th>
					<td align="left">
						<bean:write name="rs" property="xm" />
					</td>
					<th align="right">
						毕业时间
					</th>
					<td align="left">
						<bean:write name="rs" property="bysj" />						
					</td>
				</tr>				
				<tr style="height:22px">
					<th align="right">
						年级
					</th>
					<td align="left">
						<bean:write name="rs" property="nj" />	
					</td>
					<th align="right">
						户口所属区县
					</th>
					<td align="left">
						<bean:write name="rs" property="hkssqx" />
					</td>
					
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />
					</th>
					<td align="left">
						<bean:write name="rs" property="xymc" />
					</td>
					<th align="right">
						户口所属街道
					</th>
					<td align="left">
						<bean:write name="rs" property="hkssjd" />
					</td>					
				</tr>
				<tr style="height:22px">
					<th align="right">
						专业
					</th>
					<td align="left">
						<bean:write name="rs" property="zymc"/>
					</td>
					<th align="right">
						户口详细地址
					</th>
					<td align="left">
						<bean:write name="rs" property="hkxxdz"/>
					</td>					
				</tr>
				<tr style="height:22px">
					<th align="right">
						班级
					</th>
					<td align="left">
						<bean:write name="rs" property="bjmc"/>
					</td>
					<th align="right">
						联系方式
					</th>
					<td align="left">
						<bean:write name="rs" property="lxfs"/>
					</td>						
					
				</tr>
				<tr style="height:22px">
					<th align="right">
						申请日期
					</th>
					<td align="left">
						<bean:write name="rs" property="sqrq"/>
					</td>
					<th align="right">
						申请理由
					</th>
					<td align="left">
						<bean:write name="rs" property="sqly"/>
					</td>	
				</tr>
				<tr  style="height:22px">
					
					<th align="right">
						审核
					</th>
					<td align="left" colspan="3">
					<html:select property="yesNo" name="rs">
							<html:option value="未审核">未审核</html:option>
							<html:option value="通过">通过</html:option>
							<html:option value="不通过">不通过</html:option>
						</html:select>
					</td>
				</tr>	
				<tr  style="height:22px">					
					<th align="right">
						备注
					</th>
					<td align="left" colspan="3">
					<html:textarea property="bz" name="rs" style="width:100%;height:45px"></html:textarea>
					</td>
				</tr>
				</tbody>
				<tfoot>
				 <tr>
					<td colspan="6"><div class="bz">"<span class="red">*</span>"为必填项</div>
					<div class="btn">
						<logic:equal value="yes" name="writeAble">
						<button 
							onclick="refreshForm('business.do?method=gradArchivesAuding&doType=auditing')"
							 id="buttonSave">
							保 存
						</button>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<button  onclick="Close();return false;" 
							id="buttonClose">
							关 闭
						</button>
						</logic:equal>
					</div>
					</td>
				</tr>
			</tfoot>
			</table>
			</div
		</html:form>
		<logic:equal value="true" name="result">
			<script>
				alert("操作成功");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
