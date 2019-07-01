<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script language="javascript">	     
			function Save(){
	     			saveFscxInfo("ok");	
			}
			
			function saveFscxInfo(tag){
				if(tag=="ok"){
					jQuery.ajaxSetup({async:false});	
					// 得到JSON对象
			        var parameter ={};
					parameter["xn"]=escape(jQuery("#xn").val());
					parameter["zgh"]=escape(jQuery("#zgh").val());
					parameter["khdj"]=escape(jQuery("#khdj").val());
					parameter["jtdj"]=escape(jQuery("#jtdj").val());
					parameter["bz"]=escape(jQuery("#bz").val());
					
					var url = "bjlh_fdykh.do?method=fdycjTjWhBc";
		          	
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
	
		<html:form action="/bjlh_fdykh" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xn" id="xn" value="${rs.xn }" />
			<input type="hidden" name="zgh" id="zgh" value="${rs.zgh }" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:100%;height:400px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>辅导员考核成绩查看</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								学年
							</th>
							
							<td width="84%" colspan="3" >
								${rs.xn}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								职工号
							</th>
							
							<td width="84%" colspan="3" >
								${rs.zgh}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								姓名
							</th>
							
							<td width="84%" colspan="3" >
								${rs.xm}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								部门名称
							</th>
							
							<td width="84%" colspan="3" >
								${rs.bmmc}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								考核等级
							</th>
							
							<td width="84%" colspan="3" >
								${rs.khdj}
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								津贴等级
							</th>
							
							<td width="84%" colspan="3" >
								${rs.jtdj}
							</td>
						</tr>
						<tr style="height:90px">
							<th width="16%">
								备注
							</th>
							
							<td width="84%" colspan="3" style="word-break:break-all;width:95%" rowspan='5'>
								${rs.bz}
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="Close();return false;">
										关 闭
									</button>
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

