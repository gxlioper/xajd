<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script type="text/javascript">
		function saveZgsq(url){
			ajaxSubFormWithFun("zgsqForm",url,function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					document.location.href=document.location;
				}});
			});
		}
	</script>
  </head>
  
  <body>
  		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/jjgl_jjzg" method="post" styleId="zgsqForm">
			<input type="hidden" value="${jjzgForm.sqid }" name="id"/>
			<html:hidden property="sqid" />
			<html:hidden property="xh" value="${userName }"/>
			<html:hidden property="shzt"/>
			<html:hidden property="splcid" value="${cssz.xszgsqsplcid }" />
		
			<div class='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="16%">ѧ��</th>
							<td width="34%">${jbxx.xh }</td>
							<th width="16%">����</th>
							<td width="34%">${jbxx.xm }</td>
					    </tr>
					    <tr>
					    	<th>�Ա�</th>
					    	<td>${jbxx.xb }</td>
					    	<th>�꼶</th>
					    	<td>${jbxx.nj }</td>
					    </tr>
					    <tr>
					    	<th>ѧԺ</th>
					    	<td>${jbxx.xymc }</td>
					    	<th>�༶</th>
					    	<td>${jbxx.bjmc }</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ҽ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
					    	<th><font color="red">*</font>�ó���Ŀ</th>
					    	<td colspan="3">
					    		<html:select property="jjkma" >
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxkdm" labelProperty="jjxkmc"/>
					    		</html:select>
					    		<html:select property="jjkmb">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxkdm" labelProperty="jjxkmc"/>
					    		</html:select>
					    		<html:select property="jjkmc">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjxkList" property="jjxkdm" labelProperty="jjxkmc"/>
					    		</html:select>
					    		���밴���ȼ�ѡ��
					    	</td>
					    </tr>
						<tr>
					    	<th><font color="red">*</font>����꼶</th>
					    	<td>
					    		<html:select property="jjnjdm">
					    			<html:option value=""></html:option>
					    			<html:options collection="jjnjList" property="jjnjdm" labelProperty="jjnjmc"/>
					    		</html:select>
					    	</td>
					    	<th><font color="red">*</font>��ϵ�绰</th>
					    	<td>
					    		<html:text property="lxdh" maxlength="20"></html:text>
					    	</td>
					    </tr>
					    <tr>
					    	<th>��ע</th>
					    	<td colspan="3">
					    		<html:textarea property="bz" style="width:85%;" rows="5"></html:textarea>
					    	</td>
					    </tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<logic:empty name="jjzgForm" property="sqid">
										<button type="button" onclick="saveZgsq('jjgl_jjzg.do?method=save');">
											��    ��
										</button>
										<button type="button" onclick="saveZgsq('jjgl_jjzg.do?method=saveAndSubmit');">
											�ύ����
										</button>
									</logic:empty>
									<logic:notEmpty name="jjzgForm" property="sqid">
										<button type="button" onclick="saveZgsq('jjgl_jjzg.do?method=update');">
											��    ��
										</button>
										<button type="button" onclick="saveZgsq('jjgl_jjzg.do?method=submit');">
											�ύ����
										</button>
									</logic:notEmpty>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
  </body>
</html>
