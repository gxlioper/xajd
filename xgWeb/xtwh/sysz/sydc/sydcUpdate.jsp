<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/pclUtil.js"></script>
		<script language="javascript">
		
		//��ҳ����
		function saveSydc(){
			
			if($("dcnr") && $("dcnr").value==""){
				alertInfo("�������ݲ���Ϊ��!",function(){});
				return false;
			}
			
			if($("sfqy") && $("sfqy").value==""){
				alertInfo("�Ƿ����ò���Ϊ��!",function(){});
				return false;
			}
			
			var xxnr=document.getElementsByName("xxnr");
			var flag=false;
			for(i=0;i<xxnr.length;i++){
				if(xxnr[i].value!=""){
					flag=true;
				}
			}
			
			if(!flag){
				alertInfo("ѡ�����ݲ���Ϊ��!",function(){});
				return false;
			}
			
			var msg = "ȷ�ϱ�����Ϣ��";
			confirmInfo(msg,function(t) {
				saveUpdate('/xgxt/xtwhSysz.do?method=sydcUpdate&doType=save','dcnr');
			});
		}
		
		//��ʾ
		function showSydc(){
			
			var doType = $("doType").value;
			
			if(doType != ""){
				var tableName = "xg_xtwh_sydcxxb";
				var pk = "dcid";
				var pkValue = $("dcid").value;
				var query = " order by xxid "
				var tbId = "nr";
				var btnId = "nrAdd";
				var lx = "xtwh_sysz_sydc";
				
				setNr(tableName,pk,pkValue,query,tbId,btnId,lx);
			}else{
				$("nrAdd").value = "3";
				trAdd('nr','madd','nrAdd','xtwh_sysz_sydc');
			}
		}
		</script> 
	</head>
	<body onload="setFocus('dcnr');showSydc()">
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a><bean:write name="title" /></a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/xtwhSysz">
			<!-- ������ -->	
			<input type="hidden" name="dcid" id="dcid" value="${rs.dcid }"/>
			<input type="hidden" name="gd_num" id="gd_num" value="3"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			<div class="tab">		
			<!-- ��Ŀ������� -->
			<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>��ҳ������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr style="">
						<th align="right" width="20%">
							<font color="red">*</font>��������<br/><font color="red">(��100��)</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" styleId="dcnr" property="dcnr" rows="5" onblur="chLeng(this,100)" style="word-break:break-all;width:90%"></html:textarea>
						</td>
					</tr>
					<tr style="height: 23px">
						<th align="right">
							��ע<br/><font color="red">(��250��)</font>
						</th>
						<td align="left" colspan="3">
							<html:textarea name="rs" property="bz" rows="5" onblur="chLeng(this,250)" style="word-break:break-all;width:90%"></html:textarea>
						</td>					
					</tr>
					<tr style="height: 23px">
						<th align="right">
							<font color="red">*</font>�Ƿ�����
						</th>
						<td align="left" colspan="3">
							<logic:iterate name="sfqyList" id="sfqy">
								<html:radio name="rs" property="sfqy" value="${sfqy.en }">${sfqy.cn }</html:radio>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</logic:iterate>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">ע��������������õĻ����������齫���ر�</font>
						</td>					
					</tr>
				</tbody>
				<!-- ����ѡ�� -->
				<thead>
					<tr>
						<th colspan="4">
							<span>����ѡ�������ά��3��ѡ��,����Ϊ�գ�</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td colspan="4">	
							<input  value="+" onclick="if(count > 3){alert('���ֻ��ά��3��ѡ��');return false}else{trAdd('nr','add','nrAdd','xtwh_sysz_sydc')};" style="width: 20px;display: none"/>
							<input  value="-" onclick="trDel('nr','delRow')" style="width: 20px;display: none"/>
<%--							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ����&nbsp;&nbsp;--%>
							<input type="text" name="nrAdd" id="nrAdd" style="width: 20px;display: none" onblur="if(this.value > 3){alert('���ֻ��ά��3��ѡ��');return false}else{trAdd('nr','madd','nrAdd','xtwh_sysz_sydc')}"/>
<%--							&nbsp;�� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ���&nbsp;&nbsp;--%>
							<input type="text" name="nrDel" id="nrDel" style="width: 20px;display: none" onblur="trDelAll('nr','nrDel')"/>
<%--							&nbsp;��&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;--%>
							
							<table class="formlist" align="center" width="100%" id="tTb">
								<tr>
									<td>
										<div class="mid_box">
											<table align="center" style="width: 100%" id="rsT" class="tbstyle">
												<thead style="height: 23px">
													<tr>
														<td align="center" nowrap="nowrap" style='width:5%'>
															ѡ��
														</td>
														<td align="center" nowrap="nowrap" style=''>
															ѡ�����ݣ���50�֣�
														</td>
													</tr>		
												<tbody width="100%" class="tbstyle" id="nr">
												
												</tbody>	
											</table>
										</div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">"<span class="red">*</span>"Ϊ������</div>
							<div class="btn">
								<!-- �ǲ鿴 -->
								<logic:notEqual name="doType" value="view">
									<button type="button" onclick="saveSydc()" id="buttonSave">
										�� ��
									</button>
								</logic:notEqual>
								<button type="button" onclick="window.close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			</div>
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>