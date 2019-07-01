<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/sqsh/js/jxsh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				if (jQuery("#comp_title li").size() == 1){
					jQuery("#comp_title").hide();
				}
				
				var gridSetting = getShqkGrid();
				var tjdw = jQuery("#tjdw").val();
				var spgw = jQuery("#comp_title li").eq(0).attr("spgw");
				jQuery("#shid").val(spgw);
				
				gridSetting["params"] = {shid:spgw,tjdw:tjdw};
				
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				map["shid"] = jQuery("#shid").val();
				map["tjdw"] = jQuery("#tjdw").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_sqsh" method="post" styleId="sqshForm">
			<html:hidden property="tjdw" styleId="tjdw"/>
			<html:hidden property="shid" styleId="shid"/>
			
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" onclick="showSeletTjdw();return false;" class="btn_sx"
								>�л�ͳ�Ʒ�ʽ</a>
							</li>
							
							<logic:equal value="true" name="csszModel" property="kgzt">
								<li id="li_sh">
									<a href="javascript:void(0);" onclick="showAudingList();return false;" 
									   title="ѡ����Ҫ��˵ļ�¼������ð�ť���Դ����ҳ�档"
									   class="btn_sh">���</a>
								</li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%" >
			      	<logic:present name="spgwList">
						<logic:iterate id="s" name="spgwList" indexId="i">
							<logic:equal value="0" name="i">
								<li class="ha" spgw='${s.spgw }'><a href="javascript:void(0);"  onclick="changeTab(this,'${s.spgw }');"><span>${s.gwmc }</span></a></li>
							</logic:equal>
							<logic:notEqual value="0" name="i">
								<li spgw='${s.spgw }'><a href="javascript:void(0);"  onclick="changeTab(this,'${s.spgw }');"><span>${s.gwmc }</span></a></li>
							</logic:notEqual>
						</logic:iterate>
					</logic:present>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>
					<font color="blue">${csszModel.zqmc }</font>
					����������
				</span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
