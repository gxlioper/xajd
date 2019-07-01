<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="xsgzgl/xlzx/yysqnew/js/addYysqInfo.js"></script>
		<script type="text/javascript" >
		function saveYysqInfo(){
			if(jQuery("#xstell").val()=='' || jQuery("#yyzxzt").val()==''){
				return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
			}
			// �õ�JSON����
		    var parameter ={
				xh:jQuery("#xh").val(),
				zgh:jQuery("#zgh").val(),
				status:1,//����ԤԼ������Ϣ����״̬��Ϊ1ԤԼ��.
				xstell:jQuery("#xstell").val(),
				yyzxrq:jQuery("#yyzxrq").val(),
				qssj:jQuery("#qssj").val(),
				jssj:jQuery("#jssj").val(),
				yyzxzt:jQuery("#yyzxzt").val(),
				yyzxxq:jQuery("#yyzxxq").val()
			};
			var url = "xlzx_yysqnew.do?method=saveYysqInfo";
			showConfirm("ȷ�ϱ���ԤԼ��Ϣ��",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,function(data){
					if(data == true){
						showAlert("����ɹ���",{},{"clkFun":function(){
							frameElement.api.opener.refreshForm("xlzx_yysqnew_yysqnew.do");
							iFClose();
					}});
					}else{
						showAlert("����ԤԼ��Ϣʧ�ܣ�",{},{"clkFun":function(){
						}});
					}
				},'json');
				jQuery.ajaxSetup({async:true});
			}});
		}			
		
		</script>
	</head>
	<body >
	
		<html:form action="/xlzx_yysqnew" method="post">		
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="xh" id="xh" value="${yysqInfo.xh}" />
			<input type="hidden" name="zgh" id="zgh" value="${yysqInfo.zgh}" />
			<input type="hidden" name="yyzxrq" id="yyzxrq" value="${yysqInfo.yyzxrq}" />

			<div style='width:100%;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr >
							<th colspan="4">
								<span>ԤԼ������Ϣ</span>
							</th>
						</tr>
					</thead>
						<tbody id="yysqInfo">
						<tr style="height:10px">
							<th  width="16%">
								ԤԼ��ѯ����
							</th>
							<td width="34%">
									<span class="red"><B>${ yysqInfo.yyzxrq}</B></span>
							</td>
						 	<th width="16%">
								ԤԼ��ѯʱ��
							</th>
							<td width="34%">
								<html:text property="qssj" styleId="qssj"   style="width:30%"  value="${yysqInfo.qssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;��&nbsp;
								<html:text property="jssj" styleId="jssj"   style="width:30%"  value="${yysqInfo.jssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
							</td>
						</tr>
						
						<tr style="height:10px">	
							<th>
								<span class="red">*</span>Ԥ����ϵ����
							<td>
								<html:text property="xstell" styleId="xstell"  maxlength="11"  value="${ yysqInfo.xstell}" readonly="fasle" onblur="checkInputData(this);"/>
							</td>
							<th width="16%">
								ѧ�� ѧ��
							</th>
							<td width="34%">
								${currxn}&nbsp;&nbsp;${currxq}
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								<span class="red">*</span>��ѯ����
							</th>
							<td colspan="3">
								<html:text property="yyzxzt" styleId="yyzxzt" style="width:98%"   maxlength="100"  value="${ yysqInfo.yyzxzt}" />
							</td>
						</tr>
						<tr style="height:10px">
						<th>��ѯ��Ҫ<br/>
								<font color="red"><B>(��500��)</B></font>
							</th>
							<td colspan="3">
									<html:textarea  property='yyzxxq' styleId="yyzxxq" style="word-break:break-all;width:99%" 
									value="${ yysqInfo.yyzxxq}" onblur="chLeng(this,500);" rows='6' />
							</td>
						</tr>
			
					</tbody>
					</table>
				</div>
				<table  border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveYysqInfo();return false;">
										�� ��
									</button>
									<button onclick="Close();return false;">
										�� ��
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		
		</html:form>
	</body>
</html>

