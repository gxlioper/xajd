<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/jqlxcqsx/lxmdwh/js/lxmdwh.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form action="/jqlx_lxmdwh" method="post" styleId="LxmdwhForm">
			<div style="tab;overflow-x:hidden;overflow-y:hidden;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��У��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/jqlxcqsx/lxmdwh/lxxmxx.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Уѧ��ѡ�� &nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="addRowDialog()">����ѧ��</button>
								&nbsp;&nbsp;
								<button type="button" onclick="delRow()">ɾ��ѧ��</button></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" width="100%">
								<table id="autotable" width="100%" >
									<tr id="autohead"  >
										<th width="10%" style="text-align:center"><input type="checkbox" onclick="selectAll(this);" name="chkall"/></th>
										<th width="15%" style="text-align:center">ѧ��</th>
										<th width="15%" style="text-align:center">����</th>
										<th width="15%" style="text-align:center">�꼶</th>
										<th width="15%" style="text-align:center">ѧԺ</th>
										<th width="15%" style="text-align:center">רҵ</th>
										<th width="15%" style="text-align:center">�༶</th>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th><font class='red'>*</font>��У���˵��<br/><font class='red'>(��������500��)</font></th>
							<td colspan = "3">
								<html:textarea property="lxqksm" styleId="lxqksm" onblur="checkLen(this,500)" style="width:99%" rows="5" />
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
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="savePlwh();">
										����
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