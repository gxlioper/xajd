<%@ page language="java" contentType="text/html; charset=GBK" import="java.util.*,xgxt.utils.String.StringUtils"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function save(){
				var bz = jQuery("#bz").val();
				if(bz.length>500){
					showAlert("备注超过500字，请删减！");
					return false;
				}
				var url = "gyglnew_zsxxgl.do?method=saveBzBatchForUpdate";
				ajaxSubFormWithFun("form", url, function(data) {
					 if(data["message"]=="保存成功！"){
						 showAlert(data["message"],{},{"clkFun":function(){
								if (parent.window){
									refershParent();
								}
							}});
					 }else{
						 showAlert(data["message"]);
						}
				});
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="/gyglnew_zsxxgl">
		<input type="hidden" id="pkValues" name="pkValues" value="${pkValue}"/>
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
				<thead>
						<tr>
							<th colspan="4">
								<span>床位信息</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th width="15%">楼栋名称</th><td>${rs.ldmc }</td> <th width="15%">寝室号</th><td>${rs.qsh }</td>
					</tr>
					<tr>
						<th>床位号</th><td>${rs.cwh }</td><th>寝室性别</th><td>${rs.qsxb }</td>
					</tr>
					<tr>
						<th>所属<bean:message key="lable.xsgzyxpzxy" /></th><td>${rs.xymc }</td><th>所属年级</th><td>${rs.nj }</td>
					</tr>
					<tr>
						<th>学号</th><td>${rs.xh }</td><th>姓名</th><td>${rs.xm }</td>
					</tr>
					<tr>
						<th>性别</th><td>${rs.xsxb }</td><th>年级</th><td>${rs.xsnj }</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xb" /></th><td>${rs.xsxymc }</td><th>专业</th><td>${rs.xszymc }</td>
					</tr>
					<tr>
						<th>班级</th><td>${rs.xsbjmc }</td><th>入住时间</th><td>${rs.rzsj }</td>
					</tr>
					<tr>
						<th>入住原因</th><td colspan="3">${rs.rzyymc }</td>
					</tr>
					<tr>
						<th>
							备注<br/>
							<font color="red">(限制在500字内)</font>
						</th>
						<td colspan="3" style="word-break:break-all;width:100%">
							<textarea rows='5' style="width:99%" id="bz" name="bz">${rs.bz }</textarea>
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
								<button type="button"  onclick="save();return false;" id="buttonSave">
									保 存
								</button>
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
