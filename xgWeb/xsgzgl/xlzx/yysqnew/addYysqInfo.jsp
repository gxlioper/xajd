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
				return showAlert("请将带<font color='red'>*</font>的项目填写完整！");
			}
			// 得到JSON对象
		    var parameter ={
				xh:jQuery("#xh").val(),
				zgh:jQuery("#zgh").val(),
				status:1,//新增预约申请信息，将状态置为1预约中.
				xstell:jQuery("#xstell").val(),
				yyzxrq:jQuery("#yyzxrq").val(),
				qssj:jQuery("#qssj").val(),
				jssj:jQuery("#jssj").val(),
				yyzxzt:jQuery("#yyzxzt").val(),
				yyzxxq:jQuery("#yyzxxq").val()
			};
			var url = "xlzx_yysqnew.do?method=saveYysqInfo";
			showConfirm("确认保存预约信息？",{"okFun":function(){
				jQuery.ajaxSetup({async:false});
				jQuery.post(url,parameter,function(data){
					if(data == true){
						showAlert("保存成功！",{},{"clkFun":function(){
							frameElement.api.opener.refreshForm("xlzx_yysqnew_yysqnew.do");
							iFClose();
					}});
					}else{
						showAlert("保存预约信息失败！",{},{"clkFun":function(){
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
								<span>预约申请信息</span>
							</th>
						</tr>
					</thead>
						<tbody id="yysqInfo">
						<tr style="height:10px">
							<th  width="16%">
								预约咨询日期
							</th>
							<td width="34%">
									<span class="red"><B>${ yysqInfo.yyzxrq}</B></span>
							</td>
						 	<th width="16%">
								预约咨询时段
							</th>
							<td width="34%">
								<html:text property="qssj" styleId="qssj"   style="width:30%"  value="${yysqInfo.qssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />&nbsp;至&nbsp;
								<html:text property="jssj" styleId="jssj"   style="width:30%"  value="${yysqInfo.jssj}" onfocus="WdatePicker({dateFmt:'HH:mm'})" />
							</td>
						</tr>
						
						<tr style="height:10px">	
							<th>
								<span class="red">*</span>预留联系号码
							<td>
								<html:text property="xstell" styleId="xstell"  maxlength="11"  value="${ yysqInfo.xstell}" readonly="fasle" onblur="checkInputData(this);"/>
							</td>
							<th width="16%">
								学年 学期
							</th>
							<td width="34%">
								${currxn}&nbsp;&nbsp;${currxq}
							</td>
						</tr>
						<tr style="height:10px">
							<th>
								<span class="red">*</span>咨询主题
							</th>
							<td colspan="3">
								<html:text property="yyzxzt" styleId="yyzxzt" style="width:98%"   maxlength="100"  value="${ yysqInfo.yyzxzt}" />
							</td>
						</tr>
						<tr style="height:10px">
						<th>咨询概要<br/>
								<font color="red"><B>(限500字)</B></font>
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
									"<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button id="buttonSave" onclick="saveYysqInfo();return false;">
										保 存
									</button>
									<button onclick="Close();return false;">
										关 闭
									</button>

								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		
		</html:form>
	</body>
</html>

