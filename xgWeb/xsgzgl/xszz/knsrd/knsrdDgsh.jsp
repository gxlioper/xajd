<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrd/js/knsrd.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				
				//��������ѡ��
				loadViewMkxxSelectOptions();
				//����radioѡ��
				loadViewMkxxRadioOptions();

				var xh = jQuery("#xh").val();
				if (jQuery.trim(xh) != ""){
					jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
					})
				}

				//ǰһ���϶�����
				//var beforeRddc = jQuery("input[name=tjdc][value !=]").last().val();
				//jQuery("#rddc").val(beforeRddc);

				//�ǵ�һ��������˻ذ�ť
				//var firstGwid = jQuery("input[name=gwid]").first().val();
				//if (firstGwid != jQuery("#xtgwid").val()){
				//	jQuery("#btn_th").css("display","");
				//}

				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid="+jQuery("#guid").val()+"&tt="+new Date().getTime());				
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid="+jQuery("#shlc").val()+"&shid="+jQuery("#shid").val()
				,function () {
                        if(jQuery("#xxdm").val()=="12866"){
                            hideShyj();
                        }
                    });

			});
			
			function hideRddc(){
				if("1"!=jQuery("#shjg").val()){
					jQuery("#tjdctr").hide();
				}else{
					jQuery("#tjdctr").show();
				}

                if(jQuery("#xxdm").val()=="12866"){
                    hideShyj();
                }
			}

            function  hideShyj() {
                if("1"==jQuery("#shjg").val()){
                    jQuery("#shyjtr").hide();
                    jQuery("#shyj").val("ͨ��");
                }else{
                    jQuery("#shyjtr").show();
                    jQuery("#shyj").val("");
                }
            }

		</script>
	</head>
	<body>
		<html:form action="/xszz_knsrd" method="post" styleId="knsrdForm">
			<html:hidden property="guid" styleId="guid"/>
			<html:hidden property="xtgwid" styleId="xtgwid"/>
			<html:hidden property="xh" styleId="xh"/>
			<html:hidden property="shzt" styleId="shzt"/>
			<html:hidden property="shlc" styleId="shlc"/>
			<html:hidden property="shid" styleId="shid"/>
			<input type="hidden" value="${xxdm}" id="xxdm"/>
			<div style='tab;width:100%;height:460px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/xszz/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�϶���ʷ��¼
									<a onclick="showLsjl(this);" class="down" 
									   href="javascript:void(0);">
									   <font color="blue">���չ��/����</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_rdlsjl" style="display: table-row-group;">
						<tr>
							<td colspan="6">
							<table class="dateline" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>ѧ��</b></td>
										<td ><b>ѧ��</b></td>
										<td><b>�϶�����</b></td>
										<td ><b>�϶�ʱ��</b></td>
										<logic:equal value="10335" name="xxdm" scope="request">
											<td ><b>�϶�״̬</b></td>
										</logic:equal>	
									</tr>
								</thead>
								<tbody align="left">
							<logic:notEmpty name="rdlsjlList">
							<logic:iterate name="rdlsjlList" id="s">
										<tr  style="cursor:hand">
											<td >
												${s.xn}
											</td>
											<td >
												${s.xqmc}
											</td>
											<td>
												${s.dcmc}
											</td>
											<td >
												${s.sqsj}
											</td>
										<logic:equal value="10335" name="xxdm" scope="request">
											<td >
												${s.sfqxrd}
											</td>	
										</logic:equal>	
																			
										</tr>
										
								</logic:iterate>
							</logic:notEmpty>
							</tbody>
							</table>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>��ͥ���
									<a onclick="showJtqk(this);" class="up" 
									   href="javascript:void(0);">
									   <font color="blue">���չ��/����</font>	
									</a>
								</span>
							</th>
						</tr>
					</thead>
					<tbody id="t_jtqk" style="display: none;">
						<tr>
							<td colspan="4">
								<div id="div_jtqk">
								
								</div>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�������϶�������Ϣ</span>
							</th>
						</tr>
					</thead>
					<logic:equal value="10346" name="xxdm">
					<tr>
						<th>
							<span>
								��ͥ��������
							</span>
						</th>
						<td>
							${mkxxForm.jtknlxmc}
						</td>
						<th>
							<span>
								�ߵ�����Ʒ����
							</span>
						</th>
						<td>
							${mkxxForm.gdxfplxmc}
						</td>
					</tr>
					</logic:equal>
					<logic:equal name="xxdm" value="11998">
						<th>
							����������
						</th>
						<td colspan="3">
							${zf}
						</td>
					</logic:equal>
							<%-- �й�����ѧԺ���Ի�  --%>
					<logic:equal value="10355" name="xxdm">
						<tbody>
							<tr style="height:10px">
							<th align="right">
								��ͥ�˾�������
							</th>
							<td colspan="3">
								${mkxxForm.jtrjnsr }
							</td>
						</tr>
						</tbody>
					</logic:equal>
					<tr>
						<th>
							������Ϣ
						</th>
						<td colspan="3">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden property="ylzd2" styleId="fjid"/>
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
					</tr>
				
					<logic:equal value="1" name="sqsftxdc">
						<tbody>
							<tr>
								<th align="right">���뵵��</th>
								<td colspan="3">
									${mkxxForm.sqdcmc }
								</td>
							</tr>
						</tbody>
					</logic:equal>

					<logic:equal value="12389" name="xxdm">
						<tbody>
						<tr>
							<th align="right">��������</th>
							<td colspan="3">
								<logic:notEmpty name="sqlyList">
									<logic:iterate name="sqlyList" id="s"  indexId="i" >
										${s}
										<br/>
									</logic:iterate>
									${mkxxForm.ylzd10}
								</logic:notEmpty>
							</td>
						</tr>
						</tbody>
					</logic:equal>
					
					<logic:equal value="10742" name="xxdm">
						<tbody>
							<tr>
								<th align="right">��������</th>
								<td colspan="3">
									${mkxxForm.sqlydm }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					
					<logic:equal value="10277" name="xxdm">
					<tbody>
							<tr>
								<th align="right">����ԭ��</th>
								<td colspan="3">
									${mkxxForm.yymc }
								</td>
							</tr>
						</tbody>
					</logic:equal>
					<tbody>
						<tr>
							<th align="right">��������</th>
							<td colspan="3">
									${mkxxForm.sqlyyy}
							</td>
						</tr>
						<tr>
							<th name="th_sqly">
								��������
							</th>
							<td colspan="3" style="word-break:break-all;">
									${mkxxForm.sqly}
							</td>
						</tr>
					</tbody>
					<%--<%@ include file="/xsgzgl/xszz/bdpz/mkxxView.jsp" %>--%>
					<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						
						<!-- �㽭����ְҵ����ѧԺ   ���ҳ���������������� -->
						<logic:equal value="12866" name="xxdm">
							<tr>
								<th align="right">
									<font color="red">*</font>����������
								</th>
								<td colspan="3">
									<html:select property="ylzd4" styleId="ylzd4" >
										<html:option value=""></html:option>
										<html:option value="ͨ��">ͨ��</html:option>
										<html:option value="��ͨ��">��ͨ��</html:option>
									</html:select>
								</td>
							</tr>
							
						</logic:equal>
						<logic:equal value="13871" name="xxdm">
							<tr>
								<th align="right">
									<font color="red">*</font>��������
								</th>
								<td colspan="3">
									<html:text property="knpx" styleId="knpx" onkeyup="checkInput(this)" maxlength="10"/>
								</td>
							</tr>
						</logic:equal>
						<tr id="tjdctr">
							<th align="right"><font color="red">*</font>�Ƽ�����</th>
							<td colspan="3">
								<html:select property="rddc" styleId="rddc" >
									<html:option value=""></html:option>
									<html:options collection="knsdcList" property="dcdm" labelProperty="dcmc"/>
								</html:select>
							</td>
						</tr>
						<%-- <logic:equal value="10277" name="xxdm">
						<tbody>
								<tr>
									<th align="right">�Ƽ�ԭ��</th>
									<td colspan="3">
										<%
										List<HashMap<String, String>> list=(List<HashMap<String, String>>)request.getAttribute("knyyList");
										for(int i=0;i<list.size();i++){
											HashMap<String, String> map=list.get(i);%>
											<input type="checkbox" name="ylzd5" value="<%=map.get("yydm")%>" 
											<%if(("1").equals(map.get("checked"))){%>
													checked
											<%}%> 
											/><%=map.get("yymc")%><br />
										<%
										}
										%>
									</td>
								</tr>
							</tbody>
						</logic:equal> --%>
						<%--  �������Ķ������ֶ�ȥ����by yxy 2015-11-20
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
						--%>
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td colspan="3" id="shjgSpan" onchange="hideRddc();">
								
							</td>
						</tr>
						<tr id="shyjtr">
							<th>
								<font color="red">*</font>
								������
								<br/>
								<font color="red">(��200��)</font>
							</th>
							<td colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=knsrd&id=shyj" />
								<html:textarea property="shyj" style="width:98%;margin-top:5px" rows="5"
											   onblur="checkLen(this,200);" styleId="shyj">
								</html:textarea>
							</td>
						</tr>
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
									<button type="button" id="btqd" onclick="saveKnssh();">
										�� ��
									</button>
<%--									<button type="button" onclick="saveKnssh('1','ͨ��');">--%>
<%--										ͨ��--%>
<%--									</button>--%>
<%--									<button type="button" onclick="saveKnssh('2','��ͨ��');">--%>
<%--										��ͨ��--%>
<%--									</button>--%>
<%--									<button type="button" onclick="saveKnssh('3','�˻�');" id="btn_th" style="display:none;">--%>
<%--										�˻�--%>
<%--									</button>--%>
									<button type="button" name="�� ��" onclick="iFClose();">
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

