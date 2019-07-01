<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//ˢ��ҳ��
		function reflashShxxDetail(){
			
			var xh = $("xh").value;
			var xmdm = $("xmdm").value;

			var url = "szgyyq_mypj_tea.do?method=fsshDetail";
				url+="&xh="+xh;
				url+="&xmdm="+xmdm;
				
			refreshForm(url)
		}
		
		//������˷�
		function saveShf(){
		
			//�û�����
			var yhlx = $("yhlx").value;
			//��Ŀ����
			var xmdm = $("xmdm").value;
			
			var flag = true;
			
			if(yhlx == "bz" || yhlx == "bzr"){//������ �� �೤
			
				var num = jQuery("input[name=bzrshf]").length;
				
				for(var i=0;i<num;i++){
					var obj = jQuery("input[name=bzrshf]")[i];
					if(obj.value == ""){
						alertError("��"+(i+1)+"�а�������˷�Ϊ�գ���ȷ��");
						flag = false;
					}
				}
				
				var url="szgyyq_mypj_tea.do?method=saveShf";
				
				//ID
				var i = 0; 
				var id = new Array();   				  
				jQuery("input[name=checkVal][disabled!=true]").each(function(){if(!this.disabled){id[i] = jQuery(this).val();i++;}});
				
				//��������˷�
				i = 0; 
				var bzrshf = new Array();   				  
				jQuery("input[name=bzrshf]").each(function(){bzrshf[i] = jQuery(this).val();i++;});
				
				//����
			 	var parameter = {
			 		"xmdm":xmdm,
			 		"yhlx":yhlx,
					"id":id.join("!!@@!!"),
					"bzrshf":bzrshf.join("!!@@!!")
				};
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,function(result){saveSuccess(result)});
			}else if(yhlx == "xy"){//ѧԺ
				var num = jQuery("input[name=xyshf]").length;
				
				for(var i=0;i<num;i++){
					var obj = jQuery("input[name=xyshf]")[i];
					if(obj.value == ""){
						alertError("��"+(i+1)+"��"+jQuery("#xbmc").val()+"��˷�Ϊ�գ���ȷ��");
						flag = false;
					}
				}
				
				var url="szgyyq_mypj_tea.do?method=saveShf";
				
				//ID
				var i = 0; 
				var id = new Array();   			  
				jQuery("input[name=checkVal][disabled!=true]").each(function(){id[i] = jQuery(this).val();i++;});
				
				//ѧԺ��˷�
				i = 0; 
				var xyshf = new Array();   				  
				jQuery("input[name=xyshf]").each(function(){xyshf[i] = jQuery(this).val();i++;});
				
				//����
			 	var parameter = {
			 		"xmdm":xmdm,
			 		"yhlx":yhlx,
					"id":id.join("!!@@!!"),
					"xyshf":xyshf.join("!!@@!!")
				};
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,function(result){saveSuccess(result)});
				
			}else if(yhlx == "xx"){//ѧУ
				var num = jQuery("input[name=xxshf]").length;
				
				for(var i=0;i<num;i++){
					var obj = jQuery("input[name=xxshf]")[i];
					if(obj.value == ""){
						alertError("��"+(i+1)+"��ѧУ��˷�Ϊ�գ���ȷ��");
						flag = false;
					}
				}
				
				var url="szgyyq_mypj_tea.do?method=saveShf";
				
				//ID
				var i = 0; 
				var id = new Array();   				  
				jQuery("input[name=checkVal][disabled!=true]").each(function(){id[i] = jQuery(this).val();i++;});
				
				//ѧУ��˷�
				i = 0; 
				var xxshf = new Array();   				  
				jQuery("input[name=xxshf]").each(function(){xxshf[i] = jQuery(this).val();i++;});
				
				//����
			 	var parameter = {
			 		"xmdm":xmdm,
			 		"yhlx":yhlx,
					"id":id.join("!!@@!!"),
					"xxshf":xxshf.join("!!@@!!")
				};
				
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,function(result){saveSuccess(result)});
			}
		}
		
		//����ɹ�
		function saveSuccess(result){
		
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			alertInfo(result,function(tag){
				if(tag == "ok"){
				
				}
			});
		}
		
		//�������״̬
		function saveShzt(shzt){
		
			var flag = true;
			var num = jQuery("input[name=checkVal]:checked").length;
			
			if(num == 0){
				alertError("�빴ѡϣ����˵ļ�¼");
				flag = false;
			}
			
			if(flag){
				
				//�û�����
				var yhlx = $("yhlx").value;
				//��Ŀ����
				var xmdm = $("xmdm").value;

			
				if(yhlx == "bz" || yhlx == "bzr"){//�����λ�೤
				
					var num = jQuery("input[name=bzrshf]").length;
					
					for(var i=0;i<num;i++){
						var obj = jQuery("input[name=bzrshf]")[i];
						if(obj.value == ""){
							alertError("��"+(i+1)+"�а�������˷�Ϊ�գ���ȷ��");
							flag = false;
						}
					}
					
					var url="szgyyq_mypj_tea.do?method=saveShzt";
					
					var i = 0;
					//ID
					var id = new Array();
					//��������˷�
					var bzrshf = new Array();   						  
					jQuery("input[name=checkVal]:checked").each(
					function(){
						id[i] = jQuery(this).val();
						bzrshf[i] = $("bzrshf_"+jQuery(this).val()).value;
						i++;
					});
					
					//����
				 	var parameter = {
				 		"xmdm":xmdm,
				 		"yhlx":yhlx,
				 		"shzt":shzt,
						"id":id.join("!!@@!!"),
						"bzrshf":bzrshf.join("!!@@!!")
					};
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,function(result){shSuccess(result)});
					
				}else if(yhlx == "xy"){//ѧԺ
				
					var num = jQuery("input[name=xyshf]").length;
					
					for(var i=0;i<num;i++){
						var obj = jQuery("input[name=xyshf]")[i];
						if(obj.value == ""){
							alertError("��"+(i+1)+"��ѧԺ��˷�Ϊ�գ���ȷ��");
							flag = false;
						}
					}
					
					var url="szgyyq_mypj_tea.do?method=saveShzt";
					
					var i = 0;
					//ID
					var id = new Array();
					//ѧԺ��˷�
					var xyshf = new Array();   						  
					jQuery("input[name=checkVal]:checked").each(
					function(){
						id[i] = jQuery(this).val();
						xyshf[i] = $("xyshf_"+jQuery(this).val()).value;
						i++;
					});
					
					//����
				 	var parameter = {
				 		"xmdm":xmdm,
				 		"yhlx":yhlx,
				 		"shzt":shzt,
						"id":id.join("!!@@!!"),
						"xyshf":xyshf.join("!!@@!!")
					};
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,function(result){shSuccess(result)});
					
				}else if(yhlx == "xx"){//ѧУ
				
					var num = jQuery("input[name=xxshf]").length;
					
					for(var i=0;i<num;i++){
						var obj = jQuery("input[name=xxshf]")[i];
						if(obj.value == ""){
							alertError("��"+(i+1)+"��ѧУ��˷�Ϊ�գ���ȷ��");
							flag = false;
						}
					}
					
					var url="szgyyq_mypj_tea.do?method=saveShzt";
					
					var i = 0;
					//ID
					var id = new Array();
					//ѧУ��˷�
					var xxshf = new Array();   						  
					jQuery("input[name=checkVal]:checked").each(
					function(){
						id[i] = jQuery(this).val();
						xxshf[i] = $("xxshf_"+jQuery(this).val()).value;
						i++;
					});
					
					//����
				 	var parameter = {
				 		"xmdm":xmdm,
				 		"yhlx":yhlx,
				 		"shzt":shzt,
						"id":id.join("!!@@!!"),
						"xxshf":xxshf.join("!!@@!!")
					};
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,function(result){shSuccess(result)});
					
				}
			}
		}
		
		//����ɹ�
		function shSuccess(result){
		
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			alertInfo(result,function(tag){
				if(tag == "ok"){
					$("btn_sx").click();
				}
			});
		}
		
		//��ʾѧ��Ͷ��Div
		function showXsssDiv(id,ssnr,sssj,clr,clyj){
			$("ssnr").value = ssnr;
			$("xmid").value = id;
			$("p_sssj").innerHTML = sssj;			
			$("p_clr").innerHTML = clr;
			$("clyj").value = clyj;
			
			if(clr == ""){
				$("btn_bc").style.display = "";
			}else{
				$("btn_bc").style.display = "none";
			}
			tipsWindown("ϵͳ��ʾ","id:div_xsss","350","380","true","","true","id");
		}
		
		//����Ͷ�ߴ���
		function saveSscl(){
		
			var clyj = $("clyj").value;
			
			if(clyj == ""){
				alertError("�����������Ϊ�գ���ȷ�ϣ�");
				return false;
			}
			
			var url="szgyyq_mypj_tea.do?method=saveSscl";
			$("divWaiting").style.display="";
			$("divDisable").style.display="";

			var xn = $("xn").value;
			var xq = $("xq").value;
			var xh = $("xh").value;
			var xmid = $("xmid").value;
			var xmlx = $("xmdm").value;
			
			//����
		 	var parameter = {
		 		"xn":xn,
		 		"xq":xq,
				"xh":xh,
				"xmid":xmid,
				"xmlx":xmlx,
				"clyj":escape(clyj)
			};

			jQuery.post(url,parameter,function(result){doSuccess(result);});
		}
		
				
		//ִ�гɹ�
		function doSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result,function(tag){				
				if(tag == "ok"){
					closeWindown();
					$("btn_sx").click();}});
		}
		
		function setMrf(){
			var yhlx=$("yhlx").value;
			var xmdm=$("xmdm").value;
			var bzrlrf=document.getElementsByName("bzrshf");
			var xylrf=document.getElementsByName("xyshf");
			var xxlrf=document.getElementsByName("xxshf");
			var mrf=document.getElementsByName("mrf");
			for(var i=0;i<mrf.length;i++){
				if(bzrlrf[i] || xylrf[i] || xxlrf[i]){
					if($("yhlx").value=="bz"){
						bzrlrf[i].value=mrf[i].value;
					}else if($("yhlx").value=="bzr"){
						bzrlrf[i].value=mrf[i].value;
					}else if($("yhlx").value=="xy"){
						xylrf[i].value=mrf[i].value;
					}else if($("yhlx").value=="xx"){
						xxlrf[i].value=mrf[i].value;
					}
				}
			}
			
		}
		
		</script>
	</head>
	<body onload="" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ۺ��������ɿ� - �ҵĹ��� - ������ϸ</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc}</font>ѧ�� չ���ġ�</br>
				2.������н�չʾ��һ�����ͨ���ļ�¼��</br>
				3.�����һ�����û��Ѿ���˹�����ô���������ɲ�����</br>
				4.���<bean:message key="lable.xb" />�û����ͨ����ѧУ�û�δ��˵Ļ�����ô������¼�ķ���Ҳ���������շ�����</br>
				5.������״̬Ϊ<font color="blue">������</font>�Ļ��������ü�¼���ϼ��û��˻أ���Ҫ������ˡ�
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/szgyyq_mypj_tea" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xn" id="xn" value="${xn }"/>
			<input type="hidden" name="xq" id="xq" value="${xq }"/>
			<input type="hidden" name="xh" id="xh" value="${xh }"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" name="shxm" id="shxm" value="${xmdm }"/>
			<input type="hidden" name="xmid" id="xmid" value=""/>
			<input type="hidden" name="yhlx" id="yhlx" value="${yhlx }"/>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<!-- ˢ��  -->
			<button type="button" id="btn_sx" onclick="reflashShxxDetail()" style="display:none">
				ˢ��
			</button>
			<!-- ������ end-->
			
			<!-- ѧ��������Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>${xmmc }������
								<logic:notEmpty name="zhcpsd">
									��������(${zhcpsd.zxmjcf }) �ܷ�(${zhcpsd.zxmzgf })��
								</logic:notEmpty>
							</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">ѧ��</th>
						<td width="30%">
							${stuInfo.xh }
						</td>
						<th width="20%">����</th>
						<td width="30%">
							${stuInfo.xm }
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div style="height:360px;overflow-x:auto;overflow-y:auto;">
								<table class="dateline" width="100%">
								
									<!-- ���� -->
							    	<thead>
										<tr>
											<td>
												<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
											</td>
											<logic:iterate id="tit" name="topTr">
												<td>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
							      		</tr>
									</thead>
									<!-- ���� end-->
										
									<!-- 1.���� -->
									<logic:equal name="xmdm" value="szyq_dshdjzb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.alldsmc }">
													<!-- �������� -->
													${info.dsmc }
												</td>
												<td>
													<!-- �������� -->
													${info.dsrq }
												</td>
												<td title="${info.alldsxd }">
													<!-- �����ĵ� -->
													${info.dsxd }
												</td>
												<td>
													<!-- �Ƿ�� -->
													${info.sfhj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<!-- �೤�û� -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �೤�û� end-->
												
												<!-- �������û� -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �������û� end-->
												
												<!-- ѧԺ�û� -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xysh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧԺ�û� end-->
												
												<!-- ѧУ�û� -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														${info.xyshf }
													</td>
													<td>
														<!-- ѧУ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xxsh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧУ�û� end-->
												<logic:equal name="info" property="kfsh" value="yes">
												<logic:equal name="yhlx" value="bz">
													<input type="hidden" name="mrf" value="${info.sqf }"/>
												</logic:equal>
												<logic:equal name="yhlx" value="bzr">
													<input type="hidden" name="mrf" value="${info.sqf }"/>
												</logic:equal>
												<logic:equal name="yhlx" value="xy">
													<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
												</logic:equal>
												<logic:equal name="yhlx" value="xx">
													<input type="hidden" name="mrf" value="${info.xylrf }"/>
												</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- ���� end-->
									
									<!--2.���Ա��  start-->
									<logic:equal name="xmdm" value="szyq_yybdjzb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allyybdnr }">
													<!-- ���Ա������ -->
													${info.yybdnr }
												</td>
												<td>
													<!-- ���� -->
													${info.xthdrq }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<!-- �೤�û� -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �೤�û� end-->
												
												<!-- �������û� -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �������û� end-->
												
												<!-- ѧԺ�û� -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xysh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧԺ�û� end-->
												
												<!-- ѧУ�û� -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														${info.xyshf }
													</td>
													<td>
														<!-- ѧУ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xxsh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧУ�û� end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--2.���Ա��  end-->
									
									<!--3.ivt��̳  start -->
									<logic:equal name="xmdm" value="szyq_ivtltb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.alljztm }">
													<!-- ������Ŀ -->
													${info.jztm }
												</td>
												<td>
													<!-- ���� -->
													${info.xthdrq }
												</td>
												<td>
													<!-- �����Ǽ�-->
													${info.jcdj }
												</td>
												<td>
													<!-- �����Ǽ� -->
													${info.ccdj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<!-- �೤�û� -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �೤�û� end-->
												
												<!-- �������û� -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �������û� end-->
												
												<!-- ѧԺ�û� -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xysh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧԺ�û� end-->
												
												<!-- ѧУ�û� -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														${info.xyshf }
													</td>
													<td>
														<!-- ѧУ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xxsh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧУ�û� end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--3.ivt��̳  end -->
									
									<!--4.���� start -->
									<logic:equal name="xmdm" value="szyq_xthddjb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allhdnr }">
													<!-- ����� -->
													${info.hdnr }
												</td>
												<td>
													<!-- ���� -->
													${info.xthdrq }
												</td>
												<td>
													<!-- �����ȼ� -->
													${info.jldj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<!-- �೤�û� -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �೤�û� end-->
												
												<!-- �������û� -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �������û� end-->
												
												<!-- ѧԺ�û� -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xysh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧԺ�û� end-->
												
												<!-- ѧУ�û� -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														${info.xyshf }
													</td>
													<td>
														<!-- ѧУ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xxsh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧУ�û� end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--4.���� end -->
									
									<!--5.��֯����  start-->
									<logic:equal name="xmdm" value="szyc_zznlfzb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allhdzt }">
													<!-- ����� -->
													${info.hdzt }
												</td>
												<td>
													<!-- ����� -->
													${info.hdrq }
												</td>
												<td>
													<!-- ��ȼ� -->
													${info.hddj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<!-- �೤�û� -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �೤�û� end-->
												
												<!-- �������û� -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �������û� end-->
												
												<!-- ѧԺ�û� -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xysh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧԺ�û� end-->
												
												<!-- ѧУ�û� -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														${info.xyshf }
													</td>
													<td>
														<!-- ѧУ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xxsh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧУ�û� end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--5.��֯����  end-->
									
									<!--6.���ʵ��  start-->
									<logic:equal name="xmdm" value="szyc_shsjfzb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allhddd }">
													<!-- ��ص� -->
													${info.hddd }
												</td>
												<td>
													<!-- ����� -->
													${info.hdrq }
												</td>
												<td title="${info.allhdnr }">
													<!-- ����� -->
													${info.hdnr }
												</td>
												<td>
													<!-- ����� -->
													${info.hdrq }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<!-- �೤�û� -->
												<logic:equal name="yhlx" value="bz">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �೤�û� end-->
												
												<!-- �������û� -->
												<logic:equal name="yhlx" value="bzr">
													<td>
														<!-- ��������˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="bzrshf" 
																	id="bzrshf_${info.id }" 
																	value="${info.bzrlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.bzrlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>	
													<td>
														<!-- ���״̬ -->
														${info.bzrsh }
													</td>
												</logic:equal>
												<!-- �������û� end-->
												
												<!-- ѧԺ�û� -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xysh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧԺ�û� end-->
												
												<!-- ѧУ�û� -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- ��������˷� -->
														${info.bzrshf }
													</td>
													<td>
														<!-- ѧԺ��˷� -->
														${info.xyshf }
													</td>
													<td>
														<!-- ѧУ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xxsh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧУ�û� end-->
												<logic:equal name="info" property="kfsh" value="yes">
													<logic:equal name="yhlx" value="bz">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="bzr">
														<input type="hidden" name="mrf" value="${info.sqf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xy">
														<input type="hidden" name="mrf" value="${info.bzrlrf }"/>
													</logic:equal>
													<logic:equal name="yhlx" value="xx">
														<input type="hidden" name="mrf" value="${info.xylrf }"/>
													</logic:equal>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!--6.���ʵ��  end-->
									
									<!--7.5s��  start-->
									<logic:equal name="xmdm" value="szyc_5sb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<logic:notEmpty name="info" property="id">
														<logic:equal name="info" property="kfsh" value="yes">
															<input type="checkbox" name="checkVal" value="${info.id }" />
														</logic:equal>
														<logic:equal name="info" property="kfsh" value="no">
															<input type="checkbox" name="checkVal" disabled="disabled" />
														</logic:equal>
													</logic:notEmpty>
												</td>
												<td title="${info.allfzxm }">
													<!-- ��ֵ��Ŀ -->
													${info.fzxm }
												</td>
												<td>
													<!-- �Ӽ��� -->
													${info.jjf }
												</td>
												<td>
													<!-- ��ֵ -->
													${info.sqf }
												</td>
												<td>
													<!-- ���� -->
													${info.jfrq }
												</td>
												<td title="${info.jfyy }">
													<!--ԭ�� -->
													${info.yy }
												</td>
												
												<!-- ѧԺ�û� -->
												<logic:equal name="yhlx" value="xy">
													<td>
														<!-- ѧԺ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xyshf" 
																	id="xyshf_${info.id }" 
																	value="${info.xylrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xylrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xysh }
													</td>
												</logic:equal>
												<!-- ѧԺ�û� end-->
												
												<!-- ѧУ�û� -->
												<logic:equal name="yhlx" value="xx">
													<td>
														<!-- ѧԺ��˷� -->
														${info.xyshf }
													</td>
													<td>
														<!-- ѧУ��˷� -->
														<logic:notEmpty name="info" property="id">
															<logic:equal name="info" property="kfsh" value="yes">
																<input type="text" name="xxshf" 
																	id="xxshf_${info.id }" 
																	value="${info.xxlrf }" 
																	onkeyup="checkInputNum(this)"
																	onblur="checkInputNum(this)"
																	maxlength="5" 
																	style="width : 50px;ime-mode:disabled;"/>
															</logic:equal>
															<logic:equal name="info" property="kfsh" value="no">
																${info.xxlrf }
															</logic:equal>	
														</logic:notEmpty>	
													</td>
													<td>
														<!-- ���״̬ -->
														${info.xxsh }
													</td>
													<td>
														<!-- ���� -->
														<logic:notEmpty name="info" property="id">
															<!-- �Ƿ������� -->
															<logic:empty name="info" property="sssj">
																��
															</logic:empty>
															<logic:notEmpty name="info" property="sssj">
																<!-- �Ƿ񱻴��� -->
																<logic:notEmpty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','${info.clr }','${info.clyj }')">
																		<font color="blue">
																			�Ѵ���
																		</font>
																	</a>
																</logic:notEmpty>
																<logic:empty name="info" property="clr">
																	<a href="#" onclick="showXsssDiv('${info.id }','${info.ssnr }','${info.sssj }','','')">
																		<font color="blue">
																			��
																		</font>
																	</a>
																</logic:empty>
															</logic:notEmpty>
														</logic:notEmpty>
													</td>
												</logic:equal>
												<!-- ѧУ�û� end-->
		
											</tr>
											<logic:equal name="info" property="kfsh" value="yes">
												<logic:equal name="yhlx" value="xy">
													<input type="hidden" name="mrf" value="${info.sqf }"/>
												</logic:equal>
												<logic:equal name="yhlx" value="xx">
													<input type="hidden" name="mrf" value="${info.xylrf }"/>
												</logic:equal>
												</logic:equal>
											</logic:iterate>
												
											</logic:equal>
									<!--7.5s��  end-->
								</table>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<!-- Ĭ�Ϸ����� -->
								<button type="button" onclick="setMrf();">
									����Ĭ�Ϸ�
								</button>
								<!-- ���� -->
								<button type="button" onclick="saveShf();">
									<bean:message key="lable.btn_bc_space" />
								</button>
								
								<button type="button" onclick="saveShzt('tg');return false;">
									ͨ ��
								</button>
								
								<button type="button" onclick="saveShzt('btg');return false;">
									��ͨ��
								</button>
								
								<!-- ��5S��Ŀ -->
								<logic:notEqual name="xmdm" value="szyc_5sb">
									<logic:equal name="yhlx" value="xy">
										<button type="button" onclick="saveShzt('th')">
											�˻�
										</button>
									</logic:equal>
									<logic:equal name="yhlx" value="xx">
										<button type="button" onclick="saveShzt('th')">
											�˻�
										</button>
									</logic:equal>
								</logic:notEqual>
								
								<!-- 5S��Ŀ -->
								<logic:equal name="xmdm" value="szyc_5sb">
									<logic:equal name="yhlx" value="xx">
										<button type="button" onclick="saveShzt('th')">
											�˻�
										</button>
									</logic:equal>
								</logic:equal>
								
								<button type="button" onclick="Close();window.dialogArguments.document.getElementById('search_go').click()">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ѧ��������Ϣ end-->
			
			<!-- ѧ�����ߵ����� -->
			<div id="div_xsss" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����д�������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									��������
								</th>
								<td>
									<textarea id="ssnr" rows="5" cols="" 
										style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									����ʱ��
								</th>
								<td>
									<p id="p_sssj"></p>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									������
								</th>
								<td>
									<p id="p_clr"></p>
								</td>
							</tr>
							<tr id="tr_clyj">
								<th width="30%">
									�������<br/>
									<font color="red"><��500��></font>
								</th>
								<td>
									<textarea id="clyj" rows="5" cols="" 
										style="word-break:break-all;width:100%"  
										onblur="chLeng(this,500)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="saveSscl()">
											�� ��
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>