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
				jQuery("#shjgSpan").load("comm_spl.do?method=shth&lcid=${rs.splcid}&shid=${shid}");
				proviceCiyyLocalMain({type:"view",id:"mddssx",flag:"wxxdz"});
			});
			function saveSh(){
		      var shzt = jQuery("#shjg").val();
		      if (jQuery("#shjg").val() == "" || jQuery("#shyj").val() == ""){
			    showAlert("�뽫��������д������");
			    return false;
		      }
		      var url = "qmlxsh.do?method=Dgsh&type=save";
		      ajaxSubFormWithFun("LxdjForm",url,function(data){
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
		<html:form action="/qmlxdj" method="post" styleId="LxdjForm">
		<html:hidden name="rs" property="sqid" styleId="sqid"/>
		<html:hidden name="rs" property="xh" styleId="xh"/>		
		<html:hidden name="rs" property="splcid" styleId="splcid"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:520px;margin-bottom:0px;" >
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
								<span>��У������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${rs.xn}
							</td>
							<th>ѧ��</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>�Ƿ���У</th>
							<td>
									${rs.sflxdm}
							</td>
							<th>��У����</th>
							<td>
									${rs.lxlxmc}
							</td>
						</tr>
						<tr>
							<th>�໤������</th>
							<td>
								${rs.jhrxm}
							</td>
							<th>�໤����ϵ��ʽ</th>
							<td>
								${rs.jhrlxfs}
							</td>
						</tr>
						<tr>
							<th>�Ƿ���໤����ϵ</th>
							<td>
								${rs.sflx}
							</td>
							<th>��Уʱ��</th>
							<td>
								${rs.lxsj}
							</td>
						</tr>
						<tr>
							<th>��У��ͨ����</th>
							<td>
								${rs.lxgj}
							</td>
							<th>��У����/����</th>
							<td>
								${rs.lxcchb}
							</td>
						</tr>
						<tr>
							<th>Ŀ�ĵ�</th>
							<td colspan="3">
								<html:hidden name="rs"  property="mddssx" styleId="mddssx"/>
							</td>
						</tr>
						<tr>
							<th>��Уʱ��</th>
							<td>
								${rs.fxsj}
							</td>
							<th>��У��ͨ����</th>
							<td>
								${rs.fxgj}
							</td>
						</tr>
						<tr>
							<th>��У����/����</th>
							<td>
								${rs.fxcchb}
							</td>
						</tr>
						<tr>
							<th>��ע</th>
							<td colspan="3">
								${rs.bz}
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
			<tr>
					<th width="20%">
						<font color="red">*&nbsp;</font> ������
						<br />
						<font color="red">(��200��)</font>
					</th>
					<td colspan="3">
						<jsp:include page="/xsgzgl/comm/shlc/cyyj.jsp?gnid=rcswlxdj&id=shyj" />
						<textarea id="shyj" rows="5" name="shyj" style="word-break:break-all;width:100%;margin-top: 5px" onblur="checkLen(this,200);"></textarea>
					</td>
				</tr>
				</table>
				
				</div>	
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