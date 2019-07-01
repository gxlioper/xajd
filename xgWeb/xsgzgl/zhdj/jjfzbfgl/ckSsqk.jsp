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
	<input type="hidden" id="zyssNum" name="zyssNum" value="${zyssNum}"/>
	<input type="hidden" id="hbNum" name="hbNum" value="${hbNum}"/>

	<div style="taboverflow-x:hidden;overflow-y:auto;margin-bottom:20px;" >
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
				<th>学号</th>
				<td >
						${jjfz.xh}
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
				<th>学号</th>
				<td >
						${bfr.xh}
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
		</table>
		<table width="100%" border="0" class="formlist" id="bfss">
			<thead>
			<tr>
				<th colspan="5">
					<span>入党积极分子帮扶的主要事实</span>
				</th>
			</tr>
			</thead>
			<logic:iterate name="zyssList" id="zyss" indexId="s" >
				<tbody >
				<tr><th rowspan='4'width='10%'>${s+1}、帮扶&nbsp;&nbsp;&nbsp;&nbsp;</th>
					<th width='10%'>时间</th>
					<td width='20%'>${zyss.sssj}</td>
					<th  width='10%'>地点</th>
					<td  width='20%'>${zyss.ssdd}</td>
				</tr>
				<tr><td colspan='4' rowspan='3'><textarea  style="width: 100%;height: 100px" rows="4" cols="4"  readonly="readonly"  onblur="checkLen(this,500);"
														   maxlength="500">${zyss.ssnr}</textarea></td>
				</tr>
				</tbody>
			</logic:iterate>
		</table>
		<table width="100%" border="0" class="formlist" id="hbss">
			<thead>
			<tr>
				<th colspan="5">
					<span>入党积极分子就帮扶对象向组织汇报记录</span>
				</th>
			</tr>
			</thead>

			<logic:iterate name="hbList" id="hb" indexId="s"  >
				<tbody ><tr><th rowspan='4'width='10%'>${s+1}、汇报&nbsp;&nbsp;&nbsp;&nbsp;</th>
					<th width='10%'>时间</th>
					<td width='20%'>${hb.sssj}</td>
					<th  width='10%'>地点</th>
					<td  width='20%'>${hb.ssdd}</td>
				</tr>
				<tr rowspan='2'><td colspan='4' ><textarea  style="width: 100%;height: 85px" rows="4" cols="4"  readonly="readonly"  onblur="checkLen(this,500);"
															maxlength="500">${hb.ssnr}</textarea></td>
				</tr><tr><td colspan='4' >
					汇报听取人：${hb.hbtqr}
				</td></tr>
				</tbody>
			</logic:iterate>

		</table>

	</div>
	<div style="height:30px;"></div>
	<%--;height:520px --%>
	<div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			<tr>
				<td colspan="4">

					<div class="btn">
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