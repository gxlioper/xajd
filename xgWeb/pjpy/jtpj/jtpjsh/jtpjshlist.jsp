<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
			<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjsh/js/jtpjsh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
				<script type="text/javascript">
					jQuery(function(){
						var map = getSuperSearch();
						map["shzt"]="dsh";
						gridSetting["params"] = map;
						jQuery("#dataTable").initGrid(gridSetting);
						jQuery("#btn_qxsh").click(function (){
							var rows = jQuery("#dataTable").getSeletRow();
							if (rows.length != 1){
								showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
								return false;
							}
							var obj=new Object(0);
							obj["data"]={splc:"splcid",sfkq:"1"};
							cxshnew_splc(obj);
						});
					});

					//�Ͼ��ߵ�ְҵ����ѧУ
					function jtpjmddc(){
						setSearchTj();
						var xn_num = jQuery("a[name=a_name_xn]").length;
						var xq_num = jQuery("a[name=a_name_xq]").length;
						var xy_num = jQuery("a[name=a_name_xy]").length;
						var jxmc_num = jQuery("a[name=a_name_jxmc]").length;

						if(xn_num != 1){
							showAlertDivLayer("����ѡ��һ��ѧ�꣬��ֻ��ѡ��һ����");
						}else if (xq_num != 1){
							showAlertDivLayer("����ѡ��һ��ѧ�ڣ���ֻ��ѡ��һ���� ");
						}else if (jxmc_num != 1){
							showAlertDivLayer("����ѡ��һ���������ƣ���ֻ��ѡ��һ���� ");
						}else{
							var flg = true;
							var yzUrl = 'jtpjsh.do?method=yzjtpjmddc';
							jQuery("form").eq(0).attr("id","jtpjshForm");
							
							jQuery.ajaxSetup({async:false});
								ajaxSubFormWithFun("jtpjshForm", yzUrl, function(data) {
									if(data["result"] != true){
										flg = false;
									}
								});
								if(!flg){
									showAlertDivLayer("�޼��������༶���Ե�����������ѡ����������� ");
									return false;
								}
							jQuery.ajaxSetup({async:true});
							
							var url = "jtpjsh.do?method=jtpjmddc";
							url = addSuperSearchParams(url);//���ø߼���ѯ����	
							jQuery("form").eq(0).attr("action", url);
							jQuery("form").eq(0).submit();
						}
					}
				</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<html:form action="/qjsh?method=list&type=query">
		<%@ include file="/comm/hiddenValue.jsp"%>
		<input type="hidden" value="dsh" id="shzt"/>
		<input type="hidden"  name="query" id="query" value="${query}"/>
		<input type="hidden" name="cancelPath" id="cancelPath" value="jtpjsh.do?method=cancelSh"/>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="jtpjsh();return false;" 
							   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
							   class="btn_sh">���</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" id="btn_qxsh"
							   title="ѡ��һ����¼������ð�ť�����Գ���ʧ�����˲�����"
							   class="btn_qxsh">����</a>
						</li>
					</logic:equal>						
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
						   class="btn_cs">���̸���</a></li>
					<logic:equal value="10874" name="xxdm">
						<li>
							<a href="#" class="btn_down" onclick="jtpjmddc();return false;">�༶��������������</a>
						</li>
					</logic:equal>	
				</ul>
			</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			</div>
		</div>
		</html:form>
		<div class="toolbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span id="title"> ��������������б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
