<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${jjzgForm.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${jjzgForm.splcid}&shid=${data.shid}");
		});
	</script>
  </head>
  
  <body>
		<html:form action="/jjgl_jjzg" method="post" styleId="zgshForm">
		
			<div class='tab' style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr style="height: 45px;">
							<th width="15%">ѧ��</th>
							<td width="25%">${jbxx.xh }</td>
							<th width="15%">����</th>
							<td width="25%">${jbxx.xm }</td>
							<td rowspan="3" align="center">
								<img src="xsxx_xsgl.do?method=showPhoto&xh=${jbxx.xh }" width="100" height="120" >
							</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>�Ա�</th>
					    	<td>${jbxx.xb }</td>
					    	<th>�꼶</th>
					    	<td>${jbxx.nj }</td>
					    </tr>
					    <tr style="height: 45px;">
					    	<th>ѧԺ</th>
					    	<td>${jbxx.xymc }</td>
					    	<th>�༶</th>
					    	<td>${jbxx.bjmc }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height: 45px;">
					    	<th>�ó���Ŀ</th>
					    	<td colspan="4">
					    		${jjzgForm.sckmmcs }
					    	</td>
					    </tr>
						<tr style="height: 45px;">
					    	<th>����꼶</th>
					    	<td>
					    		${jjzgForm.jjnjmc }
					    	</td>
					    	<th>��ϵ�绰</th>
					    	<td colspan="2">
					    		${jjzgForm.lxdh }
					    	</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�ҽ̾���</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5">
								<table width="100%">
									<thead>
										<tr>
											<td>
												�ҽ̱��
											</td>
											<td>
												�ҽ�ѧ��
											</td>
											<td>
												��ʼʱ��
											</td>
											<td>
												����ʱ��
											</td>
										</tr>
									</thead>
									<logic:empty name="jjjlList">
					    					<tr>
					    						<td colspan="5" style="text-align:center;">
					    							����!
					    						</td>
					    					</tr>
					    			</logic:empty>
					    			<logic:notEmpty name="jjjlList">
											<logic:iterate id="jjjj" name="jjjlList">
											<tr>
												<td>${jjjj.xqid}</td>
					    						<td>[${jjjj.jjxkmc}]-[${jjjj.jjnjmc}]</td>
					    						<td>${jjjj.kssj}</td>
					    						<td>${jjjj.jssj}</td>
					    						</tr>
					    					</logic:iterate>
										</logic:notEmpty>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="5" id="shlccx">
							
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
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
			</div>
		</html:form>
  </body>
</html>
