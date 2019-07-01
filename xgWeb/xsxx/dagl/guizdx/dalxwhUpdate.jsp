<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script type="text/javascript" src="js/check.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript">
		
		function save(){
			if($("mc").value==""){
				alertInfo("���Ʋ���Ϊ��!");
				return false;
			}
			
			if($("lx").value==""){
				alertInfo("���Ͳ���Ϊ��!");
				return false;
			}
			
			refreshForm("/xgxt/guizdxDagl.do?method=dalxwhUpdate&doType=add");
		}
		
		function update(){
			if($("mc").value==""){
				alertInfo("���Ʋ���Ϊ��!");
				return false;
			}
			
			if($("lx").value==""){
				alertInfo("���Ͳ���Ϊ��!");
				return false;
			}
			refreshForm("/xgxt/guizdxDagl.do?method=dalxwhUpdate&doType=update");
		}
		
	</script>
	</head>
	<body>
		
		<html:form action="/guizdxDagl" method="post">
			<input type="hidden" name ="xxdm" id = "xxdm" value="<bean:write name="xxdm" />"/>
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>
					
						<div class="tab">
							<table width="100%"  border="0" class="formlist">
							 <thead>
			    				<tr>
			        				<th colspan="6"><span>��Ŀ����</span></th>
			        			</tr>
			   				 </thead>
			   				
			   				 <tbody>
							<tr>
								<th style="width:16%">
									<font color="red">*</font>����
								</th>
								<td style="width:34%" colspan="3">
									<logic:empty name="rs">
										<html:text  property="mc" styleId="mc" maxlength="25" style="width:120px"/>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:hidden name="rs"  property="dm" styleId="dm"/>
										<html:text name="rs"  property="mc" styleId="mc" maxlength="25" style="width:120px"/>
									</logic:notEmpty>
								</td>
							</tr>
							<tr>
								<th style="width:16%">
									<font color="red">*</font>����
								</th>
								<td style="width:34%"  colspan="3">
									<logic:empty name="rs">
										<html:select property="lx" styleId="lx" style="width:120px">
											<html:option value=""></html:option>
											<html:options collection="lxList" property="en"
												 labelProperty="cn"/>
										</html:select>
									</logic:empty>
									<logic:notEmpty name="rs">
										<html:select name="rs" property="lx" styleId="lx" style="width:120px">
											<html:option value=""></html:option>
											<html:options collection="lxList" property="en"
												 labelProperty="cn"/>
										</html:select>
									</logic:notEmpty>
								</td>
							</tr>
							</tbody>
							<tfoot>
								<tr>
									 <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
										<div class="btn">
											<logic:equal name="act" value="add">
												<button type="button" onclick="save();return false;">�� ��</button>
											</logic:equal>
											<logic:equal name="act" value="update">
												<button type="button" onclick="update();return false;">�� ��</button>
											</logic:equal>
											<button type="button" onclick="Close();return false;">�� ��</button>
										</div>
									</td>
								</tr>
							</tfoot>
						</table>
						</div>
						<!-- ��ʾ��Ϣ -->
						<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
