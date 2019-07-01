<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<style type="text/css">	
			.demo_data2 {
			   /* border: 1px solid #DEDEDE;*/
				display: inline;
			    float: left;
			    height: 15px;
			    margin: 0px 0px 0;
			    padding: 0px;
			    width: 80px;
			}
		</style>
		<script language="javascript" src="xsgzgl/xlzx/thjl/js/thjlDetail.js"> </script>
	</head>
	<body onload="init();">
		<html:form action="/xlzx_thjl" method="post" styleId="form">
		<!-- <input type="hidden" id="url" name="url" value="xlzx_thjl.do?method=thjlDetail" /> -->
		<input type="hidden" name="zgh" id="zgh" value="${zgh}" />
		<input type="hidden" name="id" id="id" value="${thjlInfo.id}" />
		<input type="hidden" name="knlxdm" id="knlxdm" value="${thjlInfo.knlxdm}" />
		<input type="hidden" name="doType" id="doType" value="${doType}" />
		<input type="hidden" name="path" id="path" value="${path}" />
		<input type="hidden" name="xxdm" id="xxdm" value="${xxdm}" />
			<div style='width:100%; overflow-y:auto;overflow-x:hidden'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr> 
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<logic:equal name="doType" value="view">
						<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
					</logic:equal>
					<logic:notEqual name="doType" value="view">
						<logic:equal name="doType" value="update">
							<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
						</logic:equal>
						<logic:notEqual name="doType" value="update">
							<%@ include file="/xsgzgl/xlzx/tsxx/selectStudent.jsp" %>
						</logic:notEqual>
					</logic:notEqual>
					<thead>
						<tr >
							<th colspan="4">
								<span>谈话教师信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="thjlInfo">
					<tr style="height:10px">
						<th  width="16%">
							谈话老师
						</th>
						<td  width="34%">
							${thjlInfo.jsxm}
						</td>
						<th width="16%">
							职工号
						</th>
						<td width="34%">
							${thjlInfo.zgh}
						</td>
					</tr>
					<tr>
						<th width="16%">
									性别
						</th>
						<td width="34%">
							${thjlInfo.jsxb}
						</td>
						<th width="16%">
									部门
						</th>
						<td width="34%">
							${thjlInfo.jsbmmc}
						</td>
					</tr>
					<thead>
						<tr >
							<th colspan="4">
								<span>谈话记录信息</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th width="16%">
							<logic:notEqual name="doType" value="view"><span class="red">*</span></logic:notEqual>谈话时间
						</th>
						<td width="34%" colspan="3">
							<logic:equal name="doType" value="view">
								${thjlInfo.thsj }
							</logic:equal>
							<logic:notEqual name="doType" value="view">
								<html:text property="thsj" styleId="thsj"  value="${thjlInfo.thsj }" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
							</logic:notEqual>
						</td>
					</tr>
					<tr style="height:100px;">
						<th>
							谈话记录<br/>
							<logic:notEqual name="doType" value="view">
								<logic:notEqual name="xxdm" value="11527">
									<font color="red"><B>(限500字)</B></font>
								</logic:notEqual>
								<logic:equal name="xxdm" value="11527">
									<font color="red"><B>(限2000字)</B></font>
								</logic:equal>
							</logic:notEqual>
						</th>
						<td colspan="3">
						<logic:notEqual name="doType" value="view">
							<logic:notEqual name="xxdm" value="11527">
								<html:textarea  property='thnr' styleId="thnr" value="${thjlInfo.thnr}" style="word-break:break-all;width:99%" onblur="chLeng(this,500);" rows='6' />
							</logic:notEqual>
							<logic:equal name="xxdm" value="11527">
								<html:textarea  property='thnr' styleId="thnr" value="${thjlInfo.thnr}" style="word-break:break-all;width:99%" onblur="chLeng(this,2000);" rows='6' />
							</logic:equal>
						</logic:notEqual>
						<logic:equal name="doType" value="view">
							${thjlInfo.thnr}
						</logic:equal>
						</td>
					</tr>
					<logic:notEqual name="doType" value="view">
						<logic:equal name="xxdm" value="11527">
		   				<tr>
							<th>
								附件信息
							</th>
							<td colspan="3">
								<html:hidden property="fjid" styleId="fjid" value="${thjlInfo.fjid}"/>
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<script type="text/javascript">
											//调用附件 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//后缀
													accept : 'png|gif|jpg|zip|rar|doc|docx|pdf',
													//最大文件大小 单位M
													maxsize: 10,
													//存放附件的隐藏域的id
													elementid : 'fjid'
													});
											});
										</script>
							</td>
						</tr>
					</logic:equal>
					</logic:notEqual>
				</tbody>
			</table>
		</div>	
			<div style="height: 35px;"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="3">
								<logic:notEqual name="doType" value="view"><div class="bz">
								"<span class="red">*</span>"为必填项
							</div></logic:notEqual>
							<div class="btn">
								<button id="buttonSave" onclick="save();return false;">
									保 存
								</button>
								<button onclick="Close();return false;">
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

