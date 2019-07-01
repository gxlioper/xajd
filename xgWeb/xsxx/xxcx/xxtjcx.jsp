<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- 头文件 -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<link rel="stylesheet" href="js/dtree/dtree.css" type="text/css" media="all" />
	<script type='text/javascript' src="js/dtree/dtree.js"></script>
	<script type='text/javascript' src="js/xgutil.js"></script>
	<script type='text/javascript' src="js/xsxx/xxtjcx.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/xsxxtjDWR.js'></script>
</head>
	<body onload="">
		<html:form action="/xsxxcx" method="post">
			<input type="hidden" name="xxmc" id="xxmc" value="${xxmc}"/>
			<input type="hidden" name="userName" id="userName" value="${userName}"/>
			<input type="hidden" name="userType" id="userType" value="${userType}"/>
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy}"/>
			<input type="hidden" name="fdyQx" id="fdyQx" value="${fdyQx}"/>
			<input type="hidden" name="xydm" id="xydm" value="${xydm}"/>

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="formbox">
				<div class="con_overlfow">			
					<p>
						年级
						<html:select property="nj" styleId="nj" onchange="refreshForm('xsxxcx.do?method=xxtjcx')">
							<html:option value="">--请选择--</html:option>
							<html:options collection="njList" property="nj" labelProperty="nj"/>
						</html:select>
					</p>
				<table>
					<tr>
						<td>
				<script type="text/javascript">		
					<!--
					dwr.engine.setAsync(false);
					d = new dTree('d');		
					d.add(0,-1,val('xxmc'));				
					GetListData.getXyListByDm(val('xydm'),val('userName'),val('fdyQx'),function (data){
						for(var i=1; i<data.length; i++){				
							if(data[i] != null && data[i].dm != ''){
								d.add("xy"+data[i].dm,0,data[i].mc,'#','onClickAct','');
								GetListData.getZyList(data[i].dm,val('nj'),val('userName'),val('fdyQx'),val('isBzr'),function(zyData){
									for(var m =1; m<zyData.length; m++){
										d.add("zy"+zyData[m].dm,"xy"+data[i].dm,zyData[m].mc,'#','onClickAct',true);
									}
								});
							}
						}
					});
					document.write(d);
					dwr.engine.setAsync(true);
					//-->
					d.closeAll();
				</script>
				</td>
				<td>
					<p id="xxP">
						
					</p>
				</td>
				</tr>
				</table>
	
				</div>
			</div>			
		</html:form>
	</body>
</html>