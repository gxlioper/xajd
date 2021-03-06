<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="gygl/sspy/pyjg/js/sspyjg.js"></script>
		<script>
			jQuery(function(){
				loadLdxx();
			});

			jQuery(function() {
				jQuery("#xsList").empty();
				var ldqsxx=jQuery('#lddm').val()+jQuery('#qsh').val();
				var html = "";
				jQuery.post("sspysq.do?method=getCwxx", {
					ldqsxx : ldqsxx
				}, function(data) {
					for(var i =0;i<data.length;i++){
						html+="<tr><td align='center'>"+data[i]["xh"]+"</td><td align='center'>"+data[i]["xm"]+"</td><td align='center'>"+data[i]["xsbjmc"]+"</td><td align='center'>"+data[i]["cwh"]+"</td></tr>";
					}
					jQuery("#xsList").append(html);
				}, 'json');
		});
		</script>
	</head>
	<body>
		<html:form method="post" styleId="sspyjgForm" action="/sspyjg">
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>楼栋寝室信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>楼栋
						</th>
						<td>
							<html:select property="lddm" styleId="lddm" onchange="loadLdxx();">
								<html:option value="">---请选择---</html:option>
								<html:options collection="ldxxList" labelProperty="ldmc" property="lddm"/>
							</html:select>
						</td>
						<th>
							<font color="red">*</font>楼栋层号
						</th>
						<td>
							<html:select property="ch" styleId="ch" onchange="loadQs();">
							</html:select>
						</td>
					</tr>
					<tr>
						<th><font color="red">*</font>寝室号</th>
						<td>
							<html:select property="qsh" styleId="qsh" onchange="loadQsdh();">
							</html:select>
						</td>
						<th>寝室电话：</th>
						<td>
							<input id="qsdh" type="text" disabled="disabled" style="width:90px;"/>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>宿舍成员</span>
						</th>
					</tr>
				</thead>
				<thead>
					<tr>
						<th  width="20%">
							<div align="center" >学号</div>
						</th>
						<th>
							<div align="center" >姓名</div>
						</th>
						<th>
							<div align="center" >班级</div>
						</th>
						<th>
							<div align="center" >入住床位</div>
						</th>
					</tr>
				</thead>
				<tbody id="xsList">
				</tbody>
				<thead>
					<tr>
						<th colspan="4">
							<span>申请评优项目</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<font color="red">*</font>学年
						</th>
						<td>
							<html:select property="xn" styleId="xn" style="width:155px;">
								<html:options collection="xnList" labelProperty="xn" property="xn" />
							</html:select>
						</td>
						<th>
							<font color="red">*</font>学期
						</th>
						<td>
							<html:select property="xq" styleId="xq" style="width:155px;">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>评优项目
						</th>
						<td align="left" colspan="3">
							<html:select property="pyxmdm" styleId="pyxmdm" disabled="false" >
								<html:options collection="pyxmList" property="pyxmdm" labelProperty="pyxmmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>申请理由&nbsp;<br/>
							<font color="red">(限500字)</font>
						</th>
						<td colspan="5">
							<html:textarea rows="4" property="sqly" styleId="sqly" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">备注&nbsp;<br/>
							<font color="red">(限500字)</font>
						</th>
						<td colspan="5">
							<html:textarea rows="4" property="bz" styleId="bz" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							附件信息
						</th>
						<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
							<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
							<script type="text/javascript">
								//调用附件 
								jQuery(function(){
									jQuery.MultiUploader({
										maxcount : 3,
										//后缀
										accept : 'png|gif|jpg|zip|rar|doc|docx',
										//最大文件大小 单位M
										maxsize: 10,
										//存放附件的隐藏域的id
										elementid : 'filepath'
										});
								});
							</script>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div style="height:30px;"></div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
								<button type="button" onclick="sspyjgSave();">
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