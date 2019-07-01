<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript' src="js/qgzx/jfgl/jfgl.js"></script>
		<script type='text/javascript'>
			jQuery(function(){
				changeBm();
			});
		</script>
	</head>
	<body>
		<html:form action="/qgzx_jfgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="bms" id="bms" value="" />
			<input type="hidden" name="hbsjs" id="hbsjs" value="" />
			<input type="hidden" name="hbjes" id="hbjes" value="" />
			<input type="hidden" name="bzs" id="bzs" value="" />
			<input type="hidden" name="qgzq" id="qgzq" value="${qgzq}" />
			<%--<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="">
				<!-- ��ʾ��Ϣ -->
				<div class="prompt" id="promptTs">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						ͬһ�����Ų��ܴ���������ͬ����ʱ��ľ�����Ŀ
					</p>
					<a class="close" title="����"
					   onclick="this.parentNode.style.display='none'"></a>
				</div>
				<!-- ��ʾ��Ϣ end-->	
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>�ڹ���������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<logic:notEqual name="jfhbfs" value="1">
							<th width="10%">
								<font class="red">*</font>���
							</th>
							<td>
								<html:select name="rs" property="nd" styleId="nd" style="width:100px" >
									<html:options collection="ndList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							</logic:notEqual>
							<logic:equal name="jfhbfs" value="1">
								<th width="10%">
									<span class="red">*</span>����
								</th>
								<td>
									<html:text property="nd" styleId="nd" onfocus="return showCalendar(this.id,'yyyy-MM');" maxlength="15"></html:text>
								</td>
							</logic:equal>
							<th width="10%">
								ѧ��
							</th>
							<td>
								<html:select property="xn" styleId="xn" disabled="false" onchange="changeBm()">
									<html:options collection="xnList" property="xn" labelProperty="xn" />
								</html:select>
								<logic:equal value="xq" name="qgzq">
									<html:select styleId="xq"  property="xq" onchange="changeBm()">
										<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
									</html:select>
								</logic:equal>
								<logic:equal value="xn" name="qgzq">
									<html:hidden property="xq" styleId="xq" value=""/>
								</logic:equal>
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td colspan="5">
								<button type="button" onclick="addTr();return false;" >���Ӿ�����</button>
								<button type="button" onclick="delTr();return false;" >ɾ��������</button>
							</td>
						</tr>
					</thead>
				</table>
				</div>
				<div style="width:100%;height:300px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width="3%">
								<input type="checkbox" name="th" onclick="selectAll(this);" />
							</td>
							<td>
								<font class="red">*</font>���˲���
							</td>
							<td>
								<font class="red">*</font>��λ��
							</td>
							<td>
								<font class="red">*</font>��λ��������
							</td>
							<td>
								<font class="red">*</font>����ʱ��
							</td>
							<td>
								<font class="red">*</font>�������
							</td>
							<td>
								��ע
							</td>
						</tr>
						<tbody id="tbody_jfxx">
						</tbody>
					</thead>
				</table>
				</div>
				<table border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="bcZjjfxx();return false;">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			<div style="display: none;">
				<table >
					<tbody id="hidden_jfxx">
						<tr>
							<td ><input type='checkbox'/></td>
							<td >
							<html:select name="rs" property="bm" styleId="bm" disabled="${rs.dis}" onchange="changeTrBmdm(this)">
								<html:options collection="bmList" property="bmdm" labelProperty="bmmc"/>
							</html:select>
							</td>
							<td ><span name="gws"></span></td>
							<td ><span name="xqrs"></span></td>
							<td ><input type='text' id='hbsj!!@@!!id' name='hbsj' style='width:90px' onclick='return showCalendar("hbsj!!@@!!onclick","y-mm-dd");' onblur='dateFormatChg(this)' value="${nowTime}"  readonly='true'/></td>
							<td ><input type='text' id='hbje' name='hbje' size='10' maxlength='10' onblur='checkInputNum(this)'/></td>
							<td><input type='text' id='bz' name='bz' style='width:100px' maxlength='1000'/></td>
						</tr>
					</tbody>
				</table>
			</div>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

