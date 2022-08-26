"use strict";

// Iteration

// let a = 100;
// let aMoreThan200 = false;

// while (aMoreThan200) {
//   console.log(a);
//   a++;

//   if (a > 200) {
//     aMoreThan200 = true;
//   }
// }
// console.log("A is bigger than 200");

function myFunc(num1, num2) {
  return num1 - num2;
}

let welcome = function (name, age, gender) {
  return console.log(
    `My name is ${name}, i am ${age} years old and of gender ${gender}`
  );
};

welcome("Felix Cited", 20, "Male");
