<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	<script language="javascript" src="js/xszzFunction.js"></script>
	<script language="javascript" src="js/String.js"></script>
	<script type="text/javascript" src="js/checkUtils.js"></script>
	<script type="text/javascript">
		//����
		function sq(){
			var cont = document.getElementById('cont').value;
			if(0==cont){
				showTopWin('jqlxgl.do?method=jqlxsqDetial&buttonCtrl=ok&oper=add',770,550);
			}else{
				alertInfo('��ѧ���Ѿ����룬�����ٴ����룡');
				return false;
			}
		}

		//ȫѡ
		function selectAll(){
			var checkBoxArr = document.getElementsByName('primarykey_cbv');
			var selall = document.getElementById('allS');
			for(var i=0;i<checkBoxArr.length;i++){
				if(checkBoxArr[i].disabled!=true){
					checkBoxArr[i].checked = selall.checked;
				}
			}
		}

		//ɾ��
		function del(checkboxName,url){
			var checkbox = jQuery('input[name='+checkboxName+']:checked');
			if(checkbox.length == 0){
				alertInfo('��ѡ����Ҫɾ��������');
				return false;
			} 
			//��ʾȷ����Ϣ
			confirmInfo('ȷ��Ҫɾ����ѡ���������', function(tag){
				if(tag == 'ok'){
					document.forms[0].action = url;
					document.forms[0].submit();
				}
			});
			if ($("pt")){
				BatAlert.showTips('���ڲ�������ȴ�...');
			}
		}
	</script>
</head>

<body>
	<html:form action="/jqlxgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<input type="hidden" name="save_xn" value="${xn }"/>
		<!-- 
		<input type="hidden" name="save_nd" value="${nd }"/>
		 -->
		<input type="hidden" name="save_xq" value="${xq }"/>
		<input type="hidden" id="cont" name="cont" value="${cont }"/>
		<button type="button" onclick="allNotEmpThenGo('/xgxt/xsxx_xsjqlxsq.do')" id="search_go" style="display: none"></button>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
	<!-- 	
		<div class="tab">
		<table class="formlist" width="100%">
			<tbody>
			<tr>
				<th>ѧ��</th>
				<td>
					${xn }
				</td>
				<th>ѧ��</th>
				<td>
					${xqmc }
				</td>
			</tr>
			<tr>
				<th>ѧ��</th>
				<td>
					${map.xh }
					<input type="hidden" id="save_xh" name="save_xh" value="${map.xh }"/>
				</td>
				<th>����</th>
				<td>
					${map.xm }
				</td>
			</tr>
			<tr>
				<th>�Ա�</th>
				<td>${map.xb }</td>
				<th>����Ա</th>
				<td>
					${map.fdyxm }
				</td>
			</tr>
			<tr>
				<th><bean:message key="lable.xsgzyxpzxy" /></th>
				<td>${map.xymc }</td>
				<th>רҵ</th>
				<td>${map.zymc }</td>
			</tr>
			<tr>
				<th>�༶</th>
				<td>${map.bjmc }</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			</tbody>
		</table>
	  </div>
	   -->
	  <div class="toolbox">
			<div class="buttonbox">
				<ul>
					<li> 
						<a href="#" onclick="sq();" class="btn_zj">����</a> 
					</li>
					<li>
						<a href="#" id="btn_xg"
						   onclick="showViewWindow('primarykey_cbv','jqlxgl.do?method=jqlxsqDetial&buttonCtrl=ok','770','550')"
						   class="btn_xg"> �޸� </a>
					</li>
					<li>
						<a href="#" id="btn_sc"
							onclick="del('primarykey_cbv','jqlxgl.do?method=jqlxsq&doType=del')"
							class="btn_sc"> ɾ�� </a>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="formbox" id="result">
			<h3 class="datetitle_01">
		    <span>
		    	��ѯ���&nbsp;&nbsp;
		    	<logic:empty name="rs">
					<font color="red">δ�ҵ��κμ�¼��</font> 
		 		 </logic:empty>
		 		 <logic:notEmpty name="rs">
					<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ��������ͷ��������</font> 
		 		 </logic:notEmpty>
		    </span>
		    </h3>
			<logic:notEmpty name="rs">
			  <div class="con_overlfow">
			  <table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
			    <thead>
			      <tr align="center" style="cursor:hand">
			      		<td>
			      			<input type="checkbox" id="allS" name="allS" onclick="selectAll();"/>
			      		</td>
						<logic:iterate id="tit" name="topTr" offset="4" scope="request">
							<td id="${tit.en}"
								onclick="tableSort(this)">
								${tit.cn}
							</td>
						</logic:iterate>
					</tr>
			    </thead>
			    <tbody>
					<logic:iterate name="rs" id="s">
						<tr onclick="rowOnClick(this)"
							ondblclick="showInfo('/xgxt/jqlxgl.do?method=jqlxsqDetial','view','770','700');"
							style="cursor:hand;background: <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>">
							<td>
								<!-- 
								<input type="hidden" value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"/>
								 -->
								<input type="checkbox" id="cbv" name="primarykey_cbv" alt=""
									<logic:iterate id="v" name="s" offset="11" length="1">
								<logic:notEqual value="δ���" name="v"> disabled="disabled"</logic:notEqual>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="12" length="1">
								<logic:notEqual value="δ���" name="v"> disabled="disabled"</logic:notEqual>
								</logic:iterate>
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />	
							</td>
							<logic:iterate id="v" name="s" offset="4">
								<td>
									<bean:write name="v" />
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
				</tbody>			
			   </table>
			   </div>
			</logic:notEmpty>
			</div>
	  
	</html:form>
	<logic:present name="result">
		<script>
			alertInfo(''+$('message').value);
			if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
