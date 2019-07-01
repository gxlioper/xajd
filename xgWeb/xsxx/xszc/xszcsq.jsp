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
		//��ѡһ����¼
		function updateOne(type,oper){
			var pk = "";
			var xn = "";
			var xq = "";
			var c = 0;
			if(oper=='view'){
				pk = curr_row.getElementsByTagName('input')[0].value;
				xn = curr_row.getElementsByTagName('input')[1].value;
				xq = curr_row.getElementsByTagName('input')[2].value;
			}else{
				var pkArr = document.getElementsByName('primarykey_cbv');
				var xnArr = document.getElementsByName('xnArr');
				var xqArr = document.getElementsByName('xqArr');
				for(var i=0;i<pkArr.length;i++){
					if(pkArr[i].checked){
						pk = pkArr[i].value;
						xn = xnArr[i].value;
						xq = xqArr[i].value;
						c++;
					}
				}
				if(c!=1){
					alertInfo('�빴ѡһ����Ҫ����������');
					return false;
				}
			}
			var url = 'xszcgl.do?method=xszcsqyydetail&pk=' + pk + '&xn=' + xn + '&xq=' + xq + '&type=' + type + '&oper=' + oper;
			showTopWin(url,400,300);
		}

		function ck(){
			//alert(curr_row.getElementsByTagName('input')[0].value);
			alert(curr_row.getElementsByTagName('input')[1].value);
			
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

		//ע��
		function zc(){
			var pkArr = document.getElementsByName('primarykey_cbv');
			var xnArr = document.getElementsByName('xnArr');
			var xqArr = document.getElementsByName('xqArr');
			var xn = "";
			var xq = "";
			var c = 0;
			for(var i=0;i<pkArr.length;i++){
				if(pkArr[i].checked){
					xn += xnArr[i].value + ',';
					xq += xqArr[i].value + ',';
					c++;
				}
			}
			if(c==0){
				alertInfo('��ѡ����Ҫ����������');
				return false;
			}
			//��ʾȷ����Ϣ
			confirmInfo('ȷ��Ҫע����', function(tag){
				if(tag == 'ok'){
					document.getElementById('zczt').value = '��ע��';
					document.getElementById('xn').value = xn;
					document.getElementById('xq').value = xq;
					document.forms[0].action = 'xszcgl.do?method=xszcsq&doType=zc';
					document.forms[0].submit();
				}
			});
		}
	</script>
</head>

<body>
	<html:form action="/xszcgl" method="post">
		<input type="hidden" name="message" id="message" value="${message }"/>
		<button type="button" onclick="allNotEmpThenGo('/xgxt/xsxx_xszcsq.do')" id="search_go" style="display: none"></button>
		<input type="hidden" id="zczt" name="zczt"/>
		<input type="hidden" id="xh" name="xh" value="${xh }"/>
		<input type="hidden" id="xn" name="xn"/>
		<input type="hidden" id="xq" name="xq"/>

		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
	  <div class="toolbox">
			<div class="buttonbox">
				<ul>
					<li> 
						<a href="#" onclick="zc();" class="btn_zj">ע��</a> 
					</li>
					<li>
						<a href="#" id="btn_sc"
						   onclick="updateOne('δע��')"
						   class="btn_sc">δע�� </a>
					</li>
					<li>
						<a href="#" id="btn_xg"
							onclick="updateOne('�ݻ�ע��')"
							class="btn_xg">�ݻ�ע�� </a>
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
					<font color="blue">��ʾ��˫�����Բ鿴��ϸ��Ϣ��������ͷ�������򣬱���ɫΪ��ɫ��ʾ��ѧ���Ѿ�ά������</font> 
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
						<logic:iterate id="tit" name="topTr" scope="request">
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
							ondblclick="updateOne('view','view');"
							style="cursor:hand;background: <logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>">
							<td>
								<input type="checkbox" id="cbv" name="primarykey_cbv"
									<logic:iterate id="v" name="s" offset="5" length="1">
										<logic:notEqual value="δ���" name="v"> disabled="disabled"</logic:notEqual>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<logic:notEqual value="${currXn }" name="v"> disabled="disabled"</logic:notEqual>
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="3" length="1">
										<logic:notEqual value="${currXq }" name="v"> disabled="disabled"</logic:notEqual>
									</logic:iterate>
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />	
												
								<logic:iterate id="v" name="s" offset="2" length="1">
									<input type="hidden" name="xnArr" value="${v }"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="3" length="1">
									<input type="hidden" name="xqArr" value="${v }"/>
								</logic:iterate>
								
							</td>
							<logic:iterate id="v" name="s" offset="2">
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
			alert(''+$('message').value);
			if (window.dialogArguments) {
				window.close();
				window.dialogArguments.document.getElementById('search_go').click();
			}
		</script>
	</logic:present>
</body>
</html>
