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
					<span>�뵳����������Ϣ</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>ѧ��</th>
				<td >
						${jjfz.xh}
				</td>
				<th>����</th>
				<td ><span id="jjfzxm">${jjfz.xm}</span></td>
			</tr>
			<tr>
				<th width="20%">�Ա�</th>
				<td width="30%"><span id="jjfzxb">${jjfz.xb}</span></td>
				<th width="20%">����</th>
				<td width="30%"><span id="jjfzssld"></span><span id="jjfzssbh">${jjfz.ssld}${jjfz.ssbh}</span></td>
			</tr>
			<tr>
				<th width="20%">��ϵ�̻�</th>
				<td width="30%"><span id="jjfzlxdh">${jjfz.lxdh}</span></td>
				<th width="20%">�ֻ�����</th>
				<td width="30%"><span id="jjfzsjhm">${jjfz.sjhm}</span></td>
			</tr>
			</tbody>
			<thead>
			<tr>
				<th colspan="4">
							<span>�������
							</span>
				</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<th>ѧ��</th>
				<td >
						${bfr.xh}
				</td>
				<th>����</th>
				<td ><span id="bfrxm">${bfr.xm}</span></td>
			</tr>
			<tr>
				<th width="20%">�Ա�</th>
				<td  width="30%"><span id="bfrxb">${bfr.xb}</span>
				</td>
				<th width="20%">��������</th>
				<td width="30%"><span id="bfrcsrq">${bfr.csrq}</span></td>
			</tr>
			<tr>
				<th width="20%">��ϵ�̻�</th>
				<td  width="30%"><span id="bfrlxdh">${bfr.lxdh}</span>
				</td>
				<th width="20%">�ֻ�����</th>
				<td width="30%"><span id="bfrsjhm">${bfr.sjhm}</span></td>
			</tr>
			<tr>
				<th width="20%">QQ����</th>
				<td width="30%"><span id="bfrqq">${bfr.qqhm}</span>
				</td>
				<th width="20%">����¥��</th>
				<td width="30%"><span id="bfrssbh">${bfr.ssld}${bfr.ssbh}</span>
				</td>
			</tr>
			<tr>
				<th width="20%">������</th>
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
					<span>�뵳�������Ӱ������Ҫ��ʵ</span>
				</th>
			</tr>
			</thead>
			<logic:iterate name="zyssList" id="zyss" indexId="s" >
				<tbody >
				<tr><th rowspan='4'width='10%'>${s+1}�����&nbsp;&nbsp;&nbsp;&nbsp;</th>
					<th width='10%'>ʱ��</th>
					<td width='20%'>${zyss.sssj}</td>
					<th  width='10%'>�ص�</th>
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
					<span>�뵳�������ӾͰ����������֯�㱨��¼</span>
				</th>
			</tr>
			</thead>

			<logic:iterate name="hbList" id="hb" indexId="s"  >
				<tbody ><tr><th rowspan='4'width='10%'>${s+1}���㱨&nbsp;&nbsp;&nbsp;&nbsp;</th>
					<th width='10%'>ʱ��</th>
					<td width='20%'>${hb.sssj}</td>
					<th  width='10%'>�ص�</th>
					<td  width='20%'>${hb.ssdd}</td>
				</tr>
				<tr rowspan='2'><td colspan='4' ><textarea  style="width: 100%;height: 85px" rows="4" cols="4"  readonly="readonly"  onblur="checkLen(this,500);"
															maxlength="500">${hb.ssnr}</textarea></td>
				</tr><tr><td colspan='4' >
					�㱨��ȡ�ˣ�${hb.hbtqr}
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
							�ر�
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