<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/head.ini"%>
	<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
	<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	<script language="javascript" src="js/calendar/calendar.js"></script>
	<script type="text/javascript" src="js/check.js"></script>
	
	<script	type="text/javascript">
		function getJllbList(){
			var jldldm=$("jldldm").value;
			var option="";
			jQuery.post('gyglnew_gyjlgl.do?method=getJllbList',{jldldm:jldldm},function(data){
					for(var i = 0; i < data.length; i++){
						option +="<option value='"+data[i].jllbdm+"'>"+data[i].jllbmc+"</option>";
					}
					jQuery('#jllbdm').empty().append(option);
					jQuery("#jllbdm").val(jQuery("#jllbdm_value").val());			
			},'json');
		}
		
		function save(type){
			var url = "gyjl_gyjlwh.do?method=gyjlXg&type="+type;
			if (jQuery("#wjxn").val() == "" || jQuery("#wjxn").val() == null
			|| jQuery("#wjxq").val() == "" || jQuery("#wjxq").val() == null
			|| jQuery("#jldldm").val() == "" || jQuery("#jldldm").val() == null
			|| jQuery("#jllbdm").val() == "" || jQuery("#jllbdm").val() == ""||
			jQuery("#wjsj").val() == "" || jQuery("#wjsj").val() == "") {
				showAlert("�뽫<font color='red'>*</font>��������д������");
				return false;
			}
			ajaxSubFormWithFun("gyjlwhForm", url, function(data) {
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

		jQuery(function(){
			getJllbList();
		})

	</script>
</head>
<body>
	<html:form action="/gyjl_gyjlwh" method="post" styleId="gyjlwhForm" onsubmit="return false" >	
		<html:hidden property="wjid" styleId="wjid"/>
		<html:hidden property="wjxn" styleId="wjxn" value="${rs.wjxn}"/>
		<html:hidden property="xh" styleId="xh" value="${rs.xh}"/>
		<html:hidden property="wjxq" styleId="wjxq" value="${rs.wjxq}"/>
		<div class="tab" style="width:100%;height:420px;overflow-x:hidden;overflow-y:auto;">
		<table class="formlist" width="100%">	
			<thead>
				<tr>
					<th colspan="4">
					<span>ѧ����Ϣ</span>
					</th>
					</tr>
			</thead>
			<tbody>
					<tr>
						<th align="right" width="20%">
							ѧ��
						</th>
						<td id="xh" align="left" width="30%">
							${rs.xh }
						</td>
						<th align="right" width="20%">
							����
						</th>
						<td align="left">
							${rs.xm}
						</td>
					</tr>
					<tr>
						<th align="right">
							�꼶
						</th>
							<td align="left">
							${rs.nj}
						</td>
						<th align="right">
							<bean:message key="lable.xb" />
						</th>
							<td align="left">
							${rs.xymc}
						</td>
					</tr>
					<tr>
						<th align="right">
							רҵ
						</th>
							<td align="left">
							${rs.zymc}
						</td>
						<th align="right">
							�༶
						</th>
						<td align="left">
							${rs.bjmc}
						</td>
					</tr>
					<tr>
						<th>
							¥������
						</th>
						<td>
							${rs.ldmc }
						</td>
						<th>
						���Һ�
						</th>
							<td>
								${rs.qsh }
							</td>
					</tr>
					<tr>
						<th>
							��λ��
						</th>
						<td>
							${rs.cwh}
						</td>
						<th>
							���ҵ绰
						</th>
						<td>
							${rs.qsdh}
						</td>
					</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="4">
					<span>��Ԣ������Ϣ</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>Υ��ѧ��
				</th>
				<td align="left" width="30%">
					${rs.wjxn}
				</td>
					<th align="right" width="20%">
						<font color="red">*</font>Υ��ѧ��
					</th>
				<td align="left" width="30%">
					${rs.xqmc}
				</td>
			</tr>
			<tr>
				<th align="right" width="20%">
					<font color="red">*</font>���ɴ���				
				</th>
				<td align="left" width="30%">
					<html:select name="rs" property="jldldm" styleId="jldldm" style="width:140px" onchange="getJllbList()">
					<html:options collection="jldlList" labelProperty="jldlmc" property="jldldm"/>
					</html:select>
				</td>
				<th align="right" width="20%">
					<font color="red">*</font>�������				
				</th>
				<td align="left" width="30%" >
					<input type="hidden" id="jllbdm_value" value="${rs.jllbdm}"/>
					<html:select name="rs" property="jllbdm" styleId="jllbdm" style="width:140px" >
					<html:options collection="jllbList" labelProperty="jllbmc" property="jllbdm"/>
					</html:select>
				</td>
			</tr>
			<tr>
					<th width="20%">
						<font color="red">*</font>Υ��ʱ��				
					</th>
					<td align="left" width="30%" colspan="3">
							<html:text name="rs" property="wjsj" styleId="wjsj" style="width:140px" onkeypress="onlyBackSpace(this,event);"
							onclick="return showCalendar(this.id,'yyyy-MM-dd')"  readonly="true"></html:text>
					</td>
			</tr>
			<tr>
					<th>
						��ע
						<br /><font color="red">(������100����)</font>
					</th>
					<td colspan="4">
						<html:textarea name="rs" property='bz' style="width:650px;" styleId="bz" rows='4' onblur="chLeng(this,100);"/>
					</td>
			</tr>
			</tbody>
			</table>
		</div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
			<tfoot>
			      <tr style="height:22px">
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			          	<button type="button"  name="�ύ" id="buttonSave" onclick="save('update');return false;">����</button>
			            <button type="button"  name="�ر�" id="buttonClose" onclick="Close();return false;">�ر�</button>
			          </div></td>
			      </tr>
			 </tfoot>
			</table>
	</html:form>
</body>
</html>
