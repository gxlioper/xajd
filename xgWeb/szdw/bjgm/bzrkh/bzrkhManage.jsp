<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript">
		//��ѯ�����
		function searchRs(){
			allNotEmpThenGo('bjgm_fdykh_bzrkh.do?method=bzrkhManage');
		}

		//ɾ��batchData(checkname,url,type)
		function del(){
			var url="bjgm_fdykh_bzrkhDelete.do";
			var checkname="primarykey_checkVal";
			var type="del";
			batchData(checkname,url,type);
		}


		//�鿴�����ο�����Ϣ
		function viewBzrkh(){
			var ids;
			if(curr_row != null){
				ids=curr_row.getElementsByTagName('input')[0].value;
			}else{
				alertInfo('��ѡ��Ҫ�����������У�');
				return false;
			}
			var url="bjgm_fdykh_bzrkhView.do?method=bzrkhView&pkValue="+ids;
			showTopWin(url,750,600);
		}
		</script>
	</head>
	<body>
		<html:form action="/bjgm_fdykh" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="ids" id="ids"/>
			<!-- ������ -->
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>			
			<!-- ���� end-->
			<!-- ��ʾ��Ϣ START-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					���˵÷�=����+����+�꼶+ѧ����<br />
					�༶�÷�=������ȫ+�屨+����+����+����+����+�μ��(ע:��ǰ���������а༶�ܷ�)<br />
					�����ܷ�=��ǰ���������а༶�÷�֮��/�༶��+���˵÷�
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->	
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="showTopWin('bjgm_fdykh_bzrkhAdd.do?method=bzrkhAdd',750,600);return false;" class="btn_zj"> ���� </a></li>
						<li><a href="#" onclick="modi('bjgm_fdykh_bzrkhEdit.do?method=bzrkhEdit',750,600);return false;" class="btn_xg"> �޸� </a></li>
						<li><a href="#" onclick="del();return false;" class="btn_sc"> ɾ�� </a></li>
						<li><a href="#" onclick="viewBzrkh();return false;" class="btn_ck"> �鿴 </a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">����</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">����</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">�����ֶ�ȷ��</a></li>
					</ul>
				</div>
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������;˫����λ��ʾ��λ������Ϣ</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort(this)">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate id="r" name="rs" offset="0">
									<tr onclick="rowOnClick(this);" style="cursor:hand" >
										<td>
											<input type="checkbox" name="primarykey_checkVal" id="pkV"
											value="${r.xn};${r.xq};${r.zgh}" />
										</td>
										<td>
											${r.xn}
										</td>
										<td>
											<logic:present name="r">
												<logic:equal name="r" property="xq" value="01">
													��
												</logic:equal>
												<logic:equal name="r" property="xq" value="02">
													��
												</logic:equal>
											</logic:present>
										</td>
										<td style="width:25%;">
											${r.bmmc}
										</td>
										<td>
											${r.zgh}
										</td>
										<td>
											${r.xm}	
										</td>
										<td>
											${r.cq}	
										</td>
										<td>
											${r.cl}	
										</td>
										<td>
											${r.nj}	
										</td>
										<td>
											${r.xsk}	
										</td>
										<td>
											${r.grdf}	
										</td>
										<td>
											${r.bjdf}	
										</td>
										<td>
											${r.grzf}	
										</td>
									</tr>
								</logic:iterate>
							</logic:notEmpty>
						</tbody>
				</table>
				<logic:present name="message">
					<script>
						alertInfo('${message}');
					</script>
				</logic:present>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjgmBzrkhForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</html:form>
	</body>
</html>
