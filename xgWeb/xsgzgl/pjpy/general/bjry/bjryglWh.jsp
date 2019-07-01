<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
		<script	type="text/javascript">
			function Save(){
				saveBjryglInfo();	
			}
			
			function saveBjryglInfo(){
				jQuery.ajaxSetup({async:false});	
				// �õ�JSON����
			    var parameter ={};	
				parameter["xn"]=escape(jQuery("#xn").val());
				parameter["xq"]=escape(jQuery("#xq").val());
				parameter["bjdm"]=escape(jQuery("#bjdm").val());
				parameter["hdsj"]=escape(jQuery("#hdsj").val());
				parameter["bz"]=escape(jQuery("#bz").val());
				var url = "pjpy_bjrygl_ajax.do?method=bjryglXgBc";
		        $("divWaiting").style.display="";
				$("divDisable").style.display="";
				jQuery.post(url,parameter,
					function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result);
					}
				);
				jQuery.ajaxSetup({async:true});
			}
		</script>
	</head>
	<body onload="">
	
		<html:form action="/pjpy_bjrygl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			<input type="hidden" name="xn" id="xn" value="${rs.xn }" />
			<input type="hidden" name="xq" id="xq" value="${rs.xq }" />
			<input type="hidden" name="bjdm" id="bjdm" value="${rs.bjdm }" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:400px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>�޸İ༶����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="15%">
								�༶����
							</th>
							
							<td width="35%" colspan="3" >
								${rs.bjmc }
							</td>
							<th width="15%">
								�����꼶
							</th>
							
							<td width="35%" colspan="3" id="ssnj">
								${rs.nj }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="15%">
								����Ժϵ
							</th>
							
							<td width="35%" colspan="3" id="ssyx">
								${rs.xymc }
							</td>
							<th width="15%">
								����רҵ
							</th>
							
							<td width="35%" colspan="3" id="sszy">
								${rs.zymc }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="20%">
								<font color="red">*</font>����ѧ��
							</th>
							
							<td width="40%" colspan="3" >
								<html:select property="xn" styleId="xn" name="rs" disabled="true">
									<html:options collection="xnList" labelProperty="xn" property="xn"/>
								</html:select>
							</td>
							
							<th width="20%">
								����ѧ��
							</th>
							
							<td width="40%" colspan="3" >
								<html:select property="xq" styleId="xq" name="rs" disabled="true">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc" />
								</html:select>
							</td>
						</tr>
						<tr style="height:22px">
							<th width="20%">
								<font color="red">*</font>�������
							</th>
							
							<td width="40%" colspan="3" >
								<html:select property="rydm" styleId="rydm" name="rs" disabled="true">
									<html:options collection="hdryList" labelProperty="mc" property="dm"/>
								</html:select>
							</td>
							<th width="15%">
								���ʱ��
							</th>
							
							<td width="25%" colspan="3">
								<html:text property="hdsj" styleId="hdsj" name="rs" style="width:90px"
									onclick="return showCalendar('hdsj','y-mm-dd');" 
									onblur="dateFormatChg(this)" readonly="true" />
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right" >
								��ע<br/><font color="blue">(��500��)</font>
							</th>
							<td colspan="7" >
							<html:textarea  property='bz' styleId="bz" style="word-break:break-all;width:97%" onblur="chLeng(this,500);"
									rows='5' value="${rs.bz }"  />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button type="button"  onclick="Save();return false;">
										�� ��
									</button>
									<button type="button"  onclick="Close();return false;">
										�� ��
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

