<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script>
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
				showDialog("",700,400,"splcNew.do?method=splcYhsz&spgw="+spgwStr+"&gwlx="+gwlx);
			}else{
				alertError("请选择需要设置用户的岗位!");
				return false;
			}
			
			hiddenMessage(true,true);
		}
		</script>
		</head>
<div id="tsxxDiv">
			<input type="hidden" name="gwlx" id="gwlx" value="" />
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th>
									<span> 岗位设置 </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<div id="div_gwxx">
										${html}
									</div>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td>
									<div class="btn">
										<!-- 确定 -->
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
</html>