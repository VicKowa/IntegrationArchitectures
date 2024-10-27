# JS fundamentals


## Promise (including error handling)

Promises represent a value that may not yet be available, but will be in the future. 
A promise can either be cancelled (operation successful) or rejected (operation failed).

### Creating a Promise
First you have to create a new Promise. In the constructor you define a function that will later be called by the promise.
This function takes two parameters, resolve and reject, which allow you to determine the success or failure of the operation.

```javascript
let promise = new Promise((resolve, reject) => {
    if (false) {
        resolve("success");
    } else {
        reject("failure");
    }
});
```

### Using a Promise
Now the promise can be called. To do this, simply call `then`, which calls the previously defined function of the promise. 
To handle the resolved or rejected state of a promise, new functions are passed to the `then` and `catch` methods. The passed function of the `then` method is called when 
the promise is resolved and the result is passed to the function it contains. If the promise is rejected, the function of the catch method handles the error by passing the rejection reason to the function.

```javascript
promise.then((result) => { 
    console.log(result);
}).catch((result) => {
    console.log(result);
});
```
source: 
- https://www.w3schools.com/Js/js_promise.asp


Korrekt, Rewrite, Change setences