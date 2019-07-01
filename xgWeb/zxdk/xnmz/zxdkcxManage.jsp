<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�qlj -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglCwgl.js"></script>
		<script language="javascript" defer="defer">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('/xgxt/zxdk_xnmz.do?method=zxdkcxManage&doType=query');
		}
		
		function addZxdk(){
			showTopWin("/xgxt/zxdk_xnmz.do?method=zxdkSq",800,600);
		}
		
		function modi(){
			var url='/xgxt/zxdk_xnmz.do?method=zxdkModi&doType=update';
			if(curr_row != null){
				showTopWin(url + '&xh='+curr_row.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function view(obj){
			var url='/xgxt/zxdk_xnmz.do?method=zxdkModi&doType=view';
			if(curr_row != null){
				showTopWin(url + '&xh='+obj.getElementsByTagName('input')[0].value,800,600);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		function zxdkShlcgz(){
			var url='/xgxt/zxdk_xnmz.do?method=zxdkShlcgz';
			if(curr_row != null){
				showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,500,300);
				return true;
			}else{
				alert('��ѡ��Ҫ�޸ĵ������У�');
				return false;
			}
		}
		
		
		//��ѯҳ�������������
		function del(){
		  	var pkValue=document.getElementsByName("checkVal");
		  
			var bool=false;
			for (i=0; i<pkValue.length; i++){
		    	if(pkValue[i].checked){
		    		bool=true;
		    	}
			}
			
			if(!bool){
				alertError("�빴ѡ��Ҫɾ��������!");
				return false;
			}
			
			confirmInfo('ȷ��Ҫɾ���ѹ�ѡ��������',function(t){
				if (t=='ok'){
					refreshForm("/xgxt/zxdk_xnmz.do?method=zxdkcxManage&doType=del");
				}
			})	
						
		}
		
		function zhgjzxdkb(dyblx){
		
			if(curr_row){
				var pkValue= curr_row.getElementsByTagName('input')[0].value;
				window.open("/xgxt/zxdk_xnmz.do?method=zxdkbbdy&pkValue="+pkValue+"&dyblx="+dyblx,800,600);
			}else {
				alertInfo("����ѡ��һ����¼!");
			}
		}
		</script>
	</head>
	<body onload="">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		
		<!-- ���� end-->
		<html:form action="/zxdk_xnmz">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<!-- �๦�ܲ����� -->
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<!-- ȷ�� -->
						<li>
							<a href="#" id="btn_shtg" onclick="addZxdk();return false;"
								class="btn_zj" > ���� </a>
						</li>
						<!-- �ֶ����� -->
						<li>
							<a href="#" id="btn_xg"
								onclick="modi();return false;"
								class="btn_xg"> �޸� </a>
						</li>
						<!-- �ֶ����� -->
						<li>
							<a href="#" id="btn_sh"
								onclick="del();return false;"
								class="btn_sc"> ɾ�� </a>
						</li>
						</logic:equal>
						<li>
							<a href="#" id="btn_sh"
								onclick="zxdkShlcgz();return false;"
								class="btn_csh"> ���̸��� </a>
						</li>
						<li>
							<a href="#" id="btn_sh"
								onclick="zhgjzxdkb('zhgjzxdk');return false;"
								class="btn_dy"> ���й�����ѧ���� </a>
						</li>
						<li>
							<a href="#" id="btn_sh"
								onclick="zhgjzxdkb('gjzxdkxsxx');return false;"
								class="btn_tj"> ѧ��������ѧ������Ϣ </a>
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
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span> ��ѯ��� <logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rsArrList">
							&nbsp;&nbsp;<font color="blue">˫��һ����¼���Բ鿴��ϸ��Ϣ��</font>
						</logic:notEmpty>
					</span>
				</h3>
				<div class="con_overlfow">
					<table summary="" class="dateline" align="" width="100%">
						<!-- ��ͷ -->
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<!-- ��ͷ end-->
						<!--���� -->
						<logic:notEmpty name="rsArrList">
							<logic:iterate name="rsArrList" id="s" indexId="index">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="view(this)">
									<td>
										<div align="center">
										<input type="checkbox" name="checkVal" id="pkV"
											<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
										</div>
									</td>
									<!-- ��ʾ��Ϣ -->
									<logic:iterate id="v" name="s" offset="2">
										<td align="left" nowrap="nowrap">
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						<!--���� end-->
						<!-- ������ -->
						<%@ include file="/comm/noRows.jsp"%>
						<!-- ������ end-->
					</table>
				</div>
				<!--��ҳ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=zxdkForm"></jsp:include>
				<!--��ҳend-->
			</div>
			<!-- ��ѯ��� end-->


			<div id="fpxx" style="display: none" style="width:650px">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th>
									<span> ������Ϣ </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<p id="Bmfpxx" class="tab"
										style="width:100%;height:200px;overflow-x:hidden;overflow-y:auto;">
									</p>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button onclick="zdfpcw();">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- ��ʾ��Ϣend -->

			<!-- ��ȴ� -->
			<%@ include file="/comm/loading.jsp"%>
			<!-- ��ȴ� end-->
			
		</html:form>
	</body>
</html>
