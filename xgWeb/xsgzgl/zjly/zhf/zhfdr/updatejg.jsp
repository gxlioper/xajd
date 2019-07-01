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
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead> 
						<tr>
							<th colspan="4">
								<span>�ۺ����ʷ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>ģ��</th>
							<td>
								<html:select property="xmmkdm" styleId="xmmkdm" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="dxlist" property="xmmkdm" labelProperty="xmmkmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>�Ʒ���Ŀ</th>
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
							<th>����Ҫ��</th>
							<td>
								<label id="khyd"></label>
							</td>
							<th>�÷�</th>
							<td>
								<label id="fs"></label>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>����˵��<font color="red">(��20��)</font></th>
							<td >
								<html:text property="sxsm" styleId="sxsm" maxlength="20" style="width:200px"></html:text>
							</td>
								<th><font color="red">*</font>������ʱ��</th>
							<td>
								<html:text property="cysj" styleId="cysj"
									onfocus="showCalendar('cysj','y-mm-dd');"/>
							</td>
						</tr>
						<tr>
							<th>
								¼����
							</th>
							<td>
								${lrrname}
								<html:hidden property="lrr" styleId="lrr"/>
							</td>
							<th>
								¼��ʱ��
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
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="savejg('save');">
										��    ��
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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