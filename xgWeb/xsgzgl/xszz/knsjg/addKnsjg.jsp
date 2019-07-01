<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsjg/js/addKnsjg.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		
	</head>
	<body>
		
		<html:form action="/xszz_knsjg" method="post" styleId="knsjgForm" onsubmit="return false;">
			<input type="hidden" value="${xxdm}" id="xxdm"/>
			
			<div style='tab;width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/selectStudent.jsp" %>
					
					<thead>
						<tr>
							<th colspan="4">
								<span>�������϶���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<logic:equal value="xn" name="sqzq">
					    <tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
								<html:hidden property="xq" styleId="xq" value="on"/>
							</td>
							<th>
							����ʱ��
							</th>
							<td>
								<html:hidden property="sqsj"/>
								<bean:write name="knsjgForm" property="sqsj"/>
							</td>
					    </tr>
					    <logic:equal value="10346" name="xxdm">
							<tr>
								<th>
									<span>
										<font color="red">*</font>��ͥ��������
									</span>
								</th>
								<td>
									<html:select property="ylzd5" styleId="ylzd5" >
										<html:option value="">---��ѡ��---</html:option>
										<html:options collection="jtknlxList" labelProperty="mc" property="dm"/>
									</html:select>
								</td>
								<th>
									<span>
										<font color="red">*</font>�ߵ�����Ʒ����
									</span>
								</th>
								<td>
									<html:select property="ylzd4" styleId="ylzd4" >
										<html:option value="">---��ѡ��---</html:option>
										<html:options collection="gdxfplxList" labelProperty="mc" property="dm"/>
									</html:select>
								</td>
							</tr>
						</logic:equal>
					    <tr>
					    	<logic:equal value="13871" name="xxdm">
					    		<th><span class="red">*</span>�϶�����</th>
								<td>
									<html:select property="rddc" styleId="rddc" style="width:130px">
										<html:options collection="knsdcList" property="dcdm"
											labelProperty="dcmc" />
									</html:select>
								</td>
								<th><span class="red">*</span>��������</th>
								<td>
									<html:text property="knpx" styleId="knpx" onkeyup="checkInput(this)" maxlength="10" />
								</td>
					    	</logic:equal>
					    	<logic:notEqual value="13871" name="xxdm">
					    		<th><span class="red">*</span>�϶�����</th>
								<td>
									<html:select property="rddc" styleId="rddc" style="width:130px">
										<html:options collection="knsdcList" property="dcdm"
											labelProperty="dcmc" />
									</html:select>
								</td>
								<th>
									<span class="red">*</span>��������
								</th>
								<td>
									<select name="sqlyyy" id="sqlyyy">
										<option value="��" >��</option>
										<option value="����">����</option>
										<option value="�¶�" >�¶�</option>
										<option value="�м�" >�м�</option>
										<option value="�ͱ�" >�ͱ�</option>
										<option value="��ʿ��Ů" >��ʿ��Ů</option>
										<option value="ũ���屣" >ũ���屣</option>
										<option value="��" >��</option>
										<option value="����" >����</option>
										<option value="����" >����</option>
										<option value="��ũרҵ" >��ũרҵ</option>
									</select>
								</td>
					    	</logic:notEqual>
					    </tr>
					    </logic:equal>
					    <logic:notEqual value="xn" name="sqzq">
					    <tr>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xn" styleId="xn" style="width:130px">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<html:select  property="xq" styleId="xq" style="width:130px">
								<html:option value=""></html:option>
								<html:options collection="xqList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
					    </tr>
					    <tr>
							<th><span class="red">*</span>�϶�����</th>
							<td>
								<html:select property="rddc" styleId="rddc" style="width:130px">
									<html:options collection="knsdcList" property="dcdm"
										labelProperty="dcmc" />
								</html:select>
							</td>
							<th>
							����ʱ��
							</th>
							<td>
								<html:hidden property="sqsj"/>
								<bean:write name="knsjgForm" property="sqsj"/>
							</td>
					    </tr>
					    <logic:equal value="13871" name="xxdm">
					    <tr>
				    		<th><span class="red">*</span>��������</th>
							<td colspan="3">
								<html:text property="knpx" styleId="knpx" onkeyup="checkInput(this)" maxlength="10"/>
							</td>
					    </tr>
					    </logic:equal>
					    </logic:notEqual>
					         <%-- �й�����ѧԺ���Ի�  --%>
					<logic:equal value="10355" name="xxdm">
						
							<tr style="height:10px">
							<th align="right">
								��ͥ�˾�������<br />
							</th>
							<td colspan="3">
								<html:textarea  property='jtrjnsr' styleId="jtrjnsr" onkeyup="value=value.replace(/[^\d]/g,'')" style="word-break:break-all;width:20%" onblur="chLengs(this,450);"
									rows='1' value="${rs.bz }"  />
							</td>
						</tr>
						
					</logic:equal>
					    <tr>
					    	<th>
								<logic:equal value="12861" name="xxdm"><font color="red">*</font></logic:equal><logic:equal value="10335" name="xxdm"><font color="red">* </font></logic:equal>������Ϣ
							</th>
							<td colspan="3">
								<html:hidden property="ylzd2" styleId="ylzd2" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
								<logic:equal value="12861" name="xxdm">
									<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'ylzd2'
													});
											});										
									</script>
	                            </logic:equal>
	                            <logic:notEqual value="12861" name="xxdm">
	                            	<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												jQuery.MultiUploader({
													maxcount : 3,
													//��׺
													accept : 'png|gif|jpg|zip|rar|doc|docx',
													//����ļ���С ��λM
													maxsize: 10,
													//��Ÿ������������id
													elementid : 'ylzd2'
													});
											});											
									</script>
	                            </logic:notEqual>		
							</td>					    
					    </tr>
					   	<logic:equal value="10742" name="xxdm">
							<tbody>
								<th ><span class="red">*</span>��������</th>
								<td colspan="3" >
								<logic:notEmpty name="sqlymcList">
								<logic:iterate name="sqlymcList" id="s"  indexId="i" >
									<label><input type="checkbox" name="sqlydm" value="${s.sqlymc}"/>${s.sqlymc}</label>
									<br/>
								</logic:iterate>
								</logic:notEmpty>
								</td>
							</tbody>
						</logic:equal>
					    <logic:equal value="10277" name="xxdm">
					<tbody>
							<tr>
								<th align="right">����ԭ��</th>
								<td colspan="3">
									
									<%
									List<HashMap<String, String>> list=(List<HashMap<String, String>>)request.getAttribute("knyyList");
									for(int i=0;i<list.size();i++){
										HashMap<String, String> map=list.get(i);%>
										<input type="checkbox" name="ylzd5" value="<%=map.get("yydm")%>" /><%=map.get("yymc")%><br />
									<%
									}
									%>
								</td>
							</tr>
						</tbody>
						</logic:equal>
					    <!--  
					    <logic:equal value="10335" name="xxdm">
						<tr>
							<th align="right"><font color="red">*</font>�޳��������</th>
							<td colspan="3">
								<html:select property="ylzd3" styleId="ylzd3">
									<html:option value=""></html:option>
									<html:options collection="wczzjeList" property="zzjedm" labelProperty="zzjemc"/>
								</html:select>
							</td>
							
						</tr>
						</logic:equal>
						-->
<%--						<logic:notEqual name="xxdm" value="10026">--%>
<%--					    	<tr>--%>
<%--								<th>--%>
<%--									�϶�����--%>
<%--									<br /><font color="red">(��200��)</font>--%>
<%--								</th>--%>
<%--								<td colspan="3">--%>
<%--									<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,200);"/>--%>
<%--								</td>--%>
<%--			      			</tr>--%>
<%--			      		</logic:notEqual>--%>
			     <!--������ҽҩ��ѧ-->
<%--			      <logic:equal value="10026" name="xxdm">--%>
			       		<tr>
							<th>
								�϶�����
								<br /><font color="red">(��30��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='ylzd5' style="width:98%" styleId="ylzd5" rows='2' onblur="checkLen(this,30);"/>
							</td>
			      		</tr>
<%--			       </logic:equal>--%>
<%--			      <logic:equal value="10026" name="xxdm">--%>
			       		<tr>
							<th>
								�϶���������
								<br /><font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='sqly' style="width:98%" styleId="sqly" rows='5' onblur="checkLen(this,200);"/>
							</td>
			     		</tr>
<%--			       </logic:equal>--%>
				</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="saveForm();">
										�� ��
									</button>
									<button type="button" type="button" onclick="iFClose();">
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

