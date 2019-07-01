<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function loadSpgw(){
			if(curr_row){
				var splc=curr_row.getElementsByTagName('input')[0].value;
				if(curr_row != null){
					var url = "splcAjax.do?method=spgwLoad";
	   				url+= "&pkValue="+splc;
					jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false,
						success:function(result){
							reLoadSpgwInfo(result);
						}
					});
				}else{
					alert('��ѡ���������̣�');
					return false;
				}
			}else{
				alertError("�빴ѡ��Ҫ���ø�λ������!");
			}
			
		}

		function showDetail(lcid){
			showDialog('',700,400,'splcNew.do?method=splcZs&lcid='+lcid);
			return false;
		}
		
		function reLoadSpgwInfo(result){
			var divHtml="";
			$('gwlx').value="";
			if(result.length>0){
				for(var i=0;i<result.length;i++){
					divHtml+="<input type='radio' name='spgw' id='spgw"+i+"' "+result[i].gwlx+" value='"+result[i].spgw+"' />��"+(i+1)+"��:"+result[i].mc+"<br/>";
				}
				
				viewTempDiv("��λ����","tsxxDiv",350,200);
				$("div_gwxx").innerHTML=divHtml;
			}
		}
		
		function tsgwLoad(){
					var url = "splcAjax.do?method=tsgwLoad";
	   				//url+= "&pkValue="+splc;
					jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false,
						success:function(result){
							reLoadTsgwInfo(result);
						}
					});
		}
		
		function reLoadTsgwInfo(result){
			var divHtml="";
			$('gwlx').value="ty";
			if(result.length>0){
				for(var i=0;i<result.length;i++){
					divHtml+="<input type='radio' name='spgw' id='spgw"+i+"' "+result[i].gwlx+" value='"+result[i].spgw+"' />"+result[i].mc+"<br/>";
				}
				
				$("div_gwxx").innerHTML=divHtml;
				viewTempDiv("�����λ����","tsxxDiv",350,200);
			}else {
				alertInfo("û�п����õ������λ!");
			}
		}
		
		function showGwszWin(){
			var spgw=document.getElementsByName("spgw");
			var flag=false;
			var  spgwStr="";
			for(i=0;i<spgw.length;i++){
				if(spgw[i].checked){
					flag=true;
					spgwStr=spgw[i].value;
					break;	
				}
			}
			var gwlx = $("gwlx").value;
			if(flag){
				//showTopWin("splcNew.do?method=splcYhsz&spgw="+spgwStr+"&gwlx="+gwlx,800,650);
				showDialog("",800,650,"splcNew.do?method=splcYhsz&spgw="+spgwStr+"&gwlx="+gwlx);
			}else{
				alertError("��ѡ����Ҫ�����û��ĸ�λ!");
				return false;
			}
			
			hiddenMessage(true,true);
		}
		
		//��ѯҳ�������������
		function del(){

			var noLength = jQuery(".dateline tbody tr:has(input[type=checkbox][checkType='']:checked)").length;
			var yesLength = jQuery(".dateline tbody tr:has(input[type=checkbox][checkType='disabled']:checked)").length;
			if(noLength == 0 || yesLength > 0){
	    		alertError("�빴ѡ�Ƿ�ʹ��״̬Ϊ���񡱵ļ�¼!");
	    		return false;
	    	}	
			
			confirmInfo('ȷ��Ҫɾ���ѹ�ѡ��������',function(t){
				if (t=='ok'){
					refreshForm("/xgxt/splcNew.do?method=splcManage&doType=del");
				}
			})	
						
		}

		function update(){

			var lcid = jQuery("input[type=checkbox]:checked").val();
			var noLength = jQuery(".dateline tbody tr:has(input[type=checkbox]:checked)").length;

			if(noLength==1){
				showDialog('',700,400,'splcNew.do?method=splcUpdate&lcid='+lcid);
				
			}else{
				alertError("�빴ѡһ����Ҫ�޸ĵļ�¼");
				return false;
			}
			
		}
		
		
		function updateLcsz(){
				var url="splcAjax.do?method=updateLcsz";	
					
				jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false
					});
			}
		
		//ͨ�ø�λ����
		function tygwsz(){
		
			//·��
			var url = "splcAjax.do?method=createTygwDiv";	
			//����
		 	var parameter = {};
		 	showDialog('', 400, 210, url);
			/*jQuery("#div_gwxx").load(
				url,
				parameter,
				function(){
					$('gwlx').value="ty";
					tipsWindown("ͨ�ø�λ����","id:tsxxDiv","400","300","true","","true","id");
				}
			);*/
		}
		
		//�����λ����
		function tsgwsz(){
		if(curr_row){
			var spgw=document.getElementsByName("spgw");
				var splc=curr_row.getElementsByTagName('input')[0].value;
				if(curr_row != null){
					//·��
					var url = "splcAjax.do?method=createTsgwDiv";
					var splc=curr_row.getElementsByTagName('input')[0].value;
					//����
				 	var parameter = {
				 		"str_pkValue":splc
				 	};
				 	url+="&str_pkValue="+splc+"&spgw="+spgw;
				 	showDialog('', 400, 210, url);
					/*jQuery("#div_gwxx").load(
						url,
						parameter,
						function(){
							$('gwlx').value="";
							tipsWindown("�����λ����","id:tsxxDiv","400","300","true","","true","id");
						}
					);*/
				}else{
					alert('��ѡ���������̣�');
					return false;
				}
			}else{
				alertError("�빴ѡ��Ҫ���ø�λ������!");
			}
		}
		
		jQuery(function(){
			updateLcsz();
		})

		</script>
	</head>
	<body >

		<html:form action="/splcNew" method="post">
			<input type="hidden" name="message" id="message" value="${message}" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="gwlx" id="gwlx" value="" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showDialog('',850,400,'splcNew.do?method=splcAdd');return false;" class="btn_zj">
								���� </a>
						</li>
