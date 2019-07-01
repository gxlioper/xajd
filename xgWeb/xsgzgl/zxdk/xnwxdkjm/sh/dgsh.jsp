<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/fileUpload/f_q.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${zsjgxx.sqid}&tt="+new Date().getTime());
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${zsjgxx.splc}&shid=${zsjgxx.shid}");
				var jmbl = jQuery("#jmbl").text();
			    jmbl = parseInt(jmbl.replace("%",""))/100;
				var yjjmje = (parseInt(jQuery("#zje").text())*jmbl).toFixed(2);
				jQuery("#yjjmje").text(yjjmje);
			});

			/*�������*/
			function saveSh(){
				var shzt = jQuery("#shjg").val();
				if('1' == shzt){
					if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == "" || jQuery("#zd3").val() == ""){
						showAlert("�뽫��������д������");
						return false;
					}
				}else{
					if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
						showAlert("�뽫��������д������");
						return false;
					}
				}
				
				var url = "xnwxdkjm_sh.do?method=xnwxdkDgsh&type=save";
				ajaxSubFormWithFun("XnwxdkjmshModel",url,function(data){
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

			function onchangeShjg(){
				var shzt = jQuery("#shjg").val();
				if('1' == shzt){
					jQuery("#tyjmlTh").show();
					jQuery("#tyjmlTd").show();
				}else{
					jQuery("#tyjmlTh").hide();
					jQuery("#tyjmlTd").hide();
					jQuery("#zd3").val("");
				}
			}
		</script>
</head>
<body>
	<html:form action="/xnwxdkjm_sh" method="post" styleId="XnwxdkjmshModel">
		
		<html:hidden  property="sqid" styleId="sqid"/>
		<html:hidden  property="xh" styleId="xh"/>		
		<html:hidden name="zsjgxx" property="splc" styleId="splc"/>
		<html:hidden name="zsjgxx" property="sqsj" styleId="sqsj"/>
			
		<html:hidden name="zsjgxx" property="shid" styleId="shid"/>
	     <%-- 
		<html:hidden  property="cjbz" styleId="cjbz"/>
	        --%>
		<html:hidden name="zsjgxx"  property="xn" styleId="xn"/>
  		<html:hidden name="zsjgxx"  property="xq" styleId="xq"/>
		<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
		   <table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>ѧ����Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
			<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${zsjgxx.xn}
							</td>
							<th>ѧ��</th>
							<td>
							    ${xqmc}
							</td>
						</tr>
						<tr>
							<th>����������</th>
							<td id = "jmbl">
								${zsjgxx.jmbl}
							</td>
							<th>Ԥ�Ƽ�����</th>
							<td>
								<label id="zje" style="display:none">${zje}</label>
								<label id="yjjmje"></label>
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
							    <table  border="0" cellspacing="0" cellpadding="0">
								    <logic:iterate id="jm"  name="jmyjlist">
								      <tr>
								        <td>
								    	    ${jm.rownum}
								        </td>
								        <td>
								        	${jm.jmyj}
								        </td>
								       </tr>
									</logic:iterate>	
									</table>
							</td>
						</tr>
						<tr>
							<th>��������</th>
							<td colspan="3">
								 ${zsjgxx.sqly}
							</td>
						</tr>
						<tr>
							<th align="right" width="10%">
								������Ϣ
							</th>
							<td colspan="3">
								<div id="commonfileupload-list-0" style="padding: 5px;"></div>
								<script type="text/javascript">
									//���ø��� 
									jQuery(function(){
										var gid = "${zsjgxx.filepath}";
										jQuery.MultiUploader_q({
											gid : gid,
											targetEl : 'commonfileupload-list-0'
										});
									});
								</script>
							</td>
						</tr>					
					</tbody>
			<thead>
				<tr>
					<th colspan="4">
						<span>������Ϣ</span>
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
					<th>
						<font color="red">*</font>��˽��
					</th>
					<td id="shjgSpan" onchange="onchangeShjg()">
						
					</td>
					<th id="tyjmlTh">
						<font color="red">*</font>ͬ�������
					</th>
					<td id="tyjmlTd">
						<html:select property="zd3" styleId="zd3">
							<html:option value=""></html:option>
							  <logic:iterate id="jm"  name="jmllist">
							  	<html:option  value="${jm.jmbl}">${jm.jmbl}</html:option>
							  </logic:iterate>
							<%--<html:options property="jmbl" name="jmllist" labelProperty="jmbl"/>--%>
						</html:select>
						<html:hidden property="zd1" styleId="zd1" value="ͬ�������"/>
					</td>
				</tr>
				
				<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=xnwxdkjm&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
			</tbody>	
		  </table>
		</div>
		<div style="height: 30px"></div>
		<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">	
			<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" name="����"  onclick="saveSh();return false;">
									�� ��
								</button>
								<button type="button" name="�ر�" id="buttonClose" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
		</table>
		<!-- ��ʾ��Ϣ -->
		<%@ include file="/comm/other/tsxxNew.jsp"%>
		</html:form>
</body>
</html>
