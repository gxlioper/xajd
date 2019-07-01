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
		<script type="text/javascript">
		//�鿴
		function getView(url){
			url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
			showTopWin(url,800,600);
		}		

		//�޸�
		function modi(url){
			if(curr_row != null){
				url+="&pkValue="+curr_row.getElementsByTagName('input')[0].value;
				showTopWin(url,800,600);
				return false;
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
		}

		//ɾ��
		function submitInfo(url,doType,msg){
			var pkV=document.getElementsByName("checkVal");
			var	blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked){
					blog=true;
					break;
				}
			}
			if(blog){
				confirmInfo("ȷ��"+msg+"��",function(data){
					if("ok"==data){
						url+="&doType="+doType;
						showTips('���������У���ȴ�......');
						refreshForm(url);
					}
				});
			}else{
				alertInfo('�빴ѡҪ�����������У�');
				return false;
			}
		}
		
		function searchRs(){
			refreshForm('xljk_zjzy_tsxswh.do');
		}
		</script>
	</head>
	<body>
		<html:form action="/xljk_tsxswh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur">
				<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
			</div>
			 <!-- ��ť -->
			 <!-- �ж�дȨ -->
			 <div class="toolbox">
				 <div class="buttonbox">
				    <ul>
					<li> <a href="#" onclick="showTopWin('/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=add',800,600);return false;" class="btn_zj"> ���� </a> </li>
				    <li> <a href="#" onclick="modi('/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=modi');return false;" class="btn_xg"> �޸� </a> </li>
					<li> <a href="#" onclick="submitInfo('/xgxt/xljk_tsxswh.do?method=tsxswhManage','del','ɾ��');return false;" class="btn_sc"> ɾ�� </a> </li>
					<li> <a href="#" onclick="impAndChkData()" class="btn_dr"> ���� </a> </li>
					<li> <a href="#" onclick="dataExport()" class="btn_dc"> ���� </a> </li>
					
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
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
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
								ondblclick="getView('/xgxt/xljk_tsxswh.do?method=tsxswhEdit&doType=view');return false;">
								<td align="center">
									<input type="checkbox" id="checkVal" name="checkVal" 
											value="<logic:iterate id="v" name="s" offset="7" length="1"><bean:write name="v"/></logic:iterate>"/>
									<input type="hidden" id="pkValue" name="pkValue" 
											value="<logic:iterate id="v" name="s" offset="7" length="1"><bean:write name="v"/></logic:iterate>"/>
								</td>
								<logic:iterate id="v" name="s" offset="0" length="7">
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
			     <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=tsxswhForm"></jsp:include>
			  	<!--��ҳ��ʾ-->
			</logic:notEmpty>
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
