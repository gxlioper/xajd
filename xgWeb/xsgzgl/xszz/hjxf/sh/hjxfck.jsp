<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/hjxf/js/comm.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${ck.hjxfid}&tt="+new Date().getTime());
		var xh = jQuery(".name").text();
		jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh});
	});
	</script>
</head>
<body>
	<html:form action="/hjxf_sh" method="post" styleId="HjxfShForm">
		
		<html:hidden  property="hjxfid" styleId="hjxfid"/>
		<html:hidden  property="xh" styleId="xh"/>		
		<html:hidden name="ck" property="splcid" styleId="splcid"/>
		<html:hidden name="ck" property="sqsj" styleId="sqsj"/>
			
		<html:hidden name="ck" property="shid" styleId="shid"/>
	     <%-- 
		<html:hidden  property="cjbz" styleId="cjbz"/>
	        --%>
		<html:hidden name="ck"  property="xn" styleId="xn"/>
  		<html:hidden name="ck"  property="xq" styleId="xq"/>
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
								<span>��ͥ��� <logic:notEqual value="" property="xh"
										name="HjxfShForm">
										<a onclick="showJtqk(this,'t_jtqk');" class="up"
											href="javascript:void(0);"> <font color="blue">���չ��/����</font>
										</a>
										|
										<a onclick="editJtqk();" class="btn_xg"
											href="javascript:void(0);"> <font color="blue">�༭��ͥ���</font>
										</a>
									</logic:notEqual> </span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">

								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ʷ���뻺����Ϣ <logic:notEqual value="" property="xh"
										name="HjxfShForm">
										<a onclick="showJtqk(this,'t_lsxx');" class="up"
											href="javascript:void(0);"> <font color="blue">���չ��/����</font>
										</a>
									</logic:notEqual> </span>
							</th>
						</tr>
					</thead>
					<tbody id="t_lsxx" style="display: none;" style="width:100%">
						<tr style="width:100%">
							<td width="100%" colspan="4">
									<table width="100%">
										<tbody id="tbody_lsxx" width="100%">
											<tr>
												<th width='10%'>ѧ��</th>
												<th width='10%'>ѧ��</th>	
												<th width='25%'>����ʱ��</th>
												<th width='20%'>�������(Ԫ)</th>
												<th width='35%'>�����ֹʱ��</th>
											</tr>
											<logic:iterate id="i" name="hjxx">
												<tr width='100%'>
													<td width='10%'>${i.xn}</td>
													<td width='10%'>${i.xq}</td>
													<td width='25%'>${i.sqsj}</td>
													<td width='20%'>${i.hjje}</td>
													<td width='35%'>${i.jqjzsj}</td>
												</tr>
											</logic:iterate>
										</tbody>
									</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѧ�����뻺����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${ck.xn}
								
							</td>
							<th>ѧ��</th>
							<td>
							    ${xqmc}
							</td>
						</tr>
						<tr>
							<th>ƶ���϶��ȼ�</th>
							<td>
								${djmc}
							</td>
							<th></th>
							<td>
							</td>
						</tr>
						<tr>
							<th>�������</th>
							<td>
							   ${ck.dkqk}
							</td>
							<th>����Ƿ�ѽ��(Ԫ)</th>
							<td>
								${ck.wnqfje}
							</td>
						</tr>
						<tr>
							<th>Ӧ�ɽ��(Ԫ)  </th>
							<td>
							    ${ck.yjje}
							</td>
							<th>����ѧ��(Ԫ) </th>
							<td>
								${ck.hjje}
							</td>
						</tr>
						<tr>
							<th>�����ֹʱ�� </th>
							<td id="jqjzsj">
								${ck.jqjzsj}
							</td>
								<th></th>
							<td>
							</td>
						</tr>
						<tr>
							<th>��ͥ����������Ҫ��Դ���������</th>
							</th>
							<td colspan="3">
							${ck.sqyy}
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td colspan="3">
							<html:hidden property="filepath" styleId="filepath" />
						    <%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
                             <script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#filepath').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
							
						</td>
						</tr>
					</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
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
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
