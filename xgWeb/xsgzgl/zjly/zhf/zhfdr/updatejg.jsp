<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/zhfdr/js/zhfdr.js"></script>
		<script type="text/javascript" src="xsgzgl/zjly/zhf/comm/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			xmmkdmChange();
			jfxmdmChange();
		//	jQuery("#xmmkdm").change();
			jQuery("#jfxmdm").change();
		});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/zjly_zhfdr" method="post" styleId="ZhfDrForm">
			<html:hidden property="id"/>
			<html:hidden property="xh" styleId="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead> 
						<tr>
							<th colspan="4">
								<span>综合素质分信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>模块</th>
							<td>
								<html:select property="xmmkdm" styleId="xmmkdm" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="dxlist" property="xmmkdm" labelProperty="xmmkmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>计分项目</th>
							<td>
								<select name="jfxmdm" id="jfxmdm" style="width:200px" value="${jfxmdm}">
								<%--<html:select property="jfxmdm" styleId="jfxmdm" style="width:200px">--%>
									<option value="" name="" data-fs = ""  data-khyd = ""></option>
									<logic:iterate id="i" name="xxlist">
									<logic:equal value="${xmmkdm}" name="i" property="xmmkdm">
											<logic:equal value="${jfxmdm}" name="i" property="jfxmdm">
												<option value="${i.jfxmdm}" name="${i.xmmkdm}" selected="selected" data-fs = "${i.fs}" data-khyd = "${i.khyd}" >${i.jfxmmc}</option>
											</logic:equal>
											<logic:notEqual value="${jfxmdm}" name="i" property="jfxmdm">
												<option value="${i.jfxmdm}" name="${i.xmmkdm}"  data-fs = "${i.fs}" data-khyd = "${i.khyd}" >${i.jfxmmc}</option>
											</logic:notEqual>
									</logic:equal>
									</logic:iterate>
									<%--<html:options collection="xxlist" property="jfxmdm" labelProperty="jfxmmc" name="xmmkdm"/>--%>
							<%--	</html:select>--%>
								</select>
							</td>
						</tr>
						<tr>
							<th>考核要点</th>
							<td>
								<label id="khyd"></label>
							</td>
							<th>得分</th>
							<td>
								<label id="fs"></label>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>事项说明<font color="red">(限20字)</font></th>
							<td >
								<html:text property="sxsm" styleId="sxsm" maxlength="20" style="width:200px"></html:text>
							</td>
								<th><font color="red">*</font>参与获得时间</th>
							<td>
								<html:text property="cysj" styleId="cysj"
									onfocus="showCalendar('cysj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<th>
								录入人
							</th>
							<td>
								${lrrname}
								<html:hidden property="lrr" styleId="lrr"/>
							</td>
							<th>
								录入时间
							</th>
							<td>
								${lrsj}
								<html:hidden property="lrsj" styleId="lrsj"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				<div style="height:30px;">
					   <select name="jfxmdms" id="jfxmdms" style="width:200px;display:none" >
									<logic:iterate id="i" name="xxlist">
										<option value="${i.jfxmdm}" name="${i.xmmkdm}" data-fs = "${i.fs}" data-khyd = "${i.khyd}" >${i.jfxmmc}</option>
									</logic:iterate>
						</select>
				</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button type="button" onclick="savejg('save');">
										保    存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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