"use strict";

// Selectors

// Divs
let resultsDiv = document.querySelector("#results-div");

// Inputs
let nameInput = document.querySelector("#nameInput");
let quantityInput = document.querySelector("#quantityInput");
let priceInput = document.querySelector("#priceInput");
let idInput = document.querySelector("#idInput");

// Buttons
let createBtn = document.querySelector("#createBtn");
let updateBtn = document.querySelector("#updateBtn");
// let deleteBtn = document.querySelector("#deleteBtn");

//  Functions

let printResults = (result) => {
  let entryParent = document.createElement("div");
  entryParent.setAttribute("class", "entry-parent");

  let entryDiv = document.createElement("div");
  entryDiv.setAttribute("class", "entry-div");
  entryDiv.textContent = `${result.id} | ${result.name} | ${result.quantity} | ${result.price}`;

  let delBtn = document.createElement("button");
  delBtn.textContent = "Delete";
  delBtn.type = "button";
  delBtn.setAttribute("Class", "btn btn-danger btn-sm");
  delBtn.setAttribute("onClick", `del(${result.id})`);

  entryParent.appendChild(entryDiv);
  entryParent.appendChild(delBtn);
  resultsDiv.appendChild(entryParent);
};

let getAll = () => {
  axios
    .get("http://localhost:8080/item/getAll")
    .then((res) => {
      resultsDiv.innerHTML = "";

      let results = res.data;

      for (let result of results) {
        printResults(result);
      }
    })
    .catch((err) => {
      console.log(err);
    });
};

let create = () => {
  console.log("hello");
  let obj = {
    name: nameInput.value,
    quantity: quantityInput.value,
    price: priceInput.value,
  };

  axios
    .post("http://localhost:8080/item/create", obj)
    .then((res) => {
      getAll();
    })
    .catch((err) => {
      console.log(err);
    });
};

let update = () => {
  let obj = {
    name: nameInput.value,
    quantity: quantityInput.value,
    price: priceInput.value,
  };

  axios
    .put(`http://localhost:8080/item/update/${idInput.value}`, obj)
    .then((res) => {
      getAll();
    })
    .catch((err) => {
      console.log(err);
    });
};

let del = (id) => {
  // if (!validateDelete()) {
  //   return;
  // }

  axios
    .delete(`http://localhost:8080/item/delete/${id}`)
    .then((res) => {
      getAll();
    })
    .catch((err) => {
      console.log(err);
    });
};

let validateDelete = () => {
  if (idInput.value === "") {
    alert("ID is required for this operation");
    return false;
  } else {
    return true;
  }
};

//  Listeners
createBtn.addEventListener("click", create);
updateBtn.addEventListener("click", update);
// deleteBtn.addEventListener("click", del);
