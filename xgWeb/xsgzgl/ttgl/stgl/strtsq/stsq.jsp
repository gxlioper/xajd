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
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
function saveForm(){	
	 var checkids ="sqly-tc";
	 if(!checkNotNull(checkids)){
		showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����!");
		return false;
	 }
	
	var url = "ttgl_strtsq.do?method=stsq&type=save";
	ajaxSubFormWithFun("strtsqForm", url, function(data) {
		if (data["message"] == "����ɹ���") {
			showAlert(data["message"], {}, {
				"clkFun" : function() {
					if (parent.window) {
						refershParent();
					}
				}
			});
		} else {
			showAlert(data["message"]);
		}
	});
}
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/ttgl_strtsq" method="post" styleId="strtsqForm" onsubmit="return false;">
		<input type="hidden" name="jgid" id="jgid" value="${rs.jgid }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">��������</th>
							<td width="30%">
								${rs.stlx }
							</td>
							<th width="20%">����ȫ��</th>
							<td width="30%">
								${rs.stqc }
							</td>
						</tr>
						<tr>
							<th>���ż��</th>
							<td>
								${rs.stjc }
							</td>
							<th>��������</th>
							<td>
								${rs.styx }
							</td>
						</tr>
						<tr>
							<th>ҵ��ָ����λ</th>
							<td>
								${rs.bmmc }
							</td>
							<th>����ָ����ʦ</th>
							<td>
								${rs.zdlsxm }
							</td>
						</tr>
						<tr>
							<th>���Ž���</th>
							<td colspan="3">
								${rs.stjs }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr class="h">
							<th colspan="4">
								<span>���Ÿ�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr class="h">
							<th colspan="7">
								<table width="100%" >
									<thead>
										<tr>
											<th width='10%' style="text-align:center">ѧ��</th>
											<th width='10%' style="text-align:center">����</th>
											<th width='10%' style="text-align:center">��Ժ</th>
											<th width='10%' style="text-align:center">ѧԺ</th>
											<th width='10%' style="text-align:center">רҵ</th>
											<th width='10%' style="text-align:center">�༶</th>
											<th width='10%' style="text-align:center">����</th>
											<th width='10%' style="text-align:center">�绰</th>
										</tr>
									</thead>
									<tbody id="tablebody">
									<logic:iterate id="i" name="fzrxxInfo" indexId="index">
											<tr name='deltr'>
												<td style='text-align:center'><input name='xh' type='hidden'  value='${i.xh}'/><label name = 'xhname'>${i.xh}</label></td>
												<td style='text-align:center'><label name = 'xm'>${i.xm}</label></td>
												<td style='text-align:center'><label name = 'symc'>${i.symc}</label></td>
												<td style='text-align:center'><label name = 'xymc'>${i.xymc}</label></td>
												<td style='text-align:center'><label name = 'zymc'>${i.zymc}</label></td>
												<td style='text-align:center'><label name = 'bjmc'>${i.bjmc}</label></td>
												<td style='text-align:center'><label name = 'fz'>������</label></td>
												<td style='text-align:center'><label name = 'sjhm'>${i.sjhm}</label></td>
											</tr>
									</logic:iterate>
									</tbody>
								</table>
							</th>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">������</th>
							<td width="30%">
								${sqr }
							</td>
							<th width="20%">����ʱ��</th>
							<td width="30%">
								${sqsj }
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��������<br><font color="red">����������200��</font><br/></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" onblur="chLengs(this,200);"
								   style="width:90%;" rows="5"></html:textarea>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�س�<br><font color="red">����������200��</font><br/></th>
							<td colspan="3">
								<html:textarea property="tc" styleId="tc" onblur="chLengs(this,200);"
								   style="width:90%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:50px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�ύ����
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