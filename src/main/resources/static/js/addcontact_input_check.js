var namecheck = document.getElementById("name");
var emailcheck = document.getElementById("email");
var phonecheck = document.getElementById("phoneNumber");
var addresscheck = document.getElementById("address");

namecheck.addEventListener("input", function () {
    if (namecheck.value >= "") {
        namecheck.setCustomValidity("Enter the name of the contact");
    } else {
        namecheck.setCustomValidity("");
        console.log("Valid input");
    }
});

emailcheck.addEventListener("input", function () {
    const emailPattern = /^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
    if (emailcheck.value === "" && !emailPattern.test(emailcheck.value)) {
        emailcheck.setCustomValidity("Enter a valid email for the contact");
    } else {
        emailcheck.setCustomValidity("");
        console.log("Valid input");
    }
});

phonecheck.addEventListener("input", function () {
    const phonePattern = /^\d{10}$/;
    if (phonecheck.value === "" && !phonePattern.test(phonecheck.value)) {
        phonecheck.setCustomValidity("Enter a valid 10-digit phone number");
    } else {
        phonecheck.setCustomValidity("");
        console.log("Valid input");
    }
});

addresscheck.addEventListener("input", function () {
    if (addresscheck.value === "" && addresscheck.value.length < 10) {
        addresscheck.setCustomValidity("Enter a valid address (at least 10 characters)");
    } else {
        addresscheck.setCustomValidity("");
        console.log("Valid input");
    }
});
