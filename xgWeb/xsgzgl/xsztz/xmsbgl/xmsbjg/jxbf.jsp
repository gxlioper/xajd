<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsbjg/js/jxbf.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/comm/js/comm.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		jQuery(function(){
			location.href = "#tbody_xmsqxx"; ;
			});
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/xmsqgl_xmjg" method="post" styleId="XsXmJgForm" onsubmit="return false;">
		<html:hidden property="xmdm" styleId="xmdm" value="${rs.xmdm}"/>
		<html:hidden property="jcxf" styleId="jcxf" value="${rs.jcxf}"/>
		<html:hidden property="xmjbdm" styleId="xmjbdm" value="${rs.xmjbdm}"/>
		<html:hidden property="jgid" styleId="jgid" value="${rs.jgid}"/>
		<html:hidden property="sskmdm" styleId="sskmdm" value="${rs.sskmdm}"/>
		<html:hidden property="xn" styleId="xn" value="${rs.xn}"/>
		<html:hidden property="xq" styleId="xq" value="${rs.xq}"/>
			<div id="winDiv" style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ�걨</span>
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
							</td>
							<th>ѧ��</th>
							<td>
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmmc}
							</td>
							<th width="20%">
								��Ŀ����
							</th>
							<td width="30%">
								${rs.xmjbmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�걨����
							</th>
							<td width="30%">
								${rs.sbbmmc}
							</td>
							<th width="20%">
								������Ŀ
							</th>
							<td width="30%">
								${rs.sskmmc}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�ɲ�������
							</th>
							<td width="30%">
								${rs.kcyrs}
							</td>
							<th width="20%">
								��Ŀ��ʼʱ��
							</th>
							<td width="30%">
								${rs.xmkssj}
							</td>
						</tr>
						<tr>
							<th width="20%">
								�걨��
							</th>
							<td width="30%" >
								${rs.sbr}
							</td>
							<th width="20%">
								�걨����ϵ��ʽ
							</th>
							<td width="30%">
								${rs.lxdh}
							</td>																			
						</tr>
						<tr>
							<th width="20%">
								�Ƿ���������
							</th>
							<td width="30%" >
								${rs.sfsljxmc}
							</td>
							<th width="20%">
								����ѧ��
							</th>
							<td width="30%" >
								${rs.jcxf}
							</td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ������Ϣ
							</th>
							<td width="30%"  colspan="3">
								<logic:notEmpty name="xmjxList">
								 <div style="overflow-y:auto;" id="jxDiv">
								 <table width="100%" border="0" class="formlist">
									<thead>
										<tr>
											<td width='15%'>��������</td>
											<td width='15%'>����ѧ��</td>
											<td width='15%'>����˳��</td>
										</tr>
									</thead>
									<tbody id="tbody_xmjxxx">
									<logic:iterate id="i" name="xmjxList" indexId="index01">
										<tr>
										<input type="hidden" name="jxid" value='${i.jgid}'/>
										<td name="jxArr">${i.jxmc}</td>
										<td>${i.fjxf}</td>
										<td>${i.xssx}</td>
										</tr>
										</logic:iterate>
								</tbody>
								</table>
								</div>
								</logic:notEmpty>
								<logic:empty name="xmjxList">
								��
								</logic:empty>
							</td>
						</tr>
						<tr><th width="20%">��Ŀ����
								</th>
							<td colspan="3">
								${rs.xmms}
							</td>
						</tr>
						<tr><th width="20%">��/�۷�����
									</th>
								<td colspan="3">
									${rs.dkfyj}
								</td>
						</tr>
						<tr><th width="20%">����Ҫ��
									</th>
								<td colspan="3">
									${rs.cyyq}
								</td>
						</tr>
					</tbody>
				 </table>
				</div>
				<div style="overflow-x:auto;height:300px;">
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td colspan="7">
							<button type="button" onclick="addXs();return false;" class="btn_01">����ѧ��</button>
							<button type="button" onclick="delXs();return false;" class="btn_01">ɾ��ѧ��</button>
							
							    <span>&nbsp;&nbsp;&nbsp;ѧ��/����
								<input type="text" id="xh" name="xh" maxleng="20" 
								   onkeypress="if(pressEnter(event)){query();return false;}"
								/>
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										�� ѯ
									</button>
								</span>
							</td>
						</tr>
						<tr>
							<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
							<td width='15%'>ѧ��</td>
							<td width='15%'>����</td>
							<td width='15%'>�༶</td>
							<td width='15%'>�Ƿ�ȱ��</td>
							<td width='15%'>��ý���</td>
							<td width='15%'>��ѧ��</td>
						</tr>
					</thead>
					<tbody id="tbody_xmsqxx">
						<logic:iterate id="i" name="sqxsList" indexId="index01">
						<tr>
						<td>
						<input type="checkbox" id="checkbox_${index01}"/>
						<input type="hidden" name='sjly' id="sjly_${index01}" value='${i.sjly}'/>
						</td>
						<td name="xh">${i.xh}</td>
						<td>${i.xm}</td>
						<td>${i.bjmc}</td>
						<td>
						<html:select property="ylzd2" styleId="ylzd2_${index01}" value="${i.ylzd2}" onchange="changeSfqq(${index01})">
								<html:options collection="sfqqList" property="dm" labelProperty="mc"/>
						</html:select>
						</td>
						<td>
						<html:select property="ylzd1" styleId="ylzd1_${index01}" value="${i.ylzd1}" onchange="changeXmjx(${index01})">
								<option value=""></option>
								<html:options collection="xmjxList" property="jgid" labelProperty="jxmc"/>
								
						</html:select>
						</td>
						
						<td>
								<span id="ylzd3_${index01}">${i.zxf}</span>
							</td>
						</tr>
						</logic:iterate>
						</tbody>
				</table>
				</div>
				<div style="height:50px"></div>
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
							<button type="button" onclick="saveJxbf();">
										����
									</button>
								<button type="button" onclick="Close();return false;">
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

