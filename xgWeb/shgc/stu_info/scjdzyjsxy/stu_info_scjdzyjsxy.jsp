<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/String.js"></script>
		<script type="text/javascript" src="js/stuinfoFunction.js"></script>
		<script type="text/javascript"
			src="/xgxt/dwr/interface/getStuDetails.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getXjydInfo.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type='text/javascript' src='js/check.js'></script>
		<script type="text/javascript" src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/xsxx/xsxxplczFunction.js"></script>
		<script type="text/javascript">
		//��Ƭ�ϴ�
		function uploadStuPic(){
		
			jQuery.ajaxSetup({async:false});
			
			var xh = jQuery("#xh").val();
		
			jQuery.ajaxFileUpload({
			  url:'general_xsxx_zxxs_ajax.do?method=uploadStuPic&xh='+xh,//�������˳���
			  secureuri:false,
			  fileElementId:'stuPic'//input���ID
			});
			
			jQuery('#stuImg').empty();
			jQuery("#stuImg").html('<img src="stuPic.jsp?xh=${rs.xh }&flg=true" border="0" align="absmiddle" style="width:120px;height:160px" />').show();
		
			jQuery.ajaxSetup({async:true});
		}
		
		function deploy(id){
			document.getElementById(id).style.display = (document.getElementById(id).style.display == 'none') ? '' : 'none';  
		}
		
		function Close() {
			var ua = navigator.userAgent;
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			if (ie) {
				var IEversion = parseFloat(ua.substring(ua.indexOf("MSIE ") + 5, ua.indexOf(";", ua.indexOf("MSIE "))));
				if (IEversion < 5.5) {
					var str = "<object id=noTipClose classid=\"clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11\">";
					str += "<param name=\"Command\" value=\"Close\"></object>";
					document.body.insertAdjacentHTML("beforeEnd", str);
					document.all.noTipClose.Click();
				} else {
					window.opener = null;
			 		window.dialogArguments.document.getElementById('search_go').onclick();
					window.close();
				}
			} else {
				window.close();
			}
		}
		
		function checkKey(o) {
			o.value=o.value.replace(/[^\w\.\/]/ig,'');
		}
		
		function send(){
			var xxdm = $("xxdm").value;
			var sfzh = $("sfzh").value;
			var csrq = $("csrq").value.replace(/\-/g,"");
			var jgshen=($("jgshen")&&$("jgshen")!=null)?$("jgshen").value:"";
			var jgshi= ($("jgshi")&&$("jgshi")!=null)?$("jgshi").value:"";
			var jgxian=($("jgxian")&&$("jgxian")!=null)?$("jgxian").value:"";
			var syshen=($("syshen")&&$("syshen")!=null)?$("syshen").value:"";
			var syshi=($("syshi")&&$("syshi")!=null)?$("syshi").value:"";
			var syxian=($("syxian")&&$("syxian")!=null)?$("syxian").value:"";
			var rxqxxshen=($("rxqxxshen")&&$("rxqxxshen")!=null)?$("rxqxxshen").value:"";
			var rxqxxshi=($("rxqxxshi")&&$("rxqxxshi")!=null)?$("rxqxxshi").value:"";
			var rxqxxxian=($("rxqxxxian")&&$("rxqxxxian")!=null)?$("rxqxxxian").value:"";
			var rxqbyxx = ($("rxqbyxx")&&$("rxqbyxx")!=null)?$("rxqbyxx").value:"";
			var rxqbyxx = ($("rxqbyxx")&&$("rxqbyxx")!=null)?$("rxqbyxx").value:"";
			var jtdzshen = ($("jtdzshen")&&$("jtdzshen")!=null)?$("jtdzshen").value:"";
			var jtdzshi  = ($("jtdzshi")&&$("jtdzshi")!=null)?$("jtdzshi").value:""; 
			var jtdzxian  = ($("jtdzxian")&&$("jtdzxian")!=null)?$("jtdzxian").value:"";
			
			var xh = $("xh").value;
			if(xh.length < 6){
				alert("ѧ��������ά��6λ��");
			    return false;
			}
			
			if(xxdm=="10649"){//��ɽʦ��
			  if(syshen==""){
			     alert("��Դʡ����Ϊ�գ�");
			     return false;
			  }else{
			    if($('syshi').options.length>1){
			       if(syshi==""){
			           alert("��Դ�в���Ϊ�գ�");
			           return false;
			       }else{
			          if($('syxian').options.length>1){
			             if(syxian==""){
			                alert("��Դ��������Ϊ�գ�");
			                return false;
			             }
			          }
			       }
			    }
			  }			  
			  if(jgshen==""){
			     alert("����ʡ����Ϊ�գ�");
			     return false;
			  }else{
			    if($('jgshi').options.length>1){
			       if(jgshi==""){
			           alert("�����в���Ϊ�գ�");
			           return false;
			       }else{
			          if($('jgxian').options.length>1){
			             if(jgxian==""){
			                alert("������������Ϊ�գ�");
			                return false;
			             }
			          }
			       }
			    }
			  }
			  if(rxqxxshen==""){
			     alert("��Уǰ��ҵѧУʡ����Ϊ�գ�");
			     return false;
			  }else{
			    if($('rxqxxshi').options.length>1){
			       if(rxqxxshi==""){
			           alert("��Уǰ��ҵѧУ�в���Ϊ�գ�");
			           return false;
			       }else{
			          if($('rxqxxxian').options.length>1){
			             if(rxqxxxian==""){
			                alert("��Уǰ��ҵѧУ��������Ϊ�գ�");
			                return false;
			             }
			          }
			       }
			    }
			  }	
			  if(jtdzshen==""){
			     alert("��ͥ��ַʡ����Ϊ�գ�");
			     return false;
			  }else{
			    if($('jtdzshi').options.length>1){
			       if(jtdzshi==""){
			           alert("��ͥ��ַ�в���Ϊ�գ�");
			           return false;
			       }else{
			          if($('jtdzxian').options.length>1){
			             if(jtdzxian==""){
			                alert("��ͥ��ַ��������Ϊ�գ�");
			                return false;
			             }
			          }
			       }
			    }
			  }			  		  			  
			}
			if(!checkRxByTime()){
				return false;
			}
			if(!checkXsxxjlxx()){
				return false;
			}
			if(!checkXsshgxxx()){
				return false;
			}
			stuinfoSave("stu_info_add.do?method=stuInfoSave&oper=");				
		}		

	function checkRxByTime(){
		if($("rxrq") && $("byny")){
			if($("rxrq")!=null && $("rxrq")!="" 
				&& $("byny")!=null && $("byny")!="")
			return checkSjTj("rxrq","��ѧʱ��","byny","��ҵʱ��")
		}
	}
	function addZpsc(url) {
		viewTempDiv('ѧ����Ƭ�ϴ�','tmpdiv1',400,200);
	}
	
	function showZpscDiv(){
	
		var xh = jQuery("#xh").val();
		
		if(xh == ""){
			alert("Ո�ȾS�o�W̖");
		}else{
			tipsWindown("ϵͳ��ʾ","id:addPic","380","130","true","","true","id");
		}
	}
	</script>
	</head>

	<body
		onload="showButton();addNotNullFiled();loadXsxxjl();loadXsshgx();">
		<html:form action="/stu_info_add" method="post">
			<input type="hidden" value="${oper}" id="oper" />
			<input type="hidden" name="url" id="url"
				value="/sjcz/stu_info_modify.jsp" />
			<input type="hidden" name="variable" id="variable" value="ydinfo" />
			<input type="hidden" name="redirect" id="redirect" value="true" />
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}" />
			<input type="hidden" name="notnull" id="notnull"
				value="xh-xm-bjdm-zydm-xydm-nj" />

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }-ά��</a>
				</p>
			</div>

			<div class="open_win" style="width:100%;height:500px;overflow-x:hidden;overflow-y:auto;vertical-align: top;">
				<table width="100%" border="0" class="formlist" id="rsTable">
					<thead>
						<tr>
							<th colspan="5" onclick="deploy('hi_jbxx')">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_jbxx">
						<tr>
							<th width="10%">
								<span class="red">*</span>ѧ��
							</th>
							<td width="30%">
								<logic:equal value="update" name="oper" >
									<html:hidden name="rs" styleId="xh" property="xh"/>
									<html:text name="rs" styleId="xh" property="xh" disabled="true"
										style="cursor:hand" styleClass="text_nor" />
								</logic:equal>
								<logic:equal value="add" name="oper">
									<html:text name="rs" property="xh" styleId="xh"
										styleClass="text_nor"
										onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="20" />
								</logic:equal>
							</td>
							<th>
								<span class="red">*</span>����
							</th>
							<td>
								<html:hidden name="rs" property="xm" styleId="xm"/>
								<html:text name="rs" property="xm" styleId="xm" maxlength="16" disabled="true"
									styleClass="text_nor" />
							</td>
							<td rowspan="7" class="nohover"
								style="vertical-align:top; text-align:center;">
								<div class="open_img">
									<div id="stuImg">
										<img style="width:120px;height:160px" 
											src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
											border="0">
									</div>
									<br />
									<button
										onclick='showZpscDiv()'
										style="width:100px" class="btn_01">
										�ϴ���Ƭ
									</button>
									<p style="color:#0F5DC1">
										���ͣ�jpg
										<br />
										������С��100k
										<br />
										��С��96*128����
									</p>
								</div>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<html:hidden name="rs" property="xb" styleId="xb"/>
								<html:radio name="rs" property="xb" value="��" disabled="true">��</html:radio>
								<html:radio name="rs" property="xb" value="Ů" disabled="true">Ů</html:radio>
							</td>
							<th>
								��������
							</th>
							<td>
								<html:hidden name="rs" property="csrq" styleId="csrq"/>
								<html:text name="rs" property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');"
									styleClass="text_nor" styleId="csrq" disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:hidden name="rs" property="mz" styleId="mz"/>
								<html:select name="rs" property="mz" styleId="mz"
									style="width:150px" disabled="true">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>
								������ò
							</th>
							<td>
								<html:hidden name="rs" property="zzmm" styleId="zzmm"/>
								<html:select name="rs" property="zzmm" styleId="zzmm"
									style="width:150px" disabled="true">
									<html:options collection="zzmmList" property="zzmmdm"
										labelProperty="zzmmmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								���֤��
							</th>
							<td align="left">
								<html:hidden name="rs" property="sfzh" styleId="sfzh"/>
								<html:text name="rs" property="sfzh" styleId="sfzh" disabled="true"
									styleClass="text_nor"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
							<th>
								�������
							</th>
							<td align="left">
								<html:hidden name="rs" property="pycc" styleId="pycc"/>
								<logic:equal value="new" name="edition">
									<html:select property="pycc" name="rs" styleId="pycc" disabled="true">
										<html:options collection="pyccList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:text name="rs" property="pycc" styleId="pycc" disabled="true"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								�������ڵ�
							</th>
							<td colspan="3">
								<!--��ַ��Ϣȡ��׼��-->
								<logic:present name="ssList">
									<html:hidden name="rs" property="hkshen" styleId="hkshen"/>
									<html:select name="rs" property="hkshen" styleId="hkshen" disabled="true"
										onchange="loadShi('hkshen','hkshi','hkxian');" style="width:120px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="hkshi" styleId="hkshi"/>
							<html:select name="rs" property="hkshi" styleId="hkshi" disabled="true"
										onchange="loadXian('hkshi','hkxian')" style="width:120px">
										<html:options collection="hkshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="hkxian" styleId="hkxian"/>
							<html:select name="rs" property="hkxian" styleId="hkxian" style="width:120px" disabled="true">
										<html:options collection="hkxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end��ַ��Ϣȡ��׼��-->
								<logic:notPresent name="ssList">
									<html:hidden name="rs" property="hkszd" styleId="hkszd"/>
									<html:text name="rs" property="hkszd" maxlength="60" disabled="true"
										styleId="hkszd" styleClass="text_nor" style="width:90%" />
								</logic:notPresent>
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
									<!--��ַ��Ϣȡ��׼��-->
									<logic:present name="ssList">
										<html:hidden name="rs" property="jgshen" styleId="jgshen"/>
										<html:select name="rs" property="jgshen" styleId="jgshen" disabled="true"
											onchange="loadShi('jgshen','jgshi','jgxian');" style="width:120px">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="jgshi" styleId="jgshi"/>
							<html:select name="rs" property="jgshi" styleId="jgshi" disabled="true"
											onchange="loadXian('jgshi','jgxian')" style="width:120px">
											<html:options collection="jgshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="jgxian" styleId="jgxian"/>
							<html:select name="rs" property="jgxian" styleId="jgxian" style="width:120px" disabled="true">
											<html:options collection="jgxianList" property="xiandm"
												labelProperty="xianmc" style="width:120px"/>
										</html:select>
									</logic:present>
									<!--end��ַ��Ϣȡ��׼��-->
									<logic:notPresent name="ssList">
										<html:hidden name="rs" property="jg" styleId="jg"/>
										<html:text name="rs" property="jg" styleId="jg" maxlength="10" disabled="true"
											styleClass="text_nor" style="width:90%" />
									</logic:notPresent>
							</td>
						</tr>
						<tr>
							<th>
								��Դ����
								<br />
								(��Դ��)
							</th>
							<td colspan="3">
								<!--��ַ��Ϣȡ��׼��-->
								<logic:present name="ssList">
									<html:hidden name="rs" property="syshen" styleId="syshen"/>
									<html:select name="rs" property="syshen" styleId="syshen" disabled="true"
										onchange="loadShi('syshen','syshi','syxian')" style="width:120px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="syshi" styleId="syshi"/>
							<html:select name="rs" property="syshi" styleId="syshi" style="width:120px" disabled="true"
										onchange="loadXian('syshi','syxian')">
										<html:options collection="shiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:hidden name="rs" property="syxian" styleId="syxian"/>
							<html:select name="rs" property="syxian" styleId="syxian" style="width:120px" disabled="true">
										<html:options collection="xianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end��ַ��Ϣȡ��׼��-->
								<logic:notPresent name="ssList">
									<html:hidden name="rs" property="syd" styleId="syd"/>
									<html:text name="rs" property="syd" styleId="syd" disabled="true"
										maxlength="25" styleClass="text_nor" style="width:90%" />
								</logic:notPresent>
							</td>
						</tr>
						<!-- �㶫����ְҵ����ѧԺ -->
						<logic:equal value="12578" name="xxdm">
							<tr>
								<th>
									ѧ����
								</th>
								<td>
									<html:text name="rs" property="xjh" styleId="xjh"
										readonly="true" styleClass="text_nor" maxlength="40" />
								</td>
								<th>
									��������
								</th>
								<td colspan="2">
									<html:select property="hkxz" name="rs" styleId="hkxz">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="ũҵ����">ũҵ����</html:option>
										<html:option value="��ũҵ����">��ũҵ����</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									����������ò����
								</th>
								<td>
									<html:text name="rs" property="jrzzmmrq" styleId="jrzzmmrq"
										onclick="return showCalendar('jrzzmmrq','y-mm-dd');"
										readonly="true" styleClass="text_nor" />
								</td>
								<th>
									�Ƿ���
								</th>
								<td colspan="2">
									<html:select property="sfhq" name="rs" styleId="sfhq">
										<html:option value="">--��ѡ��--</html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									�߿�������
								</th>
								<td colspan="4">
									<html:text name="rs" property="ksh" styleId="ksh"
										readonly="true" styleClass="text_nor" maxlength="32" />
								</td>
							</tr>
							<tr>
								<th>
									������
								</th>
								<td colspan="4">
									<%--							<html:text name="rs" property="csd" styleId="csd" styleClass="text_nor" maxlength="60"/>--%>
									<!--��ַ��Ϣȡ��׼��-->
									<logic:present name="ssList">
										<html:select name="rs" property="csds" styleId="csds"
											onchange="loadShi('csds','csdshi','csdxian');">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:select name="rs" property="csdshi" styleId="csdshi"
											onchange="loadXian('csdshi','csdxian')">
											<html:options collection="csdshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<html:select name="rs" property="csdxian" styleId="csdxian">
											<html:options collection="csdxianList" property="xiandm"
												labelProperty="xianmc" />
										</html:select>
									</logic:present>
									<!--end��ַ��Ϣȡ��׼��-->
									<logic:notPresent name="ssList">
										<html:text name="rs" property="hkszd" maxlength="60"
											styleId="hkszd" styleClass="text_nor" style="width:90%" />
									</logic:notPresent>
								</td>
							</tr>
						</logic:equal>
					</tbody>

					<thead>
						<tr>
							<th colspan="5" onclick="deploy('hi_xjxx');">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_xjxx">
						<tr>
							<th width="10%">
								<span class="red">*</span>�꼶
							</th>
							<td width="30%">
								<html:hidden name="rs" property="nj" styleId="nj"/>
								<html:select name="rs" property="nj" styleId="nj" disabled="true"
									style="width:90px" onchange="initXyzybj();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								ѧ��(��)
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="xz" styleId="xz"/>
								<html:text name="rs" property="xz" styleId="xz" disabled="true"
									onkeyup="value=value.replace(/[^\d|.]/g,'')" maxlength="3"
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:hidden name="rs" property="xymc" styleId="xymc"/>
								<input type="text" id="xymc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.xymc }" disabled="disabled"/>
								<%--<button class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
									ѡ��
								</button>
								--%><input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
							</td>
							<th>
								�Ƿ�ע��
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfzc" styleId="sfzc"/>
								<html:select property="sfzc" name="rs" style="width:120px" disabled="true"
									styleId="sfzc">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>רҵ
							</th>
							<td>
								<html:hidden name="rs" property="zymc" styleId="zymc"/>
								<input type="text" id="zymc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.zymc }" disabled="disabled"/>
								<%--<button class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
									style="display: ">
									ѡ��
								</button>
								--%><input type="hidden" name="zydm" tyleId="xydm"
									value="${rs.zydm}" />
							</td>
							<th>
								�Ƿ��߶���
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfzd" styleId="sfzd"/>
								<html:select property="sfzd" name="rs" style="width:120px" disabled="true"
									styleId="sfzd">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�༶
							</th>
							<td>
								<html:hidden name="rs" property="bjmc" styleId="bjmc"/>
								<input type="text" id="bjmc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.bjmc }" disabled="disabled"/>
								<%--<button class="btn_01" id="button_bj" style="display: "
									onclick="getXyzybj('bj');">
									ѡ��
								</button>
								--%><input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
							</td>
							<th>
								�Ƿ���У
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfzx" styleId="sfzx"/>
								<html:select property="sfzx" name="rs" styleId="sfzx" disabled="true">
									<html:option value=""></html:option>
									<html:option value="��У">��У</html:option>
									<html:option value="����У">����У</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								ѧ��״̬
							</th>
							<td>
								<html:hidden name="rs" property="xjzt" styleId="xjzt"/>
								<html:select name="rs" property="xjzt" style="width:150" disabled="true"
									styleId="xjzt">
									<html:option value=""></html:option>
									<html:options collection="xjztList" property="zxdmmc"
										labelProperty="zxdmmc" />
								</html:select>
							</td>
							<th>
								�Ƿ��ҵ��
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfbys" styleId="sfbys"/>
								<html:select property="sfbys" name="rs" style="width:120px" disabled="true"
									styleId="sfbys">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ѧʱ��
							</th>
							<td align="left">
								<html:hidden name="rs" property="rxrq" styleId="rxrq"/>
								<html:text name="rs" property="rxrq" styleId="rxrq" disabled="true"
									maxlength="10" styleClass="text_nor"
									onclick="return showCalendar('rxrq','y-mm-dd');" />
							</td>
							<th>
								�Ƿ��ҵ
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="sfyby" styleId="sfyby"/>
								<html:select property="sfyby" name="rs" styleId="sfyby" disabled="true"
									style="width:120px" styleId="sfyby">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								��ҵʱ��
							</th>
							<td >
								<html:hidden name="rs" property="byny" styleId="byny"/>
								<html:text property="byny" name="rs" styleId="byny" disabled="true"
									styleClass="text_nor" readonly="true" maxlength="10"
									onclick="return showCalendar('byny','y-mm-dd');" />
							</td>
							<th>У����Ϣ</th>
							<td colspan="2">
								<html:hidden name="rs" property="yxdm" styleId="yxdm"/>
								<html:select name="rs" property="yxdm" style="width:150" disabled="true"
									styleId="yxdm">
									<html:option value=""></html:option>
									<html:options collection="yxdmList" property="dm"
										labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="5" onclick="deploy('hi_lxfs');">
								<span>��ϵ��ʽ��֤��</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_lxfs">
						<tr>
							<th>
								��ϵ�绰
							</th>
							<td>
								<html:text name="rs" property="lxdh" styleId="lxdh"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="13" />
							</td>
							<th>
								�ֻ�����
							</th>
							<td colspan="2">
								<html:text name="rs" property="sjhm" styleId="sjhm"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="11" />
							</td>
						</tr>
						<tr>
							<th>
								QQ����
							</th>
							<td>
								<html:text name="rs" property="qqhm" styleId="qqhm"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="20" />
							</td>
							<th>
								��������
							</th>
							<td colspan="2">
								<html:text name="rs" property="dzyx" styleClass="text_nor"
									styleId="dzyx" />
							</td>
						</tr>
						<tr>
							<th>
								��������
							</th>
							<td>
								<html:hidden name="rs" property="yhdm" styleId="yhdm"/>
								<html:select name="rs" property="yhdm" styleId="yhdm" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhdm"
										labelProperty="yhmc" />
								</html:select>
							</td>
							<th>
								���п���
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="yhkh" styleId="yhkh"/>
								<html:text name="rs" property="yhkh" maxlength="20" disabled="true"
									styleId="yhkh" onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" />
							</td>
						</tr>
						<tr>
							<th>
								����Ա
							</th>
							<td>
								<html:text name="rs" property="fdyxm" styleId="fdyxm"
									disabled="true" />
							</td>
							<th>
								����Ա�绰
							</th>
							<td colspan="2">
								<html:text name="rs" property="fdylxdh" styleId="fdylxdh"
									disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								<html:text name="rs" property="bzrxm" styleId="bzrxm"
									disabled="true" />
							</td>
							<th>
								�����ε绰
							</th>
							<td colspan="2">
								<html:text name="rs" property="bzrlxdh" styleId="bzrlxdh"
									disabled="true" />
							</td>
						</tr>
						<tr>
							<th>
								���������
							</th>
							<td>
								<html:text name="rs" property="zlbzrxm" styleId="zlbzrxm"
									disabled="true" />
							</td>
							<th>
								һ��ͨ��
							</th>
							<td colspan="2">
								<html:hidden name="rs" property="kh" styleId="kh"/>
								<html:text name="rs" property="kh" styleId="kh" disabled="true"
									onkeypress="return onlyNum(this,8)" style="ime-mode:disabled"
									maxlength="20" />
							</td>
						</tr>
					</tbody>

					<%-- �°汾��ͥ��Ϣ��ѧ����Ϣ����һ��ά��  --%>
					<logic:equal value="new" name="edition">
						<%@ include file="/xsxx/stu_info_family.jsp"%>
					</logic:equal>
					<%-- end --%>

					<thead>
						<tr>
							<th colspan="5" onclick="deploy('hi_qtxx');">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="hi_qtxx">
						<tr>
							<th>
								����ƴ��
							</th>
							<td>
								<html:text name="rs" property="xmpy" styleId="xmpy"
									maxlength="64" styleClass="text_nor"
									onkeyup="value=value.replace(/[^a-zA-Z]/g,'') " />
							</td>
							<th>
								������
							</th>
							<td align="left" colspan="2">
								<html:text name="rs" property="cym" styleId="cym"
									styleClass="text_nor" maxlength="16" />
							</td>

						</tr>
						<tr>
							<th>
								���(cm)
							</th>
							<td align="left">
								<html:text name="rs" property="sg" styleId="sg"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="3" />
							</td>
							<th>
								����(kg)
							</th>
							<td colspan="2">
								<html:text name="rs" property="tz" styleId="tz"
									onkeyup="value=value.replace(/[^\d|.]/g,'') "
									styleClass="text_nor" maxlength="4" />
							</td>
						</tr>
						<tr>
							<th>
								�س�
							</th>
							<td>
								<html:text name="rs" property="tc" styleId="tc"
									styleClass="text_nor" maxlength="32" />
							</td>
							<th>
								�������
							</th>
							<td align="left" colspan="2">

								<logic:equal value="new" name="edition">
									<html:hidden name="rs" property="kslb" styleId="kslb"/>
									<html:select property="kslb" name="rs" styleId="kslb" disabled="true">
										<html:options collection="kslbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:hidden name="rs" property="kslb" styleId="kslb"/>
									<html:text name="rs" property="kslb" styleId="kslb" disabled="true"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								��ѧ��ʽ
							</th>
							<td align="left">
								<logic:equal value="new" name="edition">
									<html:hidden name="rs" property="rxfs" styleId="rxfs"/>
									<html:select property="rxfs" name="rs" styleId="rxfs" disabled="true">
										<html:options collection="rxfsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:hidden name="rs" property="rxfs" styleId="rxfs"/>
									<html:text name="rs" property="rxfs" styleId="rxfs" disabled="true"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
							<th>
								������ʽ
							</th>
							<td align="left" colspan="2">
								<logic:equal value="new" name="edition">
									<html:hidden name="rs" property="pyfs" styleId="pyfs"/>
									<html:select property="pyfs" name="rs" styleId="pyfs" disabled="true">
										<html:options collection="pyfsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:hidden name="rs" property="pyfs" styleId="pyfs"/>
									<html:text name="rs" property="pyfs" styleId="pyfs" disabled="true"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>

								<th>
									����״��
								</th>
								<td>
									<html:text name="rs" property="jkzk" styleId="jkzk"
											styleClass="text_nor" maxlength="30" />
								</td>
								<th></th>
								<td colspan="2">
									
								</td>
						</tr>

					</tbody>
					<tr>
						<th>
							��ע
						</th>
						<td colspan="4">
							<html:textarea name='rs' property='bz' styleId="bz" style="word-break:break-all;width:99%"
										rows='8' onblur="chLeng(this,2000)"/>
						</td>
					</tr>
					</table>
					</div>
					<div>
					<table width="100%" border="0" class="formlist" id="rsTable">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:notPresent name="details">
										<button class="button2" id="buttonSave"
											style="height:20px;width:80px" onclick="send();">
											�� ��
										</button>
									</logic:notPresent>
									<button class="button2" style="height:20px;width:80px"
										onclick="window.close();return false;">
										�� ��
									</button>
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
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>
			
			<div id="tmpdiv1" style="display:none">
				<iframe name='mainFrame' style='height:100%; width:100%; '
					marginwidth='0' marginheight='0' framespacing='0' frameborder='0'
					scrolling='yes'
					src='uploadPicture.do?method=uploadPicture&type=stu&id=${rs.xh}'></iframe>
			</div>
			<logic:notEmpty name="result">
				<logic:equal value="true" name="result">
					<script>
						alert("�����ɹ���");
						Close();
						if(window.dialogArguments){
							window.dialogArguments.document.getElementById('search_go').click();
						}						
					</script>
				</logic:equal>
				<logic:equal name="result" value="false">
					<script>
						alert("����ʧ��!");
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
