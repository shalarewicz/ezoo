
<!-- JSTL includes -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<table class="info-table" style="width:50%;">
<tbody>
    <tr>
        <td width="20%" class="field-label"><strong>Name:</strong></td>
        <td width="80%" class="field">${event.name}</td>
    </tr>
    <tr>
        <fmt:parseDate value="${event.time}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="both" />
        <fmt:formatDate value="${parsedDate}" pattern="d MMM, yyyy hh:mm a" var="formattedDate"/>
        <td width="20%" class="field-label"><strong>Time:</strong></td>
        <td width="80%" class="field">${formattedDate}</td>
    </tr>
    <tr>
        <td width="20%" class="field-label"><strong>Location:</strong></td>
        <td width="80%" class="field">${event.location}</td>
    </tr>
    <tr>
        <td width="20%" class="field-label"><strong>Description:</strong></td>
        <td width="80%" class="field">${event.description}</td>
    </tr>
</tbody>
</table>
