<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<!--<script type='text/javascript' src="js/comm/message.js"></script>-->
		<script language="javascript" src="js/xsgzgl/jxjzgl/jxjzgl.js"></script>
		<script language="javascript" src="js/xsgzgl/jxjzgl/jxjzTree.js"></script>
		<style>
		span{
			margin:0;
			border:0;
			list-style:none;
		}
		#gy-tree table{
			width:100%;
		}
		#gy-tree table tr td span{
			cursor:pointer;
		}
		#gy-tree table td {
			VERTICAL-ALIGN: top
		}
		#gy-tree table td.bj {
			
		}
		#gy-tree table td.bj {
			WIDTH: 15px;
			height: 15px;
		}
		#gy-tree table td.bj a{
			WIDTH: 15px;
			height: 15px;
			cursor: pointer;
		}
		#gy-tree table td.bj a div{
			WIDTH: 15px;
			height: 15px;
		}
		#gy-tree table td.kg {
			WIDTH: 15px
		}
		UNKNOWN {
			COLOR: #cc0080; TEXT-DECORATION: none
		}
		#gy-tree table A:hover {
			COLOR: #0ff080; TEXT-DECORATION: none
		}
		#gy-tree .hitTd{
			background-color: #deecf5;
		}
		
		</style>
		
		<script language="javascript" defer="defer">
		
		//��ʼ��
		function onShow(){ 
			searchRs();
		}
		
		//��ѯ�����
		function searchRs(){
			/**
			var url = "jxjzgl_cxJxjz_ajax.do?method=cxJzxsmdAjax";
			
			var ie = "ie";

			var otherValue = [ie];

			var jzid=getSjid();
			searchRsByAjax(url,jzid);
			
			setTimeout("setDivHeight()","2000");
			**/
			var url = "jxjzgl_cxJxjz_ajax.do?method=cxJzxsmdAjax";
			
			var ie = "ie";

			var jzid=jQuery("#searchJzid").val();
			
			var otherValue = [ie,jzid];
			searchRsByAjax(url,otherValue);
			
			setTimeout("setDivHeight()","2000");
		}


		function setDivHeight(){
			if($("table_rs")){
				jQuery("#div_rs").height(jQuery("#table_rs").height()+20);	
			}
		}

		//��ʼ��ҳ��
		jQuery(function(){
			//��ʼ��������Ϣ
			jQuery("#showJzmc").html(jQuery("#jxmc").val());
			//��ʼ����
			initTree();
			initJxjz();
			//alert(leafNode());
			onShow();
		});

		//��ѯ��ѵ��������
		function cxJzjzmdPage(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			//url=url+"&jzid="+jzid;
			url=url+"&resultPath=jxjzgl_cxJxjz.do?method=cxJzwh";
			window.location.href=url;
		}

		//��ѯ��ѵ�������� �ѱ���
		function cxJzjzmdPageYbz(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&sjjz=1";
			url=url+"&resultPath=jxjzgl_cxJxjz.do?method=cxJzwh";
			window.location.href=url;
		}

		//��ѯ��ѵ��������  δ����
		function cxJzjzmdPageWbz(){
			var url="jxjzgl_cxJxjz.do?method=cxJzmd";
			url=url+"&sjjz=0";
			url=url+"&resultPath=jxjzgl_cxJxjz.do?method=cxJzwh";
			window.location.href=url;
		}

