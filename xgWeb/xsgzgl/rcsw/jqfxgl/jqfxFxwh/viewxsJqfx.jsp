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

		<html:form action="/rcsw_jqfxFxwh" method="post">
			
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
						<tr>
							<th colspan="5" >
								<span>��У��Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>					
						<tr>
							<th  width="16%">
								<font color="red"></font>��У״̬
							</th>
							<td   width="34%">										
								${wfxxsrs.fxztmc}
							</td>
							<logic:equal value="0" name="fxzt">	
		       					<th  width="16%">
						        	<font color="red"></font>δ��Уԭ��
						       	</th>					       				       				       	
						     	<td  width="34%">					     	    	   
						     	     ${wfxxsrs.wfxyymc}					     	        
						       	</td>				       	
				   			</logic:equal>  
				   			<logic:equal value="1" name="fxzt">		
		       					<th  width="16%">
						        	<font color="red"></font>��Уʱ��
						       	</th>
						    				       				       				       	
						     	<td  width="34%">					     	    	   
						     	     ${fxsj}					     	        
						       	</td>				       	
				   			</logic:equal>  
						</tr>
						<tr>
							<logic:equal value="��" name="sfqdlx">	
								<th >
									�Ƿ�ȡ����ϵ
								</th>
								<td >										
									${wfxxsrs.sfqdlx}
								</td>
		       					<th  width="16%">
						        	Ԥ�Ʒ�У����
						       	</th>					       				       				       	
						     	<td  width="34%">					     	    	   
						     	   ${wfxxsrs.yjfxsj}					     	        
						       	</td>				       	
				   			</logic:equal>  
						</tr>
						<tr>
							<th >
								��ע
							</th>
							<td colspan="3" rowspan="3">										
								${wfxxsrs.bz}
							</td>
						</tr>
					</tbody>
					</table>
							
					
					
				<table width="100%" border="0" class="formlist" style="margin-top: 15px;">
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

