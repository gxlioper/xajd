<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		//http://localhost:8008/xgxt/browser.do?method=demoList
			var barkey="demo";
			function save(){
				lock();
			 	jQuery.ajax({
					url:"browser.do?method=barDemo",
					data:{barkey:barkey},
					type:"post",
					dataType:"json",
					success:function(data){
					}
				});	
			 	//���ؽ�����
			 	loadBar(barkey,function(data){
			 		jQuery("#wzx").text(data.all-data.now);
			 		jQuery("#yzx").text(data.now);
			 		if(data.finish){
			 			showAlert("ִ����ɣ�",{},{"clkFun":function(){
			 				clearInterval(myBar);
				 			unlock();
			 			}});
			 		}
			 		return true;
			 	});
			}
			barkey="autoKey";
			function auto(){
				lock();
			 	jQuery.ajax({
					url:"browser.do?method=autoBarDemo",
					data:{barkey:barkey},
					type:"post",
					dataType:"json",
					success:function(data){
					}
				});	
			 	//���ؽ�����
			 	loadBar(barkey,function(data){
			 		jQuery("#wzx").text(data.all-data.now);
			 		jQuery("#yzx").text(data.now);
			 		if(data.finish){
			 			showAlert("ִ����ɣ�",{},{"clkFun":function(){
			 				clearInterval(myBar);
			 				unlock();
			 			}});
			 		}
			 		return true;
			 	});
			}
		</script>
	</head>
	<body>
		<div>
			<html:form action="/fbglbbgl.do?method=add&type=query">
				<table width="100%" border="0" class="formlist">
					<tr>
						<th align="right" width="20%">
							δִ��
						</th>
						<td align="left" id="wzx">
							�ȴ�ִ��
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							�Ѿ�ִ��
						</th>
						<td align="left" id="yzx">
							�ȴ�ִ��
						</td>
					</tr>
				</table>
			</html:form>
			<%@include file="/xsgzgl/comm/browser/progressBar.jsp"%>
		</div>
		<table width="100%" border="0" class="formlist"
			style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="btn">
							<button id="buttonSave"
								onclick="save();return false;"
								type="button">
								ʾ��1������,ʵ�ʽ��ȣ�
							</button>
							<button id="buttonSave"
								onclick="auto();return false;"
								type="button">
								ʾ��2���Զ�ִ�У�α���ȣ�
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
