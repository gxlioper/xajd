<%@ page language="java" contentType="text/html; charset=GBK"%>
<script type="text/javascript">
	function stuKjfs(url){
		parent.document.location = "/xgxt/kjfsSet.do?method=stuKjfs&path="+url;
	}
</script>
<div class="piclink_01">
	<h3><span class="title">���ù���</span><img src="<%=stylePath%>/images/blue/ico_help.gif" width="14" height="14" class="help" onmouseover="helpcon.style.display='block'" onmouseout="helpcon.style.display='none'" />
	<p class="titlecon" id="helpcon" style="display:none;">�ṩ���ٽ���ҳ��Ŀ�ݷ�ʽ����"����"���Զ��峣�ù��ܡ�</p></h3>
		<ul>
			<%int kjfs_count = 0; %>
			<logic:present name="kjfsList">
				<logic:iterate name="kjfsList" id="kjfs">
					<% kjfs_count++; %>
				    <li>
						<a href="commXtwh.do?method=yzyhqx&path='${kjfs.gnmk }'" target="framecon" title="${kjfs.mkms}">
						<img src="<%=stylePath%>images/blue/54/${kjfs.picpath }" /> <span>${kjfs.showmk}</span> </a>
					</li>
				</logic:iterate>
			</logic:present>
			<% if (kjfs_count < 5){%>
				<% for(int i=0;i<5-kjfs_count;i++){%>
					<li>
						<a href="#'">
							<img src="<%=stylePath%>images/blue/54/moren.png" />
							<span>δ����</span>
						</a>
					</li>
				<% }%>
			<% }%>
		</ul>
		<div class="functionbut" >
			<a class="shez" title="���ó��ù���" onclick="refreshForm('/xgxt/kjfsSet.do?method=kjfsManage')">����</a>
		</div>
</div>