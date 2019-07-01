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
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/kqsq.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zwzxkqgl/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		function qqxszj(html){
			jQuery("#tbody_qqryxx").append(html);	
		}
		function editKqsq(type) {
			var flg=true;
			var objArr= [];
			jQuery.each(jQuery("#tbody_qqryxx tr"),function(i,n){
				var obj = {};
				if (flg){
					var qqlx = jQuery(n).find("select[name=qqlxdm] option:selected").val();
					var xh= jQuery(n).find("td").eq(1).text();
					var kkjs = jQuery(n).find("input[name=kkjs]").val();
					var ylzd1 = jQuery(n).find("input[name=ylzd1]").val();
					obj.xh=xh;
					obj.kkjs=kkjs;
					obj.qqlxdm=qqlx;
					obj.ylzd1=ylzd1;
					objArr.push(obj);
					flg = (qqlx != "" );
				}
			});
			var validateFlag = true;
				validateFlag = checkKqxx(flg,type);
			if(validateFlag){
			var objStr = JSON.stringify(objArr);
			jQuery("#objStr").val(objStr);
			var url = "zwzxKqsq.do?method=saveEditKqsq&type=" + type;
			ajaxSubFormWithFun("ZwzxKqsqForm", url, function(data) {
				 if(data["message"]=="����ɹ���" || data["message"]=="�ύ�ɹ���"){
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
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zwzxKqsq" method="post" styleId="ZwzxKqsqForm" onsubmit="return false;">
		<html:hidden property="xn" styleId="xn"/>
			<html:hidden property="xq" styleId="xq"/>
			<html:hidden property="sqid" styleId="sqid"/>
			<html:hidden property="bjdm" styleId="bjdm"/>
			<html:hidden property="bjmc" styleId="bjmc"/>
			<html:hidden property="sqsj" styleId="sqsj"/>
			<html:hidden property="xxdm" styleId="xxdm" value="${xxdm}"/>
			<input type="hidden" id="objStr" name="objStr"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span><font id="gnmkmc_prompt_span"></font>�޸�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${kqsqForm.xn}
							</td>
							<th width="20%">
								<font color="red">*</font><span id="ccrq_span"></span>
							</th>
							<td width="30%">
								<html:text property="ccrq"
									onclick="return showCalendar('ccrq','y-mm-dd');" styleId="ccrq"
									readonly="true"></html:text>
							</td>
						</tr>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${kqsqForm.xqmc}
							</td>
							<th width="20%">
								<font color="red">*</font><span id="cclx_span"></span>
							</th>
							<td width="30%">
								<html:select property="cclxdm" styleId="cclxdm">
									<html:option value=""></html:option>
									<html:options collection="cclxList" property="lxdm"
										labelProperty="lxmc" />
								</html:select>
							</td>
						</tr>
							<tr>
								<th width="20%">
									<font color="red">*</font>�༶
								</th>
								<td width="30%">
									${kqsqForm.bjmc}
								</td>
								<th width="20%">
									<font color="red">*</font>Ӧ������
								</th>
								<td width="30%">
									<logic:equal name="xxdm" value="2297">
										<html:text property="ydrs" styleId="ydrs" maxlength="3" readonly="true"
										onchange="computeQqrs()"></html:text>
									</logic:equal>
									<logic:notEqual name="xxdm" value="2297">
										<html:text property="ydrs" styleId="ydrs" maxlength="3"
										onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
									</logic:notEqual>
									
								</td>
							</tr>
							<tr>
								<th width="20%">
									<font color="red">*</font>ʵ������
								</th>
								<td width="30%">
									<html:text property="sdrs" styleId="sdrs" maxlength="3"
										onkeyup="value=value.replace(/[^\d]/g,'');computeQqrs()"></html:text>
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
								<td width="30%">
									<html:text property="qqrs" readonly="true" styleId="qqrs"></html:text>
								</td>
							</tr>
						</logic:notEqual>
						<tr>
							<th width="20%" id="jlf_th">
								���ɷ�
							</th>
							<td width="30%" id="jlf_td">
								<html:text property="jlf" styleId="jlf" maxlength="3" onblur="checkJlf(this)" onkeyup="value=value.replace(/(?:\D*)?(\d*)?(\.)?(\d*)?(?:\d*)?/ig,'$1$2$3')"></html:text>
							</td>
							<th width="20%">
								<span id="jlr_span"></span>
							</th>
							<td width="30%" id="jlr_td">
								${kqsqForm.jlrxm }
							</td>
						</tr>

						<logic:equal name="xxdm" value="10704">
							<tr>
								<th>
									���ศ��Ա
								</th>
								<td id="dbfdy" colspan="3">
										${kqsqForm.dbfdy }
								</td>
							</tr>
						</logic:equal>

						<tr>
						    <th>
								��ע</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" cols="50" rows="4"
									style="width:100%" onblur="chLeng(this,500)"></html:textarea>
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
								<html:text property="wjrs" styleId="wjrs" maxlength="3"
									onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
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
				<div style="height:200px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;"> 
					
					<thead>
						<tr>
							<td colspan="7">
							<button type="button" id="btn_getXsxx" onclick="bcZjRyxx();" style="display: none;"></button>
							<button type="button" onclick="addQqxs();return false;" class="btn_01">����ѧ��</button>
							<button type="button" onclick="delQqxs();return false;" class="btn_01">ɾ��ѧ��</button>
							</td>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
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
						<td>
						<input type="checkbox" id="checkbox_${index01}"/>
						</td>
						<td name="xhArr">${i.xh}</td>
						<td>${i.xm}</td>
							<logic:notEqual name="xxdm" value="12970">
								<td>
								<html:select property="qqlxdm" styleId="qqlxdm_${index01}" value="${i.qqlxdm}" onchange="changeQqlx(${index01})">
										<option value=""></option>
										<html:options collection="qqlxList" property="qqlxdm" labelProperty="qqlxmc"/>
								</html:select>
								</td>
								<td>
										<html:text property="kkjs" styleId="kkjs_${index01}" maxlength="2" value="${i.kkjs}"
											onkeyup="value=value.replace(/[^\d]/g,'');"></html:text>
								</td>
							</logic:notEqual>
							<td width="30%">
									<html:text property="ylzd1" styleId="ylzd1" value="${i.ylzd1}" onblur="chLeng(this,500)"></html:text>
							</td>
					
						</tr>
						</logic:iterate>
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
								<button type="button" onclick="editKqsq('save');">
										����ݸ�
									</button>
									<button type="button" onclick="editKqsq('submit');">
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

