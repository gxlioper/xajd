<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/jg/js/jcftzjg.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			//jifen();
			//jQuery("#xh").attr({readonly:"readonly"});
			jQuery("#selhd").bind('click', function(){				
				  var url = "jcftz_jg.do?method=getXmSelectList&type=wrd";
					showDialog('��ѡ����Ŀ',600,350,url);				
		   });
		});
		</script>
		<style type = "text/css">
			#xxdz{width:250px;}
			.fontstyl{float:left}
			.fontstyl1{margin-left:5px}
		</style>
	</head>
	<body>
		<html:form action="/jcftz_jg" method="post" styleId="jcftzjgForm">
			<input type="hidden" id="csms" name="csms"/>
			<input type="hidden" id="xxdm" name="xxdm" value="${xxdm }"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ŀ��Ϣ</span>
								<span> 
										<a onclick="showxmxx(this);" class="up"
											href="javascript:void(0);"> <font color="blue">��ϸ��Ϣ</font>
										</a>									
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width: 20%"><font color="red">*</font>��Ŀ����</th>
							<td style="width: 30%">
							<input type="hidden" name="xhs" id="xhs"/>
							<input type="hidden" name="jxdms" id="jxdms"/>
							<input type="hidden" name="sfqqs" id="sfqqs"/>
							<input type="hidden" name="tzhjcfs" id="tzhjcfs"/>
								<input type="hidden" name="jgid" id="jgid"/>
								<input type="hidden" id="xmdm" name="xmdm"/>
								<input type="text" name="xmmc" value="" id="xmmc" style="width:120px;"  readonly="readonly"/>
								<button class="btn_01" id="selhd" type="button" >ѡ��</button>
							</td>
							<th style="width: 20%">��Ŀ����</th>
							<td id="xmjbmc" style="width: 30%">
							  ${rs.xmjbmc}
                             </td>
						</tr>
						<tr>
							<th>�걨����</th>
							<td id="sbbmmc" >
								${rs.sbbmmc} 
							</td>
							<th>������Ŀ</th>
							<td id="sskmmc" name="sskmmc">
								${rs.sskmmc}
							</td>
						</tr>
						<tr>
							<th>�걨��</th>
							<td id="sbr" name="sbr">
								${rs.sbrxm}
							</td>
							<th>�걨����ϵ��ʽ</th>
							<td id="lxdh" name="lxdh">
								${rs.lxdh}
							</td>						
						</tr>
						<tbody id="t_xmxx" style="display: none;">
						<tr>
							<th>�ɲ�������</th>
							<td id="kcyrs" name="kcyrs">
								${rs.kcyrs}
							</td>
							<th>��Ŀ��ʼʱ��</th>
							<td id="xmkssj" name="xmkssj">
								${rs.xmkssj}
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ���������
							</th>
							<td id="sfsljxmc">
								${rs.sfsljxmc}
							</td>
							<th>
								����ѧ��
							</th>
							<td id="jcxf">
								${rs.jcxf}
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td id="xn" >
								${rs.xn}
							</td>
							<th>ѧ��</th>
							<td id="xq" >
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>��Ŀ����</th>
							<td id="xmlx"></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th width="20%">
								��Ŀ������Ϣ
							</th>
							<td width="30%"  colspan="3">
								
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
									</tbody>
								</table>
								</div>								
							</td>
						</tr>
						</tbody>
						<thead>
							<tr>
								<th colspan="4">
									<span>ѧ���϶�</span>
								</th>
							</tr>
					   </thead>
					</tbody>
				</table>
				</div>
				<div style="overflow-y:auto;height:250px">
					<tr>						
						<table width="100%" border="0" class="formlist">
								<tr style="display:none" id="grthead">
									<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
									<td width='10%'>ѧ��</td>
									<td width='7%'>����</td>
									<td width='10%'>�༶</td>
									<td width='7%'>������</br>������<font color='red'>*</font></td>
									<td width='10%'>��ý���</td>
									<td width='5%'>�Ƿ�</br>ȱ��</td>
									<td width='6%'>���</br>��ѧ��</td>
									<logic:equal name="xxdm" value="13627">
									<td width='8%'>��ע1</td>
									<td width='8%'>��ע2</td>
									<td width='8%'>��ע3</td>
									<td width='8%'>��ע4</td>
									<td width='8%'>��ע5</td>
									</logic:equal>
								</tr>
								<tr style="display:none" id="ttthead">
									<td width='5%'><input type="checkbox" name="th" onclick="selectAll(this);" /></td>
									<td width='10%'>�Ŷ�����</td>
									<td width='10%'>�ӳ�����</td>
									<td width='10%'>�ӳ���</br>��Ժϵ</td>
									<td width='5%'>������</br>������<font color='red'>*</font></td>
									<td width='10%'>��ý���</td>
									<td width='5%'>�Ƿ�</br>ȱ��</td>
									<td width='5%'>���</br>��ѧ��</td>
									<logic:equal name="xxdm" value="13627">
									<td width='8%'>��ע1</td>
									<td width='8%'>��ע2</td>
									<td width='8%'>��ע3</td>
									<td width='8%'>��ע4</td>
									<td width='8%'>��ע5</td>
									</logic:equal>
								</tr>
								<tbody id="tbody_dhryxx">
									
								</tbody>									
						</table>
				</div>
				<div style="height:30px;"></div>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveSqjg('save');">
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
		</html:form>
	</body>
	
</html>