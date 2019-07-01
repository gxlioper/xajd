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
			 	//加载进度条
			 	loadBar(barkey,function(data){
			 		jQuery("#wzx").text(data.all-data.now);
			 		jQuery("#yzx").text(data.now);
			 		if(data.finish){
			 			showAlert("执行完成！",{},{"clkFun":function(){
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
			 	//加载进度条
			 	loadBar(barkey,function(data){
			 		jQuery("#wzx").text(data.all-data.now);
			 		jQuery("#yzx").text(data.now);
			 		if(data.finish){
			 			showAlert("执行完成！",{},{"clkFun":function(){
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
							未执行
						</th>
						<td align="left" id="wzx">
							等待执行
						</td>
					</tr>
					<tr>
						<th align="right" width="20%">
							已经执行
						</th>
						<td align="left" id="yzx">
							等待执行
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
								示例1（常用,实际进度）
							</button>
							<button id="buttonSave"
								onclick="auto();return false;"
								type="button">
								示例2（自动执行，伪进度）
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
