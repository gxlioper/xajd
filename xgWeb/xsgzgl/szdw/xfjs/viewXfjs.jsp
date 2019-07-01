<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>

		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xfjs/js/xfjs.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
	<body>
		<html:form action="/szdw_xfjsgl" styleId="xfjsForm" method="post">
			<div class="tab"
				style="overflow-x: hidden; overflow-y: auto; height: 380px; margin-bottom: 0px;">
				<table class="formlist" width="95%">
					<thead>
						<tr style="height: 22px">
							<th colspan="4">
								<span>ѧ�翼�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="16%">
								ѧ��
							</th>
							<td align="left" width="30%" nowrap="nowrap">
								${rs.xn }
							</td>
							<th width="16%">
								ѧ��
							</th>
							<td width="38%">
								${rs.xqmc }
							</td>
						</tr>

						<tr>
							<th width="16%">
								�༶
							</th>
							<td width="34%">
								${rs.bjmc }
							</td>
							<th width="16%">
								¼����
							</th>
							<td width="34%">
								${rs.lrr}
							</td>

						</tr>
						<tr>
							<th width="16%">
								ѧԺ		
							</th>
							<td width="34%" >
								${rs.xymc}
							</td>
							<th width="16%">
								У��		
							</th>
							<td width="34%" >
								${rs.yxmc}
							</td>
						</tr>
						<tr>
						</td>
							<th width="16%">
								ѧ������	
							</th>
							<td width="34%" >
								${rs.pyccmc}
							</td>
							<th width="16%">
								����Ա	
							</th>
							<td width="34%">
								${rs.fdy}
							</td>
						</tr>
						<tr>
							<th width="16%">
								Ӧ��ѧ������
							</th>
							<td width="34%">
								${rs.ydxsrs }
							</td>
							<th width="16%">
								ʵ�ʳ�������
							</th>
							<td width="34%">
								${rs.sjcqrs }
							</td>

						</tr>
						<tr>
							<th width="16%">
								���ʱ��
							</th>
							<td width="34%">
								${rs.jcsj }
							</td>
							<th width="16%">
								���ڴ�
							</th>
							<td width="34%">
								${rs.jcjc }
							</td>
						</tr>
						<tr>
							<th width="16%" rowspan="4">
								��ע
							</th>
							<td width="34%" colspan="3" rowspan="4">
								${rs.bz }
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<table class="formlist" width="97%">
				<tfoot>
				<tr>
			        <td colspan="4">
			          <div class="btn">
			            <button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
				</tfoot>
			</table>

		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }" />
			<script type="text/javascript">
		 showAlert("�����ɹ�",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		</script>
		</logic:present>
	</body>
</html>
