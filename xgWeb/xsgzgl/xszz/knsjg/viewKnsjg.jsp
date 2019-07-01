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
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>困难生认定信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:notEqual value="xn" name="sqzq">
					    <tr>
							<th>学年</th>
							<td>
								${rs.xn }
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc }
							</td>
					    </tr>
					    <logic:equal value="10346" name="xxdm">
							<tr>
								<th>
									<span>
										家庭困难类型
									</span>
								</th>
								<td>
									${rs.jtknlxmc}
								</td>
								<th>
									<span>
										高档消费品类型
									</span>
								</th>
								<td>
									${rs.gdxfplxmc}
								</td>
							</tr>
						</logic:equal>
					    <tr>
							<th>认定档次</th>
							<td>
								${rs.dcmc }
							</td>
							<th>申请时间</th>
							<td>
								${rs.sqsj }
							</td>
					    </tr>
					    <logic:equal value="13871" name="xxdm">
								<th>困难排序</th>
								<td colspan="3">
									${rs.knpx }
								</td>
					    	</logic:equal>
					    <!-- 浙江大学个性化，现在不需要此字段，注释的原因以防以后老师再需要，如在需要吧0改为10335即可，2016年09月30日-孟威-->	
					    <logic:equal value="0" name="xxdm">
						<tr>
							<th>无偿资助金额</th>
							<td colspan="3">
								${rs.ylzd3 }
							</td>
							
						</tr>
						</logic:equal>
			  
			     <!--北京中医药大学--><%--
			      <logic:equal value="10026" name="xxdm">
			       		--%><tr>
							<th>
								认定理由
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
								认定补充理由
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
							<th>学年</th>
							<td>
								${rs.xn }
							</td>
							<th>申请时间</th>
							<td>
								${rs.sqsj }
							</td>
					    </tr>
					    <logic:equal value="10346" name="xxdm">
							<tr>
								<th>
									<span>
										家庭困难类型
									</span>
								</th>
								<td>
									${rs.jtknlxmc}
								</td>
								<th>
									<span>
										高档消费品类型
									</span>
								</th>
								<td>
									${rs.gdxfplxmc}
								</td>
							</tr>
						</logic:equal>
					    <tr>
					    	<logic:notEqual value="13871" name="xxdm">
						    	<th>认定档次</th>
								<td colspan="3">
									${rs.dcmc }
								</td>
					    	</logic:notEqual>
							<logic:equal value="13871" name="xxdm">
						    	<th>认定档次</th>
								<td >
									${rs.dcmc }
								</td>
								<th>困难排序</th>
								<td >
									${rs.knpx }
								</td>
					    	</logic:equal>
					    </tr>
					    <logic:equal value="10742" name="xxdm">
						    <tr>
								<th>申请理由</th>
								<td colspan="3">
									${rs.sqlydm }
								</td>
						    </tr>
					    </logic:equal>
					    <logic:equal value="10277" name="xxdm">
					    <tr>
							<th>困难原因</th>
							<td colspan="3">
								${rs.yymc }
							</td>
					    </tr>
					    </logic:equal>
			     <!--北京中医药大学--><%--
			      <logic:equal value="10026" name="xxdm">
			       		--%><tr>
							<th>
								认定理由
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
								认定补充理由
								<br />
							</th>
							<td colspan="3" style="word-break:break-all;">
								${rs.sqly }
							</td>
			      </tr><%--
			       </logic:equal>
			      --%></logic:equal>	
			      <%-- 中国美术学院个性化  --%>
					<logic:equal name="xxdm" value="10355">
						<th>
							家庭人均年收入
						</th>
						<td colspan="3">
							${rs.jtrjnsr}
						</td>
					</logic:equal>	      
			      <tr>
				  	<th>
						附件信息
					</th>
					<td colspan="3">
						<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
						<html:hidden property="ylzd2" styleId="fjid"/>
						<script type="text/javascript">
						//调用附件 
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
								<span>取消信息</span>
							</th>
						</tr>
					</thead>
					<tbody>					 
							<tr>
								<th>取消人</th>
								<td>
									${knsqxjlrs.czr}	
								</td>
								<th>取消时间</th>	
								<td>
									${knsqxjlrs.czsj}	
								</td>
						    </tr>
						    <tr>
								<th>取消原因</th>
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
								认定理由
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
										关 闭
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

