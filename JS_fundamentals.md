# JS fundamentals


## The await/async statement

In JS it is possible to declare a function as asynchronous by using the `async` keyword. 
This means implicitly that the function will return a promise. 

```js
// Example of an async function
async function myFunction() {
  return "Hello";
}
```

If an asynchronous function returns a value, the value is wrapped in a promise. 

```js
// return type is a promise
myFunction().then(alert); // Hello
``` 

The `await` keyword is used to pause the execution of an async function and wait for a promise to resolve.\
<i>Note that the keyword can only be used inside an async function!</i>

The syntax of await:
`let result = await promise;`

```js
async function myFunction() {
  let promise = new Promise((resolve, reject) => {
    setTimeout(() => resolve("This was a long request!"), 3000)
  });

  let result = await promise; // wait until the promise resolves (*)

  console.log(result); // "done!"
}
```
Let's say that myFunction simulates an api call, which takes 3 seconds to resolve.\
The method starts with creating a promise.\
After creating the promise we use the await keyword to wait for the promise to resolve.\
When the promise resolves, the result is stored in the variable `result`.\
Finally, the result is printed out. 

Here is an example of what happens if you didn't use the await keyword:

```js
async function myFunction() {
  let promise = new Promise((resolve, reject) => {
    setTimeout(() => resolve("This was a long request!"), 3000)
  });

  let result = promise; // wait until the promise resolves (*)

  console.log(result); // [object Promise]
}
```
The result will be `Promise { <pending> }` because the promise is not resolved yet when the console.log is executed.

### Error handling
If a promise is rejected, the execution will be stopped and the error will be thrown.\
To handle the error, you can use the `try...catch` statement or the `.catch()` method, like this:

```js
// using try...catch:
async function myFunction() {
  try {
    let request1 = new Promise((resolve, reject) => {
      setTimeout(() => reject(new Error("Whoops!")), 3000)
    });
    let request2 = new Promise((resolve, reject) => {
      setTimeout(() => resolve("Whoops again!"), 3000)
    });

    let result = await request1;
    let result2 = await request2;
  } catch (error) {
    console.log(error); // Error: Whoops!
  }
}

// alternative way to handle errors:
async function myFunction() {
  let request1 = new Promise((resolve, reject) => {
    setTimeout(() => reject(new Error("Whoops!")), 3000)
  });  
  let result = await request1.catch(error => console.log(error));
}
```

## Axios JS
Axios is a JavaScript library (or package) that can be used to make HTTP requests from node.js and the browser.\
It is an isomorphic HTTP client, meaning that it can be used in both the browser and node.js with the exact codebase.\
Axios is promise-based, which means you can take advantage of JavaScript's async and await for more readable asynchronous code.

### Installation                                                                                                                   
To install Axios, you can use npm or yarn.\

### Why don't use fetch?
Axios has the possibility to automatically transform JSON data, perform request timeouts and set default headers.\
Another advantage is that Axios has a better error handling than fetch.\

### Example of a GET request with Axios
```js
const axios = require('axios'); // this allows autocomplete when using axios.<method>

axios.get('https://placeholder/data')
  .then(function (response) {
    // this will be executed if the request is successful
    console.log(response.data);
  })
  .catch(error => {
    console.log(error);
  });
```
In this example, we are making a GET request to the URL `https://placeholders/data`.\
If the request is successful, the data will be printed out.\
Otherwise, the error will be printed out.

There is also the possibility to use async/await to make the code more readable:
```js
const axios = require('axios');

async function fetchData() {
  try {
    const response = await axios.get('https://placeholder/data');
    console.log(response.data);
  } catch (error) {
    console.log(error);
  }
}
```

### How to make a Request with Parameters
```js
axios.get('https://placeholder/data', {
  params: {
    UserID: 12345,
    Name: 'John Smith' 
  }
}).then(response => {
  console.log(response.data);
}).catch(error => {
  console.log(error);
});
```
This example displays how to make a GET request with two parameters.\

### How to make a POST request
```js
axios.post('https://placeholder/animals', {
  AnimalID: 5678,
  Name: 'Josy',
  Type: 'Tutle'
}).then(response => {
  console.log(response.data);
}).catch(error => {
  console.log(error);
});
```
Above you can see an example of how to make a POST request with some Parameters.\
The parameters are passed as an object in the second argument of the post method.