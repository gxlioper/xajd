<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		var tjszDialog ;
		/*条件设置*/
		function tjsz(xmdm,xmmc) {
			var url = 'dkbc_tjsz.do?method=tjszCx';
			url += "&xmdm=" + xmdm;
			url += "&xmmc=" + xmmc;
			var title = "条件设置";
			tjszDialog = showDialog(title, 750, 520, url,{close:function(){
				window.location = window.location + '?_t='+new Date().getTime();
			}});
		}	
		</script>
	</head>
  	<body >
	<html:form action="/dkbc_tjsz" method="post" styleId="tjszForm">
	<input type="hidden" name="xmdm" id="xmdm" value="${xmdm}"/>
	<input type="hidden" name="xmmc" id="xmmc" value="${xmmc}"/>
	<input type="hidden" name="szzt" id="szzt" value="${szzt}"/>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="2"><span>参数设置</span></th>
					</tr>
				</thead>
				<tbody>
					<logic:equal name="szzt" value="0">
						<tr height = "40px">
							<th width="40%">条件设置</th>
							<td>
								<li><a href="javascript:void(0);" onclick="tjsz('${xmdm}','${xmmc}');" class="btn_sz"><font color="red">未设置</font></a></li>	
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="szzt" value="1">
						<tr height = "40px">
							<th width="40%">条件设置</th>
							<td>
								<li><a href="javascript:void(0);" onclick="tjsz('${xmdm}','${xmmc}');" class="btn_sz"><font color="#004400">已设置</font></a></li>	
							</td>
						</tr>
					</logic:equal>	
				</tbody>
				<tfoot>
			      <tr height = "25px">
			      	<td colspan="4">
			          <div class="btn">
						
			          </div>
			      	</td>
			      </tr>
			    </tfoot>
			</table>
			</div>
		</html:form>
	</body>
</html>
