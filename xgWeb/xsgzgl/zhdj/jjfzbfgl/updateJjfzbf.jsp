<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zhdj/jjfzbfgl/js/jjfzbfgl.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">


		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/sgygc_jjfzbfgl" method="post" styleId="JjfzbfForm">
			<input type="hidden" id="bfid" name="bfid" value="${bfid}"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>入党积极分子信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<th><font color="red">*</font>学号</th>
						<td >
							<input type="text" name="rdjjfzxh" value="${jjfz.xh}" id="rdjjfzxh" style="width:120px;" readonly="readonly" class="text_nor">

						</td>
						<th>姓名</th>
						<td ><span id="jjfzxm">${jjfz.xm}</span></td>
					</tr>
					<tr>
						<th width="20%">性别</th>
						<td width="30%"><span id="jjfzxb">${jjfz.xb}</span></td>
						<th width="20%">宿舍</th>
						<td width="30%"><span id="jjfzssld"></span><span id="jjfzssbh">${jjfz.ssld}${jjfz.ssbh}</span></td>
					</tr>
					<tr>
						<th width="20%">联系固话</th>
						<td width="30%"><span id="jjfzlxdh">${jjfz.lxdh}</span></td>
						<th width="20%">手机号码</th>
						<td width="30%"><span id="jjfzsjhm">${jjfz.sjhm}</span></td>
					</tr>
					</tbody>
					<thead>
								<tr>
									<th colspan="4">
							<span>帮扶对象
							</span>
									</th>
								</tr>
					</thead>
					<tbody>
					<tr>
						<th><font color="red">*</font>学号</th>
						<td >
							<input type="text" name="bfdxxh" value="${bfr.xh}" id="bfdxxh" style="width:120px;" readonly="readonly" class="text_nor">
						</td>
						<th>姓名</th>
						<td ><span id="bfrxm">${bfr.xm}</span></td>
					</tr>
					<tr>
						<th width="20%">性别</th>
						<td  width="30%"><span id="bfrxb">${bfr.xb}</span>
						</td>
						<th width="20%">出生年月</th>
						<td width="30%"><span id="bfrcsrq">${bfr.csrq}</span></td>
					</tr>
					<tr>
						<th width="20%">联系固话</th>
						<td  width="30%"><span id="bfrlxdh">${bfr.lxdh}</span>
						</td>
						<th width="20%">手机号码</th>
						<td width="30%"><span id="bfrsjhm">${bfr.sjhm}</span></td>
					</tr>
					<tr>
						<th width="20%">QQ号码</th>
						<td width="30%"><span id="bfrqq">${bfr.qqhm}</span>
						</td>
						<th width="20%">宿舍楼栋</th>
						<td width="30%"><span id="bfrssbh">${bfr.ssld}${bfr.ssbh}</span>
						</td>
					</tr>
					<tr>
						<th width="20%">班主任</th>
						<td width="30%"><span id="bfrbzr">${bfr.bzrxm}</span>
						</td>
						<th width="20%"></th>
						<td width="30%"></td>
					</tr>



					</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>帮扶需要帮助的主要方面（必填）<font color="red">*</font></span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td   colspan="4">
							<textarea  style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhnr" name="bfjhnr" onblur="checkLen(this,500);" maxlength="500">${bfjhnr}</textarea>
						</td>
					</tr>
					</tbody>

					<thead>
					<tr>
						<th colspan="4">
							<span>入党积极分子的帮扶计划、措施（必填）<font color="red">*</font></span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td    colspan="4">
							<textarea style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhcs" name="bfjhcs"  onblur="checkLen(this,500);" maxlength="500">${bfjhcs}</textarea>
						</td>
					</tr>
					</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>入党积极分子的帮扶拟达目标（必填）<font color="red">*</font></span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td   colspan="4">
							<textarea  style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhmb" name="bfjhmb"  onblur="checkLen(this,500);" maxlength="500">${bfjhmb}</textarea>
						</td>
					</tr>
					</tbody>

				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="saveUpdateJjfzbf();">
										保    存
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