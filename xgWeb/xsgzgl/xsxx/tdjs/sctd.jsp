<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/tdjs/js/tdgl.js"></script>
		<script type="text/javascript">

			jQuery(function(){

				var trs = jQuery("tbody tr");

				if (trs.length == 0){
					showAlert("未找到职工号所匹配的指导老师，请重新输入职工号。",{},{"clkFun":function(t){
						if (t == "ok"){
							document.location.href="tdjs.do?method=cjtd";
						}
					}});
				}
			})
			
		</script>
	</head>
	<body>
		<html:form action="/tdjs" method="post" styleId="cjtdForm">
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="document.location.href='tdjs.do?method=cjtd';" class="btn_fh">返回</a></li>
						<li><a href="javascript:void(0);" onclick="saveTd();" class="btn_ccg">保存</a></li>
					</ul>
				</div>
			</div>
			
			<div class="formbox">
				<!--标题start-->
				<table class="dateline" width="100%">
					<thead>
						<tr>
							<th width="10%">指导老师</th>
							<th width="20%">团队名称</th>
							<th width="15%">年级</th>
							<th width="30%">学院</th>
							<th width="25%">专业</th>
						</tr>
					</thead>
					<tbody>
						<logic:present name="tdinfoList">
							<logic:iterate id="t" name="tdinfoList" indexId="i">
								<tr>
									<td>
										${t.zdls}
										<html:hidden property="zdlsArray" value="${t.zgh}"/>
									</td>
									<td>
										<input type="text" maxlength="32" value="${t.zdls}团队" name="tdArray"/>
									</td>
									<td>
										<html:select property="njArray" value="${tdjsForm.nj}" styleId="nj_${i}">
											<html:option value=""></html:option>
											<html:options collection="njList" property="nj" labelProperty="nj"/>
										</html:select>
									</td>
									<td>
										<html:select property="xyArray" styleId="xy_${i}"  style="width:180px;"
													 onchange="initZyArray('xy_${i}','zy_${i}');" value="${t.bmdm}">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
										</html:select>
									</td>
									<td>
										<html:select property="zyArray" styleId="zy_${i}" style="width:180px;">
											<html:option value=""></html:option>
											<logic:notEqual value="" name="t" property="bmdm">
												<logic:iterate id="zy" name="zyList">
													<logic:equal value="${t.bmdm}" name="zy" property="bmdm">
														<html:option value="${zy.zydm}">${zy.zymc}</html:option>
													</logic:equal>
												</logic:iterate>
											</logic:notEqual>
											<logic:equal value="" name="t" property="bmdm">
												<html:options collection="zyList" property="zydm" labelProperty="zymc"/>
											</logic:equal>
										</html:select>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>

