<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>

		<script type="text/javascript">		
		</script>

	</head>
	<body>

		<html:form action="/jlkjxy_jqfxwh" method="post">
			
			<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
				<table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp"%>										
				</table>
				
				<table width="95%" border="0" class="formlist">
					<thead>						
							<th colspan="5">
								<span>��Ԣ��Ϣ</span>
							</th>					
					</thead>	
						
					<tbody>
						<tr>
							<th style="text-align: left">¥�� </th>
							<th style="text-align: left">���</th>
							<th style="text-align: left">���Һ�</th>
							<th style="text-align: left">��λ��</th>
							<th style="text-align: left">���ҵ绰</th>
						</tr>
						<tr>
							<td>${rs.ldmc}</td>
							<td>${rs.ch}</td>
							<td>${rs.qsh}</td>
							<td>${rs.cwh}</td>
							<td>${rs.xm}</td>
						</tr>
					</tbody>
			     </table>	
			     <table width="95%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5" >
								<span>δ��У��Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>					
						<tr>
							<tr>
								<th>
									<font color="red"></font>��У״̬
								</th>
								<td width="80%" colspan="3">										
									${wfxxsrs.fxztmc}
								</td>
							</tr>
						</tr>										
					 <logic:equal value="0" name="fxzt">	
						<tr>
       						<th>
				        		<font color="red"></font>δ��Уԭ��
				       		</th>					       				       				       	
				     	    <td width="80%" colspan="3">					     	    	   
				     	          ${wfxxsrs.wfxyy}					     	        
				       		</td>				       	
				        </tr>
				    </logic:equal>  
				    
					</tbody>
					</table>
				</div>
							
					
					
				<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
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

