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
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/wmqsxsmd/js/wmqsxsmd.js"></script>
		<script type="text/javascript">
		</script>
		<style type="text/css">
		</style>
	</head>
	<body>
		<html:form method="post" styleId="wmqsxsmdForm" action="/gyglnew_wmqsxsmd_12688">
		<html:hidden property="id" styleId="id" />
		<html:hidden property="lrr" styleId="lrr"  />
		<html:hidden property="lrsj" styleId="lrsj" />
			<div	style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td>
								${model.xn }
							</td>
							<th>
								ѧ��
							</th>
							<td >
								${xqmc}
							</td>
						</tr>
						<tr>
							<th>
								¼����
							</th>
							<td >
								${lrrxm}
							</td>
							<th>
								¼��ʱ��
							</th>
							<td >
								${model.lrsj}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>У������
								
							</th>
							<td  colspan="3">
								<html:text property="xjfs" styleId="xjfs"   maxlength="5" onblur="checkInputNum(this);changeFs(this);"/>
								<span class="red">(ʵ�ʷ�����Ϊ���������50%)</span>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>Ժ������
								
							</th>
							<td  colspan="3">
								<html:text property="yjfs" styleId="yjfs"   maxlength="5" onblur="checkInputNum(this);changeFs(this);"/>
								<span class="red">(ʵ�ʷ�����Ϊ���������50%)</span>
							</td>
						</tr>
						<tr>
							<th>
								��ע<br />
								<span class="red">(��500��)</span>	
							</th>
							<td colspan="3">
								<html:textarea property="bz" style="width:94%;height:80px"  styleId="bz" onblur="chLengs(this,500);" ></html:textarea>
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
								</div>
								<div class="btn">
									<button type="button" onclick="save('edit');" id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="iFClose();"  id="buttonClose">
										�� ��
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