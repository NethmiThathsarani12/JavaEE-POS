function generateOrderID() {
    $("#txtOrderID").val("O00-0001");
    $.ajax({
        url: "http://localhost:8080/backEnd/orders?option=GETID",
        method: "GET",
        success: function (resp) {
            let orderId = resp.orderId;
            let tempId = parseInt(orderId.split("-")[1]);
            tempId = tempId+1;
            if (tempId <= 9){
                $("#txtOrderID").val("O00-000"+tempId);
            }else if (tempId <= 99) {
                $("#txtOrderID").val("O00-00" + tempId);
            }else if (tempId <= 999){
                $("#txtOrderID").val("O00-0" + tempId);
            }else {
                $("#txtOrderID").val("O00-"+tempId);
            }
        },
        error: function (ob, statusText, error) {

        }
    });
}

generateOrderID();

function loadAllOrders(){
    $("#orderTable").empty();
    $.ajax({
        url: "orders?option=GETALL",
        method: "GET",
        success: function (resp) {
            for (const orders of resp.data) {

                let row = `<tr><td>${orders.orderID}</td><td>${orders.cusId}</td><td>${orders.orderDate}</td><td>
                ${orders.total}</td><td>${orders.discount}</td><td>${orders.subTotal}</td></tr>`;
                $("#orderTable").append(row);

            }
            bindOrderDetailsClickEvent();
        }
    });
}

loadAllOrders();

function setCurrentDate() {
    let orderDate = $('#txtOrderDate');
    let today = new Date();
    let dd = String(today.getDate()).padStart(2, '0');
    let mm = String(today.getMonth() + 1).padStart(2, '0');
    let yyyy = today.getFullYear();
    today = yyyy + '-' + mm + '-' + dd;
    orderDate.val(today);
}

setCurrentDate();


function loadItemComboBoxData() {
    $("#txtOrderItemCode").empty();
    $("#txtOrderItemCode").append($("<option></option>").attr("value", 0).text("Select-Item"));
    let count = 0;
    $.ajax({
        url: "http://localhost:8080/backEnd/item?option=GETALL",
        method: "GET",
        success: function (res) {
            for (const item of res.data) {
                $("#txtOrderItemCode").append($("<option></option>").attr("value", count).text(item.itemCode));
                count++;
            }
        },
        error: function (ob, textStatus, error) {
            alert(textStatus);
        }
    });

}


function loadCustomerComboBoxData() {
    $("#txtOrderCusID").empty();
    $("#txtOrderCusID").append($("<option></option>").attr("value", 0).text("Select-Customer"));
    let count = 0;
    $.ajax({
        url: "http://localhost:8080/backEnd/customer?option=GETALL",
        method: "GET",
        success: function (res) {
            for (const customer of res.data) {
                $("#txtOrderCusID").append($("<option></option>").attr("value", count).text(customer.id));
                count++;
            }
        },
        error: function (ob, textStatus, error) {
            alert(textStatus);
        }
    });
}

$("#txtOrderCusID").click(function () {

    let id = $("#txtOrderCusID option:selected").text();
    let name = $("#txtOrderCusName").val();
    let address = $("#txtOrderCusAddress").val();
    let contact = $("#txtOrderCusContact").val();


    $.ajax({
        url: "http://localhost:8080/backEnd/customer?option=GETALL",
        method: "GET",
        success: function (resp) {
            for (const customer of resp.data) {

                if (customer.id == id) {

                    name = customer.name;
                    address = customer.address;
                    contact = customer.contact;

                    $("#txtOrderCusName").val(name);
                    $("#txtOrderCusAddress").val(address);
                    $("#txtOrderCusContact").val(contact);
                }

            }
        }
    });
});


$("#txtOrderItemCode").click(function () {

    let id = $("#txtOrderItemCode option:selected").text();
    let itemName = $("#txtOrderItemName").val();
    let itemQty = $("#txtOrderItemQtyOnHand").val();
    let itemPrice = $("#txtOrderItemPrice").val();

    $.ajax({
        url: "http://localhost:8080/backEnd/item?option=GETALL",
        method: "GET",
        success: function (resp) {
            for (const item of resp.data) {
                if (item.itemCode == id) {

                    itemName = item.itemName;
                    itemQty = item.itemQty;
                    itemPrice = item.itemPrice;

                    $("#txtOrderItemName").val(itemName);
                    $("#txtOrderItemQtyOnHand").val(itemQty);
                    $("#txtOrderItemPrice").val(itemPrice);
                }
            }
        }
    });
});


function bindOrderClickEvent() {
    $("#addToCartTable>tr").click('click', function () {

        tableRow = $(this);
        let itemCode = $(this).children(":eq(0)").text();
        console.log(itemCode);
        let itemName = $(this).children(":eq(1)").text();
        let unitPrice = $(this).children(":eq(2)").text();
        let qty = $(this).children(":eq(3)").text();

        $.ajax({
            url: "http://localhost:8080/backEnd/item?option=SEARCH&itemCode=" + itemCode,
            method: "GET",
            success: function (resp) {
                let avQty = resp.qtyOnHand;
                let newQty = avQty - qty;
                parseInt($("#txtOrderItemQtyOnHand").val(newQty));
            }
        });

        $("#txtOrderItemCode option:selected").text(itemCode);
        $("#txtOrderItemName").val(itemName);
        $("#txtOrderItemPrice").val(unitPrice);
        $("#txtQty").val(qty);

    });
}


var tableRow;

