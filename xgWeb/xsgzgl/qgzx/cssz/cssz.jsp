<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript" src="js/qgzx/cssz/cssz.js"></script>
	<script type="text/javascript" src="js/calendar/calendar.js"></script>
	<script>
		jQuery(function($){
//            $("#div_help").show();
		});
	</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /> </a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					1����𷢷�ʱ���ֹ��ϵͳ��<font color="blue">�Զ��ύ</font>�����Ϣ���ύ�󣨳��ڹ�����Ա�û���<font color="blue">�����ٴ��޸�</font>�����Ϣ��
				</br>2�������������Ƽ���: �趨 <font color="blue">ѧ���ɻ�ø�λ</font>���п��Ƶĸ�λ����
				</br>3�����˵�λ��˼���: �趨���˵�λ���ʱ�����ݷ�Χ�����<font color="blue">��ѡ</font>�������Ϊѧ��<font color="blue">�����λ���ڲ���</font>�Ľ�ʦ�����<font color="blue">����ѡ</font>�������Ϊ<font color="blue">��������ѧ��</font>�Ľ�ʦ(���������ݹ��˿����ѧ��)��
				</br>4���������ݶ� <font color="blue">������</font>��ſ��Ը�������������á�
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/qgzx_jcsz" method="post" styleId="qgzxCsszForm">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<%--<html:hidden property="rskzjb" styleId="rskzjb" value="${rs.rskzjb}"/>--%>
			<%--<html:hidden property="yjrskzjb" styleId="yjrskzjb" value="${rs.yjrskzjb}"/>--%>
			<%--<html:hidden property="qxfw" styleId="qxfw" value="${rs.qxfw}"/>--%>
			<%--<html:hidden property="sqfs" styleId="sqfs" value="${rs.sqfs}"/>--%>
			<%--<input type="hidden" name="cjffsh" id="cjffsh" value="${cjffsh }"/>--%>
			<html:hidden property="yjqxfw" styleId="yjqxfw" value="${rs.yjqxfw}"/>
			<html:hidden property="id" styleId="id" value="${rs.id}"/>
			<!-- ������ end-->
			<div class="tab">

				<table class="formlist" border="0" align="center" style="width: 100%">
					<thead>
						<tr>
							<th colspan="2">
								<span>У�ڵ�λ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>��λ���뿪��
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rs" property="sfkfgwsq" styleId="sfkfgwsq" value="on" onclick="changeGwsq();">��</html:radio>
								<html:radio name="rs" property="sfkfgwsq" styleId="sfkfgwsq" value="off" onclick="changeGwsq();">��</html:radio>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								��λ���뿪��ʱ��
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="gwsqkssj" styleId="gwsqkssj"   size="10"
									onclick="return showCalendar('gwsqkssj','y-mm-dd',true,'gwsqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text name="rs" property="gwsqjssj" styleId="gwsqjssj"   size="10"
									onclick="return showCalendar('gwsqjssj','y-mm-dd',false,'gwsqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
									
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>��λ������������
							</td>
							<td align="left" style="width: 60%">
								<html:select name="rs" property="yrdwsplc"  styleId="yrdwsplc">
									<html:option value=""></html:option>
									<%--<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>--%>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<%--<thead>
					<tr>
						<th colspan="2">
							<span>У�ⵥλ��������</span>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<td align="right" style="width: 35%">
							<font class="red">*</font>��λ���뿪��
						</td>
						<td align="left" style="width: 60%">
							<html:radio name="rs" property="sfkfxwgwsq" styleId="sfkfxwgwsq" value="on" onclick="changeGwsq();">��</html:radio>
							<html:radio name="rs" property="sfkfxwgwsq" styleId="sfkfxwgwsq" value="off" onclick="changeGwsq();">��</html:radio>
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 35%">
							��λ���뿪��ʱ��
						</td>
						<td align="left" style="width: 60%">
							<html:text name="rs" property="xwgwsqkssj" styleId="xwgwsqkssj"   size="10"
									   onclick="return showCalendar('xwgwsqkssj','y-mm-dd',true,'xwgwsqkssj');"
									   onblur="dateFormatChg(this)" readonly="true"></html:text>
							-
							<html:text name="rs" property="xwgwsqjssj" styleId="xwgwsqjssj"   size="10"
									   onclick="return showCalendar('xwgwsqjssj','y-mm-dd',false,'xwgwsqjssj');"
									   onblur="dateFormatChg(this)" readonly="true"></html:text>

						</td>
					</tr>
					<tr>
						<td align="right" style="width: 35%">
							<font class="red">*</font>��λ������������
						</td>
						<td align="left" style="width: 60%">
							<html:select name="rs" property="xwgwsplc"  styleId="xwgwsplc">
								<html:option value=""></html:option>
								&lt;%&ndash;<html:options collection="shlcList" property="splc" labelProperty="lcxx"/>&ndash;%&gt;
								<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
							</html:select>
						</td>
					</tr>
					</tbody>--%>
					<thead>
						<tr>
							<th colspan="2">
								<span>ѧ����λ��������</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>ѧ����λ���뿪��
							</td>
							<td align="left" style="width: 60%">
								<html:radio name="rs" property="sfkfxsgwsq" styleId="sfkfxsgwsq" value="on" onclick="changexsGwsq();">��</html:radio>
								<html:radio name="rs" property="sfkfxsgwsq" styleId="sfkfxsgwsq" value="off" onclick="changexsGwsq();">��</html:radio>
