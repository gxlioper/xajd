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
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
							<input type="text" name="rdjjfzxh" value="${jjfz.xh}" id="rdjjfzxh" style="width:120px;" readonly="readonly" class="text_nor">

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
							<input type="text" name="bfdxxh" value="${bfr.xh}" id="bfdxxh" style="width:120px;" readonly="readonly" class="text_nor">
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
					<thead>
					<tr>
						<th colspan="4">
							<span>�����Ҫ��������Ҫ���棨���</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td   colspan="4">
							<textarea  style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhnr" name="bfjhnr" readonly="readonly" onblur="checkLen(this,500);" maxlength="500">${bfjhnr}</textarea>
						</td>
					</tr>
					</tbody>

					<thead>
					<tr>
						<th colspan="4">
							<span>�뵳�������ӵİ���ƻ�����ʩ�����</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td    colspan="4">
							<textarea style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhcs" name="bfjhcs" readonly="readonly"  onblur="checkLen(this,500);" maxlength="500">${bfjhcs}</textarea>
						</td>
					</tr>
					</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>�뵳�������ӵİ�����Ŀ�꣨���</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td   colspan="4">
							<textarea  style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhmb" name="bfjhmb" readonly="readonly"  onblur="checkLen(this,500);" maxlength="500">${bfjhmb}</textarea>
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