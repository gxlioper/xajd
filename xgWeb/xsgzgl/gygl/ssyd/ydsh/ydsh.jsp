<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//Ϊbuttonע���¼�
				//jQuery("#xzpxxm").load('szdw_fdypxxmwh.do?method=fdypxxmxzView&xmdm=')
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${myForm.ssydsqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${data.splcid}&shid=${myForm.shid}",{},function(){
					jQuery("#shjg").change(function(){
						if(jQuery(this).val() == '1'){
							jQuery("#tz").show();
							jQuery("#rz").show();
						}else{
							jQuery("#tz").hide();
							jQuery("#rz").hide();
						}
					});
				});
				//��ʾѡ�д�λ��Ϣ��������Ϣ��
				var sqxxcwxx = "${data.cwxx}";
				if(sqxxcwxx!="" && sqxxcwxx!="null_null_null"){
					showSqxxCwxx(sqxxcwxx);
				}
				//��ʾѡ�д�λ��Ϣ����ס �����Ϣ��
				var rzcwxx = jQuery("#rzcwxx").val();
				if(rzcwxx!="" && rzcwxx!=undefined && rzcwxx!="null_null_null"){
					showRzcwxx(rzcwxx);
				}
				//��ʾѡ�д�λ��Ϣ������ �����Ϣ��
				var cwxx = jQuery("#cwxx").val();
				if(cwxx!="" && cwxx!=undefined && cwxx!="null_null_null"){
					showCwxx(cwxx);
				}

				var zsfxs = jQuery("#zsfxs").val();
				if("0" == zsfxs) {
					jQuery("#zsfM").hide();
					jQuery("#zsf").hide();
					jQuery("#sfcwcshs").attr("colspan","3");
				}
				// ��סʱ��"ԭ��λ�Ƿ��ʼ��"ѡ"��"
				<logic:equal name="ssydlx" value="03">
					setSfcwcsh(1,1);
				</logic:equal>
			});

			function setSfcwcsh(cellValue,rowObject){
				if(cellValue!=null&&cellValue!=""){
					jQuery("input:radio[name='sfcwcsh']:eq(1)").attr("checked","checked");
					jQuery("input:radio[name='sfcwcsh']").attr("disabled","disabled");
				}else{
					jQuery("input:radio[name='sfcwcsh']:eq(0)").attr("checked","checked");
					jQuery("input:radio[name='sfcwcsh']").removeAttr("disabled");
				}
				return cellValue;
			}
			
			//��ʾѡ�д�λ��Ϣ��������Ϣ��
			function showSqxxCwxx(cwxx){
				var cwxxSqxxSetting = {
						caption:"��ѡ��λ��Ϣ",
						multiselect:false,
						rowNum:1,
						url:"ydsq.do?method=selectCwxx&type=query&cwxx="+cwxx,
						colList:[
						   {label:'¥������',name:'ldmc', index: 'ldmc'},
						   {label:'���Һ�',name:'qsh', index: 'qsh',width:'6%'},
						   {label:'��λ��',name:'cwh', index: 'cwh',width:'6%'},
						   {label:'��λ�Ա�',name:'qsxb', index: 'qsxb'},
						   {label:'�����꼶',name:'nj', index: 'nj'},
						   {label:'����<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'}
						   <logic:notEqual name="ssydlx" value="03">
						   ,{label:'ѧ��',name:'xh', index: 'xh',formatter:setSfcwcsh},
						   {label:'����',name:'xm', index: 'xm'}
						   </logic:notEqual>
						],
						sortname: "sfrz",
					 	sortorder: "desc"
					}
				jQuery("#cwxxSqxxTable").initGrid(cwxxSqxxSetting);
			}
		</script>
	</head>
	<body >
		<html:form action="/ydsh.do?method=ydsh" method="post" styleId="demoForm">
				<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
				<html:hidden property="splcid" name="data" styleId="splcid"/>
				<html:hidden property="shzt" name="data" styleId="shzt"/>
				<html:hidden property="ssydlx" name="data" styleId="ssydlx"/>
				<html:hidden property="ssydsqid" name="myForm" styleId="ssydsqid"/>
				<html:hidden property="gwid" name="myForm" styleId="gwid"/>
				<html:hidden property="xh" name="myForm" styleId="xh"/>
				<html:hidden property="shid" name="myForm" styleId="shid"/>	
				<html:hidden property="tzqlddm" name="myForm" styleId="tzqlddm"/>	
				<html:hidden property="tzqldmc" name="myForm" styleId="tzqldmc"/>	
				<html:hidden property="tzqqsh" name="myForm" styleId="tzqqsh"/>
				<html:hidden property="tzqcwh" name="myForm" styleId="tzqcwh"/>	
				<html:hidden property="tzhlddm" name="myForm" styleId="tzhlddm"/>	
				<html:hidden property="tzhldmc" name="myForm" styleId="tzhldmc"/>	
				<html:hidden property="tzhqsh" name="myForm" styleId="tzqhsh"/>
				<html:hidden property="tzhcwh" name="myForm" styleId="tzhcwh"/>	
				<html:hidden property="zsfxs" styleId="zsfxs" value="${zsfxs}"/>
				<input type="hidden" id="xxdm" value="${xxdm }" />
				<div style="tab;overflow-x:hidden;overflow-y:auto;height:450px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<logic:notEqual value="12303" name="xxdm">
						<thead>
								<tr>
									<th colspan="4">
										<span>��λ��Ϣ</span>
									</th>
								</tr>
						</thead>
						<tbody>
							<tr>
								<th align="right" width="10%">
									¥������
								</th>
								<td align="left">
									${cwxxData.ldmc}
								</td>
								<th align="right">
									���Һ�
								</th>
								<td align="left">
									${cwxxData.qsh}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									��λ��
								</th>
								<td align="left">
									${cwxxData.cwh}
								</td>
								<th align="right">
									���ҵ绰
								</th>
								<td align="left">
									${cwxxData.qsdh}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									�շѱ�׼
								</th>
								<td align="left">
									${cwxxData.sfbz}
								</td>
								<th align="right">
									�����꼶
								</th>
								<td align="left">
									${cwxxData.nj}
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									����<bean:message key="lable.xb" />
								</th>
								<td align="left">
									${cwxxData.xymc}
								</td>
								<th align="right">
									�����༶
								</th>
								<td align="left">
									${cwxxData.bjmc}
								</td>
							</tr>
						</tbody>
					</logic:notEqual>
					<thead>
							<tr>
								<th colspan="4">
									<span>�����춯����</span>
								</th>
							</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								�����춯����
							</th>
							<td align="left">
								<span style="color:red;">${data.ssydlxmc}</span>
							</td>
							<th align="right">
								ѧ��/ѧ��
							</th>
							<td align="left">
								${data.xn }/${data.xqmc}
							</td>
						</tr>
						<% 
							String ssydlx=(String)request.getAttribute("ssydlx");//�����춯����
							String xxdm=(String)request.getAttribute("xxdm");
						%>
						<tr>
							<th align="right" width="10%" id="yy">
								<% if("00".equals(ssydlx)){ %>����ԭ��<% } %>
								<% if("01".equals(ssydlx)){ %>�������ԭ��<% } %>
								<% if("03".equals(ssydlx)){ %>��סԭ��<% } %>
							</th>
							<td align="left">
								${data.tstzyymc}
							</td>
							<th align="right" id="sj">
								<% if("00".equals(ssydlx)){ %>����ʱ��<% } %>
								<logic:equal value="11647" name="xxdm">
								<% if("01".equals(ssydlx)){ %>����ʱ��<% } %>
								</logic:equal>
								<logic:notEqual value="11647" name="xxdm">
								<% if("01".equals(ssydlx)){ %>�������ʱ��<% } %>
								</logic:notEqual>
								<% if("03".equals(ssydlx)){ %>��סʱ��<% } %>
							</th>
							<td align="left" >
								${data.tstzsj}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								<% if("00".equals(ssydlx)){ %>��������<% } %>
								<% if("01".equals(ssydlx)){ %>�������<% } %>
								<% if("03".equals(ssydlx)){ %>��ס����<% } %>
							</th>
							<td colspan="3">
								<% if("00".equals(ssydlx)){ %>
									<span style="color:blue;">${data.tzqldmc}_${data.tzqqsh}_${data.tzqcwh}</span>
								<% } %>
								<% if("01".equals(ssydlx)){ %>
									<span style="color:blue;">${data.tzqldmc}_${data.tzqqsh}_${data.tzqcwh}</span>
									<img style="vertical-align: text-bottom;" src='images/ssyd/to.gif' ></img>
									<logic:notEqual name="data" property="tzhldmc" value="">
										<span style="color:blue;">${data.tzhldmc}_${data.tzhqsh}_${data.tzhcwh}</span>
									</logic:notEqual>
									<logic:equal name="data" property="tzhldmc" value="">
										<span style="color:blue;"></span>
									</logic:equal>
								<% } %>
								<% if("03".equals(ssydlx)){ %>
									<logic:notEqual name="data" property="tzqldmc" value="">
										<span style="color:blue;">${data.tzqldmc}_${data.tzqqsh}_${data.tzqcwh}</span>
									</logic:notEqual>
									<logic:equal name="data" property="tzqldmc" value="">
										<span style="color:blue;"></span>
									</logic:equal>
								<% } %>
							</td>
						</tr>
						<% if("01".equals(ssydlx) || "03".equals(ssydlx)){ %>
							<tr>
								<th>
									��ѡ��λ��Ϣ
								</th>
								<td colspan="3">
									<table id="cwxxSqxxTable"></table>
								</td>
							</tr>
						<%} %>
							<tr>
								<th align="right" style="width: 10%">
									������Ϣ
								</th>
								<td colspan="3">
										<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
										<html:hidden name="data" property="fjxx" styleId="fjxx"/>
										<script type="text/javascript">
											//���ø��� 
											jQuery(function(){
												var gid = jQuery('#fjxx').val();
												jQuery.MultiUploader_q({
													gid : gid
													});
											});
										</script>
									</td>
							</tr>
						<tr>
							<logic:notEqual value="12303" name = "xxdm">
								<th align="right" width="10%">
									��ע
								</th>
							</logic:notEqual>
							<logic:equal value="12303" name = "xxdm">
								<th align="right" width="10%">
									��������
								</th>
							</logic:equal>
							<td colspan="3">
								${data.bz}
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
							<td colspan="4" id="shlccx">
							
							</td>
						</tr>
						<logic:notEqual value="13871" name="xxdm" >
						<tr>
							<th>
								<font color="red">*</font>ԭ��λ�Ƿ��ʼ��
							</th>
							<td id="sfcwcshs">
								<input type="radio" name="sfcwcsh" value="0" >��
						    	<input type="radio" name="sfcwcsh" value="1" checked>��
							</td>	
							<th align="right" width="10%" id="zsf">
								ס�޷�
							</th>
							<td align="left" id="zsfM">
								<html:select property="jflx" styleId="jflx" >
									<html:option value=""></html:option>
									<html:option value="01">����</html:option>
									<html:option value="02">�˻�</html:option>
								</html:select>
								<html:text size="15" property="zsfje" styleId="zsfje" maxlength="6" onkeyup="checkInputData(this);"></html:text> Ԫ
							</td>									
						</tr>
						</logic:notEqual>
						<logic:equal value="13871" name="xxdm">
							<tr style="display:none">
								<input type="hidden" name="sfcwcsh" value="0" />
							</tr>
						</logic:equal>
						<tr>
							<th>
								<font color="red">*</font>��˽��
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<% if("03".equals(ssydlx)){ %>
						<tbody id="rz">
							<tr>
								<th>ѡ��λ</th>
								<td align="left" width="10%" colspan="3">
									<button class="btn_01" type="button"  onclick="selectRzcw();return false;" <logic:equal name="userType" value="stu">disabled="disabled"</logic:equal>>ѡ��λ</button>
								</td>
							</tr>
							<tr id="yxzrzcwxx">
								<th>
									��ѡ��λ��Ϣ
								</th>
								<td colspan="3">
									<table id="rzcwxxTable"></table>
									
									<html:hidden property="rzcwxx" styleId="rzcwxx" />
								</td>
							</tr>
						</tbody>
						<% } %>
						<% if("01".equals(ssydlx)){ %>
						<tbody id="tz">
							<tr>
								<th><% if(!"10351".equals(xxdm) && !"10344".equals(xxdm)&& !"12872".equals(xxdm)){ %><span class="red">*</span><% } %>ѡ��λ</th>
								<td align="left" width="10%" colspan="3">
									<button class="btn_01" type="button"  onclick="selectCw();return false;">ѡ��λ</button>
								</td>
							</tr>
							<tr id="yxzcwxx">
								<th>
									��ѡ��λ��Ϣ
								</th>
								<td colspan="3">
									<table id="cwxxTable"></table>
									
									<html:hidden property="cwxx" styleId="cwxx" />	
								</td>
							</tr>
						</tbody>
						<% } %>
						<tr>
							<th>
								<font color="red">*</font>������
							</th>
							<td width="34%" colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=ssyd&id=shyj" />
								<textarea rows="5" style="width: 90%;margin-top:5px" id="shyj" name="shyj"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<div class="btn">
										<% if("01".equals(ssydlx) && "12861".equals(xxdm)){ %>
											<input type="checkbox" id="sstz_checkbox"/>��˫��������ͬ�⡿
											&nbsp;&nbsp;&nbsp;
										<% } %>
										<button type="button" id="btqd" onclick="save_sh();">
											ȷ��
										</button>
										<button type="button" name="�� ��" onclick="iFClose();">
											�� ��
										</button>
									</div>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>

