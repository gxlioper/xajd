<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/mrgzkh/khsq/khsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
		function editKhsq(type) {
			if("0" == jQuery("#gs").val()) {
				showAlert("��ʱ����Ϊ�㣡");
				return false;
			}
            if(parseInt(jQuery("#gs").val())  > 8){
                showAlert("ÿ�칤��ʱ��<=8Сʱ��");
                return false;
            }
			if(checkZdz()){
			var url = "mrgzkhKhsq.do?method=saveEditKhsq&type=" + type;
			ajaxSubFormWithFun("GzkhKhsqForm", url, function(data) {
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
		}
		jQuery(function(){
			var isopen = jQuery("#sqkg").val();
			var shzt = jQuery("#shzt").val();
			if('3' != shzt && (isopen==null||isopen==''||"0" == isopen)){
				jQuery("#btn_submit").hide();
			}
		});
		</script>
	</head>
	<body>
		<html:form action="/mrgzkhKhsq" method="post" styleId="GzkhKhsqForm">
		<html:hidden property="sqid" />
		<html:hidden property="xh" styleId="xh" value="${jbxx.xh}"/>
		<input type="hidden" id="sqkg" value="${sqkg}"/>
		<html:hidden styleId="shzt" property="shzt" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:460px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��д��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>���˵�λ</th>
							<td>
							
								<html:select property="yrdw" styleId="yrdw" onclick="checkXh();" onchange="getGwxx();" style="width:200px">
										<html:options collection="yrdwList" property="bmdm" labelProperty="bmmc" />
									</html:select>
							</td>
							<th><font color="red">*</font>��ʱ</th>
							<td>
								<html:text property="gs" styleId="gs" maxlength="10" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(5)?(?:\d*)?/ig,'$1$2$3')"
                                ></html:text>��Сʱ��
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��λ</th>
							<td>
								<html:select  property="gwdm" styleId="gwdm" style="width:200px">
									<html:options collection="gwList" property="gwdm" labelProperty="gwmc" />
								</html:select>
							</td>
							<th><font color="red">*</font>��������</th>
							<td colspan="3">
								<html:text  property="gzrq" styleId="gzrq" readonly="true" onfocus="showCalendar('gzrq','yyyy-MM-dd');"/>
							</td>
						</tr>
						<tr>
						<tr>
							<th><font color="red">*</font>����ʱ���</th>
							<td colspan="3">
								<html:text  property="gzkssj" styleId="gzkssj" onfocus="showCalendar('gzkssj','HH:mm');" />
								-
								<html:text  property="gzjssj" styleId="gzjssj" onfocus="showCalendar('gzjssj','HH:mm');" />
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								<span style="font-size: larger; color: orchid">������ʾ��</span>
								<br>
								<span>1.ѧ��ÿ����ɹ�������뵱����ϵͳ��ά��������ʱ������δ��ʱά��������ϵ���Ź���Ա��ʦ��ά����</span>
								<br>
								<span>2.ÿ�칤��ʱ��<=8Сʱ��ÿ�¹���ʱ��<=40Сʱ��������Ź���ʱ�䡣</span>
							</div>
						</td>
					</tr>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="editKhsq('save');">
										����ݸ�
									</button>
									<button type="button" id="btn_submit" onclick="editKhsq('submit');">
										�ύ����
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
		</html:form>
	</body>
	
</html>