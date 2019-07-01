<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript">
		function loadCjInfo(){
			
			var cjxnArr=document.getElementsByName("cjxnArr");	
			var cjxqArr=document.getElementsByName("cjxqArr");	
			var cjkcmcArr=document.getElementsByName("cjkcmcArr");	
			var cjcjArr=document.getElementsByName("cjcjArr");	
			if(cjxnArr!=null && cjxnArr.length>0){
				for(i=0;i<cjxnArr.length;i++){
					var html="<tr><td><div align='center'>"+cjkcmcArr[i].value+"</div></td><td colspan='3'><div align='center'>";
					html+=cjcjArr[i].value+"</div></td></tr>";
					jQuery("#xnxq_"+cjxnArr[i].value+"_"+cjxqArr[i].value).append(html);
	
				}
			}else{
				jQuery("#xnxq_notCjInfo").append("<tr height='280px'><td colspan='4' height='280px' align='center'>��ѧ��û�гɼ���Ϣ��</td></tr>");
				
			}
		}
	</script>
	</head>
	<body onload="loadCjInfo()">

		<html:form action="/guizdxDagl" method="post">
			<input type="hidden" name="tableName" id="tableName"
				value="xg_view_xsxx_guizdx_byzd" />
			<input type="hidden" name="url" id="url"
				value="/xgxt/guizdxDagl.do?method=byzdwhUpdate" />
			<input type="hidden" id="lx" name="lx" value="��ҵ��ת��" />
			<input type="hidden" id="doType" name="doType" value="${doType}" />
			<input type="hidden" name="xxdm" id="xxdm"
				value="<bean:write name="xxdm" />" />
			<logic:iterate name="cjList" id="cj" indexId="index">
				<input type="hidden" name="cjxnArr" id="xn_${index}"
					value="${cj.xn}" />
				<input type="hidden" name="cjxqArr" id="xq_${index}"
					value="${cj.xq}" />
				<input type="hidden" name="cjkcmcArr" id="kcmc_${index}"
					value="${cj.kcmc}" />
				<input type="hidden" name="cjcjArr" id="cj_${index}"
					value="${cj.cj}" />
			</logic:iterate>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>

			<div class="tab" style="overflow:hidden;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="6">
								<span>��Ŀ����</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th style="width:16%">
								ѧ��
							</th>
							<td style="width:34%">
								${rs.xh}
							</td>

							<th style="width:16%">
								����
							</th>
							<td style="width:34%">
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�Ա�
							</th>
							<td style="width:34%">
								${rs.xb}
							</td>

							<th style="width:16%">
								�꼶
							</th>
							<td style="width:34%">
								${rs.nj}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								<bean:message key="lable.xb" />
							</th>
							<td style="width:34%">
								${rs.xymc}
							</td>

							<th style="width:16%">
								רҵ
							</th>
							<td style="width:34%">
								${rs.zymc}
							</td>
						</tr>
						<tr>
							<th style="width:16%">
								�༶
							</th>
							<td style="width:34%">
								${rs.bjmc}
							</td>

							<th style="width:16%">
							</th>
							<td style="width:34%">

							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="xnxqcj" style="overflow-y:auto; height:300px;width:99%;overflow-x:hidden">
				<table width="50%" border="0" class="formlist" >
					<logic:iterate name="xnxqList" id="xnxq" indexId="index">
						<thead>
							<tr>
								<th colspan="4">
									<span>${xnxq.xn}ѧ��&nbsp;&nbsp;${xnxq.xqmc}ѧ��</span>
								</th>
							</tr>
						</thead>
						<tbody id="xnxq_${xnxq.xn}_${xnxq.xq}">
							<tr>
								<th align="center">
									<div align="center">
										�γ�����
									</div>
								</th>
								<th colspan="3">
									<div align="center">
										�γ̳ɼ�
									</div>
								</th>
							</tr>
						</tbody>
					</logic:iterate>
					<tbody id="xnxq_notCjInfo">
							
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">

									<button onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			<logic:present name="result">
				<script>
							alert('${message}');
							if (window.dialogArguments) {
								window.close();
								window.dialogArguments.document.getElementById('search_go').click();
							}
						</script>
			</logic:present>
		</html:form>
	</body>
</html>