</script>
	</head>
	<body onload="">

		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
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
				<span> 1."�����¼�����"��"�޸ı�������"��"ɾ����������"��"ά����������"����������ѡ��������νڵ�<br/>
				2.��ͽ����²�����"�����¼�����"<br/>
				3.��߽��Ʋ���"�޸ı�������"��"ɾ����������"<br/>
				4.ѡ���Ӧѧ���ٵ��"ȡ������",����ȡ��ѧ������
				 </span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->

		<html:form action="/jxjzgl_cxJxjz" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="operation" id="operation"
				value="${operation}" />
			<input type="hidden" name="jxid" id="jxid" value="${jxxxwhModel.jxid }"/>
			<input type="hidden" name="jxmc" id="jxmc" value="${jxxxwhModel.jxmc }"/>
			<input type="hidden" name="jddjdmZd" id="jddjdmZd" value="${jxdjzdModel.djdm }"/>
			<input type="hidden" name="searchJzid" id="searchJzid"  value="${jxxxwhModel.jxid }"/>
			<input type="hidden" name="xxdm" id="xxdm" value="${xxdm }"/>

			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="window.location.href='jxjzgl_jxjzgl_cxJxjz.do';return false;"
								class="btn_fh"> ���� </a>
						</li>
						<li>
							<a href="#" onclick="zjXjjz();return false;"
								class="btn_zj"> �����¼����� </a>
						</li>
						<li>
							<a href="#" onclick="xgJxjz();return false;"
								class="btn_xg"> �޸ı������� </a>
						</li>
						<li>
							<a href="#" onclick="checkJzmdByJxcjAndJxbx();return false;"
								class="btn_sc"> ɾ���������� </a>
						</li>
						<li>
							<a href="#" onclick="cxWhjzmd();return false;"
								class="btn_zj"> ά���������� </a>
						</li>
						<li>
							<a href="#" onclick="qxbz();return false;"
								class="btn_sc"> ȡ������ </a>
						</li>
					</ul>
				</div>
				<!-- ��ť end-->

			</div>
			
			<!--����start-->
			    <h3 class="datetitle_01">
			    <span id="jxjzrsTj">��ѵ���ƣ�${jxxxwhModel.jxmc} &nbsp;��ѵ������ <a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPage()">${rsTj.cxrs }</a>��&nbsp;�ѱ���������<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageYbz()">${rsTj.ybzrs }</a>��&nbsp;��δ����������<a href="javascript:void(0);" style="color: blue;text-decoration:underline;" class="name" onclick="cxJzjzmdPageWbz()">${rsTj.wbzrs }</a>��</span>
			    </h3>
			<!--����end-->
			
			<!-- �������� -->	
			<div style="display: none;">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- �������� end-->
			
			<!-- �๦�ܲ����� -->
			<div class="leftframe04" style="width:182px;">
				<div class="menulist" style="width:100%;">
					<!--��start-->
					<div class="menutitle" style="width:100%;">
						<h3 style="width:100%;">
							<span class="title">�����б�</span>
						</h3>
						<!--CNLTreeMenu Start:-->
						<div class="CNLTreeMenu1" id="CNLTreeMenu1"
							style="height: 500px; overflow: auto;"  style="width:97%;">
							<!-- �����б�ʼ -->
							<div id="gy-tree">
	
							</div>
							<!-- �����б���� -->
						</div>
					</div>
					<!--��end-->
				</div>
			</div>
			<div class="rightframe04" style="width:610px;">
				<!--�������Ŀ��������ʱ����rightframe04_hidden���class��-->
				<!-- ���� -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<tr>
								<th >
									��������
								</th>
								<td colspan="3" id="showJzmc">
								
								</td>
							</tr>
							<tr>
								<th width="15%" >
									�̹�����
								</th>
								<td width="35%" id="showJgmc">
									
								</td>
								<th width="15%">
									�̹ٵ绰
								</th>
								<td id="showJgdh">
									
								</td>
							</tr>
							<tr>
								<th >
									��ʦ����
								</th>
								<td id="showJsmc">
								
								</td>
								<th >
									��ʦ�绰
								</th>
								<td id="showJsdh">
									
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<!--���й������� ���ݿ�start-->
				<div class="con_overlfow" id="jzxsxx">
					<!--�������Ŀ��������ʱ����con_overlfow01_hidden���class��-->
					<div id="div_rs"
						style="width:100%;overflow-x:auto;overflow-y:hidden;">
					</div>
					</div>
				
				<!--��ҳ��ʾ-->
			<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=jxjzglFrom"></jsp:include>
			</div>
			<script type="text/javascript">
									$('choose').className="hide";
							</script>
			<br />
			
			<!-- �����¼�����  -->
			<div id="div_zjjz" style="display:none">
			</div>
			<!-- �����¼����� end-->
			
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>