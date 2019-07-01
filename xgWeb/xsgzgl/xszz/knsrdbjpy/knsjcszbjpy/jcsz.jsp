<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrdbjpy/knsjcszbjpy/js/jcsz.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
  <body >
	<html:form action="/xszz_knsjcszbjpy" method="post" styleId="jcszForm">
	<html:hidden property="rskzjb" styleId="rskzjb"/>
	<html:hidden property="rskznj" styleId="rskznj"/>
	<input type="hidden" value="${xxdm }" id="xxdm"/>
	<input type="hidden" value="${sqzq }" id="sqzq"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<!-- ����ѧԺ-->
			<logic:equal name="xxdm" value="11354">
				<html:hidden property="sqkg" styleId="sqkg" value="0" />
			</logic:equal>
			<logic:notEqual name="xxdm" value="11354">
			<table width="100%" border="0" class="formlist" id="sq_table">
				<thead>
					<tr>
						<th colspan="2"><span>ʱ������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th><span class="red">*</span>�������϶�����</th>
						<td>
							<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
							</html:select>
							<logic:equal name="sqzq" value="xq">
								<html:select  property="xq" styleId="xq" style="width:130px">
									<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</logic:equal>
						</td>
				    </tr>
					<tr>
						<th width="40%"><span class="red">*</span>���뿪��</th>
						<td>
						   
						   <logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<html:radio property="sqkg" onclick="changeSqkg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
						</td>
					</tr>
					<tr>
						<th>���뿪��ʱ��</th>
						<td>
							<html:text  property="sqkssj" styleId="sqkssj"   size="10"
									onclick="return showCalendar('sqkssj','y-mm-dd',true,'sqjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="sqjssj" styleId="sqjssj"   size="10"
									onclick="return showCalendar('sqjssj','y-mm-dd',false,'sqkssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
									
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>�������</th>
						<td>
							<html:select property="splc" styleId="splc" disabled="false" onchange="initRskzjb()">
							<html:options collection="shlcList" property="splc"
								labelProperty="lcxx" />
						</html:select>
						</td>
					</tr>
				</tbody>
				<thead>
					<tr>
						<th colspan="2"><span>��������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%">�Ƿ���ɼ�ͥ��������������</th>
						<td>
						
						   <logic:present name="kgList">
									<logic:iterate id="o" name="kgList" >
										<html:radio property="sfwcjtdc" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
							
						</td>
					</tr>
					
				</tbody>
				<thead>
					<tr>
						<th colspan="2"><span>��������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%">ѧ���Ƿ��ѡ���ѵ���</th>
						<td>
							<html:radio property="sqsftxdc" value="1">��</html:radio>&nbsp;
							<html:radio property="sqsftxdc" value="0">��</html:radio>
						</td>
					</tr>
					<tr>
						<th width="40%">�����Ƿ����</th>
						<td>
							<html:radio property="rssfkz" value="1">��</html:radio>&nbsp;
							<html:radio property="rssfkz" value="0">��</html:radio>
						</td>
					</tr>
					<tr class="rskzcs" style="display: none">
						<th width="40%">�������Ƽ���</th>
						<td id="rskzjbTd"></td>
					</tr>
					<tr class="rskzcs" style="display: none">
						<th width="40%">�������Ʒ�Χ</th>
						<td>
							<html:select property="rskzfw" styleId="rskzfw" onchange="saveKzfw()" style="width:140px">
									<html:option value=""></html:option>
									<html:option value="bj">�༶</html:option>
									<html:option value="njxy">�꼶+<bean:message key="lable.xb" /></html:option>
									<html:option value="njzy">�꼶+רҵ</html:option>
									<html:option value="xy"><bean:message key="lable.xb" /></html:option>
									<html:option value="zy">רҵ</html:option>
							</html:select>
						</td>
					</tr>
					<tr class="rskzcs" style="display: none" id="aaa">
						<th width="40%">��������</th>
						<td><a href="#" class="name" onclick="rssz()">�����������...</a>&nbsp;&nbsp;<font id="sfysz"></font> </td>
					</tr>
				</tbody>
			</table>
			</logic:notEqual>
			<table width="100%" border="0" class="formlist" id="bjpy_table">
				<thead>
					<tr>
						<th colspan="2"><span>�༶��������</span></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="40%"><span class="red">*</span>����</th>
						<td>
						   
						   <logic:present name="onoffList">
									<logic:iterate id="o" name="onoffList" >
										<html:radio property="bjpykg" onclick="changeBjpykg();" value="${o.dm}">${o.mc}</html:radio>
									</logic:iterate>								
								</logic:present>
						</td>
					</tr>
					<tr>
						<th>����ʱ��</th>
						<td>
							<html:text  property="bjpykssj" styleId="bjpykssj"   size="10"
									onclick="return showCalendar('bjpykssj','y-mm-dd',true,'bjpyjssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
								-
								<html:text  property="bjpyjssj" styleId="bjpyjssj"   size="10"
									onclick="return showCalendar('bjpyjssj','y-mm-dd',false,'bjpykssj');" 
									onblur="dateFormatChg(this)" readonly="true"></html:text>
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>С����������</th>
						<td>
							<html:text property="xzrsxx" styleId="xzrsxx" maxlength="3" style="width: 25px;" onkeyup="checkInputData(this);"></html:text>%���༶�����ٷֱȣ�"0"��ʾ�����ƣ�
						</td>
					</tr>
				</tbody>
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">			            
						<logic:equal name="writeAble" value="yes">	
						<button type="button" class="button2" onclick="saveJcsz();return false;" style="width:80px"
							id="btn_save">
							�� ��
						</button>
						</logic:equal>
						&nbsp;&nbsp;
						<button type="button" class="button2" onclick="window.close();return false;" style="width:80px;display:none" 
							id="buttonClose">
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