<!--						<li>-->
<!--							<a href="#" onclick="loadSpgw();return false;" class="btn_csh">-->
<!--								���ø�λ </a>-->
<!--						</li>-->
						<li>
							<a href="#" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="#" onclick="del();return false;" class="btn_sc"> ɾ��
							</a>
						</li>
<!--						<li>-->
<!--							<a href="#" onclick="tsgwLoad();return false;" class="btn_sz"> ͨ�ø�λ����-->
<!--							</a>-->
<!--						</li>-->
<%--						<li>--%>
<%--							<a href="#" onclick="tsgwsz();return false;" class="btn_csh">--%>
<%--								�Զ����λ��Ա���� </a>--%>
<%--						</li>--%>
<%--						<li>--%>
<%--							<a href="#" onclick="tygwsz();return false;" class="btn_sz">ͨ�ø�λ��Ա����--%>
<%--							</a>--%>
<%--						</li>--%>
					</ul>
				</div>



				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/splcNew.do?method=splcManage')">
											�� ѯ
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									��������
								</th>
								<td>
									<html:text property="lcmc" styleId="lcmc" style="width:150px" />
								</td>
								<th>
									����ģ��
								</th>
								<td>
									<html:select property="ssmk" styleId="ssmk" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="ssmkList" property="mkdm"
											labelProperty="mkmc" />
									</html:select>
								</td>
								<th>
								</th>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<%--				</logic:equal>--%>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> 
						<logic:notEmpty name="rs">
							<font color="blue">˫��ĳ����¼���ɽ����Զ����λ����Ա��ϵά����</font>
						</logic:notEmpty> </span>
				</h3>

				
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td align="center">
									<input type="checkbox" name="all" value="all" onclick="chec()" />

								</td>
								<logic:iterate id="tit" name="topTr" indexId="index" offset="1">
									<td id="<bean:write name="tit" property="en"/>" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						
						<tbody>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" ondblclick="showDetail('<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>');" style="cursor:hand">
									<td align="center">
										<input type="checkbox" name="checkVal" id="pkV" checkType="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>"										
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
						
						<!-- ������ -->
						<%@ include file="/comm/noRows.jsp"%>
						<!-- ������ end-->
						</tbody>
					</table>
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=xtwhShlcForm"></jsp:include>
			
			</div>
			<div id="tsxxDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th>
									<span> ��λ���� </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div id="div_gwxx">

									</div>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- ȷ�� -->
										<button type="button" onclick="showGwszWin();iFClose();return false;">
											<bean:message key="lable.btn_qd_space" />
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</html:form>
		<%@ include file="/comm/other/tsxx.jsp"%>
	</body>
</html>
