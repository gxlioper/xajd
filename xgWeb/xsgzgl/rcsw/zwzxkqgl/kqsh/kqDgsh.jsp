<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsh.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		function qqxszj(html){
			jQuery("#tbody_qqryxx").append(html);	
			}

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
			var url = "zwzxKqsh.do?method=kqDgsh&type=save";
			ajaxSubFormWithFun("ZwzxKqshForm",url,function(data){
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
	<body style="width: 100%">
		<html:form action="/zwzxKqsh" method="post" styleId="ZwzxKqshForm" onsubmit="return false;">
		<html:hidden name="model" property="sqid" styleId="sqid"/>
		<html:hidden name="model" property="xh" styleId="xh"/>		
		<html:hidden name="model" property="splc" styleId="splc"/>
		<html:hidden name="model" property="sqsj" styleId="sqsj"/>
		<html:hidden name="model" property="shid" styleId="shid"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><font id="gnmkmc_prompt_span"></font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">
								<span id="ccrq_span"></span>
							</th>
							<td width="30%">
								${rs.ccrq}
							</td>
						</tr>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${rs.xqmc}
							</td>
							<th width="20%">
								<span id="cclx_span"></span>
							</th>
							<td width="30%">
								${rs.cclxmc}
							</td>
						</tr>
							<tr>
								<th width="20%">
									�༶
								</th>
								<td width="30%">
									${rs.bjmc}
								</td>
								<th width="20%">
									Ӧ������
								</th>
								<td width="30%">
									${rs.ydrs}
								</td>
							</tr>
							<tr>
								<th width="20%">
									ʵ������
								</th>
								<td width="30%">
									${rs.sdrs}
								</td>
								<logic:equal name="xxdm" value="12970">
								<th width="20%">
									δ������
								</th>
								</logic:equal>
								<logic:notEqual name="xxdm" value="12970">
								<th width="20%">
									ȱ������
								</th>
								</logic:notEqual>
								<td width="30%">
									${rs.qqrs}
								</td>
							</tr>
						<tr>
							<th width="20%" id="jlf_th">
								���ɷ�
							</th>
							<td width="30%" id="jlf_td">
								${rs.jlf}
							</td>
							<th width="20%">
								<span id="jlr_span"></span>
							</th>
							<td width="30%" id="jlr_td">
								${rs.jlrxm }
							</td>
						</tr>
						<tr>
						    <th>
								��ע
							</th>
							<td colspan="3">
								${rs.bz}
							</td>
						</tr>
						<logic:equal value="11647" name="xxdm">
						<tr>
							<th>
								��ȱ�����Υ������
								<br />
								<div align="center">
									(��:�Է�,˯����...)
								</div>
							</th>
							<td align="left">
								${rs.wjrs}
							</td>
							</tr>
							</logic:equal>
					</tbody>
					<thead>
						<tr>
						<logic:equal name="xxdm" value="12970">
							<th colspan="4">
								<span>δ��ѧ����Ϣ</span>
							</th>
						</logic:equal>
						<logic:notEqual name="xxdm" value="12970">
							<th colspan="4">
								<span>ȱ��ѧ����Ϣ</span>
							</th>
						</logic:notEqual>
						</tr>
					</thead>
				 </table>
				 </div>
				 <div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					
					<thead>
						<tr>
							<td width='15%'>ѧ��</td>
							<td width='15%'>����</td>
								<logic:notEqual name="xxdm" value="12970">
									<td width='15%'><font color="red">*</font>ȱ������</td>
									<td width='15%'>���ν���</td>
									<td width='35%'>ȱ�ڱ�ע</td>
								</logic:notEqual>
							<logic:equal name="xxdm" value="12970">
								<td width='35%'>δ��ԭ��</td>
							</logic:equal>
						</tr>
					</thead>
					<tbody id="tbody_qqryxx">
						<logic:iterate id="i" name="qqxsList" indexId="index01">
						<tr>
						<td name="xhArr">${i.xh}</td>
						<td>${i.xm}</td>
							<logic:notEqual value="12970" name="xxdm">
								<td>${i.qqlxmc}</td>
								<td>${i.kkjs}</td>
							</logic:notEqual>
						<td>${i.ylzd1}</td>
						</tr>
						</logic:iterate>
						</tbody>
					</table>
					</div>
				<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
					<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=zwzxkq&id=shyj" />
					<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
				</td>
			</tr>
			</tbody>
			</table>
			</div>
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

