<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/wsbz/yy/js/wsbzyy.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			fdmcOnchange();
			zcOnChange();
			dayOnChange();
		})
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/wsbz_yy" method="post" styleId="WsbzYyForm" onsubmit="return false;">
		<input type="hidden" name="flag" id="flag" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>ԤԼ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>�������Ϸֶ�</th>
							<td>
								<html:select property="fddm" styleId="fddm">
									<html:option value=""></html:option>
									<html:options collection="fdmclist" property="fddm" labelProperty="fdmc"/>
								</html:select>
							</td>
							<th>�ලʱ��</th>
							<td>
								<label id="sj"></label>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>ԤԼʱ��</th>
							<td>
								<div style="display: none" id="input">
								<%-- 
										${yyrqday}
									<html:hidden  property="yyrqday" styleId="yyrqday" value="${yyrqday}"/>--%>
									<html:select property="yyrqday" styleId="yyrqday">
									 	<html:options collection="yyrqList" property="yyrq" labelProperty="yyrq"/>
									</html:select>
								</div> 
								<div style="display: none" id="select">
								<%-- 
								<html:text property="yyrqzc" styleId="yyrqzc" value="${zclist}" readonly="true"/>--%>
									<html:select property="yyrqzc" styleId="yyrqzc">
										<html:options collection="yyzcList" property="yyzc" labelProperty="yyzc"/>
									</html:select>
								</div> 
								
							    <html:hidden property="yyrq" styleId="yyrq" />
							</td>
							<th>�ල�ص�</th>
							<td>
								<label id="dd"></label>
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<label id="rs"></label>
							</td>
							<th>��ԤԼ����</th>
							<td>
								<label id="syrs"></label>
							</td>
						</tr>
						<tr >
							<th>����ʱ��</th>
							<td>
								${sqsj}
								<html:hidden property="sqsj" styleId="sqsj" value="${sqsj}"/>
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
						<tr>
							<th>����ְ��</th>
							<td colspan="3">
								<label id="gzzz"></label>
							</td>
						</tr>
						<tr>
							<th>����Ҫ��</th>
							<td colspan="3">
								<label id="fwyq"></label>
							</td>
						</tr>
						<tr>
							<th>ԤԼ����</th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly" 
								   style="width:99%;" rows="2"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveData('add');">
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