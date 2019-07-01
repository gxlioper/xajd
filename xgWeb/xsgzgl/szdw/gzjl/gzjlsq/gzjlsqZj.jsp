<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/gzjlsq/gzjlsq.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/gzjl/comm/comm.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/zdybd/js/zdybdUtil.js"></script>
		<script type='text/javascript'>
		function thxszj(html){
			jQuery("#tbody_dhryxx").append(html);	
			}
		
		</script>
	</head>
	<body>
		<html:form action="/gzjlsq" method="post" styleId="GzjlsqForm">
		<html:hidden property="zgh" styleId="zgh"/>
		<logic:equal value="11842" name="xxdm">
			<input type="hidden" id="objStr" name="objStr"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
		</logic:equal>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom: 0px;" >
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
							<th><font color="red">*</font>����ʱ��</th>
							<td>
							<html:text property="gzsj" styleId="gzsj" onfocus="showCalendar('gzsj','y-mm-dd');" />
							</td>
							<th>��¼ʱ��</th>
							<td>
							<input type="hidden" value="${jlsj}" name="jlsj" id="jlsj"/>
								${jlsj}
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>�������</th>
							<td>
								<html:select  property="lbdm" styleId="lbdm" style="width:200px" onchange="getLbdm(this)">
								    <html:option value="">--��ѡ��--</html:option>
									<html:options collection="gzlbList" property="gzlbdm" labelProperty="gzlbmc" />
								</html:select>
							</td>
							<th>�����</th>
							<td>
								<html:text property="lbbh" styleId="lbbh" maxlength="50"  readonly="true"></html:text>
								
							</td>
						</tr>
						<logic:equal value="11842" name="xxdm">
							<tr>
								<th><font color="red">*</font>������</th>
								<td>
									<html:select  property="lks" styleId="lks" style="width:200px">				    
										<html:options collection="lksList" property="lksdm" labelProperty="lksmc" />
									</html:select>
								</td>
							</tr>
						</logic:equal>
						<tr>			
							<th><span class="red">*</span>����ժҪ
								</br><font color="red">(��1000��)</font></th>
							<td colspan="3">
								<html:textarea property="gzzy" styleId="gzzy" 
											   onblur="checkLen(this,1000);" 
											   style="width:99%;" rows="6"></html:textarea>
							</td>
						</tr>
						<tr>
						
							<th>��ע</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
											   onblur="checkLen(this,500);"
											   style="width:99%;" rows="4"></html:textarea>
							</td>
						</tr>
					</tbody>
					<logic:equal value="11842" name="xxdm">
						<thead>
							<tr>
								<th colspan="4">
									<span≯������</span>
								</th>
							</tr>
						</thead>
					</logic:equal>
				</table>
			</div>
		<logic:equal value="11842" name="xxdm">
			<div style="height:200px;overflow-y:auto;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">					
					<thead>
						<tr>
							<td colspan="7">
							<button type="button" id="btn_getXsxx" onclick="bcZjRyxx();" style="display: none;"></button>
							<button type="button" onclick="addThxs();return false;" class="btn_01">����ѧ��</button>
							<button type="button" onclick="delThxs();return false;" class="btn_01">ɾ��ѧ��</button>
							</td>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>ѧ��</td>
							<td width='10%'>����</td>
							<td width='10%'>�Ա�</td>
							<td width='20%'>ѧԺ</td>
							<td width='20%'>רҵ</td>
							<td width='20%'>�༶</td>
						</tr>
					</thead>
					<tbody id="tbody_dhryxx">
						
					</tbody>
				</table>
				
				</div>
				<div style="height:30px"></div>
			</logic:equal>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="savegzjlsq('save');">
										����ݸ�
									</button>
									<button type="button" onclick="savegzjlsq('submit');">
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