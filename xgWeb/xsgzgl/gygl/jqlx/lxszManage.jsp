<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script language="javascript">	     
     function saveLxsz(){
     	var url = "gyglnew_jqlx_ajax.do?method=checkChangeShlc";
     	var lcid= jQuery("[name=lcid]:checked").val();
     	var parameter ={
     		"lcid":lcid
     	};
     	jQuery.post(url,parameter,
				
			function(result){
     			
     			if(result==""){
     				confirmInfo("�ò��������޸ļ�����У�������ã��Ƿ�ȷ�������ò�����",saveLxszInfo);
     			}else{
     			
     				alertInfo(result);
     				return false;
     			}
     		});
     }
    
     function saveLxszInfo(tag){
     		
			if(tag=="ok"){
				
				//����
				var pkValue=new Array();
	
				jQuery.ajaxSetup({async:false});
				
				// �õ�JSON����
		        var parameter ={};
				
				parameter["sqkg"]=jQuery("[name=sqkg]:checked").val();
				
				parameter["lcid"]=escape(jQuery("[name=lcid]:checked").val());
				
				var url = "gyglnew_jqlx_ajax.do?method=saveLxsz";
	          	
			 	$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
						
						
					}
				);
				
				jQuery.ajaxSetup({async:true});
			}
		}
		
</script>
	</head>
	<body onload="">
	
		<html:form action="/gyglnew_jqlx" method="post">
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:100%;height:510px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tbody >
						<tr style="height:22px">
							<th width="30%">
								<font color="red">*</font>���뿪��
							</th>
							
							<td width="70%" colspan="3">
								<html:radio property="sqkg" styleId="sqkg_0" value="open"></html:radio>��������
								<html:radio property="sqkg" styleId="sqkg_1" value="close"></html:radio>�ر�����
							</td>
						</tr>
						<tr>
							<th width="30%">
								<font color="red">*</font>�������
							</th>
							<td width="70%" colspan="3">
								<html:radio property="lcid" styleId="lcid_0" value="no"/>��<br/>
								<logic:iterate name="shlcList" id="shlc" indexId="index" offset="0">
									<html:radio property="lcid" styleId="lcid_${index+1 }" value="${shlc.splc }"/>${shlc.lcxx}<br/>
								</logic:iterate>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>

								<div class="btn">
									
									<logic:equal name="writeAble" value="yes">
									<button type="button" id="buttonSave" onclick="saveLxsz();">
										�� ��
									</button>
									</logic:equal>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

