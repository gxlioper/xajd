<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
						<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script>
			//����
			function twdjBc(){
				if(jQuery.trim(jQuery("#xh").val())==""){
			 		alertInfo("ѧ�Ų���Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				if(jQuery.trim(jQuery("#rwzh").val())==""){
			 		alertInfo("����֤�Ų���Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				
				if(jQuery.trim(jQuery("#twsj").val())==""){
			 		alertInfo("����ʱ�䲻��Ϊ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				var rwsj = jQuery("#rwsj").text();
				var twsj = jQuery("#twsj").val();
				if(rwsj>twsj){
					alertInfo("����ʱ�䲻ӦС�ڻ��������ʱ��!",function(tag){
			 			if(tag=="ok"){
			 				return false;
			 			}
			 		});
			 		return false;
				}
				jQuery.ajaxSetup({async:false});	
				// �õ�JSON����
			    var parameter ={};	
			    parameter["xh"]=escape(jQuery("#xh").val());
			    parameter["rwzh"]=escape(jQuery("#rwzh").val());
				parameter["twsj"]=escape(jQuery("#twsj").val());
				parameter["yzy"]=escape(jQuery("#yzy").val());
				parameter["ybj"]=escape(jQuery("#ybj").val());
				parameter["hjgx"]=escape(jQuery("#hjgx").val());
				parameter["hkszd"]=escape(jQuery("#hkszd").val());
				parameter["bz"]=escape(jQuery("#bz").val());
				url = "rwgl_rwtwgl_ajax.do?method=twdjBc&doType=update";
			    $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						if("����ɹ�"==result){
							 showAlert(result,{},{"clkFun":function(){
				    				if (parent.window){
				    					refershParent();
				    				}
							 }});
						}else{
							alertInfo(result,function(tag){
				     			if(tag=="ok"){
				     				return false;
				     			}
				     		});
				     		return false;
						}
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>
	<body>
		<html:form action="/rwgl_rwtwgl" method="post">
			<div style="height:485px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								<html:hidden name="rs" property="xh" styleId="xh"></html:hidden>
								${rs.xh }
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%" >
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%">
								${rs.xb }
							</td>
							<th width="16%">
								�꼶
							</th>
							<td width="34%">
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								${rs.xymc }
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%">
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								�༶
							</th>
							<td width="34%">
								${rs.bjmc }
							</td>
							<th width="16%">
								������ò
							</th>
							<td width="34%">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
								${rs.mzmc }
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.csrq }
							</td>
						</tr>
						<tr>
							<th width="16%">
								���֤��
							</th>
							<td width="34%">
								${rs.sfzh }
							</td>
							<th width="16%">
								�ֻ�����
							</th>
							<td width="34%">
								${rs.sjhm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ͥ��ַ
							</th>
							<td width="34%">
								${rs.jtdz }
							</td>
							<th width="16%">
								��ͥ�绰
							</th>
							<td width="34%">
								${rs.jtdh }
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
							<th width="16%">
								<font color="red"></font>����ʱ��
							</th>
							<td width="34%" id="rwsj">
								${rs.rwsj }
							</td>
							<th width="16%" >
								<font color="red"></font>�����
							</th>
							<td width="34%" style="word-break:break-all;width:99%">
								<!-- ���ݴ�ѧ -->
								<logic:equal name="xxdm" value="10351">	
									${rs.rwdwdmc }
								</logic:equal>
								<logic:notEqual name="xxdm" value="10351">	
									${rs.rwd }
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th width="16%">
								���鷽ʽ
							</th>
							<td width="34%" colspan="3">
								<font id="rwfs">${rs.rwfsmc}</font>
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
							<th width="16%">
								<font color="red">*</font>����֤��
							</th>
							<td width="34%">
								<html:text name="rs" property="rwzh" styleId="rwzh" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>����ʱ��
							</th>
							<td width="34%">
								<html:text name="rs" property="twsj" styleId="twsj" maxlength="10" onclick="return showCalendar('twsj','y-mm-dd');" onblur="dateFormatChg(this)" readonly="true" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								ԭרҵ
							</th>
							<td width="34%">
								<html:text name="rs" property="yzy" styleId="yzy" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								ԭ�༶
							</th>
							<td width="34%">
								<html:text name="rs" property="ybj" styleId="ybj" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								������ϵ
							</th>
							<td width="34%">
								<html:text name="rs" property="hjgx" styleId="hjgx" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
							<th width="16%">
								�������ڵ�
							</th>
							<td width="34%">
								<html:text name="rs" property="hkszd" styleId="hkszd" maxlength="10" styleClass="text_nor" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ע<br/><font class="red">(��1000��)</font>
							</th>
							<td width="84%" colspan="3">
								<html:textarea name="rs" property='bz' styleId="bz" style="word-break:break-all;width:99%"
										rows='3' onblur="chLeng(this,1000)"/>
							</td>
						</tr>
					</tbody>
					<%--<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="twdjBc()">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				--%></table>
			</div>
			<table class="formlist" width="100%">
				<tfoot>
					<tr>
						<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="twdjBc()">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;">
										�� ��
									</button>
								</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

