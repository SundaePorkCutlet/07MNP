<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>Insert title here</title>
</head>

<body>

<form name="updatePurchase" action="/purchase/updatePurchase?tranNo=${purchase.tranNo}" method="post">


<c:if test="${remainPoint>-1 || purchase.paymentOption!='3'}">
������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>${purchase.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td>${purchase.buyer.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		<c:if test="${purchase.paymentOption =='1'}">
			 ���ݱ���
			 </c:if>
		<c:if test="${purchase.paymentOption =='2'}">
			�ſ뱸��
			</c:if>
		<c:if test="${purchase.paymentOption =='3'}">
			����Ʈ����
			</c:if>
		</td>
		<td></td>
	</tr>
		<tr>
		<td>���ż���</td>
		<td>${amount}</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td>${purchase.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>${purchase.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>${purchase.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>${purchase.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td>${purchase.divyDate}</td>
		<td></td>
	</tr>
</table>
</c:if>
<c:if test ="${remainPoint<0 && purchase.paymentOption =='3'}">
����Ʈ�� �����Ͽ� ���ſ� �����߽��ϴ�.
</c:if>
</form>

</body>


