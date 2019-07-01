<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zsjgxx.sqid}&tt="+new Date().getTime());
		var jmbl = jQuery("#jmbl").text();
	    jmbl = parseInt(jmbl.replace("%",""))/100;
		var yjjmje = (parseInt(jQuery("#zje").text())*jmbl).toFixed(2);
		jQuery("#yjjmje").text(yjjmje);
	});
	</script>
</head>
<body>
	<html:form action="/xnwxdkjm_sh" method="post" styleId="XnwxdkjmshModel">
		
		<html:hidden  property="sqid" styleId="sqid"/>
		<html:hidden  property="xh" styleId="xh"/>		
		<html:hidden name="zsjgxx" property="splc" styleId="splc"/>
		<html:hidden name="zsjgxx" property="sqsj" styleId="sqsj"/>
			
		<html:hidden name="zsjgxx" property="shid" styleId="shid"/>
	     <%-- 
		<html:hidden  property="cjbz" styleId="cjbz"/>
	        --%>
		<html:hidden name="zsjgxx"  property="xn" styleId="xn"/>
  		<html:hidden name="zsjgxx"  property="xq" styleId="xq"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
		   <table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>学生信息</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
			<thead>
						<tr>
							<th colspan="4">
								<span>申请信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>
								${zsjgxx.xn}
							</td>
							<th>学期</th>
							<td>
							    ${xqmc}
							</td>
						</tr>
						<tr>
							<th>申请减免比例</th>
							<td id = "jmbl">
								${zsjgxx.jmbl}
							</td>
							<th>预计减免金额</th>
							<td>
								<label id="zje" style="display:none">${zje}</label>
								<label id="yjjmje"></label>
							</td>
						</tr>
						<tr>
							<th>减免依据</th>
							<td colspan="3">
							    <table  border="0" cellspacing="0" cellpadding="0">
								    <logic:iterate id="jm"  name="jmyjlist">
								      <tr>
								        <td>
								    	    ${jm.rownum}
								        </td>
								        <td>
								        	${jm.jmyj}
								        </td>
								       </tr>
									</logic:iterate>	
									</table>
							</td>
						</tr>
						<tr>
							<th>申请理由</th>
							<td colspan="3">
								 ${zsjgxx.sqly}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								附件信息
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//调用附件 
									jQuery(function(){
										var gid = "${zsjgxx.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>
					</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>审批信息</span>
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
								<button type="button" name="关闭" id="buttonClose" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- 提示信息 -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
