<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/xmwh/js/xmwhJbsz.js"></script>
	</head>
	<body>
		<html:form action="/xszz_xmwh" method="post" styleId="form1">
		<%--<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>--%>
		<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ǰ������ĿΪ��<font color="red">${xmmc} &nbsp;	
					<span id="spztTip" style="display:none;">
					��ǰ��Ŀ����ѧ�����룬����������޸�
					</span>
				</font>
					ֻ�������뿪�ؿ���״̬����������ʱ����Ч��Χ�ڽ������ú󣬲�Ϊ�������á�״̬�������Ϊ��δ���á�״̬
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
				<html:hidden property="xmdm" styleId="xmdm" />
			<html:hidden property="jdkzjb" styleId="jdkzjb" />
			<html:hidden property="rskzjb" styleId="rskzjb" />
			<html:hidden property="knsbddc" styleId="knsbddc" />
			<html:hidden property="sqxn" styleId="sqxn" />
			<html:hidden property="sqxq" styleId="sqxq" />
			<input type="hidden" name="rskzfwOld" id="rskzfwOld" value="${rskzfw}"/>
			<input type="hidden" name="jekzfwOld" id="jekzfwOld" value="${xmwhForm.jekzfw}"/>
			<input type="hidden" name="spzt" id="spzt" value="${spzt}"/>
			<input type="hidden" id="xxdm" value="${xxdm}"/>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ʱ������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">

						<tr>
							<th width="120px">
								<font color="red">*</font>���뿪��
							</th>
							<td colspan="3">	
								<logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<label>
											<html:radio property="sqkg" value="${o.dm}">${o.mc}</html:radio>
										</label>
									</logic:iterate>								
								</logic:present>
							</td>
						</tr>	
						<tr>
							<th>
								��������ʱ��
							</th>
							<td>
								<html:text property="sqkssj" styleId="sqkssj"
								onfocus="showCalendar('sqkssj','ymmdd');" />
								&nbsp;��
								<html:text property="sqjssj" styleId="sqjssj"
								onfocus="showCalendar('sqjssj','ymmdd');" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������
							</th>
							<td>
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
									labelProperty="lcxx" />
								</html:select>
							</td>
						</tr>				
						<logic:equal name="xxdm" value="14008">
						<div>
							<tr>
								<th width="120px">
									<font color="red">*</font>��˿���
								</th>
								<td>
									<logic:present name="onoffList">
										<logic:iterate id="o" name="onoffList">
											<label>
												<html:radio property="shkg" value="${o.dm}">${o.mc}</html:radio>
											</label>
										</logic:iterate>
									</logic:present>
								</td>
							</tr>
							<tr>
								<th>
									�����ֹʱ��
								</th>
								<td>
									<html:text  property="shkssj" styleId="shkssj"   size="10"
										onclick="return showCalendar('shkssj','y-mm-dd',true,'shjssj');" 
										onblur="dateFormatChg(this)" readonly="true"></html:text>
									&nbsp;��
									<html:text  property="shjssj" styleId="shjssj"   size="10"
										onclick="return showCalendar('shjssj','y-mm-dd',false,'shkssj');" 
										onblur="dateFormatChg(this)" readonly="true"></html:text>
								</td>
							</tr>
						</div>
						</logic:equal>					
						<tr>
							<th>
								��������
							</th>
							<td>
							${sqzq}
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��Ŀ��������
							</th>
							<td>
								<html:select  property="pdxn" styleId="pdxn" style="width:90px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
								<html:select onchange="changeJgzq()"  property="pdxq" styleId="pdxq" style="width:90px">
								<html:option value="">ѧ��</html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
				
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">	
						<tr>
							<th width="120px">
								<font color="red">*</font>�������Ƽ���
							</th>
							<td id="rskzjbTd"  colspan="3" >
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>ѧ����������
							</th>
							<td colspan="3">
								<html:select property="pycc" styleId="pycc">
									<html:options collection="pyccList" property="pyccdm"
												  labelProperty="pyccmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>�������Ʒ�Χ
							</th>
							<td  colspan="3" >					
								<html:select property="rskzfw" styleId="rskzfw" style="width:140px">
									<html:option value=""></html:option>
									<html:option value="bj">�༶</html:option>
									<html:option value="njxy">�꼶+<bean:message key="lable.xb" /></html:option>
									<html:option value="njzy">�꼶+רҵ</html:option>
									<html:option value="xy"><bean:message key="lable.xb" /></html:option>
									<html:option value="zy">רҵ</html:option>
									<html:option value="sy">��Ժ</html:option>
								</html:select>
								<html:radio property="xslb" value="1">��У��</html:radio>
								<html:radio property="xslb" value="0">������</html:radio>
							</td>
						</tr>
						<tr>
							<th width="120px">
								<font color="red">*</font>���ѿ��Ƽ���
							</th>
							<td id="jfkzjbTd"  colspan="3" >
							</td>

						</tr>
						<tr>
							<th>
								<font color="red">*</font>�����������
							</th>
							<td  colspan="3" >					
								<html:select property="jgsqzq" styleId="jgsqzq" style="width:140px">
									<html:option value="0">һѧ��</html:option>
									<html:option value="1">һѧ��</html:option>
									<html:option value="2">һ��</html:option>
									<html:option value="3">һ��</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
				<dir style="height: 10px"></dir>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" onclick="iFClose();">
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

