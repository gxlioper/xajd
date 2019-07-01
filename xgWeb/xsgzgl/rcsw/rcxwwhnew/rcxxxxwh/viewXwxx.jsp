<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.id}&tt="+new Date().getTime());
		});
        function yl() {
            var fjmc=jQuery("#fjmc").val();
            var fjlj=jQuery("#fjlj").val();
            var url="rcsw_rcxwwhnew_rcxwjggl.do?method=downloadFile";
            var URL="rcsw_rcxwwhnew_wjyl.do?method=preview&fjlj="+fjlj+"&fjmc="+fjmc+"&url="+url;
            window.open(encodeURI(URL));
        }

		</script>
	</head>
	<body>
		
		<html:form action="/rcsw_rcxwwhnew_rcxwxxwhgl" method="post" styleId="rcxwxxwhForm">
			<html:hidden property="id"  styleId="id"/>
			
			<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwhnew/comm/viewStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>
									<logic:equal value="10704" name="xxdm">
										综合测评记录信息
									</logic:equal>
									<logic:notEqual value="10704" name="xxdm">
										行为记录信息
									</logic:notEqual>
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th>学年</th>
							<td>
								${rs.xn }
							</td>
							<th>学期</th>
							<td>
								${rs.xqmc}
							</td>
					    </tr>
					    <tr>
							<th>
								<logic:equal value="10704" name="xxdm">
									综合测评类别
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									行为类别
								</logic:notEqual>
							</th>
							<td>
								${rs.rcxwlbmc}
							</td>
							<th>
								<logic:equal value="10704" name="xxdm">
									综合测评大类
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									行为大类
								</logic:notEqual>								
							</th>
							<td>
								${rs.rcxwlbdlmc}(${rs.ssxymc})
							</td>
					    </tr>
					    <tr>
					    	<th>
					    		<logic:equal value="10704" name="xxdm">
									综合测评小类
								</logic:equal>
								<logic:notEqual value="10704" name="xxdm">
									行为小类
								</logic:notEqual>					    		
					    	</th>
							<td>
								${rs.rcxwlbxlmc}
							</td>
							<th>
							   	评分说明
							</th>
							<td colspan="3">
								${rs.rcxwlbbz}
							</td>
			      		</tr>
					    <tr>
							<th>评定分值</th>
							<td>${rs.fz }</td>							
							<th >发生时间</th>
							<td >
								${rs.fssj}
							</td>
					    </tr>
					    <tr>
							<th>记录人</th>
							<td>${rs.jlrxm }</td>							
							<th >记录时间</th>
							<td >
								${rs.rcxwjlsj}
							</td>
					    </tr>
					    <logic:equal value="13022" name="xxdm">
					    <tr>

					    	<th>附件</th>
					    	<td colspan="3" id="fileTd">
					    		<logic:notEmpty name="rs" property="fjlj">
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">下载</a>&nbsp;${rs.fjmc}
					    		</logic:notEmpty>
					    	</td>
					    </tr>
					    </logic:equal>
					    <logic:equal value="12942" name="xxdm">
					    <tr>

					    	<th>附件</th>
					    	<td colspan="3" id="fileTd">
					    		<logic:notEmpty name="rs" property="fjlj">
					    			<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwxxwhgl.do?method=downloadFile&id=${rs.id }');return false;" class="name">下载</a>&nbsp;${rs.fjmc}
					    		</logic:notEmpty>
					    	</td>
					    </tr>
					    </logic:equal>
						<logic:equal value="10699" name="xxdm">
							<tr>

								<th>附件</th>
								<td colspan="3" id="fileTd">
									<input type="hidden" value="${rs.fjmc}" id="fjmc"/>
									<input type="hidden" value="${rs.fjlj}" id="fjlj"/>
									<logic:notEmpty name="rs" property="fjlj">
										<a href="javascript:void(0);" id="yl" onclick="yl();return false;" class="name">预览</a>
										<a href="javascript:void(0);" onclick="window.open('rcsw_rcxwwhnew_rcxwjggl.do?method=downloadFile&id=${rs.id }');return false;" class="name">下载</a>&nbsp;${rs.fjmc }
									</logic:notEmpty>
								</td>
							</tr>
						</logic:equal>
					    <tr>
							<th>
							   	给分理由
							</th>
							<td colspan="3">
								${rs.gfly}
							</td>
			      		</tr>
					    <tr>
							<th>
							   	备注	
							</th>
							<td colspan="3">
								${rs.bz}
							</td>
			      		</tr>
			      		
			      		 <tr>
							<th>
							   	审核状态
							</th>
							<td colspan="3">
								${rs.shztmc}
							</td>
			      		</tr>
					</tbody>
					
					</table>
					
			
					
					
					
				<logic:notEqual value="无需审核" name="shztmc">	
					<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="4">
										<span>审核信息</span>
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
				</logic:notEqual>	
					
			</div>
			<div style="height: 15px"></div>
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
				
			
		</html:form>
	</body>
</html>

