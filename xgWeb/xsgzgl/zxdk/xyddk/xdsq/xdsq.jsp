<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/xyddk/xdsq/js/xdsq.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function saveXdsq(url){
				if (jQuery("#xdly").val() == ""){
					showAlertDivLayer("�뽫��������д������");
					return false;
				}
				/*�������������ж�*/
				if(jQuery("#xdly").val().length > 400){
					showAlertDivLayer("�������ɲ��ܳ���400�֣�");
					return false;
				}
				
				ajaxSubFormWithFun("HsdxdForm",url,function(data){
					if (data["message"] == "����ɹ���") {
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							document.location.href=document.location;
						}});
					}else{
						showAlertDivLayer(data["message"]);
						return false;
					}
				});
			}
			
			function showXdsq(obj){
				var parentObj = jQuery(obj).parent().parent();
				var xdxn = jQuery(parentObj).find("[name='xn']").val();
				var xdje = jQuery(parentObj).find("[name='dkze']").val();
				var id = jQuery(parentObj).find("[name='jgid']").val();
				var htbh = jQuery(parentObj).find("[name='htbh']").text();
				jQuery("#addxn").text(xdxn);
				jQuery("#xdxn").val(xdxn);
				jQuery("#id").val(id);
				jQuery("#addxdje").text(xdje)
				jQuery("#xdje").val(xdje);
				jQuery("#htbh").val(htbh);
				jQuery("#xdsqTable").css("display","");
			}
			
		</script>
		<style type="text/css">
			.hiddenfont{width:155px;height:15px;display:block;white-space: nowrap;overflow: hidden;text-overflow: ellipsis;}
		</style>
	</head>
	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		
		<html:form action="/gjdk_xdsqnew" method="post" styleId="HsdxdForm" onsubmit="return false;">
			<%-- 
			<html:hidden property="splc" value="${splc}"/>
			--%>
			<div>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ<font color='blue'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(��λ:Ԫ)</font></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${dkxx.dkqs}
							</td>
							<th width="16%">
								��������(��)
							</th>
							<td width="34%">
								${dkxx.dkqx}
							</td>
						</tr>
						<tr>
							<th>
								�����ܽ��
							</th>
							<td>
								${dkxx.dkje}
							</td>
							<th>
								�ۼƷŴ����
							</th>
							<td>
								${fkzh}
							</td>
						</tr>
							<tr>
							<td colspan="4">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;" width = "10%">ѧ��</th>
										<th style="text-align: center;" width = "12%">ס�޷Ѵ����</th>
										<th style="text-align: center;" width = "10%">ѧ�Ѵ����</th>
										<th style="text-align: center;" width = "12%">����Ѵ����</th>
										<th style="text-align: center;" width = "14%">��ס�޷�Ӧ�ɶ�</th>
										<th style="text-align: center;" width = "12%">��ѧ��Ӧ�ɶ�</th>
										<th style="text-align: center;" width = "10%">��ͬ���</th>
										<th style="text-align: center;" width = "10%">�ſ�״̬</th>
										<th style="text-align: center;" width = "10%">����״̬</th>
									</tr>
									<logic:iterate id="x" name="xdxxList">
										<tr>
											<td>
												${x.xn}
												<input type="hidden" name="jgid" value="${x.jgid}"/>
												<input type="hidden" name="dkze" value="${x.dkze}"/>
												<input type="hidden" name="xn" value="${x.xn}"/>
											</td>
											<td>${x.nzsfdk}</td>
											<td>${x.nxfdk}
											</td>
											<td>
												${x.nshfdk}
											</td>
											<td>
												${x.nzsfyje}
											</td>
											<td>
												${x.nxfyje}
											</td>
											<td name="htbh">${x.htbh}</td>
											<td>
												${x.fkztmc}
											</td>
											<td>
												<logic:equal value="czan" name="x" property="cznr">
													<button type="button" style="width:50px" onclick="showXdsq(this);return false;">����</button>
				                                    <button type="button" style="width:50px;margin-top:5px" onclick="qxsq(this);return false;">����</button>	
												</logic:equal>
												<logic:notEqual value="czan" name="x" property="cznr">
													${x.cznr}
												</logic:notEqual>
											</td>
										</tr>
									</logic:iterate>
								</table>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span> 
								  <button type="button" style="margin-top:2px;margin-left:30px" onclick="update();return false;">�޸�</button>
				                  <button type="button"  onclick="submitBusi();return false;">�ύ</button>
				                  <button type="button" onclick="cancel();return false;">����</button>
				                  <button type="button" onclick="del();return false;">ɾ��</button>
				                  <button type="button" onclick="lcgz();return false;">���̸���</button>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" width="100%">
								<table style="width:100%;text-align: center;">
									<tr>
										<th style="text-align: center;"><input type="checkbox" name="selectAll" onclick="selectAll(this)" width="5%" /></th>
										<th style="text-align: center;" width = "19%">��ͬ���</th>
										<th style="text-align: center;" width = "19%">����ѧ��</th>
										<th style="text-align: center;" width = "19%">�������(Ԫ)</th>
										<th style="text-align: center;" width = "19%">��������</th>
										<th style="text-align: center;" width = "19%">���״̬</th>
									</tr>
									<logic:iterate id="x" name="xdsqList">
										<tr>
											<td><input type="checkbox" name="sqid" value="${x.id}"  />
												<input name="shzt" type="hidden" value="${x.shzt}"/>
												<input type="hidden" name="splc" value="${x.splc }"/>
											</td>
											<td>${x.htbh}</td>
											<td>${x.xdxn}<input name="xn" type="hidden" value="${x.xdxn}"/></td>
											<td>${x.xdje}<input name="xdjes" type="hidden" value="${x.xdje}"/></td>
											<td id="xdlyss">${x.xdly}</td>
											<td>
												${x.shztmc}
											</td>
										</tr>
									</logic:iterate>
									<logic:empty name="xdsqList">
										<tr>
											<td colspan="6">
												�����������¼��
											</td>
										</tr>
									</logic:empty>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table class="formlist"  style="display:none;" id="xdsqTable">
					<thead>
						<tr>
							<th colspan="4">
								<span>����ȷ��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">ѧ��</th>
							<td colspan="3" width="84%">
								<label id="addxn"></label>
								<input type="hidden" name="xdxn" id="xdxn"/>
								<input type="hidden" name="id" id="id"/>
								<input type="hidden" name="htbh" id="htbh"/>
							</td>
						</tr>
						<tr>
							<th width="16%">�������(Ԫ)</th>
							<td colspan="3" width="84%">
								<label id="addxdje"></label>
								<input type="hidden" name="xdje" id='xdje'/>
							</td>
						</tr>
						<tr>
							<th width="16%"><font color="red">*</font>��������
								<br/><font color="red">(������400��)</font>
							</th>
							<td colspan="3" width="84%">
								<html:textarea property="xdly" styleId="xdly" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveXdsq('zxdkDkjg.do?method=saveXdsq&type=save');">
										����ݸ�
									</button>
									<button type="button" onclick="saveXdsq('zxdkDkjg.do?method=saveXdsq&type=submit');">
										�ύ
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<table class="formlist"  style="display:none;" id="xdsqTable2">
					<thead>
						<tr>
							<th colspan="4">
								<span>����ȷ��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">ѧ��</th>
							<td colspan="3" width="84%">
								<label id="updatexdxn"></label>
							   <input type="hidden" name="id2"  id="id2"/>
								
							</td>
						</tr>
						<tr>
							<th width="16%">�������(Ԫ)</th>
							<td colspan="3" width="84%">
								<label id = "updatexdje" ></label>
							</td>
						</tr>
						<tr>
							<th width="16%"><font color="red">*</font>��������
								<br/><font color="red">(������400��)</font>
							</th>
							<td colspan="3" width="84%">
								<textarea name="xdlys" id="xdlys" 
											   onblur="checkLen(this,400);"
											   style="width:99%;" rows="4"/>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveXdsq2('update');">
										����ݸ�
									</button>
									<button type="button" onclick="saveXdsq2('updatesubmit');">
										�ύ
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