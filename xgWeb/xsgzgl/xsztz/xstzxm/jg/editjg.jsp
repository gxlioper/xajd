<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xstzxm/jg/js/xmjg.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
	
			jQuery(function(){
				jQuery("#selhd").unbind('click').bind('click', function(){
						var url = "xmsqgl_xmjg.do?method=getXmSelectList&xh="+jQuery("tbody").eq(0).find("tr:eq(0) td:eq(0) a").text();
						 showDialog('��ѡ����Ŀ',600,400,url);
			   });
			});
		</script>
		<style type = "text/css">
		</style>
	</head>
	<body>
		<html:form action="/xmsqgl_xmjg" method="post" styleId="XsXmJgForm">
			<input type="hidden" name="jgid" id="jgid" value="${hdmap.jgid}"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${hdmap.xmdm}"/>
			<html:hidden   property="xh" />
			<html:hidden   property="xq" />		
			<html:hidden   property="xn" />
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
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
								<span>��Ŀ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>��Ŀ����</th>
							<td>
								<input name="xmmc" id="xmmc" readonly="readonly" value="${hdmap.xmmc}"/>
								<button class="btn_01" id="selhd" type="button" >ѡ��</button>
							</td>
							<th>��Ŀ����</th>
							<td id="xmjbmc" >
								${hdmap.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
								${hdmap.xn}
							</td>
							<th>ѧ��</th>
							<td id="xq" >
								${hdmap.xqmc}
							</td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
								${hdmap.sbbmmc}
							</td>
							<th>��ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
								${hdmap.lxdh}
							</td>
						</tr>
						<tr>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
								${hdmap.sskmmc}
							</td>
							<th>����ѧ��</th>
							<td id="jcxf" name="jcxf">
								${hdmap.jcxf}
							</td>
						</tr>
						<tr>
							<th>�ɲ�������</th>
							<td id="kcyrs" name="kcyrs">
								${hdmap.kcyrs}
							</td>
							<th>����������</th>
							<td id="sqrs" name="sqrs">
								${hdmap.sqrs}
							</td>
						</tr>
						<tr>
							<th>��ͨ������</th>
							<td id="tgrs" name="tgrs">
								${hdmap.tgrs}
							</td>
							<th>��Ŀ��ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
								${hdmap.xmkssj}
							</td>
						</tr>
						<tr>
							<th>��������</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="sqly" styleId="sqly"
								   onkeyup="checkzs();return false;" 
								   style="width:99%;" rows="3">${sqly}</html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjgUpdate('update');return false;">
										�޸�
									</button>
									<button type="button" onclick="iFClose();">
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
	
</html>