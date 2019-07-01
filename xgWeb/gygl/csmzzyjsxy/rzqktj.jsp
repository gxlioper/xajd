<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			function printRzqk(url){
				var nj = $('nj').value;
				var xydm = $('xy').value;
				var zydm = $('zy').value;
				var bjdm = $('bj').value;
				var userType = $('userType').value;
				var userDep = $('userDep').value;
				
				
				if ("" != nj ){
					url +="&nj="+nj;
				}
				
				if ("" != xydm){
					url +="&xydm="+xydm;
				}
				
				if ("" != zydm){
					url +="&zydm="+zydm;
				}
				
				if ("" != bjdm){
					url +="&bjdm="+bjdm;
				}
				
				if ("xy"==userType){
					url +="&xydm="+userDep;
				}
				
				window.open(url);
			}
		</script>
	</head>

	<body  onload="xyDisabled('xy');">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/csmz_gygl" method="post">
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userDep" name="userDep" value="${userDep }" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生入住情况统计</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="printRzqk('/xgxt/csmz_gygl.do?method=printRzqk')">
										统计
									</button>
									<button type="reset">
										重置
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								年级
							</th>
							<td>
								<html:select property="nj" styleId="nj"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
							</td>
							</tr>
						<tr>
							<th>
									<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
									<html:select property="xydm"  
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
							</td>
						</tr>
				</table>
			</div>
		</html:form>
	</body>
	</html>