<%--								(ֻ������ֹʱ����ڲ��ܽ�������)  2013.1.6 qlj ÷���˵��ʾ�ظ��� ������ҵ --%> 
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								ѧ����λ���뿪��ʱ��
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="xsgwsqkssj" styleId="xsgwsqkssj"   size="10"
									onclick="return showCalendar('xsgwsqkssj','y-mm-dd',true,'xsgwsqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text name="rs" property="xsgwsqjssj" styleId="xsgwsqjssj"   size="10"
									onclick="return showCalendar('xsgwsqjssj','y-mm-dd',false,'xsgwsqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								���ڸ�ʱ����ѧ�����������λ�����ڹ��첻��Ӱ�죩
							</td>
						</tr>
						<tr id="hdgwpd">
							<td align="right" style="width: 35%">
								ѧ���ɻ�ø�λ��
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="xsgws" styleId="xsgws" onblur="checkInputNum(this)" size="10" maxlength="5"></html:text>
								��ѧ���ɻ���ڹ���λ������ֵ,"0" ��ʾ�����ƣ�
							</td>
						</tr>
						<tr id="sqgwpd">
							<td align="right" style="width: 35%">
								ѧ���������λ��
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="xsxsgws" styleId="xsxsgws" onblur="checkInputNum(this)" size="10" maxlength="5"></html:text>
								��ѧ�����ύ�ڹ���λ���������ֵ,"0" ��ʾ�����ƣ�
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>У�ڸ�λ������������
							</td>
							<td align="left" style="width: 60%">
								<html:select property="xsgwsqsplc" name="rs" styleId="xsgwsqsplc">
									<html:option value=""></html:option>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
						<tr>
						<tr>
							<td align="right">
								���˵�λ��˼���
							</td>
							<td bgcolor="#FFF5EE" id="yjqxfwTd">
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>У���λ������������
							</td>
							<td align="left" style="width: 60%">
								<html:select property="xwxsgwsqsplc" name="rs" styleId="xwxsgwsqsplc">
									<html:option value=""></html:option>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>����ʱ��ά����������
							</td>
							<td align="left" style="width: 60%">
								<html:select property="gzscwhsplc" name="rs" styleId="gzscwhsplc">
									<html:option value=""></html:option>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>ѧ����ְ��������
							</td>
							<td align="left" style="width: 60%">
								<html:select property="xslzsplc" name="rs" styleId="xslzsplc">
									<html:option value=""></html:option>
									<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
								</html:select>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="2">
								<span>��𷢷Ų�������</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr>
						<td align="right" style="width: 35%">
							<font class="red">*</font>�����걨����
						</td>
						<td align="left" style="width: 60%">
							<html:radio name="rs" property="gzsqkg" styleId="gzsqkg" value="on">��</html:radio>
							<html:radio name="rs" property="gzsqkg" styleId="gzsqkg" value="off">��</html:radio>
						</td>
					</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>���ù�������
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="cjsx" styleId="cjsx" onblur="checkInputNum(this)" size="10" maxlength="6"></html:text>
								<font id="font_sxsz">��ѧ��ÿ�¹��ʲ��ó�����ֵ��</font>
							</td>
						</tr>
						<tr>
							<td align="right" style="width: 35%">
								<font class="red">*</font>����ʱ������
							</td>
							<td align="left" style="width: 60%">
								<html:text name="rs" property="gzscsx" styleId="gzscsx" onblur="checkInputNum(this)" size="10" maxlength="6"></html:text>
								<font id="font_sxsz">��ѧ��ÿ�¹���ʱ������40Сʱ��</font>
							</td>
						</tr>
					<tr>
						<td align="right" style="width: 35%">
							�����걨����ʱ��
						</td>
						<td align="left" style="width: 60%">
							<html:text name="rs" property="gzsqkssj" styleId="gzsqkssj"   size="10"
									   onclick="return showCalendar('gzsqkssj','y-mm-dd',true,'gzsqkssj');"
									   onblur="dateFormatChg(this)" readonly="true"></html:text>
							-
							<html:text name="rs" property="gzsqjssj" styleId="gzsqjssj"   size="10"
									   onclick="return showCalendar('gzsqjssj','y-mm-dd',false,'gzsqjssj');"
									   onblur="dateFormatChg(this)" readonly="true"></html:text>
							���ڸ�ʱ���ڵ�λ���ܽ��й����걨�����ڹ��첻��Ӱ�죩
						</td>
					</tr>
					<tr>
						<td align="right" style="width: 35%">
							<font class="red">*</font>�����걨��������
						</td>
						<td align="left" style="width: 60%">
							<html:select property="gzsqsplc" name="rs" styleId="gzsqsplc">
								<html:option value=""></html:option>
								<html:optionsCollection name="splcList" value="splc" label="lcxx"/>
							</html:select>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
								<logic:equal name="writeAble" value="yes">	
									<button type="button" onclick="Save();return false;" id="buttonSave">
										�� ��
									</button>	
								</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>