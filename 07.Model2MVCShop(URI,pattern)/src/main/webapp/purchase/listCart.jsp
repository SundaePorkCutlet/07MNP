<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>





<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
function cart(){
	popWin = window.open("/purchase/deleteCart?prodNo=${cart.purchaseProd.prodNo}&menu=search","popWin","left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
}
function fncGetUserList(currentPage) {
	document.getElementById("currentPage").value = currentPage;
   	document.detailForm.submit();		
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/purchase/listCart" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">��ٱ��� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">��ü ${resultPage.totalCount } �Ǽ�, ����${resultpage.currentPage } ������</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">��ǰ��</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">����</td>
		
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<c:set var="i" value="0" />
	<c:forEach var="cart" items="${list}">
		<c:set var="i" value="${i+1}"/>

	<tr class="ct_list_pop">
		<td align="center">
		<c:if test="${cart.amount!=0 }">
			<a href="/product/getProduct?prodNo=${cart.purchaseProd.prodNo}&menu=search"
			target="rightFrame">${cart.purchaseProd.prodNo}</a>
			</c:if>
			
			<c:if test="${cart.amount==0 }">
			${cart.purchaseProd.prodNo}
			</c:if>
			
		</td>
		<td></td>
		<td align="left">
			<c:if test="${cart.amount!=0 }">
			<a href="/product/getProduct?prodNo=${cart.purchaseProd.prodNo}&menu=search"
			target="rightFrame">${cart.purchaseProd.prodName}</a>
			</c:if>
			
			<c:if test="${cart.amount==0 }">
			${cart.purchaseProd.prodName}
			</c:if>
			
		</td>
		<td></td>
		<td align="left">${cart.purchaseProd.price }</td>
		<td></td>
			<td align="left"><c:if test= "${cart.amount==0 }">
			ǰ�� ��ǰ �Դϴ�.
			</c:if></td>
		<td></td>
			<td align="left">
			<a href="/purchase/deleteCart?prodNo=${cart.purchaseProd.prodNo}&menu=search"
			target="rightFrame">&#128701;</a>
		</td>
		<td></td>
		
	

	
	
		

		
	
		
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center"></td>
		<td>
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
		<jsp:include page="../common/pageNavigator.jsp"/>
	
		</td>
		
	</tr>
</table>

<!--  ������ Navigator �� -->
</form>

</div>

</body>
</html>