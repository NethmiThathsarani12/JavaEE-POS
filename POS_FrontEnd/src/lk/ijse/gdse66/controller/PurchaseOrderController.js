// let baseUrl = "http://localhost:8080/backEnd/"

// $("#btnSubmitOrder").attr('disabled', true);
// $("#btnAddToCart").attr('disabled', true);


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


let itemCode;
let itemName;
let itemPrice;
let itemQty;
let itemOrderQty;


let total = 0;
let discount = 0;
let subTotal = 0;


let tableRow = [];
$("#btnAddToCart").on("click", function () {

    let duplicate = false;
    for (let i = 0; i < $("#addToCartTable tr").length; i++) {
        if ($("#txtOrderItemCode option:selected").text() === $("#addToCartTable tr").children(':nth-child(1)')[i].innerText) {
            duplicate = true;

        }
    }

    if (duplicate !== true) {

        loadCartTableDetail();
        reduceQty($("#txtOrderQty").val());
        calcTotal($("#txtOrderQty").val() * $("#txtOrderItemPrice").val());
        $('#txtOrderItemCode,#txtOrderItemName,#txtOrderItemPrice,#txtOrderItemQtyOnHand,#txtOrderQty').val("");
        // $("#btnAddToCart").attr('disabled', true);

    }else if (duplicate === true) {

        manageQtyOnHand(tableRow.children(':nth-child(4)').text(), $("#txtOrderQty").val());
        $(tableRow).children(':nth-child(4)').text($("#txtOrderQty").val());

        manageTotal(tableRow.children(':nth-child(5)').text(), $("#txtOrderQty").val() * $("#txtOrderItemPrice").val());
        $(tableRow).children(':nth-child(5)').text($("#txtOrderQty").val() * $("#txtOrderItemPrice").val());

    }

    $("#addToCartTable>tr").click('click', function () {

        tableRow = $(this);
        let itemCode = $(this).children(":eq(0)").text();
        let itemName = $(this).children(":eq(1)").text();
        let unitPrice = $(this).children(":eq(2)").text();
        let qty = $(this).children(":eq(3)").text();
        let total = $(this).children(":eq(4)").text();

        $("#txtOrderItemCode").val(itemCode);
        $("#txtOrderItemName").val(itemName);
        $("#txtOrderItemPrice").val(unitPrice);
        $("#txtOrderQty").val(qty);
        $("#txtTotal").val(total);

    });
});

function reduceQty(orderQty) {
    let minQty = parseInt(orderQty);
    let reduceQty = parseInt($("#txtOrderItemQtyOnHand").val());
    reduceQty = reduceQty - minQty;
    $("#txtOrderItemQtyOnHand").val(reduceQty);
}

function calcTotal(amount) {
    total += amount;
    $("#txtTotal").val(total);
}

function manageQtyOnHand(preQty, nowQty) {
    var preQty = parseInt(preQty);
    var nowQty = parseInt(nowQty);
    let avaQty = parseInt($("#txtOrderItemQtyOnHand").val());

    avaQty = avaQty + preQty;
    avaQty = avaQty - nowQty;

    $("#txtOrderItemQtyOnHand").val(avaQty);
}

function manageTotal(preTotal, nowTotal) {
    total -= preTotal;
    total += nowTotal;

    $("#txtTotal").val(total);
}


$("#addToCartTable").empty();

function loadCartTableDetail() {
    itemCode = $("#txtOrderItemCode").val();
    itemName = $("#txtOrderItemName").val();
    itemPrice = $("#txtOrderItemPrice").val();
    itemQty = $("#txtOrderItemQtyOnHand").val();
    itemOrderQty = $("#txtOrderQty").val();

    let total = itemPrice * itemOrderQty;
    let row = `<tr><td>${itemCode}</td><td>${itemName}</td><td>${itemPrice}</td><td>${itemOrderQty}</td><td>${total}</td></tr>`;

    $("#addToCartTable").append(row);
}

