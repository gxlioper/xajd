<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	<script type="text/javascript">
	jQuery(function(){
		jQuery("#shlccx").load("comm_spl.do?method=lccx&sqid=${model.sqid}&tt="+new Date().getTime());
		jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${model.splc}&shid=${model.shid}");
	});
	function saveSh(){
		var shzt = jQuery("#shjg").val();
		if (jQuery("#shzt").val() == "" || jQuery("#shyj").val() == ""){
			showAlert("�뽫��������д������");
			return false;
		}
		var url = "gzjlsh.do?method=gzjlDgsh&type=save";
		ajaxSubFormWithFun("GzjlshForm",url,function(data){
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
</head>
<body>
	<html:form action="/gzjlsh" method="post" styleId="GzjlshForm">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="zgh" styleId="zgh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<div style="height:510px;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">			
			<thead>
				<tr>
					<th colspan="4">
						<span>������Ϣ</span>
					</th>
				</tr>
			</thead>
			<%@ include file="/xsgzgl/szdw/gzjl/comm/viewTeacher.jsp" %>
			
			<thead>
				<tr>
					<th colspan="4">
						<span>������¼</span>
					</th>
				</tr>
		    </thead>
					<tbody>
						<tr>
							<th>����ʱ��</th>
							<td>
							${rs.gzsj}
							</td>
							<th>��¼ʱ��</th>
							<td>
							${rs.jlsj}
							</td>
						</tr>
						<tr>
							<th>�������</th>
							<td>
								${rs.gzlbmc}
							</td>
							<th>�����</th>
							<td>
							${rs.lbdm}
							</td>
						</tr>
						<logic:equal value="11842" name="xxdm">
							<tr>
								<th>������</th>
								<td>
								${rs.lksmc}
								</td>
							</tr>
						</logic:equal>
						<tr>						
							<th>����ժҪ</th>
							<td colspan="3">
								${rs.gzzy}
							</td>
						</tr>
							<tr>						
								<th>��ע</th>
								<td colspan="3">
									${rs.bz}
								</td>
							</tr>
					</tbody>
							<logic:present name="thdxList">
								<thead>
									<tr>
										<th colspan="4">
											<span≯������</span>
										</th>
									</tr>
						  		</thead>
							</logic:present>
							<logic:equal value="11842" name="xxdm">
			   <logic:present name="thdxList">
			   <tr>
			   	<td colspan="4">
			   		<div style="height:200px;overflow-y:auto;">
					<table width="100%" class="datelist" style="margin:2px auto;border:1px solid  hidden;">					
					<thead>
						<tr>
							<td width='15%'>ѧ��</td>
							<td width='10%'>����</td>
							<td width='10%'>�Ա�</td>
							<td width='20%'>ѧԺ</td>
							<td width='20%'>רҵ</td>
							<td width='25%'>�༶</td>
						</tr>
					</thead>
					<tbody id="tbody_dhryxx">
						<logic:iterate id="i" name="thdxList" indexId="index01">
							<tr>
								<td name="xhArr">${i.xh}</td>
								<td>${i.xm}</td>
								<td>${i.xb}</td>						
								<td>${i.xymc}</td>
								<td>${i.zymc}</td>
								<td >${i.bjmc}</td>						
							</tr>
						</logic:iterate>
					</tbody>
				</table>
				</div>
			   	</td>
			   </tr> 
							
			</logic:present>
			</logic:equal>
					
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
					<tr>
						<th >
							��˽��
						</th>
						<td id="shjgSpan">
							
						</td>
					</tr>
			</tr>
			<tr>
				<th width="20%">
					<font color="red">*&nbsp;</font> ������
					<br />
					<font color="red">(��200��)</font>
				</th>
				<td colspan="3">
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=gzjlgl&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
		</div>
		<div style="height: 20px"></div>
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
