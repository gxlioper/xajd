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
			/*
			if(xxdm=="10653"){//�ɶ�����ѧԺ
				var sjhm = ($("sjhm")&&$("sjhm")!=null)?$("sjhm").value:"";
				var lxdh1 = ($("lxdh1")&&$("lxdh1")!=null)?$("lxdh1").value:"";
				var jtszd = ($("jtszd")&&$("jtszd")!=null)?$("jtszd").value:"";
				if(sjhm==""){
				     alert("�ֻ����벻��Ϊ�գ�");
				     return false;
				}
				if(lxdh1==""){
				     alert("��ͥ�绰����Ϊ�գ�");
				     return false;
				}
				if(jtszd==""){
				     alert("��ͥ��ַ����Ϊ�գ�");
				     return false;
				}
			}
			*/
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
							<th width="12%">
								<span class="red">*</span>ѧ��
							</th>
							<td width="30%">
								<logic:equal value="update" name="oper">
									<html:text name="rs" styleId="xh" property="xh" readonly="true"
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
								<html:text name="rs" property="xm" styleId="xm" maxlength="16"
									styleClass="text_nor" />
							</td>
							<td rowspan="7" class="nohover"
								style="vertical-align:top; text-align:center;">
								<div class="open_img">
									<!--�㽭��ҵְҵ����ѧԺ-->
									<logic:equal value="12865" name="xxdm">
										<img border="0" style="height:133px;width:100px;"
											src="pictures/${rs.sfzh}.jpg" />
									</logic:equal>
									<!--end�㽭��ҵְҵ����ѧԺ-->
									<%--�㽭����ְҵ����ѧԺ--%>
									<logic:equal value="12861" name="xxdm">
										<img
											src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh}"
											border="0" align="absmiddle" style="width:140px;height:160px" />
									</logic:equal>
									<%--end�㽭����ְҵ����ѧԺ--%>
									<%--������Ϣְҵ����ѧԺ--%>
									<logic:equal value="13108" name="xxdm">
										<img
											src="<%=request.getContextPath()%>/sjcz/xszptp.jsp?xh=${rs.xh}"
											border="0" align="absmiddle" style="width:140px;height:160px" />
									</logic:equal>
									<%--end������Ϣְҵ����ѧԺ--%>

									<!--����ѧУ-->
									<logic:notEqual value="12865" name="xxdm">
										<logic:notEqual value="12861" name="xxdm">
											<logic:notEqual value="13108" name="xxdm">
												<div id="stuImg">
										<img style="width:120px;height:160px" 
											src="<%=request.getContextPath()%>/stuPic.jsp?xh=${rs.xh }"
											border="0">
										</div>
										<br />
										<button type="button"
											onclick='showZpscDiv()'
											style="width:100px" class="btn_01">
											�ϴ���Ƭ
										</button>
											</logic:notEqual>
										</logic:notEqual>
									</logic:notEqual>
									<!--end����ѧУ-->
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
								<html:radio name="rs" property="xb" value="��">��</html:radio>
								<html:radio name="rs" property="xb" value="Ů">Ů</html:radio>
							</td>
							<th>
								��������
							</th>
							<td>
								<html:text name="rs" property="csrq"
									onclick="return showCalendar('csrq','y-mm-dd');"
									styleClass="text_nor" styleId="csrq" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<html:select name="rs" property="mz" styleId="mz"
									style="width:150px">
									<html:options collection="mzList" property="mzdm"
										labelProperty="mzmc" />
								</html:select>
							</td>
							<th>
								������ò
							</th>
							<td>
								<html:select name="rs" property="zzmm" styleId="zzmm"
									style="width:150px">
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
								<html:text name="rs" property="sfzh" styleId="sfzh"
									styleClass="text_nor"
									onblur="if(!checkSfzh(this)){this.focus();}" />
							</td>
							<th>
								�������
							</th>
							<td align="left">
								<logic:equal value="new" name="edition">
									<html:select property="pycc" name="rs" styleId="pycc">
										<html:options collection="pyccList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:text name="rs" property="pycc" styleId="pycc"
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
									<html:select name="rs" property="hkshen" styleId="hkshen"
										onchange="loadShi('hkshen','hkshi','hkxian');" style="width:120px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="hkshi" styleId="hkshi"
										onchange="loadXian('hkshi','hkxian')" style="width:120px">
										<html:options collection="hkshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="hkxian" styleId="hkxian" style="width:120px">
										<html:options collection="hkxianList" property="xiandm"
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
						<tr>
							<th>
								����
							</th>
							<td colspan="3">
								<%--����ɽʦ��ѧԺ--%>
								<logic:notEqual value="10649" name="xxdm">
									<!--��ַ��Ϣȡ��׼��-->
									<logic:present name="ssList">
										<html:select name="rs" property="jgshen" styleId="jgshen"
											onchange="loadShi('jgshen','jgshi','jgxian');" style="width:120px">
											<html:option value="">--��ѡ��--</html:option>
											<html:options collection="ssList" property="ssdm"
												labelProperty="ssmc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="jgshi" styleId="jgshi"
											onchange="loadXian('jgshi','jgxian')" style="width:120px">
											<html:options collection="jgshiList" property="shidm"
												labelProperty="shimc" />
										</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="jgxian" styleId="jgxian" style="width:120px">
											<html:options collection="jgxianList" property="xiandm"
												labelProperty="xianmc" style="width:120px"/>
										</html:select>
									</logic:present>
									<!--end��ַ��Ϣȡ��׼��-->
									<logic:notPresent name="ssList">
										<html:text name="rs" property="jg" styleId="jg" maxlength="10"
											styleClass="text_nor" style="width:90%" />
									</logic:notPresent>
								</logic:notEqual>

								<%--��ɽʦ��ѧԺ--%>
								<logic:equal value="10649" name="xxdm">
									<html:select name="rs" property="jgshen" styleId="jgshen"
										onchange="loadShi('jgshen','jgshi','jgxian');">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="jgshi" styleId="jgshi"
										onchange="loadXian('jgshi','jgxian')">
										<html:options collection="jgshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="jgxian" styleId="jgxian">
										<html:options collection="jgxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:equal>
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
									<html:select name="rs" property="syshen" styleId="syshen"
										onchange="loadShi('syshen','syshi','syxian')" style="width:120px">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="syshi" styleId="syshi" style="width:120px"
										onchange="loadXian('syshi','syxian')">
										<html:options collection="shiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<html:select name="rs" property="syxian" styleId="syxian" style="width:120px">
										<html:options collection="xianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
								</logic:present>
								<!--end��ַ��Ϣȡ��׼��-->
								<logic:notPresent name="ssList">
									<html:text name="rs" property="syd" styleId="syd"
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
								<html:select name="rs" property="nj" styleId="nj"
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
								<html:text name="rs" property="xz" styleId="xz"
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
								<%--						<html:select name="rs" property="xydm" styleId="xy"--%>
								<%--							style="width:180px" onchange="initZyList();initBjList()">--%>
								<%--							<html:option value=""></html:option>--%>
								<%--							<logic:notEmpty name="xyList">--%>
								<%--							<html:options collection="xyList" property="xydm"--%>
								<%--								labelProperty="xymc" />--%>
								<%--							 </logic:notEmpty>--%>
								<%--						</html:select>--%>
								<input type="text" id="xymc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.xymc }" />
								<button type="button" class="btn_01" onclick="getXyzybj('xy');" id="button_xy">
									ѡ��
								</button>
								<input type="hidden" name="xydm" id="xydm" value="${rs.xydm}" />
							</td>
							<th>
								�Ƿ�ע��
							</th>
							<td colspan="2">
								<html:select property="sfzc" name="rs" style="width:120px"
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
								<%--						<html:select name="rs" property="zydm" style="width:180px"--%>
								<%--							styleId="zy" onchange="initBjList();">--%>
								<%--							<html:option value=""></html:option>--%>
								<%--							<logic:notEmpty name="zyList">--%>
								<%--								<html:options collection="zyList" property="zydm"--%>
								<%--									labelProperty="zymc" />--%>
								<%--							</logic:notEmpty>--%>
								<%--						</html:select>--%>
								<input type="text" id="zymc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.zymc }" />
								<button type="button" class="btn_01" onclick="getXyzybj('zy');" id="button_zy"
									style="display: ">
									ѡ��
								</button>
								<input type="hidden" name="zydm" tyleId="xydm"
									value="${rs.zydm}" />
							</td>
							<th>
								�Ƿ��߶���
							</th>
							<td colspan="2">
								<html:select property="sfzd" name="rs" style="width:120px"
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
								<%--						<html:select name="rs" property="bjdm" style="width:180px" styleId="bj">--%>
								<%--							<html:option value=""></html:option>--%>
								<%--							<logic:notEmpty name="bjList">--%>
								<%--								<html:options collection="bjList" property="bjdm"--%>
								<%--									labelProperty="bjmc" />--%>
								<%--							</logic:notEmpty>--%>
								<%--						</html:select>--%>
								<input type="text" id="bjmc"
									onkeydown="return onlyBackSpace(this,event);" value="${rs.bjmc }" />
								<button type="button" class="btn_01" id="button_bj" style="display: "
									onclick="getXyzybj('bj');">
									ѡ��
								</button>
								<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm}" />
							</td>
							<th>
								�Ƿ���У
							</th>
							<td colspan="2">
								<html:select property="sfzx" name="rs" styleId="sfzx">
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
								<html:select name="rs" property="xjzt" style="width:150"
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
								<html:select property="sfbys" name="rs" style="width:120px"
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
								<html:text name="rs" property="rxrq" styleId="rxrq"
									maxlength="10" styleClass="text_nor"
									onclick="return showCalendar('rxrq','y-mm-dd');" />
							</td>
							<th>
								�Ƿ��ҵ
							</th>
							<td colspan="2">
								<html:select property="sfyby" name="rs" styleId="sfyby"
									style="width:120px" styleId="sfyby">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<!-- �㽭�Ƽ�ѧԺ ���ӱ�ҵ֤�ź�ѧλ֤�� -->
						<logic:equal name="xxdm" value="11057">
							<logic:notEqual name="sfzxk" value="no">
								<tr>
									<th>
										��ҵʱ��
									</th>
									<td>
										<html:text property="byny" name="rs" styleId="byny"
											styleClass="text_nor" readonly="true" maxlength="10"
											onclick="return showCalendar('byny','y-mm-dd');" />
									</td>
									<th></th>
									<td colspan="3">

									</td>
								</tr>
							</logic:notEqual>
							<logic:equal name="sfzxk" value="no">
								<tr>
									<th>
										��ҵʱ��
									</th>
									<td>
										<html:text property="byny" name="rs" styleId="byny"
											styleClass="text_nor" readonly="true" maxlength="10"
											onclick="return showCalendar('byny','y-mm-dd');" />
									</td>
									<th>
										��ҵ֤��
									</th>
									<td colspan="2">
										<html:text property="byzh" name="rs" styleId="byzh"
											styleClass="text_nor" maxlength="30" onkeyup="checkKey(this)" />
									</td>
								</tr>
								<tr>
									<th>
										ѧλ֤��
									</th>
									<td colspan="4">
										<html:text property="xwzsbh" name="rs" styleId="xwzsbh"
											styleClass="text_nor" maxlength="20" onkeyup="checkKey(this)" />
									</td>
								</tr>
							</logic:equal>
						</logic:equal>
						<!-- �㽭�Ƽ�ѧԺ ���ӱ�ҵ֤�ź�ѧλ֤�� end -->

						<!-- ������ְͨҵ����ѧԺ -->
						<logic:equal value="12752" name="xxdm">
							<tr>
								<th>
									��ҵʱ��
								</th>
								<td>
									<html:text property="byny" name="rs" styleId="byny"
										styleClass="text_nor" readonly="true" maxlength="10"
										onclick="return showCalendar('byny','y-mm-dd');" />
								</td>
								<th>
									������
								</th>
								<td colspan="2">
									<html:text property="ksh" name="rs" styleId="ksh"
										styleClass="text_nor" maxlength="32"
										onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'') " />
								</td>
							</tr>
						</logic:equal>


						<!-- �Ǻ�����ְͨҵ����ѧԺ -->
						<logic:notEqual name="xxdm" value="12752">
							<!-- ���㽭�Ƽ�ѧԺ -->
							<logic:notEqual name="xxdm" value="11057">
								<tr>
									<th>
										��ҵʱ��
									</th>
									<td >
										<html:text property="byny" name="rs" styleId="byny"
											styleClass="text_nor" readonly="true" maxlength="10"
											onclick="return showCalendar('byny','y-mm-dd');" />
									</td>
									<th>У����Ϣ</th>
									<td colspan="2">
										<html:select name="rs" property="yxdm" style="width:150"
											styleId="yxdm">
											<html:option value=""></html:option>
											<html:options collection="yxdmList" property="dm"
												labelProperty="xqmc" />
										</html:select>
									</td>
								</tr>
							</logic:notEqual>
						</logic:notEqual>
						<!-- ���㽭�Ƽ�ѧԺend -->
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
								<!-- ��ְҵ����ѧԺ -->
								<logic:equal name="xxdm" value="12061">
								�̺�
								</logic:equal>
								<!-- end��ְҵ����ѧԺ -->
								<logic:notEqual name="xxdm" value="12061">
								�̶��绰
								</logic:notEqual>
							</th>
							<td>
								<html:text name="rs" property="lxdh" styleId="lxdh"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="13" />
							</td>
							<th width="12%">
								�ֻ�����
							</th>
							<td colspan="2">
								<html:text name="rs" property="sjhm" styleId="sjhm"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" maxlength="11" />
							</td>
						</tr>
						<!-- 
						<tr>
							<th>
								������
							</th>
							<td>
								<%--������������ѧԺ--%>
								<logic:equal value="11626" name="xxdm">
									<html:text property="ssbh" name="rs" styleId="ssbh"
										maxlength="10" />
								</logic:equal>
								<logic:notEqual value="11626" name="xxdm">
									<html:text name="rs" property="ssbh" styleId="ssbh"
										disabled="true" />
								</logic:notEqual>
							</td>
							<th>
								���ҵ绰
							</th>
							<td colspan="2">
								<%--������������ѧԺ--%>
								<logic:equal value="11626" name="xxdm">
									<html:text property="qsdh" name="rs" styleId="qsdh"
										maxlength="20" onkeyup="value=value.replace(/[^\d|-]/g,'') " />
								</logic:equal>
								<logic:notEqual value="11626" name="xxdm">
									<html:text name="rs" property="qsdh" styleId="qsdh"
										disabled="true" />
								</logic:notEqual>
							</td>
						</tr>
						 -->
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
								<html:select name="rs" property="yhdm" styleId="yhdm">
									<html:option value=""></html:option>
									<html:options collection="yhList" property="yhdm"
										labelProperty="yhmc" />
								</html:select>
							</td>
							<th>
								���п���
							</th>
							<td colspan="2">
								<html:text name="rs" property="yhkh" maxlength="20"
									styleId="yhkh" onkeyup="value=value.replace(/[^\d]/g,'') "
									styleClass="text_nor" />
							</td>
						</tr>
						<!-- 
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
								<html:text name="rs" property="kh" styleId="kh"
									onkeypress="return onlyNum(this,8)" style="ime-mode:disabled"
									maxlength="20" />
							</td>
						</tr>
						 -->
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
						<logic:equal name="xxdm" value="12862">
							<tr>
								<th>
									��Χ(cm)
								</th>
								<td align="left">
									<html:text name="rs" property="xsxw" styleId="xsxw"
										onkeyup="value=value.replace(/[^\d]/g,'') "
										styleClass="text_nor" maxlength="3" />
								</td>
								<th>
									Ь�ӳ���(��)
								</th>
								<td colspan="2">
									<html:text name="rs" property="xzcm" styleId="xzcm"
										onkeyup="value=value.replace(/[^\d|.]/g,'') "
										styleClass="text_nor" maxlength="3" />
								</td>
							</tr>
						</logic:equal>
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
									<html:select property="kslb" name="rs" styleId="kslb">
										<html:options collection="kslbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:text name="rs" property="kslb" styleId="kslb"
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
									<html:select property="rxfs" name="rs" styleId="rxfs">
										<html:options collection="rxfsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:text name="rs" property="rxfs" styleId="rxfs"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
							<th>
								������ʽ
							</th>
							<td align="left" colspan="2">
								<logic:equal value="new" name="edition">
									<html:select property="pyfs" name="rs" styleId="pyfs">
										<html:options collection="pyfsList" property="dm"
											labelProperty="mc" />
									</html:select>
								</logic:equal>
								<logic:notEqual value="new" name="edition">
									<html:text name="rs" property="pyfs" styleId="pyfs"
										styleClass="text_nor" maxlength="32" />
								</logic:notEqual>
							</td>
						</tr>
						<tr>

							<logic:notEqual value="10657" name="xxdm">
								<logic:notEqual name="xxdm" value="11654">
									<th>
										����״��
									</th>
								</logic:notEqual>
								<logic:equal name="xxdm" value="11654">
									<th>
										���޲�ʷ
									</th>
								</logic:equal>
								<td>
									<html:text name="rs" property="jkzk" styleId="jkzk"
											styleClass="text_nor" maxlength="30" />
								</td>
								<logic:equal value="12862" name="xxdm">
									<th>�Ƿ������</th>
									<td colspan="2">
										<html:select property="sfhq" name="rs" style="width:120px"
											styleId="sfhq">
											<html:option value=""></html:option>
											<html:option value="��">��</html:option>
											<html:option value="��">��</html:option>
										</html:select>
									</td>
								</logic:equal>
								<logic:notEqual value="12862" name="xxdm">
									<th></th>
									<td colspan="2">
										
									</td>
								</logic:notEqual>
							</logic:notEqual>
						</tr>


						<!-- �Ͼ���ʦ -->
						<logic:equal name="xxdm" value="8001">
							<tr>
								<th>
									��������
								</th>
								<td>
									<html:select name="rs" property="hkxz" style="" onchange=""
										styleId="hkxz">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="hkxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									��ѧǰ�Ļ��̶�
								</th>
								<td colspan="2">
									<html:select property="rxqwhcd" name="rs" styleId="rxqwhcd">
										<html:option value="">----��ѡ��----</html:option>
										<html:options collection="rxqwhcdList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									ע��˳�����
								</th>
								<td>
									<html:text name="rs" property="zcsxhm"
										onkeypress="return onlyNum(this,10)" style="ime-mode:disabled"
										styleClass="text_nor" styleId="zcsxhm" />
								</td>
								<th></th>
								<td colspan="2">

								</td>
							</tr>
						</logic:equal>

						<%--��ɽʦ��ѧԺ--%>
						<logic:equal value="10649" name="xxdm">
							<tr>
								<th>
									��Уǰ�ı�ҵѧУ
								</th>
								<td colspan="4">
									<html:select name="rs" property="rxqxxshen" styleId="rxqxxshen"
										onchange="loadShi('rxqxxshen','rxqxxshi','rxqxxxian')">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
									<html:select name="rs" property="rxqxxshi" styleId="rxqxxshi"
										onchange="loadXian('rxqxxshi','rxqxxxian')">
										<html:options collection="rxqxxshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
									<html:select name="rs" property="rxqxxxian" styleId="rxqxxxian">
										<html:options collection="rxqxxxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
									ѧУ���ƣ�
									<html:text name="rs" property="rxqbyxx" styleId="rxqbyxx"
										styleClass="text_nor"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									��ͥ��ϸ��ַ
								</th>
								<td colspan="4">
									<html:select name="rs" property="jtdzshen" styleId="jtdzshen"
										onchange="loadShi('jtdzshen','jtdzshi','jtdzxian')">
										<html:option value="">--��ѡ��--</html:option>
										<html:options collection="ssList" property="ssdm"
											labelProperty="ssmc" />
									</html:select>
									<html:select name="rs" property="jtdzshi" styleId="jtdzshi"
										onchange="loadXian('jtdzshi','jtdzxian')">
										<html:options collection="jtdzshiList" property="shidm"
											labelProperty="shimc" />
									</html:select>
									<html:select name="rs" property="jtdzxian" styleId="jtdzxian">
										<html:options collection="jtdzxianList" property="xiandm"
											labelProperty="xianmc" />
									</html:select>
									��������ֵ��ţ�
									<html:text name="rs" property="mph" styleId="mph"
										styleClass="text_nor"></html:text>
								</td>
							</tr>
						</logic:equal>
						<%--end��ɽʦ��ѧԺ--%>

						<!--�й����ʴ�ѧ-->
						<logic:equal value="10491" name="xxdm">
							<tr>
								<th>
									ѧ������
								</th>
								<td>
									<html:select property="xslx" name="rs" style="width:120px"
										styleId="xslx">
										<html:option value=""></html:option>
										<html:options collection="xsLxList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									ѧ�����
								</th>
								<td colspan="2">
									<html:select property="xslb" name="rs" style="width:120px"
										styleId="xslb">
										<html:option value=""></html:option>
										<html:options collection="xsLbList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									��ҵѧУ
								</th>
								<td colspan="4">
									<html:text name="rs" property="rxqdw" styleId="rxqdw"
										styleClass="text_nor" maxlength="50" />
								</td>
							</tr>
						</logic:equal>
						<!--end�й����ʴ�ѧ-->

						<!-- ���� ����ѧ�麣ѧԺ -->
						<logic:equal value="13675" name="xxdm" scope="session">
							<tr>
								<th>
									�Ƿ񱨵�
								</th>
								<td>
									${rs.sfbd}
								</td>
								<th>
									�ɷ����
								</th>
								<td colspan="2">
									${rs.jfqk }
								</td>
							</tr>
						</logic:equal>
						<!-- end���� ����ѧ�麣ѧԺ -->
						<%--���Ϲ�ҵ��ѧ--%>
						<logic:equal value="11535" name="xxdm">
							<tr>
								<th>
									�����Ƿ�����
								</th>
								<td>
									<html:select property="dasfyl" name="rs" styleId="dasfyl">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th>
									��������ԭ��
								</th>
								<td colspan="2">
									<html:text property="daylyy" name="rs" styleId="daylyy"
										maxlength="150" style="width:100%" styleClass="text_nor" />
								</td>
							</tr>
						</logic:equal>
						<%-- ��ɳ����--%>
						<logic:equal value="10827" name="xxdm">
							<tr>
								<th>
									��ҵ֤����״̬
								</th>
								<td colspan="4">
									<html:select property="byzffztdm" name="rs" styleId="byzffztdm">
										<html:option value=""></html:option>
										<html:options collection="byzffztList" property="byzffztdm"
											labelProperty="byzffztmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<%--�������ϴ�ѧ--%>
						<logic:equal value="11417" name="xxdm">
							<tr>
								<th>
									�Ƿ��ڷ�У
								</th>
								<td>
									<html:select property="sfzfx" name="rs" style="width:120px"
										styleId="sfzfx">
										<html:option value=""></html:option>
										<html:option value="��">��</html:option>
										<html:option value="��">��</html:option>
									</html:select>
								</td>
								<th>
									�ڽ���Ϣ
								</th>
								<td colspan="2">
									<html:select property="zjdm" name="rs" style="width:180px"
										styleId="zjdm">
										<html:option value=""></html:option>
										<html:options collection="zjList" property="zjdm"
											labelProperty="zjmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<%--end�������ϴ�ѧ--%>
						<%--������ְͨҵ����ѧԺ--%>
						<logic:equal value="12752" name="xxdm">
							<tr>
								<th>
									������
								</th>
								<td>
									<html:text property="dah" name="rs" styleId="dah"
										styleClass="text_nor" maxlength="30" />
								</td>
								<th>
									ҽ�Ʊ��պ�
								</th>
								<td colspan="2">
									<html:text property="ylbxh" name="rs" styleId="ylbxh"
										styleClass="text_nor" maxlength="30" />
								</td>
							</tr>
						</logic:equal>
						<%--end������ְͨҵ����ѧԺ--%>

						<!--���ְͨҵѧԺ-->
						<logic:equal value="12883" name="xxdm">
							<tr>
								<td colspan="4">
									<%@ include file="/xsxx/xxjlxxb.jsp"%>
								</td>
							</tr>
						</logic:equal>
						<!--end���ְͨҵѧԺ-->

						<%--����ְҵ����ѧԺ--%>
						<logic:equal value="11355" name="xxdm">
							<tr>
								<th>
									������
								</th>
								<td>
									<html:text property="ksh" name="rs" styleId="ksh"
										styleClass="text_nor" maxlength="32"
										onkeyup="value=value.replace(/[^a-zA-Z|\d]/g,'') " />
								</td>
								<th></th>
								<td colspan="2">

								</td>
							</tr>
						</logic:equal>
						<%--end����ְҵ����ѧԺ--%>
					</tbody>
					<!--������������-->
					<%@ include file="/xsxx/bjqnzzxy/xsxx_bjqnzzxy.jsp"%>
					<!--��������-->
					<%@ include file="/xsxx/fjgcxy/xsxx_fjgcxy.jsp"%>
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
										<button type="button" class="button2" id="buttonSave"
											style="height:20px;width:80px" onclick="send();">
											�� ��
										</button>
									</logic:notPresent>
									<!--�޸Ĳ���-->
									<%--						<logic:equal value="update" name="oper">--%>
									<%--						<button type="button" class="button2" id="buttonUp" style="height:20px;width:80px"--%>
									<%--							onclick="nextOrUp()">--%>
									<%--							��һҳ--%>
									<%--						</button>			--%>
									<%--						<button type="button" class="button2" id="buttonNext" style="height:20px;width:80px"--%>
									<%--							onclick="nextOrUp('N')">--%>
									<%--							��һҳ--%>
									<%--						</button>	--%>
									<%--						</logic:equal>--%>
									<button type="button" class="button2" style="height:20px;width:80px"
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
