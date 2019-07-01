<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		//����¥����Ϣ
		function addLdxx(){
			var url="/xgxt/gyglGywh.do?method=ldxxwh&doType=add";
			showTopWin(url,600,480);
		}
		//�޸�¥����Ϣ
		function modiLdxx(){
			if (null == curr_row) {
				alert('��ѡ��һ��');
			} else {
				var lddm = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/gyglGywh.do?method=ldxxwh&doType=update&lddm='+lddm;
				showTopWin(url,600,480);
			}
		}
		//ɾ��¥����Ϣ
		function delLdxx(){
			var pkV=document.getElementsByName("checkVal");
			
			//�ж��Ƿ���ѡ�еļ�¼
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				if(confirm("ȷ��Ҫɾ��ѡ�еļ�¼��?")){
					var mklx=$("mklx").value;
					var url="/xgxt/gyglGywh.do?method=ldwhManage";
					url+="&doType=del&mklx="+mklx;
					refreshForm(url);
					hiddenMessage(true,true);
					BatAlert.showTips('���ڲ��������Ե�...');
				}else{
				
					return false;
				}
			}else{
				alert("û����Ҫ����ļ�¼!");
				return false;
			}
		
			
		}
		
		//��ʾ¥����ϸ��Ϣ
		function showLdxxxx(){
			var pkValue=curr_row.getElementsByTagName('input')[0].value;
			var url="/xgxt/gyglGywh.do?method=ldxxxx&pkValue="+pkValue;
			refreshForm(url)
		}
		
		//��ʾ¥����ϸ��Ϣ
		function showLdxx(lddm){
			var pkValue=lddm;
			var url="/xgxt/gyglGywh.do?method=ldxxxx&pkValue="+pkValue;
			refreshForm(url)
		}
		
		//�߼���ѯ
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglGywh.do?method=ldwhManage&mklx=${mklx }');
		}
		
		
		//��һ������
		function nextCz(){
			var next = $("next_cz").value;
			
			if(next==""){
				next=$("next_1").value;
			}
			
			if(next == "gb"){//�ر�
				hiddenMessage(true,true);
			}else {
				next+="&doType=scjc";
				refreshForm(next);
			}
		}
		
		function qszdsc(){
			var pkV=document.getElementsByName("checkVal");
			
			//�ж��Ƿ���ѡ�еļ�¼
			blog=false;
			var n=0;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
					n++
				}
			}
			if(!blog){
				alert("��ѡ��һ����Ҫ�Զ��������ҵ�¥��!");
				return false;
			}
			if(n>1){
				alert("ֻ��ѡ��һ��¥�����������Զ�����!");
				return false;
			}
			
			refreshForm('/xgxt/gygl_gywh_zdsc.do?tzPath=ldwh');
		}
		
		
		</script>
	</head>
	<body onload="">

		<html:form action="/gyglGywh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<!-- ģ������ -->
			<input type="hidden" id="mklx" name="mklx" value="${mklx}" />
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="path" value="gygl_gywh_ldwh.do" />
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="addLdxx();return false;" class="btn_zj">
								���� </a>
						</li>
						<li>
							<a href="#" onclick="modiLdxx();return false;" class="btn_xg">
								�޸� </a>
						</li>
						<li>
							<a href="#" onclick="delLdxx();return false;" class="btn_sc">
								ɾ�� </a>
						</li>
						<li>
							<a href="#" class="btn_dr"
								onclick="impAndChkData();return false;">��������</a>
						</li>
						<li>
							<a href="#" class="btn_dc"
								onclick="setSearchTj();configureExportData();return false;">��������</a>
						</li>
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">�����ֶ�ȷ��</a>
						</li>
						<li>
							<a href="#" class="btn_csh" onclick="qszdsc();return false;">�����Զ�����</a>
						</li>
					</ul>
				</div>

				<!-- new �汾 -->
				<logic:equal name="superSearch" value="yes">
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				</logic:equal>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">˫��һ����¼���Բ鿴��ϸ��Ϣ;</font>
						</logic:notEmpty> </span>
				</h3>

				<logic:notEmpty name="rs">
					<div class="con_overlfow">
						<table summary="" class="dateline" align="" width="100%">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="all" value="all" onclick="chec()" />

									</td>
									<logic:iterate id="tit" name="topTr" offset="1" length="6"
										indexId="index">
										<td id="<bean:write name="tit" property="en"/>" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<tbody>
								<logic:iterate name="rs" id="s" indexId="index">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
										ondblclick="showLdxxxx()">
										<td>
											<input type="checkbox" name="checkVal" id="pkV"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="2" length="4">
											<td nowrap>
												<bean:write name="v" />
											</td>
										</logic:iterate>
										<!-- ¥��������ͳ�� -->

										<td>
											<a href="#"
												onclick="showLdxx('<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>');return false;"><font
												color="blue"><U>
												<logic:iterate id="v" name="s" offset="6" length="1">
														<bean:write name="v" />
												</logic:iterate></U>
												
												</font>
											</a>
										</td>

									</tr>
								</logic:iterate>
							</tbody>
						</table>
					</div>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=gyglGywhForm"></jsp:include>
				</logic:notEmpty>
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/delMessage.jsp"%>
		<logic:equal name="result" value="true">
			<script>
			alert("�����ɹ�!");	
		</script>
		</logic:equal>
	</body>
</html>
