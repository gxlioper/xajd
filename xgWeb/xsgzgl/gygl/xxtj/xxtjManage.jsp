<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/tabMerge.js"></script>
		<script language="javascript">
		function modi(url,h,w){
			
			if(curr_row != null&&""!=curr_row.getElementsByTagName('input')[0].value){
				//showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				showDialog('�鿴¥����ϸ��Ϣ', h, w, url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
				return true;
			}else{
				alertInfo('��ѡ����˻������һ�����ݣ�');
				return false;
			}
		}

		function exp(url){
			
			if(curr_row != null&&""!=curr_row.getElementsByTagName('input')[0].value){
				document.forms[0].action=url + '&lddm='+curr_row.getElementsByTagName('input')[0].value;
				document.forms[0].submit();
			}else{
				alertInfo('��ѡ����˻������һ�����ݣ�');
				return false;
			}
		}

		function exportZsxx(url){
		
			if(curr_row != null){
				document.forms[0].action=url + '&lddm='+curr_row.getElementsByTagName('input')[0].value;
				document.forms[0].submit();
			}else{
				
				document.forms[0].action=url+ '&lddm='+"";
				document.forms[0].submit();
			}
		}

		/**
		 * ������ʦѧԺ����¥����Ϣ
		 * ����ѡ��һ����¼������������������ֱ�ӷ��ص�����ʾ��Ϣ
		 * ���ù���input�ķ�ʽ,���ñ��ύ�������Է�����͵��ߴ���Ч��
		 * @return
		 */
		function getLdxx_dc(){
			//������չ����
			jQuery("#nbjsdiv").empty();
			if(curr_row == null){
				showAlertDivLayer("����ѡ��һ����¼��");
				return false;
			}
			var paras = curr_row.getElementsByTagName('input')[0].value;
			var html = "<input value='"+paras+"' name='params' type='hidden'/>";
			jQuery("#nbjsdiv").append(html);
			var url = "gyglnew_xxtj.do?method=exportLdxxByNbJs";
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function getGytjList(){
			var xqdm = jQuery("#xqdm").val();
			var url = 'gyglnew_xxtj.do?method=xxtjManage&xqdm='+xqdm;
			document.location.href=url;
		}
		jQuery(function(){
			var xqbj = jQuery("#xqbj").val();
			if(xqbj == "1"){
				_w_table_rowspan("#rsTable",1);
			}
		});
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_xxtj" method="post">
			<input type="hidden" name="xqbj" id="xqbj" value="${xqbj }"/>
			<div id="nbjsdiv" style="display:none">
				
			</div>
			<div class="tab_cur" >
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="modi('gyglnew_xxtj.do?method=xxtjDetail',1024,525);return false;" class="btn_cx">�鿴��ϸ��Ϣ</a></li>	
						<logic:notEqual value="99999" name="xxdm">					
						<li><a href="#" onclick="exp('gyglnew_xxtj.do?method=xxtjDczsxx');return false;" class="btn_dc">����¥��ס����Ϣ</a></li>
						<li><a href="#" onclick="exportZsxx('gyglnew_xxtj.do?method=ldzsxxDc');return false;" class="btn_dc">¥��ס�����鵼��</a></li>
						<logic:equal value="13011" name="xxdm">
						<li><a href="#" onclick="exp('gyglnew_xxtj.do?method=getExportFile');return false;" class="btn_dc">����������</a></li>
						</logic:equal>
						</logic:notEqual>
						<logic:equal value="99999" name="xxdm">
								<li><a href="#" onclick="getLdxx_dc();return false;" class="btn_dc">¥��ס����Ϣ����</a></li>
						</logic:equal>
					</ul>
				</div>
			</div>
				<div class="formbox" style="height: 768px">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;
							<logic:notEmpty name="xqList">
								<select name="xqdm" id="xqdm" onchange="getGytjList()" >
									<option value="">ȫУ��</option>
									<logic:iterate name="xqList" id="s" indexId="index">
										<option value="${s.xqdm}" <logic:equal value="${s.xqdm}" name="model" property="xqdm" >selected</logic:equal> >${s.xqmc}</option>
									</logic:iterate>
								</select>
							</logic:notEmpty>
							<logic:empty name="rs"><font color="red">û�����ͳ����Ϣ��</font></logic:empty>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr  align="center">
								<td></td>
								<logic:equal value="1" name="xqbj">
								<td></td>
								</logic:equal>
								<td colspan="3">������/��λ��</td>
								<td colspan="3">δ���䴲λ��</td>
								<td colspan="3">�ѷ��䴲λ��</td>
								<td colspan="3">����ס��λ��</td>
							</tr>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }">
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
						  int m=11;
						  for(int i=0; i<m; i++){
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>	
						 	</tr>
							<%}%>
						 </logic:empty>
						<logic:notEmpty name="rs">
							<logic:iterate name="rs" id="map">
								<tr onclick="rowOnClick(this);" ondblclick="modi('gyglnew_xxtj.do?method=xxtjDetail',1024,525);" style="cursor:hand">
									<logic:equal value="1" name="xqbj">
									<td>${map.xqmc }</td>
									</logic:equal>
									<td>
										<input type="hidden" name="lddm" value="${map.lddm }"/>
										${map.ldmc }
									</td>
									<td>${map.nsqs+map.vsqs }/${map.nscws+map.vscws }</td>
									<td>${map.nsqs }/${map.nscws }</td>
									<td>${map.vsqs }/${map.vscws }</td>
									
									<td>${map.nscws+map.vscws-map.yfpnscws-map.yfpvscws }</td>
									<td>${map.nscws-map.yfpnscws }</td>
									<td>${map.vscws-map.yfpvscws }</td>
									
									<td>${map.yfpnscws+map.yfpvscws }</td>
									<td>${map.yfpnscws }</td>
									<td>${map.yfpvscws }</td>
									
									<td>${map.yrznscws + map.yrzvscws }</td>
									<td>${map.yrznscws }</td>
									<td>${map.yrzvscws }</td>
								</tr>
							</logic:iterate>
					</logic:notEmpty>
				</table>
			</div>
		</html:form>
		
		<%@ include file="/comm/delMessage.jsp"%>
		<%@ include file="/comm/loading.jsp"%>
	</body>
</html>
