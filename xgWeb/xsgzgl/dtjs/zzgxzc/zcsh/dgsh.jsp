<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/qmlxdj/lxsh/js/lxsh.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/provicecitylocal.js"></script>
		<link rel="stylesheet" href="js/provicecitylocal.css" type="text/css"/>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#shlccx").load(
					"comm_spl.do?method=lccx&sqid=${rs.sqid}&tt="
							+ new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splcid}&shid=${shid}",function(){
					jQuery("#shjg").change(function(){
						var tempvalue = this.value;
						if(tempvalue == "2" || tempvalue == "3"){
							jQuery("#jsxbhtr").hide();
						}else{
							jQuery("#jsxbhtr").show();
						}
					})
					});
				
			});
			function saveSh(){
		      var shzt = jQuery("#shjg").val();
		      var ids = "xh"+"-"+"szdzb"+"-"+"sfsn"+"-"+"jsdzz"+"-"+"sqdw"+"-"+"dfjzrq"+"-"+"shjg"+"-"+"shyj";
	          if(checkNotNull(ids) == false){
		        showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
		        return false;
	          }
		      
		      if(${isJsxbhShow}&&shzt == '1'){
		      	if(jQuery("#jsxbh").val()== ""){
		      		 showAlert("����д�����ű�ţ�");
			         return false;
		      	}
		      }
		      var url = "dzzgxsh.do?method=Dgsh&type=save";
		      ajaxSubFormWithFun("ZcshForm",url,function(data){
			  if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
						if (parent.window){
							refershParent();
						}
					}});
	    	  }else{
	    		 showAlert(data["message"]);
	    		}
			});
	      }
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/dzzgxsh" method="post" styleId="ZcshForm">
		<html:hidden property="sqid" styleId="sqid"/>
		<html:hidden property="xh" styleId="xh"/>
		<html:hidden  property="splcid" styleId="splcid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>�Ǽ���Ϣ</span>&nbsp;
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>���ڵ�֧��</th>
							<td>
								<html:select property="szdzb" styleId="szdzb" style="width:90%">
									<html:options collection="dzbList" property="dzbdm" labelProperty="dzbmc"/>
								</html:select>							
							</td>
							<th><font color="red">*</font>�Ƿ�ʡ��</th>
							<td>
								 <html:select property="sfsn" styleId="sfsn" style="width:90%">
									<html:option value="ʡ��">ʡ��</html:option>
									<html:option value="ʡ��">ʡ��</html:option>
								 </html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>���ձ�����֯��ϵ�ĵ���֯</th>
							<td colspan="3">
								<html:text property="jsdzz" styleId="jsdzz" style="width:90%" maxlength="50"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>������֯��ϵ��ȥ��λ</th>
							<td colspan="3">
								<html:text property="sqdw" styleId="sqdw"  maxlength="50" style="width:90%"/>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>���ѽ�������</th>
							<td colspan="3">
								<html:text property="dfjzrq" styleId="dfjzrq" onclick="return showCalendar('dfjzrq','y-mm-dd',true);" style="width:180px"  />
							</td>
							<%-- <th><font color="red">*</font>�Ƿ���Ҫ���߻���֤��</th>
							<td >
								<html:radio property="sfkjhyzm" value="��" styleId="yes"/><label for="yes">��</label>
								<html:radio property="sfkjhyzm" value="��" styleId="no"/><label for="no">��</label>
							</td> --%>
						</tr>
					</tbody>
					<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
					       </tr>
				     </thead>
					<tbody>
						<tr>
								<td colspan="4" id="shlccx">

								</td>
						</tr>

					</tbody>
						<thead>
				<tr>
					<th colspan="4">
						<span>�����Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
					<tr>
						<th >
							��˽��
						</th>
						<td id="shjgSpan" colspan="3">
							
						</td>
					</tr>
			</tr>
			<logic:equal value="true" name="isJsxbhShow">
				<tr id="jsxbhtr">
					        <th>
								<font color="red">*</font>�����ű��
							</th>
							<td  colspan="3">
								<html:text styleId="jsxbh" property="jsxbh" />
							</td>
				</tr>
			</logic:equal>
			<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zzgxzc&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
				</table>
				
				</div>	
				<div style="height: 35px"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="����"  onclick="saveSh();return false;">
										�� ��
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
			<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
	</body>
	
</html>