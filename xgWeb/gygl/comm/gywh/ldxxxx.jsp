<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<jsp:directive.page import="xgxt.action.Base" />
		<jsp:directive.page import="java.util.HashMap" />
		<jsp:directive.page import="java.util.List" />
		<jsp:directive.page import="java.util.ArrayList" />
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/gyglGywh.js"></script>
		<script>
			//��ʾ¥����������Ϣ 
			function showTbody(obj,objTbody){
				if(obj.className=="up"){
					obj.className="down";
					obj.parentNode.parentNode.className="";
					document.getElementById(objTbody).style.display="";
					
				}else if(obj.className=="down"){
					obj.className="up";
					obj.parentNode.parentNode.className="cur-tr";
					document.getElementById(objTbody).style.display="none";
				}
			}
			
			//��ʾ������
			function showhid(num){
				var lddm=document.getElementById("lddm_"+num).value;
				var cs=document.getElementById("cs_"+num).value;
				var qsh=document.getElementById("qsh_"+num).value;
				var strArr=[lddm,cs,qsh];
				gyglGywh.getRzryList(strArr,function(data){
					var html="";
					for(i=0;i<data.length;i++){
						var cwh=data[i].cwh;
						var xm=data[i].xm;
						html+="<li><span>"+cwh+"�Ŵ�λ��"+xm+"</span></li>";
					}
					if(data.length==0){
						html+="<li><span>������û��ѧ����ס</span></li>"
					}
					$("ul_"+num).innerHTML=html;
				});
			}
			
			function fhldxxwh(){
				refreshForm("/xgxt/gygl_gywh_ldwh.do");
			}
			</script>
		<script src="../../js/ie6comm.js"></script>
		<script> 
			DD_belatedPNG.fix('img,.mainbody,.topframe,.mainframe,.botframe,.menu,.type_mainframe'); 
			</script>
	</head>
	<body>
		<html:form action="/gyglGywh" method="post">
			<input type="hidden" name="czxq" id="czxq" value="${czxq }" />
			<input type="hidden" name="yqdmHid" id="yqdmHid" value="${rs.yqdm}" />
			<input type="hidden" name="xqdmHid" id="xqdmHid" value="${rs.xqdm}" />
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			<div class="tab">
				
				<table width="100%" border="0" class="formlist">
					<!-- ¥����Ϣ -->
					<thead>
						<tr>
							<th colspan="6">
								<span><bean:message key="lable.ld" />��Ϣ</span>
								<div class="btn">
									<button onclick='fhldxxwh()'>
										�� ��
									</button>
								</div>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="6">
								<font color="blue">&nbsp;&nbsp;&nbsp;&nbsp; <bean:message
										key="lable.ld" />����:${ldxx.lddm}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

									<bean:message key="lable.ld" />����:${ldxx.ldmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<logic:equal name="czxq" value="��">
								����<bean:message key="lable.xiaoqu" />:${ldxx.xqmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal> <logic:equal name="czyq" value="��">
								����<bean:message key="lable.yuanqu" />:${ldxx.yqmc}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								</logic:equal> ����:${ldxx.cs}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

									�Ա�����:${ldxx.xbxd} </font>
							</td>
						</tr>
					</tbody>
					<thead class="thead_title">
						<tr>
							<td colspan="6">
								<span><bean:message key="lable.qs" />��Ϣ</span>
							</td>
						</tr>
					</thead>
					<%
							List<HashMap<String, String>> qsxxList = (List<HashMap<String, String>>) request
							.getAttribute("qsxxList");
							List<HashMap<String, String>> ldlcxxList = (List<HashMap<String, String>>) request
							.getAttribute("ldlcxxList");
							int num = 0;
							for (int i = 0; i < ldlcxxList.size(); i++) {
								HashMap<String, String> ldlcMap = ldlcxxList.get(i);
					%>
					<!-- ¥�� ��ͷ -->
					<thead>
						<tr>
							<th style="width:16.5%">
								<a href="#" class="up"
									onclick="showTbody(this,'lc_<%=ldlcMap.get("cs")%>');return false"><%=ldlcMap.get("cs")%>¥</a>
							</th>
							<th colspan="5">
								<em class="chamber">���ң�������<%=Base.isNull(ldlcMap.get("qss")) ? "0"
							: ldlcMap.get("qss")%>
								</em>
								<em class="chamber">�ѷ��䣨������<%=Base.isNull(ldlcMap.get("yfps")) ? "0"
							: ldlcMap.get("yfps")%>
								</em>
								<em class="chamber">����ס���ˣ���<%=Base.isNull(ldlcMap.get("rzrs")) ? "0"
							: ldlcMap.get("rzrs")%>
								</em>
							</th>
						</tr>
					</thead>
					<!-- ¥�� ��ͷ end-->

					<!-- ¥�� ���� -->
					<tbody id='lc_<%=ldlcMap.get("cs")%>' style="display:none;">
						<%
									//¥��������
									int qss = 0;
									if (ldlcMap.get("qss") != null) {
								qss = Integer.parseInt(ldlcMap.get("qss"));
									}

									//��¥������������Ϣ
									int len = qsxxList.size();
									int m = 1;
									//��������Ϣ���м���
									int n = 0;
									ArrayList<HashMap<String, String>> qsxx = new ArrayList<HashMap<String, String>>();
									for (int j = 1; j <= len; j++) {
								HashMap<String, String> qsxxMap = qsxxList.get(j - 1);
								if (ldlcMap.get("cs").equalsIgnoreCase(
										qsxxMap.get("cs"))) {
									//����ÿ��������Ϣ
									qsxx.add(qsxxMap);
									n++;
								}

								//��һ����������¼ʱ
								if (n == 6) {
									//��N��0���¼���
									n = 0;
						%>
						<!-- ������еļ�¼�����Һţ� -->
						<tr>
							<%
											for (int k = 0; k < qsxx.size(); k++) {
											HashMap<String, String> qsMap = qsxx.get(k);
							%>
							<td style="width:16.5%">
								<span class="num"> <%=qsMap.get("qsh")%> </span>
							</td>
							<%
							}
							%>
						</tr>
						<!-- ������еļ�¼�����Һţ� end-->

						<!-- ������еļ�¼��������ϸ��Ϣ�� -->
						<tr>
							<%
											for (int k = 0; k < qsxx.size(); k++) {
											HashMap<String, String> qsMap = qsxx.get(k);
							%>
							<td>
								<input type="hidden" name="lddmArr" id='lddm_<%=num%>'
									value='<%=qsMap.get("lddm")%>' />
								<input type="hidden" name="csArr" id='cs_<%=num%>'
									value='<%=qsMap.get("cs")%>' />
								<input type="hidden" name="qshArr" id='qsh_<%=num%>'
									value='<%=qsMap.get("qsh")%>' />
								<span align="center" title='<%=qsMap.get("fpbm")%>'>(<%=(qsMap.get("fpbm")).length() > 6 ? qsMap
												.get("fpbm").substring(0, 6)
												+ "..."
												: qsMap.get("fpbm")%>)</span>
								<p>
									��λ����
									<%=qsMap.get("cws")%>
									<br />

									��ס������
									<%=qsMap.get("rzrs")%>
									��
									<br />
								</p>
								<div style="position:relative;z-index:0;"
									onmouseover="javascript:document.getElementById('helpcon_<%=num%>').style.display='block'"
									onmouseout="javascript:document.getElementById('helpcon_<%=num%>').style.display='none'">
									<a href="#" class="check"
										onmouseover="showhid('<%=num%>');return false;"
										onclick="return false">�鿴��Ա </a>
									<div class="check_people" id="helpcon_<%=num%>"
										style="display:none;">
										<ul id="ul_<%=num%>">

										</ul>
									</div>

								</div>
							</td>
							<%
										num++;
										}
							%>
						</tr>
						<!-- ������еļ�¼��������ϸ��Ϣ end�� -->
						<%
									//���¹���������ϢLIST
									qsxx = new ArrayList<HashMap<String, String>>();

								}
									}

									//�����¥������������ܳ���6��˵�����ж���������Ϣδ�����
									if (qss % 6 != 0) {
						%>
						<tr>
							<%
										for (int k = 0; k < qsxx.size(); k++) {
										HashMap<String, String> qsMap = qsxx.get(k);
							%>
							<!-- ������еļ�¼�����Һţ� -->
							<td style="width:16.5%">
								<span class="num"> <%=qsMap.get("qsh")%> </span>
							</td>
							<!-- ������еļ�¼�����Һţ�end -->

							<%
									}
									//������
									for (int z = 1; z <= 6 - n; z++) {
							%>
							<td></td>
							<%
							}
							%>
						</tr>

						<tr>
							<%
										for (int k = 0; k < qsxx.size(); k++) {
										HashMap<String, String> qsMap = qsxx.get(k);
							%>

							<td>
								<input type="hidden" name="lddmArr" id='lddm_<%=num%>'
									value='<%=qsMap.get("lddm")%>' />
								<input type="hidden" name="csArr" id='cs_<%=num%>'
									value='<%=qsMap.get("cs")%>' />
								<input type="hidden" name="qshArr" id='qsh_<%=num%>'
									value='<%=qsMap.get("qsh")%>' />
								<span align="center" title='<%=qsMap.get("fpbm")%>'>(<%=(qsMap.get("fpbm")).length() > 6 ? qsMap
											.get("fpbm").substring(0, 6)
											+ "..."
											: qsMap.get("fpbm")%>)</span>
								<p>
									��λ����
									<%=qsMap.get("cws")%>
									<br />

									��ס������
									<%=qsMap.get("rzrs")%>
									��
									<br />
								</p>
								<div style="position:relative;z-index:0;"
									onmouseover="javascript:document.getElementById('helpcon_<%=num%>').style.display='block'"
									onmouseout="javascript:document.getElementById('helpcon_<%=num%>').style.display='none'">
									<a href="#" class="check"
										onmouseover="showhid('<%=num%>');return false;"
										onclick="return false">�鿴��Ա
										<div class="check_people" id="helpcon_<%=num%>"
											style="display:none;">
											<ul id="ul_<%=num%>">

											</ul>
										</div> </a>
								</div>
							</td>

							<%
									num++;
									}
									//������
									for (int z = 1; z <= 6 - n; z++) {
							%>
							<td></td>
							<%
							}
							%>
						</tr>
						<%
						}
						%>
					</tbody>
					<!-- ¥�� ���� end-->
					<%
					}
					%>
				</table>
			</div>


			<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alert("����ʧ�ܣ�");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alert("�����ɹ���");
					</script>
				</logic:equal>
				<script language="javascript">			
					if(window.dialogArguments){
						if(window.dialogArguments.document.getElementById("search_go")){
							dialogArgumentsQueryChick();
						}
						window.close();
					}
				</script>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
