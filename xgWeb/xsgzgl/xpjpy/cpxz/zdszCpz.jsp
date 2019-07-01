<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">

		function aginCpz(){

			var cxCsh_bynjzy = document.getElementById("cxCsh_bynjzy");
			var cxCsh_bj = document.getElementById("cxCsh_bj");
            var cxCsh_bysy = document.getElementById("cxCsh_bysy");
			var cpz=null;
			
			if(cxCsh_bynjzy.checked){
				cpzcsh=cxCsh_bynjzy.value;
			}else if(cxCsh_bj.checked){
				cpzcsh=cxCsh_bj.value;
			} else {
                cpzcsh = cxCsh_bysy.value;
			}
			showConfirm("您确定要初始化参评组吗？<br/><br/> 注：综测排名将重新计算。",{"okFun":function(){
				var url = "xpj_cpxz.do?method=zdszCpz&type=cxjs&cpzcsh="+cpzcsh;
				jQuery.post(url,{},function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}})
					refershParent();
				},"json");
			}});
		}
			
		</script>
	</head>

	<body>
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span>重新初始化参评组（<font color="blue">针对参评组的班级</font>）</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>
						<input type="radio" checked = "true" name="cxCsh" id="cxCsh_bysy" value="3" />以书院为单位划分参评组
					</td>
				</tr>
				<tr>
					<td>
						<input type="radio"  name="cxCsh" id="cxCsh_bynjzy" value="1" />以年级+专业为单位划分参评组
					</td>
				</tr>
				<tr>
					<td>
						<input type="radio"  name="cxCsh" id="cxCsh_bj" value="2" />以班级为单位划分参评组
					</td>
				</tr>
			</tbody>
			<tfoot>
				<td colspan="2">
					<div class="btn">
						<button type="button" onclick="aginCpz();">
							保 存
						</button>
						<button type="button"
							onclick="closeDialog();">
							关 闭
						</button>
					</div>
				</td>
				</tr>
			</tfoot>
		</table>
	</body>
</html>
