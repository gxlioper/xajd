<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxsq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//Ϊbuttonע���¼�
				//jQuery("#but_save").click(function(){save("fdypxxmSq")});
				//jQuery("#but_close").click();
				//jQuery("#xzpxxm").load("/xgxt/xsgzgl/szdw/fdypx/xmwh/fdypxxmxzView.jsp");
				jQuery("#xzpx").click(xzpxxm);
			});
			function go_list(){
				refreshForm("szdw_fdypxxmsq.do?method=fdypxxmsqList");
			}

			function savePxsq(obj){
				if(yanzheng()){
					
					jQuery.post("szdw_fdypxxmsq.do?method=yzFdypxsq",{xmdm:jQuery("#xmdm").val()},function(data){
						if(data.message!="true"){
							alertInfo(data.message);
						}else{
							//��֤�ɹ�����ܽ��б���
							if(obj == "submit"){
								url = "szdw_fdypxxmsq.do?method=fdypxxmSq&type=submit";
							}else{
								url = "szdw_fdypxxmsq.do?method=fdypxxmSq&type=save";
							}
							ajaxSubFormWithFun("demoForm",url,function(data){
								showAlertDivLayer(data["message"],{},{"clkFun":function(){
										if (parent.window){
											refershParent();
										}
								}});
							});
						}
					},'json');
					
				}
			}
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl" method="post" styleId="demoForm">
			<div style='tab;width:100%;height:465px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								ְ����
							</th>
							<td width="34%">
							${rs.zgh }
							</td>
							<th width="16%">
								ר��ְ
							</th>
							<td width="34%" >
								${rs.zjz }
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
							${rs.xm}
							</td>
							<th width="16%">
								�Ա�
							</th>
							<td width="34%" >
								${rs.xbs }
							</td>
						</tr>
						<tr>
							<th width="16%">
								����
							</th>
							<td width="34%">
							${rs.mzmc}
							</td>
							<th width="16%">
								������ò
							</th>
							<td width="34%" >
								${rs.zzmmmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ϵ�绰
							</th>
							<td width="34%">
							${rs.yddh}
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%" >
								${rs.dzyx}
							</td>
						</tr>
						<tr>
							<th>
								��ͥ��ַ
							</th>
							<td colspan="3">
							${rs.jtzz}
							</td>
						</tr>
						<tr>
							<th width="16%">
								˼������ʱ��
							</th>
							<td width="34%">
							${rs.szgzsj}
							</td>
							<th width="16%">
								��У����ʱ��
							</th>
							<td width="34%" >
								${rs.lxgzsj}
							</td>
						</tr>
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
							${rs.xl}
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%" >
								${rs.sxzy}
							</td>
						</tr>
						<tr>
							<th>
								��ҵԺУ
							</th>
							<td colspan="3">
							${rs.byyx}
							</td>
						</tr>
						
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����Ա��ѵ��Ŀ����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx2">
						<tr>
							<td colspan="4"><button class="btn_common" id="xzpx" type="button">ѡ����ѵ��Ŀ</button></td>
						</tr>
						<tr>
							<td colspan="4" id="xzpxxm">
						
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
					<tbody id="tbody_jbxxs">
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="84%" colspan="3">
								<html:textarea property="sqly" styleId="sqly" name="model" style="width: 98%;height: 50px;" ></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="savePxsq('save');return false;" >
										����ݸ�
									</button>
									&nbsp;&nbsp;
									<button type="button" type="button" onclick="savePxsq('submit');return false;" >
										�ύ����
									</button>
									&nbsp;&nbsp;
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
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

