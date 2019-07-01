<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript' src="xsgzgl/jjgl/zcyh/js/jzxx.js"></script>
	</head>
	<body style="width: 100%">
		<html:form action="/jjgl_zcyhgl" method="post" styleId="zcyhForm" onsubmit="return false;">
			<div style='width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>个人信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>家长编号
							</th>
							<td width="30%">${yhm}
								<input type="hidden" name="yhm" value="${yhm}" id="yhm">
							</td>
							<th width="20%">
								<font color="red">*</font>姓名
							</th>
							<td width="30%">
								<html:text property="xm" styleId="xm" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>性别
							</th>
							<td width="30%">
								<html:radio property="xb" value="1" >男</html:radio>
								<html:radio property="xb" value="2" >女</html:radio>
							</td>
							<th width="20%">
								<font color="red">*</font>联系电话
							</th>
							<td width="30%">
								<html:text property="lxdh" styleId="lxdh"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>身份证号
							</th>
							<td width="30%">
								<html:text property="sfzh" styleId="sfzh"></html:text>
							</td>
							<th width="20%">
								工作单位
							</th>
							<td width="30%">
								<html:text property="gzdw" styleId="gzdw"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>家庭住址
							</th>
							<td width="70%" colspan="3">
								<html:text property="jtzz" styleId="jtzz" style="width:95%"></html:text>
							</td>
						</tr>

					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>子女信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<table class="dateline" width="100%">
							<thead>
								<tr>
									<th width="19%"><font color="red">*</font>姓名</th>
									<th width="12%"><font color="red">*</font>性别</th>
									<th width="19%">出生日期</th>
									<th width="25%"><font color="red">*</font>在读学校</th>
									<th width="15%"><font color="red">*</font>年级</th>
									<th width="10%">操作</th>
								</tr>
							</thead>
							<tbody>
								<tr id="tr_znxx0" class="tr_znxx">
									<td><input style="width: 130px;" type="text" name="znxxModelList[0].xm"/></td>
									<td>
										<%--这里直接写死男女是因为之前的表就是这样存的--%>
										<input type="radio" value="男" name="znxxModelList[0].xb" checked/>男
										&nbsp;
										<input type="radio" value="女" name="znxxModelList[0].xb"/>女
									</td>
									<td><input style="width: 130px;" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="znxxModelList[0].csrq"/></td>
									<td><input style="width: 180px;" type="text" name="znxxModelList[0].zdxx"/></td>
									<td><input style="width: 130px;" type="text" name="znxxModelList[0].nj"/></td>
									<td><a href="javascript:void(0)" onclick="scznxx(this)">删除</a></td>
								</tr>
								<tr id="tr_zjznxx" onclick="zjznxx()">
									<td colspan="6" align="center">
										<a href="javascript:void(0);" class="btn_zj">+ 增加</a>
									</td>
								</tr>
							</tbody>
						</table>
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="save();">
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
		</html:form>
	</body>
</html>

