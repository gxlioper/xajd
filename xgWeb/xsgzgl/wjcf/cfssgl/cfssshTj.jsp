<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>	
		
		<script type="text/javascript">

		//�ύ
		function tijiao(){
			confirmInfo("�˲��������ύ��˽����ļ�¼���ύ����˲������޸ģ��Ƿ�ȷ����",function(tag){
				if(tag=="ok"){
					refreshForm('wjcfCfssgl_cfsssh.do?method=cfssshTj&doType=tj');
				}
			});
		}

		//�رյ�����
		function divtmpclose() {
			parent.document.getElementById("tmpdiv1").innerHTML = "";
			try{
				parent.hiddenMessage(true,true);
			}catch(e){				
			}
		}

		</script>
	</head>
	<body >
		<html:form action="/wjcfCfssgl_cfsssh.do?method=cfssshTj" method="post">
					<table width="100%" border="0" class="formlist">
					<tbody>
					<thead>
							<tr>
								<th colspan="4">
									<span>���ͳ����Ϣ</span>
								</th>
							</tr>
						</thead>

						<tr>
							<td colspan="4">
								<table class="formList" width="100%">
								<thead align="center">
									<tr align="center">
										<td ><b>ά��ԭ����</b></td>
										<td ><b>��������</b></td>
										<td ><b>���Ĵ���</b></td>
									</tr>
								</thead>
								<tbody align="center">
							<logic:notEmpty name="shtjList">
								<logic:iterate name="shtjList" id="s">
										<tr  style="cursor:hand">
										<td >
											${s.wcycf}
										</td>
										<td >
											${s.cxcf}
										</td>
										<td >
											${s.ggcf}
										</td>
										</tr>
										</logic:iterate>
										</logic:notEmpty>
										</tbody>
								
								</table>
							</td>
						</tr>
							
					</tbody>
						
					<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							
							<div class="btn">
								<button type="button"  onclick="tijiao();return false;" id="buttonSave">
										�ύ
								</button>
								<button type="button" onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
				</table>
				</input>
			</div>
			
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					
					
					showAlert("����ʧ�ܣ�",{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					showAlert("�����ɹ���",{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
					
					</script>
				</logic:equal>
			</logic:notEmpty>
	</body>
</html>
