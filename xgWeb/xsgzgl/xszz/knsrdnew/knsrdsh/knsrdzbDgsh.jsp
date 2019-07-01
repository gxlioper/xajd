<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/bdpz/js/bdpz.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsrdnew/knsrdsh/js/knsrdzbDgsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
			jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
			autoSetZbnr();


			var xh = jQuery("#xh").val();
			if (jQuery.trim(xh) != ""){
				jQuery("#div_jtqk").load("xszz_jtqkdc.do?method=jtqkInfo",{xh:xh},function(){
				})
			}
		});	

		/**
		 * չʾ��ͥ���������Ϣ
		 * @param obj
		 * @return
		 */
		function showJtqk(obj){
			var className = jQuery(obj).attr("class");
			var newClass = className == "up" ? "down" : "up";

			jQuery(obj).attr("class",newClass);
			jQuery("#t_jtqk").toggle();
		}

		/**
		 * �����������϶���ʷ��¼
		 * @param obj
		 * @return
		 */
		function showLsjl(obj){
			var className = jQuery(obj).attr("class");
			var newClass = className == "up" ? "down" : "up";

			jQuery(obj).attr("class",newClass);
			jQuery("#t_rdlsjl").toggle();
		}				
		</script>
</head>
<body>
	<html:form action="/xg_xszz_knsrd_knshgl" method="post" styleId="knsrdshForm">
		<input type="hidden" value="${nrids}" id="nrids"/>	
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="sqsj" styleId="sqsj"/>
		<html:hidden name="model" property="shid" styleId="shid"/>

		<div style="overflow-x:hidden;overflow-y:auto;">
			
		<table class="formlist" width="95%">
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
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
							<td colspan="4">
							<table class="dateline" width="100%">
								<thead align="left">
									<tr align="left">
										<td ><b>ѧ��</b></td>
										<td ><b>ѧ��</b></td>
										<td><b>�϶�����</b></td>
										<td ><b>�϶�ʱ��</b></td>
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
											<td >
												${s.dcmc}
											</td>
											<td >
												${s.sqsj}
											</td>
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
								<logic:notEqual value="" property="xh" name="model">
									<a onclick="showJtqk(this);" class="up" 
									   href="javascript:void(0);">
									   <font color="blue">���չ��/����</font>	
									</a>
<%--										|--%>
<%--										<a onclick="editJtqk();" class="btn_xg"--%>
<%--										   href="javascript:void(0);">--%>
<%--										   <font color="blue">�༭��ͥ���</font>	--%>
<%--										</a>--%>
								</logic:notEqual>
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
			</table>
			<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>�������϶�ָ��������Ϣ</span>.
					</th>
				</tr>
			</thead>
				<tbody >
				<logic:notEmpty name="object">
					<logic:iterate name="object" id="s" indexId="i">
						<table width="100%" border="0" class="formlist" name="knsrdzb"
							style="margin: 2px auto;">
							<tr name="mytr">
								<input type='hidden' id='sxid' name='sxid'
									value="${s.key.sxid}" />
									
								<td width="20%">
									${s.key.sxmc}
								</td>
								<td width="55%">
									<table width="100%" border="0" class="datelist"
										id="knsrdzbnr" style="margin: 2px auto;">
										<thead>
											<tr>
												<td width="32px">

												</td>
												<td width="256px" style="text-align: center;">
													ָ������
												</td>
												<td width="38px" style="text-align: center;">
													��ֵ
												</td>
												<td width="64px" style="text-align: center;">
													��˷�ֵ
												</td>
											</tr>
											<tbody name="tbody_knsrdzbnr">
												<logic:iterate name="s" id="list" indexId="j"
													property="value">
													<tr id="knsrdzbxxnr">
														<td width="32px">
															<input type="hidden" name="nrid" value="${list.nrid}" />
															<logic:notEqual value="10052" name="xxdm" >
																<input type='checkbox' name="zbnrid" disabled="disabled"  onclick="xzknsfz(this)"/>
															</logic:notEqual>
															<logic:equal value="10052" name="xxdm" >
																<input type='radio' name="zbnrid" disabled="disabled"  onclick="xzknsfz(this)"/>
															</logic:equal>	

															<input type="hidden" name="fzlxH" value="${list.fzlx}" />
															<input type="hidden" name="fzH" value="${list.fz}" />
															<input type='hidden' id='qzbl' name='qzbl'
																		value="${s.key.qzbl}" />
														</td>
														<td width="256px">
															${list.nrmc }
														</td>
														<td width="38px">
															<span id="spanfz">${list.fz }</span>
															<%--<input type="text" id="textfz" value="${list.fz}" 
															style="display:none;width" size='2'/>
															--%>
															</td>
														<td width="64px">
															<span id="spanshfz">${list.shfz }</span>
															<input type="text" id="textshfz" value="${list.shfz}" 
															style="display:none;width" size='3' onblur="checkValue(this)"/>
															<input type="hidden" name="pdfzH" value="${list.fz}" />
														</td>
													</tr>
												</logic:iterate>
											</tbody>
										</thead>
									</table>
								</td>

								<td width="25%" colspan="2">
									<table width="100%" border="0" class="datelist"
										id="knsrdsqzbnr" style="margin: 2px auto;">
										<thead>
											<tr>
												<td>
													�������������ѧ��¼�룩
												</td>
											</tr>
											<tbody>
												<tr >
													<td rowspan="5">
														${s.key.jtqk}
													</td>
												</tr>
											</tbody>
										</thead>
									</table>
								</td>

							</tr>
						</table>
					</logic:iterate>
				</logic:notEmpty>
			</tbody>
			</table>
			<table width="100%" class="formlist">
			
		
			<thead>
					<tr>
						<th colspan="4">
							<span>�����ʷ</span>
						</th>
					</tr>
			</thead>				
			<tbody>
				<tr>
					<td colspan="4" id="shlccx">
					
					</td>
				</tr>
			
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>�����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<tr>
						<th width="20%">
							���������
						</th>
						<td  width="30%">
							<span id="zsfz"></span>
						</td>
						<th width="20%">
							�϶�����
						</th>
						<td  width="30%">
							<html:select property="rddc" styleId="rddc">
									<html:option value=""></html:option>
									<html:options collection="knsdcList" property="dcdm" labelProperty="dcmc"/>
							</html:select>
						</td>
					</tr>
			</tr>
			<tr>
					<tr>
						<th>
							<font color="red">*</font>��˽��
						</th>
						<td colspan="3" id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br/>
					<font color="red">��200��</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=knsrd&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			
			</table>
			
			
		</div>
		</td>
		</tr>
		</table>
		</div>
		<div style="height: 30px"></div>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveShzt();return false;">
									����
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
			</tfoot>
		</table>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
