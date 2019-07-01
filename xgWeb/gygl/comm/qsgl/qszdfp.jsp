<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglQsgl.do?method=qszdfp');
		}
		
		//ѡ����
		function choseBm(){
			var num = document.getElementsByName("primarykey_checkVal").length;
			var flag = false;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					flag = true;
					break;
				}
			}
			
			if(flag){
				viewTempDiv("ѡ����䷶Χ","zdscDiv",500,400);	
			}else{
				alert("��ѡ����Ҫ�Զ�����Ĳ��ţ�");
			}
		}
		
		//���У��
		function clickXqYq(lx){
		
			var xqNum = document.getElementsByName("xiaoqu").length;
			var xqdm = new Array();
			var n = 0;
			
			for(var i=0;i<xqNum;i++){
				var obj = document.getElementsByName("xiaoqu")[i];
				if(obj.checked == true){
					xqdm[n] = obj.value;
					n++;
				}
			}
			
			var yqNum = document.getElementsByName("yuanqu").length;
			var yqdm = new Array();
			var m = 0;
			
			for(var i=0;i<yqNum;i++){
				var obj = document.getElementsByName("yuanqu")[i];
				if(obj.checked == true){
					yqdm[n] = obj.value;
					m++;
				}
			}
			
			//����԰��
			if($("p_yq") && lx == "xq"){
				displayNewYq(xqdm);
			}
			
			//����¥��
			if($("p_ld")){
				displayNewLd(xqdm,yqdm,"checkbox");
			}
		}
		
		//����԰��
		function displayNewYq(xqdm){
		
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			
			$("p_yq").innerHTML = "";
			var divHtml = "";
			
			dwr.engine.setAsync(false);
			
			//����У��ȡ��԰��
			searchUtil.getGyglInfo("yq",xqdm,null,null,null,userStatus,userName,userDep,function(data){
				if(data !=null && data.length >0){
					for(var i=0;i<data.length;i++){
						divHtml += "<input type='checkbox' name='yuanqu' onclick='clickXqYq()' value='"+data[i].yqdm+"'/>"+data[i].yqmc;
					}
				}
			});
			
			dwr.engine.setAsync(true);
			
			$("p_yq").innerHTML = divHtml;
		}
		
		//����¥��
		function displayNewLd(xqdm,yqdm,lx){
		
			var userStatus = $("userStatus").value;
			var userName = $("userName").value;
			var userDep = $("userDep").value;
			
			$("p_ld").innerHTML = "";
			var divHtml = "";
			
			dwr.engine.setAsync(false);
			
			//����У��԰��ȡ����¥��
			searchUtil.getGyglInfo("ldqs",xqdm,yqdm,null,null,userStatus,userName,userDep,function(data){
				if(data !=null && data.length >0){
					for(var i=0;i<data.length;i++){
						if(lx == "checkbox"){
							divHtml += "<input type='checkbox' name='ld' value='"+data[i].lddm+"'/>"+data[i].ldmc;
							divHtml += "(";
							divHtml += data[i].num;
							divHtml += ")";
							if((i+1)%3==0){
								divHtml += "</br>";
							}
						}
					}
				}
			});
			
			dwr.engine.setAsync(true);
			
			$("p_ld").innerHTML = divHtml;
		}
		
		//�����Զ�����
		function qszdfp(){
		
			var num = document.getElementsByName("primarykey_checkVal").length;
			var id = new Array();
			var flag = false;
			
			var n = 0;
			
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("primarykey_checkVal")[i];
				if(obj.checked == true){
					id[n] = "xfprs_"+obj.id.replace("pk_","");
					n++;
				}
			}
			
			if (confirm("��Ҫ���������õķ�Χ����ѡ��������Զ��������ң���ȷ�ϣ�")) {
			
				for(var i=0;i<n;i++){
					var tmp = document.createElement("input");
					tmp.type = "hidden";
					tmp.name = "xfprs";
					tmp.value = $(id[i]).value;
					document.forms[0].appendChild(tmp);
				}
				saveUpdate('/xgxt/gyglQsgl.do?method=qszdfp&doType=save','');
			}
		}
		
		//��һ������
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next == "gb"){//�ر�
				hiddenMessage(true,true);
			}else if(next == "jg"){//�����ѯ
				refreshForm("gyglQsgl.do?method=qszdfpjg&go=go");
			}else if(next == "sd"){//�ֶ�����
				refreshForm("gyglQsgl.do?method=qssdfp");
			}
		}
		</script>
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				�ڲ�ѯ������й�ѡϣ���Զ��������ҵĲ��ţ�������������ҡ���ȷ���������ƣ���ɱ�ģ�������
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ END-->
		
		<html:form action="/gyglQsgl">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- �������� -->
						<li>
							<a href="#" id="btn_qd"
								onclick="choseBm();return false;"
								class="btn_qd">
								��������
							</a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->	
				
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				
			</div>
			<!-- �๦�ܲ����� end -->
			
			<!-- ��ѯ���-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						��ѯ���
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font> 
						</logic:empty>
						<logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;
							<font color="blue">
							ʣ��δ�������ң�${wfpQsNum }
							</font> 
						</logic:notEmpty>
					</span>
				</h3>
				
				<div class="con_overlfow" >
				<table summary="" class="dateline" align="" width="100%">
					<!-- ��ͷ -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="1" length="9">
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)"
									nowrap>
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- ��ͷ end-->
					<!--���� -->
					<logic:equal name="hadRs" value="yes">
						<logic:iterate name="rsArrList" id="s" indexId="index">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<td align="center" width="5px">
									<input type="checkbox" id="pk_${index }"
										name="primarykey_checkVal"  
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>"/>
								</td>
								<!-- ��ʾ��Ϣ -->
								<logic:iterate id="v" name="s" offset="1" length="${showNum }">
									<td align="left">
										${v }
									</td>
								</logic:iterate>
								
								<!-- ����䴲λ�� -->
								<logic:iterate id="v" name="s" offset="${xfprs }" length="1">
									<td style="display:none">
										<input type="hidden" id="xfprs_${index }" value="${v }"/>
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</logic:equal>
					<!--���� end-->
					<!-- ������ -->
					<%@ include file="/comm/noRows.jsp"%>
					<!-- ������ end-->
				</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglQsglForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->
			
			<div id="zdscDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>��Χ(¥������������Ϊδ����������)</span>
								</th>
							</tr>
						</thead>
						<tbody>
						
							<!-- ������ϵ1��У��--԰��--¥���� -->
							<logic:equal name="csgx" value="1">
								<!-- У�� -->
								<tr>
									<th width="20%">
										<bean:message key="lable.xiaoqu" />
									</th>
									<td>
										<logic:notEmpty name="xqdmList">
											<logic:iterate name="xqdmList" id="xiaoqu" indexId="xqNum">
												<logic:notEqual name="xqNum" value="0">
													<input type="checkbox" name="xiaoqu" onclick="clickXqYq('xq')" value="${xiaoqu.dm }"/>${xiaoqu.mc }
												</logic:notEqual>
												<%if((xqNum.intValue()+1)%5==0){%>
													<% out.print("</br>"); %>
												<%}%> 
											</logic:iterate>
										</logic:notEmpty>
									</td>
								</tr>
								<!-- ԰�� -->
								<tr>
									<th width="20%">
										<bean:message key="lable.yuanqu" />
									</th>
									<td>
										<p id="p_yq">
											<logic:notEmpty name="yqList">
												<logic:iterate name="yqList" id="yq" indexId="yqNum">
													<logic:notEqual name="yqNum" value="0">
														<input type="checkbox" name="yuanqu" onclick="clickXqYq('yq')" value="${yq.dm }"/>${yq.mc }
													</logic:notEqual>
													<%if((yqNum.intValue()+1)%5==0){%>
														<% out.print("</br>"); %>
													<%}%> 
												</logic:iterate>
											</logic:notEmpty>
										</p>
									</td>
								</tr>
							</logic:equal>
							
							<!-- ������ϵ2��У��--¥���� -->
							<logic:equal name="csgx" value="2">
								<!-- У�� -->
								<tr>
									<th width="20%">
										<bean:message key="lable.xiaoqu" />
									</th>
									<td>
										<logic:notEmpty name="xqdmList">
											<logic:iterate name="xqdmList" id="xiaoqu" indexId="xqNum">
												<logic:notEqual name="xqNum" value="0">
													<input type="checkbox" name="xiaoqu" onclick="clickXqYq('xq')" value="${xiaoqu.dm }"/>${xiaoqu.mc }
												</logic:notEqual>
												<%if((xqNum.intValue()+1)%5==0){%>
													<% out.print("</br>"); %>
												<%}%> 
											</logic:iterate>
										</logic:notEmpty>
									</td>
								</tr>
							</logic:equal>
							
							<!-- ������ϵ3��԰��--¥���� -->
							<logic:equal name="csgx" value="3">
								<!-- ԰�� -->
								<tr>
									<th width="20%">
										<bean:message key="lable.yuanqu" />
									</th>
									<td>
										<logic:notEmpty name="yqList">
											<logic:iterate name="yqList" id="yq" indexId="yqNum">
												<logic:notEqual name="yqNum" value="0">
													<input type="checkbox" name="yuanqu" onclick="clickXqYq('yq')" value="${yq.dm }"/>${yq.mc }
												</logic:notEqual>
												<%if((yqNum.intValue()+1)%5==0){%>
													<% out.print("</br>"); %>
												<%}%> 
											</logic:iterate>
										</logic:notEmpty>
									</td>
								</tr>
							</logic:equal>
							
							<!-- ¥�� -->
							<tr>
								<th width="20%">
									<bean:message key="lable.ld" />
								</th>
								<td>
									<p id="p_ld">
										<logic:notEmpty name="ldQsList">
											<logic:iterate name="ldQsList" id="ld" indexId="ldNum">
												<input type="checkbox" name="ld" value="${ld.lddm }"/>${ld.ldmc }(${ld.num})
												<%if((ldNum.intValue()+1)%3==0){%>
													<% out.print("</br>"); %>
												<%}%> 
											</logic:iterate>
										</logic:notEmpty>
									</p>
								</td>
							</tr>
							<!-- ¥�� end-->
							
							<!-- �Ա� -->
							<tr>
								<th width="20%">
									�Ա�����
								</th>
								<td>
									<input type="hidden" id="xb" name="xb" value="������"/>
									<input type="radio" name="xb" id="xb_no" value="������" onclick="$('xb').value = this.value" checked="checked"/>������
									<input type="radio" name="xb" id="xb_man" value="��" onclick="$('xb').value = this.value"/>��
									<input type="radio" name="xb" id="xb_woman" value="Ů" onclick="$('xb').value = this.value"/>Ů	
								</td>
							</tr>
							<!-- �Ա� end-->
							
							<!-- �ɷ��ס -->
							<tr>
								<th width="20%">
									�ɷ��ס
								</th>
								<td>
									<input type="hidden" id="kfhz" name="kfhz" value="������"/>
									<input type="radio" name="kfhz" id="kfhz_no" value="������" onclick="$('kfhz').value = this.value" checked="checked"/>������
									<input type="radio" name="kfhz" id="kfhz_no" value="����" onclick="$('kfhz').value = this.value"/>����
									<input type="radio" name="kfhz" id="kfhz_yes" value="����" onclick="$('kfhz').value = this.value"/>����	
								</td>
							</tr>
							<!-- �ɷ��ס end-->
							
						</tbody>
						<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<!-- ȷ�� -->
									<button onclick="qszdfp()">
										<bean:message key="lable.btn_qd_space" />
									</button>
									<!-- �ر� -->
									<button onclick="hiddenMessage(true,true);return false;">
										<bean:message key="lable.btn_gb_space" />
									</button>
								</div>
							</td>
						</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- ��ʾ��Ϣ -->
			<logic:present name="message">
				<!-- ���������ʾ�� -->
				<div id="tsxxDiv" style="display: none">
					<div class="tab">
						<table class="formlist">
							<thead>
								<tr>
									<th>
										<span>
											�Ѿ��ɹ�������${message }�����ң���ȷ����һ������
										</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<input type="hidden"  name="next" id="next_cz" value="jg"/>
										<input type="radio" name="next" id="next_jg" value="jg" onclick="$('next_cz').value = this.value" checked="checked"/>
										�鿴�Ѿ�����Զ����������
										</br>
										<input type="radio" name="next" id="next_sd" value="sd" onclick="$('next_cz').value = this.value"/>
										ǰ��"�����ֶ�����"ģ��
										</br>
										<input type="radio" name="next" id="next_gb" value="gb" onclick="$('next_cz').value = this.value"/>
										�رձ�ҳ�棬����Ϊ����������з���
									</td>
								</tr>
							</tbody>
							<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button onclick="nextCz()">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
							</tfoot>
						</table>
					</div>
				</div>
				<script defer="defer">
					viewTempDiv("��ѡ����һ������","tsxxDiv",350,200);
					if($("message") && $("message").value != ""){
						$("message").value = "";
						$("doType").value = "";	
					}
				</script>
			</logic:present>
			<!-- ��ʾ��Ϣend -->
		</html:form>
	</body>
</html>