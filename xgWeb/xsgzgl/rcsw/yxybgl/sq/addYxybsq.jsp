<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/yxybgl/sq/js/yxybsq.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/yxybgl_sq" method="post" styleId="yxybsqForm" onsubmit="return false;">
		<html:hidden property="xn" styleId="xn"/>
		<html:hidden property="xq" styleId="xq"/>
		<html:hidden property="txr" styleId="txr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�±���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${xn}
							</td>
							</td>
							<th>ѧ��</th>
							<td>
								${xq}
							</td>
						</tr>
						<tr>
							<th>
								��д��
							</th>
							<td>
								${txrxm}
							</td>
							</td>
							<th><span class="red">*</span>�·�</th>
							<td>
								
								<html:select property="yf" styleId="yf" style="width:200px">
									<html:option value=""></html:option>
									<html:options name="yfList"/>
								</html:select>
							</td>
						</tr>
						<tr>
					    	<th><span class="red">*</span>ѧԺ</th>
					    	<td>
								<html:select property="xydm" styleId="xydm" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xyList" labelProperty="xymc" property="xydm"/>
								</html:select>
							</td>							
						</tr>
								<tr>
									<th><span><font color="red">*</font></span>
										���¹�����չ���
										<br /><font color="red">&lt;��2000��&gt;</font>
									</th>
									<td colspan="3">
										<html:textarea property='bygzkzqk' style="width:98%" styleId="bygzkzqk" rows='8' onblur="checkLen(this,2000);"/>
									</td>
								</tr>
						<tr>
							<th><span><font color="red">*</font></span>
								ѧ����ע�ȵ�
								<br /><font color="red">&lt;��2000��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xsgzrd' style="width:98%" styleId="xsgzrd" rows='8' onblur="checkLen(this,2000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th><span><font color="red">*</font></span>
								ѧ��˼�붯̬
								<br /><font color="red">&lt;��2000��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xssxdt' style="width:98%" styleId="xssxdt" rows='8' onblur="checkLen(this,2000);"/>
							</td>
			      		</tr>
			      		<tr>
							<th><span><font color="red">*</font></span>
								ѧ�����󼰹�������
								<br /><font color="red">&lt;��2000��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='xstsjgzjy' style="width:98%" styleId="xstsjgzjy" rows='8' onblur="checkLen(this,2000);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:30px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="saveYxybsq('save');">
										����ݸ�
									</button>
									<button type="button" onclick="saveYxybsq('submit');">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

