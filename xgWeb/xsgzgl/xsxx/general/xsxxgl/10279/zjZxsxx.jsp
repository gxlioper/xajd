<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�lt -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	    <script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			jQuery('#buttonSave').focus();	
		}
		/**
		 * ���������
		 */
		function showAlertDivLayer() {
			var argumentsArr = Array.prototype.slice.call(arguments);
			if(argumentsArr[0] == null) return;
			
			var clickFun = null;
			
			if (argumentsArr.length == 3){
				clickFun = argumentsArr[2]["clkFun"];
			}
			ymPrompt.alert({
				title:"ϵͳ��ʾ",
				useSlide:true,
				maskAlphaColor:"#FFFFFF",
				maskAlpha:0.3,
				message:argumentsArr[0],
				width:340,
				winPos:[180,160],
				height:160,
				//showMask:false,
				handler:clickFun
			});
			//setTimeout(function(){ymPrompt.doHandler();},3000);
		}
		
		//������У��������Ϣ
		function saveZxsSq(){
			var str = "xh-xm-nj-xydm-zydm-bjdm";
			var flag = checkNotNull(str);
			if (!flag) {
				alertError("��\'*\'���ֶα�����д��");
				return false;
			}
		
			var xh = $("xh").value;
			if(xh.length < 6){
				alertError("ѧ��������ά��6λ��");
			    return false;
			}
			var xbn = $("xbn");
			var xbnv = $("xbnv");
			if(!xbn.checked && !xbnv.checked){
				alertError("��ѡ���Ա�");
			    return false;
			}
			
			//���ѧ���Ƿ����
			var flag = checkXhisExists(xh);
			if (flag) {
				refreshForm('xsxx_tygl.do?method=bcZxsxx');
			}
		}
		//��ʾTBODY���� 
		function displayTbody(tbodyId) {
			if (document.getElementById(tbodyId)) {
				document.getElementById(tbodyId).style.display=(document.getElementById(tbodyId).style.display==''?'none':'');
			}
		}
		//��ʾ�ϴ���Ƭ
		function showZpscDiv(){
	
			var xh = jQuery("#xh").val();
			
			if(xh == ""){
				alertError("������дѧ�ţ�");
				return false;
			}else{
				tipsWindown("ϵͳ��ʾ","id:addPic","380","130","true","","true","id");
			}
		}
		//��Ƭ�ϴ�
		function uploadStuPic(){
		
			jQuery.ajaxSetup({async:false,dataType:'text'});
			
			var xh = jQuery("#xh").val();
			jQuery.ajaxFileUpload({
			  url:'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh='+xh,//�������˳���
			  secureuri:false,
			  fileElementId:'stuPic',
			  success:function(data,type){
				if (type=='success'){
					alert(data);
				}
			  }
			});
			
			jQuery('#stuImg').empty();
			jQuery("#stuImg").html('<img src="stuPic.jsp?xh='+xh+'&flg=true" border="0" align="absmiddle" style="width:100px;height:140px" />').show();
		
			jQuery.ajaxSetup({async:true});
		}
		//���ѧ���Ƿ����
		function checkXhisExists(xh) {
			var flag = true;
			jQuery.ajaxSetup({async:false});
			jQuery.ajax({
				 type: "POST",
			     url: "xsxx_tygl.do?method=jcXhsfcz",
			     dataType:"json",
			     data: {xh:xh},
			     success: function(data){
			     	if (data=='1') {
			     		flag = false;
			     		alertError("ѧ�ţ�'" + xh + "'�Ѿ����ڣ����������룡");
			     		//jQuery('#xh').focus();
			     	} 
			   	 }
			});
			jQuery.ajaxSetup({async:true});
			return flag;
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body>
		<html:form action="/xsxx_tygl"  method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="text" style="display: none;"/>
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<!-- ѧ��������Ϣ begin-->
					<thead>
						<tr>
							<th colspan="5">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody id="hi_jbxx">
						<tr>
							<th width="10%">
								<span class="red">*</span>ѧ��
							</th>
							<td width="30%">
									<html:text  property="xh" styleId="xh"
										styleClass="text_nor"  maxlength="20" />
							</td>
							<th>
								<span class="red">*</span>����
							</th>
							<td>
								<html:text property="xm" styleId="xm" maxlength="16"
									styleClass="text_nor" />
							</td>
							<td rowspan="5" class="nohover"
								style="vertical-align:top; text-align:center;width:100px">
								<div id="stuImg" style="margin-left:0px;margin-top: 0px;height: 130px;">
									<img style="width:100px;height:130px" 
										src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh}"
										border="0">
								</div>`
								<br/>
								<button type="button" onclick="showZpscDiv();" style="width:100px">�ϴ���Ƭ</button>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�Ա�
							</th>
							<td>
								<html:radio  property="xb" styleId="xbn" value="��" >��</html:radio>
								<html:radio property="xb" styleId="xbnv" value="Ů">Ů</html:radio>
							</td>
							<th>
								��������
							</th>
							<td>
								<html:text  property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');"
									styleClass="text_nor" styleId="csrq" readonly="true" />
							</td>
						</tr>
						
						<tr>
							<th width="10%">
								<span class="red">*</span>�꼶
							</th>
							<td width="30%">
								<html:select  property="nj" styleId="nj"
									style="width:90px" onchange="initXyzybj();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								ѧ��(��)
							</th>
							<td colspan="">
								<html:text property="xz" styleId="xz"
									onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="1"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�༶
							</th>
							<td colspan="">
								
								<input type="text" id="bjmc"
									onkeydown="" value="${rs.bjmc }" readonly="readonly"/>
								<button type="button" class="btn_01" id="button_bj" style="display: "
									onclick="getBj();">
									ѡ��
								</button>
								<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
							</td>
						
							<th>
								������ò
							</th>
							<td>
								<html:select  property="zzmm" styleId="zzmm"
									style="width:150px">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<input type="text" id="xymc"
									onkeydown="" value="${rs.xymc }"  readonly="readonly"/>
								<!--  <button class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
									ѡ��
								</button>-->
								<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
							</td>
							
							<th>
								����
							</th>
							<td>
								<html:select  property="mz" styleId="mz"
									style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							
						</tr>
						<tr>
							<th>
								<span class="red">*</span>רҵ
							</th>
							<td>
								
								<input type="text" id="zymc"
									onkeydown="" value="${rs.zymc }" readonly="readonly"/>
							<!-- 	<button class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
									style="display: ">
									ѡ��
								</button> -->
								<input type="hidden" name="zydm" tyleId="xydm"
									value="${rs.zydm}" />
							</td>
							<th>
								
							</th>
							<td align="left" colspan="2">
								
							</td>
						</tr>
						<tr>
							<th>
								�߿���Դ��
							</th>
							<td colspan="4">
									<!--��ַ��Ϣȡ��׼��-->
									<logic:present name="ssList">
										<html:select  property="syds" styleId="jgshen"
											onchange="loadShi('jgshen','jgshi','jgxian');" style="width:120px">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select  property="sydshi" styleId="jgshi"
											onchange="loadXian('jgshi','jgxian')" style="width:120px">
											<html:options collection="jgshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select property="sydx" styleId="jgxian" style="width:120px">
											<html:options collection="jgxianList" property="xiandm"
												labelProperty="xianmc" style="width:120px"/>
										</html:select>
									</logic:present>
									<!--end��ַ��Ϣȡ��׼��-->
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button"  name="����" onclick="saveZxsSq()" id="buttonSave">
										�� ��
									</button>
									<button type="button"  name="�ر�" onclick="Close()" id="buttonClose">�� ��</button>					           
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<!-- �ϴ���Ƭ -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϴ���Ƭ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="stuPic" name="stuPic" style="width:90%"
										   onchange='uploadStuPic();closeWindown();'
									/>
									<br/>
									<span style="color:red">ע�����ϴ�jpg��gif��png��bmp ��ʽ���ļ����� 1 M ��</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>