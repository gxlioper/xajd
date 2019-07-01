<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		function searchRs(){
			allNotEmpThenGo('/xgxt/zhcpPdbx.do?method=pdbxCx&doType=searchGo');
		}
		
		function showPdbxQd(){
			var pkValue=curr_row.getElementsByTagName('input')[0].value;
			showTopWin("/xgxt/zhcpPdbx.do?method=pdbxQd&pkValue="+pkValue,800,600);
		}
		
		//�����˻�
		function plth(){
			var pk=document.getElementsByName("primary_key");
			var blog=false;
			for(i=0;i<pk.length;i++){
				if(pk[i].checked){
					blog=true;
				}
			}
			if(!blog){
				alert("����ѡ����Ҫ�˻صļ�¼��");
				return false;
			}
			
			if(confirm("ȷ��Ҫ��ѡ�еļ�¼�����˻ز�����")){
				refreshForm("/xgxt/zhcpPdbx.do?method=pdbxCx&doType=jsqr");
			}
		}
		
		function zfjs(){
			viewTempDiv("��ѡ����һ������","bjxzDiv",600,200);
		}
		
		
		function checkAllBox(){
			var checkArr=null
			
			if($("checkAll").checked){
				checkArr=true;
			}

			var xszdArr=document.getElementsByName("bjdmArr");
			for(i=0;i<xszdArr.length;i++){
				if(!xszdArr[i].disabled){
					xszdArr[i].checked=checkArr;
				}
			}
		}
		
		function pdbxfjs(){
			var bjdmArr=document.getElementsByName("bjdmArr");
			blog=false;
			for(i=0;i<bjdmArr.length;i++){
				if(bjdmArr[i].checked){
					blog=true;
					break;
				}
			}
			
			if(!blog){
				alert("��ѡ����Ҫ����ƽ���ֵİ༶��");
				return false;
			}
			refreshForm("/xgxt/zhcpPdbx.do?method=pdbxCx&doType=pdbxfjs");
		}
		
		</script>
	</head>
	<body>

		<html:form action="/zhcpPdbx" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			
			
			<div class="prompt" id="div_help" style="display: none">
					<h3>
						<span>��ʾ��</span>
					</h3>
					<p>
						�ڸ�ģ��������޷���ѯ������,�����ǽ�ʦȷ�Ϲ�����δ���š�
					</p>
					<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="zfjs();return false;" class="btn_zj">�ּܷ���</a></li>
						<li><a href="#" onclick="plth();return false;" class="btn_xg">�˻����� </a></li>	
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; 
						<logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty>  
						<logic:notEmpty name="rs">
							<font color="blue">˫��һ����¼���Բ鿴��ȷ��ѧ��������Ϣ��</font>
						</logic:notEmpty>
					</span>
				</h3>

				<logic:notEmpty name="rs">
				<div class="con_overlfow" >
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()" />
								</td>
								<logic:iterate id="tit" name="topTr"  offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										 nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="rs" id="s" indexId="index">
								<tr onclick="rowMoreClick(this,'',event);" ondblclick="showPdbxQd()" style="cursor:hand">
									<td>
										<input type="checkbox" name="primary_key" id="pkV"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v}</logic:iterate>" 
											<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>
											/>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					</div>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zhcpPdbxForm"></jsp:include>
				</logic:notEmpty>
			</div>
		</html:form>
		<!-- ��ʾ��Ϣ -->
			
		<div id="bjxzDiv" style="display:none">
			<div class="tab">
				<table class="formlist">
					<thead>
						<tr>
							<th colspan="7">
								<span> ѡ��༶ </span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<input type="checkbox" name="checkAll" id="checkAll" onclick="checkAllBox()">
							</th>
							<th>
								<div align="left">
								�༶����
								</div>
							</th>
							<th>
								<div align="left">
								�༶����
								</div>
							</th>
							<th>
								<div align="left">
								�ύ����
								</div>
							</th>
							<th>
								<div align="left">
								δ�ύ����
								</div>
							</th>
							<th>
								<div align="left">
								ȷ������
								</div>
							</th>
							<th>
								<div align="left">
								δȷ������
								</div>
							</th>
						</tr>
						<logic:iterate id="pdbxf" name="pdbxfTj" indexId="index">
						<tr>
							<td>
								<input type="checkbox" name="bjdmArr" id="bjdm_${index }" value='${pdbxf.bjdm }'>
							</td>
							<td>
								${pdbxf.bjmc }
							</td>
							<td>
								${pdbxf.bjrs }
							</td>
							<td>
								${pdbxf.tjrs }
							</td>
							<td>
								${pdbxf.wtjrs }
							</td>
							<td>
								${pdbxf.qrrs }
							</td>
							<td>
								${pdbxf.wqrrs }
							</td>
						</tr>
						</logic:iterate>
					</tbody>
					<tfoot>
						<tr>
							<td  colspan="7">
								<div class="btn">
									<!-- ȷ�� -->
									<button type="button" onclick="pdbxfjs()">
										<bean:message key="lable.btn_qd_space" />
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
		<logic:present name="message">
			<script>
				alert("${message}!");	
			</script>
		</logic:present>
	</body>
</html>
