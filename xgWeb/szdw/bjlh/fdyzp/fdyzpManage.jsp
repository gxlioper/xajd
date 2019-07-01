<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
		//�鿴������
		function getFdyzp(url){
			url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
			showTopWin(url,800,600);
		}		

		//�޸�������
		function modiFdyzp(url){
			if(curr_row != null){
				if(curr_row.getElementsByTagName('input')[1].value=="0"){
					url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
					showTopWin(url,800,600);
					return true;	
				}else{
					alertInfo('�˼�¼�ѱ�ʹ�ã������ٴν����޸ģ�');
					return false;
				}
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}

		//��ʾ���Ʋ�����	
		function showCopyFdyzp(){
			if(curr_row != null){
				viewTempDiv('����','showDiv',280,200);
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}

		//����������
		function copyFdyzp(url,tj){
			url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
			saveUpdate(url,tj);
		}

		//������������״̬ ,ɾ��
		function submitInfo(url,doType,msg){
			if(curr_row != null){
				if(doType=="del" && curr_row.getElementsByTagName('input')[1].value=="1"){
					alertInfo('�˼�¼�ѱ�ʹ�ã����ܽ���ɾ����');
					return false;
				}else{
					confirmInfo("ȷ��"+msg+"��",function(data){
						if("ok"==data){
							url+="&doType="+doType+"&pkValue="+curr_row.getElementsByTagName('input')[0].value;
							showTips('���������У���ȴ�......');
							refreshForm(url);
						}
					});
				}
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}
		
		function searchRs(){
			refreshForm('bjlh_fdykh_fdyzp.do');
		}
		</script>
	</head>
	<body>
		<html:form action="/bjlh_fdyzp" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
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
					���и���Ա������������ɡ��޸ġ���ɾ������δ���õ��������޷�����
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			 <!-- ��ť -->
			 <!-- �ж�дȨ -->
			 <div class="toolbox">
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" onclick="showTopWin('/xgxt/bjlh_fdyzp.do?method=fdyzpEdit&doType=add',800,600);return false;" class="btn_zj"> ���� </a> </li>
				    <li> <a href="#" onclick="modiFdyzp('/xgxt/bjlh_fdyzp.do?method=fdyzpEdit&doType=modi');return false;" class="btn_xg"> �޸� </a> </li>
					<li> <a href="#" onclick="submitInfo('/xgxt/bjlh_fdyzp.do?method=fdyzpManage','del','ɾ��');return false;" class="btn_sc"> ɾ�� </a> </li>
					<li> <a href="#" onclick="showCopyFdyzp();return false;" class="btn_sx"> ���� </a> </li>
					<li> <a href="#" onclick="submitInfo('/xgxt/bjlh_fdyzp.do?method=fdyzpManage','qy','����');return false;" class="btn_shtg"> ���� </a> </li>
					<li> <a href="#" onclick="submitInfo('/xgxt/bjlh_fdyzp.do?method=fdyzpManage','ty','ͣ��');return false;" class="btn_shbtg"> ͣ�� </a> </li>
					
				    </ul>
				 </div>
				 <!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs" >
								<font color="blue"> 
									��ʾ��˫��һ�п��Բ鿴��ϸ��Ϣ��������ͷ��������;</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
			<logic:notEmpty name="rs">
			<div class="con_overlfow">
				 <table summary="" id="rsTable" class="dateline" width="100%">
			    	<thead>
							<tr align="center" style="cursor:hand">
								<td style="display: none"></td>
								<logic:iterate id="tit" name="topTr" indexId="index">
									<td onclick="tableSort_hc(this,1)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand" align="left"
								ondblclick="getFdyzp('/xgxt/bjlh_fdyzp.do?method=fdyzpEdit&doType=view');return false;">
								<%--<input type="checkbox" id="checkVal" name="checkVal" 
									<logic:iterate id="v" name="s" offset="5" length="1"><bean:write name="v"/></logic:iterate>
											value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>											
								--%>
								<td style="display: none">
									<input type="hidden" id="pkValue" name="pkValue" 
										value="<logic:iterate id="v" name="s" offset="0" length="1"><bean:write name="v"/></logic:iterate>"/>
									<input type="hidden" id="kfxg" name="kfxg" 
										value="<logic:iterate id="v" name="s" offset="5" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="1" length="4">
									<td>
											<bean:write name="v" />
									</td>
								</logic:iterate>
								
							</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
				<!--��ҳ��ʾ-->
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdyzpForm"></jsp:include>
			  	<!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>
			<div id="showDiv" style="display:none;height: 500px" align="center">
				<table class="formlist" border="0" align="center" style="width: 100%;">
					<thead>
						<tr>
							<th colspan="2">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>		
						<tr>
							<td align='right' width='25%'>
								<font class="red">*</font>ѧ��
							</td>
							<td align='left'>
								<html:select property="xn" styleId="select_xn">
										<html:options collection="addxnList" property="xn" labelProperty="xn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align='right' width='25%'>
								<font class="red">*</font>����
							</td>
							<td align='left'>
								<input type="text" name="mc" id="mc" maxlength="100"/>
							</td>
						</tr>
					</tbody>		
					<tfoot>
						<tr>
							<td colspan='2'>
								<div class="btn">
									<button type="button" onclick="copyFdyzp('/xgxt/bjlh_fdyzp.do?method=fdyzpManage&doType=copy','xn-mc')">
										����
									</button>
									&nbsp;&nbsp;
									<button type="button" onclick='hiddenMessage(true,true)'>
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
