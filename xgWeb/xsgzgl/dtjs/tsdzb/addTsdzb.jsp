<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/dtjs/tsdzb/js/tsdzb.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			jQuery("#qxCheck").bind("click",function(){
				xz(this);
			});
		})
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/dtjs_tsdzb" method="post" styleId="tsdzbForm" onsubmit="return false;">
		<html:hidden property="cjr" value="${tsdzbForm.cjrxm}"/>
		<html:hidden property="cjrzgh" />
		<html:hidden property="cjrlx" />
		<html:hidden property="cjsj" styleId="cjsj"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>党支部信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>党支部名称
							</th>
							<td width="30%">
								<html:text property="dzbmc" styleId="dzbmc" maxlength="20" style="width:95%"></html:text>
							</td>
							</td>
							<th>
								<span><font color="red">*</font></span>负责人
							</th>
							<td>
								<html:text property="fzr" styleId="fzr" maxlength="10" style="width:95%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>联系方式
							</th>
							<td width="30%">
								<html:text property="lxfs" styleId="lxfs" style="width:95%"></html:text>
							</td>
							</td>
							<th>创建人</th>
							<td>
								${tsdzbForm.cjrxm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								创建时间
							</th>
							<td colspan="3">
								${tsdzbForm.cjsj}
							</td>
						</tr>
						<tr>
							<th>
								管辖班级</br>
								<input type="checkbox" id="qxCheck"/>全选
							</th>							
							<td colspan="3">
							<logic:equal value="xx" name="userStatus">						
								<div id="searchDistrict">
									年级：
									<html:select property="njdm" styleId="njdm" style="width:10%;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="njmc" />
									</html:select>
									学院：
									<html:select property="xydm" styleId="xydm" style="width:25%;"  onchange="changeXy();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									专业：
									<select name="zydm" id="zydm" style="width:25%;"></select>
									<div style="float: right;">
										<button type="button" class="btn_cx" id="search_go" onclick="searchBj()">
									                           查 询
										</button>
									</div>
								</div>
							</logic:equal>
							<logic:equal value="xy" name="userStatus">						
								<div id="searchDistrict">
									年级：
									<html:select property="njdm" styleId="njdm" style="width:10%;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="njmc" />
									</html:select>
									学院：
									<html:select property="xydm" styleId="xydm" style="width:25%;"  onchange="changeXy();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									专业：
									<select name="zydm" id="zydm" style="width:25%;"></select>
									<div style="float: right;">
										<button type="button" class="btn_cx" id="search_go" onclick="searchBj()">
									                         查 询
									    </button>
								    </div>
								</div>
							</logic:equal>
								<div id="displayArea">
									<logic:iterate id="t" name="bjList" indexId="index">
									<span>
									<font style="width:100px;">									
									<input type="checkbox" name="bjdms" value="${t.bjdm}" />${t.bjmc}
									<%if((index+1)%5==0){%> </br> <%}%>
									</font>
									</span>
									</logic:iterate>
								</div>
							</td>						
							</tr>
						<tr>
							<th>
								备注
								<br/><font color="red">&lt;限500字&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"为必填项
							</div>
							<div class="btn">
									<button type="button" onclick="save('save');">
										保存
									</button>
									<button type="button" onclick="iFClose();">
										关闭
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

