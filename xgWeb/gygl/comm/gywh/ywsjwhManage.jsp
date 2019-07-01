<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function changePage(obj){
			var url="/xgxt/gyglGywh.do?method=ywsjwhManage";
			var mklx=obj.id;
			url+="&mklx="+mklx;
			refreshForm(url);
		}
		
		function loadPages(){
			if($("mklx")){
				var mklx=$("mklx").value;
				if(mklx!=""){
					$("qsfp_li").className="";
					$("cwfp_li").className="";
					$("lsxx_li").className="";
					$(mklx+"_li").className="ha";
				}	
			}
		}
		
		
		function addYqxx(){
			var url="/xgxt/gyglGywh.do?method=yqxxwh&doType=add";
			showTopWin(url);
		}
		
		function modiYqxx(){
			if (null == curr_row) {
				alert('��ѡ��һ��');
			} else {
				var yqdm = curr_row.getElementsByTagName('input')[0].value;
				var url = '/xgxt/gyglGywh.do?method=yqxxwh&doType=update&yqdm='+yqdm;
				showTopWin(url);
			}
		}
		
		function delYwsj(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				var mklx=$("mklx").value;
				var url="/xgxt/gyglGywh.do?method=ywsjwhManage";
				url+="&doType=del&mklx="+mklx;
				if(confirm("ȷ��Ҫɾ��ѡ�еļ�¼��?")){
					refreshForm(url);
					hiddenMessage(true,true);
					BatAlert.showTips('���ڲ��������Ե�...');
				}else {
					return false;
				}
			}else{
				alert("�빴ѡϣ��ɾ���ļ�¼!");
				return false;
			}
		
			
		}
		
		function searchRs(){
			allNotEmpThenGo('/xgxt/gyglGywh.do?method=ywsjwhManage&mklx=${mklx}');
		}
		
		function setPath(){
			$("path").value="gygl_gywh_jcsj.do&searchType=${mklx}";
		}
		
		function loadTitle(){
			$("input_mhcx_msg").style.left='130px';
			$("input_mhcx_msg").style.top='117px';
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
		</script>
	</head>
	<body onload="loadPages();loadTitle()">

		<html:form action="/gyglGywh" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ģ������ -->
			<input type="hidden" id="mklx" name="mklx" value="${mklx}"/>
			<input type="hidden" name="path" value="gygl_gywh_jcsj.do&searchType=xq"/>
			<input type="hidden" id="czxq" name="czxq" value="${czxq}"/><!-- ����У�� -->
			<input type="hidden" id="czyq" name="czyq" value="${czyq}"/><!-- ����԰�� -->
			<input type="hidden" id="xqdm" name="xqdm" value="${xqdm}"/><!-- У������ -->
			<input type="hidden" id="yqdm" name="yqdm" value="${yqdm}"/><!-- ԰������ -->
			<input type="hidden" id="lddm" name="lddm" value="${lddm}"/><!-- ¥������ -->
			<input type="hidden" id="cs" name="cs" value="${cs}"/><!-- ���� -->
			<input type="hidden" id="qsh" name="qsh" value="${qsh}"/><!-- ���Һ� -->
			<input type="hidden" id="dm" name="dm" value="${dm}"/><!-- ����԰�� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="delYwsj();return false;" class="btn_sc"> ɾ�� </a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">��������</a></li>		
						<li><a href="#" class="btn_dc" onclick="setPath();setSearchTj();configureExportData();return false;">��������</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">�����ֶ�ȷ��</a></li>
					</ul>
				</div>
				<div class="compTab" id="card" style="position:relative">
					<div class="comp_title" id="div1">
						<ul id="ul1">		
							<logic:iterate name="pageCard" id="pcard">				
							<li id="${pcard.dm}_li" class="ha">
								<a href="#" onclick="changePage(this);return false;" id="${pcard.dm}">
									<logic:equal name="pcard" property="mcxs" value="lable">	
										<span><bean:message key="${pcard.mc}" />��Ϣ</span>
									</logic:equal>
									<logic:notEqual name="pcard" property="mcxs" value="lable">	
										<span>${pcard.mc}</span>
									</logic:notEqual>
								</a>
							</li>
							</logic:iterate>
						</ul>	
					</div>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>

			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
						<font color="blue"></font>	
						</logic:notEmpty>
						
						<font color="blue">
						<logic:equal name="mklx" value="qsfp">
						ע���Ǳ�����ģ��¼������ݣ���ǰ�������ֶ�����ģ�����ȡ��������
						</logic:equal>
						
						<logic:equal name="mklx" value="cwfp">
						ע���Ǳ�����ģ��¼������ݣ���ǰ����λ�ֶ�����ģ�����ȡ��������
						</logic:equal>
						
						<logic:equal name="mklx" value="lsxx">
						ע���Ǳ�����ģ��¼������ݣ���ǰ����ʷס����Ϣģ�����ɾ��������
						</logic:equal>
						
						</font>
					</span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />

								</td>
								<logic:iterate id="tit" name="topTr"   offset="1" indexId="index">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand">
									<td>
										<!-- �ǵ������ݲ���ɾ�� -->
										<logic:iterate id="sjly" name="s" offset="${rsNum }">
											<logic:equal name="sjly" value="����">
												<input type="checkbox" name="checkVal" id="pkV"
													value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
													/>
											</logic:equal>
											<logic:notEqual name="sjly" value="����">
												<input type="checkbox" name="checkVal" id="pkV" disabled="disabled"/>
											</logic:notEqual>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglGywhForm"></jsp:include>
				</logic:notEmpty>
			</div>
		</html:form>
		<%@ include file="/comm/delMessage.jsp"%>
		<logic:equal name="result" value="true">
		<script>
			alert("�����ɹ�!");	
		</script>
		</logic:equal>
	</body>
</html>
