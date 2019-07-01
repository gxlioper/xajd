<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getCommGygl.js'></script>
		<script language="javascript" defer="defer">
		//�����Ԣ�ṹ
		function clickGyjg(lv,dm,dj){
			
			if(lv == "1"){//�����һ��
				var div2_name = "div_info2_"+dm;
				var div2_num = document.getElementsByName(div2_name).length;

				for(var i=0;i<div2_num;i++){
					var obj = document.getElementsByName(div2_name)[i];
					var id = obj.value;
					var value = id.split("_")[3];
					
					if($(id)){
						if($(id).style.display == "none"){
							$(id).style.display = "";
						}else{
							$(id).style.display = "none";
						}
					}
					
					//������һ��
					clickGyjg("2",value,"no");
				}	
				
			}else if(lv == "2"){//����ڶ���
				var div3_name = "div_info3_"+dm;
				var div3_num = document.getElementsByName(div3_name).length;

				for(var i=0;i<div3_num;i++){
					var obj = document.getElementsByName(div3_name)[i];
					var id = obj.value;
					var value = id.split("_")[3];
					
					if($(id)){
						if($(id).style.display == "none"){
							if(dj == "yes"){
								$(id).style.display = "";
							}
						}else{
							$(id).style.display = "none";
						}
					}
					
					//������һ��
					clickGyjg("3",value,"no");
				}			
			}else if(lv == "3"){//���������
				var div4_name = "div_info4_"+dm;
				var div4_num = document.getElementsByName(div4_name).length;

				for(var i=0;i<div4_num;i++){
					var obj = document.getElementsByName(div4_name)[i];
					var id = obj.value;
					
					if($(id)){
						if($(id).style.display == "none"){
							if(dj == "yes"){
								$(id).style.display = "";
							}
						}else{
							$(id).style.display = "none";
						}
					}
				}
			}else if(lv == "4"){//������ļ�

			}
		}
		
		//����������Ϣ
		function creatQsInfo(lddm,cs){

			dwr.engine.setAsync(false);
			
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			
			//���Ҽ���
			var qsjb = $("qsjb").value;
			var div_id = "div_"+lddm+"_"+cs;
	
			if($(div_id)){
				//�Ѵ�������DIV
				if($(div_id).innerHTML != ""){
					if($(div_id).style.display == "none"){
						$(div_id).style.display = "";
					}else{
						$(div_id).style.display = "none";
					}
				}else{
					//���䷽ʽ
					var fpfs = $("fpfs").value;
					//�������
					var fpdx = $("fpdx").value;
					//��������
					var gyInfo = [lddm,cs,fpdx,fpfs];
					//�����tr����
					var tr_num = "0";

					getCommGygl.getQsxxList(gyInfo,userStatus,userName,userDep,function(data){

						if(data !=null && data.length >0){
							
							var divHtml = "<table style=\"width: 100%\">";
							
							//ÿ����ʾ����
							var colNum = 5;
							//������
							var spaceSize = 0;
							//����
							var rowNum = data.length/colNum;
							//���Һ�
							var qshHtml = new Array();
							//��������
							var qsnrHtml = new Array();
							
							if(data.length%colNum!=0){
								spaceSize=data.length%colNum+(5-1);
								rowNum=parseInt(data.length/colNum+1);
							}
							
							//ѭ������
							for(var i=0;i<rowNum;i++){
							
								qshHtml[i]="<tr>";
								qsnrHtml[i]="<tr>";
								
								var n=0;
								
								for(var j=(i*colNum);j<data.length+spaceSize;j++){
									
									if(n!=colNum){
										//���Һ�
										qshHtml[i]+="<td width=\"20%\">"
										qshHtml[i]+="<span class=\"num\">";
										if(j<data.length){
											qshHtml[i]+=data[j].qsh;
											qshHtml[i]+="("+data[j].xb+")";
										}
										qshHtml[i]+="</span>";
										qshHtml[i]+="</td>"
										
										//��������
										qsnrHtml[i]+="<td width=\"20%\">"
										if(j<data.length){
											//¥������
											var lddm = data[j].lddm;
											//¥������
											var ldmc = data[j].ldmc;
											//����
											var cs = data[j].cs;
											//���Һ�
											var qsh = data[j].qsh;
											//��ס״̬
											var rzzt = data[j].rzzt;
											//������
											var fpbj = data[j].fpbj;
											
											//���䲿��
											var fpbm = data[j].fpbm;
											var allmc = data[j].fpbm;
											//�����꼶
											var nj = "";
											//���䲿��
											var bmdm = "";
												
											if(data[j].bmdm !=""&& data[j].bmdm!=null){
												bmdm = data[j].bmdm;
											}
												
											if(data[j].nj !=""&& data[j].nj!=null){
												nj = data[j].nj;
											}
												
											//��������
											if(fpbj == "����"){
												allmc="��������";
												fpbm="��������";
											}
											//�ѷ���
											else if(fpbm !="" && fpbm !=null){
												fpbm=fpbm;
												allmc = allmc;
												if(fpbm.length >8){
													fpbm = fpbm.substring(0,6)+"..."
												}	
											}else{
												allmc="";
												fpbm="δ����";
											}
											
											//������Ϣ����Ҫ��������
											divHtml+="<input type=\"hidden\" name=\"lddm\" value=\""+lddm+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"ldmc\" value=\""+ldmc+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"cs\" value=\""+cs+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"qsh\" value=\""+qsh+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"rzzt\" value=\""+rzzt+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"nj\" id=\"nj_"+lddm+"_"+cs+"_"+qsh+"\" value=\""+nj+"\"/>";
											divHtml+="<input type=\"hidden\" name=\"bmdm\" id=\"bmdm_"+lddm+"_"+cs+"_"+qsh+"\" value=\""+bmdm+"\"/>";
	
											qsnrHtml[i]+="<span id=\"span_"+lddm+"_"+cs+"_"+qsh+"\" title=\""+allmc+"\">(";
											qsnrHtml[i]+=fpbm;
											qsnrHtml[i]+=")</span>";
											qsnrHtml[i]+="</br>";
												
											//��λ��
											var cws = data[j].cws;
											qsnrHtml[i]+="��λ����"+cws;
											qsnrHtml[i]+="</br>";
											
											//��ס����
											var yzrs = data[j].yzrs;
											qsnrHtml[i]+="����ס��"+yzrs;
											qsnrHtml[i]+="</br>";
											
											qsnrHtml[i]+="��ס״̬��"+rzzt;
											
											//��������
											if(fpbj == "����"){
												qsnrHtml[i]+="<a class=\"cant\">���ɷ���</a>";
											}else{
												if(fpfs == "2"){
													if(userStatus == "xx"){
														//�ѷ���
														if(fpbm !="δ����"){
															qsnrHtml[i]+="<a href=\"#\" onclick=\"fpqs(this,'"+lddm+"','"+cs+"','"+qsh+"');return false;\" class=\"cancel\">ȡ������</a>";
														}else{
															qsnrHtml[i]+="<a href=\"#\" onclick=\"fpqs(this,'"+lddm+"','"+cs+"','"+qsh+"');return false;\" class=\"distribution\">��������</a>";
														}
													}else{
														qsnrHtml[i]+="<a href=\"#\" onclick=\"fpqs(this,'"+lddm+"','"+cs+"','"+qsh+"');return false;\" class=\"distribution\">��������</a>";
													}
												}else{
													//�ѷ���
													if(fpbm !="δ����"){
														qsnrHtml[i]+="<a href=\"#\" onclick=\"fpqs(this,'"+lddm+"','"+cs+"','"+qsh+"');return false;\" class=\"cancel\">ȡ������</a>";
													}else{
														qsnrHtml[i]+="<a href=\"#\" onclick=\"fpqs(this,'"+lddm+"','"+cs+"','"+qsh+"');return false;\" class=\"distribution\">��������</a>";
													}
												}
											}	
										}
										qsnrHtml[i]+="</td>"
										
										n++;
									}else{
										n=0;
										break;
									}
								}	
								
								qshHtml[i]+="</tr>";
							}
							
							for(var i=0;i<rowNum;i++){
								divHtml+=qshHtml[i];
								divHtml+=qsnrHtml[i];
							}
							
							divHtml+= "</table>";
					
							$(div_id).innerHTML = divHtml;
						}else{
							$(div_id).innerHTML = "��¥�����޿ɹ���������ң���ȷ��";
						}
					});
						
				}
			}
			
			dwr.engine.setAsync(true);
		}
		
		//����������������
		function clickBm(lv,bmdm,djlx,nj){
			
			var fpdx = $("fpdx").value;
			var ul_id = "ul_"+lv+"_"+bmdm;
			
			var flag = true;
			
			if(fpdx == "bj" && djlx == "bj"){
				flag = false;
			}else if(fpdx == "njzy" && djlx == "zy"){
				flag = false;
			}else if(fpdx == "njxy" && djlx == "xy"){
				flag = false;
			}else if(fpdx == "xy" && djlx == "xy"){
				flag = false;
			}
			
			if($(ul_id) && flag){
				if($(ul_id).innerHTML != ""){
					if($(ul_id).style.display == "none"){
						$(ul_id).style.display = "";
					}else{
						$(ul_id).style.display = "none";
					}
				}else{
					creatNewBm(lv,bmdm,djlx,nj);
					$(ul_id).style.display = "";
				}
			}
		}
		
		//�����²���
		function creatNewBm(lv,bmdm,djlx,nj){
		
			var bmlx = "";

			if(djlx == "nj"){
				bmlx = "xy";
			}else if(djlx == "xy"){
				bmlx = "zy";
			}else if(djlx == "zy"){
				bmlx = "bj";
			}
			
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			var bmInfo=[bmlx,nj,bmdm];
			
			var ul_id = "ul_"+lv+"_"+bmdm;
			var nextLv = parseInt(lv)+1;

			dwr.engine.setAsync(false);
	
			var divHtml = "";
			
			getCommGygl.getBmList(bmInfo,userStatus,userName,userDep,function(data){
				if(data !=null && data.length >0){
					for(var i=0;i<data.length;i++){
						divHtml += "<li>";
						divHtml += "<a href=\"#\""; 
						divHtml += "onclick=\"clickBm('"+nextLv+"','"+data[i].dm+"','"+data[i].bmlx+"','"+data[i].nj+"');return false;\">";
						divHtml += data[i].mc;
						divHtml += "</a>";
						divHtml += "</li>";
						divHtml += "<ul id=\"ul_"+nextLv+"_"+data[i].dm+"\" style=\"display:none\">";											
						divHtml += "</ul>";
					}
				}
			});

			$(ul_id).innerHTML = divHtml;
			
			dwr.engine.setAsync(true);
		}
		
		//��������
		function fpqs(obj,lddm,cs,qsh){

			var span_id = "span_"+lddm+"_"+cs+"_"+qsh;
			var bmdm_id = "bmdm_"+lddm+"_"+cs+"_"+qsh;
			var nj_id = "nj_"+lddm+"_"+cs+"_"+qsh;
			
			if(obj.innerHTML == "��������"){

				if($("clickBmdm").value == ""){
					alert("�������Ĳ����б���ѡ����Ҫ������Ķ���");
					return false;
				}
				
				if($(span_id)){
					var nj = $("clickBmnj").value;
					var bmdm = $("clickBmdm").value;
					var bmmc = $("clickBmmc").value;
					var allmc = bmmc;

					if(bmmc.length > 5){
						allmc = bmmc.substring(0,5)+"...";
					}
					
					$(span_id).innerHTML = "("+allmc+")";
					$(span_id).title = bmmc;
					
					if($(bmdm_id)){
						$(bmdm_id).value = bmdm;
					}
					
					if($(nj_id)){
						$(nj_id).value = nj;
					}
				}
			
				obj.className = "cancel";
				obj.innerHTML = "ȡ������";
			}else{
			
				if($(span_id)){
					$(span_id).innerHTML = "δ����";
					$(span_id).title = "";
				}
				
				obj.className = "distribution";
				obj.innerHTML = "��������";
				
				if($(bmdm_id)){
					$(bmdm_id).value = "";
				}
				
				if($(nj_id)){
					$(nj_id).value = "";
				}
			}
		}
		
		//�����ֶ�����
		function fhsdfp(){
			if (confirm("��Ҫ����ǰһҳ�棬��ȷ���Ѿ������˷�����Ϣ��")) {
				refreshForm("gygl_qsgl_qsfp.do");
			}
		}
		
		//�����ֶ�����
		function saveQsfp(){
			var num =  document.getElementsByName("lddm").length;
			for(var i=0;i<num;i++){
				var lddm = document.getElementsByName("lddm")[i];
				var ldmc = document.getElementsByName("ldmc")[i];
				var cs = document.getElementsByName("cs")[i];
				var qsh = document.getElementsByName("qsh")[i];
				var rzzt = document.getElementsByName("rzzt")[i];
				var bmdm = document.getElementsByName("bmdm")[i];
				
				if(bmdm.value=="" && rzzt.value!="�յ�"){
					alert(ldmc.value+cs.value+"��"+qsh.value+"�����Ѿ���ѧ����ס����δ���䣬��ȷ�ϣ�");
					return false;
				}
			}
			if (confirm("ȷ��Ҫ���浱ǰ�����ҷ�����?")) {
				saveUpdate('/xgxt/gyglQsgl.do?method=qssdfpByHand&doType=save','');
			}
		}
	
		//��ʾ¥������Ϣ	
		function showCsInfo(obj,id){
			if(obj.className=="table_up"){
				obj.className="table_down";
				obj.parentNode.parentNode.className="cur-tr";
				$(id).style.display="none";
			}else{
				obj.className="table_up";
				obj.parentNode.parentNode.className="";
				$(id).style.display="";
			}
		}
		
		//��ʾ¥��������Ϣ	
		function showQsInfo(obj,id,lddm,cs){
			if(obj.className=="up"){
				obj.className="down";
				obj.parentNode.parentNode.className="cur-tr";
				$(id).style.display="none";
			}else{
				obj.className="up";
				obj.parentNode.parentNode.className="";
				$(id).style.display="";
			}

			creatQsInfo(lddm,cs,"yes");
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>���ҷ��� - �ֶ�����</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/gyglQsgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="byHand" name="byHand" value="yes"/>
			<input type="hidden" id="userStatus" value="${userStatus }"/>
			<input type="hidden" id="fpfs" value="${fpfs }"/>
			<logic:notEmpty name="ssbh">
				<logic:iterate name="ssbh" id="ss">
					<input type="hidden" name="primarykey_checkVal" value="${ss }"/>
				</logic:iterate>
			</logic:notEmpty>
			
				<input type="hidden" id="qsjb" value="1"/>
			
			<!-- ������ -->
			<logic:notEmpty name="ssbh">
				<logic:iterate name="ssbh" id="ss">
					<input type="hidden" name="ssbh" value="${ss }"/>
				</logic:iterate>
			</logic:notEmpty>
			<!-- ������ -->

			<!--����start-->
		    <table class="formlist" style="margin-bottom:4px" >
    			<thead>
					<tr>
						<td >
							<span>
								�����չ�������ص���Ϣ
<%--								<font color="blue">--%>
<%--								ע���������Ϊ--%>
<%--								<logic:equal name="fpdx" value="xy">ѧԺ</logic:equal>--%>
<%--								<logic:equal name="fpdx" value="njxy">�꼶+ѧԺ</logic:equal>--%>
<%--								<logic:equal name="fpdx" value="njzy">�꼶+רҵ</logic:equal>--%>
<%--								<logic:equal name="fpdx" value="bj">�༶</logic:equal>--%>
<%--								</font>--%>
							</span>	
							<div class="btn">
								<logic:empty name="ldInfoList">
									<button  id="btn_bc" disabled="disabled">
										<bean:message key="lable.btn_bc_space" />
									</button>
								</logic:empty>
								<logic:notEmpty name="ldInfoList">
								<button onclick="saveQsfp()" id="btn_bc">
									<bean:message key="lable.btn_bc_space" />
								</button>
								</logic:notEmpty>
								<button onclick="fhsdfp()" id="btn_fh">
									<bean:message key="lable.btn_fh_space" />
								</button>
							</div>
						</td>
					</tr>
				<thead>
			</table>
		    <div class="leftframe04">
				<%@ include file="/comm/bmTree.jsp"%>	
		    </div>
			<div class="rightframe04">
				<table width="100%"><tr><td>
				<%int n=0;%>
				<logic:notEmpty name="ldInfoList">
					<logic:iterate name="ldInfoList" id="ldInfo">
						<logic:equal name="ldInfo" property="Lv" value="ld">
							<%n++;%>
							<logic:iterate name="ldInfo" property="infoList" id="ld">
								<div class="table_updown">
									<a href="#" class="table_down" onclick="showCsInfo(this,'tb_cs_${ld.lddm }');return false">
										${ld.xqmc }&nbsp;&nbsp;${ld.yqmc }&nbsp;&nbsp;${ld.ldmc }
									</a>
								</div>
								<!-- ���� -->
								<logic:iterate name="ldInfoList" id="csInfo">
									<logic:equal name="csInfo" property="Lv" value="cs">
										<table width="100%"  border="0" class="formlist" id="tb_cs_${ld.lddm }" style="display:none">
											<logic:iterate name="csInfo" property="infoList" id="cs">
												<logic:equal name="cs" property="lddm" value="${ld.lddm }">
													<thead>
														<tr>
															<th width="20%">
																<a href="#" class="down" onclick="showQsInfo(this,'tb_qs_${ld.lddm }_${cs.dm }','${ld.lddm }','${cs.dm }');return false">
																	${cs.mc }<bean:message key="lable.cs" />
																</a>
															</th>
															<th colspan="5">
														
															</th>
														</tr>
													</thead>
													<tbody id="tb_qs_${ld.lddm }_${cs.dm }" style="display:none;">
														<tr>
															<td colspan="6">
																<div id="div_${ld.lddm }_${cs.dm }" style="width: 100%">
																	
																</div>
															</td>
														</tr>		
													</tbody>
														</tr>
													</thead>
												</logic:equal>
											</logic:iterate>
										</table>
									</logic:equal>
								</logic:iterate>
								<!-- ���� end-->
							</logic:iterate>
						</logic:equal>
					</logic:iterate>
				</logic:notEmpty></td></tr></table>
			</div>	
			<%if(n==0){ %>
					<div class="page_prompt">
					<div class="page_promptcon">
					  <h3>��ܰ��ʾ��</h3>
					  <p>û�пɹ���������ң�</p>
					</div>
					<p>&nbsp;</p>
					</div>
			<%}%>		
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>