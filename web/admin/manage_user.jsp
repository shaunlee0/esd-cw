<%@page import="com.esd.cw.model.Claim"%>
<%@page import="com.esd.cw.model.Payment"%>
<%@page import="com.esd.cw.model.User"%>
<!-- include header start (leave alone) -->
<jsp:include page='../header.jsp'/>
<!-- include header end -->

<%
    // grab the user we are managing for use in the form / page
    User manageUser = (User) request.getAttribute("manageUser");
%>

<!-- page content start (customise) -->
<h1>Admin - Manage User</h1>
<h3 class="info-heading">User Information</h3>
<div class="row">
    <div class="col-md-12">
        <div class="user-information-section">
            <div class="user-information-block">
                <label class="information-title">User ID</label>
                <div class="information-value"><%=manageUser.getUserId()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Name</label>
                <div class="information-value"><%=manageUser.getMember().getName()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Address</label>
                <div class="information-value"><%=manageUser.getMember().getAddress()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Date of Birth</label>
                <div class="information-value"><%=manageUser.getMember().getDateOfBirth()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Date of Registration</label>
                <div class="information-value"><%=manageUser.getMember().getDateOfRegistration()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Status</label>
                <div class="information-value"><%=manageUser.getMember().getStatus()%></div>
            </div>
        </div>
    </div>
</div>
<h3 class="info-heading">Admin Actions</h3>
<div class="user-actions-block">
    <form method="post">
        <div class="form-group">
            <label class="information-title">Member Status</label>
            <select class="form-control" name="newStatus">
                <option ${manageUser.getMember().getStatus().equals("PENDING") ? 'selected' : ''} value="PENDING">Pending</option>
                <option ${manageUser.getMember().getStatus().equals("UNPAID") ? 'selected' : ''} value="UNPAID">Unpaid</option>
                <option ${manageUser.getMember().getStatus().equals("SUSPENDED") ? 'selected' : ''} value="SUSPENDED">Suspended</option>
                <option ${manageUser.getMember().getStatus().equals("PAID") ? 'selected' : ''} value="PAID">Paid</option>
            </select>
        </div>
        <input name="userId" type="text" value="<%= manageUser.getUserId()%>" hidden/>
        <button type="submit" class="btn btn-success">Update</button>
    </form>
</div>

<h3 class="info-heading">Financial & Account Information</h3>
<div class="row">
    <div class="col-md-12">
        <div class="user-information-section">
            <div class="user-information-block">
                <label class="information-title">Claims Remaining</label>
                <div class="information-value"><%=manageUser.getMember().getClaimsRemaining()%></div>
            </div>
            <div class="user-information-block">
                <label class="information-title">Balance</label>
                <div class="information-value">&pound; <%=manageUser.getMember().getBalance()%></div>
            </div>
        </div>
    </div>
</div>
<h3 class="info-heading">Claims</h3>
<div class="row">
    <div class="col-md-12">
        <table class="table">
            <thead>
                <tr>
                    <td>ID</td>
                    <td>Amount</td>
                    <td>Rationale</td>
                    <td>Status</td>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Claim claim : manageUser.getClaims()) {
                %>
                <tr>
                    <td><%=claim.getId()%></td>
                    <td><%=claim.getAmount()%></td>
                    <td><%=claim.getRationale()%></td>
                    <td><%=claim.getStatus()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>
<h3 class="info-heading">Payment History</h3>
<div class="row">
    <div class="col-md-12">
        <table class="table">
            <thead>
                <tr>
                    <td>Type</td>
                    <td>Amount</td>
                    <td>Date</td>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Payment payment : manageUser.getPayments()) {
                %>
                <tr>
                    <td><%=payment.getTypeOfPayment()%></td>
                    <td><%=payment.getPaymentAmount()%></td>
                    <td><%=payment.getDateOfPayment()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</div>
<!-- page content end -->

<!-- include footer start (leave alone) -->
<jsp:include page='../footer.jsp'/>
<!-- include header end -->
