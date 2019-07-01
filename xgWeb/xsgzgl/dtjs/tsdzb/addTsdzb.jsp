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
								<span>��֧����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>��֧������
							</th>
							<td width="30%">
								<html:text property="dzbmc" styleId="dzbmc" maxlength="20" style="width:95%"></html:text>
							</td>
							</td>
							<th>
								<span><font color="red">*</font></span>������
							</th>
							<td>
								<html:text property="fzr" styleId="fzr" maxlength="10" style="width:95%"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>��ϵ��ʽ
							</th>
							<td width="30%">
								<html:text property="lxfs" styleId="lxfs" style="width:95%"></html:text>
							</td>
							</td>
							<th>������</th>
							<td>
								${tsdzbForm.cjrxm}
							</td>
						</tr>
						<tr>
							<th width="20%">
								����ʱ��
							</th>
							<td colspan="3">
								${tsdzbForm.cjsj}
							</td>
						</tr>
						<tr>
							<th>
								��Ͻ�༶</br>
								<input type="checkbox" id="qxCheck"/>ȫѡ
							</th>							
							<td colspan="3">
							<logic:equal value="xx" name="userStatus">						
								<div id="searchDistrict">
									�꼶��
									<html:select property="njdm" styleId="njdm" style="width:10%;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="njmc" />
									</html:select>
									ѧԺ��
									<html:select property="xydm" styleId="xydm" style="width:25%;"  onchange="changeXy();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									רҵ��
									<select name="zydm" id="zydm" style="width:25%;"></select>
									<div style="float: right;">
										<button type="button" class="btn_cx" id="search_go" onclick="searchBj()">
									                           �� ѯ
										</button>
									</div>
								</div>
							</logic:equal>
							<logic:equal value="xy" name="userStatus">						
								<div id="searchDistrict">
									�꼶��
									<html:select property="njdm" styleId="njdm" style="width:10%;">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj" labelProperty="njmc" />
									</html:select>
									ѧԺ��
									<html:select property="xydm" styleId="xydm" style="width:25%;"  onchange="changeXy();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm" labelProperty="xymc" />
									</html:select>
									רҵ��
									<select name="zydm" id="zydm" style="width:25%;"></select>
									<div style="float: right;">
										<button type="button" class="btn_cx" id="search_go" onclick="searchBj()">
									                         �� ѯ
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
								��ע
								<br/><font color="red">&lt;��500��&gt;</font>
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
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
									<button type="button" onclick="save('save');">
										����
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
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

