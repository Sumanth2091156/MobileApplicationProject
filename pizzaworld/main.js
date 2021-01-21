var mysql = require("mysql");
var express = require("express");
var bodyParser = require("body-parser");
const http = require('http');

// connection to mysql
var con = mysql.createConnection({
  host: "localhost",
  user: "root",
  password: "",
  database: "pizzaworld",
});




var app = express();
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

app.listen(3000, () => {
  console.log("Restful running on port 3000");
});

app.post("/register/", (req, res, next) => {
  var sqldata = req.body;
  var fname = sqldata.fname;
  var lname = sqldata.lname;
  var phone = sqldata.phone;
  var email = sqldata.email;
  var password = sqldata.password;
 
      var sql = "INSERT INTO customer(fname,lname,phone,email,password) VALUES (?,?,?,?,?)";
      var values = [fname,lname,phone,email,password];

      console.log(sql, values);

      con.query(sql, values, function (err, result, fields) {
        con.on("error", (err) => {
          console.log("[MySQL ERROR]", err);
        });
        res.json("Success");
        console.log("Registered successfully" + sqldata);
      });
    
  });



app.post("/addpizza/", (req, res, next) => {
  var sqldata = req.body;
  var name = sqldata.name;
  var price = sqldata.price;
  var image = sqldata.image;
  var description = sqldata.description;
      var sql = "INSERT INTO pizza (name,price,image,description) VALUES (?,?,?,?)";
      var values = [name, price, image, description];

      console.log(sql, values);

      con.query(sql, values, function (err, result, fields) {
        con.on("error", (err) => {
          console.log("[MySQL ERROR]", err);
        });
        res.json("Pizza Added Successfully");
        console.log("Pizza Added Successfully" + sqldata);
      });
    
  });

  app.post("/feedback/", (req, res, next) => {
    var sqldata = req.body;
    var title = sqldata.title;
    var description = sqldata.description;
        var sql = "INSERT INTO feedback (title,description) VALUES (?,?)";
        var values = [title, description];
  
        console.log(sql, values);
  
        con.query(sql, values, function (err, result, fields) {
          con.on("error", (err) => {
            console.log("[MySQL ERROR]", err);
          });
          res.json("Feedback Added Successfully");
          console.log("Feedback Added Successfully" + sqldata);
        });
      
    });

app.post("/login/", (req, res, next) => {
    var sqldata = req.body;
    var email = sqldata.email;
    var password = sqldata.password;
  
    con.query("SELECT * FROM customer where email = ?", [email], function (
      err,
      result,
      fields
    ) {
      con.on("error", (err) => {
        console.log("[MySQL ERROR]", err);
      });
  
      if (result && result.length) {
        if (password == result[0].password) {
          res.json("Success");
        } else {
          res.json("Invalid user");
        }
      }
    });
  });

app.post("/adminlogin/", (req, res, next) => {
    var sqldata = req.body;
    var email = sqldata.email;
    var password = sqldata.password;
  
    con.query("SELECT * FROM admin where email = ?", [email], function (
      err,
      result,
      fields
    ) {
      con.on("error", (err) => {
        console.log("[MySQL ERROR]", err);
      });
  
      if (result && result.length) {
        if (password == result[0].password) {
          res.json("Success");
        } else {
          res.json("Invalid user");
        }
      }
    });
  });





  app.get('/getpizza', (req, res) => {
    
    
    con.query("SELECT * FROM pizza",(err, rows, fields) =>{
      if (!err) 
      res.send(rows)
      else
      console.log(err)

          });
  });
    




  
  



