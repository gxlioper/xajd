<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/zyfwgl/zyfwsh/js/zyfwSh.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.hdid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zyfwShForm.splc}&shid=${zyfwShForm.shid}");
			proviceCiyyLocalMain({type:"view",id:"fwddssx",flag:"yxxdz"});
		});
		
	</script>
</head>
<body>
	<html:form action="/xsxx_zyfwgl_sh" method="post" styleId="zyfwShForm">
		<html:hidden  property="fwid" styleId="fwid"/>
		<html:hidden  property="xh" styleId="xh"/>		
		<html:hidden  property="splc" styleId="splc"/>
		<html:hidden  property="shid" styleId="shid"/>
		<html:hidden  property="gwid" styleId="gwid"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
			<table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
			<thead>
				<tr>
					<th colspan="4">
						<span>־Ը����Ǽ���Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th width="20%">
						ѧ��
					</th>
					<td width="30%">
						${zyfwShForm.xn}
					</td>
					</td>
					<th width="20%">ѧ��</th>
					<td width="30%">
						${zyfwShForm.xqmc}
					</td>
				</tr>
				<tr>
					<th width="20%">
						����ʼʱ��
					</th>
					<td width="30%">
						${zyfwShForm.fwkssj}
					</td>
					<th width="20%">
						�������ʱ��
					</th>
					<td width="30%">
						${zyfwShForm.fwjssj}
					</td>
				</tr>
				<tr>
					<th width="20%">
						��֤��
					</th>
					<td width="30%">
						${zyfwShForm.jzr}
					</td>
					<th width="20%">����Сʱ��</th>
					<td width="30%">
						${zyfwShForm.fwxss}
					</td>
				</tr>
				<tr>
					<th width="20%">
						����ص�
					</th>
					<td colspan="3">
						<input type="hidden" id="fwddssx" value="${zyfwShForm.fwddssx}"/>
						${zyfwShForm.fwdd}
					</td>
				</tr>
				<tr>
					<th width="20%" >�������� </th>
					<td colspan="3">
						${zyfwShForm.fwnr}
					</td>
				</tr>
			</tbody>
			<thead>
					<tr>
						<th colspan="4">
							<span>־Ը����������</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>�����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<tr>
						<th >
							��˽��
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">(��200��)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zyfwgl&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveForDgsh();return false;">
									�� ��
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		</html:form>
</body>
</html>
