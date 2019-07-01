<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<style type="text/css">
             .note{
                position:absolute;line-height:20px;padding:3px 5px;top:20px;
             }
		</style>
		<script type='text/javascript'>
		function saveFdgl(type) {
			var flag = false;
			var ids = "fdkm-xdrs-fdsj";
			var kfxydm = "";
			if(check(ids) == false){
				showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
				return false;
			}

			jQuery("input:checkbox[name=xydm]:checked").each(function(index){
				if(flag){
					kfxydm += ",";
				}else{
					flag = true;
				}
				kfxydm += jQuery(this).val();
			});
			jQuery("#kfxydm").val(kfxydm);
			if(kfxydm==""){
				showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
				return false;
			}
			var url = "zzyrxmglfdgl.do?method=addFdgl&type="+type;
			ajaxSubFormWithFun("fdglForm", url, function(data) {
				if (data["message"] == "����ɹ���") {
					showAlert(data["message"], {}, {
						"clkFun" : function() {
							if (parent.window) {
								refershParent();
							}
						}
					});
				} else {
					showAlert(data["message"]);
				}
			});
			
		}
		function check(ids){
			var id=ids.split("-");
			for(var i=0;i<id.length;i++){
				var lddm=jQuery("#"+id[i]).val();
				if(lddm==null||""==lddm){
					return false;
				}
			}
			return true;
		}

		function selectAll(){
			var isCheck=jQuery("#qx").is(':checked');
			jQuery("input:checkbox[name=xydm]").each(function() {
		        this.checked = isCheck;       //ѭ����ֵ��ÿ����ѡ���Ƿ�ѡ��
		    });
		}
		
		jQuery(function(){
			jQuery("#fbbz").focus(function(){
				jQuery("#note").css("display","none");
			})

			jQuery("#fbbz").blur(function(){
				var content = this.value;
				if(content == null || content == ""){
					jQuery("#note").css("display","block");
				}else{
					jQuery("#note").css("display","none");
				}
			})
		})
		
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglfdgl" method="post" styleId="fdglForm" onsubmit="return false;">
			<input type="hidden" name="fbrxh" value="${jbxx.xh }"/>
			<input type="hidden" name="fblx" value="0"/>
			<input type="hidden" id="kfxydm" name="kfxydm" value=""/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>����</th>
						<td>${jbxx.xm }</td>
						<th>ѧ��</th>
						<td>${jbxx.xh }</td>
					</tr>
					<tr>
						<th>����ѧԺ</th>
						<td>${jbxx.xymc }</td>
						<th>רҵ</th>
						<td>${jbxx.zymc }</td>
					</tr>
					<tr>
						<th>�༶</th>
						<td>${jbxx.bjmc }</td>
						<th>��ϵ�绰</th>
						<td>${jbxx.sjhm }</td>
					</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ϣ����</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red">*</span>����ѧ�Ƽ�����
							</th>
							<td width="30%">
								<html:text property="fdkm" styleId="fdkm" value = "" maxlength="10"></html:text>
							</td>
							</td>
							<th>
								<span class="red">*</span>�޶�����
							</th>
							<td>
								<html:text property="xdrs" styleId="xdrs" onkeyup="checkInput(this)" value = "" maxlength="2"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>����ʱ��
							</th>
							<td colspan="3">
								<html:textarea property='fdsj' style="width:98%" styleId="fdsj" rows='4' onblur="checkLen(this,250);"/>
							</td>
			      		</tr>
						<tr>
							<th><span class="red">*</span>����<bean:message key="lable.xb" /></th>
							<td colspan="3">
								<table width="100%" border="0" class="formlist">
									<tr>
										<td style="border:0px;"><input type='checkbox' value="qx" name="qx" id="qx" onclick="selectAll();"/>ȫѡ</td>
										<td style="border:0px;"></td>
										<td style="border:0px;"></td>
									</tr>
									<tr>
									<logic:iterate name="xyList" id="l" indexId="index">
										<td style="border:0px;">
										<span style="word-break:break-all;margin-right:15px;">
										<input type='checkbox' value='${l.xydm }' name='xydm'/>${l.xymc }</span>
										</td>
										<%if(index.intValue()%3==2 && index.intValue() != 0){ %>
										</tr><tr>
										<%} %>
									</logic:iterate>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								����˵��
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<div style="position:relative;">							
									<html:textarea property='fbbz' style="width:98%" styleId="fbbz" rows='8' onblur="checkLen(this,500);"/>
									<div id="note" class="note">
            							<font color="#777">1����������ѧ�ơ����ƻ���</font></br>
            							<font color="#777">2��Ԥ��Ŀ��</font></br>
            							<font color="#777">3����������</font></br>
            							<font color="#777">4���Ա�Ҫ��</font></br>
            							<font color="#777">5���꼶Ҫ��</font></br>
        							</div>
								</div>								
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
									<button type="button" onclick="saveFdgl('save');">
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

