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
						<th><font color="red">*</font>ѧ��</th>
						<td >
							<input type="text" name="rdjjfzxh" value="" id="rdjjfzxh" style="width:120px;" readonly="readonly" class="text_nor">
							<button class="btn_01" type="button" onclick="showDialog('��ѡ��һ��ѧ��',800,500,'sgygc_jjfzbfgl.do?method=getJjfz');">ѡ��</button>
						</td>
						<th>����</th>
						<td ><span id="jjfzxm"></span></td>
					</tr>
					<tr>
						<th width="20%">�Ա�</th>
						<td width="30%"><span id="jjfzxb"></span></td>
						<th width="20%">����</th>
						<td width="30%"><span id="jjfzssld"></span><span id="jjfzssbh"></span></td>
					</tr>
					<tr>
						<th width="20%">��ϵ�̻�</th>
						<td width="30%"><span id="jjfzlxdh"></span></td>
						<th width="20%">�ֻ�����</th>
						<td width="30%"><span id="jjfzsjhm"></span></td>
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
						<th><font color="red">*</font>ѧ��</th>
						<td >
							<input type="text" name="bfdxxh" value="" id="bfdxxh" style="width:120px;" readonly="readonly" class="text_nor">
							<button class="btn_01" type="button" onclick="showDialog('��ѡ��һ��ѧ��',800,500,'sgygc_jjfzbfgl.do?method=getBfr');">ѡ��</button>
						</td>
						<th>����</th>
						<td ><span id="bfrxm"></span></td>
					</tr>
					<tr>
						<th width="20%">�Ա�</th>
						<td  width="30%"><span id="bfrxb"></span>
						</td>
						<th width="20%">��������</th>
						<td width="30%"><span id="bfrcsrq"></span></td>
					</tr>
					<tr>
						<th width="20%">��ϵ�̻�</th>
						<td  width="30%"><span id="bfrlxdh"></span>
						</td>
						<th width="20%">�ֻ�����</th>
						<td width="30%"><span id="bfrsjhm"></span></td>
					</tr>
					<tr>
						<th width="20%">QQ����</th>
						<td width="30%"><span id="bfrqq"></span>
						</td>
						<th width="20%">����¥��</th>
						<td width="30%"><span id="bfrssld"></span><span id="bfrssbh"></span>
						</td>
					</tr>
					<tr>
						<th width="20%">������</th>
						<td width="30%"><span id="bfrbzr"></span>
						</td>
						<th width="20%"></th>
						<td width="30%"></td>
					</tr>



					</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>�����Ҫ��������Ҫ���棨���<font color="red">*</font></span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td   colspan="4">
							<textarea  style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhnr" name="bfjhnr" onblur="checkLen(this,500);" maxlength="500"></textarea>
						</td>
					</tr>
					</tbody>

					<thead>
					<tr>
						<th colspan="4">
							<span>�뵳�������ӵİ���ƻ�����ʩ�����<font color="red">*</font></span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td    colspan="4">
							<textarea style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhcs" name="bfjhcs"  onblur="checkLen(this,500);" maxlength="500"></textarea>
						</td>
					</tr>
					</tbody>
					<thead>
					<tr>
						<th colspan="4">
							<span>�뵳�������ӵİ�����Ŀ�꣨���<font color="red">*</font></span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr style="">
						<td   colspan="4">
							<textarea  style="width: 100%;height: 100px" rows="4" cols="4" id="bfjhmb" name="bfjhmb"  onblur="checkLen(this,500);" maxlength="500"></textarea>
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
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveJjfzbf();">
										��    ��
									</button>
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