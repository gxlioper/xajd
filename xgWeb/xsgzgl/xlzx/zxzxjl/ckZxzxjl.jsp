<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/zxzxjl/js/zxzxjlxx.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zxzx_zxzxjl" method="post" styleId="zxzxjlForm" onsubmit="return false;">
		<html:hidden property="xh" styleId="xh"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>						
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="qtxx">
						<tr>
							<th width="20%">
								�Ƿ������Ů
							</th>
							<td width="30%">
								${rs.sfdszn}
							</td>							
							<th>��ͥ���ڵ�</th>
							<td>
								${rs.jtszd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ͥ����״��
							</th>
							<td width="30%">
								${rs.jtjjzk}
							</td>							
							<th>�����Ļ��̶�</th>
							<td>
								${rs.fqwhcd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ĸ���Ļ��̶�
							</th>
							<td width="30%">
								${rs.mqwhcd}
							</td>
							<th>��ĸ�Ļ���״��</th>
							<td>
								${rs.fmhyzk}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��ͥ��Ա���޾���ʷ
							</th>
							<td width="30%">
								${rs.jtjsbs}
							</td>					
							<th>�Լ�ͥ��ϲ���̶�</th>
							<td>
								${rs.jtxhcd}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�Ƿ���ܹ�������ѯ����������
							</th>
							<td width="30%">
								${rs.sfzl}
							</td>							
							<th>�Ǽ�����</th>
							<td>
								${rs.djrq}
							</td>
						</tr>
						<tr>
							<th>
								����ѯ����
							</th>
							<td colspan="3">
								${rs.yzxwt}
							</td>
			      		</tr>
			      		<tr>
						<th>
							��ѯ���ⲹ��
						</th>
						<td colspan="3">
							${rs.wtbc}					
						</td>
					</tr>
					<tr>
						<th>
							��ѯ��������Ԥ�ڽ��
						</th>
						<td colspan="3">
							${rs.zxqw}					
						</td>
					</tr>						
					</tbody>
				 </table>
				 <logic:present name="jlList">
					 <table id="zxjl" width="100%" border="0" class="formlist">				 
						 <thead>
								<tr>
									<th colspan="4">
										<span>��ѯ��¼<a onclick="showjlxx(this);" class="up"
											href="javascript:void(0);"> <font color="blue">���չ��/����</font>
										</a></span>
									</th>
								</tr>
						</thead>
							<tbody id="xgTbody" style="display: none">											
							<logic:iterate name="jlList" id="jl" indexId="s">								
									<tr>
										<th width="20%">���</th>
										<td width="30%">
											${jl.bh}
										</td>
										<th width="20%">��ѯʦ����</th>
										<td width="25%">
											${jl.txrxm}					
										</td>
									</tr>
									<tr>
										<th>��ѯʱ��</th>
										<td>
											${jl.zxsj}
										</td>
										<th>��ѯ�ص�</th>
										<td>
											${jl.zxdd}								
										</td>
									</tr>
									<tr>
										<th>�����߶�����ĳ�������ѯĿ�ļ���ѯʦ������ӡ��</th>
										<td colspan="3">${jl.zxpg}</td>
									</tr>
									<tr>
										<th>��ѯ����</th>											
										<td colspan="3">${jl.zxgc}</td>				
									</tr>
									<tr>
										<th>��ѯ����</th>
										<td colspan="3">${jl.zxfk}</td>
									</tr>
									<tr>
										<th>��ѯ���</th>
										<td colspan="3">${jl.zxth}</td>
									</tr>								
								</logic:iterate>
							</tbody>					
					</logic:present>								
				 </table>
				</div>
			  <div style="height:35px"></div>  
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

