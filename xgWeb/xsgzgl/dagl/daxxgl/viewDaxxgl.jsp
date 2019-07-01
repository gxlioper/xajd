<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script language="javascript" src="js/Basic.js"></script>
		<script type="text/javascript" defer="defer">
		function goDaxxInfo(){

			jQuery("#daxxTabId").addClass("ha");
			jQuery("#daclTabId").removeClass("ha");
			jQuery("#tbody_dajbxx").show();
			jQuery("#tbody_daclxx").hide();
		}
		
		function goDaclInfo(){
			
			var pk=jQuery("#xh").val()+jQuery("#dazrsj").val();
			jQuery("#daclTabId").addClass("ha");
			jQuery("#daxxTabId").removeClass("ha");
			jQuery("#tbody_dajbxx").hide();
			jQuery("#tbody_daclxx").show();
		}
		
		</script>
	</head>
	<body>
		<html:form action="/daxxgl" method="post"  onsubmit="return false;" styleId="form">
			<html:hidden  property="xh"  styleId="xh" value="${rs.xh}"/>
			<html:hidden  property="dazrsj"  styleId="dazrsj" value="${rs.dazrsj}"/>
			<div style='width:100%;height:500px;overflow-y:auto;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_xsjbxx">
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<tr><td colspan="4">
				    <div class="comp_title" id="comp_title" width="100%">
						<ul style="width:100%" id="tabUl">
					      	<li class="ha" id="daxxTabId"><a href="javascript:void(0);" onclick="goDaxxInfo();return false;"><span>����������Ϣ</span></a></li>
							<li id="daclTabId"><a href="javascript:void(0);" onclick="goDaclInfo();return false;"><span>�����嵥��Ϣ</span></a></li>
					    </ul>
					</div></td></tr>
					</tbody>
					<tbody id="tbody_dajbxx">
						<tr><td align="left" colspan="4" ><span style="color:blue;">����ת����Ϣ</span></td></tr>
						<tr>
							<th width="18%">
								����ת��ʱ��
							</th>
							<td width="32%">
								${rs.dazrsj}
							</td>
							<th width="18%">
								����ת�뷽ʽ
							</th>
							<td width="32%">
								${rs.dazrfs}
							</td>
						</tr>
						<tr>
							<th >
								ת�뷽ʽ���
							</th>
							<td  colspan="3" >
								${rs.zrfsbh}
							</td>
						</tr>
						<tr><td align="left" colspan="4"><span style="color:blue">����ת����Ϣ</span></td></tr>
						<tr>
							<th >
								����ת��ʱ��
							</th>
							<td  >
								${rs.dazcsj }
							</td>
							<th >
								����ת����ʽ
							</th>
							<td >
								${rs.dazcfs }
							</td>
						</tr>
						<tr>
							<th >
								<logic:equal name="xxdm" value="12869">��Ҫ���</logic:equal>
							<logic:notEqual name="xxdm" value="12869">����ת�����</logic:notEqual>
							</th>
							<td  colspan="3" >
								${rs.zcfsbh }
							</td>
						</tr>
						<logic:equal name="sfxsbyqx" value="1" >
							<tr><td align="left" colspan="4"><span style="color:blue">��ҵȥ����Ϣ</span></td></tr>
							<tr>
								<th >
									��ҵȥ��
								</th>
								<td colspan="3">
									${rs.byqxmc }
								</td>
							</tr>
							<tr>
								<th >
									��λ����
								</th>
								<td >
									${rs.dwmc }
								</td> 
								<th >
									��λ�ʱ�
								</th>
								<td >
									${rs.dwyb }
								</td>
							</tr>
							<tr>
								<th >
									��λ��ַ
								</th>
								<td  colspan="3" >
									${rs.dwdz }
								</td>
							</tr>
							<tr>
								<th >
									��λ��ϵ��
								</th>
								<td >
									${rs.dwlxr }
								</td>
								<th >
									��λ��ϵ�绰
								</th>
								<td >
									${rs.dwlxdh }
								</td>
							</tr>
						</logic:equal>
						
						<logic:equal name="xxdm" value="12869">
						<tr><td align="left" colspan="4"><span style="color:blue">����Ͷ����Ϣ</span></td></tr>
						<tr>
							<th >
								����Ͷ�ݵ�λ
							</th>
							<td >
								${rs.dazjdw }
							</td>
							<th >
								����Ͷ���ʱ�
							</th>
							<td >
								${rs.dazjyb}
							</td>
						</tr>
						<tr>
							<th >
								����Ͷ�ݵ�ַ
							</th>
							<td   colspan="3">
								${rs.dazjdz }
							</td>
						</tr>
						<tr>
							<th >
								����Ͷ�ݵ�λ��ϵ��
							</th>
							<td >
								${rs.dazjdwlxr}
							</td>
							<th >
								����Ͷ�ݵ�λ�绰
							</th>
							<td >
								${rs.dazjdwdh}
							</td>
						</tr>
						</logic:equal>
						
						<logic:notEqual name="xxdm" value="12869">
						<tr><td align="left" colspan="4"><span style="color:blue">����ת����Ϣ</span></td></tr>
						<tr>
							<th >
								����ת�ĵ�λ
							</th>
							<td >
								${rs.dazjdw }
							</td>
							<th >
								����ת���ʱ�
							</th>
							<td >
								${rs.dazjyb}
							</td>
						</tr>
						<tr>
							<th >
								����ת�ĵ�ַ
							</th>
							<td   colspan="3">
								${rs.dazjdz }
							</td>
						</tr>
						<tr>
							<th >
								����ת�ĵ�λ��ϵ��
							</th>
							<td >
								${rs.dazjdwlxr}
							</td>
							<th >
								����ת�ĵ�λ�绰
							</th>
							<td >
								${rs.dazjdwdh}
							</td>
						</tr>
						</logic:notEqual>
						
						<tr><td align="left" colspan="4"><span style="color:blue">������Ϣ</span></td></tr>
						<tr>
							<th >
								��ע
							</th>
							<td   colspan="3">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<tbody id="tbody_daclxx" style="display:none;">
						<!-- ��ʾ��ά���ĵ����嵥ģ�� -->
						<logic:equal name="whzt" value="��ά��">
							<tr name="mbwclTr">
								<th width="18%">
									�����嵥ģ��
								</th>
								<td colspan="3">
									<font color="blue">${rs.daqdmb_mc}</font>
									<div style="float:right;">(<font color="blue">��ɫ����Ĳ���Ϊ�õ����嵥ģ���ڲ���</font>; <font color="black">��ɫ����Ϊģ�������</font>)</div>
								</td>
							</tr>
							<tr name="clTr">
								<td colspan="4" width="100%">
									<table id="clTable" width="100%" border="0">
										<tr width="100%" align="center">
											<td width="8%">˳��</td>
											<td width="17%">��������</td>
											<td width="14%">����</td>
											<td width="14%">ҳ��</td>
											<td width="17%">�鵵����</td>
											<td width="30%">��ע</td>
										</tr>
										<logic:notEmpty name="mbnclList">
											<logic:iterate name="mbnclList" id="a" indexId="index">
												<tr align="center">
												<html:hidden property="daqdcl_id"  value="${a.daqdcl_id }" />
												<td>${a.sx}</td>
												<td><font color="blue">${a.daqdcl_mc}</font></td>
												<td>${a.fs }</td>
												<td>${a.ys }</td>
												<td>${a.gdrq }</td>
												<td>${a.bz }</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
										<logic:notEmpty name="mbwclList">
											<logic:iterate name="mbwclList" id="b" indexId="index">
												<tr align="center">
												<html:hidden property="daqdcl_id"  value="${b.daqdcl_id }" />
												<td>${b.sx}</td>
												<td><font color="black">${b.daqdcl_mc}</font></td>
												<td>${b.fs }</td>
												<td>${b.ys }</td>
												<td>${b.gdrq }</td>
												<td>${b.bz }</td>
												</tr>
											</logic:iterate>
										</logic:notEmpty>
									</table>
								</td>
							  </tr>
						  </logic:equal>
						<logic:notEqual name="whzt" value="��ά��">
							<tr name="mbwclTr">
								<td colspan="4">
									<span style="padding-left:30px"><font color="blue">δά�������嵥</font></span>
								</td>
							</tr>
						</logic:notEqual>
					</tbody>				
				</table>
			</div>
			<table border="1" class="formlist" >	
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		
		</html:form>
	</body>
</html>
