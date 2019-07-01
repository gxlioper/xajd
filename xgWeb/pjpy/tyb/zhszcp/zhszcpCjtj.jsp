<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="js/xgutil.js"></script>
<script>
	function printNtzy(type){
		var bjdm = val("bj");
		var xydm = val('xy');
		if(bjdm == ""){
			alert("请选择班级！");
			return false;
		}
		if(xydm == ""){
			alert("请选择"+jQuery("#xbmc").val()+"!");
			return false;
		}
		var url = "pjpyTybZhszcp.do?method=printZhcpcjtj&type="+type;
		wjcfDataExport(url);
	}
</script>
</head>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyTybZhszcp.do" method="post">
		<input type="hidden" name="zyV" id="zyV" />
		<input type="hidden" name="bjV" id="bjV" />
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		<div class="toolbox">
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
						 <li> <a href="#" onclick="printNtzy(1)" class="btn_dy">成绩统计表</a> </li>					  
					  	 <li> <a href="#" onclick="printNtzy(2)" class="btn_dy">综合测评统计表</a> </li>	
					</ul>					
				  </div>
				  <div class="searchtab">
					<table width="100%" border="0">				      
					  <tbody>
					  <logic:equal value="nd" name="pjzq">
						<tr>
						<th>年级</th>
						<td>
							<html:select property="queryequals_nj"
								onchange="initZyList();initBjList();">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj"
									labelProperty="nj" />
							</html:select>
						</td>
						<th>年度</th>
						<td>
							<html:select property="queryequals_nd" styleId="nd">
								<html:options collection="ndList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
						<th></th>
						<td>		
						</td>
					  </tr>
					  </logic:equal>
					  <logic:equal value="xn" name="pjzq">
						 <tr>
							<th>年级</th>
							<td>
								<html:select property="queryequals_nj"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>学年</th>
							<td>
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th></th>
							<td>		
							</td>
						  </tr>
					  </logic:equal>
					  <logic:equal value="xq" name="pjzq">
						<tr>
							<th>年级</th>
							<td>
								<html:select property="queryequals_nj"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>学年</th>
							<td>
								<html:select property="queryequals_xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							<th>学期</th>
							<td>
								<html:select property="queryequals_xq" styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>		
							</td>
					  </tr>
					  </logic:equal>
					  
					  <tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<html:select property="queryequals_xydm" styleId="xy"
								onchange="initZyList();initBjList()" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="xyList" property="xydm"
									labelProperty="xymc" />
							</html:select>
						</td>
						<th>专业</th>
						<td>	
							<html:select property="queryequals_zydm" styleId="zy" style="width:160px"
								onchange="initBjList()">
								<html:option value=""></html:option>
								<html:options collection="zyList" property="zydm"
									labelProperty="zymc" />
							</html:select>	
						</td>
						<th>班级</th>
						<td>
							<html:select property="queryequals_bjdm" styleId="bj" style="width:160px">
								<html:option value=""></html:option>
								<html:options collection="bjList" property="bjdm"
									labelProperty="bjmc" />
							</html:select>
						</td>
					  </tr>	
					  </tbody>
					</table>
				</div>
				</div>
	</html:form>	
</body>

