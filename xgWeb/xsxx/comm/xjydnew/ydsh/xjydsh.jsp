<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//Ϊbuttonע���¼�
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${data.xjydsqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${data.splcid}&shid=${myForm.shid}");

				initShow();
				onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');
			});

			function initShow(){
				var xxdm = jQuery("#xxdm").val();
				var xjlbdm = jQuery("#ydlbdm").val();
				if(xjlbdm == ""){
					jQuery("#xjlbmc").html("");
					jQuery("#sfyxj").html("");
					jQuery("#sfzx").html("");
					jQuery("#tzbj").hide();
					jQuery("#lrqzsj").hide();					
				}else{
					jQuery.post("xjyd.do?method=xjydlbShpzGet",{values:xjlbdm},function(data){
						if(data != null){
							if(data["sftjbj"]=='0'){
								jQuery("#tzbj").show();
								jQuery("#zzTzbj").show();
								
							}else{
								jQuery("#tzbj").hide();
								jQuery("#zzTzbj").hide();
							}

							if(data["lrqzsj"]=='1'){
								jQuery("#lrqzsj").show();
								jQuery("#zzQzsj").show();
							}else{
								jQuery("#lrqzsj").hide();
								jQuery("#zzQzsj").hide();
							}
							if("10511" == xxdm) {
								if(data["xzsfkq"]=='1'){
									jQuery("#xzpd").show();
									jQuery("#zzxzpd").show();
								}else{
									jQuery("#xzpd").hide();
									jQuery("#zzxzpd").hide();
								}
								if(data["xxsfkq"]=='1') {
									jQuery("#xxpd").show();
									jQuery("#zzxxpd").show();
								}else{
									jQuery("#xxpd").hide();
									jQuery("#zzxxpd").hide();			
								}
							}		
						}
					},'json');
				}
			}
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/xjydsh.do" method="post" styleId="demoForm">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<html:hidden property="splcid" name="data" styleId="splcid"/>
			<html:hidden property="shzt" name="data" styleId="shzt"/>
			<html:hidden property="ydlbdm" name="data" styleId="ydlbdm"/>
			<html:hidden property="xjydsqid" name="data" styleId="xjydsqid"/>
			<html:hidden property="xh" name="data" styleId="xh"/>
			<html:hidden property="shid" name="myForm" styleId="shid"/>	
			<html:hidden property="gwid" name="myForm" styleId="gwid"/>
			<html:hidden property="isZhgw" name="myForm" styleId="isZhgw" />
			
			<div style='tab;width:100%; height: 465px; overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
							<tr>
								<th colspan="4">
									<span>ѧ���춯������Ϣ</span>
								</th>
							</tr>
					</thead>
					<tbody>
						<tr>
							<th align="right" width="10%">
								�춯���
							</th>
							<td align="left">
								<span style="color:red;">${data.ydlbmc }</span>
							</td>
							<th align="right">
								ѧ��/ѧ��
							</th>
							<td align="left">
								${data.xn } ${data.xqmc}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								ѧ�����[�춯��]
							</th>
							<td align="left" colspan="3">
								<table border="0" style="float:left">
									<tr>
										<th style="width:70px">ԭѧ�����</th>
										<td style="width:180px">&nbsp;${data.ydqxjlbmc}</td>
									</tr>
									<tr>
										<th>�Ƿ���ѧ��</th>
										<td>&nbsp;${data.ydqsfyxjmc}</td>
									</tr>
									<tr>
										<th>�Ƿ���У</th>
										<td>&nbsp;${data.ydqsfzxmc}</td>
									</tr>
								</table>
								<img style="float:left;margin:30px 5px" src='images/ssyd/to.gif' ></img>
								<table border="0"  style="float:left">
									<tr>
										<th style="width:90px">�춯��ѧ�����</th>
										<td style="width:180px">&nbsp;${data.ydlbmc }</td>
									</tr>
									<tr>
										<th>�Ƿ���ѧ��</th>
										<td>&nbsp;${data.ydhsfyxjmc}</td>
									</tr>
									<tr>
										<th>�Ƿ���У</th>
										<td>&nbsp;${data.ydhsfzxmc}</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${data.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
											});
									});
								</script>
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								����ʱ��
							</th>
							<td align="left" colspan="3">
								${data.sqsj }
							</td>
						</tr>
						<tr id="tzbj">
							<th align="right" width="10%">
								����רҵ/�༶
							</th>
							<td align="left" colspan="3">
								<table border="0"  style="float:left">
									<tr>
										<th style="width:70px;height:20px;">ԭ�꼶</th>
										<td style="width:180px" id="ydqnj">&nbsp;${data.ydqnj}</td>
									</tr>
									<tr>
										<th style="height:20px;">ԭ<bean:message key="lable.xb" /></th>
										<td id="ydqxy">&nbsp;${data.ydqxymc}</td>
									</tr>
									<tr>
										<th style="height:20px;">ԭרҵ</th>
										<td id="ydqzy">&nbsp;${data.ydqzymc}</td>
									</tr>
									<tr>
										<th style="height:20px;">ԭ�༶</th>
										<td id="ydqbj">&nbsp;${data.ydqbjmc}</td>
									</tr>
								</table>
								<img style="float:left;margin:55px 5px" src='images/ssyd/to.gif' ></img>
								<table border="0" style="float:left">
									<tr>
										<th style="width:90px;height:20px;">�춯���꼶</th>
										<td style="width:180px">&nbsp;${data.ydhnj}</td>
									</tr>
									<tr>
										<th style="height:20px;">�춯��<bean:message key="lable.xb" /></th>
										<td>&nbsp;${data.ydhxymc}
										</td>
									</tr>
									<tr>
										<th style="height:20px;">�춯��רҵ</th>
										<td>&nbsp;${data.ydhzymc}
										</td>
									</tr>
									<logic:notEmpty name="data" property="ydhbjmc">
										<tr>
											<th style="height:20px;">�춯��༶</th>
											<td>${data.ydhbjmc }
											</td>
										</tr>
									</logic:notEmpty>
								</table>
							</td>
						</tr>
						<tr id="lrqzsj">
							<th>�����춯��ֹʱ��</th>
							<td colspan="3">
								${data.sqkssj }&nbsp;��&nbsp; ${data.sqjssj }
							</td>
						</tr>
						
						<logic:equal name="xxdm" value="10511">					
							<tr id="xzpd">
								<th align="right" width="10%">
									ѧ��
								</th>
								<td align="left" colspan="3">
									${data.xz }
								</td>
							</tr>
							<tr id="xxpd">
								<th align="right">
									��ԴѧУ/ȥ��ѧУ
								</th>
								<td align="left" colspan="3">
									${data.xxmc }
								</td>
							</tr>
							<tr>
								<th align="right" width="10%">
									�춯ԭ��
								</th>
								<td align="left" colspan="3">
									${data.ydyymc }
								</td>
							</tr>
						</logic:equal>
											
						<tr>
							<th align="right" width="10%">
								��������
							</th>
							<td align="left" colspan="3">
								${data.sqly }
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
						<tr>
							<th>
								<span class="red">*</span>��˽��
							</th>
							<td colspan="3" id="shjgSpan">
								
							</td>
						</tr>
						<logic:equal value="true" property="isZhgw" name="myForm" >
							<tr id="zzTzbj">
								<th>���յ������༶</th>
								<td colspan="3">
									<table border="0" style="float:left">
										<tr>
											<th style="width:80px;"><span class="red">*</span>�춯���꼶</th>
											<td style="width:205px">
												<html:select name="data" property="ydhnj" styleId="nj" 
													onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');" style="width:100px">
													<html:option value="">&nbsp;</html:option>
													<html:options collection="njList" property="nj"
														labelProperty="nj" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th><span class="red">*</span>�춯��<bean:message key="lable.xb" /></th>
											<td>											
												<html:select name="data" property="ydhxydm" styleId="xydm"
												onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy','1','1');"  style="width:180px;">
													<html:option value="">&nbsp;</html:option>
													<html:options collection="xyList" property="xydm"
														labelProperty="xymc" />
												</html:select>
								
											</td>
										</tr>
										<tr>
											<th><span class="red">*</span>�춯��רҵ</th>
											<td>
												<html:select name="data" property="ydhzydm" styleId="zydm"
												onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy','1','1');"   style="width:180px;">
													<html:option value="">&nbsp;</html:option>
													<html:options collection="zyList" property="zydm"
														labelProperty="zymc" />
												</html:select>
											</td>
										</tr>
										<tr>
											<th><span class="red">*</span>�춯��༶</th>
											<td>
												<html:select name="data" property="ydhbjdm" styleId="bjdm" style="width:180px;">
													<html:option value="">&nbsp;</html:option>
													<html:options collection="bjList" property="bjdm"
														labelProperty="bjmc" />
												</html:select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							
							<logic:equal name="xxdm" value="10511">					
								<tr id="zzxzpd">
									<th align="right" width="10%">
										<span class="red">*</span>ѧ��
									</th>
									<td align="left" colspan="3">
										<html:text name="data" property="xz" styleId="xz" maxlength="1" onkeyup="value=value.replace(/[^\d]/g,'')"></html:text>
									</td>
								</tr>
								<tr id="zzxxpd">
									<th align="right" width="18%">
										<span class="red">*</span>��ԴѧУ/ȥ��ѧУ
									</th>
									<td align="left" colspan="3">
										<html:select name="data" property="lyqxxxdm" styleId="lyqxxxdm" disabled="false">
											<html:option value=""></html:option>
											<html:options collection="lyqxxxList" property="lyqxxxdm"
												labelProperty="lyqxxxmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th align="right" width="10%">
										<span class="red">*</span>�Ƿ�ʦ����
									</th>
									<td align="left">
										<html:select name="data" property="sfsfs" styleId="sfsfs" disabled="false">
											<html:option value=""></html:option>
											<html:option value="��ʦ��">��ʦ��</html:option>
											<html:option value="ʦ��">ʦ��</html:option>
											<html:option value="���ʦ����">���ʦ����</html:option>
										</html:select>
									</td>
									<th align="right" width="18%">
										<span class="red">*</span>��ǰ״̬
									</th>
									<td align="left">
										<html:select name="data" property="dqztdm" styleId="dqztdm" disabled="false">
											<html:option value=""></html:option>
											<html:options collection="dqztList" property="dqztdm"
												labelProperty="dqztmc" />
										</html:select>
									</td>
								</tr>
								<tr>
									<th align="right" width="10%">
										<span class="red">*</span>�춯ԭ��
									</th>
									<td align="left" colspan="3">
										<html:select name="data" property="ydyydm" styleId="ydyydm" disabled="false">
											<html:option value=""></html:option>
											<html:options collection="ydyyList" property="ydyydm"
												labelProperty="ydyymc" />
										</html:select>
									</td>
								</tr>
							</logic:equal>
							
							<tr>
								<th align="right">
									<logic:notEqual value="13871" name="xxdm">
									<logic:notEqual value="70002" name="xxdm">
									<logic:notEqual value="5002" name="xxdm">
										<span class="red">*</span>
									</logic:notEqual>
									</logic:notEqual>
									</logic:notEqual>
									ѧ���춯�ĺ�
								</th>
								<td align="left"  >
									<html:text property='xjydwh' styleId="xjydwh" maxlength="50"/>
								</td>
								
								<logic:notEqual name="xxdm" value="10511">
									<th align="right">
										<span class="red">*</span>ѧ���춯ʱ��
									</th>
									<td align="left">
									<html:text property="xjydsj" styleId="xjydsj"
										style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" />
									</td>
								</logic:notEqual>
								
								<logic:equal name="xxdm" value="10511">
									<th align="right">
										<span class="red">*</span>ѧ���춯���ʱ��
									</th>
									<td align="left">
									<html:text property="xjydsj" styleId="xjydsj"
										style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true);" />
									</td>
								</logic:equal>
							</tr>
							<tr id="zzQzsj">
								<th><span class="red">*</span>�춯��ֹʱ��</th>
								<td colspan="3">
									<html:text property="sqkssj" styleId="sqkssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',true,'sqjssj');" />
								    &nbsp;��&nbsp;
								    <html:text property="sqjssj" styleId="sqjssj" style="width:100px" onfocus="return showCalendar(this.id,'yyyy-MM-dd',false,'sqkssj');" />
								</td>
							</tr>
							<tr>
								<th>
									��ע
									<br />
									<font color="red">(��100��)</font>	
								</th>
								<td colspan="3">
									<textarea rows="5" style="width: 90%" id="xjydbz" name="xjydbz" onblur="checkLen(this,100);"></textarea>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<th >
								<span class="red">*</span>������
								<br />
								<font color="red">(��200��)</font>		
							</th>
							<td width="34%" colspan="3">
								<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xjyd&id=shyj" />
								<textarea rows="5" style="width: 90%;margin-top:5px;" id="shyj" name="shyj" onblur="checkLen(this,200);"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:35px"></div>	
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<div class="btn">
										<button type="button" id="btqd" onclick="save_sh();">
											�� ��
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
			</div>
		</html:form>
	</body>
</html>

