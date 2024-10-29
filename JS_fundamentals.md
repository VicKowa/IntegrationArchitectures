# JS fundamentals

## Promise (including error handling)

Promises represent a value that may not yet be available, but will be in the future. 
A promise can either be cancelled (operation successful) or rejected (operation failed).

### Creating a Promise
First, you have to create a new Promise. In the constructor, you define a function that will later be called by the promise.
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
Source: 
- https://www.w3schools.com/Js/js_promise.asp

## Functions and Callback Handler (including the term “Pyramid of Doom”)

### Functions 

In JavaScript, a function can be defined in multiple ways:
 - global Function: `function doStuff() {...}`
 - Anonymous Function: `function() {...}`
 - arrow Function: `() {...}`

### Callback

A Callback functions are normal functions that are passed as an argument to another function and are called at a later time.

```javascript
function doStuff(callback){
    // ... Code ...
    callback();
    // ... Code ...
}

// Call the function with the callback as argument
doStuff(() => {
    console.log("Do Stuff");
});
```


### The Pyramid of Doom

This term refers to the code structure that is created when several 
callbacks are to be executed in parallel. The result is a pyramid-like structure.

```javascript
doStuff1((r1) => {
    doStuff2(r1, (r2) => {
        doStuff3(r2, (r3) => {
            doStuff4(r3, (r4) => {
                console.log("Do Stuff: ", r4);
            });
        });
    });
});
```

This structure can quickly become confusing and is therefore considered a code smell. 
However, there are several alternatives to the Pyramid of Doom: Promises and the async-await keywords

source: 
- https://www.w3schools.com/js/js_function_definition.asp
- https://dev.to/junihoj/the-perils-of-callback-hell-navigating-the-pyramid-of-doom-in-javascript-alj

## Cookie-Handler Express.js

In Express.js, it is possible to create, read, change and delete cookies using the cookie parser 
module. These cookies can store various information across sessions in the user's browser.

First, the module must be loaded with express:
```javaScript
const express = require("express");
const app = express();
const cookieParser = require("cookie-parser");
app.use(cookieParser());
```

# Create Cookie
This is possible by using the “res.cookie(...)” method. This allows you 
to define new cookies that are sent to the client with the response.


```javaScript
app.get('/set-cookie', (req, res) => {
    // ... Code ...
    res.cookie('key1', "My cookie")

    // ... Code ...
    res.send('Send cookie');
});
```

## Read Cookie
It is also possible to read a cookie. For this purpose, `req.cookies` is used to access all cookies created by the Express Server.

```javaScript
app.get('/get-cookie', (req, res) => {
    // ... Code ...

    const key1 = req.cookies.key1;

    // ... Code ...
});
```

Source:
- https://medium.com/@ethantcollins98/setting-and-using-cookies-with-a-node-js-express-server-49479673d043
- 