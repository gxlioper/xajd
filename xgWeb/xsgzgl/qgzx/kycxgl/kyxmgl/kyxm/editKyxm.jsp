<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/kycxgl/kyxmgl/kyxm/js/kyxm.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery("#xh").attr({readonly:"readonly"});
		});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/qgzx_kycxkyxmgl" method="post" styleId="KyxmglForm" onsubmit="return false;">
		<html:hidden property="xmid" styleId="xmid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>科研项目</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
						    <th width="20%">
								<font color="red">*</font>项目负责人学号
							</th>
							<td width="30%">
								<html:text property="xh" styleId="xh" maxlength="50"></html:text>
								<button class="btn_01" onclick="showDialog('请选择一个学生',800,500,'qgzx_kycxkyxmgl.do?method=showStudents')" type="button" >选择</button>
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%" name='xsxm'>
							${KyxmglForm.xm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								年级
							</th>
							<td width="30%" name='xsnj'>
							${KyxmglForm.nj}
							</td>
							
							<th width="20%">
								学院
							</th>
							<td width="30%" name='xsxy'>
							${KyxmglForm.xymc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>项目名称
							</th>
							<td width="30%">
								<html:text property="xmmc" styleId="xmmc" maxlength="50"></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>项目编号
							</th>
							<td width="30%">
								<html:text property="xmbh" styleId="xmbh" maxlength="10"></html:text>
							</td>
							
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>项目属性
							</th>
							<td width="30%">
								<html:select property="xmsxdm" styleId="xmsxdm" style="width:130px">
									<html:options collection="xmsxList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
							
							
							<th width="20%">
								<font color="red">*</font>项目所属单位
							</th>
							<td width="30%">
								<html:select property="xmssdw" styleId="xmssdw" style="width:150px">
									<html:options collection="bmList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						
						</tr>
							<tr>
								<th width="20%">
								<font color="red">*</font>依托实验室
							</th>
							<td width="30%">
								<html:text property="ytsys" styleId="ytsys" maxlength="100"></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>研究周期
							</th>
							<td width="30%" >
							<html:text property="yjzq" styleId="yjzq" maxlength="10"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>项目开始时间
							</th>
							<td width="30%">
								<html:text property="kssj"
									onfocus="showCalendar('kssj','yyyyMMdd',true,'jssj');" styleId="kssj" ></html:text>
							</td>
							<th width="20%">
								<font color="red">*</font>项目结束时间
							</th>
							<td width="30%">
								<html:text property="jssj"
									onfocus="showCalendar('jssj','yyyyMMdd',false,'kssj');" styleId="jssj" ></html:text>
							</td>																		
						</tr>
					
					</tbody>
				 </table>
				 </div>
			  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="saveKyxm('update');">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
									</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

