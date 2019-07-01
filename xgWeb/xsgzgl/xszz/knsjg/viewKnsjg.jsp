<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsjg/js/updateKnsjg.js"></script>
	</head>
	<body>
		
		<html:form action="/xszz_knsjg" method="post" styleId="knsjgForm">
			<html:hidden property="guid"  styleId="guid"/>
			
			<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>�������϶���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:notEqual value="xn" name="sqzq">
					    <tr>
							<th>ѧ��</th>
							<td>
								${rs.xn }
							</td>
							<th>ѧ��</th>
							<td>
								${rs.xqmc }
							</td>
					    </tr>
					    <logic:equal value="10346" name="xxdm">
							<tr>
								<th>
									<span>
										��ͥ��������
									</span>
								</th>
								<td>
									${rs.jtknlxmc}
								</td>
								<th>
									<span>
										�ߵ�����Ʒ����
									</span>
								</th>
								<td>
									${rs.gdxfplxmc}
								</td>
							</tr>
						</logic:equal>
					    <tr>
							<th>�϶�����</th>
							<td>
								${rs.dcmc }
							</td>
							<th>����ʱ��</th>
							<td>
								${rs.sqsj }
							</td>
					    </tr>
					    <logic:equal value="13871" name="xxdm">
								<th>��������</th>
								<td colspan="3">
									${rs.knpx }
								</td>
					    	</logic:equal>
					    <!-- �㽭��ѧ���Ի������ڲ���Ҫ���ֶΣ�ע�͵�ԭ���Է��Ժ���ʦ����Ҫ��������Ҫ��0��Ϊ10335���ɣ�2016��09��30��-����-->	
					    <logic:equal value="0" name="xxdm">
						<tr>
							<th>�޳��������</th>
							<td colspan="3">
								${rs.ylzd3 }
							</td>
							
						</tr>
						</logic:equal>
			  
			     <!--������ҽҩ��ѧ--><%--
			      <logic:equal value="10026" name="xxdm">
			       		--%><tr>
							<th>
								�϶�����
								<br />
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.sqly }
							</td>
			      </tr><%--
			       </logic:equal>
			      <logic:equal value="10026" name="xxdm">
			       		--%><tr>
							<th>
								�϶���������
								<br />
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.ylzd5 }
							</td>
			      </tr><%--
			       </logic:equal>
			      --%></logic:notEqual>
			      <logic:equal value="xn" name="sqzq">
					    <tr>
							<th>ѧ��</th>
							<td>
								${rs.xn }
							</td>
							<th>����ʱ��</th>
							<td>
								${rs.sqsj }
							</td>
					    </tr>
					    <logic:equal value="10346" name="xxdm">
							<tr>
								<th>
									<span>
										��ͥ��������
									</span>
								</th>
								<td>
									${rs.jtknlxmc}
								</td>
								<th>
									<span>
										�ߵ�����Ʒ����
									</span>
								</th>
								<td>
									${rs.gdxfplxmc}
								</td>
							</tr>
						</logic:equal>
					    <tr>
					    	<logic:notEqual value="13871" name="xxdm">
						    	<th>�϶�����</th>
								<td colspan="3">
									${rs.dcmc }
								</td>
					    	</logic:notEqual>
							<logic:equal value="13871" name="xxdm">
						    	<th>�϶�����</th>
								<td >
									${rs.dcmc }
								</td>
								<th>��������</th>
								<td >
									${rs.knpx }
								</td>
					    	</logic:equal>
					    </tr>
					    <logic:equal value="10742" name="xxdm">
						    <tr>
								<th>��������</th>
								<td colspan="3">
									${rs.sqlydm }
								</td>
						    </tr>
					    </logic:equal>
					    <logic:equal value="10277" name="xxdm">
					    <tr>
							<th>����ԭ��</th>
							<td colspan="3">
								${rs.yymc }
							</td>
					    </tr>
					    </logic:equal>
			     <!--������ҽҩ��ѧ--><%--
			      <logic:equal value="10026" name="xxdm">
			       		--%><tr>
							<th>
								�϶�����
								<br />
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.ylzd5 }
							</td>
			      </tr><%--
			       </logic:equal>
			      <logic:equal value="10026" name="xxdm">
			       		--%><tr>
							<th>
								�϶���������
								<br />
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.sqly }
							</td>
			      </tr><%--
			       </logic:equal>
			      --%></logic:equal>	
			      <%-- �й�����ѧԺ���Ի�  --%>
					<logic:equal name="xxdm" value="10355">
						<th>
							��ͥ�˾�������
						</th>
						<td colspan="3">
							${rs.jtrjnsr}
						</td>
					</logic:equal>	      
			      <tr>
				  	<th>
						������Ϣ
					</th>
					<td colspan="3">
						<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
						<html:hidden property="ylzd2" styleId="fjid"/>
						<script type="text/javascript">
						//���ø��� 
							jQuery(function(){
								var gid = "${rs.ylzd2}";
								jQuery.MultiUploader_q({
									gid : gid
								});
							});
						</script>
					</td>
				  </tr>      	     
			      <logic:equal value="10335" name="xxdm">
			      <logic:notEmpty name="knsqxjlrs">
			      	<thead>
						<tr>
							<th colspan="4">
								<span>ȡ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>					 
							<tr>
								<th>ȡ����</th>
								<td>
									${knsqxjlrs.czr}	
								</td>
								<th>ȡ��ʱ��</th>	
								<td>
									${knsqxjlrs.czsj}	
								</td>
						    </tr>
						    <tr>
								<th>ȡ��ԭ��</th>
								<td colspan="3">
									${knsqxjlrs.qxyy}	
								</td>
						    </tr>							    						 			 			      		
				   </tbody>
				   </logic:notEmpty>
			      </logic:equal><%--
			          <logic:notEqual name="xxdm" value="10026">
					    	<tr>
							<th>
								�϶�����
								<br />
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.sqly }
							</td>
			      </tr>
			      		</logic:notEqual>
			      
					--%></tbody>
				</table>
			</div>
			<div>		
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								
								<div class="btn">
									
									<button type="button" type="button" onclick="iFClose();">
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

