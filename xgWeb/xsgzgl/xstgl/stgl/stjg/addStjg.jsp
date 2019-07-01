<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stjg/js/stjg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			xsbjfj();
			jQuery("#fzrlb").change(function(){
				xsbjfj();
				jQuery("#stfzrxm").val("");
				jQuery("#stfzr").val("");
			});
			jQuery("#selfzr").click(function(){
				var flag = fzrlbpd(jQuery("#fzrlb").val());
				if(flag == false){
					showAlert("����ѡ���������");
					flag = null;
					return false;
				}
			});
			//jQuery("#zdlszc").html("<option value=''></option>"+jQuery("#zdlszc").html());
			jQuery("#ssbm").html("<option value=''></option>"+jQuery("#ssbm").html());
		});
		function showSelYm(url,title){
			showDialog(title, 770, 520, url,{close:function(){
				if (jQuery("#fzrlb")){
					jQuery('#fzrlb').attr('disabled',false);
				}
			}});
		}

		//�Զ���������������ж�
		function fzrlbpd(value){
			jQuery('#fzrlb').attr('disabled',true);
			if(value == '��ʦ'){
				url = "stglStsq.do?method=getTea";
				title = "��ʦ";
				showSelYm(url,title)
			}else if(value == 'ѧ��'){
				url = "stglStsq.do?method=getStu";
				title = "ѧ��";
				showSelYm(url,title)
			}else{
				return false;
			}
		}
		//ѡ��ָ����ʦ
		function selectzdls(){
			url = "stglStsq.do?method=getTea&flag=selzdls";
			title = "��ʦ";
			showSelYm(url,title)
		}
		//�����������
		jQuery(function(){
			jQuery("#stlbdm").change(function(){
				var stlbdm = jQuery("#stlbdm").val();
				var html = getXmlblist(stlbdm);
				jQuery("#xmlbdm").html(html);
			});
		});
		//ѡ��ѧ����ʾ�༶
		function xsbjfj(){
			var fzrlb=jQuery("#fzrlb").val();
			if(fzrlb=="ѧ��"){
				document.getElementById("bjmctr").style.display = "";
			}else{
				document.getElementById("bjmctr").style.display = "none";
				jQuery("#xymc").html("");
				jQuery("#bjmc").html("");
			}
		}

        //������
        function addRowDialog(){
            var url = "stglStsq.do?method=getTea&flag=selzdls";
            var title = "ָ����ʦѡ��";
            showDialog(title, 770, 550, url);
        }

        //ɾ����
        function delRow(){
            var obj = jQuery("[name='chk']:checked").parent().parent();
            if(obj.length == 0){
                showAlert("����ѡ��ָ����ʦ��Ϣ���ٽ���ɾ��������");
                return false;
            }
            jQuery(obj).remove();
            jQuery("[name='chkall']").removeAttr("checked");
        }
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/stglStjg" method="post" styleId="StjgForm" onsubmit="return false;">
			<html:hidden property="jtr" styleId="jtr" value="${jtr}"/>
			<html:hidden property="xhs" styleId="xhs" value=""/>
			<input hidden id="xxdm"  value="${xxdm }"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<font color="red">*</font>������Ŀ����
							</th>
							<td colspan="3">
								<html:text property="stxmmc" styleId="stxmmc" maxlength="50" style="width:98%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>�������
							</th>
							<td width="30%">
								<html:select property="stlbdm" style="width:152px;" styleId="stlbdm" >
									<html:options collection="stlbList" property="stlbdm"
										labelProperty="stlbmc" />
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>��Ŀ���
							</th>
							<td width="30%">
								<html:select property="xmlbdm" styleId="xmlbdm" style="width:152px;">
									<html:options collection="xmlbList" property="xmlbdm"
										labelProperty="xmlbmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>��Чѧ��
							</th>
							<td width="30%">
								<html:select  property="xn" styleId="xn" value="${mryxxn}" style="width:152px;">
								<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>�ҿ���λ
							</th>
							<logic:notEqual name="xxdm" value="12872">
								<td width="30%">
									<html:select property="gkdw" styleId="gkdw" style="width:152px" >
										<html:options collection="bmList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</logic:notEqual>
							<logic:equal value="12872" name = "xxdm">
								<td width="30%">
									<html:select property="gkdw" styleId="gkdw" style="width:152px" >
										<html:option value="У��ί">У��ί</html:option>
										<html:option value="������Ʋ�">������Ʋ�</html:option>
										<html:options collection="gkdwList" property="mc" labelProperty="mc" />
									</html:select>
								</td>
							
							</logic:equal>
						</tr>
						<!-- 
						<tr>
							<th width="20%">
								���ſ�ʼʱ��
							</th>
							<td width="30%">
								<html:text property="kssj" style="width:148px;"
									onclick="return showCalendar('kssj','ymmdd',true,'jssj');" styleId="kssj" ></html:text>
							</td>
							<th width="20%">
								���Ž���ʱ��
							</th>
							<td width="30%">
								<html:text property="jssj" style="width:148px;"
									onclick="return showCalendar('jssj','ymmdd',false,'kssj');" styleId="jssj" ></html:text>
							</td>
						</tr> -->
						<tr>
							<th width="20%">
								<font color="red">*</font>���������
							</th>
							<td width="30%">
								<html:select property="fzrlb" styleId="fzrlb" style="width:152px;" >
									<option value='��ʦ'>��ʦ</option>
									<option value='ѧ��'>ѧ��</option>
								</html:select>
							</td>
							<th width="20%">
								<font color="red">*</font>���Ÿ�����
							</th>
							<td width="30%">
								<input type="text" name="stfzrxm" style="width:100px;" id="stfzrxm" readonly="true" maxlength="10"/>
								<html:hidden property="stfzr" styleId="stfzr" />
								<button class="btn_01" id="selfzr" type="button">ѡ��</button>
							</td>
						</tr>
						<tr id="bjmctr">
							<th width="20%">
								����������ѧԺ
							</th>
							<td width="30%" id="xymc">
							
							</td>
							<th width="20%">
								���������ڰ༶
							</th>
							<td width="30%" id="bjmc">
							
							</td>
						</tr>
					<thead>
					<tr>
						<th colspan="4">
							<span>ָ����ʦ</span>
							<button type="button" style="margin-top:2px;margin-left:30px" onclick="addRowDialog();return false;">����</button>
							<button type="button" onclick="delRow();return false;">ɾ��</button>
						</th>
					</tr>
					</thead>
					<tbody>
					<tr colspan="4">
						<td width="100%" colspan="4">
							<div width="100%" id="autotable">
								<table width="100%" id="tablebody">
									<tr>
										<th width="5%"><input type="checkbox" onclick="selectAll(this);" name="chkall"/></th>
										<th width="30%" style="text-align:left;"><font color="red">*</font>ָ����ʦ����</th>
										<th width="20%" style="text-align:left;"><font color="red">*</font>��������</th>
										<th width="20%" style="text-align:left;">��ϵ�绰</th>
										<th width="20%" style="text-align:left;">ְ��</th>
									</tr>
								</table>
							</div>
						</td>

					</tr>
						<tr>
							<th width="20%">
								������ϵ�绰
							</th>
							<td width="30%" >
								<html:text property="lxdh" style="width:148px;" styleId="lxdh" maxlength="20"></html:text>
							</td>
							<th width="20%">
								������
							</th>
							<td width="30%">
								${jtrxm}
							</td>	
						</tr>
						<tr>
							<th width="20%">
								<font color="red">*</font>���ų���ʱ��
							</th>
							<td width="30%">
								<html:text property="stclsj"  style="width:148px"
									onclick="return showCalendar('stclsj','y-mm-dd');" styleId="stclsj" ></html:text>
							</td>
							<th width="20%">
								����ʱ��
							</th>
							<td width="30%">
								<html:text property="sqsj" value="${sqsj}" style="width:148px"
									onclick="return showCalendar('sqsj','y-mm-dd');" styleId="sqsj" ></html:text>
							</td>						
						</tr>
					<logic:equal value="12872" name = "xxdm">
						<tr>
							<th width="20%">
								<font color="red">*</font>�Ǽ�
							</th>
							<td width="30%">
								<html:select property="stxj" styleId="stxj" style="width:152px" >
									<html:options collection="stxjList" property="xj"
												  labelProperty="xj" />
								</html:select>
							</td>
							<th width="20%">
							</th>
							<td width="30%">
							</td>
						</tr>
					</logic:equal>
						
						<tr id="fjtr">
							<th>
								����ƻ���
							</th>
							<td colspan="3">
								<html:hidden property="fj" styleId="fj" />
								<%@ include file="/xsgzgl/comm/fileUpload/f.jsp"%>
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
												elementid : 'fj'
											});
										});
											
								</script>
							</td>
						</tr>
						<tr>
							<th width="20%">
								���ż��
							    </br><font color="red">(��500��)</font></th>
							</th>
							<td colspan="3">
								<html:textarea property="stsm" styleId="stsm" 
											   onkeyup="checkzsinput_yz(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						<tr>
							<th width="20%">
								���Ż����
								</br><font color="red">(��500��)</font></th>
							</th>
							<td colspan="3">
								<html:textarea property="sthjqk" styleId="sthjqk" 
											   onkeyup="checkzsinput_yz(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">*</font>�����������
							</th>
							<td colspan="3">
								<html:select property="splc" styleId="splc">
									<option value=""></option>
									<html:options collection="shlcList" property="splc"
										labelProperty="lcxx" />
								</html:select>
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
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="saveStjg('save');">
										����
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body><html:hidden property="jtr" styleId="jtr" value="${jtr}" />
</html>

