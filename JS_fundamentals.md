# JS Fundamentals

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

### Object Creation in JavaScript

JavaScript offers several methods for creating objects.\
You can use different methods for different use cases.

#### 1. Object literal

**Use case**: 
> You can best use it for small, single-use objects without requiring a blueprint (e.g. [Constructor](#3-using-a-constructor-function), [Class](#5-using-the-class-keyword)).

```javascript
let salesMan = {
  firstname: 'firstname',
  lastname: 'lastname',
  sid: sid
};
```

#### 2. Using the `new` keyword

**Use case**: 
> You can use it when you first want to create an empty object and then later add properties to it.

```javascript
let salesMan = new Object();
salesMan.firstname = 'firstname';
salesMan.lastname = 'lastname';
salesMan.sid = sid;
```

#### 3. Using a constructor function

**Use case**: 
> You can use it when you want to create multiple objects with the same blueprint.

```javascript
function SalesMan(firstname, lastname, sid) {
  this.firstname = 'firstname';
  this.lastname = 'lastname';
  this.sid = sid;
}
```

#### 4. Using the `Object.create` method

**Use case**: 
> You can use it when you want to create an object with a specific prototype.\
> That means you can create a prototype object with required properties and then create an object 
> having these properties as well as additional properties.

```javascript
const prototype = {
  firstname: 'firstname',
  lastname: 'lastname',
  sid: -1
};

let salesMan = Object.create(prototype);
salesMan.firstname = 'specificFirstName';
salesMan.lastname = 'specificLastName';
salesMan.sid = specificSid;

// additional properties
salesMan.additionalProperty = 'additionalValue';
```

#### 5. Using the `class` keyword

**Use case**:
> You can use it when you want to create multiple objects with the same blueprint.\

```javascript
class SalesMan {
  constructor(firstname, lastname, sid) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.sid = sid;
  }
}

let salesMan = new SalesMan('firstname', 'lastname', sid);
```

#### 6. Using the `function` keyword

**Use case**:
> You can use it when you want to create multiple objects with the same blueprint.

```javascript
function SalesMan(firstname, lastname, sid) {
  this.firstname = firstname;
  this.lastname = lastname;
  this.sid = sid;
}

let salesMan = new SalesMan('firstname', 'lastname', sid);
```

#### 7. Using the `Object.assign` method

**Use case**:
> You can use it when you want to create a new object by copying the properties of an existing object.

```javascript
let salesMan = {
  firstname: 'firstname',
  lastname: 'lastname',
  sid: sid
};

let newSalesMan = Object.assign({}, salesMan);
```

#### 8. Using the `spread` operator

**Use case**:
> You can use it when you want to create a new object by copying the properties of an existing object.

```javascript
let salesMan = {
  firstname: 'firstname',
  lastname: 'lastname',
  sid: sid
};

let newSalesMan = { ...salesMan };
```

#### 9. Using the `Object.setPrototypeOf` method

**Use case**:
> You can use it when you want to set the prototype of an object.

```javascript
const prototype = {
  firstname: 'firstname',
  lastname: 'lastname',
  sid: -1
};

let salesMan = {
  firstname: 'specificFirstName',
  lastname: 'specificLastName',
  sid: specificSid
};

Object.setPrototypeOf(salesMan, prototype);
```

#### 10. Using the `__proto__` property

**Use case**:
> You can use it when you want to set the prototype of an object.

```javascript
const prototype = {
  firstname: 'firstname',
  lastname: 'lastname',
  sid: -1
};

let salesMan = {
  firstname: 'specificFirstName',
  lastname: 'specificLastName',
  sid: specificSid
};

salesMan.__proto__ = prototype;
```

### REST API in Express.js

#### 1. What is REST API?

**REST** stands for **RE**presentational **S**tate **T**ransfer.\
It is an architectural style that defines a set of constraints to be used for creating a RESTful web service.

> **RESTful web service** is a web service that follows the REST architecture.

##### Constraints of REST architecture

1. **Uniform Interface**:\
   The interface between clients and servers should be the same for all RESTful web services.
2. **Client-Server**:\
   There should be a separation between the client and the server.
3. **Stateless**:\
   Each request from a client to a server must contain all the information needed to understand the request. That implies that the server cannot store any information about the client.
4. **Cacheable**:\
   The server must indicate to the client whether the response can be cached or not (i.e. if the response can be saved client-sided).
5. **Layered System**:\
   A client cannot ordinarily tell whether it is connected directly to the end server or to an intermediary along the way. In general: one cannot view outside the layer they are inside (e.g. MVC-Pattern).
6. **Code on Demand (optional)**:\
   Servers can provide executable code to the client.

#### 2. What is a Resource?

A **resource** is an object or representation of something that can be identified.\
A resource can be a physical object, a service, or a collection of other resources.

> **Resources** are identified by their unique URIs.
> > **URI** stands for **U**niform **R**esource **I**dentifier.

In this case, the `salesman` is a resource we want to interact with.\
Therefore, we can define the following routes:

- `GET /salesman`: Get all salesmen
- `GET /salesman/:id`: Get a specific salesman
- `POST /salesman`: Create a new salesman
- `PUT /salesman/:id`: Update a specific salesman
- `DELETE /salesman/:id`: Delete a specific salesman

#### 3. How to create a REST API in Express.js?

**Important**: If not already done, install Express.js by running `npm install express`.

1. Create a new file (e.g. `server.js`) and require Express.js:

```javascript
const express = require('express');
const app = express();
```
2. Define the routes for the REST API:

**Note**: The following code snippets are placeholders for the actual implementation.\
\
`app.get('/salesman', ...)` defines a route for getting all salesmen.\
If a GET request is made to `/salesman`, the callback function will be executed.


```javascript
app.get('/salesman', (req, res) => {
  res.send('Get all salesmen (To be implemented)');
});

app.get('/salesman/:id', (req, res) => {
  res.send('Get a specific salesman (To be implemented)');
});

app.post('/salesman', (req, res) => {
  res.send('Create a new salesman (To be implemented)');
});

app.put('/salesman/:id', (req, res) => {
  res.send('Update a specific salesman (To be implemented)');
});

app.delete('/salesman/:id', (req, res) => {
  res.send('Delete a specific salesman (To be implemented)');
});
```

**Note**: The `req` and `res` parameters are objects that represent the HTTP request and the HTTP response, respectively.

3. Create the salesmen array:

**Note**: This array will be used to store the salesmen data.

```javascript
let salesmen = [
  { id: 1, firstname: 'John', lastname: 'Doe' },
  { id: 2, firstname: 'Jane', lastname: 'Doe' }
];
```

4. Implement the routes:

4.1. Get all salesmen:

**Note**: The `res.json()` method sends a JSON response.

```javascript
app.get('/salesman', (req, res) => {
  res.json(salesmen);
});
```

4.2. Get a specific salesman:

**Note**: The `req.params` object contains the route parameters (therefore the specific sid).

**Example**: If the route is `/salesman/1`, then `req.params.id` will be `1`.

> The `res.status(404).send('Salesman not found')` method sets the status code to `404` and sends the message 'Salesman not found'.

```javascript
app.get('/salesman/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const salesman = salesmen.find(s => s.id === id);
  
  if (salesman) {
    res.json(salesman);
  } else {
    res.status(404).send('Salesman not found');
  }
});
```

4.3. Create a new salesman:

> The `res.status(201)` method sets the status code to `201` (Created).

```javascript
app.post('/salesman', (req, res) => {
  const id = salesmen.length + 1;
  const firstname = req.body.firstname;
  const lastname = req.body.lastname;
  const salesman = { id, firstname, lastname };
  
  salesmen.push(salesman);
  
  res.status(201).json(salesman);
});
```

4.4. Update a specific salesman:

```javascript
app.put('/salesman/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const salesman = salesmen.find(s => s.id === id);
  
  if (salesman) {
    salesman.firstname = req.body.firstname;
    salesman.lastname = req.body.lastname;
    res.json(salesman);
  } else {
    res.status(404).send('Salesman not found');
  }
});
```

4.5. Delete a specific salesman:

**Note**: The `salesmen.splice(index, 1)` method removes the element at the specified index.

```javascript
app.delete('/salesman/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const index = salesmen.findIndex(s => s.id === id);
  
  if (index !== -1) {
    salesmen.splice(index, 1);
    res.send('Salesman deleted');
  } else {
    res.status(404).send('Salesman not found');
  }
});
```

5. Try it out:

**Note**: Start the server by running `node server.js`.

- To get all salesmen:\
  Open a browser and navigate to `http://localhost:3000/salesman`.
- To get a specific salesman:\
  Open a browser and navigate to `http://localhost:3000/salesman/1`.
- To create a new salesman:\
  Use a tool like [Postman](https://www.postman.com/) to send a POST request to `http://localhost:3000/salesman` with a JSON body.
- To update a specific salesman:\
  Use a tool like [Postman](https://www.postman.com/) to send a PUT request to `http://localhost:3000/salesman/1` with a JSON body.
- To delete a specific salesman:\
  Use a tool like [Postman](https://www.postman.com/) to send a DELETE request to `http://localhost:3000/salesman/1`.
- To test the REST API:\
  Use a tool like [Postman](https://www.postman.com/) to send GET, POST, PUT, and DELETE requests to the defined routes.

### Observer-Pattern with RxJS

#### 1. What is the Observer Pattern?

The **Observer Pattern** is a behavioral design pattern that defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically.

#### General Structure of the Observer Pattern

##### What is RxJS?

**RxJS** is a library for reactive programming using Observables, to make it easier to compose asynchronous or callback-based code. The idea is to treat events as streams of data that can be manipulated.

##### Key Concepts of RxJS

1. **Observable**:\
   Represents a collection of future values or events. You can subscribe to them to receive these values or events.
2. **Observer**:\
   An object that receives notifications from an Observable. Typically, it has three methods for handling the notifications: 
   - `next()`: Handles the next value in the sequence.
   - `error()`: Handles errors.
   - `complete()`: Handles the completion of the sequence.
3. **Subscription**:\
   Represents the execution of an Observable. It has a method `unsubscribe()` to stop the execution.
4. **Operators**:\
   Functions that allow you to manipulate the data emitted by Observables. The manipulation can include filtering, transforming, combining, etc.

##### Comparison with Observer Pattern

- **Observable** is equivalent to the **Subject** in the Observer Pattern.
- **Observer** is equivalent to the **Observer** in the Observer Pattern.
- **Subscription** is equivalent to the **ConcreteObserver** in the Observer Pattern.
- **Operators** are additional features provided by RxJS.

#### 2. How to implement the Observer Pattern with RxJS?

**Important**: If not already done, install RxJS by running `npm install rxjs`.

1. Create a new file (e.g. `observer.js`) and import RxJS:

```javascript
import { Observable } from 'rxjs';
``` 

2. Define the Observable:

```javascript
const observable = new Observable(subscriber => {
  subscriber.next('Hello');
  subscriber.next('World');
  subscriber.complete();
});
```

**Explanation**:\
The `Observable` constructor takes a function as an argument (referred to as *subscriber-function*).\
This *subscriber-function* is called whenever an Observer subscribes to the Observable.\
The `subscriber` object has methods like `next()`, `error()`, and `complete()` to handle the notifications.

3. Subscribe to the Observable:

```javascript
observable.subscribe({
  next: value => console.log(value),
  error: error => console.error(error),
  complete: () => console.log('Done')
});
```

**Explanation**:\
The `subscribe()` method is used to start the execution of the Observable.\
It takes an object with methods to handle the notifications.\
In this case, we are logging the values, errors, and completion messages.

**Note**: The output will be:
```
Hello
World
Done
```

Because we defined the Observable to emit two values ('Hello' and 'World') and then complete if subscribed to.
The `Done` is logged because we defined the observer to handle the completion with `console.log('Done')`. 

## Source

1. [RxJS - General](https://blog.logrocket.com/guide-rxjs-observables/#:~:text=Observables%2C%20operators%2C%20and%20observers%20are%20three%20fundamental%20concepts,subscribing%20to%20the%20stream%20and%20handling%20its%20emissions.)
2. [RxJS - Observable](https://rxjs.dev/guide/observable)
3. [RxJS - Observer](https://rxjs.dev/guide/observer)
4. [REST - General](https://restfulapi.net/)
5. [Object Creation in JavaScript](https://www.digitalocean.com/community/tutorials/understanding-objects-in-javascript)
6. [Object Creation in JavaScript](https://www.w3schools.com/js/js_objects.asp)
7. [Object Creation in JavaScript](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Object/)
8. [REST API in Express.js](https://expressjs.com/en/starter/hello-world.html)
9. [Cookies](https://medium.com/@ethantcollins98/setting-and-using-cookies-with-a-node-js-express-server-49479673d043)

