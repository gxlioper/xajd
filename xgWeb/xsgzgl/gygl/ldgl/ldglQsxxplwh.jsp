<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script language="javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="dwr/interface/systemFunction.js"></script>

		<script type="text/javascript">
		function save(){
			var url="gyglnew_ldgl.do?method=ldglQsxxplwh&doType=save";
			refreshForm(url);
		}

		//�������Ҵ�λ���޸�
		function plqscws(obj){
			var tab=$("ldqstable");//table
			var trs=tab.getElementsByTagName("TR");
			if(trs&&trs.length>1){
				for(var i=1;i<trs.length;i++){
					var input=trs[i].cells[obj.parentNode.cellIndex].getElementsByTagName("INPUT");
					if(input&&input.length==3){
						input[2].value=obj.value;
					}
				}
			}
		}

		//���������Ա��޸�
		function plqsxb(obj){
			var tab=$("ldqstable");//table
			var trs=tab.getElementsByTagName("TR");
			if(trs&&trs.length>1&&obj.value!=""){
				for(var i=1;i<trs.length;i++){
					var input=trs[i].cells[obj.parentNode.cellIndex].getElementsByTagName("SELECT");
					if(input&&input.length==1){
						input[0].value=obj.value;
					}
				}
			}
		}
		</script>
	</head>
	<body>
		<div class="prompt" >
	       <h3><span>��ʾ���ô��Ĺ��ܽ���¥������ʱʹ�ã������ֹ���������ʹ�øù��ܣ������п��ܻὫ�ѵ��������ݴ����</span></h3>
	       <p></p>
	   	</div>
		<!-- From���� start-->
		<form action="gyglnew_ldgl.do?method=ldglQsxxplwh" method="post">
		<input type="hidden" name="lddm" value="${gyglnewLdglForm.lddm}" />
		<!---------------------���----��������ʾ----------------->
		<div class="formbox">
			<table id="ldqstable" class="table_01" width="98%">
				<tr><td></td>
				<%
					int max_qss=Integer.parseInt((String)request.getAttribute("max_qss"));
					for(int i=0;i<max_qss;i++){
						%>
						<td align="center">
							<logic:equal value="��ס" name="ldxx" property="ldxb">
								<select style="width: 40px;" onchange="plqsxb(this)">
									<option value=""></option>
									<option value="��">��</option>
									<option value="Ů">Ů</option>
								</select>
							</logic:equal>
							<input type="text" style="width: 15px;" maxlength="2" onkeyup="checkInputData(this);plqscws(this);"/>��λ
						</td>
						<%						
					}
				%>
				</tr>
				<logic:iterate id="ch_list" name="ldchqs_list">
					<tr style="height: 30px;">
						<% int ch_qs_count=0;  %>
						<logic:iterate id="qs" name="ch_list" indexId="qs_index">
							<%ch_qs_count++; %>
							<logic:equal value="0" name="qs_index">
								<td>
									<nobr>${qs.chmc}��</nobr>
								</td>
							</logic:equal>
							<td align="center"><nobr>
								<input type="hidden" name="qsh_old_s" value="${qs.qsh}"/>
								<!-- ����ʵ��Ӧ�ã���ʱ�������޸����ҺŹ��ܣ���˽�qsh_new_s���أ���ʱ���� -->
								<input type="hidden" name="qsh_new_s" value="${qs.qsh}" maxlength="10" style="width: 50px;"/>
								<font color="red">${qs.qsh}</font><br/>
								<logic:equal value="��ס" name="ldxx" property="ldxb">
								<select name="xb_s" style="width: 40px;">
									<logic:equal value="��" name="qs" property="qsxb">
									<option value="��" selected="selected">��</option>
									<option value="Ů">Ů</option>
									</logic:equal>
									<logic:equal value="Ů" name="qs" property="qsxb">
									<option value="��">��</option>
									<option value="Ů" selected="selected">Ů</option>
									</logic:equal>
								</select>
								</logic:equal>
								<input type="text" name="cws_s" value="${qs.cws}" maxlength="2" style="width: 15px;" onkeyup="checkInputData(this);"/>��λ
								</nobr>
							</td>
						</logic:iterate>
						<%for(int i=ch_qs_count;i<max_qss;i++){ %>
							<td></td>
						<%} %>
					</tr>
				</logic:iterate>
			</table>
			
			<table width="100%" border="0" class="formlist">					
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									<font color="blue">
										ע����"<font color="red">*</font>"����Ϣ����¼��
									</font>
								</div>
								<div class="btn">
									<button type="button" class="button2" id="btn_bc"  onclick="save();return false;">
										�� ��
									</button>
									<button type="button" class="button2"  onclick="Close();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			
		</div>
		
		</form>
	</body>
</html>
