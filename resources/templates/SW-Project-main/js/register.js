const router = require('express').Router();
const path = require('path');

router.post('/register',(req,res)=>{
    const mysql = require('../database')();
    const connection = mysql.init();
    mysql.db_open(connection);
    const userid = req.body.id;
    const userpw = req.body.password;
    const username = req.body.username;
    const phone = req.body.phoneNumber
    if (userid && userpw && username && phone){
        connection.query('SELECT * FROM USERS WHERE id = ? OR username = ?',[userid,username], (err, results, fields) =>{
            if (err){
                throw err;
            }else if (results.length<=0){
                connection.query('INSERT INTO USERS (id, password, username) VALUES(?,?,?)',[userid,userpw,username], (err,data)=>{
                    if (err){
                        console.log(err);
                    }
                });
                res.send(`<script type="text/javascript">alert("${username}님 환영합니다!"); document.location.href="login_signup.html";</script>`);
            }
            else if (userid==results[0].ID){
                res.send('<script type="text/javascript">alert("이미 존재하는 아이디입니다."); document.location.href="/register";</script>');
            }
            connection.end();
        });
    }else{
        res.send('<script type="text/javascript">alert("정보를 모두 입력해주세요."); document.location.href="/register";</script>');
        connection.end();
    }   
})
module.exports = router;