$(document).on("change keyup blur", "#txtOrderQty", function () {
    let qtyOnHand = $("#txtOrderItemQtyOnHand").val();
    let buyQty = $("#txtOrderQty").val();
    let buyOnHand = qtyOnHand - buyQty;
    if (buyOnHand < 0) {
        $("#lblCheckQty").parent().children('strong').text(qtyOnHand + " : Empty On Stock..!!");
        // $("#btnAddToCart").attr('disabled', true);
    } else {
        $("#lblCheckQty").parent().children('strong').text("");
        // $("#btnAddToCart").attr('disabled', false);
    }
});

$(document).on("change keyup blur", "#txtDiscount", function () {
    discount = $("#txtDiscount").val();
    discount = (total / 100) * discount;
    subTotal = total - discount;

    $("#txtSubTotal").val(subTotal);
});


$(document).on("change keyup blur", "#txtCash", function () {
    let cash = $("#txtCash").val();
    let balance = cash - subTotal;
    $("#txtBalance").val(balance);
    if (balance < 0) {
        $("#lblCheckSubtotal").parent().children('strong').text(balance + " : plz enter valid Balance");
        // $("#btnSubmitOrder").attr('disabled', true);
    } else {
        $("#lblCheckSubtotal").parent().children('strong').text("");
        // $("#btnSubmitOrder").attr('disabled', false);
    }
});



$("#btnPurchase").click(function () {

    var orderDetails = [];
    for (let i = 0; i < $("#addToCartTable tr").length; i++) {
        var detailOb = {
            orderId: $("#txtOrderID").val(),
            itemId: $("#addToCartTable tr").children(':nth-child(1)')[i].innerText,
            qty: $("#addToCartTable tr").children(':nth-child(4)')[i].innerText,
            unitPrice: $("#addToCartTable tr").children(':nth-child(5)')[i].innerText
        }
        orderDetails.push(detailOb);
    }
    var orderId = $("#txtOrderID").val();
    var customerId = $("#txtOrderCusID option:selected").text();
    var date = $("#txtOrderDate").val();

    var orderOb = {
        "orderId": orderId, "date": date, "customerId": customerId, "detail": orderDetails
    }
    console.log(orderOb)
    console.log(orderDetails)

    $.ajax({
        url: "http://localhost:8080/backEnd/orders",
        method: "POST",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(orderOb),
        success: function (res) {
            saveUpdateAlert("Order", res.message);
            generateOrderID();

        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            unSuccessUpdateAlert("Order", message);
        }
    });

    $.ajax({
        url:  "http://localhost:8080/backEnd/orders",
        method: "PUT",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify(orderOb)
    });

    clearDetails();
    $("#addToCartTable").empty();
    $("#btnPurchase").attr('disabled', true);
    $("#btnAddToCart").attr('disabled', true);
    total = 0;
});

function clearDetails() {
    $('#txtOrderCusID,#txtOrderCusName,#txtOrderCusAddress,#txtOrderCusContact,#txtOrderItemCode,#txtOrderItemName,#txtOrderItemPrice,#txtOrderItemQtyOnHand,#txtOrderQty,#txtDiscount,#txtTotal,#txtDiscount,#txtSubTotal,#txtCash,#txtBalance').val("");
    $("#addToCartTable").empty();
    $("#btnPurchase").attr('disabled', true);
    $("#btnAddToCart").attr('disabled', true);
}

$("#btnClearAll").click(function () {
    clearDetails();
});


// $("#tblAddToCart").dblclick(function () {
//     Swal.fire({
//         title: 'Do you want to Delete the Select row?',
//         showDenyButton: true,
//         showCancelButton: true,
//         confirmButtonText: 'Yes',
//         denyButtonText: 'No',
//         customClass: {
//             actions: 'my-actions', cancelButton: 'order-1 right-gap', confirmButton: 'order-2', denyButton: 'order-3',
//         }
//     }).then((result) => {
//         if (result.isConfirmed) {
//             $(this).children('tr').eq(0).remove();
//             Swal.fire('Delete!', '', 'success')
//         } else if (result.isDenied) {
//             Swal.fire('Select row are not Delete', '', 'info')
//         }
//     })
//
// });