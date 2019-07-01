<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/default/easyui.css" />
		<link rel="stylesheet" type="text/css"
			href="js/jquery/themes/icon.css" />
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/comm/searchTj.js"></script>
		<script language="javascript" defer="defer">
		//����ģ��
		function mbDownLoad(tag){
			if(tag=='ok'){
				//ģ����ѯ
				var input_mhcx = opener.document.getElementById("input_mhcx").value;
				
				//�����ѯ
				var searchLx = new Array();
				var searchTj = new Array();
				var searchTjz = new Array();
				
				var n=0;
				var m=3;
				
				searchLx[0]="xy";
				searchLx[1]="zy";
				searchLx[2]="bj";
				
				for(var i=0;i<jytj.length;i++){
					searchLx[m]=jytj[i];
					m++;
				}
				
				var tj_num = opener.document.getElementById("searchTjDiv").getElementsByTagName('input').length;
					
				for(var j=0;j<tj_num;j++){
					var obj = opener.document.getElementById("searchTjDiv").getElementsByTagName('input')[j];
					searchTj[n]=obj.name;
					searchTjz[n]=escape(obj.value);
					n++;
				}
				
				//������Ŀ
				var czxm = $("czxm").value;
				//������ʽ	
				var dcxs = jQuery('input[name=bmlx]:checked').val();
				
				//��������
			 	var parameter = {
			 		"dcxs":dcxs,
			 		"czxm":czxm,
					"input_mhcx":input_mhcx,
					"searchTj":searchTj.join("!!@@!!"),
					"searchTjz":searchTjz.join("!!@@!!"),
					"searchLx":searchLx.join("!!@@!!")
				};
				
				$("divWaiting").style.top="120px"
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				var url="zhcpSjdr.do?method=expToExcel";
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
		}
		
		//��ʾ�����ɹ���Ϣ
		function dcSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			//$("divWaiting").style.top="0px"
			alertInfo(result,function(t){
				if (t == 'ok'){
					jQuery('#search_go').click();
				}
			});
		}
		
		//��ʾ������Ϣ
		function showDcInfo(){
			
			confirmInfo('���ɵ���ģ�潫����ԭ�еģ���ȷ��Ҫ��������?',function(t){
			
				if (t == 'ok'){
					createStencil();
				}
			
			})
		}
		
		//����ģ��
		function createStencil(){
			opener.document.getElementById("createTj").click();
			//ģ����ѯ
			var input_mhcx = opener.document.getElementById("input_mhcx").value;
			//�����ѯ
			var searchLx = new Array();
			var searchTj = new Array();
			var searchTjz = new Array();
			
			var n=0;
			var m=3;
			
			searchLx[0]="xy";
			searchLx[1]="zy";
			searchLx[2]="bj";
			
			for(var i=0;i<jytj.length;i++){
				searchLx[m]=jytj[i];
				m++;
			}
		
			var tj_num = opener.document.getElementById("searchTjDiv").getElementsByTagName('input').length;

			for(var j=0;j<tj_num;j++){
				var obj = opener.document.getElementById("searchTjDiv").getElementsByTagName('input')[j];
				searchTj[n]=obj.name;
				searchTjz[n]=escape(obj.value);
				n++;
			}
			
			//������ʽ
			var dcxs = jQuery('input[name=bmlx]:checked').val();
			
			//��������
		 	var parameter = {
		 		"dcxs":dcxs,
				"input_mhcx":input_mhcx,
				"searchTj":searchTj.join("!!@@!!"),
				"searchTjz":searchTjz.join("!!@@!!"),
				"searchLx":searchLx.join("!!@@!!")
			};
			
			var url="zhcpSjdr.do?method=showDcInfo";
			jQuery.post(url,parameter,function(result){
				if(result == "false"){
					alertInfo("�޷�������ѡ�������˳����ţ�������ȷ�ϣ�");
				}else{
					confirmInfo(result,mbDownLoad);
				}
			});
		}
		
		function xzmb(){
			var checkbox = jQuery('input[name=primarykey_cbv]:checked');
			
			if (checkbox.length > 0){
				refreshForm('zhcpSjdr.do?method=downloadStencil');
				jQuery("#search_go").attr('disabled',false);
	   			jQuery("#search_go").attr('class','btn_cx');
			} else {
				alertInfo('�빴ѡ��Ҫ���ص�ģ��!',function(){});
			}
		}
		</script>
	</head>
	<body onload="">

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�������� - �ۺ����ʲ��� - �۲��ά��</a>
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
				1.����ǰҳ����ѡ���<font color="blue">��������</font>���ģ�塣</br>
				2.������Ͻ�<font color="blue">����ģ��</font>������ģ��Ĵ������Ա����ء�</br>
				3.������ʽ�����Ժ������ݷ�Χ����<font color="blue">excel</font>�������ѡ�ǡ��༶���Ļ�����ô����ѡ·���������԰༶</br>&nbsp;&nbsp;&nbsp;Ϊ��λ��ѧ����Ϣ��"<bean:message key="lable.xb" />"ͬ��</br>
				4.�����ǰҳ��Ĺ���������ѡ��ĳ�꼶��������ʽѡ�񡰰༶������ôϵͳ��������꼶�µ�����</br>&nbsp;&nbsp;&nbsp;�༶���������������Դ����ơ�</br>
				5.���δѡ�������Ļ�������ȫУ���а༶����Ϣ��������Ӱ�쵼��<font color="blue">Ч��</font>����ע�⡣</br>
				6.�������ݱ���ʹ�ñ����������ģ�壬�������<font color="blue">�����쳣</font>��</br>
				7.�����ϣ������������ģ��Ļ����빴ѡ��Ӧ��¼�����<font color="blue">����ģ��</font>��</br>
				8.����ģ��ΪRAR��ʽ��ѹ��������Ҫ<font color="blue">��ѹ</font>����в�����
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/zhcpSjdr" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }" />
			<!-- ģ�嵼��Div-->
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showDcInfo();return false;" class="btn_csh">
								����ģ��
							</a>
						</li>
						<li>
							<a href="#" onclick="xzmb();return false;" class="btn_down">
								����ģ��
							</a>
						</li>
					</ul>
				</div>
				
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>��������</th>
								<td>
									<html:radio property="bmlx" value="xy" ></html:radio>
									<bean:message key="lable.xsgzyxpzxy" />
									<html:radio property="bmlx" value="bj" ></html:radio>
									�༶
								</td>
								<th>����ģ������</th>
								<td>
									<html:text property="wjmc"></html:text>
								</td>	
								<td colspan="2">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="refreshForm('zhcpSjdr.do?method=sjdrExp&czxm=${czxm }')">
											�� ѯ
										</button>
									</div>
								</td> 
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">��ʾ����ҳǰ����������ѡ���ģ��</font>
						</logic:notEmpty> 
					</span>
				</h3>
				<table summary="" class="dateline" width="100%">
					<thead>
						<tr>
							<td>
								<input type="checkbox" disabled="disabled"/>
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" scope="request">
								<td onclick="tableSort(this)">
									${tit}
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<tbody>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="s">
								<tr>
									<td>
										<input type="checkbox" name="primarykey_cbv"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</td>
									<logic:iterate id="v" name="s" offset="0">
										<td>
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</logic:notEmpty>
					</tbody>
				</table>
			</div>
			<!--��ҳ��ʾ-->
			<jsp:include flush="true"
				page="/sjcz/turnpage.jsp?form=zhcpSjdrForm"></jsp:include>
			<!--��ҳ��ʾ-->
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

